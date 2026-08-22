# thedarkcolour-Immersive-Forestry — cultivation

- Alias: `IF`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-Immersive-Forestry`
- Package/path root: `src/main/java/forestry/cultivation`
- Java files scanned: **30**
- Date: 2026-07-30

## Summary
Module `cultivation` in `thedarkcolour-Immersive-Forestry` is rooted at `src/main/java/forestry/cultivation` (30 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `IFarmHousingInternal`
- `ModuleCultivation`
- `BlockPlanter`
- `BlockTypePlanter`
- `package-info`
- `CultivationBlocks`
- `CultivationMenuTypes`
- `CultivationTiles`
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
- `CultivationClientHandler`
- `package-info`
- `TileArboretum`
- `TileBog`
- `TileFarmCrops`
- `TileFarmEnder`
- `TileFarmGourd`
- `TileFarmMushroom`
- `TileFarmNether`
- `TilePlanter`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py IF "cultivation"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
  - `features`
  - `gui`
  - `gui/widgets`
  - `inventory`
  - `items`
  - `proxy`
  - `tiles`
- Declaration skim (first files):
  - `src/main/java/forestry/cultivation/IFarmHousingInternal.java`
    - L1: package forestry.cultivation;
    - L15: public interface IFarmHousingInternal extends IFarmHousing, ILiquidTankTile, IClimateProvider {
    - L16: @Override
    - L19: @Override
  - `src/main/java/forestry/cultivation/ModuleCultivation.java`
    - L1: package forestry.cultivation;
    - L19: @ForestryModule
    - L20: public class ModuleCultivation extends BlankForestryModule {
    - L37: @Override
    - L38: public ResourceLocation getId() {
    - L42: @Override
    - L43: public void registerEvents(IEventBus modBus) {
    - L47: @Override
    - L48: public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
    - L52: @Override
    - L53: public List<ResourceLocation> getModuleDependencies() {
  - `src/main/java/forestry/cultivation/blocks/BlockPlanter.java`
    - L1: package forestry.cultivation.blocks;
    - L12: public class BlockPlanter extends BlockBase<BlockTypePlanter> {
    - L15: public BlockPlanter(BlockTypePlanter type, boolean manual) {
    - L20: public boolean isManual() {
    - L24: @Override
    - L25: public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
    - L33: @Override
    - L34: public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
  - `src/main/java/forestry/cultivation/blocks/BlockTypePlanter.java`
    - L1: package forestry.cultivation.blocks;
    - L12: public enum BlockTypePlanter implements IBlockType {
    - L21: public static final List<BlockTypePlanter> VALUES = List.of(values());
    - L31: @Override
    - L32: public IMachineProperties<?> getMachineProperties() {
    - L36: @Override
    - L37: public String getSerializedName() {
  - `src/main/java/forestry/cultivation/blocks/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.cultivation.blocks;
  - `src/main/java/forestry/cultivation/features/CultivationBlocks.java`
    - L1: package forestry.cultivation.features;
    - L9: @FeatureProvider
    - L10: public class CultivationBlocks {
    - L13: public static final FeatureBlockGroup<BlockPlanter, BlockTypePlanter> MANAGED_PLANTER = REGISTRY.blockGroup(type -> new BlockPlanter(type, false), BlockTypePlanter.VALUES).item(ItemBlockPlanter::new).identifier("managed"
    - L14: public static final FeatureBlockGroup<BlockPlanter, BlockTypePlanter> MANUAL_PLANTER = REGISTRY.blockGroup(type -> new BlockPlanter(type, true), BlockTypePlanter.VALUES).item(ItemBlockPlanter::new).identifier("manual", F
  - `src/main/java/forestry/cultivation/features/CultivationMenuTypes.java`
    - L1: package forestry.cultivation.features;
    - L10: @FeatureProvider
    - L11: public class CultivationMenuTypes {
    - L14: public static final FeatureMenuType<ContainerPlanter> PLANTER = REGISTRY.menuType(ContainerPlanter::fromNetwork, "planter");
  - `src/main/java/forestry/cultivation/features/CultivationTiles.java`
    - L1: package forestry.cultivation.features;
    - L16: @FeatureProvider
    - L17: public class CultivationTiles {
    - L20: public static final FeatureTileType<TileArboretum> ARBORETUM = createTile("arboretum", () -> BlockTypePlanter.ARBORETUM, TileArboretum::new);
    - L21: public static final FeatureTileType<TileBog> BOG = createTile("bog", () -> BlockTypePlanter.PEAT_POG, TileBog::new);
    - L22: public static final FeatureTileType<TileFarmCrops> CROPS = createTile("crops", () -> BlockTypePlanter.FARM_CROPS, TileFarmCrops::new);
    - L23: public static final FeatureTileType<TileFarmEnder> ENDER = createTile("ender", () -> BlockTypePlanter.FARM_ENDER, TileFarmEnder::new);
    - L24: public static final FeatureTileType<TileFarmGourd> GOURD = createTile("gourd", () -> BlockTypePlanter.FARM_GOURD, TileFarmGourd::new);
    - L25: public static final FeatureTileType<TileFarmMushroom> MUSHROOM = createTile("mushroom", () -> BlockTypePlanter.FARM_MUSHROOM, TileFarmMushroom::new);
    - L26: public static final FeatureTileType<TileFarmNether> NETHER = createTile("nether", () -> BlockTypePlanter.FARM_NETHER, TileFarmNether::new);
  - `src/main/java/forestry/cultivation/features/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.cultivation.features;
  - `src/main/java/forestry/cultivation/gui/ContainerPlanter.java`
    - L1: package forestry.cultivation.gui;
    - L15: public class ContainerPlanter extends ContainerLiquidTanks<TilePlanter> {
    - L16: public static ContainerPlanter fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
    - L21: public ContainerPlanter(int windowId, Inventory playerInventory, TilePlanter tileForestry) {
    - L51: @Override
    - L52: public void broadcastChanges() {
  - `src/main/java/forestry/cultivation/gui/GuiPlanter.java`
    - L1: package forestry.cultivation.gui;
    - L18: public class GuiPlanter extends GuiForestryTitled<ContainerPlanter> {
    - L21: public GuiPlanter(ContainerPlanter container, Inventory playerInventory, Component title) {
    - L66: @Override
    - L67: protected void addLedgers() {
    - L75: @Override
    - L76: protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseY, int mouseX) {
  - `src/main/java/forestry/cultivation/gui/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.cultivation.gui;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-Immersive-Forestry` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `IFarmHousingInternal` in `IFarmHousingInternal.java`
- key type `ModuleCultivation` (`ModuleCultivation.java`)
- enum `BlockTypePlanter` in `BlockTypePlanter.java`
- key type `CultivationClientHandler` (`CultivationClientHandler.java`)

## Port relevance to Re-Forestry
- Mentions of `cultivation` appear in `files/implemented-features.md` — check that file for port status.
- Primary Forestry reference for Re-Forestry port decisions.

## Source map
- `src/main/java/forestry/cultivation/IFarmHousingInternal.java`
- `src/main/java/forestry/cultivation/ModuleCultivation.java`
- `src/main/java/forestry/cultivation/blocks/BlockPlanter.java`
- `src/main/java/forestry/cultivation/blocks/BlockTypePlanter.java`
- `src/main/java/forestry/cultivation/blocks/package-info.java`
- `src/main/java/forestry/cultivation/features/CultivationBlocks.java`
- `src/main/java/forestry/cultivation/features/CultivationMenuTypes.java`
- `src/main/java/forestry/cultivation/features/CultivationTiles.java`
- `src/main/java/forestry/cultivation/features/package-info.java`
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
- `src/main/java/forestry/cultivation/proxy/CultivationClientHandler.java`
- `src/main/java/forestry/cultivation/proxy/package-info.java`
- `src/main/java/forestry/cultivation/tiles/TileArboretum.java`
- `src/main/java/forestry/cultivation/tiles/TileBog.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmCrops.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmEnder.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmGourd.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmMushroom.java`
- `src/main/java/forestry/cultivation/tiles/TileFarmNether.java`
- `src/main/java/forestry/cultivation/tiles/TilePlanter.java`
- `src/main/java/forestry/cultivation/tiles/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
