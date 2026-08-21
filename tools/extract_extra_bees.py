#!/usr/bin/env python3
"""Extract Extra Bees species/mutations/effects/items from Binnie extrabees/ into queries JSON.

Usage: python3 tools/extract_extra_bees.py --clone <binnie> --out queries --apply
Without --apply: print counts only.
"""
from __future__ import annotations

import argparse
import json
import re
import sys
from collections import OrderedDict
from pathlib import Path

NS = "reforestry"
BINNIE_MOD = "extrabees"
SPECIES_COLLISIONS = {
    "primeval": "bee_eb_primeval",
    "relic": "bee_eb_relic",
    "boggy": "bee_eb_boggy",
}
EFFECT_COLLISIONS = {
    "radioactive": "bee_effect_eb_radioactive",
}
HIVE_COLLISIONS = {
    "nether": "beehive_eb_nether",
}
ALLOY_COMMENTED = ("INVAR", "BRONZE", "BRASS", "STEEL")
EXPECTED = {
    "species": 116,
    "mutations": 168,
    "forestry_result_mutations": 34,
    "effects": 25,
    "flowers": 11,
    "alveary": 7,
    "hives": 4,
    "frames": 5,
}

ENUM_HEADER_RE = re.compile(r"public\s+enum\s+(\w+)\b[^{]*\{", re.S)
COLOR_RE = re.compile(r"new\s+Color\s*\(\s*(0x[0-9a-fA-F]+|\d+)\s*\)")
BRANCH_CTOR_RE = re.compile(
    r"^\t([A-Z][A-Z0-9_]*)\s*\(\s*(ExtraBeeBranchDefinition|BeeBranchDefinition)\.([A-Z][A-Z0-9_]+)\s*,\s*"
    r'"([^"]*)"\s*,\s*(true|false)\s*,\s*new\s+Color\s*\(\s*(0x[0-9a-fA-F]+|\d+)\s*\)\s*,\s*'
    r"new\s+Color\s*\(\s*(0x[0-9a-fA-F]+|\d+)\s*\)\s*\)",
    re.M,
)
REGISTER_MUT_RE = re.compile(
    r"registerMutation\s*\(\s*([^,]+?)\s*,\s*([^,]+?)\s*,\s*(?:([^,]+?)\s*,\s*)?(\d+)\s*\)"
    r"((?:\s*\.\s*restrictBiomeType\s*\(\s*BiomeDictionary\.Type\.(\w+)\s*\))*)",
)
CHROMOSOME_SET_RE = re.compile(
    r"set\s*\(\s*template\s*,\s*EnumBeeChromosome\.(\w+)\s*,\s*(.*?)\s*\)\s*;",
    re.S,
)
ADD_PRODUCT_RE = re.compile(
    r"add(Product|Specialty)\s*\(\s*(.*?)\s*,\s*([0-9.]+)f?\s*\)",
    re.S,
)
SET_TEMP_RE = re.compile(r"setTemperature\s*\(\s*EnumTemperature\.(\w+)\s*\)")
SET_HUMID_RE = re.compile(r"setHumidity\s*\(\s*EnumHumidity\.(\w+)\s*\)")
SCIENTIFIC_RE = re.compile(r'^\t([A-Z][A-Z0-9_]*)\s*\(\s*"([^"]*)"\s*\)', re.M)
HIVE_DESC_RE = re.compile(
    r"^\t([A-Z]+)\s*\(\s*EnumHiveType\.(\w+)\s*,\s*ConfigurationMain\.get(\w+)\(\)((?:\s*\*\s*[\d.]+)?)"
    r"\s*,\s*ExtraBeeDefinition\.(\w+)\s*,\s*new\s+(WorldGenHive\w+)\s*\(\s*\)\s*\)",
    re.M,
)
HIVE_DROP_RE = re.compile(
    r"EnumHiveType\.(\w+)\.addDrops\s*\(\s*(.*?)\s*\)\s*;",
    re.S,
)
HIVE_DROP_ITEM_RE = re.compile(
    r"new\s+HiveDrop\s*\(\s*(ExtraBeeDefinition\.(\w+)|valiantSpecies)\s*,\s*([0-9.]+)\s*\)"
    r"(?:\.setIgnobleShare\s*\(\s*([0-9.]+)\s*\))?"
)
FRAME_MOD_RE = re.compile(
    r"logic\.setModifier\s*\(\s*EnumBeeModifier\.(\w+)\s*,\s*([0-9.E+-]+)f?\s*,\s*([0-9.E+-]+)f?\s*\)"
)
INDUSTRIAL_FRAME_RE = re.compile(
    r'^\t([A-Z][A-Z0-9_]*)\s*\(\s*"([^"]*)"\s*,\s*(\d+)\s*,\s*(\d+)\s*\)',
    re.M,
)
MISC_ITEM_RE = re.compile(
    r'^\t([A-Z][A-Z0-9_]*)\s*\(\s*"([^"]*)"\s*,\s*"([^"]*)"\s*\)',
    re.M,
)
SET_FX_RE = re.compile(r"ExtraBeesEffect\.(\w+)\.setFX\s*\(\s*\"([^\"]+)\"\s*\)")
CONFIG_RATE_RE = re.compile(
    r"private static float (\w+) = ([0-9.]+)F;"
)
LOCAL_SPECIES_RE = re.compile(r'registerSpecies\(ReForestry\.id\("bee_(\w+)"\)')
LOCAL_HIVE_RE = re.compile(r"^\s+([A-Z][A-Z0-9_]+)\s*\(", re.M)
LOCAL_FLOWER_RE = re.compile(r"^\s+([A-Z][A-Z0-9_]+)\s*\(", re.M)
LOCAL_EFFECT_RE = re.compile(r"public static final Identifier (\w+) = ReForestry\.id\(\"([^\"]+)\"\)")


def to_hex(value: str) -> str:
    raw = value.strip()
    if raw.startswith("0x") or raw.startswith("0X"):
        return "0x" + raw[2:].lower().zfill(6)
    return f"0x{int(raw):06x}"


def strip_comments(text: str) -> str:
    text = re.sub(r"/\*.*?\*/", lambda m: "\n" * m.group(0).count("\n"), text, flags=re.S)
    out = []
    for line in text.splitlines(True):
        if "//" in line:
            in_str = False
            escaped = False
            cut = None
            for i, ch in enumerate(line):
                if escaped:
                    escaped = False
                    continue
                if ch == "\\":
                    escaped = True
                    continue
                if ch == '"':
                    in_str = not in_str
                elif not in_str and line.startswith("//", i):
                    cut = i
                    break
            if cut is not None:
                line = line[:cut] + ("\n" if line.endswith("\n") else "")
        out.append(line)
    return "".join(out)


def find_matching(text: str, open_index: int, opener: str, closer: str) -> int:
    depth = 0
    i = open_index
    n = len(text)
    in_str = False
    escaped = False
    while i < n:
        ch = text[i]
        if in_str:
            if escaped:
                escaped = False
            elif ch == "\\":
                escaped = True
            elif ch == '"':
                in_str = False
        elif ch == '"':
            in_str = True
        elif ch == opener:
            depth += 1
        elif ch == closer:
            depth -= 1
            if depth == 0:
                return i
        i += 1
    raise ValueError(f"unbalanced {opener}{closer} at {open_index}")


def enum_constants_region(src: str) -> str:
    match = ENUM_HEADER_RE.search(src)
    if not match:
        raise ValueError("enum header not found")
    start = match.end()
    i = start
    depth = 1
    n = len(src)
    in_str = False
    escaped = False
    while i < n:
        ch = src[i]
        if in_str:
            if escaped:
                escaped = False
            elif ch == "\\":
                escaped = True
            elif ch == '"':
                in_str = False
            i += 1
            continue
        if ch == '"':
            in_str = True
        elif ch == "{":
            depth += 1
        elif ch == "}":
            depth -= 1
            if depth == 0:
                return src[start:i]
        elif ch == ";" and depth == 1:
            return src[start:i]
        i += 1
    raise ValueError("enum body not found")


def parse_enum_members(src: str) -> list[dict]:
    body = enum_constants_region(src)
    members: list[dict] = []
    i = 0
    n = len(body)
    while i < n:
        while i < n and body[i] in " \t\r\n":
            i += 1
        if i >= n:
            break
        if body.startswith("/*", i):
            i = body.find("*/", i)
            if i < 0:
                break
            i += 2
            continue
        if body.startswith("//", i):
            nl = body.find("\n", i)
            i = n if nl < 0 else nl + 1
            continue
        match = re.match(r"[A-Z][A-Z0-9_]*", body[i:])
        if not match:
            i += 1
            continue
        name = match.group(0)
        i += len(name)
        while i < n and body[i] in " \t":
            i += 1
        args = ""
        if i < n and body[i] == "(":
            end = find_matching(body, i, "(", ")")
            args = body[i + 1 : end]
            i = end + 1
        while i < n and body[i] in " \t\r\n":
            i += 1
        member_body = ""
        if i < n and body[i] == "{":
            end = find_matching(body, i, "{", "}")
            member_body = body[i + 1 : end]
            i = end + 1
        while i < n and body[i] in " \t\r\n":
            i += 1
        if i < n and body[i] == ",":
            i += 1
        members.append({"name": name, "args": args.strip(), "body": member_body})
        if i < n and body[i] == ";":
            break
    return members


def method_body(src: str, signature: str) -> str:
    idx = src.find(signature)
    if idx < 0:
        return ""
    brace = src.find("{", idx)
    if brace < 0:
        return ""
    end = find_matching(src, brace, "{", "}")
    return src[brace + 1 : end]


def parse_int_args(args: str) -> list[int]:
    if not args.strip():
        return []
    out = []
    for part in split_top_args(args):
        if part.startswith("0x") or part.startswith("0X"):
            out.append(int(part, 16))
        elif re.fullmatch(r"-?\d+", part):
            out.append(int(part))
    return out


def split_top_args(argstr: str) -> list[str]:
    args: list[str] = []
    depth = 0
    start = 0
    in_str = False
    escaped = False
    for i, ch in enumerate(argstr):
        if in_str:
            if escaped:
                escaped = False
            elif ch == "\\":
                escaped = True
            elif ch == '"':
                in_str = False
            continue
        if ch == '"':
            in_str = True
        elif ch in "({[":
            depth += 1
        elif ch in ")}]":
            depth -= 1
        elif ch == "," and depth == 0:
            part = argstr[start:i].strip()
            if part:
                args.append(part)
            start = i + 1
    part = argstr[start:].strip()
    if part:
        args.append(part)
    return args


def find_named_calls(text: str, names: tuple[str, ...]) -> list[tuple[str, list[str]]]:
    name_re = re.compile(r"\b(" + "|".join(names) + r")\s*\(")
    results = []
    i = 0
    while True:
        match = name_re.search(text, i)
        if not match:
            break
        open_i = text.find("(", match.start(1) + len(match.group(1)))
        close = find_matching(text, open_i, "(", ")")
        results.append((match.group(1), split_top_args(text[open_i + 1 : close])))
        i = close + 1
    return results


def parse_item_stack(expr: str) -> dict | None:
    text = re.sub(r"\s+", " ", expr.strip())
    stack = re.fullmatch(r"new ItemStack\(\s*(Items|Blocks)\.(\w+)\s*(?:,\s*(.+))?\)", text)
    if not stack:
        return None
    owner, field, rest = stack.group(1), stack.group(2), stack.group(3)
    count = 1
    meta = 0
    if rest:
        parts = split_top_args(rest)
        if parts:
            count = int(parts[0])
        if len(parts) > 1:
            meta = int(parts[1])
    return {
        "kind": "item_stack",
        "owner": owner,
        "field": field,
        "count": count,
        "meta": meta,
        "item": f"minecraft:{field.lower()}",
    }


def rid(path: str) -> str:
    return f"{NS}:{path}"


def species_path(enum_name: str) -> str:
    lower = enum_name.lower()
    return SPECIES_COLLISIONS.get(lower, f"bee_{lower}")


def species_id(enum_name: str) -> str:
    return rid(species_path(enum_name))


def binnie_species_uid(enum_name: str) -> str:
    return f"{BINNIE_MOD}.species.{enum_name.lower()}"


def forestry_bee_id(enum_name: str) -> str:
    return rid(f"bee_{enum_name.lower()}")


def parse_bee_ref(token: str, current: str | None = None, extra_enums: set[str] | None = None) -> dict:
    raw = re.sub(r"\s+", "", token)
    extra_enums = extra_enums or set()
    if raw.startswith("BeeDefinition."):
        enum = raw.split(".")[-1]
        return {
            "source": "BeeDefinition",
            "enum": enum,
            "binnie_uid": f"forestry.species.{enum.lower()}",
            "reforestry_id": forestry_bee_id(enum),
            "forestry": True,
        }
    if raw.startswith("ExtraBeeDefinition."):
        enum = raw.split(".")[-1]
        return {
            "source": "ExtraBeeDefinition",
            "enum": enum,
            "binnie_uid": binnie_species_uid(enum),
            "reforestry_id": species_id(enum),
            "forestry": False,
        }
    enum = raw
    if current is None and extra_enums and enum not in extra_enums:
        raise ValueError(f"bare bee ref without current species: {token!r}")
    return {
        "source": "ExtraBeeDefinition",
        "enum": enum,
        "binnie_uid": binnie_species_uid(enum),
        "reforestry_id": species_id(enum),
        "forestry": False,
    }


def parse_product_item(expr: str) -> dict:
    text = re.sub(r"\s+", " ", expr.strip())
    out: dict = {"source": text}
    comb = re.fullmatch(r"EnumHoneyComb\.(\w+)\.get\(\s*1\s*\)", text)
    if comb:
        name = comb.group(1)
        out.update({"kind": "extra_bees_comb", "enum": name, "reforestry_id": rid(f"bee_comb_{name.lower()}")})
        return out
    vanilla = re.fullmatch(r"ItemHoneyComb\.VanillaComb\.(\w+)\.get\(\s*\)", text)
    if vanilla:
        name = vanilla.group(1)
        out.update({"kind": "forestry_comb", "enum": name, "reforestry_id": rid(f"bee_comb_{name.lower()}")})
        return out
    stack = parse_item_stack(text)
    if stack:
        out.update(stack)
        return out
    forestry = re.fullmatch(r'Mods\.Forestry\.stack\(\s*"([^"]+)"\s*\)', text)
    if forestry:
        name = forestry.group(1)
        out.update({"kind": "forestry_item", "binnie_stack": name, "reforestry_id": rid(name)})
        return out
    if "entry.getKey()" in text:
        out.update({"kind": "copy_map_entry"})
        return out
    out.update({"kind": "unparsed"})
    return out


def parse_allele(expr: str) -> dict:
    text = re.sub(r"\s+", " ", expr.strip())
    out: dict = {"source": text}
    if text in ("true", "false"):
        out.update({"kind": "boolean", "value": text == "true"})
        return out
    enum_allele = re.fullmatch(r"EnumAllele\.(\w+)\.(\w+)", text)
    if enum_allele:
        out.update({"kind": "enum_allele", "group": enum_allele.group(1), "value": enum_allele.group(2)})
        return out
    flowers = re.search(r"ExtraBeesFlowers\.(\w+)\.getUID\(\)", text)
    if flowers:
        name = flowers.group(1)
        out.update({
            "kind": "extra_bees_flower",
            "enum": name,
            "binnie_uid": f"{BINNIE_MOD}.flower.{name.lower()}",
            "reforestry_id": rid(f"flower_type_{name.lower()}"),
        })
        return out
    effect = re.search(r"ExtraBeesEffect\.(\w+)\.getUID\(\)", text)
    if effect:
        name = effect.group(1)
        out.update({
            "kind": "extra_bees_effect",
            "enum": name,
            "binnie_uid": f"{BINNIE_MOD}.effect.{name.lower()}",
            "reforestry_id": rid(f"bee_effect_{name.lower()}"),
        })
        return out
    forestry_effect = re.search(r'AlleleHelper\.getAllele\(\s*"([^"]+)"\s*\)', text)
    if forestry_effect:
        uid = forestry_effect.group(1)
        path = uid.split(".")[-1]
        if path.startswith("effect"):
            path = "bee_effect_" + path[len("effect"):].lower()
        out.update({"kind": "forestry_allele", "binnie_uid": uid, "reforestry_id": rid(path)})
        return out
    allele_effects = re.fullmatch(r"AlleleEffects\.effect(\w+)", text)
    if allele_effects:
        name = allele_effects.group(1)
        out.update({
            "kind": "forestry_effect",
            "enum": name.upper() if name != "None" else "NONE",
            "reforestry_id": rid(f"bee_effect_{name.lower()}"),
        })
        return out
    out.update({"kind": "unparsed"})
    return out


def parse_comb_product(expr: str, locals_map: dict[str, str] | None = None) -> dict:
    text = re.sub(r"\s+", " ", expr.strip())
    if locals_map and text in locals_map:
        text = locals_map[text]
    out: dict = {"source": text}
    if text in ("beeswax",):
        out.update({"kind": "forestry_item", "reforestry_id": rid("beeswax")})
        return out
    if text in ("honeyDrop",):
        out.update({"kind": "forestry_item", "reforestry_id": rid("honey_drop")})
        return out
    extra_item = re.fullmatch(r"ExtraBeeItems\.(\w+)(?:\.get\(\s*1\s*\))?", text)
    if extra_item:
        name = extra_item.group(1)
        out.update({"kind": "extra_bees_misc", "enum": name, "reforestry_id": rid(name.lower())})
        return out
    drop = re.fullmatch(r"EnumHoneyDrop\.(\w+)(?:\.get\(\s*1\s*\))?", text)
    if drop:
        name = drop.group(1)
        out.update({"kind": "extra_bees_drop", "enum": name, "reforestry_id": rid(f"honey_drop_{name.lower()}")})
        return out
    propolis = re.fullmatch(r"EnumPropolis\.(\w+)(?:\.get\(\s*1\s*\))?", text)
    if propolis:
        name = propolis.group(1)
        out.update({"kind": "extra_bees_propolis", "enum": name, "reforestry_id": rid(f"propolis_{name.lower()}")})
        return out
    forestry = re.fullmatch(r'Mods\.Forestry\.stack\(\s*"([^"]+)"\s*\)', text)
    if forestry:
        name = forestry.group(1)
        out.update({"kind": "forestry_item", "binnie_stack": name, "reforestry_id": rid(name)})
        return out
    stack = parse_item_stack(text)
    if stack:
        out.update(stack)
        return out
    oredict = re.fullmatch(r'"([^"]+)"', text)
    if oredict:
        out.update({"kind": "oredict", "ore": oredict.group(1)})
        return out
    ic2 = re.search(r'Utils\.getIC2Item\(\s*"([^"]+)"\s*\)', text)
    if ic2:
        out.update({"kind": "ic2_item", "name": ic2.group(1)})
        return out
    out.update({"kind": "unparsed"})
    return out


DYE_COMB_ORDER = [
    "RED", "YELLOW", "BLUE", "GREEN", "BLACK", "WHITE", "BROWN", "ORANGE",
    "CYAN", "PURPLE", "GRAY", "LIGHTBLUE", "PINK", "LIMEGREEN", "MAGENTA", "LIGHTGRAY",
]
DYE_METAS = [1, 11, 4, 2, 0, 15, 3, 14, 6, 5, 8, 12, 9, 10, 13, 7]
DYE_REMNANT = {0: "BLACK_DYE", 1: "RED_DYE", 2: "GREEN_DYE", 3: "BROWN_DYE", 4: "BLUE_DYE", 11: "YELLOW_DYE", 15: "WHITE_DYE"}


def dye_comb_products(comb_enum: str) -> list[dict]:
    index = DYE_COMB_ORDER.index(comb_enum)
    meta = DYE_METAS[index]
    drop = DYE_COMB_ORDER[index]
    remnant = DYE_REMNANT.get(meta)
    products = [
        {"source": "honeyDrop", "kind": "forestry_item", "reforestry_id": rid("honey_drop"), "chance": 0.80, "try": False},
        {"source": "beeswax", "kind": "forestry_item", "reforestry_id": rid("beeswax"), "chance": 0.80, "try": False},
        {
            "source": f"EnumHoneyDrop.{drop}.get(1)",
            "kind": "extra_bees_drop",
            "enum": drop,
            "reforestry_id": rid(f"honey_drop_{drop.lower()}"),
            "chance": 1.00,
            "try": False,
        },
    ]
    if remnant:
        products.append({
            "source": f"ExtraBeeItems.{remnant}.get(1)",
            "kind": "extra_bees_misc",
            "enum": remnant,
            "reforestry_id": rid(remnant.lower()),
            "chance": None,
            "role": "squeezer_remnant",
            "try": False,
        })
    else:
        products.append({
            "source": f"new ItemStack(Items.DYE, 1, {meta})",
            "kind": "item_stack",
            "owner": "Items",
            "field": "DYE",
            "count": 1,
            "meta": meta,
            "item": "minecraft:dye",
            "chance": None,
            "role": "squeezer_remnant",
            "try": False,
        })
    return products


def parse_comb_products(body: str, comb_enum: str) -> tuple[list[dict], list[str]]:
    products: list[dict] = []
    copies: list[str] = []
    locals_map = {}
    for match in re.finditer(r"ItemStack\s+(\w+)\s*=\s*(.+?);", body):
        locals_map[match.group(1)] = match.group(2).strip()
    if "addDyeSubtypes" in body:
        return dye_comb_products(comb_enum), copies
    for copy in re.finditer(r"copyProducts\s*\(\s*EnumHoneyComb\.(\w+)\s*\)", body):
        copies.append(copy.group(1))
    for name, args in find_named_calls(body, ("tryAddProduct", "addProduct")):
        if len(args) < 2:
            continue
        item = parse_comb_product(args[0], locals_map)
        item["chance"] = float(args[1].rstrip("fF"))
        item["try"] = name == "tryAddProduct"
        products.append(item)
    return products, copies


def parse_mutations_in(text: str, current: str | None, extra_enums: set[str] | None = None) -> list[dict]:
    mutations = []
    for match in REGISTER_MUT_RE.finditer(text):
        parent0, parent1, result, chance, _chain, biome = match.groups()
        if "IBeeDefinition" in parent0 or "IAlleleBeeSpecies" in parent0:
            continue
        entry = {
            "parent0": parse_bee_ref(parent0, current, extra_enums),
            "parent1": parse_bee_ref(parent1, current, extra_enums),
            "chance": int(chance),
            "conditions": [],
        }
        if result:
            entry["result"] = parse_bee_ref(result, current, extra_enums)
        elif current:
            entry["result"] = parse_bee_ref(current, current, extra_enums)
        else:
            raise ValueError(f"mutation missing result: {match.group(0)}")
        entry["forestry_result"] = bool(entry["result"].get("forestry"))
        if biome:
            entry["conditions"].append({"type": "restrictBiomeType", "biome": biome})
        mutations.append(entry)
    return mutations


def load_local_ids(repo_root: Path) -> dict:
    species_file = repo_root / "src/main/java/com/leon1236/reforestry/apiculture/genetics/DefaultBeeSpecies.java"
    hive_file = repo_root / "src/main/java/com/leon1236/reforestry/apiculture/blocks/BlockHiveType.java"
    flower_file = repo_root / "src/main/java/com/leon1236/reforestry/apiculture/genetics/FlowerType.java"
    effect_file = repo_root / "src/main/java/com/leon1236/reforestry/api/apiculture/ForestryBeeEffects.java"
    comb_file = repo_root / "src/main/java/com/leon1236/reforestry/apiculture/items/EnumHoneyComb.java"
    local = {
        "species": set(),
        "hives": set(),
        "flowers": set(),
        "effects": {},
        "combs": set(),
    }
    if species_file.is_file():
        local["species"] = set(LOCAL_SPECIES_RE.findall(species_file.read_text(encoding="utf-8")))
    if hive_file.is_file():
        local["hives"] = {n.lower() for n in LOCAL_HIVE_RE.findall(hive_file.read_text(encoding="utf-8"))}
    if flower_file.is_file():
        local["flowers"] = {n.lower() for n in LOCAL_FLOWER_RE.findall(flower_file.read_text(encoding="utf-8"))}
    if effect_file.is_file():
        local["effects"] = dict(LOCAL_EFFECT_RE.findall(effect_file.read_text(encoding="utf-8")))
    if comb_file.is_file():
        local["combs"] = {n.lower() for n in LOCAL_HIVE_RE.findall(comb_file.read_text(encoding="utf-8"))}
    return local


def extract_branches(src: str) -> list[dict]:
    members = parse_enum_members(src)
    default_body = method_body(src, "private static IAllele[] getDefaultTemplate()")
    default_genome = []
    for chrom, allele in CHROMOSOME_SET_RE.findall(default_body):
        parsed = parse_allele(allele)
        parsed["chromosome"] = chrom
        default_genome.append(parsed)
    out = []
    for member in members:
        scientific = ""
        match = re.match(r'"([^"]*)"', member["args"])
        if match:
            scientific = match.group(1)
        genome = []
        for chrom, allele in CHROMOSOME_SET_RE.findall(member["body"]):
            parsed = parse_allele(allele)
            parsed["chromosome"] = chrom
            genome.append(parsed)
        out.append({
            "enum": member["name"],
            "scientific": scientific,
            "uid": member["name"].lower(),
            "empty": member["name"] == "ALLOY",
            "genome": genome,
            "uses_default_only": not genome,
        })
    return out, default_genome


def extract_species(src: str, branches: list[dict], local: dict) -> tuple[list[dict], list[str], list[dict]]:
    members = parse_enum_members(src)
    branch_by_name = {b["enum"]: b for b in branches}
    skipped_commented = []
    for match in re.finditer(r"^\t//\s*([A-Z][A-Z0-9_]*)\s*,?", src, re.M):
        skipped_commented.append(match.group(1))
    species = []
    mutations = []
    extra_enums = {member["name"] for member in members}
    for member in members:
        header = BRANCH_CTOR_RE.search(f"\t{member['name']}({member['args']})")
        if not header:
            raise ValueError(f"could not parse ExtraBeeDefinition ctor for {member['name']}: {member['args']!r}")
        enum, branch_src, branch, binomial, dominant, primary, secondary = header.groups()
        props = method_body(member["body"], "protected void setSpeciesProperties")
        alleles = method_body(member["body"], "protected void setAlleles")
        mut_body = method_body(member["body"], "protected void registerMutations")
        secret_body = method_body(member["body"], "protected boolean isSecret")
        products = []
        specialties = []
        copy_products_from = None
        if "BeeDefinition.NOBLE.getGenome()" in props:
            copy_products_from = {
                "source": "BeeDefinition",
                "enum": "NOBLE",
                "reforestry_id": forestry_bee_id("NOBLE"),
            }
        for name, args in find_named_calls(props, ("addProduct", "addSpecialty")):
            if len(args) < 2:
                continue
            try:
                chance = float(args[1].rstrip("fF"))
            except ValueError:
                continue
            item = parse_product_item(args[0])
            item["chance"] = chance
            if name == "addProduct":
                products.append(item)
            else:
                specialties.append(item)
        genome_overrides = []
        for chrom, allele in CHROMOSOME_SET_RE.findall(alleles):
            parsed = parse_allele(allele)
            parsed["chromosome"] = chrom
            genome_overrides.append(parsed)
        temp = SET_TEMP_RE.search(props)
        humid = SET_HUMID_RE.search(props)
        lower = enum.lower()
        path = species_path(enum)
        collides = lower in local["species"]
        entry = OrderedDict([
            ("enum", enum),
            ("binnie_uid", binnie_species_uid(enum)),
            ("reforestry_id", rid(path)),
            ("collision", collides),
            ("collides_with", rid(f"bee_{lower}") if collides else None),
            ("binomial", binomial),
            ("dominant", dominant == "true"),
            ("primary_color", to_hex(primary)),
            ("secondary_color", to_hex(secondary)),
            ("authority", "Binnie"),
            ("branch", branch),
            ("branch_source", branch_src),
            ("secret", "return true" in secret_body),
            ("has_effect", "setHasEffect" in props),
            ("nocturnal", "setNocturnal" in props),
            ("temperature", temp.group(1) if temp else None),
            ("humidity", humid.group(1) if humid else None),
            ("products", products),
            ("specialties", specialties),
            ("copy_products_from", copy_products_from),
            ("genome_overrides", genome_overrides),
            ("hive_found", not mut_body.strip()),
        ])
        if not collides:
            entry.pop("collides_with")
        species.append(entry)
        mutations.extend(parse_mutations_in(mut_body, enum, extra_enums))
    do_init = method_body(src, "public static void doInit()")
    mutations.extend(parse_mutations_in(do_init, None, extra_enums))
    for spec in species:
        b = branch_by_name.get(spec["branch"])
        spec["branch_empty"] = bool(b and spec["branch"] == "ALLOY")
        spec["branch_scientific"] = b["scientific"] if b else None
    return species, skipped_commented, mutations


def extract_effects(src: str, local: dict) -> list[dict]:
    members = parse_enum_members(src)
    fx = {name: particle for name, particle in SET_FX_RE.findall(src)}
    local_effect_paths = set(local["effects"].values())
    out = []
    for member in members:
        default_path = f"bee_effect_{member['name'].lower()}"
        path = EFFECT_COLLISIONS.get(member["name"].lower(), default_path)
        collision = default_path in local_effect_paths
        out.append({
            "enum": member["name"],
            "binnie_uid": f"{BINNIE_MOD}.effect.{member['name'].lower()}",
            "reforestry_id": rid(path),
            "collision": collision,
            "collides_with": rid(default_path) if collision else None,
            "dominant": True,
            "combinable": False,
            "fx": f"particles/{fx[member['name']]}" if member["name"] in fx else None,
        })
        if not collision:
            out[-1].pop("collides_with")
    return out


def extract_flowers(src: str, local: dict) -> list[dict]:
    members = parse_enum_members(src)
    acceptable = {}
    switch = method_body(src, "public List<Block> getAcceptableBlocks()")
    current = None
    for line in switch.splitlines():
        case = re.match(r"case\s+(\w+)\s*:", line.strip())
        if case:
            current = case.group(1)
            acceptable[current] = []
            continue
        if current is None:
            continue
        for block in re.findall(r"(?<![A-Za-z])Blocks\.(\w+)", line):
            acceptable[current].append(f"minecraft:{block.lower()}")
        if "getBotaniaBlock" in line:
            acceptable[current].append("botania:flower")
        if "OreDictionary.getOres(\"treeSapling\")" in line:
            acceptable[current].append("ore:treeSapling")
        if line.strip().startswith("return") and "emptyList" in line:
            acceptable[current] = acceptable.get(current, [])
    rules = []
    register = method_body(src, "public void register()")
    if "case ROCK:" in register:
        rules.append("ROCK")
    out = []
    for member in members:
        name = member["name"]
        path = f"flower_type_{name.lower()}"
        collision = name.lower() in local["flowers"]
        blocks = acceptable.get(name, [])
        entry = {
            "enum": name,
            "binnie_uid": f"{BINNIE_MOD}.flower.{name.lower()}",
            "reforestry_id": rid(path),
            "collision": collision,
            "dominant": True,
            "acceptable_blocks": blocks,
            "material_rule": name if name in ("ROCK", "LEAVES", "WOOD") else None,
        }
        if collision:
            entry["collides_with"] = rid(path)
        out.append(entry)
    return out


def extract_combs(src: str, local: dict) -> list[dict]:
    members = parse_enum_members(src)
    out = []
    copies: dict[str, list[str]] = {}
    by_name = {}
    for member in members:
        ints = parse_int_args(member["args"])
        inactive = not ints
        secondary = ints[0] if len(ints) >= 1 else 16777215
        primary = ints[1] if len(ints) >= 2 else 16777215
        products, copy_from = parse_comb_products(member["body"], member["name"])
        copies[member["name"]] = copy_from
        entry = {
            "enum": member["name"],
            "active": not inactive,
            "inactive_placeholder": inactive,
            "primary_color": to_hex(str(primary)),
            "secondary_color": to_hex(str(secondary)),
            "reforestry_id": rid(f"bee_comb_{member['name'].lower()}"),
            "products": products,
            "copy_products_from": copy_from,
        }
        out.append(entry)
        by_name[member["name"]] = entry
    for entry in out:
        resolved = []
        seen = set()
        stack = list(entry["copy_products_from"])
        while stack:
            src_name = stack.pop(0)
            if src_name in seen:
                continue
            seen.add(src_name)
            src_entry = by_name.get(src_name)
            if not src_entry:
                continue
            resolved.extend(src_entry["products"])
            stack.extend(src_entry["copy_products_from"])
        if resolved:
            entry["copied_products"] = resolved
    return out


def extract_drops(src: str) -> list[dict]:
    members = parse_enum_members(src)
    out = []
    for member in members:
        args = [p.strip() for p in member["args"].split(",")] if member["args"] else []
        inactive = not args
        primary = secondary = 16777215
        liquid = ""
        if len(args) >= 3:
            primary = int(args[0], 16) if args[0].startswith("0x") else int(args[0])
            secondary = int(args[1], 16) if args[1].startswith("0x") else int(args[1])
            liquid = args[2].strip().strip('"')
        out.append({
            "enum": member["name"],
            "active": not inactive,
            "inactive_placeholder": inactive,
            "primary_color": to_hex(str(primary)),
            "secondary_color": to_hex(str(secondary)),
            "liquid": liquid,
            "reforestry_id": rid(f"honey_drop_{member['name'].lower()}"),
        })
    return out


def extract_propolis(src: str) -> list[dict]:
    members = parse_enum_members(src)
    out = []
    for member in members:
        args = [p.strip() for p in member["args"].split(",")] if member["args"] else []
        inactive = not args
        primary = secondary = 16777215
        liquid = ""
        if len(args) >= 3:
            primary = int(args[0], 16) if args[0].startswith("0x") else int(args[0])
            secondary = int(args[1], 16) if args[1].startswith("0x") else int(args[1])
            liquid = args[2].strip().strip('"')
        out.append({
            "enum": member["name"],
            "active": not inactive,
            "inactive_placeholder": inactive,
            "primary_color": to_hex(str(primary)),
            "secondary_color": to_hex(str(secondary)),
            "liquid": liquid,
            "reforestry_id": rid(f"propolis_{member['name'].lower()}"),
        })
    return out


def extract_misc(src: str) -> list[dict]:
    out = []
    for match in MISC_ITEM_RE.finditer(src):
        enum, lang, model = match.groups()
        body_start = src.find(match.group(0))
        brace = src.find("{", body_start, body_start + len(match.group(0)) + 40)
        metal = gem = None
        if brace != -1 and src[body_start:brace + 1].count("{") and src[match.end():match.end() + 2].find("{") == 0:
            pass
        member_src = src[match.start():]
        next_enum = re.search(r"\n\t[A-Z][A-Z0-9_]*\(", member_src[1:])
        chunk = member_src[: next_enum.start() + 1] if next_enum else member_src[:400]
        metal_m = re.search(r'setMetal\(\s*"([^"]+)"\s*\)', chunk)
        gem_m = re.search(r'setGem\(\s*"([^"]+)"\s*\)', chunk)
        if metal_m:
            metal = metal_m.group(1)
        if gem_m:
            gem = gem_m.group(1)
        out.append({
            "enum": enum,
            "lang_key": lang,
            "model_path": model,
            "reforestry_id": rid(model),
            "metal": metal,
            "gem": gem,
            "ore_gated": metal is not None or gem is not None,
        })
    return out


def extract_frames(src: str) -> list[dict]:
    members = parse_enum_members(src)
    out = []
    for member in members:
        ints = parse_int_args(member["args"])
        max_damage = ints[0] if ints else 240
        mods = []
        for name, value, max_value in FRAME_MOD_RE.findall(member["body"]):
            mods.append({"stat": name.lower(), "value": float(value), "max": float(max_value)})
        out.append({
            "enum": member["name"],
            "binnie_registry": f"hive_frame.{member['name'].lower()}",
            "reforestry_id": rid(f"hive_frame_{member['name'].lower()}"),
            "max_damage": max_damage,
            "modifiers": mods,
        })
    return out


def extract_industrial_frames(src: str) -> list[dict]:
    members = parse_enum_members(src)
    out = []
    header = {m.group(1): m for m in INDUSTRIAL_FRAME_RE.finditer(src)}
    for member in members:
        match = header.get(member["name"])
        display = match.group(2) if match else member["name"]
        wear = int(match.group(3)) if match else None
        power = int(match.group(4)) if match else None
        mods = {}
        for stat, assign in (
            ("territoryMod", "territory"),
            ("mutationMod", "mutation"),
            ("lifespanMod", "lifespan"),
            ("productionMod", "production"),
            ("floweringMod", "flowering"),
        ):
            found = re.search(rf"{stat}\s*=\s*([0-9.]+)f?", member["body"])
            if found:
                mods[assign] = float(found.group(1))
        flags = {
            "lighted": "lighted = true" in member["body"],
            "sunlight": "sunlight = true" in member["body"],
            "rain": "rain = true" in member["body"],
        }
        out.append({
            "enum": member["name"],
            "display": display,
            "wear": wear,
            "power": power,
            "modifiers": mods,
            "flags": flags,
            "skipped": True,
            "skip_reason": "Wave 7 skip: industrial frames",
        })
    return out


def extract_alveary(src: str) -> list[dict]:
    members = parse_enum_members(src)
    out = []
    for member in members:
        clazz = re.search(r"(\w+)::new", member["args"])
        out.append({
            "enum": member["name"],
            "class": clazz.group(1) if clazz else None,
            "reforestry_id": rid(f"alveary_{member['name'].lower()}"),
            "binnie_meta": None,
        })
    for i, entry in enumerate(out):
        entry["binnie_meta"] = i
    return out


def extract_hives(hive_type_src: str, desc_src: str, block_reg_src: str, config_src: str, local: dict) -> list[dict]:
    types = [m["name"] for m in parse_enum_members(hive_type_src)]
    rates = dict(CONFIG_RATE_RE.findall(config_src))
    desc = {m.group(1): m for m in HIVE_DESC_RE.finditer(desc_src)}
    drops_by_type: dict[str, list[dict]] = {name: [] for name in types}
    for hive, body in HIVE_DROP_RE.findall(block_reg_src):
        for drop in HIVE_DROP_ITEM_RE.finditer(body):
            raw, extra_enum, chance, ignoble = drop.groups()
            forestry = raw == "valiantSpecies"
            enum = "VALIANT" if forestry else extra_enum
            drops_by_type[hive].append({
                "source": "BeeDefinition" if forestry else "ExtraBeeDefinition",
                "enum": enum,
                "reforestry_id": forestry_bee_id(enum) if forestry else species_id(enum),
                "chance": float(chance),
                "ignoble_share": float(ignoble) if ignoble else None,
            })
    placement = {
        "WATER": "in water above sand/clay/ground/rock",
        "ROCK": "replaceable stone with air on one horizontal side",
        "NETHER": "netherrack wall in nether biomes",
        "MARBLE": "ore:stoneMarble with at least one non-marble face",
    }
    out = []
    for name in types:
        match = desc.get(name)
        config_method = match.group(3) if match else None
        rate_field = {
            "WaterHiveRate": "waterHiveRate",
            "MarbleHiveRate": "marbleHiveRate",
            "RockHiveRate": "rockHiveRate",
            "NetherHiveRate": "netherHiveRate",
        }.get(config_method or "", "")
        config_rate = float(rates.get(rate_field, 2.0))
        multiplier = 1.0
        if match and match.group(4).strip():
            multiplier = float(match.group(4).replace("*", "").strip())
        species_enum = match.group(5) if match else None
        worldgen = match.group(6) if match else None
        default_path = f"beehive_{name.lower()}"
        path = HIVE_COLLISIONS.get(name.lower(), default_path)
        collision = name.lower() in local["hives"]
        entry = {
            "enum": name,
            "binnie_block": f"{BINNIE_MOD}:hive",
            "binnie_meta": types.index(name),
            "reforestry_id": rid(path),
            "collision": collision,
            "species_enum": species_enum,
            "species_reforestry_id": species_id(species_enum) if species_enum else None,
            "config_rate": config_rate,
            "chance_multiplier": multiplier,
            "gen_chance": config_rate * multiplier,
            "worldgen_class": worldgen,
            "placement": placement.get(name),
            "nether_biome_only": name == "NETHER",
            "drops": drops_by_type.get(name, []),
        }
        if collision:
            entry["collides_with"] = rid(default_path)
        out.append(entry)
    return out


def extract_ectoplasm(src: str) -> dict:
    hardness = re.search(r"setHardness\s*\(\s*([0-9.]+)f\s*\)", src)
    opacity = re.search(r"setLightOpacity\s*\(\s*(\d+)\s*\)", src)
    drop = re.search(r"return Items\.(\w+);", src)
    qty = re.search(r"return \(rand\.nextInt\((\d+)\) == 0\) \? 1 : 0;", src)
    return {
        "binnie_registry": f"{BINNIE_MOD}:ectoplasm",
        "reforestry_id": rid("ectoplasm"),
        "extends": "BlockWeb",
        "hardness": float(hardness.group(1)) if hardness else None,
        "light_opacity": int(opacity.group(1)) if opacity else None,
        "drop": f"minecraft:{drop.group(1).lower()}" if drop else None,
        "drop_chance": f"1/{qty.group(1)}" if qty else None,
    }


def build_id_map(species, effects, flowers, hives, items) -> dict:
    def rows(entries, extra=None):
        out = []
        for entry in entries:
            row = {
                "enum": entry.get("enum"),
                "binnie_uid": entry.get("binnie_uid") or entry.get("binnie_registry") or entry.get("binnie_block"),
                "reforestry_id": entry.get("reforestry_id"),
                "collision": bool(entry.get("collision")),
            }
            if entry.get("collides_with"):
                row["collides_with"] = entry["collides_with"]
            if extra:
                row.update(extra(entry))
            out.append(row)
        return out

    return {
        "namespace": NS,
        "policy": {
            "species_path": "bee_<enum.lower>",
            "collisions": SPECIES_COLLISIONS,
            "effect_collisions": EFFECT_COLLISIONS,
            "hive_collisions": HIVE_COLLISIONS,
            "keep_spelling": ["ARTIC"],
            "skip_empty_alloy_branch": True,
            "skip_inactive_combs": True,
        },
        "species": rows(species),
        "effects": rows(effects),
        "flowers": rows(flowers),
        "hives": rows(hives),
        "combs": [
            {
                "enum": c["enum"],
                "reforestry_id": c["reforestry_id"],
                "active": c["active"],
                "skipped": c["inactive_placeholder"],
            }
            for c in items["combs"]
        ],
        "frames": [
            {"enum": f["enum"], "binnie_uid": f["binnie_registry"], "reforestry_id": f["reforestry_id"]}
            for f in items["frames"]
        ],
        "alveary": [
            {"enum": a["enum"], "reforestry_id": a["reforestry_id"]}
            for a in items["alveary"]
        ],
    }


def build_extract_md(counts, mismatches, species, mutations, effects, flowers, items, hives, skipped_commented) -> str:
    forestry_result = sum(1 for m in mutations if m["forestry_result"])
    active_combs = sum(1 for c in items["combs"] if c["active"])
    inactive_combs = [c["enum"] for c in items["combs"] if not c["active"]]
    inactive_drops = [d["enum"] for d in items["drops"] if not d["active"]]
    inactive_propolis = [p["enum"] for p in items["propolis"] if not p["active"]]
    collisions = [s for s in species if s["collision"]]
    effect_collisions = [e for e in effects if e["collision"]]
    hive_collisions = [h for h in hives if h["collision"]]
    lines = [
        "# Extra Bees extract (EB0)",
        "",
        "Research extract from Binnie `extrabees/` only. Not playable content. Re-run:",
        "",
        "```",
        "python3 tools/extract_extra_bees.py --clone MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie --out queries --apply",
        "```",
        "",
        "## Counts",
        "",
        "| Item | Extracted | Expected | Source |",
        "|---|---:|---:|---|",
        f"| Species | {counts['species']} | {EXPECTED['species']} | `ExtraBeeDefinition` |",
        f"| Mutations | {counts['mutations']} | {EXPECTED['mutations']} | `ExtraBeeDefinition.registerMutations` + `doInit` |",
        f"| Forestry-result mutations | {forestry_result} | {EXPECTED['forestry_result_mutations']} | `doInit` 3-arg `registerMutation` |",
        f"| Effects | {counts['effects']} | {EXPECTED['effects']} | `ExtraBeesEffect` |",
        f"| Flower types | {counts['flowers']} | {EXPECTED['flowers']} | `ExtraBeesFlowers` |",
        f"| Alveary parts | {counts['alveary']} | {EXPECTED['alveary']} | `ExtraBeeMachines` |",
        f"| Hive types | {counts['hives']} | {EXPECTED['hives']} | `EnumHiveType` / `BinnieHiveDescription` |",
        f"| Frames | {counts['frames']} | {EXPECTED['frames']} | `EnumHiveFrame` |",
        f"| Active combs | {active_combs} | — | `EnumHoneyComb` 2-arg ctor |",
        f"| Inactive combs | {len(inactive_combs)} | — | `EnumHoneyComb` no-arg ctor |",
        "",
    ]
    if mismatches:
        lines += ["## Count mismatches", ""]
        for row in mismatches:
            lines.append(f"- **{row['item']}**: extracted {row['extracted']} vs expected {row['expected']}. {row['why']}")
        lines.append("")
    else:
        lines += ["All locked counts match `queries/wave7-plan.md`.", ""]
    lines += [
        "## Id collisions (local CE)",
        "",
        "Locked remaps:",
    ]
    for spec in collisions:
        lines.append(f"- `{spec['binnie_uid']}` → `{spec['reforestry_id']}` (CE `{spec.get('collides_with')}`)")
    for effect in effect_collisions:
        lines.append(f"- effect `{effect['enum']}` → `{effect['reforestry_id']}` (CE `{effect.get('collides_with')}`)")
    for hive in hive_collisions:
        lines.append(f"- hive `{hive['enum']}` → `{hive['reforestry_id']}` (CE `{hive.get('collides_with')}`)")
    if not collisions and not effect_collisions and not hive_collisions:
        lines.append("- none")
    lines += [
        "",
        "## Skip list (Wave 7 / extract policy)",
        "",
        "| Skip | Why | Source |",
        "|---|---|---|",
        "| Empty ALLOY branch | No species; commented INVAR/BRONZE/BRASS/STEEL | `ExtraBeeBranchDefinition.ALLOY`, `ExtraBeeDefinition` alloy comment block |",
        f"| Commented species {', '.join(skipped_commented) or '(none)'} | Not registered | `ExtraBeeDefinition` `//NAME` lines |",
        f"| Inactive combs {', '.join(inactive_combs)} | No-arg ctor placeholders (`active = false`) | `EnumHoneyComb()` |",
        f"| Inactive honey drops {', '.join(inactive_drops)} | No-arg ctor placeholders | `EnumHoneyDrop()` |",
        f"| Inactive propolis {', '.join(inactive_propolis)} | No-arg ctor placeholders | `EnumPropolis()` |",
        "| Bee dictionary | Wave 7 skip | `ItemBeeDictionary` registry `dictionary` |",
        "| Honey crystal | Wave 7 skip (IC2 electric item) | `ItemHoneyCrystal` registry `honey_crystal` |",
        f"| Industrial frames ({len(items['industrial_frames'])}) | Wave 7 skip | `EnumIndustrialFrame` |",
        "| Binnie genetics / botany / Extra Trees | Out of EB0 scope | parse `extrabees/` only |",
        "",
        "## ARTIC spelling",
        "",
        "Binnie enum is `ARTIC` (not ARCTIC). Extract keeps `reforestry:bee_artic` / `extrabees.species.artic`.",
        "",
        "## Forestry-result mutations",
        "",
        f"{forestry_result} mutations result in CE bees (`BeeDefinition.*`). Those need GP0c `modifySpecies` before they can be registered from Extra Bees. Listed in `extra-bees-mutations.json` with `forestry_result: true`.",
        "",
        "## Files",
        "",
        "- `queries/extra-bees-id-map.json`",
        "- `queries/extra-bees-species.json`",
        "- `queries/extra-bees-mutations.json`",
        "- `queries/extra-bees-effects.json`",
        "- `queries/extra-bees-flowers.json`",
        "- `queries/extra-bees-items.json`",
        "- `queries/extra-bees-hives.json`",
        "",
    ]
    return "\n".join(lines)


def write_json(path: Path, data, apply: bool) -> None:
    text = json.dumps(data, indent=2, ensure_ascii=False) + "\n"
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text, encoding="utf-8")


def resolve_java(clone: Path) -> Path:
    root = clone / "extrabees" / "src" / "main" / "java" / "binnie" / "extrabees"
    if not root.is_dir():
        print(f"error: Extra Bees java not found under {root}", file=sys.stderr)
        sys.exit(1)
    return root


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--clone", required=True, help="ACGaming-Binnie clone root")
    parser.add_argument("--out", required=True, help="output directory (usually queries)")
    parser.add_argument("--apply", action="store_true", help="write JSON/md (default: print counts only)")
    args = parser.parse_args()

    clone = Path(args.clone)
    out_dir = Path(args.out)
    java = resolve_java(clone)
    repo_root = Path(__file__).resolve().parent.parent
    local = load_local_ids(repo_root)

    def read(rel: str) -> str:
        path = java / rel
        if not path.is_file():
            print(f"error: missing {path}", file=sys.stderr)
            sys.exit(1)
        return path.read_text(encoding="utf-8")

    definition = read("genetics/ExtraBeeDefinition.java")
    branch_src = read("genetics/ExtraBeeBranchDefinition.java")
    effect_src = read("genetics/effect/ExtraBeesEffect.java")
    flower_src = read("genetics/ExtraBeesFlowers.java")
    comb_src = read("items/types/EnumHoneyComb.java")
    drop_src = read("items/types/EnumHoneyDrop.java")
    propolis_src = read("items/types/EnumPropolis.java")
    misc_src = read("items/types/ExtraBeeItems.java")
    frame_src = read("items/types/EnumHiveFrame.java")
    industrial_src = read("items/types/EnumIndustrialFrame.java")
    machine_src = read("machines/ExtraBeeMachines.java")
    hive_type_src = read("blocks/type/EnumHiveType.java")
    hive_desc_src = read("worldgen/BinnieHiveDescription.java")
    block_reg_src = read("init/BlockRegister.java")
    config_src = read("utils/config/ConfigurationMain.java")
    ectoplasm_src = read("blocks/BlockEctoplasm.java")
    dictionary_src = read("items/ItemBeeDictionary.java")
    crystal_src = read("items/ItemHoneyCrystal.java")

    branches, default_genome = extract_branches(branch_src)
    species, skipped_commented, mutations = extract_species(definition, branches, local)
    effects = extract_effects(effect_src, local)
    flowers = extract_flowers(flower_src, local)
    combs = extract_combs(comb_src, local)
    drops = extract_drops(drop_src)
    propolis = extract_propolis(propolis_src)
    misc = extract_misc(misc_src)
    frames = extract_frames(frame_src)
    industrial = extract_industrial_frames(industrial_src)
    alveary = extract_alveary(machine_src)
    hives = extract_hives(hive_type_src, hive_desc_src, block_reg_src, config_src, local)
    ectoplasm = extract_ectoplasm(ectoplasm_src)

    items = {
        "combs": combs,
        "drops": drops,
        "propolis": propolis,
        "misc": misc,
        "frames": frames,
        "industrial_frames": industrial,
        "alveary": alveary,
        "ectoplasm": ectoplasm,
        "skipped": {
            "dictionary": {
                "binnie_registry": f"{BINNIE_MOD}:dictionary",
                "class": "ItemBeeDictionary",
                "reason": "Wave 7 skip",
            },
            "honey_crystal": {
                "binnie_registry": f"{BINNIE_MOD}:honey_crystal",
                "class": "ItemHoneyCrystal",
                "reason": "Wave 7 skip (IC2)",
            },
            "industrial_frames": {
                "count": len(industrial),
                "class": "EnumIndustrialFrame",
                "reason": "Wave 7 skip",
            },
            "inactive_combs": [c["enum"] for c in combs if not c["active"]],
            "alloy_branch": {"enum": "ALLOY", "commented_species": list(ALLOY_COMMENTED)},
            "commented_species": skipped_commented,
        },
    }

    counts = {
        "species": len(species),
        "mutations": len(mutations),
        "forestry_result_mutations": sum(1 for m in mutations if m["forestry_result"]),
        "effects": len(effects),
        "flowers": len(flowers),
        "alveary": len(alveary),
        "hives": len(hives),
        "frames": len(frames),
        "active_combs": sum(1 for c in combs if c["active"]),
        "inactive_combs": sum(1 for c in combs if not c["active"]),
        "misc": len(misc),
        "drops": len(drops),
        "propolis": len(propolis),
    }
    mismatches = []
    for key, expected in EXPECTED.items():
        extracted = counts[key]
        if extracted != expected:
            why = "extracted from source class; not padded."
            mismatches.append({"item": key, "extracted": extracted, "expected": expected, "why": why})

    artic = next((s for s in species if s["enum"] == "ARTIC"), None)
    alloy_species = [s for s in species if s["branch"] == "ALLOY"]

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] Extra Bees extract from {clone}")
    for key in ("species", "mutations", "forestry_result_mutations", "effects", "flowers", "alveary", "hives", "frames"):
        marker = "OK" if counts[key] == EXPECTED.get(key, counts[key]) else "MISMATCH"
        expected = EXPECTED.get(key, "-")
        print(f"  {key}: {counts[key]} (expected {expected}) {marker}")
    print(f"  active_combs: {counts['active_combs']} inactive_combs: {counts['inactive_combs']}")
    print(f"  ARTIC spelling: {artic['reforestry_id'] if artic else 'MISSING'}")
    print(f"  ALLOY species extracted: {len(alloy_species)} (expected 0)")
    print(f"  commented species skipped: {', '.join(skipped_commented)}")
    if mismatches:
        print("count mismatches vs wave7-plan.md:")
        for row in mismatches:
            print(f"  {row['item']}: {row['extracted']} != {row['expected']}")

    species_json = {
        "source_class": "binnie.extrabees.genetics.ExtraBeeDefinition",
        "branch_class": "binnie.extrabees.genetics.ExtraBeeBranchDefinition",
        "count": len(species),
        "default_genome": default_genome,
        "branches": branches,
        "commented_skipped": skipped_commented,
        "species": species,
    }
    mutations_json = {
        "source_class": "binnie.extrabees.genetics.ExtraBeeDefinition",
        "count": len(mutations),
        "forestry_result_count": counts["forestry_result_mutations"],
        "mutations": mutations,
    }
    effects_json = {
        "source_class": "binnie.extrabees.genetics.effect.ExtraBeesEffect",
        "count": len(effects),
        "effects": effects,
    }
    flowers_json = {
        "source_class": "binnie.extrabees.genetics.ExtraBeesFlowers",
        "count": len(flowers),
        "flowers": flowers,
    }
    hives_json = {
        "source_class": "binnie.extrabees.blocks.type.EnumHiveType",
        "worldgen_class": "binnie.extrabees.worldgen.BinnieHiveDescription",
        "count": len(hives),
        "hives": hives,
    }
    id_map = build_id_map(species, effects, flowers, hives, items)
    extract_md = build_extract_md(
        counts, mismatches, species, mutations, effects, flowers, items, hives, skipped_commented
    )

    if args.apply:
        write_json(out_dir / "extra-bees-id-map.json", id_map, True)
        write_json(out_dir / "extra-bees-species.json", species_json, True)
        write_json(out_dir / "extra-bees-mutations.json", mutations_json, True)
        write_json(out_dir / "extra-bees-effects.json", effects_json, True)
        write_json(out_dir / "extra-bees-flowers.json", flowers_json, True)
        write_json(out_dir / "extra-bees-items.json", items, True)
        write_json(out_dir / "extra-bees-hives.json", hives_json, True)
        (out_dir / "extra-bees-extract.md").write_text(extract_md, encoding="utf-8")
        print(f"written under {out_dir}")
    else:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
