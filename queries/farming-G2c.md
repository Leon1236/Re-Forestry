# G2c — Farm hatch / valve / gearbox IO

**Date:** 2026-08-20  
**Stage:** Wave 4 `G2c`. Assembled farm gearbox accepts FE, valve accepts water, hatch exports products. No crop logics, no tube→farm-type circuits.

## Gearbox

`TileFarmGearbox` is `IFarmComponent.Active`. Buffer is Team Reborn `SimpleEnergyStorage` like the alveary fan (`TileAlvearyClimatiser`): capacity **10000**, max insert **200**, extract **0**. `EnergyStorage.SIDED` on `FarmingTiles.GEARBOX` — no `AttachmentType`, no `ModuleEnergy` dependency. A peat engine can still push FE into the gearbox.

Work loop copies CE `TileFarmGearbox`: `WORK_CYCLES = 4`, `ENERGY_PER_OPERATION = 200` (4×50). `EnergyHelper.consumeEnergyToDoWork` fills `workCounter`; every 5 ticks if `workCounter >= 4` it calls `controller.doWork()`. If that returns false, `activationDelay` throttles up to 120 (`10 * previousDelays`). G2c `doWork()` is still a no-op (returns false after the control-block cancel check), so a powered gearbox idles with that throttle until G3.

NBT is `ValueInput`/`ValueOutput` (`Energy` long like the fan, clamped to capacity, plus CE `ActivationDelay` / `PrevDelays`). Direct `amount` mutation, not `CompoundTag`. `EnergyStorage.SIDED` returns null if the gearbox BE is removed (CE `getEnergyHandler`).

## Hatch

`TileFarmHatch` is `IFarmComponent.Active`. CE `allowsAutomation` is true on the hatch only (`MultiblockTileEntityForestry` defaults false). `TileFarm` is `WorldlyContainer`: non-hatch faces return no slots so vanilla hoppers cannot dump into a gearbox/plain. Fabric `ItemStorage.SIDED` also auto-wraps any `Container` BE — non-hatch farm tiles register `Storage.empty()` so pipes cannot use that fallback. Hatch uses `InventoryHelper.registerSided` → `ContainerStorage.of`.

Hoppers on the hatch see the controller inventory:

- `getSlotsForFace`: all 22 farm slots (CE `InventoryAdapter` slot map).
- Insert: `canPlaceItem` (resources / germlings / fertilizer / water can). Product slots stay insert-false.
- Extract: production slots 12–19 only (`InventoryFarm.isProductSlot`, CE `canTakeItemThroughFace`).

Every 40 ticks the hatch pushes **one stack** from the product inventory into the first matching neighbor: `ItemStorage.SIDED.find` on the 6 adjacent sides, CE `AdjacentInventoryCache` filter (`y < hatch` and not a `TileFarm`). For a normal cube that is the block **below**. Uses `Level.isLoaded` (not deprecated `hasChunkAt`).

## Valve

`FluidStorage.SIDED` on `FarmingTiles.VALVE` returns the controller water tank (`FilteredFluidStorage`, WATER-only, 10000 mB from G2b). Same shape as alveary hygroregulator.

## Control

`TileFarmControl` is `IFarmComponent.Listener`. `cancelTask` copies CE: check UP, DOWN, and the farm-logic direction; ignore `FarmBlock` neighbors; `Level.getSignal(controlPos, facing) > 0`. Neighbor chunk uses `isLoaded`.

`FarmController` collects Active + Listener on `onBlockAdded` / `onBlockRemoved` like `AlvearyController`. `serverTick` / `clientTick` every Active (no CE per-part tick offset). `doWork()` consults `farmListeners.cancelTask` on each horizontal side (CE `FarmManager` continues other sides; it does not abort the whole cycle) then returns false, so G3 can add real work without a new listener list.

## Skipped

- Crop types / `FarmLogicCrops` / wheat (G3).
- Tube circuits that pick a farm type (G3).
- CE `NO_POWER` error from empty gearboxes (G3, when `doWork` is real).
- CE Active tick offsets (user: tick like alveary).
- CONTROL redstone wire visual (`canConnectRedstone` has no 26.2 equivalent; same skip as G2a).

Next: **G3** crops logic.
