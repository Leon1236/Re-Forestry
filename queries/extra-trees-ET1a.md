# ET1a — Extra Trees woods (ModuleWood kinds)

**Date:** 2026-08-21  
**Status:** Implemented  
**Donor:** `MarkDown_Maker/github_clone/ACGaming-Binnie` (extract, do not depend)  
**Extract:** `queries/extra-trees-extract/woods.json`

## Player exit

Creative tab **Extra Trees** lists 30 plank woods + shrub log (+ fireproof variants and derived blocks). Crafting: log → planks → slab / stairs / fence / fence gate / door, with matching fireproof plank family and fabricator fireproof log/plank recipes (mirror arboriculture).

## Counts

| Kind | Count | Notes |
|---|---|---|
| Product woods (`ExtraTreeWoodType.WITH_PRODUCTS`) | **30** | Skip Fir/Beech/Elm/Pear/Olive/Gingko → already `ForestryWoodType` |
| Log-only bark variants | **4** | `eucalyptus2`, `eucalyptus3`, `et_cherry`, `cinnamon` — LOG + fireproof LOG only |
| Shrub | **1** | `shrub_log` / `shrub_fireproof_log` (cube column; Binnie oak textures) |
| Total `ExtraTreeWoodType` values | **35** | Never edit `ForestryWoodType` |

## Module / plugin

- `@ForestryModule` id `reforestry:extra_trees` — `ModuleExtraTrees`
- `ExtraTreesForestryPlugin` stub on `fabric.mod.json` `reforestry:plugin`
- Loaded from `ReForestry.onInitialize()` after Extra Bees
- Package `com.leon1236.reforestry.extratrees.*`

## Registration (Binnie ModuleWood ET1a kinds only)

`ExtraTreesBlocks` mirrors arboriculture `FeatureBlockGroup` + `WoodAccess.register`:

- LOG / PLANKS / SLAB / STAIRS / FENCE / FENCE_GATE (+ fireproof)
- DOOR (normal only; Binnie had no fireproof doors)
- **Not** in ET1a (→ ET1b): stripped, wood, boats, signs, trapdoor, button, pressure plate

`BlockForestryDoor` now takes `IWoodType` so Extra Trees can reuse it.

## Remaps / gaps

| Item | Choice |
|---|---|
| Binnie Cherry bark log | Registry id `et_cherry_*` — avoids clash with vanilla `cherry_fireproof_*` |
| Extra Trees Ash wood | Registry id `et_ash_*` — avoids clash with charcoal decorative `ash_stairs` / `ash_brick` |
| Cherry / cinnamon log→planks | `hill_cherry_planks` / `minecraft:jungle_planks` (+ fireproof jungle) per Binnie plank targets |
| Eucalyptus2/3 → planks | Craft into `eucalyptus_planks` |
| Shrub block | Simple pillar log (not Binnie thin connecting shrub); connecting shrub deferred |
| Door art | Shared Binnie `door.standard` copied per wood |
| Assets script | `python3 tools/generate_extra_trees_ET1a_assets.py` |

## Next

**ET1b** — stripped / boats / signs / trapdoor / button / plate (full modern `WoodBlockKind`; no charcoal walls).
