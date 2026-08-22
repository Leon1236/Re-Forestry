# Tiviacz1337-Travelers-Backpack — datagen

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/datagen`
- Java files scanned: **3**
- Date: 2026-07-30

## Summary
Module `datagen` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/datagen` (3 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModBlockLootTables`
- `ModDataGeneration`
- `ModRecipeProvider`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "datagen"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/datagen/ModBlockLootTables.java`
    - L1: package com.tiviacz.travelersbackpack.datagen;
    - L24: public class ModBlockLootTables extends FabricBlockLootSubProvider {
    - L25: protected ModBlockLootTables(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> holderLookupProvidr) {
    - L29: @Override
    - L30: public void generate() {
    - L53: protected LootTable.Builder createBackpackDrop(Block block) {
    - L78: protected LootTable.Builder createSleepingBagDrop(Block block) {
  - `src/main/java/com/tiviacz/travelersbackpack/datagen/ModDataGeneration.java`
    - L1: package com.tiviacz.travelersbackpack.datagen;
    - L6: public class ModDataGeneration implements DataGeneratorEntrypoint {
    - L7: @Override
    - L8: public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
  - `src/main/java/com/tiviacz/travelersbackpack/datagen/ModRecipeProvider.java`
    - L1: package com.tiviacz.travelersbackpack.datagen;
    - L31: public class ModRecipeProvider extends FabricRecipeProvider {
    - L32: public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> holderProvider) {
    - L36: protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput writer) {
    - L38: @Override
    - L39: public void buildRecipes() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `datagen` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/datagen/ModBlockLootTables.java`
- `src/main/java/com/tiviacz/travelersbackpack/datagen/ModDataGeneration.java`
- `src/main/java/com/tiviacz/travelersbackpack/datagen/ModRecipeProvider.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
