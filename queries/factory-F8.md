# Stage F8 — Squeezer (+ container recipes)

First item→fluid Factory machine: squeeze items or full cans into product tank, remnant output, no sockets.

## Files created

- `api/recipes/ISqueezerRecipe.java` — mirrors CE `forestry/api/recipes/ISqueezerRecipe.java`; runtime uses `FluidVariant` + droplets via `FluidUnits`.
- `api/recipes/ISqueezerContainerRecipe.java` — extends squeezer API; `getEmptyContainer()` for can/capsule/refractory recovery recipes.
- `core/recipes/RecipeItemAmount.java` — item + count codec for remnant/container JSON fields.
- `core/recipes/RecipeUtils.java` — squeezer ingredient / recipe / container-recipe lookup helpers.
- `factory/recipes/SqueezerRecipe.java` — record + MapCodec/StreamCodec; id **`reforestry:squeezer`**.
- `factory/recipes/SqueezerContainerRecipe.java` — id **`reforestry:squeezer_container`**.
- `factory/tiles/TileSqueezer.java` — `extends TilePowered implements WorldlyContainer`. Single `MultiFluidTank` (`Product`, 10000 mB). Energy 1100 recv / **40000** cap (CE SoT). `ENERGY_PER_RECIPE_TIME=200` × recipe time. 9 resource slots + remnant + can in/out. Container recipes drain filled can/capsule/refractory into product tank (CE match + explicit Fabric drain).
- `factory/gui/ContainerSqueezer.java` — progress/error + product tank sync (amount mB + fluid registry id).
- `factory/client/ScreenSqueezer.java` — programmatic panel + product tank bar + progress meter + error icons.
- `tools/extract_squeezer_recipes.py` — CE extractor (usage in docstring).
- `data/reforestry/recipe/squeezer/*.json` — **26** CE recipes (seeds, cactus, containers, fruit tags, honey/honey_drop, ice, lava, mulch, sponge comb).
- `data/reforestry/recipe/squeezer.json` — shaped craft (tin/glass/sturdy; CE used tin not iron).
- `data/reforestry/loot_table/blocks/squeezer.json` — drop self.
- Placeholder assets: `blockstates/squeezer.json`, `models/block/squeezer.json`, `models/item/squeezer.json`, `items/squeezer.json`.

## Files modified

- `core/inventory/InventoryUtil.java` — `consumeIngredients`, `canConsumeIngredients`, ranged `getStacks`.
- `core/fluids/FluidContainerHelper.java` — `isDrainableFilledContainer`, `drainFromSlotToTank`.
- `factory/blocks/BlockTypeFactoryPlain.java` — `SQUEEZER` PLAIN entry + `TileSqueezer::serverTick`.
- `factory/features/FactoryRecipeTypes.java` — `SQUEEZER`, `SQUEEZER_CONTAINER`.
- `factory/features/FactoryTiles.java` — `SQUEEZER` BE type; `EnergyHelper` + `InventoryHelper` + `FluidStorage.SIDED`.
- `factory/features/FactoryMenuTypes.java` — `SQUEEZER` menu.
- `factory/features/FactoryCreativeTabs.java` — tab lists squeezer.
- `factory/client/FactoryClientHandler.java` — `ScreenSqueezer` registration.

## Smoke recipes

### `reforestry:squeezer` — seeds → seed oil (CE `squeezer/seeds.json`)

| Field | Recipe JSON (mB) | Runtime (droplets) |
|---|---|---|
| Input | `c:seeds` × **1** | 1 item consumed from 3×3 grid |
| Output fluid | `reforestry:seed_oil` × **10 mB** | **810** droplets (`10 × 81`) |
| Time | **10** | 10 work-cycle ticks |
| Total FE | — | **2000 FE** (`200 × 10`) |
| Remnant | none (`chance` 0) | — |

### `reforestry:squeezer_container` — full can recovery (CE `squeezer/container/can.json`)

| Field | Value |
|---|---|
| Input | **Full** `reforestry:can` (1000 mB fluid) in resource grid |
| Fluid | Drained into product tank (not recipe JSON — from container contents) |
| Time | **10** ticks |
| Total FE | **2000 FE** |
| Remnants | `reforestry:ingot_tin` × 1 at **5%** chance |
| After cycle | Empty can remains in resource slot |

Same pattern for `container/capsule.json` (→ `reforestry:beeswax`) and `container/refractory.json` (→ `reforestry:refractory_wax`).

## Playable path (DoD #2)

**Seeds → seed oil**

1. `forceload add <x> <z>`
2. `setblock <x> <y> <z> reforestry:squeezer`
3. Adjacent `reforestry:debug_creative_energy` for FE.
4. `give @p wheat_seeds 64` — place seeds in 3×3 input grid.
5. Wait ~2.5s (10 work cycles × 5-tick interval ≈ 50 game ticks with power).
6. Product tank should show ~10 mB seed oil; seeds consumed.

**Container empty**

1. `give @p reforestry:can` filled with seed oil (creative tab F4c examples).
2. Place **full** can in any resource slot.
3. After 10 cycles, fluid moves to product tank; can becomes empty; ~5% tin remnant in remnant slot.

Live RCON not run this session.

## Verified via MCP (`thedarkcolour-ForestryCE`)

- `forestry/api/recipes/ISqueezerRecipe.java`
- `forestry/api/recipes/ISqueezerContainerRecipe.java`
- `forestry/factory/recipes/SqueezerRecipe.java`
- `forestry/factory/recipes/SqueezerContainerRecipe.java`
- `forestry/factory/tiles/TileSqueezer.java`
- `forestry/factory/inventory/InventorySqueezer.java`
- `forestry/factory/gui/ContainerSqueezer.java`
- `data/forestry/recipes/squeezer/seeds.json`
- `data/forestry/recipes/squeezer/container/can.json`
- `Constants.PROCESSOR_TANK_CAPACITY` = 10000 mB
- `Constants.MACHINE_MAX_ENERGY` = 40000

## DoD status

- `./gradlew compileJava` — **BUILD SUCCESSFUL**.
- Item→fluid + container drain paths documented above.
- `files/implemented-features.md` Phase 6 F8 row updated.
- This file.

## Polish 2026-07-29 (CE parity audit)

Compared recipe set to ForestryCE + ForestryMC. **No CE recipe files missing** after adding `honey_drop.json` (honey 100 mB + 5% `propolis_normal`). ForestryMC-only lava variants (dirt / 2× phosphor) stay dropped like CE.

**JEI:** `FactoryJeiPlugin` collects `ISqueezerRecipe` but skips `ISqueezerContainerRecipe` — container recipes implement the squeezer interface with empty inputs / blank fluid, which showed as tin / beeswax / refractory wax chance-only stubs. CE avoids this by loading `FactoryRecipeTypes.SQUEEZER` only.

**GUI tank color:** `ScreenSqueezer` (and `ScreenCarpenter`) now use `RenderUtil.getFluidColor` instead of hashing the fluid id (milk looked cyan). Lang: `block.reforestry.fluid_milk` = "Milk".

**Local balance tweaks (not CE):** cactus → 5% mulch; `c:seeds` → 1% mulch; coconut → **50 mB** `reforestry:milk` (CE uses 500 mB vanilla milk).

**Still deferred:** CE `tank_product_fill_level` 0–4 blockstate / model variants (cosmetic).

## Blockers for F9 Bottler

- **No JSON recipes** — Bottler uses runtime fill/empty via item fluid handlers (CE `BottlerRecipe` / `FluidUtil`), not `FactoryRecipeTypes`.
- Reuses F4c containers + F7/F8 `FluidContainerHelper` patterns; needs bidirectional item↔tank transfer with viscosity-scaled energy on fill (0 on empty per CE).
- Squeezer product tank + can slots prove container fill path; Bottler adds single resource tank + input/output container slots without a crafting grid.
