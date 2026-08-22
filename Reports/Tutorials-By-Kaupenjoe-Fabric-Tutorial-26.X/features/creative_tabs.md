# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — creative_tabs

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/creativemodetab`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `creative_tabs` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/creativemodetab` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModCreativeModeTabs`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "creative_tabs"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/creativemodetab/ModCreativeModeTabs.java`
    - L1: package net.kaupenjoe.tutorialmod.creativemodetab;
    - L14: public class ModCreativeModeTabs {
    - L15: public static final CreativeModeTab FLUORITE_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
    - L54: public static final CreativeModeTab FLUORITE_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
    - L86: public static void registerModCreativeModeTabs() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `creative_tabs` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/creativemodetab/ModCreativeModeTabs.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
