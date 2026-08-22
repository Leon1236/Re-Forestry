# thedarkcolour-ForestryCE — compat

- Alias: `CE`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/thedarkcolour-ForestryCE`
- Package/path root: `src/main/java/forestry/compat`
- Java files scanned: **27**
- Date: 2026-07-30

## Summary
Module `compat` in `thedarkcolour-ForestryCE` is rooted at `src/main/java/forestry/compat` (27 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModuleCurios`
- `CuriosCompat`
- `CuriosClientHandler`
- `SpectaclesCurioRenderer`
- `package-info`
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
- Graph follow-up: `python3 tools/graphify_query.py CE "compat"`
- Source root exists: **True**
- Nested packages under this module:
  - `curios`
  - `curios/client`
  - `kubejs`
  - `kubejs/apiculture`
  - `kubejs/event`
  - `patchouli`
  - `patchouli/component`
  - `patchouli/processor`
- Declaration skim (first files):
  - `src/main/java/forestry/compat/ModuleCurios.java`
    - L1: package forestry.compat;
    - L13: @ForestryModule
    - L14: public class ModuleCurios implements IForestryModule {
    - L15: @Override
    - L16: public ResourceLocation getId() {
    - L20: @Override
    - L21: public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
  - `src/main/java/forestry/compat/curios/CuriosCompat.java`
    - L1: package forestry.compat.curios;
    - L12: public class CuriosCompat {
    - L13: public static final boolean IS_LOADED = ModList.get().isLoaded("curios");
    - L15: public static final Capability<ICuriosItemHandler> CURIOS_INVENTORY = CapabilityManager.get(new CapabilityToken<>() {
    - L18: public static boolean hasNaturalistEye(Player player) {
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
  - `src/main/java/forestry/compat/kubejs/ForestryKubeJsPlugin.java`
    - L1: package forestry.compat.kubejs;
    - L18: public class ForestryKubeJsPlugin extends KubeJSPlugin {
    - L19: @Override
    - L20: public void registerEvents() {
    - L25: @Override
    - L26: public void registerBindings(BindingsEvent event) {
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
  - `src/main/java/forestry/compat/kubejs/apiculture/KubeBeeEffect.java`
    - L1: package forestry.compat.kubejs.apiculture;
    - L11: public record KubeBeeEffect(UnaryOperator<IEffectData> validateStorage, boolean combinable,
    - L15: @Override
    - L16: public IEffectData validateStorage(IEffectData storedData) {
    - L20: @Override
    - L21: public boolean isCombinable() {
    - L25: @Override
    - L26: public IEffectData doEffect(IGenome genome, IEffectData storedData, IBeeHousing housing) {
    - L30: @Override
    - L31: public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
    - L35: @Override
    - L36: public boolean isDominant() {
  - `src/main/java/forestry/compat/kubejs/apiculture/KubeFlowerType.java`
    - L1: package forestry.compat.kubejs.apiculture;
    - L11: public record KubeFlowerType(BiPredicate<Level, BlockPos> isAcceptableFlower,
    - L13: @Override
    - L14: public boolean isAcceptableFlower(Level level, BlockPos pos) {
    - L18: @Override
    - L19: public boolean plantRandomFlower(Level level, BlockPos pos, List<BlockState> nearbyFlowers) {
    - L23: @Override
    - L24: public boolean isDominant() {
    - L28: public interface PlantRandomFlowerFunction {
  - `src/main/java/forestry/compat/kubejs/apiculture/KubeHiveDefinition.java`
    - L1: package forestry.compat.kubejs.apiculture;
    - L17: public record KubeHiveDefinition(IHiveGen placement, BlockState hiveState, Predicate<Holder<Biome>> isGoodBiome,
    - L20: @Override
    - L21: public IHiveGen getHiveGen() {
    - L25: @Override
    - L26: public BlockState getBlockState() {
    - L30: @Override
    - L31: public boolean isGoodBiome(Holder<Biome> biome) {
    - L35: @Override
    - L36: public boolean isGoodHumidity(HumidityType humidity) {
    - L40: @Override
    - L41: public boolean isGoodTemperature(TemperatureType temperature) {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/thedarkcolour-ForestryCE` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

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
