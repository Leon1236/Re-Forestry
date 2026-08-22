# bernie-g-geckolib — mixin

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/mixin`
- Java files scanned: **19**
- Date: 2026-07-30

## Summary
Module `mixin` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/mixin` (19 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BlockEntityRenderStateMixin`
- `EntityRenderStateMixin`
- `EntityRendererMixin`
- `HumanoidArmorLayerMixin`
- `HumanoidModelMixin`
- `ModelFeatureRendererMixin`
- `ModelMixin`
- `PlayerModelMixin`
- `SpecialModelRenderersMixin`
- `SpecialModelWrapperMixin`
- `TextureManagerMixin`
- `package-info`
- `AbstractContainerMenuMixin`
- `HashedStackMixin`
- `ItemStackMixin`
- `LivingEntityMixin`
- `SlotMixin`
- `SynchronizedRemoteSlotMixin`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "mixin"`
- Source root exists: **True**
- Nested packages under this module:
  - `client`
  - `common`
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/mixin/client/BlockEntityRenderStateMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L16: @SuppressWarnings("AddedMixinMembersNamePattern")
    - L17: @Mixin(BlockEntityRenderState.class)
    - L18: public class BlockEntityRenderStateMixin implements GeoRenderState {
    - L19: @Shadow
    - L20: public int lightCoords;
    - L21: @Unique
    - L24: @Unique
    - L25: @Override
    - L26: public <D> void addGeckolibData(DataTicket<D> dataTicket, D data) {
    - L30: @Unique
    - L31: @Override
  - `common/src/main/java/com/geckolib/mixin/client/EntityRenderStateMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L16: @SuppressWarnings("AddedMixinMembersNamePattern")
    - L17: @Mixin(EntityRenderState.class)
    - L18: public class EntityRenderStateMixin implements GeoRenderState {
    - L19: @Shadow
    - L20: public int lightCoords;
    - L21: @Shadow
    - L22: public float ageInTicks;
    - L23: @Unique
    - L26: @Unique
    - L27: @Override
    - L28: public <D> void addGeckolibData(DataTicket<D> dataTicket, D data) {
  - `common/src/main/java/com/geckolib/mixin/client/EntityRendererMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L18: @Mixin(value = EntityRenderer.class, priority = 5000)
    - L19: public class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {
    - L22: @SuppressWarnings({"ConstantValue", "rawtypes", "unchecked"})
    - L23: @WrapMethod(method = "createRenderState(Lnet/minecraft/world/entity/Entity;F)Lnet/minecraft/client/renderer/entity/state/EntityRenderState;")
    - L24: public S geckolib$captureDataForArmorLayer(T entity, float partialTick, Operation<S> original) {
    - L43: @SuppressWarnings("unchecked")
    - L44: @Unique
  - `common/src/main/java/com/geckolib/mixin/client/HumanoidArmorLayerMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L18: @Mixin(HumanoidArmorLayer.class)
    - L19: public abstract class HumanoidArmorLayerMixin<S extends HumanoidRenderState, M extends HumanoidModel<S>, A extends HumanoidModel<S>> extends RenderLayer<S, M> {
    - L20: public HumanoidArmorLayerMixin(RenderLayerParent<S, M> renderer) {
    - L27: @SuppressWarnings({"rawtypes", "unchecked"})
    - L28: @WrapWithCondition(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V",
    - L32: public boolean geckolib$wrapArmorPieceRender(HumanoidArmorLayer<S, M, A> layer, PoseStack poseStack, SubmitNodeCollector renderTasks, ItemStack stack, EquipmentSlot slot, int packedLight, S entityRenderState) {
  - `common/src/main/java/com/geckolib/mixin/client/HumanoidModelMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L14: @SuppressWarnings("ConstantValue")
    - L15: @Mixin(HumanoidModel.class)
    - L16: public class HumanoidModelMixin<T extends HumanoidRenderState> {
    - L18: @SuppressWarnings("unchecked")
    - L19: @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At("TAIL"))
    - L20: public void geckolib$callModelSetupCallbacks(T instance, CallbackInfo ci) {
  - `common/src/main/java/com/geckolib/mixin/client/ModelFeatureRendererMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L12: @Mixin(ModelFeatureRenderer.class)
    - L13: public class ModelFeatureRendererMixin {
    - L15: @Inject(method = "prepareModel", at = @At("TAIL"))
    - L16: public <S> void geckolib$injectModelCleanup(ModelFeatureRenderer.Submit<S> submit, CallbackInfo ci) {
  - `common/src/main/java/com/geckolib/mixin/client/ModelMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L12: @SuppressWarnings("ConstantValue")
    - L13: @Mixin(Model.class)
    - L14: public class ModelMixin<S> {
    - L16: @SuppressWarnings("unchecked")
    - L17: @Inject(method = "setupAnim", at = @At("TAIL"))
    - L18: public void geckolib$callModelSetupCallbacks(S instance, CallbackInfo ci) {
  - `common/src/main/java/com/geckolib/mixin/client/PlayerModelMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L13: @SuppressWarnings("ConstantValue")
    - L14: @Mixin(PlayerModel.class)
    - L15: public class PlayerModelMixin<T extends AvatarRenderState> {
    - L17: @SuppressWarnings("unchecked")
    - L18: @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V", at = @At("TAIL"))
    - L19: public void geckolib$callModelSetupCallbacks(T instance, CallbackInfo ci) {
  - `common/src/main/java/com/geckolib/mixin/client/SpecialModelRenderersMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L17: @Mixin(SpecialModelRenderers.class)
    - L18: public class SpecialModelRenderersMixin {
    - L19: @Shadow
    - L20: @Final
    - L24: @Inject(method = "bootstrap", at = @At("TAIL"))
  - `common/src/main/java/com/geckolib/mixin/client/SpecialModelWrapperMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L18: @Mixin(SpecialModelWrapper.class)
    - L19: public class SpecialModelWrapperMixin {
    - L21: @SuppressWarnings({"unchecked", "rawtypes"})
    - L22: @WrapOperation(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/special/SpecialModelRenderer;extractArgument(Lnet/minecraft/world/item/ItemStack;)Ljava/lang/Object;"))
    - L23: public <T> @Nullable T geckolib$extractAllArguments(SpecialModelRenderer<T> instance, ItemStack itemStack, Operation<T> original,
  - `common/src/main/java/com/geckolib/mixin/client/TextureManagerMixin.java`
    - L1: package com.geckolib.mixin.client;
    - L17: @Mixin(value = TextureManager.class, priority = 2000)
    - L18: public abstract class TextureManagerMixin {
    - L19: @Shadow protected abstract TextureContents loadContentsSafe(Identifier textureId, ReloadableTexture texture);
    - L21: @Shadow public abstract void register(Identifier path, AbstractTexture texture);
    - L24: @WrapOperation(method = "getTexture(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/texture/AbstractTexture;",
    - L45: @WrapWithCondition(method = "getTexture(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/texture/AbstractTexture;",
  - `common/src/main/java/com/geckolib/mixin/client/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.mixin.client;

## Data & assets
Related resource paths (heuristic name match):
- `common/src/main/resources/geckolib.mixins.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/mixin/client/BlockEntityRenderStateMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/EntityRenderStateMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/EntityRendererMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/HumanoidArmorLayerMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/HumanoidModelMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/ModelFeatureRendererMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/ModelMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/PlayerModelMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/SpecialModelRenderersMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/SpecialModelWrapperMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/TextureManagerMixin.java`
- `common/src/main/java/com/geckolib/mixin/client/package-info.java`
- `common/src/main/java/com/geckolib/mixin/common/AbstractContainerMenuMixin.java`
- `common/src/main/java/com/geckolib/mixin/common/HashedStackMixin.java`
- `common/src/main/java/com/geckolib/mixin/common/ItemStackMixin.java`
- `common/src/main/java/com/geckolib/mixin/common/LivingEntityMixin.java`
- `common/src/main/java/com/geckolib/mixin/common/SlotMixin.java`
- `common/src/main/java/com/geckolib/mixin/common/SynchronizedRemoteSlotMixin.java`
- `common/src/main/java/com/geckolib/mixin/common/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
