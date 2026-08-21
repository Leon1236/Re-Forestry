# Stage F5 — Centrifuge vertical slice

First playable Factory machine: block + energy + recipe + probabilistic output, no sockets, no fluids.

## Files created

- `api/recipes/ICentrifugeRecipe.java` — mirrors CE `forestry/api/recipes/ICentrifugeRecipe.java` (`getInput()`, `getProcessingTime()`, `getProducts(RandomSource, double outputMult)`, `getAllProducts()`), adapted to MC 26.2 types (`RandomSource`, `Ingredient`, no Forge).
- `factory/recipes/CentrifugeRecipe.java` — record implementing `ICentrifugeRecipe`. `MapCodec`/`StreamCodec` pair (`input: Ingredient`, `time: int` optional default 20, `products: List<Product>`), `RecipeSerializer<CentrifugeRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC)`. Reuses the existing `api/core/Product` type (item + count + components + chance, already built in an earlier stage) — no new product type invented.
- `factory/features/FactoryRecipeTypes.java` — `REGISTRY.recipeType("centrifuge", () -> CentrifugeRecipe.SERIALIZER)`, id **`reforestry:centrifuge`**. Declared as `FeatureRecipeType<CentrifugeRecipe>` (concrete class, not the `ICentrifugeRecipe` interface — `Recipe.getType()`'s return type only unifies against the concrete recipe class).
- `factory/tiles/TileCentrifuge.java` — `extends TilePowered implements Container, WorldlyContainer`. No `ISocketable`/circuit inventory (F15 later). Slots: `0` = resource, `1-9` = 3×3 product grid. `hasWork()`/`workCycle()` drive a pending-products queue (`ArrayDeque<ItemStack>`) so a multi-product recipe drains one item into the grid per work cycle instead of forcing all products into slot 1. Recipe lookup is server-only (`((ServerLevel) level).recipeAccess().getRecipes()`, filtered to `instanceof ICentrifugeRecipe`) since MC 26.2 doesn't sync the full recipe list to clients — `canPlaceItem`/`getSlotsForFace` stay permissive and let the server reject invalid inserts on tick.
- `factory/gui/ContainerCentrifuge.java` — `extends ContainerMachine<TileCentrifuge>`, one resource slot + 3×3 product grid, `addDataSlots` for progress (`ContainerData`, 1 int, 0-100 scaled) and error (`ContainerData`, error count + up to 4 numeric error ids, reusing `IErrorLogic`/`ForestryError` from Core).
- `factory/client/ScreenCentrifuge.java` — `extends AbstractContainerScreen<ContainerCentrifuge>`. No dedicated GUI texture exists yet for the centrifuge, so this stage draws a plain grey panel + progress meter + error icons with `guiGraphics.fill`/`blit` instead of inventing a texture path. Full art is F17 scope.
- `tools/extract_centrifuge_recipes.py` — one-off extractor, usage in its own docstring (`python3 tools/extract_centrifuge_recipes.py --root . --apply`). Reads CE's generated `data/forestry/recipes/centrifuge/*.json`, renames `forestry:` → `reforestry:`, converts CE's `{"item": "..."}` ingredient objects to plain strings for `Ingredient.CODEC`, and **skips** (reports, never guesses) recipes carrying NBT (`amber_drone.json`, `amber_sapling.json`) or referencing an item not yet registered in this port (`silky_propolis.json` → `silk_wisp`).
- `src/main/resources/data/reforestry/recipe/centrifuge/*.json` — 17 extracted recipes (of 20 CE source files; 3 skipped as above). One example, `honey_comb.json`:

  ```json
  {
    "type": "reforestry:centrifuge",
    "input": "reforestry:bee_comb_honey",
    "time": 20,
    "products": [
      { "item": "reforestry:beeswax", "chance": 1.0 },
      { "item": "reforestry:honey_drop", "chance": 0.9 }
    ]
  }
  ```

- `src/main/resources/data/reforestry/recipe/centrifuge.json` — shaped machine-craft recipe, CE's glass/copper/`sturdy_machine` pattern ported to Fabric convention tags (`#c:glass_blocks/colorless`, `#c:ingots/copper`) instead of Forge tags.
- `src/main/resources/data/reforestry/loot_table/blocks/centrifuge.json` — standard "drop self" loot table.
- Placeholder assets: `assets/reforestry/blockstates/centrifuge.json`, `models/block/centrifuge.json` (`cube_all` on the existing `reforestry:block/centrifuge.0` texture), `models/item/centrifuge.json`, `items/centrifuge.json` (MC 26.2's item-model-definition format). Full centrifuge art is F17 scope.

## Files modified

- `factory/blocks/BlockTypeFactoryPlain.java` — added `CENTRIFUGE("centrifuge", new MachineProperties.Builder<>(FactoryTiles.CENTRIFUGE, "centrifuge").setServerTicker(TileCentrifuge::serverTick).create())`, following the F3a `BlockTypeFactoryPlain`/`FactoryBlocks.PLAIN` group pattern.
- `factory/features/FactoryTiles.java` — added `CENTRIFUGE` `FeatureBlockEntityType`, `init()` calls `EnergyHelper.registerSided(CENTRIFUGE.type())` (same one-liner F3b already used for the debug fixture).
- `factory/features/FactoryMenuTypes.java` — added `CENTRIFUGE` `FeatureMenuType<ContainerCentrifuge, BlockPos>`.
- `factory/client/FactoryClientHandler.java` — `MenuScreens.register(FactoryMenuTypes.CENTRIFUGE.type(), ScreenCentrifuge::new)`.
- `factory/features/FactoryCreativeTabs.java` — **new file**, first Factory creative tab (`reforestry:factory`), icon + display item = the centrifuge block. CE ships a "Machines" creative tab for this module; no existing tab in this port fit, so a new one was added rather than overloading Core/Apiculture's.
- `factory/ModuleFactory.java` — `init()` now calls `FactoryRecipeTypes.init()` and `FactoryCreativeTabs.init()` in addition to the existing block/tile/menu init calls.
- `assets/reforestry/lang/en_us.json` — added `itemGroup.factory` and `block.reforestry.centrifuge`.

## Bug found and fixed (blocks *all* `TilePowered` machines, not just the centrifuge)

`core/energy/EnergyHelper.consumeEnergyToDoWork` (written in F3b) called `energyStorage.extract(...)` inside a `Transaction` to drain the tile's own buffer for its work cycle. But every `TilePowered` constructs its `SimpleEnergyStorage` with **`maxExtract = 0`** (intentionally receive-only from the *external* Transfer API's point of view, matching CE's `EnergyTransferMode.RECEIVE`). Team Reborn's `SimpleEnergyStorage.extract()` is hard-capped by that same `maxExtract` field — so the tile's *internal* work-cycle consumption was being silently capped at 0 too, and `workCounter` never advanced no matter how much energy the tile held. This went uncaught in F3b because that stage's DoD explicitly wasn't run live (`TileDebugPowered`'s "drains its buffer" claim was never actually observed in-game).

Fix — `consumeEnergyToDoWork` now takes the concrete `SimpleEnergyStorage` and decrements its public `amount` field directly (bypassing the Transfer API's insert/extract transaction, which exists for *external* neighbors, not the tile's own accounting):

```java
public static boolean consumeEnergyToDoWork(SimpleEnergyStorage energyStorage, int ticksPerWorkCycle, int energyPerWorkCycle) {
    if (energyPerWorkCycle == 0) return true;
    long energyPerCycle = (long) Math.ceil(energyPerWorkCycle / (double) ticksPerWorkCycle);
    if (energyStorage.amount < energyPerCycle) return false;
    energyStorage.amount -= energyPerCycle;
    return true;
}
```

`TilePowered.doWork()` now also calls `setChanged()` right after a successful consumption (previously only `onFinalCommit` from an external `insert`/`extract` transaction did that). External neighbors still can't drain a machine (`maxExtract` stays `0` on every `TilePowered`'s `EnergyStorage.SIDED` exposure) — only the tile's own `doWork()` loop can spend its buffer now.

## Energy model (unchanged from F3b spec, now actually verified live)

- Capacity **40000** FE, max receive **800** FE/tick (CE SoT `TileCentrifuge`).
- `ENERGY_PER_WORK_CYCLE = 3200` (CE constant) spread over the recipe's own `time` (ticks): `ENERGY_PER_RECIPE_TIME = 3200 / 20 = 160` FE per tick of processing, so a 20-tick recipe costs the full 3200 FE, a 40-tick recipe costs 6400 FE over double the time, etc. — matches CE's "scaled by recipe time" note.
- `TilePowered.WORK_TICK_INTERVAL = 5` means the tile only advances its work counter once every 5 game ticks, so a 20-tick recipe takes ~5 real seconds of continuous power before the first product batch is queued, then another ~5s per additional product drained from the pending queue into the 3×3 grid.

## Verified via MCP (`thedarkcolour-ForestryCE`)

- `forestry/api/recipes/ICentrifugeRecipe.java`, `forestry/factory/recipes/CentrifugeRecipe.java`, `forestry/factory/tiles/TileCentrifuge.java`, `forestry/factory/gui/ContainerCentrifuge.java`.
- Generated recipes: CE `src/generated/resources/data/forestry/recipes/centrifuge/*.json` (20 files; 17 extracted, 3 skipped — see extractor).

## Playable path (DoD #2) — verified live via RCON on a running `./gradlew runServer`

1. `forceload add <x> <z>` (needed off the default spawn chunks in this dev world).
2. `setblock <x> <y> <z> reforestry:centrifuge`
3. `setblock <x> <y+? or adjacent> reforestry:debug_creative_energy` — pushes FE into the centrifuge every tick up to its 800 FE/tick cap until full (40000).
4. `data merge block <x> <y> <z> {Items:[{Slot:0b,id:"reforestry:bee_comb_honey",count:1}]}` — inserts a known-recipe resource item directly into slot 0 (no player was online in this session, so items were injected via block-entity NBT instead of a GUI; the GUI slot behaves identically).
5. Wait ~10-15 real seconds (recipe time 20 ticks × `WORK_TICK_INTERVAL` 5, times up to 3 work cycles: consume input, then drain each pending product into the grid).
6. `data get block <x> <y> <z>` confirms the resource is gone and the grid holds the recipe's products.

**Observed smoke-test result** (fresh block, fresh energy, clean run):

- Before: `Items: [{count: 1, Slot: 0b, id: "reforestry:bee_comb_honey"}]`, `Energy: 0L`
- After ~12s: `Energy: 40000L`, `Items: [{count: 1, Slot: 1b, id: "reforestry:beeswax"}, {count: 1, Slot: 2b, id: "reforestry:honey_drop"}]`

This matches `honey_comb.json`'s recipe exactly: `beeswax` at `chance: 1.0` (always present) and `honey_drop` at `chance: 0.9` (present this run; a re-roll can legitimately omit it, which is the intended probabilistic behavior — `beeswax` can never be missing since its chance is 1.0).

## What was slimmed vs CE (and why)

- **No `ISocketable`/circuit inventory.** Explicitly out of scope (F15). `TileCentrifuge` has zero socket slots, zero circuit multiplier hooks — the recipe's stated `time`/energy numbers are used as-is.
- **No fluids.** Out of scope (F4). The centrifuge only ever handles item stacks.
- **No client-side recipe cache/lookup.** MC 26.2 doesn't sync the full recipe list to clients by default; `findRecipe` is server-only. `canPlaceItem`/`getSlotsForFace` are permissive so a client-side insert attempt isn't blocked incorrectly — the server is the source of truth and will simply not process an unrecognized item (`NO_RECIPE` error).
- **No dedicated GUI texture.** `ScreenCentrifuge` draws a generic panel programmatically; a real `centrifuge.png` + energy bar/product-slot art is F17 scope.

## Blockers for F4 (fluids)

None from this stage. `TileCentrifuge` has no tank/fluid handling to build on top of or migrate — F4 can add fluid I/O to a future Factory machine (e.g. Squeezer/Fermenter) independently. The one thing F4 (or any future `TilePowered` machine) should be aware of: the `EnergyHelper.consumeEnergyToDoWork` fix above changes that method's parameter type from `EnergyStorage` to the concrete `SimpleEnergyStorage` — any new machine just extends `TilePowered` as before and gets the fix for free, no per-machine change needed.
