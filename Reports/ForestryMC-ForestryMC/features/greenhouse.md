# ForestryMC-ForestryMC — greenhouse

- Alias: `forestry12`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC`
- Package/path root: `src/main/java/forestry/greenhouse`
- Java files scanned: **9**
- Date: 2026-07-30

## Summary
Module `greenhouse` in `ForestryMC-ForestryMC` is rooted at `src/main/java/forestry/greenhouse` (9 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModuleGreenhouse`
- `BlockClimatiser`
- `BlockClimatiserType`
- `BlockGreenhouse`
- `BlockGreenhouseType`
- `BlockGreenhouseWindow`
- `BlockRegistryGreenhouse`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py forestry12 "greenhouse"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
- Declaration skim (first files):
  - `src/main/java/forestry/greenhouse/ModuleGreenhouse.java`
    - L11: package forestry.greenhouse;
    - L32: @ForestryModule(containerID = Constants.MOD_ID, moduleID = ForestryModuleUids.GREENHOUSE, name = "Greenhouse", author = "Nedelosk", url = Constants.URL, unlocalizedDescription = "for.module.greenhouse.description")
    - L33: public class ModuleGreenhouse extends BlankForestryModule {
    - L35: @Nullable
    - L38: public static BlockRegistryGreenhouse getBlocks() {
    - L43: @Override
    - L44: public void registerItemsAndBlocks() {
    - L48: @Override
    - L49: public void registerRecipes() {
  - `src/main/java/forestry/greenhouse/blocks/BlockClimatiser.java`
    - L11: package forestry.greenhouse.blocks;
    - L40: public class BlockClimatiser extends Block implements IBlockWithMeta, IItemModelRegister {
    - L41: public static final PropertyEnum<BlockClimatiserType> TYPE = PropertyEnum.create("type", BlockClimatiserType.class);
    - L43: public BlockClimatiser() {
    - L51: @Override
    - L52: public String getNameFromMeta(int meta) {
    - L57: @SuppressWarnings("deprecation")
    - L58: @Override
    - L59: public IBlockState getStateFromMeta(int meta) {
    - L63: @Override
    - L64: public int getMetaFromState(IBlockState state) {
    - L68: @Override
  - `src/main/java/forestry/greenhouse/blocks/BlockClimatiserType.java`
    - L11: package forestry.greenhouse.blocks;
    - L17: public enum BlockClimatiserType implements IStringSerializable {
    - L24: public static final BlockClimatiserType[] VALUES = values();
    - L26: @Override
    - L27: public String toString() {
    - L31: @Override
    - L32: public String getName() {
  - `src/main/java/forestry/greenhouse/blocks/BlockGreenhouse.java`
    - L11: package forestry.greenhouse.blocks;
    - L40: public class BlockGreenhouse extends Block implements IItemModelRegister, IBlockWithMeta {
    - L41: public static final PropertyEnum<BlockGreenhouseType> TYPE = PropertyEnum.create("type", BlockGreenhouseType.class);
    - L43: public BlockGreenhouse() {
    - L51: @Override
    - L52: public String getNameFromMeta(int meta) {
    - L57: @SideOnly(Side.CLIENT)
    - L58: @Override
    - L59: public void registerModel(Item item, IModelManager manager) {
    - L68: @SuppressWarnings("deprecation")
    - L69: @Override
    - L70: public IBlockState getStateFromMeta(int meta) {
  - `src/main/java/forestry/greenhouse/blocks/BlockGreenhouseType.java`
    - L11: package forestry.greenhouse.blocks;
    - L17: public enum BlockGreenhouseType implements IStringSerializable {
    - L26: public static final BlockGreenhouseType[] VALUES = values();
    - L28: public final boolean twoLayers;
    - L38: @Override
    - L39: public String toString() {
    - L43: @Override
    - L44: public String getName() {
  - `src/main/java/forestry/greenhouse/blocks/BlockGreenhouseWindow.java`
    - L11: package forestry.greenhouse.blocks;
    - L37: public class BlockGreenhouseWindow extends Block implements IItemModelRegister {
    - L39: public BlockGreenhouseWindow() {
    - L47: @SuppressWarnings("deprecation")
    - L48: @Override
    - L49: public boolean isOpaqueCube(IBlockState state) {
    - L53: @SuppressWarnings("deprecation")
    - L54: public boolean isFullCube(IBlockState state) {
    - L59: @Override
    - L60: @SideOnly(Side.CLIENT)
    - L61: public BlockRenderLayer getRenderLayer() {
    - L65: @SideOnly(Side.CLIENT)
  - `src/main/java/forestry/greenhouse/blocks/BlockRegistryGreenhouse.java`
    - L11: package forestry.greenhouse.blocks;
    - L16: public class BlockRegistryGreenhouse extends BlockRegistry {
    - L18: public final BlockGreenhouse greenhouseBlock;
    - L19: public final BlockClimatiser climatiserBlock;
    - L20: public final BlockGreenhouseWindow window;
    - L21: public final BlockGreenhouseWindow roofWindow;
    - L23: public BlockRegistryGreenhouse() {
  - `src/main/java/forestry/greenhouse/blocks/package-info.java`
    - L11: @ParametersAreNonnullByDefault
    - L12: @FieldsAreNonnullByDefault
    - L13: @MethodsReturnNonnullByDefault
    - L14: package forestry.greenhouse.blocks;
  - `src/main/java/forestry/greenhouse/package-info.java`
    - L11: @ParametersAreNonnullByDefault
    - L12: @FieldsAreNonnullByDefault
    - L13: @MethodsReturnNonnullByDefault
    - L14: package forestry.greenhouse;

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/assets/forestry/blockstates/greenhouse.json`
- `src/main/resources/assets/forestry/blockstates/greenhouse.window.json`
- `src/main/resources/assets/forestry/blockstates/greenhouse.window_up.json`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/hatch.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/dehumidifier.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/hygroregulator.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/fan.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/heater.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/top.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/gears.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/screen.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/control.png`
- `src/main/resources/assets/forestry/textures/blocks/greenhouse_deprecated/humidifier.png`
- `src/main/resources/assets/forestry/models/block/greenhouse_window_closed.json`
- `src/main/resources/assets/forestry/models/block/greenhouse_window_up_closed.json`
- `src/main/resources/assets/forestry/models/block/greenhouse_window_up_open.json`
- `src/main/resources/assets/forestry/models/block/greenhouse_window_open.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/plain.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/dehumidifier.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/hatch.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/humidifier.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/hygro.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/screen.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/gearbox.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/heater.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/control.json`
- `src/main/resources/assets/forestry/models/item/greenhouse/fan.json`
- `src/main/resources/assets/forestry/models/block/greenhouse/plain.json`
- `src/main/resources/assets/forestry/models/block/greenhouse/dehumidifier.json`
- `src/main/resources/assets/forestry/models/block/greenhouse/hatch.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModuleGreenhouse` (`ModuleGreenhouse.java`)
- enum `BlockClimatiserType` in `BlockClimatiserType.java`
- enum `BlockGreenhouseType` in `BlockGreenhouseType.java`
- key type `BlockRegistryGreenhouse` (`BlockRegistryGreenhouse.java`)

## Port relevance to Re-Forestry
- Mentions of `greenhouse` appear in `files/implemented-features.md` — check that file for port status.
- 1.12 Forestry — useful for CE-dropped content (greenhouse, book, climatology, database, etc.).

## Source map
- `src/main/java/forestry/greenhouse/ModuleGreenhouse.java`
- `src/main/java/forestry/greenhouse/blocks/BlockClimatiser.java`
- `src/main/java/forestry/greenhouse/blocks/BlockClimatiserType.java`
- `src/main/java/forestry/greenhouse/blocks/BlockGreenhouse.java`
- `src/main/java/forestry/greenhouse/blocks/BlockGreenhouseType.java`
- `src/main/java/forestry/greenhouse/blocks/BlockGreenhouseWindow.java`
- `src/main/java/forestry/greenhouse/blocks/BlockRegistryGreenhouse.java`
- `src/main/java/forestry/greenhouse/blocks/package-info.java`
- `src/main/java/forestry/greenhouse/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
