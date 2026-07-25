#!/usr/bin/env python3
"""Generates the per-species-group leaf block models used by LeafBlockStateResolver.

CE's own real generated assets map all 50 tree species onto exactly 15
shared leaf texture groups (verified against thedarkcolour-ForestryCE's
generated tree_<species>_default_leaves.json files, not guessed) - the
same 15 groups already staged as textures under
assets/reforestry/textures/block/leaves/<group>.png (+ _pollinated
variant). This script writes the 15 base + 15 pollinated tiny model
files (parented off minecraft:block/leaves, same parent the old flat
placeholder used) and prints the species->group Java map literal so the
50-row table is authored once here rather than retyped by hand in
LeafBlockStateResolver.java.

Usage:
  python3 tools/generate_leaf_block_models.py --root . --apply
Without --apply this only prints a summary + the Java literal (dry run).
"""
import argparse
import json
import re
from pathlib import Path

SPECIES_ID_RE = re.compile(r'ReForestry\.id\("tree_(\w+)"\)')

# group -> species using that group's texture. Every real species is
# accounted for; there is no species with no leaf art.
GROUPS = {
    "oak": ["oak", "beech", "dark_oak", "elm", "mahoe", "pear", "plum", "wenge"],
    "spruce": ["spruce", "fir", "giant_sequoia", "kauri", "larch", "macrocarpa", "pewen", "pine", "sequoia"],
    "birch": ["birch", "chestnut", "hill_cherry", "lime", "poplar"],
    "jungle": ["jungle", "ebony", "kapok", "mahogany", "teak", "zebrawood"],
    "acacia": ["acacia", "balsa", "baobab", "desert_acacia", "padauk", "walnut"],
    "azalea": ["feijoa", "lemon", "orange"],
    "mangrove": ["cocobolo", "sipiri"],
    "palm": ["coconut", "date", "papaya"],
    "willow": ["willow", "olive"],
    "cherry": ["cherry"],
    "dogwood": ["dogwood"],
    "ginkgo": ["ginkgo"],
    "ipe": ["ipe"],
    "jacaranda": ["jacaranda"],
    "maple": ["maple"],
}


def load_species_ids(root: Path):
    content = (root / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java").read_text(encoding="utf-8")
    return sorted(set(SPECIES_ID_RE.findall(content)))


def species_to_group():
    mapping = {}
    for group, species_list in GROUPS.items():
        for species in species_list:
            mapping[species] = group
    return mapping


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".", help="project root (default: current directory)")
    parser.add_argument("--apply", action="store_true", help="write files (default: dry run)")
    args = parser.parse_args()

    root = Path(args.root)
    assets = root / "src/main/resources/assets/reforestry"
    textures_dir = assets / "textures/block/leaves"
    models_dir = assets / "models/block/leaves"

    species_ids = load_species_ids(root)
    mapping = species_to_group()

    missing_species = sorted(set(species_ids) - set(mapping.keys()))
    unknown_species = sorted(set(mapping.keys()) - set(species_ids))
    missing_textures = []
    for group in GROUPS:
        for suffix in ("", "_pollinated"):
            texture_file = textures_dir / f"{group}{suffix}.png"
            if not texture_file.exists():
                missing_textures.append(texture_file)

    print(f"{len(species_ids)} species total, {len(mapping)} mapped across {len(GROUPS)} groups")
    if missing_species:
        print("WARNING: species with no group mapping:", missing_species)
    if unknown_species:
        print("WARNING: mapped species not found in DefaultTreeSpecies.java:", unknown_species)
    if missing_textures:
        print("WARNING: missing staged textures:", [str(p) for p in missing_textures])

    print()
    print("Java map literal for LeafBlockStateResolver:")
    print("Map.ofEntries(")
    entries = [f'        Map.entry("{species}", "{group}")' for species, group in sorted(mapping.items())]
    print(",\n".join(entries))
    print(")")

    if not args.apply:
        print()
        print("dry run only - rerun with --apply to write files")
        return

    models_dir.mkdir(parents=True, exist_ok=True)
    written = 0
    for group in GROUPS:
        for suffix in ("", "_pollinated"):
            model_path = models_dir / f"{group}{suffix}.json"
            model_json = {
                "parent": "minecraft:block/leaves",
                "textures": {"all": f"reforestry:block/leaves/{group}{suffix}"},
            }
            model_path.write_text(json.dumps(model_json, indent=2) + "\n", encoding="utf-8")
            written += 1

    print(f"written {written} leaf group models to {models_dir}")


if __name__ == "__main__":
    main()
