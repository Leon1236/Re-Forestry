#!/usr/bin/env python3
"""Extract Gendustry mutagen/protein/DNA recipes into reforestry datapack recipes.

Usage:
  python3 tools/extract_gendustry_processor_recipes.py --apply
"""
import argparse
import json
import sys
from pathlib import Path

DONOR_NS = "gendustry"
FORESTRY_NS = "forestry"
NEW_NS = "reforestry"
KINDS = ("mutagen", "protein", "dna")


def rename_id(resource_id: str) -> str:
    ns, _, path = resource_id.partition(":")
    if ns in (DONOR_NS, FORESTRY_NS):
        return f"{NEW_NS}:{path}"
    return resource_id


def map_ingredient(ingredient: dict) -> dict:
    if "tag" in ingredient:
        tag = ingredient["tag"].replace("forge:", "c:")
        return {"tag": rename_id(tag) if ":" in tag and not tag.startswith("#") else tag}
    if "item" in ingredient:
        return {"item": rename_id(ingredient["item"])}
    return ingredient


def transform(data: dict, kind: str) -> tuple[dict | None, list[str]]:
    expected = f"{DONOR_NS}:{kind}"
    if data.get("type") != expected:
        return None, [f"not a {kind} recipe (type={data.get('type')})"]
    try:
        out = {
            "type": f"{NEW_NS}:{kind}",
            "amount": data["amount"],
        }
        if kind in ("mutagen", "protein"):
            out["ingredient"] = map_ingredient(data["ingredient"])
        else:
            out["species_type"] = rename_id(data["species_type"])
            out["stage"] = data["stage"]
        return out, []
    except KeyError as exc:
        return None, [f"missing key: {exc}"]


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument(
        "--donor-gen-root",
        default="MarkDown_Maker/github_clone/thedarkcolour-gendustry/src/generated/resources",
    )
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    src_root = root / args.donor_gen_root / "data" / DONOR_NS / "recipes"
    dst_root = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe"
    if not src_root.is_dir():
        print(f"error: {src_root} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting gendustry processor recipes\n  from: {src_root}\n  to:   {dst_root}\n")

    written, skipped = 0, 0
    for kind in KINDS:
        src_dir = src_root / kind
        dst_dir = dst_root / kind
        if not src_dir.is_dir():
            print(f"SKIP  missing donor folder {src_dir}")
            skipped += 1
            continue
        for src in sorted(src_dir.glob("*.json")):
            data = json.loads(src.read_text(encoding="utf-8"))
            transformed, reasons = transform(data, kind)
            if transformed is None:
                print(f"SKIP  {kind}/{src.name}  ({'; '.join(reasons)})")
                skipped += 1
                continue
            dst = dst_dir / src.name
            print(f"WRITE {dst.relative_to(root)}")
            if args.apply:
                dst.parent.mkdir(parents=True, exist_ok=True)
                dst.write_text(json.dumps(transformed, indent=2) + "\n", encoding="utf-8")
            written += 1

    print(f"\nDone: {written} written, {skipped} skipped")


if __name__ == "__main__":
    main()
