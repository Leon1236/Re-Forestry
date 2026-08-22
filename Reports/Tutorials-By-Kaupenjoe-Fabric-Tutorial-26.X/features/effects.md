# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — effects

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/effect`
- Java files scanned: **2**
- Date: 2026-07-30

## Summary
Module `effects` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/effect` (2 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModEffects`
- `StinkyEffect`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "effects"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/effect/ModEffects.java`
    - L1: package net.kaupenjoe.tutorialmod.effect;
    - L11: public class ModEffects {
    - L12: public static final Holder<MobEffect> STINKY = registerMobEffect("stinky",
    - L20: public static void registerEffects() {
  - `src/main/java/net/kaupenjoe/tutorialmod/effect/StinkyEffect.java`
    - L1: package net.kaupenjoe.tutorialmod.effect;
    - L13: public class StinkyEffect extends MobEffect {
    - L14: public StinkyEffect(MobEffectCategory category, int color) {
    - L18: @Override
    - L19: public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
    - L32: @Override
    - L33: public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `effects` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/effect/ModEffects.java`
- `src/main/java/net/kaupenjoe/tutorialmod/effect/StinkyEffect.java`


### Companion package `potion`
- `ModPotions.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
