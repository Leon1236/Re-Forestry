# ForestryMC-ForestryMC — food

- Alias: `forestry12`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC`
- Package/path root: `src/main/java/forestry/food`
- Java files scanned: **5**
- Date: 2026-07-30

## Summary
Module `food` in `ForestryMC-ForestryMC` is rooted at `src/main/java/forestry/food` (5 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModuleFood`
- `ItemAmbrosia`
- `ItemRegistryFood`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py forestry12 "food"`
- Source root exists: **True**
- Nested packages under this module:
  - `items`
- Declaration skim (first files):
  - `src/main/java/forestry/food/ModuleFood.java`
    - L11: package forestry.food;
    - L23: @ForestryModule(containerID = Constants.MOD_ID, moduleID = ForestryModuleUids.FOOD, name = "Food", author = "SirSengir", url = Constants.URL, unlocalizedDescription = "for.module.food.description")
    - L24: public class ModuleFood extends BlankForestryModule {
    - L25: @Nullable
    - L28: public static ItemRegistryFood getItems() {
    - L33: @Override
    - L34: public void registerItemsAndBlocks() {
  - `src/main/java/forestry/food/items/ItemAmbrosia.java`
    - L11: package forestry.food.items;
    - L20: public class ItemAmbrosia extends ItemForestryFood {
    - L22: public ItemAmbrosia() {
    - L29: @Override
    - L30: public boolean hasEffect(ItemStack itemstack) {
  - `src/main/java/forestry/food/items/ItemRegistryFood.java`
    - L11: package forestry.food.items;
    - L16: public class ItemRegistryFood extends ItemRegistry {
    - L17: public final ItemForestryFood honeyedSlice;
    - L18: public final ItemForestryFood ambrosia;
    - L19: public final ItemForestryFood honeyPot;
    - L21: public ItemRegistryFood() {
  - `src/main/java/forestry/food/items/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.food.items;
  - `src/main/java/forestry/food/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.food;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModuleFood` (`ModuleFood.java`)
- key type `ItemRegistryFood` (`ItemRegistryFood.java`)

## Port relevance to Re-Forestry
- Mentions of `food` appear in `files/implemented-features.md` — check that file for port status.
- 1.12 Forestry — useful for CE-dropped content (greenhouse, book, climatology, database, etc.).

## Source map
- `src/main/java/forestry/food/ModuleFood.java`
- `src/main/java/forestry/food/items/ItemAmbrosia.java`
- `src/main/java/forestry/food/items/ItemRegistryFood.java`
- `src/main/java/forestry/food/items/package-info.java`
- `src/main/java/forestry/food/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
