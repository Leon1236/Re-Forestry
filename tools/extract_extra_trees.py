#!/usr/bin/env python3
"""Extract Extra Trees woods/fruits/species/mutations from Binnie extratrees/ into queries JSON.

Usage: python3 tools/extract_extra_trees.py --clone <binnie> --out queries/extra-trees-extract --apply
Without --apply: print counts only.
"""
from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

NS = "reforestry"
BINNIE_MOD = "extratrees"
CE_WOOD_OVERLAP = {
    "Fir": "FIR",
    "Beech": "BEECH",
    "Elm": "ELM",
    "Pear": "PEAR",
    "Olive": "OLIVE",
    "Gingko": "GINKGO",
}
KEEP_BINOMIAL = {
    "AcornOak": "tree_acorn_oak",
}
BINOMIAL_FUZZY = {
    ("ginkgo", "biloba"): ("ginkgo", "bilboa"),
    ("cocous", "nucifera"): ("cocos", "nucifera"),
}
VANILLA_TREE_MAP = {
    "Oak": "tree_oak",
    "Birch": "tree_birch",
    "Cherry": "tree_hill_cherry",
    "Lemon": "tree_lemon",
    "Maple": "tree_maple",
    "Plum": "tree_plum",
    "Chestnut": "tree_chestnut",
    "Walnut": "tree_walnut",
    "Lime": "tree_lime",
    "Willow": "tree_willow",
    "Ebony": "tree_ebony",
    "Balsa": "tree_balsa",
    "Jungle": "tree_jungle",
    "Kapok": "tree_kapok",
    "Teak": "tree_teak",
    "Spruce": "tree_spruce",
    "Pine": "tree_pine",
    "Larch": "tree_larch",
    "Mahogony": "tree_mahogany",
    "Wenge": "tree_wenge",
}
DEFAULT_FRUIT_REUSE = {
    "Apple": "fruit_apple",
    "Pear": "fruit_pear",
    "Orange": "fruit_orange",
    "Olive": "fruit_olive",
    "Coconut": "fruit_coconut",
}
ID_COLLISION_REMAP = {
    "Fir": "tree_et_fir",
    "Elm": "tree_et_elm",
    "Lime": "tree_et_lime",
}
EXPECTED = {
    "species": 97,
    "fruits": 59,
    "plank_woods": 36,
    "overlap_woods": 6,
    "new_plank_woods": 30,
    "binomial_skip": 9,
    "mutations": 97,
    "moths": 22,
    "moth_mutations": 0,
}
ENUM_HEADER_RE = re.compile(r"public\s+enum\s+(\w+)\b[^{]*\{", re.S)
COLOR_RE = re.compile(r"new\s+Color\s*\(\s*(0x[0-9a-fA-F]+|\d+)\s*\)")
LOCAL_SPECIES_RE = re.compile(
    r'registerSpecies\(\s*ReForestry\.id\("([^"]+)"\)\s*,\s*"([^"]+)"\s*,\s*"([^"]+)"'
)
LOCAL_WOOD_RE = re.compile(r"^\s+([A-Z][A-Z0-9_]*)\(", re.M)
LOCAL_FRUIT_RE = re.compile(
    r'public static final IFruit (\w+) = new \w+\(\s*ReForestry\.id\("([^"]+)"\)'
)
CHROMOSOME_SET_RE = re.compile(
    r"template\.set\s*\(\s*EnumTreeChromosome\.(\w+)\s*,\s*(.*?)\s*\)\s*;",
    re.S,
)
FRUIT_FAMILY_RE = re.compile(
    r'addFruitFamily\s*\(\s*(?:AlleleManager\.alleleRegistry\.getFruitFamily\s*\(\s*"([^"]+)"\s*\)|ETFruitFamily\.(\w+))\s*\)'
)
WORLDGEN_RE = re.compile(r"return\s+new\s+((?:WorldGen[\w.]+)|BinnieWorldGenTree)\s*\(")
ADD_PRODUCT_RE = re.compile(
    r"addProduct\s*\(\s*(.*?)\s*,\s*([0-9.]+)f?\s*\)",
    re.S,
)


def rid(path: str) -> str:
    return f"{NS}:{path}"


def snake(name: str) -> str:
    text = name.replace("-", "_")
    text = re.sub(r"([a-z0-9])([A-Z])", r"\1_\2", text)
    text = re.sub(r"([A-Z]+)([A-Z][a-z])", r"\1_\2", text)
    return text.replace("__", "_").lower()


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
        match = re.match(r"[A-Z][A-Za-z0-9_]*", body[i:])
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


def method_body(src: str, signature: str) -> str:
    idx = src.find(signature)
    if idx < 0:
        return ""
    brace = src.find("{", idx)
    if brace < 0:
        return ""
    end = find_matching(src, brace, "{", "}")
    return src[brace + 1 : end]


def unquote(token: str) -> str:
    text = token.strip()
    if len(text) >= 2 and text[0] == '"' and text[-1] == '"':
        return text[1:-1]
    return text


def parse_color(expr: str) -> str | None:
    match = COLOR_RE.search(expr)
    if not match:
        return None
    return to_hex(match.group(1))


def write_json(path: Path, data, apply: bool) -> None:
    text = json.dumps(data, indent=2, ensure_ascii=False) + "\n"
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text, encoding="utf-8")


def resolve_java(clone: Path) -> Path:
    root = clone / "extratrees" / "src" / "main" / "java" / "binnie" / "extratrees"
    if not root.is_dir():
        print(f"error: Extra Trees java not found under {root}", file=sys.stderr)
        sys.exit(1)
    return root


def load_local(repo_root: Path) -> dict:
    species_src = (
        repo_root / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultTreeSpecies.java"
    ).read_text(encoding="utf-8")
    wood_src = (
        repo_root / "src/main/java/com/leon1236/reforestry/arboriculture/ForestryWoodType.java"
    ).read_text(encoding="utf-8")
    fruit_src = (
        repo_root / "src/main/java/com/leon1236/reforestry/arboriculture/genetics/DefaultFruits.java"
    ).read_text(encoding="utf-8")
    species = []
    by_binomial = {}
    by_id = {}
    for sid, genus, binomial in LOCAL_SPECIES_RE.findall(species_src):
        row = {
            "reforestry_id": rid(sid),
            "path": sid,
            "genus": genus,
            "binomial": binomial,
        }
        species.append(row)
        by_binomial[(genus.lower(), binomial.lower())] = row
        by_id[sid] = row
    woods = []
    for name in LOCAL_WOOD_RE.findall(wood_src):
        if name in {"VALUES", "ForestryWoodType"}:
            continue
        woods.append({"enum": name, "path": name.lower()})
    fruits = []
    for const, path in LOCAL_FRUIT_RE.findall(fruit_src):
        fruits.append({"const": const, "path": path, "reforestry_id": rid(path)})
    return {
        "species": species,
        "species_by_binomial": by_binomial,
        "species_by_id": by_id,
        "woods": woods,
        "wood_enums": {w["enum"] for w in woods},
        "fruits": fruits,
        "fruit_paths": {f["path"] for f in fruits},
    }


def parse_wood_ref(expr: str) -> dict:
    text = re.sub(r"\s+", "", expr)
    if text.startswith("EnumETLog."):
        name = text.split(".", 1)[1]
        return {"kind": "et_log", "enum": name, "path": snake(name)}
    if text.startswith("EnumForestryWoodType.") or text.startswith("EnumVanillaWoodType."):
        name = text.split(".", 1)[1]
        return {"kind": "forestry_or_vanilla", "enum": name, "path": name.lower()}
    if text.startswith("EnumShrubLog."):
        return {"kind": "shrub_log", "enum": "INSTANCE", "path": "shrub_log"}
    if text.startswith("ExtraTreePlanks."):
        name = text.split(".", 1)[1]
        return {"kind": "et_plank", "enum": name, "path": snake(name)}
    if text.startswith("ForestryPlanks."):
        name = text.split(".", 1)[1]
        return {"kind": "forestry_plank", "enum": name, "path": name.lower()}
    if text.startswith("VanillaPlanks."):
        name = text.split(".", 1)[1]
        return {"kind": "vanilla_plank", "enum": name, "path": name.lower()}
    return {"kind": "unparsed", "source": expr}


def extract_woods(plank_src: str, log_src: str, shrub_src: str, local: dict) -> dict:
    planks = []
    for member in parse_enum_members(plank_src):
        args = split_top_args(member["args"])
        color = to_hex(args[0]) if args else None
        overlap = CE_WOOD_OVERLAP.get(member["name"])
        path = snake(member["name"])
        if overlap:
            reuse = f"ForestryWoodType.{overlap}"
            new = False
        else:
            reuse = None
            new = True
        planks.append(
            {
                "enum": member["name"],
                "path": path,
                "color": color,
                "ce_overlap": overlap is not None,
                "forestry_wood_type": overlap,
                "reuse": reuse,
                "register": new,
                "reforestry_id_note": f"ExtraTreeWoodType.{member['name']}" if new else f"reuse {reuse}",
            }
        )
    logs = []
    for member in parse_enum_members(log_src):
        args = split_top_args(member["args"])
        display = unquote(args[0]) if args else member["name"]
        plank = parse_wood_ref(args[1]) if len(args) > 1 else None
        has_products = True
        if len(args) > 2:
            has_products = args[2] != "false"
        overlap = CE_WOOD_OVERLAP.get(member["name"])
        logs.append(
            {
                "enum": member["name"],
                "display": display,
                "uid": display.lower().replace(" ", "_"),
                "path": snake(member["name"]),
                "plank": plank,
                "has_products": has_products,
                "ce_overlap": overlap is not None,
                "forestry_wood_type": overlap,
            }
        )
    shrubs = []
    for member in parse_enum_members(shrub_src):
        shrubs.append(
            {
                "enum": member["name"],
                "path": "shrub_log",
                "register": True,
                "note": "EnumShrubLog.INSTANCE — extra log, not a plank wood",
            }
        )
    overlap_planks = [p for p in planks if p["ce_overlap"]]
    new_planks = [p for p in planks if p["register"]]
    return {
        "source_class": "binnie.extratrees.wood.planks.ExtraTreePlanks",
        "log_class": "binnie.extratrees.wood.EnumETLog",
        "shrub_class": "binnie.extratrees.wood.EnumShrubLog",
        "plank_count": len(planks),
        "overlap_count": len(overlap_planks),
        "new_plank_count": len(new_planks),
        "shrub_log_count": len(shrubs),
        "planks": planks,
        "logs": logs,
        "shrub_logs": shrubs,
        "ce_overlap_woods": [
            {
                "et_plank": p["enum"],
                "forestry_wood_type": p["forestry_wood_type"],
                "note": "do not re-register; ExtraTreeWoodType skips these",
            }
            for p in overlap_planks
        ],
        "log_only_variants": [l for l in logs if not l["has_products"]],
    }


def parse_fruit_product(expr: str) -> dict:
    text = re.sub(r"\s+", " ", expr.strip())
    food = re.fullmatch(r"Food\.(\w+)\.get\(\s*(\d+)\s*\)", text)
    if food:
        return {
            "kind": "et_food",
            "enum": food.group(1),
            "count": int(food.group(2)),
            "reforestry_id": rid(snake(food.group(1))),
        }
    stack = re.fullmatch(r"new ItemStack\(\s*Items\.(\w+)\s*\)", text)
    if stack:
        return {"kind": "minecraft", "item": f"minecraft:{stack.group(1).lower()}", "count": 1}
    return {"kind": "unparsed", "source": text}


def extract_fruits(src: str, local: dict) -> dict:
    fruits = []
    commented = []
    if re.search(r"Papayimar", src) and re.search(r"//\s*,\s*Papayimar", src):
        commented.append(
            {
                "enum": "Papayimar",
                "reason": "commented out of AlleleETFruitDefinition; Food.PAPAYIMAR still exists",
            }
        )
    src = strip_comments(src)
    for member in parse_enum_members(src):
        args = split_top_args(member["args"])
        ident = unquote(args[0]) if args else snake(member["name"])
        path = f"fruit_{snake(member['name'])}"
        reuse = DEFAULT_FRUIT_REUSE.get(member["name"])
        if reuse and reuse in local["fruit_paths"]:
            register = False
            reuse_id = rid(reuse)
        else:
            register = True
            reuse_id = None
        ripening = None
        pod = None
        family = None
        if len(args) >= 6 and "FruitSprite" in args[4]:
            ripening = {
                "time": int(args[1]),
                "unripe": to_hex(args[2]),
                "ripe": to_hex(args[3]),
                "sprite": args[4].split(".")[-1],
            }
            family = args[5].split(".")[-1]
            kind = "ripening"
        elif len(args) >= 3 and "FruitPod" in args[1]:
            pod = args[1].split(".")[-1]
            family = args[2].split(".")[-1]
            kind = "pod"
        else:
            kind = "unknown"
        products = []
        for call in ADD_PRODUCT_RE.findall(member["body"]):
            products.append({"item": parse_fruit_product(call[0]), "chance": float(call[1])})
        fruits.append(
            {
                "enum": member["name"],
                "binnie_name": ident,
                "binnie_uid": f"{BINNIE_MOD}.fruit.{ident}",
                "path": path,
                "reforestry_id": rid(reuse) if reuse_id else rid(path),
                "kind": kind,
                "family": family,
                "ripening": ripening,
                "pod": pod,
                "products": products,
                "default_fruits_reuse": reuse_id is not None,
                "reuse": reuse_id,
                "register": register,
            }
        )
    return {
        "source_class": "binnie.extratrees.genetics.AlleleETFruitDefinition",
        "count": len(fruits),
        "new_count": sum(1 for f in fruits if f["register"]),
        "reuse_count": sum(1 for f in fruits if f["default_fruits_reuse"]),
        "commented_skipped": commented,
        "fruits": fruits,
    }


def parse_tree_genome(body: str) -> list[dict]:
    alleles = []
    for chrom, expr in CHROMOSOME_SET_RE.findall(body):
        text = re.sub(r"\s+", " ", expr.strip())
        row = {"chromosome": chrom, "source": text}
        fruit = re.fullmatch(r"AlleleETFruitDefinition\.(\w+)\.getAllele\(\)", text)
        if fruit:
            name = fruit.group(1)
            reuse = DEFAULT_FRUIT_REUSE.get(name)
            row.update(
                {
                    "kind": "et_fruit",
                    "enum": name,
                    "reforestry_id": rid(reuse if reuse else f"fruit_{snake(name)}"),
                }
            )
        else:
            fa = re.fullmatch(r"ForestryAllele\.(\w+)\.(\w+)\.getAllele\(\)", text)
            if fa:
                row.update({"kind": "forestry_allele", "group": fa.group(1), "value": fa.group(2)})
            else:
                row.update({"kind": "unparsed"})
        alleles.append(row)
    return alleles


def parse_fruit_families(body: str) -> list[str]:
    out = []
    for forestry, et in FRUIT_FAMILY_RE.findall(body):
        out.append(forestry if forestry else f"extratrees.{et.lower()}")
    return out


def match_local_binomial(genus: str, binomial: str, local: dict) -> dict | None:
    key = (genus.lower(), binomial.lower())
    hit = local["species_by_binomial"].get(key)
    if hit:
        return {"match": "exact", **hit}
    fuzzy = BINOMIAL_FUZZY.get(key)
    if fuzzy:
        hit = local["species_by_binomial"].get(fuzzy)
        if hit:
            return {"match": "spelling", "et": key, "ce": fuzzy, **hit}
    return None


def extract_species(src: str, local: dict) -> dict:
    species = []
    for member in parse_enum_members(src):
        args = split_top_args(member["args"])
        genus = unquote(args[0]) if args else ""
        binomial = unquote(args[1]) if len(args) > 1 else ""
        leaf_type = args[2].split(".")[-1] if len(args) > 2 else None
        leaf = parse_color(args[3]) if len(args) > 3 else None
        pollinated = parse_color(args[4]) if len(args) > 4 else None
        sapling = args[5].split(".")[-1] if len(args) > 5 else None
        wood = parse_wood_ref(args[6]) if len(args) > 6 else None
        wood_color = parse_color(args[7]) if len(args) > 7 else None
        worldgen = None
        wg = WORLDGEN_RE.search(member["body"])
        if wg:
            worldgen = wg.group(1)
        path = f"tree_{snake(member['name'])}"
        binnie_uid = member["name"].lower()
        local_hit = match_local_binomial(genus, binomial, local)
        keep = member["name"] in KEEP_BINOMIAL
        skip = local_hit is not None and not keep
        collision = None
        if skip:
            reforestry_id = local_hit["reforestry_id"]
            action = "skip_reuse"
        elif keep:
            reforestry_id = rid(KEEP_BINOMIAL[member["name"]])
            action = "keep_acorn_oak"
        elif member["name"] in ID_COLLISION_REMAP:
            reforestry_id = rid(ID_COLLISION_REMAP[member["name"]])
            collision = {
                "would_be": rid(path),
                "collides_with": rid(path),
                "remap": reforestry_id,
                "reason": "snake id already used by DefaultTreeSpecies with a different binomial",
            }
            action = "id_collision_remap"
        elif path in local["species_by_id"] and not skip:
            reforestry_id = rid(f"tree_et_{snake(member['name'])}")
            collision = {
                "would_be": rid(path),
                "collides_with": rid(path),
                "remap": reforestry_id,
                "reason": "snake id already used by DefaultTreeSpecies with a different binomial",
            }
            action = "id_collision_remap"
        else:
            reforestry_id = rid(path)
            action = "register"
        species.append(
            {
                "enum": member["name"],
                "binnie_uid": binnie_uid,
                "genus": genus,
                "binomial": binomial,
                "path": path if action == "register" else reforestry_id.split(":", 1)[1],
                "reforestry_id": reforestry_id,
                "action": action,
                "ce_binomial_match": local_hit,
                "id_collision": collision,
                "leaf_type": leaf_type,
                "leaf_color": leaf,
                "leaf_pollinated_color": pollinated,
                "sapling_type": sapling,
                "wood": wood,
                "wood_color": wood_color,
                "worldgen": worldgen,
                "has_fruit_leaves": "hasFruitLeaves" in member["body"] and "return true" in member["body"],
                "fruit_families": parse_fruit_families(member["body"]),
                "genome": parse_tree_genome(member["body"]),
            }
        )
    skips = [s for s in species if s["action"] == "skip_reuse"]
    keeps = [s for s in species if s["action"] == "keep_acorn_oak"]
    collisions = [s for s in species if s["action"] == "id_collision_remap"]
    return {
        "source_class": "binnie.extratrees.genetics.ETTreeDefinition",
        "count": len(species),
        "register_count": sum(1 for s in species if s["action"] == "register"),
        "binomial_skip_count": len(skips),
        "id_collision_count": len(collisions),
        "acorn_oak": keeps[0] if keeps else None,
        "binomial_skips": [
            {
                "enum": s["enum"],
                "et_binomial": f"{s['genus']} {s['binomial']}",
                "reuse": s["reforestry_id"],
                "match": s["ce_binomial_match"]["match"] if s["ce_binomial_match"] else None,
            }
            for s in skips
        ],
        "id_collisions": [
            {
                "enum": s["enum"],
                "et_binomial": f"{s['genus']} {s['binomial']}",
                "remap": s["reforestry_id"],
                "collides_with": s["id_collision"]["collides_with"] if s["id_collision"] else None,
            }
            for s in collisions
        ],
        "species": species,
    }


def forestry_tree_ref(vanilla_name: str) -> dict:
    path = VANILLA_TREE_MAP.get(vanilla_name)
    if path is None:
        path = "tree_" + snake(vanilla_name)
    return {
        "source": "getVanilla",
        "vanilla_name": vanilla_name,
        "binnie_uid": f"forestry.tree{vanilla_name}",
        "reforestry_id": rid(path),
        "forestry": True,
        "typo": vanilla_name == "Mahogony",
    }


def et_tree_ref(enum_name: str, species_by_enum: dict) -> dict:
    spec = species_by_enum.get(enum_name)
    if spec:
        return {
            "source": "ETTreeDefinition",
            "enum": enum_name,
            "binnie_uid": spec["binnie_uid"],
            "reforestry_id": spec["reforestry_id"],
            "forestry": False,
            "action": spec["action"],
        }
    return {
        "source": "ETTreeDefinition",
        "enum": enum_name,
        "binnie_uid": enum_name.lower(),
        "reforestry_id": rid(f"tree_{snake(enum_name)}"),
        "forestry": False,
        "action": "unresolved",
    }


def parse_mutation_parent(token: str, aliases: dict, species_by_enum: dict) -> dict:
    text = re.sub(r"\s+", "", token)
    vanilla = re.fullmatch(r'getVanilla\("(\w+)"\)', text)
    if vanilla:
        return forestry_tree_ref(vanilla.group(1))
    if text in aliases:
        return forestry_tree_ref(aliases[text])
    et = re.fullmatch(r"ETTreeDefinition\.(\w+)\.getSpecies\(\)", text)
    if et:
        return et_tree_ref(et.group(1), species_by_enum)
    return {"source": "unparsed", "token": token}


def extract_mutations(src: str, species: list[dict]) -> dict:
    body = method_body(src, "public static void init()")
    aliases = {}
    for match in re.finditer(
        r'(?:final\s+)?IAlleleTreeSpecies\s+(\w+)\s*=\s*\(IAlleleTreeSpecies\)\s*getVanilla\("(\w+)"\)',
        body,
    ):
        aliases[match.group(1)] = match.group(2)
    species_by_enum = {s["enum"]: s for s in species}
    mutations = []
    for match in re.finditer(
        r"new\s+ExtraTreeMutation\s*\((.*?)\)(\s*(?:\.\s*set\w+\s*\([^;]*\))*)\s*;",
        body,
        re.S,
    ):
        args = split_top_args(match.group(1))
        extras = match.group(2) or ""
        if len(args) < 4:
            continue
        parent0 = parse_mutation_parent(args[0], aliases, species_by_enum)
        parent1 = parse_mutation_parent(args[1], aliases, species_by_enum)
        result = parse_mutation_parent(args[2], aliases, species_by_enum)
        chance = int(args[3])
        height = None
        h = re.search(r"setHeight\s*\(\s*(\d+)\s*\)", extras)
        if h:
            height = int(h.group(1))
        mutations.append(
            {
                "parent0": parent0,
                "parent1": parent1,
                "result": result,
                "chance": chance,
                "height": height,
                "result_skipped": result.get("action") == "skip_reuse",
            }
        )
    return {
        "source_class": "binnie.extratrees.genetics.ExtraTreeMutation",
        "count": len(mutations),
        "vanilla_name_map": {k: rid(v) for k, v in VANILLA_TREE_MAP.items()},
        "mutations": mutations,
    }


def extract_moths(src: str) -> dict:
    moths = []
    for member in parse_enum_members(src):
        args = split_top_args(member["args"])
        display = unquote(args[0]) if args else member["name"].replace("_", " ")
        scientific = unquote(args[1]) if len(args) > 1 else ""
        color = to_hex(args[2]) if len(args) > 2 else None
        parts = scientific.split()
        genus = parts[0] if parts else ""
        binomial = parts[1] if len(parts) > 1 else ""
        binnie_uid = "extrabutterflies.species." + member["name"].lower().replace("_", "")
        moths.append(
            {
                "enum": member["name"],
                "display": display,
                "scientific": scientific,
                "genus": genus,
                "binomial": binomial,
                "color": color,
                "binnie_uid": binnie_uid,
                "texture": "butterflies/" + member["name"].lower(),
                "reforestry_id": rid("moth_" + snake(member["name"])),
            }
        )
    register_body = method_body(src, "protected void registerMutations()")
    mutation_calls = []
    if register_body.strip() and "registerMutation" in register_body:
        mutation_calls = re.findall(r"registerMutation\s*\(", register_body)
    return {
        "source_class": "binnie.extratrees.genetics.ButterflySpecies",
        "count": len(moths),
        "mutations": [],
        "mutation_count": len(mutation_calls),
        "note": "registerMutations() is empty; no Binnie moth mutations",
        "moths": moths,
    }


def extract_foods(src: str) -> dict:
    foods = []
    for member in parse_enum_members(src):
        args = split_top_args(member["args"])
        hunger = int(args[0]) if args and re.fullmatch(r"-?\d+", args[0]) else None
        foods.append(
            {
                "enum": member["name"],
                "hunger": hunger,
                "binnie_model": member["name"].lower(),
                "reforestry_id": rid(snake(member["name"])),
            }
        )
    return {
        "source_class": "binnie.extratrees.items.Food",
        "count": len(foods),
        "foods": foods,
    }


def extract_fluid_enum(src: str, source_class: str, group: str) -> list[dict]:
    fluids = []
    for member in parse_enum_members(src):
        args = split_top_args(member["args"])
        ident = unquote(args[0]) if args else snake(member["name"])
        color = None
        if len(args) > 1 and re.fullmatch(r"\d+", args[1]):
            color = to_hex(args[1])
        path = ident.replace(".", "_")
        row = {
            "enum": member["name"],
            "group": group,
            "binnie_ident": ident,
            "color": color,
            "reforestry_id": rid(path),
        }
        if group == "juice" and len(args) >= 5:
            row["transparency"] = float(args[2])
            row["squeezing"] = unquote(args[3])
            row["ore_food"] = unquote(args[4])
        if group in {"alcohol", "liqueur", "spirit"} and len(args) >= 4:
            row["transparency"] = float(args[2])
            row["abv"] = float(args[3].replace("F", ""))
        if group == "misc" and len(args) >= 3:
            row["transparency"] = float(args[2])
        fluids.append(row)
    return fluids


def extract_fluids(paths: dict) -> dict:
    juices = extract_fluid_enum(paths["juice"], "binnie.extratrees.liquid.Juice", "juice")
    alcohols = extract_fluid_enum(paths["alcohol"], "binnie.extratrees.liquid.Alcohol", "alcohol")
    liqueurs = extract_fluid_enum(paths["liqueur"], "binnie.extratrees.liquid.Liqueur", "liqueur")
    spirits = extract_fluid_enum(paths["spirit"], "binnie.extratrees.liquid.Spirit", "spirit")
    misc = extract_fluid_enum(paths["misc"], "binnie.extratrees.liquid.MiscFluid", "misc")
    extra = extract_fluid_enum(paths["extra"], "binnie.extratrees.items.ExtraTreeLiquid", "tree_liquid")
    return {
        "juice_class": "binnie.extratrees.liquid.Juice",
        "alcohol_class": "binnie.extratrees.liquid.Alcohol",
        "liqueur_class": "binnie.extratrees.liquid.Liqueur",
        "spirit_class": "binnie.extratrees.liquid.Spirit",
        "misc_class": "binnie.extratrees.liquid.MiscFluid",
        "extra_class": "binnie.extratrees.items.ExtraTreeLiquid",
        "counts": {
            "juice": len(juices),
            "alcohol": len(alcohols),
            "liqueur": len(liqueurs),
            "spirit": len(spirits),
            "misc": len(misc),
            "tree_liquid": len(extra),
        },
        "juices": juices,
        "alcohols": alcohols,
        "liqueurs": liqueurs,
        "spirits": spirits,
        "misc": misc,
        "tree_liquids": extra,
    }


def extract_machines(machine_src: str, module_src: str, design_src: str | None) -> dict:
    machines = []
    for member in parse_enum_members(machine_src):
        body = member["args"] + "\n" + member["body"]
        name = member["name"]
        if name in {"Lumbermill", "Press", "BREWERY", "Distillery"}:
            bucket = "shipped"
            reason = "registered in ExtraTreeMachine and crafted in ModuleMachine.doInit"
        elif name == "Nursery":
            bucket = "never"
            reason = "enum member returns null (TODO); never shipped"
        elif name in {"Woodworker", "Panelworker", "Glassworker"}:
            bucket = "et-d"
            reason = "designer; Wave 7 ET-D deferred"
        else:
            bucket = "unknown"
            reason = "unclassified"
        if "return null" in body and name == "Nursery":
            bucket = "never"
        machines.append(
            {
                "enum": name,
                "path": snake(name),
                "bucket": bucket,
                "reason": reason,
            }
        )
    infuser_in_enum = any(m["enum"] == "Infuser" for m in machines)
    designs = []
    if design_src:
        designs = [m["name"] for m in parse_enum_members(design_src)]
    recipes = re.findall(r'addRecipe\("(\w+)"', module_src)
    return {
        "source_class": "binnie.extratrees.machines.ExtraTreeMachine",
        "module_class": "binnie.extratrees.modules.ModuleMachine",
        "machines": machines,
        "craft_recipes": recipes,
        "infuser_in_enum": infuser_in_enum,
        "design_class": "binnie.design.EnumDesign",
        "design_count": len(designs),
        "designs_sample": designs[:12],
    }


def extract_misc_items(src: str) -> list[dict]:
    items = []
    for member in parse_enum_members(src):
        args = split_top_args(member["args"])
        ident = unquote(args[0]) if args else snake(member["name"])
        items.append(
            {
                "enum": member["name"],
                "binnie_name": ident,
                "reforestry_id": rid(ident if ident == snake(ident) else snake(member["name"])),
            }
        )
    return items


def build_extract_md(counts, mismatches, woods, fruits, species, mutations, moths, foods, fluids, machines, items) -> str:
    lines = [
        "# Extra Trees extract (ET0)",
        "",
        "Research extract from Binnie `extratrees/` (design module counted only to bound ET-D). Not playable content. Re-run:",
        "",
        "```",
        "python3 tools/extract_extra_trees.py --clone MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie --out queries/extra-trees-extract --apply",
        "```",
        "",
        "Layout: all JSON/md for this stage live under `queries/extra-trees-extract/` (not flat `queries/extra-trees-*.json`).",
        "",
        "## Counts",
        "",
        "| Item | Extracted | Expected | Source |",
        "|---|---:|---:|---|",
        f"| Tree species | {counts['species']} | {EXPECTED['species']} | `ETTreeDefinition` |",
        f"| Binomial skip/reuse | {counts['binomial_skip']} | {EXPECTED['binomial_skip']} | same genus+epithet as `DefaultTreeSpecies` |",
        f"| Fruits | {counts['fruits']} | {EXPECTED['fruits']} | `AlleleETFruitDefinition` |",
        f"| Plank woods | {counts['plank_woods']} | {EXPECTED['plank_woods']} | `ExtraTreePlanks` |",
        f"| CE overlap woods | {counts['overlap_woods']} | {EXPECTED['overlap_woods']} | Fir/Beech/Elm/Pear/Olive/Gingko |",
        f"| New plank woods | {counts['new_plank_woods']} | {EXPECTED['new_plank_woods']} | skip 6 overlap |",
        f"| Shrub log | {counts['shrub_log']} | 1 | `EnumShrubLog` |",
        f"| Mutations | {counts['mutations']} | {EXPECTED['mutations']} | `ExtraTreeMutation.init` |",
        f"| Moths | {counts['moths']} | {EXPECTED['moths']} | `ButterflySpecies` |",
        f"| Moth mutations | {counts['moth_mutations']} | {EXPECTED['moth_mutations']} | empty `registerMutations` |",
        f"| Foods | {counts['foods']} | — | `Food` |",
        f"| Juices | {fluids['counts']['juice']} | — | `Juice` |",
        f"| Alcohols | {fluids['counts']['alcohol']} | — | `Alcohol` |",
        f"| Liqueurs | {fluids['counts']['liqueur']} | — | `Liqueur` |",
        f"| Spirits | {fluids['counts']['spirit']} | — | `Spirit` |",
        f"| Misc fluids | {fluids['counts']['misc']} | — | `MiscFluid` |",
        f"| Tree liquids | {fluids['counts']['tree_liquid']} | — | `ExtraTreeLiquid` |",
        "",
    ]
    if mismatches:
        lines += ["Count mismatches vs `queries/wave7-plan.md`:", ""]
        for row in mismatches:
            lines.append(f"- {row['item']}: extracted {row['extracted']} != expected {row['expected']} ({row['why']})")
        lines.append("")
    else:
        lines.append("All locked counts match `queries/wave7-plan.md`.")
        lines.append("")
    lines += [
        "## AcornOak",
        "",
        "Binnie `AcornOak` is `quercus` / `robur` — the same binomial as CE `tree_oak`. Wave 7 keeps it as `reforestry:tree_acorn_oak` so Extra Trees still has a distinct breeding species. It is **not** one of the 9 skip/reuse rows.",
        "",
        "## Source quirks (not padded)",
        "",
        "- `AlleleETFruitDefinition.Plantain` ctor name is `platain` (Binnie typo); Re-Forestry id uses the enum: `fruit_plantain`.",
        "- `getVanilla(\"Mahogony\")` is Binnie’s spelling; remaps to `reforestry:tree_mahogany`.",
        "- `Gingko` / `cocous` / `acer ubrum` (`RedMaple`) keep Binnie spelling in extract; CE overlap uses `GINKGO` / `cocos` / `tree_ginkgo`.",
        "- `RoseGum` and `SwampGum` share `eucalyptus grandis` inside Extra Trees; both stay as separate species.",
        "- Commented fruit `Papayimar` is not in the 59; `Food.PAPAYIMAR` still exists.",
        "",
        "## Files",
        "",
        "- `queries/extra-trees-extract/woods.json`",
        "- `queries/extra-trees-extract/fruits.json`",
        "- `queries/extra-trees-extract/species.json`",
        "- `queries/extra-trees-extract/mutations.json`",
        "- `queries/extra-trees-extract/moths.json`",
        "- `queries/extra-trees-extract/foods.json`",
        "- `queries/extra-trees-extract/fluids.json`",
        "- `queries/extra-trees-extract/machines.md`",
        "- `queries/extra-trees-extract/overlap.md`",
        "",
    ]
    return "\n".join(lines)


def build_overlap_md(woods, fruits, species, local) -> str:
    lines = [
        "# Extra Trees overlap with Re-Forestry arboriculture",
        "",
        "Do not re-register these. ET1 woods use `ExtraTreeWoodType` for the 30 new planks + shrub log only.",
        "",
        "## Woods (6)",
        "",
        "| ExtraTreePlanks | ForestryWoodType | Note |",
        "|---|---|---|",
    ]
    for row in woods["ce_overlap_woods"]:
        note = "Binnie spelling Gingko → CE GINKGO" if row["et_plank"] == "Gingko" else "same wood family"
        lines.append(f"| {row['et_plank']} | {row['forestry_wood_type']} | {note} |")
    lines += [
        "",
        "`EnumETLog` also has log-only variants (no plank products): "
        + ", ".join(l["enum"] for l in woods["log_only_variants"])
        + ".",
        "",
        "## Species binomial skip/reuse (9)",
        "",
        "| ET enum | ET binomial | Reuse | Match |",
        "|---|---|---|---|",
    ]
    for row in species["binomial_skips"]:
        lines.append(
            f"| {row['enum']} | `{row['et_binomial']}` | `{row['reuse']}` | {row['match']} |"
        )
    acorn = species.get("acorn_oak")
    lines += [
        "",
        "## AcornOak (keep)",
        "",
    ]
    if acorn:
        lines.append(
            f"`{acorn['enum']}` is `{acorn['genus']} {acorn['binomial']}` like `tree_oak`, but Wave 7 **keeps** `{acorn['reforestry_id']}`."
        )
    lines += [
        "",
        "## Id collisions (not binomial skips)",
        "",
        "Snake of the Binnie enum would collide with an existing `tree_*` that has a **different** binomial. Remap like Extra Bees `bee_eb_*`.",
        "",
        "| ET enum | ET binomial | Would be | Remap |",
        "|---|---|---|---|",
    ]
    for row in species["id_collisions"]:
        lines.append(
            f"| {row['enum']} | `{row['et_binomial']}` | `{row['collides_with']}` | `{row['remap']}` |"
        )
    if not species["id_collisions"]:
        lines.append("| — | — | — | — |")
    lines += [
        "",
        "Lime is Persian lime (`citrus latifolia`), not CE silver lime (`tilia tomentosa` / `tree_lime`). "
        "Fir is silver fir (`abies alba`); CE `tree_fir` is balsam fir (reused by `BalsamFir`). "
        "Elm is English elm (`ulmus procera`); CE `tree_elm` is wych elm (`ulmus glabra`).",
        "",
        "## DefaultFruits reuse",
        "",
        "| ET fruit | Reuse |",
        "|---|---|",
    ]
    for fruit in fruits["fruits"]:
        if fruit["default_fruits_reuse"]:
            lines.append(f"| {fruit['enum']} | `{fruit['reforestry_id']}` |")
    lines += [
        "",
        "Lemon stays CE-only (`fruit_lemon`). Extra Trees has KeyLime/Lime/Citron, not a Lemon fruit allele.",
        "",
        "## Skip list (Wave 7 / extract policy)",
        "",
        "| Skip | Why | Source |",
        "|---|---|---|",
        "| 6 overlap woods | Already `ForestryWoodType` FIR/BEECH/ELM/PEAR/OLIVE/GINKGO | `ExtraTreePlanks` |",
        "| 9 duplicate species | Same binomial as `DefaultTreeSpecies` (Gingko/Coconut spelling-normalized) | `ETTreeDefinition` |",
        "| Infuser | Code under `machines/infuser/` but **not** in `ExtraTreeMachine`; never shipped | not a stage |",
        "| Nursery | Enum supplier returns null (TODO) | never shipped, not a stage |",
        "| Designer / stained glass / multi-fence | ET-D deferred | `Woodworker`/`Panelworker`/`Glassworker` + `binnie.design` |",
        "| Kitchen bottle rack | `ModuleKitchen` TODO (`blockKitchen = Blocks.AIR`) | optional ET-K after ET5 |",
        "| Tree/moth databases | Wave 7 skip | `ModuleTreeDatabase` / `ModuleMothDatabase` |",
        "| Commented fruit Papayimar | Not in `AlleleETFruitDefinition.values()` | comment `// , Papayimar(...)` |",
        "| Mail | Out of scope | — |",
        "",
        "## `getVanilla` → local tree ids",
        "",
        "Binnie `forestry.tree` + CamelCase (`forestry.treeCherry`) remaps to CE 1.21.1 / Re-Forestry `tree_*`:",
        "",
        "| getVanilla | Binnie uid | Re-Forestry |",
        "|---|---|---|",
    ]
    for name, path in VANILLA_TREE_MAP.items():
        note = " (Binnie typo Mahogony)" if name == "Mahogony" else ""
        extra = " — 1.12 Cherry is today's hill cherry, not vanilla `tree_cherry`" if name == "Cherry" else note
        lines.append(f"| {name} | `forestry.tree{name}` | `reforestry:{path}`{extra} |")
    lines.append("")
    return "\n".join(lines)


def build_machines_md(machines: dict) -> str:
    lines = [
        "# Extra Trees machines (ET0)",
        "",
        "Parsed from `ExtraTreeMachine` + `ModuleMachine`. Design module counted only to bound **ET-D**.",
        "",
        "## Shipped (ET4)",
        "",
        "Crafted in `ModuleMachine.doInit`: " + ", ".join(f"`{r}`" for r in machines["craft_recipes"] if r in {"lumbermill", "press", "brewery", "distillery"}) + ".",
        "",
        "| Enum | Id | Stage |",
        "|---|---|---|",
    ]
    for m in machines["machines"]:
        if m["bucket"] == "shipped":
            lines.append(f"| {m['enum']} | `{snake(m['enum'])}` | ET4 |")
    lines += [
        "",
        "## Stub / never shipped (not stages)",
        "",
        "| Item | Why |",
        "|---|---|",
        "| Infuser | Java exists under `machines/infuser/` (`InfuserMachine` uid `infuser`) but **is not an `ExtraTreeMachine` constant**, so it was never registered. Wave 7: never. |",
        "| Nursery | `ExtraTreeMachine.Nursery` supplier returns `null` with a TODO pointing at `PackageNursery`. Tile class exists. Wave 7: never. |",
        "",
        "## ET-D deferred",
        "",
        "Designer machines (gated on carpentry module): Woodworker, Panelworker, Glassworker.",
        f"Binnie `design` `EnumDesign` has **{machines['design_count']}** patterns — that is the size bound for ET-D (stained glass / multi-fence / carpentry). Do not start ET-D in Wave 7.",
        "",
        "Carpentry crafts (`woodworker`, `panelworker`, `glassworker`) only run if the carpentry module is enabled.",
        "",
        "## Optional ET-K",
        "",
        "`ModuleKitchen.registerItemsAndBlocks` is a TODO; `blockKitchen = Blocks.AIR`. Bottle rack after ET5 if wanted.",
        "",
        "## Other skips",
        "",
        "- `ItemArboristDatabase` / `ItemMothDatabase` — Wave 7 skip (databases).",
        "- Hops (`BlockHops` / `ItemHops`) belong with ET5 foods, not machines.",
        "",
        "## Enum dump",
        "",
        "| Enum | Bucket | Reason |",
        "|---|---|---|",
    ]
    for m in machines["machines"]:
        lines.append(f"| {m['enum']} | {m['bucket']} | {m['reason']} |")
    lines.append("")
    return "\n".join(lines)


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--clone", required=True, help="ACGaming-Binnie clone root")
    parser.add_argument("--out", required=True, help="output directory (queries/extra-trees-extract)")
    parser.add_argument("--apply", action="store_true", help="write JSON/md (default: print counts only)")
    args = parser.parse_args()

    clone = Path(args.clone)
    out_dir = Path(args.out)
    java = resolve_java(clone)
    repo_root = Path(__file__).resolve().parent.parent
    local = load_local(repo_root)
    design_path = clone / "design" / "src" / "main" / "java" / "binnie" / "design" / "EnumDesign.java"

    def read(rel: str) -> str:
        path = java / rel
        if not path.is_file():
            print(f"error: missing {path}", file=sys.stderr)
            sys.exit(1)
        return path.read_text(encoding="utf-8")

    plank_src = strip_comments(read("wood/planks/ExtraTreePlanks.java"))
    log_src = strip_comments(read("wood/EnumETLog.java"))
    shrub_src = strip_comments(read("wood/EnumShrubLog.java"))
    fruit_src = read("genetics/AlleleETFruitDefinition.java")
    species_src = strip_comments(read("genetics/ETTreeDefinition.java"))
    mutation_src = strip_comments(read("genetics/ExtraTreeMutation.java"))
    moth_src = strip_comments(read("genetics/ButterflySpecies.java"))
    food_src = strip_comments(read("items/Food.java"))
    machine_src = strip_comments(read("machines/ExtraTreeMachine.java"))
    module_src = strip_comments(read("modules/ModuleMachine.java"))
    items_src = strip_comments(read("items/ExtraTreeItems.java"))
    fluid_paths = {
        "juice": strip_comments(read("liquid/Juice.java")),
        "alcohol": strip_comments(read("liquid/Alcohol.java")),
        "liqueur": strip_comments(read("liquid/Liqueur.java")),
        "spirit": strip_comments(read("liquid/Spirit.java")),
        "misc": strip_comments(read("liquid/MiscFluid.java")),
        "extra": strip_comments(read("items/ExtraTreeLiquid.java")),
    }
    design_src = strip_comments(design_path.read_text(encoding="utf-8")) if design_path.is_file() else None

    woods = extract_woods(plank_src, log_src, shrub_src, local)
    fruits = extract_fruits(fruit_src, local)
    species = extract_species(species_src, local)
    mutations = extract_mutations(mutation_src, species["species"])
    moths = extract_moths(moth_src)
    foods = extract_foods(food_src)
    fluids = extract_fluids(fluid_paths)
    machines = extract_machines(machine_src, module_src, design_src)
    items = extract_misc_items(items_src)
    foods["misc_items"] = items

    counts = {
        "species": species["count"],
        "binomial_skip": species["binomial_skip_count"],
        "fruits": fruits["count"],
        "plank_woods": woods["plank_count"],
        "overlap_woods": woods["overlap_count"],
        "new_plank_woods": woods["new_plank_count"],
        "shrub_log": woods["shrub_log_count"],
        "mutations": mutations["count"],
        "moths": moths["count"],
        "moth_mutations": moths["mutation_count"],
        "foods": foods["count"],
    }
    mismatches = []
    for key, expected in EXPECTED.items():
        extracted = counts.get(key)
        if extracted is None:
            continue
        if extracted != expected:
            mismatches.append(
                {
                    "item": key,
                    "extracted": extracted,
                    "expected": expected,
                    "why": "extracted from source class; not padded.",
                }
            )

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] Extra Trees extract from {clone}")
    for key in (
        "species",
        "binomial_skip",
        "fruits",
        "plank_woods",
        "overlap_woods",
        "new_plank_woods",
        "mutations",
        "moths",
        "moth_mutations",
    ):
        marker = "OK" if counts[key] == EXPECTED.get(key, counts[key]) else "MISMATCH"
        expected = EXPECTED.get(key, "-")
        print(f"  {key}: {counts[key]} (expected {expected}) {marker}")
    print(f"  shrub_log: {counts['shrub_log']}")
    print(f"  foods: {counts['foods']}")
    print(f"  fluids: {fluids['counts']}")
    print(f"  DefaultFruits reuse: {fruits['reuse_count']}")
    print(f"  AcornOak: {species['acorn_oak']['reforestry_id'] if species['acorn_oak'] else 'MISSING'}")
    print(f"  binomial skips: {', '.join(s['enum'] for s in species['binomial_skips'])}")
    print(f"  id collisions: {', '.join(s['enum'] for s in species['id_collisions']) or '(none)'}")
    if mismatches:
        print("count mismatches vs wave7-plan.md:")
        for row in mismatches:
            print(f"  {row['item']}: {row['extracted']} != {row['expected']}")

    extract_md = build_extract_md(
        counts, mismatches, woods, fruits, species, mutations, moths, foods, fluids, machines, items
    )
    overlap_md = build_overlap_md(woods, fruits, species, local)
    machines_md = build_machines_md(machines)

    if args.apply:
        out_dir.mkdir(parents=True, exist_ok=True)
        write_json(out_dir / "woods.json", woods, True)
        write_json(out_dir / "fruits.json", fruits, True)
        write_json(out_dir / "species.json", species, True)
        write_json(out_dir / "mutations.json", mutations, True)
        write_json(out_dir / "moths.json", moths, True)
        write_json(out_dir / "foods.json", foods, True)
        write_json(out_dir / "fluids.json", fluids, True)
        (out_dir / "extract.md").write_text(extract_md, encoding="utf-8")
        (out_dir / "overlap.md").write_text(overlap_md, encoding="utf-8")
        (out_dir / "machines.md").write_text(machines_md, encoding="utf-8")
        print(f"written under {out_dir}")
    else:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
