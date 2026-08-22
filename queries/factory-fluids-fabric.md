# Factory fluids on Fabric (F4 design)

## Units

| Layer | Unit | Rule |
|---|---|---|
| CE / datapack recipes | millibuckets (mB) | Keep recipe JSON numbers as CE wrote them |
| Runtime tanks / transfer | droplets | Fabric transfer API (`FluidConstants.BUCKET` = 81000 droplets) |

Helper: `core.fluids.FluidUnits` — `mbToDroplets(mb)` / `dropletsToMb(droplets)` using **81 droplets per mB**. Never store mB inside a tank amount field.

## Multi-tank facade (not Forge TankManager)

Do **not** port `TankManager` / `IFluidHandler` / Forge capability hierarchy.

| Piece | Role |
|---|---|
| `FilteredFluidStorage` | `SingleFluidStorage` + insert filter + `onFinalCommit` → `setChanged` |
| `MultiFluidTank` | `CombinedStorage` over named tanks; NBT via each tank’s `readValue` / `writeValue` |
| `FluidStorage.SIDED` | Expose the whole `MultiFluidTank` (or one tank) from the BE, same pattern as hygroregulator |

### Dual-tank builder (F7 Still shape)

```java
MultiFluidTank tanks = MultiFluidTank.builder(this::setChanged)
    .tank("Resource", FluidUnits.mbToDroplets(10000), FilteredFluidStorage.only(ForestryFluids.BIOMASS.getFluid()))
    .tank("Product", FluidUnits.mbToDroplets(10000), FilteredFluidStorage.only(ForestryFluids.BIO_ETHANOL.getFluid()))
    .build();

FluidStorage.SIDED.registerForBlockEntity((tile, dir) -> tile.getTankManager(), TYPE);
```

Named access: `tanks.tank("Resource")` / `tanks.tank(0)`.

Filters must stay in core as predicates / fluid references — **never** import `FactoryRecipeTypes` from core (avoids CE’s core→factory cycle). Factory-owned recipe allowlists can supply predicates later.

## Fluids ownership

Package `com.leon1236.reforestry.core.fluids`. Registered from `ModuleCore` via `ForestryFluids.init()`. Factory machines consume these fluids; they do not own the registry.
