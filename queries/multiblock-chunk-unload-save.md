# Multiblock inventory save on chunk unload (Fabric)

**Date:** 2026-07-29  
**Status:** Fixed in `MultiblockLogic`

## Problem

Fabric `ServerChunkEvents.CHUNK_UNLOAD` runs *before* `ChunkMap.save()` (Fabric `ChunkMapMixin` injects at the `save` invoke). ReForestry (like CE) detaches multiblock parts on unload and forfeits the save-delegate flag.

Alveary shared bee inventory lives on `AlvearyController` and is only written into the save-delegate tile’s `multiblockData` while `isMultiblockSaveDelegate() && controller != null`. Unload-then-save could therefore drop controller NBT (inventory, climate steps, etc.).

CE/Immersive do not have this snapshot — they assume unload ordering that still allows a live write, or rely on earlier autosaves. On Fabric the unload save can overwrite a good autosave without `multiblockData`.

## Fix

In `com.leon1236.reforestry.core.multiblock.MultiblockLogic`:

1. `detachSelf(..., chunkUnloading=true)` — if this part is the save delegate, snapshot `controller.write(...)` into `cachedMultiblockData` *before* detach/forfeit.
2. `write(ValueOutput|CompoundTag)` — persist live controller data when still attached as save delegate; otherwise persist `cachedMultiblockData` when present.

## Unaffected

Single-block inventories (apiary, beehouse, factory machines, alveary addon local slots) already use `ContainerHelper.saveAllItems` in `saveAdditional` + `setChanged()` — vanilla chunk save covers them.

## Smoke

Put bees/products in an alveary → walk far enough for the chunk to unload → return → contents still present.

## Tracker

Noted under Phase 4 rows **4.5** / **4.6** in `files/implemented-features.md`.
