#!/usr/bin/env python3
"""Extracts Forestry CE squeezer + squeezer_container recipe JSON into reforestry types.

Converts CE forge fluid objects (Amount + FluidName in mB) to port shape
(fluid id + amount in mB). Renames forestry: -> reforestry:. Maps legacy
forge tags to c: equivalents where needed. Skips recipes referencing items
or fluids not yet registered in this port.

Usage:
  python3 tools/extract_squeezer_recipes.py --root . --apply
"""
import argparse
import json
import sys
from pathlib import Path

from ce_paths import rel as _ce_rel, generated as _ce_generated

OLD_NS = "forestry"
NEW_NS = "reforestry"

TAG_MAP = {
    "forge:seeds": "c:seeds",
    "forge:glass": "c:glass_blocks/colorless",
    "forge:ingots/tin": "c:ingots/tin",
}

KNOWN_FLUIDS = {
    "reforestry:seed_oil", "reforestry:juice", "reforestry:honey", "reforestry:ice",
    "reforestry:biomass", "reforestry:bio_ethanol", "reforestry:short_mead", "reforestry:glass",
    "reforestry:wax", "minecraft:water", "minecraft:lava", "minecraft:milk",
}

KNOWN_ITEMS = {
    "minecraft:air", "minecraft:cactus", "minecraft:ice", "minecraft:magma_block",
    "minecraft:sand", "minecraft:sponge", "minecraft:honey_block", "minecraft:honeycomb_block",
    "minecraft:cobblestone", "minecraft:snowball", "minecraft:water_bucket",
    "reforestry:can", "reforestry:capsule", "reforestry:refractory", "reforestry:ingot_tin",
    "reforestry:mulch", "reforestry:honey_drop", "reforestry:honeydew",
    "reforestry:bee_comb_sponge", "reforestry:beeswax", "reforestry:refractory_wax",
    "reforestry:propolis", "reforestry:phosphor", "reforestry:ice_shard",
}


def rename_id(resource_id: str) -> str:
    ns, _, path = resource_id.partition(":")
    return f"{NEW_NS}:{path}" if ns == OLD_NS else resource_id


def map_ingredient(ingredient: dict) -> dict:
    if "tag" in ingredient:
        tag = ingredient["tag"]
        return {"tag": TAG_MAP.get(tag, tag.replace("forge:", "c:"))}
    if "item" in ingredient:
        return {"item": rename_id(ingredient["item"])}
    return ingredient


def transform_item(data: dict) -> dict:
    item = rename_id(data["id"])
    count = data.get("Count", data.get("count", 1))
    return {"item": item, "count": count}


def transform_fluid(data: dict) -> dict:
    return {"fluid": rename_id(data["FluidName"]), "amount": data["Amount"]}


def check_refs(refs: set[str], kind: str) -> list[str]:
    missing = []
    for ref in refs:
        if ref.startswith("#"):
            continue
        if kind == "fluid" and ref not in KNOWN_FLUIDS and not ref.startswith("minecraft:"):
            missing.append(ref)
        elif kind == "item" and ref not in KNOWN_ITEMS and ref != "minecraft:air" and not ref.startswith("minecraft:"):
            missing.append(ref)
    return missing


def transform_squeezer(data: dict) -> tuple[dict | None, list[str]]:
    fluid_refs = set()
    item_refs = set()
    output = transform_fluid(data["output"])
    fluid_refs.add(output["fluid"])
    remnant = transform_item(data["remnant"])
    if remnant["item"] != "minecraft:air":
        item_refs.add(remnant["item"])
    resources = [map_ingredient(element) for element in data["resources"]]
    for ing in resources:
        if "item" in ing:
            item_refs.add(ing["item"])
    missing = check_refs(fluid_refs, "fluid") + check_refs(item_refs, "item")
    if missing:
        return None, [f"unknown refs: {', '.join(sorted(missing))}"]
    return {
        "type": f"{NEW_NS}:squeezer",
        "time": data["time"],
        "resources": resources,
        "output": output,
        "remnant": remnant,
        "chance": data["chance"],
    }, []


def transform_container(data: dict) -> tuple[dict | None, list[str]]:
    container = transform_item(data["container"])
    remnants = transform_item(data["remnants"])
    refs = {container["item"], remnants["item"]}
    missing = check_refs(refs, "item")
    if missing:
        return None, [f"unknown refs: {', '.join(sorted(missing))}"]
    return {
        "type": f"{NEW_NS}:squeezer_container",
        "container": container,
        "time": data["time"],
        "remnants": remnants,
        "remnantsChance": data["remnantsChance"],
    }, []


def transform(data: dict) -> tuple[dict | None, list[str]]:
    recipe_type = data.get("type", "")
    if recipe_type == f"{OLD_NS}:squeezer":
        return transform_squeezer(data)
    if recipe_type == f"{OLD_NS}:squeezer_container":
        return transform_container(data)
    return None, [f"unsupported type {recipe_type}"]


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--ce-gen-root",
                         default=_ce_rel(_ce_generated()))
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    src_dir = root / args.ce_gen_root / "data" / OLD_NS / "recipes" / "squeezer"
    dst_dir = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe" / "squeezer"
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting squeezer recipes\n  from: {src_dir}\n  to:   {dst_dir}\n")

    written, skipped = 0, 0
    for src in sorted(src_dir.rglob("*.json")):
        data = json.loads(src.read_text(encoding="utf-8"))
        transformed, reasons = transform(data)
        rel = src.relative_to(src_dir)
        if transformed is None:
            print(f"SKIP  {rel}  ({'; '.join(reasons)})")
            skipped += 1
            continue
        dst = dst_dir / rel
        print(f"WRITE {dst}")
        if args.apply:
            dst.parent.mkdir(parents=True, exist_ok=True)
            dst.write_text(json.dumps(transformed, indent=2) + "\n", encoding="utf-8")
        written += 1

    print(f"\nsummary: written={written} skipped={skipped}")
    if not args.apply:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
