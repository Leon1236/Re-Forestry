# Tiviacz1337-Travelers-Backpack — common

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/common`
- Java files scanned: **7**
- Date: 2026-07-30

## Summary
Module `common` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/common` (7 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BackpackAbilities`
- `BackpackManager`
- `ServerActions`
- `BackpackUpgradeRecipe`
- `BackpackUpgradeRecipeBuilder`
- `ShapedBackpackRecipe`
- `ShapedBackpackRecipeBuilder`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "common"`
- Source root exists: **True**
- Nested packages under this module:
  - `recipes`
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/common/BackpackAbilities.java`
    - L1: package com.tiviacz.travelersbackpack.common;
    - L66: public class BackpackAbilities {
    - L67: public static final BackpackAbilities ABILITIES = new BackpackAbilities();
    - L69: public boolean abilityTick(@Nullable ItemStack backpack, @Nullable Player player) {
  - `src/main/java/com/tiviacz/travelersbackpack/common/BackpackManager.java`
    - L1: package com.tiviacz.travelersbackpack.common;
    - L19: public class BackpackManager {
    - L20: public static LevelResource BACKPACKS = LevelResourceMixin.invokeInit("backpacks");
    - L22: public static void addBackpack(ServerPlayer player, ItemStack stack) {
    - L40: @Nullable
    - L41: public static ItemStack readBackpack(ServerLevel serverLevel, UUID playerUUID, String backpackId) {
    - L54: @Nullable
    - L55: public static ItemStack getBackpack(ServerLevel serverLevel, String backpackId) {
    - L75: public static File getBackpackFile(ServerLevel serverLevel, UUID playerUUID, String backpackId) {
    - L79: public static File getBackpackFile(ServerPlayer player, String backpackId) {
    - L83: public static File getPlayerBackpackFolder(ServerPlayer player) {
    - L87: public static File getPlayerBackpackFolder(ServerLevel serverLevel, UUID uuid) {
  - `src/main/java/com/tiviacz/travelersbackpack/common/ServerActions.java`
    - L1: package com.tiviacz.travelersbackpack.common;
    - L54: public class ServerActions {
    - L55: public static void swapTool(Player player, int slot, int button) {
    - L93: public static void equipBackpack(Player player, boolean equip) {
    - L101: public static boolean swapBackpack(Player player) {
  - `src/main/java/com/tiviacz/travelersbackpack/common/recipes/BackpackUpgradeRecipe.java`
    - L1: package com.tiviacz.travelersbackpack.common.recipes;
    - L23: public class BackpackUpgradeRecipe extends SimpleSmithingRecipe {
    - L24: public static final MapCodec<BackpackUpgradeRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
    - L34: public static final StreamCodec<RegistryFriendlyByteBuf, BackpackUpgradeRecipe> STREAM_CODEC = StreamCodec.composite(
    - L48: public static final RecipeSerializer<BackpackUpgradeRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    - L54: public BackpackUpgradeRecipe(CommonInfo commonInfo, Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, ItemStackTemplate result) {
    - L62: @Override
    - L63: public ItemStack assemble(SmithingRecipeInput input) {
    - L78: public void upgradeInventory(ItemStack stack, Tiers.Tier nextTier) {
    - L88: public RenderInfo getUpgradedTanksCapacity(ItemStack stack, int storageSlots) {
    - L97: @Override
    - L98: public Optional<Ingredient> templateIngredient() {
  - `src/main/java/com/tiviacz/travelersbackpack/common/recipes/BackpackUpgradeRecipeBuilder.java`
    - L1: package com.tiviacz.travelersbackpack.common.recipes;
    - L18: public class BackpackUpgradeRecipeBuilder {
    - L26: public BackpackUpgradeRecipeBuilder(Ingredient template, Ingredient base, Ingredient addition, RecipeCategory category, ItemStackTemplate result) {
    - L34: public static BackpackUpgradeRecipeBuilder backpackUpgrade(Ingredient template, Ingredient base, Ingredient addition, RecipeCategory category, Item result) {
    - L38: public BackpackUpgradeRecipeBuilder unlocks(String name, Criterion<?> criterion) {
    - L43: public void save(RecipeOutput output, String id) {
    - L47: public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
  - `src/main/java/com/tiviacz/travelersbackpack/common/recipes/ShapedBackpackRecipe.java`
    - L1: package com.tiviacz.travelersbackpack.common.recipes;
    - L30: public class ShapedBackpackRecipe extends NormalCraftingRecipe {
    - L31: public static final MapCodec<ShapedBackpackRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
    - L40: public static final StreamCodec<RegistryFriendlyByteBuf, ShapedBackpackRecipe> STREAM_CODEC = StreamCodec.composite(
    - L51: public static final RecipeSerializer<ShapedBackpackRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    - L52: public final ShapedRecipePattern pattern;
    - L53: public final ItemStackTemplate result;
    - L55: public ShapedBackpackRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo, ShapedRecipePattern pattern, ItemStackTemplate result) {
    - L61: @Override
    - L62: public ItemStack assemble(CraftingInput pInput) {
    - L104: public static int getProperColor(Item item) {
    - L114: @VisibleForTesting
  - `src/main/java/com/tiviacz/travelersbackpack/common/recipes/ShapedBackpackRecipeBuilder.java`
    - L1: package com.tiviacz.travelersbackpack.common.recipes;
    - L24: public class ShapedBackpackRecipeBuilder implements RecipeBuilder {
    - L31: @Nullable
    - L45: public static ShapedBackpackRecipeBuilder shaped(HolderGetter<Item> items, RecipeCategory category, ItemLike item) {
    - L49: public static ShapedBackpackRecipeBuilder shaped(HolderGetter<Item> items, RecipeCategory category, ItemLike item, int count) {
    - L53: public ShapedBackpackRecipeBuilder define(Character symbol, TagKey<Item> tag) {
    - L57: public ShapedBackpackRecipeBuilder define(Character symbol, ItemLike item) {
    - L61: public ShapedBackpackRecipeBuilder define(final Character symbol, final Ingredient ingredient) {
    - L72: public ShapedBackpackRecipeBuilder pattern(final String row) {
    - L81: public ShapedBackpackRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
    - L86: public ShapedBackpackRecipeBuilder group(@Nullable String group) {
    - L91: public ShapedBackpackRecipeBuilder showNotification(boolean showNotification) {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/data/minecraft/tags/villager_trade/wandering_trader/uncommon.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `common` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/common/BackpackAbilities.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/BackpackManager.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/ServerActions.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/recipes/BackpackUpgradeRecipe.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/recipes/BackpackUpgradeRecipeBuilder.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/recipes/ShapedBackpackRecipe.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/recipes/ShapedBackpackRecipeBuilder.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
