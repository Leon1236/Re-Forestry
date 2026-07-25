#!/usr/bin/env python3
# Usage: python3 tools/sync_tree_species_leaves.py [--apply]
# Injects .setDecorativeLeaves / .addVanillaStates / .addVanillaSapling from CE
# DefaultTreeSpecies into the local DefaultTreeSpecies after leaf blocks exist.

from __future__ import annotations

import argparse
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CE = ROOT / "MarkDown_Maker/Finished_github_clone/2026-07-22/thedarkcolour-ForestryCE/src/main/java/forestry/plugin/DefaultTreeSpecies.java"
CE_IDS = ROOT / "MarkDown_Maker/Finished_github_clone/2026-07-22/thedarkcolour-ForestryCE/src/main/java/forestry/api/arboriculture/ForestryTreeSpecies.java"
OURS = ROOT / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java"

CE_SPECIES_RE = re.compile(r"arboriculture\.registerSpecies\(\s*ForestryTreeSpecies\.([A-Z0-9_]+)")
OURS_SPECIES_RE = re.compile(r'registration\.registerSpecies\(ReForestry\.id\("([^"]+)"\)')
ID_CONST_RE = re.compile(
    r'public static final ResourceLocation (\w+)\s*=\s*forestry\("([^"]+)"\)'
)
LEAF_CONST_RE = re.compile(r"ForestryLeafType\.([A-Z0-9_]+)")
DECORATIVE_RE = re.compile(
    r"\.setDecorativeLeaves\((ArboricultureBlocks\.LEAVES_DECORATIVE\.stack\(ForestryLeafType\.[A-Z0-9_]+\)|new ItemStack\(Items\.[A-Z0-9_]+\))\)"
)
VANILLA_BLOCK_RE = re.compile(r"\.addVanillaStates\(Blocks\.([A-Z0-9_]+)\.getStateDefinition\(\)\.getPossibleStates\(\)\)")
VANILLA_SAPLING_RE = re.compile(r"\.addVanillaSapling\(Items\.([A-Z0-9_]+)\)")

DECORATIVE_STRIP_RE = re.compile(r"\n[ \t]+\.setDecorativeLeaves\([^\n]+\)")
VANILLA_STATE_STRIP_RE = re.compile(r"\n[ \t]+\.addVanillaStates\([^\n]+\)")
VANILLA_SAPLING_STRIP_RE = re.compile(r"\n[ \t]+\.addVanillaSapling\([^\n]+\)")

IMPORTS = [
    "import net.minecraft.world.item.ItemStack;\n",
    "import net.minecraft.world.item.Items;\n",
    "import net.minecraft.world.level.block.Blocks;\n",
    "import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;\n",
    "import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;\n",
]


def load_ce_id_map(path: Path) -> dict[str, str]:
    return {m.group(1): m.group(2) for m in ID_CONST_RE.finditer(path.read_text())}


def extract_ce_leaf_meta(path: Path, id_map: dict[str, str]) -> dict[str, dict]:
    text = path.read_text()
    meta: dict[str, dict] = {}
    for part in re.split(r"(?=arboriculture\.registerSpecies\()", text):
        match = CE_SPECIES_RE.search(part)
        if not match:
            continue
        species_id = id_map.get(match.group(1))
        if species_id is None:
            raise SystemExit(f"No id mapping for ForestryTreeSpecies.{match.group(1)}")
        decorative = DECORATIVE_RE.search(part)
        if decorative is None:
            raise SystemExit(f"No setDecorativeLeaves for {species_id}")
        leaf_consts = LEAF_CONST_RE.findall(part)
        # First ForestryLeafType in decorative or vanilla states for this species
        leaf_type = None
        dec_leaf = LEAF_CONST_RE.search(decorative.group(1))
        if dec_leaf:
            leaf_type = dec_leaf.group(1)
        elif leaf_consts:
            leaf_type = leaf_consts[0]
        vanilla_blocks = VANILLA_BLOCK_RE.findall(part)
        vanilla_saplings = VANILLA_SAPLING_RE.findall(part)
        meta[species_id] = {
            "decorative": decorative.group(1),
            "leaf_type": leaf_type,
            "vanilla_blocks": vanilla_blocks,
            "vanilla_saplings": vanilla_saplings,
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


def inject(block: str, meta: dict[str, dict]) -> str:
    match = OURS_SPECIES_RE.search(block)
    if not match:
        return block
    species_id = match.group(1)
    species_meta = meta.get(species_id)
    if species_meta is None:
        raise SystemExit(f"Local species {species_id} not found in CE leaf metadata")

    block = DECORATIVE_STRIP_RE.sub("", block)
    block = VANILLA_STATE_STRIP_RE.sub("", block)
    block = VANILLA_SAPLING_STRIP_RE.sub("", block)

    insert = f"\n                .setDecorativeLeaves(() -> {species_meta['decorative']})"
    leaf_type = species_meta["leaf_type"]
    if leaf_type is not None:
        insert += (
            f"\n                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.{leaf_type})"
            f".block().getStateDefinition().getPossibleStates())"
            f"\n                .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.{leaf_type})"
            f".block().getStateDefinition().getPossibleStates())"
        )
    for block_name in species_meta["vanilla_blocks"]:
        insert += (
            f"\n                .addVanillaStates(Blocks.{block_name}.getStateDefinition().getPossibleStates())"
        )
    for sapling in species_meta["vanilla_saplings"]:
        insert += f"\n                .addVanillaSapling(Items.{sapling})"

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
    meta = extract_ce_leaf_meta(CE, id_map)
    text = ensure_imports(OURS.read_text())
    parts = re.split(r"(?=registration\.registerSpecies\()", text)
    out = [parts[0]]
    applied = 0
    for part in parts[1:]:
        before = part
        part = inject(part, meta)
        if part != before:
            applied += 1
        out.append(part)
    result = "".join(out)
    print(f"species={len(meta)} patched_blocks={applied}")
    if args.apply:
        OURS.write_text(result)
        print(f"wrote {OURS}")
    else:
        print("dry run only; pass --apply to write")


if __name__ == "__main__":
    main()
