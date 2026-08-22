# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — datagen

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/datagen`
- Java files scanned: **18**
- Date: 2026-07-30

## Summary
Module `datagen` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/datagen` (18 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModAdvancementsProvider`
- `ModBlockLootTableProvider`
- `ModBlockTagsProvider`
- `ModDamageTypes`
- `ModEquipmentAssetProvider`
- `ModItemTagsProvider`
- `ModJukeboxSongs`
- `ModModelProvider`
- `ModPaintingTagsProvider`
- `ModPaintings`
- `ModRecipeProvider`
- `ModRegistryDataProvider`
- `ModSoundsProvider`
- `CrystallizerRecipeBuilder`
- `ModPOITags`
- `ModTradeSets`
- `ModVillagerTradeTags`
- `ModVillagerTrades`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "datagen"`
- Source root exists: **True**
- Nested packages under this module:
  - `recipe`
  - `villager`
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModAdvancementsProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L28: public class ModAdvancementsProvider extends AdvancementProvider {
    - L29: public ModAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    - L33: public static class TutorialModAdvancements implements AdvancementSubProvider {
    - L34: @Override
    - L35: public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockLootTableProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L30: public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    - L31: public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    - L35: @Override
    - L36: public void generate() {
    - L90: public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockTagsProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L12: public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    - L13: public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
    - L17: @Override
    - L18: protected void addTags(HolderLookup.Provider registries) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModDamageTypes.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L13: public class ModDamageTypes {
    - L14: public static final ResourceKey<DamageType> STINKY = ResourceKey.create(Registries.DAMAGE_TYPE,
    - L17: public static void bootstrap(BootstrapContext<DamageType> context) {
    - L22: public static DamageSource create(Level level, ResourceKey<DamageType> key) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModEquipmentAssetProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L19: public class ModEquipmentAssetProvider implements DataProvider {
    - L22: public ModEquipmentAssetProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
    - L35: @Override
    - L36: public CompletableFuture<?> run(final CachedOutput cache) {
    - L46: @Override
    - L47: public String getName() {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModItemTagsProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L14: public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    - L15: public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
    - L19: @Override
    - L20: protected void addTags(HolderLookup.Provider registries) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModJukeboxSongs.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L15: public class ModJukeboxSongs {
    - L16: public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
    - L19: public static void bootstrap(BootstrapContext<JukeboxSong> context) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModModelProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L31: public class ModModelProvider extends FabricModelProvider {
    - L32: public ModModelProvider(FabricPackOutput output) {
    - L36: @Override
    - L37: public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
    - L76: @Override
    - L77: public void generateItemModels(ItemModelGenerators itemModelGenerators) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModPaintingTagsProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L13: public class ModPaintingTagsProvider extends FabricTagsProvider<PaintingVariant> {
    - L14: public ModPaintingTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
    - L18: @Override
    - L19: protected void addTags(HolderLookup.Provider registries) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModPaintings.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L14: public class ModPaintings {
    - L15: public static final ResourceKey<PaintingVariant> SAW_THEM_KEY = create("saw_them");
    - L16: public static final ResourceKey<PaintingVariant> SHRIMP_KEY = create("shrimp");
    - L17: public static final ResourceKey<PaintingVariant> WORLD_KEY = create("world");
    - L18: public static final ResourceKey<PaintingVariant> WANDERER_KEY = create("wanderer");
    - L21: public static void bootstrap(BootstrapContext<PaintingVariant> context) {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRecipeProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L21: public class ModRecipeProvider extends FabricRecipeProvider {
    - L22: public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    - L26: @Override
    - L27: protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
    - L29: @Override
    - L30: public void buildRecipes() {
  - `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRegistryDataProvider.java`
    - L1: package net.kaupenjoe.tutorialmod.datagen;
    - L10: public class ModRegistryDataProvider extends FabricDynamicRegistryProvider {
    - L11: public ModRegistryDataProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    - L15: @Override
    - L16: protected void configure(HolderLookup.Provider registries, Entries entries) {
    - L25: @Override
    - L26: public String getName() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModRegistryDataProvider` (`ModRegistryDataProvider.java`)

## Port relevance to Re-Forestry
- Mentions of `datagen` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModAdvancementsProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockLootTableProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockTagsProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModDamageTypes.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModEquipmentAssetProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModItemTagsProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModJukeboxSongs.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModModelProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModPaintingTagsProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModPaintings.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRecipeProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRegistryDataProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/ModSoundsProvider.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/recipe/CrystallizerRecipeBuilder.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModPOITags.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModTradeSets.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModVillagerTradeTags.java`
- `src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModVillagerTrades.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
