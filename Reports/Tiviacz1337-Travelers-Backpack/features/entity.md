# Tiviacz1337-Travelers-Backpack — entity

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/entity`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `entity` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/entity` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BackpackItemEntity`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "entity"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/entity/BackpackItemEntity.java`
    - L1: package com.tiviacz.travelersbackpack.entity;
    - L14: public class BackpackItemEntity extends ItemEntity {
    - L15: public boolean wasFloatingUp = false;
    - L16: public boolean isInvulnerable;
    - L18: public BackpackItemEntity(EntityType<? extends ItemEntity> entityType, Level level) {
    - L24: @Override
    - L25: public void tick() {
    - L50: @Override
    - L51: public boolean isInWater() {
    - L58: @Override
    - L59: public boolean fireImmune() {
    - L63: @Override

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/entity/BackpackItemEntity.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
