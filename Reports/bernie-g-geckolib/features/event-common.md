# bernie-g-geckolib — event-common

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/event`
- Java files scanned: **2**
- Date: 2026-07-30

## Summary
Module `event-common` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/event` (2 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `GeoRenderEvent`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "event-common"`
- Source root exists: **True**
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/event/GeoRenderEvent.java`
    - L1: package com.geckolib.event;
    - L35: /// @param <T> Animatable class type
    - L36: /// @param <O> Associated object class type, or [Void] if none
    - L37: /// @param <R> RenderState class type
    - L38: public interface GeoRenderEvent<T extends GeoAnimatable, O, R extends GeoRenderState> {
    - L87: /// @param <T> BlockEntity animatable class type
    - L88: /// @param <R> RenderState class type
    - L89: interface Block<T extends BlockEntity & GeoAnimatable, R extends BlockEntityRenderState> extends GeoRenderEvent<T, Void, R> {
    - L91: @Override
    - L98: /// @param <T> BlockEntity animatable class type
    - L99: /// @param <R> RenderState class type
    - L100: interface CompileRenderLayers<T extends BlockEntity & GeoAnimatable, R extends BlockEntityRenderState> extends Block<T, R> {
  - `common/src/main/java/com/geckolib/event/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.event;

## Data & assets
Related resource paths (heuristic name match):
- `common/src/main/resources/geckolib.mixins.json`
- `common/src/main/resources/geckolib.png`
- `common/src/main/resources/META-INF/interface_injections.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `GeoRenderEvent` in `GeoRenderEvent.java`

## Port relevance to Re-Forestry
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/event/GeoRenderEvent.java`
- `common/src/main/java/com/geckolib/event/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
