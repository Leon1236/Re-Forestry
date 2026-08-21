# G4a — Gourd, shroom, poales, succulentes, infernal

**Date:** 2026-08-21  
**Stage:** Wave 4 `G4a`. Five crop-like farm types from CE `DefaultFarms` + the CE circuit map. Not G4b (arboreal/peat/orchard/cocoa/ender) and not cultivation.

## Farm types (CE `DefaultFarms` as-is)

| Id | Logic | Icon | Fertilizer | Water (mb) | Soil | Farmables |
|---|---|---|---|---|---|---|
| `reforestry:gourd` | `FarmLogicGourd` | melon | 10 | `40 * hydration` | dirt → farmland | pumpkin seeds / melon seeds (`FarmableGourd`) |
| `reforestry:shroom` | `FarmLogicMushroom` | red mushroom | 20 | `80 * hydration` | mycelium, podzol | brown + red mushroom (`FarmableMushroom`) |
| `reforestry:infernal` | `FarmLogicInfernal` | nether wart | 20 | 0 | soul sand | nether wart age 3 (`FarmableAgingCrop`, no replant) |
| `reforestry:poales` | `FarmLogicReeds` | sugar cane | 10 | `20 * hydration` | sand, dirt | stacked sugar cane height 3 |
| `reforestry:succulentes` | `FarmLogicSucculent` | green dye | 10 | 1 | sand | stacked cactus height 3 |

Succulent icon is CE `Items.GREEN_DYE`. On 26.2 that field is gone; the same item is `Items.DYE.green()`.

## Circuits (CE `AgricultureForestryPlugin`, no extra pairs)

| Tube | Type | Managed | Manual |
|---|---|---|---|
| bronze | crops | yes (G3) | yes (G3) |
| obsidian | gourd | yes | yes |
| apatite | shroom | yes | yes |
| diamond | poales | no | yes |
| gold | succulentes | no | yes |
| blaze | infernal | yes | no |

Copper/tin/iron/lapis/ender tubes stay G4b. Circuit uids are `farm.managed.{path}` / `farm.manual.{path}` (CE).

## Base classes

`FarmLogicMushroom` extends `FarmLogicArboreal`, which extends `FarmLogicHomogeneous`. Those bases are ported because mushroom/infernal need them. The **arboreal farm type is not registered** (G4b). Empty sides still use `FakeFarmLogic` when `reforestry:arboreal` is missing.

`FarmLogicReeds` / `FarmLogicSucculent` are empty subclasses of `FarmLogicSoil`, same as CE 1.21.1. They harvest stacked crops via `FarmLogic.harvest` + `FarmableStacked`. They do **not** auto-plant; CE only ships **manual** circuits for those two types.

## 26.2

Every CE `hasChunkAt` on these logics is `Level.isLoaded`. Water consumption is still millibuckets on `IFarmType`; `FarmManager` converts to droplets at the housing boundary (G3). Infernal water is 0 so that check is skipped.

Review extras vs a blind CE copy:

- `FarmLogicArboreal.maintainSeedlings` checks `isLoaded` before planting (CE had none).
- `FarmableStacked.getCropAt` checks `isLoaded` on the mature-height block.
- `FarmLogicHomogeneous` soil loop: `isLoaded` before `getBlockState`; break/hardness use the column `position`, not the row start.
- `FarmLogicInfernal.harvest` skips empty **column** blocks (`position`). CE checks the row start `pos` every iteration, which skips the whole row if the first soil cell is air.

## JEI

`FarmingJeiPlugin` emits one recipe per **manual** `IFarmCircuit` (CE). After G4a that list is crops + gourd + shroom + poales + succulentes. Infernal is managed-only, so it has no JEI row (same as CE). Soils/germlings/products come from `IFarmType.getSoils()` + `IFarmable.addGermlings` / `addProducts`. Nether wart `FarmableAgingCrop` has empty products (CE constructor); that only matters if infernal ever appears in JEI.

## Lang

Display keys are `Util.makeDescriptionId("farm", id)` → `farm.reforestry.gourd` etc. `de_de` / `zh_tw` leftover CE files used `succulent` / `reed`; the game looks up `succulentes` / `poales`. Those locales now have both.

## DoD D12 smoke

- Obsidian tube, `reforestry.farms.managed`: dirt + pumpkin/melon seeds + fertilizer + water + FE. Checkerboard water vs stems; harvests pumpkin/melon fruit.
- Apatite managed: mycelium or podzol + brown/red mushrooms. Plants mushrooms that can survive; harvests huge-mushroom blocks.
- Blaze managed: soul sand + nether wart. Places soil, plants wart, harvests age 3 (no replant). Water not required.
- Diamond **manual** only: player-grown sugar cane of height 3 is harvested. Gold **manual** only: cactus height 3.
- JEI `reforestry:farming` shows the four new manual circuits (not infernal).

## Not in G4a

Arboreal, peat, orchard, cocoa, ender (G4b). Cultivation planters. Managed poales/succulentes and manual infernal (CE has none).

Next: **G4b**.
