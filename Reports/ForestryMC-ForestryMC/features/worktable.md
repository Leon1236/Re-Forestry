# ForestryMC-ForestryMC — worktable

- Alias: `forestry12`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC`
- Package/path root: `src/main/java/forestry/worktable`
- Java files scanned: **30**
- Date: 2026-07-30

## Summary
Module `worktable` in `ForestryMC-ForestryMC` is rooted at `src/main/java/forestry/worktable` (30 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModuleWorktable`
- `BlockRegistryWorktable`
- `BlockTypeWorktable`
- `BlockWorktable`
- `package-info`
- `WorktableJeiPlugin`
- `WorktableRecipeTransferHandler`
- `package-info`
- `ContainerWorktable`
- `GuiWorktable`
- `package-info`
- `ClearWorktable`
- `MemorizedRecipeSlot`
- `package-info`
- `InventoryCraftingForestry`
- `InventoryWorktable`
- `package-info`
- `PacketRegistryWorktable`
- `package-info`
- `PacketWorktableMemoryUpdate`
- `PacketWorktableRecipeRequest`
- `PacketWorktableRecipeUpdate`
- `package-info`
- `package-info`
- `MemorizedRecipe`
- `RecipeMemory`
- `package-info`
- `ICrafterWorktable`
- `TileWorktable`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py forestry12 "worktable"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
  - `compat`
  - `gui`
  - `gui/widgets`
  - `inventory`
  - `network`
  - `network/packets`
  - `recipes`
  - `tiles`
- Declaration skim (first files):
  - `src/main/java/forestry/worktable/ModuleWorktable.java`
    - L1: package forestry.worktable;
    - L18: @ForestryModule(containerID = Constants.MOD_ID, moduleID = ForestryModuleUids.WORKTABLE, name = "Worktable", author = "Nedelosk", url = Constants.URL, unlocalizedDescription = "for.module.worktable.description")
    - L19: public class ModuleWorktable extends BlankForestryModule {
    - L20: @Nullable
    - L23: public static BlockRegistryWorktable getBlocks() {
    - L28: @Override
    - L29: public void registerItemsAndBlocks() {
    - L34: @Override
    - L35: public IPacketRegistry getPacketRegistry() {
    - L39: @Override
    - L40: public void doInit() {
    - L46: @Override
  - `src/main/java/forestry/worktable/blocks/BlockRegistryWorktable.java`
    - L1: package forestry.worktable.blocks;
    - L6: public class BlockRegistryWorktable extends BlockRegistry {
    - L7: public final BlockWorktable worktable;
    - L9: public BlockRegistryWorktable() {
  - `src/main/java/forestry/worktable/blocks/BlockTypeWorktable.java`
    - L1: package forestry.worktable.blocks;
    - L9: public enum BlockTypeWorktable implements IBlockType {
    - L12: public static final BlockTypeWorktable[] VALUES = values();
    - L20: @Override
    - L21: public IMachineProperties getMachineProperties() {
    - L25: @Override
    - L26: public String getName() {
  - `src/main/java/forestry/worktable/blocks/BlockWorktable.java`
    - L11: package forestry.worktable.blocks;
    - L15: public class BlockWorktable extends BlockBase<BlockTypeWorktable> {
    - L16: public BlockWorktable(BlockTypeWorktable worktable) {
  - `src/main/java/forestry/worktable/blocks/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.worktable.blocks;
  - `src/main/java/forestry/worktable/compat/WorktableJeiPlugin.java`
    - L1: package forestry.worktable.compat;
    - L22: @JEIPlugin
    - L23: @SideOnly(Side.CLIENT)
    - L24: public class WorktableJeiPlugin implements IModPlugin {
    - L26: @Override
    - L27: public void register(IModRegistry registry) {
  - `src/main/java/forestry/worktable/compat/WorktableRecipeTransferHandler.java`
    - L1: package forestry.worktable.compat;
    - L24: @SideOnly(Side.CLIENT)
    - L25: class WorktableRecipeTransferHandler implements IRecipeTransferHandler<ContainerWorktable> {
    - L26: @Override
    - L27: public Class<ContainerWorktable> getContainerClass() {
    - L31: @Nullable
    - L32: @Override
    - L33: public IRecipeTransferError transferRecipe(ContainerWorktable container, IRecipeLayout recipeLayout, EntityPlayer player, boolean maxTransfer, boolean doTransfer) {
  - `src/main/java/forestry/worktable/compat/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.worktable.compat;
  - `src/main/java/forestry/worktable/gui/ContainerWorktable.java`
    - L11: package forestry.worktable.gui;
    - L40: public class ContainerWorktable extends ContainerTile<TileWorktable> implements IContainerCrafting, IGuiSelectable {
    - L45: public ContainerWorktable(EntityPlayer player, TileWorktable tile) {
    - L73: @Override
    - L74: public void detectAndSendChanges() {
    - L89: public void updateCraftMatrix() {
    - L98: @Override
    - L99: public void onCraftMatrixChanged(IInventory iinventory, int slot) {
    - L113: @Override
    - L114: public void onCraftMatrixChanged(IInventory iinventory) {
    - L119: @SideOnly(Side.CLIENT)
    - L120: public static void clearRecipe() {
  - `src/main/java/forestry/worktable/gui/GuiWorktable.java`
    - L11: package forestry.worktable.gui;
    - L28: public class GuiWorktable extends GuiForestryTitled<ContainerWorktable> {
    - L34: public GuiWorktable(EntityPlayer player, TileWorktable tile) {
    - L55: @Override
    - L56: public void updateScreen() {
    - L74: @Override
    - L75: protected void actionPerformed(GuiButton button) {
    - L81: @Override
    - L82: protected void addLedgers() {
  - `src/main/java/forestry/worktable/gui/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.worktable.gui;
  - `src/main/java/forestry/worktable/gui/widgets/ClearWorktable.java`
    - L11: package forestry.worktable.gui.widgets;
    - L18: public class ClearWorktable extends Widget {
    - L20: public ClearWorktable(WidgetManager manager, int xPos, int yPos) {
    - L26: @Override
    - L27: public void draw(int startX, int startY) {
    - L30: @Override
    - L31: public void handleMouseClick(int mouseX, int mouseY, int mouseButton) {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/assets/forestry/blockstates/worktable.json`
- `src/main/resources/assets/forestry/textures/blocks/worktable.3.png`
- `src/main/resources/assets/forestry/textures/blocks/worktable.0.png`
- `src/main/resources/assets/forestry/textures/blocks/worktable.2.png`
- `src/main/resources/assets/forestry/textures/blocks/worktable.4.png`
- `src/main/resources/assets/forestry/textures/blocks/worktable.1.png`
- `src/main/resources/assets/forestry/textures/gui/worktable2.png`
- `src/main/resources/assets/forestry/models/item/worktable.json`
- `src/main/resources/assets/forestry/models/block/worktable.json`
- `src/main/resources/assets/forestry/manual/en_us/core/worktable.json`
- `src/main/resources/assets/forestry/manual/zh_tw/core/worktable.json`
- `src/main/resources/assets/forestry/manual/zh_cn/core/worktable.json`
- `src/main/resources/assets/forestry/manual/ru_ru/core/worktable.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModuleWorktable` (`ModuleWorktable.java`)
- key type `BlockRegistryWorktable` (`BlockRegistryWorktable.java`)
- enum `BlockTypeWorktable` in `BlockTypeWorktable.java`
- key type `WorktableJeiPlugin` (`WorktableJeiPlugin.java`)
- key type `WorktableRecipeTransferHandler` (`WorktableRecipeTransferHandler.java`)
- key type `PacketRegistryWorktable` (`PacketRegistryWorktable.java`)
- key type `PacketWorktableMemoryUpdate` (`PacketWorktableMemoryUpdate.java`)
- key type `PacketWorktableRecipeRequest` (`PacketWorktableRecipeRequest.java`)
- key type `PacketWorktableRecipeUpdate` (`PacketWorktableRecipeUpdate.java`)
- interface `ICrafterWorktable` in `ICrafterWorktable.java`

## Port relevance to Re-Forestry
- Mentions of `worktable` appear in `files/implemented-features.md` — check that file for port status.
- 1.12 Forestry — useful for CE-dropped content (greenhouse, book, climatology, database, etc.).

## Source map
- `src/main/java/forestry/worktable/ModuleWorktable.java`
- `src/main/java/forestry/worktable/blocks/BlockRegistryWorktable.java`
- `src/main/java/forestry/worktable/blocks/BlockTypeWorktable.java`
- `src/main/java/forestry/worktable/blocks/BlockWorktable.java`
- `src/main/java/forestry/worktable/blocks/package-info.java`
- `src/main/java/forestry/worktable/compat/WorktableJeiPlugin.java`
- `src/main/java/forestry/worktable/compat/WorktableRecipeTransferHandler.java`
- `src/main/java/forestry/worktable/compat/package-info.java`
- `src/main/java/forestry/worktable/gui/ContainerWorktable.java`
- `src/main/java/forestry/worktable/gui/GuiWorktable.java`
- `src/main/java/forestry/worktable/gui/package-info.java`
- `src/main/java/forestry/worktable/gui/widgets/ClearWorktable.java`
- `src/main/java/forestry/worktable/gui/widgets/MemorizedRecipeSlot.java`
- `src/main/java/forestry/worktable/gui/widgets/package-info.java`
- `src/main/java/forestry/worktable/inventory/InventoryCraftingForestry.java`
- `src/main/java/forestry/worktable/inventory/InventoryWorktable.java`
- `src/main/java/forestry/worktable/inventory/package-info.java`
- `src/main/java/forestry/worktable/network/PacketRegistryWorktable.java`
- `src/main/java/forestry/worktable/network/package-info.java`
- `src/main/java/forestry/worktable/network/packets/PacketWorktableMemoryUpdate.java`
- `src/main/java/forestry/worktable/network/packets/PacketWorktableRecipeRequest.java`
- `src/main/java/forestry/worktable/network/packets/PacketWorktableRecipeUpdate.java`
- `src/main/java/forestry/worktable/network/packets/package-info.java`
- `src/main/java/forestry/worktable/package-info.java`
- `src/main/java/forestry/worktable/recipes/MemorizedRecipe.java`
- `src/main/java/forestry/worktable/recipes/RecipeMemory.java`
- `src/main/java/forestry/worktable/recipes/package-info.java`
- `src/main/java/forestry/worktable/tiles/ICrafterWorktable.java`
- `src/main/java/forestry/worktable/tiles/TileWorktable.java`
- `src/main/java/forestry/worktable/tiles/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
