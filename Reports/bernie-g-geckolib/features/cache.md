# bernie-g-geckolib — cache

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/cache`
- Java files scanned: **27**
- Date: 2026-07-30

## Summary
Module `cache` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/cache` (27 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `AnimatableIdCache`
- `BakedAnimationCache`
- `BakedModelCache`
- `GeckoLibResources`
- `SyncedSingletonAnimatableCache`
- `Animation`
- `BakedAnimations`
- `BoneAnimation`
- `Keyframe`
- `KeyframeStack`
- `CustomInstructionKeyframeData`
- `KeyFrameData`
- `ParticleKeyframeData`
- `SoundKeyframeData`
- `package-info`
- `package-info`
- `BakedGeoModel`
- `GeoBone`
- `GeoLocator`
- `GeoQuad`
- `GeoVertex`
- `ModelProperties`
- `CuboidGeoBone`
- `GeoCube`
- `package-info`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "cache"`
- Source root exists: **True**
- Nested packages under this module:
  - `animation`
  - `animation/keyframeevent`
  - `model`
  - `model/cuboid`
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/cache/AnimatableIdCache.java`
    - L1: package com.geckolib.cache;
    - L11: /// Storage class that keeps track of the last animatable id used, and provides new ones on request
    - L14: public final class AnimatableIdCache extends SavedData {
    - L18: @SuppressWarnings("DataFlowIssue")
    - L19: public static final SavedDataType<AnimatableIdCache> TYPE = new SavedDataType<>(GeckoLibConstants.id("animatable_id_ticker"), AnimatableIdCache::new, CODEC, null);
    - L35: public static long getFreeId(ServerLevel level) {
  - `common/src/main/java/com/geckolib/cache/BakedAnimationCache.java`
    - L1: package com.geckolib.cache;
    - L15: public record BakedAnimationCache(Map<Identifier, BakedAnimations> cache) {
    - L17: public int size() {
    - L27: public @Nullable Animation getAnimation(Identifier animationFile, Identifier[] fallbackFiles, String animationName) {
    - L67: @ApiStatus.Internal
    - L68: @Deprecated(forRemoval = true)
  - `common/src/main/java/com/geckolib/cache/BakedModelCache.java`
    - L1: package com.geckolib.cache;
    - L20: public record BakedModelCache(Map<Identifier, BakedGeoModel> cache) {
    - L21: public static final Supplier<BakedGeoModel> MISSINGNO = createMissingModel();
    - L24: public int size() {
    - L31: public BakedGeoModel getModel(Identifier modelFile) {
    - L57: @ApiStatus.Internal
    - L58: @Deprecated(forRemoval = true)
  - `common/src/main/java/com/geckolib/cache/GeckoLibResources.java`
    - L1: package com.geckolib.cache;
    - L35: /// Cache class for holding loaded [Animations][Animation] and [Models][GeoModel]
    - L36: public final class GeckoLibResources implements PreparableReloadListener {
    - L37: public static final Identifier RELOAD_LISTENER_ID = GeckoLibConstants.id("geckolib_resources");
    - L38: public static final Identifier ANIMATIONS_PATH = GeckoLibConstants.id("geckolib/animations");
    - L39: public static final Identifier MODELS_PATH = GeckoLibConstants.id("geckolib/models");
    - L40: public static final Pattern SUFFIX_STRIPPER = Pattern.compile("((\\.geo)|((\\.animation)s?))?(\\.json)$");
    - L41: public static final Pattern PREFIX_STRIPPER = Pattern.compile("^(geckolib/)((animations/)|(models/))?");
    - L42: public static final PreparableReloadListener.StateKey<PendingResources> STATE_KEY = new PreparableReloadListener.StateKey<>();
    - L43: @SuppressWarnings("unchecked")
    - L51: public static BakedAnimationCache getBakedAnimations() {
    - L56: public static BakedModelCache getBakedModels() {
  - `common/src/main/java/com/geckolib/cache/SyncedSingletonAnimatableCache.java`
    - L1: package com.geckolib.cache;
    - L14: /// Caching class for [SingletonGeoAnimatable]s that have been registered as syncable
    - L15: public final class SyncedSingletonAnimatableCache {
    - L22: @ApiStatus.Internal
    - L23: public static void registerSyncedAnimatable(SingletonGeoAnimatable animatable) {
    - L35: @ApiStatus.Internal
    - L36: public static @Nullable GeoAnimatable getSyncedAnimatable(String syncedAnimatableId) {
    - L48: /// as this method eliminates class duplication collisions
    - L49: public static String getOrCreateId(SingletonGeoAnimatable animatable) {
  - `common/src/main/java/com/geckolib/cache/animation/Animation.java`
    - L1: package com.geckolib.cache.animation;
    - L19: @SuppressWarnings("ClassCanBeRecord")
    - L20: public class Animation {
    - L21: protected final String name;
    - L22: protected final double length;
    - L23: protected final LoopType loopType;
    - L24: protected final BoneAnimation[] boneAnimations;
    - L25: protected final Set<Variable> usedVariables;
    - L26: protected final KeyframeMarkers keyframeMarkers;
    - L28: public Animation(String name, double length, LoopType loopType, BoneAnimation[] boneAnimations, Set<Variable> usedVariables, KeyframeMarkers keyframeMarkers) {
    - L38: public String name() {
    - L43: public double length() {
  - `common/src/main/java/com/geckolib/cache/animation/BakedAnimations.java`
    - L1: package com.geckolib.cache.animation;
    - L10: public record BakedAnimations(Map<String, Animation> animations) {
    - L12: public @Nullable Animation getAnimation(String name) {
  - `common/src/main/java/com/geckolib/cache/animation/BoneAnimation.java`
    - L1: package com.geckolib.cache.animation;
    - L16: public record BoneAnimation(String boneName, KeyframeStack rotationKeyFrames, KeyframeStack positionKeyFrames, KeyframeStack scaleKeyFrames) {
    - L18: public Set<Variable> getUsedVariables() {
  - `common/src/main/java/com/geckolib/cache/animation/Keyframe.java`
    - L1: package com.geckolib.cache.animation;
    - L22: public record Keyframe(double startTime, double length, MathValue startValue, MathValue endValue, EasingType easingType, MathValue[] easingArgs) {
    - L23: public Keyframe(double startTime, double length, MathValue startValue, MathValue endValue) {
    - L27: public Keyframe(double startTime, double length, MathValue startValue, MathValue endValue, EasingType easingType) {
    - L31: public Keyframe(double startTime, double length, MathValue startValue, MathValue endValue, EasingType easingType, List<MathValue> easingArgs) {
    - L36: public Set<Variable> getUsedVariables() {
    - L53: @Override
    - L54: public int hashCode() {
    - L58: @Override
    - L59: public boolean equals(Object obj) {
  - `common/src/main/java/com/geckolib/cache/animation/KeyframeStack.java`
    - L1: package com.geckolib.cache.animation;
    - L14: public record KeyframeStack(Keyframe[] xKeyframes, Keyframe[] yKeyframes, Keyframe[] zKeyframes) {
    - L15: public static final KeyframeStack EMPTY = new KeyframeStack(new Keyframe[0], new Keyframe[0], new Keyframe[0]);
    - L17: public KeyframeStack(List<Keyframe> xKeyframes, List<Keyframe> yKeyframes, List<Keyframe> zKeyframes) {
    - L22: public Set<Variable> getUsedVariables() {
    - L40: public double getTotalKeyframeTime() {
  - `common/src/main/java/com/geckolib/cache/animation/keyframeevent/CustomInstructionKeyframeData.java`
    - L1: package com.geckolib.cache.animation.keyframeevent;
    - L8: public class CustomInstructionKeyframeData extends KeyFrameData {
    - L11: public CustomInstructionKeyframeData(double time, String instructions) {
    - L18: public String getInstructions() {
    - L22: @Override
    - L23: public int hashCode() {
  - `common/src/main/java/com/geckolib/cache/animation/keyframeevent/KeyFrameData.java`
    - L1: package com.geckolib.cache.animation.keyframeevent;
    - L8: /// Base class for custom [Keyframe] events
    - L12: public abstract class KeyFrameData {
    - L16: public KeyFrameData(double time, @Nullable String locatorName) {
    - L22: public double getTime() {
    - L29: public @Nullable String getLocatorName() {
    - L33: @Override
    - L34: public boolean equals(Object obj) {
    - L44: @Override
    - L45: public int hashCode() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `BakedAnimationCache` in `BakedAnimationCache.java`
- record `BakedModelCache` in `BakedModelCache.java`
- record `PendingResources` in `GeckoLibResources.java`
- record `KeyframeMarkers` in `Animation.java`
- record `BakedAnimations` in `BakedAnimations.java`
- record `BoneAnimation` in `BoneAnimation.java`
- record `Keyframe` in `Keyframe.java`
- record `KeyframeStack` in `KeyframeStack.java`
- record `GeoQuad` in `GeoQuad.java`
- record `GeoVertex` in `GeoVertex.java`
- record `GeoCube` in `GeoCube.java`

## Port relevance to Re-Forestry
- Mentions of `cache` appear in `files/implemented-features.md` — check that file for port status.
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/cache/AnimatableIdCache.java`
- `common/src/main/java/com/geckolib/cache/BakedAnimationCache.java`
- `common/src/main/java/com/geckolib/cache/BakedModelCache.java`
- `common/src/main/java/com/geckolib/cache/GeckoLibResources.java`
- `common/src/main/java/com/geckolib/cache/SyncedSingletonAnimatableCache.java`
- `common/src/main/java/com/geckolib/cache/animation/Animation.java`
- `common/src/main/java/com/geckolib/cache/animation/BakedAnimations.java`
- `common/src/main/java/com/geckolib/cache/animation/BoneAnimation.java`
- `common/src/main/java/com/geckolib/cache/animation/Keyframe.java`
- `common/src/main/java/com/geckolib/cache/animation/KeyframeStack.java`
- `common/src/main/java/com/geckolib/cache/animation/keyframeevent/CustomInstructionKeyframeData.java`
- `common/src/main/java/com/geckolib/cache/animation/keyframeevent/KeyFrameData.java`
- `common/src/main/java/com/geckolib/cache/animation/keyframeevent/ParticleKeyframeData.java`
- `common/src/main/java/com/geckolib/cache/animation/keyframeevent/SoundKeyframeData.java`
- `common/src/main/java/com/geckolib/cache/animation/keyframeevent/package-info.java`
- `common/src/main/java/com/geckolib/cache/animation/package-info.java`
- `common/src/main/java/com/geckolib/cache/model/BakedGeoModel.java`
- `common/src/main/java/com/geckolib/cache/model/GeoBone.java`
- `common/src/main/java/com/geckolib/cache/model/GeoLocator.java`
- `common/src/main/java/com/geckolib/cache/model/GeoQuad.java`
- `common/src/main/java/com/geckolib/cache/model/GeoVertex.java`
- `common/src/main/java/com/geckolib/cache/model/ModelProperties.java`
- `common/src/main/java/com/geckolib/cache/model/cuboid/CuboidGeoBone.java`
- `common/src/main/java/com/geckolib/cache/model/cuboid/GeoCube.java`
- `common/src/main/java/com/geckolib/cache/model/cuboid/package-info.java`
- `common/src/main/java/com/geckolib/cache/model/package-info.java`
- `common/src/main/java/com/geckolib/cache/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
