# CU2 — Remaining planters

**Date:** 2026-08-21  
**Stage:** Wave 4 `CU2`. All seven planter kinds playable (managed + manual). Wave 4 farming/cultivation is done.

## Planter → farm type (CE `Tile*` subclasses)

Each tile looks up the G4 `IFarmType` at construction (`Objects.requireNonNull`, same as CE `Preconditions.checkNotNull`). Manual/managed only switches `getLogic(boolean)`. `CultivationTiles.init()` also marks both `{type}_managed` and `{type}_manual` as valid blocks for that BE (CE `createTile` supplier).

| Planter type | Tile | Farm type | Logic |
|---|---|---|---|
| arboretum | `reforestry:arboretum` | `reforestry:arboreal` | `FarmLogicArboreal` |
| farm_crops | `reforestry:crops` | `reforestry:crops` | `FarmLogicCrops` |
| farm_mushroom | `reforestry:mushroom` | `reforestry:shroom` | `FarmLogicMushroom` |
| farm_gourd | `reforestry:gourd` | `reforestry:gourd` | `FarmLogicGourd` |
| farm_nether | `reforestry:nether` | `reforestry:infernal` | `FarmLogicInfernal` |
| farm_ender | `reforestry:ender` | `reforestry:ender` | `FarmLogicEnder` |
| peat_bog | `reforestry:bog` | `reforestry:peat` | `FarmLogicPeat` |

Ghost stacks stay CE: gourd germling/resource lists are empty (seeds/dirt still accepted); peat germlings empty; mushroom tooltip key does not exist.

## Craft tubes (not multifarm circuit tubes)

Extractor `tools/extract_planter_recipes.py` asserts every managed shaped recipe. SUFFIX → PREFIX tubes, `basic_circuit_board` → `circuit_board_basic`.

| Planter | Craft tube | Circuit tube (G3/G4) |
|---|---|---|
| farm_crops | bronze | bronze |
| arboretum | **gold** | copper (arboreal) |
| farm_mushroom | apatite | apatite |
| farm_gourd | lapis | obsidian |
| farm_nether | blaze | blaze |
| farm_ender | ender | iron |
| peat_bog | obsidian | tin |

21 recipes, 14 loot tables, 21 recipe-book unlocks (F-ADV `recipes/misc`). All 14 ids in `#minecraft:mineable/pickaxe`. Agriculture tab still lists managed then manual per type; icon is arboretum managed.

## 26.2

`peat_bog.json` keeps CE’s `#texture` model (not `farm_parent`) and adds `"render_type": "minecraft:cutout_mipped"` so it matches the other planters’ cutout. Unused leftover `blockstates/arboretum.json` (no unsuffixed block) was removed. No `hasChunkAt`.

## Smoke (server was not up)

- `/give @p reforestry:arboretum_managed` — dirt + oak sapling + fertilizer + water + FE; places humus, plants, harvests logs/apples
- `farm_mushroom_managed` — mycelium/podzol + red/brown mushroom
- `farm_gourd_managed` — dirt + pumpkin/melon **seeds** (no GUI ghosts for those slots)
- `farm_nether_managed` — soul sand + nether wart (no water)
- `farm_ender_managed` — end stone + chorus flower (portal particles; no water)
- `peat_bog_managed` — bog earth + water; harvests peat
- Matching `_manual` variants: pre-place soil/crops; planter does not till
