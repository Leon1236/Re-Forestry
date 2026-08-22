# Factory F16 — Hygroregulator RecipeType (alveary)

Stage complete 2026-07-25. CE reference: `IHygroregulatorRecipe`, `HygroregulatorRecipe`, `FactoryRecipeTypes.HYGROREGULATOR`, `TileAlvearyHygroregulator`, `RecipeUtils.getHygroRegulatorRecipe`.

## Scope

- **API:** `IHygroregulatorRecipe` — fluid input, retain time, humidity/temperature steps
- **Factory registration:** `reforestry:hygroregulator` on `FactoryRecipeTypes.HYGROREGULATOR` (`HygroregulatorRecipe` MapCodec/StreamCodec)
- **Datapack:** water + lava + ice (`reforestry:ice`; do not rename to CE 1.21.1 `crushed_ice`) — ice factory-off fallback + tank filter are **F-HYGRO** (`queries/f-hygro.md`)
- **Alveary tile:** `TileAlvearyHygroregulator` uses `RecipeUtils.getHygroregulatorRecipe` instead of hardcoded list
- **Factory-off safety:** `HygroregulatorFallbackRecipes` — water/lava/ice when Factory module disabled or recipes not loaded (ice climate: F-HYGRO)

**Not in F16:** JEI category (F18). Ice fallback + tank filter: **F-HYGRO**.

## Apiculture → Factory recipe API dependency

The alveary hygroregulator lives in **Apiculture**, but the recipe **type** is registered on the **Factory** module (mirrors CE).

| Layer | Depends on |
|---|---|
| `TileAlvearyHygroregulator` (apiculture) | `IHygroregulatorRecipe` (api), `RecipeUtils` (core) |
| `RecipeUtils.getHygroregulatorRecipe` (core) | `HygroregulatorRecipe`, `HygroregulatorFallbackRecipes` (factory) |
| Recipe JSON + serializer | Factory module init → `FactoryRecipeTypes.HYGROREGULATOR` static registration |

Apiculture does **not** declare a module dependency on Factory (CE parity — alveary works standalone). Runtime recipe lookup goes through core; compile-time core already references factory recipe classes (same pattern as fermenter/moistener helpers).

## Two resolution paths

### 1. Factory module on (datapack)

1. `ModuleFactory.init()` loads `FactoryRecipeTypes` → registers `reforestry:hygroregulator` serializer/type.
2. Datapack reload loads `data/reforestry/recipe/hygroregulator/water.json`, `lava.json`, and `ice.json`.
3. `RecipeUtils.getHygroregulatorRecipe` scans loaded recipes; first match on fluid type + sufficient tank amount wins.

| Recipe | Fluid / cycle | Humidity | Temperature | `time` in JSON | Work ticks |
|---|---|---|---|---|---|
| `hygroregulator/water` | 1 mB water | +1 | −1 | 0 | 20 (CE default when `time` ≤ 0) |
| `hygroregulator/lava` | 1 mB lava | −1 | +1 | 0 | 20 |
| `hygroregulator/ice` | 1 mB `reforestry:ice` | +2 | −2 | 10 | 10 |

### 2. Factory module off (fallback)

1. Factory module disabled in `config/reforestry/modules.properties` → `FactoryRecipeTypes` never loads → no datapack recipes parsed.
2. `RecipeUtils.getHygroregulatorRecipe` finds no `HygroregulatorRecipe` instances → **`HygroregulatorFallbackRecipes.match`** returns hardcoded water/lava/ice (same table as above).
3. Alveary hygro climate control continues to work.

Fallback is also used if Factory is on but datapack files are removed/custom packs omit hygro recipes.

## Smoke paths

### Water → humidify / cool (Factory on or off)

1. Build 3×3×3 alveary with at least one **hygroregulator** block (`reforestry:alveary_hygroregulator`).
2. Place queen/workers; ensure multiblock validates.
3. Put **water bucket** in hygro GUI bucket slot (or pipe water into tank).
4. Observe climate: **+1 humidity**, **−1 temperature** per 20-tick work cycle; **1 mB** consumed per cycle.

### Lava → dry / heat

1. Same setup; use **lava bucket** instead.
2. Observe: **−1 humidity**, **+1 temperature** per cycle; 1 mB lava per cycle.

### Factory-off regression

1. Set `reforestry:factory=false` in `config/reforestry/modules.properties`; restart server/world.
2. Repeat water smoke — behavior must match (fallback path).

## CE parity notes

- CE tile hardcodes `heatTicks = 20` regardless of recipe `time`; port uses `time > 0 ? time : 20`.
- CE water recipe includes `temperature_steps: -1` (previous hardcoded port used `0` — fixed via CE extract).
- CE liquid consumption is **1 mB** per cycle (previous hardcoded port used 100 mB — fixed via CE extract).
- Ice fluid id stays `reforestry:ice` (not CE 1.21.1 `crushed_ice`). F-HYGRO added fallback + tank filter.

## F17 blockers (out of F16 scope)

- **JEI** hygroregulator category — F18. Tank filter + ice fallback: **F-HYGRO**.

## Files touched

| Path | Role |
|---|---|
| `api/recipes/IHygroregulatorRecipe.java` | API (new) |
| `factory/recipes/HygroregulatorRecipe.java` | Serializer + impl |
| `factory/recipes/HygroregulatorFallbackRecipes.java` | Factory-off fallback |
| `factory/features/FactoryRecipeTypes.java` | `HYGROREGULATOR` registration |
| `core/recipes/RecipeUtils.java` | `getHygroregulatorRecipe` |
| `apiculture/multiblock/TileAlvearyHygroregulator.java` | Recipe lookup |
| `data/reforestry/recipe/hygroregulator/water.json` | Datapack |
| `data/reforestry/recipe/hygroregulator/lava.json` | Datapack |
| `tools/extract_hygroregulator_recipes.py` | CE extract script |
