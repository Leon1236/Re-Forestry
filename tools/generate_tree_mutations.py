#!/usr/bin/env python3
"""Extracts real tree mutations from thedarkcolour-ForestryCE's
DefaultTreeSpecies.java (via mods.db) and patches .addMutations(...) chains
onto the matching registerSpecies(...) calls in our own
arboriculture/genetics/DefaultTreeSpecies.java (written by
generate_tree_species.py, which must be run first).

Unlike generate_bee_mutations.py's join (bee species only carry a fuzzy
outlineColor key), tree species have an authoritative CE-constant -> real
registry-id mapping (ForestryTreeSpecies.java, the same file
generate_tree_species.py already uses to resolve species ids), so mutation
parents/results are resolved directly and unambiguously - no color-based
disambiguation needed.

CE's tree mutation chances are plain fractions (0.15f = 15%), unlike bees'
integer percentages - multiplied by 100 here to match this port's
IMutationsRegistration.add(..., chancePercent) convention.

Every real tree mutation in CE is a single, unlooped mutations.add(...) call
(confirmed by scanning the source for loop constructs before writing this
script - none exist, unlike bees' overworldHiveBees loops), optionally
followed by .restrictTemperature(...)/.restrictHumidity(...) - both dropped
(logged, not silently) since no climate system exists in this port yet, the
same policy already used for bee mutations and tree species' own aggregate
climate fields.

Usage:
  python3 tools/generate_tree_mutations.py --mods-db <writable copy of mods.db> \\
      --target src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java \\
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
CE_IDS_PATH = "src/main/java/forestry/api/arboriculture/ForestryTreeSpecies.java"

ID_CONST_RE = re.compile(r'public static final ResourceLocation (\w+)\s*=\s*forestry\("tree_(\w+)"\)')
REGISTER_START_RE = re.compile(r"arboriculture\.registerSpecies\(\s*ForestryTreeSpecies\.(\w+),")
ADD_MUTATION_RE = re.compile(
    r"mutations\.add\(\s*ForestryTreeSpecies\.(\w+),\s*ForestryTreeSpecies\.(\w+),\s*([\d.]+)f\s*\)"
    r"((?:\s*\.\w+\((?:[^()]|\([^()]*\))*\))*)\s*;"
)
CHAIN_CALL_RE = re.compile(r"\.(\w+)\(((?:[^()]|\([^()]*\))*)\)")
OUR_REGISTER_RE = re.compile(r'registration\.registerSpecies\(ReForestry\.id\("(tree_\w+)"\)')


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


def build_id_constants(ids_source):
    return {m.group(1): m.group(2) for m in ID_CONST_RE.finditer(ids_source)}


def resolve_conditions(chain_text, warnings, context):
    conditions = []
    for call_match in CHAIN_CALL_RE.finditer(chain_text):
        name, args = call_match.group(1), call_match.group(2).strip()
        if name in ("restrictTemperature", "restrictHumidity"):
            warnings.append(f"{context}: dropped {name}({args}) - no climate system exists in this port yet")
            continue
        warnings.append(f"{context}: unrecognized mutation condition .{name}({args}) - dropped")
    return conditions


def extract_mutations(ce_source, id_constants, warnings):
    starts = [(m.start(), m.group(1)) for m in REGISTER_START_RE.finditer(ce_source)]
    boundaries = [s for s, _ in starts] + [len(ce_source)]

    per_species = {}
    for idx, (start, ce_name) in enumerate(starts):
        block = ce_source[start:boundaries[idx + 1]]
        add_mutations_idx = block.find(".addMutations(mutations -> {")
        if add_mutations_idx == -1:
            continue
        brace_open = block.index("{", add_mutations_idx)
        brace_close = find_matching_brace(block, brace_open)
        mutations_text = block[brace_open + 1:brace_close]

        result_id = id_constants.get(ce_name)
        if result_id is None:
            warnings.append(f"{ce_name}: could not resolve its own registry id - skipping its mutations")
            continue

        mutations = []
        for m in ADD_MUTATION_RE.finditer(mutations_text):
            first_ce, second_ce, fraction, chain = m.groups()
            first_id = id_constants.get(first_ce)
            second_id = id_constants.get(second_ce)
            if first_id is None or second_id is None:
                unresolved = first_ce if first_id is None else second_ce
                warnings.append(f"{ce_name}: mutation with {first_ce}+{second_ce} skipped - '{unresolved}' has no resolvable registry id")
                continue
            resolve_conditions(chain, warnings, f"{ce_name} ({first_ce}+{second_ce})")
            chance_percent = float(fraction) * 100
            mutations.append((first_id, second_id, chance_percent))

        if mutations:
            per_species[result_id] = mutations
    return per_species


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--mods-db", required=True, help="path to a writable copy of mods.db")
    parser.add_argument("--target", required=True, help="path to DefaultTreeSpecies.java to patch")
    parser.add_argument("--apply", action="store_true", help="write the patched file (default: dry run)")
    args = parser.parse_args()

    ce_source = load_repo_file(args.mods_db, CE_REPO, CE_SPECIES_PATH)
    ids_source = load_repo_file(args.mods_db, CE_REPO, CE_IDS_PATH)
    our_source = Path(args.target).read_text(encoding="utf-8")

    warnings = []
    id_constants = build_id_constants(ids_source)
    our_ids = set(OUR_REGISTER_RE.findall(our_source))

    per_species = extract_mutations(ce_source, id_constants, warnings)

    total_mutations = 0
    skipped_not_ours = 0
    patches = {}
    for result_id, mutations in per_species.items():
        our_result_id = f"tree_{result_id}"
        if our_result_id not in our_ids:
            warnings.append(f"tree_{result_id}: result species not found in target file - skipping its {len(mutations)} mutation(s)")
            skipped_not_ours += len(mutations)
            continue
        lines = []
        for first_id, second_id, chance_percent in mutations:
            first_full = f"tree_{first_id}"
            second_full = f"tree_{second_id}"
            if first_full not in our_ids or second_full not in our_ids:
                missing = first_full if first_full not in our_ids else second_full
                warnings.append(f"{our_result_id}: mutation parent {missing} not found in target file - skipped")
                skipped_not_ours += 1
                continue
            lines.append(f'mutations.add(ReForestry.id("{first_full}"), ReForestry.id("{second_full}"), {chance_percent:g}f);')
            total_mutations += 1
        if lines:
            patches[our_result_id] = lines

    print(f"CE mutation definitions found: {sum(len(m) for m in per_species.values())}")
    print(f"Ported: {total_mutations}")
    print(f"Skipped (unresolved/missing): {skipped_not_ours}")
    print(f"Species receiving .addMutations(...): {len(patches)}")
    for warning in warnings:
        print(" ", warning, file=sys.stderr)

    if not args.apply:
        print("dry run only - rerun with --apply to patch the target file")
        return

    patched = our_source
    for our_id, lines in patches.items():
        marker = f'registration.registerSpecies(ReForestry.id("{our_id}")'
        start = patched.find(marker)
        if start == -1:
            warnings.append(f"{our_id}: could not locate its registerSpecies statement to patch")
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
