#!/usr/bin/env python3
"""Extract the 55 multifarm structure recipes, loot, tags, and recipe unlocks from CE.

Skips planter recipes (*_managed, *_manual). Remaps forestry: → reforestry:,
CE SUFFIX electron tubes (tin_electron_tube) to local PREFIX (electron_tube_tin),
and forge: tags → c: tags.

Usage: python3 tools/extract_farm_recipes.py --root . --apply
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

from reference_repos import ROOT, latest_clones
from rename_namespace import rewrite_text

OLD_NS = "forestry"
NEW_NS = "reforestry"
CE_REPO = "thedarkcolour-ForestryCE"
CE_FALLBACK = ROOT / "MarkDown_Maker" / "Finished_github_clone" / "2026-08-17" / "thedarkcolour-ForestryCE"

TUBE_MATERIAL = {
    "golden": "gold",
    "diamantine": "diamond",
    "apatine": "apatite",
    "blazing": "blaze",
}

TUBE_RE = re.compile(r"(?:forestry|reforestry):([a-z0-9_]+)_electron_tube")


def ce_farms_generated() -> Path:
    found = latest_clones().get(CE_REPO)
    if found is None or not found.is_dir():
        found = CE_FALLBACK
    return found / "src" / "generated" / "resources_farms"


def is_multifarm_structure(name: str) -> bool:
    return "_farm_" in name and "managed" not in name and "manual" not in name


def remap_text(text: str) -> str:
    rewritten, _, _ = rewrite_text(text, OLD_NS, NEW_NS)
    rewritten = rewritten.replace("forge:", "c:")

    def tube(match: re.Match[str]) -> str:
        material = TUBE_MATERIAL.get(match.group(1), match.group(1))
        return f"{NEW_NS}:electron_tube_{material}"

    return TUBE_RE.sub(tube, rewritten)


def write_json_text(path: Path, text: str, apply: bool) -> None:
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text if text.endswith("\n") else text + "\n", encoding="utf-8")


def copy_filtered(src_dir: Path, dest_dir: Path, apply: bool) -> list[str]:
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)
    copied: list[str] = []
    for src in sorted(src_dir.glob("*.json")):
        if not is_multifarm_structure(src.stem):
            continue
        text = remap_text(src.read_text(encoding="utf-8"))
        write_json_text(dest_dir / src.name, text, apply)
        copied.append(src.stem)
    return copied


def merge_pickaxe_tag(src: Path, dest: Path, apply: bool) -> int:
    ce = json.loads(remap_text(src.read_text(encoding="utf-8")))
    existing = json.loads(dest.read_text(encoding="utf-8")) if dest.is_file() else {"values": []}
    values = list(existing.get("values", []))
    seen = set(values)
    added = 0
    for item in ce.get("values", []):
        if item not in seen:
            values.append(item)
            seen.add(item)
            added += 1
    existing["values"] = values
    write_json_text(dest, json.dumps(existing, indent=2), apply)
    return added


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    ce = ce_farms_generated()
    data = root / "src" / "main" / "resources" / "data"

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting farm structure recipes from {ce}")

    recipes = copy_filtered(
        ce / "data" / OLD_NS / "recipe",
        data / NEW_NS / "recipe",
        args.apply,
    )
    loot = copy_filtered(
        ce / "data" / OLD_NS / "loot_table" / "blocks",
        data / NEW_NS / "loot_table" / "blocks",
        args.apply,
    )
    advancements = copy_filtered(
        ce / "data" / OLD_NS / "advancement" / "recipes" / "misc",
        data / NEW_NS / "advancement" / "recipes" / "misc",
        args.apply,
    )

    farm_base_src = ce / "data" / OLD_NS / "tags" / "block" / "valid_farm_base.json"
    farm_base_dest = data / NEW_NS / "tags" / "block" / "valid_farm_base.json"
    write_json_text(farm_base_dest, remap_text(farm_base_src.read_text(encoding="utf-8")), args.apply)

    pickaxe_added = merge_pickaxe_tag(
        ce / "data" / "minecraft" / "tags" / "block" / "mineable" / "pickaxe.json",
        data / "minecraft" / "tags" / "block" / "mineable" / "pickaxe.json",
        args.apply,
    )

    print(f"recipes {len(recipes)}, loot {len(loot)}, advancements {len(advancements)}, pickaxe +{pickaxe_added}")
    if len(recipes) != 55:
        print(f"error: expected 55 recipes, got {len(recipes)}", file=sys.stderr)
        sys.exit(1)
    if "stone_brick_farm_block" not in recipes or "stone_brick_farm_plain" in recipes:
        print("error: stone_brick_farm_block recipe mapping is wrong", file=sys.stderr)
        sys.exit(1)
    if not args.apply:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
