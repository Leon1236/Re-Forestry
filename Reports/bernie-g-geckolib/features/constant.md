# bernie-g-geckolib — constant

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/constant`
- Java files scanned: **6**
- Date: 2026-07-30

## Summary
Module `constant` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/constant` (6 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DataTickets`
- `DefaultAnimations`
- `DataTicket`
- `OverridingDataTicket`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "constant"`
- Source root exists: **True**
- Nested packages under this module:
  - `dataticket`
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/constant/DataTickets.java`
    - L1: package com.geckolib.constant;
    - L31: /// This class should only be used on the client side
    - L32: public final class DataTickets {
    - L33: public static final DataTicket<Class<? extends GeoAnimatable>> ANIMATABLE_CLASS = DataTicket.create("animatable_class", new TypeToken<>() {});
    - L34: public static final DataTicket<Long> ANIMATABLE_INSTANCE_ID = DataTicket.create("animatable_instance_id", new TypeToken<>() {});
    - L35: public static final DataTicket<Float> PARTIAL_TICK = DataTicket.create("partial_tick", new TypeToken<>() {});
    - L36: public static final DataTicket<Integer> RENDER_COLOR = DataTicket.create("render_color", new TypeToken<>() {});
    - L37: public static final DataTicket<Integer> PACKED_OVERLAY = DataTicket.create("packed_overlay", new TypeToken<>() {});
    - L38: public static final DataTicket<Integer> PACKED_LIGHT = DataTicket.create("packed_light", new TypeToken<>() {});
    - L39: public static final DataTicket<Vec3> VELOCITY = DataTicket.create("velocity", new TypeToken<>() {});
    - L40: public static final DataTicket<Boolean> IS_MOVING = DataTicket.create("is_moving", new TypeToken<>() {});
    - L41: public static final DataTicket<Vec3> POSITION = DataTicket.create("position", new TypeToken<>() {});
  - `common/src/main/java/com/geckolib/constant/DefaultAnimations.java`
    - L1: package com.geckolib.constant;
    - L20: /// Optionally usable class that holds constants for recommended animation paths
    - L25: public final class DefaultAnimations {
    - L26: public static final RawAnimation ITEM_ON_USE = RawAnimation.begin().thenPlay("item.use");
    - L28: public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("misc.idle");
    - L29: public static final RawAnimation IDLE_FLYING = RawAnimation.begin().thenLoop("misc.idle.flying");
    - L30: public static final RawAnimation LIVING = RawAnimation.begin().thenLoop("misc.living");
    - L31: public static final RawAnimation SPAWN = RawAnimation.begin().thenPlay("misc.spawn");
    - L32: public static final RawAnimation DIE = RawAnimation.begin().thenPlay("misc.die");
    - L33: public static final RawAnimation INTERACT = RawAnimation.begin().thenPlay("misc.interact");
    - L34: public static final RawAnimation DEPLOY = RawAnimation.begin().thenPlay("misc.deploy");
    - L35: public static final RawAnimation REST = RawAnimation.begin().thenPlay("misc.rest");
  - `common/src/main/java/com/geckolib/constant/dataticket/DataTicket.java`
    - L1: package com.geckolib.constant.dataticket;
    - L14: public class DataTicket<D> {
    - L29: @SuppressWarnings({"unchecked", "rawtypes"})
    - L30: public static <D> DataTicket<D> create(String id, Class<? extends D> objectType) {
    - L37: @SuppressWarnings("unchecked")
    - L38: public static <D> DataTicket<D> create(String id, TypeToken<D> token) {
    - L42: public String id() {
    - L47: public Type dataType() {
    - L51: @Override
    - L52: public int hashCode() {
    - L56: @Override
    - L57: public boolean equals(Object obj) {
  - `common/src/main/java/com/geckolib/constant/dataticket/OverridingDataTicket.java`
    - L1: package com.geckolib.constant.dataticket;
    - L18: /// @param <C> The class type that this DataTicket overrides a value from
    - L19: public final class OverridingDataTicket<D, C> extends DataTicket<D> {
    - L30: /// Get the class type that this `DataTicket` overrides a value from
    - L31: public Class<C> getOverriddenClass() {
    - L36: public Function<C, D> getValueExtractor() {
    - L41: public <R extends GeoRenderState> boolean canExtractFrom(R renderState) {
    - L46: public D extractFrom(C renderState) {
    - L55: @SuppressWarnings({"unchecked", "rawtypes"})
    - L56: public static <D, C> OverridingDataTicket<D, C> create(String id, Class<? extends D> objectType, Class<C> overriddenClass, Function<C, D> valueExtractor) {
    - L65: @SuppressWarnings("unchecked")
    - L66: public static <D, C> OverridingDataTicket<D, C> create(String id, TypeToken<D> typeToken, Class<C> overriddenClass, Function<C, D> valueExtractor) {
  - `common/src/main/java/com/geckolib/constant/dataticket/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.constant.dataticket;
  - `common/src/main/java/com/geckolib/constant/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.constant;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/constant/DataTickets.java`
- `common/src/main/java/com/geckolib/constant/DefaultAnimations.java`
- `common/src/main/java/com/geckolib/constant/dataticket/DataTicket.java`
- `common/src/main/java/com/geckolib/constant/dataticket/OverridingDataTicket.java`
- `common/src/main/java/com/geckolib/constant/dataticket/package-info.java`
- `common/src/main/java/com/geckolib/constant/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
