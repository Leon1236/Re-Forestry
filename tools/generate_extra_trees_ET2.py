#!/usr/bin/env python3
"""Generate Extra Trees ET2 Java (foods, fruits, species+mutations) from queries/extra-trees-extract/.

Usage:
  python3 tools/generate_extra_trees_ET2.py --apply
  python3 tools/generate_extra_trees_ET2.py --apply --assets --lang
Without --apply: dry-run summary only.
"""
from __future__ import annotations

import argparse
import json
import re
import shutil
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
EXTRACT = ROOT / "queries/extra-trees-extract"
FRUITS_JSON = EXTRACT / "fruits.json"
SPECIES_JSON = EXTRACT / "species.json"
MUTATIONS_JSON = EXTRACT / "mutations.json"
FOODS_JSON = EXTRACT / "foods.json"

OUT_FOOD = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/items/EnumExtraTreesFood.java"
OUT_FRUITS = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/genetics/ExtraTreesFruits.java"
OUT_SPECIES = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/genetics/ExtraTreesTreeSpecies.java"
LANG_PATH = ROOT / "src/main/resources/assets/reforestry/lang/en_us.json"
BINNIE_LANG = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/resources/assets/extratrees/lang/en_US.lang"
BINNIE_FOOD_TEX = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/resources/assets/extratrees/textures/items/food"
BINNIE_FRUIT_TEX = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/resources/assets/extratrees/textures/blocks/fruit"
BINNIE_POD_TEX = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/resources/assets/extratrees/textures/blocks/pods"

REGISTER_ACTIONS = frozenset({"register", "id_collision_remap", "keep_acorn_oak"})

ET_LOG_OVERLAP = {
	"Beech": "ForestryWoodType.BEECH",
	"Fir": "ForestryWoodType.FIR",
	"Elm": "ForestryWoodType.ELM",
	"Pear": "ForestryWoodType.PEAR",
	"Olive": "ForestryWoodType.OLIVE",
	"Gingko": "ForestryWoodType.GINKGO",
}

ET_LOG_MAP = {
	"Cedar": "ExtraTreeWoodType.CEDAR",
	"Hemlock": "ExtraTreeWoodType.HEMLOCK",
	"Cypress": "ExtraTreeWoodType.CYPRESS",
	"Fig": "ExtraTreeWoodType.FIG",
	"Alder": "ExtraTreeWoodType.ALDER",
	"Hazel": "ExtraTreeWoodType.HAZEL",
	"Hornbeam": "ExtraTreeWoodType.HORNBEAM",
	"Box": "ExtraTreeWoodType.BOX",
	"Butternut": "ExtraTreeWoodType.BUTTERNUT",
	"Hickory": "ExtraTreeWoodType.HICKORY",
	"Whitebeam": "ExtraTreeWoodType.WHITEBEAM",
	"Apple": "ExtraTreeWoodType.APPLE",
	"Yew": "ExtraTreeWoodType.YEW",
	"Hawthorn": "ExtraTreeWoodType.HAWTHORN",
	"Rowan": "ExtraTreeWoodType.ROWAN",
	"Elder": "ExtraTreeWoodType.ELDER",
	"Maclura": "ExtraTreeWoodType.MACLURA",
	"Syzgium": "ExtraTreeWoodType.SYZGIUM",
	"Brazilwood": "ExtraTreeWoodType.BRAZILWOOD",
	"Logwood": "ExtraTreeWoodType.LOGWOOD",
	"Iroko": "ExtraTreeWoodType.IROKO",
	"Locust": "ExtraTreeWoodType.LOCUST",
	"Eucalyptus": "ExtraTreeWoodType.EUCALYPTUS",
	"Purpleheart": "ExtraTreeWoodType.PURPLEHEART",
	"Ash": "ExtraTreeWoodType.ASH",
	"Holly": "ExtraTreeWoodType.HOLLY",
	"Sweetgum": "ExtraTreeWoodType.SWEETGUM",
	"Rosewood": "ExtraTreeWoodType.ROSEWOOD",
	"PinkIvory": "ExtraTreeWoodType.PINK_IVORY",
	"Banana": "ExtraTreeWoodType.BANANA",
	"Eucalyptus2": "ExtraTreeWoodType.EUCALYPTUS2",
	"Eucalyptus3": "ExtraTreeWoodType.EUCALYPTUS3",
	"Cherry": "ExtraTreeWoodType.ET_CHERRY",
	"Cinnamon": "ExtraTreeWoodType.CINNAMON",
}

FORESTRY_OR_VANILLA = {
	"OAK": "VanillaWoodType.OAK",
	"JUNGLE": "VanillaWoodType.JUNGLE",
	"CITRUS": "ForestryWoodType.CITRUS",
	"PLUM": "ForestryWoodType.PLUM",
	"POPLAR": "ForestryWoodType.POPLAR",
	"WILLOW": "ForestryWoodType.WILLOW",
	"MAPLE": "ForestryWoodType.MAPLE",
	"PINE": "ForestryWoodType.PINE",
}

REUSE_FRUITS = {
	"reforestry:fruit_olive": "DefaultFruits.OLIVE",
	"reforestry:fruit_orange": "DefaultFruits.ORANGE",
	"reforestry:fruit_apple": "DefaultFruits.APPLE",
	"reforestry:fruit_pear": "DefaultFruits.PEAR",
	"reforestry:fruit_coconut": "DefaultFruits.COCONUT",
}

ALLELE_MAP = {
	("TreeHeight", "Smallest"): "ForestryAlleles.HEIGHT_SMALLEST",
	("TreeHeight", "Smaller"): "ForestryAlleles.HEIGHT_SMALLER",
	("TreeHeight", "Small"): "ForestryAlleles.HEIGHT_SMALL",
	("TreeHeight", "Average"): "ForestryAlleles.HEIGHT_AVERAGE",
	("TreeHeight", "Large"): "ForestryAlleles.HEIGHT_LARGE",
	("TreeHeight", "Larger"): "ForestryAlleles.HEIGHT_LARGER",
	("TreeHeight", "Largest"): "ForestryAlleles.HEIGHT_LARGEST",
	("TreeHeight", "Gigantic"): "ForestryAlleles.HEIGHT_GIGANTIC",
	("Saplings", "Lowest"): "ForestryAlleles.SAPLINGS_LOWEST",
	("Saplings", "Lower"): "ForestryAlleles.SAPLINGS_LOWER",
	("Saplings", "Low"): "ForestryAlleles.SAPLINGS_LOW",
	("Saplings", "Average"): "ForestryAlleles.SAPLINGS_AVERAGE",
	("Saplings", "High"): "ForestryAlleles.SAPLINGS_HIGH",
	("Saplings", "Higher"): "ForestryAlleles.SAPLINGS_HIGHER",
	("Saplings", "Highest"): "ForestryAlleles.SAPLINGS_HIGHEST",
	("Yield", "Lowest"): "ForestryAlleles.YIELD_LOWEST",
	("Yield", "Lower"): "ForestryAlleles.YIELD_LOWER",
	("Yield", "Low"): "ForestryAlleles.YIELD_LOW",
	("Yield", "Average"): "ForestryAlleles.YIELD_AVERAGE",
	("Yield", "High"): "ForestryAlleles.YIELD_HIGH",
	("Yield", "Higher"): "ForestryAlleles.YIELD_HIGHER",
	("Yield", "Highest"): "ForestryAlleles.YIELD_HIGHEST",
	("Sappiness", "Lowest"): "ForestryAlleles.SAPPINESS_LOWEST",
	("Sappiness", "Lower"): "ForestryAlleles.SAPPINESS_LOWER",
	("Sappiness", "Low"): "ForestryAlleles.SAPPINESS_LOW",
	("Sappiness", "Average"): "ForestryAlleles.SAPPINESS_AVERAGE",
	("Sappiness", "High"): "ForestryAlleles.SAPPINESS_HIGH",
	("Sappiness", "Higher"): "ForestryAlleles.SAPPINESS_HIGHER",
	("Sappiness", "Highest"): "ForestryAlleles.SAPPINESS_HIGHEST",
	("Maturation", "Slowest"): "ForestryAlleles.MATURATION_SLOWEST",
	("Maturation", "Slower"): "ForestryAlleles.MATURATION_SLOWER",
	("Maturation", "Slow"): "ForestryAlleles.MATURATION_SLOW",
	("Maturation", "Average"): "ForestryAlleles.MATURATION_AVERAGE",
	("Maturation", "Fast"): "ForestryAlleles.MATURATION_FAST",
	("Maturation", "Faster"): "ForestryAlleles.MATURATION_FASTER",
	("Maturation", "Fastest"): "ForestryAlleles.MATURATION_FASTEST",
	("Int", "Int1"): "ForestryAlleles.GIRTH_1",
	("Int", "Int2"): "ForestryAlleles.GIRTH_2",
	("Int", "Int3"): "ForestryAlleles.GIRTH_3",
}

CHROM_MAP = {
	"FRUITS": "TreeChromosomes.FRUIT",
	"FERTILITY": "TreeChromosomes.SAPLINGS",
	"HEIGHT": "TreeChromosomes.HEIGHT",
	"YIELD": "TreeChromosomes.YIELD",
	"SAPPINESS": "TreeChromosomes.SAPPINESS",
	"MATURATION": "TreeChromosomes.MATURATION",
	"GIRTH": "TreeChromosomes.GIRTH",
}

SPRITE_ID = {
	"TINY": "FRUIT_TINY",
	"SMALL": "FRUIT_SMALL",
	"AVERAGE": "FRUIT_AVERAGE",
	"LARGE": "FRUIT_LARGE",
	"LARGER": "FRUIT_LARGER",
	"PEAR": "FRUIT_PEAR",
}

POD_ENUM = {
	"BANANA": "ExtraTreesPodType.BANANA",
	"RED_BANANA": "ExtraTreesPodType.RED_BANANA",
	"PLANTAIN": "ExtraTreesPodType.PLANTAIN",
}


def path_id(rid: str) -> str:
	return rid.split(":", 1)[1]


def food_field(enum: str) -> str:
	if enum == "Blackthorn":
		return "BLACKTHORN"
	return enum


def food_serialized(enum: str, rid: str) -> str:
	return path_id(rid)


def java_const(name: str) -> str:
	s = re.sub(r"(?<!^)(?=[A-Z])", "_", name).upper()
	s = s.replace("__", "_")
	return s


def parse_hex(s: str) -> str:
	s = s.lower().replace("0x", "")
	return "0x" + s


def wood_expr(wood: dict) -> str:
	kind = wood["kind"]
	enum = wood.get("enum")
	if kind == "shrub_log":
		return "ExtraTreeWoodType.SHRUB"
	if kind == "et_log":
		if enum in ET_LOG_OVERLAP:
			return ET_LOG_OVERLAP[enum]
		if enum not in ET_LOG_MAP:
			raise KeyError(f"Unknown et_log wood: {enum}")
		return ET_LOG_MAP[enum]
	if kind == "forestry_or_vanilla":
		if enum not in FORESTRY_OR_VANILLA:
			raise KeyError(f"Unknown forestry_or_vanilla wood: {enum}")
		return FORESTRY_OR_VANILLA[enum]
	raise KeyError(f"Unknown wood kind: {kind}")


def fruit_field(enum: str) -> str:
	return java_const(enum)


def species_field(enum: str) -> str:
	return java_const(enum)


def load_json(path: Path):
	return json.loads(path.read_text())


def generate_food_enum(foods: list[dict]) -> str:
	entries = []
	for food in foods:
		if food["enum"] == "PAPAYIMAR":
			continue
		field = food_field(food["enum"])
		ser = food_serialized(food["enum"], food["reforestry_id"])
		hunger = food["hunger"]
		sat = round(hunger * 0.1, 2)
		entries.append(f'\t{field}("{ser}", {hunger}, {sat}f)')
	body = ",\n".join(entries) + ";"
	return f"""package com.leon1236.reforestry.extratrees.items;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumExtraTreesFood implements IItemSubtype {{
{body}

	public static final EnumExtraTreesFood[] VALUES = values();

	private final String serializedName;
	public final int nutrition;
	public final float saturationModifier;

	EnumExtraTreesFood(String serializedName, int nutrition, float saturationModifier) {{
		this.serializedName = serializedName;
		this.nutrition = nutrition;
		this.saturationModifier = saturationModifier;
	}}

	@Override
	public String getSerializedName() {{
		return serializedName;
	}}
}}
"""


def generate_fruits(fruits_data: dict, foods_by_enum: dict) -> str:
	lines = []
	fields = []
	for fruit in fruits_data["fruits"]:
		if not fruit.get("register"):
			continue
		field = fruit_field(fruit["enum"])
		fields.append(field)
		fid = path_id(fruit["reforestry_id"])
		products = []
		for p in fruit.get("products") or []:
			item = p["item"]
			chance = p["chance"]
			count = int(item.get("count", 1))
			if item["kind"] == "et_food":
				ff = food_field(item["enum"])
				expr = f"ExtraTreesItems.FOODS.item(EnumExtraTreesFood.{ff})"
			elif item["kind"] == "minecraft":
				name = item["item"].split(":")[1].upper()
				expr = f"Items.{name}"
			else:
				raise KeyError(item)
			for _ in range(count):
				products.append(f"new IFruit.Product({expr}, {chance}f)")
		prod_list = "List.of(" + ", ".join(products) + ")" if products else "List.of()"
		if fruit["kind"] == "ripening":
			rip = fruit["ripening"]
			sprite = SPRITE_ID[rip["sprite"]]
			ripe = parse_hex(rip["ripe"])
			unripe = parse_hex(rip["unripe"])
			time = rip["time"]
			lines.append(
				f"\tpublic static final IFruit {field} = new RipeningFruit(ReForestry.id(\"{fid}\"), true, {time}, {sprite},\n"
				f"\t\t\t{ripe}, {unripe}, {prod_list});"
			)
		elif fruit["kind"] == "pod":
			pod = POD_ENUM[fruit["pod"]]
			lines.append(
				f"\tpublic static final IFruit {field} = new ExtraTreesPodFruit(ReForestry.id(\"{fid}\"), true, {pod},\n"
				f"\t\t\t{prod_list});"
			)
		else:
			raise KeyError(fruit["kind"])

	all_list = ", ".join(fields)
	return f"""package com.leon1236.reforestry.extratrees.genetics;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.arboriculture.genetics.RipeningFruit;
import com.leon1236.reforestry.extratrees.blocks.ExtraTreesPodType;
import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;
import com.leon1236.reforestry.extratrees.items.EnumExtraTreesFood;

public final class ExtraTreesFruits {{
	private static final Identifier FRUIT_TINY = ReForestry.id("block/fruit/tiny");
	private static final Identifier FRUIT_SMALL = ReForestry.id("block/fruit/small");
	private static final Identifier FRUIT_AVERAGE = ReForestry.id("block/fruit/average");
	private static final Identifier FRUIT_LARGE = ReForestry.id("block/fruit/large");
	private static final Identifier FRUIT_LARGER = ReForestry.id("block/fruit/larger");
	private static final Identifier FRUIT_PEAR = ReForestry.id("block/fruit/pear");

{chr(10).join(lines)}

	public static final List<IFruit> ALL = List.of({all_list});

	private ExtraTreesFruits() {{
	}}

	public static void register(IArboricultureRegistration registration) {{
		for (IFruit fruit : ALL) {{
			registration.registerFruit(fruit.id(), fruit);
		}}
	}}
}}
"""


def genome_fruit_expr(g: dict) -> str:
	rid = g["reforestry_id"]
	if rid in REUSE_FRUITS:
		return REUSE_FRUITS[rid]
	return f"ExtraTreesFruits.{fruit_field(g['enum'])}"


def genome_lines(genome: list[dict]) -> list[str]:
	out = []
	for g in genome:
		chrom = g["chromosome"]
		chrom_java = CHROM_MAP[chrom]
		if g["kind"] == "et_fruit":
			fruit = genome_fruit_expr(g)
			out.append(
				f"\t\t\t\t\tgenome.set({chrom_java}, AlleleManager.INSTANCE.registryAllele({fruit}, {fruit}.isDominant()));"
			)
		elif g["kind"] == "forestry_allele":
			key = (g["group"], g["value"])
			if key not in ALLELE_MAP:
				raise KeyError(f"Unknown allele {key}")
			out.append(f"\t\t\t\t\tgenome.set({chrom_java}, {ALLELE_MAP[key]});")
		else:
			raise KeyError(g["kind"])
	return out


def generate_species(species_data: dict, mutations_data: dict) -> str:
	species_list = [s for s in species_data["species"] if s["action"] in REGISTER_ACTIONS]
	mutations_by_result: dict[str, list] = {}
	ce_mutations: list = []
	for m in mutations_data["mutations"]:
		result_id = m["result"]["reforestry_id"]
		action = m["result"].get("action")
		if action == "skip_reuse" or m.get("result_skipped"):
			ce_mutations.append(m)
		else:
			mutations_by_result.setdefault(result_id, []).append(m)

	blocks = []
	for sp in species_list:
		sid = path_id(sp["reforestry_id"])
		color = parse_hex(sp["leaf_color"])
		wood = wood_expr(sp["wood"])
		g_lines = genome_lines(sp.get("genome") or [])
		genome_block = ""
		if g_lines:
			genome_block = (
				"\n\t\t\t\t.setGenome(genome -> {\n"
				+ "\n".join(g_lines)
				+ "\n\t\t\t\t})"
			)
		muts = mutations_by_result.get(sp["reforestry_id"], [])
		mut_block = ""
		if muts:
			mut_lines = []
			for m in muts:
				p0 = path_id(m["parent0"]["reforestry_id"])
				p1 = path_id(m["parent1"]["reforestry_id"])
				chance = float(m["chance"])
				line = f"\t\t\t\t\tmutations.add(ReForestry.id(\"{p0}\"), ReForestry.id(\"{p1}\"), {chance}f)"
				if m.get("height") is not None:
					line += f"\n\t\t\t\t\t\t\t.addMutationCondition(new MutationConditionMinHeight({int(m['height'])}))"
				line += ";"
				mut_lines.append(line)
			mut_block = (
				"\n\t\t\t\t.addMutations(mutations -> {\n"
				+ "\n".join(mut_lines)
				+ "\n\t\t\t\t})"
			)
		blocks.append(
			f"""\t\tregistration.registerSpecies(ReForestry.id("{sid}"), "{sp['genus']}", "{sp['binomial']}", true, {color}, {wood})
\t\t\t\t.setAuthority("Binnie")
\t\t\t\t.setGenerator(new ExtraTreesTreeGenerator({wood})){genome_block}{mut_block};"""
		)

	ce_blocks = []
	by_ce: dict[str, list] = {}
	for m in ce_mutations:
		by_ce.setdefault(m["result"]["reforestry_id"], []).append(m)
	for rid, muts in by_ce.items():
		sid = path_id(rid)
		mut_lines = []
		for m in muts:
			p0 = path_id(m["parent0"]["reforestry_id"])
			p1 = path_id(m["parent1"]["reforestry_id"])
			chance = float(m["chance"])
			line = f"\t\t\t\tmutations.add(ReForestry.id(\"{p0}\"), ReForestry.id(\"{p1}\"), {chance}f)"
			if m.get("height") is not None:
				line += f"\n\t\t\t\t\t\t.addMutationCondition(new MutationConditionMinHeight({int(m['height'])}))"
			line += ";"
			mut_lines.append(line)
		ce_blocks.append(
			f"""\t\tregistration.modifySpecies(ReForestry.id("{sid}"), species -> species.addMutations(mutations -> {{
{chr(10).join(mut_lines)}
\t\t\t\t}}));"""
		)

	return f"""package com.leon1236.reforestry.extratrees.genetics;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.VanillaWoodType;
import com.leon1236.reforestry.arboriculture.genetics.DefaultFruits;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;

public final class ExtraTreesTreeSpecies {{
	private ExtraTreesTreeSpecies() {{
	}}

	public static void register(IArboricultureRegistration registration) {{
{chr(10).join(blocks)}

{chr(10).join(ce_blocks)}
	}}
}}
"""


def parse_binnie_lang(path: Path) -> dict[str, str]:
	out = {}
	if not path.is_file():
		return out
	for line in path.read_text(errors="replace").splitlines():
		line = line.strip()
		if not line or line.startswith("#") or "=" not in line:
			continue
		k, v = line.split("=", 1)
		out[k.strip()] = v.strip()
	return out


def update_lang(species_data: dict, foods: list[dict], fruits_data: dict) -> None:
	lang = json.loads(LANG_PATH.read_text())
	binnie = parse_binnie_lang(BINNIE_LANG)
	for food in foods:
		if food["enum"] == "PAPAYIMAR":
			continue
		key = f"item.reforestry.{path_id(food['reforestry_id'])}"
		bkey = f"extratrees.item.food.{food['binnie_model']}"
		lang[key] = binnie.get(bkey, food["binnie_model"].replace("_", " ").title())
	for fruit in fruits_data["fruits"]:
		if not fruit.get("register"):
			continue
		key = f"allele.reforestry.fruits.{path_id(fruit['reforestry_id'])}"
		name = fruit["binnie_name"].replace("_", " ").title()
		lang[key] = name
		lang[key + ".desc"] = name
	for sp in species_data["species"]:
		if sp["action"] not in REGISTER_ACTIONS:
			continue
		sid = path_id(sp["reforestry_id"])
		binnie_uid = sp["binnie_uid"].lower()
		bkey = f"extratrees.species.{binnie_uid}.name"
		name = binnie.get(bkey, sp["enum"])
		# Binnie keys often drop underscores
		if bkey not in binnie:
			compact = sp["enum"].lower()
			bkey2 = f"extratrees.species.{compact}.name"
			name = binnie.get(bkey2, re.sub(r"(?<!^)(?=[A-Z])", " ", sp["enum"]))
		lang[f"allele.reforestry.tree_species.{sid}"] = name
		lang[f"allele.reforestry.tree_species.{sid}.desc"] = f"{name}.|Extra Trees"
	for pod in ("banana", "red_banana", "plantain"):
		lang[f"block.reforestry.pods_{pod}"] = pod.replace("_", " ").title() + " Pod"
	LANG_PATH.write_text(json.dumps(lang, indent=2, ensure_ascii=False) + "\n")


def copy_assets(foods: list[dict]) -> None:
	item_tex = ROOT / "src/main/resources/assets/reforestry/textures/item"
	block_tex = ROOT / "src/main/resources/assets/reforestry/textures/block"
	fruit_dir = block_tex / "fruit"
	pods_dir = block_tex / "pods"
	models_item = ROOT / "src/main/resources/assets/reforestry/models/item"
	items_def = ROOT / "src/main/resources/assets/reforestry/items"
	fruit_dir.mkdir(parents=True, exist_ok=True)
	pods_dir.mkdir(parents=True, exist_ok=True)
	item_tex.mkdir(parents=True, exist_ok=True)

	for name in ("tiny", "small", "average", "large", "larger", "pear"):
		src = BINNIE_FRUIT_TEX / f"{name}.png"
		if src.is_file():
			shutil.copy2(src, fruit_dir / f"{name}.png")

	for pod, binnie in (
		("banana", "banana"),
		("red_banana", "redbanana"),
		("plantain", "plantain"),
	):
		for age in (0, 1, 2):
			src = BINNIE_POD_TEX / f"{binnie}.{age}.png"
			if src.is_file():
				shutil.copy2(src, pods_dir / f"{pod}.{age}.png")
		bs_dir = ROOT / "src/main/resources/assets/reforestry/blockstates"
		bs_dir.mkdir(parents=True, exist_ok=True)
		bs = {"variants": {}}
		for age in (0, 1, 2):
			for facing, rot in (("south", None), ("west", 90), ("north", 180), ("east", 270)):
				key = f"age={age},facing={facing}"
				entry = {"model": f"reforestry:block/pods/{pod}_{age}"}
				if rot is not None:
					entry["y"] = rot
				bs["variants"][key] = entry
		(bs_dir / f"pods_{pod}.json").write_text(json.dumps(bs, indent=2) + "\n")
		model_dir = ROOT / "src/main/resources/assets/reforestry/models/block/pods"
		model_dir.mkdir(parents=True, exist_ok=True)
		for age in (0, 1, 2):
			(model_dir / f"{pod}_{age}.json").write_text(json.dumps({
				"parent": f"block/cocoa_stage{age}",
				"textures": {
					"particle": f"reforestry:block/pods/{pod}.{age}",
					"cocoa": f"reforestry:block/pods/{pod}.{age}",
				},
			}, indent=2) + "\n")
		(models_item / f"pods_{pod}.json").write_text(json.dumps({
			"parent": f"reforestry:block/pods/{pod}_2",
		}, indent=2) + "\n")
		(items_def / f"pods_{pod}.json").write_text(json.dumps({
			"model": {"type": "minecraft:model", "model": f"reforestry:item/pods_{pod}"}
		}, indent=2) + "\n")

	for food in foods:
		if food["enum"] == "PAPAYIMAR":
			continue
		fid = path_id(food["reforestry_id"])
		model_name = food["binnie_model"]
		src = BINNIE_FOOD_TEX / f"{model_name}.png"
		# some foods have 001 suffix in binnie
		if not src.is_file():
			alt = BINNIE_FOOD_TEX / f"{model_name}001.png"
			src = alt if alt.is_file() else src
		if src.is_file():
			shutil.copy2(src, item_tex / f"{fid}.png")
		(models_item / f"{fid}.json").write_text(json.dumps({
			"parent": "minecraft:item/generated",
			"textures": {"layer0": f"reforestry:item/{fid}"}
		}, indent=2) + "\n")
		(items_def / f"{fid}.json").write_text(json.dumps({
			"model": {"type": "minecraft:model", "model": f"reforestry:item/{fid}"}
		}, indent=2) + "\n")


def main() -> int:
	ap = argparse.ArgumentParser()
	ap.add_argument("--apply", action="store_true")
	ap.add_argument("--assets", action="store_true")
	ap.add_argument("--lang", action="store_true")
	args = ap.parse_args()

	fruits_data = load_json(FRUITS_JSON)
	species_data = load_json(SPECIES_JSON)
	mutations_data = load_json(MUTATIONS_JSON)
	foods_data = load_json(FOODS_JSON)

	new_fruits = [f for f in fruits_data["fruits"] if f.get("register")]
	species = [s for s in species_data["species"] if s["action"] in REGISTER_ACTIONS]
	foods = [f for f in foods_data["foods"] if f["enum"] != "PAPAYIMAR"]
	print(f"fruits register={len(new_fruits)} reuse={fruits_data.get('reuse_count')}")
	print(f"species register={len(species)} (skip {species_data.get('binomial_skip_count')})")
	print(f"mutations={mutations_data.get('count')} foods={len(foods)}")

	if not args.apply:
		return 0

	foods_by_enum = {f["enum"]: f for f in foods_data["foods"]}
	OUT_FOOD.parent.mkdir(parents=True, exist_ok=True)
	OUT_FRUITS.parent.mkdir(parents=True, exist_ok=True)
	OUT_FOOD.write_text(generate_food_enum(foods_data["foods"]))
	OUT_FRUITS.write_text(generate_fruits(fruits_data, foods_by_enum))
	OUT_SPECIES.write_text(generate_species(species_data, mutations_data))
	print(f"wrote {OUT_FOOD.relative_to(ROOT)}")
	print(f"wrote {OUT_FRUITS.relative_to(ROOT)}")
	print(f"wrote {OUT_SPECIES.relative_to(ROOT)}")

	if args.lang:
		update_lang(species_data, foods_data["foods"], fruits_data)
		print(f"updated {LANG_PATH.relative_to(ROOT)}")
	if args.assets:
		copy_assets(foods_data["foods"])
		print("copied food/fruit/pod assets")
	return 0


if __name__ == "__main__":
	sys.exit(main())
