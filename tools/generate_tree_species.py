#!/usr/bin/env python3
"""Extracts all 50 real tree species from thedarkcolour-ForestryCE's
DefaultTreeSpecies.java (via mods.db) and emits our own
arboriculture/genetics/DefaultTreeSpecies.java registering them through the
IArboricultureRegistration DSL.

Queries mods.db directly (like generate_bee_mutations.py) rather than needing
a hand-transcribed JSON intermediate - CE's real registerSpecies(...) calls
and their .setGenome(...)/.setAuthority(...) chains are parsed with the same
brace/statement-depth-aware technique generate_bee_mutations.py already uses.

Ported per species: genus/species/dominant/escritoireColor/woodType (from the
real registerSpecies(...) call), .setAuthority(...), and all real
.setGenome(...) chromosome overrides this port's engine already covers
(HEIGHT/SAPLINGS/YIELD/SAPPINESS/MATURATION/GIRTH/FIREPROOF, plus FRUIT/EFFECT
resolved to this port's DefaultFruits/TreeEffect values).

Deliberately NOT ported here (each is a separate, already-planned follow-up,
not a gap): .addMutations(...) (tools/generate_tree_mutations.py, run after
this script - it needs the species this script creates to already exist),
.setTreeFeature/.setGenerator/.addVanillaStates/.addVanillaSapling/
.setDecorativeLeaves/.setRarity (use tools/sync_tree_species_worldgen.py).

Usage:
  python3 tools/generate_tree_species.py --mods-db <writable copy of mods.db> \\
      --output src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java \\
      --apply
Without --apply this only prints a summary (dry run).
"""
import argparse
import re
import sqlite3
import sys
from pathlib import Path

CE_REPO = "thedarkcolour-ForestryCE"
CE_SPECIES_PATH = "src/main/java/forestry/plugin/DefaultTreeSpecies.java"
CE_TAXA_PATH = "src/main/java/forestry/api/genetics/ForestryTaxa.java"
CE_IDS_PATH = "src/main/java/forestry/api/arboriculture/ForestryTreeSpecies.java"

TAXA_CONST_RE = re.compile(r'public static final String (\w+)\s*=\s*"([^"]*)"')
ID_CONST_RE = re.compile(r'public static final ResourceLocation (\w+)\s*=\s*forestry\("tree_(\w+)"\)')
REGISTER_START_RE = re.compile(
    r"arboriculture\.registerSpecies\(\s*ForestryTreeSpecies\.(\w+),\s*(\w+),\s*(\w+),\s*(true|false),\s*"
    r"TextColor\.fromRgb\((0x[0-9a-fA-F]+|-?\d+)\),\s*((?:Vanilla|Forestry)WoodType\.\w+)\s*\)"
)
GENOME_SET_RE = re.compile(r"genome\.set\(TreeChromosomes\.(\w+),\s*([^;]+?)\);")


def find_statement_end(text, start_index):
    depth = 0
    for i in range(start_index, len(text)):
        ch = text[i]
        if ch in "({":
            depth += 1
        elif ch in ")}":
            depth -= 1
        elif ch == ";" and depth == 0:
            return i
    raise ValueError("no statement-ending semicolon found")


def find_matching_brace(text, open_index):
    depth = 0
    for i in range(open_index, len(text)):
        if text[i] == "{":
            depth += 1
        elif text[i] == "}":
            depth -= 1
            if depth == 0:
                return i
    raise ValueError("unbalanced braces")


def load_repo_file(mods_db_path, repo, path):
    con = sqlite3.connect(mods_db_path)
    cur = con.cursor()
    cur.execute("SELECT content FROM files WHERE repo=? AND path=?", (repo, path))
    row = cur.fetchone()
    if row is None:
        raise SystemExit(f"{path} not found in repo {repo}")
    return row[0]


def to_hex(literal):
    value = int(literal, 0) & 0xFFFFFF
    return f"0x{value:06x}"


def java_string(value):
    return '"' + value.replace("\\", "\\\\").replace('"', '\\"') + '"'


PLAIN_ALLELE_FAMILIES = {"HEIGHT", "SAPLINGS", "YIELD", "SAPPINESS", "MATURATION", "GIRTH"}

FRUIT_NAME_MAP = {
    "NONE": "NONE", "APPLE": "APPLE", "COCOA": "COCOA", "CHESTNUT": "CHESTNUT",
    "WALNUT": "WALNUT", "CHERRY": "CHERRY", "DATES": "DATES", "PAPAYA": "PAPAYA",
    "LEMON": "LEMON", "PLUM": "PLUM", "PEAR": "PEAR", "ORANGE": "ORANGE",
    "COCONUT": "COCONUT", "FEIJOA": "FEIJOA", "OLIVE": "OLIVE",
}
EFFECT_NAME_MAP = {"NONE": "NONE", "BLOSSOMING": "BLOSSOMING"}


def resolve_genome_value(chromosome, expr, warnings, ce_name):
    expr = expr.strip()
    if chromosome in PLAIN_ALLELE_FAMILIES:
        m = re.match(r"ForestryAlleles\.(\w+)$", expr)
        if m:
            return f"ForestryAlleles.{m.group(1)}"
        warnings.append(f"{ce_name}: unresolved {chromosome} override: {expr}")
        return None
    if chromosome == "FRUIT":
        m = re.match(r"ForestryAlleles\.FRUIT_(\w+)$", expr)
        if m and m.group(1) in FRUIT_NAME_MAP:
            name = FRUIT_NAME_MAP[m.group(1)]
            return (f"AlleleManager.INSTANCE.registryAllele(DefaultFruits.{name}, "
                    f"DefaultFruits.{name}.isDominant())")
        warnings.append(f"{ce_name}: unresolved FRUIT override: {expr}")
        return None
    if chromosome == "EFFECT":
        m = re.match(r"ForestryAlleles\.TREE_EFFECT_(\w+)$", expr)
        if m and m.group(1) in EFFECT_NAME_MAP:
            name = EFFECT_NAME_MAP[m.group(1)]
            return f"AlleleManager.INSTANCE.registryAllele(TreeEffect.{name}, true)"
        warnings.append(f"{ce_name}: unresolved EFFECT override: {expr}")
        return None
    if chromosome == "FIREPROOF":
        if expr in ("true", "false"):
            return f"AlleleManager.INSTANCE.booleanAllele({expr}, true)"
        warnings.append(f"{ce_name}: unresolved FIREPROOF override: {expr}")
        return None
    warnings.append(f"{ce_name}: unrecognized chromosome {chromosome} (override skipped)")
    return None


def build_taxa_constants(taxa_source):
    return {m.group(1): m.group(2) for m in TAXA_CONST_RE.finditer(taxa_source)}


def build_id_constants(ids_source):
    return {m.group(1): m.group(2) for m in ID_CONST_RE.finditer(ids_source)}


def extract_species(ce_source, taxa_constants, id_constants, warnings):
    species = []
    for m in REGISTER_START_RE.finditer(ce_source):
        ce_name, genus_const, species_const, dominant, color, wood_type = m.groups()
        genus = taxa_constants.get(genus_const)
        sp = taxa_constants.get(species_const)
        if genus is None or sp is None:
            warnings.append(f"{ce_name}: could not resolve genus/species constants ({genus_const}, {species_const})")
            continue
        real_id = id_constants.get(ce_name)
        if real_id is None:
            warnings.append(f"{ce_name}: could not resolve real registry id from ForestryTreeSpecies.java - falling back to lowercased constant name")
            real_id = ce_name.lower()

        stmt_end = find_statement_end(ce_source, m.start())
        chain_text = ce_source[m.end():stmt_end]

        authority = None
        auth_match = re.search(r'\.setAuthority\("([^"]*)"\)', chain_text)
        if auth_match:
            authority = auth_match.group(1)

        genome_overrides = []
        genome_idx = chain_text.find(".setGenome(genome -> {")
        if genome_idx != -1:
            brace_open = chain_text.index("{", genome_idx)
            brace_close = find_matching_brace(chain_text, brace_open)
            genome_block = chain_text[brace_open + 1:brace_close]
            for gm in GENOME_SET_RE.finditer(genome_block):
                chromosome, value_expr = gm.groups()
                genome_overrides.append((chromosome, value_expr))

        species.append({
            "ce_name": ce_name,
            "id": real_id,
            "genus": genus,
            "species": sp,
            "dominant": dominant == "true",
            "color": to_hex(color),
            "wood_type": wood_type,
            "authority": authority,
            "genome_overrides": genome_overrides,
        })
    return species


def generate_species_java(entry, warnings):
    lines = [
        f'registration.registerSpecies(ReForestry.id("tree_{entry["id"]}"), {java_string(entry["genus"])}, '
        f'{java_string(entry["species"])}, {"true" if entry["dominant"] else "false"}, '
        f'{entry["color"]}, {entry["wood_type"]})'
    ]
    if entry["authority"]:
        lines.append(f'        .setAuthority({java_string(entry["authority"])})')

    resolved_overrides = []
    for chromosome, expr in entry["genome_overrides"]:
        value = resolve_genome_value(chromosome, expr, warnings, entry["ce_name"])
        if value is not None:
            resolved_overrides.append((chromosome, value))

    if resolved_overrides:
        lines.append("        .setGenome(genome -> {")
        for chromosome, value in resolved_overrides:
            lines.append(f"            genome.set(TreeChromosomes.{chromosome}, {value});")
        lines.append("        });")
    else:
        lines[-1] += ";"

    if not lines[-1].endswith(";"):
        lines[-1] += ";"

    return "\n".join(lines)


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--mods-db", required=True, help="path to a writable copy of mods.db")
    parser.add_argument("--output", required=True, help="path to write DefaultTreeSpecies.java to")
    parser.add_argument("--apply", action="store_true", help="write the file (default: dry run)")
    args = parser.parse_args()

    ce_source = load_repo_file(args.mods_db, CE_REPO, CE_SPECIES_PATH)
    taxa_source = load_repo_file(args.mods_db, CE_REPO, CE_TAXA_PATH)
    ids_source = load_repo_file(args.mods_db, CE_REPO, CE_IDS_PATH)

    warnings = []
    taxa_constants = build_taxa_constants(taxa_source)
    id_constants = build_id_constants(ids_source)
    species = extract_species(ce_source, taxa_constants, id_constants, warnings)

    bodies = [generate_species_java(entry, warnings) for entry in species]

    header = (
        "package com.leon1236.reforestry.arboriculture.genetics;\n\n"
        "import com.leon1236.reforestry.ReForestry;\n"
        "import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;\n"
        "import com.leon1236.reforestry.arboriculture.ForestryWoodType;\n"
        "import com.leon1236.reforestry.arboriculture.VanillaWoodType;\n"
        "import com.leon1236.reforestry.core.genetics.ForestryAlleles;\n"
        "import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;\n\n"
        "public final class DefaultTreeSpecies {\n"
        "    private DefaultTreeSpecies() {\n"
        "    }\n\n"
        "    public static void register(IArboricultureRegistration registration) {\n"
    )
    footer = "    }\n}\n"

    indented = "\n\n".join("        " + body.replace("\n", "\n        ") for body in bodies)
    output = header + indented + "\n" + footer

    print(f"{len(species)} species extracted, {len(bodies)} emitted, {len(warnings)} warnings")
    for warning in warnings:
        print(" ", warning, file=sys.stderr)

    if args.apply:
        Path(args.output).write_text(output, encoding="utf-8")
        print(f"written to {args.output}")
    else:
        print("dry run only - rerun with --apply to write the file")
        print("\n--- preview of first species ---")
        if bodies:
            print(bodies[0])


if __name__ == "__main__":
    main()
