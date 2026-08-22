# ForestryMC-ForestryMC — cultivation

- Alias: `forestry12`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC`
- Package/path root: `src/main/java/forestry/cultivation`
- Java files scanned: **27**
- Date: 2026-07-30

## Summary
Module `cultivation` in `ForestryMC-ForestryMC` is rooted at `src/main/java/forestry/cultivation` (27 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModuleCultivation`
- `BlockPlanter`
- `BlockRegistryCultivation`
- `BlockTypePlanter`
- `PlanterProperties`
- `PlanterStateMapper`
- `package-info`
- `ContainerPlanter`
- `GuiPlanter`
- `package-info`
- `GhostItemStackWidget`
- `package-info`
- `InventoryPlanter`
- `package-info`
- `ItemBlockPlanter`
- `package-info`
- `package-info`
- `TileArboretum`
- `TileBog`
- `TileFarmCrops`
- `TileFarmEnder`
- `TileFarmGourd`
- `TileFarmMushroom`
- `TileFarmNether`
- `TilePlantation`
- `TilePlanter`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py forestry12 "cultivation"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
  - `gui`
  - `gui/widgets`
  - `inventory`
  - `items`
  - `tiles`
- Declaration skim (first files):
  - `src/main/java/forestry/cultivation/ModuleCultivation.java`
    - L1: package forestry.cultivation;
    - L24: @ForestryModule(containerID = Constants.MOD_ID, moduleID = ForestryModuleUids.CULTIVATION, name = "Cultivation", author = "Nedelosk", url = Constants.URL, unlocalizedDescription = "for.module.cultivation.description")
    - L25: public class ModuleCultivation extends BlankForestryModule {
    - L26: @Nullable
    - L29: public static BlockRegistryCultivation getBlocks() {
    - L34: @Override
    - L35: public void registerItemsAndBlocks() {
    - L39: @Override
    - L40: public Set<ResourceLocation> getDependencyUids() {
    - L45: @Override
    - L46: public void doInit() {
    - L59: @Override
  - `src/main/java/forestry/cultivation/blocks/BlockPlanter.java`
    - L1: package forestry.cultivation.blocks;
    - L29: public class BlockPlanter extends BlockBase<BlockTypePlanter> {
    - L30: public static final PropertyBool MANUAL = PropertyBool.create("manual");
    - L32: public BlockPlanter(BlockTypePlanter blockType) {
    - L37: @Override
    - L38: protected BlockStateContainer createBlockState() {
    - L42: @SideOnly(Side.CLIENT)
    - L43: @Override
    - L44: public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    - L52: @Override
    - L53: public IBlockState getStateFromMeta(int meta) {
    - L58: @Override
  - `src/main/java/forestry/cultivation/blocks/BlockRegistryCultivation.java`
    - L1: package forestry.cultivation.blocks;
    - L9: public class BlockRegistryCultivation extends BlockRegistry {
    - L12: public final BlockPlanter arboretum;
    - L13: public final BlockPlanter farmCrops;
    - L14: public final BlockPlanter farmMushroom;
    - L15: public final BlockPlanter farmGourd;
    - L16: public final BlockPlanter farmNether;
    - L17: public final BlockPlanter farmEnder;
    - L18: public final BlockPlanter peatBog;
    - L21: public BlockRegistryCultivation() {
    - L53: public Set<BlockPlanter> getPlanters() {
  - `src/main/java/forestry/cultivation/blocks/BlockTypePlanter.java`
    - L1: package forestry.cultivation.blocks;
    - L14: public enum BlockTypePlanter implements IBlockTypeCustom {
    - L32: @Override
    - L33: public IMachineProperties getMachineProperties() {
    - L37: @Override
    - L38: public String getName() {
  - `src/main/java/forestry/cultivation/blocks/PlanterProperties.java`
    - L1: package forestry.cultivation.blocks;
    - L12: public class PlanterProperties<T extends TilePlanter> extends MachineProperties<T> {
    - L18: @Override
    - L19: public void registerModel(Item item, IModelManager manager) {
  - `src/main/java/forestry/cultivation/blocks/PlanterStateMapper.java`
    - L1: package forestry.cultivation.blocks;
    - L21: @SideOnly(Side.CLIENT)
    - L22: public class PlanterStateMapper extends ForestryStateMapper {
    - L24: @Override
    - L25: public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block block) {
  - `src/main/java/forestry/cultivation/blocks/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.cultivation.blocks;
  - `src/main/java/forestry/cultivation/gui/ContainerPlanter.java`
    - L1: package forestry.cultivation.gui;
    - L13: public class ContainerPlanter extends ContainerLiquidTanks<TilePlanter> {
    - L14: public ContainerPlanter(TilePlanter tileForestry, InventoryPlayer playerInventory) {
    - L44: @Override
    - L45: public void detectAndSendChanges() {
  - `src/main/java/forestry/cultivation/gui/GuiPlanter.java`
    - L1: package forestry.cultivation.gui;
    - L16: public class GuiPlanter extends GuiForestryTitled<ContainerPlanter> {
    - L19: public GuiPlanter(TilePlanter tile, InventoryPlayer playerInventory) {
    - L64: @Override
    - L65: protected void addLedgers() {
    - L72: @Override
    - L73: protected void drawGuiContainerBackgroundLayer(float var1, int mouseX, int mouseY) {
  - `src/main/java/forestry/cultivation/gui/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.cultivation.gui;
  - `src/main/java/forestry/cultivation/gui/widgets/GhostItemStackWidget.java`
    - L1: package forestry.cultivation.gui.widgets;
    - L21: public class GhostItemStackWidget extends ItemStackWidget {
    - L24: public GhostItemStackWidget(WidgetManager widgetManager, int xPos, int yPos, ItemStack itemStack, Slot slot) {
    - L29: @Override
    - L30: public void draw(int startX, int startY) {
    - L65: @Nullable
    - L66: @Override
    - L67: public ToolTip getToolTip(int mouseX, int mouseY) {
  - `src/main/java/forestry/cultivation/gui/widgets/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.cultivation.gui.widgets;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModuleCultivation` (`ModuleCultivation.java`)
- key type `BlockRegistryCultivation` (`BlockRegistryCultivation.java`)
- enum `BlockTypePlanter` in `BlockTypePlanter.java`

## Port relevance to Re-Forestry
- Mentions of `cultivation` appear in `files/implemented-features.md` — check that file for port status.
- 1.12 Forestry — useful for CE-dropped content (greenhouse, book, climatology, database, etc.).

## Source map
- `src/main/java/forestry/cultivation/ModuleCultivation.java`
- `src/main/java/forestry/cultivation/blocks/BlockPlanter.java`
- `src/main/java/forestry/cultivation/blocks/BlockRegistryCultivation.java`
- `src/main/java/forestry/cultivation/blocks/BlockTypePlanter.java`
- `src/main/java/forestry/cultivation/blocks/PlanterProperties.java`
- `src/main/java/forestry/cultivation/blocks/PlanterStateMapper.java`
- `src/main/java/forestry/cultivation/blocks/package-info.java`
- `src/main/java/forestry/cultivation/gui/ContainerPlanter.java`
- `src/main/java/forestry/cultivation/gui/GuiPlanter.java`
- `src/main/java/forestry/cultivation/gui/package-info.java`
- `src/main/java/forestry/cultivation/gui/widgets/GhostItemStackWidget.java`
- `src/main/java/forestry/cultivation/gui/widgets/package-info.java`
- `src/main/java/forestry/cultivation/inventory/InventoryPlanter.java`
- `src/main/java/forestry/cultivation/inventory/package-info.java`
- `src/main/java/forestry/cultivation/items/ItemBlockPlanter.java`
- `src/main/java/forestry/cultivation/items/package-info.java`
- `src/main/java/forestry/cultivation/package-info.java`
- `src/main/java/forestry/cultivation/tiles/TileArboretum.java`
- `src/main/java/forestry/cultivation/tiles/TileBog.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmCrops.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmEnder.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmGourd.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmMushroom.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmNether.java`
- `src/main/java/forestry/cultivation/tiles/TilePlantation.java`
- `src/main/java/forestry/cultivation/tiles/TilePlanter.java`
- `src/main/java/forestry/cultivation/tiles/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
