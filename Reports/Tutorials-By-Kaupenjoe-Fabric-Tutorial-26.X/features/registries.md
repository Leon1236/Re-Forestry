# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — registries

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/registries`
- Java files scanned: **3**
- Date: 2026-07-30

## Summary
Module `registries` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/registries` (3 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModCompostables`
- `ModFuels`
- `ModPotionRecipes`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "registries"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/registries/ModCompostables.java`
    - L1: package net.kaupenjoe.tutorialmod.registries;
    - L6: public class ModCompostables {
    - L7: public static void registerCompostables() {
  - `src/main/java/net/kaupenjoe/tutorialmod/registries/ModFuels.java`
    - L1: package net.kaupenjoe.tutorialmod.registries;
    - L6: public class ModFuels {
    - L7: public static void registerFuels() {
  - `src/main/java/net/kaupenjoe/tutorialmod/registries/ModPotionRecipes.java`
    - L1: package net.kaupenjoe.tutorialmod.registries;
    - L9: public class ModPotionRecipes {
    - L10: public static void registerPotionRecipes() {

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
- `src/main/java/net/kaupenjoe/tutorialmod/registries/ModCompostables.java`
- `src/main/java/net/kaupenjoe/tutorialmod/registries/ModFuels.java`
- `src/main/java/net/kaupenjoe/tutorialmod/registries/ModPotionRecipes.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
