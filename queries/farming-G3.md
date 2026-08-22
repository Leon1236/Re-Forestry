# G3 — Crops farm logic (wheat)

**Date:** 2026-08-20  
**Stage:** Wave 4 `G3`. Assembled farm with a crops circuit, water, fertilizer, and wheat seeds plants and harvests wheat. Other farm types stay G4.

## Farm type

`AgricultureForestryPlugin.registerFarming` now builds `reforestry:crops` (CE `DefaultFarms.addCropFarmables` only):

- Logic `FarmLogicCrops`, icon wheat
- Water `(int)(20 * hydrationModifier)` millibuckets
- Fertilizer 5
- Soil dirt → farmland
- Farmables: wheat (AGE 7, replant 0), potato, carrot, beetroot (AGE 3, replant 0)

`FarmTypeBuilderImpl.build()` matches CE: fertilizer/water required, apply leftover windfall modifiers, then convert windfall builders into farmables. `FarmingRegistrationImpl.buildFarmTypes()` throws if `modifyFarmType` targeted an id that was never created.

Display name: `Util.makeDescriptionId("farm", id)` → `farm.reforestry.crops`. Grammar keys in leftover lang are `farm.reforestry.grammar.managed` / `manual` (CE `farm.forestry.grammar.*`).

## `Level.isLoaded` (not `hasChunkAt`)

CE `FarmLogicCrops` / `FarmLogic` / `FarmLogicWatered` / `FarmHelper` / `FarmTarget` all call `world.hasChunkAt`. On 26.2 that is deprecated. Every copy uses `level.isLoaded(position)` instead.

## Water millibuckets → droplets

CE `IFarmHousing.hasLiquid` / `removeLiquid` take NeoForge millibucket `FluidStack`. Ours take Fabric `FluidVariant` + **droplets**. Convert at the housing boundary only:

| Call | CE millibuckets | Re-Forestry droplets |
|---|---|---|
| Place a water source (`FarmLogicWatered.trySetWater`) | 1000 (`FluidType.BUCKET_VOLUME`) | `FluidUnits.mbToDroplets(1000)` |
| Cultivate / harvest water use | `getWaterConsumption` (crops: `20 * hydration`) | `FluidUnits.mbToDroplets(mb)` |

`IFarmType.getWaterConsumption` still returns millibuckets like CE. `FarmManager` converts before `hasLiquid` / `removeLiquid`. Zero consumption skips the liquid check and is a no-op drain.

## Work loop

`FarmController.doWork()` runs `FarmManager` (cultivate / harvest / collect, fertilizer, pending produce). Gearbox still spends FE then calls `doWork()`. No gearbox energy for >4 ticks sets `ForestryError.NO_POWER`. Control `cancelTask` is per-side via `FarmHelper.isCycleCanceledByListeners`. Extent is `max(NS,EW) * multiFarmSize + 1` (same G2a formula, recomputed when targets refresh).

Socket apply is unchanged from G2b: `FarmController.setSocket` calls `ICircuitBoard.onInsertion(this)` with the **controller** (`IFarmHousing`), not the tile. `TileFarm.setSocket` only delegates. Matches CE `FarmController.setSocket`.

Bronze tube circuits: `farm.managed.crops` on `reforestry.farms.managed`, `farm.manual.crops` on `reforestry.farms.manual`. Other tubes wait for G4.

## JEI

CE category id `forestry:farming` → `reforestry:farming` (`FarmingJeiPlugin`, catalyst intricate circuit board). Recipes are manual farm circuits only (CE). Crops-only until G4 registers the rest. Not skipped.

## DoD D12 smoke

- Assemble a 3×4×3 farm (gearbox + valve + hatch + control).
- Solder a bronze tube on `reforestry.farms.managed`, insert the board, feed dirt / wheat seeds / fertilizer / water / FE. Farm places farmland (and water on managed), plants wheat, harvests age 7.
- Repeat with `reforestry.farms.manual` (no auto water). Potato / carrot / beetroot germlings also plant.
- Overlay errors: `NO_FERTILIZER`, `NO_LIQUID_FARM`, `NO_FARMLAND`, `NO_POWER`.
- JEI category `reforestry:farming` shows the **manual** crops circuit only (soil dirt, four germlings, four products).

## Skips (cannot exist yet)

- `FarmController.resetFarmLogic` looks up `reforestry:arboreal` like CE. That type is G4b, so empty sides use `FakeFarmLogic` until then.
- `IFarmTypeBuilder.addGermling` / `addProduct` are stored then ignored in `build()`, same as CE 1.21.1. Orchard/cocoa JEI is G4b.

## Not in G3

Gourd/shroom/poales/succulentes/infernal (G4a). Arboreal/peat/orchard/cocoa/ender (G4b). Cultivation planters. GP0a.

Next: **G4a**.
