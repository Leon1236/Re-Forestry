# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X — networking

- Alias: `kaupenjoe`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Package/path root: `src/main/java/net/kaupenjoe/tutorialmod/networking`
- Java files scanned: **4**
- Date: 2026-07-30

## Summary
Module `networking` in `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` is rooted at `src/main/java/net/kaupenjoe/tutorialmod/networking` (4 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `ClientboundPackets`
- `ModPackets`
- `ServerboundPackets`
- `TestPayloadC2S`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py kaupenjoe "networking"`
- Source root exists: **True**
- Nested packages under this module:
  - `packet`
- Declaration skim (first files):
  - `src/main/java/net/kaupenjoe/tutorialmod/networking/ClientboundPackets.java`
    - L1: package net.kaupenjoe.tutorialmod.networking;
    - L4: public class ClientboundPackets {
  - `src/main/java/net/kaupenjoe/tutorialmod/networking/ModPackets.java`
    - L1: package net.kaupenjoe.tutorialmod.networking;
    - L8: public class ModPackets {
    - L21: public static void registerPackets() {
  - `src/main/java/net/kaupenjoe/tutorialmod/networking/ServerboundPackets.java`
    - L1: package net.kaupenjoe.tutorialmod.networking;
    - L9: public class ServerboundPackets {
    - L10: public static void handleTestPayload(TestPayloadC2S testPayloadC2S, ServerPlayNetworking.Context context) {
  - `src/main/java/net/kaupenjoe/tutorialmod/networking/packet/TestPayloadC2S.java`
    - L1: package net.kaupenjoe.tutorialmod.networking.packet;
    - L10: public record TestPayloadC2S(String name, int value) implements CustomPacketPayload {
    - L11: public static final Type<TestPayloadC2S> TYPE = new Type<>(
    - L20: public static final StreamCodec<RegistryFriendlyByteBuf, TestPayloadC2S> STREAM_CODEC = StreamCodec.composite(
    - L30: @Override
    - L31: public Type<? extends CustomPacketPayload> type() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `ClientboundPackets` (`ClientboundPackets.java`)
- key type `ModPackets` (`ModPackets.java`)
- key type `ServerboundPackets` (`ServerboundPackets.java`)
- record `TestPayloadC2S` in `TestPayloadC2S.java`

## Port relevance to Re-Forestry
- Pattern/reference only — do not add as a player dependency.

## Source map
- `src/main/java/net/kaupenjoe/tutorialmod/networking/ClientboundPackets.java`
- `src/main/java/net/kaupenjoe/tutorialmod/networking/ModPackets.java`
- `src/main/java/net/kaupenjoe/tutorialmod/networking/ServerboundPackets.java`
- `src/main/java/net/kaupenjoe/tutorialmod/networking/packet/TestPayloadC2S.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
