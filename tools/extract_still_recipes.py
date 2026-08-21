#!/usr/bin/env python3
"""Extracts Forestry CE still recipe JSON into reforestry:still recipes.

Converts CE forge fluid objects (Amount + FluidName in mB) to port shape
(fluid id + amount in mB). Renames forestry: -> reforestry:.

Usage:
  python3 tools/extract_still_recipes.py --root . --apply
"""
import argparse
import json
import sys
from pathlib import Path

from ce_paths import rel as _ce_rel, generated as _ce_generated

OLD_NS = "forestry"
NEW_NS = "reforestry"


def rename_id(resource_id: str) -> str:
    ns, _, path = resource_id.partition(":")
    return f"{NEW_NS}:{path}" if ns == OLD_NS else resource_id


def transform_fluid(data: dict) -> dict:
    amount = data["Amount"]
    fluid_name = rename_id(data["FluidName"])
    return {"fluid": fluid_name, "amount": amount}


def transform(data: dict) -> tuple[dict | None, list[str]]:
    if data.get("type") != f"{OLD_NS}:still":
        return None, ["not a still recipe"]
    try:
        return {
            "type": f"{NEW_NS}:still",
            "input": transform_fluid(data["input"]),
            "output": transform_fluid(data["output"]),
            "time": data["time"],
        }, []
    except KeyError as exc:
        return None, [f"missing key: {exc}"]


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--ce-gen-root",
                         default=_ce_rel(_ce_generated()))
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    src_dir = root / args.ce_gen_root / "data" / OLD_NS / "recipes" / "still"
    dst_dir = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe" / "still"
    if not src_dir.is_dir():
        print(f"error: {src_dir} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting still recipes\n  from: {src_dir}\n  to:   {dst_dir}\n")

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
