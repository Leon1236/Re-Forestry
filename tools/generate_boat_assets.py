#!/usr/bin/env python3
"""Generate Forestry boat/chest-boat item models, item defs, recipes, and vanilla tags.

Usage:
  python3 tools/generate_boat_assets.py
"""
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
WOODS = [
    "larch", "teak", "acacia_desert", "lime", "chestnut", "wenge", "baobab", "sequoia",
    "kapok", "ebony", "elm", "mahogany", "balsa", "willow", "walnut", "greenheart", "hill_cherry",
    "mahoe", "poplar", "palm", "papaya", "pine", "plum", "maple", "citrus",
    "giganteum", "ipe", "padauk", "cocobolo", "fir", "coconut", "beech", "feijoa", "dogwood",
    "ginkgo", "jacaranda", "pewen", "macrocarpa", "olive", "orange", "pear", "kauri", "zebrawood",
]


def main() -> None:
    assert len(WOODS) == 43, len(WOODS)
    models_item = ROOT / "src/main/resources/assets/reforestry/models/item"
    items_def = ROOT / "src/main/resources/assets/reforestry/items"
    recipes = ROOT / "src/main/resources/data/reforestry/recipe"
    tag_boats = ROOT / "src/main/resources/data/minecraft/tags/item/boats.json"
    tag_chest = ROOT / "src/main/resources/data/minecraft/tags/item/chest_boats.json"

    models_item.mkdir(parents=True, exist_ok=True)
    items_def.mkdir(parents=True, exist_ok=True)
    recipes.mkdir(parents=True, exist_ok=True)
    tag_boats.parent.mkdir(parents=True, exist_ok=True)

    boat_ids = []
    chest_ids = []
    for wood in WOODS:
        boat = f"{wood}_boat"
        chest = f"{wood}_chest_boat"
        boat_ids.append(f"reforestry:{boat}")
        chest_ids.append(f"reforestry:{chest}")
        for item_id in (boat, chest):
            (models_item / f"{item_id}.json").write_text(
                json.dumps({
                    "parent": "minecraft:item/generated",
                    "textures": {"layer0": f"reforestry:item/{item_id}"},
                }, indent=2) + "\n",
                encoding="utf-8",
            )
            (items_def / f"{item_id}.json").write_text(
                json.dumps({
                    "model": {
                        "type": "minecraft:model",
                        "model": f"reforestry:item/{item_id}",
                    }
                }, indent=2) + "\n",
                encoding="utf-8",
            )
        (recipes / f"{boat}.json").write_text(
            json.dumps({
                "type": "minecraft:crafting_shaped",
                "category": "misc",
                "group": "boat",
                "key": {"#": f"reforestry:{wood}_planks"},
                "pattern": ["# #", "###"],
                "result": {"id": f"reforestry:{boat}"},
            }, indent=2) + "\n",
            encoding="utf-8",
        )
        (recipes / f"{chest}.json").write_text(
            json.dumps({
                "type": "minecraft:crafting_shapeless",
                "category": "misc",
                "group": "chest_boat",
                "ingredients": ["minecraft:chest", f"reforestry:{boat}"],
                "result": {"id": f"reforestry:{chest}"},
            }, indent=2) + "\n",
            encoding="utf-8",
        )

    tag_boats.write_text(json.dumps({"replace": False, "values": boat_ids}, indent=2) + "\n", encoding="utf-8")
    tag_chest.write_text(json.dumps({"replace": False, "values": chest_ids}, indent=2) + "\n", encoding="utf-8")
    print(f"Wrote {len(WOODS)} boats + {len(WOODS)} chest boats")


if __name__ == "__main__":
    main()
