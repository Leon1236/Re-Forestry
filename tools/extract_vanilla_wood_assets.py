#!/usr/bin/env python3
"""Generates fireproof-vanilla-wood block/item assets by copying vanilla's own.

Forestry's "fireproof" vanilla-wood blocks (oak_fireproof_log, etc.) reuse
vanilla's own block classes (RotatedPillarBlock, SlabBlock, FenceBlock, ...),
so they have the exact same blockstate properties as their vanilla
counterparts and can reuse vanilla's own blockstate/item-model-definition
JSON verbatim (which already reference "minecraft:block/oak_log" etc.) - only
the outer file name needs to change, to our own registry name.

This pulls those files straight out of the game's client jar rather than
retyping them, since re-deriving multipart blockstate JSON (fences, stairs)
by hand is error-prone busywork for content that's byte-identical to vanilla.

Usage:
  python3 tools/extract_vanilla_wood_assets.py \\
      --client-jar ~/.gradle/caches/fabric-loom/26.2/minecraft-client.jar \\
      --root . --namespace reforestry \\
      --wood-types oak,spruce,birch,jungle,acacia,dark_oak,cherry,mangrove,pale_oak \\
      --apply

Without --apply this only lists what would be written (dry run).
"""
import argparse
import zipfile
from pathlib import Path

KIND_TO_VANILLA_ID = {
    "log": "{type}_log",
    "stripped_log": "stripped_{type}_log",
    "wood": "{type}_wood",
    "stripped_wood": "stripped_{type}_wood",
    "planks": "{type}_planks",
    "slab": "{type}_slab",
    "fence": "{type}_fence",
    "fence_gate": "{type}_fence_gate",
    "stairs": "{type}_stairs",
}


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--client-jar", required=True, help="path to minecraft-client.jar")
    parser.add_argument("--root", default=".", help="project root (default: current directory)")
    parser.add_argument("--namespace", required=True, help="our resource namespace, e.g. reforestry")
    parser.add_argument("--wood-types", required=True, help="comma-separated vanilla wood type names, e.g. oak,spruce")
    parser.add_argument("--apply", action="store_true", help="write files (default: dry run)")
    args = parser.parse_args()

    wood_types = [w.strip() for w in args.wood_types.split(",") if w.strip()]
    resources_root = Path(args.root) / "src" / "main" / "resources"
    blockstates_out = resources_root / "assets" / args.namespace / "blockstates"
    items_out = resources_root / "assets" / args.namespace / "items"

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] extracting {len(wood_types)} wood type(s) x {len(KIND_TO_VANILLA_ID)} kind(s) from {args.client_jar}\n")

    if args.apply:
        blockstates_out.mkdir(parents=True, exist_ok=True)
        items_out.mkdir(parents=True, exist_ok=True)

    written = 0
    missing = []
    with zipfile.ZipFile(args.client_jar) as jar:
        names = set(jar.namelist())
        for wood_type in wood_types:
            for kind, pattern in KIND_TO_VANILLA_ID.items():
                vanilla_id = pattern.format(type=wood_type)
                our_id = f"{wood_type}_fireproof_{kind}"

                blockstate_src = f"assets/minecraft/blockstates/{vanilla_id}.json"
                item_src = f"assets/minecraft/items/{vanilla_id}.json"

                if blockstate_src not in names or item_src not in names:
                    missing.append((our_id, blockstate_src, item_src))
                    continue

                blockstate_dst = blockstates_out / f"{our_id}.json"
                item_dst = items_out / f"{our_id}.json"
                print(f"{our_id}  <-  {vanilla_id}")
                if args.apply:
                    blockstate_dst.write_bytes(jar.read(blockstate_src))
                    item_dst.write_bytes(jar.read(item_src))
                written += 1

    print(f"\n{written} block(s) {'written' if args.apply else 'would be written'} (2 files each)")
    if missing:
        print(f"\n{len(missing)} MISSING (vanilla source not found, skipped):")
        for our_id, bs, it in missing:
            print(f"  {our_id}: {bs} / {it}")
    if not args.apply:
        print("\ndry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
