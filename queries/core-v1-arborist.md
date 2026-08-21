# CORE-V1 — Arborist villager

**Date:** 2026-08-20  
**Status:** Done. Unemployed villagers take the arborist job at `reforestry:tree_chest`.

## CE 1.21.1 source copied

`forestry/arboriculture/villagers/ArboricultureVillagers.java` (clone `2026-08-17/thedarkcolour-ForestryCE`).

| Level | Listings (exact CE constructor args) |
|---|---|
| 1 | `GivePlanksForEmeralds(1–4 emeralds, 10–32 planks, 8 uses, 2 xp, 0F)` + `GivePollenForEmeralds(1–1 emeralds, 1–3 saplings, SAPLING, complexity ≤ 4, 8, 2, 0F)` |
| 2 | Same planks (xp 6) + pollen `POLLEN` complexity ≤ 6 (emeralds 2–3, count 1, xp 6) + `GiveItemForEmeralds(grafter_proven, sell 1, emeralds 1–4, 8, 6)` — proven grafter uses CE helper **0.05** price multiplier |
| 3 | `GiveLogsForEmeralds(2–5 emeralds, 6–18 logs, 8, 2, 0F)` **twice** (CE duplicate lines) |
| 4 | Pollen and sapling, emeralds 5–20, count 1, complexity ≤ 10, 8 uses, 15 xp, 0F |

Random planks/logs: `ForestryWoodType.getRandom` + `WoodAccess.getStack(type, PLANKS\|LOG, false)`. Germlings: `reforestry:sapling` / `reforestry:pollen_fertile` + `TREE_GENOME`. Proven grafter id is `reforestry:grafter_proven`.

## Complexity (no public API)

CE `ISpecies.getComplexity()` still exists. Local `ITreeSpecies` has `getRarity()` only — **not** a public `getComplexity()`.

Villager filter uses the same heuristic as CE `GeneticsUtil.getResearchComplexity`: `1 + mutation-depth` (`getMutationsInto` walk). CE `DefaultTreeSpecies` only calls `setComplexity(10)` on **giant sequoia** (`reforestry:tree_giant_sequoia`); that override is applied in `TreeSpeciesComplexity` without adding `ISpecies.getComplexity`.

Rarity is **not** used for trades.

## Fabric 26.2 (not CE Forge / not FAPI TradeOfferHelper)

- POI: `PoiHelper.register(reforestry:tree_chest, 1, 1, tree_chest block)` — tickets/search match CE `PoiType(..., 1, 1)`.
- Profession: `BuiltInRegistries.VILLAGER_PROFESSION` + `ResourceKey` `reforestry:arborist`. 26.2 profession is a record with `Int2ObjectMap<ResourceKey<TradeSet>>`; vanilla name key is `entity.reforestry.villager.arborist` (also kept CE `entity.minecraft.villager.reforestry.arborist`).
- Trades: confirmed against `Minecraft-26.2` `net.minecraft.world.item.trading.VillagerTrades` — **no** `TRADES` field (only `ResourceKey<VillagerTrade>` constants). FAPI 26.2 has no `TradeOfferHelper`. Equivalent is datapack `villager_trade` + `trade_set` registries. Custom loot functions `reforestry:set_random_forestry_wood` / `reforestry:set_random_tree_genome` reproduce CE random wood / genome rolls. Trade-set `amount` equals listing count so every CE listing appears (vanilla farmers pick 2 from a larger tag).
- Tag: `data/minecraft/tags/point_of_interest_type/acquirable_job_site.json` includes `reforestry:tree_chest`.
- Work sound: `SoundEvents.VILLAGER_WORK_FISHERMAN`.
- No arborist house NBT (CE has none).

`queries/arb-11.9h-arborist-villager.md` is updated; `tree_chest` exists (A9 / CORE-E4).
