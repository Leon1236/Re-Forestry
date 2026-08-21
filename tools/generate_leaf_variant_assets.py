#!/usr/bin/env python3
# Usage: python3 tools/generate_leaf_variant_assets.py [--apply]
# Writes blockstates/models/items/loot/tags for default, fruit-default, and decorative leaves.

from __future__ import annotations

import argparse
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/reforestry"
DATA = ROOT / "src/main/resources/data"
NS = "reforestry"

# Mirrors LeafBlockStateResolver.SPECIES_TO_GROUP (path without tree_ prefix)
SPECIES_TO_GROUP = {
    "acacia": "acacia",
    "balsa": "acacia",
    "baobab": "acacia",
    "beech": "oak",
    "birch": "birch",
    "cherry": "cherry",
    "chestnut": "birch",
    "cocobolo": "mangrove",
    "coconut": "palm",
    "dark_oak": "oak",
    "date": "palm",
    "desert_acacia": "acacia",
    "dogwood": "dogwood",
    "ebony": "jungle",
    "elm": "oak",
    "feijoa": "azalea",
    "fir": "spruce",
    "giant_sequoia": "spruce",
    "ginkgo": "ginkgo",
    "hill_cherry": "birch",
    "ipe": "ipe",
    "jacaranda": "jacaranda",
    "jungle": "jungle",
    "kapok": "jungle",
    "kauri": "spruce",
    "larch": "spruce",
    "lemon": "azalea",
    "lime": "birch",
    "macrocarpa": "spruce",
    "mahoe": "oak",
    "mahogany": "jungle",
    "maple": "maple",
    "oak": "oak",
    "olive": "willow",
    "orange": "azalea",
    "padauk": "acacia",
    "papaya": "palm",
    "pear": "oak",
    "pewen": "spruce",
    "pine": "spruce",
    "plum": "oak",
    "poplar": "birch",
    "sequoia": "spruce",
    "sipiri": "mangrove",
    "spruce": "spruce",
    "teak": "jungle",
    "walnut": "acacia",
    "wenge": "oak",
    "willow": "willow",
    "zebrawood": "jungle",
}

# Escritoire colors from DefaultTreeSpecies (species path -> RGB)
# Filled at runtime from local DefaultTreeSpecies if present; fallback white.
COLORS: dict[str, int] = {}

SUFFIXES = ("default_leaves", "default_leaves_fruit", "decorative_leaves")


def load_escritoire_colors() -> dict[str, int]:
    path = ROOT / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java"
    text = path.read_text()
    import re

    colors = {}
    for match in re.finditer(
        r'registration\.registerSpecies\(ReForestry\.id\("([^"]+)"\),\s*"[^"]+",\s*"[^"]+",\s*(?:true|false),\s*(0x[0-9a-fA-F]+|\d+)',
        text,
    ):
        colors[match.group(1)] = int(match.group(2), 0)
    return colors


def to_signed_argb(rgb: int) -> int:
    argb = 0xFF000000 | (rgb & 0xFFFFFF)
    return argb - 0x100000000 if argb >= 0x80000000 else argb


def block_id(species_path: str, suffix: str) -> str:
    return f"{species_path}_{suffix}"


def write_json(path: Path, data, apply: bool) -> None:
    text = json.dumps(data, indent=2) + "\n"
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text)


def loot_table(item_id: str, species_path: str) -> dict:
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "entries": [
                    {
                        "type": "minecraft:alternatives",
                        "children": [
                            {
                                "type": "minecraft:item",
                                "conditions": [
                                    {
                                        "condition": "minecraft:any_of",
                                        "terms": [
                                            {
                                                "condition": "minecraft:match_tool",
                                                "predicate": {"items": "minecraft:shears"},
                                            },
                                            {
                                                "condition": "minecraft:match_tool",
                                                "predicate": {
                                                    "predicates": {
                                                        "minecraft:enchantments": [
                                                            {
                                                                "enchantments": "minecraft:silk_touch",
                                                                "levels": {"min": 1},
                                                            }
                                                        ]
                                                    }
                                                },
                                            },
                                        ],
                                    }
                                ],
                                "name": f"{NS}:{item_id}",
                            },
                            {
                                "type": "minecraft:item",
                                "conditions": [
                                    {"condition": "minecraft:survives_explosion"},
                                    {
                                        "chances": [0.05, 0.0625, 0.083333336, 0.1],
                                        "condition": "minecraft:table_bonus",
                                        "enchantment": "minecraft:fortune",
                                    },
                                ],
                                "name": f"{NS}:sapling",
                            },
                        ],
                    }
                ],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"{NS}:blocks/{item_id}",
    }


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()
    colors = load_escritoire_colors()
    leaf_ids: list[str] = []
    count = 0

    for species_path, group in SPECIES_TO_GROUP.items():
        full_species = f"tree_{species_path}"
        model = f"{NS}:block/leaves/{group}"
        rgb = colors.get(full_species, 0xFFFFFF)
        for suffix in SUFFIXES:
            bid = block_id(full_species, suffix)
            leaf_ids.append(f"{NS}:{bid}")

            write_json(
                ASSETS / "blockstates" / f"{bid}.json",
                {"variants": {"": {"model": model}}},
                args.apply,
            )
            write_json(
                ASSETS / "models" / "item" / f"{bid}.json",
                {"parent": model},
                args.apply,
            )
            write_json(
                ASSETS / "items" / f"{bid}.json",
                {
                    "model": {
                        "type": "minecraft:model",
                        "model": f"{NS}:item/{bid}",
                        "tints": [{"type": "minecraft:constant", "value": to_signed_argb(rgb)}],
                    }
                },
                args.apply,
            )
            write_json(
                DATA / NS / "loot_table" / "blocks" / f"{bid}.json",
                loot_table(bid, full_species),
                args.apply,
            )
            count += 1

    tag = {"values": ["reforestry:leaves"] + leaf_ids}
    write_json(DATA / "minecraft" / "tags" / "block" / "leaves.json", tag, args.apply)
    write_json(DATA / "minecraft" / "tags" / "item" / "leaves.json", {"values": leaf_ids}, args.apply)

    print(f"{'APPLY' if args.apply else 'DRY'}: {count} leaf variant asset sets, {len(leaf_ids)} tag entries")
    if not args.apply:
        print("dry run only; pass --apply to write")


if __name__ == "__main__":
    main()
