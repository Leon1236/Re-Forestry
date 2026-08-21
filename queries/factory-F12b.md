# Factory F12b — Fabricator shaped craft

Stage complete 2026-07-25.

## Scope

- `IFabricatorRecipe` API + `FabricatorRecipe` MapCodec/StreamCodec (`reforestry:fabricator`)
- `ICraftingPlan` API (plan durability hook; no plan items ported yet)
- `FabricatorMoltenFluids` predicate (`api/predicates`) + `FabricatorMoltenFluidSetup` reload on server start / datapack reload
- `TileFabricator` — plan slot, 3×3 ghost craft grid, result slot, 2×9 ingredient storage; `craftResult()` drains molten + consumes storage; smelting path unchanged
- `ContainerFabricator` / `ScreenFabricator` — CE slot layout (176×211)
- `RecipeUtils.getFabricatorRecipe` / `isFabricatorPlan`
- `tools/extract_fabricator_recipes.py` (CE `fabricator/` shaped recipes, skips `smelting/`)
- Smoke recipe: `reforestry:fabricator/fireproof/log/oak` (full CE fabricator set restored in F18)

**Not in F12b:** Moistener (F13), wax-cast plan items. Bulk fabricator crafts + JEI = F18 (`queries/factory-F18.md`, `tools/import_fabricator_recipes.py`).

## Smoke recipe — `reforestry:fabricator/fireproof/log/oak`

| Input | Amount |
|---|---|
| **Molten** | 500 mB `reforestry:glass` (from smelting path) |
| **Grid (ghost)** | `oak_log` — `refractory_wax` — `oak_log` (middle row) |
| **Storage** | 2× oak log + 1× refractory wax in ingredient slots |
| **Plan** | empty (no plan item) |
| **Energy** | 200 FE per work cycle (+100 heat) |
| **Output** | 2× `reforestry:oak_fireproof_log` (result slot) |

### In-game smoke steps

1. Place powered `reforestry:fabricator` (≥1100 FE/tick).
2. Melt glass into molten tank (F12a path) until ≥500 mB liquid glass.
3. Set ghost grid middle row: oak log | refractory wax | oak log.
4. Put matching ingredients in the 2×9 storage below.
5. Leave plan slot empty; wait for work cycles.
6. Collect 2× fireproof oak log from result slot (top-right).

## CE parity notes

| CE | Re-Forestry |
|---|---|
| Slots: metal, plan, result, 2×9 storage + ghost 3×3 | same indices |
| `craftResult()` in `workCycle()` after heat tick | same |
| `hasWork()` craft path + melting fallback | same |
| `NO_RESOURCE_LIQUID` / `NO_RESOURCE_INVENTORY` | same |
| Molten filter from smelting + craft fluids | `FabricatorMoltenFluids` via recipe scan |
| `ICraftingPlan.planUsed` on craft | same hook |
| FE 3300 / 1100 / 200 | 1:1 RF→FE |

## Files touched

| Area | Paths |
|---|---|
| API | `api/recipes/IFabricatorRecipe.java`, `api/items/ICraftingPlan.java`, `api/predicates/FabricatorMoltenFluids.java` |
| Core | `core/recipes/RecipeUtils.java` |
| Factory | `factory/recipes/FabricatorRecipe.java`, `factory/FabricatorMoltenFluidSetup.java`, `factory/tiles/TileFabricator.java`, `factory/gui/ContainerFabricator.java`, `factory/client/ScreenFabricator.java`, `factory/features/FactoryRecipeTypes.java`, `factory/ModuleFactory.java` |
| Data | `data/reforestry/recipe/fabricator/fireproof/log/oak.json` |
| Tools | `tools/extract_fabricator_recipes.py` |

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-25).

## Blockers for F13 Moistener

- `IMoistenerRecipe` + `reforestry:moistener` type/serializer
- `TileMoistener` + spore slot + product slot + water tank (if CE uses fluid) / humidity logic
- `FuelManager.moistenerResource` already seeded in `ModuleFactory` — wire to tile
- Extract CE `data/forestry/recipes/moistener/` + block/item/GUI
- No dependency on fabricator craft; fabricator smelting + shaped craft are complete
