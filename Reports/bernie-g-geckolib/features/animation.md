# bernie-g-geckolib — animation

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/animation`
- Java files scanned: **18**
- Date: 2026-07-30

## Summary
Module `animation` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/animation` (18 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `AnimationController`
- `AnimationProcessor`
- `RawAnimation`
- `AutoPlayingSoundKeyframeHandler`
- `package-info`
- `EasingType`
- `LoopType`
- `PlayState`
- `package-info`
- `package-info`
- `AnimationPoint`
- `AnimationTest`
- `AnimationTimeline`
- `BoneSnapshot`
- `ControllerState`
- `EasingState`
- `KeyFrameEvent`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "animation"`
- Source root exists: **True**
- Nested packages under this module:
  - `keyframehandler`
  - `object`
  - `state`
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/animation/AnimationController.java`
    - L1: package com.geckolib.animation;
    - L40: @SuppressWarnings("UnusedReturnValue")
    - L41: public class AnimationController<T extends GeoAnimatable> {
    - L42: protected final String name;
    - L43: protected final AnimationStateHandler<T> stateHandler;
    - L44: protected final Supplier<Map<String, RawAnimation>> triggerableAnimations = Suppliers.memoize(Object2ObjectOpenHashMap::new);
    - L46: protected @Nullable KeyframeEventHandler<T, SoundKeyframeData> soundKeyframeHandler = null;
    - L47: protected @Nullable KeyframeEventHandler<T, ParticleKeyframeData> particleKeyframeHandler = null;
    - L48: protected @Nullable KeyframeEventHandler<T, CustomInstructionKeyframeData> customKeyframeHandler = null;
    - L50: protected boolean additiveAnimations = false;
    - L51: protected int transitionTicks;
    - L52: protected double animationSpeed = 1;
  - `common/src/main/java/com/geckolib/animation/AnimationProcessor.java`
    - L1: package com.geckolib.animation;
    - L35: /// Internal class for handling the processing of animations and animation-related functionality.
    - L39: @ApiStatus.Internal
    - L40: public class AnimationProcessor {
    - L45: @SuppressWarnings({"unchecked", "rawtypes"})
    - L46: public static <T extends GeoAnimatable> void extractControllerStates(T animatable, GeoRenderState renderState, GeoModel<T> geoModel) {
    - L76: public static void createBoneSnapshots(ControllerState controllerState, BoneSnapshots snapshots) {
  - `common/src/main/java/com/geckolib/animation/RawAnimation.java`
    - L1: package com.geckolib.animation;
    - L14: /// A builder class for a raw/unbaked animation. These are constructed to pass to the
    - L23: public final class RawAnimation {
    - L24: public static final StreamCodec<ByteBuf, RawAnimation> STREAM_CODEC = StreamCodec.composite(
    - L42: public static RawAnimation begin() {
    - L50: public RawAnimation thenPlay(String animationName) {
    - L57: public RawAnimation thenLoop(String animationName) {
    - L66: public RawAnimation thenWait(int ticks) {
    - L76: public RawAnimation thenPlayAndHold(String animation) {
    - L85: public RawAnimation thenPlayXTimes(String animationName, int playCount) {
    - L97: public RawAnimation then(String animationName, LoopType loopType) {
    - L103: public List<Stage> getAnimationStages() {
  - `common/src/main/java/com/geckolib/animation/keyframehandler/AutoPlayingSoundKeyframeHandler.java`
    - L1: package com.geckolib.animation.keyframehandler;
    - L29: /// @param <A> Animatable class type
    - L30: public class AutoPlayingSoundKeyframeHandler<A extends GeoAnimatable> implements AnimationController.KeyframeEventHandler<A, SoundKeyframeData> {
    - L31: @Override
    - L32: public void handle(KeyFrameEvent<A, SoundKeyframeData> event) {
  - `common/src/main/java/com/geckolib/animation/keyframehandler/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.animation.keyframehandler;
  - `common/src/main/java/com/geckolib/animation/object/EasingType.java`
    - L1: package com.geckolib.animation.object;
    - L24: @FunctionalInterface
    - L25: public interface EasingType {
  - `common/src/main/java/com/geckolib/animation/object/LoopType.java`
    - L1: package com.geckolib.animation.object;
    - L18: /// Custom loop types are supported by extending this class and providing the extended class instance as the loop type for the animation
    - L19: @FunctionalInterface
    - L20: public interface LoopType {
  - `common/src/main/java/com/geckolib/animation/object/PlayState.java`
    - L1: package com.geckolib.animation.object;
    - L13: public enum PlayState {
  - `common/src/main/java/com/geckolib/animation/object/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.animation.object;
  - `common/src/main/java/com/geckolib/animation/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.animation;
  - `common/src/main/java/com/geckolib/animation/state/AnimationPoint.java`
    - L1: package com.geckolib.animation.state;
    - L30: public record AnimationPoint(Animation animation, @Nullable EasingType easingOverride, LoopType loopType, double animTime, int[][][] keyFramePoints) {
    - L31: public static final int NO_KEYFRAME = -2;
    - L32: public static final int BEFORE_FIRST_KEYFRAME = -1;
    - L35: public boolean hasFinished() {
    - L42: public int[] scalePoints(int boneAnimationIndex) {
    - L49: public int[] rotationPoints(int boneAnimationIndex) {
    - L56: public int[] translationPoints(int boneAnimationIndex) {
    - L63: public @Nullable Keyframe getPreviousKeyframe(int boneAnimationIndex, Transform transformationType, Axis axis) {
    - L70: public @Nullable Keyframe getCurrentKeyframe(int boneAnimationIndex, Transform transformationType, Axis axis) {
    - L77: public @Nullable Keyframe getNextKeyframe(int boneAnimationIndex, Transform transformationType, Axis axis) {
    - L84: public @Nullable Keyframe getKeyframe(int boneAnimationIndex, Transform transformationType, Axis axis, int keyframeOffset) {
  - `common/src/main/java/com/geckolib/animation/state/AnimationTest.java`
    - L1: package com.geckolib.animation.state;
    - L26: public record AnimationTest<T extends GeoAnimatable>(T animatable, GeoRenderState renderState, AnimatableManager<T> manager, AnimationController<T> controller) {
    - L30: public boolean isMoving() {
    - L39: public void setAnimation(RawAnimation animation) {
    - L44: public PlayState setAndContinue(RawAnimation animation) {
    - L56: public boolean isCurrentAnimation(RawAnimation animation) {
    - L68: public boolean isCurrentAnimationStage(String name) {
    - L75: public void setControllerSpeed(float speed) {
    - L80: public boolean hasData(DataTicket<?> dataTicket) {
    - L91: public <D> @Nullable D getData(DataTicket<D> dataTicket) {
    - L101: @Contract("_,null->null;_,!null->!null")
    - L102: public <D> @Nullable D getDataOrDefault(DataTicket<D> dataTicket, @Nullable D defaultValue) {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `AnimationStateHandler` in `AnimationController.java`
- interface `KeyframeEventHandler` in `AnimationController.java`
- record `Stage` in `RawAnimation.java`
- key type `AutoPlayingSoundKeyframeHandler` (`AutoPlayingSoundKeyframeHandler.java`)
- interface `EasingType` in `EasingType.java`
- interface `LoopType` in `LoopType.java`
- enum `PlayState` in `PlayState.java`
- enum `Transform` in `AnimationPoint.java`
- enum `Axis` in `AnimationPoint.java`
- record `AnimationPoint` in `AnimationPoint.java`
- record `AnimationTest` in `AnimationTest.java`
- record `AnimationTimeline` in `AnimationTimeline.java`
- record `Stage` in `AnimationTimeline.java`
- record `ControllerState` in `ControllerState.java`
- record `EasingState` in `EasingState.java`
- record `KeyFrameEvent` in `KeyFrameEvent.java`

## Port relevance to Re-Forestry
- Mentions of `animation` appear in `files/implemented-features.md` — check that file for port status.
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/animation/AnimationController.java`
- `common/src/main/java/com/geckolib/animation/AnimationProcessor.java`
- `common/src/main/java/com/geckolib/animation/RawAnimation.java`
- `common/src/main/java/com/geckolib/animation/keyframehandler/AutoPlayingSoundKeyframeHandler.java`
- `common/src/main/java/com/geckolib/animation/keyframehandler/package-info.java`
- `common/src/main/java/com/geckolib/animation/object/EasingType.java`
- `common/src/main/java/com/geckolib/animation/object/LoopType.java`
- `common/src/main/java/com/geckolib/animation/object/PlayState.java`
- `common/src/main/java/com/geckolib/animation/object/package-info.java`
- `common/src/main/java/com/geckolib/animation/package-info.java`
- `common/src/main/java/com/geckolib/animation/state/AnimationPoint.java`
- `common/src/main/java/com/geckolib/animation/state/AnimationTest.java`
- `common/src/main/java/com/geckolib/animation/state/AnimationTimeline.java`
- `common/src/main/java/com/geckolib/animation/state/BoneSnapshot.java`
- `common/src/main/java/com/geckolib/animation/state/ControllerState.java`
- `common/src/main/java/com/geckolib/animation/state/EasingState.java`
- `common/src/main/java/com/geckolib/animation/state/KeyFrameEvent.java`
- `common/src/main/java/com/geckolib/animation/state/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
