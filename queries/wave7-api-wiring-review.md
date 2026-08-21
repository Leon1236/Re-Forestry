# Wave 7 — API / namespace / module wiring review

**Date:** 2026-08-21  
**Branch:** `cursor/wave-7-full-port-6593`

## Verdict

**PASS** after Must/Should fixes below.

## Checklist (independent)

| Check | Result |
|---|---|
| `ReForestry.java` loads ModuleGendustry / ExtraBees / ExtraTrees | OK |
| `fabric.mod.json` `reforestry:plugin` ×3 + JEI Gendustry + ET; no donor `depends` | OK |
| Module ids `reforestry:gendustry` / `extra_bees` / `extra_trees`; packages `gendustry` / `extra_bees` / `extratrees` | OK |
| Addon `shouldLoad` uses `isModuleEnabled` (genetics runs in `ModuleCore` before addon `init`) | OK |
| Plugin hooks: GD errors; EB genetics/apiculture/circuits; ET genetics/arboriculture/lep/client | OK |
| Namespace `reforestry:` only in shipped assets | OK |
| S2 filter registration via `ReforestryPlugin` + `isModuleEnabled(lepidopterology)` | OK |
| No Java comments in addon code | OK |
| Energy: `TilePowered(capacity, maxReceive)` / Team Reborn `SimpleEnergyStorage` | OK |
| No deprecated `hasChunkAt` / mutating `BoundingBox` in addons | OK |

## Fixed this pass

- **Must:** `ExtraTreesJeiPlugin` now gates on `isModuleEnabled(extra_trees)` (was ungated → crash if module disabled).
- **Should:** `AgricultureForestryPlugin.shouldLoad`, `DefaultFarms` arboriculture gate, Gendustry/Farming JEI gates use `isModuleEnabled`.
- **Nice:** `ModuleExtraTrees` uses imports instead of FQCN for fluids/recipes.
