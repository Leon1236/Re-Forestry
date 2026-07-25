#!/usr/bin/env python3
# Usage: python3 tools/sync_tree_species_worldgen.py [--apply]
# Copies .setRarity/.setTemperature/.setHumidity from ForestryCE DefaultTreeSpecies
# and adds .setTreeFeature(FeatureTreeVanilla::new) to each local species registration.

from __future__ import annotations

import argparse
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CE = ROOT / "MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-ForestryCE/src/main/java/forestry/plugin/DefaultTreeSpecies.java"
CE_IDS = ROOT / "MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-ForestryCE/src/main/java/forestry/api/arboriculture/ForestryTreeSpecies.java"
OURS = ROOT / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java"

CE_SPECIES_RE = re.compile(r"arboriculture\.registerSpecies\(\s*ForestryTreeSpecies\.([A-Z0-9_]+)")
OURS_SPECIES_RE = re.compile(r'registration\.registerSpecies\(ReForestry\.id\("([^"]+)"\)')
TEMP_RE = re.compile(r"\.setTemperature\(TemperatureType\.([A-Z]+)\)")
HUMID_RE = re.compile(r"\.setHumidity\(HumidityType\.([A-Z]+)\)")
RARITY_RE = re.compile(r"\.setRarity\(([0-9.]+)f\)")
ID_CONST_RE = re.compile(
    r'public static final ResourceLocation (\w+)\s*=\s*forestry\("([^"]+)"\)'
)

FEATURE_CALL = ".setTreeFeature(FeatureTreeVanilla::new)"
FEATURE_STRIP_RE = re.compile(r"\n[ \t]+\.setTreeFeature\([^)]+\)")
TEMP_STRIP_RE = re.compile(r"\n[ \t]+\.setTemperature\(TemperatureType\.[A-Z]+\)")
HUMID_STRIP_RE = re.compile(r"\n[ \t]+\.setHumidity\(HumidityType\.[A-Z]+\)")
RARITY_STRIP_RE = re.compile(r"\n[ \t]+\.setRarity\([0-9.]+f\)")

IMPORTS = [
    "import com.leon1236.reforestry.api.core.HumidityType;\n",
    "import com.leon1236.reforestry.api.core.TemperatureType;\n",
    "import com.leon1236.reforestry.arboriculture.worldgen.FeatureTreeVanilla;\n",
]


def load_ce_id_map(path: Path) -> dict[str, str]:
    text = path.read_text()
    return {match.group(1): match.group(2) for match in ID_CONST_RE.finditer(text)}


def extract_ce_meta(path: Path, id_map: dict[str, str]) -> dict[str, dict[str, str | None]]:
    text = path.read_text()
    meta: dict[str, dict[str, str | None]] = {}
    for part in re.split(r"(?=arboriculture\.registerSpecies\()", text):
        match = CE_SPECIES_RE.search(part)
        if not match:
            continue
        const = match.group(1)
        species_id = id_map.get(const)
        if species_id is None:
            raise SystemExit(f"No id mapping for ForestryTreeSpecies.{const}")
        rarity = RARITY_RE.search(part)
        temp = TEMP_RE.search(part)
        humid = HUMID_RE.search(part)
        meta[species_id] = {
            "rarity": rarity.group(1) if rarity else None,
            "temperature": temp.group(1) if temp else None,
            "humidity": humid.group(1) if humid else None,
        }
    return meta


def ensure_imports(text: str) -> str:
    for import_line in IMPORTS:
        if import_line not in text:
            text = text.replace(
                "import com.leon1236.reforestry.ReForestry;\n",
                "import com.leon1236.reforestry.ReForestry;\n" + import_line,
                1,
            )
    return text


def inject_meta(block: str, meta: dict[str, dict[str, str | None]]) -> str:
    match = OURS_SPECIES_RE.search(block)
    if not match:
        return block
    species_id = match.group(1)
    species_meta = meta.get(species_id)
    if species_meta is None:
        raise SystemExit(f"Local species {species_id} not found in CE metadata")

    block = FEATURE_STRIP_RE.sub("", block)
    block = TEMP_STRIP_RE.sub("", block)
    block = HUMID_STRIP_RE.sub("", block)
    block = RARITY_STRIP_RE.sub("", block)

    insert = f"\n                {FEATURE_CALL}"
    if species_meta["temperature"] is not None:
        insert += f"\n                .setTemperature(TemperatureType.{species_meta['temperature']})"
    if species_meta["humidity"] is not None:
        insert += f"\n                .setHumidity(HumidityType.{species_meta['humidity']})"
    if species_meta["rarity"] is not None:
        insert += f"\n                .setRarity({species_meta['rarity']}f)"

    return re.sub(
        r'(registration\.registerSpecies\(ReForestry\.id\("[^"]+"\)[^\n]*\))',
        r"\1" + insert,
        block,
        count=1,
    )


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    id_map = load_ce_id_map(CE_IDS)
    meta = extract_ce_meta(CE, id_map)
    text = OURS.read_text()
    text = ensure_imports(text)

    parts = re.split(r"(?=registration\.registerSpecies\()", text)
    out = [parts[0]]
    applied = 0
    rarity_count = 0
    climate_count = 0
    for part in parts[1:]:
        before = part
        part = inject_meta(part, meta)
        if part != before:
            applied += 1
        match = OURS_SPECIES_RE.search(part)
        if match:
            species_meta = meta[match.group(1)]
            if species_meta["rarity"] is not None:
                rarity_count += 1
            if species_meta["temperature"] is not None or species_meta["humidity"] is not None:
                climate_count += 1
        out.append(part)

    result = "".join(out)
    print(f"species={len(meta)} patched_blocks={applied} with_rarity={rarity_count} with_climate={climate_count}")
    if args.apply:
        OURS.write_text(result)
        print(f"wrote {OURS}")
    else:
        print("dry run only; pass --apply to write")


if __name__ == "__main__":
    main()
