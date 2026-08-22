# Tiviacz1337-Travelers-Backpack — network

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/network`
- Java files scanned: **11**
- Date: 2026-07-30

## Summary
Module `network` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/network` (11 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ClientboundSendMessagePacket`
- `ClientboundSyncAttachmentPacket`
- `ClientboundSyncComponentsPacket`
- `ClientboundSyncItemStackPacket`
- `ClientboundUpdateRecipePacket`
- `ServerboundActionTagPacket`
- `ServerboundFilterSettingsPacket`
- `ServerboundFilterTagsPacket`
- `ServerboundRetrieveBackpackPacket`
- `ServerboundSlotPacket`
- `SupporterBadgePacket`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "network"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSendMessagePacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L15: public record ClientboundSendMessagePacket(boolean drop, BlockPos pos) implements CustomPacketPayload {
    - L16: public static final Identifier ID = Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "send_message");
    - L17: public static final Type<ClientboundSendMessagePacket> TYPE = new Type<>(ID);
    - L19: public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSendMessagePacket> STREAM_CODEC = StreamCodec.composite(
    - L25: public static void handle(ClientboundSendMessagePacket message, ClientPlayNetworking.Context ctx) {
    - L35: @Override
    - L36: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSyncAttachmentPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L21: public record ClientboundSyncAttachmentPacket(int entityID, ItemStack backpack,
    - L23: public static final Identifier ID = Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "sync_attachment");
    - L24: public static final Type<ClientboundSyncAttachmentPacket> TYPE = new Type<>(ID);
    - L26: public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSyncAttachmentPacket> STREAM_CODEC = StreamCodec.composite(
    - L33: public ClientboundSyncAttachmentPacket(int entityID, ItemStack serverBackpack) {
    - L37: public ClientboundSyncAttachmentPacket(int entityID, ItemStack backpack, boolean removeData) {
    - L68: public static void handle(ClientboundSyncAttachmentPacket message, ClientPlayNetworking.Context ctx) {
    - L81: @Override
    - L82: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSyncComponentsPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L15: public record ClientboundSyncComponentsPacket(int entityID, DataComponentMap map) implements CustomPacketPayload {
    - L16: public static final Identifier ID = Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "sync_components");
    - L17: public static final Type<ClientboundSyncComponentsPacket> TYPE = new Type<>(ID);
    - L19: public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSyncComponentsPacket> STREAM_CODEC = StreamCodec.composite(
    - L25: public static void handle(ClientboundSyncComponentsPacket message, ClientPlayNetworking.Context ctx) {
    - L32: @Override
    - L33: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSyncItemStackPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L19: public record ClientboundSyncItemStackPacket(int entityId, int slot, Holder<Item> itemInstance,
    - L21: public static final Identifier ID = Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "sync_itemstack");
    - L22: public static final Type<ClientboundSyncItemStackPacket> TYPE = new Type<>(ID);
    - L23: public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSyncItemStackPacket> STREAM_CODEC = StreamCodec.composite(
    - L31: public static void handle(ClientboundSyncItemStackPacket message, ClientPlayNetworking.Context ctx) {
    - L58: @Override
    - L59: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundUpdateRecipePacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L15: public record ClientboundUpdateRecipePacket(ItemStack output,
    - L17: public static final Identifier ID = Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "update_recipe");
    - L18: public static final Type<ClientboundUpdateRecipePacket> TYPE = new Type<>(ID);
    - L20: public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundUpdateRecipePacket> STREAM_CODEC = StreamCodec.composite(
    - L32: public static void handle(ClientboundUpdateRecipePacket message, ClientPlayNetworking.Context ctx) {
    - L44: @Override
    - L45: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundActionTagPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L22: public record ServerboundActionTagPacket(CompoundTag actionTag) implements CustomPacketPayload {
    - L23: public static final Type<ServerboundActionTagPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "action_tag"));
    - L24: public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundActionTagPacket> STREAM_CODEC = StreamCodec.composite(
    - L29: public static final int UPGRADE_TAB = 0;
    - L30: public static final int OPEN_SCREEN = 1;
    - L31: public static final int OPEN_BACKPACK = 2;
    - L32: public static final int SORTER = 3;
    - L33: public static final int SLEEPING_BAG = 4;
    - L34: public static final int FILL_TANK = 5;
    - L35: public static final int SWAP_TOOL = 6;
    - L36: public static final int TOGGLE_BUTTONS_VISIBILITY = 7;
  - `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundFilterSettingsPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L20: public record ServerboundFilterSettingsPacket(int slot, List<Integer> settings) implements CustomPacketPayload {
    - L21: public static final Type<ServerboundFilterSettingsPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "filter_settings"));
    - L23: public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundFilterSettingsPacket> STREAM_CODEC = StreamCodec.composite(
    - L29: public static void handle(ServerboundFilterSettingsPacket message, ServerPlayNetworking.Context ctx) {
    - L53: @Override
    - L54: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundFilterTagsPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L20: public record ServerboundFilterTagsPacket(int slot, List<String> tags) implements CustomPacketPayload {
    - L21: public static final Type<ServerboundFilterTagsPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "filter_tags"));
    - L23: public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundFilterTagsPacket> STREAM_CODEC = StreamCodec.composite(
    - L29: public static void handle(ServerboundFilterTagsPacket message, ServerPlayNetworking.Context ctx) {
    - L54: @Override
    - L55: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundRetrieveBackpackPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L15: public record ServerboundRetrieveBackpackPacket(ItemStack backpackHolder) implements CustomPacketPayload {
    - L16: public static final Type<ServerboundRetrieveBackpackPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "retrieve_backpack"));
    - L18: public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundRetrieveBackpackPacket> STREAM_CODEC = StreamCodec.composite(
    - L23: public static void handle(ServerboundRetrieveBackpackPacket message, ServerPlayNetworking.Context ctx) {
    - L39: @Override
    - L40: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundSlotPacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L21: public record ServerboundSlotPacket(int selectType, List<Integer> unsortables,
    - L23: public static final Type<ServerboundSlotPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "slots"));
    - L24: public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundSlotPacket> STREAM_CODEC = StreamCodec.composite(
    - L31: public static final int UNSORTABLES = 0;
    - L32: public static final int MEMORY = 1;
    - L34: public static void handle(ServerboundSlotPacket message, ServerPlayNetworking.Context ctx) {
    - L68: @Override
    - L69: public Type<? extends CustomPacketPayload> type() {
  - `src/main/java/com/tiviacz/travelersbackpack/network/SupporterBadgePacket.java`
    - L1: package com.tiviacz.travelersbackpack.network;
    - L15: public class SupporterBadgePacket {
    - L16: public record Serverbound(boolean isEnabledForPlayer) implements CustomPacketPayload {
    - L17: public static final Type<Serverbound> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "supporter_badge_serverbound"));
    - L18: public static final StreamCodec<FriendlyByteBuf, Serverbound> STREAM_CODEC = StreamCodec.composite(
    - L23: @Override
    - L24: public Type<? extends CustomPacketPayload> type() {
    - L28: public static void handle(Serverbound message, ServerPlayNetworking.Context ctx) {
    - L44: public record Clientbound(boolean isEnabledForPlayer, String playerName) implements CustomPacketPayload {
    - L45: public static final Type<Clientbound> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TravelersBackpack.MODID, "supporter_badge_clientbound"));
    - L46: public static final StreamCodec<FriendlyByteBuf, Clientbound> STREAM_CODEC = StreamCodec.composite(
    - L52: @Override

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- record `ClientboundSendMessagePacket` in `ClientboundSendMessagePacket.java`
- key type `ClientboundSendMessagePacket` (`ClientboundSendMessagePacket.java`)
- record `ClientboundSyncAttachmentPacket` in `ClientboundSyncAttachmentPacket.java`
- key type `ClientboundSyncAttachmentPacket` (`ClientboundSyncAttachmentPacket.java`)
- record `ClientboundSyncComponentsPacket` in `ClientboundSyncComponentsPacket.java`
- key type `ClientboundSyncComponentsPacket` (`ClientboundSyncComponentsPacket.java`)
- record `ClientboundSyncItemStackPacket` in `ClientboundSyncItemStackPacket.java`
- key type `ClientboundSyncItemStackPacket` (`ClientboundSyncItemStackPacket.java`)
- record `ClientboundUpdateRecipePacket` in `ClientboundUpdateRecipePacket.java`
- key type `ClientboundUpdateRecipePacket` (`ClientboundUpdateRecipePacket.java`)
- record `ServerboundActionTagPacket` in `ServerboundActionTagPacket.java`
- key type `ServerboundActionTagPacket` (`ServerboundActionTagPacket.java`)
- record `ServerboundFilterSettingsPacket` in `ServerboundFilterSettingsPacket.java`
- key type `ServerboundFilterSettingsPacket` (`ServerboundFilterSettingsPacket.java`)
- record `ServerboundFilterTagsPacket` in `ServerboundFilterTagsPacket.java`
- key type `ServerboundFilterTagsPacket` (`ServerboundFilterTagsPacket.java`)
- record `ServerboundRetrieveBackpackPacket` in `ServerboundRetrieveBackpackPacket.java`
- key type `ServerboundRetrieveBackpackPacket` (`ServerboundRetrieveBackpackPacket.java`)
- record `ServerboundSlotPacket` in `ServerboundSlotPacket.java`
- key type `ServerboundSlotPacket` (`ServerboundSlotPacket.java`)
- record `Serverbound` in `SupporterBadgePacket.java`
- record `Clientbound` in `SupporterBadgePacket.java`
- key type `SupporterBadgePacket` (`SupporterBadgePacket.java`)

## Port relevance to Re-Forestry
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSendMessagePacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSyncAttachmentPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSyncComponentsPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundSyncItemStackPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ClientboundUpdateRecipePacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundActionTagPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundFilterSettingsPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundFilterTagsPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundRetrieveBackpackPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/ServerboundSlotPacket.java`
- `src/main/java/com/tiviacz/travelersbackpack/network/SupporterBadgePacket.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
