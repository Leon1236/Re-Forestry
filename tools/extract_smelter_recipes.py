#!/usr/bin/env python3
"""Extracts Forestry CE's generated smelter recipe JSON into this project's
reforestry:smelter recipe type (data/reforestry/recipe/smelter/*.json).

Unwraps forge:conditional wrappers (keeps inner recipe; tag-empty conditions
cannot be evaluated offline). Converts forge: ingredient tags to c: tags.

Usage:
  python3 tools/extract_smelter_recipes.py --root . --apply
Without --apply this only prints what would be written (dry run).
"""
import argparse
import json
import sys
from pathlib import Path

from ce_paths import rel as _ce_rel, generated as _ce_generated

OLD_NS = "forestry"
NEW_NS = "reforestry"

FORGE_TAG_PREFIX = "forge:"
C_TAG_PREFIX = "c:"


def rename_id(resource_id: str) -> str:
    ns, _, path = resource_id.partition(":")
    return f"{NEW_NS}:{path}" if ns == OLD_NS else resource_id


def rename_tag(tag: str) -> str:
    if tag.startswith(FORGE_TAG_PREFIX):
        return C_TAG_PREFIX + tag[len(FORGE_TAG_PREFIX):]
    return tag


def transform_ingredient_stack(data: dict) -> dict:
    count = data["count"]
    ingredient = data["ingredient"]
    if "item" in ingredient:
        return {"count": count, "ingredient": rename_id(ingredient["item"])}
    if "tag" in ingredient:
        return {"count": count, "ingredient": {"tag": rename_tag(ingredient["tag"])}}
    raise ValueError(f"unsupported ingredient shape: {ingredient}")


def unwrap_recipe(data: dict) -> dict | None:
    recipe_type = data.get("type", "")
    if recipe_type == "forge:conditional":
        recipes = data.get("recipes", [])
        if not recipes:
            return None
        return recipes[0].get("recipe")
    if recipe_type == f"{OLD_NS}:smelter":
        return data
    return None


def transform(data: dict) -> tuple[dict | None, list[str]]:
    inner = unwrap_recipe(data)
    if inner is None:
        return None, ["not a smelter recipe"]

    if inner.get("type") != f"{OLD_NS}:smelter":
        return None, ["inner recipe is not forestry:smelter"]

    if "temperature" in inner and inner["temperature"] != 0:
        return None, ["non-zero temperature not ported in this stage"]

    try:
        inputs = [transform_ingredient_stack(entry) for entry in inner["inputs"]]
        output = transform_ingredient_stack(inner["output"])
    except (KeyError, ValueError) as exc:
        return None, [str(exc)]

    return {
        "type": f"{NEW_NS}:smelter",
        "inputs": inputs,
        "output": output,
        "processingTime": inner["processingTime"],
    }, []


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--ce-gen-root",
                         default=_ce_rel(_ce_generated()))
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    src_dir = root / args.ce_gen_root / "data" / OLD_NS / "recipes" / "smelter"
    dst_dir = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe" / "smelter"
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting smelter recipes\n  from: {src_dir}\n  to:   {dst_dir}\n")

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
