# thedarkcolour-gendustry — menu

- Alias: `gendustry`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`
- Package/path root: `src/main/java/thedarkcolour/gendustry/menu`
- Java files scanned: **8**
- Date: 2026-07-30

## Summary
Module `menu` in `thedarkcolour-gendustry` is rooted at `src/main/java/thedarkcolour/gendustry/menu` (8 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `AbstractMutatronMenu`
- `AdvancedMutatronMenu`
- `IndustrialApiaryMenu`
- `MutatronMenu`
- `ProducerMenu`
- `ReplicatorMenu`
- `ThreeInputMenu`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py gendustry "menu"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/thedarkcolour/gendustry/menu/AbstractMutatronMenu.java`
    - L1: package thedarkcolour.gendustry.menu;
    - L14: public class AbstractMutatronMenu<T extends AbstractMutatronBlockEntity> extends ContainerLiquidTanks<T> {
    - L15: protected AbstractMutatronMenu(int windowId, MenuType<?> menuType, Inventory playerInv, T tile) {
  - `src/main/java/thedarkcolour/gendustry/menu/AdvancedMutatronMenu.java`
    - L1: package thedarkcolour.gendustry.menu;
    - L26: public class AdvancedMutatronMenu extends AbstractMutatronMenu<AdvancedMutatronBlockEntity> {
    - L28: public static final int BUTTON_CYCLE_LEFT = 0;
    - L29: public static final int BUTTON_CYCLE_RIGHT = 1;
    - L30: public static final int CHOICE_CLICKED = 2;
    - L33: public final Slot[] choices;
    - L42: @Nullable
    - L45: public AdvancedMutatronMenu(int windowId, Inventory playerInventory, AdvancedMutatronBlockEntity tile) {
    - L62: public static AdvancedMutatronMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
    - L68: @Override
    - L69: public void broadcastChanges() {
    - L100: @Override
  - `src/main/java/thedarkcolour/gendustry/menu/IndustrialApiaryMenu.java`
    - L1: package thedarkcolour.gendustry.menu;
    - L20: public class IndustrialApiaryMenu extends ContainerTile<IndustrialApiaryBlockEntity> {
    - L25: public IndustrialApiaryMenu(int windowId, Inventory playerInv, IndustrialApiaryBlockEntity tile) {
    - L50: @Override
    - L51: public void broadcastChanges() {
    - L68: public static IndustrialApiaryMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
  - `src/main/java/thedarkcolour/gendustry/menu/MutatronMenu.java`
    - L1: package thedarkcolour.gendustry.menu;
    - L13: public class MutatronMenu extends AbstractMutatronMenu<MutatronBlockEntity> {
    - L14: public MutatronMenu(int windowId, Inventory playerInv, MutatronBlockEntity tile) {
    - L18: public static MutatronMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
  - `src/main/java/thedarkcolour/gendustry/menu/ProducerMenu.java`
    - L1: package thedarkcolour.gendustry.menu;
    - L16: public class ProducerMenu extends ContainerLiquidTanks<ProducerBlockEntity<?, ?>> {
    - L17: public ProducerMenu(int windowId, Inventory playerInventory, ProducerBlockEntity<?, ?> tile) {
    - L33: public static ProducerMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
  - `src/main/java/thedarkcolour/gendustry/menu/ReplicatorMenu.java`
    - L1: package thedarkcolour.gendustry.menu;
    - L18: public class ReplicatorMenu extends ContainerLiquidTanks<ReplicatorBlockEntity> {
    - L19: public ReplicatorMenu(int windowId, Inventory playerInv, ReplicatorBlockEntity tile) {
    - L32: public static ReplicatorMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
  - `src/main/java/thedarkcolour/gendustry/menu/ThreeInputMenu.java`
    - L1: package thedarkcolour.gendustry.menu;
    - L23: public class ThreeInputMenu<T extends TilePowered & Container & IFilterSlotDelegate & IHintTile> extends ContainerTile<T> {
    - L24: public ThreeInputMenu(int windowId, Inventory playerInventory, T tile, MenuType<?> menuType) {
    - L37: public static ThreeInputMenu<SamplerBlockEntity> samplerFromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
    - L42: public static ThreeInputMenu<ImprinterBlockEntity> imprinterFromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
    - L47: public static ThreeInputMenu<GeneticTransposerBlockEntity> geneticTransposerFromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
    - L52: public static ThreeInputMenu<SamplerBlockEntity> sampler(int windowId, Inventory playerInventory, SamplerBlockEntity tile) {
    - L56: public static ThreeInputMenu<ImprinterBlockEntity> imprinter(int windowId, Inventory playerInventory, ImprinterBlockEntity tile) {
    - L60: public static ThreeInputMenu<GeneticTransposerBlockEntity> geneticTransposer(int windowId, Inventory playerInventory, GeneticTransposerBlockEntity tile) {
  - `src/main/java/thedarkcolour/gendustry/menu/package-info.java`
    - L1: @net.minecraft.MethodsReturnNonnullByDefault
    - L2: @net.minecraft.FieldsAreNonnullByDefault
    - L3: @javax.annotation.ParametersAreNonnullByDefault
    - L4: package thedarkcolour.gendustry.menu;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `menu` appear in `files/implemented-features.md` — check that file for port status.
- Addon module shape for future `reforestry:gendustry`; not yet ported.

## Source map
- `src/main/java/thedarkcolour/gendustry/menu/AbstractMutatronMenu.java`
- `src/main/java/thedarkcolour/gendustry/menu/AdvancedMutatronMenu.java`
- `src/main/java/thedarkcolour/gendustry/menu/IndustrialApiaryMenu.java`
- `src/main/java/thedarkcolour/gendustry/menu/MutatronMenu.java`
- `src/main/java/thedarkcolour/gendustry/menu/ProducerMenu.java`
- `src/main/java/thedarkcolour/gendustry/menu/ReplicatorMenu.java`
- `src/main/java/thedarkcolour/gendustry/menu/ThreeInputMenu.java`
- `src/main/java/thedarkcolour/gendustry/menu/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
