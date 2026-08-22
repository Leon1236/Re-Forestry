#!/usr/bin/env python3
"""Generate Extra Trees ET6 moth species Java, taxa, textures, models, lang from moths.json.

Usage:
  python3 tools/generate_extra_trees_ET6.py --apply
  python3 tools/generate_extra_trees_ET6.py --apply --assets --lang
Without --apply: dry-run summary only.
"""
from __future__ import annotations

import argparse
import json
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
MOTHS_JSON = ROOT / "queries/extra-trees-extract/moths.json"
BINNIE_ET = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees"
BINNIE_ENTITY = BINNIE_ET / "src/main/resources/assets/extratrees/textures/entity/butterflies"
BINNIE_ITEM = BINNIE_ET / "src/main/resources/assets/extratrees/textures/items/butterflies"

OUT_JAVA = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/genetics/ExtraTreesMothSpecies.java"
OUT_IDS = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/genetics/ExtraTreesMothIds.java"
ASSETS = ROOT / "src/main/resources/assets/reforestry"
TAXON_DIR = ROOT / "src/main/resources/data/reforestry/taxon"
LANG_PATH = ASSETS / "lang/en_us.json"
ITEMS_BUTTERFLY = ASSETS / "items/butterfly.json"
MODELS_DIR = ASSETS / "models/item/butterfly"
ENTITY_TEX = ASSETS / "textures/entity/butterfly"
ITEM_TEX = ASSETS / "textures/item/butterfly"

PAPILIONIDAE = {"atrophaneura", "teinopalpus", "troides"}


def load_moths():
	data = json.loads(MOTHS_JSON.read_text())
	moths = data["moths"]
	if len(moths) != 22:
		raise SystemExit(f"Expected 22 moths, got {len(moths)}")
	return data, moths


def path_id(reforestry_id: str) -> str:
	return reforestry_id.split(":", 1)[1]


def binnie_texture_stem(texture: str) -> str:
	# "butterflies/white_admiral" -> white_admiral
	return texture.split("/", 1)[-1]


def write_java(moths: list[dict]) -> str:
	lines = [
		"package com.leon1236.reforestry.extratrees.genetics;",
		"",
		"import net.minecraft.network.chat.TextColor;",
		"",
		"import com.leon1236.reforestry.ReForestry;",
		"import com.leon1236.reforestry.api.genetics.ForestryTaxa;",
		"import com.leon1236.reforestry.api.plugin.IGeneticRegistration;",
		"import com.leon1236.reforestry.api.plugin.ILepidopterologyRegistration;",
		"",
		"public final class ExtraTreesMothSpecies {",
		"\tprivate ExtraTreesMothSpecies() {",
		"\t}",
		"",
		"\tpublic static void registerTaxa(IGeneticRegistration registration) {",
	]
	seen = set()
	for moth in moths:
		genus = moth["genus"].lower()
		if genus in seen:
			continue
		seen.add(genus)
		if genus in ("danaus", "pararge"):
			continue
		family = "ForestryTaxa.FAMILY_SWALLOWTAIL_BUTTERFLIES" if genus in PAPILIONIDAE else "ForestryTaxa.FAMILY_BRUSH_FOOTED_BUTTERFLIES"
		lines.append(f'\t\tregistration.defineTaxon({family}, "{genus}");')
	lines.append("\t}")
	lines.append("")
	lines.append("\tpublic static void register(ILepidopterologyRegistration butterflies) {")
	for moth in moths:
		pid = path_id(moth["reforestry_id"])
		genus = moth["genus"].lower()
		binomial = moth["binomial"]
		color = moth["color"]
		lines.append(
			f'\t\tbutterflies.registerSpecies(ReForestry.id("{pid}"), "{genus}", "{binomial}", true, TextColor.fromRgb({color}), 0.5f)'
		)
		lines.append('\t\t\t.setMoth(true)')
		lines.append('\t\t\t.setAuthority("Binnie");')
		lines.append("")
	if lines[-1] == "":
		lines.pop()
	lines.append("\t}")
	lines.append("}")
	lines.append("")
	return "\n".join(lines)


def write_ids(moths: list[dict]) -> str:
	lines = [
		"package com.leon1236.reforestry.extratrees.genetics;",
		"",
		"import java.util.List;",
		"",
		"import net.minecraft.resources.Identifier;",
		"",
		"import com.leon1236.reforestry.ReForestry;",
		"",
		"public final class ExtraTreesMothIds {",
		"\tprivate ExtraTreesMothIds() {",
		"\t}",
		"",
	]
	names = []
	for moth in moths:
		pid = path_id(moth["reforestry_id"])
		const = pid.upper()
		names.append(const)
		lines.append(f'\tpublic static final Identifier {const} = ReForestry.id("{pid}");')
	lines.append("")
	joined = ",\n\t\t\t".join(names)
	lines.append(f"\tpublic static final List<Identifier> ALL = List.of(\n\t\t\t{joined});")
	lines.append("}")
	lines.append("")
	return "\n".join(lines)


def write_taxa(moths: list[dict]) -> list[Path]:
	written = []
	seen = set()
	for moth in moths:
		genus = moth["genus"].lower()
		if genus in seen:
			continue
		seen.add(genus)
		path = TAXON_DIR / f"{genus}.json"
		if path.exists():
			continue
		parent = "papilionidae" if genus in PAPILIONIDAE else "nymphalidae"
		path.write_text(json.dumps({"parent": parent, "name": genus, "rank": "genus"}, indent=2) + "\n")
		written.append(path)
	return written


def copy_textures(moths: list[dict]) -> tuple[int, int]:
	ENTITY_TEX.mkdir(parents=True, exist_ok=True)
	ITEM_TEX.mkdir(parents=True, exist_ok=True)
	ent = item = 0
	for moth in moths:
		pid = path_id(moth["reforestry_id"])
		stem = binnie_texture_stem(moth["texture"])
		src_e = BINNIE_ENTITY / f"{stem}.png"
		src_i = BINNIE_ITEM / f"{stem}.png"
		if not src_e.is_file():
			raise SystemExit(f"Missing Binnie entity texture: {src_e}")
		if not src_i.is_file():
			raise SystemExit(f"Missing Binnie item texture: {src_i}")
		shutil.copy2(src_e, ENTITY_TEX / f"{pid}.png")
		shutil.copy2(src_i, ITEM_TEX / f"{pid}.png")
		ent += 1
		item += 1
	return ent, item


def write_models(moths: list[dict]) -> int:
	MODELS_DIR.mkdir(parents=True, exist_ok=True)
	select = json.loads(ITEMS_BUTTERFLY.read_text())
	cases = select["model"]["cases"]
	existing = {c["when"] for c in cases}
	count = 0
	for moth in moths:
		rid = moth["reforestry_id"]
		pid = path_id(rid)
		model = {
			"parent": "reforestry:item/butterfly",
			"textures": {"butterfly": f"reforestry:item/butterfly/{pid}"},
		}
		(MODELS_DIR / f"{pid}.json").write_text(json.dumps(model, indent=2) + "\n")
		count += 1
		if rid not in existing:
			cases.append({
				"when": rid,
				"model": {
					"type": "minecraft:model",
					"model": f"reforestry:item/butterfly/{pid}",
				},
			})
			existing.add(rid)
	ITEMS_BUTTERFLY.write_text(json.dumps(select, indent=2) + "\n")
	return count


def write_lang(moths: list[dict]) -> int:
	lang = json.loads(LANG_PATH.read_text())
	added = 0
	for moth in moths:
		pid = path_id(moth["reforestry_id"])
		key = f"allele.reforestry.butterfly_species.butterfly_{pid}"
		if key not in lang:
			added += 1
		lang[key] = moth["display"]
	LANG_PATH.write_text(json.dumps(lang, indent=2, ensure_ascii=False) + "\n")
	return added


def main():
	parser = argparse.ArgumentParser()
	parser.add_argument("--apply", action="store_true")
	parser.add_argument("--assets", action="store_true")
	parser.add_argument("--lang", action="store_true")
	args = parser.parse_args()

	_, moths = load_moths()
	print(f"moths={len(moths)}")
	print(f"ids={[path_id(m['reforestry_id']) for m in moths]}")

	if not args.apply:
		print("dry-run; pass --apply to write")
		return

	OUT_JAVA.parent.mkdir(parents=True, exist_ok=True)
	OUT_JAVA.write_text(write_java(moths))
	OUT_IDS.write_text(write_ids(moths))
	print(f"wrote {OUT_JAVA.relative_to(ROOT)}")
	print(f"wrote {OUT_IDS.relative_to(ROOT)}")

	taxa = write_taxa(moths)
	print(f"taxon files new={len(taxa)}")

	if args.assets:
		ent, item = copy_textures(moths)
		models = write_models(moths)
		print(f"textures entity={ent} item={item} models={models}")

	if args.lang:
		added = write_lang(moths)
		print(f"lang keys upserted={len(moths)} (new={added})")


if __name__ == "__main__":
	main()
