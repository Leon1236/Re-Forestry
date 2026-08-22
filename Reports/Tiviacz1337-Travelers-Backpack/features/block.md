# Tiviacz1337-Travelers-Backpack — block

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/block`
- Java files scanned: **2**
- Date: 2026-07-30

## Summary
Module `block` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/block` (2 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `SleepingBagBlock`
- `TravelersBackpackBlock`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "block"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/block/SleepingBagBlock.java`
    - L1: package com.tiviacz.travelersbackpack.block;
    - L50: public class SleepingBagBlock extends BedBlock {
    - L51: public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
    - L52: public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
    - L53: public static final BooleanProperty CAN_DROP = BlockStateProperties.CONDITIONAL;
    - L54: protected static final VoxelShape SLEEPING_BAG = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    - L56: protected static final VoxelShape SLEEPING_BAG_NORTH = Stream.of(
    - L61: protected static final VoxelShape SLEEPING_BAG_EAST = Stream.of(
    - L66: protected static final VoxelShape SLEEPING_BAG_SOUTH = Stream.of(
    - L71: protected static final VoxelShape SLEEPING_BAG_WEST = Stream.of(
    - L76: public SleepingBagBlock(DyeColor color, Properties properties) {
    - L81: @Override
  - `src/main/java/com/tiviacz/travelersbackpack/block/TravelersBackpackBlock.java`
    - L1: package com.tiviacz.travelersbackpack.block;
    - L64: public class TravelersBackpackBlock extends Block implements EntityBlock {
    - L65: public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    - L67: public TravelersBackpackBlock(Properties builder) {
    - L72: @Override
    - L73: public RenderShape getRenderShape(BlockState state) {
    - L77: @Override
    - L78: public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
    - L91: @Override
    - L92: protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
    - L101: @Override
    - L102: protected void onExplosionHit(BlockState pState, ServerLevel pLevel, BlockPos pPos, Explosion pExplosion, BiConsumer<ItemStack, BlockPos> pDropConsumer) {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/data/carryon/tags/block/block_blacklist.json`
- `src/main/resources/data/minecraft/tags/block/enchantment_power_provider.json`
- `src/main/resources/data/minecraft/tags/block/wither_immune.json`
- `src/main/resources/data/minecraft/tags/block/dragon_immune.json`
- `src/main/resources/assets/travelersbackpack/blockstates/cactus.json`
- `src/main/resources/assets/travelersbackpack/blockstates/wolf.json`
- `src/main/resources/assets/travelersbackpack/blockstates/brown_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/wither.json`
- `src/main/resources/assets/travelersbackpack/blockstates/coal.json`
- `src/main/resources/assets/travelersbackpack/blockstates/purple_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/squid.json`
- `src/main/resources/assets/travelersbackpack/blockstates/pig.json`
- `src/main/resources/assets/travelersbackpack/blockstates/melon.json`
- `src/main/resources/assets/travelersbackpack/blockstates/red_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/villager.json`
- `src/main/resources/assets/travelersbackpack/blockstates/skeleton.json`
- `src/main/resources/assets/travelersbackpack/blockstates/emerald.json`
- `src/main/resources/assets/travelersbackpack/blockstates/pink_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/fox.json`
- `src/main/resources/assets/travelersbackpack/blockstates/pumpkin.json`
- `src/main/resources/assets/travelersbackpack/blockstates/cake.json`
- `src/main/resources/assets/travelersbackpack/blockstates/netherite.json`
- `src/main/resources/assets/travelersbackpack/blockstates/magenta_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/dragon.json`
- `src/main/resources/assets/travelersbackpack/blockstates/blue_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/black_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/magma_cube.json`
- `src/main/resources/assets/travelersbackpack/blockstates/orange_sleeping_bag.json`
- `src/main/resources/assets/travelersbackpack/blockstates/iron.json`
- `src/main/resources/assets/travelersbackpack/blockstates/lime_sleeping_bag.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `block` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/block/SleepingBagBlock.java`
- `src/main/java/com/tiviacz/travelersbackpack/block/TravelersBackpackBlock.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
