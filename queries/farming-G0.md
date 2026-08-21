# G0 — Agriculture API + farming module

**Date:** 2026-08-20  
**Stage:** Wave 4 `G0`. No farm blocks, tiles, menus, recipes, or circuits.

## Package map

| CE | Re-Forestry |
|---|---|
| `forestry.api.agriculture` | `com.leon1236.reforestry.api.agriculture` |
| `forestry.api.plugin.IFarmingRegistration` / `IFarmTypeBuilder` / `IWindfallFarmableBuilder` | `com.leon1236.reforestry.api.plugin` |
| `forestry.api.core.multiblock.IFarmController` / `IFarmComponent` / `IMultiblockLogicFarm` | `com.leon1236.reforestry.api.multiblock` (next to alveary, not `api.core.multiblock`) |
| `forestry.agriculture.farmlogic.FarmingManager` | `com.leon1236.reforestry.farming.farmlogic.FarmingManager` |
| `forestry.agriculture.ModuleFarming` | `com.leon1236.reforestry.farming.ModuleFarming` |
| `forestry.apiimpl.fake.FakeFarmingManager` | `com.leon1236.reforestry.core.FakeFarmingManager` |
| `forestry.agriculture.plugin.FarmingRegistration` | `com.leon1236.reforestry.core.plugin.FarmingRegistrationImpl` |

Farm type ids stay CE paths under our namespace: `reforestry:crops`, `gourd`, `shroom`, `infernal`, `poales`, `succulentes`, `ender`, `arboreal`, `peat`, `orchard`, `cocoa` (`ForestryFarmTypes`).

Module id: `reforestry:farming`. Toggle appears in `config/reforestry/modules.properties` from `ModuleConfig`.

## FluidVariant instead of FluidStack

CE `IFarmHousing.hasLiquid` / `removeLiquid` take NeoForge `FluidStack`. Re-Forestry uses Fabric transfer:

```
boolean hasLiquid(FluidVariant variant, long amount);
void removeLiquid(FluidVariant variant, long amount);
```

`ResourceLocation` → `Identifier`. Nested farm component listeners match alveary generics (`Listener<T>` / `Active<T>`).

`hasLiquid` / `removeLiquid` amounts are Fabric transfer **droplets** (same unit as `FluidUnits` / factory tanks), not CE millibuckets.

## No-op manager when farming is off

`IForestryApi.getFarmingManager()` never throws. Default is `FakeFarmingManager` with `isLoaded() == false`, fertilizer value 0, no farm types. `ModuleFarming.init()` runs `PluginManager.runFarmingRegistration` (plugins, then default fertilizer 500, then a real `FarmingManager` with `isLoaded()` default true). If the farming module is disabled, registration never runs and the fake manager stays.

G0 `buildFarmTypes()` returns an empty map. `createFarmType` / `modifyFarmType` store builders so plugins do not crash; types are not built until G3. G3 `FarmTypeBuilderImpl.build()` must apply `windfallFarmableModifications` then convert windfall builders into farmables, matching CE `FarmTypeBuilder.build()`. Leftover `modifyFarmType` for an id that was never created should throw at that G3 build, matching CE `ModifiableRegistrar`.

## Fertilizer 500

After plugins run, `ModuleFarming.init` registers `CoreItems.FERTILIZER_COMPOUND` at **500** on the registration object if no plugin already did, then `FarmingManager` is constructed. Matches CE `AgricultureForestryPlugin.registerFarming` timing (value is in the manager map at construction). No `AgricultureForestryPlugin` in G0 (that plugin would also register circuits/types). Duplicate `createFarmType` ids throw, matching CE `Registrar`.

## No pattern DSL

CE farm assembly uses `FarmPattern` / `MultiblockPattern`. Re-Forestry will reuse `RectangularMultiblockControllerBase` in G2. Do not port that DSL.

Farm shape (for G2, not implemented here): X,Z in [3,5], Y exactly 4, min 36 blocks; interior plain; exterior band `dy==2` plain; ≥1 gearbox. Extent `max(NS,EW) * multiFarmSize + 1`, default size 2.

## No blocks

G0 has no farm blocks, tiles, menus, recipes, farm circuits, or cultivation planters. G1 55 blocks and G2a assemble are done. Next: **G2b** GUI/inventory.
