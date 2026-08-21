#!/usr/bin/env python3
"""Parse every bee mutation from DefaultBeeSpecies.java and write queries/bee-mutations.json + queries/bee-breeding-guide.md.

Usage:
  python3 tools/export_bee_mutations.py
  python3 tools/export_bee_mutations.py --dry-run
  python3 tools/export_bee_mutations.py --json path/out.json --md path/out.md
"""
from __future__ import annotations

import argparse
import json
import re
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DEFAULT_SOURCE = ROOT / "src/main/java/com/leon1236/reforestry/apiculture/genetics/DefaultBeeSpecies.java"
DEFAULT_JSON = ROOT / "queries/bee-mutations.json"
DEFAULT_MD = ROOT / "queries/bee-breeding-guide.md"
NS = "reforestry"

REGISTER_RE = re.compile(
    r'registration\.registerSpecies\(ReForestry\.id\("(bee_\w+)"\),\s*"([^"]*)",\s*"([^"]*)",\s*(true|false),\s*(0x[0-9a-fA-F]+)\)'
)
MUTATION_RE = re.compile(
    r'mutations\.add\(\s*ReForestry\.id\("(bee_\w+)"\)\s*,\s*ReForestry\.id\("(bee_\w+)"\)\s*,\s*([\d.]+)f\s*\)'
    r'((?:\s*\.\w+\((?:[^()]|\([^()]*\))*\))*)\s*;'
)
CHAIN_CALL_RE = re.compile(r"\.(\w+)\(((?:[^()]|\([^()]*\))*)\)")
SECRET_RE = re.compile(r"\.setSecret\(true\)")


def full_id(short: str) -> str:
    return f"{NS}:{short}"


def short_name(species_id: str) -> str:
    if species_id.startswith(f"{NS}:"):
        return species_id.split(":", 1)[1]
    return species_id


def display_name(short: str) -> str:
    if short.startswith("bee_"):
        return short[4:].replace("_", " ").title()
    return short.replace("_", " ").title()


def find_matching_brace(text: str, open_index: int) -> int:
    depth = 0
    for i in range(open_index, len(text)):
        if text[i] == "{":
            depth += 1
        elif text[i] == "}":
            depth -= 1
            if depth == 0:
                return i
    raise ValueError("unbalanced braces")


def find_statement_end(text: str, start_index: int) -> int:
    depth = 0
    for i in range(start_index, len(text)):
        ch = text[i]
        if ch in "({":
            depth += 1
        elif ch in ")}":
            depth -= 1
        elif ch == ";" and depth == 0:
            return i
    raise ValueError("no statement-ending semicolon found")


def simplify_tag(args: str) -> str:
    args = args.strip()
    if "." in args:
        parts = args.split(".")
        if len(parts) >= 2:
            return f"{parts[-2]}.{parts[-1]}"
        return parts[-1]
    return args


def parse_conditions(chain_text: str) -> list[dict]:
    conditions: list[dict] = []
    for match in CHAIN_CALL_RE.finditer(chain_text or ""):
        name, args = match.group(1), match.group(2).strip()
        if name == "restrictBiomeType":
            conditions.append({"type": "biome", "tag": simplify_tag(args)})
        elif name == "restrictDateRange":
            parts = [p.strip() for p in args.split(",")]
            if len(parts) == 4:
                conditions.append({
                    "type": "date_range",
                    "start_month": int(parts[0]),
                    "start_day": int(parts[1]),
                    "end_month": int(parts[2]),
                    "end_day": int(parts[3]),
                })
            else:
                conditions.append({"type": "date_range", "raw": args})
        elif name == "addMutationCondition":
            if "CaveDwelling" in args:
                conditions.append({"type": "cave_dwelling"})
            else:
                conditions.append({"type": "custom", "raw": args})
        elif name == "requireDay":
            conditions.append({"type": "daytime", "day": True})
        elif name == "requireNight":
            conditions.append({"type": "daytime", "day": False})
        elif name == "requireResource":
            conditions.append({"type": "resource", "raw": args})
        else:
            conditions.append({"type": name, "raw": args})
    return conditions


def format_condition(cond: dict) -> str:
    kind = cond.get("type")
    if kind == "biome":
        return f"biome:{cond['tag']}"
    if kind == "date_range":
        if "start_month" in cond:
            return (
                f"date {cond['start_month']}/{cond['start_day']}"
                f"–{cond['end_month']}/{cond['end_day']}"
            )
        return f"date:{cond.get('raw', '?')}"
    if kind == "cave_dwelling":
        return "cave dwelling"
    if kind == "daytime":
        return "day" if cond.get("day") else "night"
    if kind == "resource":
        return f"resource:{cond.get('raw', '?')}"
    return f"{kind}:{cond.get('raw', '')}".rstrip(":")


def parse_species_and_mutations(source: str) -> tuple[list[dict], list[dict]]:
    species: list[dict] = []
    mutations: list[dict] = []
    starts = [m.start() for m in REGISTER_RE.finditer(source)]
    starts.append(len(source))

    for idx in range(len(starts) - 1):
        match = REGISTER_RE.match(source, starts[idx])
        if not match:
            continue
        short, genus, epithet, dominant, color = match.groups()
        stmt_end = find_statement_end(source, starts[idx])
        block = source[starts[idx]:stmt_end + 1]
        entry = {
            "id": full_id(short),
            "short": short,
            "genus": genus,
            "species": epithet,
            "dominant": dominant == "true",
            "outline_color": color.lower(),
            "secret": bool(SECRET_RE.search(block)),
            "has_mutations": False,
        }
        species.append(entry)

        add_idx = block.find(".addMutations(mutations -> {")
        if add_idx == -1:
            continue
        brace_open = block.index("{", add_idx)
        brace_close = find_matching_brace(block, brace_open)
        mutations_text = block[brace_open + 1:brace_close]
        entry["has_mutations"] = True
        for mut in MUTATION_RE.finditer(mutations_text):
            parent_a, parent_b, chance, chain = mut.groups()
            mutations.append({
                "parent_a": full_id(parent_a),
                "parent_b": full_id(parent_b),
                "result": full_id(short),
                "chance_percent": float(chance),
                "conditions": parse_conditions(chain),
            })
    return species, mutations


def mutation_step(mut: dict) -> dict:
    return {
        "parents": [short_name(mut["parent_a"]), short_name(mut["parent_b"])],
        "result": short_name(mut["result"]),
        "chance": mut["chance_percent"],
        "conditions": [format_condition(c) for c in mut["conditions"]],
    }


def merge_step_lists(steps_a: list[dict], steps_b: list[dict]) -> list[dict]:
    seen: set[str] = set()
    merged: list[dict] = []
    for step in steps_a + steps_b:
        result = step["result"]
        if result in seen:
            continue
        merged.append(step)
        seen.add(result)
    return merged


def compute_paths(species: list[dict], mutations: list[dict]) -> tuple[dict, list[str], list[str]]:
    all_ids = {s["id"] for s in species}
    inbound: dict[str, list[dict]] = defaultdict(list)
    for mut in mutations:
        inbound[mut["result"]].append(mut)

    sources = sorted(s["id"] for s in species if s["id"] not in inbound)
    best_steps: dict[str, list[dict]] = {sid: [] for sid in sources}
    best_len: dict[str, int] = {sid: 0 for sid in sources}

    changed = True
    while changed:
        changed = False
        for mut in mutations:
            a, b, result = mut["parent_a"], mut["parent_b"], mut["result"]
            if a not in best_steps or b not in best_steps:
                continue
            candidate = merge_step_lists(best_steps[a], best_steps[b]) + [mutation_step(mut)]
            new_len = len(candidate)
            current = best_len.get(result)
            better = current is None or new_len < current
            if not better and current == new_len:
                old_chance = best_steps[result][-1]["chance"] if best_steps.get(result) else -1
                better = mut["chance_percent"] > old_chance
            if better:
                best_len[result] = new_len
                best_steps[result] = candidate
                changed = True

    paths: dict[str, dict] = {}
    for sid in sorted(all_ids):
        if sid in sources:
            paths[sid] = {"depth": 0, "source": True, "steps": []}
            continue
        if sid not in best_steps:
            continue
        steps = best_steps[sid]
        paths[sid] = {
            "depth": len(steps),
            "source": False,
            "steps": steps,
            "recipes": [
                {
                    "parents": [short_name(m["parent_a"]), short_name(m["parent_b"])],
                    "chance": m["chance_percent"],
                    "conditions": [format_condition(c) for c in m["conditions"]],
                }
                for m in inbound[sid]
            ],
        }

    unreachable = sorted(sid for sid in all_ids if sid not in best_steps)
    return paths, sources, unreachable


def build_payload(source_path: Path, species: list[dict], mutations: list[dict],
                  paths: dict, sources: list[str], unreachable: list[str]) -> dict:
    return {
        "source": str(source_path.as_posix()),
        "species_count": len(species),
        "mutation_count": len(mutations),
        "source_species": sources,
        "unreachable_species": unreachable,
        "species": species,
        "mutations": mutations,
        "paths": paths,
    }


def write_markdown(path: Path, payload: dict) -> None:
    lines: list[str] = []
    lines.append("# Bee breeding guide (Re-Forestry)")
    lines.append("")
    lines.append(
        f"Extracted from `{payload['source']}` — "
        f"**{payload['species_count']}** species, **{payload['mutation_count']}** mutations."
    )
    lines.append("")
    lines.append("## Source / hive bees")
    lines.append("")
    lines.append("These species have no inbound mutations (found in hives, special sources, or endgame):")
    lines.append("")
    for sid in payload["source_species"]:
        lines.append(f"- `{short_name(sid)}` ({display_name(short_name(sid))})")
    lines.append("")

    if payload["unreachable_species"]:
        lines.append("## Unreachable species")
        lines.append("")
        lines.append("Registered but not reachable from source bees via mutations:")
        lines.append("")
        for sid in payload["unreachable_species"]:
            lines.append(f"- `{short_name(sid)}`")
        lines.append("")

    by_result: dict[str, list[dict]] = defaultdict(list)
    for mut in payload["mutations"]:
        by_result[mut["result"]].append(mut)

    lines.append("## All mutations (by result)")
    lines.append("")
    for result in sorted(by_result.keys()):
        lines.append(f"### {display_name(short_name(result))} (`{short_name(result)}`)")
        lines.append("")
        for mut in by_result[result]:
            a = short_name(mut["parent_a"])
            b = short_name(mut["parent_b"])
            chance = mut["chance_percent"]
            conds = ", ".join(format_condition(c) for c in mut["conditions"])
            suffix = f" — _{conds}_" if conds else ""
            lines.append(f"- `{a}` × `{b}` → `{short_name(result)}` ({chance:g}%){suffix}")
        lines.append("")

    lines.append("## How to breed (shortest path from source bees)")
    lines.append("")
    lines.append(
        "Each path is one shortest sequence of mutations you must perform "
        "(shared ancestors counted once). Depth equals the number of steps in that path."
    )
    lines.append("")

    breedable = [
        (sid, info) for sid, info in sorted(payload["paths"].items())
        if not info.get("source") and info.get("steps")
    ]
    for sid, info in breedable:
        lines.append(f"### {display_name(short_name(sid))} — depth {info['depth']}")
        lines.append("")
        recipes = info.get("recipes") or []
        if len(recipes) > 1:
            lines.append("Direct recipes (any one works as the final step):")
            lines.append("")
            for recipe in recipes:
                a, b = recipe["parents"]
                conds = ", ".join(recipe.get("conditions") or [])
                suffix = f" — _{conds}_" if conds else ""
                lines.append(f"- `{a}` × `{b}` ({recipe['chance']:g}%){suffix}")
            lines.append("")
            lines.append("Example shortest path:")
            lines.append("")
        for i, step in enumerate(info["steps"], 1):
            a, b = step["parents"]
            conds = ", ".join(step.get("conditions") or [])
            suffix = f" ({conds})" if conds else ""
            lines.append(
                f"{i}. `{a}` × `{b}` → `{step['result']}` ({step['chance']:g}%){suffix}"
            )
        lines.append("")

    path.write_text("\n".join(lines) + "\n", encoding="utf-8")


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--source", type=Path, default=DEFAULT_SOURCE, help="DefaultBeeSpecies.java path")
    parser.add_argument("--json", type=Path, default=DEFAULT_JSON, help="output JSON path")
    parser.add_argument("--md", type=Path, default=DEFAULT_MD, help="output Markdown path")
    parser.add_argument("--dry-run", action="store_true", help="parse and print summary only; do not write files")
    args = parser.parse_args()

    source_text = args.source.read_text(encoding="utf-8")
    species, mutations = parse_species_and_mutations(source_text)
    paths, sources, unreachable = compute_paths(species, mutations)
    payload = build_payload(args.source.relative_to(ROOT) if args.source.is_relative_to(ROOT) else args.source,
                            species, mutations, paths, sources, unreachable)

    print(f"species: {payload['species_count']}")
    print(f"mutations: {payload['mutation_count']}")
    print(f"source bees: {len(sources)}")
    print(f"unreachable: {len(unreachable)}")
    if unreachable:
        for sid in unreachable:
            print(f"  unreachable: {sid}", file=sys.stderr)

    noble = next((m for m in mutations if m["result"] == full_id("bee_noble")), None)
    sinister = [m for m in mutations if m["result"] == full_id("bee_sinister")]
    leporine = next((m for m in mutations if m["result"] == full_id("bee_leporine")), None)
    if noble:
        print(f"check noble: {short_name(noble['parent_a'])} × {short_name(noble['parent_b'])} @ {noble['chance_percent']:g}%")
    if sinister:
        tags = [c.get("tag") for m in sinister for c in m["conditions"] if c.get("type") == "biome"]
        print(f"check sinister: {len(sinister)} recipes, biomes={tags}")
    if leporine:
        print(f"check leporine: conditions={leporine['conditions']}")

    if args.dry_run:
        print("dry run — no files written")
        return 0

    args.json.parent.mkdir(parents=True, exist_ok=True)
    args.md.parent.mkdir(parents=True, exist_ok=True)
    args.json.write_text(json.dumps(payload, indent=2) + "\n", encoding="utf-8")
    write_markdown(args.md, payload)
    print(f"wrote {args.json}")
    print(f"wrote {args.md}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
