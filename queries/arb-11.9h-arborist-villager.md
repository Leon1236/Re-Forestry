# ARB-11.9h — Arborist villager — DEFERRED

**Date:** 2026-07-25  
**Status:** Skipped — prerequisite chest block not registered.

## Prerequisite check

CE POI binds to `CoreBlocks.NATURALIST_CHEST.get(NaturalistChestBlockType.ARBORIST_CHEST)` → registry id `forestry:tree_chest`.

| Asset / code | Present? |
|---|---|
| `assets/.../blockstates/tree_chest.json` + models | Yes (orphaned copy) |
| Lang `block.reforestry.tree_chest` / villager lang keys | Yes |
| Profession textures `textures/entity/villager/profession/arborist*.png` | Yes |
| Java block / BE / `NATURALIST_CHEST` in `CoreBlocks` | **No** |
| Any `*Villagers.java` in Re-Forestry | **No** |

`CoreBlocks` only has bog earth, peat, humus, storage, ores — no naturalist chest group. Same gap for `bee_chest` / apiarist.

## Village structures (verified)

`data/reforestry/structures/village/` contains **only** apiarist houses:

- `apiarist_house_{desert,plains,savanna,snowy,taiga}_1.nbt`

No arborist house NBT. Scope already excludes structure injection unless CE datapack was copied — it was not for arborist.

## CE reference (do not invent when implementing later)

- `forestry/arboriculture/villagers/ArboricultureVillagers.java`
- POI id `tree_chest`, profession id `arborist`, work sound `VILLAGER_WORK_FISHERMAN`
- Trades: planks/logs (random `ForestryWoodType`), sapling/pollen by complexity, proven grafter (levels 1–4)

## Fabric notes (for later)

Replace Forge `VillagerTradesEvent` with Fabric object-builder APIs (`PointOfInterestHelper` / `TradeOfferHelper` / profession registry — confirm names against current Fabric API when implementing). Register after `tree_chest` block exists.

## Unblock path

1. Port naturalist chests (`bee_chest` / `tree_chest` / `butterfly_chest`) + tile entities (core/apiculture storage content).
2. Then implement `ArboricultureVillagers` + `acquirable_job_site` tag + trades.
3. Structure injection remains optional / separate.
