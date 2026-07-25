#!/usr/bin/env python3
"""Generates the per-species item-model dispatch for the genetic sapling item.

Reads all 50 species ids straight from our own already-written
arboriculture/genetics/DefaultTreeSpecies.java (no mods.db query needed -
this is our own data, not CE's). For the 44 species with a staged
<species>_sapling.png texture, writes a small generated-item model at
models/item/tree_saplings/<species>.json. The remaining species use
VanillaWoodType-backed vanilla tree species (acacia, birch, cherry,
dark_oak, jungle, oak, spruce) and have no Forestry-side texture at all -
those cases point straight at vanilla's own item/<name>_sapling model.

Writes assets/reforestry/items/sapling.json as a minecraft:select model
keyed on the reforestry:tree_species item-model property (a genome-reading
property registered client-side via SelectItemModelPropertiesMixin), one
case per species, falling back to the oak case for a genome-less stack.

Usage:
  python3 tools/generate_sapling_item_models.py --root . --apply
Without --apply this only prints a summary (dry run).
"""
import argparse
import json
import re
from pathlib import Path

SPECIES_ID_RE = re.compile(r'ReForestry\.id\("tree_(\w+)"\)')

VANILLA_SAPLING_SPECIES = {"acacia", "birch", "cherry", "dark_oak", "jungle", "oak", "spruce"}


def load_species_ids(root: Path):
    content = (root / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java").read_text(encoding="utf-8")
    return sorted(set(SPECIES_ID_RE.findall(content)))


def model_reference(species: str) -> str:
    if species in VANILLA_SAPLING_SPECIES:
        return f"minecraft:item/{species}_sapling"
    return f"reforestry:item/tree_saplings/{species}"


def build_case(species: str) -> dict:
    return {
        "when": f"reforestry:tree_{species}",
        "model": {"type": "minecraft:model", "model": model_reference(species)},
    }


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".", help="project root (default: current directory)")
    parser.add_argument("--apply", action="store_true", help="write files (default: dry run)")
    args = parser.parse_args()

    root = Path(args.root)
    assets = root / "src/main/resources/assets/reforestry"
    textures_dir = assets / "textures/item"

    species_ids = load_species_ids(root)
    written_models = []
    skipped = []
    for species in species_ids:
        if species in VANILLA_SAPLING_SPECIES:
            continue
        texture_file = textures_dir / f"{species}_sapling.png"
        if not texture_file.exists():
            skipped.append(species)
            continue
        model_path = assets / "models/item/tree_saplings" / f"{species}.json"
        model_json = {
            "parent": "item/generated",
            "textures": {"layer0": f"reforestry:item/{species}_sapling"},
        }
        written_models.append((model_path, model_json))

    select_definition = {
        "model": {
            "type": "minecraft:select",
            "property": "reforestry:tree_species",
            "cases": [build_case(species) for species in species_ids],
            "fallback": {"type": "minecraft:model", "model": model_reference("oak")},
        }
    }
    sapling_item_path = assets / "items/sapling.json"

    print(f"{len(species_ids)} species total")
    print(f"{len(written_models)} generated-item models to write (models/item/tree_saplings/*.json)")
    print(f"{len(VANILLA_SAPLING_SPECIES)} species mapped to vanilla sapling item models")
    if skipped:
        print("species with no sapling texture and not in the vanilla set (skipped):", skipped)
    print("1 select item model definition to write: items/sapling.json")

    if not args.apply:
        print("dry run only - rerun with --apply to write files")
        return

    for model_path, model_json in written_models:
        model_path.parent.mkdir(parents=True, exist_ok=True)
        model_path.write_text(json.dumps(model_json, indent=2) + "\n", encoding="utf-8")

    sapling_item_path.write_text(json.dumps(select_definition, indent=2) + "\n", encoding="utf-8")
    print(f"written {len(written_models)} sapling item models + {sapling_item_path}")


if __name__ == "__main__":
    main()
