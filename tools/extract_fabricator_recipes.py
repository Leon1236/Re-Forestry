#!/usr/bin/env python3
"""Extracts Forestry CE fabricator shaped craft recipe JSON into reforestry:fabricator.

Converts CE forge fluid objects (Amount + FluidName in mB) to port shape
(fluid id + amount in mB). Renames forestry: -> reforestry:. Maps legacy
forge tags to c: equivalents where needed.

Usage:
  python3 tools/extract_fabricator_recipes.py --root . --apply
  python3 tools/extract_fabricator_recipes.py --root . --apply --only hardened_casing
"""
import argparse
import json
import sys
from pathlib import Path

from ce_paths import rel as _ce_rel, generated as _ce_generated

OLD_NS = "forestry"
NEW_NS = "reforestry"

TAG_MAP = {
    "forge:gems/diamond": "c:gems/diamond",
    "forge:glass": "c:glass_blocks/colorless",
}


def rename_id(resource_id: str) -> str:
    ns, _, path = resource_id.partition(":")
    return f"{NEW_NS}:{path}" if ns == OLD_NS else resource_id


def map_ingredient(ingredient) -> dict | list:
    if isinstance(ingredient, list):
        return [map_ingredient(entry) for entry in ingredient]
    if isinstance(ingredient, dict):
        if "tag" in ingredient:
            tag = ingredient["tag"]
            return {"tag": TAG_MAP.get(tag, tag.replace("forge:", "c:"))}
        if "item" in ingredient:
            return {"item": rename_id(ingredient["item"])}
    return ingredient


def map_recipe_node(node):
    if isinstance(node, dict):
        out = {}
        for key, value in node.items():
            if key in ("item", "id") and isinstance(value, str):
                out[key] = rename_id(value)
            elif key in ("key", "ingredients", "plan", "result"):
                if key == "key" and isinstance(value, dict):
                    out[key] = {k: map_ingredient(v) for k, v in value.items()}
                elif key == "ingredients" and isinstance(value, list):
                    out[key] = [map_ingredient(v) for v in value]
                elif key == "plan":
                    out[key] = map_ingredient(value)
                elif key == "result" and isinstance(value, dict):
                    mapped = dict(value)
                    if "item" in mapped:
                        mapped["item"] = rename_id(mapped["item"])
                    if "id" in mapped:
                        mapped["id"] = rename_id(mapped["id"])
                    out[key] = mapped
                else:
                    out[key] = map_recipe_node(value)
            else:
                out[key] = map_recipe_node(value)
        return out
    if isinstance(node, list):
        return [map_recipe_node(entry) for entry in node]
    return node


def transform_fluid(data: dict) -> dict:
    return {"fluid": rename_id(data["FluidName"]), "amount": data["Amount"]}


def transform(data: dict) -> tuple[dict | None, list[str]]:
    if data.get("type") != f"{OLD_NS}:fabricator":
        return None, ["not a fabricator craft recipe"]
    try:
        return {
            "type": f"{NEW_NS}:fabricator",
            "molten": transform_fluid(data["molten"]),
            "plan": map_ingredient(data["plan"]),
            "recipe": map_recipe_node(data["recipe"]),
        }, []
    except KeyError as exc:
        return None, [f"missing key: {exc}"]


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--ce-gen-root",
                         default=_ce_rel(_ce_generated()))
    parser.add_argument("--only", action="append", default=[], metavar="STEM",
                        help="recipe file stem without .json (repeatable)")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    src_dir = root / args.ce_gen_root / "data" / OLD_NS / "recipes" / "fabricator"
    dst_dir = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe" / "fabricator"
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)

    only = set(args.only)
    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting fabricator craft recipes\n  from: {src_dir}\n  to:   {dst_dir}\n")

    written, skipped = 0, 0
    for src in sorted(src_dir.rglob("*.json")):
        if src.parent.name == "smelting":
            continue
        rel = src.relative_to(src_dir)
        if only and rel.stem not in only and str(rel.with_suffix("")) not in only:
            continue
        data = json.loads(src.read_text(encoding="utf-8"))
        transformed, reasons = transform(data)
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
