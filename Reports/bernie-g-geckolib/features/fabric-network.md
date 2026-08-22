# bernie-g-geckolib — fabric-network

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `fabric/src/main/java/com/geckolib/network`
- Java files scanned: **1**
- Date: 2026-07-30

## Summary
Module `fabric-network` in `bernie-g-geckolib` is rooted at `fabric/src/main/java/com/geckolib/network` (1 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `GeckoLibNetworkingFabric`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "fabric-network"`
- Source root exists: **True**
- Declaration skim (first files):
  - `fabric/src/main/java/com/geckolib/network/GeckoLibNetworkingFabric.java`
    - L1: package com.geckolib.network;
    - L19: public final class GeckoLibNetworkingFabric implements GeckoLibNetworking {
    - L23: @SuppressWarnings("unchecked")
    - L24: @Override
    - L25: public <B extends FriendlyByteBuf, P extends MultiloaderPacket> void registerPacketInternal(CustomPacketPayload.Type<P> payloadType, StreamCodec<B, P> codec, boolean isClientBound) {
    - L43: @Override
    - L44: public void sendToAllPlayersTrackingEntity(MultiloaderPacket packet, Entity trackingEntity) {
    - L54: @Override
    - L55: public void sendToAllPlayersTrackingBlock(MultiloaderPacket packet, ServerLevel level, BlockPos pos) {
    - L62: @Override
    - L63: public void sendToPlayer(MultiloaderPacket packet, ServerPlayer player) {

## Data & assets
Related resource paths (heuristic name match):
- `fabric/src/main/resources/fabric.mod.json`
- `common/src/main/resources/geckolib.mixins.json`
- `common/src/main/resources/geckolib.png`
- `common/src/main/resources/META-INF/interface_injections.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `GeckoLibNetworkingFabric` (`GeckoLibNetworkingFabric.java`)

## Port relevance to Re-Forestry
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `fabric/src/main/java/com/geckolib/network/GeckoLibNetworkingFabric.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
