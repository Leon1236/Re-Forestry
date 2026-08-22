# Forestry CE Factory module inventory (verified via MCP `thedarkcolour-ForestryCE`)

Research date: 2026-07-25. Paths verified with `list_files` / `search_code` / `get_file`. Do not invent.

## 1. Module ID & dependencies

| Item | Source of truth |
|---|---|
| `ForestryModuleIds.FACTORY` = `forestry:factory` | `src/main/java/forestry/api/modules/ForestryModuleIds.java` |
| `ModuleFactory` | `src/main/java/forestry/factory/ModuleFactory.java` |
| Declared module deps | **Only CORE** — inherits `BlankForestryModule.getModuleDependencies()` → `List.of(ForestryModuleIds.CORE)`. Does **not** declare ENERGY or FLUIDS. |
| Blank default | `src/main/java/forestry/modules/BlankForestryModule.java` |

`ModuleFactory` responsibilities:
- `setupApi()`: init `FuelManager.fermenterFuel`, `moistenerResource`, `rainSubstrate` (not engine fuels)
- Register 2 packets: recipe transfer request/update
- Client: `FactoryClientHandler`

## 2. Machines inventory

### Plain (`BlockTypeFactoryPlain` → `FactoryBlocks.PLAIN`)

Source: `src/main/java/forestry/factory/blocks/BlockTypeFactoryPlain.java`, tiles under `forestry/factory/tiles/`, menus `FactoryMenuTypes`.

| Machine id | Tile | Base class | FE? | Fluids (tank layout) | JSON recipes | Sockets | Menu |
|---|---|---|---|---|---|---|---|
| `fabricator` | `TileFabricator` | `TilePowered` | yes (maxTransfer 1100, capacity **3300**) | molten tank (`NONE` layout) | `fabricator` + `fabricator_smelting` | no | yes |
| `bottler` | `TileBottler` | `TilePowered` | yes (1100, **40000**) | 1 resource tank (`RESOURCE`) | **none** (runtime `BottlerRecipe` via FluidUtil) | no | yes |
| `carpenter` | `TileCarpenter` | `TilePowered` | yes (1100, `MACHINE_MAX_ENERGY`=40000) | 1 resource (`RESOURCE`) | `carpenter` | no | yes |
| `centrifuge` | `TileCentrifuge` | `TilePowered` | yes (800, 40000) | none (`NONE`) | `centrifuge` | **yes** (1 socket) | yes |
| `fermenter` | `TileFermenter` | `TilePowered` | yes (2000, **80000**) | resource+product (`BOTH`) | `fermenter` + `FuelManager.fermenterFuel` | no | yes |
| `moistener` | `TileMoistener` | **`TileBase` (NOT powered)** | **no FE** | water resource (`RESOURCE`) | `moistener` + `FuelManager.moistenerResource` | no | yes |
| `smelter` | `TileSmelter` | `TilePowered` | yes (1100, 40000) | none (`NONE`) | `smelter` | **yes** | yes |
| `squeezer` | `TileSqueezer` | `TilePowered` | yes (1100, 40000) | product tank (`PRODUCT`) | `squeezer` + `squeezer_container` | **yes** | yes |
| `still` | `TileStill` | `TilePowered` | yes (1100, **80000**) | resource+product (`BOTH`) | `still` | no | yes |

### TESR (`BlockTypeFactoryTesr` → `FactoryBlocks.TESR`)

| Machine id | Tile | Base | FE? | Notes |
|---|---|---|---|---|
| `rainmaker` | `TileMillRainmaker` | `TileMill` → `TileBase` | **no** | No menu; right-click with rain substrate from `FuelManager.rainSubstrate`. Animated mill. |

Registration hubs:
- Blocks: `src/main/java/forestry/factory/features/FactoryBlocks.java`
- Tiles: `.../FactoryTiles.java`
- Menus: `.../FactoryMenuTypes.java` (no rainmaker menu)
- Block classes: `BlockFactoryPlain.java`, `BlockFactoryTESR.java`
- Client menus: `FactoryClientHandler.java` (TESR cutout layer only; mill BER is core `RenderMill`)

## 3. Recipe types (`FactoryRecipeTypes`)

Source: `src/main/java/forestry/factory/features/FactoryRecipeTypes.java`

| Registry id | Feature field | API interface | Impl | Notes |
|---|---|---|---|---|
| `forestry:carpenter` | `CARPENTER` | `ICarpenterRecipe` | `CarpenterRecipe` | shaped grid + optional fluid/box |
| `forestry:centrifuge` | `CENTRIFUGE` | `ICentrifugeRecipe` | `CentrifugeRecipe` | chance products |
| `forestry:fabricator` | `FABRICATOR` | `IFabricatorRecipe` | `FabricatorRecipe` | shaped + molten fluid |
| `forestry:fabricator_smelting` | `FABRICATOR_SMELTING` | `IFabricatorSmeltingRecipe` | `FabricatorSmeltingRecipe` | melt item → fluid |
| `forestry:fermenter` | `FERMENTER` | `IFermenterRecipe` | `FermenterRecipe` | item+fluid → biomass |
| `forestry:hygroregulator` | `HYGROREGULATOR` | `IHygroregulatorRecipe` | `HygroregulatorRecipe` (**apiculture** alveary) | registered on Factory module |
| `forestry:moistener` | `MOISTENER` | `IMoistenerRecipe` | `MoistenerRecipe` | item→item, water+light |
| `forestry:smelter` | `SMELTER` | `ISmelterRecipe` | `SmelterRecipe` | alloy-style inputs |
| `forestry:squeezer` | `SQUEEZER` | `ISqueezerRecipe` | `SqueezerRecipe` | items → fluid + remnant |
| `forestry:squeezer_container` | `SQUEEZER_CONTAINER` | `ISqueezerContainerRecipe` | `SqueezerContainerRecipe` | empty can/capsule |
| `forestry:still` | `STILL` | `IStillRecipe` | `StillRecipe` | fluid→fluid |

Base API: `IForestryRecipe` (`forestry/api/recipes/`). Also `IVariableFermentable` for fermenter modifiers.

**Bottler has no RecipeType** — `BottlerRecipe` is a runtime helper using Forge `FluidUtil` item fluid handlers.

Serializer helpers: `forestry/factory/recipes/RecipeSerializers.java`.

## 4. Power model (consume FE, not generate)

```
TilePowered
  └─ ForestryEnergyStorage(maxTransfer, capacity, RECEIVE)
       └─ ForgeCapabilities.ENERGY (Forge Energy / RF-compatible)
  └─ work every WORK_TICK_INTERVAL=5 game ticks
  └─ EnergyHelper.consumeEnergyToDoWork(...)
```

Sources of truth:
- `src/main/java/forestry/core/tiles/TilePowered.java`
- `src/main/java/forestry/energy/ForestryEnergyStorage.java` (extends Forge `EnergyStorage`)
- `src/main/java/forestry/energy/EnergyHelper.java`
- Capacity constant: `Constants.MACHINE_MAX_ENERGY = 40000`

Per-machine energy notes (from tile constructors / constants):
- Centrifuge: `ENERGY_PER_WORK_CYCLE=3200` scaled by recipe time
- Carpenter: `2040` base / recipe time
- Squeezer/Smelter: `2000` / recipe time
- Still: `ENERGY_PER_RECIPE_TIME=200` × cycles
- Fermenter: fixed `setEnergyPerWorkCycle(4200)`
- Fabricator: `200` per work cycle (also heats)
- Bottler filling: viscosity-scaled energy; emptying uses **0** energy

**ModuleEnergy relationship:** Factory **consumes** FE. Engines that **generate** FE live in `ModuleEnergy` (`EngineBlockType`: PEAT, BIOGAS, CLOCKWORK, COMBUSTION, SOLAR). Factory does **not** module-depend on ENERGY — any FE provider works. Shared code package `forestry.energy.*` is used by core `TilePowered` regardless of whether engines module is enabled.

## 5. Fluid model

Core stack (not a separate `forestry/fluids` Java package — `ForestryModuleIds.FLUIDS` exists but fluids live in core):
- `TankManager`, `FilteredTank`, `StandardTank`, `FluidHelper`, `FluidRecipeFilter`, `FluidTagFilter`, `ForestryFluids`
- Capability: `ForgeCapabilities.FLUID_HANDLER` → `TankManager`
- Tiles implement `ILiquidTankTile`

`FluidRecipeFilter` (**core**) imports `FactoryRecipeTypes` — core→factory compile coupling for tank allowlists (carpenter/fermenter/still/fabricator smelting/hygroregulator).

## 6. Upgrades / circuits

| Piece | Path |
|---|---|
| `CircuitMachineUpgrade` | `forestry/factory/circuits/CircuitMachineUpgrade.java` |
| Applies to | `IMachineUpgradable` (`TilePowered`: speed/power/output multipliers) |
| Socket type | `ForestryCircuitSocketTypes.MACHINE` = `forestry:machine` |
| Socketable factory tiles | Centrifuge, Squeezer, Smelter |
| Registration | `forestry/plugin/DefaultForestryPlugin.java` (`ForestryCircuitLayouts.MACHINE_UPGRADE`, e.g. `machine.speed.boost.1`) |
| Core helpers | `ISocketable`, `ContainerSocketed`, `PacketSocketUpdate` |

## 7. Recipe JSON locations

**Not** under `src/main/resources/data/forestry/recipes` (empty).

**Generated (packed):** `src/generated/resources/data/forestry/recipes/<type>/…`

| Folder | Example type string |
|---|---|
| `carpenter/` (+ deep crates) | `forestry:carpenter` |
| `centrifuge/` | `forestry:centrifuge` |
| `fabricator/` (+ `fabricator/smelting/`) | `forestry:fabricator` / `forestry:fabricator_smelting` |
| `fermenter/` | `forestry:fermenter` |
| `moistener/` | `forestry:moistener` |
| `smelter/` | `forestry:smelter` |
| `squeezer/` (+ `container/`, `fruit/`) | `forestry:squeezer` / container type |
| `still/` | `forestry:still` |
| `hygroregulator/` | `forestry:hygroregulator` (alveary, not a factory block) |

**Datagen SoT:**
- `src/main/java/forestry/core/data/recipe/ForestryRecipeProvider.java` (huge)
- Builders: `src/main/java/forestry/core/data/builder/*RecipeBuilder.java` (Carpenter, Centrifuge, Still, …)

Machine crafting recipes for the blocks themselves also appear as root JSON like `carpenter.json`, `still.json` (shaped crafting for the machine item).

## 8. Menus & network

Menus: `FactoryMenuTypes` + `Container*` / `Gui*` under `forestry/factory/gui/`.

Factory-specific packets only:
- Serverbound `PacketRecipeTransferRequest` — JEI transfer into Carpenter/Fabricator crafting grids
- Clientbound `PacketRecipeTransferUpdate`
- Registered in `ModuleFactory.registerPackets`; ids in `PacketIdServer.RECIPE_TRANSFER_REQUEST` / `PacketIdClient.RECIPE_TRANSFER_UPDATE`

**Re-Forestry note (2026-07-27):** F18 registered JEI categories/catalysts/click areas; transfer packets above are still CE-only (not ported).

GUI sync for energy/progress uses core `IStreamableGui` / `ContainerTile` + per-tile `ContainerListener` data (fermenter/moistener/fabricator heat).

## 9. API surface

Under `forestry/api/recipes/` — factory recipe interfaces listed above (+ hygroregulator, variable fermentable).

Under `forestry/api/fuels/` — Factory uses: `FuelManager`, `FermenterFuel`, `MoistenerFuel`, `RainSubstrate`. (Engine fuel maps are Energy module.)

Circuits API: `forestry/api/circuits/` (`ForestryCircuitSocketTypes.MACHINE`).

No dedicated `forestry/api/factory` package.

## 10. Core dependencies (port prerequisites)

Must exist / port before or with Factory:
- `TilePowered`, `TileBase`, `TileMill`, `MachineProperties` / `IBlockType`
- Energy: `ForestryEnergyStorage`, `EnergyHelper` (Team Reborn Energy on Fabric)
- Fluids: `TankManager`, `FilteredTank`, `FluidHelper`, container fill/drain
- Circuits: boards, sockets, `IMachineUpgradable`
- Inventory: `InventoryAdapter`, `InventoryUtil`, ghost crafting
- `RecipeUtils` lookup helpers
- `FuelManager` maps + CoreItems fuels (fertilizer, wheat chain, iodine/dissipation charges)
- Forestry fluids (biomass, bio_ethanol, seed oil, honey, juice, ice, …) for still/fermenter/engines pipeline

## 11. ModuleEnergy engines (power generation vs consumption)

| Engine | Tile | Role |
|---|---|---|
| peat | `PeatEngineBlockEntity` | generate FE from peat |
| biogas | `BiogasEngineBlockEntity` | generate FE from fluids |
| combustion | `CombustionEngineTileEntity` | generate FE (ethanol) + coolant |
| clockwork | `ClockworkEngineBlockEntity` | generate FE (player wound) |
| solar | `SolarEngineTileEntity` (+ `SolarPanelBlock`) | generate FE |

SoT: `ModuleEnergy.java`, `EngineBlockType.java`, `forestry/energy/tiles/*`.

Factory does not require engines to function if another mod supplies FE.

## 12. Suggested Fabric port order (simplest first)

1. **Core prerequisites:** `TilePowered` + EnergyStorage adapter + `TankManager`/FluidHelper + `MachineProperties` block groups + circuits sockets (if not done).
2. **Centrifuge** — items only, one recipe type, sockets optional second pass.
3. **Smelter** — items only + sockets (similar to centrifuge).
4. **Still** — pure fluid→fluid, one recipe, dual tanks (teaches fluid FE machines).
5. **Squeezer** — items→fluid + container recipes + sockets.
6. **Bottler** — no JSON recipes; FluidUtil/Transfer API complexity.
7. **Carpenter** — crafting grid + fluid + JEI transfer packet.
8. **Fermenter** — dual tanks + FuelManager fuels + fermentation state.
9. **Fabricator** — heat + smelting recipes + crafting grid + transfer packet.
10. **Moistener** — no FE; light-level logic + FuelManager wheat chain + water.
11. **Rainmaker** — TileMill animation/TESR + rain substrate (no menu).
12. **Hygroregulator recipes** — only if alveary/apiculture needs them (registered on Factory types).

Parallel: extract recipes from generated JSON / datagen builders via `tools/`; port API interfaces under `com.leon1236.reforestry.api.recipes`.
