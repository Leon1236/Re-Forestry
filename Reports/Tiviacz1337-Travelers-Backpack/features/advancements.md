# Tiviacz1337-Travelers-Backpack — advancements

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/advancements`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `advancements` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/advancements` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ActionTypeTrigger`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "advancements"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/advancements/ActionTypeTrigger.java`
    - L1: package com.tiviacz.travelersbackpack.advancements;
    - L12: public class ActionTypeTrigger extends SimpleCriterionTrigger<ActionTypeTrigger.TriggerInstance> {
    - L13: public void trigger(ServerPlayer player, String type) {
    - L17: @Override
    - L18: public Codec<TriggerInstance> codec() {
    - L22: public record TriggerInstance(Optional<ContextAwarePredicate> player, String action) implements SimpleInstance {
    - L23: public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    - L28: public boolean test(String type) {
    - L32: @Override
    - L33: public Optional<ContextAwarePredicate> player() {
    - L38: public static final String UNDYE_BACKPACK = "undye_backpack";
    - L39: public static final String CHANGE_SLEEPING_BAG = "change_sleeping_bag";

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `TriggerInstance` in `ActionTypeTrigger.java`

## Port relevance to Re-Forestry
- Mentions of `advancements` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/advancements/ActionTypeTrigger.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
