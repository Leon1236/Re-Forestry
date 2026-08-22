# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — items

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/item`
- Java files scanned: **4**
- Date: 2026-07-30

## Summary
Module `items` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/item` (4 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModArmorMaterials`
- `ModItems`
- `ModToolMaterials`
- `ChiselItem`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "items"`
- Source root exists: **True**
- Nested packages under this module:
  - `custom`
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/item/ModArmorMaterials.java`
    - L1: package net.kaupenjoe.tutorialmod.item;
    - L13: public class ModArmorMaterials {
    - L14: public static final ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY =
    - L17: public static final ResourceKey<EquipmentAsset> FLUORITE_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite"));
    - L19: public static final ArmorMaterial FLUORITE_ARMOR_MATERIAL = new ArmorMaterial(750,
  - `src/main/java/net/kaupenjoe/tutorialmod/item/ModItems.java`
    - L1: package net.kaupenjoe.tutorialmod.item;
    - L22: public class ModItems {
    - L23: public static final Item FLUORITE = registerItem("fluorite", Item::new);
    - L24: public static final Item RAW_FLUORITE = registerItem("raw_fluorite", Item::new);
    - L26: public static final Item CHISEL = registerItem("chisel", properties -> new ChiselItem(properties.durability(32)));
    - L27: public static final Item STRAWBERRY = registerItem("strawberry", properties -> new Item(properties
    - L29: @Override
    - L30: public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
    - L36: public static final Item COMBUSTIBLE_SPORES = registerItem("combustible_spores", properties -> new Item(properties.stacksTo(16)));
    - L38: public static final Item FLUORITE_SWORD = registerItem("fluorite_sword",
    - L40: public static final Item FLUORITE_PICKAXE = registerItem("fluorite_pickaxe",
    - L42: public static final Item FLUORITE_SHOVEL = registerItem("fluorite_shovel",
  - `src/main/java/net/kaupenjoe/tutorialmod/item/ModToolMaterials.java`
    - L1: package net.kaupenjoe.tutorialmod.item;
    - L6: public class ModToolMaterials {
    - L7: public static final ToolMaterial FLUORITE = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL,
  - `src/main/java/net/kaupenjoe/tutorialmod/item/custom/ChiselItem.java`
    - L1: package net.kaupenjoe.tutorialmod.item.custom;
    - L25: public class ChiselItem extends Item {
    - L35: public ChiselItem(Properties properties) {
    - L39: @Override
    - L40: public InteractionResult use(Level level, Player player, InteractionHand hand) {
    - L49: @Override
    - L50: public InteractionResult useOn(UseOnContext context) {
    - L71: @Override
    - L72: public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `items` appear in `files/implemented-features.md` — check that file for port status.
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/item/ModArmorMaterials.java`
- `src/main/java/net/kaupenjoe/tutorialmod/item/ModItems.java`
- `src/main/java/net/kaupenjoe/tutorialmod/item/ModToolMaterials.java`
- `src/main/java/net/kaupenjoe/tutorialmod/item/custom/ChiselItem.java`


### Companion package `food`
- `ModFoods.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
