# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — data_components

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/data`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `data_components` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/data` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModDataComponents`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "data_components"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/data/ModDataComponents.java`
    - L1: package net.kaupenjoe.tutorialmod.data;
    - L12: public class ModDataComponents {
    - L13: public static final DataComponentType<BlockPos> COORDINATES = register("coordinates",
    - L22: public static void registerDataComponents() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/data/ModDataComponents.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
