# Stage F7 — Still (dual tanks + droplets)

First fluid→fluid Factory machine: biomass → bio_ethanol with dual tanks, energy, container slots, no sockets.

## Files created

- `api/recipes/IStillRecipe.java` — mirrors CE `forestry/api/recipes/IStillRecipe.java`; uses `FluidVariant` + droplet amounts at runtime.
- `core/recipes/RecipeFluidAmount.java` — recipe JSON fluid + mB codec; `amountDroplets()` via `FluidUnits`.
- `core/fluids/FluidContainerHelper.java` — Fabric transfer drain/fill between `Container` slots and block tanks (forestry cans + vanilla buckets).
- `factory/recipes/StillRecipe.java` — record + MapCodec/StreamCodec; id **`reforestry:still`**.
- `factory/tiles/TileStill.java` — `extends TilePowered implements WorldlyContainer, IRenderableTile`. Dual `MultiFluidTank` (resource/product, 10000 mB each). Energy 1100 recv / **80000** cap (CE SoT). `ENERGY_PER_RECIPE_TIME=200` × `time`. Buffered input fluid during work. Container slots: product (filled cans), resource (empty cans), can (filled cans in).
- `factory/gui/ContainerStill.java` — tank sync via `SimpleContainerData` (amount mB + fluid type per tank) + progress/error data.
- `factory/client/ScreenStill.java` — CE GUI texture + two tank widgets + progress drip + error tabs.
- `tools/extract_still_recipes.py` — CE extractor (usage in docstring).
- `data/reforestry/recipe/still/ethanol.json` — CE ethanol recipe (only still recipe in CE datagen).
- `data/reforestry/recipe/still.json` — shaped craft (iron/sturdy/glass; CE used `gear_iron`, not ported).
- `data/reforestry/loot_table/blocks/still.json` — drop self.
- Assets: facing blockstate, models, textures (including `textures/gui/still.png`).

## Files modified

- `factory/blocks/BlockTypeFactoryPlain.java` — `STILL` PLAIN entry + `TileStill::serverTick`.
- `factory/features/FactoryRecipeTypes.java` — `STILL`.
- `factory/features/FactoryTiles.java` — `STILL` BE type; `EnergyHelper` + `InventoryHelper` + `FluidStorage.SIDED` on still.
- `factory/features/FactoryMenuTypes.java` — `STILL` menu.
- `factory/features/FactoryCreativeTabs.java` — tab lists still.
- `factory/client/FactoryClientHandler.java` — `ScreenStill` registration.

## Smoke recipe (CE ethanol)

| Field | Recipe JSON (mB) | Runtime (droplets) |
|---|---|---|
| Input fluid | `reforestry:biomass` × **10 mB** | **810** droplets (`10 × 81`) |
| Output fluid | `reforestry:bio_ethanol` × **3 mB** | **243** droplets (`3 × 81`) |
| Duration (`time` / `getCyclesPerUnit`) | **100** | 100 work-cycle ticks |
| Total FE per work cycle | — | **20000 FE** (`200 × 100`) |

**Conversion (current Re-Forestry):** one work cycle drains **10 mB** biomass and produces **3 mB** bio_ethanol — same numbers JEI shows.

### CE vs Re-Forestry (intentional)

CE `TileStill` batches `time` recipe units in one work cycle: drain `10×100 = 1000 mB`, produce `3×100 = 300 mB`, same FE/`time` cost. JEI still shows the single-unit recipe (10→3). That mismatch confused playtesting, so Re-Forestry processes **one recipe unit** per work cycle so the machine matches JEI. Same **ratio**; different absolute batch size and therefore **FE per mB** (we still pay `200×time` FE for one unit — CE pays that for `time` units). Revisit energy if CE throughput parity is needed later.

## Polish / validation (2026-07-29)

Validated against CE (code, recipe, JEI, assets, lang). Fixes applied:

| Fix | Change |
|---|---|
| JEI ↔ machine amounts | Stopped multiplying drain/output by `getCyclesPerUnit()`; `time` only sets ticks + energy |
| `hasWork` resource check | Require full `drainAmount` (= one `inputAmount`), not a partial tank |
| Tank filters | `FilteredFluidStorage.any()` on both tanks (recipe lookup gates work); was hardcoded Biomass/BioEthanol |
| Blockstate tank levels | **Not ported** — CE updates `TANK_RESOURCE_LEVEL` / `TANK_PRODUCT_LEVEL`; we use `IRenderableTile` TESR instead |

Assets/lang/JEI category: complete vs CE (one ethanol recipe; facing blockstate is an intentional improvement).

## Playable path — smoke test

1. `forceload add <x> <z>`
2. `setblock <x> <y> <z> reforestry:still`
3. Adjacent `reforestry:debug_creative_energy` for FE.
4. Inject biomass into resource tank (enough for several units), e.g. 100 mB = 8100 droplets:

   ```mcfunction
   data merge block <x> <y> <z> {Tanks:{Resource:{variant:{fluidName:"reforestry:biomass"},amount:8100L}}}
   ```

5. Wait for one work cycle (~100 ticks of powered work).
6. Product tank should gain **243** droplets (3 mB ethanol); energy drops by ~20000 FE per completed cycle.

Alternative: filled biomass can in CAN slot; empty can in RESOURCE for auto-fill of product.

## Verified via MCP (`thedarkcolour-ForestryCE`)

- `forestry/api/recipes/IStillRecipe.java`
- `forestry/factory/recipes/StillRecipe.java`
- `forestry/factory/tiles/TileStill.java`
- `forestry/factory/gui/ContainerStill.java`
- `forestry/factory/inventory/InventoryStill.java`
- `data/forestry/recipes/still/ethanol.json`
- `Constants.PROCESSOR_TANK_CAPACITY` = 10000 mB

## DoD status

- Biomass → bio_ethanol path playable; droplet conversion via `FluidUnits.mbToDroplets`.
- Machine amounts match JEI (10 mB → 3 mB).
- `files/implemented-features.md` Phase 6 F7 row updated.
- This file.

## Known follow-ups

- Energy cost still uses CE’s `200 × time` formula while processing only one recipe unit — revisit if CE FE/mB parity is desired.
- GUI tanks still use hardcoded Biomass/BioEthanol colours (fine while only one recipe exists).
