#!/usr/bin/env python3
"""Generate CE-parity comb→block shaped recipes. Usage: python3 tools/generate_comb_block_recipes.py --root . --apply"""

import argparse
import json
from pathlib import Path

COMB_TYPES = (
    "honey",
    "cocoa",
    "simmering",
    "stringy",
    "frozen",
    "dripping",
    "silky",
    "parched",
    "mysterious",
    "powdery",
    "wheaten",
    "mossy",
    "mellow",
    "kaolin",
    "vintage",
    "sponge",
    "sculken",
)


def recipe_for(comb: str) -> dict:
    return {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "group": "combs",
        "key": {"#": f"reforestry:bee_comb_{comb}"},
        "pattern": ["##", "##"],
        "result": {"id": f"reforestry:block_bee_comb_{comb}"},
        "show_notification": True,
    }


def advancement_for(comb: str) -> dict:
    comb_item = f"reforestry:bee_comb_{comb}"
    recipe_id = f"reforestry:block_bee_comb_{comb}"
    return {
        "parent": "minecraft:recipes/root",
        "criteria": {
            "has_item": {
                "conditions": {
                    "items": [{"items": comb_item}]
                },
                "trigger": "minecraft:inventory_changed",
            },
            "has_the_recipe": {
                "conditions": {"recipe": recipe_id},
                "trigger": "minecraft:recipe_unlocked",
            },
        },
        "requirements": [["has_item", "has_the_recipe"]],
        "rewards": {"recipes": [recipe_id]},
    }


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--root", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root).resolve()
    out_dir = root / "src/main/resources/data/reforestry/recipe"
    adv_dir = root / "src/main/resources/data/reforestry/advancement/recipes/building_blocks"
    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] comb block recipes -> {out_dir}")
    print(f"[{mode}] comb block advancements -> {adv_dir}")

    for comb in COMB_TYPES:
        path = out_dir / f"block_bee_comb_{comb}.json"
        payload = recipe_for(comb)
        if args.apply:
            path.write_text(json.dumps(payload, indent=2) + "\n", encoding="utf-8")
        print(f"  recipe {path.name}")

        adv_path = adv_dir / f"block_bee_comb_{comb}.json"
        adv_payload = advancement_for(comb)
        if args.apply:
            adv_path.parent.mkdir(parents=True, exist_ok=True)
            adv_path.write_text(json.dumps(adv_payload, indent=2) + "\n", encoding="utf-8")
        print(f"  advancement {adv_path.name}")

    print(f"  total: {len(COMB_TYPES)}")


if __name__ == "__main__":
    main()
