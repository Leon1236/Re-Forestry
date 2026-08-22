# bernie-g-geckolib — animatable

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/animatable`
- Java files scanned: **24**
- Date: 2026-07-30

## Summary
Module `animatable` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/animatable` (24 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `GeoAnimatable`
- `GeoBlockEntity`
- `GeoEntity`
- `GeoItem`
- `GeoReplacedEntity`
- `SingletonGeoAnimatable`
- `GeoRenderProvider`
- `package-info`
- `AnimatableInstanceCache`
- `InstancedAnimatableInstanceCache`
- `SingletonAnimatableInstanceCache`
- `package-info`
- `AnimatableManager`
- `ContextAwareAnimatableManager`
- `package-info`
- `package-info`
- `StatelessAnimatable`
- `StatelessAnimationController`
- `StatelessGeoBlockEntity`
- `StatelessGeoEntity`
- `StatelessGeoObject`
- `StatelessGeoReplacedEntity`
- `StatelessGeoSingletonAnimatable`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "animatable"`
- Source root exists: **True**
- Nested packages under this module:
  - `client`
  - `instance`
  - `manager`
  - `stateless`
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/animatable/GeoAnimatable.java`
    - L1: package com.geckolib.animatable;
    - L19: public interface GeoAnimatable {
    - L43: @ApiStatus.OverrideOnly
  - `common/src/main/java/com/geckolib/animatable/GeoBlockEntity.java`
    - L1: package com.geckolib.animatable;
    - L16: public interface GeoBlockEntity extends GeoAnimatable {
    - L23: @ApiStatus.NonExtendable
    - L57: @ApiStatus.NonExtendable
  - `common/src/main/java/com/geckolib/animatable/GeoEntity.java`
    - L1: package com.geckolib.animatable;
    - L19: public interface GeoEntity extends GeoAnimatable {
    - L28: @ApiStatus.NonExtendable
    - L56: @ApiStatus.NonExtendable
  - `common/src/main/java/com/geckolib/animatable/GeoItem.java`
    - L1: package com.geckolib.animatable;
    - L28: public interface GeoItem extends SingletonGeoAnimatable {
    - L64: @Override
    - L76: class ContextBasedAnimatableInstanceCache extends SingletonAnimatableInstanceCache {
    - L77: public ContextBasedAnimatableInstanceCache(GeoAnimatable animatable) {
    - L84: @SuppressWarnings("unchecked")
    - L85: @Override
    - L86: public AnimatableManager<GeoItem> getManagerForId(long uniqueId) {
    - L89: @Override
    - L90: protected Map<ItemDisplayContext, AnimatableManager<GeoItem>> buildContextOptions(GeoAnimatable animatable) {
    - L100: @Override
    - L101: public ItemDisplayContext getCurrentContext() {
  - `common/src/main/java/com/geckolib/animatable/GeoReplacedEntity.java`
    - L1: package com.geckolib.animatable;
    - L18: public interface GeoReplacedEntity extends SingletonGeoAnimatable {
    - L28: @ApiStatus.NonExtendable
    - L55: @ApiStatus.NonExtendable
    - L74: @ApiStatus.NonExtendable
    - L75: @Override
    - L79: @SuppressWarnings("DataFlowIssue")
    - L80: @ApiStatus.NonExtendable
    - L81: @Override
  - `common/src/main/java/com/geckolib/animatable/SingletonGeoAnimatable.java`
    - L1: package com.geckolib.animatable;
    - L21: public interface SingletonGeoAnimatable extends GeoAnimatable {
    - L39: @ApiStatus.NonExtendable
    - L67: @ApiStatus.NonExtendable
    - L95: @ApiStatus.NonExtendable
    - L110: @ApiStatus.NonExtendable
    - L118: @Override
  - `common/src/main/java/com/geckolib/animatable/client/GeoRenderProvider.java`
    - L1: package com.geckolib.animatable.client;
    - L13: /// This can be safely instantiated as a new anonymous class inside your [Item] class
    - L19: public interface GeoRenderProvider {
  - `common/src/main/java/com/geckolib/animatable/client/package-info.java`
    - L4: @NullMarked
    - L5: package com.geckolib.animatable.client;
  - `common/src/main/java/com/geckolib/animatable/instance/AnimatableInstanceCache.java`
    - L1: package com.geckolib.animatable.instance;
    - L16: /// The base cache class responsible for returning the [AnimatableManager] for a given instanceof of a [GeoAnimatable]
    - L18: /// This class is abstracted and not intended for direct use.<br/>
    - L20: public abstract class AnimatableInstanceCache {
    - L21: protected final GeoAnimatable animatable;
    - L22: protected final Supplier<GeoRenderProvider> renderProvider;
    - L24: public AnimatableInstanceCache(GeoAnimatable animatable) {
    - L45: public abstract <T extends GeoAnimatable> AnimatableManager<T> getManagerForId(long uniqueId);
    - L52: public <D> void addDataPoint(long uniqueId, DataTicket<D> dataTicket, D data) {
    - L61: public <D> @Nullable D getDataPoint(long uniqueId, DataTicket<D> dataTicket) {
    - L72: public Object getRenderProvider() {
  - `common/src/main/java/com/geckolib/animatable/instance/InstancedAnimatableInstanceCache.java`
    - L1: package com.geckolib.animatable.instance;
    - L13: public class InstancedAnimatableInstanceCache extends AnimatableInstanceCache {
    - L14: protected final Supplier<AnimatableManager<?>> manager = Suppliers.memoize(() -> new AnimatableManager<>(this.animatable));
    - L16: public InstancedAnimatableInstanceCache(GeoAnimatable animatable) {
    - L23: @SuppressWarnings("unchecked")
    - L24: @Override
    - L25: public AnimatableManager<?> getManagerForId(long uniqueId) {
  - `common/src/main/java/com/geckolib/animatable/instance/SingletonAnimatableInstanceCache.java`
    - L1: package com.geckolib.animatable.instance;
    - L14: public class SingletonAnimatableInstanceCache extends AnimatableInstanceCache {
    - L15: protected final Long2ObjectMap<AnimatableManager<?>> managers = new Long2ObjectOpenHashMap<>();
    - L17: @ApiStatus.Internal
    - L18: public SingletonAnimatableInstanceCache(GeoAnimatable animatable) {
    - L25: @SuppressWarnings("unchecked")
    - L26: @Override
    - L27: public <T extends GeoAnimatable> AnimatableManager<T> getManagerForId(long uniqueId) {
  - `common/src/main/java/com/geckolib/animatable/instance/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.animatable.instance;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `GeoAnimatable` in `GeoAnimatable.java`
- interface `GeoBlockEntity` in `GeoBlockEntity.java`
- interface `GeoEntity` in `GeoEntity.java`
- interface `GeoItem` in `GeoItem.java`
- interface `GeoReplacedEntity` in `GeoReplacedEntity.java`
- interface `SingletonGeoAnimatable` in `SingletonGeoAnimatable.java`
- interface `GeoRenderProvider` in `GeoRenderProvider.java`
- record `ControllerRegistrar` in `AnimatableManager.java`
- interface `StatelessGeoReplacedEntity` in `StatelessGeoReplacedEntity.java`

## Port relevance to Re-Forestry
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/animatable/GeoAnimatable.java`
- `common/src/main/java/com/geckolib/animatable/GeoBlockEntity.java`
- `common/src/main/java/com/geckolib/animatable/GeoEntity.java`
- `common/src/main/java/com/geckolib/animatable/GeoItem.java`
- `common/src/main/java/com/geckolib/animatable/GeoReplacedEntity.java`
- `common/src/main/java/com/geckolib/animatable/SingletonGeoAnimatable.java`
- `common/src/main/java/com/geckolib/animatable/client/GeoRenderProvider.java`
- `common/src/main/java/com/geckolib/animatable/client/package-info.java`
- `common/src/main/java/com/geckolib/animatable/instance/AnimatableInstanceCache.java`
- `common/src/main/java/com/geckolib/animatable/instance/InstancedAnimatableInstanceCache.java`
- `common/src/main/java/com/geckolib/animatable/instance/SingletonAnimatableInstanceCache.java`
- `common/src/main/java/com/geckolib/animatable/instance/package-info.java`
- `common/src/main/java/com/geckolib/animatable/manager/AnimatableManager.java`
- `common/src/main/java/com/geckolib/animatable/manager/ContextAwareAnimatableManager.java`
- `common/src/main/java/com/geckolib/animatable/manager/package-info.java`
- `common/src/main/java/com/geckolib/animatable/package-info.java`
- `common/src/main/java/com/geckolib/animatable/stateless/StatelessAnimatable.java`
- `common/src/main/java/com/geckolib/animatable/stateless/StatelessAnimationController.java`
- `common/src/main/java/com/geckolib/animatable/stateless/StatelessGeoBlockEntity.java`
- `common/src/main/java/com/geckolib/animatable/stateless/StatelessGeoEntity.java`
- `common/src/main/java/com/geckolib/animatable/stateless/StatelessGeoObject.java`
- `common/src/main/java/com/geckolib/animatable/stateless/StatelessGeoReplacedEntity.java`
- `common/src/main/java/com/geckolib/animatable/stateless/StatelessGeoSingletonAnimatable.java`
- `common/src/main/java/com/geckolib/animatable/stateless/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
