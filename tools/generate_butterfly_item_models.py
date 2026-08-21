# Usage: python3 tools/generate_butterfly_item_models.py
from pathlib import Path
import json
import re

SPECIES_SRC = Path("src/main/java/com/leon1236/reforestry/api/lepidopterology/ForestryButterflySpecies.java")
ITEMS_DIR = Path("src/main/resources/assets/reforestry/items")
MODELS_DIR = Path("src/main/resources/assets/reforestry/models/item/butterfly")


def species_ids():
    text = SPECIES_SRC.read_text()
    match = re.search(r"public static final List<Identifier> ALL = List.of\((.*?)\);", text, re.S)
    if not match:
        raise SystemExit("Could not find ForestryButterflySpecies.ALL")
    names = re.findall(r"\b([A-Z0-9_]+)\b", match.group(1))
    constants = dict(re.findall(r"public static final Identifier (\w+) = ReForestry\.id\(\"([^\"]+)\"\)", text))
    missing = [name for name in names if name not in constants]
    if missing:
        raise SystemExit(f"Unknown ALL entries: {missing}")
    return [constants[name] for name in names]


def main():
    ids = species_ids()
    MODELS_DIR.mkdir(parents=True, exist_ok=True)
    cases = []
    for path in ids:
        model = {
            "parent": "reforestry:item/butterfly",
            "textures": {
                "butterfly": f"reforestry:item/butterfly/{path}"
            }
        }
        (MODELS_DIR / f"{path}.json").write_text(json.dumps(model, indent=2) + "\n")
        cases.append({
            "when": f"reforestry:{path}",
            "model": {
                "type": "minecraft:model",
                "model": f"reforestry:item/butterfly/{path}"
            }
        })
    select = {
        "model": {
            "type": "minecraft:select",
            "property": "reforestry:butterfly_species",
            "cases": cases,
            "fallback": {
                "type": "minecraft:model",
                "model": "reforestry:item/butterfly"
            }
        }
    }
    (ITEMS_DIR / "butterfly.json").write_text(json.dumps(select, indent=2) + "\n")
    print(f"Wrote {len(ids)} butterfly item models")


if __name__ == "__main__":
    main()
