# G4b — Arboreal, peat, orchard, cocoa, ender

**Date:** 2026-08-21  
**Stage:** Wave 4 `G4b`. Five remaining farm types from CE `DefaultFarms` + the rest of the CE circuit map. Not cultivation (`CU0+`).

## Farm types (CE `DefaultFarms` as-is)

| Id | Logic | Icon | Fertilizer | Water (mb) | Soil | Farmables |
|---|---|---|---|---|---|---|
| `reforestry:arboreal` | `FarmLogicArboreal` | oak sapling | 10 | `10 * hydration` | dirt/humus → humus | vanilla saplings (`FarmableSapling` / mangrove) + `FarmableGE` |
| `reforestry:peat` | `FarmLogicPeat` | peat | 2 | `20 * hydration` | bog earth | products peat + dirt (`FarmableInfo`) |
| `reforestry:ender` | `FarmLogicEnder` | ender eye | 20 | 0 | end stone | `FarmableChorus` (age 5 flower) |
| `reforestry:orchard` | `FarmLogicOrchard` | cherry fruit | 10 | `40 * hydration` | none | tree saplings + fruit products when arboriculture is loaded |
| `reforestry:cocoa` | `FarmLogicCocoa` | cocoa beans | 120 | `20 * hydration` | none | cocoa beans germling/product; plants via `FarmableCocoa` |

CE 1.21.1 names: `FarmableSapling` (not `FarmableVanillaSapling`), `FarmableChorus` (not `FarmableChorusPlant` / aging crop). Chorus plants are harvested by `FarmLogicEnder` with `CropDestroy`, not a separate farmable.

## Circuits (CE `AgricultureForestryPlugin`, no extra pairs)

| Tube | Type | Managed | Manual |
|---|---|---|---|
| copper | arboreal | yes | no |
| copper | orchard | no | yes |
| tin | peat | yes | yes |
| iron | ender | yes | yes |
| lapis | cocoa | no | yes |

Plus the G4a pairs (bronze crops, obsidian gourd, apatite shroom, diamond poales manual, gold succulentes manual, blaze infernal managed). Circuit uids stay `farm.managed.{path}` / `farm.manual.{path}`.

`FarmController.resetFarmLogic` now finds `reforestry:arboreal` and empty sides use managed arboretum, same as CE.

## Soft arboriculture

Vanilla sapling windfalls always register. `FarmableGE` and the orchard fruit/species loop run only if `reforestry:arboriculture` is loaded. Same jar, no donor mod. Forestry saplings are `reforestry:sapling` with `tree_genome`; planting writes `TileSapling`. Tag `reforestry:tree_saplings` lists `sapling_ge` (`required: false`).

## Germlings / products → JEI

CE `IFarmTypeBuilder.addGermling` / `addProduct` were stored and ignored in `build()`. Cocoa, peat, and orchard need those lists for germling slots and JEI. `FarmTypeBuilderImpl` now wraps leftover germlings/products as `FarmableInfo`. JEI still iterates `IFarmable.addGermlings` / `addProducts` (manual circuits only). New JEI rows: peat, ender, cocoa, orchard. Arboreal is managed-only, so no JEI row (CE).

## 26.2 / Fabric

Every CE `hasChunkAt` on these logics is `Level.isLoaded`. Cocoa break/hardness uses the column `location`, not the row start. `CropChorusFlower` returns a chorus flower stack instead of NeoForge `CommonHooks.handleBlockDrops`. Water is still millibuckets on `IFarmType`; `FarmManager` converts to droplets.

`IFruitBearer` is under `api.core.genetics` (CE path). `TileLeaves` and `TileFruitPod` implement it for orchard harvest (`CropFruit`).

`de_de` / `zh_tw` leftover files used `arboretum`; the game looks up `arboreal`. Those locales now have both.

CE puts humus in `#minecraft:dirt` (block + item) and humus/bog earth/peat in `#minecraft:mineable/shovel`. 26.2 saplings survive on `#minecraft:supports_vegetation` → `#minecraft:substrate_overworld` → `#minecraft:dirt`, so the dirt tag is what lets the arboreal farm plant on the humus it places. `FarmableGE` fruit windfall is built lazily so it still sees tree species if farming registration ever ran first.

## DoD D12 smoke

- Copper tube, `reforestry.farms.managed`: dirt or humus + oak/forestry sapling + fertilizer + water + FE. Places humus, plants saplings, harvests logs, collects apples/sticks.
- Tin managed: bog earth + water + fertilizer. Places bog earth and water, harvests mature peat → peat item + dirt.
- Iron managed: end stone + chorus flower. Plants with spacing, harvests age-5 flowers then plants. Water not required.
- Copper **manual**: orchard harvests ripe `IFruitBearer` leaves/pods on existing fruit trees. Lapis **manual**: cocoa beans on jungle logs, harvests age 2.
- JEI `reforestry:farming` adds peat / ender / cocoa / orchard (not arboreal, not infernal).

## Not in G4b

Cultivation planters (`CU0+`). Managed orchard/cocoa and manual arboreal/infernal (CE has none).

Next: **CU0**.
