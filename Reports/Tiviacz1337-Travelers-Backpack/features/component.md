# Tiviacz1337-Travelers-Backpack — component

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/component`
- Java files scanned: **3**
- Date: 2026-07-30

## Summary
Module `component` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/component` (3 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `Fluids`
- `RenderInfo`
- `Slots`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "component"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/component/Fluids.java`
    - L1: package com.tiviacz.travelersbackpack.component;
    - L9: public record Fluids(FluidVariantWrapper leftFluidStack, FluidVariantWrapper rightFluidStack) {
    - L10: public static final Codec<Fluids> CODEC = RecordCodecBuilder.create(instance ->
    - L17: public static final StreamCodec<RegistryFriendlyByteBuf, Fluids> STREAM_CODEC = StreamCodec.composite(
    - L23: public static Fluids empty() {
  - `src/main/java/com/tiviacz/travelersbackpack/component/RenderInfo.java`
    - L1: package com.tiviacz.travelersbackpack.component;
    - L13: public record RenderInfo(CompoundTag compoundTag) {
    - L14: public static final RenderInfo EMPTY = new RenderInfo(new CompoundTag());
    - L15: public static final Codec<RenderInfo> CODEC = RecordCodecBuilder.create(instance ->
    - L20: public static final StreamCodec<ByteBuf, RenderInfo> STREAM_CODEC = StreamCodec.composite(
    - L24: public static final String LEFT_TANK = "LeftTank";
    - L25: public static final String RIGHT_TANK = "RightTank";
    - L26: public static final String CAPACITY = "Capacity";
    - L27: public static final String LANTERN = "Lantern";
    - L29: public boolean isEmpty() {
    - L33: public boolean hasTanks() {
    - L37: public boolean hasLantern() {
  - `src/main/java/com/tiviacz/travelersbackpack/component/Slots.java`
    - L1: package com.tiviacz.travelersbackpack.component;
    - L13: public record Slots(List<Integer> unsortables, List<Pair<Integer, Pair<ItemStack, Boolean>>> memory) {
    - L14: public static final Slots EMPTY = new Slots(List.of(), List.of());
    - L15: public static final Codec<Slots> CODEC = RecordCodecBuilder.create(instance ->
    - L22: public static final StreamCodec<RegistryFriendlyByteBuf, Slots> STREAM_CODEC = StreamCodec.composite(
    - L28: public static Slots updateUnsortables(Slots oldSlots, List<Integer> data) {
    - L32: public static Slots updateMemory(Slots oldSlots, List<Pair<Integer, Pair<ItemStack, Boolean>>> data) {
    - L36: public List<Integer> unsortables() {
    - L40: public List<Pair<Integer, Pair<ItemStack, Boolean>>> memory() {
    - L44: @Override
    - L45: public boolean equals(Object obj) {
    - L57: public boolean memoryMatch(List<Pair<Integer, Pair<ItemStack, Boolean>>> memory) {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/data/travelersbackpack/advancement/tool_slots.json`
- `src/main/resources/assets/travelersbackpack/textures/gui/slots.png`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `Fluids` in `Fluids.java`
- record `RenderInfo` in `RenderInfo.java`
- record `Slots` in `Slots.java`

## Port relevance to Re-Forestry
- Mentions of `component` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/component/Fluids.java`
- `src/main/java/com/tiviacz/travelersbackpack/component/RenderInfo.java`
- `src/main/java/com/tiviacz/travelersbackpack/component/Slots.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
