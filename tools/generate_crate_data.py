#!/usr/bin/env python3
"""Generate carpenter crate pack/unpack recipes and item models from CE 1.21.1.

Usage:
  python3 tools/generate_crate_data.py --apply
"""
from __future__ import annotations

import argparse
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CE = ROOT / "MarkDown_Maker/Finished_github_clone/2026-08-17/thedarkcolour-ForestryCE"
CE_CRATES = CE / "src/generated/resources/data/forestry/recipe/carpenter/crates"
ASSETS = ROOT / "src/main/resources/assets/reforestry"
DATA = ROOT / "src/main/resources/data/reforestry/recipe/carpenter/crates"

# CE 1.21.1 item ids that Re-Forestry still registers under CE 1.20.1 paths.
ITEM_REWRITE = {
    "forestry:honey_comb": "reforestry:bee_comb_honey",
    "forestry:cocoa_comb": "reforestry:bee_comb_cocoa",
    "forestry:simmering_comb": "reforestry:bee_comb_simmering",
    "forestry:stringy_comb": "reforestry:bee_comb_stringy",
    "forestry:frozen_comb": "reforestry:bee_comb_frozen",
    "forestry:dripping_comb": "reforestry:bee_comb_dripping",
    "forestry:silky_comb": "reforestry:bee_comb_silky",
    "forestry:parched_comb": "reforestry:bee_comb_parched",
    "forestry:mysterious_comb": "reforestry:bee_comb_mysterious",
    "forestry:powdery_comb": "reforestry:bee_comb_powdery",
    "forestry:wheaten_comb": "reforestry:bee_comb_wheaten",
    "forestry:mossy_comb": "reforestry:bee_comb_mossy",
    "forestry:mellow_comb": "reforestry:bee_comb_mellow",
    "forestry:kaolin_comb": "reforestry:bee_comb_kaolin",
    "forestry:vintage_comb": "reforestry:bee_comb_vintage",
    "forestry:spongy_comb": "reforestry:bee_comb_sponge",
    "forestry:sculken_comb": "reforestry:bee_comb_sculken",
    "forestry:fertilizer": "reforestry:fertilizer_compound",
    "forestry:pollen_cluster": "reforestry:pollen_cluster_normal",
    "forestry:crystalline_pollen_cluster": "reforestry:pollen_cluster_crystalline",
    "forestry:propolis": "reforestry:propolis_normal",
    "forestry:bronze_ingot": "reforestry:ingot_bronze",
    "forestry:tin_ingot": "reforestry:ingot_tin",
}


def rewrite_id(value: str) -> str:
    if value in ITEM_REWRITE:
        return ITEM_REWRITE[value]
    return value.replace("forestry:", "reforestry:")


def rewrite_ingredient(entry):
    if isinstance(entry, str):
        if entry.startswith("#"):
            return "#" + rewrite_id(entry[1:])
        return rewrite_id(entry)
    if isinstance(entry, dict):
        if "item" in entry:
            return rewrite_id(entry["item"])
        if "tag" in entry:
            return "#" + rewrite_id(entry["tag"])
        if "id" in entry:
            out = dict(entry)
            out["id"] = rewrite_id(entry["id"])
            return out
    raise ValueError(f"unsupported ingredient: {entry!r}")


def rewrite_keys(key: dict) -> dict:
    return {k: rewrite_ingredient(v) for k, v in key.items()}


def liquid(data: dict) -> dict | None:
    raw = data.get("liquid")
    if not raw:
        return None
    fluid = raw.get("fluid") or raw.get("id")
    amount = raw.get("amount")
    if not fluid or amount is None:
        return None
    return {"fluid": rewrite_id(fluid), "amount": amount}


def box_ingredient(data: dict):
    raw = data.get("box")
    if raw in (None, [], {}):
        return None
    return rewrite_ingredient(raw)


def convert_embedded(recipe: dict) -> dict:
    out: dict = {"category": recipe.get("category", "misc")}
    if "pattern" in recipe:
        out["type"] = "minecraft:crafting_shaped"
        out["key"] = rewrite_keys(recipe["key"])
        out["pattern"] = recipe["pattern"]
    else:
        out["type"] = "minecraft:crafting_shapeless"
        out["ingredients"] = [rewrite_ingredient(v) for v in recipe["ingredients"]]
    result = recipe["result"]
    out["result"] = {
        "id": rewrite_id(result.get("id") or result.get("item")),
    }
    if "count" in result:
        out["result"]["count"] = result["count"]
    out["show_notification"] = True
    return out


def convert_carpenter(data: dict) -> dict:
    out: dict = {
        "type": "reforestry:carpenter",
        "time": data["time"],
        "recipe": convert_embedded(data["recipe"]),
    }
    box = box_ingredient(data)
    if box is not None:
        out["box"] = box
    liq = liquid(data)
    if liq is not None:
        out["liquid"] = liq
    return out


def write_json(path: Path, data) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")


COMB_LAYERS = ("reforestry:item/bee_combs.0", "reforestry:item/bee_combs.1")
POLLEN_LAYERS = ("reforestry:item/pollen.0", "reforestry:item/pollen.1")
PROPOLIS_TEXTURE = "reforestry:item/propolis.0"

TEXTURE_OVERRIDES = {
    "minecraft:cactus": "minecraft:block/cactus_side",
    "minecraft:mycelium": "minecraft:block/mycelium_side",
    "minecraft:grass_block": "minecraft:block/grass_block_top",
}

BLOCK_TEXTURE_ITEMS = frozenset({
    "reforestry:humus",
    "reforestry:bog_earth",
    "minecraft:oak_log",
    "minecraft:birch_log",
    "minecraft:jungle_log",
    "minecraft:spruce_log",
    "minecraft:acacia_log",
    "minecraft:dark_oak_log",
    "minecraft:cobblestone",
    "minecraft:dirt",
    "minecraft:grass_block",
    "minecraft:stone",
    "minecraft:granite",
    "minecraft:diorite",
    "minecraft:andesite",
    "minecraft:prismarine",
    "minecraft:prismarine_bricks",
    "minecraft:dark_prismarine",
    "minecraft:bricks",
    "minecraft:cactus",
    "minecraft:sand",
    "minecraft:red_sand",
    "minecraft:obsidian",
    "minecraft:netherrack",
    "minecraft:soul_sand",
    "minecraft:sandstone",
    "minecraft:nether_bricks",
    "minecraft:mycelium",
    "minecraft:gravel",
    "minecraft:oak_sapling",
    "minecraft:birch_sapling",
    "minecraft:jungle_sapling",
    "minecraft:spruce_sapling",
    "minecraft:acacia_sapling",
    "minecraft:dark_oak_sapling",
})

EXAMPLE_CRATES = ("crated_wheat", "crated_oak_log", "crated_honey_comb")


def is_comb_item(item_id: str) -> bool:
    return item_id.startswith("reforestry:bee_comb_")


def is_pollen_item(item_id: str) -> bool:
    return item_id in (
        "reforestry:pollen_cluster_normal",
        "reforestry:pollen_cluster_crystalline",
    )


def contents_textures(item_id: str) -> list[str]:
    if is_comb_item(item_id):
        return list(COMB_LAYERS)
    if is_pollen_item(item_id):
        return list(POLLEN_LAYERS)
    if item_id == "reforestry:propolis_normal":
        return [PROPOLIS_TEXTURE]
    if item_id in TEXTURE_OVERRIDES:
        return [TEXTURE_OVERRIDES[item_id]]
    namespace, path = item_id.split(":", 1)
    folder = "block" if item_id in BLOCK_TEXTURE_ITEMS else "item"
    return [f"{namespace}:{folder}/{path}"]


def contained_tints(item_id: str) -> list[int]:
    namespace, path = item_id.split(":", 1)
    if namespace != "reforestry":
        return []
    definition_path = ASSETS / "items" / f"{path}.json"
    if not definition_path.is_file():
        return []
    data = json.loads(definition_path.read_text(encoding="utf-8"))
    tints = data.get("model", {}).get("tints")
    if not tints:
        return []
    return [int(entry["value"]) for entry in tints]


def crate_item_definition(model: str, tints: list[int] | None = None) -> dict:
    model_obj: dict = {"type": "minecraft:model", "model": model}
    if tints:
        model_obj["tints"] = [{"type": "minecraft:constant", "value": value} for value in tints]
    return {"model": model_obj}


def crate_item_model(filled: bool, overlay_layers: list[str] | None = None) -> dict:
    if not filled:
        return {"parent": "item/generated", "textures": {"layer0": "reforestry:item/crate"}}
    textures = {"layer0": "reforestry:item/crate-filled"}
    if overlay_layers:
        for index, texture in enumerate(overlay_layers, start=1):
            textures[f"layer{index}"] = texture
    return {"parent": "item/generated", "textures": textures}


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()
    if not CE_CRATES.is_dir():
        raise SystemExit(f"CE crate recipes not found: {CE_CRATES}")

    recipes: list[tuple[Path, dict]] = []
    crate_ids = {"crate"}
    contained_by_crate: dict[str, str] = {}

    empty_src = CE_CRATES / "empty.json"
    recipes.append((DATA / "empty.json", convert_carpenter(json.loads(empty_src.read_text()))))

    for src in sorted(CE_CRATES.rglob("*.json")):
        if src.name == "empty.json":
            continue
        rel = src.relative_to(CE_CRATES)
        dest = DATA / rel
        data = json.loads(src.read_text())
        converted = convert_carpenter(data)
        recipes.append((dest, converted))
        result_id = converted["recipe"]["result"]["id"]
        if result_id.startswith("reforestry:crated_"):
            crate_ids.add(result_id.split(":", 1)[1])
        for ingredient in converted["recipe"].get("ingredients", []):
            if isinstance(ingredient, str) and ingredient.startswith("reforestry:crated_"):
                crate_id = ingredient.split(":", 1)[1]
                crate_ids.add(crate_id)
                contained_by_crate[crate_id] = result_id

    filled_ids = sorted(crate_id for crate_id in crate_ids if crate_id != "crate")
    missing = [crate_id for crate_id in filled_ids if crate_id not in contained_by_crate]
    if missing:
        raise SystemExit(f"unpack recipes missing contained item for: {missing}")

    item_files: list[tuple[Path, dict]] = []
    model_files: list[tuple[Path, dict]] = []
    for crate_id in filled_ids:
        contained = contained_by_crate[crate_id]
        overlays = contents_textures(contained)
        model_path = ASSETS / "models/item" / f"{crate_id}.json"
        item_path = ASSETS / "items" / f"{crate_id}.json"
        tints = contained_tints(contained)
        crate_tints = [-1, *tints] if tints else None
        model_files.append((model_path, crate_item_model(True, overlays)))
        item_files.append((item_path, crate_item_definition(f"reforestry:item/{crate_id}", crate_tints)))

    print(f"crate recipes: {len(recipes)}")
    print(f"filled crate models: {len(model_files)}")
    for crate_id in EXAMPLE_CRATES:
        contained = contained_by_crate[crate_id]
        print(f"{crate_id}: {contained} -> {contents_textures(contained)}")
    if not args.apply:
        print("dry-run; pass --apply to write")
        return
    for path, data in recipes:
        write_json(path, data)
    for path, data in model_files:
        write_json(path, data)
    for path, data in item_files:
        write_json(path, data)
    print("wrote crate recipes, models, and item definitions")


if __name__ == "__main__":
    main()
