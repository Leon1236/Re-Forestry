#!/usr/bin/env python3
"""Generates DefaultBeeSpecies.java from tools-extracted bee species data.

Reads a JSON file (one entry per species, shape documented alongside the
extraction that produced it: id/genus/species/dominant/outlineColor plus
optional products/specialties/genomeOverrides/bodyColor/stripesColor/
secret/glint/authority) and emits Java source registering each species via
the IApicultureRegistration DSL (registration.registerSpecies(...)).

Deliberately NOT emitted (out of scope for the step that needed this script):
mutations (breeding/mutation system doesn't exist yet), climate
temperature/humidity fields (no climate system consumes them yet), jubilance
(depends on unbuilt per-species behavior classes), and any product/specialty
whose source item couldn't be resolved to a real registered item (logged to
stderr, not silently dropped from the log - just dropped from the generated
file).

Usage:
  python3 tools/generate_bee_species.py --input bee_species.json --output <path> --apply
Without --apply this only prints a summary (dry run).
"""
import argparse
import json
import re
import sys
from pathlib import Path

COMB_NAMES = {
    "HONEY", "COCOA", "SIMMERING", "STRINGY", "FROZEN", "DRIPPING", "SILKY",
    "PARCHED", "MYSTERIOUS", "POWDERY", "WHEATEN", "MOSSY", "MELLOW",
    "KAOLIN", "VINTAGE", "SPONGE", "SCULKEN",
}
POLLEN_NAMES = {"NORMAL", "CRYSTALLINE"}
APICULTURE_ITEM_NAMES = {
    "HONEY_DROP", "HONEYDEW", "EXPERIENCE_DROP", "ROYAL_JELLY", "AMBER_DRONE",
    "HONEYED_SLICE", "HONEY_POT", "AMBROSIA",
}


def resolve_product(entry):
    if "comb" in entry:
        name = entry["comb"]
        if name not in COMB_NAMES:
            return None, f"unknown comb {name}"
        return f"ApicultureItems.BEE_COMBS.get(EnumHoneyComb.{name}).item()", None
    if "pollenCluster" in entry:
        name = entry["pollenCluster"]
        if name not in POLLEN_NAMES:
            return None, f"unknown pollen cluster {name}"
        return f"ApicultureItems.POLLEN_CLUSTER.get(EnumPollenCluster.{name}).item()", None
    if "item" in entry:
        name = entry["item"]
        if name not in APICULTURE_ITEM_NAMES:
            return None, f"unknown apiculture item {name}"
        return f"ApicultureItems.{name}.item()", None
    if "itemExpr" in entry:
        match = re.search(r"\bItems\.([A-Z0-9_]+)", entry["itemExpr"])
        if match:
            return f"Items.{match.group(1)}", None
        return None, f"unresolvable itemExpr: {entry['itemExpr']}"
    return None, "product entry has no recognized field"


def resolve_allele(chromosome, allele):
    if chromosome in ("SPEED", "LIFESPAN", "POLLINATION", "FERTILITY",
                       "TEMPERATURE_TOLERANCE", "HUMIDITY_TOLERANCE", "TERRITORY"):
        return f"ForestryAlleles.{allele}"
    if chromosome == "ACTIVITY":
        name = allele.removeprefix("ACTIVITY_")
        return f"AlleleManager.INSTANCE.registryAllele(ActivityType.{name}, false)"
    if chromosome == "FLOWER_TYPE":
        name = allele.removeprefix("FLOWER_TYPE_")
        return f"AlleleManager.INSTANCE.registryAllele(FlowerType.{name}, false)"
    if chromosome == "EFFECT":
        name = allele.removeprefix("EFFECT_")
        return f"AlleleManager.INSTANCE.registryAllele(BeeEffect.{name}, false)"
    if chromosome in ("CAVE_DWELLING", "TOLERATES_RAIN"):
        value = "true" if allele.upper() == "TRUE" else "false"
        return f"AlleleManager.INSTANCE.booleanAllele({value}, false)"
    return None


def to_hex(value):
    if isinstance(value, str):
        return value if value.startswith("0x") else f"0x{int(value):06x}"
    return f"0x{value:06x}"


def java_string(value):
    return '"' + value.replace("\\", "\\\\").replace('"', '\\"') + '"'


def generate_species(entry, warnings):
    sid = entry["id"]
    lines = []
    lines.append(
        f'registration.registerSpecies(ReForestry.id("bee_{sid}"), {java_string(entry["genus"])}, '
        f'{java_string(entry["species"])}, {"true" if entry["dominant"] else "false"}, {to_hex(entry["outlineColor"])})'
    )

    if "bodyColor" in entry:
        lines.append(f'        .setBodyColor({to_hex(entry["bodyColor"])})')
    if "stripesColor" in entry:
        lines.append(f'        .setStripesColor({to_hex(entry["stripesColor"])})')
    if entry.get("secret"):
        lines.append("        .setSecret(true)")
    if entry.get("glint"):
        lines.append("        .setGlint(true)")
    if "authority" in entry:
        lines.append(f'        .setAuthority({java_string(entry["authority"])})')

    for entry_field, method, label in (("products", "addProduct", "product"), ("specialties", "addSpecialty", "specialty")):
        for product in entry.get(entry_field, []):
            expr, error = resolve_product(product)
            if error:
                warnings.append(f"{sid}: skipped {label} - {error}")
                continue
            lines.append(f'        .{method}({expr}, {product["chance"]}f)')

    overrides = entry.get("genomeOverrides", [])
    if overrides:
        lines.append("        .setGenome(genome -> {")
        for override in overrides:
            expr = resolve_allele(override["chromosome"], override["allele"])
            if expr is None:
                warnings.append(f"{sid}: skipped genome override {override}")
                continue
            lines.append(f"            genome.set(BeeChromosomes.{override['chromosome']}, {expr});")
        lines.append("        });")
    else:
        lines[-1] += ";"

    if len(lines) > 1 and not lines[-1].endswith(";"):
        lines[-1] += ";"

    return "\n".join(lines)


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--input", required=True, help="path to the extracted species JSON")
    parser.add_argument("--output", required=True, help="path to write DefaultBeeSpecies.java to")
    parser.add_argument("--apply", action="store_true", help="write the file (default: dry run, prints summary only)")
    args = parser.parse_args()

    species = json.loads(Path(args.input).read_text(encoding="utf-8"))
    warnings = []
    bodies = [generate_species(entry, warnings) for entry in species]

    header = (
        "package com.leon1236.reforestry.apiculture.genetics;\n\n"
        "import net.minecraft.world.item.Items;\n\n"
        "import com.leon1236.reforestry.ReForestry;\n"
        "import com.leon1236.reforestry.api.plugin.IApicultureRegistration;\n"
        "import com.leon1236.reforestry.apiculture.features.ApicultureItems;\n"
        "import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;\n"
        "import com.leon1236.reforestry.apiculture.items.EnumPollenCluster;\n"
        "import com.leon1236.reforestry.core.genetics.ForestryAlleles;\n"
        "import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;\n\n"
        "public final class DefaultBeeSpecies {\n"
        "    private DefaultBeeSpecies() {\n"
        "    }\n\n"
        "    public static void register(IApicultureRegistration registration) {\n"
    )
    footer = "    }\n}\n"

    indented = "\n\n".join("        " + body.replace("\n", "\n        ") for body in bodies)
    output = header + indented + "\n" + footer

    print(f"{len(species)} species processed, {len(warnings)} warnings")
    for warning in warnings:
        print(" ", warning, file=sys.stderr)

    if args.apply:
        Path(args.output).write_text(output, encoding="utf-8")
        print(f"written to {args.output}")
    else:
        print("dry run only - rerun with --apply to write the file")


if __name__ == "__main__":
    main()
