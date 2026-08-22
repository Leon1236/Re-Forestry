# F18 — Factory JEI + fabricator recipe restore

Stage complete 2026-07-27. Follow-ups through 2026-07-29.

## What landed

### JEI plugin (`FactoryJeiPlugin`)

- `@JeiPlugin` discovered by JEI (annotation processor in `build.gradle`); no Fabric entrypoint needed.
- Categories: fabricator, carpenter, centrifuge, fermenter, moistener, smelter, squeezer, still, **bottler**, rainmaker.
- Recipes loaded from client `RecipeAccess.getSynchronizedRecipes()` for RecipeType machines (same sync path as the GUI recipes ledger).
- **Bottler** has no RecipeType — recipes are built at JEI register time by `BottlerRecipeMaker` (scan JEI items + Forestry containers × source fluids).
- Catalysts = each machine block; click areas on factory screens open the matching JEI category.
- Recipe-tab icons are JEI clickable ingredients (look up / cheat-give).

Shared helpers live under `core.compat.jei` (`ReforestryJeiRecipeTypes`, `JeiRecipeSources`, `ForestryRecipeCategory`, …).

### Fabricator recipes bulk restore

- `tools/import_fabricator_recipes.py` ports CE `forestry/recipes/fabricator/**` → `reforestry/recipe/fabricator/**` (`forestry:`→`reforestry:`, `forge:` tags→`c:`).
- **~265** fabricator craft recipes (tubes, flexible casing, fireproof wood families). Smelting recipes were already present under `fabricator_smelting/`.

### Ghost / ledger polish

- Ghost craft slots + recipes-tab icons draw a semi-transparent white overlay (`0x66FFFFFF`) — MC 26.2 `fakeItem()` alone looks opaque.
- Molten glass GUI reservoir stays 16px; solidify only when energy is empty (see `queries/fabricator-recipes-ledger.md`).

## Not in F18

- Carpenter/fabricator **recipe transfer** packets (CE has them; optional follow-up).
- Apiculture / arboriculture / charcoal JEI categories.

## Follow-up 2026-07-29 — Squeezer JEI

`JeiRecipeSources.collect(ISqueezerRecipe.class)` also matched `ISqueezerContainerRecipe` (extends squeezer). Those recipes have empty inputs and blank fluid, so JEI showed remnant-only entries (tin 5%, beeswax 10%, refractory wax 10%). Filter them out in `FactoryJeiPlugin` (CE registers by `FactoryRecipeTypes.SQUEEZER` type, not by interface). Container drain stays in-game; it is just not a JEI layout.

## Follow-up 2026-07-29 — Bottler JEI

CE changelog removed Bottler recipes from JEI; ForestryMC 1.12 had a dynamic category. Restored on request:

| Piece | Role |
|---|---|
| `BottlerRecipeCategory` | Blank 62×60 layout; left = empty into tank, right = fill from tank (ForestryMC UVs) |
| `BottlerRecipeMaker` | Emptying for filled containers; filling for empty × all source fluids; seeds Forestry cans/capsules/buckets |
| `ReforestryJeiRecipeTypes.BOTTLER` | Recipe type for runtime `BottlerRecipe` |
| Click areas | ScreenBottler progress arrows `(107,33)` fill and `(45,33)` empty |

Item description `for.jei.description.bottler` remains via `JeiDescriptions`. Details: `queries/factory-F9.md`.

## Follow-up 2026-07-29 — JEI descriptions for all factory machines

`FactoryJeiPlugin` now registers `JeiDescriptions` for every PLAIN machine + rainmaker. Lang keys `for.jei.description.<id>` exist in all locales. Full GUI/data polish (hints, loot, pickaxe tag): `queries/factory-machine-gui-data-polish.md`.

## Smoke checks

1. Fabricator recipes tab lists tubes + many fireproof outputs (not only oak).
2. Click recipe → ghost 3×3 fills; items look faded and cannot be taken.
3. JEI: search electron tube / fireproof log → fabricator recipes appear.
4. JEI cheat mode: click recipe output → get item.
5. Click fabricator result slot area → opens fabricator JEI category.
6. Squeezer JEI: no tin/beeswax/refractory-wax-only rows; honey_drop shows propolis remnant chance.
7. Bottler JEI: empty can + biomass (and water bucket) show fill/empty recipes; GUI arrow click opens category.
8. JEI item info tab: each factory machine shows its description text.

## Compile

```
./gradlew compileJava
```

BUILD SUCCESSFUL (2026-07-27; Bottler JEI + machine descriptions recompiled 2026-07-29).
