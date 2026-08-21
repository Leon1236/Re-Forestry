# Factory F10 — Carpenter

Stage complete 2026-07-25.

## Scope

- `ICarpenterRecipe` API + `CarpenterRecipe` MapCodec/StreamCodec (`reforestry:carpenter`)
- `TileCarpenter` — `TilePowered`, 1100 FE/tick receive, 40000 cap, 2040 FE per 10-tick reference × recipe `time`
- 3×3 craft grid + box + resource tank + 2×9 storage + product + can input
- `ContainerCarpenter` / `ScreenCarpenter` (218px-tall GUI, resource tank, progress meter, error icons)
- `BlockTypeFactoryPlain.CARPENTER`, creative tab, shaped craft recipe, placeholder block model
- `FluidStorage.SIDED` + `ItemStorage.SIDED` + `EnergyHelper.registerSided`
- Minimal CE recipes via `tools/extract_carpenter_recipes.py` (3 files)

## Smoke recipe — `reforestry:carpenter/bog_earth`

| Input | Amount |
|---|---|
| **Fluid** | 1000 mB `minecraft:water` (resource tank or drained from fluid container in can slot) |
| **3×3 grid** | Shaped pattern `#X# / XYX / #X#` — `#` dirt, `X` sand, `Y` mulch |
| **2×9 storage** | Same ingredients as grid (machine consumes from storage, not the grid) |
| **Box** | none |
| **Energy** | 204 FE × `time` (5) per work cycle, 5 ticks per cycle |
| **Output** | 8× `reforestry:bog_earth` → product slot |

### In-game smoke steps

1. Place `reforestry:carpenter`, power with ≥1100 FE/tick source.
2. Fill resource tank with 1000+ mB water (bucket or `reforestry:can` with water).
3. Set 3×3 grid to bog-earth pattern; put matching dirt/sand/mulch in storage row(s).
4. Wait for progress bar; collect 8 bog earth from product slot.

Also extracted: `humus` (water + dirt/mulch), `impregnated_casing` (250 mB seed_oil + logs).

## Recipe-transfer packets

**Not implemented.** Manual grid + storage fill is playable without JEI transfer. CE `PacketRecipeTransferRequest`/`Update` deferred to F18 (JEI polish). No Fabric networking scaffold in factory module yet (same as F1 note).

## Files touched

| Area | Paths |
|---|---|
| API | `api/recipes/ICarpenterRecipe.java` |
| Core | `core/recipes/CraftingInputHelper.java`, `core/recipes/RecipeUtils.java` (carpenter lookup) |
| Factory | `factory/recipes/CarpenterRecipe.java`, `factory/tiles/TileCarpenter.java`, `factory/gui/ContainerCarpenter.java`, `factory/client/ScreenCarpenter.java` |
| Features | `FactoryRecipeTypes`, `FactoryTiles`, `FactoryMenuTypes`, `BlockTypeFactoryPlain`, `FactoryCreativeTabs`, `FactoryClientHandler` |
| Data | `data/reforestry/recipe/carpenter.json`, `data/reforestry/recipe/carpenter/{bog_earth,humus,impregnated_casing}.json` |
| Assets | `assets/reforestry/blockstates/carpenter.json`, models/item/block, `items/carpenter.json` |
| Tools | `tools/extract_carpenter_recipes.py` |

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-25).

## Blockers for F11 Fermenter

- `IFermenterRecipe` + serializer not started; CE uses item + fluid input, biomass/fluid output, `FuelManager.fermenterFuel` map (empty stub in `ModuleFactory.setupApi`).
- Fermenter needs `FluidRecipeFilter`-style tank filtering (input/output fluids from recipe set) — can mirror F4 `FilteredFluidStorage` + runtime filter or accept-all initially.
- No fermenter recipes extracted yet; CE has large recipe tree under `data/forestry/recipes/fermenter/`.
- Sockets (F15) not required for Fermenter base behavior.
