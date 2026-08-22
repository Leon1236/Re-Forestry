# Tiviacz1337-Travelers-Backpack — attachment

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/attachment`
- Java files scanned: **2**
- Date: 2026-07-30

## Summary
Module `attachment` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/attachment` (2 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `AttachmentUtils`
- `BackpackAttachment`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "attachment"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/attachment/AttachmentUtils.java`
    - L1: package com.tiviacz.travelersbackpack.attachment;
    - L23: public class AttachmentUtils {
    - L24: public static Optional<BackpackAttachment> getAttachment(Player player) {
    - L31: public static void registerJoinEquip() {
    - L56: public static void synchronise(Player player) {
    - L60: public static boolean isWearingBackpack(Player player) {
    - L77: public static ItemStack getWearingBackpack(Player player) {
    - L93: public static void equipBackpack(Player player, ItemStack stack) {
    - L103: @Nullable
    - L104: public static BackpackWrapper getBackpackWrapper(Player player, ItemStack stack) {
    - L108: @Nullable
    - L109: public static BackpackWrapper getBackpackWrapper(Player player, ItemStack stack, int[] dataLoad) {
  - `src/main/java/com/tiviacz/travelersbackpack/attachment/BackpackAttachment.java`
    - L1: package com.tiviacz.travelersbackpack.attachment;
    - L17: public class BackpackAttachment {
    - L18: public static Codec<BackpackAttachment> CODEC = ItemStack.OPTIONAL_CODEC.xmap(BackpackAttachment::new, BackpackAttachment::getBackpack);
    - L19: public static StreamCodec<RegistryFriendlyByteBuf, BackpackAttachment> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.map(BackpackAttachment::new, BackpackAttachment::getBackpack);
    - L21: public BackpackWrapper backpackWrapper;
    - L22: public ItemStack backpack = new ItemStack(Items.AIR, 0);
    - L24: public BackpackAttachment(ItemStack backpack) {
    - L28: public boolean hasBackpack() {
    - L32: public ItemStack getBackpack() {
    - L36: public void equipBackpack(ItemStack stack, Player player) {
    - L48: public void updateBackpack(ItemStack stack, Player player) {
    - L57: public void applyComponents(DataComponentMap map) {

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
- `src/main/java/com/tiviacz/travelersbackpack/attachment/AttachmentUtils.java`
- `src/main/java/com/tiviacz/travelersbackpack/attachment/BackpackAttachment.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
