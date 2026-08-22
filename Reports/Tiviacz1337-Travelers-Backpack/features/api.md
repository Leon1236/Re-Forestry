# Tiviacz1337-Travelers-Backpack — api

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/api`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `api` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/api` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `EffectFluid`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "api"`
- Source root exists: **True**
- Nested packages under this module:
  - `fluids`
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/api/fluids/EffectFluid.java`
    - L1: package com.tiviacz.travelersbackpack.api.fluids;
    - L10: public abstract class EffectFluid {
    - L11: public final String uniqueId;
    - L12: public final Fluid fluid;
    - L13: public int effectID;
    - L14: public final long amountRequired;
    - L16: public EffectFluid(String uniqueId, FluidVariantWrapper fluidVariantWrapper, long amountRequired) {
    - L20: public EffectFluid(String uniqueId, Fluid fluid, long amountRequired) {
    - L31: public EffectFluid(String uniqueId, String modid, String fluidName, long amountRequired) {
    - L43: public String getUniqueId() {
    - L47: public void setEffectID(int id) {
    - L51: public int getEffectID() {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/assets/travelersbackpack/blockstates/lapis.json`
- `src/main/resources/assets/travelersbackpack/items/lapis.json`
- `src/main/resources/assets/travelersbackpack/textures/block/backpack/lapis.png`
- `src/main/resources/assets/travelersbackpack/models/item/lapis.json`
- `src/main/resources/assets/travelersbackpack/models/block/lapis.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `api` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/api/fluids/EffectFluid.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
