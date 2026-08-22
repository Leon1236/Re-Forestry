# bernie-g-geckolib — util

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/util`
- Java files scanned: **7**
- Date: 2026-07-30

## Summary
Module `util` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/util` (7 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ClientUtil`
- `GeckoLibUtil`
- `JsonUtil`
- `MiscUtil`
- `RenderStateUtil`
- `RenderUtil`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "util"`
- Source root exists: **True**
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/util/ClientUtil.java`
    - L1: package com.geckolib.util;
    - L13: /// Helper class for segregating client-side code
    - L14: public final class ClientUtil {
    - L16: public static @Nullable Player getClientPlayer() {
    - L21: public static @Nullable Level getLevel() {
    - L26: public static boolean clientPlayerHasCape() {
    - L33: public static Vec3 getCameraPos() {
    - L40: public static boolean isFirstPerson() {
    - L45: public static MoonPhase getClientMoonPhase() {
    - L54: public static double getCurrentTick() {
    - L63: public static double getCurrentTick(@Nullable Float partialTick) {
    - L71: @ApiStatus.Internal
  - `common/src/main/java/com/geckolib/util/GeckoLibUtil.java`
    - L1: package com.geckolib.util;
    - L21: /// Helper class for various GeckoLib-specific functions.
    - L22: public final class GeckoLibUtil {
    - L26: public static AnimatableInstanceCache createInstanceCache(GeoAnimatable animatable) {
    - L38: public static AnimatableInstanceCache createInstanceCache(GeoAnimatable animatable, boolean singletonObject) {
    - L90: @ApiStatus.Internal
    - L91: public static boolean areComponentsMatchingIgnoringGeckoLibId(PatchedDataComponentMap map1, PatchedDataComponentMap map2) {
  - `common/src/main/java/com/geckolib/util/JsonUtil.java`
    - L1: package com.geckolib.util;
    - L18: /// JSON helper class for various .json functions
    - L19: public final class JsonUtil {
    - L23: public static double worldToModelUnits(double value) {
    - L30: public static double modelToWorldUnits(double value) {
    - L35: public static <L, R> @Nullable Either<@Nullable L, @Nullable R> getEither(JsonObject obj,
    - L42: public static <L, R> @Nullable Either<@Nullable L, @Nullable R> getEither(JsonObject obj, @Nullable Predicate<JsonObject> nullPredicate,
    - L58: public static @Nullable Vec3 jsonToVec3(@Nullable JsonElement element) {
    - L82: public static double[] jsonArrayToDoubleArray(@Nullable JsonArray array) throws JsonParseException{
    - L100: @SuppressWarnings("unchecked")
    - L101: public static <T> T @Nullable [] jsonArrayToObjectArray(@Nullable JsonArray array, JsonDeserializationContext context, Class<T> objectClass) {
    - L118: public static <T> T @Nullable[] jsonArrayToObjectArray(@Nullable JsonArray array, Int2ObjectFunction<T[]> arrayFactory, Function<JsonElement, @Nullable T> mappingFunction) {
  - `common/src/main/java/com/geckolib/util/MiscUtil.java`
    - L1: package com.geckolib.util;
    - L9: /// Helper class for miscellaneous functions that don't fit into the other util classes
    - L10: public final class MiscUtil {
    - L12: public static float getDirectionAngle(Direction direction) {
    - L25: public static boolean areFloatsEqual(double a, double b) {
    - L32: public static double lerpYaw(double delta, double start, double end) {
    - L44: @SafeVarargs
    - L45: public static <E, C extends Collection<E>> C mergeCollections(Int2ObjectFunction<C> factory, C... collections) {
  - `common/src/main/java/com/geckolib/util/RenderStateUtil.java`
    - L1: package com.geckolib.util;
    - L5: /// Helper class for RenderState-related functionality
    - L6: public final class RenderStateUtil {
    - L14: public static HumanoidRenderState makeMinimalArmorRenderingClone(final HumanoidRenderState newRenderState, final EntityRenderState oldRenderState) {
  - `common/src/main/java/com/geckolib/util/RenderUtil.java`
    - L1: package com.geckolib.util;
    - L47: /// Helper class for various methods and functions useful while rendering
    - L48: public final class RenderUtil {
    - L52: public static void transformToBone(PoseStack poseStack, GeoBone bone) {
    - L69: public static void translateAndRotateMatrixForBone(PoseStack poseStack, GeoBone bone) {
    - L93: public static void prepMatrixForBone(PoseStack poseStack, GeoBone bone) {
    - L100: public static void prepMatrixForBoneAndUpdateListeners(PoseStack poseStack, GeoBone bone, @Nullable RenderPassInfo<?> renderPassInfo) {
    - L117: public static Vec3 renderPoseToPosition(Matrix4fc pose, float xScale, float yScale, float zScale) {
  - `common/src/main/java/com/geckolib/util/package-info.java`
    - L4: @NullMarked
    - L5: package com.geckolib.util;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `util` appear in `files/implemented-features.md` — check that file for port status.
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/util/ClientUtil.java`
- `common/src/main/java/com/geckolib/util/GeckoLibUtil.java`
- `common/src/main/java/com/geckolib/util/JsonUtil.java`
- `common/src/main/java/com/geckolib/util/MiscUtil.java`
- `common/src/main/java/com/geckolib/util/RenderStateUtil.java`
- `common/src/main/java/com/geckolib/util/RenderUtil.java`
- `common/src/main/java/com/geckolib/util/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
