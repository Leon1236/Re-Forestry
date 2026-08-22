# OR0 — Climatology port

**Date:** 2026-08-22  
**Status:** done

ForestryMC 1.12 climatology restored under `com.leon1236.reforestry.climatology`. CE dropped this module.

## Done / in flight

1. Float climate API (`IClimateState`, transformer, `WorldClimateHolder` SavedData)
2. `ClimateProvider` / `ForestryClimateManager.getState` read world overlay → enum for bees
3. Habitat Former + Habitat Screen (see implementation)

## Deferred (not OR0 blockers)

- **ClimateListener** + listener packets + housing transform particles — 1.12 housing used a cached listener; bees work via overlay pull on `ClimateProvider` for OR0.
- Ambient climate particles (`ClimateHandlerServer/Client`, Former `randomDisplayTick`).
- OR1 greenhouse multiblock.

## Hygro float mapping

Datapack `humidity_steps` / `temperature_steps` → Former recipe floats: `humidΔ = steps * 0.01f`, `tempΔ = steps * 0.005f`. Temperature work cycle still uses 1.12 `(0.05 + |tempΔ|) * 0.5 / speed`.


## Phase 3-6 (done 2026-08-22)

Habitat Former + Habitat Screen under `com.leon1236.reforestry.climatology`.

### Intentional skips
- Ambient Former `randomDisplayTick` particles (deferred with ClimateHandler*).
- Block hardness forced to 2.0 by `BlockMachine` (1.12 was 1.0).

### Should-fix closed (2026-08-22)
- GUI text fields (0–200%) for temperature/humidity.
- `ForestryError.WRONG_RESOURCE` wired like 1.12 humidity branch.
- Habitat Screen item tint layers 1–2 (`habitat_screen_climate` / `habitat_screen_link`).
- Recipe advancements for `habitat_former` and `habitat_screen`.
- Former + Screen also listed on core `reforestry` creative tab (module tab kept).

### Preview draw
Habitat Screen range preview uses `LevelRenderEvents.BEFORE_GIZMOS` + `Gizmos.cuboid` (same path as spectacles highlight), not removed Fabric `WorldRenderEvents`.
