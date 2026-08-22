# ARB-11.9h — Arborist villager

**Date:** 2026-08-20 (was deferred 2026-07-25)  
**Status:** Done as **CORE-V1**. `tree_chest` is registered (`NaturalistChestBlockType.TREE_CHEST` / `CoreBlocks.NATURALIST_CHESTS`).

See `queries/core-v1-arborist.md` for the CE trade table, complexity heuristic, and 26.2 TradeSet wiring.

## What landed

| Asset / code | Present? |
|---|---|
| `tree_chest` block / BE / GUI | Yes (A9) |
| POI `reforestry:tree_chest` | Yes — `PoiHelper.register`, tickets/search 1/1 |
| Profession `reforestry:arborist` | Yes — `BuiltInRegistries.VILLAGER_PROFESSION` |
| `acquirable_job_site` tag | Yes |
| Profession textures | Yes (already copied) |
| Lang (26.2 `entity.reforestry.villager.arborist` + older keys) | Yes |
| Trades | Yes — datapack `villager_trade` / `trade_set` copying CE listings |
| Arborist house NBT | No — CE has none; still skipped |

Beekeeper / escritoire is Wave 1b (`CORE-V2`) — not this stage.
