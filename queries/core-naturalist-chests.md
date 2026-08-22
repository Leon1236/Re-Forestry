# Core naturalist chests + desk analyzer

**Date:** 2026-08-17  
**Ids:** CE20 / local assets (`bee_chest`, `tree_chest`, `butterfly_chest`, `analyzer`). CE 1.21.1 renamed chests to `apiarists_chest` / `arborists_chest` / `lepidopterists_chest` — ignored.

## Landed

- Three species-filtered chests (125 slots, 5 pages). Filter is `IndividualItems.getSpeciesTypeId` vs `ForestrySpeciesTypes.BEE` / `TREE` / `BUTTERFLY`.
- Page flip uses vanilla `clickMenuButton` + `ExtendedMenuProvider` extra `BlockPos + page` (same idea as naturalist backpacks). 26.2 `MenuProvider` has no `shouldTriggerClientSideContainerClosingOnOpen`, so flipping a page may grab the mouse briefly. Lid BER landed in CORE-E4 (`queries/core-e4.md`).
- Desk analyzer: `TilePowered` 40000 FE / 800 receive, 125 work ticks, 20320 FE/cycle, 100 mB honey from `ForestryFluids.HONEY` (source + flowing). Marks `CoreDataComponents.ANALYZED`. Honey cans drain via `FluidContainerHelper.drainIntoTank`. GUI texture is CE `alyzer.png` (not `analyzer.png`, which is a different leftover sheet).

## Recipes

- `bee_chest`: glass + `#reforestry:combs` + wooden chest (CE20).
- `tree_chest`: glass + saplings + wooden chest (CE20).
- **Skipped `butterfly_chest` recipe** — CE20 uses `forestry:butterfly_ge`, which is not registered here. Block is still `/give`-able and in the creative tab.
- Analyzer: CE 1.21.1 shaped (`XTX /  Y  / X X`) with `reforestry:portable_alyzer`, `#c:ingots/bronze`, `reforestry:sturdy_machine` (our sturdy casing id; CE 1.21.1 calls it `sturdy_casing`).

## Skipped

- Escritoire block, carpenter recipe, and memory-game GUI (out of time).
- Chest / analyzer BER — done in CORE-E4.
- Butterfly chest crafting recipe (see above).
- Vanilla honey fluid (none on 26.2); tank accepts Forestry honey only.

## Wiring

Registration is on existing `CoreBlocks` / `CoreTiles` / `CoreMenuTypes` / `CoreClientHandler` / `CoreCreativeTabs`. `ModuleCore` init order unchanged.
