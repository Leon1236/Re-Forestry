# CORE-E4 — Naturalist chest + analyzer BER

**Date:** 2026-08-20  
**Ids:** `bee_chest` / `tree_chest` / `butterfly_chest` / `analyzer` (CE20; not CE 1.21.1 `apiarists_chest`).

## What landed

- In-world BER: CE cube models (`RenderNaturalistChest` 64×64 lid/base/lock; `RenderAnalyzer` pedestal/cover/towers) via 26.2 `extractRenderState` + `submit` and `SpriteId` / `Sheets.BLOCKS_MAPPER` (same as `RenderMachine`).
- Client lid interpolation (`lidAngle` / `prevLidAngle`, 0.1/tick) plus `numPlayersUsing` on `getUpdateTag` (`PlayersUsing`). No extra payload.
- Analyzer specimen is `SLOT_ANALYZE`. Full `saveCustomOnly` on `getUpdatePacket` / `getUpdateTag` (12 slots, not 125). `sendBlockUpdated` when that slot changes. No `PacketItemStackDisplay`.
- Item-in-hand: `NaturalistChestSpecialRenderer` / `AnalyzerSpecialRenderer` (closed lid / empty pedestal). Not CE `ForestryBewlr`.

## Not done

- `ARB-11.9c` pollen (out of this stage).
