#!/usr/bin/env python3
"""Extract Forestry CE carpenter recipes into reforestry:carpenter JSON.

Escritoire is included once the block exists. Never extracts mail
(stamps/letters/catalogue) — that module is out of scope. Rewrites forge
tags to c:/minecraft equivalents and forestry: -> reforestry:.

Usage:
  python3 tools/extract_carpenter_recipes.py --root . --apply
  python3 tools/extract_carpenter_recipes.py --root . --apply --only escritoire
  python3 tools/extract_carpenter_recipes.py --root . --apply --only wood_pulp --only woven_silk
"""
import argparse
import json
import sys
from pathlib import Path

from ce_paths import rel as _ce_rel, generated as _ce_generated

OLD_NS = "forestry"
NEW_NS = "reforestry"

# Top-level CE filenames (and circuits/* stems) that Re-Forestry can load today.
PORTABLE = {
    "ash_brick.json",
    "bog_earth.json",
    "candles.json",
    "carton.json",
    "dissipation_charge.json",
    "ender_pearl.json",
    "escritoire.json",
    "hardened_casing.json",
    "humus.json",
    "impregnated_casing.json",
    "impregnated_stick.json",
    "iodine_charge.json",
    "kit_axe.json",
    "kit_hoe.json",
    "kit_pickaxe.json",
    "kit_shovel.json",
    "kit_sword.json",
    "paper.json",
    "portable_analyzer.json",
    "reclaim_bronze_axe.json",
    "reclaim_bronze_hoe.json",
    "reclaim_bronze_pickaxe.json",
    "reclaim_bronze_shovel.json",
    "reclaim_bronze_sword.json",
    "scented_paneling.json",
    "soldering_iron.json",
    "wood_pulp.json",
    "woven_silk.json",
}

PORTABLE_CIRCUITS = {
    "basic.json",
    "enhanced.json",
    "refined.json",
    "intricate.json",
}

MAIL_NEVER = {
    "catalogue.json",
    "letter.json",
    "stamp_1n.json",
    "stamp_2n.json",
    "stamp_5n.json",
    "stamp_10n.json",
    "stamp_20n.json",
    "stamp_50n.json",
    "stamp_100n.json",
}

TAG_MAP = {
    "forge:sand": "minecraft:sand",
    "forge:glass": "c:glass_blocks/colorless",
    "forge:sawdust": "c:dusts/wood",
    "forge:dusts/wood": "c:dusts/wood",
    "forge:ingots/tin": "c:ingots/tin",
    "forge:ingots/bronze": "c:ingots/bronze",
    "forge:ingots/iron": "c:ingots/iron",
    "forge:ingots/gold": "c:ingots/gold",
    "forge:ingots/copper": "c:ingots/copper",
    "forge:gems/diamond": "c:gems/diamond",
    "forge:gems/apatite": "c:gems/apatite",
    "forge:dusts/redstone": "minecraft:redstone",
}


def rename_id(resource_id: str) -> str:
    ns, _, path = resource_id.partition(":")
    return f"{NEW_NS}:{path}" if ns == OLD_NS else resource_id


def map_tag(tag: str) -> str:
    mapped = TAG_MAP.get(tag, tag.replace("forge:", "c:") if tag.startswith("forge:") else tag)
    return mapped if mapped.startswith("#") else f"#{mapped}"


def transform_key(value):
    if isinstance(value, str):
        if value.startswith("#"):
            return map_tag(value[1:])
        return rename_id(value)
    if isinstance(value, dict):
        if "item" in value:
            return rename_id(value["item"])
        if "tag" in value:
            tag = value["tag"]
            # redstone has no common tag in our datapack — prefer the item id
            if tag in ("forge:dusts/redstone", "c:dusts/redstone"):
                return "minecraft:redstone"
            return map_tag(tag)
    return value


def transform_recipe_inner(data):
    out = dict(data)
    if "key" in out:
        out["key"] = {k: transform_key(v) for k, v in out["key"].items()}
    if "ingredients" in out:
        out["ingredients"] = [transform_key(v) for v in out["ingredients"]]
    if "result" in out:
        result = out["result"]
        if isinstance(result, dict) and "item" in result:
            item = rename_id(result["item"])
            count = result.get("count", 1)
            out["result"] = {"id": item, "count": count} if count != 1 else {"id": item}
    return out


def transform_box(box):
    if not box or box == []:
        return None
    if isinstance(box, list) and len(box) == 1:
        return transform_key(box[0])
    return transform_key(box)


def transform(data):
    if data.get("type") != f"{OLD_NS}:carpenter":
        return None, ["not a carpenter recipe"]

    transformed = {
        "type": f"{NEW_NS}:carpenter",
        "time": data.get("time", 5),
        "recipe": transform_recipe_inner(data["recipe"]),
    }
    if "liquid" in data:
        liquid = data["liquid"]
        transformed["liquid"] = {
            "fluid": rename_id(liquid["FluidName"]),
            "amount": liquid["Amount"],
        }
    box = transform_box(data.get("box"))
    if box is not None:
        transformed["box"] = box
    # Skip CE NBT result overrides (circuit boards are separate items in RF).
    return transformed, []


def circuit_dst_name(src_name: str) -> str:
    stem = Path(src_name).stem
    return f"circuit_board_{stem}.json"


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--ce-gen-root",
                        default=_ce_rel(_ce_generated()))
    parser.add_argument("--apply", action="store_true")
    parser.add_argument("--all-ce", action="store_true",
                        help="Attempt every non-mail CE carpenter recipe (may reference missing items).")
    parser.add_argument("--escritoire", action="store_true",
                        help="Also extract escritoire (same as --only escritoire when not using --all-ce).")
    parser.add_argument("--only", action="append", default=[], metavar="STEM",
                        help="recipe stem without .json (repeatable); circuits use circuit_board_<type>")
    args = parser.parse_args()

    root = Path(args.root)
    src_dir = root / args.ce_gen_root / "data" / OLD_NS / "recipes" / "carpenter"
    dst_dir = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe" / "carpenter"
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)

    only = set(args.only)
    if args.escritoire:
        only.add("escritoire")
    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting carpenter recipes\n  from: {src_dir}\n  to:   {dst_dir}\n")

    jobs = []
    for src in sorted(src_dir.glob("*.json")):
        stem = src.stem
        if src.name in MAIL_NEVER:
            continue
        if only and stem not in only and f"circuit_board_{stem}" not in only:
            continue
        if not args.all_ce and not only and src.name not in PORTABLE:
            continue
        jobs.append((src, dst_dir / src.name))

    circuits_dir = src_dir / "circuits"
    if circuits_dir.is_dir():
        for src in sorted(circuits_dir.glob("*.json")):
            dst_name = circuit_dst_name(src.name)
            stem = Path(dst_name).stem
            if only and stem not in only and src.stem not in only:
                continue
            if not args.all_ce and not only and src.name not in PORTABLE_CIRCUITS:
                continue
            jobs.append((src, dst_dir / dst_name))

    written, skipped = 0, 0
    for src, dst in jobs:
        data = json.loads(src.read_text(encoding="utf-8"))
        transformed, reasons = transform(data)
        if transformed is None:
            print(f"SKIP  {src.relative_to(src_dir)}  ({'; '.join(reasons)})")
            skipped += 1
            continue
        print(f"WRITE {dst.relative_to(root)}")
        if args.apply:
            dst.parent.mkdir(parents=True, exist_ok=True)
            dst.write_text(json.dumps(transformed, indent=2) + "\n", encoding="utf-8")
        written += 1

    print(f"\nsummary: written={written} skipped={skipped}")
    if not args.apply:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
