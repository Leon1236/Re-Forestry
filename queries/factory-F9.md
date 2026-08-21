# Stage F9 — Bottler (runtime fill/empty, no RecipeType)

Fill or drain fluid containers against a single resource tank; no datapack process recipes.

## Files created

- `factory/recipes/BottlerRecipe.java` — runtime helper mirroring CE `BottlerRecipe`; uses `FluidStorage.ITEM` via `ContainerItemContext.withConstant`. Emptying ctor order matches CE: `(empty, fluid, filled, false)` so `matchFilled` compares the filled stack.
- `factory/tiles/TileBottler.java` — `extends TilePowered implements WorldlyContainer`. Single `MultiFluidTank` (`Resource`, 10000 mB). Energy 1100 recv / **40000** cap (CE SoT). `TICKS_PER_RECIPE_TIME=5`, `ENERGY_PER_RECIPE_TIME=1000` × viscosity-scaled fill time. Six slots (CE layout). Auto fluid dump to adjacent tanks (50 mB/tick, no FE). Client syncs `syncedFillRecipe` via `ContainerData` (fill vs empty progress arrow).
- `factory/gui/ContainerBottler.java` — progress + fill/empty direction + resource **fluid registry id** tank sync + error data + **`IContainerEnergy`** (power ledger).
- `factory/client/ScreenBottler.java` — bottler GUI texture + center tank (`RenderUtil` / `FluidVariantAttributes`) + directional progress + error icons + **`setHintKey("bottler")`**.
- `factory/compat/jei/BottlerRecipeCategory.java` / `BottlerRecipeMaker.java` — JEI fill/empty recipes (ForestryMC layout; CE had removed JEI recipes — restored on request).
- `data/reforestry/recipe/bottler.json` — shaped craft (can/glass/sturdy; CE used tin can).
- `data/reforestry/loot_table/blocks/bottler.json` — drop self.
- Placeholder assets already present: `blockstates/bottler.json`, `models/block/bottler.json`, `models/item/bottler.json`; added `items/bottler.json`.

## Files modified

- `core/fluids/FluidContainerHelper.java` — `FillStatus`, `fillContainers`, `drainContainers`, `isFillableContainerWithRoom`, `canAcceptFluid` (neighbor dump probe).
- `factory/blocks/BlockTypeFactoryPlain.java` — `BOTTLER` entry + `TileBottler::serverTick`.
- `factory/features/FactoryTiles.java` — `BOTTLER` BE type; `EnergyHelper` + `InventoryHelper` + `FluidStorage.SIDED`.
- `factory/features/FactoryMenuTypes.java` — `BOTTLER` menu.
- `factory/features/FactoryCreativeTabs.java` — tab lists bottler.
- `factory/client/FactoryClientHandler.java` — `ScreenBottler` registration.
- `data/reforestry/hints.properties` — `bottler=pipette;`
- `assets/reforestry/lang/*` — modern `block.reforestry.bottler` + patchouli + JEI keys (all shipped locales).
- `core/compat/jei/ReforestryJeiRecipeTypes.java` / `factory/compat/jei/FactoryJeiPlugin.java` — Bottler JEI type, category, catalyst, click areas.

## Energy rules (CE mirror)

| Operation | FE per work step | Work steps | Notes |
|---|---|---|---|
| **Fill** container from tank | `round(fillTime × 1000)` | `round(fillTime × 5)` | `fillTime = (fluidDroplets / 81000) × viscosityMultiplier` |
| **Empty** container into tank | **0** | same tick formula | Viscosity scales time only |
| **Auto dump** to neighbor | **0** | every 20 ticks | Up to 50 mB (4050 droplets) per move |

`viscosityMultiplier = ((viscosity / 1000) − 1) / 20 + 1` via `FluidVariantAttributes.getViscosity`.

### Examples (full 1000 mB container)

| Fluid | Viscosity | fillTime | Work steps | FE / step | Total fill FE |
|---|---|---|---|---|---|
| Water | 1000 | 1.0 | 5 | 1000 | **5000** |
| Biomass | 6560 | 1.278 | 6 | 1278 | **7668** |
| Seed oil | 5000 | 1.2 | 6 | 1200 | **7200** |

Emptying any of the above: **0 FE** (same work-step count).

## Smoke paths (DoD #2)

### Empty filled can → tank

1. `forceload add <x> <z>`
2. `setblock <x> <y> <z> reforestry:bottler`
3. Adjacent `reforestry:debug_creative_energy` (only needed for fill path).
4. `give @p reforestry:can` with biomass (creative tab F4c filled examples).
5. Place **full can** in left input (slot 0); moves to emptying processing (slot 2) within ~1s.
6. Wait ~6 work steps (viscosity-scaled; biomass ≈ 30 game ticks with power not required for empty).
7. Empty can in left output (slot 4); resource tank gains **81000** droplets (1000 mB biomass).
8. GUI: **left** progress arrow animates; pipette hint ledger present.

### Fill empty can ← tank

1. Pre-fill tank:

   ```mcfunction
   data merge block <x> <y> <z> {Tanks:{Resource:{variant:{fluidName:"reforestry:biomass"},amount:81000L}}}
   ```

2. Place **empty** `reforestry:can` in right input (slot 1) → filling processing (slot 3).
3. With adjacent FE source, wait ~6 work steps.
4. Full can in right output (slot 5); tank drained; ~**7668 FE** consumed.
5. GUI: **right** progress arrow (tank → container); power ledger shows FE.

**Water bucket** smoke: same layout with `minecraft:water_bucket` / empty bucket; water accepts all containers.

### JEI

1. Look up Bottler / empty can / biomass → Bottler category lists fill and empty recipes.
2. In Bottler GUI, click left or right progress arrow → opens JEI Bottler recipes.

## Follow-up polish (2026-07-29)

Parity audit vs ForestryCE + ForestryMC:

| Item | Status |
|---|---|
| Emptying `BottlerRecipe` ctor / `matchFilled` | Fixed (was swapped → recipe rebuild every check) |
| Empty stack via transfer drain | Fixed (CE FluidUtil equivalent) |
| Power ledger (`IContainerEnergy`) | Wired |
| Hint ledger (`bottler=pipette`) | Wired in `data/reforestry/hints.properties` + `ScreenBottler` |
| Tank tooltip / color | Fluid registry id sync (Carpenter pattern) |
| Fill arrow client sync | `syncedFillRecipe` ContainerData (was always left/empty arrow) |
| Modern lang keys | All locales |
| JEI recipe category | Restored (CE removed; ForestryMC had it; user requested) |
| Recipe unlock advancement / pickaxe tag | Pickaxe tag landed factory-wide 2026-07-29; advancements still deferred |

## Verified via MCP

- `thedarkcolour-ForestryCE` — `TileBottler`, `BottlerRecipe`, `GuiBottler`, craft recipe, hints; changelog “Remove bottler recipes from JEI”
- `ForestryMC-ForestryMC` — `BottlerRecipeCategory` / `BottlerRecipeMaker` / `BottlerRecipeWrapper` (layout reference for JEI restore)

## DoD status

- `./gradlew compileJava` — **BUILD SUCCESSFUL**.
- Fill/empty + FE rules documented above.
- `files/implemented-features.md` Phase 6 F9 / F18 rows updated.
- This file + `queries/factory-F18.md` + `queries/factory-machine-gui-data-polish.md`.

## Not blocking / deferred

- Recipe unlock advancements (no factory-wide set yet).
- JEI recipe-transfer handler (none for Bottler in classic either).
- BuildCraft gate triggers (CE dropped).
