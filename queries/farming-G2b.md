# G2b — Farm inventory + GUI

**Date:** 2026-08-20  
**Stage:** Wave 4 `G2b`. Assembled farm opens the multifarm GUI. Slots, socket, and water tank persist on the controller. No hatch/valve/energy export, no crop logics, no tube→farm-type circuits.

## Inventory lives on the controller

Same alveary pattern: `InventoryFarm` is owned by `FarmController`, not by hatches. `TileFarm` is a `Container` that delegates to `getInternalInventory()`. NBT is `ValueInput`/`ValueOutput` nested in the controller `CompoundTag` (`Items`, `Tanks`, `Sockets`, hydration/fertilizer ints). `onDestroyed` drops inventory + the circuit board.

`InventoryPlantation` is farm-local (not a second global inventory framework). Slot map copies CE `InventoryFarm.CONFIG`: resources 0,6; germlings 6,6; production 12,8; fertilizer 20,1; can 21,1.

**acceptsAs\*:** fertilizer uses `IFarmingManager.getFertilizeValue > 0`. Resources and germlings are **permissive** until a real `IFarmType` is applied (FakeFarmLogic is ignored). G3 tightens via farm types. Can slot is water containers only. Even while permissive, circuit boards, fertilizer, and water cans are rejected from resource/germling so shift-click reaches the socket / fertilizer / can slots.

Unassembled farms expose an empty inventory (CE `FakeInventoryAdapter`), while the real `InventoryFarm` stays on the controller until `onDestroyed`.

## Menu / screen

- Menu id `reforestry:farm` (`FarmingMenuTypes.FARM`).
- `ContainerFarm.fromNetwork` is `BlockPos` `STREAM_CODEC` (alveary gold path).
- Hydration, fertilizer bar, tank mB, climate, and errors go through `ContainerData` on `TileFarm`. Not Forge `PacketGuiStream`.
- `ScreenFarm` extends `ScreenForestry` (216×220, `mfarm.png`). Tank at 15,19 with overlay origin 216,18. Socket at 69,40. FarmLogicSlot N/S/W/E at CE pixels. Fertilizer blit 81,94 width 4 height scaled 16. Hint key `farm`. Title `for.gui.farm.title`. Hydration ledger stacks at y=36 under climate (same as power+climate). Fake farm-logic slots have no empty tooltip. JEI extra areas include the hydration ledger.
- Water tank: controller-owned `FilteredFluidStorage`, WATER-only, 10000 mB (`Constants.PROCESSOR_TANK_CAPACITY`). Pipette click regions like factory still. G2c valve will wire `FluidStorage.SIDED`.
- `SlotCircuitSocket.set` now calls `ISocketable.setSocket` (needed so farm and centrifuge sockets actually apply). Socket max stack is 1 so a stack of boards is not consumed.

## Circuits (layouts only)

`AgricultureForestryPlugin` (`reforestry:plugin` entry, `shouldLoad()` = farming module loaded) registers:

- `ForestryCircuitLayouts.MANAGED_FARM` = `reforestry.farms.managed`
- `ForestryCircuitLayouts.MANUAL_FARM` = `reforestry.farms.manual`
- Socket type `ForestryCircuitSocketTypes.FARM` = `reforestry:farm`

No bronze/copper/etc tube→type circuits. `CircuitFarmLogic` exists and skips a missing `IFarmType` instead of crashing. FarmController is `ISocketable` (1 slot, type FARM); inserting a board applies `ICircuitBoard.onInsertion(controller)`.

`ModuleManager` marks a module loaded **before** `init()`, and `PluginManager` re-checks `shouldLoad()` on each registration pass (so farm layouts are not frozen out when apiculture registers first).

## Not in G2b

Gearbox `EnergyStorage`, hatch auto-export, crop logics, tube circuits.

Next: **G2c** hatch/valve/gearbox IO — **done**. See `queries/farming-G2c.md`.
