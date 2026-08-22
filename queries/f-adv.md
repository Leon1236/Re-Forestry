# F-ADV — Recipe-unlock advancements

**Date:** 2026-08-20  
**Source:** CE 1.21.1 generated `data/forestry/advancement/recipes/**` (1135 JSON).  
**Not used:** CE 1.20.1 `data/forestry/advancements/` gameplay files (`get_carpenter.json`, `get_centrifuge.json`, …).  
**Output:** `data/reforestry/advancement/recipes/**` — **1070** written (**139** remapped from a different CE recipe id), **65** skipped.  
**Tool:** `python3 tools/extract_recipe_advancements.py --root . --apply`

## Vanilla / CE layout (verified)

Minecraft 26.2 client jar uses singular `data/minecraft/advancement/` (not `advancements/`). Recipe unlocks live under `advancement/recipes/` and parent **`minecraft:recipes/root`** (that file’s only criterion is `minecraft:impossible`).

CE 1.21.1 matches that: folder `advancement/recipes/{misc,combat,building_blocks,food,tools,redstone}/`. All 1135 recipe unlocks already parent to `minecraft:recipes/root`. The extra `advancement/root.json` is the honeycomb gameplay tree (guide-book reward) — **copied Wave 9 Stage 0** (`data/reforestry/advancement/root.json` + existing `loot_table/grant_guide.json`).

No written file needed a parent retarget. Mail / escritoire skips never left a dangling `forestry:` parent.

## Counts

| Bucket | Count | Notes |
|---|---|---|
| CE recipe unlocks scanned | 1135 | plus 1 skipped gameplay `root.json` (not under `recipes/`) |
| **Written** | **1070** | `forestry:` → `reforestry:` via `rename_namespace.rewrite_text`; 139 of those also remapped to a local recipe id |
| Skipped mail | 0 | CE 1.21.1 recipe unlocks have no mailbox/stamp/letter/catalogue ids |
| Skipped escritoire | 0 | Carpenter-only in CE (`recipe/carpenter/escritoire.json`); no vanilla crafting unlock |
| Skipped missing local recipe | 65 | recipe id after namespace + remap still not in `data/reforestry/recipe/` |
| Skipped missing parent | 0 | |

### Remaps (139) — local recipe exists under a different id

| Reason | Count | What |
|---|---|---|
| `wood_ce121_name` | 110 | `camelthorn`→`acacia_desert`, `giant_sequoia`→`giganteum`, `lemon`→`citrus`, `sour_cherry`→`hill_cherry`, `zebrano`→`zebrawood` (tags such as `#reforestry:camelthorn_logs` remapped too) |
| `backpack` | 9 | `*_backpack` / `apiarists_backpack` → `*_bag` / `apiarist_bag` |
| `other_missing` (now remapped) | 20 | machines, kits, tools, chests, frames, armor — see list below |

Exact recipe-path remaps in `tools/extract_recipe_advancements.py` `RECIPE_PATH_REMAP` (only applied when the target recipe file exists):

- `thermionic_fabricator` → `fabricator`
- `sturdy_casing` → `sturdy_machine`
- `apiarists_backpack` → `apiarist_bag`; `arborists_backpack` → `arborist_bag`; `lepidopterists_backpack` → `lepidopterist_bag`; other `*_backpack` → `*_bag`
- `bee_smoker` → `smoker`
- `spectacles` → `naturalist_helmet`
- `pickaxe_kit` → `kit_pickaxe`; `shovel_kit` → `kit_shovel`
- `refractory_capsule` → `refractory`
- `apiarists_chest` → `bee_chest`; `arborists_chest` → `tree_chest`
- `impregnated_frame` → `frame_impregnated`; `untreated_frame` → `frame_untreated`
- `apiarists_hat/shirt/pants/shoes` → `apiarist_helmet/chest/legs/boots`
- `survivalists_*` tool crafts → `bronze_*`

Criterion items ride the same path remap (`survivalists_pickaxe` → `bronze_pickaxe` on kit unlocks).

Not remapped (no matching vanilla crafting recipe):

- ~~`portable_analyzer`~~ — **fixed Wave 9 Stage 0:** `analyzer` unlock criterion uses `reforestry:portable_alyzer`; new `recipes/tools/portable_alyzer.json` unlocks `carpenter/portable_analyzer`
- `raintank` / `genetic_filter` / `wax_capsule` / `bog_earth_wax_capsule` / escritoire / mail. Engine unlocks (`peat_engine`, `biogas_engine`, `clockwork_engine`) were copied in FE1–FE2.

### Missing-recipe breakdown (65 at extract; peat later copied)

| Reason | Count | What |
|---|---|---|
| `engine` | 0 remaining | `peat_engine` / `biogas_engine` / `clockwork_engine` copied in FE1–FE2. |
| `farm` | 1 | `raintank` (farming rain tank). Local factory block recipe is `rainmaker` (that unlock **was** copied) |
| `wax_capsule` | 1 | `bog_earth_wax_capsule`. Local fluid container id is `capsule`, not `wax_capsule` |
| `other_missing` | 60 | CE-only crafts with **no** local recipe under any id: tin/bronze/apatite blocks and smelting, comb blocks, gears, `genetic_filter`, charcoal block, cake-from-container, `portable_analyzer`, … |

Do **not** invent those recipes to close the remaining 65.

## Sample paths

Written:

- `data/reforestry/advancement/recipes/misc/carpenter.json`
- `data/reforestry/advancement/recipes/misc/fabricator.json` (from CE `thermionic_fabricator`)
- `data/reforestry/advancement/recipes/misc/capsule.json`
- `data/reforestry/advancement/recipes/building_blocks/alveary_block.json`
- `data/reforestry/advancement/recipes/building_blocks/acacia_desert_planks.json` (from CE `camelthorn_planks`)
- `data/reforestry/advancement/recipes/tools/apiarist_bag.json` (from CE `apiarists_backpack`)

Skipped (engines copied later):

- `recipes/misc/peat_engine.json` — copied in FE1
- `recipes/misc/biogas_engine.json` / `recipes/misc/clockwork_engine.json` — copied in FE2
- `recipes/tools/portable_analyzer.json` (no vanilla `portable_alyzer` recipe)
- `recipes/building_blocks/bog_earth_wax_capsule.json` (no `wax_capsule` recipe)
