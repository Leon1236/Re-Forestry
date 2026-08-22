# SuperMartijn642-SuperMartijn642sCoreLib — network

## Summary

Small Forge **1.16.5** networking helper package (`com.supermartijn642.core.network`, **6 Java files, ~561 LOC**) that wraps Forge’s `SimpleChannel` into a typed, multiplexed packet API for dependent mods. Provides a `BasePacket` contract (`write` / `read` / optional `verify` / `handle`), a per-mod `PacketChannel` with index-based dispatch and direction enforcement, execution context (`PacketContext`), and abstract bases for block-position and block-entity targeted packets. **No assets, no lang, no datapack.** In this clone the package is **not referenced by other CoreLib modules** — it is a consumer-facing library API (paired conceptually with `block.BlockEntityBasePacket` ↔ `gui.BlockEntityBaseContainer`, but those only import types from here; nothing in CoreLib registers or sends packets). Re-Forestry must **not** depend on CoreLib; treat as a pattern reference only (`reforestry-standalone-adopt`).

## Player/API surface

Public types other mods subclass or call:

| Type | Role |
|---|---|
| `BasePacket` | Packet contract: serialize (`write`/`read`), optional validation (`verify`, default `true`), handle on receive (`handle(PacketContext)`). |
| `PacketChannel` | Channel factory + registry + send helpers. One Forge `SimpleChannel` per instance; all registered packet types multiplex through a single wrapper message (`Payload`) prefixed with an `int` index. |
| `PacketDirection` | `SERVER_TO_CLIENT`, `CLIENT_TO_SERVER`, `BOTH_WAYS` — enforced at send time and again on receive. |
| `PacketContext` | Receive-side context: handling/originating side (`CoreSide`), local player, world, main-thread task queue (`queueTask`). Wraps Forge `NetworkEvent.Context`. |
| `BlockPosBasePacket` | Abstract `BasePacket` storing `BlockPos`; default `write`/`read` via `PacketBuffer`; delegates `handle` → `handle(BlockPos, PacketContext)`. |
| `BlockEntityBasePacket<T extends TileEntity>` | Extends `BlockPosBasePacket`; optional `RegistryKey<World> dimension`; resolves TE on receive and calls `handle(T tile, PacketContext)` if present and cast-safe. |

### Typical mod author flow

1. `PacketChannel channel = PacketChannel.create("mymod", "main");` (or `create("mymod")` → channel name `"main"`).
2. For each packet class: `channel.registerMessage(MyPacket.class, MyPacket::new, PacketDirection.CLIENT_TO_SERVER, true);` — last arg = handle on main thread via `context.queueTask`.
3. Client: `channel.sendToServer(new MyPacket(...));`
4. Server: `channel.sendToPlayer(player, …)`, `sendToAllPlayers`, `sendToDimension`, `sendToAllTrackingEntity`, `sendToAllNear` (world/pos/radius overloads).

Deprecated overloads still present: parameterless `PacketChannel.create()`, `registerMessage` without direction (defaults `BOTH_WAYS`), `PacketContext.getSendingPlayer()` / `getUnderlyingContext()`.

## Architecture

```
Mod init
   │
   ▼
PacketChannel.create(modid, name)
   │  NetworkRegistry.newSimpleChannel(ResourceLocation(modid, name), protocol "1")
   │  registers ONE SimpleChannel message: Payload { BasePacket }
   │
   ▼
registerMessage(Class, Supplier, PacketDirection, shouldBeQueued)
   │  assigns int index; maps Class ↔ index ↔ direction ↔ queue flag
   │
Send path                          Receive path
─────────                          ────────────
checkRegistration(direction)       decoder: read index → supplier.get()
Payload(packet)                    read(buffer) on packet instance
channel.send*(Payload)             verify(context) → false = discard
                                   shouldBeQueued ? queueTask(handle) : handle.run()
```

**Multiplexing:** Instead of registering one Forge message per packet class, CoreLib writes `buffer.writeInt(index)` then delegates to `packet.write(buffer)`. The outer `Payload` type is the only registered `SimpleChannel` builder (id `0`).

**Side safety:** Server send helpers throw `IllegalStateException` if called client-side (where applicable). `sendToPlayer` requires `ServerPlayerEntity`. Direction mismatches throw at send (`IllegalArgumentException`) or receive (`RuntimeException`).

**Block-entity packets:** `BlockEntityBasePacket.getTileEntity` picks world as: explicit `dimension` if set; else `context.getWorld()` on client; on server, `context.getWorld().getServer().getLevel(dimension)` when dimension differs from handling world. Missing TE or failed cast → packet silently no-ops (handler not called).

Graphify (alias `corelib`): `PacketChannel` community ~25; links to `BasePacket`, `PacketProperties`, `SimpleChannel`, `RegistryUtil.isValidNamespace`. `BlockEntityBasePacket` community ~65; short path to `BaseBlockEntity` via shared `BlockPos` / TE concepts (vanilla `SUpdateTileEntityPacket` on BE side, not this channel).

## Data & assets

- **No** resources under this package (no textures, lang, JSON, or network codec registries in `src/main/resources`).
- Wire format is entirely defined by each packet’s `write`/`read` plus the channel’s leading `int` index.
- Protocol version string is hard-coded `"1"` (encoder/decoder accept only `"1"`).

## Dependencies

**Within CoreLib**

| Symbol | Used by | Purpose |
|---|---|---|
| `CoreLib.LOGGER` | `PacketChannel.create` | Warn when active mod creates channel for foreign/wrong modid |
| `RegistryUtil.isValidNamespace` | `PacketChannel.create` | Validate modid and channel name `[a-z0-9_.-]` |
| `CoreSide` | `PacketContext`, `BlockEntityBasePacket` | Logical side abstraction |
| `ClientUtils.getPlayer` / `getWorld` / `queueTask` | `PacketContext` | Client-side player/world and main-thread dispatch |

**External (this clone)**

- Forge networking: `NetworkRegistry`, `SimpleChannel`, `PacketDistributor`, `NetworkEvent.Context`, `ModLoadingContext`
- Minecraft 1.16.5: `PacketBuffer`, `ResourceLocation`, `BlockPos`, `World`, `RegistryKey`, `Registry`, `Entity`, `PlayerEntity`, `ServerPlayerEntity`, `TileEntity`

**Not a dependency of Re-Forestry** — copy/adapt ideas only; do not add `supermartijn642corelib` to `fabric.mod.json`.

## Notable algorithms/contracts

1. **Verify gate** — `verify(context)` runs before `handle`; return `false` drops the packet without error (useful for stale pos/permission checks).
2. **Main-thread default** — `shouldBeQueued == true` → `context.enqueueWork` (server) or `ClientUtils.queueTask` (client); `false` runs handler on network thread (dangerous for world/block access).
3. **Registration uniqueness** — duplicate `registerMessage` for same class throws; unregistered class at send throws.
4. **Index bounds (upstream quirk)** — `read` checks `if (packetsByIndex.size() < index)`; valid last index is `size - 1`, so an index equal to `size` slips through and can throw `IndexOutOfBoundsException` instead of the intended “unregistered packet” error. Negative indices behave similarly. Do not copy this check literally if adopting the pattern.
5. **Dimension optional encoding** — `BlockEntityBasePacket` writes a boolean then optional `ResourceLocation` for dimension; `null` dimension means “use receiver’s relevant world” (player world / client world).
6. **Modid warning** — if `ModLoadingContext.getActiveNamespace()` is not `minecraft`/`forge` and differs from requested modid, logs a warning (helps catch channels registered under wrong namespace).
7. **Exception wrapping** — write/read/verify/handle exceptions become `RuntimeException` with packet class and channel id in the message.

## Port relevance to Re-Forestry

| Idea | Verdict for RF (Fabric 26.2) |
|---|---|
| Depend on / ship CoreLib | **No** — standalone adopt only. |
| Whole `PacketChannel` + Forge `SimpleChannel` | **Do not port** — RF target is Fabric networking (`PayloadTypeRegistry`, `CustomPayload`, `StreamCodec` / `RegistryFriendlyByteBuf`, `ServerPlayNetworking` / `ClientPlayNetworking`). One payload type per packet (or a typed registry) replaces index multiplexing. |
| `BasePacket` write/read/verify/handle lifecycle | **Useful pattern** — map to `CustomPayload` + handler with validation before world mutation; aligns with CE-style “check then act” packet handling. |
| `PacketDirection` enforcement | **Useful pattern** — register separate S2C/C2S payload types or assert direction in handler; Fabric does not auto-enforce like Forge registration. |
| `PacketContext.queueTask` | **Required habit on Fabric too** — always schedule block/BE/GUI work on the server/main thread (`server.execute`, client `Minecraft.getInstance().execute`). |
| `BlockPosBasePacket` / `BlockEntityBasePacket` | **Reference for custom packets** — RF today syncs mostly via vanilla `ClientboundBlockEntityDataPacket` / `getUpdateTag`, data components (`networkSynchronized`), and multiblock `encodeDescriptionPacket`/`decodeDescriptionPacket` on `MultiblockLogic` — not via a shared packet base. When RF adds bespoke payloads (GUI actions, genetics tools, alveary control), a local `BlockPosPayload` helper with TE lookup + dimension check is worth copying conceptually, not as CoreLib types. |
| Index-multiplexed single channel | **Usually skip** — Fabric 26.2 prefers explicit payload ids (`Identifier`) per message; multiplexing adds versioning fragility (index order must match exactly across mod versions). |
| `PacketBuffer` serialization | **Replace** — `RegistryFriendlyByteBuf` + codecs (see existing RF: `SmelterRecipe` stream codec, `ApicultureDataComponents` synced components). |

Net: treat **network** as a **small pattern library** (typed packets, direction, verify, BE-at-pos resolution, main-thread dispatch), not a drop-in. RF networking source of truth remains CE/IF patterns adapted to Fabric payload API when custom packets are needed.

## Source map

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`

| File | Lines | Notes |
|---|---|---|
| `src/main/java/com/supermartijn642/core/network/PacketChannel.java` | ~312 | Channel factory, registry, multiplex encode/decode, all send helpers, inner `PacketProperties` + `Payload` |
| `src/main/java/com/supermartijn642/core/network/BasePacket.java` | ~35 | Core packet interface |
| `src/main/java/com/supermartijn642/core/network/PacketContext.java` | ~71 | Side/player/world + queueTask |
| `src/main/java/com/supermartijn642/core/network/BlockEntityBasePacket.java` | ~92 | TE-targeted abstract packet |
| `src/main/java/com/supermartijn642/core/network/BlockPosBasePacket.java` | ~40 | Pos-targeted abstract packet |
| `src/main/java/com/supermartijn642/core/network/PacketDirection.java` | ~11 | Direction enum |

Related (other modules, cited only):

- `.../CoreLib.java`, `.../CoreSide.java`, `.../ClientUtils.java` — logging, side, client queue
- `.../registry/RegistryUtil.java` — namespace validation on channel create
- `.../block/BaseBlockEntity.java` — vanilla `SUpdateTileEntityPacket` sync (separate from this custom channel)
- `.../gui/BlockEntityBaseContainer.java`, `.../gui/widget/BlockEntityBaseWidget.java` — GUI object lifetime at `BlockPos` (no packet imports required)

Evidence: graphify alias `corelib`; MCP repo `SuperMartijn642-SuperMartijn642sCoreLib`; inventory `Reports/SuperMartijn642-SuperMartijn642sCoreLib/00-INVENTORY.md`.

## Open questions/gaps

1. **No in-clone call sites** — zero `registerMessage` / `sendTo*` usages in this CoreLib tree; real packet subclasses live in other SuperMartijn642 mods (not analyzed here). Phase 2 could sample one consumer mod if RF needs concrete examples.
2. **Forge 1.16.5 only in this clone** — newer CoreLib builds on CurseForge/Modrinth may target Fabric or modern MC; Fabric-equivalent API in upstream was **not verified** (README does not mention networking).
3. **RF custom networking status** — grep shows no `ServerPlayNetworking` / `CustomPayload` in RF yet; genetics/components and BE update packets cover current sync. Unclear which CE/IF features will need bespoke payloads first (analyzer, mailbox, sorter, etc.) — defer until porting those features.
4. **Index-multiplex versioning** — adding/removing/reordering registered packets changes indices; no migration/version field beyond protocol `"1"`. Prefer explicit payload ids on Fabric.
5. **Security** — `verify` is optional; `BlockEntityBasePacket` does not check player reach/permission. Any RF adopt must add server-side validation (distance, container still open, owner) per Forestry CE norms.
