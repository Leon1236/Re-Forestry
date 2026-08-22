# ACGaming-Binnie — extratrees-api

- Alias: `binnie`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Package/path root: `extratrees-api/src/main/java/binnie/extratrees/api`
- Java files scanned: **14**
- Date: 2026-07-30

## Summary
Module `extratrees-api` in `ACGaming-Binnie` is rooted at `extratrees-api/src/main/java/binnie/extratrees/api` (14 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `CarpentryManager`
- `ICarpentryInterface`
- `package-info`
- `ExtraTreesRecipeManager`
- `IBreweryCrafting`
- `IBreweryManager`
- `IBreweryRecipe`
- `IDistilleryManager`
- `IDistilleryRecipe`
- `IFruitPressManager`
- `IFruitPressRecipe`
- `ILumbermillManager`
- `ILumbermillRecipe`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py binnie "extratrees-api"`
- Source root exists: **True**
- Nested packages under this module:
  - `recipes`
- Declaration skim (first files):
  - `extratrees-api/src/main/java/binnie/extratrees/api/CarpentryManager.java`
    - L1: package binnie.extratrees.api;
    - L3: public class CarpentryManager {
    - L4: public static ICarpentryInterface carpentryInterface;
  - `extratrees-api/src/main/java/binnie/extratrees/api/ICarpentryInterface.java`
    - L1: package binnie.extratrees.api;
    - L8: public interface ICarpentryInterface {
    - L16: @Nullable
  - `extratrees-api/src/main/java/binnie/extratrees/api/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @MethodsReturnNonnullByDefault
    - L3: package binnie.extratrees.api;
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/ExtraTreesRecipeManager.java`
    - L1: package binnie.extratrees.api.recipes;
    - L5: public class ExtraTreesRecipeManager {
    - L7: @Nullable
    - L8: public static IBreweryManager breweryManager;
    - L9: @Nullable
    - L10: public static ILumbermillManager lumbermillManager;
    - L11: @Nullable
    - L12: public static IFruitPressManager fruitPressManager;
    - L13: @Nullable
    - L14: public static IDistilleryManager distilleryManager;
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IBreweryCrafting.java`
    - L1: package binnie.extratrees.api.recipes;
    - L8: public interface IBreweryCrafting {
    - L10: @Nullable
    - L13: @Nullable
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IBreweryManager.java`
    - L1: package binnie.extratrees.api.recipes;
    - L9: public interface IBreweryManager extends ICraftingManager<IBreweryRecipe> {
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IBreweryRecipe.java`
    - L1: package binnie.extratrees.api.recipes;
    - L10: public interface IBreweryRecipe extends IBinnieRecipe {
    - L11: @Nullable
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IDistilleryManager.java`
    - L1: package binnie.extratrees.api.recipes;
    - L8: public interface IDistilleryManager extends ICraftingManager<IDistilleryRecipe> {
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IDistilleryRecipe.java`
    - L1: package binnie.extratrees.api.recipes;
    - L6: public interface IDistilleryRecipe extends IBinnieRecipe {
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IFruitPressManager.java`
    - L1: package binnie.extratrees.api.recipes;
    - L7: public interface IFruitPressManager extends ICraftingManager<IFruitPressRecipe> {
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IFruitPressRecipe.java`
    - L1: package binnie.extratrees.api.recipes;
    - L7: public interface IFruitPressRecipe extends IBinnieRecipe {
  - `extratrees-api/src/main/java/binnie/extratrees/api/recipes/ILumbermillManager.java`
    - L1: package binnie.extratrees.api.recipes;
    - L6: public interface ILumbermillManager extends ICraftingManager<ILumbermillRecipe> {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `ICarpentryInterface` in `ICarpentryInterface.java`
- interface `IBreweryCrafting` in `IBreweryCrafting.java`
- interface `IBreweryManager` in `IBreweryManager.java`
- interface `IBreweryRecipe` in `IBreweryRecipe.java`
- interface `IDistilleryManager` in `IDistilleryManager.java`
- interface `IDistilleryRecipe` in `IDistilleryRecipe.java`
- interface `IFruitPressManager` in `IFruitPressManager.java`
- interface `IFruitPressRecipe` in `IFruitPressRecipe.java`
- interface `ILumbermillManager` in `ILumbermillManager.java`
- interface `ILumbermillRecipe` in `ILumbermillRecipe.java`

## Port relevance to Re-Forestry
- Data/source to extract for addon modules; not a runtime dependency.

## Source map
- `extratrees-api/src/main/java/binnie/extratrees/api/CarpentryManager.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/ICarpentryInterface.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/package-info.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/ExtraTreesRecipeManager.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IBreweryCrafting.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IBreweryManager.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IBreweryRecipe.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IDistilleryManager.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IDistilleryRecipe.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IFruitPressManager.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/IFruitPressRecipe.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/ILumbermillManager.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/ILumbermillRecipe.java`
- `extratrees-api/src/main/java/binnie/extratrees/api/recipes/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
