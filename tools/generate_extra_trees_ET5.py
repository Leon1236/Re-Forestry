#!/usr/bin/env python3
"""Generate Extra Trees ET5 fluids, misc items, hops assets, tags, recipes from extract.

Usage:
  python3 tools/generate_extra_trees_ET5.py --apply
  python3 tools/generate_extra_trees_ET5.py --apply --assets --lang
Without --apply: dry-run summary only.
"""
from __future__ import annotations

import argparse
import json
import re
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
EXTRACT = ROOT / "queries/extra-trees-extract"
FLUIDS_JSON = EXTRACT / "fluids.json"
FOODS_JSON = EXTRACT / "foods.json"

BINNIE = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie"
BINNIE_ET = BINNIE / "extratrees"
BINNIE_FOOD = BINNIE_ET / "src/main/java/binnie/extratrees/items/Food.java"
BINNIE_MODULE_CORE = BINNIE_ET / "src/main/java/binnie/extratrees/modules/ModuleCore.java"
BINNIE_MODULE_ALCOHOL = BINNIE_ET / "src/main/java/binnie/extratrees/modules/ModuleAlcohol.java"
BINNIE_ALCOHOL = BINNIE_ET / "src/main/java/binnie/extratrees/liquid/Alcohol.java"
BINNIE_LANG = BINNIE_ET / "src/main/resources/assets/extratrees/lang/en_US.lang"
BINNIE_MISC_TEX = BINNIE_ET / "src/main/resources/assets/extratrees/textures/items/misc"
BINNIE_HOPS_ITEM = BINNIE_ET / "src/main/resources/assets/extratrees/textures/items/hops.png"
BINNIE_HOPS_BLOCK = BINNIE_ET / "src/main/resources/assets/extratrees/textures/blocks/hops"
BINNIE_LIQUID = BINNIE_ET / "src/main/resources/assets/extratrees/textures/blocks/liquids/liquid.png"
BINNIE_LIQUID_META = BINNIE_ET / "src/main/resources/assets/extratrees/textures/blocks/liquids/liquid.png.mcmeta"

OUT_FLUIDS = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/fluids/ExtraTreesFluids.java"
OUT_RECIPES = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/recipes/ExtraTreesAlcoholRecipes.java"
LANG_PATH = ROOT / "src/main/resources/assets/reforestry/lang/en_us.json"
ASSETS = ROOT / "src/main/resources/assets/reforestry"
DATA = ROOT / "src/main/resources/data"

# Alcohol.Fruit ident "juice" collides with Forestry JUICE; gingerAle is not snake_case.
ID_REMAP = {
	"reforestry:juice": "reforestry:alcohol_fruit",
	"reforestry:gingerAle": "reforestry:ginger_ale",
}

FOOD_ENUM_TO_ID = {
	"Blackthorn": "blackthorn",
}


def fluid_path_id(reforestry_id: str) -> str:
	rid = ID_REMAP.get(reforestry_id, reforestry_id)
	return rid.split(":", 1)[1]


def parse_color(color: str) -> int:
	s = color.strip().lower()
	if s.startswith("0x"):
		return int(s, 16) & 0xFFFFFF
	return int(s) & 0xFFFFFF


def load_fluids():
	data = json.loads(FLUIDS_JSON.read_text())
	groups = []
	for key in ("juices", "alcohols", "liqueurs", "spirits", "misc", "tree_liquids"):
		for entry in data[key]:
			entry = dict(entry)
			entry["path_id"] = fluid_path_id(entry["reforestry_id"])
			entry["color_int"] = parse_color(entry["color"])
			groups.append(entry)
	return data, groups


def parse_food_crops() -> dict[str, list[str]]:
	text = BINNIE_FOOD.read_text()
	crops: dict[str, list[str]] = {}
	for m in re.finditer(
		r"(\w+)\(\d+\)\s*\{[^}]*?registerOreDictEntries\(\)\s*\{([^}]+)\}",
		text,
		re.S,
	):
		name = m.group(1)
		body = m.group(2)
		crop_names = re.findall(r'registerCrop\("([^"]+)"\)', body)
		crops[name] = ["crop" + c for c in crop_names]
	return crops


def parse_squeezes() -> list[dict]:
	text = BINNIE_MODULE_CORE.read_text()
	out = []
	for m in re.finditer(r"Food\.(\w+)\.add(Juice|Oil)\(\s*(\d+),\s*(\d+),\s*(\d+)\s*\)", text):
		food = m.group(1)
		food_id = FOOD_ENUM_TO_ID.get(food, food.lower())
		out.append(
			{
				"food_enum": food,
				"food_id": food_id,
				"kind": m.group(2).lower(),
				"time": int(m.group(3)),
				"amount": int(m.group(4)),
				"mulch": int(m.group(5)),
			}
		)
	return out


def parse_alcohol_fermentations() -> dict[str, list[str]]:
	text = BINNIE_ALCOHOL.read_text()
	result: dict[str, list[str]] = {}
	# Split by enum constants that override init
	for m in re.finditer(
		r"(\w+)\(\"[^\"]+\",[^)]+\)\s*\{[^}]*?protected void init\(\)\s*\{([^}]+)\}",
		text,
		re.S,
	):
		name = m.group(1)
		body = m.group(2)
		juices = re.findall(r"addFermentation\(Juice\.(\w+)\)", body)
		result[name] = juices
	return result


def parse_distillery() -> list[tuple[str, str, str, str]]:
	text = BINNIE_MODULE_ALCOHOL.read_text()
	rows = []
	for m in re.finditer(
		r"this\.addDistillery\(Alcohol\.(\w+),\s*Spirit\.(\w+),\s*Spirit\.(\w+),\s*Spirit\.(\w+)\)",
		text,
	):
		rows.append((m.group(1), m.group(2), m.group(3), m.group(4)))
	return rows


def load_binnie_lang() -> dict[str, str]:
	if not BINNIE_LANG.exists():
		return {}
	out = {}
	for line in BINNIE_LANG.read_text(errors="ignore").splitlines():
		if "=" not in line or line.strip().startswith("#"):
			continue
		k, _, v = line.partition("=")
		out[k.strip()] = v.strip()
	return out


def fluid_display_name(entry: dict, lang: dict[str, str]) -> str:
	ident = entry["binnie_ident"]
	group = entry["group"]
	enum = entry["enum"]
	candidates = []
	if group == "juice":
		candidates.append(f"extratrees.fluid.juice.{enum.lower()}")
	elif group == "alcohol":
		candidates.append(f"extratrees.fluid.alcohol.{enum.lower()}")
	elif group == "liqueur":
		candidates.append(f"extratrees.fluid.liqueur.{enum.lower()}")
	elif group == "spirit":
		candidates.append(f"extratrees.fluid.spirit.{enum.lower()}")
	elif group == "misc":
		candidates.append(f"extratrees.fluid.miscfluid.{enum.lower()}")
	elif group == "tree_liquid":
		candidates.append(f"extratrees.fluid.extratreeliquid.{enum.lower()}")
	# also try ident-based
	flat = ident.replace(".", "")
	for c in candidates:
		if c in lang:
			return lang[c]
	# fallback title from path
	return entry["path_id"].replace("_", " ").title()


def write_fluids_java(entries: list[dict]) -> str:
	lines = [
		"package com.leon1236.reforestry.extratrees.fluids;",
		"",
		"import java.util.Locale;",
		"",
		"import org.jetbrains.annotations.Nullable;",
		"",
		"import net.minecraft.world.item.BucketItem;",
		"import net.minecraft.world.level.Level;",
		"import net.minecraft.world.level.material.Fluid;",
		"",
		"import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;",
		"import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;",
		"import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;",
		"",
		"import com.leon1236.reforestry.ReForestry;",
		"import com.leon1236.reforestry.core.fluids.FeatureFluid;",
		"import com.leon1236.reforestry.core.fluids.ForestryFluidProperties;",
		"",
		"public enum ExtraTreesFluids {",
	]
	for i, e in enumerate(entries):
		comma = "," if i < len(entries) - 1 else ";"
		lines.append(
			f"\t{e['enum'].upper() if e['enum'][0].islower() else to_enum_name(e['enum'])}"
			f"(\"{e['path_id']}\", 0x{e['color_int']:06X}){comma}"
		)
	# Fix: enum names must be valid. Use group-aware naming for collisions across groups.
	# Rebuild properly below — this draft is replaced.
	return ""


def to_enum_name(enum: str) -> str:
	# PascalCase / already Camel → UPPER_SNAKE
	s = re.sub(r"(?<!^)(?=[A-Z])", "_", enum)
	return s.upper()


def unique_enum_names(entries: list[dict]) -> None:
	"""Assign Java enum constant names; disambiguate duplicates across groups."""
	used: set[str] = set()
	for e in entries:
		base = to_enum_name(e["enum"])
		# Juice Olive vs etc. — if collision, prefix with group
		name = base
		if name in used:
			prefix = {
				"juice": "JUICE_",
				"alcohol": "ALCOHOL_",
				"liqueur": "LIQUEUR_",
				"spirit": "SPIRIT_",
				"misc": "MISC_",
				"tree_liquid": "TREE_",
			}.get(e["group"], "")
			name = prefix + base
		if name in used:
			name = name + "_" + e["group"].upper()
		used.add(name)
		e["java_enum"] = name


def generate_fluids_java(entries: list[dict]) -> str:
	unique_enum_names(entries)
	lines = [
		"package com.leon1236.reforestry.extratrees.fluids;",
		"",
		"import org.jetbrains.annotations.Nullable;",
		"",
		"import net.minecraft.world.item.BucketItem;",
		"import net.minecraft.world.level.Level;",
		"import net.minecraft.world.level.material.Fluid;",
		"",
		"import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;",
		"import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;",
		"import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;",
		"",
		"import com.leon1236.reforestry.ReForestry;",
		"import com.leon1236.reforestry.core.fluids.FeatureFluid;",
		"import com.leon1236.reforestry.core.fluids.ForestryFluidProperties;",
		"",
		"public enum ExtraTreesFluids {",
	]
	for i, e in enumerate(entries):
		comma = "," if i < len(entries) - 1 else ";"
		lines.append(f"\t{e['java_enum']}(\"{e['path_id']}\", 0x{e['color_int']:06X}){comma}")
	lines += [
		"",
		"\tprivate final FeatureFluid feature;",
		"",
		"\tExtraTreesFluids(String pathId, int particleColor) {",
		"\t\tthis.feature = FeatureFluid.create(",
		"\t\t\t\tReForestry.id(\"extra_trees\"),",
		"\t\t\t\tpathId,",
		"\t\t\t\tForestryFluidProperties.builder().particleColor(particleColor).build());",
		"\t}",
		"",
		"\tpublic FeatureFluid getFeature() {",
		"\t\treturn this.feature;",
		"\t}",
		"",
		"\tpublic String pathId() {",
		"\t\treturn this.feature.getName();",
		"\t}",
		"",
		"\tpublic Fluid getFluid() {",
		"\t\treturn this.feature.source();",
		"\t}",
		"",
		"\tpublic Fluid getFlowing() {",
		"\t\treturn this.feature.flowing();",
		"\t}",
		"",
		"\tpublic BucketItem getBucket() {",
		"\t\treturn this.feature.bucket();",
		"\t}",
		"",
		"\tpublic boolean is(@Nullable Fluid fluid) {",
		"\t\treturn fluid != null && (getFluid() == fluid || getFlowing() == fluid);",
		"\t}",
		"",
		"\tpublic static ExtraTreesFluids byPathId(String pathId) {",
		"\t\tfor (ExtraTreesFluids fluid : values()) {",
		"\t\t\tif (fluid.pathId().equals(pathId)) {",
		"\t\t\t\treturn fluid;",
		"\t\t\t}",
		"\t\t}",
		"\t\tthrow new IllegalArgumentException(\"Unknown Extra Trees fluid: \" + pathId);",
		"\t}",
		"",
		"\tpublic static void init() {",
		"\t\tfor (ExtraTreesFluids fluid : values()) {",
		"\t\t\tForestryFluidProperties properties = fluid.feature.properties();",
		"\t\t\tFluidVariantAttributeHandler handler = new FluidVariantAttributeHandler() {",
		"\t\t\t\t@Override",
		"\t\t\t\tpublic int getTemperature(FluidVariant variant) {",
		"\t\t\t\t\treturn properties.temperature();",
		"\t\t\t\t}",
		"",
		"\t\t\t\t@Override",
		"\t\t\t\tpublic int getViscosity(FluidVariant variant, @Nullable Level level) {",
		"\t\t\t\t\treturn Math.max(1, properties.viscosity());",
		"\t\t\t\t}",
		"\t\t\t};",
		"\t\t\tFluidVariantAttributes.register(fluid.getFluid(), handler);",
		"\t\t\tFluidVariantAttributes.register(fluid.getFlowing(), handler);",
		"\t\t}",
		"\t}",
		"}",
		"",
	]
	return "\n".join(lines)


def generate_alcohol_recipes_java(
	entries: list[dict],
	squeezes: list[dict],
	crops: dict[str, list[str]],
	fermentations: dict[str, list[str]],
	distillery: list[tuple[str, str, str, str]],
) -> str:
	by_group_enum = {(e["group"], e["enum"]): e for e in entries}
	juice_by_crop = {}
	for e in entries:
		if e["group"] == "juice":
			juice_by_crop["crop" + e["squeezing"]] = e

	def juice_enum(squeezing: str) -> str:
		for e in entries:
			if e["group"] == "juice" and e["squeezing"] == squeezing:
				return e["java_enum"]
		raise KeyError(squeezing)

	press_fixed = []
	for s in squeezes:
		food_crops = crops.get(s["food_enum"], [])
		matched = [juice_by_crop[c] for c in food_crops if c in juice_by_crop]
		if not matched:
			continue
		amount = s["amount"] * (2 if s["kind"] == "oil" else 1)
		food_const = "BLACKTHORN" if s["food_enum"] == "Blackthorn" else s["food_enum"]
		for juice in matched:
			press_fixed.append(
				"\t\tFruitPressRecipeManager.addRecipe("
				f"new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.{food_const})), "
				f"ExtraTreesFluids.{juice['java_enum']}.getFluid(), {amount});"
			)
	press_fixed.append(
		f"\t\tFruitPressRecipeManager.addRecipe(new ItemStack(Items.APPLE), ExtraTreesFluids.{juice_enum('Apple')}.getFluid(), 200);"
	)
	press_fixed.append(
		f"\t\tFruitPressRecipeManager.addRecipe(new ItemStack(Items.CARROT), ExtraTreesFluids.{juice_enum('Carrot')}.getFluid(), 200);"
	)

	def alcohol_java(name: str) -> str:
		return by_group_enum[("alcohol", name)]["java_enum"]

	def spirit_java(name: str) -> str:
		return by_group_enum[("spirit", name)]["java_enum"]

	def juice_java_from_alcohol_enum(juice_name: str) -> str:
		return by_group_enum[("juice", juice_name)]["java_enum"]

	brew_lines = []
	for alcohol_name, juices in fermentations.items():
		if ("alcohol", alcohol_name) not in by_group_enum:
			continue
		out_enum = alcohol_java(alcohol_name)
		for jn in juices:
			jin = juice_java_from_alcohol_enum(jn)
			brew_lines.append(
				f"\t\tBreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.{jin}.getFluid(), ExtraTreesFluids.{out_enum}.getFluid());"
			)

	grain_lines = [
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_BARLEY, ExtraTreesFluids."
		+ alcohol_java("Ale")
		+ ".getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_BARLEY, ExtraTreesFluids."
		+ alcohol_java("Lager")
		+ ".getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.LAGER_YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_ROASTED, ExtraTreesFluids."
		+ alcohol_java("Stout")
		+ ".getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_CORN, ExtraTreesFluids."
		+ alcohol_java("CornBeer")
		+ ".getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_RYE, ExtraTreesFluids."
		+ alcohol_java("RyeBeer")
		+ ".getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_WHEAT, ExtraTreesFluids."
		+ alcohol_java("WheatBeer")
		+ ".getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_BARLEY, ExtraTreesFluids."
		+ alcohol_java("Barley")
		+ ".getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_CORN, ExtraTreesFluids."
		+ alcohol_java("Corn")
		+ ".getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_RYE, ExtraTreesFluids."
		+ alcohol_java("Rye")
		+ ".getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));",
		"\t\tBreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_WHEAT, ExtraTreesFluids."
		+ alcohol_java("Wheat")
		+ ".getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));",
	]

	dist_lines = []
	for src, s1, s2, s3 in distillery:
		dist_lines.append(
			f"\t\taddDistillery(ExtraTreesFluids.{alcohol_java(src)}, ExtraTreesFluids.{spirit_java(s1)}, "
			f"ExtraTreesFluids.{spirit_java(s2)}, ExtraTreesFluids.{spirit_java(s3)});"
		)

	return "\n".join(
		[
			"package com.leon1236.reforestry.extratrees.recipes;",
			"",
			"import net.minecraft.world.item.ItemStack;",
			"import net.minecraft.world.item.Items;",
			"import net.minecraft.world.level.material.Fluid;",
			"",
			"import com.leon1236.reforestry.core.fluids.FluidUnits;",
			"import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;",
			"import com.leon1236.reforestry.extratrees.fluids.ExtraTreesFluids;",
			"import com.leon1236.reforestry.extratrees.items.EnumExtraTreesFood;",
			"import com.leon1236.reforestry.extratrees.tags.ExtraTreesTags;",
			"",
			"public final class ExtraTreesAlcoholRecipes {",
			"\tprivate ExtraTreesAlcoholRecipes() {",
			"\t}",
			"",
			"\tpublic static void init() {",
			"\t\tregisterPress();",
			"\t\tregisterBrewery();",
			"\t\tregisterDistillery();",
			"\t}",
			"",
			"\tprivate static void registerPress() {",
			*press_fixed,
			"\t}",
			"",
			"\tprivate static void registerBrewery() {",
			*brew_lines,
			*grain_lines,
			"\t}",
			"",
			"\tprivate static void registerDistillery() {",
			*dist_lines,
			"\t}",
			"",
			"\tprivate static void addDistillery(ExtraTreesFluids source, ExtraTreesFluids single,",
			"\t\t\tExtraTreesFluids doubleDistilled, ExtraTreesFluids triple) {",
			"\t\tlong inAmount = FluidUnits.mbToDroplets(1000);",
			"\t\tlong out1 = FluidUnits.mbToDroplets(800);",
			"\t\tlong out2 = FluidUnits.mbToDroplets(400);",
			"\t\tlong out3 = FluidUnits.mbToDroplets(200);",
			"\t\tFluid in = source.getFluid();",
			"\t\tDistilleryRecipeManager.addRecipe(in, inAmount, single.getFluid(), out1, 0);",
			"\t\tDistilleryRecipeManager.addRecipe(in, inAmount, doubleDistilled.getFluid(), out2, 1);",
			"\t\tDistilleryRecipeManager.addRecipe(in, inAmount, triple.getFluid(), out3, 2);",
			"\t\tDistilleryRecipeManager.addRecipe(single.getFluid(), inAmount, doubleDistilled.getFluid(), out2, 0);",
			"\t\tDistilleryRecipeManager.addRecipe(single.getFluid(), inAmount, triple.getFluid(), out3, 1);",
			"\t\tDistilleryRecipeManager.addRecipe(doubleDistilled.getFluid(), inAmount, triple.getFluid(), out2, 0);",
			"\t}",
			"}",
			"",
		]
	)


def write_fluid_assets(entries: list[dict], apply: bool) -> int:
	count = 0
	still_src = ASSETS / "textures/block/liquid/juice_still.png"
	still_meta = ASSETS / "textures/block/liquid/juice_still.png.mcmeta"
	liquid_dst = ASSETS / "textures/block/liquid/extra_trees_liquid.png"
	liquid_meta_dst = ASSETS / "textures/block/liquid/extra_trees_liquid.png.mcmeta"
	if apply:
		if BINNIE_LIQUID.exists():
			shutil.copy2(BINNIE_LIQUID, liquid_dst)
			if BINNIE_LIQUID_META.exists():
				shutil.copy2(BINNIE_LIQUID_META, liquid_meta_dst)
		elif still_src.exists():
			shutil.copy2(still_src, liquid_dst)
			if still_meta.exists():
				shutil.copy2(still_meta, liquid_meta_dst)

	levels = {f"level={i}": {"model": None} for i in range(16)}
	for e in entries:
		pid = e["path_id"]
		model_name = f"fluid_{pid}"
		blockstate = {
			"variants": {
				f"level={i}": {"model": f"reforestry:block/{model_name}"} for i in range(16)
			}
		}
		model = {"textures": {"particle": "reforestry:block/liquid/extra_trees_liquid"}}
		bucket_model = {
			"parent": "minecraft:item/generated",
			"textures": {
				"layer0": "minecraft:item/bucket",
				"layer1": "reforestry:item/liquids/can.contents",
			},
		}
		bucket_item_def = {
			"model": {
				"type": "minecraft:model",
				"model": f"reforestry:item/bucket_{pid}",
				"tints": [
					{"type": "minecraft:constant", "value": -1},
					{
						"type": "minecraft:constant",
						"value": to_signed_argb(e["color_int"]),
					},
				],
			}
		}
		if apply:
			(ASSETS / "blockstates" / f"fluid_{pid}.json").write_text(json.dumps(blockstate, indent=2) + "\n")
			(ASSETS / "models/block" / f"fluid_{pid}.json").write_text(json.dumps(model, indent=2) + "\n")
			(ASSETS / "models/item" / f"bucket_{pid}.json").write_text(json.dumps(bucket_model, indent=2) + "\n")
			(ASSETS / "items" / f"bucket_{pid}.json").write_text(json.dumps(bucket_item_def, indent=2) + "\n")
		count += 1
	return count


def to_signed_argb(rgb: int) -> int:
	argb = 0xFF000000 | (rgb & 0xFFFFFF)
	return argb - 0x100000000 if argb >= 0x80000000 else argb


def write_misc_assets(apply: bool) -> int:
	ids = ["yeast", "yeast_lager", "grain_wheat", "grain_barley", "grain_rye", "grain_corn", "grain_roasted", "hops"]
	tex_map = {
		"yeast": BINNIE_MISC_TEX / "yeast.png",
		"yeast_lager": BINNIE_MISC_TEX / "yeast_lager.png",
		"grain_wheat": BINNIE_MISC_TEX / "grain_wheat.png",
		"grain_barley": BINNIE_MISC_TEX / "grain_barley.png",
		"grain_rye": BINNIE_MISC_TEX / "grain_rye.png",
		"grain_corn": BINNIE_MISC_TEX / "grain_corn.png",
		"grain_roasted": BINNIE_MISC_TEX / "grain_roasted.png",
		"hops": BINNIE_HOPS_ITEM,
	}
	for iid in ids:
		src = tex_map[iid]
		if apply and src.exists():
			dst = ASSETS / "textures/item" / f"{iid}.png"
			dst.parent.mkdir(parents=True, exist_ok=True)
			shutil.copy2(src, dst)
		model = {"parent": "minecraft:item/generated", "textures": {"layer0": f"reforestry:item/{iid}"}}
		item_def = {"model": {"type": "minecraft:model", "model": f"reforestry:item/{iid}"}}
		if apply:
			(ASSETS / "models/item" / f"{iid}.json").write_text(json.dumps(model, indent=2) + "\n")
			(ASSETS / "items" / f"{iid}.json").write_text(json.dumps(item_def, indent=2) + "\n")
	# hops crop block textures + models
	if apply and BINNIE_HOPS_BLOCK.exists():
		out_tex = ASSETS / "textures/block/hops"
		out_tex.mkdir(parents=True, exist_ok=True)
		for src in BINNIE_HOPS_BLOCK.glob("*.png"):
			# Prefer down stages for single-block crop
			if "_down" in src.name or src.name.startswith("hops_stage_"):
				shutil.copy2(src, out_tex / src.name.replace("_down", ""))
	# blockstate age 0-7 using down textures
	variants = {}
	for age in range(8):
		tex = f"reforestry:block/hops/hops_stage_{age}"
		model_name = f"hops_stage_{age}"
		model = {
			"parent": "minecraft:block/crop",
			"textures": {"crop": tex},
		}
		variants[f"age={age}"] = {"model": f"reforestry:block/{model_name}"}
		if apply:
			(ASSETS / "models/block" / f"{model_name}.json").write_text(json.dumps(model, indent=2) + "\n")
	if apply:
		(ASSETS / "blockstates/hops.json").write_text(
			json.dumps({"variants": variants}, indent=2) + "\n"
		)
	return len(ids)


def write_tags(crops: dict[str, list[str]], foods_data: dict, apply: bool) -> int:
	count = 0
	# Map food id → crop tags (c:crops/xxx common style + reforestry helper tags)
	tag_members: dict[str, list[str]] = {}
	for food in foods_data["foods"]:
		enum = food["enum"]
		fid = food["reforestry_id"].split(":")[1]
		for crop in crops.get(enum, []):
			# cropApple → apple
			simple = crop[4:].lower()
			# camel to snake-ish: WildCherry → wildcherry kept as wild_cherry if needed
			simple = re.sub(r"(?<!^)(?=[A-Z])", "_", crop[4:]).lower()
			tag = f"c:crops/{simple}"
			tag_members.setdefault(tag, []).append(f"reforestry:{fid}")

	# Also put foods into existing c:fruits/* when matching
	fruit_map = {
		"orange": "c:fruits/orange",
		"pear": "c:fruits/pear",
		"cherry_plum": "c:fruits/plum",
		"wild_cherry": "c:fruits/cherry",
		"sour_cherry": "c:fruits/cherry",
		"black_cherry": "c:fruits/cherry",
		"olive": "c:fruits/olive",
		"coconut": "c:fruits/coconut",
	}

	def write_tag(path: Path, values: list[str]):
		nonlocal count
		payload = {"replace": False, "values": sorted(set(values))}
		if apply:
			path.parent.mkdir(parents=True, exist_ok=True)
			# merge if exists
			if path.exists():
				old = json.loads(path.read_text())
				vals = list(old.get("values", []))
				for v in values:
					if v not in vals:
						vals.append(v)
				payload["values"] = vals
			path.write_text(json.dumps(payload, indent=2) + "\n")
		count += 1

	for tag, members in tag_members.items():
		ns, rest = tag.split(":", 1)
		kind, name = rest.split("/", 1)
		write_tag(DATA / ns / "tags/item" / kind / f"{name}.json", members)

	for fid, tag in fruit_map.items():
		ns, rest = tag.split(":", 1)
		kind, name = rest.split("/", 1)
		write_tag(DATA / ns / "tags/item" / kind / f"{name}.json", [f"reforestry:{fid}"])

	# grain / hops tags under reforestry and c
	write_tag(DATA / "reforestry/tags/item/grain_wheat.json", ["reforestry:grain_wheat"])
	write_tag(DATA / "reforestry/tags/item/grain_barley.json", ["reforestry:grain_barley"])
	write_tag(DATA / "reforestry/tags/item/grain_rye.json", ["reforestry:grain_rye"])
	write_tag(DATA / "reforestry/tags/item/grain_corn.json", ["reforestry:grain_corn"])
	write_tag(DATA / "reforestry/tags/item/grain_roasted.json", ["reforestry:grain_roasted"])
	write_tag(DATA / "reforestry/tags/item/hops.json", ["reforestry:hops"])
	write_tag(DATA / "c/tags/item/crops/hops.json", ["reforestry:hops"])
	write_tag(DATA / "c/tags/item/seeds/wheat.json", ["reforestry:grain_wheat"])
	write_tag(DATA / "c/tags/item/seeds/barley.json", ["reforestry:grain_barley"])
	write_tag(DATA / "c/tags/item/seeds/rye.json", ["reforestry:grain_rye"])
	write_tag(DATA / "c/tags/item/seeds/corn.json", ["reforestry:grain_corn"])
	return count


def write_squeezer_recipes(squeezes: list[dict], apply: bool) -> int:
	count = 0
	out_dir = DATA / "reforestry/recipe/squeezer/extra_trees"
	if apply:
		out_dir.mkdir(parents=True, exist_ok=True)
	for s in squeezes:
		fluid = "reforestry:juice" if s["kind"] == "juice" else "reforestry:seed_oil"
		chance = max(0.01, s["mulch"] / 100.0)
		recipe = {
			"type": "reforestry:squeezer",
			"time": s["time"],
			"resources": [{"item": f"reforestry:{s['food_id']}"}],
			"output": {"fluid": fluid, "amount": s["amount"]},
			"remnant": {"item": "reforestry:mulch", "count": 1},
			"chance": min(1.0, chance),
		}
		if apply:
			(out_dir / f"{s['food_id']}.json").write_text(json.dumps(recipe, indent=2) + "\n")
		count += 1
	return count


def write_crafting_recipes(apply: bool) -> int:
	recipes = {
		"yeast": {
			"type": "minecraft:crafting_shaped",
			"category": "misc",
			"key": {"b": "minecraft:bread", "m": "minecraft:brown_mushroom"},
			"pattern": [" m ", "mbm"],
			"result": {"id": "reforestry:yeast", "count": 8},
		},
		"yeast_lager": {
			"type": "minecraft:crafting_shaped",
			"category": "misc",
			"key": {"b": "minecraft:bread", "m": "minecraft:brown_mushroom"},
			"pattern": ["mbm", " m "],
			"result": {"id": "reforestry:yeast_lager", "count": 8},
		},
		"grain_wheat": {
			"type": "minecraft:crafting_shaped",
			"category": "misc",
			"key": {"s": "minecraft:wheat_seeds"},
			"pattern": [" s ", "sss", " s "],
			"result": {"id": "reforestry:grain_wheat", "count": 5},
		},
		"grain_barley": {
			"type": "minecraft:crafting_shaped",
			"category": "misc",
			"key": {"s": "reforestry:grain_wheat"},
			"pattern": [" s ", "s  ", " s "],
			"result": {"id": "reforestry:grain_barley", "count": 3},
		},
		"grain_corn": {
			"type": "minecraft:crafting_shaped",
			"category": "misc",
			"key": {"s": "reforestry:grain_wheat"},
			"pattern": [" s ", "  s", " s "],
			"result": {"id": "reforestry:grain_corn", "count": 3},
		},
		"grain_rye": {
			"type": "minecraft:crafting_shaped",
			"category": "misc",
			"key": {"s": "reforestry:grain_wheat"},
			"pattern": ["   ", "s s", " s "],
			"result": {"id": "reforestry:grain_rye", "count": 3},
		},
	}
	smelts = {
		"grain_roasted_from_wheat": {
			"type": "minecraft:smelting",
			"category": "misc",
			"ingredient": "reforestry:grain_wheat",
			"result": {"id": "reforestry:grain_roasted"},
			"experience": 0.0,
			"cookingtime": 200,
		},
		"grain_roasted_from_barley": {
			"type": "minecraft:smelting",
			"category": "misc",
			"ingredient": "reforestry:grain_barley",
			"result": {"id": "reforestry:grain_roasted"},
			"experience": 0.0,
			"cookingtime": 200,
		},
		"grain_roasted_from_rye": {
			"type": "minecraft:smelting",
			"category": "misc",
			"ingredient": "reforestry:grain_rye",
			"result": {"id": "reforestry:grain_roasted"},
			"experience": 0.0,
			"cookingtime": 200,
		},
		"grain_roasted_from_corn": {
			"type": "minecraft:smelting",
			"category": "misc",
			"ingredient": "reforestry:grain_corn",
			"result": {"id": "reforestry:grain_roasted"},
			"experience": 0.0,
			"cookingtime": 200,
		},
	}
	carpenter = {
		"wood_wax": {
			"type": "reforestry:carpenter",
			"time": 25,
			"recipe": {
				"type": "minecraft:crafting_shaped",
				"category": "misc",
				"key": {"x": "reforestry:beeswax"},
				"pattern": ["x"],
				"result": {"id": "reforestry:wood_wax", "count": 4},
				"show_notification": True,
			},
			"liquid": {"fluid": "reforestry:turpentine", "amount": 50},
		}
	}
	out = DATA / "reforestry/recipe/extra_trees"
	if apply:
		out.mkdir(parents=True, exist_ok=True)
	n = 0
	for name, recipe in {**recipes, **smelts}.items():
		if apply:
			(out / f"{name}.json").write_text(json.dumps(recipe, indent=2) + "\n")
		n += 1
	carp_dir = DATA / "reforestry/recipe/carpenter"
	if apply:
		(carp_dir / "wood_wax.json").write_text(json.dumps(carpenter["wood_wax"], indent=2) + "\n")
	n += 1
	return n


def update_lang(entries: list[dict], apply: bool) -> int:
	lang = json.loads(LANG_PATH.read_text())
	binnie = load_binnie_lang()
	added = 0
	for e in entries:
		pid = e["path_id"]
		name = fluid_display_name(e, binnie)
		k_bucket = f"item.reforestry.bucket_{pid}"
		k_fluid = f"fluid.reforestry.{pid}"
		k_block = f"block.reforestry.fluid_{pid}"
		for k, v in ((k_bucket, f"{name} Bucket"), (k_fluid, name), (k_block, name)):
			if k not in lang:
				lang[k] = v
				added += 1
	misc = {
		"item.reforestry.yeast": binnie.get("extratrees.item.yeast.name", "Yeast"),
		"item.reforestry.yeast_lager": "Lager Yeast",
		"item.reforestry.grain_wheat": binnie.get("extratrees.item.grain_wheat.name", "Wheat Grain"),
		"item.reforestry.grain_barley": binnie.get("extratrees.item.grain_barley.name", "Barley Grain"),
		"item.reforestry.grain_rye": binnie.get("extratrees.item.grain_rye.name", "Rye Grain"),
		"item.reforestry.grain_corn": binnie.get("extratrees.item.grain_corn.name", "Corn Grain"),
		"item.reforestry.grain_roasted": binnie.get("extratrees.item.grain_roasted.name", "Roasted Grain"),
		"item.reforestry.hops": binnie.get("extratrees.item.hops.name", "Hops"),
		"block.reforestry.hops": binnie.get("extratrees.item.hops.name", "Hops"),
		"extratrees.gui.distillery.level": "Lv %s",
	}
	for k, v in misc.items():
		if k not in lang or lang.get(k) == "Lager LagerYeast":
			lang[k] = v
			added += 1
	if apply:
		LANG_PATH.write_text(json.dumps(lang, indent=2, ensure_ascii=False) + "\n")
	return added


def main():
	ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
	ap.add_argument("--apply", action="store_true")
	ap.add_argument("--assets", action="store_true")
	ap.add_argument("--lang", action="store_true")
	args = ap.parse_args()

	data, entries = load_fluids()
	unique_enum_names(entries)
	crops = parse_food_crops()
	squeezes = parse_squeezes()
	fermentations = parse_alcohol_fermentations()
	distillery = parse_distillery()
	foods = json.loads(FOODS_JSON.read_text())

	fluids_java = generate_fluids_java(entries)
	recipes_java = generate_alcohol_recipes_java(entries, squeezes, crops, fermentations, distillery)

	press_count = recipes_java.count("FruitPressRecipeManager.addRecipe")
	brew_count = recipes_java.count("BreweryRecipeManager.add")
	dist_count = recipes_java.count("DistilleryRecipeManager.addRecipe")

	print(f"fluids: {len(entries)}")
	print(f"squeezer food recipes: {len(squeezes)}")
	print(f"press recipes: {press_count}")
	print(f"brewery lines: {brew_count}")
	print(f"distillery addRecipe calls: {dist_count}")
	print(f"id remaps: {ID_REMAP}")

	if not args.apply:
		print("dry-run only (pass --apply)")
		return

	OUT_FLUIDS.parent.mkdir(parents=True, exist_ok=True)
	OUT_FLUIDS.write_text(fluids_java)
	OUT_RECIPES.parent.mkdir(parents=True, exist_ok=True)
	OUT_RECIPES.write_text(recipes_java)
	print(f"wrote {OUT_FLUIDS.relative_to(ROOT)}")
	print(f"wrote {OUT_RECIPES.relative_to(ROOT)}")

	n_sq = write_squeezer_recipes(squeezes, True)
	n_craft = write_crafting_recipes(True)
	n_tags = write_tags(crops, foods, True)
	print(f"squeezer json: {n_sq}, craft/smelt/carpenter: {n_craft}, tags: {n_tags}")

	if args.assets:
		n_f = write_fluid_assets(entries, True)
		n_m = write_misc_assets(True)
		print(f"fluid assets: {n_f}, misc/hops assets: {n_m}")
	if args.lang:
		n_l = update_lang(entries, True)
		print(f"lang keys added: {n_l}")


if __name__ == "__main__":
	main()
