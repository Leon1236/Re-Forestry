# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — recipes

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/recipe`
- Java files scanned: **3**
- Date: 2026-07-30

## Summary
Module `recipes` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/recipe` (3 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModRecipes`
- `CrystallizerRecipe`
- `CrystallizerRecipeInput`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "recipes"`
- Source root exists: **True**
- Nested packages under this module:
  - `custom`
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/recipe/ModRecipes.java`
    - L1: package net.kaupenjoe.tutorialmod.recipe;
    - L11: public class ModRecipes {
    - L12: public static final RecipeSerializer<CrystallizerRecipe> CRYSTALLIZER_SERIALIZER = Registry.register(
    - L15: public static final RecipeType<CrystallizerRecipe> CRYSTALLIZER_TYPE = Registry.register(
    - L18: @Override
    - L19: public String toString() {
    - L24: public static void registerModRecipes() {
  - `src/main/java/net/kaupenjoe/tutorialmod/recipe/custom/CrystallizerRecipe.java`
    - L1: package net.kaupenjoe.tutorialmod.recipe.custom;
    - L13: public record CrystallizerRecipe(Ingredient inputItem, ItemStackTemplate output) implements Recipe<CrystallizerRecipeInput> {
    - L14: public static final MapCodec<CrystallizerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
    - L19: public static final StreamCodec<RegistryFriendlyByteBuf, CrystallizerRecipe> STREAM_CODEC =
    - L30: @Override
    - L31: public boolean matches(CrystallizerRecipeInput input, Level level) {
    - L39: @Override
    - L40: public ItemStack assemble(CrystallizerRecipeInput input) {
    - L44: @Override
    - L45: public boolean showNotification() {
    - L49: @Override
    - L50: public String group() {
  - `src/main/java/net/kaupenjoe/tutorialmod/recipe/custom/CrystallizerRecipeInput.java`
    - L1: package net.kaupenjoe.tutorialmod.recipe.custom;
    - L6: public record CrystallizerRecipeInput(ItemStack input) implements RecipeInput {
    - L7: @Override
    - L8: public ItemStack getItem(int index) {
    - L12: @Override
    - L13: public int size() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `CrystallizerRecipe` in `CrystallizerRecipe.java`
- record `CrystallizerRecipeInput` in `CrystallizerRecipeInput.java`

## Port relevance to Re-Forestry
- Mentions of `recipes` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/recipe/ModRecipes.java`
- `src/main/java/net/kaupenjoe/tutorialmod/recipe/custom/CrystallizerRecipe.java`
- `src/main/java/net/kaupenjoe/tutorialmod/recipe/custom/CrystallizerRecipeInput.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
