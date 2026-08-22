#!/usr/bin/env python3
"""Generate Extra Bees centrifuge + squeezer datapack recipes from extract.

Reads queries/extra-bees-items.json (EB0). Soft-skips products/fluids that are
not registered in Re-Forestry (IC2, missing Binnie fluids, OreDict without a
local item). Never invents registry ids.

Usage:
  python3 tools/generate_extra_bees_machine_recipes.py
  python3 tools/generate_extra_bees_machine_recipes.py --apply
"""
from __future__ import annotations

import argparse
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
EXTRACT = ROOT / "queries" / "extra-bees-items.json"
ITEMS_DIR = ROOT / "src" / "main" / "resources" / "assets" / "reforestry" / "items"
CENTRIFUGE_DIR = ROOT / "src" / "main" / "resources" / "data" / "reforestry" / "recipe" / "centrifuge" / "extra_bees"
SQUEEZER_DIR = ROOT / "src" / "main" / "resources" / "data" / "reforestry" / "recipe" / "squeezer" / "extra_bees"

CENTRIFUGE_TIME = 20
DROP_SQUEEZER_TIME = 10
DROP_FLUID_AMOUNT = 200
PROPOLIS_SQUEEZER_TIME = 20
PROPOLIS_FLUID_AMOUNT = 500

LIQUID_TO_FLUID = {
	"juice": "reforestry:juice",
	"milk": "reforestry:milk",
	"seedoil": "reforestry:seed_oil",
	"short.mead": "reforestry:short_mead",
	"for.honey": "reforestry:honey",
	"Water": "minecraft:water",
	"water": "minecraft:water",
}

KNOWN_VANILLA_FLUIDS = {
	"minecraft:water",
	"minecraft:lava",
	"minecraft:milk",
}

DYE_META_PRODUCT = {
	4: "minecraft:lapis_lazuli",
	15: "minecraft:bone_meal",
}

DYE_META_REMNANT = {
	0: "minecraft:black_dye",
	1: "minecraft:red_dye",
	2: "minecraft:green_dye",
	3: "minecraft:brown_dye",
	4: "minecraft:blue_dye",
	5: "minecraft:purple_dye",
	6: "minecraft:cyan_dye",
	7: "minecraft:light_gray_dye",
	8: "minecraft:gray_dye",
	9: "minecraft:pink_dye",
	10: "minecraft:lime_dye",
	11: "minecraft:yellow_dye",
	12: "minecraft:light_blue_dye",
	13: "minecraft:magenta_dye",
	14: "minecraft:orange_dye",
	15: "minecraft:white_dye",
}

OREDICT_TO_ITEM = {
	"dustSulfur": "minecraft:gunpowder",
	"dustSmallIron": "minecraft:iron_nugget",
	"dustSmallPyrite": "minecraft:iron_nugget",
	"dustCertusQuartz": "minecraft:quartz",
	"dustEnderPearl": "minecraft:ender_pearl",
	"dustSawdust": "minecraft:stick",
	"sawdust": "minecraft:stick",
}

ITEM_STACK_FIELD = {
	"ROTTEN_FLESH": "minecraft:rotten_flesh",
	"REDSTONE": "minecraft:redstone",
	"CLAY_BALL": "minecraft:clay_ball",
	"SLIME_BALL": "minecraft:slime_ball",
	"BLAZE_POWDER": "minecraft:blaze_powder",
	"GLOWSTONE_DUST": "minecraft:glowstone_dust",
	"QUARTZ": "minecraft:quartz",
	"BROWN_MUSHROOM_BLOCK": "minecraft:brown_mushroom_block",
	"RED_MUSHROOM_BLOCK": "minecraft:red_mushroom_block",
}


def load_known_items() -> set[str]:
	known = {f"reforestry:{p.stem}" for p in ITEMS_DIR.glob("*.json")}
	known.update({
		"minecraft:air",
		"minecraft:rotten_flesh",
		"minecraft:redstone",
		"minecraft:clay_ball",
		"minecraft:slime_ball",
		"minecraft:blaze_powder",
		"minecraft:glowstone_dust",
		"minecraft:quartz",
		"minecraft:brown_mushroom_block",
		"minecraft:red_mushroom_block",
		"minecraft:bone_meal",
		"minecraft:lapis_lazuli",
		"minecraft:black_dye",
		"minecraft:red_dye",
		"minecraft:green_dye",
		"minecraft:brown_dye",
		"minecraft:blue_dye",
		"minecraft:purple_dye",
		"minecraft:cyan_dye",
		"minecraft:light_gray_dye",
		"minecraft:gray_dye",
		"minecraft:pink_dye",
		"minecraft:lime_dye",
		"minecraft:yellow_dye",
		"minecraft:light_blue_dye",
		"minecraft:magenta_dye",
		"minecraft:orange_dye",
		"minecraft:white_dye",
	})
	return known


def load_known_fluids() -> set[str]:
	fluids = set(KNOWN_VANILLA_FLUIDS)
	for path in (ROOT / "src" / "main" / "resources" / "assets" / "reforestry" / "blockstates").glob("fluid_*.json"):
		fluids.add(f"reforestry:{path.stem.removeprefix('fluid_')}")
	return fluids


def misc_id_by_enum(data: dict) -> dict[str, str]:
	return {entry["enum"]: entry["reforestry_id"] for entry in data.get("misc", [])}


def resolve_item_stack(product: dict, *, remnant: bool) -> tuple[str | None, str | None]:
	field = product.get("field")
	meta = product.get("meta", 0)
	count = product.get("count", 1)
	if field == "DYE":
		table = DYE_META_REMNANT if remnant else DYE_META_PRODUCT
		item = table.get(meta)
		if item is None:
			return None, f"unmapped dye meta {meta}"
		return item, None
	if field in ITEM_STACK_FIELD:
		return ITEM_STACK_FIELD[field], None
	item = product.get("item")
	if item and item != "minecraft:dye":
		return item, None
	return None, f"unresolved item_stack {product.get('source')}"


def resolve_product_item(
	product: dict,
	*,
	known_items: set[str],
	misc_ids: dict[str, str],
	remnant: bool = False,
) -> tuple[str | None, int, str | None]:
	kind = product.get("kind")
	count = int(product.get("count") or 1)
	if kind == "ic2_item":
		return None, count, f"ic2:{product.get('name')}"
	if kind == "oredict":
		ore = product.get("ore")
		mapped = OREDICT_TO_ITEM.get(ore) if ore else None
		if mapped:
			if mapped not in known_items and not mapped.startswith("minecraft:"):
				return None, count, f"missing item {mapped}"
			return mapped, count, None
		return None, count, f"oredict:{ore}"
	if kind == "item_stack":
		item, reason = resolve_item_stack(product, remnant=remnant)
		if item is None:
			return None, count, reason
		if item not in known_items and not item.startswith("minecraft:"):
			return None, count, f"missing item {item}"
		return item, count, None
	if kind == "extra_bees_misc":
		enum = product.get("enum")
		item = misc_ids.get(enum) or product.get("reforestry_id")
		if enum and enum in misc_ids:
			item = misc_ids[enum]
		if not item:
			return None, count, f"missing misc {enum}"
		if item not in known_items:
			return None, count, f"missing item {item}"
		return item, count, None
	item = product.get("reforestry_id")
	if not item:
		return None, count, f"no id for {kind}:{product.get('source')}"
	if item not in known_items:
		return None, count, f"missing item {item}"
	return item, count, None


def comb_products(comb: dict) -> list[dict]:
	copied = list(comb.get("copied_products") or [])
	own = [p for p in (comb.get("products") or []) if p.get("role") != "squeezer_remnant"]
	return copied + own


def remnant_for_drop(data: dict, drop_enum: str) -> dict | None:
	for comb in data.get("combs", []):
		for product in comb.get("products") or []:
			if product.get("role") != "squeezer_remnant":
				continue
			for sibling in comb.get("products") or []:
				if sibling.get("kind") == "extra_bees_drop" and sibling.get("enum") == drop_enum:
					return product
	return None


def write_json(path: Path, payload: dict, apply: bool) -> None:
	text = json.dumps(payload, indent=2) + "\n"
	print(f"WRITE {path.relative_to(ROOT)}")
	if apply:
		path.parent.mkdir(parents=True, exist_ok=True)
		path.write_text(text, encoding="utf-8")


def generate_centrifuge(data: dict, known_items: set[str], misc_ids: dict[str, str], apply: bool) -> tuple[int, list[str]]:
	written = 0
	skips: list[str] = []
	for comb in data.get("combs", []):
		if not comb.get("active"):
			continue
		comb_id = comb["reforestry_id"]
		enum = comb["enum"].lower()
		products_out: list[dict] = []
		for product in comb_products(comb):
			item, count, reason = resolve_product_item(product, known_items=known_items, misc_ids=misc_ids)
			chance = product.get("chance")
			if chance is None:
				skips.append(f"centrifuge {enum}: skip product without chance ({product.get('source')})")
				continue
			if item is None:
				tag = "try" if product.get("try") else "hard"
				skips.append(f"centrifuge {enum}: soft-skip {tag} product ({reason})")
				continue
			entry = {"item": item, "chance": float(chance)}
			if count != 1:
				entry["count"] = count
			products_out.append(entry)
		if not products_out:
			skips.append(f"centrifuge {enum}: no resolvable products — recipe omitted")
			continue
		payload = {
			"type": "reforestry:centrifuge",
			"input": comb_id,
			"time": CENTRIFUGE_TIME,
			"products": products_out,
		}
		write_json(CENTRIFUGE_DIR / f"{enum}_comb.json", payload, apply)
		written += 1
	return written, skips


def map_liquid(liquid: str, known_fluids: set[str]) -> tuple[str | None, str | None]:
	if not liquid:
		return None, "empty liquid"
	mapped = LIQUID_TO_FLUID.get(liquid)
	if mapped is None:
		return None, f"unregistered Binnie/IC2 fluid '{liquid}'"
	if mapped not in known_fluids and not mapped.startswith("minecraft:"):
		return None, f"fluid not in Re-Forestry: {mapped}"
	return mapped, None


def generate_squeezer_drops(
	data: dict,
	known_items: set[str],
	known_fluids: set[str],
	misc_ids: dict[str, str],
	apply: bool,
) -> tuple[int, list[str]]:
	written = 0
	skips: list[str] = []
	for drop in data.get("drops", []):
		if not drop.get("active"):
			continue
		enum = drop["enum"].lower()
		drop_id = drop["reforestry_id"]
		fluid, reason = map_liquid(drop.get("liquid") or "", known_fluids)
		if fluid is None:
			skips.append(f"squeezer drop {enum}: soft-skip ({reason})")
			continue
		remnant_item = "minecraft:air"
		remnant_count = 0
		chance = 0.0
		remnant = remnant_for_drop(data, drop["enum"])
		if remnant is not None:
			item, count, rem_reason = resolve_product_item(
				remnant, known_items=known_items, misc_ids=misc_ids, remnant=True
			)
			if item is None:
				skips.append(f"squeezer drop {enum}: soft-skip remnant ({rem_reason})")
			else:
				remnant_item = item
				remnant_count = count
				chance = 1.0
		payload = {
			"type": "reforestry:squeezer",
			"time": DROP_SQUEEZER_TIME,
			"resources": [{"item": drop_id}],
			"output": {"fluid": fluid, "amount": DROP_FLUID_AMOUNT},
			"remnant": {"item": remnant_item, "count": remnant_count},
			"chance": chance,
		}
		write_json(SQUEEZER_DIR / f"honey_drop_{enum}.json", payload, apply)
		written += 1
	return written, skips


def generate_squeezer_propolis(
	data: dict,
	known_fluids: set[str],
	apply: bool,
) -> tuple[int, list[str]]:
	written = 0
	skips: list[str] = []
	for propolis in data.get("propolis", []):
		if not propolis.get("active"):
			continue
		enum = propolis["enum"].lower()
		propolis_id = propolis["reforestry_id"]
		fluid, reason = map_liquid(propolis.get("liquid") or "", known_fluids)
		if fluid is None:
			skips.append(f"squeezer propolis {enum}: soft-skip ({reason})")
			continue
		payload = {
			"type": "reforestry:squeezer",
			"time": PROPOLIS_SQUEEZER_TIME,
			"resources": [{"item": propolis_id}],
			"output": {"fluid": fluid, "amount": PROPOLIS_FLUID_AMOUNT},
			"remnant": {"item": "minecraft:air", "count": 0},
			"chance": 0.0,
		}
		write_json(SQUEEZER_DIR / f"propolis_{enum}.json", payload, apply)
		written += 1
	return written, skips


def main() -> None:
	parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
	parser.add_argument("--apply", action="store_true")
	parser.add_argument("--extract", type=Path, default=EXTRACT)
	args = parser.parse_args()

	data = json.loads(args.extract.read_text(encoding="utf-8"))
	known_items = load_known_items()
	known_fluids = load_known_fluids()
	misc_ids = misc_id_by_enum(data)

	mode = "APPLY" if args.apply else "DRY RUN"
	print(f"[{mode}] Extra Bees centrifuge/squeezer from {args.extract.relative_to(ROOT)}")
	print(f"  known items={len(known_items)} fluids={sorted(known_fluids)}\n")

	c_n, c_skips = generate_centrifuge(data, known_items, misc_ids, args.apply)
	d_n, d_skips = generate_squeezer_drops(data, known_items, known_fluids, misc_ids, args.apply)
	p_n, p_skips = generate_squeezer_propolis(data, known_fluids, args.apply)

	all_skips = c_skips + d_skips + p_skips
	print("\n--- soft-skips ---")
	for line in all_skips:
		print(line)
	print(
		f"\nsummary: centrifuge={c_n} squeezer_drops={d_n} squeezer_propolis={p_n} "
		f"soft_skips={len(all_skips)}"
	)
	if not args.apply:
		print("dry run only — rerun with --apply to write files")


if __name__ == "__main__":
	main()
