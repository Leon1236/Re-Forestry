#!/usr/bin/env python3
"""Extracts real bee mutations from thedarkcolour-ForestryCE's DefaultBeeSpecies.java
(via mods.db) and patches .addMutations(...) chains onto the matching registerSpecies(...)
calls in our own apiculture/genetics/DefaultBeeSpecies.java.

CE's mutation parent/result species are ForestryBeeSpecies.<NAME> constants with no
literal genus/species/color available in this file alone (they come from a static
import elsewhere). Rather than resolving that import, CE species are matched to our
own ported species by outlineColor hex, which both files carry as a literal - a robust
join key confirmed unique across the 69 ported species.

Conditions ported faithfully: restrictBiomeType with a vanilla BiomeTags tag, or with
Forge's Tags.Biomes.IS_PLAINS (mapped to Fabric's ConventionalBiomeTags.IS_PLAINS,
already on the classpath via the fabric-api umbrella dependency), or with one of
ForestryTags.Biomes.{SHATTERED_SAVANNA,WARPED_FOREST,DEEP_DARK} (ported as three small
data/reforestry/tags/worldgen/biome/special/*.json files + a ReforestryBiomeTags
holder - see --emit-tags). Cave-gated mutations (MutationConditionCave/
CaveMutationCondition) map to CAVE_DWELLING via a new MutationConditionCaveDwelling.
restrictDateRange ports directly (Month enum -> int). restrictTemperature/
restrictHumidity are DROPPED (logged, not silently) - no climate system exists in this
port yet, per CLAUDE.md.

Any CE species referenced by a mutation that isn't part of the 69 ported species
(Gendustry/addon-only bees) is skipped and logged, not silently dropped.

Usage:
  python3 tools/generate_bee_mutations.py --mods-db <writable copy of mods.db> \\
      --target src/main/java/com/leon1236/reforestry/apiculture/genetics/DefaultBeeSpecies.java \\
      --apply
Without --apply this only prints a summary (dry run).
"""
import argparse
import re
import sqlite3
import sys
from pathlib import Path

CE_REPO = "thedarkcolour-ForestryCE"
CE_SPECIES_PATH = "src/main/java/forestry/plugin/DefaultBeeSpecies.java"
CE_TAXA_PATH = "src/main/java/forestry/api/genetics/ForestryTaxa.java"

MONTHS = {
    "JANUARY": 1, "FEBRUARY": 2, "MARCH": 3, "APRIL": 4, "MAY": 5, "JUNE": 6,
    "JULY": 7, "AUGUST": 8, "SEPTEMBER": 9, "OCTOBER": 10, "NOVEMBER": 11, "DECEMBER": 12,
}

REGISTER_RE = re.compile(
    r"apiculture\.registerSpecies\(ForestryBeeSpecies\.(\w+),\s*(\w+),\s*(\w+),\s*(?:true|false),\s*new Color\((0x[0-9a-fA-F]+|\d+)\)\)"
)
TAXA_CONST_RE = re.compile(r'public static final String (\w+)\s*=\s*"([^"]*)"')
OUR_REGISTER_RE = re.compile(
    r'registration\.registerSpecies\(ReForestry\.id\("bee_(\w+)"\),\s*"([^"]*)",\s*"([^"]*)",\s*(?:true|false),\s*(0x[0-9a-fA-F]+)\)'
)
ARRAY_DECL_RE = re.compile(r"ResourceLocation\[\]\s+(\w+)\s*=\s*new ResourceLocation\[\]\s*\{([^}]*)\};?")
DOUBLE_LOOP_RE = re.compile(
    r"for \(int i = 0;[^{]*\{\s*ResourceLocation (\w+) = (\w+)\[i\];\s*"
    r"for \(int j = i \+ 1;[^{]*\{\s*mutations\.add\(\1, \2\[j\], (\d+)\);\s*\}\s*\}",
)
SINGLE_LOOP_RE = re.compile(
    r"for \(ResourceLocation (\w+) : (\w+)\) \{\s*"
    r"mutations\.add\(([^,]+?),\s*([^,]+?),\s*(\d+)\)((?:\s*\.\w+\((?:[^()]|\([^()]*\))*\))*);\s*\}"
)
DIRECT_ADD_RE = re.compile(
    r"mutations\.add\(([^,]+?),\s*([^,]+?),\s*(\d+)\)((?:\s*\.\w+\((?:[^()]|\([^()]*\))*\))*);"
)
CHAIN_CALL_RE = re.compile(r"\.(\w+)\(((?:[^()]|\([^()]*\))*)\)")


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


def find_statement_end(text, start_index):
    """Finds the ';' that ends the chained statement beginning at start_index,
    skipping any ';' nested inside parens/braces (e.g. inside a .setGenome(genome -> {...}) lambda)."""
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


def load_repo_file(mods_db_path, repo, path):
    con = sqlite3.connect(mods_db_path)
    cur = con.cursor()
    cur.execute("SELECT content FROM files WHERE repo=? AND path=?", (repo, path))
    row = cur.fetchone()
    if row is None:
        raise SystemExit(f"{path} not found in repo {repo}")
    return row[0]


def build_taxa_constants(taxa_source):
    return {m.group(1): m.group(2) for m in TAXA_CONST_RE.finditer(taxa_source)}


def build_ce_name_to_taxon(ce_source, taxa_constants, warnings):
    result = {}
    for m in REGISTER_RE.finditer(ce_source):
        ce_name, genus_const, species_const, color = m.groups()
        genus = taxa_constants.get(genus_const)
        species = taxa_constants.get(species_const)
        if genus is None or species is None:
            warnings.append(f"{ce_name}: could not resolve genus/species constants ({genus_const}, {species_const})")
            continue
        color_hex = color.lower() if color.startswith("0x") else f"0x{int(color):06x}"
        result[ce_name] = (genus.lower(), species.lower(), color_hex)
    return result


def build_our_taxon_to_id(our_source):
    taxon_to_id = {}
    for m in OUR_REGISTER_RE.finditer(our_source):
        our_id, genus, species, color = m.groups()
        taxon_to_id.setdefault((genus.lower(), species.lower()), []).append((our_id, color.lower()))
    return taxon_to_id


def resolve_condition(name, args, warnings, ce_name):
    if name == "restrictBiomeType":
        if args == "BiomeTags.IS_NETHER":
            return ".restrictBiomeType(net.minecraft.tags.BiomeTags.IS_NETHER)"
        if args == "BiomeTags.IS_FOREST":
            return ".restrictBiomeType(net.minecraft.tags.BiomeTags.IS_FOREST)"
        if args == "Tags.Biomes.IS_PLAINS":
            return ".restrictBiomeType(net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags.IS_PLAINS)"
        if args == "ForestryTags.Biomes.SHATTERED_SAVANNA":
            return ".restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.SHATTERED_SAVANNA)"
        if args == "ForestryTags.Biomes.WARPED_FOREST":
            return ".restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.WARPED_FOREST)"
        if args == "ForestryTags.Biomes.DEEP_DARK":
            return ".restrictBiomeType(com.leon1236.reforestry.api.core.ReforestryBiomeTags.DEEP_DARK)"
        warnings.append(f"{ce_name}: unresolved restrictBiomeType({args})")
        return None
    if name == "addMutationCondition" and args in ("new MutationConditionCave()", "new CaveMutationCondition()"):
        return ".addMutationCondition(new com.leon1236.reforestry.core.genetics.mutations.MutationConditionCaveDwelling())"
    if name == "restrictDateRange":
        parts = [p.strip() for p in args.split(",")]
        if len(parts) == 4:
            months_days = []
            ok = True
            for i in (0, 2):
                match = re.match(r"Month\.(\w+)", parts[i])
                if not match or match.group(1) not in MONTHS:
                    ok = False
                    break
                months_days.append(MONTHS[match.group(1)])
                months_days.append(int(parts[i + 1]))
            if ok:
                return f".restrictDateRange({months_days[0]}, {months_days[1]}, {months_days[2]}, {months_days[3]})"
        warnings.append(f"{ce_name}: unresolved restrictDateRange({args})")
        return None
    if name in ("restrictTemperature", "restrictHumidity"):
        warnings.append(f"{ce_name}: dropped {name}({args}) - no climate system exists in this port yet")
        return None
    warnings.append(f"{ce_name}: unrecognized mutation condition .{name}({args})")
    return None


def resolve_chain(chain_text, warnings, ce_name):
    resolved = []
    for call_match in CHAIN_CALL_RE.finditer(chain_text):
        name, args = call_match.group(1), call_match.group(2).strip()
        condition = resolve_condition(name, args, warnings, ce_name)
        if condition:
            resolved.append(condition)
    return resolved


def resolve_ce_ref(ref, arrays, loop_var=None, loop_value=None):
    ref = ref.strip()
    if loop_var is not None and ref == loop_var:
        return [loop_value]
    match = re.match(r"ForestryBeeSpecies\.(\w+)$", ref)
    if match:
        return [match.group(1)]
    match = re.match(r"(\w+)\[i\]$", ref)
    if match and match.group(1) in arrays:
        return None
    if ref in arrays:
        return arrays[ref]
    return None


def parse_mutations_block(block_text, ce_name, overworld_hive_bees, warnings):
    mutations = []
    arrays = {"overworldHiveBees": overworld_hive_bees}
    for m in ARRAY_DECL_RE.finditer(block_text):
        name = m.group(1)
        names = [x.strip().removeprefix("ForestryBeeSpecies.") for x in m.group(2).split(",") if x.strip()]
        arrays[name] = names

    consumed = set()

    for m in DOUBLE_LOOP_RE.finditer(block_text):
        consumed.add(m.span())
        array_name, chance = m.group(2), int(m.group(3))
        members = arrays.get(array_name, [])
        for i in range(len(members)):
            for j in range(i + 1, len(members)):
                mutations.append((members[i], members[j], chance, []))

    for m in SINGLE_LOOP_RE.finditer(block_text):
        if any(a <= m.start() < b for a, b in consumed):
            continue
        consumed.add(m.span())
        loop_var, array_name, first_ref, second_ref, chance, chain = m.groups()
        members = arrays.get(array_name)
        if members is None:
            warnings.append(f"{ce_name}: unknown loop array '{array_name}'")
            continue
        for member in members:
            first = resolve_ce_ref(first_ref, arrays, loop_var, member)
            second = resolve_ce_ref(second_ref, arrays, loop_var, member)
            if first is None or second is None or len(first) != 1 or len(second) != 1:
                warnings.append(f"{ce_name}: could not resolve loop mutation add({first_ref}, {second_ref})")
                continue
            conditions = resolve_chain(chain, warnings, ce_name)
            mutations.append((first[0], second[0], int(chance), conditions))

    for m in DIRECT_ADD_RE.finditer(block_text):
        if any(a <= m.start() < b for a, b in consumed):
            continue
        first_ref, second_ref, chance, chain = m.groups()
        first = resolve_ce_ref(first_ref, arrays)
        second = resolve_ce_ref(second_ref, arrays)
        if first is None or second is None or len(first) != 1 or len(second) != 1:
            warnings.append(f"{ce_name}: could not resolve direct mutation add({first_ref}, {second_ref})")
            continue
        conditions = resolve_chain(chain, warnings, ce_name)
        mutations.append((first[0], second[0], int(chance), conditions))

    return mutations


def extract_ce_mutations(ce_source, warnings):
    overworld_match = re.search(r"ResourceLocation\[\]\s+overworldHiveBees\s*=\s*new ResourceLocation\[\]\s*\{([^}]*)\}", ce_source)
    overworld_hive_bees = []
    if overworld_match:
        overworld_hive_bees = [x.strip().removeprefix("ForestryBeeSpecies.") for x in overworld_match.group(1).split(",") if x.strip()]

    starts = [m.start(1) for m in re.finditer(r"apiculture\.registerSpecies\((ForestryBeeSpecies\.\w+)", ce_source)]
    starts.append(len(ce_source))

    per_species = {}
    for idx in range(len(starts) - 1):
        block = ce_source[starts[idx]:starts[idx + 1]]
        name_match = re.match(r"ForestryBeeSpecies\.(\w+)", block)
        if not name_match:
            continue
        ce_name = name_match.group(1)
        add_mutations_idx = block.find(".addMutations(mutations -> {")
        if add_mutations_idx == -1:
            continue
        brace_open = block.index("{", add_mutations_idx)
        brace_close = find_matching_brace(block, brace_open)
        mutations_text = block[brace_open + 1:brace_close]
        mutations = parse_mutations_block(mutations_text, ce_name, overworld_hive_bees, warnings)
        if mutations:
            per_species[ce_name] = mutations
    return per_species


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--mods-db", required=True, help="path to a writable copy of mods.db")
    parser.add_argument("--target", required=True, help="path to DefaultBeeSpecies.java to patch")
    parser.add_argument("--apply", action="store_true", help="write the patched file (default: dry run)")
    args = parser.parse_args()

    ce_source = load_repo_file(args.mods_db, CE_REPO, CE_SPECIES_PATH)
    taxa_source = load_repo_file(args.mods_db, CE_REPO, CE_TAXA_PATH)
    our_source = Path(args.target).read_text(encoding="utf-8")

    warnings = []
    taxa_constants = build_taxa_constants(taxa_source)
    ce_name_to_taxon = build_ce_name_to_taxon(ce_source, taxa_constants, warnings)
    our_taxon_to_id = build_our_taxon_to_id(our_source)

    duplicate_taxa = {t: ids for t, ids in our_taxon_to_id.items() if len(ids) > 1}
    for taxon, ids in duplicate_taxa.items():
        warnings.append(f"genus/species {taxon} is shared by our species {ids} - mutations targeting it are ambiguous and skipped")

    ce_name_to_our_id = {}
    for ce_name, (genus, species, color) in ce_name_to_taxon.items():
        candidates = our_taxon_to_id.get((genus, species))
        if not candidates:
            continue
        if len(candidates) > 1:
            matching_color = [c for c in candidates if c[1] == color]
            if len(matching_color) == 1:
                ce_name_to_our_id[ce_name] = matching_color[0][0]
            else:
                warnings.append(f"{ce_name}: ambiguous match among {candidates}, color {color} did not disambiguate")
            continue
        ce_name_to_our_id[ce_name] = candidates[0][0]

    per_species_ce = extract_ce_mutations(ce_source, warnings)

    total_mutations = 0
    skipped_unported = 0
    patches = {}
    for ce_name, mutations in per_species_ce.items():
        our_result_id = ce_name_to_our_id.get(ce_name)
        if our_result_id is None:
            warnings.append(f"result species {ce_name} is not a ported (base-game) species - skipping its {len(mutations)} mutation(s)")
            skipped_unported += len(mutations)
            continue
        lines = []
        for first_ce, second_ce, chance, conditions in mutations:
            first_id = ce_name_to_our_id.get(first_ce)
            second_id = ce_name_to_our_id.get(second_ce)
            if first_id is None or second_id is None:
                unported = first_ce if first_id is None else second_ce
                warnings.append(f"{ce_name}: mutation with {first_ce}+{second_ce} skipped - parent '{unported}' not ported")
                skipped_unported += 1
                continue
            call = f'mutations.add(ReForestry.id("bee_{first_id}"), ReForestry.id("bee_{second_id}"), {chance}f)'
            for condition in conditions:
                call += condition
            call += ";"
            lines.append(call)
            total_mutations += 1
        if lines:
            patches[our_result_id] = lines

    print(f"CE mutation definitions found: {sum(len(m) for m in per_species_ce.values())}")
    print(f"Ported (resolvable to our species): {total_mutations}")
    print(f"Skipped (unported species): {skipped_unported}")
    print(f"Species receiving .addMutations(...): {len(patches)}")
    for warning in warnings:
        print(" ", warning, file=sys.stderr)

    if not args.apply:
        print("dry run only - rerun with --apply to patch the target file")
        return

    patched = our_source
    for our_id, lines in patches.items():
        marker = f'registration.registerSpecies(ReForestry.id("bee_{our_id}")'
        start = patched.find(marker)
        if start == -1:
            warnings.append(f"bee_{our_id}: could not locate its registerSpecies statement to patch")
            continue
        stmt_end = find_statement_end(patched, start)
        addition = "\n        .addMutations(mutations -> {\n"
        for line in lines:
            addition += "            " + line + "\n"
        addition += "        })"
        patched = patched[:stmt_end] + addition + patched[stmt_end:]

    Path(args.target).write_text(patched, encoding="utf-8")
    print(f"patched {args.target}")


if __name__ == "__main__":
    main()
