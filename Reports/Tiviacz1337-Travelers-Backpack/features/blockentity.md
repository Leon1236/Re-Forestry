# Tiviacz1337-Travelers-Backpack — blockentity

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/blockentity`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `blockentity` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/blockentity` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BackpackBlockEntity`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "blockentity"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/blockentity/BackpackBlockEntity.java`
    - L1: package com.tiviacz.travelersbackpack.blockentity;
    - L48: public class BackpackBlockEntity extends BlockEntity implements MenuProvider, RenderDataBlockEntity {
    - L51: public List<Integer> infiniteAccessUsers = new ArrayList<>();
    - L52: public int settingsUser = -1;
    - L54: @Nullable
    - L55: public Player player;
    - L57: public static final String BACKPACK = "Backpack";
    - L58: public static final String SLEEPING_BAG = "SleepingBag";
    - L59: public static final String SETTINGS_USER = "SettingsUser";
    - L61: public BackpackBlockEntity(BlockPos pos, BlockState state) {
    - L65: public BackpackWrapper getWrapper() {
    - L69: public void removeWrapper() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `BackpackRenderData` in `BackpackBlockEntity.java`

## Port relevance to Re-Forestry
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/blockentity/BackpackBlockEntity.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
