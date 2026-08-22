# Unstarted modules — CE 1.21.1 stage inventory (2026-08-18)

Clone: `MarkDown_Maker/Finished_github_clone/2026-08-17/thedarkcolour-ForestryCE`  
Verified against CE **1.21.1** (not CE20 package paths).

## Local load (`ReForestry.java`)

Live list is `queries/remaining-work-stages.md`. This file is the CE 1.21.1 package inventory (2026-08-18). Energy, sorting, farming, and cultivation **CU0** have landed since; lepidopterology has not. Mail dropped.

## CE 1.21.1 package moves (vs item-gap-implementation-plan.md)

| Topic | Plan / CE20 said | CE 1.21.1 actual |
|---|---|---|
| Energy impl | `forestry.energy` | `forestry.core.content.energy` (+ shared BER bits still under textures `engine_*`) |
| Sorting impl | `forestry.sorting` | `forestry.core.content.sorting` |
| Farm/cultivation impl | `forestry.farming` / `forestry.cultivation` | **one** jar package `forestry.agriculture` in `src/farms/java` (`ModuleFarming`, `ModuleCultivation`) |
| Farm API | `api.farming` | **`api.agriculture`** |
| Filter API | `api.genetics.filter` | **`api.core.genetics.filter`** |
| Engine fuels API | `api.fuels` Engine* | CE: `api.core.machines.fuels` (`EngineBronzeFuel`, `EngineCopperFuel`, `FuelManager.biogasEngineFuel` / `peatEngineFuel`). Local already has `api.fuels` for factory — **extend that**, do not invent a second fuels package. |
| Engines shipped | peat, biogas, combustion, clockwork, solar + solar_panel | **Only** `peat`, `biogas`, `clockwork`. Combustion / solar / solar_panel **removed in 1.21.1** (still exist in CE20). |
| Butterfly items | `butterfly_ge`, `serum_ge`, … | `butterfly`, `butterfly_serum`, `caterpillar`, `cocoon` (`ButterflyLifeStage.itemId`) |
| `forestry.core.engine` | confused with energy | Platform genetics/circuits/climate — **not** the energy module |

Module ids unchanged: `forestry:energy`, `farming`, `cultivation`, `sorting`, `lepidopterology`.

## Scale (Java excl. package-info)

| Area | Files |
|---|---|
| `core/content/energy` | 22 |
| `core/content/sorting` | 24 |
| `agriculture` (farms jar total) | 103 |
| → multifarm | 26 |
| → planter | 15 |
| → farmlogic | 38 |
| lepidopterology (butterflies jar) | 61 |
| `api.agriculture` | 15 |
| `api.lepidopterology` (+ genetics) | 12 |
| `api.core.genetics.filter` | 5 |

Recipes: 3 engines; 76 farm/planter; 1 genetic_filter; 35 butterfly species JSON; 1 butterfly_mutation JSON (+ mating/chest recipes).

## Re-Forestry package mapping (keep our layout)

| CE 1.21.1 | Our target |
|---|---|
| `forestry.core.content.energy` | `com.leon1236.reforestry.energy` |
| `forestry.core.content.sorting` | `com.leon1236.reforestry.sorting` |
| `forestry.agriculture` multifarm | `com.leon1236.reforestry.farming` |
| `forestry.agriculture` planter | `com.leon1236.reforestry.cultivation` |
| `forestry.agriculture.farmlogic` | shared under farming (cultivation reuses) |
| `forestry.lepidopterology` | `com.leon1236.reforestry.lepidopterology` |
| `api.agriculture` | `api.agriculture` |
| `api.core.genetics.filter` | `api.genetics.filter` *or* `api.core.genetics.filter` (pick one; prefer mirror CE path under `api/`) |

Do **not** depend on `forestryfarms` / `forestrybutterflies` jars — copy into our packages (standalone).
