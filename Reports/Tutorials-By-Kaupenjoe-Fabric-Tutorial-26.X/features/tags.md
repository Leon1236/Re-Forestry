# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — tags

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/tags`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `tags` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/tags` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModTags`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "tags"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/tags/ModTags.java`
    - L1: package net.kaupenjoe.tutorialmod.tags;
    - L11: public class ModTags {
    - L12: public static class Blocks {
    - L13: public static final TagKey<Block> NEEDS_FLUORITE_TOOL = createTag("needs_fluorite_tool");
    - L14: public static final TagKey<Block> INCORRECT_FOR_FLUORITE_TOOL = createTag("incorrect_for_fluorite_tool");
    - L21: public static class Items {
    - L22: public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
    - L23: public static final TagKey<Item> FLUORITE_REPAIR = createTag("fluorite_repair");
    - L30: public static class Trades {
    - L31: public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_1 = createTag("kaupenger/level_1");
    - L32: public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_2 = createTag("kaupenger/level_2");

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `tags` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/tags/ModTags.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
