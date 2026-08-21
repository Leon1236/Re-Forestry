#!/usr/bin/env python3
"""Audit Wave 7 (gendustry / extra_bees / extra_trees) DoD asset completeness.

Usage:
  python3 tools/audit_wave7_dod.py
  python3 tools/audit_wave7_dod.py --json /tmp/wave7_dod.json
"""
from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/reforestry"
DATA = ROOT / "src/main/resources/data/reforestry"
LANG = ASSETS / "lang/en_us.json"
ITEMS_DIR = ASSETS / "items"
MODELS_ITEM = ASSETS / "models/item"
TEX_ITEM = ASSETS / "textures/item"
TEX_BLOCK = ASSETS / "textures/block"
LOOT_BLOCKS = DATA / "loot_table/blocks"
RECIPE_DIR = DATA / "recipe"
TAGS_BLOCK = DATA / "tags/block"
TAGS_MINEABLE = [
	DATA / "tags/block/mineable/axe.json",
	DATA / "tags/block/mineable/pickaxe.json",
	DATA / "tags/block/mineable/hoe.json",
	DATA / "tags/block/mineable/shovel.json",
	ROOT / "src/main/resources/data/minecraft/tags/block/mineable/axe.json",
	ROOT / "src/main/resources/data/minecraft/tags/block/mineable/pickaxe.json",
	ROOT / "src/main/resources/data/minecraft/tags/block/mineable/hoe.json",
	ROOT / "src/main/resources/data/minecraft/tags/block/mineable/shovel.json",
	ROOT / "src/main/resources/data/minecraft/tags/block/mineable/scoop.json",
	DATA / "tags/block/mineable_scoop.json",
]


def read(p: Path) -> str:
	return p.read_text(encoding="utf-8", errors="replace")


def enum_constants(java: str) -> list[str]:
	"""Best-effort enum constant names before first method/body semicolon block."""
	m = re.search(r"enum\s+\w+[^{]*\{(.*?)(?:;\s*\n|;\s*public|;\s*private|;\s*static)", java, re.S)
	if not m:
		m = re.search(r"enum\s+\w+[^{]*\{([^}]+)\}", java, re.S)
		if not m:
			return []
		body = m.group(1)
	else:
		body = m.group(1)
	names = []
	for line in body.splitlines():
		line = line.strip()
		if not line or line.startswith("//") or line.startswith("/*") or line.startswith("*"):
			continue
		# CONST or CONST(...) or CONST = 
		mm = re.match(r"([A-Z][A-Z0-9_]*)\s*(?:\(|,|;|$)", line)
		if mm:
			names.append(mm.group(1))
	return names


def serialized_names(java_path: Path) -> list[str]:
	java = read(java_path)
	# Explicit constructor string: CONST("id"
	explicit = {}
	for m in re.finditer(
		r"\b([A-Z][A-Z0-9_]*)\s*\(\s*\"([a-z0-9_/]+)\"",
		java,
	):
		explicit[m.group(1)] = m.group(2)
	# ExtraTreeWoodType style: ASH(..., "et_ash")
	for m in re.finditer(
		r"\b([A-Z][A-Z0-9_]*)\s*\([^;]*?\"([a-z0-9_]+)\"\s*\)\s*,",
		java,
	):
		explicit.setdefault(m.group(1), m.group(2))
	# name().toLowerCase default
	consts = enum_constants(java)
	out = []
	for c in consts:
		if c in explicit:
			out.append(explicit[c])
		else:
			out.append(c.lower())
	return out


def apply_id(identifier: str, id_type: str, subtype: str) -> str:
	if id_type == "TYPE_ONLY" or not identifier:
		return subtype
	if id_type == "PREFIX":
		return f"{identifier}_{subtype}"
	if id_type == "SUFFIX":
		return f"{subtype}_{identifier}"
	return subtype


def parse_feature_groups(java_path: Path) -> list[dict]:
	"""Return list of {kind, identifier, id_type, enum_ref or single_id, has_item}."""
	java = read(java_path)
	groups = []
	# REGISTRY.item("id"
	for m in re.finditer(r'REGISTRY\.item\(\s*"([a-z0-9_]+)"', java):
		groups.append({"kind": "item_single", "id": m.group(1), "has_item": True})
	# REGISTRY.block("id"
	for m in re.finditer(r'REGISTRY\.block\(\s*"([a-z0-9_]+)"', java):
		# check if BlockItem in same call roughly
		start = m.start()
		snippet = java[start : start + 400]
		has_item = "BlockItem" in snippet or "ItemBlock" in snippet or ",\n\t\t\tBlockItem" in snippet
		# ExtraTrees HOPS has BlockItem
		has_item = True  # FeatureBlock with item ctor is always with item in our modules when third arg present
		if re.search(r'REGISTRY\.block\(\s*"' + m.group(1) + r'"[^;]*BlockItem', java, re.S) or \
		   re.search(r'REGISTRY\.block\(\s*"' + m.group(1) + r'"[^;]*ItemBlock', java, re.S) or \
		   re.search(r'REGISTRY\.block\(\s*"' + m.group(1) + r'"[^;]*,\s*\n\s*\w', java, re.S):
			# wall signs may omit item
			pass
		# Detect no-item: .block("x", ctor) without third arg — rare; wall_sign uses group
		groups.append({"kind": "block_single", "id": m.group(1), "has_item": True})

	# itemGroup / blockGroup chains ending in .create()
	# Capture identifier(...) before create
	pattern = re.compile(
		r"REGISTRY\.(itemGroup|blockGroup)\([^;]+?\)\s*"
		r"((?:\.[a-zA-Z]+\([^;]*?\)\s*)*?)"
		r"\.create\(\)",
		re.S,
	)
	for m in pattern.finditer(java):
		kind = "item_group" if m.group(1) == "itemGroup" else "block_group"
		chain = m.group(2)
		full = m.group(0)
		# enum type: last type arg before ), — look at first call args
		header = re.search(r"REGISTRY\.(?:itemGroup|blockGroup)\((.*)\)\s*(?:\.|$)", full.split(".identifier")[0] + ")", re.S)
		enum_ref = None
		# Find EnumType.values() / EnumType.VALUES / ExtraTreeWoodType.WITH_PRODUCTS
		em = re.search(
			r"([A-Za-z0-9_]+)\.(?:values\(\)|VALUES|WITH_PRODUCTS|ALL_LOG_TYPES|LOG_TYPES)",
			full,
		)
		if em:
			enum_ref = em.group(1)
		ident = ""
		id_type = "TYPE_ONLY"
		im = re.search(
			r'\.identifier\(\s*"([^"]+)"\s*(?:,\s*FeatureGroup\.IdentifierType\.(PREFIX|SUFFIX|TYPE_ONLY)\s*)?\)',
			chain + full,
		)
		if im:
			ident = im.group(1)
			id_type = im.group(2) or "PREFIX"
		has_item = True
		if kind == "block_group":
			# no .item( means no block item (wall signs)
			if ".item(" not in full and ".itemWithType(" not in full:
				has_item = False
		groups.append(
			{
				"kind": kind,
				"identifier": ident,
				"id_type": id_type,
				"enum_ref": enum_ref,
				"has_item": has_item,
				"file": str(java_path),
			}
		)
	return groups


def resolve_enum(enum_ref: str) -> list[str]:
	candidates = list((ROOT / "src/main/java").rglob(f"{enum_ref}.java"))
	if not candidates:
		# maybe nested
		return []
	return serialized_names(candidates[0])


def collect_wave7_ids() -> dict:
	item_ids: set[str] = set()
	block_ids: set[str] = set()
	sources = [
		ROOT / "src/main/java/com/leon1236/reforestry/gendustry/features/GItems.java",
		ROOT / "src/main/java/com/leon1236/reforestry/gendustry/features/GBlocks.java",
		ROOT / "src/main/java/com/leon1236/reforestry/extra_bees/features/ExtraBeesItems.java",
		ROOT / "src/main/java/com/leon1236/reforestry/extra_bees/features/ExtraBeesBlocks.java",
		ROOT / "src/main/java/com/leon1236/reforestry/extratrees/features/ExtraTreesItems.java",
		ROOT / "src/main/java/com/leon1236/reforestry/extratrees/features/ExtraTreesBlocks.java",
	]
	# Extra trees woodGroup helper uses custom identifier
	# Re-parse ExtraTreesBlocks specially for wood groups

	for src in sources:
		if not src.exists():
			continue
		for g in parse_feature_groups(src):
			if g["kind"] in ("item_single",):
				item_ids.add(g["id"])
			elif g["kind"] == "block_single":
				block_ids.add(g["id"])
				if g.get("has_item"):
					item_ids.add(g["id"])
			else:
				subtypes = resolve_enum(g.get("enum_ref") or "")
				for st in subtypes:
					rid = apply_id(g.get("identifier", ""), g.get("id_type", "TYPE_ONLY"), st)
					if g["kind"] == "item_group":
						item_ids.add(rid)
					else:
						block_ids.add(rid)
						if g.get("has_item"):
							item_ids.add(rid)

	# Fluids -> buckets
	for fluid_java in [
		ROOT / "src/main/java/com/leon1236/reforestry/gendustry/fluids/GFluids.java",
		ROOT / "src/main/java/com/leon1236/reforestry/extratrees/fluids/ExtraTreesFluids.java",
	]:
		if not fluid_java.exists():
			continue
		text = read(fluid_java)
		for m in re.finditer(r'\b([A-Z][A-Z0-9_]*)\s*\(\s*"([a-z0-9_]+)"', text):
			fid = m.group(2)
			item_ids.add(f"bucket_{fid}")
			# fluid block may exist
			block_ids.add(fid)  # fluid blocks often share fluid id — may over-report

	# Gene sample / template already in GItems
	# Fix fluid blocks: gendustry fluid blocks
	return {"items": sorted(item_ids), "blocks": sorted(block_ids)}


def load_lang() -> dict:
	return json.loads(read(LANG))


def recipe_mentions(item_id: str) -> bool:
	needle = f"reforestry:{item_id}"
	# scan recipe dir once via cache
	return needle in RECIPE_CACHE


def build_recipe_cache() -> str:
	parts = []
	if RECIPE_DIR.exists():
		for p in RECIPE_DIR.rglob("*.json"):
			parts.append(read(p))
	# also carpenter etc under data/reforestry/recipe
	return "\n".join(parts)


def mineable_set() -> set[str]:
	found: set[str] = set()
	for p in TAGS_MINEABLE:
		if not p.exists():
			continue
		try:
			data = json.loads(read(p))
		except Exception:
			continue
		for v in data.get("values", []):
			if isinstance(v, str) and v.startswith("reforestry:"):
				found.add(v.split(":", 1)[1])
			elif isinstance(v, str) and v.startswith("#"):
				# expand tag file if local
				tag_path = resolve_tag(v)
				if tag_path and tag_path.exists():
					try:
						td = json.loads(read(tag_path))
						for tv in td.get("values", []):
							if isinstance(tv, str) and tv.startswith("reforestry:"):
								found.add(tv.split(":", 1)[1])
					except Exception:
						pass
	# also all reforestry mineable tags
	for p in (ROOT / "src/main/resources/data").rglob("mineable/**/*.json"):
		try:
			data = json.loads(read(p))
		except Exception:
			continue
		for v in data.get("values", []):
			if isinstance(v, str) and v.startswith("reforestry:"):
				found.add(v.split(":", 1)[1])
			elif isinstance(v, str) and v.startswith("#reforestry:"):
				tp = DATA / "tags/block" / (v.split(":", 1)[1] + ".json")
				if tp.exists():
					try:
						td = json.loads(read(tp))
						for tv in td.get("values", []):
							if isinstance(tv, str) and tv.startswith("reforestry:"):
								found.add(tv.split(":", 1)[1])
					except Exception:
						pass
	return found


def resolve_tag(tag: str) -> Path | None:
	# #reforestry:foo or #minecraft:mineable/axe
	if not tag.startswith("#"):
		return None
	full = tag[1:]
	ns, path = full.split(":", 1)
	return ROOT / f"src/main/resources/data/{ns}/tags/block/{path}.json"


def model_texture_refs(model_path: Path) -> list[str]:
	try:
		data = json.loads(read(model_path))
	except Exception:
		return []
	refs = []
	textures = data.get("textures")
	if isinstance(textures, dict):
		for v in textures.values():
			if isinstance(v, str):
				refs.append(v)
	parent = data.get("parent")
	if isinstance(parent, str) and parent.startswith("reforestry:item/"):
		# parent model may hold textures
		pid = parent.split("/", 1)[-1]
		pp = MODELS_ITEM / f"{pid}.json"
		if pp.exists() and pp != model_path:
			refs.extend(model_texture_refs(pp))
	return refs


def texture_exists(ref: str) -> bool:
	# reforestry:item/foo or reforestry:block/foo or item/foo
	if ":" in ref:
		ns, path = ref.split(":", 1)
	else:
		ns, path = "minecraft", ref
	if ns == "minecraft":
		return True  # don't audit vanilla
	# path like item/foo or block/foo
	png = ASSETS / "textures" / f"{path}.png"
	return png.exists()


def lang_keys_for_item(iid: str) -> list[str]:
	return [f"item.reforestry.{iid}", f"block.reforestry.{iid}"]


CRAFTABLE_HINTS = [
	# machines / parts / upgrades / woods that should have recipes
	"industrial_apiary",
	"mutagen_producer",
	"dna_extractor",
	"protein_liquefier",
	"sampler",
	"mutatron",
	"advanced_mutatron",
	"imprinter",
	"genetic_transposer",
	"replicator",
	"upgrade_frame",
	"elite_upgrade_frame",
	"labware",
	"pollen_kit",
	"alveary_",
	"_upgrade",
	"elite_upgrade",
	"hive_frame_",
	"scented_gear",
	"proven_gear",
	"wood_wax",
	"lumbermill",
	"press",
	"brewery",
	"distillery",
	"_planks",
	"_boat",
	"_door",
	"_trapdoor",
	"_fence",
	"_stairs",
	"_slab",
	"_sign",
	"_button",
	"_pressure_plate",
	"yeast",
	"grain_",
]


def should_have_recipe(iid: str) -> bool:
	# skip gene_sample, genetic_template, blank samples (crafted via machines), foods from trees, buckets, pods, combs, drops
	skip_prefixes = (
		"bee_comb_",
		"honey_drop_",
		"propolis_",
		"bucket_",
		"pods_",
		"gene_sample",
		"genetic_template",
		"blank_",
	)
	if iid in ("gene_sample", "genetic_template"):
		return False
	if any(iid.startswith(p) for p in skip_prefixes):
		return False
	# foods — typically no crafting (from trees)
	food_java = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/items/EnumExtraTreesFood.java"
	if food_java.exists():
		foods = set(serialized_names(food_java))
		if iid in foods:
			return False
	# fluid block ids falsely collected
	if iid in ("mutagen", "liquid_dna", "protein"):
		return False
	for hint in CRAFTABLE_HINTS:
		if hint in iid:
			return True
	# gendustry resources / upgrades
	gd_res = ROOT / "src/main/java/com/leon1236/reforestry/gendustry/item/GendustryResourceType.java"
	if gd_res.exists() and iid in set(serialized_names(gd_res)):
		return True
	# misc extra bees craftable (dusts etc) — check recipes separately; don't force all
	return False


def main() -> int:
	global RECIPE_CACHE
	ap = argparse.ArgumentParser()
	ap.add_argument("--json", type=Path)
	args = ap.parse_args()

	ids = collect_wave7_ids()
	# Refine fluid blocks: only real fluid blocks
	fluid_block_ids = set()
	for fluid_java, is_block in [
		(ROOT / "src/main/java/com/leon1236/reforestry/gendustry/fluids/GFluids.java", True),
	]:
		pass
	# Remove over-collected ExtraTrees fluid ids from blocks (they're fluids not blocks)
	et_fluids = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/fluids/ExtraTreesFluids.java"
	et_fluid_ids = set()
	if et_fluids.exists():
		for m in re.finditer(r'\b([A-Z][A-Z0-9_]*)\s*\(\s*"([a-z0-9_]+)"', read(et_fluids)):
			et_fluid_ids.add(m.group(2))
	gd_fluids = ROOT / "src/main/java/com/leon1236/reforestry/gendustry/fluids/GFluids.java"
	gd_fluid_ids = set()
	if gd_fluids.exists():
		for m in re.finditer(r'\b([A-Z][A-Z0-9_]*)\s*\(\s*"([a-z0-9_]+)"', read(gd_fluids)):
			gd_fluid_ids.add(m.group(2))

	blocks = [b for b in ids["blocks"] if b not in et_fluid_ids]
	# gendustry fluids have fluid blocks
	items = list(ids["items"])
	# remove fluid ids that aren't items from items if accidentally added
	items = [i for i in items if i not in et_fluid_ids and i not in gd_fluid_ids]

	lang = load_lang()
	RECIPE_CACHE = build_recipe_cache()
	mineable = mineable_set()

	missing_items_json = []
	missing_models = []
	missing_lang = []
	missing_textures = []
	missing_loot = []
	missing_mineable = []
	missing_recipes = []

	# Wall signs / hanging wall — no item
	no_item_blocks = set()
	for wood in resolve_enum("ExtraTreeWoodType"):
		no_item_blocks.add(f"{wood}_wall_sign")
		no_item_blocks.add(f"{wood}_wall_hanging_sign")

	for iid in items:
		if iid in no_item_blocks:
			continue
		if not (ITEMS_DIR / f"{iid}.json").exists():
			missing_items_json.append(iid)
		model = MODELS_ITEM / f"{iid}.json"
		if not model.exists():
			# boat models sometimes nested? still expect models/item/{id}.json
			missing_models.append(iid)
		else:
			for ref in model_texture_refs(model):
				if ref.startswith("reforestry:") or (":" not in ref and ref.startswith("item/")):
					if not texture_exists(ref if ":" in ref else f"reforestry:{ref}"):
						missing_textures.append(f"{iid} -> {ref}")
		# lang: item or block key
		keys = lang_keys_for_item(iid)
		if not any(k in lang for k in keys):
			# fluid buckets sometimes fluid.reforestry.x or item.reforestry.bucket_x
			alt = f"item.reforestry.{iid}"
			if alt not in lang:
				missing_lang.append(iid)
		if should_have_recipe(iid) and not recipe_mentions(iid):
			missing_recipes.append(iid)

	for bid in blocks:
		if bid in et_fluid_ids:
			continue
		loot = LOOT_BLOCKS / f"{bid}.json"
		# hops may drop differently; fluid blocks may not have loot
		if bid in gd_fluid_ids:
			continue
		if not loot.exists():
			missing_loot.append(bid)
		# mineable — skip crops/pods/fluid
		if bid in ("hops",) or bid.startswith("pods_"):
			continue
		if bid not in mineable:
			# check if in any block tag that is mineable via #mineable
			missing_mineable.append(bid)

	# Creative tab lang
	tab_keys = [
		"itemGroup.reforestry.gendustry",
		"itemGroup.reforestry.gene_samples",
		"itemGroup.reforestry.extra_bees",
		"itemGroup.reforestry.extra_trees",
	]
	# FeatureCreativeTab may use itemGroup.{name} without namespace — check FeatureCreativeTab
	tab_missing = []
	for k in [
		"itemGroup.gendustry",
		"itemGroup.gene_samples",
		"itemGroup.extra_bees",
		"itemGroup.extra_trees",
		"itemGroup.reforestry.gendustry",
		"itemGroup.reforestry.gene_samples",
		"itemGroup.reforestry.extra_bees",
		"itemGroup.reforestry.extra_trees",
	]:
		pass
	# Check which exist
	tab_present = [k for k in lang if "itemGroup" in k and any(x in k for x in ("gendustry", "gene_samples", "extra_bees", "extra_trees"))]

	report = {
		"counts": {
			"items_registered": len(items),
			"blocks_registered": len(blocks),
			"missing_items_json": len(missing_items_json),
			"missing_models": len(missing_models),
			"missing_lang": len(missing_lang),
			"missing_textures": len(missing_textures),
			"missing_loot": len(missing_loot),
			"missing_mineable": len(missing_mineable),
			"missing_recipes": len(missing_recipes),
		},
		"missing_items_json": missing_items_json,
		"missing_models": missing_models,
		"missing_lang": missing_lang[:200],
		"missing_textures": missing_textures[:200],
		"missing_loot": missing_loot[:200],
		"missing_mineable": missing_mineable[:200],
		"missing_recipes": missing_recipes[:200],
		"tab_lang_keys": tab_present,
		"sample_items": items[:30],
	}

	print(json.dumps(report["counts"], indent=2))
	print("\n--- missing_items_json ---")
	print("\n".join(missing_items_json[:80]) or "(none)")
	print("\n--- missing_models ---")
	print("\n".join(missing_models[:80]) or "(none)")
	print("\n--- missing_lang (first 80) ---")
	print("\n".join(missing_lang[:80]) or "(none)")
	print("\n--- missing_textures (first 40) ---")
	print("\n".join(missing_textures[:40]) or "(none)")
	print("\n--- missing_loot (first 80) ---")
	print("\n".join(missing_loot[:80]) or "(none)")
	print("\n--- missing_mineable (first 80) ---")
	print("\n".join(missing_mineable[:80]) or "(none)")
	print("\n--- missing_recipes (first 80) ---")
	print("\n".join(missing_recipes[:80]) or "(none)")
	print("\n--- tab lang ---")
	print("\n".join(tab_present) or "(none)")

	if args.json:
		args.json.write_text(json.dumps(report, indent=2) + "\n", encoding="utf-8")
		print(f"\nwrote {args.json}")
	return 0


RECIPE_CACHE = ""

if __name__ == "__main__":
	sys.exit(main())
