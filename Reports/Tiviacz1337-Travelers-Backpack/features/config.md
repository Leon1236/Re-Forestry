# Tiviacz1337-Travelers-Backpack — config

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/config`
- Java files scanned: **3**
- Date: 2026-07-30

## Summary
Module `config` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/config` (3 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BackpackEffect`
- `Cooldown`
- `TravelersBackpackConfig`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "config"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/config/BackpackEffect.java`
    - L1: package com.tiviacz.travelersbackpack.config;
    - L6: public record BackpackEffect(Holder<MobEffect> effect, int minDuration, int maxDuration, int amplifier) {
  - `src/main/java/com/tiviacz/travelersbackpack/config/Cooldown.java`
    - L1: package com.tiviacz.travelersbackpack.config;
    - L3: public record Cooldown(int minCooldown, int maxCooldown) {
  - `src/main/java/com/tiviacz/travelersbackpack/config/TravelersBackpackConfig.java`
    - L1: package com.tiviacz.travelersbackpack.config;
    - L19: public class TravelersBackpackConfig {
    - L20: public static class Server {
    - L23: public final BackpackSettings backpackSettings;
    - L24: public final BackpackUpgrades backpackUpgrades;
    - L25: public final World world;
    - L26: public final BackpackAbilities backpackAbilities;
    - L27: public final SlownessDebuff slownessDebuff;
    - L51: public static class BackpackUpgrades {
    - L52: public final ModConfigSpec.BooleanValue enableSleepingBag;
    - L53: public final ModConfigSpec.BooleanValue enableTanksUpgrade;
    - L54: public final ModConfigSpec.BooleanValue enableCraftingUpgrade;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `BackpackEffect` in `BackpackEffect.java`
- record `Cooldown` in `Cooldown.java`
- record `Tier` in `TravelersBackpackConfig.java`

## Port relevance to Re-Forestry
- Mentions of `config` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/config/BackpackEffect.java`
- `src/main/java/com/tiviacz/travelersbackpack/config/Cooldown.java`
- `src/main/java/com/tiviacz/travelersbackpack/config/TravelersBackpackConfig.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
