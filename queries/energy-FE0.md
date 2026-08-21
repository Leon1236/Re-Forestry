# FE0 — Energy module shell + fuels

**Date:** 2026-08-20  
**Stage:** Wave 2 `FE0`. No engine blocks.

## API package

CE 1.21.1 keeps fuels under `forestry.api.core.machines.fuels`. Re-Forestry already has fermenter / moistener / rain on `com.leon1236.reforestry.api.fuels`. **Extend that package** (`EngineBronzeFuel`, `EngineCopperFuel`, `FuelManager.biogasEngineFuel` / `peatEngineFuel`). Do not add `api.core.machines.fuels`.

Maps are `HashMap` like `ModuleFactory.setupApi`, not CE `FluidMap` / `ItemStackMap`. Factory tiles already iterate entries and match stacks; FE1 engines should do the same.

## Team Reborn push vs Forge caps

CE engines expose NeoForge `Capabilities.EnergyStorage` EXTRACT (`ForestryEnergyStorage`). Machines **receive**. Engines **push**.

Re-Forestry machines already receive via Team Reborn `EnergyStorage` (`EnergyHelper.consumeEnergyToDoWork`). Engines will **push** in **FE1** the same way `TileCreativeEnergy` does: `SimpleEnergyStorage(maxInsert=0, maxExtract>0)` + `EnergyStorage.SIDED` + `EnergyStorageUtil.move`. Neighbor engines that refuse insert (`maxInsert=0`) need a special-case fill of `amount` (CE `forceReceiveEnergy`). Do not add `EnergyHelper.sendEnergy` in FE0.

## Milk

CE `setupApi` seeds `NeoForgeMod.MILK` (NeoForge’s vanilla milk fluid). Minecraft 26.2 `Fluids` has only `EMPTY` / water / lava — **no `Fluids.MILK`**. Vanilla milk is still `Items.MILK_BUCKET` only (`MarkDown_Maker/tmp_minecraft_26_2_src/.../Fluids.java`).

Seeded **`ForestryFluids.MILK.getFluid()`** (`reforestry:milk`) so our milk buckets work. Did not seed a vanilla milk fluid because it does not exist. If 26.2 later adds `Fluids.MILK` and it is a different `Fluid` than `reforestry:milk`, seed both.

## Ethanol skipped

CE `Constants` has `ENGINE_CYCLE_DURATION_ETHANOL = 15000` (no `ENGINE_FUEL_VALUE_ETHANOL`). That duration lives on `EnergyConstants` for CE parity. `ModuleEnergy.setupApi` does **not** put ethanol / `BIO_ETHANOL` into `biogasEngineFuel`. FE0 copies that: no ethanol fuel. No combustion fuels.

Remaining CE `Constants` energy fields (`ENGINE_TANK_CAPACITY`, `ENGINE_HEAT_VALUE_LAVA`, `ENGINE_PISTON_SPEED_MAX`, `ENGINE_COPPER_HEAT_MAX`, `ENGINE_COPPER_ASH_FOR_ITEM`) wait for FE1/FE2 — they have no fuel-map consumer, and tank capacity must use our mB/`FluidUnits` choice when biogas tanks land.
