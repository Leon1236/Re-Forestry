# Stage F6 — Smelter vertical slice

Second playable Factory machine: alloy-style multi-input smelter, energy + recipe, no sockets, no fluids, no temperature mechanic.

## F6-ALLOY — Smelter alloy catch-up (2026-08-20)

Local datapack was only bronze. Re-ran `python3 tools/extract_smelter_recipes.py --root . --apply` from repo root (script dir is on `sys.path`, so `ce_paths` / `reference_repos` import). Source is CE **1.20.1** generated resources via `tools/ce_paths.py`:

`MarkDown_Maker/Finished_github_clone/2026-08-17/thedarkcolour-ForestryCE-1.20.1/src/generated/resources/data/forestry/recipes/smelter/`

Stage notes said “CE20 has 10 recipes.” That folder actually has **12** JSON files. The script wrote all 12 (`written=12 skipped=0`). No hand-typed ingredient lists.

### Extracted files (`data/reforestry/recipe/smelter/`)

| File | Playable without another mod? |
|---|---|
| `bronze_from_ingots.json` | Yes — `c:ingots/copper` + `c:ingots/tin` → `c:ingots/bronze` (we fill tin/bronze tags) |
| `bronze_from_raw_materials.json` | Yes — `c:raw_materials/copper` + `c:raw_materials/tin` → `c:ingots/bronze` |
| `brass_from_ingots.json` | No — `c:ingots/zinc` / `c:ingots/brass` |
| `brass_from_raw_materials.json` | No — `c:raw_materials/zinc` / `c:ingots/brass` |
| `constantan_from_ingots.json` | No — `c:ingots/nickel` / `c:ingots/constantan` |
| `constantan_from_raw_materials.json` | No — `c:raw_materials/nickel` / `c:ingots/constantan` |
| `electrum_from_ingots.json` | No — `c:ingots/silver` / `c:ingots/electrum` |
| `electrum_from_raw_materials.json` | No — `c:raw_materials/silver` / `c:ingots/electrum` |
| `invar_from_ingots.json` | No — `c:ingots/nickel` / `c:ingots/invar` |
| `invar_from_raw_materials.json` | No — `c:raw_materials/nickel` / `c:ingots/invar` |
| `silicon_from_coal.json` | No — output `c:silicon` |
| `silicon_from_coke.json` | No — `c:coal_coke` + `c:silicon` |

Bronze was already local. The script overwrote both bronze files; content still matches (3 copper + 1 tin → 4 bronze, `processingTime` 40).

Brass / invar / electrum / constantan / silicon are **not** Re-Forestry items (CE 1.21.1 and we only register tin + bronze). Recipes use `c:ingots/*`, `c:raw_materials/*`, and `c:silicon` tags. `SmelterRecipe.getOutput()` uses `CraftingPatternHelper.firstStack`; empty tags yield `ItemStack.EMPTY` so those recipes stay uncraftable until another mod fills the tags. CE wrapped the non-bronze recipes in `forge:conditional` / `tag_empty`; the extractor always keeps the inner recipe.

CE **1.21.1** generated datapack has **no** `recipe/smelter/` folder (carpenter / centrifuge / fabricator / fermenter / squeezer / still / hygroregulator / moistener only). CE 1.21.1 Java also has no `SMELTER` on `FactoryTiles` / `FactoryRecipeTypes` — generated copies were dropped, not moved. Re-Forestry keeps the F6 machine and the CE20 recipe set.

Did not register brass / invar / electrum / constantan / silicon items.

---


## Files created

- `api/recipes/ISmelterRecipe.java` — mirrors CE `forestry/api/recipes/ISmelterRecipe.java` (`getProcessingTime()`, `getInputs()`, `getOutput()`, `matches(...)`), using port `IngredientStack` instead of CE's class path.
- `core/recipes/IngredientStack.java` — count + `Ingredient` codec/stream pair (CE `forestry/core/recipes/IngredientStack.java` shape, MC 26.2 MapCodec/StreamCodec).
- `core/inventory/InventoryHelper.java` — `registerSided(type)` wraps `ItemStorage.SIDED.registerForBlockEntity((tile, dir) -> ContainerStorage.of(tile, dir), type)` for `WorldlyContainer` tiles (Fabric Transfer API v8 uses `ContainerStorage`, not the older `InventoryStorage` name).
- `factory/recipes/SmelterRecipe.java` — record implementing `ISmelterRecipe`; `MapCodec`/`StreamCodec` (`inputs`, `output`, `processingTime`); static `canAlloy(ISmelterRecipe, List<ItemStack>)` ported from CE.
- `factory/tiles/TileSmelter.java` — `extends TilePowered implements WorldlyContainer`. Slots: `0-8` = 3×3 resource grid, `10` = product. No `ISocketable`/circuit inventory (F15). Energy 1100 recv / 40000 cap (CE SoT). `ENERGY_PER_WORK_CYCLE=2000`, `ENERGY_PER_RECIPE_TIME=200`, `TICKS_PER_RECIPE_TIME=1` — recipe time 40 → 40 ticks/cycle, 8000 FE total. Craft-preview ghost slot via `SimpleContainer(1)` synced when recipe changes.
- `factory/gui/ContainerSmelter.java` — 3×3 input + preview + output slots; progress + error `ContainerData` (same pattern as centrifuge).
- `factory/client/ScreenSmelter.java` — programmatic grey panel + progress meter + error icons (no dedicated texture yet; F17 scope).
- `tools/extract_smelter_recipes.py` — extractor (usage in docstring). Unwraps `forge:conditional` → inner `forestry:smelter`, renames `forge:` tags → `c:`. CE generated tree not present locally under `for textures only/…`; recipes were written from MCP-fetched CE JSON for this stage.
- `src/main/resources/data/reforestry/recipe/smelter/*.json` — original F6 wrote bronze from MCP; **F6-ALLOY** re-extracted the full CE20 set (12 files). Example smoke recipe:

  ```json
  {
    "type": "reforestry:smelter",
    "inputs": [
      { "count": 3, "ingredient": { "tag": "c:ingots/copper" } },
      { "count": 1, "ingredient": { "tag": "c:ingots/tin" } }
    ],
    "output": { "count": 4, "ingredient": { "tag": "c:ingots/bronze" } },
    "processingTime": 40
  }
  ```

- `src/main/resources/data/reforestry/recipe/smelter.json` — shaped machine craft (glass/iron/furnace, CE pattern; Fabric `c:` tags).
- `src/main/resources/data/reforestry/loot_table/blocks/smelter.json` — drop self.
- Placeholder assets: `blockstates/smelter.json`, `models/block/smelter.json` (`cube_all` on `reforestry:block/smelter.0`), `models/item/smelter.json`, `items/smelter.json`.

## Files modified

- `factory/blocks/BlockTypeFactoryPlain.java` — added `SMELTER` PLAIN group entry + `TileSmelter::serverTick`.
- `factory/features/FactoryRecipeTypes.java` — `SMELTER = REGISTRY.recipeType("smelter", () -> SmelterRecipe.SERIALIZER)`, id **`reforestry:smelter`**.
- `factory/features/FactoryTiles.java` — `SMELTER` block entity type; `init()` registers `EnergyHelper.registerSided` + `InventoryHelper.registerSided` for **both** centrifuge and smelter (centrifuge lacked item sided exposure until F6).
- `factory/features/FactoryMenuTypes.java` — `SMELTER` menu type.
- `factory/features/FactoryCreativeTabs.java` — tab lists smelter block item.
- `factory/client/FactoryClientHandler.java` — `ScreenSmelter` registration.
- `assets/reforestry/lang/en_us.json` — `block.reforestry.smelter`.

## Energy model (CE 1:1 RF→FE)

- Capacity **40000**, max receive **1100** FE/tick.
- Base **2000** FE per 10-tick reference window → **200 FE per recipe-time tick**. A `processingTime: 40` recipe costs **8000 FE** over 40 work-cycle ticks (× `WORK_TICK_INTERVAL` 5 game ticks between work steps ≈ ~10s wall time with continuous power, same order as centrifuge).

## What was slimmed vs CE

- **No sockets / `ISocketable` / circuit multipliers** (F15).
- **No temperature warm-up** — CE hints mention warming; tile has no temperature state in CE either when `temperature: 0` on all datagen recipes. Not ported.
- **No flame/smoke particles** on tick (client FX polish; optional later).
- **No dedicated GUI texture** — programmatic screen like F5 centrifuge.
- **Conditional forge tag-empty gating** — CE wraps brass/constantan/electrum/invar in `forge:conditional`; port always registers the inner recipe (tags may be empty until another mod/datapack fills `c:` tags).

## Playable path (DoD #2) — documented smoke test

Requires **`c:ingots/tin`** and **`c:ingots/bronze`** tags populated (vanilla alone only has copper/iron/gold). With a mod or datapack that adds tin/bronze to those tags:

1. `forceload add <x> <z>`
2. `setblock <x> <y> <z> reforestry:smelter`
3. Adjacent `reforestry:debug_creative_energy` for FE (1100 FE/tick cap until 40000 full).
4. Inject inputs into block entity NBT (or use GUI):

   ```mcfunction
   data merge block <x> <y> <z> {Items:[
     {Slot:0b,id:"minecraft:copper_ingot",count:3},
     {Slot:1b,id:"<tin_ingot_id>",count:1}
   ]}
   ```

5. Wait ~10s with power; slot `10` should hold 4× bronze (recipe `bronze_from_ingots.json`).
6. `data get block <x> <y> <z>` — inputs consumed, output present, `Energy` drained from full buffer.

**Alternative without tin:** `invar_from_ingots` uses only `c:ingots/iron`, `c:ingots/nickel`, `c:ingots/invar` — still needs nickel/invar tags from another mod.

Live RCON not run this session (no minecraft-world MCP smoke in subagent).

## Verified via MCP (`thedarkcolour-ForestryCE`)

- `forestry/api/recipes/ISmelterRecipe.java`
- `forestry/factory/recipes/SmelterRecipe.java`
- `forestry/factory/tiles/TileSmelter.java`
- `forestry/factory/gui/ContainerSmelter.java`
- `forestry/factory/inventory/InventorySmelter.java`
- Generated recipes under CE20 `data/forestry/recipes/smelter/` (12 files). CE 1.21.1 has no generated `recipe/smelter/` folder.

## DoD status

- `./gradlew compileJava` — **BUILD SUCCESSFUL**.
- Alloy recipe + FE path documented above; live RCON optional (tag dependency).
- `files/implemented-features.md` Phase 6 F6 row updated.
- This file.

## Blockers for F7 Still

None from F6. Still is the first **fluid→fluid** machine (`TileStill`, dual tanks `TankLayout.BOTH`, `IStillRecipe`, 80000 cap / 1100 recv per CE inventory). Reuses F4 `FilteredFluidStorage`/`MultiFluidTank` + F3b `TilePowered` energy loop. Smelter adds no fluid code to migrate. Item sided registration pattern (`InventoryHelper.registerSided`) applies to future item-only machines; Still will need `FluidStorage.SIDED` registration in addition.
