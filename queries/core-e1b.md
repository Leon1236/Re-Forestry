# CORE-E1b — Escritoire memory game

**Date:** 2026-08-20  
**Status:** Done. Bee/tree specimens start a CE-parity token match game; bounty items are `CORE-E1c`.

## What landed

- `EscritoireGame` / `EscritoireGameBoard` / `EscritoireGameToken` / `EscritoireTextSource` under `com.leon1236.reforestry.core.escritoire`.
- Client widgets `GameTokenWidget` + `ProbeButton` on `ScreenEscritoire` (CE ring layout).
- Probe consumes up to `tokenCount/4` input samples (min 2, max 5); suitability = products/specialties/fruit + honey_drop 0.5 / honeydew 0.7 / honey comb 0.4.
- Token count = active+inactive complexity, evened, clamp 6–22; start bounty 16; mismatch → FAILURE; all matched → SUCCESS.
- Clicks: `clickMenuButton` / `handleInventoryButtonClick` — probe id `-1`, token indices `0–21`. Sync: `reforestry:escritoire_game_sync` when `lastUpdate` changes (not Forge `PacketGuiSelectRequest`).
- Butterflies not accepted in the analyze slot (Track D).

## Complexity

Shared helper: `com.leon1236.reforestry.core.genetics.GeneticsUtil.getResearchComplexity` (bee + tree). Arborist villager still goes through thin `TreeSpeciesComplexity` wrapper. See `queries/core-escritoire.md`.

## Smoke

1. Place escritoire, put a bee or sapling in the center slot → tokens appear.
2. Click two matching tokens → stay matched; mismatch → FAILURE.
3. Fill left sample slots with honey drop / comb / matching products; probe → some tokens peek; bounty paper icons drop.
4. Clear the board → SUCCESS; result slots fill via `CORE-E1c`.
