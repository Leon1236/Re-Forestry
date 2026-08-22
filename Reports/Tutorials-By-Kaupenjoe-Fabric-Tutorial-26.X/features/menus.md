# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — menus

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/menu`
- Java files scanned: **5**
- Date: 2026-07-30

## Summary
Module `menus` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/menu` (5 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModMenuTypes`
- `CrystallizerMenu`
- `CrystallizerScreen`
- `PedestalMenu`
- `PedestalScreen`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "menus"`
- Source root exists: **True**
- Nested packages under this module:
  - `custom`
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/menu/ModMenuTypes.java`
    - L1: package net.kaupenjoe.tutorialmod.menu;
    - L13: public class ModMenuTypes {
    - L14: public static final MenuType<PedestalMenu> PEDESTAL_MENU =
    - L18: public static final MenuType<CrystallizerMenu> CRYSTALLIZER_MENU =
    - L23: public static void registerModMenuTypes() {
  - `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/CrystallizerMenu.java`
    - L1: package net.kaupenjoe.tutorialmod.menu.custom;
    - L16: public class CrystallizerMenu extends AbstractContainerMenu {
    - L18: public final CrystallizerBlockEntity blockEntity;
    - L21: public CrystallizerMenu(int pContainerId, Inventory inv, BlockPos blockPos) {
    - L25: public CrystallizerMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
    - L37: @Override
    - L38: public boolean mayPlace(ItemStack itemStack) {
    - L46: public boolean isCrafting() {
    - L50: public int getScaledArrowProgress() {
    - L58: public int getScaledCrystalProgress() {
    - L83: @Override
    - L84: public ItemStack quickMoveStack(Player playerIn, int pIndex) {
  - `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/CrystallizerScreen.java`
    - L1: package net.kaupenjoe.tutorialmod.menu.custom;
    - L11: public class CrystallizerScreen extends AbstractContainerScreen<CrystallizerMenu> {
    - L19: public CrystallizerScreen(CrystallizerMenu menu, Inventory inventory, Component title) {
    - L23: @Override
    - L24: public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
  - `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/PedestalMenu.java`
    - L1: package net.kaupenjoe.tutorialmod.menu.custom;
    - L13: public class PedestalMenu extends AbstractContainerMenu {
    - L16: public PedestalMenu(int containerId, Inventory inv, BlockPos blockPos) {
    - L20: public PedestalMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
    - L28: @Override
    - L29: public int getMaxStackSize() {
    - L53: @Override
    - L54: public ItemStack quickMoveStack(Player playerIn, int pIndex) {
    - L86: @Override
    - L87: public boolean stillValid(Player pPlayer) {
  - `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/PedestalScreen.java`
    - L1: package net.kaupenjoe.tutorialmod.menu.custom;
    - L11: public class PedestalScreen extends AbstractContainerScreen<PedestalMenu> {
    - L15: public PedestalScreen(PedestalMenu menu, Inventory inventory, Component title) {
    - L19: @Override
    - L20: public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `menus` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/menu/ModMenuTypes.java`
- `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/CrystallizerMenu.java`
- `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/CrystallizerScreen.java`
- `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/PedestalMenu.java`
- `src/main/java/net/kaupenjoe/tutorialmod/menu/custom/PedestalScreen.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
