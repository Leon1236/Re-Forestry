# Tiviacz1337-Travelers-Backpack — handlers

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/handlers`
- Java files scanned: **8**
- Date: 2026-07-30

## Summary
Module `handlers` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/handlers` (8 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DeathHandler`
- `EntityItemHandler`
- `KeybindHandler`
- `LootHandler`
- `RightClickHandler`
- `ScreenRenderHandler`
- `SleepHandler`
- `TickHandler`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "handlers"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/DeathHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L23: public class DeathHandler {
    - L24: public static void registerListeners() {
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/EntityItemHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L9: public class EntityItemHandler {
    - L10: public static void registerListeners() {
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/KeybindHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L34: public class KeybindHandler {
    - L35: public static final Identifier TRAVELERS_BACKPACK_PHASE = Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "phase");
    - L36: public static final KeyMapping.Category CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "controls"));
    - L37: public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.travelersbackpack.inventory", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, CATEGORY);
    - L38: public static final KeyMapping SORT_BACKPACK = new KeyMapping("key.travelersbackpack.sort", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
    - L39: public static final KeyMapping ABILITY = new KeyMapping("key.travelersbackpack.ability", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
    - L40: public static final KeyMapping SWAP_TOOL = new KeyMapping("key.travelersbackpack.cycle_tool", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, CATEGORY);
    - L41: public static final KeyMapping TOGGLE_UPGRADE_0 = new KeyMapping("key.travelersbackpack.toggle_upgrade_0", GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
    - L42: public static final KeyMapping TOGGLE_UPGRADE_1 = new KeyMapping("key.travelersbackpack.toggle_upgrade_1", GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
    - L43: public static final KeyMapping TOGGLE_UPGRADE_2 = new KeyMapping("key.travelersbackpack.toggle_upgrade_2", GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
    - L44: public static final KeyMapping TOGGLE_UPGRADE_3 = new KeyMapping("key.travelersbackpack.toggle_upgrade_3", GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/LootHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L13: public class LootHandler {
    - L14: public static void registerListeners() {
    - L69: public static void addLootPool(LootTable.Builder builder, Item item, float chance) {
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/RightClickHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L50: public class RightClickHandler {
    - L51: public static void registerListeners() {
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/ScreenRenderHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L35: public class ScreenRenderHandler {
    - L36: public static void renderAboveContents(AbstractContainerScreen<?> screen, GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
    - L120: public static void registerScreenEvents() {
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/SleepHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L12: public class SleepHandler {
    - L13: public static void registerListener() {
  - `src/main/java/com/tiviacz/travelersbackpack/handlers/TickHandler.java`
    - L1: package com.tiviacz.travelersbackpack.handlers;
    - L6: public class TickHandler {
    - L9: public static void register() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `DeathHandler` (`DeathHandler.java`)
- key type `EntityItemHandler` (`EntityItemHandler.java`)
- key type `KeybindHandler` (`KeybindHandler.java`)
- key type `LootHandler` (`LootHandler.java`)
- key type `RightClickHandler` (`RightClickHandler.java`)
- key type `ScreenRenderHandler` (`ScreenRenderHandler.java`)
- key type `SleepHandler` (`SleepHandler.java`)
- key type `TickHandler` (`TickHandler.java`)

## Port relevance to Re-Forestry
- Mentions of `handlers` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/handlers/DeathHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/EntityItemHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/KeybindHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/LootHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/RightClickHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/ScreenRenderHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/SleepHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/TickHandler.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
