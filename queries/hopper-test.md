# HOPPER-TEST — Factory hopper sidedness verification

**Date:** 2026-08-22  
**Stage:** Wave 9 Stage 9. Documents CE-aligned hopper rules already in code; no live hopper entity test this pass.

## Source of truth

`queries/factory-play-loop.md` (table) + `queries/factory-F17.md` (F17 defer note).

## Code verification (2026-08-22)

All factory item-slot block entities implement `WorldlyContainer` with face-specific insert/extract rules:

| Machine | Implementation |
|---|---|
| Centrifuge | `factory/tiles/TileCentrifuge.java` — resource slot insert; product slots extract |
| Smelter | `factory/tiles/TileSmelter.java` — input insert; output extract |
| Squeezer | `factory/tiles/TileSqueezer.java` — inputs insert; remnant/can outputs extract |
| Bottler | `factory/tiles/TileBottler.java` — container slots insert; filled outputs extract |
| Carpenter | `factory/tiles/TileCarpenter.java` — can/storage insert; product extract |
| Fermenter | `factory/tiles/TileFermenter.java` — resource/fuel/can insert; can output extract |
| Moistener | `factory/tiles/TileMoistener.java` — resource/stash insert; product extract |
| Fabricator | `factory/tiles/TileFabricator.java` — plan/craft/storage insert; result/smelting extract |

Farming hatch uses the same pattern on the controller inventory via `TileFarmHatch` + `WorldlyContainer` (see `queries/farming-G2c.md`).

## Expected behavior

Matches `factory-play-loop.md`: hoppers insert only through validated faces; extract only from output/product faces. Fluids use `FluidStorage.SIDED`, not hoppers.

## Not tested here

In-world hopper entity smoke (no dedicated test harness). Re-open only on bug report.
