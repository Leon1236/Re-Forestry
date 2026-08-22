# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — blocks

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/block`
- Java files scanned: **14**
- Date: 2026-07-30

## Summary
Module `blocks` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/block` (14 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModBlocks`
- `CrystallizerBlock`
- `FluoriteLampBlock`
- `HoneyBerryBushBlock`
- `MagicBlock`
- `PedestalBlock`
- `RiceCropBlock`
- `StrawberryCropBlock`
- `ImplementedInventory`
- `ModBlockEntities`
- `CrystallizerBlockEntity`
- `PedestalBlockEntity`
- `PedestalBlockEntityRenderState`
- `PedestalBlockEntityRenderer`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "blocks"`
- Source root exists: **True**
- Nested packages under this module:
  - `custom`
  - `entity`
  - `entity/custom`
  - `entity/renderer`
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/block/ModBlocks.java`
    - L1: package net.kaupenjoe.tutorialmod.block;
    - L28: public class ModBlocks {
    - L29: public static final Block FLUORITE_BLOCK = registerBlock("fluorite_block",
    - L32: public static final Block RAW_FLUORITE_BLOCK = registerBlock("raw_fluorite_block",
    - L36: public static final Block FLUORITE_ORE = registerBlock("fluorite_ore",
    - L39: public static final Block FLUORITE_DEEPSLATE_ORE = registerBlock("fluorite_deepslate_ore",
    - L43: public static final Block FLUORITE_NETHER_ORE = registerBlock("fluorite_nether_ore",
    - L46: public static final Block FLUORITE_END_ORE = registerBlock("fluorite_end_ore",
    - L50: public static final Block MAGIC_BLOCK = registerBlock("magic_block",
    - L55: public static final Block FLUORITE_STAIRS = registerBlock("fluorite_stairs",
    - L58: public static final Block FLUORITE_SLAB = registerBlock("fluorite_slab",
    - L61: public static final Block FLUORITE_BUTTON = registerBlock("fluorite_button",
  - `src/main/java/net/kaupenjoe/tutorialmod/block/custom/CrystallizerBlock.java`
    - L1: package net.kaupenjoe.tutorialmod.block.custom;
    - L31: public class CrystallizerBlock extends BaseEntityBlock {
    - L32: public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    - L33: public static final BooleanProperty LIT = BlockStateProperties.LIT;
    - L34: public static final MapCodec<CrystallizerBlock> CODEC = simpleCodec(CrystallizerBlock::new);
    - L37: public CrystallizerBlock(Properties properties) {
    - L42: @Override
    - L43: public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
    - L47: @Override
    - L48: protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    - L53: @Override
    - L54: protected MapCodec<? extends BaseEntityBlock> codec() {
  - `src/main/java/net/kaupenjoe/tutorialmod/block/custom/FluoriteLampBlock.java`
    - L1: package net.kaupenjoe.tutorialmod.block.custom;
    - L13: public class FluoriteLampBlock extends Block {
    - L14: public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    - L16: public FluoriteLampBlock(Properties properties) {
    - L21: @Override
    - L22: protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
    - L28: @Override
    - L29: protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
  - `src/main/java/net/kaupenjoe/tutorialmod/block/custom/HoneyBerryBushBlock.java`
    - L1: package net.kaupenjoe.tutorialmod.block.custom;
    - L17: public class HoneyBerryBushBlock extends SweetBerryBushBlock {
    - L18: public HoneyBerryBushBlock(Properties properties) {
    - L22: @Override
    - L23: protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
    - L27: @Override
    - L28: protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
  - `src/main/java/net/kaupenjoe/tutorialmod/block/custom/MagicBlock.java`
    - L1: package net.kaupenjoe.tutorialmod.block.custom;
    - L22: public class MagicBlock extends Block {
    - L23: public MagicBlock(Properties properties) {
    - L27: @Override
    - L28: protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    - L34: @Override
    - L35: public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
  - `src/main/java/net/kaupenjoe/tutorialmod/block/custom/PedestalBlock.java`
    - L1: package net.kaupenjoe.tutorialmod.block.custom;
    - L23: public class PedestalBlock extends BaseEntityBlock {
    - L24: public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    - L27: public PedestalBlock(Properties properties) {
    - L31: @Override
    - L32: protected MapCodec<? extends BaseEntityBlock> codec() {
    - L36: @Override
    - L37: protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    - L41: @Override
    - L42: public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
    - L46: @Override
    - L47: public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
  - `src/main/java/net/kaupenjoe/tutorialmod/block/custom/RiceCropBlock.java`
    - L1: package net.kaupenjoe.tutorialmod.block.custom;
    - L11: public class RiceCropBlock extends CropBlock {
    - L12: public RiceCropBlock(Properties properties) {
    - L16: @Override
    - L17: protected ItemLike getBaseSeedId() {
    - L21: @Override
    - L22: protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
  - `src/main/java/net/kaupenjoe/tutorialmod/block/custom/StrawberryCropBlock.java`
    - L1: package net.kaupenjoe.tutorialmod.block.custom;
    - L11: public class StrawberryCropBlock extends CropBlock {
    - L12: public static final int MAX_AGE = 5;
    - L13: public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    - L15: public StrawberryCropBlock(Properties properties) {
    - L19: @Override
    - L20: protected ItemLike getBaseSeedId() {
    - L24: @Override
    - L25: protected IntegerProperty getAgeProperty() {
    - L29: @Override
    - L30: public int getMaxAge() {
    - L34: @Override
  - `src/main/java/net/kaupenjoe/tutorialmod/block/entity/ImplementedInventory.java`
    - L1: package net.kaupenjoe.tutorialmod.block.entity;
    - L27: @FunctionalInterface
    - L28: public interface ImplementedInventory extends WorldlyContainer {
    - L67: @Override
    - L87: @Override
    - L102: @Override
    - L116: @Override
  - `src/main/java/net/kaupenjoe/tutorialmod/block/entity/ModBlockEntities.java`
    - L1: package net.kaupenjoe.tutorialmod.block.entity;
    - L13: public class ModBlockEntities {
    - L14: public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
    - L18: public static final BlockEntityType<CrystallizerBlockEntity> CRYSTALLIZER_BE =
    - L23: public static void registerBlockEntities() {
  - `src/main/java/net/kaupenjoe/tutorialmod/block/entity/custom/CrystallizerBlockEntity.java`
    - L1: package net.kaupenjoe.tutorialmod.block.entity.custom;
    - L40: public class CrystallizerBlockEntity extends BlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {
    - L41: public final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    - L50: public CrystallizerBlockEntity(BlockPos worldPosition, BlockState blockState) {
    - L53: @Override
    - L54: public int get(int dataId) {
    - L62: @Override
    - L63: public void set(int dataId, int value) {
    - L70: @Override
    - L71: public int getCount() {
    - L78: @Override
    - L79: public NonNullList<ItemStack> getItems() {
  - `src/main/java/net/kaupenjoe/tutorialmod/block/entity/custom/PedestalBlockEntity.java`
    - L1: package net.kaupenjoe.tutorialmod.block.entity.custom;
    - L29: public class PedestalBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem, ExtendedMenuProvider<BlockPos> {
    - L30: public NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    - L32: public PedestalBlockEntity(BlockPos worldPosition, BlockState blockState) {
    - L36: @Override
    - L37: public BlockEntity getContainerBlockEntity() {
    - L41: @Override
    - L42: public ItemStack getTheItem() {
    - L46: @Override
    - L47: public void setTheItem(ItemStack itemStack) {
    - L52: @Override
    - L53: public void clearContent() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `ImplementedInventory` in `ImplementedInventory.java`

## Port relevance to Re-Forestry
- Mentions of `blocks` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/block/ModBlocks.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/custom/CrystallizerBlock.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/custom/FluoriteLampBlock.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/custom/HoneyBerryBushBlock.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/custom/MagicBlock.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/custom/PedestalBlock.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/custom/RiceCropBlock.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/custom/StrawberryCropBlock.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/entity/ImplementedInventory.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/entity/ModBlockEntities.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/entity/custom/CrystallizerBlockEntity.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/entity/custom/PedestalBlockEntity.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/entity/renderer/PedestalBlockEntityRenderState.java`
- `src/main/java/net/kaupenjoe/tutorialmod/block/entity/renderer/PedestalBlockEntityRenderer.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
