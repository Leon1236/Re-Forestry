#!/usr/bin/env python3
"""Extract the 21 planter recipes, loot, unlocks, blockstates, and item models from CE.

Ids are SUFFIX ({type}_managed / {type}_manual). Remaps forestry: → reforestry:,
CE SUFFIX electron tubes (bronze_electron_tube) to local PREFIX (electron_tube_bronze),
CE SUFFIX circuit boards (basic_circuit_board) to local PREFIX (circuit_board_basic),
and forge: tags → c: tags.

Usage: python3 tools/extract_planter_recipes.py --root . --apply
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

PLANTER_TYPES = (
    "arboretum",
    "farm_crops",
    "farm_mushroom",
    "farm_gourd",
    "farm_nether",
    "farm_ender",
    "peat_bog",
)
PLANTER_MODES = ("managed", "manual")
EXPECTED_RECIPES = 21
EXPECTED_BLOCKS = 14

TUBE_MATERIAL = {
    "golden": "gold",
    "diamantine": "diamond",
    "apatine": "apatite",
    "blazing": "blaze",
}

TUBE_RE = re.compile(r"(?:forestry|reforestry):([a-z0-9_]+)_electron_tube")
CIRCUIT_RE = re.compile(r"(?:forestry|reforestry):([a-z0-9_]+)_circuit_board")


def ce_farms_generated() -> Path:
    found = latest_clones().get(CE_REPO)
    if found is None or not found.is_dir():
        found = CE_FALLBACK
    return found / "src" / "generated" / "resources_farms"


def is_planter_id(name: str) -> bool:
    return name.endswith("_managed") or name.endswith("_manual") or name.endswith("_managed_from_manual")


def planter_type(block_id: str) -> str:
    for mode in PLANTER_MODES:
        suffix = f"_{mode}"
        if block_id.endswith(suffix):
            return block_id[: -len(suffix)]
    raise ValueError(f"not a planter block id: {block_id}")


def remap_text(text: str) -> str:
    rewritten, _, _ = rewrite_text(text, OLD_NS, NEW_NS)
    rewritten = rewritten.replace("forge:", "c:")

    def tube(match: re.Match[str]) -> str:
        material = TUBE_MATERIAL.get(match.group(1), match.group(1))
        return f"{NEW_NS}:electron_tube_{material}"

    def circuit(match: re.Match[str]) -> str:
        return f"{NEW_NS}:circuit_board_{match.group(1)}"

    rewritten = TUBE_RE.sub(tube, rewritten)
    return CIRCUIT_RE.sub(circuit, rewritten)


def write_json_text(path: Path, text: str, apply: bool) -> None:
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text if text.endswith("\n") else text + "\n", encoding="utf-8")


def write_json(path: Path, data: object, apply: bool) -> None:
    write_json_text(path, json.dumps(data, indent=2), apply)


def copy_filtered(src_dir: Path, dest_dir: Path, apply: bool) -> list[str]:
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)
    copied: list[str] = []
    for src in sorted(src_dir.glob("*.json")):
        if not is_planter_id(src.stem):
            continue
        text = remap_text(src.read_text(encoding="utf-8"))
        write_json_text(dest_dir / src.name, text, apply)
        copied.append(src.stem)
    return copied


def write_blockstates_and_item_models(assets: Path, apply: bool) -> list[str]:
    written: list[str] = []
    for planter_type_name in PLANTER_TYPES:
        model = f"{NEW_NS}:block/{planter_type_name}"
        blockstate = {
            "variants": {
                "facing=north": {"model": model},
                "facing=south": {"model": model, "y": 180},
                "facing=west": {"model": model, "y": 270},
                "facing=east": {"model": model, "y": 90},
            }
        }
        item_model = {"parent": model}
        for mode in PLANTER_MODES:
            block_id = f"{planter_type_name}_{mode}"
            write_json(assets / "blockstates" / f"{block_id}.json", blockstate, apply)
            write_json(assets / "models" / "item" / f"{block_id}.json", item_model, apply)
            written.append(block_id)
    return written


def merge_pickaxe_tag(dest: Path, block_ids: list[str], apply: bool) -> int:
    existing = json.loads(dest.read_text(encoding="utf-8")) if dest.is_file() else {"values": []}
    values = list(existing.get("values", []))
    seen = set(values)
    added = 0
    for block_id in block_ids:
        item = f"{NEW_NS}:{block_id}"
        if item not in seen:
            values.append(item)
            seen.add(item)
            added += 1
    existing["values"] = values
    write_json(dest, existing, apply)
    return added


def assert_craft_tubes(recipe_dir: Path, apply: bool) -> None:
    if not apply:
        return
    expected = {
        "farm_crops_managed": "electron_tube_bronze",
        "arboretum_managed": "electron_tube_gold",
        "farm_mushroom_managed": "electron_tube_apatite",
        "farm_gourd_managed": "electron_tube_lapis",
        "farm_nether_managed": "electron_tube_blaze",
        "farm_ender_managed": "electron_tube_ender",
        "peat_bog_managed": "electron_tube_obsidian",
    }
    for recipe_name, tube_id in expected.items():
        recipe = json.loads((recipe_dir / f"{recipe_name}.json").read_text(encoding="utf-8"))
        tube = recipe["key"]["T"]["item"]
        if tube != f"{NEW_NS}:{tube_id}":
            print(
                f"error: {recipe_name} craft tube is {tube}, expected {NEW_NS}:{tube_id} "
                "(craft tube, not the multifarm circuit tube)",
                file=sys.stderr,
            )
            sys.exit(1)
        board = recipe["key"]["B"]["item"]
        if board != f"{NEW_NS}:circuit_board_basic":
            print(f"error: {recipe_name} circuit board is {board}, expected {NEW_NS}:circuit_board_basic", file=sys.stderr)
            sys.exit(1)


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    ce = ce_farms_generated()
    data = root / "src" / "main" / "resources" / "data"
    assets = root / "src" / "main" / "resources" / "assets" / NEW_NS
    recipe_dir = data / NEW_NS / "recipe"

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting planter recipes from {ce}")

    recipes = copy_filtered(ce / "data" / OLD_NS / "recipe", recipe_dir, args.apply)
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
    blocks = write_blockstates_and_item_models(assets, args.apply)
    pickaxe_added = merge_pickaxe_tag(
        data / "minecraft" / "tags" / "block" / "mineable" / "pickaxe.json",
        blocks,
        args.apply,
    )

    assert_craft_tubes(recipe_dir, args.apply)

    print(
        f"recipes {len(recipes)}, loot {len(loot)}, advancements {len(advancements)}, "
        f"blocks {len(blocks)}, pickaxe +{pickaxe_added}"
    )
    if len(recipes) != EXPECTED_RECIPES:
        print(f"error: expected {EXPECTED_RECIPES} recipes, got {len(recipes)}", file=sys.stderr)
        sys.exit(1)
    if len(blocks) != EXPECTED_BLOCKS:
        print(f"error: expected {EXPECTED_BLOCKS} block ids, got {len(blocks)}", file=sys.stderr)
        sys.exit(1)
    if "farm_crops_managed" not in recipes or "managed_farm_crops" in recipes:
        print("error: planter id mapping is PREFIX instead of SUFFIX", file=sys.stderr)
        sys.exit(1)
    if not args.apply:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
