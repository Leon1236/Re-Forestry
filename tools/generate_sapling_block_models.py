#!/usr/bin/env python3
"""Generates the per-species sapling block models used by SaplingBlockStateResolver.

The 43 non-vanilla-wood species already have <species>_sapling.png item icon
art (from tools/generate_sapling_item_models.py, 11.4), but that lives under
textures/item/ - vanilla's block atlas (textures/block/) and item atlas
(textures/item/) are disjoint, so a block-context model cannot reference an
items-atlas-only sprite. This script duplicates each texture into
textures/block/tree_saplings/<species>.png and writes a matching
models/block/tree_saplings/<species>.json (minecraft:block/cross parent,
same parent the old flat sapling_ge.json placeholder used). The 7
vanilla-wood species point straight at vanilla's own
minecraft:block/<name>_sapling models instead, mirroring the item-side
script's existing precedent exactly.

Usage:
  python3 tools/generate_sapling_block_models.py --root . --apply
Without --apply this only prints a summary + the Java literal (dry run).
"""
import argparse
import json
import re
import shutil
from pathlib import Path

SPECIES_ID_RE = re.compile(r'ReForestry\.id\("tree_(\w+)"\)')

VANILLA_SAPLING_SPECIES = {"acacia", "birch", "cherry", "dark_oak", "jungle", "oak", "spruce"}


def load_species_ids(root: Path):
    content = (root / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java").read_text(encoding="utf-8")
    return sorted(set(SPECIES_ID_RE.findall(content)))


def model_reference(species: str) -> str:
    if species in VANILLA_SAPLING_SPECIES:
        return f"minecraft:block/{species}_sapling"
    return f"reforestry:block/tree_saplings/{species}"


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".", help="project root (default: current directory)")
    parser.add_argument("--apply", action="store_true", help="write files (default: dry run)")
    args = parser.parse_args()

    root = Path(args.root)
    assets = root / "src/main/resources/assets/reforestry"
    item_textures_dir = assets / "textures/item"
    block_textures_dir = assets / "textures/block/tree_saplings"
    models_dir = assets / "models/block/tree_saplings"

    species_ids = load_species_ids(root)
    to_copy = []
    skipped = []
    for species in species_ids:
        if species in VANILLA_SAPLING_SPECIES:
            continue
        texture_file = item_textures_dir / f"{species}_sapling.png"
        if not texture_file.exists():
            skipped.append(species)
            continue
        to_copy.append(species)

    print(f"{len(species_ids)} species total")
    print(f"{len(to_copy)} species need a copied block texture + generated block model")
    print(f"{len(VANILLA_SAPLING_SPECIES)} species mapped to vanilla sapling block models")
    if skipped:
        print("species with no item sapling texture and not in the vanilla set (skipped):", skipped)

    print()
    print("Java map literal for SaplingBlockStateResolver:")
    print("Map.ofEntries(")
    entries = [f'        Map.entry("{species}", "{model_reference(species)}")' for species in species_ids]
    print(",\n".join(entries))
    print(")")

    if not args.apply:
        print()
        print("dry run only - rerun with --apply to write files")
        return

    block_textures_dir.mkdir(parents=True, exist_ok=True)
    models_dir.mkdir(parents=True, exist_ok=True)
    for species in to_copy:
        shutil.copyfile(item_textures_dir / f"{species}_sapling.png", block_textures_dir / f"{species}.png")
        model_json = {
            "parent": "minecraft:block/cross",
            "textures": {"cross": f"reforestry:block/tree_saplings/{species}"},
        }
        (models_dir / f"{species}.json").write_text(json.dumps(model_json, indent=2) + "\n", encoding="utf-8")

    print(f"copied {len(to_copy)} textures and wrote {len(to_copy)} block models")


if __name__ == "__main__":
    main()
