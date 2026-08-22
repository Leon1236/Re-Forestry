# Wave 4 — Farming then cultivation (`reforestry:farming` / `reforestry:cultivation`)

**Status:** **done** (2026-08-21 wrap-up). G0–G4b + CU0–CU2 landed. Next is genetics `GP0a1`, not more farming.  
**Date:** 2026-08-20 (plan); wrap-up 2026-08-21.  
**Source:** `queries/remaining-work-stages.md` Wave 4. CE 1.21.1 `src/farms/java/forestry/agriculture` + `forestry.api.agriculture`.  
**Our packages:** `com.leon1236.reforestry.farming` (multifarm) + `com.leon1236.reforestry.cultivation` (planters) + `com.leon1236.reforestry.farming.farmlogic` (shared).  
**API:** `com.leon1236.reforestry.api.agriculture`. Farm multiblock interfaces under `api.multiblock` next to alveary.  
**Mail** stays out of scope. **Do not commit.**  
**Do not** start genetics `GP0a`, lepidopterology, or sorting `S2`.  
**Do not** port CE `MultiblockPattern` / `FarmPattern` DSL — reuse `RectangularMultiblockControllerBase`.

Wave 3 sorting (S0/S1) is already landed.

## Registry ids (CE 1.21.1 contracts)

| Kind | Id |
|---|---|
| Module farming | `reforestry:farming` |
| Module cultivation | `reforestry:cultivation` |
| Tab | `reforestry:agriculture` (lang key `itemGroup.agriculture`) |
| Farm menu | `reforestry:farm` |
| Planter menu | `reforestry:planter` |
| Tiles | `reforestry:plain` / `gearbox` / `hatch` / `valve` / `control` |
| Socket | `reforestry:farm` |
| Layouts | `reforestry.farms.managed` / `reforestry.farms.manual` |
| Farm types | `reforestry:crops` (and the other 10 `ForestryFarmTypes` paths) |

Farm blocks: `{material}_farm_{part}` with CE exceptions (`chiseled_sandstone`, `nether_brick`, `chiseled_stone_brick`, `chiseled_quartz`, `quartz_pillar`; `STONE_BRICK`+`PLAIN` → `stone_brick_farm_block`).

Planters: `{type}_managed` / `{type}_manual` for `arboretum`, `farm_crops`, `farm_mushroom`, `farm_gourd`, `farm_nether`, `farm_ender`, `peat_bog`.

## Fabric vs CE

| CE | Re-Forestry |
|---|---|
| `api.core.multiblock` farm types | `api.multiblock` (`IFarmController`, `IFarmComponent`, `IMultiblockLogicFarm`) |
| `FarmController` + `FarmPattern` DSL | `FarmController extends RectangularMultiblockControllerBase` |
| NeoForge `FluidStack` on `IFarmHousing` | `FluidVariant` + `long` amount |
| NeoForge energy/item/fluid caps | Team Reborn `EnergyStorage.SIDED` + fabric-transfer |
| `CompoundTag` tile NBT | `ValueInput` / `ValueOutput` |
| `PacketGuiStream` | Menu `ContainerData` |
| `forestry:tin_electron_tube` | `reforestry:electron_tube_tin` |
| `forestry.farms.managed` | `reforestry.farms.managed` |
| `forestry:crops` | `reforestry:crops` |

Farm shape: X,Z in [3,5], Y exactly 4, min 36; interior plain; exterior band dy==2 plain; ≥1 gearbox. Extent `max(NS,EW) * multiFarmSize + 1`, default size 2.

Circuits match CE `AgricultureForestryPlugin` (7 managed + 9 manual). No extra pairs. Empty multifarm sides default to managed arboretum.

## Leftover CE gaps (cannot fix here)

- **CONTROL redstone dust visual:** CE `FarmBlock.canConnectRedstone` is NeoForge-only. Minecraft 26.2 + Fabric API have no hook for dust to connect without `isSignalSource` (that would make the farm *emit* power). Control still reads `Level.getSignal` on UP/DOWN/farm-side and cancels that side. See `queries/farming-G2a.md` / `queries/farming-G2c.md`.
- **`FarmPattern` / `MultiblockPattern`:** not ported (intentional). Same numeric rules via `FarmMultiblockSizeLimits` + rectangular hooks.
- CE `FarmTypeBuilder.build()` ignores leftover `addGermling` / `addProduct`. We wrap those as `FarmableInfo` so cocoa/peat/orchard germling slots and JEI work.

## Stages

G0 API+module → G1 55 blocks → G2a assemble → G2b GUI → G2c IO → G3 wheat → G4a/G4b types → CU0/CU1/CU2 planters. **All done.**
