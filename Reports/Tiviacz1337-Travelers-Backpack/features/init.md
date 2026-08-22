# Tiviacz1337-Travelers-Backpack — init

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/init`
- Java files scanned: **13**
- Date: 2026-07-30

## Summary
Module `init` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/init` (13 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ModAdvancements`
- `ModAttachmentTypes`
- `ModBlockEntityTypes`
- `ModBlocks`
- `ModCommands`
- `ModDataComponents`
- `ModFluids`
- `ModItemGroups`
- `ModItems`
- `ModNetwork`
- `ModRecipeSerializers`
- `ModScreenHandlerTypes`
- `ModTags`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "init"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModAdvancements.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L9: public class ModAdvancements {
    - L10: public static ActionTypeTrigger ACTION_TRIGGER;
    - L12: public static void init() {
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModAttachmentTypes.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L11: public class ModAttachmentTypes {
    - L12: public static final AttachmentType<BackpackAttachment> TRAVELERS_BACKPACK = AttachmentRegistry.create(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "travelers_backpack"), builder -> builder
    - L16: public static void init() {
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModBlockEntityTypes.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L33: public class ModBlockEntityTypes {
    - L34: public static BlockEntityType<BackpackBlockEntity> BACKPACK;
    - L36: public static void init() {
    - L88: public static void initSidedStorage() {
    - L93: public static SingleVariantStorage<FluidVariant> getProperTank(BackpackBlockEntity blockEntity, Direction clickedDirection) {
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModBlocks.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L17: public class ModBlocks {
    - L20: public static Block STANDARD_TRAVELERS_BACKPACK;
    - L22: public static Block NETHERITE_TRAVELERS_BACKPACK;
    - L23: public static Block DIAMOND_TRAVELERS_BACKPACK;
    - L24: public static Block GOLD_TRAVELERS_BACKPACK;
    - L25: public static Block EMERALD_TRAVELERS_BACKPACK;
    - L26: public static Block IRON_TRAVELERS_BACKPACK;
    - L27: public static Block LAPIS_TRAVELERS_BACKPACK;
    - L28: public static Block REDSTONE_TRAVELERS_BACKPACK;
    - L29: public static Block COAL_TRAVELERS_BACKPACK;
    - L31: public static Block QUARTZ_TRAVELERS_BACKPACK;
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModCommands.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L9: public class ModCommands {
    - L10: public static void registerCommands() {
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModDataComponents.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L17: public class ModDataComponents {
    - L18: public static final DataComponentType<Integer> TIER = DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build();
    - L19: public static final DataComponentType<Integer> STORAGE_SLOTS = DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build();
    - L20: public static final DataComponentType<Integer> UPGRADE_SLOTS = DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build();
    - L21: public static final DataComponentType<Integer> TOOL_SLOTS = DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build();
    - L22: public static final DataComponentType<Fluids> FLUIDS = DataComponentType.<Fluids>builder().persistent(Fluids.CODEC).networkSynchronized(Fluids.STREAM_CODEC).build();
    - L23: public static final DataComponentType<Boolean> TAB_OPEN = DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build();
    - L24: public static final DataComponentType<List<Integer>> FILTER_SETTINGS = DataComponentType.<List<Integer>>builder().persistent(Codec.INT.listOf()).networkSynchronized(ByteBufCodecs.INT.apply(ByteBufCodecs.list())).build();
    - L25: public static final DataComponentType<List<String>> FILTER_TAGS = DataComponentType.<List<String>>builder().persistent(Codec.STRING.listOf()).networkSynchronized(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list())).bui
    - L26: public static final DataComponentType<Boolean> UPGRADE_ENABLED = DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build();
    - L27: public static final DataComponentType<Boolean> SHIFT_CLICK_TO_BACKPACK = DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build();
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModFluids.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L10: public class ModFluids {
    - L11: public static final FlowingFluid POTION_STILL = register("potion_still", new PotionFluid.Still());
    - L12: public static final FlowingFluid POTION_FLOWING = register("potion_flowing", new PotionFluid.Flowing());
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModItemGroups.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L21: public class ModItemGroups {
    - L22: public static final ResourceKey<CreativeModeTab> TRAVELERS_BACKPACK = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "travelers_backpack"));
    - L24: public static void registerItemGroup() {
    - L30: public static ItemStack createTabStack() {
    - L36: public static void addItemGroup() {
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModItems.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L29: public class ModItems {
    - L31: public static TravelersBackpackItem STANDARD_TRAVELERS_BACKPACK;
    - L33: public static TravelersBackpackItem NETHERITE_TRAVELERS_BACKPACK;
    - L34: public static TravelersBackpackItem DIAMOND_TRAVELERS_BACKPACK;
    - L35: public static TravelersBackpackItem GOLD_TRAVELERS_BACKPACK;
    - L36: public static TravelersBackpackItem EMERALD_TRAVELERS_BACKPACK;
    - L37: public static TravelersBackpackItem IRON_TRAVELERS_BACKPACK;
    - L38: public static TravelersBackpackItem LAPIS_TRAVELERS_BACKPACK;
    - L39: public static TravelersBackpackItem REDSTONE_TRAVELERS_BACKPACK;
    - L40: public static TravelersBackpackItem COAL_TRAVELERS_BACKPACK;
    - L42: public static TravelersBackpackItem QUARTZ_TRAVELERS_BACKPACK;
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModNetwork.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L11: public class ModNetwork {
    - L12: public static void initClient() {
    - L27: public static void initServer() {
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModRecipeSerializers.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L11: public class ModRecipeSerializers {
    - L12: public static RecipeSerializer<ShapedBackpackRecipe> BACKPACK_SHAPED;
    - L13: public static RecipeSerializer<BackpackUpgradeRecipe> BACKPACK_UPGRADE;
    - L15: public static void init() {
  - `src/main/java/com/tiviacz/travelersbackpack/init/ModScreenHandlerTypes.java`
    - L1: package com.tiviacz.travelersbackpack.init;
    - L16: public class ModScreenHandlerTypes {
    - L17: public static ExtendedMenuType<BackpackItemMenu, ItemScreenData> BACKPACK_MENU = new ExtendedMenuType<>(BackpackItemMenu::new, ItemScreenData.PACKET_CODEC);
    - L18: public static ExtendedMenuType<BackpackBlockEntityMenu, BlockEntityScreenData> BACKPACK_BLOCK_MENU = new ExtendedMenuType<>(BackpackBlockEntityMenu::new, BlockEntityScreenData.PACKET_CODEC);
    - L19: public static ExtendedMenuType<BackpackSettingsMenu, SettingsScreenData> BACKPACK_SETTINGS_MENU = new ExtendedMenuType<>(BackpackSettingsMenu::new, SettingsScreenData.PACKET_CODEC);
    - L21: public static void init() {
    - L27: public record ItemScreenData(int screenID, int entityID) {
    - L28: public static final StreamCodec<RegistryFriendlyByteBuf, ItemScreenData> PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.INT, ItemScreenData::screenID,
    - L32: public record BlockEntityScreenData(int entityId, BlockPos pos) {
    - L33: public static final StreamCodec<RegistryFriendlyByteBuf, BlockEntityScreenData> PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.INT, BlockEntityScreenData::entityId,
    - L37: public record SettingsScreenData(boolean isBlockEntity, int screenId, BlockPos pos, int index) {
    - L38: public static final StreamCodec<RegistryFriendlyByteBuf, SettingsScreenData> PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, SettingsScreenData::isBlockEntity,

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ModNetwork` (`ModNetwork.java`)
- record `ItemScreenData` in `ModScreenHandlerTypes.java`
- record `BlockEntityScreenData` in `ModScreenHandlerTypes.java`
- record `SettingsScreenData` in `ModScreenHandlerTypes.java`
- key type `ModScreenHandlerTypes` (`ModScreenHandlerTypes.java`)

## Port relevance to Re-Forestry
- Mentions of `init` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/init/ModAdvancements.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModAttachmentTypes.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModBlockEntityTypes.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModBlocks.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModCommands.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModDataComponents.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModFluids.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModItemGroups.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModItems.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModNetwork.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModRecipeSerializers.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModScreenHandlerTypes.java`
- `src/main/java/com/tiviacz/travelersbackpack/init/ModTags.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
