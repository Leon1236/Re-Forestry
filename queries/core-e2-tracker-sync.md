# CORE-E2 — Breeding tracker client sync

Fabric play payload vs Forestry CE 1.21.1 Forge/NeoForge packet, and the join/dimension hooks we used.

## Payload

| | CE 1.21.1 | Re-Forestry (Fabric 26.2) |
|---|---|---|
| Wire id | `forestry:genome_tracker_update` (`PacketIdClient.GENOME_TRACKER_UPDATE`) | `reforestry:genome_tracker_update` |
| Class | `PacketGenomeTrackerSync` (`CustomPacketPayload`) | `GenomeTrackerSyncPayload` (`CustomPacketPayload`) |
| Body | `RegistryFriendlyByteBuf.writeNbt` / `readNbt` of a `CompoundTag` | `ByteBufCodecs.COMPOUND_TAG` on `RegistryFriendlyByteBuf` |
| NBT keys | `TYPE`, `SD`, `MD`, `RD` (plus subclass ints in `writeUpdateData`) | Same `TYPE` / `SD` / `MD` / `RD`. A-DISC1 adds CE keys `QueensTotal` / `PrincessesTotal` / `DronesTotal` via `writeUpdateData` |

MC 26.2 still has `net.minecraft.nbt.CompoundTag`. Yarn’s older `CustomPayload` name is `CustomPacketPayload` on this mapping. `CompoundTag.getString` returns `Optional`; we use `getStringOr`. String lists use `getListOrEmpty` + `ListTag.getStringOr`.

Do **not** copy CE’s NeoForge `NetworkUtil` / event-bus packet registrar. Fabric:

- Register codec: `PayloadTypeRegistry.clientboundPlay().register(type, codec)` (26.2 FAPI; there is no `playS2C()`)
- Send: `ServerPlayNetworking.send(player, payload)`
- Receive: `ClientPlayNetworking.registerGlobalReceiver` from client init only

`PacketRegistry` is the common registrar later stages should add payloads to (`registerClientbound`). Client receivers stay out of that class so a dedicated server never loads `fabric.api.client.networking`.

## Client cache

CE picks `ClientBreedingHandler` vs `ServerBreedingHandler` at enum init with `FMLEnvironment.dist`. DistExecutor is deprecated there.

We register a `BreedingTrackerManager.ClientAccess` from `CoreClientHandler` → `ClientBreedingHandler.register()`. `getTracker` uses `Level.isClientSide()` and delegates to that access. The server enum does not reference the client class.

Login sends a full dump (`syncToPlayer`). `registerSpecies` / `registerMutation` / `researchMutation` send only the new entries; the client **merges** into the cache (same as CE `load()`). Disconnect clears the cache (`ClientPlayConnectionEvents.DISCONNECT`) so a later world does not keep the previous session’s discoveries.

## Join / dimension

| | CE | Re-Forestry |
|---|---|---|
| Login | NeoForge `PlayerEvent.PlayerLoggedInEvent` | Fabric `ServerPlayConnectionEvents.JOIN` |
| Dimension change | NeoForge `PlayerEvent.PlayerChangedDimensionEvent` | Fabric `ServerEntityLevelChangeEvents.AFTER_PLAYER_CHANGE_LEVEL` (26.2 name; not `ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD`) |

Each hook loops `ForestrySpeciesTypes.BEE`, `TREE`, and `BUTTERFLY` and calls `syncToPlayer`. Butterfly tracker is empty until lepidopterology.

Analyzer GUI and worktable packets are out of CORE-E2. Apiarist caste counts are A-DISC1.
