# ACGaming-Binnie — design

- Alias: `binnie`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Package/path root: `design/src/main/java/binnie/design`
- Java files scanned: **21**
- Date: 2026-07-30

## Summary
Module `design` in `ACGaming-Binnie` is rooted at `design/src/main/java/binnie/design` (21 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `Design`
- `DesignHelper`
- `DesignerManager`
- `EnumDesign`
- `EnumPattern`
- `Layout`
- `BlockDesign`
- `DesignBlock`
- `package-info`
- `ComponentDesignerRecipe`
- `ControlRecipeSlot`
- `ControlTileSelect`
- `DesignErrorCode`
- `DesignerSlots`
- `SlotValidatorDesignAdhesive`
- `SlotValidatorDesignMaterial`
- `WindowDesigner`
- `package-info`
- `ItemDesign`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py binnie "design"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
  - `gui`
  - `items`
- Declaration skim (first files):
  - `design/src/main/java/binnie/design/Design.java`
    - L1: package binnie.design;
    - L16: @Mod(
    - L23: public class Design {
    - L24: @SuppressWarnings("NullableProblems")
    - L25: @Mod.Instance(Constants.DESIGN_MOD_ID)
    - L26: public static Design instance;
    - L27: @Nullable
    - L30: @Nullable
    - L33: public static IDesignManager getDesignManager() {
    - L38: public static ValidatorSprite getSpritePolish() {
    - L43: @Mod.EventHandler
    - L44: public void preInit(FMLPreInitializationEvent event) {
  - `design/src/main/java/binnie/design/DesignHelper.java`
    - L1: package binnie.design;
    - L17: public final class DesignHelper {
    - L21: public static DesignBlock getDesignBlock(final IDesignSystem system, final int meta) {
    - L33: public static int getBlockMetadata(final IDesignSystem system, final DesignBlock block) {
    - L42: public static int getItemMetadata(final IDesignSystem system, final DesignBlock block) {
    - L49: public static ItemStack getItemStack(final BlockDesign block, final IDesignMaterial type1, final IDesignMaterial type2, final IDesign design) {
    - L56: public static ItemStack getItemStack(final BlockDesign block, final int type1, final int type2, final int design) {
    - L60: public static ItemStack getItemStack(final BlockDesign blockC, final DesignBlock block) {
    - L64: public static int getMetadata(final int plank1, final int plank2, final int design, final int rotation, final int facing) {
    - L68: public static boolean isValidPanelPlacement(IBlockAccess world, BlockPos pos, @Nullable EnumFacing facing) {
  - `design/src/main/java/binnie/design/DesignerManager.java`
    - L1: package binnie.design;
    - L15: public final class DesignerManager implements IDesignManager {
    - L25: public void registerDesignSystem(final IDesignSystem system) {
    - L29: public Collection<IDesignSystem> getDesignSystems() {
    - L33: @Override
    - L34: public boolean registerDesign(final int index, final IDesign design) {
    - L38: @Override
    - L39: public int getDesignIndex(final IDesign design) {
    - L48: @Override
    - L49: public IDesign getDesign(final int index) {
    - L53: @Override
    - L54: public boolean registerDesignCategory(final IDesignCategory category) {
  - `design/src/main/java/binnie/design/EnumDesign.java`
    - L1: package binnie.design;
    - L13: public enum EnumDesign implements IDesign {
  - `design/src/main/java/binnie/design/EnumPattern.java`
    - L1: package binnie.design;
    - L11: public enum EnumPattern implements IPattern {
  - `design/src/main/java/binnie/design/Layout.java`
    - L1: package binnie.design;
    - L10: public class Layout implements ILayout {
    - L19: public static ILayout get(final IPattern pattern, final boolean inverted) {
    - L23: public static ILayout get(final IPattern pattern) {
    - L27: @Override
    - L28: public IPattern getPattern() {
    - L32: @Override
    - L33: public boolean isInverted() {
    - L41: @Override
    - L42: public ILayout rotateRight() {
    - L46: @Override
    - L47: public ILayout rotateLeft() {
  - `design/src/main/java/binnie/design/blocks/BlockDesign.java`
    - L1: package binnie.design.blocks;
    - L57: public abstract class BlockDesign extends BlockMetadata implements IMultipassBlock<BlockDesign.Key>, IColoredBlock, ISpriteRegister, IItemModelRegister, IStateMapperRegister {
    - L58: public static final EnumFacing[] RENDER_DIRECTIONS = new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH};
    - L59: public static final EnumFacing[] RENDER_DIRECTIONS_ITEM = new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.WEST, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.NORTH};
    - L62: public BlockDesign(final IDesignSystem system, final Material material) {
    - L67: public static int getMetadata(final int plank1, final int plank2, final int design) {
    - L71: @SubscribeEvent
    - L72: public void onClick(final PlayerInteractEvent.RightClickBlock event) {
    - L102: public abstract ItemStack getCreativeStack(final IDesign p0);
    - L104: @Override
    - L105: public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> itemList) {
    - L111: public IDesignSystem getDesignSystem() {
  - `design/src/main/java/binnie/design/blocks/DesignBlock.java`
    - L1: package binnie.design.blocks;
    - L22: public class DesignBlock {
    - L39: public DesignBlock(final IDesignSystem system, @Nullable final IDesignMaterial primaryWood, @Nullable final IDesignMaterial secondaryWood, @Nullable final IDesign design, final int rotation, @Nullable final EnumFacing di
    - L68: @Override
    - L69: public String toString() {
    - L73: public IDesign getDesign() {
    - L77: public IDesignMaterial getPrimaryMaterial() {
    - L81: public IDesignMaterial getSecondaryMaterial() {
    - L85: public int getPrimaryColour() {
    - L89: public int getSecondaryColour() {
  - `design/src/main/java/binnie/design/blocks/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @MethodsReturnNonnullByDefault
    - L3: @FieldsAreNonnullByDefault
    - L4: package binnie.design.blocks;
  - `design/src/main/java/binnie/design/gui/ComponentDesignerRecipe.java`
    - L1: package binnie.design.gui;
    - L23: public class ComponentDesignerRecipe extends ComponentRecipe implements IComponentRecipe, INetwork.GuiNBT, IErrorStateSource {
    - L27: public ComponentDesignerRecipe(final Machine machine, final IDesignerType type) {
    - L33: @Override
    - L34: public void readFromNBT(final NBTTagCompound nbttagcompound) {
    - L39: @Override
    - L40: public NBTTagCompound writeToNBT(final NBTTagCompound nbttagcompound1) {
    - L46: @Override
    - L47: public boolean isRecipe() {
    - L51: @Override
    - L52: public ItemStack getProduct() {
    - L64: @Override
  - `design/src/main/java/binnie/design/gui/ControlRecipeSlot.java`
    - L1: package binnie.design.gui;
    - L14: public class ControlRecipeSlot extends ControlSlotBase {
    - L15: public ControlRecipeSlot(final IWidget parent, final int x, final int y) {
    - L28: @Override
    - L29: public ItemStack getItemStack() {
  - `design/src/main/java/binnie/design/gui/ControlTileSelect.java`
    - L1: package binnie.design.gui;
    - L38: public class ControlTileSelect extends Control implements IControlValue<IDesign>, IControlScrollable {
    - L42: protected ControlTileSelect(final IWidget parent, final int x, final int y) {
    - L49: @Override
    - L50: public float getPercentageIndex() {
    - L54: @Override
    - L55: public void setPercentageIndex(final float index) {
    - L58: @Override
    - L59: public float getPercentageShown() {
    - L63: @Override
    - L64: public IDesign getValue() {
    - L68: @Override

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- enum `EnumDesign` in `EnumDesign.java`
- enum `EnumPattern` in `EnumPattern.java`
- enum `DesignErrorCode` in `DesignErrorCode.java`

## Port relevance to Re-Forestry
- Data/source to extract for addon modules; not a runtime dependency.

## Source map
- `design/src/main/java/binnie/design/Design.java`
- `design/src/main/java/binnie/design/DesignHelper.java`
- `design/src/main/java/binnie/design/DesignerManager.java`
- `design/src/main/java/binnie/design/EnumDesign.java`
- `design/src/main/java/binnie/design/EnumPattern.java`
- `design/src/main/java/binnie/design/Layout.java`
- `design/src/main/java/binnie/design/blocks/BlockDesign.java`
- `design/src/main/java/binnie/design/blocks/DesignBlock.java`
- `design/src/main/java/binnie/design/blocks/package-info.java`
- `design/src/main/java/binnie/design/gui/ComponentDesignerRecipe.java`
- `design/src/main/java/binnie/design/gui/ControlRecipeSlot.java`
- `design/src/main/java/binnie/design/gui/ControlTileSelect.java`
- `design/src/main/java/binnie/design/gui/DesignErrorCode.java`
- `design/src/main/java/binnie/design/gui/DesignerSlots.java`
- `design/src/main/java/binnie/design/gui/SlotValidatorDesignAdhesive.java`
- `design/src/main/java/binnie/design/gui/SlotValidatorDesignMaterial.java`
- `design/src/main/java/binnie/design/gui/WindowDesigner.java`
- `design/src/main/java/binnie/design/gui/package-info.java`
- `design/src/main/java/binnie/design/items/ItemDesign.java`
- `design/src/main/java/binnie/design/items/package-info.java`
- `design/src/main/java/binnie/design/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
