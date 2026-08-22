# thedarkcolour-Immersive-Forestry — compat

- Alias: `IF`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-Immersive-Forestry`
- Package/path root: `src/main/java/forestry/compat`
- Java files scanned: **30**
- Date: 2026-07-30

## Summary
Module `compat` in `thedarkcolour-Immersive-Forestry` is rooted at `src/main/java/forestry/compat` (30 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModuleCurios`
- `CuriosCompat`
- `CuriosClientHandler`
- `SpectaclesCurioRenderer`
- `package-info`
- `package-info`
- `IndividualSubtypeInterpreter`
- `JeiUtil`
- `package-info`
- `ForestryKubeJsPlugin`
- `KubeForestryPlugin`
- `KubeActivityType`
- `KubeBeeEffect`
- `KubeFlowerType`
- `KubeHiveDefinition`
- `package-info`
- `ApicultureEventJS`
- `ForestryClientEventJS`
- `ForestryClientEvents`
- `ForestryEvents`
- `GeneticsEventJS`
- `package-info`
- `package-info`
- `package-info`
- `FluidComponent`
- `package-info`
- `package-info`
- `CarpenterProcessor`
- `FabricatorProcessor`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py IF "compat"`
- Source root exists: **True**
- Nested packages under this module:
  - `curios`
  - `curios/client`
  - `jei`
  - `kubejs`
  - `kubejs/apiculture`
  - `kubejs/event`
  - `patchouli`
  - `patchouli/component`
  - `patchouli/processor`
- Declaration skim (first files):
  - `src/main/java/forestry/compat/ModuleCurios.java`
    - L1: package forestry.compat;
    - L14: @ForestryModule
    - L15: public class ModuleCurios implements IForestryModule {
    - L16: @Override
    - L17: public ResourceLocation getId() {
    - L21: @Override
    - L22: public List<String> getModDependencies() {
    - L26: @Override
    - L27: public List<ResourceLocation> getModuleDependencies() {
    - L31: @Override
    - L32: public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
  - `src/main/java/forestry/compat/curios/CuriosCompat.java`
    - L1: package forestry.compat.curios;
    - L8: public class CuriosCompat {
    - L9: public static final boolean IS_LOADED = ModList.get().isLoaded("curios");
    - L11: public static boolean hasNaturalistEye(Player player) {
  - `src/main/java/forestry/compat/curios/client/CuriosClientHandler.java`
    - L1: package forestry.compat.curios.client;
    - L9: public class CuriosClientHandler implements IClientModuleHandler {
    - L10: @Override
    - L11: public void registerEvents(IEventBus modBus) {
  - `src/main/java/forestry/compat/curios/client/SpectaclesCurioRenderer.java`
    - L1: package forestry.compat.curios.client;
    - L23: public class SpectaclesCurioRenderer implements ICurioRenderer {
    - L28: public SpectaclesCurioRenderer() {
    - L38: @Override
    - L39: public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext context, PoseStack poseStack, RenderLayerParent<T, M> parent, MultiBufferSource buffers, int light, float limbSwing, floa
  - `src/main/java/forestry/compat/curios/client/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.compat.curios.client;
  - `src/main/java/forestry/compat/curios/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.compat.curios;
  - `src/main/java/forestry/compat/jei/IndividualSubtypeInterpreter.java`
    - L1: package forestry.compat.jei;
    - L11: public class IndividualSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    - L12: @Nullable
    - L13: @Override
    - L14: public Object getSubtypeData(ItemStack ingredient, UidContext context) {
    - L18: @Override
    - L19: public String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
  - `src/main/java/forestry/compat/jei/JeiUtil.java`
    - L1: package forestry.compat.jei;
    - L38: public class JeiUtil {
    - L39: public static final String DESCRIPTION_KEY = "for.jei.description.";
    - L48: public static void addDescription(IRecipeRegistration registry, String itemKey, FeatureItem<?>... items) {
    - L57: public static void addDescription(IRecipeRegistration registry, Block... blocks) {
    - L68: public static void addDescription(IRecipeRegistration registry, FeatureItem<?>... items) {
    - L74: public static void addDescription(IRecipeRegistration registry, Item... items) {
    - L80: public static void addDescription(IRecipeRegistration registry, Item item) {
    - L86: public static void addDescription(IRecipeRegistration registry, Item item, String itemKey) {
    - L91: public static List<IRecipeSlotBuilder> layoutSlotGrid(IRecipeLayoutBuilder builder, RecipeIngredientRole role, int width, int height, int xOffset, int yOffset, int slotSpacing) {
    - L102: public static void setCraftingItems(List<IRecipeSlotBuilder> craftingSlots, CraftingRecipe craftingGridRecipe, ICraftingGridHelper craftingGridHelper) {
    - L112: public static void setCraftingItems(List<IRecipeSlotBuilder> craftingSlots, List<Ingredient> ingredients, int width, int height, ICraftingGridHelper craftingGridHelper) {
  - `src/main/java/forestry/compat/jei/package-info.java`
    - L1: @javax.annotation.ParametersAreNonnullByDefault
    - L2: @forestry.core.utils.FieldsAreNonnullByDefault
    - L3: @net.minecraft.MethodsReturnNonnullByDefault
    - L4: package forestry.compat.jei;
  - `src/main/java/forestry/compat/kubejs/ForestryKubeJsPlugin.java`
    - L1: package forestry.compat.kubejs;
    - L19: public class ForestryKubeJsPlugin implements KubeJSPlugin {
    - L20: @Override
    - L21: public void registerEvents(EventGroupRegistry registry) {
    - L26: @Override
    - L27: public void registerBindings(BindingRegistry bindings) {
  - `src/main/java/forestry/compat/kubejs/KubeForestryPlugin.java`
    - L1: package forestry.compat.kubejs;
    - L17: public class KubeForestryPlugin implements IForestryPlugin {
    - L18: public static final ResourceLocation ID = ForestryConstants.forestry("kubejs");
    - L20: @Override
    - L21: public void registerGenetics(IGeneticRegistration genetics) {
    - L25: @Override
    - L26: public void registerApiculture(IApicultureRegistration apiculture) {
    - L30: @Override
    - L31: public void registerClient(Consumer<Consumer<IClientRegistration>> registrar) {
    - L35: @Override
    - L36: public ResourceLocation id() {
    - L40: @Override
  - `src/main/java/forestry/compat/kubejs/apiculture/KubeActivityType.java`
    - L1: package forestry.compat.kubejs.apiculture;
    - L8: public record KubeActivityType(IsActiveFunction isActive, InactiveErrorFunction inactiveErrorFunction,
    - L10: @Override
    - L11: public boolean isActive(long gameTime, long dayTime, BlockPos pos) {
    - L15: @Override
    - L16: public IError getInactiveError(long gameTime, long dayTime, BlockPos pos) {
    - L20: @Override
    - L21: public LightPreference getLightPreference() {
    - L25: @Override
    - L26: public boolean isDominant() {
    - L30: public interface IsActiveFunction {
    - L34: public interface InactiveErrorFunction {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-Immersive-Forestry` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModuleCurios` (`ModuleCurios.java`)
- key type `CuriosClientHandler` (`CuriosClientHandler.java`)
- key type `ForestryKubeJsPlugin` (`ForestryKubeJsPlugin.java`)
- key type `KubeForestryPlugin` (`KubeForestryPlugin.java`)
- interface `IsActiveFunction` in `KubeActivityType.java`
- interface `InactiveErrorFunction` in `KubeActivityType.java`
- record `KubeActivityType` in `KubeActivityType.java`
- record `KubeBeeEffect` in `KubeBeeEffect.java`
- interface `PlantRandomFlowerFunction` in `KubeFlowerType.java`
- record `KubeFlowerType` in `KubeFlowerType.java`
- interface `PostGenFunction` in `KubeHiveDefinition.java`
- record `KubeHiveDefinition` in `KubeHiveDefinition.java`
- interface `ForestryClientEvents` in `ForestryClientEvents.java`
- interface `ForestryEvents` in `ForestryEvents.java`

## Port relevance to Re-Forestry
- Primary Forestry reference for Re-Forestry port decisions.

## Source map
- `src/main/java/forestry/compat/ModuleCurios.java`
- `src/main/java/forestry/compat/curios/CuriosCompat.java`
- `src/main/java/forestry/compat/curios/client/CuriosClientHandler.java`
- `src/main/java/forestry/compat/curios/client/SpectaclesCurioRenderer.java`
- `src/main/java/forestry/compat/curios/client/package-info.java`
- `src/main/java/forestry/compat/curios/package-info.java`
- `src/main/java/forestry/compat/jei/IndividualSubtypeInterpreter.java`
- `src/main/java/forestry/compat/jei/JeiUtil.java`
- `src/main/java/forestry/compat/jei/package-info.java`
- `src/main/java/forestry/compat/kubejs/ForestryKubeJsPlugin.java`
- `src/main/java/forestry/compat/kubejs/KubeForestryPlugin.java`
- `src/main/java/forestry/compat/kubejs/apiculture/KubeActivityType.java`
- `src/main/java/forestry/compat/kubejs/apiculture/KubeBeeEffect.java`
- `src/main/java/forestry/compat/kubejs/apiculture/KubeFlowerType.java`
- `src/main/java/forestry/compat/kubejs/apiculture/KubeHiveDefinition.java`
- `src/main/java/forestry/compat/kubejs/apiculture/package-info.java`
- `src/main/java/forestry/compat/kubejs/event/ApicultureEventJS.java`
- `src/main/java/forestry/compat/kubejs/event/ForestryClientEventJS.java`
- `src/main/java/forestry/compat/kubejs/event/ForestryClientEvents.java`
- `src/main/java/forestry/compat/kubejs/event/ForestryEvents.java`
- `src/main/java/forestry/compat/kubejs/event/GeneticsEventJS.java`
- `src/main/java/forestry/compat/kubejs/event/package-info.java`
- `src/main/java/forestry/compat/kubejs/package-info.java`
- `src/main/java/forestry/compat/package-info.java`
- `src/main/java/forestry/compat/patchouli/component/FluidComponent.java`
- `src/main/java/forestry/compat/patchouli/component/package-info.java`
- `src/main/java/forestry/compat/patchouli/package-info.java`
- `src/main/java/forestry/compat/patchouli/processor/CarpenterProcessor.java`
- `src/main/java/forestry/compat/patchouli/processor/FabricatorProcessor.java`
- `src/main/java/forestry/compat/patchouli/processor/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
