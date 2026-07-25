#!/usr/bin/env python3
"""Imports arboriculture wood-block assets/recipes/loot tables/tags from
ForestryCE's pre-generated datagen output (Forge 1.20.1) into this project
(Fabric 26.2), renaming the namespace and migrating the handful of JSON
formats that changed between MC versions along the way.

Sources:
  models/blockstates (structurally unchanged since 1.20.1, only need the
           namespace rename) come from CE's build-time datagen output:
           for textures only/thedarkcolour-ForestryCE/src/generated/resources
  recipes/loot tables/tags (also datagen output) - same root.
  textures (static PNGs, never datagen output) come from CE's own
           src/main/resources: for textures only/thedarkcolour-ForestryCE/src/main/resources

Model/texture copying follows the real reference graph instead of guessing
filenames: each blockstate is parsed for every "model" it points at (many
block kinds reference several - e.g. stairs need base+inner+outer, doors
need 8 open/closed x top/bottom x left/right variants), each of those model
files is copied and its own "textures" dict (plus its "parent" chain, for
any Forestry-side parent models) is followed to find the actual PNGs
needed, which are then copied too. A naive "copy the file matching the
block's own id" approach silently drops most of these.

Recipe/loot table/tag folder layout migration (verified against the 26.2
game jar's own vanilla data, not guessed):
  data/<ns>/recipes/       -> data/<ns>/recipe/
  data/<ns>/loot_tables/   -> data/<ns>/loot_table/   (blocks/ subfolder stays plural)
  data/<ns>/tags/blocks/   -> data/<ns>/tags/block/
  data/<ns>/tags/items/    -> data/<ns>/tags/item/

Recipe content migration (verified against data/minecraft/recipe/oak_stairs.json
etc. in the 26.2 game jar):
  - result: {"item": "ns:id", "count": N} -> {"id": "ns:id", "count": N}
  - ingredient objects compact to plain strings: {"item": "ns:id"} -> "ns:id",
    {"tag": "ns:id"} -> "#ns:id"
  - a key mapping to multiple item alternatives (CE's door recipes accept
    both plain and fireproof planks) is simplified to just the first
    alternative - matches vanilla's own door recipes (which only accept one
    plank variant), not a format requirement. Documented scope cut.
  - "show_notification" is dropped (absent from vanilla's own 26.2 recipes)
  - the Forge cross-loader tag forge:rods/wooden (used by CE's fence
    recipes) has no Fabric equivalent; replaced with a literal
    minecraft:stick, matching vanilla's own real oak_fence recipe exactly.

Wall sign / wall hanging sign blocks have no item and (per CE's own
datagen) no loot table of their own - CE points their drop at the standing
variant's loot table via a Java-side lootFrom(...) property, which has no
effect on statically-copied JSON. This script reproduces the same effect by
copying the standing variant's loot table content under the wall variant's
own id (with the id string substituted), so breaking a wall sign still
drops the sign item.

Only the wood-type block system (43 ForestryWoodType x 26 kinds, see
ArboricultureBlocks.java) is in scope - not leaves/pods/genetics assets
(those are step 11.5's job, once the genetic blocks exist to import for).

Usage:
  python3 tools/import_ce_generated.py --root . --apply

Without --apply this only prints a per-category file count (dry run).
"""
import argparse
import json
import re
import sys
from pathlib import Path

OLD_NS = "forestry"
NEW_NS = "reforestry"

WOOD_TYPES = [
    "larch", "teak", "acacia_desert", "lime", "chestnut", "wenge", "baobab", "sequoia",
    "kapok", "ebony", "elm", "mahogany", "balsa", "willow", "walnut", "greenheart", "hill_cherry",
    "mahoe", "poplar", "palm", "papaya", "pine", "plum", "maple", "citrus",
    "giganteum", "ipe", "padauk", "cocobolo", "fir", "coconut", "beech", "feijoa", "dogwood",
    "ginkgo", "jacaranda", "pewen", "macrocarpa", "olive", "orange", "pear", "kauri", "zebrawood",
]

# (suffix, has_item, standing_suffix_if_wall) - matches ArboricultureBlocks.java's
# woodGroup()/registerWood() identifier() calls exactly. standing_suffix_if_wall is
# set for the two item-less "wall" kinds, naming the kind whose loot table to mirror.
KINDS = [
    ("log", True, None), ("fireproof_log", True, None),
    ("stripped_log", True, None), ("fireproof_stripped_log", True, None),
    ("wood", True, None), ("fireproof_wood", True, None),
    ("stripped_wood", True, None), ("fireproof_stripped_wood", True, None),
    ("planks", True, None), ("fireproof_planks", True, None),
    ("slab", True, None), ("fireproof_slab", True, None),
    ("fence", True, None), ("fireproof_fence", True, None),
    ("fence_gate", True, None), ("fireproof_fence_gate", True, None),
    ("stairs", True, None), ("fireproof_stairs", True, None),
    ("door", True, None), ("trapdoor", True, None), ("button", True, None), ("pressure_plate", True, None),
    ("sign", True, None), ("wall_sign", False, "sign"),
    ("hanging_sign", True, None), ("wall_hanging_sign", False, "hanging_sign"),
]


def all_ids():
    for wood in WOOD_TYPES:
        for suffix, has_item, mirror in KINDS:
            yield wood, f"{wood}_{suffix}", has_item, (f"{wood}_{mirror}" if mirror else None)


def rename_ns(text: str) -> str:
    text = re.sub(rf'(?<![A-Za-z0-9_-]){OLD_NS}(?=[:/."\'])', NEW_NS, text)
    text = re.sub(rf'\.{OLD_NS}\.', f'.{NEW_NS}.', text)
    return text


def read_json(path: Path):
    return json.loads(path.read_text(encoding="utf-8"))


def write_text(path: Path, text: str, apply: bool, log, label: str):
    log(f"{label} {path}")
    if apply:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text, encoding="utf-8")


def write_json(path: Path, data, apply: bool, log, label: str):
    write_text(path, json.dumps(data, indent=2) + "\n", apply, log, label)


def ce_id_to_relpath(resource_id: str):
    """'forestry:block/larch_stairs_inner' -> ('block', 'larch_stairs_inner')."""
    ns, _, path = resource_id.partition(":")
    if ns != OLD_NS:
        return None
    kind, _, name = path.partition("/")
    return kind, name


def referenced_model_ids(blockstate_json):
    models = set()

    def collect(entry):
        items = entry if isinstance(entry, list) else [entry]
        for item in items:
            if isinstance(item, dict) and "model" in item:
                models.add(item["model"])

    for variant in blockstate_json.get("variants", {}).values():
        collect(variant)
    for part in blockstate_json.get("multipart", []):
        collect(part.get("apply", {}))
    return models


class AssetCopier:
    """Copies model JSON (from models_ce) and the textures they reference
    (from textures_ce, a separate root since textures are static, not
    datagen output), following parent/texture references transitively."""

    def __init__(self, models_ce: Path, textures_ce: Path, assets_out: Path, apply: bool, log):
        self.models_ce = models_ce
        self.textures_ce = textures_ce
        self.assets_out = assets_out
        self.apply = apply
        self.log = log
        self.copied_models = set()
        self.copied_textures = set()
        self.model_count = 0
        self.texture_count = 0

    def copy_model(self, model_id: str):
        if model_id in self.copied_models:
            return
        self.copied_models.add(model_id)
        rel = ce_id_to_relpath(model_id)
        if rel is None:
            return
        kind, name = rel
        src = self.models_ce / "models" / kind / f"{name}.json"
        if not src.is_file():
            return
        data = read_json(src)
        dst = self.assets_out / "models" / kind / f"{name}.json"
        write_text(dst, rename_ns(json.dumps(data, indent=2) + "\n"), self.apply, self.log, "MODEL    ")
        self.model_count += 1

        for texture_id in data.get("textures", {}).values():
            self.copy_texture(texture_id)
        parent = data.get("parent")
        if parent:
            self.copy_model(parent)

    def copy_texture(self, texture_id: str):
        if texture_id in self.copied_textures:
            return
        self.copied_textures.add(texture_id)
        rel = ce_id_to_relpath(texture_id)
        if rel is None:
            return
        kind, name = rel
        src = self.textures_ce / "textures" / kind / f"{name}.png"
        if not src.is_file():
            return
        dst = self.assets_out / "textures" / kind / f"{name}.png"
        self.log(f"TEXTURE  {dst}")
        if self.apply:
            dst.parent.mkdir(parents=True, exist_ok=True)
            dst.write_bytes(src.read_bytes())
        self.texture_count += 1
        mcmeta = src.with_suffix(".png.mcmeta")
        if mcmeta.is_file():
            dst_meta = dst.with_suffix(".png.mcmeta")
            self.log(f"TEXTURE  {dst_meta}")
            if self.apply:
                dst_meta.write_bytes(mcmeta.read_bytes())


# Vanilla items CE's 1.20.1 recipes reference under a name that was later
# renamed - confirmed against the real 26.2 game jar's own vanilla recipes
# (e.g. data/minecraft/recipe/oak_hanging_sign.json uses minecraft:iron_chain
# where CE's larch_hanging_sign.json still says minecraft:chain).
VANILLA_ITEM_RENAMES = {
    "minecraft:chain": "minecraft:iron_chain",
}


def compact_ingredient(entry):
    if isinstance(entry, str):
        renamed = rename_ns(entry)
        return VANILLA_ITEM_RENAMES.get(renamed, renamed)
    if isinstance(entry, dict):
        if "tag" in entry:
            return "#" + rename_ns(entry["tag"])
        if "item" in entry:
            renamed = rename_ns(entry["item"])
            return VANILLA_ITEM_RENAMES.get(renamed, renamed)
        raise ValueError(f"unrecognized ingredient object: {entry}")
    if isinstance(entry, list):
        if not entry:
            raise ValueError("empty ingredient alternatives list")
        return compact_ingredient(entry[0])
    raise ValueError(f"unrecognized ingredient shape: {entry}")


def transform_recipe(data):
    data = dict(data)
    if "ingredients" in data:
        data["ingredients"] = [compact_ingredient(e) for e in data["ingredients"]]
    if "ingredient" in data:
        data["ingredient"] = compact_ingredient(data["ingredient"])
    if "key" in data:
        data["key"] = {k: compact_ingredient(v) for k, v in data["key"].items()}
    if "result" in data and isinstance(data["result"], dict) and "item" in data["result"]:
        result = dict(data["result"])
        result["id"] = result.pop("item")
        data["result"] = result
    data.pop("show_notification", None)
    data = json.loads(rename_ns(json.dumps(data)))
    key_map = data.get("key")
    if key_map:
        for k, v in list(key_map.items()):
            if v == "#forge:rods/wooden":
                key_map[k] = "minecraft:stick"
    return data


def import_all(root: Path, ce_gen_root: Path, ce_main_root: Path, apply: bool, log):
    counts = {"blockstates": 0, "recipes": 0, "loot_tables": 0, "tags": 0}

    assets_out = root / "src" / "main" / "resources" / "assets" / NEW_NS
    data_out = root / "src" / "main" / "resources" / "data" / NEW_NS
    assets_ce_gen = ce_gen_root / "assets" / OLD_NS
    assets_ce_main = ce_main_root / "assets" / OLD_NS
    data_ce = ce_gen_root / "data" / OLD_NS

    copier = AssetCopier(assets_ce_gen, assets_ce_main, assets_out, apply, log)
    loot_by_id = {}

    for wood, block_id, has_item, mirror in all_ids():
        blockstate_src = assets_ce_gen / "blockstates" / f"{block_id}.json"
        if blockstate_src.is_file():
            blockstate = read_json(blockstate_src)
            write_text(assets_out / "blockstates" / f"{block_id}.json",
                       rename_ns(json.dumps(blockstate, indent=2) + "\n"), apply, log, "BLOCKSTATE")
            counts["blockstates"] += 1
            for model_id in referenced_model_ids(blockstate):
                copier.copy_model(model_id)

        if has_item:
            copier.copy_model(f"{OLD_NS}:item/{block_id}")

            item_def = {"model": {"type": "minecraft:model", "model": f"{NEW_NS}:item/{block_id}"}}
            write_json(assets_out / "items" / f"{block_id}.json", item_def, apply, log, "ITEM DEF ")

        recipe_src = data_ce / "recipes" / f"{block_id}.json"
        if recipe_src.is_file():
            transformed = transform_recipe(read_json(recipe_src))
            write_json(data_out / "recipe" / f"{block_id}.json", transformed, apply, log, "RECIPE   ")
            counts["recipes"] += 1

        loot_src = data_ce / "loot_tables" / "blocks" / f"{block_id}.json"
        if loot_src.is_file():
            loot_by_id[block_id] = read_json(loot_src)
        elif mirror and mirror in loot_by_id:
            # The wall variant has no item of its own - it drops the standing
            # variant's item (matching CE's Java-side lootFrom(...) intent), so
            # only the per-block random_sequence hint is rewritten to the wall
            # variant's own id; the entries[].name item reference is left
            # pointing at the standing variant's real, droppable item.
            mirrored = json.loads(json.dumps(loot_by_id[mirror]))
            if mirrored.get("random_sequence") == f"{OLD_NS}:blocks/{mirror}":
                mirrored["random_sequence"] = f"{OLD_NS}:blocks/{block_id}"
            loot_by_id[block_id] = mirrored

        if block_id in loot_by_id:
            write_text(data_out / "loot_table" / "blocks" / f"{block_id}.json",
                       rename_ns(json.dumps(loot_by_id[block_id], indent=2) + "\n"), apply, log, "LOOT     ")
            counts["loot_tables"] += 1

    for wood in WOOD_TYPES:
        for tag_name in (f"{wood}_logs", f"fireproof_{wood}_logs"):
            block_tag_src = data_ce / "tags" / "blocks" / f"{tag_name}.json"
            if block_tag_src.is_file():
                write_text(data_out / "tags" / "block" / f"{tag_name}.json",
                           rename_ns(block_tag_src.read_text(encoding="utf-8")), apply, log, "TAG BLOCK")
                counts["tags"] += 1
            item_tag_src = data_ce / "tags" / "items" / f"{tag_name}.json"
            if item_tag_src.is_file():
                write_text(data_out / "tags" / "item" / f"{tag_name}.json",
                           rename_ns(item_tag_src.read_text(encoding="utf-8")), apply, log, "TAG ITEM ")
                counts["tags"] += 1

    counts["models"] = copier.model_count
    counts["textures"] = copier.texture_count
    return counts


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--root", default=".", help="project root (default: current directory)")
    parser.add_argument("--ce-gen-root",
                         default="for textures only/thedarkcolour-ForestryCE/src/generated/resources",
                         help="path to CE's generated resources, relative to --root")
    parser.add_argument("--ce-main-root",
                         default="for textures only/thedarkcolour-ForestryCE/src/main/resources",
                         help="path to CE's static (textures) resources, relative to --root")
    parser.add_argument("--apply", action="store_true", help="write files (default: dry run)")
    parser.add_argument("--quiet", action="store_true", help="only print the summary, not each file")
    args = parser.parse_args()

    root = Path(args.root)
    ce_gen_root = root / args.ce_gen_root
    ce_main_root = root / args.ce_main_root
    if not ce_gen_root.is_dir():
        print(f"error: {ce_gen_root} not found", file=sys.stderr)
        sys.exit(1)
    if not ce_main_root.is_dir():
        print(f"error: {ce_main_root} not found", file=sys.stderr)
        sys.exit(1)

    mode = "APPLY" if args.apply else "DRY RUN"
    print(f"[{mode}] importing arboriculture wood-block assets\n  generated: {ce_gen_root}\n  static:    {ce_main_root}\n")

    log = (lambda *_: None) if args.quiet else print
    counts = import_all(root, ce_gen_root, ce_main_root, args.apply, log)

    print("\nsummary:")
    for key, value in counts.items():
        print(f"  {key}: {value}")
    if not args.apply:
        print("\ndry run only - rerun with --apply to write files")


if __name__ == "__main__":
    main()
