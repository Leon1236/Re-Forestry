# thedarkcolour-gendustry — recipe

- Alias: `gendustry`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`
- Package/path root: `src/main/java/thedarkcolour/gendustry/recipe`
- Java files scanned: **15**
- Date: 2026-07-30

## Summary
Module `recipe` in `thedarkcolour-gendustry` is rooted at `src/main/java/thedarkcolour/gendustry/recipe` (15 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DnaFinishedRecipe`
- `DnaRecipe`
- `GeneticTemplateRecipe`
- `MutagenFinishedRecipe`
- `MutagenRecipe`
- `ProcessorRecipe`
- `ProteinFinishedRecipe`
- `ProteinRecipe`
- `DnaRecipeCache`
- `IRecipeCache`
- `MutagenRecipeCache`
- `ProteinRecipeCache`
- `RecipeCacheRegistry`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py gendustry "recipe"`
- Source root exists: **True**
- Nested packages under this module:
  - `cache`
- Declaration skim (first files):
  - `src/main/java/thedarkcolour/gendustry/recipe/DnaFinishedRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L15: public class DnaFinishedRecipe implements FinishedRecipe {
    - L21: public DnaFinishedRecipe(ResourceLocation id, ISpeciesType<?, ?> speciesType, ILifeStage stage, int amount) {
    - L28: @Override
    - L29: public void serializeRecipeData(JsonObject json) {
    - L35: @Override
    - L36: public ResourceLocation getId() {
    - L40: @Override
    - L41: public RecipeSerializer<?> getType() {
    - L45: @Nullable
    - L46: @Override
    - L47: public JsonObject serializeAdvancement() {
  - `src/main/java/thedarkcolour/gendustry/recipe/DnaRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L20: public class DnaRecipe extends ProcessorRecipe {
    - L24: public DnaRecipe(ResourceLocation id, ISpeciesType<?, ?> speciesType, ILifeStage stage, int amount) {
    - L31: public ISpeciesType<?, ?> getSpeciesType() {
    - L35: public ILifeStage getStage() {
    - L39: @Override
    - L40: public RecipeSerializer<?> getSerializer() {
    - L44: @Override
    - L45: public RecipeType<?> getType() {
    - L49: @Override
    - L50: public boolean isIngredient(ItemStack stack) {
    - L54: public static class Serializer implements RecipeSerializer<DnaRecipe> {
  - `src/main/java/thedarkcolour/gendustry/recipe/GeneticTemplateRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L25: public class GeneticTemplateRecipe extends CustomRecipe {
    - L26: public GeneticTemplateRecipe(ResourceLocation id, CraftingBookCategory category) {
    - L30: @Override
    - L31: public boolean matches(CraftingContainer container, Level level) {
    - L91: @Override
    - L92: public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
  - `src/main/java/thedarkcolour/gendustry/recipe/MutagenFinishedRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L13: // Using AI to write this class is the real data generation
    - L14: public class MutagenFinishedRecipe implements FinishedRecipe {
    - L19: public MutagenFinishedRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
    - L25: @Override
    - L26: public void serializeRecipeData(JsonObject json) {
    - L31: @Override
    - L32: public ResourceLocation getId() {
    - L36: @Override
    - L37: public RecipeSerializer<?> getType() {
    - L41: @Nullable
    - L42: @Override
  - `src/main/java/thedarkcolour/gendustry/recipe/MutagenRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L16: public class MutagenRecipe extends ProcessorRecipe {
    - L19: public MutagenRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
    - L24: public Ingredient getIngredient() {
    - L28: @Override
    - L29: public boolean isIngredient(ItemStack stack) {
    - L33: @Override
    - L34: public RecipeSerializer<?> getSerializer() {
    - L38: @Override
    - L39: public RecipeType<?> getType() {
    - L43: public static class Serializer implements RecipeSerializer<MutagenRecipe> {
    - L44: @Override
  - `src/main/java/thedarkcolour/gendustry/recipe/ProcessorRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L9: public abstract class ProcessorRecipe implements IForestryRecipe {
    - L11: protected final int amount;
    - L13: public ProcessorRecipe(ResourceLocation id, int amount) {
    - L18: public int getAmount() {
    - L22: @Override
    - L23: public ItemStack getResultItem(RegistryAccess registryAccess) {
    - L27: @Override
    - L28: public ResourceLocation getId() {
    - L32: public abstract boolean isIngredient(ItemStack stack);
  - `src/main/java/thedarkcolour/gendustry/recipe/ProteinFinishedRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L13: // Using AI to write this class is the real data generation
    - L14: public class ProteinFinishedRecipe implements FinishedRecipe {
    - L19: public ProteinFinishedRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
    - L25: @Override
    - L26: public void serializeRecipeData(JsonObject json) {
    - L31: @Override
    - L32: public ResourceLocation getId() {
    - L36: @Override
    - L37: public RecipeSerializer<?> getType() {
    - L41: @Nullable
    - L42: @Override
  - `src/main/java/thedarkcolour/gendustry/recipe/ProteinRecipe.java`
    - L1: package thedarkcolour.gendustry.recipe;
    - L16: public class ProteinRecipe extends ProcessorRecipe {
    - L19: public ProteinRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
    - L25: public Ingredient getIngredient() {
    - L29: @Override
    - L30: public boolean isIngredient(ItemStack stack) {
    - L34: @Override
    - L35: public RecipeSerializer<?> getSerializer() {
    - L39: @Override
    - L40: public RecipeType<?> getType() {
    - L44: public static class Serializer implements RecipeSerializer<ProteinRecipe> {
    - L45: @Override
  - `src/main/java/thedarkcolour/gendustry/recipe/cache/DnaRecipeCache.java`
    - L1: package thedarkcolour.gendustry.recipe.cache;
    - L14: public enum DnaRecipeCache implements IRecipeCache {
    - L19: @Nullable
    - L20: public DnaRecipe getRecipe(ILifeStage stage) {
    - L25: @Override
    - L26: public void reload(RecipeManager recipes) {
    - L34: @Override
    - L35: public void unload() {
  - `src/main/java/thedarkcolour/gendustry/recipe/cache/IRecipeCache.java`
    - L1: package thedarkcolour.gendustry.recipe.cache;
    - L9: public interface IRecipeCache {
  - `src/main/java/thedarkcolour/gendustry/recipe/cache/MutagenRecipeCache.java`
    - L1: package thedarkcolour.gendustry.recipe.cache;
    - L19: public enum MutagenRecipeCache implements IRecipeCache {
    - L27: @Nullable
    - L28: public MutagenRecipe getRecipe(ItemStack stack) {
    - L42: @Override
    - L43: public void reload(RecipeManager recipes) {
    - L63: @Override
    - L64: public void unload() {
  - `src/main/java/thedarkcolour/gendustry/recipe/cache/ProteinRecipeCache.java`
    - L1: package thedarkcolour.gendustry.recipe.cache;
    - L20: public enum ProteinRecipeCache implements IRecipeCache {
    - L28: @Nullable
    - L29: public ProteinRecipe getRecipe(ItemStack stack) {
    - L43: @Override
    - L44: public void reload(RecipeManager recipes) {
    - L64: @Override
    - L65: public void unload() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- enum `DnaRecipeCache` in `DnaRecipeCache.java`
- interface `IRecipeCache` in `IRecipeCache.java`
- enum `MutagenRecipeCache` in `MutagenRecipeCache.java`
- enum `ProteinRecipeCache` in `ProteinRecipeCache.java`
- key type `RecipeCacheRegistry` (`RecipeCacheRegistry.java`)

## Port relevance to Re-Forestry
- Mentions of `recipe` appear in `files/implemented-features.md` — check that file for port status.
- Addon module shape for future `reforestry:gendustry`; not yet ported.

## Source map
- `src/main/java/thedarkcolour/gendustry/recipe/DnaFinishedRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/DnaRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/GeneticTemplateRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/MutagenFinishedRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/MutagenRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/ProcessorRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/ProteinFinishedRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/ProteinRecipe.java`
- `src/main/java/thedarkcolour/gendustry/recipe/cache/DnaRecipeCache.java`
- `src/main/java/thedarkcolour/gendustry/recipe/cache/IRecipeCache.java`
- `src/main/java/thedarkcolour/gendustry/recipe/cache/MutagenRecipeCache.java`
- `src/main/java/thedarkcolour/gendustry/recipe/cache/ProteinRecipeCache.java`
- `src/main/java/thedarkcolour/gendustry/recipe/cache/RecipeCacheRegistry.java`
- `src/main/java/thedarkcolour/gendustry/recipe/cache/package-info.java`
- `src/main/java/thedarkcolour/gendustry/recipe/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
