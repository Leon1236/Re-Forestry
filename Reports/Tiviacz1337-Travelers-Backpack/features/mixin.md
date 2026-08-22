# Tiviacz1337-Travelers-Backpack — mixin

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/mixin`
- Java files scanned: **18**
- Date: 2026-07-30

## Summary
Module `mixin` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/mixin` (18 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `AbstractContainerMenuMixin`
- `AbstractContainerScreenMixin`
- `GuiMixin`
- `HumanoidRenderStateMixin`
- `HumoanoidMobRenderer`
- `ItemEntityMixin`
- `ItemStackMixin`
- `LevelResourceMixin`
- `LivingEntityPriorityMixin`
- `LocalPlayerMixin`
- `MobEntityMixin`
- `PlayerMixin`
- `ShulkerBoxBlockEntityMixin`
- `EndermanEntityMixin`
- `ExperienceOrbEntityMixin`
- `GhastEntityMixin`
- `LootTableMixin`
- `SmallFireballEntityMixin`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "mixin"`
- Source root exists: **True**
- Nested packages under this module:
  - `abilities`
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/AbstractContainerMenuMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L15: @Mixin(AbstractContainerMenu.class)
    - L16: public class AbstractContainerMenuMixin {
    - L18: @Redirect(method = "tryItemClickBehaviourOverride", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;overrideOtherStackedOnMe(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/Slo
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/AbstractContainerScreenMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L11: @Mixin(AbstractContainerScreen.class)
    - L12: public class AbstractContainerScreenMixin {
    - L13: @Inject(at = @At(value = "TAIL"), method = "extractContents")
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/GuiMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L14: @Mixin(Gui.class)
    - L15: public class GuiMixin {
    - L16: @Inject(method = "extractCrosshair", at = @At("HEAD"), cancellable = true)
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/HumanoidRenderStateMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L10: @Mixin(HumanoidRenderState.class)
    - L11: public class HumanoidRenderStateMixin implements HumanoidRenderStateBackpackInject {
    - L12: @Shadow
    - L13: public ItemStack chestEquipment;
    - L14: public ItemStack backpack;
    - L15: @Nullable
    - L16: public String name;
    - L18: @Override
    - L19: public void setBackpackStack(ItemStack stack) {
    - L23: @Override
    - L24: public ItemStack getBackpackStack() {
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/HumoanoidMobRenderer.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L23: @Mixin(HumanoidMobRenderer.class)
    - L24: public class HumoanoidMobRenderer {
    - L25: @Inject(at = @At(value = "TAIL"), method = "extractHumanoidRenderState")
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/ItemEntityMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L16: @Mixin(ItemEntity.class)
    - L17: public abstract class ItemEntityMixin {
    - L18: @Shadow
    - L19: public abstract ItemStack getItem();
    - L21: @Shadow
    - L22: public int pickupDelay;
    - L24: @Shadow
    - L25: public abstract void setItem(ItemStack stack);
    - L27: @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I"), cancellable = true)
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/ItemStackMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L15: @Mixin(ItemStack.class)
    - L16: public class ItemStackMixin {
    - L18: @Inject(method = "overrideOtherStackedOnMe", at = @At(value = "TAIL"))
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/LevelResourceMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L7: @Mixin(LevelResource.class)
    - L8: public interface LevelResourceMixin {
    - L9: @Invoker("<init>")
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/LivingEntityPriorityMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L14: @Mixin(value = LivingEntity.class, priority = 0)
    - L15: public class LivingEntityPriorityMixin {
    - L16: @Inject(method = "dropAllDeathLoot", at = @At("HEAD"))
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/LocalPlayerMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L17: @Mixin(LocalPlayer.class)
    - L18: public abstract class LocalPlayerMixin {
    - L19: @Shadow
    - L20: public ClientInput input;
    - L22: @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/ClientInput;tick()V", shift = At.Shift.AFTER))
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/MobEntityMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L26: @Mixin(Mob.class)
    - L27: public abstract class MobEntityMixin extends LivingEntity {
    - L29: protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, Level level) {
    - L33: @Inject(at = @At(value = "TAIL"), method = "finalizeSpawn")
    - L34: protected void initialize(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
  - `src/main/java/com/tiviacz/travelersbackpack/mixin/PlayerMixin.java`
    - L1: package com.tiviacz.travelersbackpack.mixin;
    - L34: @Mixin(Player.class)
    - L35: public abstract class PlayerMixin extends LivingEntity {
    - L36: @Shadow
    - L37: @Final
    - L40: protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
    - L52: @Inject(at = @At(value = "TAIL"), method = "tick")
    - L89: @Inject(at = @At(value = "HEAD"), method = "attack")
    - L100: @Inject(method = "getFlyingSpeed", at = @At(value = "RETURN"), cancellable = true)
    - L101: protected void getFlyingSpeed(CallbackInfoReturnable<Float> cir) {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/travelersbackpack.mixins.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `LevelResourceMixin` in `LevelResourceMixin.java`

## Port relevance to Re-Forestry
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/mixin/AbstractContainerMenuMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/AbstractContainerScreenMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/GuiMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/HumanoidRenderStateMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/HumoanoidMobRenderer.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/ItemEntityMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/ItemStackMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/LevelResourceMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/LivingEntityPriorityMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/LocalPlayerMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/MobEntityMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/PlayerMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/ShulkerBoxBlockEntityMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/abilities/EndermanEntityMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/abilities/ExperienceOrbEntityMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/abilities/GhastEntityMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/abilities/LootTableMixin.java`
- `src/main/java/com/tiviacz/travelersbackpack/mixin/abilities/SmallFireballEntityMixin.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
