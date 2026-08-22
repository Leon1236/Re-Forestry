# bernie-g-geckolib — network

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/network`
- Java files scanned: **17**
- Date: 2026-07-30

## Summary
Module `network` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/network` (17 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `package-info`
- `MultiloaderPacket`
- `BlockEntityAnimTriggerPacket`
- `StatelessBlockEntityPlayAnimPacket`
- `StatelessBlockEntityStopAnimPacket`
- `StopTriggeredBlockEntityAnimPacket`
- `package-info`
- `EntityAnimTriggerPacket`
- `StatelessEntityPlayAnimPacket`
- `StatelessEntityStopAnimPacket`
- `StopTriggeredEntityAnimPacket`
- `package-info`
- `SingletonAnimTriggerPacket`
- `StatelessSingletonPlayAnimPacket`
- `StatelessSingletonStopAnimPacket`
- `StopTriggeredSingletonAnimPacket`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "network"`
- Source root exists: **True**
- Nested packages under this module:
  - `packet`
  - `packet/blockentity`
  - `packet/entity`
  - `packet/singleton`
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/network/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.network;
  - `common/src/main/java/com/geckolib/network/packet/MultiloaderPacket.java`
    - L1: package com.geckolib.network.packet;
    - L10: public interface MultiloaderPacket extends CustomPacketPayload {
  - `common/src/main/java/com/geckolib/network/packet/blockentity/BlockEntityAnimTriggerPacket.java`
    - L1: package com.geckolib.network.packet.blockentity;
    - L19: public record BlockEntityAnimTriggerPacket(BlockPos pos, Optional<String> controllerName, String animName) implements MultiloaderPacket {
    - L20: public static final CustomPacketPayload.Type<BlockEntityAnimTriggerPacket> TYPE = new Type<>(GeckoLibConstants.id("blockentity_anim_trigger"));
    - L21: public static final StreamCodec<FriendlyByteBuf, BlockEntityAnimTriggerPacket> CODEC = StreamCodec.composite(
    - L27: @Override
    - L28: public Type<? extends CustomPacketPayload> type() {
    - L32: @Override
    - L33: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/blockentity/StatelessBlockEntityPlayAnimPacket.java`
    - L1: package com.geckolib.network.packet.blockentity;
    - L19: public record StatelessBlockEntityPlayAnimPacket(BlockPos blockPos, RawAnimation animation) implements MultiloaderPacket {
    - L20: public static final Type<StatelessBlockEntityPlayAnimPacket> TYPE = new Type<>(GeckoLibConstants.id("stateless_block_entity_play_anim"));
    - L21: public static final StreamCodec<FriendlyByteBuf, StatelessBlockEntityPlayAnimPacket> CODEC = StreamCodec.composite(
    - L26: @Override
    - L27: public Type<? extends CustomPacketPayload> type() {
    - L31: @Override
    - L32: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/blockentity/StatelessBlockEntityStopAnimPacket.java`
    - L1: package com.geckolib.network.packet.blockentity;
    - L19: public record StatelessBlockEntityStopAnimPacket(BlockPos blockPos, String animation) implements MultiloaderPacket {
    - L20: public static final Type<StatelessBlockEntityStopAnimPacket> TYPE = new Type<>(GeckoLibConstants.id("stateless_block_entity_stop_anim"));
    - L21: public static final StreamCodec<FriendlyByteBuf, StatelessBlockEntityStopAnimPacket> CODEC = StreamCodec.composite(
    - L26: @Override
    - L27: public Type<? extends CustomPacketPayload> type() {
    - L31: @Override
    - L32: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/blockentity/StopTriggeredBlockEntityAnimPacket.java`
    - L1: package com.geckolib.network.packet.blockentity;
    - L19: public record StopTriggeredBlockEntityAnimPacket(BlockPos pos, Optional<String> controllerName, Optional<String> animName) implements MultiloaderPacket {
    - L20: public static final Type<StopTriggeredBlockEntityAnimPacket> TYPE = new Type<>(GeckoLibConstants.id("stop_triggered_blockentity_anim"));
    - L21: public static final StreamCodec<FriendlyByteBuf, StopTriggeredBlockEntityAnimPacket> CODEC = StreamCodec.composite(
    - L27: @Override
    - L28: public Type<? extends CustomPacketPayload> type() {
    - L32: @Override
    - L33: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/blockentity/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.network.packet.blockentity;
  - `common/src/main/java/com/geckolib/network/packet/entity/EntityAnimTriggerPacket.java`
    - L1: package com.geckolib.network.packet.entity;
    - L21: public record EntityAnimTriggerPacket(int entityId, boolean isReplacedEntity, Optional<String> controllerName, String animName) implements MultiloaderPacket {
    - L22: public static final CustomPacketPayload.Type<EntityAnimTriggerPacket> TYPE = new Type<>(GeckoLibConstants.id("entity_anim_trigger"));
    - L23: public static final StreamCodec<FriendlyByteBuf, EntityAnimTriggerPacket> CODEC = StreamCodec.composite(
    - L30: @Override
    - L31: public Type<? extends CustomPacketPayload> type() {
    - L35: @Override
    - L36: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/entity/StatelessEntityPlayAnimPacket.java`
    - L1: package com.geckolib.network.packet.entity;
    - L21: public record StatelessEntityPlayAnimPacket(int entityId, boolean isReplacedEntity, RawAnimation animation) implements MultiloaderPacket {
    - L22: public static final Type<StatelessEntityPlayAnimPacket> TYPE = new Type<>(GeckoLibConstants.id("stateless_entity_play_anim"));
    - L23: public static final StreamCodec<FriendlyByteBuf, StatelessEntityPlayAnimPacket> CODEC = StreamCodec.composite(
    - L29: @Override
    - L30: public Type<? extends CustomPacketPayload> type() {
    - L34: @Override
    - L35: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/entity/StatelessEntityStopAnimPacket.java`
    - L1: package com.geckolib.network.packet.entity;
    - L20: public record StatelessEntityStopAnimPacket(int entityId, boolean isReplacedEntity, String animation) implements MultiloaderPacket {
    - L21: public static final Type<StatelessEntityStopAnimPacket> TYPE = new Type<>(GeckoLibConstants.id("stateless_entity_stop_anim"));
    - L22: public static final StreamCodec<FriendlyByteBuf, StatelessEntityStopAnimPacket> CODEC = StreamCodec.composite(
    - L28: @Override
    - L29: public Type<? extends CustomPacketPayload> type() {
    - L33: @Override
    - L34: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/entity/StopTriggeredEntityAnimPacket.java`
    - L1: package com.geckolib.network.packet.entity;
    - L21: public record StopTriggeredEntityAnimPacket(int entityId, boolean isReplacedEntity, Optional<String> controllerName, Optional<String> animName) implements MultiloaderPacket {
    - L22: public static final Type<StopTriggeredEntityAnimPacket> TYPE = new Type<>(GeckoLibConstants.id("stop_triggered_entity_anim"));
    - L23: public static final StreamCodec<FriendlyByteBuf, StopTriggeredEntityAnimPacket> CODEC = StreamCodec.composite(
    - L30: @Override
    - L31: public Type<? extends CustomPacketPayload> type() {
    - L35: @Override
    - L36: public void receiveMessage(@Nullable Player sender, Consumer<Runnable> workQueue) {
  - `common/src/main/java/com/geckolib/network/packet/entity/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.network.packet.entity;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `MultiloaderPacket` in `MultiloaderPacket.java`
- key type `MultiloaderPacket` (`MultiloaderPacket.java`)
- record `BlockEntityAnimTriggerPacket` in `BlockEntityAnimTriggerPacket.java`
- key type `BlockEntityAnimTriggerPacket` (`BlockEntityAnimTriggerPacket.java`)
- record `StatelessBlockEntityPlayAnimPacket` in `StatelessBlockEntityPlayAnimPacket.java`
- key type `StatelessBlockEntityPlayAnimPacket` (`StatelessBlockEntityPlayAnimPacket.java`)
- record `StatelessBlockEntityStopAnimPacket` in `StatelessBlockEntityStopAnimPacket.java`
- key type `StatelessBlockEntityStopAnimPacket` (`StatelessBlockEntityStopAnimPacket.java`)
- record `StopTriggeredBlockEntityAnimPacket` in `StopTriggeredBlockEntityAnimPacket.java`
- key type `StopTriggeredBlockEntityAnimPacket` (`StopTriggeredBlockEntityAnimPacket.java`)
- record `EntityAnimTriggerPacket` in `EntityAnimTriggerPacket.java`
- key type `EntityAnimTriggerPacket` (`EntityAnimTriggerPacket.java`)
- record `StatelessEntityPlayAnimPacket` in `StatelessEntityPlayAnimPacket.java`
- key type `StatelessEntityPlayAnimPacket` (`StatelessEntityPlayAnimPacket.java`)
- record `StatelessEntityStopAnimPacket` in `StatelessEntityStopAnimPacket.java`
- key type `StatelessEntityStopAnimPacket` (`StatelessEntityStopAnimPacket.java`)
- record `StopTriggeredEntityAnimPacket` in `StopTriggeredEntityAnimPacket.java`
- key type `StopTriggeredEntityAnimPacket` (`StopTriggeredEntityAnimPacket.java`)
- record `SingletonAnimTriggerPacket` in `SingletonAnimTriggerPacket.java`
- key type `SingletonAnimTriggerPacket` (`SingletonAnimTriggerPacket.java`)
- record `StatelessSingletonPlayAnimPacket` in `StatelessSingletonPlayAnimPacket.java`
- key type `StatelessSingletonPlayAnimPacket` (`StatelessSingletonPlayAnimPacket.java`)
- record `StatelessSingletonStopAnimPacket` in `StatelessSingletonStopAnimPacket.java`
- key type `StatelessSingletonStopAnimPacket` (`StatelessSingletonStopAnimPacket.java`)
- record `StopTriggeredSingletonAnimPacket` in `StopTriggeredSingletonAnimPacket.java`
- key type `StopTriggeredSingletonAnimPacket` (`StopTriggeredSingletonAnimPacket.java`)

## Port relevance to Re-Forestry
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/network/package-info.java`
- `common/src/main/java/com/geckolib/network/packet/MultiloaderPacket.java`
- `common/src/main/java/com/geckolib/network/packet/blockentity/BlockEntityAnimTriggerPacket.java`
- `common/src/main/java/com/geckolib/network/packet/blockentity/StatelessBlockEntityPlayAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/blockentity/StatelessBlockEntityStopAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/blockentity/StopTriggeredBlockEntityAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/blockentity/package-info.java`
- `common/src/main/java/com/geckolib/network/packet/entity/EntityAnimTriggerPacket.java`
- `common/src/main/java/com/geckolib/network/packet/entity/StatelessEntityPlayAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/entity/StatelessEntityStopAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/entity/StopTriggeredEntityAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/entity/package-info.java`
- `common/src/main/java/com/geckolib/network/packet/singleton/SingletonAnimTriggerPacket.java`
- `common/src/main/java/com/geckolib/network/packet/singleton/StatelessSingletonPlayAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/singleton/StatelessSingletonStopAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/singleton/StopTriggeredSingletonAnimPacket.java`
- `common/src/main/java/com/geckolib/network/packet/singleton/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
