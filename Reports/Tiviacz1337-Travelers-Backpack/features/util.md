# Tiviacz1337-Travelers-Backpack — util

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/util`
- Java files scanned: **18**
- Date: 2026-07-30

## Summary
Module `util` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/util` (18 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BackpackDeathHelper`
- `ClientRegistryHelper`
- `ContainerContentsHelper`
- `CooldownHelper`
- `FluidStackHelper`
- `FluidTypeHelper`
- `FluidUtil`
- `HumanoidRenderStateBackpackInject`
- `InventoryHelper`
- `ItemStackUtils`
- `KeyHelper`
- `LogHelper`
- `PacketDistributor`
- `Reference`
- `RegistryHelper`
- `RenderHelper`
- `Supporters`
- `TextUtils`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "util"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/util/BackpackDeathHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L22: public class BackpackDeathHelper {
    - L23: public static boolean onPlayerDrops(Level level, Player player, ItemStack stack) {
  - `src/main/java/com/tiviacz/travelersbackpack/util/ClientRegistryHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L9: public class ClientRegistryHelper {
    - L10: public static Optional<RegistryAccess> getRegistryAccess() {
  - `src/main/java/com/tiviacz/travelersbackpack/util/ContainerContentsHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L7: public class ContainerContentsHelper {
    - L8: public static NonNullList<ItemStack> getItems(ItemContainerContents contents, int defaultSize) {
    - L15: public static ItemContainerContents updateStack(ItemContainerContents contents, int defaultSize, ItemStack stack, int index) {
  - `src/main/java/com/tiviacz/travelersbackpack/util/CooldownHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L5: public class CooldownHelper {
    - L6: public static int createCooldown(int minSeconds, int maxSeconds) {
    - L11: public static int seconds(int seconds) {
  - `src/main/java/com/tiviacz/travelersbackpack/util/FluidStackHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L18: public class FluidStackHelper {
    - L19: public static SoundEvent getFluidEmptySound(FluidVariant fluidVariant) {
    - L29: public static SoundEvent getFluidFillSound(FluidVariant fluidVariant) {
    - L39: public static FluidVariant setPotionFluidVariant(ItemStack stack, int potionType) {
    - L56: public static PotionContents getPotionTypeFromFluidVariant(FluidVariant variant) {
    - L60: public static ItemStack getItemStackFromFluidStack(FluidVariant variant) {
    - L64: public static ItemStack getSplashItemStackFromFluidStack(FluidVariant fluidStack) {
    - L68: public static ItemStack getLingeringItemStackFromFluidStack(FluidVariant fluidStack) {
    - L72: public static ItemStack createPotionStack(Item item, PotionContents contents) {
  - `src/main/java/com/tiviacz/travelersbackpack/util/FluidTypeHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L8: public class FluidTypeHelper {
    - L10: public static Component getFluidVariantName(FluidVariant variant) {
    - L14: public static SoundEvent getSound(FluidVariant fluid, boolean isFill) {
    - L18: public static final boolean BUCKET_EMPTY = false;
    - L19: public static final boolean BUCKET_FILL = true;
  - `src/main/java/com/tiviacz/travelersbackpack/util/FluidUtil.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L19: public class FluidUtil {
    - L20: public static Optional<Storage<FluidVariant>> getFluidStorageAtCursor(Player player, AbstractContainerMenu menu) {
    - L28: public static Optional<Storage<FluidVariant>> getFluidStorageAtSlot(SingleSlotStorage<ItemVariant> storage) {
    - L36: public static boolean hasFluidStorageConstant(ItemStack stack) {
    - L41: public static Optional<Storage<FluidVariant>> getFluidStorageConstant(ItemStack stack) {
    - L46: public static boolean hasFluid(Player player, AbstractContainerMenu menu) {
    - L53: public static boolean hasFluid(Storage<FluidVariant> storage) {
    - L57: public static long tryEmptyContainerAtCursor(FluidTank tank, long maxTransferAmount, Storage<FluidVariant> storage, boolean execute) {
    - L73: public static long tryFillContainerAtCursor(FluidTank tank, long maxTransferAmount, Storage<FluidVariant> storage, boolean execute) {
    - L89: public static long tryFillBucketAtCursor(FluidTank tank, long maxTransferAmount, Storage<FluidVariant> storage, boolean execute) {
    - L105: public static long tryFillContainerAtSlot(FluidTank tank, long maxTransferAmount, Storage<FluidVariant> storage, boolean execute, Transaction transaction) {
  - `src/main/java/com/tiviacz/travelersbackpack/util/HumanoidRenderStateBackpackInject.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L5: public interface HumanoidRenderStateBackpackInject {
  - `src/main/java/com/tiviacz/travelersbackpack/util/InventoryHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L10: public class InventoryHelper {
    - L11: public static ItemStack removeItem(ItemStackHandler handler, int slot, int amount) {
    - L22: public static ItemStack removeItemShiftClick(ItemStackHandler handler, int slot, int amount) {
    - L32: public static ItemStack takeItem(ItemStackHandler handler, int slot) {
    - L36: public static boolean isEmpty(ItemStackHandler handler) {
    - L45: public static void iteratePlayerInv(Inventory playerInv, BiConsumer<Integer, ItemStack> consumer) {
    - L52: public static void iterateHandler(ItemStackHandler handler, BiConsumer<Integer, ItemStack> consumer) {
    - L59: public static boolean iterate(ItemStackHandler handler, BiFunction<Integer, ItemStack, Boolean> function) {
    - L69: public static ItemStack addItemStackToHandler(ItemStackHandler handler, ItemStack stack, boolean simulate) {
    - L73: public static ItemStack extractFromBackpack(ItemStackHandler handler, ItemStack stack, int amount, boolean simulate) {
    - L82: public static ItemStack insertItemStacked(ItemStackHandler inventory, ItemStack stack, boolean simulate) {
  - `src/main/java/com/tiviacz/travelersbackpack/util/ItemStackUtils.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L16: public class ItemStackUtils {
    - L17: public static boolean isSameItemSameTags(ItemStack stack1, ItemStack stack2) {
    - L24: public static boolean isSameItemSameComponents(ItemStack pStack, ItemStack pOther) {
    - L32: public static boolean checkComponentsIgnoreDamage(DataComponentMap map, DataComponentMap other) {
    - L38: public static DataComponentMap createDataComponentMap(ItemStack serverDataHolder, DataComponentType... dataComponentTypes) {
    - L49: public static ItemStack reduceSize(ItemStack backpack) {
  - `src/main/java/com/tiviacz/travelersbackpack/util/KeyHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L6: public class KeyHelper {
    - L7: public static boolean isShiftPressed() {
    - L12: public static boolean isCtrlPressed() {
  - `src/main/java/com/tiviacz/travelersbackpack/util/LogHelper.java`
    - L1: package com.tiviacz.travelersbackpack.util;
    - L6: public class LogHelper {
    - L7: public static void log(Level level, String format, Object... data) {
    - L11: public static void info(String format, Object... data) {
    - L15: public static void error(String format, Object... data) {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ClientRegistryHelper` (`ClientRegistryHelper.java`)
- interface `HumanoidRenderStateBackpackInject` in `HumanoidRenderStateBackpackInject.java`
- key type `PacketDistributor` (`PacketDistributor.java`)
- key type `RegistryHelper` (`RegistryHelper.java`)

## Port relevance to Re-Forestry
- Mentions of `util` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/util/BackpackDeathHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/ClientRegistryHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/ContainerContentsHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/CooldownHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/FluidStackHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/FluidTypeHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/FluidUtil.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/HumanoidRenderStateBackpackInject.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/InventoryHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/ItemStackUtils.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/KeyHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/LogHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/PacketDistributor.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/Reference.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/RegistryHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/RenderHelper.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/Supporters.java`
- `src/main/java/com/tiviacz/travelersbackpack/util/TextUtils.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
