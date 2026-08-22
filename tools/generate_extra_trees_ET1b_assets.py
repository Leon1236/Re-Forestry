#!/usr/bin/env python3
"""Generate Extra Trees ET1b wood assets, recipes, tags, and loot.

Usage:
  python3 tools/generate_extra_trees_ET1b_assets.py
"""
from __future__ import annotations

import json
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
WOODS_JSON = ROOT / "queries/extra-trees-extract/woods.json"

ASSETS = ROOT / "src/main/resources/assets/reforestry"
DATA = ROOT / "src/main/resources/data"

PATH_REMAP = {
    "cherry": "et_cherry",
    "ash": "et_ash",
}


def write_json(path: Path, data: object) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")


def item_def(model_id: str) -> dict:
    return {"model": {"type": "minecraft:model", "model": model_id}}


def simple_loot(block_id: str) -> dict:
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "conditions": [{"condition": "minecraft:survives_explosion"}],
                "entries": [{"type": "minecraft:item", "name": f"reforestry:{block_id}"}],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"reforestry:blocks/{block_id}",
    }


def loot_alias(block_id: str, item_id: str) -> dict:
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "conditions": [{"condition": "minecraft:survives_explosion"}],
                "entries": [{"type": "minecraft:item", "name": f"reforestry:{item_id}"}],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"reforestry:blocks/{block_id}",
    }


def axis_log_blockstate(model: str) -> dict:
    return {
        "variants": {
            "axis=x": {"model": model, "x": 90, "y": 90},
            "axis=y": {"model": model},
            "axis=z": {"model": model, "x": 90},
        }
    }


def load_products() -> list[str]:
    data = json.loads(WOODS_JSON.read_text(encoding="utf-8"))
    return [PATH_REMAP.get(p["path"], p["path"]) for p in data["planks"] if p.get("register")]


def copy_larch_json(src_name: str, dst_name: str, replacements: list[tuple[str, str]]) -> dict:
    src = json.loads((ASSETS / src_name).read_text(encoding="utf-8"))
    text = json.dumps(src)
    for old, new in replacements:
        text = text.replace(old, new)
    return json.loads(text)


def copy_png(src: Path, dst: Path) -> None:
    dst.parent.mkdir(parents=True, exist_ok=True)
    shutil.copyfile(src, dst)


def ensure_png(src: Path, dst: Path) -> None:
    if not dst.is_file():
        copy_png(src, dst)


def write_stripped_and_wood(wood: str) -> None:
    tex = ASSETS / "textures/block"
    # Heartwood (trunk) as stripped side/top — Binnie has no dedicated stripped art
    ensure_png(tex / f"{wood}_log_top.png", tex / f"stripped_{wood}_log.png")
    ensure_png(tex / f"{wood}_log_top.png", tex / f"stripped_{wood}_log_top.png")

    write_json(ASSETS / f"models/block/{wood}_stripped_log.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": f"reforestry:block/stripped_{wood}_log_top",
            "side": f"reforestry:block/stripped_{wood}_log",
        },
    })
    write_json(ASSETS / f"models/block/{wood}_wood.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": f"reforestry:block/{wood}_log",
            "side": f"reforestry:block/{wood}_log",
        },
    })
    write_json(ASSETS / f"models/block/{wood}_stripped_wood.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": f"reforestry:block/stripped_{wood}_log",
            "side": f"reforestry:block/stripped_{wood}_log",
        },
    })

    for kind in ("stripped_log", "wood", "stripped_wood"):
        model = f"reforestry:block/{wood}_{kind}"
        write_json(ASSETS / f"blockstates/{wood}_{kind}.json", axis_log_blockstate(model))
        write_json(ASSETS / f"blockstates/{wood}_fireproof_{kind}.json", axis_log_blockstate(model))
        write_json(ASSETS / f"models/item/{wood}_{kind}.json", {"parent": model})
        write_json(ASSETS / f"models/item/{wood}_fireproof_{kind}.json", {"parent": model})
        write_json(ASSETS / f"items/{wood}_{kind}.json", item_def(f"reforestry:item/{wood}_{kind}"))
        write_json(ASSETS / f"items/{wood}_fireproof_{kind}.json", item_def(f"reforestry:item/{wood}_fireproof_{kind}"))
        write_json(DATA / f"reforestry/loot_table/blocks/{wood}_{kind}.json", simple_loot(f"{wood}_{kind}"))
        write_json(DATA / f"reforestry/loot_table/blocks/{wood}_fireproof_{kind}.json", simple_loot(f"{wood}_fireproof_{kind}"))


def write_trapdoor(wood: str) -> None:
    tex = ASSETS / "textures/block"
    ensure_png(tex / f"{wood}_planks.png", tex / f"{wood}_trapdoor.png")
    for suffix, parent in (
        ("trapdoor_bottom", "template_orientable_trapdoor_bottom"),
        ("trapdoor_top", "template_orientable_trapdoor_top"),
        ("trapdoor_open", "template_orientable_trapdoor_open"),
    ):
        write_json(ASSETS / f"models/block/{wood}_{suffix}.json", {
            "parent": f"minecraft:block/{parent}",
            "render_type": "minecraft:cutout",
            "textures": {"texture": f"reforestry:block/{wood}_trapdoor"},
        })
    write_json(
        ASSETS / f"blockstates/{wood}_trapdoor.json",
        copy_larch_json("blockstates/larch_trapdoor.json", "", [("larch_trapdoor", f"{wood}_trapdoor")]),
    )
    write_json(ASSETS / f"models/item/{wood}_trapdoor.json", {"parent": f"reforestry:block/{wood}_trapdoor_bottom"})
    write_json(ASSETS / f"items/{wood}_trapdoor.json", item_def(f"reforestry:item/{wood}_trapdoor"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_trapdoor.json", simple_loot(f"{wood}_trapdoor"))


def write_button_plate(wood: str) -> None:
    write_json(ASSETS / f"models/block/{wood}_button.json", {
        "parent": "minecraft:block/button",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"models/block/{wood}_button_pressed.json", {
        "parent": "minecraft:block/button_pressed",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"models/block/{wood}_button_inventory.json", {
        "parent": "minecraft:block/button_inventory",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(
        ASSETS / f"blockstates/{wood}_button.json",
        copy_larch_json("blockstates/larch_button.json", "", [("larch_button", f"{wood}_button")]),
    )
    write_json(ASSETS / f"models/item/{wood}_button.json", {"parent": f"reforestry:block/{wood}_button_inventory"})
    write_json(ASSETS / f"items/{wood}_button.json", item_def(f"reforestry:item/{wood}_button"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_button.json", simple_loot(f"{wood}_button"))

    write_json(ASSETS / f"models/block/{wood}_pressure_plate.json", {
        "parent": "minecraft:block/pressure_plate_up",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"models/block/{wood}_pressure_plate_down.json", {
        "parent": "minecraft:block/pressure_plate_down",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"blockstates/{wood}_pressure_plate.json", {
        "variants": {
            "powered=false": {"model": f"reforestry:block/{wood}_pressure_plate"},
            "powered=true": {"model": f"reforestry:block/{wood}_pressure_plate_down"},
        }
    })
    write_json(ASSETS / f"models/item/{wood}_pressure_plate.json", {"parent": f"reforestry:block/{wood}_pressure_plate"})
    write_json(ASSETS / f"items/{wood}_pressure_plate.json", item_def(f"reforestry:item/{wood}_pressure_plate"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_pressure_plate.json", simple_loot(f"{wood}_pressure_plate"))


def write_signs(wood: str) -> None:
    write_json(ASSETS / f"models/block/{wood}_sign.json", {
        "textures": {"particle": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"models/block/{wood}_hanging_sign.json", {
        "textures": {"particle": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"blockstates/{wood}_sign.json", {"variants": {"": {"model": f"reforestry:block/{wood}_sign"}}})
    write_json(ASSETS / f"blockstates/{wood}_wall_sign.json", {"variants": {"": {"model": f"reforestry:block/{wood}_sign"}}})
    write_json(ASSETS / f"blockstates/{wood}_hanging_sign.json", {"variants": {"": {"model": f"reforestry:block/{wood}_hanging_sign"}}})
    write_json(ASSETS / f"blockstates/{wood}_wall_hanging_sign.json", {"variants": {"": {"model": f"reforestry:block/{wood}_hanging_sign"}}})

    item_tex = ASSETS / "textures/item"
    ensure_png(ASSETS / "textures/item/larch_sign.png", item_tex / f"{wood}_sign.png")
    ensure_png(ASSETS / "textures/item/larch_hanging_sign.png", item_tex / f"{wood}_hanging_sign.png")

    for kind in ("sign", "hanging_sign"):
        write_json(ASSETS / f"models/item/{wood}_{kind}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"reforestry:item/{wood}_{kind}"},
        })
        write_json(ASSETS / f"items/{wood}_{kind}.json", item_def(f"reforestry:item/{wood}_{kind}"))

    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_sign.json", simple_loot(f"{wood}_sign"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_wall_sign.json", loot_alias(f"{wood}_wall_sign", f"{wood}_sign"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_hanging_sign.json", simple_loot(f"{wood}_hanging_sign"))
    write_json(
        DATA / f"reforestry/loot_table/blocks/{wood}_wall_hanging_sign.json",
        loot_alias(f"{wood}_wall_hanging_sign", f"{wood}_hanging_sign"),
    )

    sign_entity = ASSETS / "textures/entity/signs"
    hanging_entity = sign_entity / "hanging"
    ensure_png(sign_entity / "larch.png", sign_entity / f"{wood}.png")
    ensure_png(hanging_entity / "larch.png", hanging_entity / f"{wood}.png")


def write_boats(wood: str) -> None:
    item_tex = ASSETS / "textures/item"
    ensure_png(item_tex / "larch_boat.png", item_tex / f"{wood}_boat.png")
    ensure_png(item_tex / "larch_chest_boat.png", item_tex / f"{wood}_chest_boat.png")
    ensure_png(ASSETS / "textures/entity/boat/larch.png", ASSETS / f"textures/entity/boat/{wood}.png")
    ensure_png(ASSETS / "textures/entity/chest_boat/larch.png", ASSETS / f"textures/entity/chest_boat/{wood}.png")

    for kind in ("boat", "chest_boat"):
        write_json(ASSETS / f"models/item/{wood}_{kind}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"reforestry:item/{wood}_{kind}"},
        })
        write_json(ASSETS / f"items/{wood}_{kind}.json", item_def(f"reforestry:item/{wood}_{kind}"))


def write_recipes(wood: str) -> None:
    recipe_dir = DATA / "reforestry/recipe"
    planks = f"reforestry:{wood}_planks"

    write_json(recipe_dir / f"{wood}_wood.json", {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "group": "bark",
        "key": {"#": f"reforestry:{wood}_log"},
        "pattern": ["##", "##"],
        "result": {"id": f"reforestry:{wood}_wood"},
    })
    write_json(recipe_dir / f"{wood}_stripped_wood.json", {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "group": "bark",
        "key": {"#": f"reforestry:{wood}_stripped_log"},
        "pattern": ["##", "##"],
        "result": {"id": f"reforestry:{wood}_stripped_wood"},
    })
    write_json(recipe_dir / f"{wood}_fireproof_wood.json", {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "group": "bark",
        "key": {"#": f"reforestry:{wood}_fireproof_log"},
        "pattern": ["##", "##"],
        "result": {"id": f"reforestry:{wood}_fireproof_wood"},
    })
    write_json(recipe_dir / f"{wood}_fireproof_stripped_wood.json", {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "group": "bark",
        "key": {"#": f"reforestry:{wood}_fireproof_stripped_log"},
        "pattern": ["##", "##"],
        "result": {"id": f"reforestry:{wood}_fireproof_stripped_wood"},
    })

    write_json(recipe_dir / f"{wood}_trapdoor.json", {
        "type": "minecraft:crafting_shaped",
        "category": "redstone",
        "group": "wooden_trapdoor",
        "key": {"#": planks},
        "pattern": ["###", "###"],
        "result": {"count": 2, "id": f"reforestry:{wood}_trapdoor"},
    })
    write_json(recipe_dir / f"{wood}_button.json", {
        "type": "minecraft:crafting_shapeless",
        "category": "redstone",
        "ingredients": [planks],
        "result": {"id": f"reforestry:{wood}_button"},
    })
    write_json(recipe_dir / f"{wood}_pressure_plate.json", {
        "type": "minecraft:crafting_shaped",
        "category": "redstone",
        "key": {"P": planks},
        "pattern": ["PP"],
        "result": {"id": f"reforestry:{wood}_pressure_plate"},
    })
    write_json(recipe_dir / f"{wood}_sign.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {"P": planks, "S": "minecraft:stick"},
        "pattern": ["PPP", "PPP", " S "],
        "result": {"id": f"reforestry:{wood}_sign"},
    })
    write_json(recipe_dir / f"{wood}_hanging_sign.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {"#": f"reforestry:{wood}_stripped_log", "X": "minecraft:iron_chain"},
        "pattern": ["X X", "###", "###"],
        "result": {"id": f"reforestry:{wood}_hanging_sign"},
    })
    write_json(recipe_dir / f"{wood}_boat.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "group": "boat",
        "key": {"#": planks},
        "pattern": ["# #", "###"],
        "result": {"id": f"reforestry:{wood}_boat"},
    })
    write_json(recipe_dir / f"{wood}_chest_boat.json", {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "group": "chest_boat",
        "ingredients": ["minecraft:chest", f"reforestry:{wood}_boat"],
        "result": {"id": f"reforestry:{wood}_chest_boat"},
    })

    fab = DATA / "reforestry/recipe/fabricator/fireproof"
    for kind, src in (
        ("stripped_log", f"{wood}_stripped_log"),
        ("wood", f"{wood}_wood"),
        ("stripped_wood", f"{wood}_stripped_wood"),
    ):
        write_json(fab / kind / f"{wood}.json", {
            "type": "reforestry:fabricator",
            "molten": {"fluid": "reforestry:glass", "amount": 500},
            "plan": [],
            "recipe": {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "key": {"#": "reforestry:refractory_wax", "X": f"reforestry:{src}"},
                "pattern": ["   ", "X#X", "   "],
                "result": {"count": 2, "id": f"reforestry:{wood}_fireproof_{kind}"},
            },
        })


def append_tag(path: Path, entries: list[str]) -> None:
    if path.is_file():
        data = json.loads(path.read_text(encoding="utf-8"))
    else:
        data = {"values": []}
    values = data.setdefault("values", [])
    existing = set(values)
    for entry in entries:
        if entry not in existing:
            values.append(entry)
    write_json(path, data)


def write_advancement(path: Path, recipe_id: str, unlock_item: str) -> None:
    write_json(path, {
        "parent": "minecraft:recipes/root",
        "criteria": {
            "has_item": {
                "conditions": {"items": [{"items": unlock_item}]},
                "trigger": "minecraft:inventory_changed",
            },
            "has_the_recipe": {
                "conditions": {"recipe": recipe_id},
                "trigger": "minecraft:recipe_unlocked",
            },
        },
        "requirements": [["has_the_recipe", "has_item"]],
        "rewards": {"recipes": [recipe_id]},
    })


def write_recipe_advancements(wood: str) -> None:
    adv = DATA / "reforestry/advancement/recipes"
    rid = f"reforestry:{wood}"
    write_advancement(adv / f"building_blocks/{wood}_wood.json", f"{rid}_wood", f"{rid}_log")
    write_advancement(adv / f"building_blocks/{wood}_stripped_wood.json", f"{rid}_stripped_wood", f"{rid}_stripped_log")
    write_advancement(adv / f"building_blocks/{wood}_fireproof_wood.json", f"{rid}_fireproof_wood", f"{rid}_fireproof_log")
    write_advancement(
        adv / f"building_blocks/{wood}_fireproof_stripped_wood.json",
        f"{rid}_fireproof_stripped_wood",
        f"{rid}_fireproof_stripped_log",
    )
    write_advancement(adv / f"building_blocks/{wood}_planks.json", f"{rid}_planks", f"#reforestry:{wood}_logs")
    write_advancement(adv / f"building_blocks/{wood}_slab.json", f"{rid}_slab", f"{rid}_planks")
    write_advancement(adv / f"building_blocks/{wood}_stairs.json", f"{rid}_stairs", f"{rid}_planks")
    write_advancement(adv / f"building_blocks/{wood}_fence.json", f"{rid}_fence", "#c:rods/wooden")
    write_advancement(adv / f"building_blocks/{wood}_fence_gate.json", f"{rid}_fence_gate", "#c:rods/wooden")
    write_advancement(adv / f"building_blocks/{wood}_fireproof_planks.json", f"{rid}_fireproof_planks", f"#reforestry:fireproof_{wood}_logs")
    write_advancement(adv / f"building_blocks/{wood}_fireproof_slab.json", f"{rid}_fireproof_slab", f"{rid}_fireproof_planks")
    write_advancement(adv / f"building_blocks/{wood}_fireproof_stairs.json", f"{rid}_fireproof_stairs", f"{rid}_fireproof_planks")
    write_advancement(adv / f"building_blocks/{wood}_fireproof_fence.json", f"{rid}_fireproof_fence", "#c:rods/wooden")
    write_advancement(adv / f"building_blocks/{wood}_fireproof_fence_gate.json", f"{rid}_fireproof_fence_gate", "#c:rods/wooden")
    write_advancement(adv / f"redstone/{wood}_door.json", f"{rid}_door", f"{rid}_planks")
    write_advancement(adv / f"redstone/{wood}_trapdoor.json", f"{rid}_trapdoor", f"{rid}_planks")
    write_advancement(adv / f"redstone/{wood}_button.json", f"{rid}_button", f"{rid}_planks")
    write_advancement(adv / f"redstone/{wood}_pressure_plate.json", f"{rid}_pressure_plate", f"{rid}_planks")
    write_advancement(adv / f"misc/{wood}_sign.json", f"{rid}_sign", f"{rid}_planks")
    write_advancement(adv / f"misc/{wood}_hanging_sign.json", f"{rid}_hanging_sign", "minecraft:iron_chain")
    write_advancement(adv / f"misc/{wood}_boat.json", f"{rid}_boat", f"{rid}_planks")
    write_advancement(adv / f"misc/{wood}_chest_boat.json", f"{rid}_chest_boat", f"{rid}_boat")


def write_tags(products: list[str]) -> None:
    for wood in products:
        append_tag(DATA / f"reforestry/tags/block/{wood}_logs.json", [
            f"reforestry:{wood}_log",
            f"reforestry:{wood}_wood",
            f"reforestry:{wood}_stripped_log",
            f"reforestry:{wood}_stripped_wood",
        ])
        append_tag(DATA / f"reforestry/tags/item/{wood}_logs.json", [
            f"reforestry:{wood}_log",
            f"reforestry:{wood}_wood",
            f"reforestry:{wood}_stripped_log",
            f"reforestry:{wood}_stripped_wood",
        ])
        append_tag(DATA / f"reforestry/tags/block/fireproof_{wood}_logs.json", [
            f"reforestry:{wood}_fireproof_log",
            f"reforestry:{wood}_fireproof_wood",
            f"reforestry:{wood}_fireproof_stripped_log",
            f"reforestry:{wood}_fireproof_stripped_wood",
        ])
        append_tag(DATA / f"reforestry/tags/item/fireproof_{wood}_logs.json", [
            f"reforestry:{wood}_fireproof_log",
            f"reforestry:{wood}_fireproof_wood",
            f"reforestry:{wood}_fireproof_stripped_log",
            f"reforestry:{wood}_fireproof_stripped_wood",
        ])

    trapdoors = [f"reforestry:{w}_trapdoor" for w in products]
    buttons = [f"reforestry:{w}_button" for w in products]
    plates = [f"reforestry:{w}_pressure_plate" for w in products]
    signs = [f"reforestry:{w}_sign" for w in products]
    hanging = [f"reforestry:{w}_hanging_sign" for w in products]
    boats = [f"reforestry:{w}_boat" for w in products]
    chest_boats = [f"reforestry:{w}_chest_boat" for w in products]

    for kind in ("block", "item"):
        append_tag(DATA / f"minecraft/tags/{kind}/wooden_trapdoors.json", trapdoors)
        append_tag(DATA / f"minecraft/tags/{kind}/wooden_buttons.json", buttons)
        append_tag(DATA / f"minecraft/tags/{kind}/wooden_pressure_plates.json", plates)
    append_tag(DATA / "minecraft/tags/item/signs.json", signs)
    append_tag(DATA / "minecraft/tags/item/hanging_signs.json", hanging)
    append_tag(DATA / "minecraft/tags/block/standing_signs.json", [f"reforestry:{w}_sign" for w in products])
    append_tag(DATA / "minecraft/tags/block/wall_signs.json", [f"reforestry:{w}_wall_sign" for w in products])
    append_tag(DATA / "minecraft/tags/block/ceiling_hanging_signs.json", [f"reforestry:{w}_hanging_sign" for w in products])
    append_tag(DATA / "minecraft/tags/block/wall_hanging_signs.json", [f"reforestry:{w}_wall_hanging_sign" for w in products])
    append_tag(DATA / "minecraft/tags/item/boats.json", boats)
    append_tag(DATA / "minecraft/tags/item/chest_boats.json", chest_boats)


def main() -> None:
    products = load_products()
    assert len(products) == 30, len(products)
    for wood in products:
        write_stripped_and_wood(wood)
        write_trapdoor(wood)
        write_button_plate(wood)
        write_signs(wood)
        write_boats(wood)
        write_recipes(wood)
        write_recipe_advancements(wood)
    write_tags(products)
    print(f"ET1b assets: {len(products)} product woods (stripped/wood/trapdoor/button/plate/signs/boats)")


if __name__ == "__main__":
    main()
