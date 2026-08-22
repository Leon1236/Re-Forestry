# ForestryMC-ForestryMC — database

- Alias: `forestry12`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC`
- Package/path root: `src/main/java/forestry/database`
- Java files scanned: **28**
- Date: 2026-07-30

## Summary
Module `database` in `ForestryMC-ForestryMC` is rooted at `src/main/java/forestry/database` (28 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DatabaseFilter`
- `DatabaseFilterName`
- `DatabaseFilterToolTip`
- `DatabaseHelper`
- `DatabaseItem`
- `ModuleDatabase`
- `BlockDatabase`
- `BlockRegistryDatabase`
- `BlockTypeDatabase`
- `package-info`
- `ContainerDatabase`
- `GuiDatabase`
- `DatabaseButton`
- `GuiDatabaseButton`
- `package-info`
- `package-info`
- `WidgetDatabaseSlot`
- `package-info`
- `InventoryDatabase`
- `InventoryDatabaseAnalyzer`
- `PacketRegistryDatabase`
- `package-info`
- `PacketExtractItem`
- `PacketInsertItem`
- `package-info`
- `package-info`
- `TileDatabase`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py forestry12 "database"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
  - `gui`
  - `gui/buttons`
  - `gui/widgets`
  - `inventory`
  - `network`
  - `network/packets`
  - `tiles`
- Declaration skim (first files):
  - `src/main/java/forestry/database/DatabaseFilter.java`
    - L1: package forestry.database;
    - L8: public abstract class DatabaseFilter implements Predicate<ItemStack> {
    - L9: protected final Pattern pattern;
    - L11: protected DatabaseFilter(Pattern pattern) {
  - `src/main/java/forestry/database/DatabaseFilterName.java`
    - L1: package forestry.database;
    - L8: public class DatabaseFilterName extends DatabaseFilter {
    - L9: public DatabaseFilterName(Pattern pattern) {
    - L13: @Override
    - L14: public boolean test(ItemStack itemStack) {
  - `src/main/java/forestry/database/DatabaseFilterToolTip.java`
    - L1: package forestry.database;
    - L10: public class DatabaseFilterToolTip extends DatabaseFilter {
    - L11: public DatabaseFilterToolTip(Pattern pattern) {
    - L15: @Override
    - L16: public boolean test(ItemStack itemStack) {
  - `src/main/java/forestry/database/DatabaseHelper.java`
    - L1: package forestry.database;
    - L13: public class DatabaseHelper {
    - L14: public static boolean ascending;
    - L16: public static final Comparator<DatabaseItem> SORT_BY_NAME = (DatabaseItem firstStack, DatabaseItem secondStack) -> {
    - L28: public static String getItemName(ItemStack itemStack) {
    - L45: public static void update(String searchText, List<DatabaseItem> items, ArrayList<DatabaseItem> sorted) {
  - `src/main/java/forestry/database/DatabaseItem.java`
    - L1: package forestry.database;
    - L5: public class DatabaseItem {
    - L6: public final ItemStack itemStack;
    - L7: public final int invIndex;
    - L9: public DatabaseItem(ItemStack itemStack, int invIndex) {
    - L14: @Override
    - L15: public boolean equals(Object obj) {
  - `src/main/java/forestry/database/ModuleDatabase.java`
    - L1: package forestry.database;
    - L28: @ForestryModule(containerID = Constants.MOD_ID, moduleID = ForestryModuleUids.DATABASE, name = "Database", author = "Nedelosk", url = Constants.URL, unlocalizedDescription = "for.module.database.description")
    - L29: public class ModuleDatabase extends BlankForestryModule {
    - L30: @Nullable
    - L33: public static BlockRegistryDatabase getBlocks() {
    - L38: @Override
    - L39: public void registerItemsAndBlocks() {
    - L43: @Override
    - L44: public void doInit() {
    - L50: @Override
    - L51: public void registerRecipes() {
    - L105: @Override
  - `src/main/java/forestry/database/blocks/BlockDatabase.java`
    - L1: package forestry.database.blocks;
    - L5: public class BlockDatabase extends BlockBase<BlockTypeDatabase> {
    - L7: public BlockDatabase(BlockTypeDatabase blockType) {
  - `src/main/java/forestry/database/blocks/BlockRegistryDatabase.java`
    - L1: package forestry.database.blocks;
    - L6: public class BlockRegistryDatabase extends BlockRegistry {
    - L8: public final BlockDatabase database;
    - L10: public BlockRegistryDatabase() {
  - `src/main/java/forestry/database/blocks/BlockTypeDatabase.java`
    - L1: package forestry.database.blocks;
    - L9: public enum BlockTypeDatabase implements IBlockTypeCustom {
    - L11: public static final BlockTypeDatabase[] VALUES = values();
    - L19: @Override
    - L20: public IMachineProperties getMachineProperties() {
    - L24: @Override
    - L25: public String getName() {
  - `src/main/java/forestry/database/blocks/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.database.blocks;
  - `src/main/java/forestry/database/gui/ContainerDatabase.java`
    - L1: package forestry.database.gui;
    - L14: public class ContainerDatabase extends ContainerAnalyzerProvider<TileDatabase> {
    - L16: public ContainerDatabase(TileDatabase tileForestry, InventoryPlayer playerInventory) {
    - L29: public void sendContainerToListeners() {
    - L35: public IItemHandler getItemHandler() {
  - `src/main/java/forestry/database/gui/GuiDatabase.java`
    - L1: package forestry.database.gui;
    - L32: public class GuiDatabase extends GuiAnalyzerProvider<ContainerDatabase> implements IScrollable {
    - L37: public final TileDatabase tile;
    - L41: @Nullable
    - L46: @Nullable
    - L50: public GuiDatabase(TileDatabase tile, EntityPlayer player) {
    - L79: @Nullable
    - L80: public DatabaseItem getSelectedItem() {
    - L87: public int getSize() {
    - L91: public int getRealSize() {
    - L95: public ItemStack getSelectedItemStack() {
    - L102: public void markForSorting() {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/assets/forestry/blockstates/database.json`
- `src/main/resources/assets/forestry/textures/blocks/database_side_column.png`
- `src/main/resources/assets/forestry/textures/blocks/database_top.png`
- `src/main/resources/assets/forestry/textures/blocks/database_side.png`
- `src/main/resources/assets/forestry/textures/blocks/database_bottom.png`
- `src/main/resources/assets/forestry/textures/gui/database_mutation_screen.png`
- `src/main/resources/assets/forestry/textures/gui/database_inventory.png`
- `src/main/resources/assets/forestry/models/item/database.json`
- `src/main/resources/assets/forestry/models/block/database.json`
- `src/main/resources/assets/forestry/manual/en_us/genetics/database.json`
- `src/main/resources/assets/forestry/manual/zh_tw/genetics/database.json`
- `src/main/resources/assets/forestry/manual/zh_cn/genetics/database.json`
- `src/main/resources/assets/forestry/manual/ru_ru/genetics/database.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModuleDatabase` (`ModuleDatabase.java`)
- key type `BlockRegistryDatabase` (`BlockRegistryDatabase.java`)
- enum `BlockTypeDatabase` in `BlockTypeDatabase.java`
- enum `DatabaseButton` in `DatabaseButton.java`
- key type `PacketRegistryDatabase` (`PacketRegistryDatabase.java`)
- key type `PacketExtractItem` (`PacketExtractItem.java`)
- key type `PacketInsertItem` (`PacketInsertItem.java`)

## Port relevance to Re-Forestry
- Mentions of `database` appear in `files/implemented-features.md` — check that file for port status.
- 1.12 Forestry — useful for CE-dropped content (greenhouse, book, climatology, database, etc.).

## Source map
- `src/main/java/forestry/database/DatabaseFilter.java`
- `src/main/java/forestry/database/DatabaseFilterName.java`
- `src/main/java/forestry/database/DatabaseFilterToolTip.java`
- `src/main/java/forestry/database/DatabaseHelper.java`
- `src/main/java/forestry/database/DatabaseItem.java`
- `src/main/java/forestry/database/ModuleDatabase.java`
- `src/main/java/forestry/database/blocks/BlockDatabase.java`
- `src/main/java/forestry/database/blocks/BlockRegistryDatabase.java`
- `src/main/java/forestry/database/blocks/BlockTypeDatabase.java`
- `src/main/java/forestry/database/blocks/package-info.java`
- `src/main/java/forestry/database/gui/ContainerDatabase.java`
- `src/main/java/forestry/database/gui/GuiDatabase.java`
- `src/main/java/forestry/database/gui/buttons/DatabaseButton.java`
- `src/main/java/forestry/database/gui/buttons/GuiDatabaseButton.java`
- `src/main/java/forestry/database/gui/buttons/package-info.java`
- `src/main/java/forestry/database/gui/package-info.java`
- `src/main/java/forestry/database/gui/widgets/WidgetDatabaseSlot.java`
- `src/main/java/forestry/database/gui/widgets/package-info.java`
- `src/main/java/forestry/database/inventory/InventoryDatabase.java`
- `src/main/java/forestry/database/inventory/InventoryDatabaseAnalyzer.java`
- `src/main/java/forestry/database/network/PacketRegistryDatabase.java`
- `src/main/java/forestry/database/network/package-info.java`
- `src/main/java/forestry/database/network/packets/PacketExtractItem.java`
- `src/main/java/forestry/database/network/packets/PacketInsertItem.java`
- `src/main/java/forestry/database/network/packets/package-info.java`
- `src/main/java/forestry/database/package-info.java`
- `src/main/java/forestry/database/tiles/TileDatabase.java`
- `src/main/java/forestry/database/tiles/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
