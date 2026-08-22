# bernie-g-geckolib — model

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/model`
- Java files scanned: **6**
- Date: 2026-07-30

## Summary
Module `model` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/model` (6 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DefaultedBlockGeoModel`
- `DefaultedEntityGeoModel`
- `DefaultedGeoModel`
- `DefaultedItemGeoModel`
- `GeoModel`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "model"`
- Source root exists: **True**
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/model/DefaultedBlockGeoModel.java`
    - L1: package com.geckolib.model;
    - L13: /// Using this class pre-sorts provided asset paths into the "block" subdirectory
    - L14: public class DefaultedBlockGeoModel<T extends GeoAnimatable> extends DefaultedGeoModel<T> {
    - L15: /// Create a new instance of this model class with no asset subpath
    - L18: public <B extends BlockEntity & GeoAnimatable> DefaultedBlockGeoModel(BlockEntityType<B> blockEntityType) {
    - L28: public DefaultedBlockGeoModel(Identifier assetSubpath) {
    - L35: @Override
    - L36: protected String subtype() {
    - L43: @Override
    - L44: public DefaultedBlockGeoModel<T> withAltModel(Identifier altPath) {
    - L51: @Override
    - L52: public DefaultedBlockGeoModel<T> withAltAnimations(Identifier altPath) {
  - `common/src/main/java/com/geckolib/model/DefaultedEntityGeoModel.java`
    - L1: package com.geckolib.model;
    - L11: /// Using this class pre-sorts provided asset paths into the "entity" subdirectory
    - L14: public class DefaultedEntityGeoModel<T extends GeoAnimatable> extends DefaultedGeoModel<T> {
    - L15: /// Create a new instance of this model class with no asset subpath
    - L18: public <E extends Entity & GeoAnimatable> DefaultedEntityGeoModel(EntityType<E> entityType) {
    - L27: public DefaultedEntityGeoModel(Identifier assetSubpath) {
    - L34: @Override
    - L35: protected String subtype() {
    - L42: @Override
    - L43: public DefaultedEntityGeoModel<T> withAltModel(Identifier altPath) {
    - L50: @Override
    - L51: public DefaultedEntityGeoModel<T> withAltAnimations(Identifier altPath) {
  - `common/src/main/java/com/geckolib/model/DefaultedGeoModel.java`
    - L1: package com.geckolib.model;
    - L7: /// Defaulted model class for GeckoLib models
    - L9: /// This class allows for minimal boilerplate when implementing basic models, and saves on new classes
    - L12: public abstract class DefaultedGeoModel<T extends GeoAnimatable> extends GeoModel<T> {
    - L23: public DefaultedGeoModel(Identifier assetSubpath) {
    - L32: public DefaultedGeoModel<T> withAltModel(Identifier altPath) {
    - L41: public DefaultedGeoModel<T> withAltAnimations(Identifier altPath) {
    - L50: public DefaultedGeoModel<T> withAltTexture(Identifier altPath) {
    - L60: public Identifier buildFormattedModelPath(Identifier basePath) {
    - L68: public Identifier buildFormattedAnimationPath(Identifier basePath) {
    - L76: public Identifier buildFormattedTexturePath(Identifier basePath) {
    - L90: protected abstract String subtype();
  - `common/src/main/java/com/geckolib/model/DefaultedItemGeoModel.java`
    - L1: package com.geckolib.model;
    - L13: /// Using this class pre-sorts provided asset paths into the "item" subdirectory
    - L14: public class DefaultedItemGeoModel<T extends GeoAnimatable> extends DefaultedGeoModel<T> {
    - L15: /// Create a new instance of this model class with no asset subpath
    - L18: public <B extends Item & GeoAnimatable> DefaultedItemGeoModel(Item item) {
    - L28: public DefaultedItemGeoModel(Identifier assetSubpath) {
    - L35: @Override
    - L36: protected String subtype() {
    - L43: @Override
    - L44: public DefaultedItemGeoModel<T> withAltModel(Identifier altPath) {
    - L51: @Override
    - L52: public DefaultedItemGeoModel<T> withAltAnimations(Identifier altPath) {
  - `common/src/main/java/com/geckolib/model/GeoModel.java`
    - L1: package com.geckolib.model;
    - L16: /// Base class for all code-based model objects
    - L21: public abstract class GeoModel<T extends GeoAnimatable> {
    - L23: public abstract Identifier getModelResource(GeoRenderState renderState);
    - L26: public abstract Identifier getTextureResource(GeoRenderState renderState);
    - L29: public abstract Identifier getAnimationResource(T animatable);
    - L36: public Identifier[] getAnimationResourceFallbacks(T animatable) {
    - L45: public void addAdditionalStateData(T animatable, @Nullable Object relatedObject, GeoRenderState renderState) {}
    - L50: @ApiStatus.Internal
    - L51: public BakedGeoModel getBakedModel(Identifier location) {
    - L60: @ApiStatus.Internal
    - L61: public @Nullable Animation getBakedAnimation(T animatable, String name) throws RuntimeException {
  - `common/src/main/java/com/geckolib/model/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.model;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `model` appear in `files/implemented-features.md` — check that file for port status.
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/model/DefaultedBlockGeoModel.java`
- `common/src/main/java/com/geckolib/model/DefaultedEntityGeoModel.java`
- `common/src/main/java/com/geckolib/model/DefaultedGeoModel.java`
- `common/src/main/java/com/geckolib/model/DefaultedItemGeoModel.java`
- `common/src/main/java/com/geckolib/model/GeoModel.java`
- `common/src/main/java/com/geckolib/model/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
