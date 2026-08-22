# thedarkcolour-ForestryCE — worktable

- Alias: `CE`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/thedarkcolour-ForestryCE`
- Package/path root: `src/main/java/forestry/worktable`
- Java files scanned: **34**
- Date: 2026-07-30

## Summary
Module `worktable` in `thedarkcolour-ForestryCE` is rooted at `src/main/java/forestry/worktable` (34 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModuleWorktable`
- `WorktableBlock`
- `WorktableBlockType`
- `package-info`
- `WorktableClientHandler`
- `package-info`
- `WorktableJeiPlugin`
- `WorktableRecipeTransferHandler`
- `package-info`
- `WorktableBlocks`
- `WorktableMenus`
- `WorktableTiles`
- `package-info`
- `WorktableCraftingContainer`
- `WorktableInventory`
- `package-info`
- `package-info`
- `PacketWorktableMemoryUpdate`
- `PacketWorktableRecipeRequest`
- `PacketWorktableRecipeUpdate`
- `package-info`
- `package-info`
- `MemorizedRecipe`
- `RecipeMemory`
- `package-info`
- `WorktableMenu`
- `WorktableScreen`
- `package-info`
- `ClearWorktable`
- `MemorizedRecipeSlot`
- `package-info`
- `ICrafterWorktable`
- `WorktableTile`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py CE "worktable"`
- Source root exists: **True**
- Nested packages under this module:
  - `blocks`
  - `client`
  - `compat`
  - `features`
  - `inventory`
  - `network`
  - `network/packets`
  - `recipes`
  - `screens`
  - `screens/widgets`
  - `tiles`
- Declaration skim (first files):
  - `src/main/java/forestry/worktable/ModuleWorktable.java`
    - L1: package forestry.worktable;
    - L18: @ForestryModule
    - L19: public class ModuleWorktable extends BlankForestryModule {
    - L20: @Override
    - L21: public ResourceLocation getId() {
    - L25: @Override
    - L26: public void registerPackets(IPacketRegistry registry) {
    - L33: @Override
    - L34: public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
  - `src/main/java/forestry/worktable/blocks/WorktableBlock.java`
    - L1: package forestry.worktable.blocks;
    - L13: public class WorktableBlock extends BlockBase<WorktableBlockType> {
    - L14: public WorktableBlock(WorktableBlockType blockType) {
    - L18: @Override
    - L19: public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
  - `src/main/java/forestry/worktable/blocks/WorktableBlockType.java`
    - L1: package forestry.worktable.blocks;
    - L10: public enum WorktableBlockType implements IBlockType {
    - L19: @Override
    - L20: public IMachineProperties<?> getMachineProperties() {
    - L24: @Override
    - L25: public String getSerializedName() {
  - `src/main/java/forestry/worktable/blocks/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.worktable.blocks;
  - `src/main/java/forestry/worktable/client/WorktableClientHandler.java`
    - L1: package forestry.worktable.client;
    - L10: public class WorktableClientHandler implements IClientModuleHandler {
    - L11: @Override
    - L12: public void registerEvents(IEventBus modBus) {
  - `src/main/java/forestry/worktable/client/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.worktable.client;
  - `src/main/java/forestry/worktable/compat/WorktableJeiPlugin.java`
    - L1: package forestry.worktable.compat;
    - L17: @JeiPlugin
    - L18: public class WorktableJeiPlugin implements IModPlugin {
    - L19: @Override
    - L20: public ResourceLocation getPluginUid() {
    - L24: @Override
    - L25: public void registerRecipes(IRecipeRegistration registration) {
    - L29: @Override
    - L30: public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
    - L34: @Override
    - L35: public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    - L39: @Override
  - `src/main/java/forestry/worktable/compat/WorktableRecipeTransferHandler.java`
    - L1: package forestry.worktable.compat;
    - L25: class WorktableRecipeTransferHandler implements IRecipeTransferHandler<WorktableMenu, CraftingRecipe> {
    - L26: @Override
    - L27: public Class<WorktableMenu> getContainerClass() {
    - L31: @Override
    - L32: public Optional<MenuType<WorktableMenu>> getMenuType() {
    - L36: @Override
    - L37: public RecipeType<CraftingRecipe> getRecipeType() {
    - L41: @Nullable
    - L42: @Override
    - L43: public IRecipeTransferError transferRecipe(WorktableMenu container, CraftingRecipe recipe, IRecipeSlotsView recipeSlots, Player player, boolean maxTransfer, boolean doTransfer) {
  - `src/main/java/forestry/worktable/compat/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.worktable.compat;
  - `src/main/java/forestry/worktable/features/WorktableBlocks.java`
    - L1: package forestry.worktable.features;
    - L12: @FeatureProvider
    - L13: public class WorktableBlocks {
    - L16: public static final IBlockFeature<WorktableBlock, ItemBlockForestry<?>> WORKTABLE = REGISTRY.block(() -> new WorktableBlock(WorktableBlockType.WORKTABLE), ItemBlockForestry::new, "worktable");
  - `src/main/java/forestry/worktable/features/WorktableMenus.java`
    - L1: package forestry.worktable.features;
    - L10: @FeatureProvider
    - L11: public class WorktableMenus {
    - L12: public static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ForestryModuleIds.WORKTABLE);
    - L14: public static final FeatureMenuType<WorktableMenu> WORKTABLE = REGISTRY.menuType(WorktableMenu::fromNetwork, "worktable");
  - `src/main/java/forestry/worktable/features/WorktableTiles.java`
    - L1: package forestry.worktable.features;
    - L12: @FeatureProvider
    - L13: public class WorktableTiles {
    - L16: public static final FeatureTileType<WorktableTile> WORKTABLE = REGISTRY.tile(WorktableTile::new, "worktable", () -> List.of(WorktableBlocks.WORKTABLE.block()));

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/assets/forestry/blockstates/worktable.json`
- `src/main/resources/assets/forestry/patchouli_books/foresters_manual/en_us/entries/core/worktable.json`
- `src/main/resources/assets/forestry/textures/gui/worktable2.png`
- `src/main/resources/assets/forestry/textures/block/worktable.3.png`
- `src/main/resources/assets/forestry/textures/block/worktable.0.png`
- `src/main/resources/assets/forestry/textures/block/worktable.2.png`
- `src/main/resources/assets/forestry/textures/block/worktable.4.png`
- `src/main/resources/assets/forestry/textures/block/worktable.1.png`
- `src/main/resources/assets/forestry/models/item/worktable.json`
- `src/main/resources/assets/forestry/models/block/worktable.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/thedarkcolour-ForestryCE` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModuleWorktable` (`ModuleWorktable.java`)
- enum `WorktableBlockType` in `WorktableBlockType.java`
- key type `WorktableClientHandler` (`WorktableClientHandler.java`)
- key type `WorktableJeiPlugin` (`WorktableJeiPlugin.java`)
- key type `WorktableRecipeTransferHandler` (`WorktableRecipeTransferHandler.java`)
- record `PacketWorktableMemoryUpdate` in `PacketWorktableMemoryUpdate.java`
- key type `PacketWorktableMemoryUpdate` (`PacketWorktableMemoryUpdate.java`)
- record `PacketWorktableRecipeRequest` in `PacketWorktableRecipeRequest.java`
- key type `PacketWorktableRecipeRequest` (`PacketWorktableRecipeRequest.java`)
- record `PacketWorktableRecipeUpdate` in `PacketWorktableRecipeUpdate.java`
- key type `PacketWorktableRecipeUpdate` (`PacketWorktableRecipeUpdate.java`)
- interface `ICrafterWorktable` in `ICrafterWorktable.java`

## Port relevance to Re-Forestry
- Mentions of `worktable` appear in `files/implemented-features.md` — check that file for port status.
- Primary Forestry reference for Re-Forestry port decisions.

## Source map
- `src/main/java/forestry/worktable/ModuleWorktable.java`
- `src/main/java/forestry/worktable/blocks/WorktableBlock.java`
- `src/main/java/forestry/worktable/blocks/WorktableBlockType.java`
- `src/main/java/forestry/worktable/blocks/package-info.java`
- `src/main/java/forestry/worktable/client/WorktableClientHandler.java`
- `src/main/java/forestry/worktable/client/package-info.java`
- `src/main/java/forestry/worktable/compat/WorktableJeiPlugin.java`
- `src/main/java/forestry/worktable/compat/WorktableRecipeTransferHandler.java`
- `src/main/java/forestry/worktable/compat/package-info.java`
- `src/main/java/forestry/worktable/features/WorktableBlocks.java`
- `src/main/java/forestry/worktable/features/WorktableMenus.java`
- `src/main/java/forestry/worktable/features/WorktableTiles.java`
- `src/main/java/forestry/worktable/features/package-info.java`
- `src/main/java/forestry/worktable/inventory/WorktableCraftingContainer.java`
- `src/main/java/forestry/worktable/inventory/WorktableInventory.java`
- `src/main/java/forestry/worktable/inventory/package-info.java`
- `src/main/java/forestry/worktable/network/package-info.java`
- `src/main/java/forestry/worktable/network/packets/PacketWorktableMemoryUpdate.java`
- `src/main/java/forestry/worktable/network/packets/PacketWorktableRecipeRequest.java`
- `src/main/java/forestry/worktable/network/packets/PacketWorktableRecipeUpdate.java`
- `src/main/java/forestry/worktable/network/packets/package-info.java`
- `src/main/java/forestry/worktable/package-info.java`
- `src/main/java/forestry/worktable/recipes/MemorizedRecipe.java`
- `src/main/java/forestry/worktable/recipes/RecipeMemory.java`
- `src/main/java/forestry/worktable/recipes/package-info.java`
- `src/main/java/forestry/worktable/screens/WorktableMenu.java`
- `src/main/java/forestry/worktable/screens/WorktableScreen.java`
- `src/main/java/forestry/worktable/screens/package-info.java`
- `src/main/java/forestry/worktable/screens/widgets/ClearWorktable.java`
- `src/main/java/forestry/worktable/screens/widgets/MemorizedRecipeSlot.java`
- `src/main/java/forestry/worktable/screens/widgets/package-info.java`
- `src/main/java/forestry/worktable/tiles/ICrafterWorktable.java`
- `src/main/java/forestry/worktable/tiles/WorktableTile.java`
- `src/main/java/forestry/worktable/tiles/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
