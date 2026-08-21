# Wave 2 — Energy (`reforestry:energy`)

**Date:** 2026-08-20  
**Source:** `queries/remaining-work-stages.md` Wave 2. CE 1.21.1 `forestry.core.content.energy` (~22 Java).  
**Our package:** `com.leon1236.reforestry.energy`  
**Do not** port solar / combustion / `engine_electrical` / `engine_generator`.  
**Mail** stays out of scope. **Do not commit.**

## Registry ids (CE 1.21.1 contracts)

`EnergyBlocks` uses `.identifier("engine", IdentifierType.SUFFIX)` + subtype `peat` / `biogas` / `clockwork`:

| Block / menu / tile | Id |
|---|---|
| Peat | `reforestry:peat_engine` |
| Biogas | `reforestry:biogas_engine` |
| Clockwork | `reforestry:clockwork_engine` |

Leftover assets/lang use CE20 names (`engine_peat`, …). **Rename onto `peat_engine` etc.** Do not register the old ids.

## Already present (do not redo)

- Team Reborn `EnergyStorage` + `EnergyHelper.consumeEnergyToDoWork` (machines **receive** FE).
- `TileCreativeEnergy` push pattern (`EnergyStorageUtil.move`) — engines must **push** the same way.
- `api.fuels.FuelManager` (fermenter / moistener / rain only) — **extend**, do not add `api.core.machines.fuels`.
- Peat items: `CoreItems.PEAT` / `BITUMINOUS_PEAT` / `ASH`.
- Fluids: `ForestryFluids` including `MILK`, `BIOMASS`, `SEED_OIL`, `HONEY`, `JUICE`.
- Engine BER textures already under `assets/reforestry/textures/block/engine_{copper,bronze,clock}_*` + trunk + GUI `peatengine.png` / `bioengine.png`.
- Hints: `engine.copper` / `engine.bronze` in `hints.properties`.
- Tags: `c:ingots/copper`, `c:ingots/bronze`, `c:gears/copper`, `c:gears/bronze`, `c:glass_blocks/colorless`.
- Errors: `NO_REDSTONE`, `NO_FUEL`, `NO_HEAT`, `FORCED_COOLDOWN`.
- `IActivatable` (no Forge `PacketActiveUpdate` — use BE update tags like escritoire/alveary).
- F-ADV skipped `recipes/misc/peat_engine.json` etc. — copy in when recipes land.

## Fabric vs CE (must document in `queries/energy-FE0.md` / stage notes)

| CE | Re-Forestry |
|---|---|
| NeoForge `Capabilities.EnergyStorage` + `ForestryEnergyStorage` EXTRACT | Team Reborn `SimpleEnergyStorage(maxInsert=0, maxExtract>0)` + `EnergyStorage.SIDED` + `EnergyStorageUtil.move` |
| Engine chaining `forceReceiveEnergy` | Special-case neighbor `EngineBlockEntity` / our tile base: add to `amount` even if `maxInsert=0` |
| `NeoForgeMod.MILK` | `ForestryFluids.MILK`; also seed vanilla `Fluids.MILK` if 26.2 has it |
| `CompoundTag` | `ValueInput` / `ValueOutput` |
| `PacketActiveUpdate` | Block entity update tag / `sendBlockUpdated` |
| `ItemBlockTesr` | 26.2 special item model (`reforestry:engine`) like escritoire |
| `RenderEngine` `MultiBufferSource` | 26.2 `SubmitNodeCollector` like `RenderEscritoire` |
| Ethanol constants exist | **Not seeded** in CE `setupApi` — do not add ethanol fuel |
| Engines in core creative tab | `CoreCreativeTabs` when `reforestry:energy` loaded |

## Stage FE0 — Module shell + fuels (S)

**Exit:** Module toggles in `config/reforestry/modules.properties`. Fuel maps exist. **No blocks.**

- `EngineBronzeFuel` / `EngineCopperFuel` records in `api.fuels`.
- `FuelManager.biogasEngineFuel` (`Map<Fluid, EngineBronzeFuel>`) and `peatEngineFuel` (`Map<ItemStack, EngineCopperFuel>`).
- `ModuleEnergy` `@ForestryModule`, id `reforestry:energy`, depends `core`. `setupApi()` from `init()` (same as factory).
- Register in `ReForestry.onInitialize()` load list.
- Empty `EnergyClientHandler` + `registerClientHandler`.
- Seed **exactly** CE `ModuleEnergy.setupApi` (values from CE `Constants`):

| Fuel | powerPerCycle | burnDuration | dissipationMultiplier |
|---|---|---|---|
| biomass | 50 | 2500 | 1 |
| water | 10 | 1000 | 3 |
| milk | 10 | 10000 | 3 |
| seed_oil | 30 | 2500 | 1 |
| honey | 20 | 2500 | 1 |
| juice | 10 | 2500 | 1 |
| peat | 20 | 2500 | — |
| bituminous peat | 40 | 3000 | — |

- `queries/energy-FE0.md` (FE mapping + milk choice).
- Update `files/implemented-features.md` + tick remaining-work-stages FE0.

## Stage FE1 — Peat engine (M)

**Exit:** Place `peat_engine`, feed peat, **redstone on**, facing a centrifuge → centrifuge runs **without** `debug_creative_energy`.

- `EngineBlock` (not `BlockMachine`): 6-way `VERTICAL_FACING`, CE voxel shapes, analog redstone from energy fill, place toward energy receiver, wrench-rotate via `Block.rotate`.
- `EngineBlockType` + `EnergyBlocks` group `identifier("engine", SUFFIX)` + TESR item.
- `EngineBlockEntity` base: heat, piston stages, `IActivatable`, **push** FE along facing, require redstone (`NO_REDSTONE`), `EnergyHelper.sendEnergy` / `isEnergyReceiverOrEngine` on existing `core.energy.EnergyHelper`.
- `PeatEngineBlockEntity` + `InventoryEnginePeat` + menu/screen (`peatengine.png`), hint `engine.copper`.
- Capacity 200000, heat max 10000, ash every 7500 burn ticks → `ASH`.
- BER `RenderEngine` + item special renderer; textures `engine_copper_*` + shared trunks. Layer `ENGINE_LAYER`.
- Recipe CE peat_engine.json (copper ingots/gears + glass + piston). Loot self-drop. `#minecraft:mineable/pickaxe`. Lang `block.reforestry.peat_engine` (+ tooltip). Core tab. F-ADV `recipes/misc/peat_engine.json`.
- Migrate leftover `engine_peat` assets to `peat_engine` (or new files; do not leave the live id pointing at the old name).

## Stage FE2 — Biogas + clockwork (M)

**Exit:** `biogas_engine` burns fluid fuel (needs heat / lava tank); `clockwork_engine` right-click winds, **no menu**, outputs FE. Overwind hurts via `reforestry:clockwork` damage type.

- Biogas: tanks fuel/heating/burn (`ENGINE_TANK_CAPACITY` = 10 buckets), `InventoryEngineBiogas` can slot, menu/screen `bioengine.png`, hint `engine.bronze`, capacity 300000, heat max 10000. Fabric fluid tanks (`FilteredFluidStorage` / `MultiFluidTank`). Lava heat via `#minecraft:lava` (or CE lava filter). Recipe bronze ingots/gears + glass + piston.
- Clockwork: no GUI; `openGui` winds tension; always redstone-on; 2 FE/cycle; max energy 10000; damage type datapack from CE `damage_type/clockwork.json`. Recipe planks + glass + copper gear + piston + clock.
- BER: bronze / clock texture prefixes. Item special models. Loot, pickaxe tag, lang, tab, F-ADV for both recipes.
- Do **not** add solar/combustion. Leave leftover `engine_electrical` / `engine_generator` assets unregistered.

## Validation

After each stage: full review (Must / Should / Nice — implement, do not park). After FE2: one more full Wave 2 review + `./gradlew classes`.
