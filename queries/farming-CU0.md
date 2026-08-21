# CU0 — Cultivation module shell

**Date:** 2026-08-21  
**Stage:** Wave 4 `CU0`. No planter blocks, tiles, menus, recipes, or circuits.

## Package map

| CE 1.21.1 | Re-Forestry |
|---|---|
| `forestry.agriculture.ModuleCultivation` | `com.leon1236.reforestry.cultivation.ModuleCultivation` |
| `forestry.agriculture.client.CultivationClientHandler` | `com.leon1236.reforestry.cultivation.client.CultivationClientHandler` |

Keep our `cultivation` package (CE 1.21.1 merged farms into `forestry.agriculture`; MIGRATION.md still uses module id `forestry:cultivation`). Do not move planters under `farming`.

Module id: `reforestry:cultivation` (`ForestryModuleIds.CULTIVATION`). Annotation name `Cultivation`; description matches CE `for.module.cultivation.description` (lang already present).

## Depends on farming

CE `getModuleDependencies()` is `CORE` + `FARMING`. Same here. `ModuleManager` drops any enabled module whose deps are not enabled, so cultivation loads only when farming is on. Toggle is written by `ModuleConfig` into `config/reforestry/modules.properties` on the next game run, like farming.

## Empty client handler

G0 `FarmingClientHandler` registered no screens. CU0 `CultivationClientHandler.registerClient()` is empty (same shape as other module handlers; filled in CU1). CE handler registers planter cutout layers + `GuiPlanter` — that is CU1.

CE `ModuleCultivation` has no `setup`/`init` override. It only registers capabilities in `registerEvents` (planter item/energy/fluid). No tiles in CU0, so no Fabric transfer registrations yet. `ModuleCultivation.init()` uses the `IForestryModule` default (empty).

## No separate cultivation plugin

CE `AgricultureForestryPlugin` is the **farming** plugin (`id` `forestry:farming`). It registers multifarm layouts/circuits and farm types only. There is no CE cultivation `IForestryPlugin`. Do not add a cultivation plugin or planter circuits in CU0; those wait for CU1 blocks.

## No blocks

CU0 has no planter blocks, tiles, menus, recipes, or JEI. Orphan `arboretum.json` / `peat_bog.json` models under `assets/reforestry` are leftover 1.12 names from an older texture dump — not registered, not CU0.

CU1 registers `{type}_managed` / `{type}_manual` with `.identifier("managed", IdentifierType.SUFFIX)` / `.identifier("manual", IdentifierType.SUFFIX)` (CE `CultivationBlocks`). Do **not** use `.identifier("managed")` — that helper defaults to **PREFIX** and would invent `managed_farm_crops`. Examples: `reforestry:farm_crops_managed`, `reforestry:arboretum_manual`. Types: `arboretum`, `farm_crops`, `farm_mushroom`, `farm_gourd`, `farm_nether`, `farm_ender`, `peat_bog`.

## Smoke

- `config/reforestry/modules.properties` lists `reforestry:cultivation=true` after a run (same `ModuleConfig` write as farming).
- Set `reforestry:farming=false`, restart: log `Module reforestry:cultivation is missing dependencies, disabling it`. Cultivation client handler does not run.
- No `/give` planter ids yet.
