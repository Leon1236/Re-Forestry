# CORE-E1a — Escritoire block + empty GUI

**Date:** 2026-08-20  
**Status:** Done (game/notes/BER/villager follow in E1b–E1c / E5 / V2).

## What landed

- Registry id `reforestry:escritoire` (CE `forestry:escritoire`).
- `BlockTypeCore.ESCRITOIRE` with CE desk collision shapes (facing-aware via `MachineProperties.setShape`).
- `TileEscritoire` + 12-slot inventory (CE layout): analyze `0`, results `1–6`, inputs `7–11`.
- Menu/screen with CE slot positions; player inventory offset `(34, 153)` on the 228×235 GUI.
- Carpenter recipe: 500 mB `seed_oil`, planks pattern, time 50 — extracted via `tools/extract_carpenter_recipes.py --escritoire`.
- Creative tab entry next to analyzer. Block loot table. Facing blockstates (`BlockMachine.FACING`).
- `#minecraft:mineable/axe` includes `reforestry:escritoire`.

## Package

`com.leon1236.reforestry.core.escritoire` (not CE `core.content.escritoire`).

## Smoke

1. Carpenter: planks + 500 mB seed oil → escritoire.
2. Place, open GUI.
3. Save/reload — items still in the desk.
