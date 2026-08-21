# Fabricator molten glass + machine recipes ledger

**Date:** 2026-07-27 (updated same day — ghost alpha, bulk recipes, JEI)

## Molten glass

- GUI tank is a **16px** reservoir at `(26, 48)` (CE `ReservoirWidget`), not a 58px tank — taller fill painted over the storage rows.
- Solidify rule (Re-Forestry, not CE): drain `5 mB/tick` only when `energyStorage.amount <= 0`. While energy remains, glass stays until a craft recipe consumes it. Heat still controls *melting* items into the tank.

## Ghost craft grid

Fabricator/Carpenter craft matrix uses `SlotGhostCrafting` (`isFake`, `mayPickup=false`). Clicks go through `PhantomSlotHelper` (copy held item as ghost, no consume). Recipe-tab fill only writes pattern stacks into the ghost inventory — they cannot be taken out.

**Semi-transparent look:** MC 26.2 `GuiGraphicsExtractor.fakeItem()` draws like a normal item. `ScreenForestry.extractSlot` and `GuiRecipeLedger` add a white overlay (`0x66FFFFFF`) on ghost/pattern icons so they read as previews.

## Tube + fireproof recipes

Electron tubes + flexible casing live under `data/reforestry/recipe/fabricator/electron_tubes/`. Fireproof wood families (log/wood/planks/stripped_*) and the rest of CE’s fabricator crafts were bulk-imported with `tools/import_fabricator_recipes.py` (~265 craft recipes total). The temporary carpenter `electron_tube_blaze` smoke recipe was removed.

## Recipes ledger API

Public API (`com.leon1236.reforestry.api.gui`):

- `MachineRecipeEntry(result, pattern)` — pattern size 9 for craft-grid machines, empty for list-only
- `IMachineRecipeSource` — tile-side listing hook
- `IContainerRecipeBook` — menu listing + `selectRecipe`; button ids `1000 + index`

Listing helpers: `MachineGuiRecipes` via `level.recipeAccess().getSynchronizedRecipes()` (MC 26.2 no longer syncs all recipes by default).

Factory serializers are registered with Fabric `RecipeSynchronization.synchronizeRecipeSerializer` in `FactoryRecipeTypes.init()`.

Pattern fill: `CraftingPatternHelper.patternFromCraftingRecipe`.

Client: `GuiRecipeLedger` on left (bottom), wired from `ScreenForestry` when the menu implements `IContainerRecipeBook`. Ledger icons are JEI clickable ingredients when JEI is present (`ForestryScreenJeiHandler`).

| Machine | List | Click fills pattern |
|---|---|---|
| Fabricator, Carpenter | yes | yes |
| Centrifuge, Smelter, Moistener, Squeezer, Still, Fermenter | yes | no |
| Bottler | empty (dynamic) | no |

## JEI

Factory categories + catalysts: see `queries/factory-F18.md` (`FactoryJeiPlugin`).
