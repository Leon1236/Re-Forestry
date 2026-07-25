#!/usr/bin/env python3
"""ARB-11.9e — import missing arboriculture datapack JSON from Forestry CE.

Fills gaps left after wood-family import, boats, grafter, and charcoal:
  - vanilla fireproof recipes / loot / fireproof_*_logs tags
  - mangrove + pale_oak fireproof (local VanillaWoodType; not in CE 1.20.1)
  - pod + genetic leaves loot tables (Java getDrops; CE tables have no pools)
  - ash smelting + compost/fertilizer ash recipes
  - c:dusts/ash and c:gems/apatite (Forge → conventional common tags)
  - fireproof-plank → vanilla door recipes
  - note: genetic `reforestry:leaves` is block-only (no item); do not add to item #minecraft:leaves

Reuses recipe transform rules from tools/import_ce_generated.py.

Usage:
  python3 tools/import_arb_11_9e_datapack.py --root . --apply
"""
from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

ROOT_DEFAULT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(Path(__file__).resolve().parent))
from import_ce_generated import transform_recipe, rename_ns  # noqa: E402

OLD_NS = "forestry"
NEW_NS = "reforestry"

CE_VANILLA_WOODS = ["oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "cherry"]
EXTRA_VANILLA_WOODS = ["mangrove", "pale_oak"]
ALL_VANILLA_WOODS = CE_VANILLA_WOODS + EXTRA_VANILLA_WOODS

FIREPROOF_RECIPE_KINDS = [
    "fireproof_planks",
    "fireproof_slab",
    "fireproof_stairs",
    "fireproof_fence",
    "fireproof_fence_gate",
    "fireproof_wood",
    "fireproof_stripped_wood",
]

FIREPROOF_LOOT_KINDS = [
    "fireproof_log",
    "fireproof_stripped_log",
    "fireproof_wood",
    "fireproof_stripped_wood",
    "fireproof_planks",
    "fireproof_slab",
    "fireproof_stairs",
    "fireproof_fence",
    "fireproof_fence_gate",
]

POD_IDS = ["pods_dates", "pods_papaya", "pods_coconut", "pods_cocoa"]

TAG_REMAP = {
    "#forge:dusts/ash": "#c:dusts/ash",
    "#forge:gems/apatite": "#c:gems/apatite",
}


def write_json(path: Path, data, apply: bool, log, label: str):
    text = json.dumps(data, indent=2) + "\n"
    log(f"{label} {path}")
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text, encoding="utf-8")


def remap_common_tags(data):
    raw = json.dumps(data)
    for old, new in TAG_REMAP.items():
        raw = raw.replace(old, new)
    return json.loads(raw)


def transform_smelting(data):
    data = dict(data)
    data.pop("show_notification", None)
    data.pop("cookingtime", None)
    if "ingredient" in data:
        ing = data["ingredient"]
        if isinstance(ing, dict):
            if "item" in ing:
                data["ingredient"] = rename_ns(ing["item"])
            elif "tag" in ing:
                data["ingredient"] = "#" + rename_ns(ing["tag"])
        else:
            data["ingredient"] = rename_ns(str(ing))
    result = data.get("result")
    if isinstance(result, str):
        data["result"] = {"id": rename_ns(result)}
    elif isinstance(result, dict) and "item" in result:
        out = dict(result)
        out["id"] = rename_ns(out.pop("item"))
        data["result"] = out
    return json.loads(rename_ns(json.dumps(data)))


def transform_any_recipe(data):
    if data.get("type") == "minecraft:smelting":
        return remap_common_tags(transform_smelting(data))
    return remap_common_tags(transform_recipe(data))


def substitute_wood(text: str, from_wood: str, to_wood: str) -> str:
    # Boundary-aware replaces. A naive `oak_fireproof` → `pale_oak_fireproof`
    # pass also matches inside already-substituted `pale_oak_fireproof`
    # and produces `pale_pale_oak_*`.
    text = text.replace(f"{OLD_NS}:{from_wood}_", f"{OLD_NS}:{to_wood}_")
    fw = re.escape(from_wood)
    text = re.sub(rf"(?<![A-Za-z0-9_])fireproof_{fw}_", f"fireproof_{to_wood}_", text)
    text = re.sub(rf"(?<![A-Za-z0-9_])fireproof_{fw}(?![A-Za-z0-9_])", f"fireproof_{to_wood}", text)
    text = re.sub(rf"(?<![A-Za-z0-9_]){fw}_fireproof", f"{to_wood}_fireproof", text)
    return text


def import_ce_file(src: Path, dst: Path, apply: bool, log, label: str, transform):
    if not src.is_file():
        return False
    data = transform(json.loads(src.read_text(encoding="utf-8")))
    write_json(dst, data, apply, log, label)
    return True


def synthesize_from_oak(oak_src: Path, wood: str, dst: Path, apply: bool, log, label: str, transform):
    if not oak_src.is_file():
        return False
    raw = substitute_wood(oak_src.read_text(encoding="utf-8"), "oak", wood)
    data = transform(json.loads(raw))
    write_json(dst, data, apply, log, label)
    return True


def ensure_tag_values(path: Path, values: list[str], apply: bool, log):
    existing = {"values": []}
    if path.is_file():
        existing = json.loads(path.read_text(encoding="utf-8"))
    vals = list(existing.get("values", []))
    changed = False
    for v in values:
        if v not in vals:
            vals.append(v)
            changed = True
    if not changed and path.is_file():
        return False
    existing["values"] = vals
    write_json(path, existing, apply, log, "TAG FIX  ")
    return True


def remove_forge_biome_modifier(root: Path, apply: bool, log):
    forge_dir = root / "src/main/resources/data/reforestry/forge"
    biome = forge_dir / "biome_modifier"
    removed = 0
    if biome.is_dir():
        leftover = list(biome.iterdir())
        if leftover:
            log(f"WARN     forge/biome_modifier not empty: {[p.name for p in leftover]}")
        else:
            log(f"REMOVE   {biome}")
            if apply:
                biome.rmdir()
            removed += 1
    if forge_dir.is_dir() and not any(forge_dir.iterdir()):
        log(f"REMOVE   {forge_dir}")
        if apply:
            forge_dir.rmdir()
        removed += 1
    return removed


def run(root: Path, ce_gen: Path, apply: bool, log):
    data_ce = ce_gen / "data" / OLD_NS
    data_out = root / "src/main/resources/data" / NEW_NS
    counts = {
        "recipes": 0,
        "loot": 0,
        "tags": 0,
        "common_tags": 0,
        "tag_fixes": 0,
        "removed_dirs": 0,
    }

    for wood in CE_VANILLA_WOODS:
        for kind in FIREPROOF_RECIPE_KINDS:
            name = f"{wood}_{kind}.json"
            if import_ce_file(
                data_ce / "recipes" / name,
                data_out / "recipe" / name,
                apply, log, "RECIPE   ", transform_any_recipe,
            ):
                counts["recipes"] += 1
        door = f"{wood}_door.json"
        if import_ce_file(
            data_ce / "recipes" / door,
            data_out / "recipe" / door,
            apply, log, "RECIPE   ", transform_any_recipe,
        ):
            counts["recipes"] += 1
        for kind in FIREPROOF_LOOT_KINDS:
            name = f"{wood}_{kind}.json"
            if import_ce_file(
                data_ce / "loot_tables" / "blocks" / name,
                data_out / "loot_table" / "blocks" / name,
                apply, log, "LOOT     ",
                lambda d: json.loads(rename_ns(json.dumps(d))),
            ):
                counts["loot"] += 1
        for side in ("blocks", "items"):
            tag_name = f"fireproof_{wood}_logs.json"
            src = data_ce / "tags" / side / tag_name
            out_side = "block" if side == "blocks" else "item"
            if import_ce_file(
                src,
                data_out / "tags" / out_side / tag_name,
                apply, log, "TAG      ",
                lambda d: json.loads(rename_ns(json.dumps(d))),
            ):
                counts["tags"] += 1

    oak_recipe_root = data_ce / "recipes"
    oak_loot_root = data_ce / "loot_tables" / "blocks"
    oak_tag_item = data_ce / "tags" / "items" / "fireproof_oak_logs.json"
    oak_tag_block = data_ce / "tags" / "blocks" / "fireproof_oak_logs.json"

    for wood in EXTRA_VANILLA_WOODS:
        for kind in FIREPROOF_RECIPE_KINDS:
            name = f"{wood}_{kind}.json"
            if synthesize_from_oak(
                oak_recipe_root / f"oak_{kind}.json",
                wood,
                data_out / "recipe" / name,
                apply, log, "RECIPE   ", transform_any_recipe,
            ):
                counts["recipes"] += 1
        door_src = oak_recipe_root / "oak_door.json"
        if door_src.is_file():
            door = transform_any_recipe(json.loads(door_src.read_text(encoding="utf-8")))
            key = door.get("key", {})
            for k, v in list(key.items()):
                if isinstance(v, str) and v.endswith("_fireproof_planks"):
                    key[k] = f"{NEW_NS}:{wood}_fireproof_planks"
            door["key"] = key
            result = dict(door.get("result", {}))
            result["id"] = f"minecraft:{wood}_door"
            door["result"] = result
            write_json(data_out / "recipe" / f"{wood}_door.json", door, apply, log, "RECIPE   ")
            counts["recipes"] += 1
        for kind in FIREPROOF_LOOT_KINDS:
            name = f"{wood}_{kind}.json"
            if synthesize_from_oak(
                oak_loot_root / f"oak_{kind}.json",
                wood,
                data_out / "loot_table" / "blocks" / name,
                apply, log, "LOOT     ",
                lambda d: json.loads(rename_ns(json.dumps(d))),
            ):
                counts["loot"] += 1
        for src, out_side in ((oak_tag_item, "item"), (oak_tag_block, "block")):
            if synthesize_from_oak(
                src,
                wood,
                data_out / "tags" / out_side / f"fireproof_{wood}_logs.json",
                apply, log, "TAG      ",
                lambda d: json.loads(rename_ns(json.dumps(d))),
            ):
                counts["tags"] += 1

    for pod in POD_IDS:
        if import_ce_file(
            data_ce / "loot_tables" / "blocks" / f"{pod}.json",
            data_out / "loot_table" / "blocks" / f"{pod}.json",
            apply, log, "LOOT     ",
            lambda d: json.loads(rename_ns(json.dumps(d))),
        ):
            counts["loot"] += 1

    if import_ce_file(
        data_ce / "loot_tables" / "blocks" / "leaves.json",
        data_out / "loot_table" / "blocks" / "leaves.json",
        apply, log, "LOOT     ",
        lambda d: json.loads(rename_ns(json.dumps(d))),
    ):
        counts["loot"] += 1

    for recipe_name in ("ash.json", "compost_ash.json", "fertilizer_ash.json", "fertilizer_apatite.json"):
        if import_ce_file(
            data_ce / "recipes" / recipe_name,
            data_out / "recipe" / recipe_name,
            apply, log, "RECIPE   ", transform_any_recipe,
        ):
            counts["recipes"] += 1

    common_item = root / "src/main/resources/data/c/tags/item"
    for rel, values in (
        ("dusts/ash.json", [f"{NEW_NS}:ash"]),
        ("gems/apatite.json", [f"{NEW_NS}:apatite"]),
    ):
        write_json(common_item / rel, {"values": values}, apply, log, "C-TAG    ")
        counts["common_tags"] += 1

    # Keep minecraft log tags aware of extra vanilla fireproof woods.
    logs_block = root / "src/main/resources/data/minecraft/tags/block/logs.json"
    extra_log_entries = []
    for wood in EXTRA_VANILLA_WOODS:
        for kind in ("fireproof_log", "fireproof_wood", "fireproof_stripped_log", "fireproof_stripped_wood"):
            extra_log_entries.append(f"{NEW_NS}:{wood}_{kind}")
    if ensure_tag_values(logs_block, extra_log_entries, apply, log):
        counts["tag_fixes"] += 1

    counts["removed_dirs"] = remove_forge_biome_modifier(root, apply, log)
    return counts


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=str(ROOT_DEFAULT))
    parser.add_argument(
        "--ce-gen-root",
        default="for textures only/thedarkcolour-ForestryCE/src/generated/resources",
    )
    parser.add_argument("--apply", action="store_true")
    parser.add_argument("--quiet", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    ce_gen = root / args.ce_gen_root
    if not ce_gen.is_dir():
        print(f"error: {ce_gen} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] ARB-11.9e datapack catch-up\n  ce: {ce_gen}\n")
    log = (lambda *_: None) if args.quiet else print
    counts = run(root, ce_gen, args.apply, log)
    print("\nsummary:")
    for key, value in counts.items():
        print(f"  {key}: {value}")
    if not args.apply:
        print("\ndry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
