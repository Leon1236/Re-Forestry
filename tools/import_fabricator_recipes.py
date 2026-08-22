#!/usr/bin/env python3
# Usage: python3 tools/import_fabricator_recipes.py
import json
from pathlib import Path

from ce_paths import generated as _ce_generated

ROOT = Path(__file__).resolve().parents[1]
CE_DIR = _ce_generated() / "data" / "forestry" / "recipes" / "fabricator"
OUT_DIR = ROOT / "src/main/resources/data/reforestry/recipe/fabricator"


def convert_id(value: str) -> str:
    if value.startswith("forestry:"):
        return "reforestry:" + value[len("forestry:"):]
    return value


def convert_tag(tag: str) -> str:
    if tag.startswith("forge:"):
        return "c:" + tag[len("forge:"):]
    if tag.startswith("c:"):
        return tag
    return tag


def convert_ingredient(value):
    if isinstance(value, str):
        return convert_id(value)
    if not isinstance(value, dict):
        return value
    if "item" in value:
        return convert_id(value["item"])
    if "tag" in value:
        tag = convert_tag(value["tag"])
        return tag if tag.startswith("#") else f"#{tag}"
    if "ingredient" in value:
        return convert_ingredient(value["ingredient"])
    return value


def convert_molten(molten: dict) -> dict:
    if "fluid" in molten:
        return {
            "fluid": convert_id(molten["fluid"]),
            "amount": molten["amount"],
        }
    return {
        "fluid": convert_id(molten["FluidName"]),
        "amount": molten["Amount"],
    }


def convert_result(result: dict) -> dict:
    if "id" in result:
        return {
            "count": result.get("count", 1),
            "id": convert_id(result["id"]),
        }
    return {
        "count": result.get("count", 1),
        "id": convert_id(result["item"]),
    }


def convert_recipe(recipe: dict) -> dict:
    out = dict(recipe)
    if "key" in out:
        out["key"] = {k: convert_ingredient(v) for k, v in out["key"].items()}
    if "result" in out:
        out["result"] = convert_result(out["result"])
    out.pop("show_notification", None)
    return out


def convert_file(data: dict) -> dict:
    return {
        "type": "reforestry:fabricator",
        "molten": convert_molten(data["molten"]),
        "plan": data.get("plan", []),
        "recipe": convert_recipe(data["recipe"]),
    }


def main() -> None:
    if not CE_DIR.is_dir():
        raise SystemExit(f"CE recipes not found: {CE_DIR}")

    written = 0
    skipped = 0
    for source in sorted(CE_DIR.rglob("*.json")):
        relative = source.relative_to(CE_DIR)
        if relative.parts[0] == "smelting":
            continue

        target = OUT_DIR / relative
        with source.open(encoding="utf-8") as handle:
            data = json.load(handle)
        if data.get("type") != "forestry:fabricator":
            continue

        converted = convert_file(data)
        target.parent.mkdir(parents=True, exist_ok=True)
        with target.open("w", encoding="utf-8") as handle:
            json.dump(converted, handle, indent=2)
            handle.write("\n")
        written += 1

    print(f"Wrote {written} fabricator recipes to {OUT_DIR}")
    print(f"Skipped {skipped} files")


if __name__ == "__main__":
    main()
