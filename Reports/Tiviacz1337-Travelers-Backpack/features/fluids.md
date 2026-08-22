# Tiviacz1337-Travelers-Backpack — fluids

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/fluids`
- Java files scanned: **10**
- Date: 2026-07-30

## Summary
Module `fluids` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/fluids` (10 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `EffectFluidRegistry`
- `MilkFluid`
- `PotionFluid`
- `LavaEffect`
- `MilkEffect`
- `PotionEffect`
- `WaterEffect`
- `MilkFluidVariantAttributeHandler`
- `PotionFluidVariantAttributeHandler`
- `PotionFluidVariantRenderHandler`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "fluids"`
- Source root exists: **True**
- Nested packages under this module:
  - `effects`
  - `milk`
  - `potion`
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/EffectFluidRegistry.java`
    - L1: package com.tiviacz.travelersbackpack.fluids;
    - L28: public class EffectFluidRegistry {
    - L29: public static BiMap<String, EffectFluid> EFFECT_REGISTRY = HashBiMap.create();
    - L31: public static EffectFluid WATER_EFFECT;
    - L32: public static EffectFluid LAVA_EFFECT;
    - L33: public static EffectFluid POTION_EFFECT;
    - L34: public static EffectFluid MILK_EFFECT;
    - L37: public static EffectFluid TAN_POTION_EFFECT;
    - L38: public static EffectFluid TAN_WATER_CANTEEN_EFFECT;
    - L39: public static EffectFluid TAN_WATER_EFFECT;
    - L43: public static void initEffects() {
    - L58: public static int registerFluidEffect(EffectFluid effect) {
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/MilkFluid.java`
    - L1: package com.tiviacz.travelersbackpack.fluids;
    - L3: /*public abstract class MilkFluid extends FlowingFluid {
    - L5: @Override
    - L6: public FlowingFluid getFlowing() {
    - L10: @Override
    - L11: public FlowingFluid getSource() {
    - L15: @Override
    - L16: public Item getBucket() {
    - L20: @Nullable
    - L21: @Override
    - L22: public ParticleOptions getDripParticle() {
    - L26: @Override
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/PotionFluid.java`
    - L1: package com.tiviacz.travelersbackpack.fluids;
    - L28: public abstract class PotionFluid extends FlowingFluid {
    - L29: @Override
    - L30: public FlowingFluid getFlowing() {
    - L34: @Override
    - L35: public FlowingFluid getSource() {
    - L39: @Override
    - L40: public Item getBucket() {
    - L44: @Override
    - L45: @Nullable
    - L46: public ParticleOptions getDripParticle() {
    - L50: @Override
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/LavaEffect.java`
    - L1: package com.tiviacz.travelersbackpack.fluids.effects;
    - L13: public class LavaEffect extends EffectFluid {
    - L14: public LavaEffect() {
    - L18: @Override
    - L19: public void affectDrinker(FluidVariantWrapper fluidStack, Level level, Entity entity) {
    - L30: @Override
    - L31: public boolean canExecuteEffect(FluidVariantWrapper stack, Level level, Entity entity) {
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/MilkEffect.java`
    - L1: package com.tiviacz.travelersbackpack.fluids.effects;
    - L10: public class MilkEffect extends EffectFluid {
    - L11: public MilkEffect() {
    - L15: @Override
    - L16: public void affectDrinker(FluidVariantWrapper fluidStack, Level level, Entity entity) {
    - L22: @Override
    - L23: public boolean canExecuteEffect(FluidVariantWrapper stack, Level level, Entity entity) {
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/PotionEffect.java`
    - L1: package com.tiviacz.travelersbackpack.fluids.effects;
    - L14: public class PotionEffect extends EffectFluid {
    - L15: public PotionEffect(String uniqueId, Fluid fluid) {
    - L19: public PotionEffect(String uniqueId, String modid, String fluidName) {
    - L23: @Override
    - L24: public void affectDrinker(FluidVariantWrapper stack, Level level, Entity entity) {
    - L36: @Override
    - L37: public boolean canExecuteEffect(FluidVariantWrapper stack, Level level, Entity entity) {
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/WaterEffect.java`
    - L1: package com.tiviacz.travelersbackpack.fluids.effects;
    - L15: public class WaterEffect extends EffectFluid {
    - L16: public WaterEffect() {
    - L20: @Override
    - L21: public void affectDrinker(FluidVariantWrapper fluidStack, Level level, Entity entity) {
    - L35: @Override
    - L36: public boolean canExecuteEffect(FluidVariantWrapper stack, Level level, Entity entity) {
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/milk/MilkFluidVariantAttributeHandler.java`
    - L1: package com.tiviacz.travelersbackpack.fluids.milk;
    - L3: /*public class MilkFluidVariantAttributeHandler implements FluidVariantAttributeHandler {
    - L4: @Override
    - L5: public Component getName(FluidVariant fluidVariant) {
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/potion/PotionFluidVariantAttributeHandler.java`
    - L1: package com.tiviacz.travelersbackpack.fluids.potion;
    - L9: public class PotionFluidVariantAttributeHandler implements FluidVariantAttributeHandler {
    - L10: @Override
    - L11: public Component getName(FluidVariant fluidVariant) {
    - L15: public String getTranslationKey(FluidVariant fluidVariant) {
  - `src/main/java/com/tiviacz/travelersbackpack/fluids/potion/PotionFluidVariantRenderHandler.java`
    - L1: package com.tiviacz.travelersbackpack.fluids.potion;
    - L13: @Environment(EnvType.CLIENT)
    - L14: public class PotionFluidVariantRenderHandler implements FluidVariantRenderHandler {
    - L17: @Override
    - L18: public int getColor(FluidVariant fluidVariant, @Nullable BlockAndTintGetter view, @Nullable BlockPos pos) {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `EffectFluidRegistry` (`EffectFluidRegistry.java`)
- key type `MilkFluidVariantAttributeHandler` (`MilkFluidVariantAttributeHandler.java`)
- key type `PotionFluidVariantAttributeHandler` (`PotionFluidVariantAttributeHandler.java`)
- key type `PotionFluidVariantRenderHandler` (`PotionFluidVariantRenderHandler.java`)

## Port relevance to Re-Forestry
- Mentions of `fluids` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/fluids/EffectFluidRegistry.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/MilkFluid.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/PotionFluid.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/LavaEffect.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/MilkEffect.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/PotionEffect.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/effects/WaterEffect.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/milk/MilkFluidVariantAttributeHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/potion/PotionFluidVariantAttributeHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/fluids/potion/PotionFluidVariantRenderHandler.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
