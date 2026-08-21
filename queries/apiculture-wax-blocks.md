# Apiculture wax blocks (CE 1.20.1 restore)

CE 1.21.1 dropped `wax_block` / `wax_block_refractory`. Restored from CE 1.20.1 ids (`forestry:` → `reforestry:`).

## 26.2 adaptations

- Properties use `BlockBehaviour.Properties.ofFullCopy(Blocks.HONEYCOMB_BLOCK)` (not deprecated `copy`).
- Flammability for `wax_block` is `FlammableBlockRegistry.getDefaultInstance().add(block, 45, 45)`. Refractory is not registered as flammable.
- **Melt skipped:** Minecraft 26.2 `Block` has no `onCaughtFire`. CE 1.20.1 melted the block into `ForestryFluids.WAX` legacy fluid on fire. Fire can still burn the yellow block away via the flammability registry; it does not leave a wax fluid source.
- **Fabricator smelting skipped:** CE 1.20.1 melted `wax_block` → 1000 mB wax at 500. Local fabricator crafts do not consume `reforestry:wax` fluid (only glass smelting is used), so the recipe would produce a fluid with no current product recipes.

## Not ported

CE 1.21 generated waxstone stairs/walls family — out of scope until those blocks are registered.
CE 1.20.1 jumbo-candle recipes that consumed wax blocks — skipped; jumbo candles are not registered.
