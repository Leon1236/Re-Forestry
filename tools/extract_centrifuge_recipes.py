#!/usr/bin/env python3
"""Extracts Forestry CE's generated centrifuge recipe JSON into this project's
reforestry:centrifuge recipe type (data/reforestry/recipe/centrifuge/*.json).

Recipes referencing an item not yet registered in this port (e.g. amber_drone/
amber_sapling carry NBT-bearing genome data that has no equivalent yet) are
skipped and reported, never guessed.

Usage:
  python3 tools/extract_centrifuge_recipes.py --root . --apply
Without --apply this only prints what would be written (dry run).
"""
import argparse
import json
import sys
from pathlib import Path

from ce_paths import rel as _ce_rel, generated as _ce_generated

OLD_NS = "forestry"
NEW_NS = "reforestry"

KNOWN_ITEMS = {
    "minecraft:honeycomb", "minecraft:cocoa_beans", "minecraft:clay_ball",
    "minecraft:quartz", "minecraft:gunpowder", "minecraft:wheat", "minecraft:snowball",
    "minecraft:sculk",
    "reforestry:beeswax", "reforestry:amber", "reforestry:refractory_wax",
    "reforestry:honey_drop", "reforestry:honeydew", "reforestry:experience_drop",
    "reforestry:silk_wisp",
    "reforestry:propolis_normal", "reforestry:propolis_pulsating",
    "reforestry:propolis_silky", "reforestry:propolis_volcanic",
    "reforestry:pollen_cluster_normal", "reforestry:pollen_cluster_crystalline",
    "reforestry:bee_comb_honey", "reforestry:bee_comb_cocoa", "reforestry:bee_comb_simmering",
    "reforestry:bee_comb_stringy", "reforestry:bee_comb_frozen", "reforestry:bee_comb_dripping",
    "reforestry:bee_comb_silky", "reforestry:bee_comb_parched", "reforestry:bee_comb_mysterious",
    "reforestry:bee_comb_powdery", "reforestry:bee_comb_wheaten", "reforestry:bee_comb_mossy",
    "reforestry:bee_comb_mellow", "reforestry:bee_comb_kaolin", "reforestry:bee_comb_vintage",
    "reforestry:bee_comb_sponge", "reforestry:bee_comb_sculken",
}


def rename_id(resource_id: str) -> str:
    ns, _, path = resource_id.partition(":")
    return f"{NEW_NS}:{path}" if ns == OLD_NS else resource_id


def transform(data):
    if set(data["input"].keys()) - {"item"}:
        return None, ["input carries NBT/tag data - not portable in this stage"]

    input_item = rename_id(data["input"]["item"])
    products = []
    referenced = {input_item}
    for product in data["products"]:
        if set(product.keys()) - {"item", "chance", "count"}:
            return None, [f"product {product.get('item')} carries NBT/tag data - not portable in this stage"]
        item = rename_id(product["item"])
        referenced.add(item)
        entry = {"item": item, "chance": product["chance"]}
        if "count" in product:
            entry["count"] = product["count"]
        products.append(entry)

    missing = sorted(i for i in referenced if i not in KNOWN_ITEMS)
    if missing:
        return None, [f"unregistered item(s): {', '.join(missing)}"]

    return {
        "type": f"{NEW_NS}:centrifuge",
        "input": input_item,
        "time": data.get("time", 20),
        "products": products,
    }, []


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--ce-gen-root",
                         default=_ce_rel(_ce_generated()))
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    src_dir = root / args.ce_gen_root / "data" / OLD_NS / "recipes" / "centrifuge"
    dst_dir = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe" / "centrifuge"
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting centrifuge recipes\n  from: {src_dir}\n  to:   {dst_dir}\n")

    written, skipped = 0, 0
    for src in sorted(src_dir.glob("*.json")):
        data = json.loads(src.read_text(encoding="utf-8"))
        transformed, reasons = transform(data)
        if transformed is None:
            print(f"SKIP  {src.name}  ({'; '.join(reasons)})")
            skipped += 1
            continue
        dst = dst_dir / src.name
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
