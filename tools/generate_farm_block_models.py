#!/usr/bin/env python3
"""Emit vanilla JSON models/blockstates for the 55 multifarm structure blocks.

CE uses neoforge:composite (material cube + farm overlay). Fabric 26.2 cannot
load that, so this writes two overlapping 16³ elements instead. Models parent
minecraft:block/block so item display transforms match a normal cube.

Usage: python3 tools/generate_farm_block_models.py --root . --apply
"""

from __future__ import annotations

import argparse
import json
import sys
from pathlib import Path

from reference_repos import ROOT, latest_clones

NS = "reforestry"
CE_REPO = "thedarkcolour-ForestryCE"
CE_FALLBACK = ROOT / "MarkDown_Maker" / "Finished_github_clone" / "2026-08-17" / "thedarkcolour-ForestryCE"

MATERIALS = (
    "STONE_BRICK",
    "MOSSY_STONE_BRICK",
    "CRACKED_STONE_BRICK",
    "BRICK",
    "CUT_SANDSTONE",
    "SANDSTONE_CHISELED",
    "BRICK_NETHER",
    "BRICK_CHISELED",
    "QUARTZ",
    "QUARTZ_CHISELED",
    "QUARTZ_LINES",
)

PARTS = ("PLAIN", "GEARBOX", "HATCH", "VALVE", "CONTROL")

MAT_ID = {
    "SANDSTONE_CHISELED": "chiseled_sandstone",
    "BRICK_NETHER": "nether_brick",
    "BRICK_CHISELED": "chiseled_stone_brick",
    "QUARTZ_CHISELED": "chiseled_quartz",
    "QUARTZ_LINES": "quartz_pillar",
}

MATERIAL_TEXTURES = {
    "STONE_BRICK": ("all", "minecraft:block/stone_bricks"),
    "MOSSY_STONE_BRICK": ("all", "minecraft:block/mossy_stone_bricks"),
    "CRACKED_STONE_BRICK": ("all", "minecraft:block/cracked_stone_bricks"),
    "BRICK": ("all", "minecraft:block/bricks"),
    "CUT_SANDSTONE": ("column", "minecraft:block/sandstone_top", "minecraft:block/cut_sandstone"),
    "SANDSTONE_CHISELED": ("column", "minecraft:block/sandstone_top", "minecraft:block/chiseled_sandstone"),
    "BRICK_NETHER": ("all", "minecraft:block/nether_bricks"),
    "BRICK_CHISELED": ("all", "minecraft:block/chiseled_stone_bricks"),
    "QUARTZ": ("column", "minecraft:block/quartz_block_top", "minecraft:block/quartz_block_side"),
    "QUARTZ_CHISELED": ("column", "minecraft:block/chiseled_quartz_block_top", "minecraft:block/chiseled_quartz_block"),
    "QUARTZ_LINES": ("column", "minecraft:block/quartz_pillar_top", "minecraft:block/quartz_pillar_side"),
}

OVERLAY = {
    "PLAIN": ("reforestry:block/farm/top", "reforestry:block/farm/plain"),
    "PLAIN_BAND": ("reforestry:block/farm/top", "reforestry:block/farm/band"),
    "GEARBOX": ("reforestry:block/farm/gearbox", "reforestry:block/farm/gearbox"),
    "HATCH": ("reforestry:block/farm/hatch", "reforestry:block/farm/hatch"),
    "VALVE": ("reforestry:block/farm/valve", "reforestry:block/farm/valve"),
    "CONTROL": ("reforestry:block/farm/control", "reforestry:block/farm/control"),
}

CUBE_FACES = ("down", "up", "north", "south", "west", "east")


def ce_root() -> Path:
    found = latest_clones().get(CE_REPO)
    if found is None or not found.is_dir():
        found = CE_FALLBACK
    return found


def material_name(material: str) -> str:
    return MAT_ID.get(material, material.lower())


def block_id(part: str, material: str) -> str:
    mat = material_name(material)
    part_name = "block" if part == "PLAIN" and material == "STONE_BRICK" else part.lower()
    return f"{mat}_farm_{part_name}"


def all_block_ids() -> list[str]:
    return [block_id(part, material) for material in MATERIALS for part in PARTS]


def cube_faces(texture_key: str) -> dict:
    return {face: {"texture": f"#{texture_key}", "cullface": face} for face in CUBE_FACES}


def column_faces(end_key: str, side_key: str) -> dict:
    return {
        "down": {"texture": f"#{end_key}", "cullface": "down"},
        "up": {"texture": f"#{end_key}", "cullface": "up"},
        "north": {"texture": f"#{side_key}", "cullface": "north"},
        "south": {"texture": f"#{side_key}", "cullface": "south"},
        "west": {"texture": f"#{side_key}", "cullface": "west"},
        "east": {"texture": f"#{side_key}", "cullface": "east"},
    }


def farm_model(material: str, overlay_key: str) -> dict:
    spec = MATERIAL_TEXTURES[material]
    overlay_end, overlay_side = OVERLAY[overlay_key]
    textures = {
        "particle": spec[1],
        "overlay_end": overlay_end,
        "overlay_side": overlay_side,
    }
    if spec[0] == "all":
        textures["base"] = spec[1]
        base_faces = cube_faces("base")
    else:
        textures["base_end"] = spec[1]
        textures["base_side"] = spec[2]
        base_faces = column_faces("base_end", "base_side")
    return {
        "parent": "minecraft:block/block",
        "render_type": "minecraft:cutout",
        "textures": textures,
        "elements": [
            {"from": [0, 0, 0], "to": [16, 16, 16], "faces": base_faces},
            {"from": [0, 0, 0], "to": [16, 16, 16], "faces": column_faces("overlay_end", "overlay_side")},
        ],
    }


def dump(path: Path, data: dict, apply: bool) -> None:
    text = json.dumps(data, indent=2) + "\n"
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text, encoding="utf-8")


def merge_lang(root: Path, ids: list[str], apply: bool) -> int:
    ce_lang = ce_root() / "src" / "generated" / "resources" / "assets" / "forestry" / "lang" / "en_us.json"
    dest = root / "src" / "main" / "resources" / "assets" / NS / "lang" / "en_us.json"
    if not ce_lang.is_file():
        print(f"warning: missing CE lang {ce_lang}", file=sys.stderr)
        return 0
    source = json.loads(ce_lang.read_text(encoding="utf-8"))
    dest_data = json.loads(dest.read_text(encoding="utf-8"))
    added = 0
    for block_id_name in ids:
        old_key = f"block.forestry.{block_id_name}"
        new_key = f"block.{NS}.{block_id_name}"
        if old_key in source and new_key not in dest_data:
            dest_data[new_key] = source[old_key]
            added += 1
        elif old_key in source:
            dest_data[new_key] = source[old_key]
    if apply:
        dest.write_text(json.dumps(dest_data, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    return added


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root)
    assets = root / "src" / "main" / "resources" / "assets" / NS
    blockstates = assets / "blockstates"
    models_block = assets / "models" / "block"
    models_item = assets / "models" / "item"

    overlay_dir = assets / "textures" / "block" / "farm"
    for name in ("plain", "gearbox", "hatch", "valve", "control", "band", "top", "reverse"):
        png = overlay_dir / f"{name}.png"
        if not png.is_file():
            print(f"error: missing overlay {png}", file=sys.stderr)
            sys.exit(1)

    ids = all_block_ids()
    if len(ids) != 55:
        print(f"error: expected 55 ids, got {len(ids)}", file=sys.stderr)
        sys.exit(1)
    if "stone_brick_farm_block" not in ids or "stone_brick_farm_plain" in ids:
        print("error: stone_brick_farm_block id mapping is wrong", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] {len(ids)} farm block models")

    written = 0
    for material in MATERIALS:
        for part in PARTS:
            name = block_id(part, material)
            if part == "PLAIN":
                dump(models_block / f"{name}.json", farm_model(material, "PLAIN"), args.apply)
                dump(models_block / f"{name}_band.json", farm_model(material, "PLAIN_BAND"), args.apply)
                dump(
                    blockstates / f"{name}.json",
                    {
                        "variants": {
                            "band=false": {"model": f"{NS}:block/{name}"},
                            "band=true": {"model": f"{NS}:block/{name}_band"},
                        }
                    },
                    args.apply,
                )
                written += 3
            else:
                dump(models_block / f"{name}.json", farm_model(material, part), args.apply)
                dump(blockstates / f"{name}.json", {"variants": {"": {"model": f"{NS}:block/{name}"}}}, args.apply)
                written += 2
            dump(models_item / f"{name}.json", {"parent": f"{NS}:block/{name}"}, args.apply)
            written += 1

    lang_added = merge_lang(root, ids, args.apply)
    print(f"{written} model/blockstate files, {lang_added} lang keys")
    if not args.apply:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
