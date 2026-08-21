#!/usr/bin/env python3
"""Generate Extra Trees ET3 FeatureTree ports from Binnie WorldGen* classes.

Usage:
  python3 tools/generate_extra_trees_ET3.py --apply
Without --apply: dry-run summary only.
"""
from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BINNIE_GEN = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/java/binnie/extratrees/gen"
BINNIE_DEF = ROOT / "MarkDown_Maker/github_clone/ACGaming-Binnie/extratrees/src/main/java/binnie/extratrees/genetics/ETTreeDefinition.java"
SPECIES_JSON = ROOT / "queries/extra-trees-extract/species.json"
OUT_DIR = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/worldgen"
SPECIES_JAVA = ROOT / "src/main/java/com/leon1236/reforestry/extratrees/genetics/ExtraTreesTreeSpecies.java"

REGISTER_ACTIONS = frozenset({"register", "id_collision_remap", "keep_acorn_oak"})

CE_REUSE = {
	"WorldGenPlum": ("FeaturePlum", "com.leon1236.reforestry.arboriculture.worldgen.FeaturePlum"),
	"WorldGenLemon": ("FeatureLemon", "com.leon1236.reforestry.arboriculture.worldgen.FeatureLemon"),
}

WORLDGEN_TO_CLASS = {
	"BinnieWorldGenTree": "FeaturePinkIvory",
	"WorldGenAlder.CommonAlder": "FeatureCommonAlder",
	"WorldGenApple.OrchardApple": "FeatureOrchardApple",
	"WorldGenApple.SweetCrabapple": "FeatureSweetCrabapple",
	"WorldGenApple.FloweringCrabapple": "FeatureFloweringCrabapple",
	"WorldGenApple.PrairieCrabapple": "FeaturePrairieCrabapple",
	"WorldGenAsh.CommonAsh": "FeatureCommonAsh",
	"WorldGenBanana": "FeatureBanana",
	"WorldGenBeech.CopperBeech": "FeatureCopperBeech",
	"WorldGenConifer.WesternHemlock": "FeatureWesternHemlock",
	"WorldGenConifer.Cypress": "FeatureCypress",
	"WorldGenConifer.Yew": "FeatureYew",
	"WorldGenConifer.Cedar": "FeatureCedar",
	"WorldGenConifer.LoblollyPine": "FeatureLoblollyPine",
	"WorldGenEucalyptus.SwampGum": "FeatureSwampGum",
	"WorldGenEucalyptus.RoseGum": "FeatureRoseGum",
	"WorldGenEucalyptus.RainbowGum": "FeatureRainbowGum",
	"WorldGenFir.DouglasFir": "FeatureDouglasFir",
	"WorldGenFir.SilverFir": "FeatureSilverFir",
	"WorldGenHolly.Holly": "FeatureHolly",
	"WorldGenJungle.Brazilwood": "FeatureBrazilwood",
	"WorldGenJungle.Logwood": "FeatureLogwood",
	"WorldGenJungle.Rosewood": "FeatureRosewood",
	"WorldGenJungle.Purpleheart": "FeaturePurpleheart",
	"WorldGenJungle.OsangeOrange": "FeatureOsangeOrange",
	"WorldGenJungle.OldFustic": "FeatureOldFustic",
	"WorldGenJungle.Coffee": "FeatureCoffee",
	"WorldGenJungle.BrazilNut": "FeatureBrazilNut",
	"WorldGenLazy.Tree": "FeatureLazyTree",
	"WorldGenMaple.RedMaple": "FeatureRedMaple",
	"WorldGenPoplar.Aspen": "FeatureAspen",
	"WorldGenShrub.Shrub": "FeatureShrub",
	"WorldGenSorbus.Whitebeam": "FeatureWhitebeam",
	"WorldGenSorbus.Rowan": "FeatureRowan",
	"WorldGenTree2.Sweetgum": "FeatureSweetgum",
	"WorldGenTree2.Locust": "FeatureLocust",
	"WorldGenTree2.Iroko": "FeatureIroko",
	"WorldGenTree2.Box": "FeatureBox",
	"WorldGenTree2.Clove": "FeatureClove",
	"WorldGenTree3.Hazel": "FeatureHazel",
	"WorldGenTree3.Sycamore": "FeatureSycamore",
	"WorldGenTree3.Hawthorn": "FeatureHawthorn",
	"WorldGenTree3.Pecan": "FeaturePecan",
	"WorldGenTree3.Elm": "FeatureEtElm",
	"WorldGenTree3.Elder": "FeatureElder",
	"WorldGenTree3.Hornbeam": "FeatureHornbeam",
	"WorldGenTree3.Sallow": "FeatureSallow",
	"WorldGenTree3.AcornOak": "FeatureAcornOak",
	"WorldGenTropical.Mango": "FeatureMango",
	"WorldGenWalnut.Butternut": "FeatureButternut",
}

RARITY_BY_ENUM = {
	"WildCherry": 0.0015,
	"Blackcurrant": 0.0025,
	"Redcurrant": 0.0025,
	"Raspberry": 0.0025,
	"Blueberry": 0.0025,
	"Cranberry": 0.0025,
	"Juniper": 0.0025,
	"GoldenRaspberry": 0.0025,
}


def parse_heights(class_body: str) -> tuple[int, int]:
	m = re.search(r"super\(\s*tree\s*,\s*(\d+)\s*,\s*(\d+)\s*\)", class_body)
	if m:
		return int(m.group(1)), int(m.group(2))
	m = re.search(r"super\(\s*tree\s*\)", class_body)
	if m:
		return 5, 3
	return 5, 2


def extract_class_body(file_text: str, class_name: str) -> str | None:
	pattern = rf"(?:public static class|public class) {re.escape(class_name)}\b[^{{]*\{{"
	m = re.search(pattern, file_text)
	if not m:
		return None
	start = m.end() - 1
	depth = 0
	for i in range(start, len(file_text)):
		if file_text[i] == "{":
			depth += 1
		elif file_text[i] == "}":
			depth -= 1
			if depth == 0:
				return file_text[start + 1 : i]
	return None


def extract_method(class_body: str, method_name: str) -> str | None:
	pattern = rf"protected void {method_name}\s*\([^)]*\)\s*\{{"
	m = re.search(pattern, class_body)
	if not m:
		return None
	start = m.end() - 1
	depth = 0
	for i in range(start, len(class_body)):
		if class_body[i] == "{":
			depth += 1
		elif class_body[i] == "}":
			depth -= 1
			if depth == 0:
				return class_body[start + 1 : i]
	return None


def _split_args(argstr: str) -> list[str]:
	args: list[str] = []
	depth = 0
	start = 0
	for i, ch in enumerate(argstr):
		if ch in "({[<":
			depth += 1
		elif ch in ")}]>":
			depth -= 1
		elif ch == "," and depth == 0:
			args.append(argstr[start:i].strip())
			start = i + 1
	args.append(argstr[start:].strip())
	return args


def _replace_calls(text: str, method: str, replacer) -> str:
	needle = f"this.{method}("
	out: list[str] = []
	i = 0
	while True:
		j = text.find(needle, i)
		if j < 0:
			out.append(text[i:])
			break
		out.append(text[i:j])
		paren = j + len(needle) - 1
		depth = 0
		k = paren
		while k < len(text):
			if text[k] == "(":
				depth += 1
			elif text[k] == ")":
				depth -= 1
				if depth == 0:
					break
			k += 1
		call_args = text[paren + 1 : k]
		out.append(replacer(call_args))
		i = k + 1
	return "".join(out)


def translate_leaves(body: str) -> str:
	text = body

	def cyl(args: str) -> str:
		parts = _split_args(args)
		if len(parts) != 5 or "new Vector" not in parts[0]:
			return f"this.generateCylinder({args})"
		vec = parts[0]
		inner = vec[vec.find("(") + 1 : vec.rfind(")")]
		xyz = _split_args(inner)
		if len(xyz) != 3:
			return f"this.generateCylinder({args})"
		return (
			f"generateCylinder(level, rand, leaf, contour, startPos, "
			f"{xyz[0]}, {xyz[1]}, {xyz[2]}, {parts[1]})"
		)

	def sph(args: str) -> str:
		parts = _split_args(args)
		if len(parts) != 4 or "new Vector" not in parts[0]:
			return f"this.generateSphere({args})"
		vec = parts[0]
		inner = vec[vec.find("(") + 1 : vec.rfind(")")]
		xyz = _split_args(inner)
		if len(xyz) != 3:
			return f"this.generateSphere({args})"
		return (
			f"generateSphere(level, leaf, contour, startPos, "
			f"{xyz[0]}, {xyz[1]}, {xyz[2]}, {parts[1]})"
		)

	text = _replace_calls(text, "generateCylinder", cyl)
	text = _replace_calls(text, "generateSphere", sph)
	text = re.sub(r"WorldGenUtils\.randBetween\(\s*this\.rand\s*,", "randBetween(rand,", text)
	text = re.sub(r"WorldGenUtils\.randBetween\(\s*rand\s*,", "randBetween(rand,", text)
	text = re.sub(r"this\.randBetween\(", "randBetween(rand, ", text)
	text = re.sub(r"this\.rand\b", "rand", text)
	text = re.sub(r"(?<!this\.)(?<![\w.])girth\b", "this.girth", text)
	text = re.sub(r"(?<!this\.)(?<![\w.])height\b", "this.height", text)
	text = re.sub(r"(?m)^\s*//.*$", "", text)
	text = re.sub(
		r"final int offset = \(this\.girth - 1\) / 2;\s*"
		r"for \(int x = 0; x < this\.girth; \+\+x\) \{\s*"
		r"for \(int y = 0; y < this\.girth; \+\+y\) \{\s*"
		r"for \(int i = 0; i < 2; \+\+i\) \{\s*"
		r"\}\s*\}\s*\}",
		"",
		text,
		flags=re.S,
	)
	lines = []
	for line in text.splitlines():
		stripped = line.lstrip("\t")
		if stripped.strip() == "":
			lines.append("")
		else:
			lines.append("\t\t" + stripped)
	return "\n".join(lines).strip("\n")


def resolve_binnie_source(worldgen: str) -> tuple[Path, str, str | None]:
	if worldgen == "BinnieWorldGenTree":
		return BINNIE_GEN / "BinnieWorldGenTree.java", "BinnieWorldGenTree", None
	if worldgen == "WorldGenBanana":
		return BINNIE_GEN / "WorldGenBanana.java", "WorldGenBanana", None
	if "." in worldgen:
		outer, inner = worldgen.split(".", 1)
		return BINNIE_GEN / f"{outer}.java", outer, inner
	raise KeyError(worldgen)


def pink_ivory_leaves() -> str:
	return """\
		float leafSpawn = this.height;
		float width = this.height * randBetween(rand, 0.35f, 0.4f);
		if (width < 1.2f) {
			width = 1.55f;
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, width - 1.0f);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, width);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn, 0.0f, width - 0.5f);"""


def feature_java(class_name: str, base: int, variation: int, leaves: str, min_height: int | None = None) -> str:
	ctor_args = f"tree, {base}, {variation}"
	if min_height is not None:
		ctor_args += f", {min_height}"
	return f"""package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class {class_name} extends FeatureBinnieTree {{
	public {class_name}(ITreeGenData tree) {{
		super({ctor_args});
	}}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {{
{leaves}
	}}
}}
"""


def shrub_java() -> str:
	return """package com.leon1236.reforestry.extratrees.worldgen;

import java.util.List;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.FeatureTree;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLog;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import com.leon1236.reforestry.core.worldgen.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureShrub extends FeatureTree {
	public FeatureShrub(ITreeGenData tree) {
		super(tree, 1, 1, 1);
	}

	@Override
	public void generateTrunk(LevelAccessor level, List<BlockPos> logOrigins, List<BlockPos> branchCoords, RandomSource rand, TreeBlockTypeLog wood, BlockPos startPos) {
		FeatureHelper.generateTreeTrunk(level, logOrigins, rand, wood, startPos, this.height, this.girth, 0, 0, null, 0);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height;
		FeatureHelper.generateCylinderFromTreeStartPos(level, leaf, startPos.offset(0, (int) leafSpawn--, 0), this.girth, this.girth, 1, FeatureHelper.EnumReplaceMode.SOFT, contour);
		FeatureHelper.generateCylinderFromTreeStartPos(level, leaf, startPos.offset(0, (int) leafSpawn--, 0), this.girth, this.girth + 1.0f, 1, FeatureHelper.EnumReplaceMode.SOFT, contour);
		int i = 0;
		while (leafSpawn >= 0.0f) {
			FeatureHelper.generateCylinderFromTreeStartPos(level, leaf, startPos.offset(0, (int) leafSpawn--, 0), this.girth, this.girth + 2.0f + i, 1, FeatureHelper.EnumReplaceMode.SOFT, contour);
			i++;
		}
	}
}
"""


def build_feature_sources(needed: set[str]) -> dict[str, str]:
	out: dict[str, str] = {}
	for worldgen in sorted(needed):
		if worldgen in CE_REUSE:
			continue
		class_name = WORLDGEN_TO_CLASS[worldgen]
		if worldgen == "WorldGenShrub.Shrub":
			out[class_name] = shrub_java()
			continue
		if worldgen == "BinnieWorldGenTree":
			out[class_name] = feature_java(class_name, 5, 3, pink_ivory_leaves())
			continue
		path, outer, inner = resolve_binnie_source(worldgen)
		text = path.read_text()
		target = inner or outer
		body = extract_class_body(text, target)
		if body is None and worldgen == "WorldGenBeech.CopperBeech":
			body = extract_class_body(text, "CommonBeech")
		if body is None:
			raise RuntimeError(f"Cannot find class body for {worldgen} in {path}")
		base, variation = parse_heights(body)
		if worldgen == "WorldGenBeech.CopperBeech":
			parent = extract_class_body(text, "CommonBeech")
			assert parent is not None
			base, variation = parse_heights(parent)
			leaves_src = extract_method(parent, "generateLeaves")
		else:
			leaves_src = extract_method(body, "generateLeaves")
		if leaves_src is None:
			if worldgen == "BinnieWorldGenTree":
				leaves = pink_ivory_leaves()
			else:
				raise RuntimeError(f"No generateLeaves for {worldgen}")
		else:
			leaves = translate_leaves(leaves_src)
			leaves = "\n".join("\t\t" + line if line.strip() else "" for line in leaves.splitlines())
		out[class_name] = feature_java(class_name, base, variation, leaves)
	return out


def patch_species(species_rows: list[dict], apply: bool) -> tuple[int, int]:
	text = SPECIES_JAVA.read_text()
	original = text
	gen_count = 0
	rarity_count = 0

	for row in species_rows:
		path = row["path"]
		worldgen = row["worldgen"]
		enum_name = row["enum"]
		id_lit = f'ReForestry.id("{path}")'

		if worldgen in CE_REUSE:
			feat, _ = CE_REUSE[worldgen]
			factory = f"{feat}::new"
			import_needed = False
		else:
			feat = WORLDGEN_TO_CLASS[worldgen]
			factory = f"{feat}::new"
			import_needed = True

		pattern = rf'(registration\.registerSpecies\({re.escape(id_lit)}[\s\S]*?)\.setGenerator\(new ExtraTreesTreeGenerator\(([^)]+)\)\)'
		m = re.search(pattern, text)
		if not m:
			raise RuntimeError(f"Cannot find setGenerator for {path}")
		wood_arg = m.group(2).strip()
		if "::" in wood_arg or "Feature" in wood_arg:
			new_gen = f".setGenerator(new ExtraTreesTreeGenerator({factory}, {wood_arg.split(',')[-1].strip()}))"
			if "Feature" in wood_arg and "::" in wood_arg:
				pass
			else:
				new_gen = f".setGenerator(new ExtraTreesTreeGenerator({factory}, {wood_arg}))"
		else:
			new_gen = f".setGenerator(new ExtraTreesTreeGenerator({factory}, {wood_arg}))"

		old = m.group(0)
		replacement = m.group(1) + new_gen
		if old != replacement:
			text = text.replace(old, replacement, 1)
			gen_count += 1

		rarity = RARITY_BY_ENUM.get(enum_name)
		if rarity is not None:
			block_pat = rf'registration\.registerSpecies\({re.escape(id_lit)}[\s\S]*?(?=registration\.registerSpecies|\Z)'
			bm = re.search(block_pat, text)
			if not bm:
				raise RuntimeError(f"Cannot find species block for rarity on {path}")
			if ".setRarity(" not in bm.group(0):
				auth_pat = rf'(registration\.registerSpecies\({re.escape(id_lit)}[\s\S]*?\.setAuthority\("Binnie"\))'
				am = re.search(auth_pat, text)
				if not am:
					raise RuntimeError(f"Cannot find authority for rarity on {path}")
				text = text.replace(am.group(0), am.group(0) + f"\n\t\t\t\t.setRarity({rarity}f)", 1)
				rarity_count += 1

	imports = [
		"import com.leon1236.reforestry.arboriculture.worldgen.FeatureLemon;",
		"import com.leon1236.reforestry.arboriculture.worldgen.FeaturePlum;",
	]
	for wg, cls in sorted(WORLDGEN_TO_CLASS.items()):
		if any(r["worldgen"] == wg for r in species_rows):
			imports.append(f"import com.leon1236.reforestry.extratrees.worldgen.{cls};")

	for imp in imports:
		if imp not in text:
			text = text.replace(
				"import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;",
				"import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;\n" + imp,
			)

	if apply and text != original:
		SPECIES_JAVA.write_text(text)
	return gen_count, rarity_count


def main() -> int:
	parser = argparse.ArgumentParser()
	parser.add_argument("--apply", action="store_true")
	args = parser.parse_args()

	data = json.loads(SPECIES_JSON.read_text())
	species = [s for s in data["species"] if s["action"] in REGISTER_ACTIONS]
	needed = {s["worldgen"] for s in species}
	missing = needed - set(WORLDGEN_TO_CLASS) - set(CE_REUSE)
	if missing:
		print("Missing worldgen mappings:", sorted(missing), file=sys.stderr)
		return 1

	features = build_feature_sources(needed)
	print(f"species={len(species)} unique_worldgen={len(needed)} feature_files={len(features)} ce_reuse={len(needed & set(CE_REUSE))}")

	if args.apply:
		OUT_DIR.mkdir(parents=True, exist_ok=True)
		for name, source in features.items():
			(OUT_DIR / f"{name}.java").write_text(source)
			print(f"wrote {name}.java")
		gen_count, rarity_count = patch_species(species, apply=True)
		print(f"patched setGenerator={gen_count} setRarity={rarity_count}")
	else:
		gen_count, rarity_count = patch_species(species, apply=False)
		print(f"dry-run would patch setGenerator={gen_count} setRarity={rarity_count}")
		for name in sorted(features):
			print(f"  feature {name}")
	return 0


if __name__ == "__main__":
	raise SystemExit(main())
