# CORE-E5 — Escritoire BER

**Date:** 2026-08-20  
**Status:** Done.  
**Ids:** `escritoire` (CE `forestry:escritoire`).

## What landed

- In-world BER: CE desk model (`RenderEscritoire` 64×32 `escritoire.png`; desk / stands / drawers) via 26.2 `extractRenderState` + `submit` and `SpriteId` / `Sheets.BLOCKS_MAPPER` (same pattern as analyzer/chest).
- Specimen is `InventoryEscritoire.SLOT_ANALYZE`. Full `saveCustomOnly` on `getUpdatePacket` / `getUpdateTag` (12 slots). `sendBlockUpdated` when that slot changes. No `PacketItemStackDisplay` / `reforestry:itemstack_display`.
- Display item sits on the desk (`translate 0.5, 0.65` + `0.75` scale + bob/spin), rotated with facing — CE layout.
- Item-in-hand: `EscritoireSpecialRenderer` (empty desk). Registered as `reforestry:escritoire` special model (not CE `ForestryBewlr`).

## Choice vs CE

CE syncs the analyze stack with `forestry:itemstack_display` (`IItemStackDisplay` + client-only `individualOnDisplayClient`). Analyzer already used BE update tags in `CORE-E4`; escritoire mirrors that because the desk inventory is only 12 slots.
