# FE1 — Peat engine

**Date:** 2026-08-20  
**Stage:** Wave 2 `FE1`. First playable FE source: `reforestry:peat_engine`.

## FE push

Engines **push** FE the same way `TileCreativeEnergy` does: `SimpleEnergyStorage(maxInsert=0, maxExtract=2000)` + `EnergyStorageUtil.move`. `EnergyHelper.sendEnergy` looks up `EnergyStorage.SIDED.find(level, pos.relative(facing), facing.getOpposite())`. Chunk checks use `Level.isLoaded`, not deprecated `hasChunkAt`.

Neighbor engines refuse insert (`maxInsert=0`). `IEnginePowerHandler.forceReceiveEnergy` adds to `amount` anyway (CE `ForestryEnergyStorage.forceReceiveEnergy`). SIDED is registered only on `EngineBlock.VERTICAL_FACING`.

## HashMap fuel lookup

`FuelManager.peatEngineFuel` is a `HashMap<ItemStack, EngineCopperFuel>`. Never `containsKey(stack)`. `FuelManager.getPeatEngineFuel` iterates and matches with `ItemStack.isSameItem`, like `TileFermenter.findFermenterFuel`.

## Update tag vs PacketActiveUpdate

CE uses `PacketActiveUpdate` + `IStreamableGui` for piston/heat. Re-Forestry uses BE update tags (`saveCustomOnly` + `sendBlockUpdated`) for `Active`, `EngineHeat`, and `PistonSpeed` so the BER can animate. GUI values (output, stored FE, heat, burn time, errors) use `ContainerData` like `TilePowered`.

## Special item model

CE `ItemBlockTesr` is 26.2 `minecraft:special` type `reforestry:engine` (mixin next to escritoire). Texture prefix `engine_copper_`. Idle piston step is CE’s no-level `progress = 0.25`. Block model is particle-only `engine_copper.0`. `RenderShape.MODEL` matches rainmaker/escritoire TESR blocks.

## Other Fabric choices

- `EngineBlock` is not `BlockMachine` (6-way `VERTICAL_FACING`). Wrench `state.rotate(CLOCKWISE_90)` cycles all six faces. CE’s world-aware “snap to energy receiver” rotate is NeoForge-only; placement still prefers a receiver.
- Minecraft 26.2 runs `Block.useItemOn` before `Item.useOn`. `EngineBlock.useItemOn` returns `PASS` for `ItemWrench` so the wrench can rotate UP/DOWN without opening the GUI.
- No `IContainerEnergy` on the peat menu: `GuiPowerLedger` is a consumer UI (usage / max receive). Engines use `GuiEngineLedger` (output, stored, heat °C) like CE `EngineLedger`. RF wording matches existing machine ledgers and `block.reforestry.peat_engine.tooltip`.
- Registry id is CE 1.21.1 `peat_engine` (SUFFIX), not leftover `engine_peat`.
