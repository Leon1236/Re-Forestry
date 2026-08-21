#!/usr/bin/env python3
"""Copy CE 1.21.1 recipe-unlock advancements (forestry: → reforestry:). Usage: python3 tools/extract_recipe_advancements.py --root . --apply"""

import argparse
import json
import shutil
import sys
from collections import Counter
from pathlib import Path

from reference_repos import ROOT, latest_clones
from rename_namespace import rewrite_text

OLD_NS = "forestry"
NEW_NS = "reforestry"
CE_REPO = "thedarkcolour-ForestryCE"
CE_FALLBACK = ROOT / "MarkDown_Maker" / "Finished_github_clone" / "2026-08-17" / "thedarkcolour-ForestryCE"

MAIL_TOKENS = (
    "mailbox",
    "stamps",
    "letters",
    "catalogue",
    "trade_station",
    "stamp_collector",
    "paper_from_letters",
    "mail",
    "stamp",
    "letter",
)

VANILLA_RECIPE_PARENT = "minecraft:recipes/root"

RECIPE_PATH_REMAP = {
    "thermionic_fabricator": "fabricator",
    "sturdy_casing": "sturdy_machine",
    "apiarists_backpack": "apiarist_bag",
    "arborists_backpack": "arborist_bag",
    "lepidopterists_backpack": "lepidopterist_bag",
    "adventurer_backpack": "adventurer_bag",
    "builder_backpack": "builder_bag",
    "digger_backpack": "digger_bag",
    "forester_backpack": "forester_bag",
    "hunter_backpack": "hunter_bag",
    "miner_backpack": "miner_bag",
    "bee_smoker": "smoker",
    "spectacles": "naturalist_helmet",
    "pickaxe_kit": "kit_pickaxe",
    "shovel_kit": "kit_shovel",
    "refractory_capsule": "refractory",
    "apiarists_chest": "bee_chest",
    "arborists_chest": "tree_chest",
    "impregnated_frame": "frame_impregnated",
    "untreated_frame": "frame_untreated",
    "apiarists_hat": "apiarist_helmet",
    "apiarists_shirt": "apiarist_chest",
    "apiarists_pants": "apiarist_legs",
    "apiarists_shoes": "apiarist_boots",
    "survivalists_axe": "bronze_axe",
    "survivalists_hoe": "bronze_hoe",
    "survivalists_pickaxe": "bronze_pickaxe",
    "survivalists_shovel": "bronze_shovel",
    "survivalists_sword": "bronze_sword",
}

WOOD_TOKEN_REMAP = (
    ("giant_sequoia", "giganteum"),
    ("sour_cherry", "hill_cherry"),
    ("camelthorn", "acacia_desert"),
    ("zebrano", "zebrawood"),
    ("lemon", "citrus"),
)


def ce121_generated() -> Path:
    found = latest_clones().get(CE_REPO)
    if found is None or not found.is_dir():
        found = CE_FALLBACK
    return found / "src" / "generated" / "resources"


def local_recipe_ids(recipe_root: Path) -> set[str]:
    ids = set()
    if not recipe_root.is_dir():
        return ids
    for path in recipe_root.rglob("*.json"):
        rel = path.relative_to(recipe_root).as_posix()
        if not rel.endswith(".json"):
            continue
        ids.add(f"{NEW_NS}:{rel[:-5]}")
    return ids


def recipe_id_from_advancement(data: dict) -> str | None:
    rewards = (data.get("rewards") or {}).get("recipes") or []
    if rewards:
        return rewards[0]
    for crit in (data.get("criteria") or {}).values():
        recipe = (crit.get("conditions") or {}).get("recipe")
        if recipe:
            return recipe
    return None


def namespaced_recipe_id(recipe_id: str) -> str:
    ns, sep, path = recipe_id.partition(":")
    if sep and ns == OLD_NS:
        return f"{NEW_NS}:{path}"
    return recipe_id


def remap_path(path: str) -> str:
    if path in RECIPE_PATH_REMAP:
        return RECIPE_PATH_REMAP[path]
    remapped = path
    for old, new in WOOD_TOKEN_REMAP:
        remapped = remapped.replace(old, new)
    return remapped


def remap_local_recipe_id(recipe_id: str) -> str:
    ns, sep, path = recipe_id.partition(":")
    if not sep:
        return recipe_id
    if ns not in (OLD_NS, NEW_NS):
        return recipe_id
    return f"{NEW_NS}:{remap_path(path)}"


def resolved_local_recipe_id(recipe_id: str, known_recipes: set[str]) -> str:
    namespaced = namespaced_recipe_id(recipe_id)
    remapped = remap_local_recipe_id(recipe_id)
    if remapped != namespaced and remapped in known_recipes:
        return remapped
    return namespaced


def dest_rel_for(src_rel: str, local_recipe_id: str) -> str:
    path = local_recipe_id.partition(":")[2]
    filename = path.rsplit("/", 1)[-1] + ".json"
    parent = str(Path(src_rel).parent).replace("\\", "/")
    if parent in (".", ""):
        return filename
    return f"{parent}/{filename}"


def haystack(rel: str, recipe_id: str | None) -> str:
    parts = [rel.lower().replace("\\", "/")]
    if recipe_id:
        parts.append(recipe_id.lower())
    return " ".join(parts)


def skip_reason(rel: str, recipe_id: str | None, known_recipes: set[str]) -> str | None:
    blob = haystack(rel, recipe_id)
    if any(token in blob for token in MAIL_TOKENS):
        return "mail"
    if "escritoire" in blob:
        return "escritoire"
    if not recipe_id:
        return "no_recipe_id"
    local_id = resolved_local_recipe_id(recipe_id, known_recipes)
    if local_id not in known_recipes:
        return "missing_recipe"
    return None


def missing_subreason(recipe_id: str) -> str:
    path = recipe_id.partition(":")[2]
    if "backpack" in path:
        return "backpack"
    if "wax_capsule" in path:
        return "wax_capsule"
    if path in ("peat_engine", "biogas_engine", "clockwork_engine") or path.endswith("_engine"):
        return "engine"
    if path.startswith("farm") or path in ("raintank",):
        return "farm"
    wood_ce121 = ("camelthorn_", "giant_sequoia_", "lemon_", "sour_cherry_", "zebrano_")
    if path.startswith(wood_ce121):
        return "wood_ce121_name"
    return "other_missing"


def rewrite_namespace(text: str) -> str:
    rewritten, _, _ = rewrite_text(text, OLD_NS, NEW_NS)
    return rewritten


def remap_json_ids(obj):
    if isinstance(obj, dict):
        return {key: remap_json_ids(value) for key, value in obj.items()}
    if isinstance(obj, list):
        return [remap_json_ids(value) for value in obj]
    if isinstance(obj, str):
        return remap_id_string(obj)
    return obj


def remap_id_string(value: str) -> str:
    tagged = value.startswith("#")
    body = value[1:] if tagged else value
    ns, sep, path = body.partition(":")
    if not sep or ns not in (OLD_NS, NEW_NS):
        return value
    remapped = f"{NEW_NS}:{remap_path(path)}"
    return "#" + remapped if tagged else remapped


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--root", default=".")
    parser.add_argument("--apply", action="store_true")
    args = parser.parse_args()

    root = Path(args.root).resolve()
    src_dir = ce121_generated() / "data" / OLD_NS / "advancement" / "recipes"
    dst_dir = root / "src" / "main" / "resources" / "data" / NEW_NS / "advancement" / "recipes"
    recipe_root = root / "src" / "main" / "resources" / "data" / NEW_NS / "recipe"

    if not src_dir.is_dir():
        print(f"error: CE recipe advancements not found: {src_dir}", file=sys.stderr)
        sys.exit(1)

    known = local_recipe_ids(recipe_root)
    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] recipe-unlock advancements")
    print(f"  from: {src_dir}")
    print(f"  to:   {dst_dir}")
    print(f"  local recipes: {len(known)}")

    sources = sorted(src_dir.rglob("*.json"))
    written = 0
    remapped = 0
    skipped = Counter()
    missing_subs = Counter()
    remap_subs = Counter()
    parent_mismatch = 0
    samples_write = []
    samples_skip = []
    samples_remap = []

    planned: list[tuple[Path, Path, dict]] = []
    dest_owners: dict[str, str] = {}

    for src in sources:
        rel = src.relative_to(src_dir).as_posix()
        raw = src.read_text(encoding="utf-8")
        data = json.loads(raw)
        recipe_id = recipe_id_from_advancement(data)
        reason = skip_reason(rel, recipe_id, known)
        if reason:
            skipped[reason] += 1
            if reason == "missing_recipe" and recipe_id:
                missing_subs[missing_subreason(recipe_id)] += 1
            if len(samples_skip) < 12:
                samples_skip.append(f"{rel} ({reason}" + (f" {recipe_id}" if recipe_id else "") + ")")
            continue
        parent = data.get("parent")
        if parent != VANILLA_RECIPE_PARENT:
            parent_mismatch += 1
            if parent and parent.startswith(f"{OLD_NS}:"):
                skipped["skipped_parent"] += 1
                if len(samples_skip) < 12:
                    samples_skip.append(f"{rel} (skipped_parent {parent})")
                continue
            data["parent"] = VANILLA_RECIPE_PARENT
        rewritten = remap_json_ids(json.loads(rewrite_namespace(raw)))
        if data.get("parent") == VANILLA_RECIPE_PARENT and rewritten.get("parent") != VANILLA_RECIPE_PARENT:
            rewritten["parent"] = VANILLA_RECIPE_PARENT
        local_id = resolved_local_recipe_id(recipe_id, known) if recipe_id else None
        namespaced = namespaced_recipe_id(recipe_id) if recipe_id else None
        dest_rel = dest_rel_for(rel, local_id) if local_id else rel
        if dest_rel in dest_owners:
            skipped["remap_collision"] += 1
            if len(samples_skip) < 12:
                samples_skip.append(f"{rel} (remap_collision {dest_rel} from {dest_owners[dest_rel]})")
            continue
        dest_owners[dest_rel] = rel
        dst = dst_dir / dest_rel
        planned.append((src, dst, rewritten))
        written += 1
        if local_id and namespaced and local_id != namespaced:
            remapped += 1
            remap_subs[missing_subreason(recipe_id)] += 1
            if len(samples_remap) < 12:
                samples_remap.append(f"{rel} -> {local_id}")
        if len(samples_write) < 8:
            samples_write.append(dst.relative_to(root).as_posix())

    if args.apply:
        if dst_dir.exists():
            shutil.rmtree(dst_dir)
        for _, dst, payload in planned:
            dst.parent.mkdir(parents=True, exist_ok=True)
            dst.write_text(json.dumps(payload, indent=2) + "\n", encoding="utf-8")

    print(f"\nsummary: scanned={len(sources)} written={written} remapped={remapped} skipped={sum(skipped.values())}")
    print("skip reasons:")
    for key in ("mail", "escritoire", "no_recipe_id", "skipped_parent", "remap_collision", "missing_recipe"):
        print(f"  {key}: {skipped.get(key, 0)}")
    if missing_subs:
        print("missing_recipe breakdown:")
        for key, count in sorted(missing_subs.items(), key=lambda item: (-item[1], item[0])):
            print(f"  {key}: {count}")
    if remap_subs:
        print("remapped breakdown:")
        for key, count in sorted(remap_subs.items(), key=lambda item: (-item[1], item[0])):
            print(f"  {key}: {count}")
    print(f"parent already {VANILLA_RECIPE_PARENT} (no retarget): {written - parent_mismatch}")
    print(f"parent retargeted to {VANILLA_RECIPE_PARENT}: {parent_mismatch}")
    print("sample writes:")
    for sample in samples_write:
        print(f"  {sample}")
    print("sample remaps:")
    for sample in samples_remap:
        print(f"  {sample}")
    print("sample skips:")
    for sample in samples_skip:
        print(f"  {sample}")
    if not args.apply:
        print("dry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
