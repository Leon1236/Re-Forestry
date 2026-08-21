# Wave 7 — Implementation report

**Branch:** `cursor/wave-7-full-port-6593`  
**PR:** https://github.com/Leon1236/Re-Forestry/pull/4  
**Date:** 2026-08-21  
**Plan:** `/opt/cursor/artifacts/plans/wave_7_full_port_fe0f721f.plan.md`  
**Scale:** ~60 commits; ~8582 files changed; ~153k insertions

## Verdict

All locked Wave 7 playable stages **GD0 → S2** are implemented on the branch. Each stage had its own implement agent and a full (not spot-check) review agent with Must/Should fixed before the next stage. Five independent end-of-wave reviews were launched after S2 (results appended below when they finish).

## Pipeline followed

1. Docs sync (`wave7-plan.md`, `remaining-work-stages.md`, `implemented-features.md`) — flowers+EB3 before EB2; GD7a/b.
2. Per-stage implement → full review → fix → next stage.
3. Five parallel final reviews: Gendustry, Extra Bees, Extra Trees, API/module wiring, assets/lang/recipes DoD.
4. This report.

## What shipped

### Gendustry (`reforestry:gendustry`) — GD0–GD8

| Stage | Outcome |
|---|---|
| GD0 | Module + tab; 10 parts + 23 upgrades + pollen kit; crafts; upgrade tag; pollen kit wired |
| GD1 | Fluids mutagen / liquid_dna / protein + buckets; elite upgrade crafts |
| GD2 | 4 mutagen + 7 protein + 10 DNA recipes; types/caches |
| GD3 | Mutagen producer, protein liquefier, DNA extractor (FE + labware chance) |
| GD4 | Sampler; gene sample/template DataComponents; wipe + combine; gene_samples tab |
| GD5 | Mutatron + advanced mutatron |
| GD6 | Imprinter, genetic transposer, replicator (dual tanks) |
| GD7a | Industrial apiary as `IBeeHousing` with `new BeekeepingLogic(this)` |
| GD7b | All upgrade modifiers; fertility drones; automation recycle; youth −20% mutation |
| GD8 | 12 errors + sprites; JEI mutagen/protein/DNA + gene-sample subtypes |

**Java files (approx):** 76 under `com.leon1236.reforestry.gendustry`

### Extra Bees (`reforestry:extra_bees`) — EB1–EB4

| Stage | Outcome |
|---|---|
| EB1 | 142 giveable: 74 combs, 24 drops, 4 propolis, 5 frames, 30 misc, ectoplasm, 4 hives |
| EB5 | 7 alveary parts + stimulator circuits + FE |
| EB6 | 74 centrifuge + 21 squeezer recipes (29 soft-skips documented) |
| EB-FLOWERS+EB3 | 11 flower types + 25 effects (`bee_effect_eb_radioactive`) **before** species |
| EB2a–e | **116** species + **168** mutations (134 EB + 34 FR `modifySpecies`) |
| EB4 | 4 wild hives + worldgen + scoop loot |

**Remaps:** `bee_eb_primeval` / `bee_eb_relic` / `bee_eb_boggy`; `beehive_eb_nether`; ARTIC spelling.

**Java files (approx):** 51 under `com.leon1236.reforestry.extra_bees`

### Extra Trees (`reforestry:extra_trees`) — ET1a–ET6 + S2

| Stage | Outcome |
|---|---|
| ET1a | 30 plank woods + shrub + 4 log-only bark; skip 6 CE overlaps |
| ET1b | Full modern WoodBlockKind (stripped/boats/signs/trapdoor/button/plate) |
| ET2 | 54 fruits + 88 species + 97 mutations; keep `tree_acorn_oak`; remaps `tree_et_*` |
| ET3 | 88/88 saplings grow (52 gens); Binnie rarity worldgen |
| ET4 | Lumbermill playable; press/brewery/distillery placeable |
| ET5 | 104 fluids; press/brewery/distillery recipes; hops; yeast/grains |
| ET6 | 22 moths (`moth_*`) on BUTTERFLY; 0 mutations |
| S2 | Genetic filter flutter/butterfly/serum/caterpillar/cocoon; moths in picker |

**Java files (approx):** 102 under `com.leon1236.reforestry.extratrees`

## Locked skips (honored)

- Gendustry: scoop / grafter / uranium / Patchouli / `debug_wand`
- Extra Bees: dictionary / honey crystal / industrial frames / inactive combs / empty ALLOY
- Extra Trees: 9 binomial duplicates / 6 overlap woods / designer (ET-D) / infuser / nursery / ET-K bottle rack
- Mail: out of scope

## Notable engineering choices

- Energy: local `TilePowered(capacity, maxReceive)` — donor arg order swapped
- Industrial apiary: `new BeekeepingLogic(this)` like `TileBeeHousing`
- Youth elite: mutation −20%/stack; honest tooltip (no lifespan lie)
- Gene samples: MC 26.2 DataComponents (not raw NBT)
- Extra Bees plugin `shouldLoad` uses `isModuleEnabled` so genetics registers before module init
- Forge recipe tags remapped to `c:` / local tags
- Bulk content generated via `tools/generate_extra_bees_*.py` and `tools/generate_extra_trees_ET*.py`

## Stage notes

Per-stage writeups live under:

- `queries/gendustry-GD0.md` … `GD8.md`
- `queries/extra-bees-EB1.md` … `EB6.md` (+ EB2a–e, EB3, EB4)
- `queries/extra-trees-ET1a.md` … `ET6.md`
- `queries/sorting-S2.md`

## Residual risks / deferred polish

| Item | Severity | Note |
|---|---|---|
| Marble hive never spawns vanilla-only | Medium | Needs `#c:stones/marble` from another mod/datapack |
| Press/brewery/distillery GUI art | Low | Stand-in `still.png` — Binnie clone lacked dedicated art |
| ET cocktails / glassware / double-high hops | Low | Documented ET5 skips |
| Mutatron JEI → tree/butterfly mutations | Low | Bee mutations only (no tree/butterfly JEI categories yet) |
| Default decorative ET leaf blocks | Low | Growth uses shared `TileLeaves` |
| In-world smoke of entire Wave 7 | Medium | Compile-verified; not every machine world-tested in this pass |

## Final five independent reviews

*(Filled when the five parallel review agents complete.)*

| Review | Focus | Verdict |
|---|---|---|
| 1 | Gendustry GD0–GD8 | **PASS** — see `queries/gendustry-wave7-review.md` |
| 2 | Extra Bees EB1–EB4 | **PASS** — see `queries/extra-bees-wave7-full-review.md` |
| 3 | Extra Trees ET1–ET6 | pending |
| 4 | API / namespace / module wiring | **PASS** — see `queries/wave7-api-wiring-review.md` |
| 5 | Assets / lang / recipes / DoD | **PASS** — see `queries/wave7-dod-asset-review.md` |

## How to verify locally

```bash
./gradlew compileJava
# modules.properties: reforestry:gendustry / extra_bees / extra_trees
# Creative tabs: gendustry, gene_samples, extra_bees, extra_trees
# /give reforestry:mutagen_producer, industrial_apiary, bee_water, tree_acorn_oak sapling, moth_white_admiral
```
