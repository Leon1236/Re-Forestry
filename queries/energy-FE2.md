# FE2 — Biogas + clockwork engines

**Date:** 2026-08-20  
**Stage:** Wave 2 `FE2`. Playable FE sources: `reforestry:biogas_engine`, `reforestry:clockwork_engine`.

## Droplets vs mB

CE tanks store millibuckets. Fabric transfer uses droplets (`FluidUnits`, 81 droplets per mB). Fuel and heating tanks are 10 buckets (`ENGINE_TANK_CAPACITY_MB=10000` → `mbToDroplets`). The burn reservoir still stores remaining burn as fluid amount (CE `setAmount(burnDuration)`), but in droplets. Drain is `mbToDroplets(1)` per tick, not 1 droplet. Loading a bucket copies `mbToDroplets(1000)` from the fuel tank and sets burn capacity to `mbToDroplets(burnDuration)`.

## Clockwork has no menu

CE `createMenu` is null; `EnergyMenus` has no clockwork type. Re-Forestry: `hasGui()=false` and `openGui` winds tension instead of `player.openMenu`. `EngineBlock.useItemOn` already calls `tile.openGui`. Skip non-`ServerPlayer` (client hand). No NeoForge `FakePlayer` check.

## Damage type

Datapack `data/reforestry/damage_type/clockwork.json` copies CE (`exhaustion` 0, `scaling` when caused by living non-player). `message_id` is `reforestry.clockwork` (hive pattern). Overwind uses `hurtServer` 6 with `CoreDamageTypes.CLOCKWORK`. Death keys `death.attack.reforestry.clockwork` already existed in en_us; other locales copied from leftover `death.engine.clockwork` / CE.

## Lava heating tank

CE `FluidTagFilter.LAVA`. Heating-tank insert uses `#minecraft:lava` (`FluidTags.LAVA`). Warmup still requires `Fluids.LAVA` like CE `getFluidType() == Fluids.LAVA`. Insert lava, no extract. Warmup drains `mbToDroplets(1)` and adds `ENGINE_HEAT_VALUE_LAVA` (20) while `shutdown` and heat stage ≤ 0.25.

## Pipette not on burn reservoir

CE `BiogasSlot.handleMouseClick` is empty. Fuel (89,19) and heating (107,19) use `ScreenForestry.addTankClickRegion` (16×58). Burn at 30,47 is 16×16 draw + name tooltip only — no click region, `getTank(2)` is null so pipette cannot target it.

## In-world buckets

CE `BlockBase.useItemOn` fills tanks with `FluidUtil.interactWithFluidHandler` before opening the GUI (shift skips that). Re-Forestry `EngineBlock` does the same via `FluidHelper.interactWithTank` after the wrench `PASS`. Clockwork has no fluid SIDED, so a bucket still winds.

## Other Fabric choices

- Tanks: `MultiFluidTank` + `FilteredFluidStorage` (fuel insert-filter from `FuelManager.biogasEngineFuel.containsKey`; heating `#minecraft:lava` / no extract; burn no insert / no extract + mutable capacity). `FluidHelper.registerSided` on the full tank manager (CE ignores facing). Energy SIDED still facing-only like peat.
- Can slot uses `FluidContainerHelper.drainIntoTank` into the tank manager (CE `drainContainers(tankManager)`).
- Registry ids are CE 1.21.1 `biogas_engine` / `clockwork_engine` (SUFFIX), not leftover `engine_biogas` / `engine_clockwork`.
- Item special type `reforestry:engine` with prefixes `engine_bronze_` / `engine_clock_`.
