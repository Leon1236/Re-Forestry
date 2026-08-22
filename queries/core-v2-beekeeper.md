# CORE-V2 — Beekeeper villager

**Date:** 2026-08-20  
**Status:** Done. Unemployed villagers take the beekeeper job at `reforestry:escritoire`.

## CE 1.21.1 source copied

`forestry/apiculture/apiarist/villagers/ApicultureVillagers.java` (clone `2026-08-17/thedarkcolour-ForestryCE`).

| Level | Listings (exact CE constructor args) |
|---|---|
| 1 | `GiveHoneyCombForItem(combs, wheat\|carrot\|potato, sell 2–4, buy 8–12, 8 uses, 2 xp, 0F)` ×3 |
| 2 | `GiveItemForEmeralds(smoker, sell 1–1, emeralds 1–4, 8, 6)` (helper **0.05**) + `GiveDroneForItems(propolis_normal, buy 2–4, sell 1–1, 8, 6, 0F)` mundane drone from forest/meadows/modest/wintry/tropical/marshy |
| 3 | `GiveEmeraldForItem(princess, 1–1, emerald 1–1, 8, 10)` + `GiveItemForEmeralds(frame_proven, sell 1–2, emeralds 1–6, 8, 10)` + `GiveItemForLogAndEmerald(logs 32–64, emeralds 16–32, apiary, sell 1–1, 8, 10)` |
| 4 | `GiveItemForItemAndEmerald(princess 1–1, emeralds 10–64, monastic drone, 8, 15)` + `GiveItemForTwoItems(drone 1–1, ender_eye 12–16, ended drone, 8, 15)` |

Local ids: `bee_comb_*` (tag `reforestry:village_combs`), `propolis_normal`, `smoker`, `frame_proven`, `apiary`, `bee_princess_ge`, `bee_drone_ge`, `bee_monastic`, `bee_ended`.

## Fabric 26.2

- POI: `PoiHelper.register(reforestry:escritoire, 1, 1, escritoire block)` — CE tickets/search 1/1.
- Profession: `reforestry:beekeeper`, work sound `VILLAGER_WORK_LIBRARIAN`.
- Trades: datapack `villager_trade` + `trade_set` (no `VillagerTrades.TRADES` / FAPI `TradeOfferHelper`). Loot functions: `set_random_village_comb`, `set_random_mundane_drone`, `set_bee_species_genome`.
- Tag: `data/minecraft/tags/point_of_interest_type/acquirable_job_site.json` includes `reforestry:escritoire`.
- Lang: `entity.reforestry.villager.beekeeper` (+ kept `entity.minecraft.villager.reforestry.beekeeper`).

**Log+apiary note:** CE picks a random vanilla log each offer. 26.2 `TradeCost` is a single item holder, so the datapack trade uses `minecraft:oak_log` with the same count ranges.

## Village houses

- NBT under `data/reforestry/structure/village/` (MC 26.2 `STRUCTURE_DIRECTORY_NAME = "structure"`; CE path `structure/village/apiarist_house_*_1.nbt`). Five biomes: plains / desert / savanna / snowy / taiga.
- `VillagerJigsaw` injects `ApiaristPoolElement` into `minecraft:village/{biome}/houses` on `SERVER_STARTING` (weight 15), matching CE.
- Pool element type `reforestry:apiarist`. Data marker `apiary` places an apiary with climate-matched village queen + 3 worn proven frames.
- Village bee pools via `IApicultureRegistration.addVillageBee` (common/rare lists on `IHiveManager`), same species as CE `ApicultureForestryPlugin`.
- Accessor mixin mutates `StructureTemplatePool` templates in place (`rawTemplates` is final on 26.2).

## Known skips / Fabric deltas

- Butterflies on escritoire: Track D / lepidopterology.
- Level-3 apiary trade asks for `minecraft:oak_log` only (CE picks a random vanilla log each offer; 26.2 `TradeCost` is a single item holder).
