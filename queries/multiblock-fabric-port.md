# Multiblock framework — Fabric port notes (Phase F)

**Date:** 2026-07-25  
**Reference:** `thedarkcolour-ForestryCE` / Immersive-Forestry `forestry.core.multiblock.*`  
**Local:** `com.leon1236.reforestry.core.multiblock` + `api.multiblock`

## Forge → Fabric substitutes

| CE / Forge | Re-Forestry |
|---|---|
| `TickEvent.LevelTickEvent` START | `ServerTickEvents.START_LEVEL_TICK` + `ClientTickEvents.START_LEVEL_TICK` |
| `ChunkEvent.Load` | `ServerChunkEvents.CHUNK_LOAD` / `ClientChunkEvents.CHUNK_LOAD` |
| `LevelEvent.Unload` | `ServerLevelEvents.UNLOAD` + client `ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE` |
| `BlockEntity.onLoad` | `ServerBlockEntityEvents` / `ClientBlockEntityEvents` `BLOCK_ENTITY_LOAD` |
| `BlockEntity.onChunkUnloaded` | Chunk unload iterates BEs → `IMultiblockLogic.onChunkUnload` |
| `BlockEntity.setRemoved` | still calls `invalidate` (non-chunk break) |
| `LevelChunk.setUnsaved(true)` | `LevelChunk.markUnsaved()` |
| `ChunkPos.asLong` / `.x`/`.z` | `ChunkPos.pack` / `.x()` / `.z()` |

## Packages

- API: `IMultiblockComponent`, `IMultiblockController`, `IMultiblockLogic`, `MultiblockTileEntityBase`
- Core: registry / world registry / logic / controller base / rectangular / forestry owner wrapper
- Smoke subclass: `TestRectangularController` + `TestMultiblockLogic` (not registered as blocks; alveary G will be the playable structure)

## Deferred to G (alveary)

- `IAlveary*` / farm multiblock APIs
- Inventory adapter on `MultiblockControllerForestry` / tile inventories
- Registered alveary blocks + GUI
