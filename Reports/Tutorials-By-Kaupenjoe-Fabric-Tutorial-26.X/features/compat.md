# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — compat

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/compat`
- Java files scanned: **4**
- Date: 2026-07-30

## Summary
Module `compat` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/compat` (4 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `TutorialModREIClient`
- `TutorialModREICommon`
- `CrystallizerCategory`
- `CrystallizerDisplay`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "compat"`
- Source root exists: **True**
- Nested packages under this module:
  - `custom`
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/compat/TutorialModREIClient.java`
    - L1: package net.kaupenjoe.tutorialmod.compat;
    - L12: public class TutorialModREIClient implements REIClientPlugin {
    - L13: @Override
    - L14: public void registerCategories(CategoryRegistry registry) {
    - L20: @Override
    - L21: public void registerScreens(ScreenRegistry registry) {
  - `src/main/java/net/kaupenjoe/tutorialmod/compat/TutorialModREICommon.java`
    - L1: package net.kaupenjoe.tutorialmod.compat;
    - L11: public class TutorialModREICommon implements REICommonPlugin {
    - L12: public static final CategoryIdentifier<CrystallizerDisplay> CRYSTALLIZER =
    - L15: @Override
    - L16: public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
    - L20: @Override
    - L21: public void registerDisplays(ServerDisplayRegistry registry) {
  - `src/main/java/net/kaupenjoe/tutorialmod/compat/custom/CrystallizerCategory.java`
    - L1: package net.kaupenjoe.tutorialmod.compat.custom;
    - L21: public class CrystallizerCategory implements DisplayCategory<Display> {
    - L22: public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,
    - L25: @Override
    - L26: public CategoryIdentifier<? extends Display> getCategoryIdentifier() {
    - L30: @Override
    - L31: public Component getTitle() {
    - L35: @Override
    - L36: public Renderer getIcon() {
    - L40: @Override
    - L41: public List<Widget> setupDisplay(Display display, Rectangle bounds) {
    - L57: @Override
  - `src/main/java/net/kaupenjoe/tutorialmod/compat/custom/CrystallizerDisplay.java`
    - L1: package net.kaupenjoe.tutorialmod.compat.custom;
    - L20: public record CrystallizerDisplay(EntryIngredient input, EntryIngredient output,
    - L22: public static final DisplaySerializer<CrystallizerDisplay> SERIALIZER = DisplaySerializer.of(
    - L40: public CrystallizerDisplay(RecipeHolder<CrystallizerRecipe> entry) {
    - L44: public CrystallizerDisplay(Identifier id, CrystallizerRecipe recipe) {
    - L48: @Override
    - L49: public List<EntryIngredient> getInputEntries() {
    - L53: @Override
    - L54: public List<EntryIngredient> getOutputEntries() {
    - L58: @Override
    - L59: public CategoryIdentifier<?> getCategoryIdentifier() {
    - L63: @Override

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `CrystallizerDisplay` in `CrystallizerDisplay.java`

## Port relevance to Re-Forestry
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/compat/TutorialModREIClient.java`
- `src/main/java/net/kaupenjoe/tutorialmod/compat/TutorialModREICommon.java`
- `src/main/java/net/kaupenjoe/tutorialmod/compat/custom/CrystallizerCategory.java`
- `src/main/java/net/kaupenjoe/tutorialmod/compat/custom/CrystallizerDisplay.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
