# Factory F12a — Fabricator smelting

Stage complete 2026-07-25.

## Scope

- `IFabricatorSmeltingRecipe` API
- `FabricatorSmeltingRecipe` MapCodec/StreamCodec (`reforestry:fabricator_smelting`)
- `TileFabricator` — `TilePowered`, 1100 FE/tick receive, 3300 cap, 200 FE per work cycle; heat + molten glass tank (smelting path only)
- `ContainerFabricator` / `ScreenFabricator` — metal slot, molten tank, heat + melting-point meters, error icons
- `BlockTypeFactoryPlain.FABRICATOR`, creative tab, shaped craft recipe, existing block/item models
- `FluidStorage.SIDED` + `ItemStorage.SIDED` + `EnergyHelper.registerSided`
- 4 CE smelting recipes via `tools/extract_fabricator_smelting_recipes.py`

**Not in F12a (F12b):** `IFabricatorRecipe` / `reforestry:fabricator` craft type, plan slot, 3×3 ghost grid, result slot, shaped tube/casing recipes.

## Smoke recipe — `reforestry:fabricator_smelting/glass`

| Input | Amount |
|---|---|
| **Item** | 1× `minecraft:glass` (metal slot) |
| **Energy** | 200 FE per work cycle (+100 heat per cycle when recipe present) |
| **Heat** | Melting point **1000** (glass); machine max heat 5000 |
| **Output** | 1000 mB `reforestry:glass` (liquid glass) in molten tank |

Alternate smoke: `sand.json` — 1 sand/red_sand, melting **3000**, 1000 mB liquid glass.

### In-game smoke steps

1. Craft and place `reforestry:fabricator` (gold + glass + sturdy machine + chest).
2. Power with ≥1100 FE/tick source.
3. Put 1 glass block in the metal slot (center-right of GUI).
4. Wait for heat bar to reach melting marker (1000 for glass).
5. Glass melts into molten tank; collect via fluid pipes or cans (F4c).

## CE parity notes

| CE | Re-Forestry |
|---|---|
| `MAX_HEAT` 5000 | same |
| Energy 3300 / 1100 / 200 per cycle | same (1:1 RF→FE) |
| Molten tank 8 buckets | 8000 mB |
| Heat decay >2500: −2/tick, else −1/tick | same |
| Solidify drain 5 mB when heat &lt; melting−100 | same |
| `FluidRecipeFilter.FABRICATOR_SMELTING_OUTPUT` | `FilteredFluidStorage.only(reforestry:glass)` — all CE smelting recipes output glass |

## Files touched

| Area | Paths |
|---|---|
| API | `api/recipes/IFabricatorSmeltingRecipe.java` |
| Core | `core/recipes/RecipeUtils.java` (melting lookup + input filter) |
| Factory | `factory/recipes/FabricatorSmeltingRecipe.java`, `factory/tiles/TileFabricator.java`, `factory/gui/ContainerFabricator.java`, `factory/client/ScreenFabricator.java` |
| Features | `FactoryRecipeTypes`, `FactoryTiles`, `FactoryMenuTypes`, `BlockTypeFactoryPlain`, `FactoryCreativeTabs`, `FactoryClientHandler` |
| Data | `data/reforestry/recipe/fabricator.json`, `data/reforestry/recipe/fabricator_smelting/*.json` (4 recipes) |
| Tools | `tools/extract_fabricator_smelting_recipes.py` |
| Lang | `assets/reforestry/lang/en_us.json` (`for.gui.fabricator.heat`, `requiredHeat`) |

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-25).

## Blockers for F12b Fabricator craft

- `IFabricatorRecipe` + `reforestry:fabricator` RecipeType/serializer
- Plan slot filter (`RecipeUtils.isFabricatorPlan`), ghost 3×3 crafting inventory, result slot
- `craftResult()` draining molten fluid + grid ingredients; plan durability (`ICraftingPlan`)
- Extract CE `data/forestry/recipes/fabricator/` shaped recipes (tubes, casings, fireproof wood, stained glass, etc.)
- Expand GUI to CE layout (inventory 2×9, plan, result, craft matrix); optional `ReservoirWidget` texture parity
- `hasWork()` craft path + `NO_RESOURCE_LIQUID` / `NO_RESOURCE_INVENTORY` errors
- Dynamic molten-tank filter from both smelting + craft recipe output fluids (CE `FluidRecipeFilter`)
