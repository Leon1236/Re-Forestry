# thedarkcolour-Immersive-Forestry — energy

- Alias: `IF`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-Immersive-Forestry`
- Package/path root: `src/main/java/forestry/energy`
- Java files scanned: **30**
- Date: 2026-07-30

## Summary
Module `energy` in `thedarkcolour-Immersive-Forestry` is rooted at `src/main/java/forestry/energy` (30 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `EnergyHelper`
- `EnergyTransferMode`
- `ForestryEnergyStorage`
- `ModuleEnergy`
- `EngineBlock`
- `EngineBlockType`
- `package-info`
- `EnergyClientHandler`
- `package-info`
- `EnergyBlocks`
- `EnergyMenus`
- `EnergyTiles`
- `package-info`
- `InventoryEngineBiogas`
- `InventoryEnginePeat`
- `package-info`
- `BiogasEngineMenu`
- `PeatEngineMenu`
- `package-info`
- `package-info`
- `BiogasEngineScreen`
- `BiogasSlot`
- `EngineScreen`
- `PeatEngineScreen`
- `package-info`
- `BiogasEngineBlockEntity`
- `ClockworkEngineBlockEntity`
- `EngineBlockEntity`
- `PeatEngineBlockEntity`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py IF "energy"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
  - `client`
  - `features`
  - `inventory`
  - `menu`
  - `render`
  - `screen`
  - `tiles`
- Declaration skim (first files):
  - `src/main/java/forestry/energy/EnergyHelper.java`
    - L1: package forestry.energy;
    - L12: public class EnergyHelper {
    - L13: public static int scaleForDifficulty(int energyValue) {
    - L22: public static boolean consumeEnergyToDoWork(ForestryEnergyStorage energyStorage, int ticksPerWorkCycle, int energyPerWorkCycle) {
    - L36: public static int sendEnergy(ForestryEnergyStorage energyStorage, Direction orientation, @Nullable BlockEntity tile) {
    - L40: public static int sendEnergy(ForestryEnergyStorage energyStorage, Direction face, @Nullable BlockEntity tile, int amount, boolean simulate) {
    - L68: public static boolean canSendEnergy(ForestryEnergyStorage energyStorage, Direction orientation, BlockEntity tile) {
    - L72: public static boolean isEnergyReceiverOrEngine(Direction side, @Nullable BlockEntity tile) {
  - `src/main/java/forestry/energy/EnergyTransferMode.java`
    - L1: package forestry.energy;
    - L3: public enum EnergyTransferMode {
    - L6: public boolean canExtract() {
    - L10: public boolean canReceive() {
  - `src/main/java/forestry/energy/ForestryEnergyStorage.java`
    - L1: package forestry.energy;
    - L13: public class ForestryEnergyStorage extends EnergyStorage implements IStreamable, INbtReadable, INbtWritable {
    - L14: public ForestryEnergyStorage(int maxTransfer, int capacity) {
    - L18: public ForestryEnergyStorage(int maxTransfer, int capacity, EnergyTransferMode mode) {
    - L26: @Override
    - L27: public void read(CompoundTag nbt, HolderLookup.Provider registries) {
    - L31: @Override
    - L32: public CompoundTag write(CompoundTag nbt, HolderLookup.Provider registries) {
    - L37: @Override
    - L38: public void writeData(RegistryFriendlyByteBuf data) {
    - L42: public void writeData(FriendlyByteBuf data) {
    - L46: @Override
  - `src/main/java/forestry/energy/ModuleEnergy.java`
    - L1: package forestry.energy;
    - L28: @ForestryModule
    - L29: public class ModuleEnergy extends BlankForestryModule {
    - L37: @Override
    - L38: public ResourceLocation getId() {
    - L42: @Override
    - L43: public void registerEvents(IEventBus modBus) {
    - L47: @Override
    - L48: public void setupApi() {
    - L84: @Override
    - L85: public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
  - `src/main/java/forestry/energy/blocks/EngineBlock.java`
    - L1: package forestry.energy.blocks;
    - L26: public class EngineBlock extends BlockBase<EngineBlockType> {
    - L29: public static final EnumProperty<Direction> VERTICAL_FACING = EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN, Direction.UP);
    - L40: public EngineBlock(EngineBlockType blockType) {
    - L46: @Override
    - L47: protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    - L51: @Override
    - L52: public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
    - L57: @Override
    - L58: public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation rot) {
    - L79: @Nullable
    - L80: @Override
  - `src/main/java/forestry/energy/blocks/EngineBlockType.java`
    - L1: package forestry.energy.blocks;
    - L10: public enum EngineBlockType implements IBlockType {
    - L15: public static final EngineBlockType[] VALUES = values();
    - L30: @Override
    - L31: public IMachineProperties<?> getMachineProperties() {
    - L35: @Override
    - L36: public String getSerializedName() {
  - `src/main/java/forestry/energy/blocks/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.energy.blocks;
  - `src/main/java/forestry/energy/client/EnergyClientHandler.java`
    - L1: package forestry.energy.client;
    - L9: public class EnergyClientHandler implements forestry.api.client.IClientModuleHandler {
    - L10: @Override
    - L11: public void registerEvents(IEventBus modBus) {
  - `src/main/java/forestry/energy/client/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.energy.client;
  - `src/main/java/forestry/energy/features/EnergyBlocks.java`
    - L1: package forestry.energy.features;
    - L14: @FeatureProvider
    - L15: public class EnergyBlocks {
    - L18: public static final FeatureBlockGroup<EngineBlock, EngineBlockType> ENGINES = REGISTRY.blockGroup(EngineBlock::new, List.of(EngineBlockType.VALUES)).item(ItemBlockTesr::new).identifier("engine").create();
  - `src/main/java/forestry/energy/features/EnergyMenus.java`
    - L1: package forestry.energy.features;
    - L11: @FeatureProvider
    - L12: public class EnergyMenus {
    - L15: public static final FeatureMenuType<BiogasEngineMenu> ENGINE_BIOGAS = REGISTRY.menuType(BiogasEngineMenu::fromNetwork, "engine_biogas");
    - L16: public static final FeatureMenuType<PeatEngineMenu> ENGINE_PEAT = REGISTRY.menuType(PeatEngineMenu::fromNetwork, "engine_peat");
  - `src/main/java/forestry/energy/features/EnergyTiles.java`
    - L1: package forestry.energy.features;
    - L13: @FeatureProvider
    - L14: public class EnergyTiles {
    - L17: public static final FeatureTileType<BiogasEngineBlockEntity> BIOGAS_ENGINE = REGISTRY.tile(BiogasEngineBlockEntity::new, "biogas_engine", () -> EnergyBlocks.ENGINES.get(EngineBlockType.BIOGAS).collect());
    - L18: public static final FeatureTileType<ClockworkEngineBlockEntity> CLOCKWORK_ENGINE = REGISTRY.tile(ClockworkEngineBlockEntity::new, "clockwork_engine", () -> EnergyBlocks.ENGINES.get(EngineBlockType.CLOCKWORK).collect());
    - L19: public static final FeatureTileType<PeatEngineBlockEntity> PEAT_ENGINE = REGISTRY.tile(PeatEngineBlockEntity::new, "peat_engine", () -> EnergyBlocks.ENGINES.get(EngineBlockType.PEAT).collect());

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/assets/forestry/textures/forestry/atlas/gui/errors/no_energy_net.png`
- `src/main/resources/assets/forestry/textures/forestry/atlas/gui/misc/energy.png`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-Immersive-Forestry` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- enum `EnergyTransferMode` in `EnergyTransferMode.java`
- key type `ModuleEnergy` (`ModuleEnergy.java`)
- enum `EngineBlockType` in `EngineBlockType.java`
- key type `EnergyClientHandler` (`EnergyClientHandler.java`)

## Port relevance to Re-Forestry
- Mentions of `energy` appear in `files/implemented-features.md` — check that file for port status.
- Primary Forestry reference for Re-Forestry port decisions.

## Source map
- `src/main/java/forestry/energy/EnergyHelper.java`
- `src/main/java/forestry/energy/EnergyTransferMode.java`
- `src/main/java/forestry/energy/ForestryEnergyStorage.java`
- `src/main/java/forestry/energy/ModuleEnergy.java`
- `src/main/java/forestry/energy/blocks/EngineBlock.java`
- `src/main/java/forestry/energy/blocks/EngineBlockType.java`
- `src/main/java/forestry/energy/blocks/package-info.java`
- `src/main/java/forestry/energy/client/EnergyClientHandler.java`
- `src/main/java/forestry/energy/client/package-info.java`
- `src/main/java/forestry/energy/features/EnergyBlocks.java`
- `src/main/java/forestry/energy/features/EnergyMenus.java`
- `src/main/java/forestry/energy/features/EnergyTiles.java`
- `src/main/java/forestry/energy/features/package-info.java`
- `src/main/java/forestry/energy/inventory/InventoryEngineBiogas.java`
- `src/main/java/forestry/energy/inventory/InventoryEnginePeat.java`
- `src/main/java/forestry/energy/inventory/package-info.java`
- `src/main/java/forestry/energy/menu/BiogasEngineMenu.java`
- `src/main/java/forestry/energy/menu/PeatEngineMenu.java`
- `src/main/java/forestry/energy/package-info.java`
- `src/main/java/forestry/energy/render/package-info.java`
- `src/main/java/forestry/energy/screen/BiogasEngineScreen.java`
- `src/main/java/forestry/energy/screen/BiogasSlot.java`
- `src/main/java/forestry/energy/screen/EngineScreen.java`
- `src/main/java/forestry/energy/screen/PeatEngineScreen.java`
- `src/main/java/forestry/energy/screen/package-info.java`
- `src/main/java/forestry/energy/tiles/BiogasEngineBlockEntity.java`
- `src/main/java/forestry/energy/tiles/ClockworkEngineBlockEntity.java`
- `src/main/java/forestry/energy/tiles/EngineBlockEntity.java`
- `src/main/java/forestry/energy/tiles/PeatEngineBlockEntity.java`
- `src/main/java/forestry/energy/tiles/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
