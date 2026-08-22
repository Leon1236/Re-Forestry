# G2a — Farm multiblock assemble (no GUI)

**Date:** 2026-08-20  
**Stage:** Wave 4 `G2a`. A valid farm forms. Invalid shapes show CE error strings on right-click. No GUI, inventory, hatch/valve/energy, or crop logic.

## Rectangular, not FarmPattern

CE 1.21.1 farm assembly uses `FarmPattern` / `MultiblockPattern` DSL. Re-Forestry does **not** port that. G2a uses the alveary gold path: `FarmController extends RectangularMultiblockControllerBase`.

Shape matches the CE pattern rules, expressed as size limits + `isGoodForInterior` / `isGoodForExteriorLevel` / `isMachineWhole`:

- X,Z in [3, 5], Y exactly 4, min 36 blocks (`FarmMultiblockSizeLimits`)
- Interior must be `TileFarmPlain` (`for.multiblock.farm.error.needPlainInterior`)
- Exterior band `dy == 2` must be `TileFarmPlain` (`for.multiblock.farm.error.needPlainBand`)
- Other exterior cells may be any farm part
- After the cube check, ≥1 gearbox (`TileFarmGearbox` or `EnumFarmBlockType.GEARBOX`; `for.multiblock.farm.error.needGearbox`)

`getUnlocalizedType` is `for.multiblock.farm.type`. Unassembled right-click uses `BlockStructure` overlay (same as alveary). Assembled right-click does not open a menu (`TileFarm` is not `MenuProvider`).

## BAND

CE `TileFarmPlain.onMachineAssembled`: `bandY = maxCoord.getY() - 1` (for height 4 that is exterior `dy == 2`), `FarmBlock.BAND = true` with `Block.UPDATE_CLIENTS`. `onMachineBroken` sets `BAND = false`. No second band rule.

CONTROL still does not fake redstone emit (26.2 has no `canConnectRedstone` equivalent without becoming a signal source).

## Stub housing

`FarmController` implements `IFarmHousing` enough to compile: `doWork()` false, `FakeFarmInventory`, `hasLiquid` false, `getFarmLogic` never null (`FakeFarmLogic` if arboreal is not built yet). `IExtentCache` no-ops (return 0). `IErrorLogic` comes from `MultiblockControllerForestry` like alveary. `isSquare()` reads `farms.square_multiblock_farms`. `isValidPlatform` uses `ForestryTags.Blocks.VALID_FARM_BASE` (`reforestry:valid_farm_base`).

`allowedExtent` is computed on assemble (`max(NS, EW) * farms.multiblock_farm_size + 1`, default size 2, range 1–10) for G3 farmland. G2a does not cultivate.

Gearbox/hatch/valve/control tiles are thin subclasses. G2a does **not** register `EnergyStorage` / `ItemStorage` / `FluidStorage`.

Tiles: `reforestry:plain` / `gearbox` / `hatch` / `valve` / `control` via `FarmingBlocks.FARM.getRowBlocks`. Fake controller is `FakeFarmController.INSTANCE` when disconnected (`MultiblockLogicFarm`, alveary pattern).

## No GUI

No `ContainerFarm`, `ScreenFarm`, menus, farm circuits, or crop logics.

G1 worlds may have farm blocks with no block entity. `FarmBlock` creates the matching tile on right-click or neighbor update so those leftovers can assemble without replacing every block.

Skipped here (later stages / missing 26.2 API): G2b GUI/inventory, G2c hatch/valve/`EnergyStorage` IO, CONTROL redstone wire visual (`canConnectRedstone` has no 26.2 equivalent).

## D12 smoke (listed)

- `/give @p reforestry:stone_brick_farm_block` × enough plains + one `stone_brick_farm_gearbox`. Build 3×4×3 (all plains except one gearbox on a non-band exterior). After a moment the third layer (maxY−1) plains show the band texture. Right-click does **not** open a GUI.
- Same size with **no** gearbox: right-click overlay `for.multiblock.farm.error.needGearbox`.
- Gearbox on the third layer (dy==2): overlay `for.multiblock.farm.error.needPlainBand`.
- Non-plain in the hollow interior: overlay `for.multiblock.farm.error.needPlainInterior`.
- 3×3×3 or 6×4×3: too small / too large (existing `for.multiblock.error.small*` / `large*`).
- Break a block: band turns off; structure unassembles.

Next: **G2b** GUI/inventory.
