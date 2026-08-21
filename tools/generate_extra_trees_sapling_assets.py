#!/usr/bin/env python3
"""Generate Extra Trees sapling item/block assets (Binnie tinted templates).

Binnie Extra Trees does not ship per-species sapling PNGs. Each species uses a
sapling_type template (Default/Fruit/Shrub/Poplar/…) with wood + leaf tint colors.
This script:

1. Copies Binnie trunk/leaves templates into assets/reforestry
2. Bakes a full-color cross PNG per registered ET species (item multiply)
3. Writes models/item + models/block under tree_saplings/
4. Merges cases into items/sapling.json (keeps existing CE cases)
5. Prints the Map.entry lines to append to SaplingBlockStateResolver

Usage:
  python3 tools/generate_extra_trees_sapling_assets.py --apply
"""
from __future__ import annotations

import argparse
import json
import re
import shutil
from pathlib import Path

from PIL import Image

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/reforestry"
BINNIE_SAP = (
	ROOT
	/ "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/resources"
	/ "assets/extratrees/textures/blocks/saplings"
)
EXTRACT = ROOT / "queries/extra-trees-extract/species.json"
SPECIES_JAVA = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/genetics/ExtraTreesTreeSpecies.java"
RESOLVER = ROOT / "src/main/java/com/leon1236/reforestry/arboriculture/client/SaplingBlockStateResolver.java"

TYPE_MAP = {
	"Default": "default",
	"Jungle": "jungle",
	"Conifer": "conifer",
	"Fruit": "fruit",
	"Poplar": "poplar",
	"Palm": "palm",
	"Shrub": "shrub",
}


def to_signed_argb(rgb: int) -> int:
	argb = 0xFF000000 | (rgb & 0xFFFFFF)
	return argb - 0x100000000 if argb >= 0x80000000 else argb


def parse_color(value: str | int) -> int:
	if isinstance(value, int):
		return value & 0xFFFFFF
	return int(value, 16) & 0xFFFFFF


def tint_layer(src: Image.Image, rgb: int) -> Image.Image:
	"""Multiply grayscale RGB by tint; preserve alpha."""
	img = src.convert("RGBA")
	r_t, g_t, b_t = (rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF
	pixels = img.load()
	w, h = img.size
	for y in range(h):
		for x in range(w):
			r, g, b, a = pixels[x, y]
			if a == 0:
				continue
			# Forestry/Binnie germling multiply tint
			pixels[x, y] = (
				(r * r_t) // 255,
				(g * g_t) // 255,
				(b * b_t) // 255,
				a,
			)
	return img


def composite_sapling(trunk: Image.Image, leaves: Image.Image, wood: int, leaf: int) -> Image.Image:
	base = tint_layer(trunk, wood)
	top = tint_layer(leaves, leaf)
	return Image.alpha_composite(base, top)


def load_registered_species() -> list[dict]:
	java = SPECIES_JAVA.read_text(encoding="utf-8")
	ids = re.findall(r"registerSpecies\(ReForestry\.id\(\"([a-z0-9_]+)\"\)", java)
	by_path = {
		s["path"]: s
		for s in json.loads(EXTRACT.read_text(encoding="utf-8"))["species"]
	}
	out = []
	for sid in ids:
		info = by_path[sid]
		out.append(
			{
				"id": sid,
				"key": sid.removeprefix("tree_"),
				"sapling_type": info["sapling_type"],
				"leaf_color": parse_color(info["leaf_color"]),
				"wood_color": parse_color(info["wood_color"]),
			}
		)
	return out


def ensure_templates(apply: bool) -> None:
	dst_item = ASSETS / "textures/item/tree_saplings/templates"
	dst_block = ASSETS / "textures/block/tree_saplings/templates"
	if apply:
		dst_item.mkdir(parents=True, exist_ok=True)
		dst_block.mkdir(parents=True, exist_ok=True)
	for kind in sorted(set(TYPE_MAP.values())):
		for part in ("trunk", "leaves"):
			src = BINNIE_SAP / f"{kind}.{part}.png"
			if not src.exists():
				raise SystemExit(f"missing Binnie template {src}")
			for dst_dir in (dst_item, dst_block):
				target = dst_dir / f"{kind}_{part}.png"
				if apply:
					shutil.copy2(src, target)


def merge_sapling_item_json(species: list[dict], apply: bool) -> None:
	path = ASSETS / "items/sapling.json"
	data = json.loads(path.read_text(encoding="utf-8"))
	cases = data["model"]["cases"]
	existing = set()
	for case in cases:
		when = case["when"]
		if isinstance(when, list):
			existing.update(when)
		else:
			existing.add(when)

	added = 0
	for s in species:
		when = f"reforestry:{s['id']}"
		if when in existing:
			continue
		cases.append(
			{
				"when": when,
				"model": {
					"type": "minecraft:model",
					"model": f"reforestry:item/tree_saplings/{s['key']}",
				},
			}
		)
		added += 1

	cases.sort(key=lambda c: c["when"] if isinstance(c["when"], str) else c["when"][0])
	if apply:
		path.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")
	print(f"sapling.json: +{added} cases (total {len(cases)})")


def patch_resolver(species: list[dict], apply: bool) -> None:
	text = RESOLVER.read_text(encoding="utf-8")
	m = re.search(
		r"(private static final Map<String, String> SPECIES_TO_MODEL = Map\.ofEntries\(\n)(.*?)(\n\s*\);)",
		text,
		re.S,
	)
	if not m:
		raise SystemExit("could not locate SPECIES_TO_MODEL in SaplingBlockStateResolver")

	by_key = {
		key: f'            Map.entry("{key}", "{model}")'
		for key, model in re.findall(r'Map\.entry\("([^"]+)",\s*"([^"]+)"\)', m.group(2))
	}
	added = 0
	for s in species:
		if s["key"] in by_key:
			continue
		by_key[s["key"]] = (
			f'            Map.entry("{s["key"]}", "reforestry:block/tree_saplings/{s["key"]}")'
		)
		added += 1
	if added == 0:
		print("SaplingBlockStateResolver: no new entries")
		return

	ordered = [by_key[k] for k in sorted(by_key)]
	body = ",\n".join(ordered)
	new_text = text[: m.start()] + m.group(1) + body + m.group(3) + text[m.end() :]
	if apply:
		RESOLVER.write_text(new_text, encoding="utf-8")
	print(f"SaplingBlockStateResolver: +{added} entries (total {len(ordered)})")

def main() -> int:
	ap = argparse.ArgumentParser(description=__doc__)
	ap.add_argument("--apply", action="store_true")
	args = ap.parse_args()

	species = load_registered_species()
	print(f"{len(species)} Extra Trees species")
	ensure_templates(args.apply)

	item_models = ASSETS / "models/item/tree_saplings"
	block_models = ASSETS / "models/block/tree_saplings"
	item_tex = ASSETS / "textures/item"
	block_tex = ASSETS / "textures/block/tree_saplings"
	if args.apply:
		item_models.mkdir(parents=True, exist_ok=True)
		block_models.mkdir(parents=True, exist_ok=True)
		block_tex.mkdir(parents=True, exist_ok=True)

	baked = 0
	for s in species:
		kind = TYPE_MAP[s["sapling_type"]]
		trunk = Image.open(BINNIE_SAP / f"{kind}.trunk.png")
		leaves = Image.open(BINNIE_SAP / f"{kind}.leaves.png")
		img = composite_sapling(trunk, leaves, s["wood_color"], s["leaf_color"])
		item_png = item_tex / f"{s['key']}_sapling.png"
		block_png = block_tex / f"{s['key']}.png"
		item_model = {
			"parent": "item/generated",
			"textures": {"layer0": f"reforestry:item/{s['key']}_sapling"},
		}
		block_model = {
			"parent": "minecraft:block/cross",
			"textures": {"cross": f"reforestry:block/tree_saplings/{s['key']}"},
		}
		if args.apply:
			img.save(item_png)
			img.save(block_png)
			(item_models / f"{s['key']}.json").write_text(
				json.dumps(item_model, indent=2) + "\n", encoding="utf-8"
			)
			(block_models / f"{s['key']}.json").write_text(
				json.dumps(block_model, indent=2) + "\n", encoding="utf-8"
			)
		baked += 1

	print(f"baked {baked} sapling textures + models")
	merge_sapling_item_json(species, args.apply)
	patch_resolver(species, args.apply)
	if not args.apply:
		print("dry run only — rerun with --apply")
	return 0


if __name__ == "__main__":
	raise SystemExit(main())
