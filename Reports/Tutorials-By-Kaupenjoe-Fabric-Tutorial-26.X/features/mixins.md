# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — mixins

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/mixin`
- Java files scanned: **2**
- Date: 2026-07-30

## Summary
Module `mixins` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/mixin` (2 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `AbstractClientPlayerMixin`
- `ExampleMixin`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "mixins"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/mixin/AbstractClientPlayerMixin.java`
    - L1: package net.kaupenjoe.tutorialmod.mixin;
    - L14: @Mixin(AbstractClientPlayer.class)
    - L15: public abstract class AbstractClientPlayerMixin extends Player {
    - L16: public AbstractClientPlayerMixin(Level level, GameProfile gameProfile) {
    - L20: @Inject(method = "getFieldOfViewModifier", at = @At(value = "TAIL"), cancellable = true)
  - `src/main/java/net/kaupenjoe/tutorialmod/mixin/ExampleMixin.java`
    - L1: package net.kaupenjoe.tutorialmod.mixin;
    - L9: @Mixin(MinecraftServer.class)
    - L10: public class ExampleMixin {
    - L11: @Inject(at = @At("HEAD"), method = "loadLevel")

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/tutorialmod.mixins.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/mixin/AbstractClientPlayerMixin.java`
- `src/main/java/net/kaupenjoe/tutorialmod/mixin/ExampleMixin.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
