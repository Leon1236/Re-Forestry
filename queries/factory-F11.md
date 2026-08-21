# Factory F11 — Fermenter

Stage complete 2026-07-25.

## Scope

- `IFermenterRecipe` + `IVariableFermentable` API
- `FermenterRecipe` MapCodec/StreamCodec (`reforestry:fermenter`)
- `TileFermenter` — `TilePowered`, 2000 FE/tick receive, 80000 cap, 4200 FE per work cycle
- Item resource + fluid resource tanks + product tank; fermenter fuel (compost/mulch/fertilizer compound)
- `ContainerFermenter` / `ScreenFermenter` (dual tanks, fuel + fermentation meters, error icons)
- `BlockTypeFactoryPlain.FERMENTER`, creative tab, shaped craft recipe, placeholder block model
- `FuelManager.fermenterFuel` seeded in `ModuleFactory.setupApi()` (CE Preference values)
- `FluidStorage.SIDED` + `ItemStorage.SIDED` + `EnergyHelper.registerSided`
- 19 CE fermenter recipes via `tools/extract_fermenter_recipes.py`

## Smoke recipe — `reforestry:fermenter/sugar_cane`

| Input | Amount |
|---|---|
| **Item** | 1× `minecraft:sugar_cane` (resource slot) |
| **Fluid** | 50 mB total `minecraft:water` (resource tank or can slot) |
| **Fuel** | `reforestry:fertilizer_bio` (compost), `reforestry:mulch`, or `reforestry:fertilizer_compound` |
| **Energy** | 4200 FE per work cycle (5-tick interval) |
| **Output** | ~50 mB `reforestry:biomass` (modifier 1.0; juice/honey recipes use 1.5×) |

### In-game smoke steps

1. Craft and place `reforestry:fermenter`; power with ≥2000 FE/tick source.
2. Put 1 sugar cane in the center resource slot.
3. Fill resource tank with water (bucket or `reforestry:can` with water in the left fluid-can slot).
4. Add compost or mulch to the fuel slot below the resource slot.
5. Wait for fermentation + fuel meters; collect biomass from the product tank (or fill cans in the right slots).

Alternate smoke: `sugar_cane_juice.json` — same item + `reforestry:juice` → 1.5× biomass output.

## Fuel values (CE Preference)

| Item | fermentPerCycle | burnDuration |
|---|---|---|
| `reforestry:fertilizer_compound` | 56 | 200 |
| `reforestry:fertilizer_bio` (compost) | 48 | 250 |
| `reforestry:mulch` | 48 | 250 |

## Files touched

| Area | Paths |
|---|---|
| API | `api/recipes/IFermenterRecipe.java`, `api/recipes/IVariableFermentable.java` |
| Core | `core/recipes/RecipeUtils.java` (fermenter lookup + input filter) |
| Factory | `factory/recipes/FermenterRecipe.java`, `factory/tiles/TileFermenter.java`, `factory/gui/ContainerFermenter.java`, `factory/client/ScreenFermenter.java`, `factory/ModuleFactory.java` |
| Features | `FactoryRecipeTypes`, `FactoryTiles`, `FactoryMenuTypes`, `BlockTypeFactoryPlain`, `FactoryCreativeTabs`, `FactoryClientHandler` |
| Data | `data/reforestry/recipe/fermenter.json`, `data/reforestry/recipe/fermenter/*.json` (19 recipes) |
| Tools | `tools/extract_fermenter_recipes.py` |

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-25).

## Blockers for F12a Fabricator smelting

- `IFabricatorSmeltingRecipe` + serializer not started; CE uses item → molten fluid in fabricator tank with `FluidRecipeFilter.FABRICATOR_SMELTING` allowlist.
- Fabricator base machine (`TileFabricator`, plan slot, 3×3 + liquid) is F12 proper — smelting is a separate recipe type consumed by fabricator tank filtering.
- No fabricator recipes extracted yet; CE tree under `data/forestry/recipes/fabricator/`.
- Fermenter biomass output feeds F7 Still — smoke chain: fermenter → still → ethanol is playable once both are placed.
