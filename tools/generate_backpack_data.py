#!/usr/bin/env python3
"""Generate backpack tags, recipes, and 26.2 item model definitions from CE data.

Usage:
  python3 tools/generate_backpack_data.py --apply
"""
from __future__ import annotations

import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/reforestry"
DATA = ROOT / "src/main/resources/data/reforestry"

COLORS = {
    "miner": 0x36187d,
    "digger": 0x363cc5,
    "forester": 0x347427,
    "hunter": 0x412215,
    "adventurer": 0x7fb8c2,
    "builder": 0xdd3a3a,
    "brewer": 0xBD7CBD,
}
NATURALIST = {
    "apiarist": (0xc4923d, "reforestry:bee_chest"),
    "arborist": (0x657e3a, "reforestry:tree_chest"),
    "lepidopterist": (0x995b31, "reforestry:butterfly_chest"),
}
WHITE = 0xFFFFFF
BAGS = list(COLORS.keys())

FORGE_TO_C = {
    "forge:obsidian": "c:obsidians",
    "forge:ores": "c:ores",
    "forge:dusts": "c:dusts",
    "forge:gems": "c:gems",
    "forge:ingots": "c:ingots",
    "forge:nuggets": "c:nuggets",
    "forge:raw_materials": "c:raw_materials",
    "forge:cobblestone": "c:cobblestones",
    "forge:gravel": "c:gravels",
    "forge:netherrack": "c:netherracks",
    "forge:stone": "c:stones",
    "forge:sandstone": "c:sandstone_blocks",
    "forge:sand": "c:sands",
    "forge:crops": "c:crops",
    "forge:seeds": "c:seeds",
    "forge:bones": "c:bones",
    "forge:eggs": "c:eggs",
    "forge:ender_pearls": "c:ender_pearls",
    "forge:feathers": "c:feathers",
    "forge:gunpowder": "c:gunpowders",
    "forge:leather": "c:leathers",
    "forge:slimeballs": "c:slime_balls",
    "forge:string": "c:strings",
    "forge:glass_panes": "c:glass_panes",
    "forge:stained_glass": "c:glass_blocks",
    "forge:glass": "c:glass_blocks",
    "forge:chests": "c:chests",
    "forge:ingots/iron": "c:ingots/iron",
    "forge:chests/wooden": "c:chests/wooden",
}

VANILLA_RENAMES = {
    "minecraft:grass": "minecraft:short_grass",
}


def to_signed_argb(rgb: int) -> int:
    argb = 0xFF000000 | (rgb & 0xFFFFFF)
    return argb - 0x100000000 if argb >= 0x80000000 else argb


def rewrite_tag_entry(entry: str) -> str:
    if entry.startswith("#"):
        inner = entry[1:]
        inner = FORGE_TO_C.get(inner, inner)
        inner = inner.replace("forestry:", "reforestry:")
        inner = VANILLA_RENAMES.get(inner, inner)
        return "#" + inner
    entry = entry.replace("forestry:", "reforestry:")
    return VANILLA_RENAMES.get(entry, entry)


def write_json(path: Path, data) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")


def backpack_item_definition(bag: str, woven: bool) -> dict:
    prefix = "woven" if woven else "normal"
    primary = to_signed_argb(COLORS[bag])
    secondary = to_signed_argb(WHITE)
    tints = [
        {"type": "minecraft:constant", "value": primary},
        {"type": "minecraft:constant", "value": secondary},
    ]

    def model(mode: str) -> dict:
        return {
            "type": "minecraft:model",
            "model": f"reforestry:item/backpack/{prefix}_{mode}",
            "tints": tints,
        }

    return {
        "model": {
            "type": "minecraft:select",
            "property": "reforestry:backpack_mode",
            "cases": [
                {"when": "locked", "model": model("locked")},
                {"when": "receive", "model": model("receive")},
                {"when": "resupply", "model": model("resupply")},
            ],
            "fallback": model("neutral"),
        }
    }


def shaped_bag(bag: str, v_ingredient: str) -> dict:
    return {
        "type": "minecraft:crafting_shaped",
        "category": "equipment",
        "key": {
            "#": "#minecraft:wool",
            "V": v_ingredient,
            "X": "#c:strings",
            "Y": "#c:chests/wooden",
        },
        "pattern": ["X#X", "VYV", "X#X"],
        "result": {"id": f"reforestry:{bag}_bag"},
    }


def woven_recipe(bag: str) -> dict:
    return {
        "type": "reforestry:carpenter",
        "time": 200,
        "recipe": {
            "type": "minecraft:crafting_shaped",
            "category": "equipment",
            "key": {
                "W": "reforestry:woven_silk",
                "X": "minecraft:diamond",
                "T": f"reforestry:{bag}_bag",
            },
            "pattern": ["WXW", "WTW", "WWW"],
            "result": {"id": f"reforestry:{bag}_bag_woven"},
            "show_notification": True,
        },
        "liquid": {"fluid": "minecraft:water", "amount": 1000},
    }


CE_ALLOW = {
    "miner": [
        "#forge:obsidian", "#forge:ores", "#forge:dusts", "#forge:gems", "#forge:ingots",
        "#forge:nuggets", "#forge:raw_materials", "#minecraft:coals",
        "forestry:bronze_pickaxe", "forestry:kit_pickaxe", "forestry:broken_bronze_pickaxe",
    ],
    "digger": [
        "#forge:cobblestone", "#forge:gravel", "#forge:netherrack", "#forge:stone",
        "#forge:sandstone", "#forge:sand", "#minecraft:dirt", "minecraft:flint",
        "minecraft:clay_ball", "minecraft:snowball", "minecraft:soul_sand", "minecraft:clay",
        "minecraft:snow", "forestry:bronze_pickaxe", "forestry:kit_pickaxe",
        "forestry:broken_bronze_pickaxe",
    ],
    "forester": [
        "#minecraft:logs", "#minecraft:saplings", "#forge:crops", "#forge:seeds",
        "#minecraft:flowers", "minecraft:stick", "minecraft:vine", "minecraft:sugar_cane",
        "minecraft:cactus", "minecraft:red_mushroom", "minecraft:brown_mushroom",
        "minecraft:grass", "minecraft:pumpkin", "minecraft:melon", "minecraft:golden_apple",
        "minecraft:nether_wart", "minecraft:beetroot", "minecraft:chorus_fruit",
        "minecraft:chorus_plant", "minecraft:apple",
    ],
    "hunter": [
        "#forge:bones", "#forge:eggs", "#forge:ender_pearls", "#forge:feathers",
        "#minecraft:fishes", "#forge:gunpowder", "#forge:leather", "#forge:slimeballs",
        "#forge:string", "minecraft:blaze_powder", "minecraft:blaze_rod",
        "minecraft:rotten_flesh", "minecraft:skeleton_skull", "minecraft:ghast_tear",
        "minecraft:gold_nugget", "minecraft:arrow", "minecraft:spectral_arrow",
        "minecraft:tipped_arrow", "minecraft:porkchop", "minecraft:cooked_porkchop",
        "minecraft:beef", "minecraft:cooked_beef", "minecraft:chicken",
        "minecraft:cooked_chicken", "minecraft:mutton", "minecraft:cooked_mutton",
        "minecraft:rabbit", "minecraft:cooked_rabbit", "minecraft:rabbit_foot",
        "minecraft:rabbit_hide", "minecraft:spider_eye", "minecraft:fermented_spider_eye",
        "minecraft:bone_meal", "minecraft:hay_block", "minecraft:white_wool",
        "minecraft:ender_eye", "minecraft:magma_cream", "minecraft:glistering_melon_slice",
        "minecraft:cod", "minecraft:cooked_cod", "minecraft:lead", "minecraft:fishing_rod",
        "minecraft:name_tag", "minecraft:saddle", "minecraft:diamond_horse_armor",
        "minecraft:golden_horse_armor", "minecraft:iron_horse_armor",
    ],
    "adventurer": [],
    "builder": [
        "#forge:glass_panes", "#minecraft:wooden_slabs", "#forge:stained_glass",
        "#forge:stone", "#forge:sandstone", "#minecraft:planks", "#minecraft:wooden_stairs",
        "#minecraft:wooden_fences", "#minecraft:wooden_trapdoors", "#forge:glass",
        "#forge:chests", "#minecraft:wooden_doors", "#minecraft:fence_gates",
        "#minecraft:fences", "#minecraft:terracotta", "minecraft:torch",
        "minecraft:crafting_table", "minecraft:redstone_torch", "minecraft:redstone_lamp",
        "minecraft:sea_lantern", "minecraft:end_rod", "minecraft:stone_bricks",
        "minecraft:bricks", "minecraft:clay", "minecraft:terracotta",
        "minecraft:white_terracotta", "minecraft:white_glazed_terracotta",
        "minecraft:packed_ice", "minecraft:nether_bricks", "minecraft:nether_brick_fence",
        "minecraft:furnace", "minecraft:lever", "minecraft:dispenser", "minecraft:dropper",
        "minecraft:ladder", "minecraft:iron_bars", "minecraft:quartz_block",
        "minecraft:quartz_stairs", "minecraft:sandstone_stairs",
        "minecraft:red_sandstone_stairs", "minecraft:cobblestone_wall",
        "minecraft:stone_button", "minecraft:oak_button", "minecraft:stone_slab",
        "minecraft:sandstone_slab", "minecraft:oak_slab", "minecraft:purpur_block",
        "minecraft:purpur_pillar", "minecraft:purpur_stairs", "minecraft:purpur_slab",
        "minecraft:end_stone_bricks", "minecraft:white_carpet", "minecraft:iron_trapdoor",
        "minecraft:stone_pressure_plate", "minecraft:oak_pressure_plate",
        "minecraft:light_weighted_pressure_plate", "minecraft:heavy_weighted_pressure_plate",
        "minecraft:oak_sign", "minecraft:item_frame", "minecraft:acacia_door",
        "minecraft:birch_door", "minecraft:dark_oak_door", "minecraft:iron_door",
        "minecraft:jungle_door", "minecraft:oak_door", "minecraft:spruce_door",
    ],
    "brewer": [
        "minecraft:potion", "minecraft:lingering_potion", "minecraft:splash_potion",
        "minecraft:glass_bottle", "minecraft:experience_bottle", "minecraft:honey_bottle",
    ],
}

SHAPED_V = {
    "miner": "#c:ingots/iron",
    "digger": "#c:stones",
    "forester": "#minecraft:logs",
    "hunter": "#c:feathers",
    "adventurer": "#c:bones",
    "builder": "minecraft:clay_ball",
    "brewer": "minecraft:glass_bottle",
}


def naturalist_bag_recipe(bag: str, chest: str) -> dict:
    return {
        "type": "minecraft:crafting_shaped",
        "category": "equipment",
        "key": {
            "#": "#minecraft:wool",
            "V": "#c:rods/wooden",
            "X": "#c:strings",
            "Y": chest,
        },
        "pattern": ["X#X", "VYV", "X#X"],
        "result": {"id": f"reforestry:{bag}_bag"},
    }


def main() -> None:
    for bag in BAGS:
        for woven in (False, True):
            item_id = f"{bag}_bag_woven" if woven else f"{bag}_bag"
            write_json(ASSETS / "items" / f"{item_id}.json", backpack_item_definition(bag, woven))
        write_json(DATA / "recipe" / f"{bag}_bag.json", shaped_bag(bag, SHAPED_V[bag]))
        write_json(DATA / "recipe" / "carpenter" / f"{bag}_bag_woven.json", woven_recipe(bag))
        allow = [rewrite_tag_entry(v) for v in CE_ALLOW[bag]]
        write_json(DATA / "tags/item/backpack/allow" / f"{bag}.json", {"values": allow})
        write_json(DATA / "tags/item/backpack/reject" / f"{bag}.json", {"values": []})
    saved_colors = dict(COLORS)
    for bag, (color, chest) in NATURALIST.items():
        COLORS[bag] = color
        write_json(ASSETS / "items" / f"{bag}_bag.json", backpack_item_definition(bag, False))
        write_json(DATA / "recipe" / f"{bag}_bag.json", naturalist_bag_recipe(bag, chest))
    COLORS.clear()
    COLORS.update(saved_colors)
    print(f"wrote backpack item defs, recipes, and tags for {len(BAGS)} bags + {len(NATURALIST)} naturalist bags")


if __name__ == "__main__":
    main()
