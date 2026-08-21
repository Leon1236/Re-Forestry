#!/usr/bin/env python3
"""Generate Extra Trees ET1a wood assets, recipes, tags, loot, and lang.

Usage:
  python3 tools/generate_extra_trees_ET1a_assets.py
"""
from __future__ import annotations

import json
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
WOODS_JSON = ROOT / "queries/extra-trees-extract/woods.json"
BINNIE = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/resources/assets/extratrees"
BINNIE_LOGS = BINNIE / "textures/blocks/logs"
BINNIE_PLANKS = BINNIE / "textures/blocks/planks"
BINNIE_DOOR_LOWER = BINNIE / "textures/blocks/door.standard.lower.png"
BINNIE_DOOR_UPPER = BINNIE / "textures/blocks/door.standard.upper.png"

ASSETS = ROOT / "src/main/resources/assets/reforestry"
DATA = ROOT / "src/main/resources/data"
LANG = ASSETS / "lang/en_us.json"

# path -> display name for for.trees.woodType.*
DISPLAY = {
    "cedar": "Cedar",
    "hemlock": "Hemlock",
    "cypress": "Cypress",
    "fig": "Fig",
    "alder": "Alder",
    "hazel": "Hazel",
    "hornbeam": "Hornbeam",
    "box": "Box",
    "butternut": "Butternut",
    "hickory": "Hickory",
    "whitebeam": "Whitebeam",
    "apple": "Apple",
    "yew": "Yew",
    "hawthorn": "Hawthorn",
    "rowan": "Rowan",
    "elder": "Elder",
    "maclura": "Maclura",
    "syzgium": "Syzgium",
    "brazilwood": "Brazilwood",
    "logwood": "Logwood",
    "iroko": "Iroko",
    "locust": "Locust",
    "eucalyptus": "Eucalyptus",
    "purpleheart": "Purpleheart",
    "ash": "Ash",
    "et_ash": "Ash",
    "holly": "Holly",
    "sweetgum": "Sweetgum",
    "rosewood": "Rosewood",
    "pink_ivory": "Pink Ivory",
    "banana": "Banana",
    "eucalyptus2": "Eucalyptus",
    "eucalyptus3": "Eucalyptus",
    "et_cherry": "Cherry",
    "cinnamon": "Cinnamon",
    "shrub": "Shrub",
}

# log-only bark variants: own log id -> plank recipe result (normal / fireproof)
LOG_ONLY_PLANKS = {
    "eucalyptus2": ("reforestry:eucalyptus_planks", "reforestry:eucalyptus_fireproof_planks"),
    "eucalyptus3": ("reforestry:eucalyptus_planks", "reforestry:eucalyptus_fireproof_planks"),
    "et_cherry": ("reforestry:hill_cherry_planks", "reforestry:hill_cherry_fireproof_planks"),
    "cinnamon": ("minecraft:jungle_planks", "reforestry:jungle_fireproof_planks"),
}

# Binnie texture filenames that omit underscores / remapped stems
PLANK_SRC = {
    "pink_ivory": "pinkivory.png",
    "et_ash": "ash.png",
}
LOG_SRC = {
    "pink_ivory": "pinkivory",
    "et_cherry": "cherry",
    "et_ash": "ash",
}

# woods.json path → registry serializedName when ids collide with existing content
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


def door_loot(block_id: str) -> dict:
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "bonus_rolls": 0.0,
                "conditions": [{"condition": "minecraft:survives_explosion"}],
                "entries": [
                    {
                        "type": "minecraft:item",
                        "conditions": [
                            {
                                "block": f"reforestry:{block_id}",
                                "condition": "minecraft:block_state_property",
                                "properties": {"half": "lower"},
                            }
                        ],
                        "name": f"reforestry:{block_id}",
                    }
                ],
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


def stairs_blockstate(prefix: str) -> dict:
    m = f"reforestry:block/{prefix}_stairs"
    mi = f"reforestry:block/{prefix}_stairs_inner"
    mo = f"reforestry:block/{prefix}_stairs_outer"
    variants = {}
    for facing, y in (("east", 0), ("north", 270), ("south", 90), ("west", 180)):
        for half, x in (("bottom", 0), ("top", 180)):
            for shape, model, extra_y in (
                ("straight", m, 0),
                ("inner_left", mi, 270 if half == "bottom" else 0),
                ("inner_right", mi, 0 if half == "bottom" else 90),
                ("outer_left", mo, 270 if half == "bottom" else 0),
                ("outer_right", mo, 0 if half == "bottom" else 90),
            ):
                key = f"facing={facing},half={half},shape={shape}"
                entry: dict = {"model": model, "uvlock": True}
                total_y = (y + extra_y) % 360
                if half == "top":
                    entry["x"] = 180
                    if shape == "straight":
                        pass
                    elif shape in ("inner_left", "outer_left"):
                        total_y = (y + (270 if half == "bottom" else 90)) % 360
                        # Match larch: for top, inner_left uses x=180,y from facing+adjust
                # Simpler: copy from a known good template by reading larch once below
                variants[key] = entry
                if total_y:
                    entry["y"] = total_y
                if half == "top" and shape == "straight":
                    entry["x"] = 180
    return {"variants": variants}


def copy_larch_stairs_blockstate(wood: str) -> dict:
    src = json.loads((ASSETS / "blockstates/larch_stairs.json").read_text(encoding="utf-8"))
    text = json.dumps(src)
    text = text.replace("larch_stairs", f"{wood}_stairs")
    return json.loads(text)


def copy_larch_fence_blockstate(wood: str) -> dict:
    src = json.loads((ASSETS / "blockstates/larch_fence.json").read_text(encoding="utf-8"))
    text = json.dumps(src).replace("larch_fence", f"{wood}_fence")
    return json.loads(text)


def copy_larch_fence_gate_blockstate(wood: str) -> dict:
    src = json.loads((ASSETS / "blockstates/larch_fence_gate.json").read_text(encoding="utf-8"))
    text = json.dumps(src).replace("larch_fence_gate", f"{wood}_fence_gate")
    return json.loads(text)


def copy_larch_door_blockstate(wood: str) -> dict:
    src = json.loads((ASSETS / "blockstates/larch_door.json").read_text(encoding="utf-8"))
    text = json.dumps(src).replace("larch_door", f"{wood}_door")
    return json.loads(text)


def copy_larch_door_models(wood: str) -> None:
    for name in (
        "door_bottom_left",
        "door_bottom_left_open",
        "door_bottom_right",
        "door_bottom_right_open",
        "door_top_left",
        "door_top_left_open",
        "door_top_right",
        "door_top_right_open",
    ):
        src = json.loads((ASSETS / f"models/block/larch_{name}.json").read_text(encoding="utf-8"))
        text = json.dumps(src).replace("larch_door", f"{wood}_door")
        write_json(ASSETS / f"models/block/{wood}_{name}.json", json.loads(text))


def load_woods() -> tuple[list[str], list[str], list[str]]:
    data = json.loads(WOODS_JSON.read_text(encoding="utf-8"))
    products = [PATH_REMAP.get(p["path"], p["path"]) for p in data["planks"] if p.get("register")]
    log_only = [PATH_REMAP.get(entry["path"], entry["path"]) for entry in data["log_only_variants"]]
    shrub = ["shrub"]
    return products, log_only, shrub


def copy_textures(products: list[str], log_only: list[str]) -> None:
    tex_block = ASSETS / "textures/block"
    tex_item = ASSETS / "textures/item"
    tex_block.mkdir(parents=True, exist_ok=True)
    tex_item.mkdir(parents=True, exist_ok=True)

    for wood in products + log_only:
        src_stem = LOG_SRC.get(wood, wood)
        bark = BINNIE_LOGS / f"{src_stem}_bark.png"
        trunk = BINNIE_LOGS / f"{src_stem}_trunk.png"
        if not bark.is_file():
            raise SystemExit(f"missing log bark texture: {bark}")
        if not trunk.is_file():
            raise SystemExit(f"missing log trunk texture: {trunk}")
        shutil.copyfile(bark, tex_block / f"{wood}_log.png")
        shutil.copyfile(trunk, tex_block / f"{wood}_log_top.png")

    for wood in products:
        plank_name = PLANK_SRC.get(wood, f"{wood}.png")
        plank = BINNIE_PLANKS / plank_name
        if not plank.is_file():
            # try enum Capitalized
            alt = BINNIE_PLANKS / f"{wood.replace('_', '')}.png"
            if alt.is_file():
                plank = alt
            else:
                raise SystemExit(f"missing plank texture: {plank}")
        shutil.copyfile(plank, tex_block / f"{wood}_planks.png")
        if not BINNIE_DOOR_LOWER.is_file() or not BINNIE_DOOR_UPPER.is_file():
            raise SystemExit("missing Binnie door.standard textures")
        shutil.copyfile(BINNIE_DOOR_LOWER, tex_block / f"{wood}_door_bottom.png")
        shutil.copyfile(BINNIE_DOOR_UPPER, tex_block / f"{wood}_door_top.png")
        shutil.copyfile(BINNIE_DOOR_LOWER, tex_item / f"{wood}_door.png")


def write_log_assets(wood: str) -> None:
    write_json(ASSETS / f"models/block/{wood}_log.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": f"reforestry:block/{wood}_log_top",
            "side": f"reforestry:block/{wood}_log",
        },
    })
    write_json(ASSETS / f"blockstates/{wood}_log.json", axis_log_blockstate(f"reforestry:block/{wood}_log"))
    write_json(ASSETS / f"blockstates/{wood}_fireproof_log.json", axis_log_blockstate(f"reforestry:block/{wood}_log"))
    write_json(ASSETS / f"models/item/{wood}_log.json", {"parent": f"reforestry:block/{wood}_log"})
    write_json(ASSETS / f"models/item/{wood}_fireproof_log.json", {"parent": f"reforestry:block/{wood}_log"})
    write_json(ASSETS / f"items/{wood}_log.json", item_def(f"reforestry:item/{wood}_log"))
    write_json(ASSETS / f"items/{wood}_fireproof_log.json", item_def(f"reforestry:item/{wood}_fireproof_log"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_log.json", simple_loot(f"{wood}_log"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_fireproof_log.json", simple_loot(f"{wood}_fireproof_log"))


def write_shrub_assets() -> None:
    wood = "shrub"
    write_json(ASSETS / f"models/block/{wood}_log.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": "minecraft:block/oak_log_top",
            "side": "minecraft:block/oak_log",
        },
    })
    write_json(ASSETS / f"blockstates/{wood}_log.json", axis_log_blockstate(f"reforestry:block/{wood}_log"))
    write_json(ASSETS / f"blockstates/{wood}_fireproof_log.json", axis_log_blockstate(f"reforestry:block/{wood}_log"))
    write_json(ASSETS / f"models/item/{wood}_log.json", {"parent": f"reforestry:block/{wood}_log"})
    write_json(ASSETS / f"models/item/{wood}_fireproof_log.json", {"parent": f"reforestry:block/{wood}_log"})
    write_json(ASSETS / f"items/{wood}_log.json", item_def(f"reforestry:item/{wood}_log"))
    write_json(ASSETS / f"items/{wood}_fireproof_log.json", item_def(f"reforestry:item/{wood}_fireproof_log"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_log.json", simple_loot(f"{wood}_log"))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_fireproof_log.json", simple_loot(f"{wood}_fireproof_log"))


def write_product_assets(wood: str) -> None:
    write_json(ASSETS / f"models/block/{wood}_planks.json", {
        "parent": "minecraft:block/cube_all",
        "textures": {"all": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"blockstates/{wood}_planks.json", {"variants": {"": {"model": f"reforestry:block/{wood}_planks"}}})
    write_json(ASSETS / f"blockstates/{wood}_fireproof_planks.json", {"variants": {"": {"model": f"reforestry:block/{wood}_planks"}}})
    write_json(ASSETS / f"models/item/{wood}_planks.json", {"parent": f"reforestry:block/{wood}_planks"})
    write_json(ASSETS / f"models/item/{wood}_fireproof_planks.json", {"parent": f"reforestry:block/{wood}_planks"})
    write_json(ASSETS / f"items/{wood}_planks.json", item_def(f"reforestry:item/{wood}_planks"))
    write_json(ASSETS / f"items/{wood}_fireproof_planks.json", item_def(f"reforestry:item/{wood}_fireproof_planks"))

    for kind, parent in (
        ("slab", "slab"),
        ("slab_top", "slab_top"),
    ):
        write_json(ASSETS / f"models/block/{wood}_{kind}.json", {
            "parent": f"minecraft:block/{parent}",
            "textures": {
                "bottom": f"reforestry:block/{wood}_planks",
                "side": f"reforestry:block/{wood}_planks",
                "top": f"reforestry:block/{wood}_planks",
            },
        })
    write_json(ASSETS / f"blockstates/{wood}_slab.json", {
        "variants": {
            "type=bottom": {"model": f"reforestry:block/{wood}_slab"},
            "type=double": {"model": f"reforestry:block/{wood}_planks"},
            "type=top": {"model": f"reforestry:block/{wood}_slab_top"},
        }
    })
    write_json(ASSETS / f"blockstates/{wood}_fireproof_slab.json", {
        "variants": {
            "type=bottom": {"model": f"reforestry:block/{wood}_slab"},
            "type=double": {"model": f"reforestry:block/{wood}_planks"},
            "type=top": {"model": f"reforestry:block/{wood}_slab_top"},
        }
    })
    write_json(ASSETS / f"models/item/{wood}_slab.json", {"parent": f"reforestry:block/{wood}_slab"})
    write_json(ASSETS / f"models/item/{wood}_fireproof_slab.json", {"parent": f"reforestry:block/{wood}_slab"})
    write_json(ASSETS / f"items/{wood}_slab.json", item_def(f"reforestry:item/{wood}_slab"))
    write_json(ASSETS / f"items/{wood}_fireproof_slab.json", item_def(f"reforestry:item/{wood}_fireproof_slab"))

    for kind, parent in (
        ("stairs", "stairs"),
        ("stairs_inner", "inner_stairs"),
        ("stairs_outer", "outer_stairs"),
    ):
        write_json(ASSETS / f"models/block/{wood}_{kind}.json", {
            "parent": f"minecraft:block/{parent}",
            "textures": {
                "bottom": f"reforestry:block/{wood}_planks",
                "side": f"reforestry:block/{wood}_planks",
                "top": f"reforestry:block/{wood}_planks",
            },
        })
    write_json(ASSETS / f"blockstates/{wood}_stairs.json", copy_larch_stairs_blockstate(wood))
    write_json(ASSETS / f"blockstates/{wood}_fireproof_stairs.json", copy_larch_stairs_blockstate(wood))
    write_json(ASSETS / f"models/item/{wood}_stairs.json", {"parent": f"reforestry:block/{wood}_stairs"})
    write_json(ASSETS / f"models/item/{wood}_fireproof_stairs.json", {"parent": f"reforestry:block/{wood}_stairs"})
    write_json(ASSETS / f"items/{wood}_stairs.json", item_def(f"reforestry:item/{wood}_stairs"))
    write_json(ASSETS / f"items/{wood}_fireproof_stairs.json", item_def(f"reforestry:item/{wood}_fireproof_stairs"))

    write_json(ASSETS / f"models/block/{wood}_fence_post.json", {
        "parent": "minecraft:block/fence_post",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"models/block/{wood}_fence_side.json", {
        "parent": "minecraft:block/fence_side",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"models/block/{wood}_fence_inventory.json", {
        "parent": "minecraft:block/fence_inventory",
        "textures": {"texture": f"reforestry:block/{wood}_planks"},
    })
    write_json(ASSETS / f"blockstates/{wood}_fence.json", copy_larch_fence_blockstate(wood))
    write_json(ASSETS / f"blockstates/{wood}_fireproof_fence.json", copy_larch_fence_blockstate(wood))
    write_json(ASSETS / f"models/item/{wood}_fence.json", {"parent": f"reforestry:block/{wood}_fence_inventory"})
    write_json(ASSETS / f"models/item/{wood}_fireproof_fence.json", {"parent": f"reforestry:block/{wood}_fence_inventory"})
    write_json(ASSETS / f"items/{wood}_fence.json", item_def(f"reforestry:item/{wood}_fence"))
    write_json(ASSETS / f"items/{wood}_fireproof_fence.json", item_def(f"reforestry:item/{wood}_fireproof_fence"))

    for suffix, parent in (
        ("fence_gate", "template_fence_gate"),
        ("fence_gate_open", "template_fence_gate_open"),
        ("fence_gate_wall", "template_fence_gate_wall"),
        ("fence_gate_wall_open", "template_fence_gate_wall_open"),
    ):
        write_json(ASSETS / f"models/block/{wood}_{suffix}.json", {
            "parent": f"minecraft:block/{parent}",
            "textures": {"texture": f"reforestry:block/{wood}_planks"},
        })
    write_json(ASSETS / f"blockstates/{wood}_fence_gate.json", copy_larch_fence_gate_blockstate(wood))
    write_json(ASSETS / f"blockstates/{wood}_fireproof_fence_gate.json", copy_larch_fence_gate_blockstate(wood))
    write_json(ASSETS / f"models/item/{wood}_fence_gate.json", {"parent": f"reforestry:block/{wood}_fence_gate"})
    write_json(ASSETS / f"models/item/{wood}_fireproof_fence_gate.json", {"parent": f"reforestry:block/{wood}_fence_gate"})
    write_json(ASSETS / f"items/{wood}_fence_gate.json", item_def(f"reforestry:item/{wood}_fence_gate"))
    write_json(ASSETS / f"items/{wood}_fireproof_fence_gate.json", item_def(f"reforestry:item/{wood}_fireproof_fence_gate"))

    copy_larch_door_models(wood)
    write_json(ASSETS / f"blockstates/{wood}_door.json", copy_larch_door_blockstate(wood))
    write_json(ASSETS / f"models/item/{wood}_door.json", {
        "parent": "minecraft:item/generated",
        "textures": {"layer0": f"reforestry:item/{wood}_door"},
    })
    write_json(ASSETS / f"items/{wood}_door.json", item_def(f"reforestry:item/{wood}_door"))

    for bid in (
        f"{wood}_planks",
        f"{wood}_fireproof_planks",
        f"{wood}_slab",
        f"{wood}_fireproof_slab",
        f"{wood}_stairs",
        f"{wood}_fireproof_stairs",
        f"{wood}_fence",
        f"{wood}_fireproof_fence",
        f"{wood}_fence_gate",
        f"{wood}_fireproof_fence_gate",
    ):
        write_json(DATA / f"reforestry/loot_table/blocks/{bid}.json", simple_loot(bid))
    write_json(DATA / f"reforestry/loot_table/blocks/{wood}_door.json", door_loot(f"{wood}_door"))


def write_tags(all_logs: list[str], products: list[str]) -> None:
    for wood in all_logs:
        write_json(DATA / f"reforestry/tags/block/{wood}_logs.json", {
            "values": [f"reforestry:{wood}_log"]
        })
        write_json(DATA / f"reforestry/tags/item/{wood}_logs.json", {
            "values": [f"reforestry:{wood}_log"]
        })
        write_json(DATA / f"reforestry/tags/block/fireproof_{wood}_logs.json", {
            "values": [f"reforestry:{wood}_fireproof_log"]
        })
        write_json(DATA / f"reforestry/tags/item/fireproof_{wood}_logs.json", {
            "values": [f"reforestry:{wood}_fireproof_log"]
        })

    def append_tag(path: Path, entries: list[str]) -> None:
        data = json.loads(path.read_text(encoding="utf-8"))
        values = data.setdefault("values", [])
        existing = set(values)
        for entry in entries:
            if entry not in existing:
                values.append(entry)
        write_json(path, data)

    burn = [f"#reforestry:{w}_logs" for w in all_logs]
    fireproof = [f"#reforestry:fireproof_{w}_logs" for w in all_logs]
    natural = [f"reforestry:{w}_log" for w in all_logs]
    append_tag(DATA / "minecraft/tags/block/logs_that_burn.json", burn)
    append_tag(DATA / "minecraft/tags/block/logs.json", fireproof)
    # logs.json also usually includes burning via #logs_that_burn in vanilla;
    # arboriculture puts fireproof tags in logs.json and burning woods in logs_that_burn.
    append_tag(DATA / "minecraft/tags/block/overworld_natural_logs.json", natural)

    def ensure_append(rel: str, entries: list[str]) -> None:
        path = DATA / rel
        if path.is_file():
            append_tag(path, entries)
        else:
            write_json(path, {"values": entries})

    ensure_append("minecraft/tags/item/logs_that_burn.json", burn)
    ensure_append("minecraft/tags/item/logs.json", fireproof)

    planks = []
    slabs = []
    stairs = []
    fences = []
    fence_gates = []
    doors = []
    for wood in products:
        planks.append(f"reforestry:{wood}_planks")
        planks.append(f"reforestry:{wood}_fireproof_planks")
        slabs.append(f"reforestry:{wood}_slab")
        slabs.append(f"reforestry:{wood}_fireproof_slab")
        stairs.append(f"reforestry:{wood}_stairs")
        stairs.append(f"reforestry:{wood}_fireproof_stairs")
        fences.append(f"reforestry:{wood}_fence")
        fences.append(f"reforestry:{wood}_fireproof_fence")
        fence_gates.append(f"reforestry:{wood}_fence_gate")
        fence_gates.append(f"reforestry:{wood}_fireproof_fence_gate")
        doors.append(f"reforestry:{wood}_door")

    for kind in ("block", "item"):
        ensure_append(f"minecraft/tags/{kind}/planks.json", planks)
        ensure_append(f"minecraft/tags/{kind}/wooden_slabs.json", slabs)
        ensure_append(f"minecraft/tags/{kind}/wooden_stairs.json", stairs)
        ensure_append(f"minecraft/tags/{kind}/wooden_fences.json", fences)
        ensure_append(f"minecraft/tags/{kind}/fence_gates.json", fence_gates)
        ensure_append(f"minecraft/tags/{kind}/wooden_doors.json", doors)
    ensure_append("minecraft/tags/item/wooden_fence_gates.json", fence_gates)


def write_recipes(products: list[str], log_only: list[str]) -> None:
    recipe_dir = DATA / "reforestry/recipe"
    fab_log = recipe_dir / "fabricator/fireproof/log"
    fab_planks = recipe_dir / "fabricator/fireproof/planks"

    def shapeless_planks(recipe_id: str, log_tag_or_item: str, result: str, count: int = 4) -> None:
        ingredient = log_tag_or_item if log_tag_or_item.startswith("#") else log_tag_or_item
        write_json(recipe_dir / f"{recipe_id}.json", {
            "type": "minecraft:crafting_shapeless",
            "category": "building",
            "ingredients": [ingredient],
            "result": {"count": count, "id": result},
        })

    for wood in products:
        shapeless_planks(f"{wood}_planks", f"#reforestry:{wood}_logs", f"reforestry:{wood}_planks")
        shapeless_planks(f"{wood}_fireproof_planks", f"#reforestry:fireproof_{wood}_logs", f"reforestry:{wood}_fireproof_planks")

        for fireproof in (False, True):
            prefix = f"{wood}_fireproof" if fireproof else wood
            planks = f"reforestry:{wood}_fireproof_planks" if fireproof else f"reforestry:{wood}_planks"
            write_json(recipe_dir / f"{prefix}_slab.json", {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "group": "wooden_slab",
                "key": {"#": planks},
                "pattern": ["###"],
                "result": {"count": 6, "id": f"reforestry:{prefix}_slab"},
            })
            write_json(recipe_dir / f"{prefix}_stairs.json", {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "group": "wooden_stairs",
                "key": {"#": planks},
                "pattern": ["#  ", "## ", "###"],
                "result": {"count": 4, "id": f"reforestry:{prefix}_stairs"},
            })
            write_json(recipe_dir / f"{prefix}_fence.json", {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "group": "wooden_fence",
                "key": {"#": "minecraft:stick", "W": planks},
                "pattern": ["W#W", "W#W"],
                "result": {"count": 3, "id": f"reforestry:{prefix}_fence"},
            })
            write_json(recipe_dir / f"{prefix}_fence_gate.json", {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "group": "wooden_fence_gate",
                "key": {"#": "minecraft:stick", "W": planks},
                "pattern": ["#W#", "#W#"],
                "result": {"id": f"reforestry:{prefix}_fence_gate"},
            })

        write_json(recipe_dir / f"{wood}_door.json", {
            "type": "minecraft:crafting_shaped",
            "category": "redstone",
            "group": "wooden_door",
            "key": {"#": f"reforestry:{wood}_planks"},
            "pattern": ["##", "##", "##"],
            "result": {"count": 3, "id": f"reforestry:{wood}_door"},
        })

        write_json(fab_log / f"{wood}.json", {
            "type": "reforestry:fabricator",
            "molten": {"fluid": "reforestry:glass", "amount": 500},
            "plan": [],
            "recipe": {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "key": {"#": "reforestry:refractory_wax", "X": f"reforestry:{wood}_log"},
                "pattern": ["   ", "X#X", "   "],
                "result": {"count": 2, "id": f"reforestry:{wood}_fireproof_log"},
            },
        })
        write_json(fab_planks / f"{wood}.json", {
            "type": "reforestry:fabricator",
            "molten": {"fluid": "reforestry:glass", "amount": 500},
            "plan": [],
            "recipe": {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "key": {"#": "reforestry:refractory_wax", "X": f"reforestry:{wood}_planks"},
                "pattern": ["XXX", "X#X", "XXX"],
                "result": {"count": 8, "id": f"reforestry:{wood}_fireproof_planks"},
            },
        })

    for wood in log_only:
        normal, fireproof = LOG_ONLY_PLANKS[wood]
        shapeless_planks(f"{wood}_planks", f"#reforestry:{wood}_logs", normal)
        shapeless_planks(f"{wood}_fireproof_planks", f"#reforestry:fireproof_{wood}_logs", fireproof)
        write_json(fab_log / f"{wood}.json", {
            "type": "reforestry:fabricator",
            "molten": {"fluid": "reforestry:glass", "amount": 500},
            "plan": [],
            "recipe": {
                "type": "minecraft:crafting_shaped",
                "category": "building",
                "key": {"#": "reforestry:refractory_wax", "X": f"reforestry:{wood}_log"},
                "pattern": ["   ", "X#X", "   "],
                "result": {"count": 2, "id": f"reforestry:{wood}_fireproof_log"},
            },
        })

    # shrub: fabricator fireproof only
    write_json(fab_log / "shrub.json", {
        "type": "reforestry:fabricator",
        "molten": {"fluid": "reforestry:glass", "amount": 500},
        "plan": [],
        "recipe": {
            "type": "minecraft:crafting_shaped",
            "category": "building",
            "key": {"#": "reforestry:refractory_wax", "X": "reforestry:shrub_log"},
            "pattern": ["   ", "X#X", "   "],
            "result": {"count": 2, "id": "reforestry:shrub_fireproof_log"},
        },
    })


def write_lang(all_woods: list[str]) -> None:
    lang = json.loads(LANG.read_text(encoding="utf-8"))
    lang["itemGroup.extra_trees"] = "Extra Trees"
    for wood in all_woods:
        key = f"for.trees.woodType.{wood}"
        lang[key] = DISPLAY[wood]
    LANG.write_text(json.dumps(lang, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")


def main() -> None:
    products, log_only, shrub = load_woods()
    all_logs = products + log_only + shrub
    copy_textures(products, log_only)
    for wood in products + log_only:
        write_log_assets(wood)
    write_shrub_assets()
    for wood in products:
        write_product_assets(wood)
    write_tags(all_logs, products)
    write_recipes(products, log_only)
    write_lang(all_logs)
    print(
        f"ET1a assets: {len(products)} product woods, {len(log_only)} log-only, "
        f"{len(shrub)} shrub; total log types {len(all_logs)}"
    )


if __name__ == "__main__":
    main()
