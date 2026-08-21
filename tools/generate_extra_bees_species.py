#!/usr/bin/env python3
"""Generate Extra Bees species + mutation Java from queries/extra-bees-*.json.

EB2a batch: hive-line parents inside barren/rocky/hostile/volcanic/shadow/aquatic/
classical (exclude INK + GLOWSTONE until dye/energetic parents exist in later EB2*).
Also emits all 34 forestry_result modifySpecies mutations.

Usage:
  python3 tools/generate_extra_bees_species.py --batch EB2a --apply
Without --apply: dry-run summary only.
"""
from __future__ import annotations

import argparse
import json
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SPECIES_JSON = ROOT / "queries/extra-bees-species.json"
MUTATIONS_JSON = ROOT / "queries/extra-bees-mutations.json"
OUT_SPECIES = ROOT / "src/main/java/com/leon1236/reforestry/extra_bees/genetics/ExtraBeesBeeSpecies.java"

EB2A_BRANCHES = frozenset({
	"BARREN", "ROCKY", "HOSTILE", "VOLCANIC", "SHADOW", "AQUATIC", "CLASSICAL",
})
EB2A_DEFER = frozenset({"INK", "GLOWSTONE"})

BATCHES = {
	"EB2a": {
		"branches": EB2A_BRANCHES,
		"defer": EB2A_DEFER,
		"include_forestry_result": True,
	},
}

FERTILITY = {
	"LOW": "FERTILITY_1",
	"NORMAL": "FERTILITY_2",
	"HIGH": "FERTILITY_3",
	"MAXIMUM": "FERTILITY_4",
}

POLLINATION = {
	"SLOWEST": "POLLINATION_SLOWEST",
	"SLOWER": "POLLINATION_SLOWER",
	"SLOW": "POLLINATION_SLOW",
	"AVERAGE": "POLLINATION_AVERAGE",
	"FAST": "POLLINATION_FAST",
	"FASTER": "POLLINATION_FASTER",
	"FASTEST": "POLLINATION_FASTEST",
	"MAXIMUM": "POLLINATION_MAXIMUM",
}

BIOME_TAGS = {
	"RIVER": "net.minecraft.tags.BiomeTags.IS_RIVER",
	"OCEAN": "net.minecraft.tags.BiomeTags.IS_OCEAN",
	"NETHER": "net.minecraft.tags.BiomeTags.IS_NETHER",
}


def path_id(reforestry_id: str) -> str:
	return reforestry_id.split(":", 1)[1]


def genus_of(species: dict) -> str:
	sci = species.get("branch_scientific")
	if sci:
		return sci.lower()
	return species["branch"].lower()


def resolve_product(entry: dict) -> tuple[str | None, str | None]:
	kind = entry.get("kind")
	rid = entry.get("reforestry_id")
	if kind == "extra_bees_comb":
		return f"ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.{entry['enum']})", None
	if kind == "forestry_comb":
		return f"ApicultureItems.BEE_COMBS.get(EnumHoneyComb.{entry['enum']}).item()", None
	if kind == "forestry_item" and rid == "reforestry:royal_jelly":
		return "ApicultureItems.ROYAL_JELLY.item()", None
	if rid and rid.startswith("reforestry:bee_comb_"):
		name = rid.removeprefix("reforestry:bee_comb_").upper()
		if name in {"HONEY", "POWDERY", "SIMMERING", "STRINGY", "FROZEN", "DRIPPING", "SILKY",
					"PARCHED", "MYSTERIOUS", "WHEATEN", "MOSSY", "MELLOW", "KAOLIN", "VINTAGE",
					"SPONGE", "SCULKEN", "COCOA"}:
			return f"ApicultureItems.BEE_COMBS.get(EnumHoneyComb.{name}).item()", None
		return f"ExtraBeesItems.BEE_COMBS.item(EnumExtraBeeComb.{name})", None
	return None, f"unresolved product {entry}"


def allele_line(g: dict) -> tuple[str, str] | None:
	chrom = g["chromosome"]
	kind = g["kind"]
	if chrom == "FLOWERING":
		chrom = "POLLINATION"
	if chrom == "FLOWER_PROVIDER":
		chrom = "FLOWER_TYPE"
	if chrom == "NEVER_SLEEPS":
		if g.get("value") is True or str(g.get("value")).lower() == "true":
			return "ACTIVITY", "AlleleManager.INSTANCE.registryAllele(ActivityType.METATURNAL, false)"
		return None
	if kind == "boolean":
		val = "true" if g.get("value") is True or str(g.get("value")).lower() == "true" else "false"
		return chrom, f"AlleleManager.INSTANCE.booleanAllele({val}, false)"
	if kind == "enum_allele":
		group = g.get("group")
		value = g["value"]
		if group == "Fertility":
			return "FERTILITY", f"ForestryAlleles.{FERTILITY[value]}"
		if group == "Flowering":
			return "POLLINATION", f"ForestryAlleles.{POLLINATION[value]}"
		if group in ("Speed", "Lifespan", "Tolerance"):
			prefix = {"Speed": "SPEED", "Lifespan": "LIFESPAN", "Tolerance": "TOLERANCE"}[group]
			return chrom, f"ForestryAlleles.{prefix}_{value}"
		if group == "Flowers":
			return "FLOWER_TYPE", f"AlleleManager.INSTANCE.registryAllele(FlowerType.{value}, false)"
		raise ValueError(f"unknown enum_allele {g}")
	if kind == "extra_bees_flower":
		return "FLOWER_TYPE", f"AlleleManager.INSTANCE.registryAllele(ExtraBeesFlowerType.{g['enum']}, false)"
	if kind == "extra_bees_effect":
		eid = path_id(g["reforestry_id"])
		const = g["enum"]
		if const == "RADIOACTIVE":
			const_name = "RADIOACTIVE"
		else:
			const_name = const
		return "EFFECT", (
			f"AlleleManager.INSTANCE.registryAllele("
			f"BeeChromosomes.EFFECT.getSafe(ExtraBeesBeeEffects.{const_name}).orElseThrow(), false)"
		)
	if kind == "forestry_effect":
		return "EFFECT", f"ForestryAlleles.EFFECT_{g['enum']}"
	raise ValueError(f"unknown genome allele {g}")


def merged_genome(branch_genome: list, overrides: list, nocturnal: bool) -> list[tuple[str, str]]:
	ordered: list[tuple[str, str]] = []
	seen: dict[str, int] = {}
	for g in list(branch_genome) + list(overrides):
		resolved = allele_line(g)
		if resolved is None:
			continue
		chrom, expr = resolved
		if chrom in seen:
			ordered[seen[chrom]] = (chrom, expr)
		else:
			seen[chrom] = len(ordered)
			ordered.append((chrom, expr))
	if nocturnal and "ACTIVITY" not in seen:
		ordered.append(("ACTIVITY", "AlleleManager.INSTANCE.registryAllele(ActivityType.NOCTURNAL, false)"))
		seen["ACTIVITY"] = len(ordered) - 1
	return ordered


def condition_suffix(conditions: list) -> str:
	parts = []
	for c in conditions:
		if c.get("type") == "restrictBiomeType":
			tag = BIOME_TAGS.get(c["biome"])
			if not tag:
				raise ValueError(f"unknown biome condition {c}")
			parts.append(f".restrictBiomeType({tag})")
		else:
			raise ValueError(f"unsupported condition {c}")
	return "".join(parts)


def emit_species(species: dict, branch_by_enum: dict, mutations_by_result: dict, warnings: list) -> str:
	rid = path_id(species["reforestry_id"])
	genus = genus_of(species)
	binomial = species["binomial"]
	primary = species["primary_color"]
	secondary = species["secondary_color"]
	chain = [
		f'registration.registerSpecies(ReForestry.id("{rid}"), "{genus}", "{binomial}", '
		f'{"true" if species["dominant"] else "false"}, {primary})'
	]
	chain.append(f".setBodyColor({primary})")
	chain.append(f".setStripesColor({secondary})")
	chain.append('.setAuthority("Binnie")')
	if species.get("has_effect"):
		chain.append(".setGlint(true)")
	if species.get("secret"):
		chain.append(".setSecret(true)")
	temp = species.get("temperature")
	if temp:
		chain.append(f".setTemperature(TemperatureType.{temp})")
	hum = species.get("humidity")
	if hum:
		chain.append(f".setHumidity(HumidityType.{hum})")
	for product in species.get("products") or []:
		expr, err = resolve_product(product)
		if err:
			warnings.append(f"{rid}: {err}")
			continue
		chain.append(f".addProduct({expr}, {product['chance']}f)")
	for specialty in species.get("specialties") or []:
		expr, err = resolve_product(specialty)
		if err:
			warnings.append(f"{rid}: specialty {err}")
			continue
		chain.append(f".addSpecialty({expr}, {specialty['chance']}f)")
	branch = branch_by_enum[species["branch"]]
	genome = merged_genome(branch.get("genome") or [], species.get("genome_overrides") or [], species.get("nocturnal", False))
	if genome:
		genome_lines = [
			"\t\t\t\t\tgenome.set(BeeChromosomes.%s, %s);" % (chrom, expr) for chrom, expr in genome
		]
		chain.append(".setGenome(genome -> {\n" + "\n".join(genome_lines) + "\n\t\t\t\t})")
	muts = mutations_by_result.get(species["reforestry_id"], [])
	if muts:
		mut_lines = []
		for m in muts:
			p0 = path_id(m["parent0"]["reforestry_id"])
			p1 = path_id(m["parent1"]["reforestry_id"])
			chance = float(m["chance"])
			suffix = condition_suffix(m.get("conditions") or [])
			mut_lines.append(
				f'\t\t\t\t\tmutations.add(ReForestry.id("{p0}"), ReForestry.id("{p1}"), {chance}f){suffix};'
			)
		chain.append(".addMutations(mutations -> {\n" + "\n".join(mut_lines) + "\n\t\t\t\t});")
	else:
		chain[-1] = chain[-1] + ";"
	out = ["\t\t" + chain[0]]
	for part in chain[1:]:
		if part.startswith(".setGenome") or part.startswith(".addMutations"):
			first, *rest = part.split("\n", 1)
			out.append("\t\t\t\t" + first)
			if rest:
				out.append(rest[0])
		else:
			out.append("\t\t\t\t" + part)
	return "\n".join(out)


def emit_modify_species(fr_mutations: list) -> str:
	by_result: dict[str, list] = defaultdict(list)
	for m in fr_mutations:
		by_result[m["result"]["reforestry_id"]].append(m)
	blocks = []
	for result_id, muts in sorted(by_result.items()):
		rid = path_id(result_id)
		lines = [f'\t\tregistration.modifySpecies(ReForestry.id("{rid}"), species -> species.addMutations(mutations -> {{']
		for m in muts:
			p0 = path_id(m["parent0"]["reforestry_id"])
			p1 = path_id(m["parent1"]["reforestry_id"])
			chance = float(m["chance"])
			suffix = condition_suffix(m.get("conditions") or [])
			lines.append(
				f"\t\t\tmutations.add(ReForestry.id(\"{p0}\"), ReForestry.id(\"{p1}\"), {chance}f){suffix};"
			)
		lines.append("\t\t}));")
		blocks.append("\n".join(lines))
	return "\n\n".join(blocks)


def generate(batch_name: str) -> tuple[str, dict]:
	batch = BATCHES[batch_name]
	species_doc = json.loads(SPECIES_JSON.read_text(encoding="utf-8"))
	mutations_doc = json.loads(MUTATIONS_JSON.read_text(encoding="utf-8"))
	branch_by_enum = {b["enum"]: b for b in species_doc["branches"]}
	all_species = species_doc["species"]
	selected = [
		s for s in all_species
		if s["branch"] in batch["branches"] and s["enum"] not in batch["defer"]
	]
	selected_ids = {s["reforestry_id"] for s in selected}
	warnings: list[str] = []

	eb_muts = []
	fr_muts = []
	for m in mutations_doc["mutations"]:
		if m.get("forestry_result"):
			if batch["include_forestry_result"]:
				fr_muts.append(m)
			continue
		result_id = m["result"]["reforestry_id"]
		if result_id not in selected_ids:
			continue
		parents_ok = True
		for p in (m["parent0"], m["parent1"]):
			if not p.get("forestry") and p["reforestry_id"] not in selected_ids:
				warnings.append(
					f"skip mutation {m['parent0']['enum']} x {m['parent1']['enum']} -> {m['result']['enum']}: "
					f"parent {p['enum']} not in batch"
				)
				parents_ok = False
				break
		if parents_ok:
			eb_muts.append(m)

	mutations_by_result: dict[str, list] = defaultdict(list)
	for m in eb_muts:
		mutations_by_result[m["result"]["reforestry_id"]].append(m)

	new_taxa = sorted({
		genus_of(s) for s in selected
		if genus_of(s) != "monapis"
	})

	species_blocks = [
		emit_species(s, branch_by_enum, mutations_by_result, warnings)
		for s in selected
	]

	modify_block = emit_modify_species(fr_muts) if fr_muts else ""

	taxa_lines = "\n".join(
		f'\t\tregistration.defineTaxon(ForestryTaxa.FAMILY_BEES, "{g}");'
		for g in new_taxa
	)

	species_body = "\n\n".join(species_blocks)
	parts = [
		"package com.leon1236.reforestry.extra_bees.genetics;",
		"",
		"import com.leon1236.reforestry.ReForestry;",
		"import com.leon1236.reforestry.api.core.HumidityType;",
		"import com.leon1236.reforestry.api.core.TemperatureType;",
		"import com.leon1236.reforestry.api.genetics.ForestryTaxa;",
		"import com.leon1236.reforestry.api.plugin.IApicultureRegistration;",
		"import com.leon1236.reforestry.api.plugin.IGeneticRegistration;",
		"import com.leon1236.reforestry.apiculture.features.ApicultureItems;",
		"import com.leon1236.reforestry.apiculture.genetics.ActivityType;",
		"import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;",
		"import com.leon1236.reforestry.apiculture.genetics.FlowerType;",
		"import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;",
		"import com.leon1236.reforestry.core.genetics.ForestryAlleles;",
		"import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;",
		"import com.leon1236.reforestry.extra_bees.features.ExtraBeesItems;",
		"import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeComb;",
		"",
		"public final class ExtraBeesBeeSpecies {",
		"\tprivate ExtraBeesBeeSpecies() {",
		"\t}",
		"",
		"\tpublic static void registerTaxa(IGeneticRegistration registration) {",
		taxa_lines,
		"\t}",
		"",
		"\tpublic static void register(IApicultureRegistration registration) {",
		species_body,
		"",
		modify_block,
		"\t}",
		"}",
		"",
	]
	java = "\n".join(parts)
	stats = {
		"species": len(selected),
		"eb_mutations": len(eb_muts),
		"fr_mutations": len(fr_muts),
		"taxa": new_taxa,
		"species_enums": [s["enum"] for s in selected],
		"warnings": warnings,
		"deferred": sorted(batch["defer"]),
	}
	return java, stats


def main() -> None:
	parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
	parser.add_argument("--batch", default="EB2a", choices=sorted(BATCHES))
	parser.add_argument("--apply", action="store_true")
	parser.add_argument("--output", type=Path, default=OUT_SPECIES)
	args = parser.parse_args()

	java, stats = generate(args.batch)
	print(f"batch {args.batch}: {stats['species']} species, {stats['eb_mutations']} EB mutations, "
		  f"{stats['fr_mutations']} modifySpecies FR mutations")
	print("species:", ", ".join(stats["species_enums"]))
	print("taxa:", ", ".join(stats["taxa"]))
	print("deferred:", ", ".join(stats["deferred"]))
	for w in stats["warnings"]:
		print(" ", w, file=sys.stderr)
	if args.apply:
		args.output.parent.mkdir(parents=True, exist_ok=True)
		args.output.write_text(java, encoding="utf-8")
		print(f"written {args.output}")
	else:
		print("dry run — pass --apply to write")


if __name__ == "__main__":
	main()
