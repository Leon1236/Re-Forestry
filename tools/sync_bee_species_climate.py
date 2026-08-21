#!/usr/bin/env python3
# Usage: python3 tools/sync_bee_species_climate.py
# Copies .setTemperature/.setHumidity from ForestryCE DefaultBeeSpecies into ours by species id.

from __future__ import annotations

import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CE = ROOT / "MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-ForestryCE/src/main/java/forestry/plugin/DefaultBeeSpecies.java"
OURS = ROOT / "src/main/java/com/leon1236/reforestry/apiculture/genetics/DefaultBeeSpecies.java"

CE_SPECIES_RE = re.compile(r"apiculture\.registerSpecies\(\s*ForestryBeeSpecies\.([A-Z0-9_]+)")
OURS_SPECIES_RE = re.compile(r'registration\.registerSpecies\(ReForestry\.id\("([^"]+)"\)')
TEMP_RE = re.compile(r"\.setTemperature\(TemperatureType\.([A-Z]+)\)")
HUMID_RE = re.compile(r"\.setHumidity\(HumidityType\.([A-Z]+)\)")


def ce_name_to_id(name: str) -> str:
    return "bee_" + name.lower()


def extract_ce_climate(path: Path) -> dict[str, tuple[str | None, str | None]]:
    text = path.read_text()
    climate: dict[str, tuple[str | None, str | None]] = {}
    for part in re.split(r"(?=apiculture\.registerSpecies\()", text):
        match = CE_SPECIES_RE.search(part)
        if not match:
            continue
        species_id = ce_name_to_id(match.group(1))
        temp = TEMP_RE.search(part)
        humid = HUMID_RE.search(part)
        climate[species_id] = (
            temp.group(1) if temp else None,
            humid.group(1) if humid else None,
        )
    return climate


def inject_climate(block: str, climate: dict[str, tuple[str | None, str | None]]) -> str:
    match = OURS_SPECIES_RE.search(block)
    if not match:
        return block
    species_id = match.group(1)
    temp, humid = climate.get(species_id, (None, None))
    if temp is None and humid is None:
        return block
    block = re.sub(r"\n\t+\.setTemperature\(TemperatureType\.[A-Z]+\)", "", block)
    block = re.sub(r"\n\t+\.setHumidity\(HumidityType\.[A-Z]+\)", "", block)
    insert = ""
    if temp is not None:
        insert += f"\n                .setTemperature(TemperatureType.{temp})"
    if humid is not None:
        insert += f"\n                .setHumidity(HumidityType.{humid})"
    return re.sub(
        r'(registration\.registerSpecies\(ReForestry\.id\("[^"]+"\)[^\n]*\))',
        r"\1" + insert,
        block,
        count=1,
    )


def main() -> None:
    climate = extract_ce_climate(CE)
    text = OURS.read_text()
    if "import com.leon1236.reforestry.api.core.TemperatureType;" not in text:
        text = text.replace(
            "import com.leon1236.reforestry.apiculture.features.ApicultureItems;",
            "import com.leon1236.reforestry.api.core.HumidityType;\n"
            "import com.leon1236.reforestry.api.core.TemperatureType;\n"
            "import com.leon1236.reforestry.apiculture.features.ApicultureItems;",
            1,
        )

    parts = re.split(r"(?=registration\.registerSpecies\()", text)
    out = [parts[0]]
    applied = 0
    for part in parts[1:]:
        before = part
        updated = inject_climate(part, climate)
        if updated != before:
            applied += 1
        out.append(updated)
    OURS.write_text("".join(out))
    print(f"Applied climate to {applied} species ({sum(1 for t, h in climate.values() if t or h)} CE overrides available)")


if __name__ == "__main__":
    main()
