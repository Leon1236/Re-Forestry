# Stage F4 — Fluids foundation (core)

Completed after agent hit API limit mid-run; implementation was already on disk and clean-compiles. Docs finished by orchestrator.

## Delivered

- `core.fluids.FluidUnits` — mB ↔ droplets (81×)
- `FilteredFluidStorage` / `MultiFluidTank` — Fabric multi-tank facade
- `ForestryFluids` enum — all 9 CE fluids with CE-matched density/viscosity/color/flammability
- `FeatureFluid` + `BaseFlowingFluid` + `BlockForestryFluid` — source/flowing/block/bucket registration
- Client tint via `FluidClientHandler` (wired from `CoreClientHandler`)
- Buckets in core creative tab
- Design note: `queries/factory-fluids-fabric.md`

## Registry ids

| Fluid | Fluid id | Flowing | Block | Bucket |
|---|---|---|---|---|
| bio_ethanol | `reforestry:bio_ethanol` | `reforestry:bio_ethanol_flowing` | `reforestry:fluid_bio_ethanol` | `reforestry:bucket_bio_ethanol` |
| biomass | `reforestry:biomass` | `…_flowing` | `reforestry:fluid_biomass` | `reforestry:bucket_biomass` |
| glass | `reforestry:glass` | … | `reforestry:fluid_glass` | `reforestry:bucket_glass` |
| honey | `reforestry:honey` | … | `reforestry:fluid_honey` | `reforestry:bucket_honey` |
| ice | `reforestry:ice` | … | `reforestry:fluid_ice` | `reforestry:bucket_ice` |
| juice | `reforestry:juice` | … | `reforestry:fluid_juice` | `reforestry:bucket_juice` |
| seed_oil | `reforestry:seed_oil` | … | `reforestry:fluid_seed_oil` | `reforestry:bucket_seed_oil` |
| short_mead | `reforestry:short_mead` | … | `reforestry:fluid_short_mead` | `reforestry:bucket_short_mead` |
| wax | `reforestry:wax` | … | `reforestry:fluid_wax` | `reforestry:bucket_wax` |

## F7 hookup

Still builds a two-tank `MultiFluidTank` (resource biomass + product bio_ethanol), registers `FluidStorage.SIDED`, converts recipe mB with `FluidUnits.mbToDroplets`.

## Deferred

- `FluidStorage.ITEM` on buckets / cans → F4c / F9 (bottler)
- Recipe-driven tank filters → per-machine stages (keep out of core→factory imports)
- Dual-tank in-world proof → F7 Still

## DoD

- Compile: clean `./gradlew clean compileJava` SUCCESSFUL
- Nine fluids registered; dual-tank pattern documented
