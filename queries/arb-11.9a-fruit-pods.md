# ARB-11.9a — Fruit pods

**Date:** 2026-07-25  
**Status:** Implemented

## What landed

- `ForestryPodType` (cocoa/dates/papaya/coconut)
- `BlockFruitPod` + `TileFruitPod` (AGE 0–2, log-face survival, ripening, bonemeal, drops)
- `ArboricultureBlocks.PODS` (`pods_*` ids, BlockItems + creative tab)
- `ArboricultureTiles.PODS`
- `PodFruit.trySpawnFruitBlock` — cocoa → vanilla `Blocks.COCOA`; others → `setFruitBlock`
- Log tags: `ReforestryBiomeTags.Blocks.PALM_LOGS` / `PAPAYA_LOGS` / `COCONUT_LOGS` (JSON already existed)
- `IFruit.getLogTag()` default `JUNGLE_LOGS`
- `BlockUtil` pod helpers (`tryPlantCocoaPod`, `getValidPodFacing`, `isValidPodLocation`)

## Fabric / MC 26.2 notes

- CE extends `CocoaBlock`; 26.2 `CocoaBlock.codec()` returns invariant `MapCodec<CocoaBlock>`, so local `BlockFruitPod` extends `HorizontalDirectionalBlock` and copies cocoa AGE/shapes/placement.
- Chunk checks: `Level.isLoaded` when available, else `LevelReader.hasChunk` (no deprecated `hasChunkAt`).

## Playtest

Grow date/papaya/coconut saplings — pods should appear on matching log faces during `generateExtras`. Cocoa fruit allele plants vanilla cocoa on jungle logs. Break ripe pods for fruit items; creative pod items place without crash (need adjacent valid log to survive).
