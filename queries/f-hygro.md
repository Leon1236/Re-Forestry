# F-HYGRO — Hygro ice fallback + tank filter

Stage complete 2026-08-20. Extends `queries/factory-F16.md`.

## What landed

- **Factory-off ice:** `HygroregulatorFallbackRecipes` now includes `ForestryFluids.ICE` with the same climate as `data/reforestry/recipe/hygroregulator/ice.json`: humidity +2, temperature −2, retainTime 10, 1 mB per cycle.
- **Tank insert filter:** `HygroregulatorFluidSetup` collects fluids from `IHygroregulatorRecipe` and **always unions** fallback water, lava, and ice. Reloaded from `ModuleFactory.reloadFluidFilters` (server start + datapack reload). `FilteredFluidStorage.canInsert` uses `HygroregulatorInputFluids.test`, so pipes/pipette/buckets cannot dump garbage fluids. `RecipeUtils.getHygroregulatorRecipe` matches the same interface (not only the `HygroregulatorRecipe` record).
- **Input slot:** `FluidContainerHelper.drainIntoTank` + `isFilledContainer` / `canTankAccept` — ice bucket (`reforestry:bucket_ice`) and any can/capsule whose extracted fluid is in the allowed set.

## Ice id

Keep **`reforestry:ice`**. Do **not** rename the fluid (or the hygro JSON) to CE 1.21.1 `crushed_ice`. Display lang already says “Crushed Ice”; the registry path stays `ice`.

## Factory-off

Fallback class stays in the factory package (RecipeUtils already imports it). When Factory recipe types never load, datapack ice is missing but fallback ice still matches. The allowed-fluid set always includes the three fallbacks even before a reload.
