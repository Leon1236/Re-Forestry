# EB2e — Extra Bees dyes / quantum / festive / FTB / Botania + INK

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `ExtraBeeDefinition` / `ExtraBeeBranchDefinition`  
**Extracts:** `queries/extra-bees-species.json`, `queries/extra-bees-mutations.json`, `queries/extra-bees-id-map.json`  
**Generator:** `python3 tools/generate_extra_bees_species.py --batch EB2e --apply --lang`  
**Package:** `com.leon1236.reforestry.extra_bees.genetics.ExtraBeesBeeSpecies`

## Player exit

- **24** new Extra Bees species (116 cumulative with EB2a–d)
- **24** new EB mutations (134 cumulative; FR `modifySpecies` unchanged at 34 from EB2a)
- Total mutations registered via Extra Bees plugin: **168** (134 EB + 34 FR)
- Soft Botania flower type already from EB3 (`ExtraBeesFlowerType.MYSTICAL`)

## Species (24)

| Branch | Genus taxon | Species |
|---|---|---|
| AQUATIC (deferred) | `aquapis` | ink |
| PRIMARY | `primapis` (new) | red, yellow, blue, green, black, white, brown |
| SECONDARY | `secapis` (new) | orange, cyan, purple, gray, lightblue, pink, limegreen |
| TERTIARY | `tertiapis` (new) | magenta, lightgray |
| FESTIVE | `festapis` (shared CE) | celebratory |
| FTB | `eftebeapis` (new) | jaded |
| AUSTERE | `modapis` (shared CE) | hazardous |
| QUANTUM | `quantapis` (new) | quantum, unusual, spatial |
| BOTANIA | `botaniapis` (new) | mystical |

## Mutations

- **24** EB `addMutations` on EB2e results
- Dye ladder: CE Valiant × biome bees → primary → secondary → tertiary
- INK = BLACK × OCEAN (parents now registered)
- JADED = ENDED × `bee_eb_relic`
- QUANTUM chain: UNUSUAL → SPATIAL → QUANTUM
- MYSTICAL = NOBLE × MONASTIC
- FR `modifySpecies` not re-emitted (still only EB2a WATER/ROCK/BASALT/MARBLE set)

## Product / genome mappings

| Extract | Re-Forestry |
|---|---|
| `Items.DYE` meta 0 (INK specialty) | `Items.INK_SAC` |
| `Mods.Forestry.stack("pollen")` | `POLLEN_CLUSTER.NORMAL` |
| `VanillaComb.QUARTZ` (removed in modern CE) | `Items.QUARTZ` (same chance) |
| MYSTICAL `copy_products_from` NOBLE | dripping comb 0.2f |
| `NEVER_SLEEPS` false (QUANTUM override) | `ActivityType.DIURNAL` |
| Territory alleles | `ForestryAlleles.TERRITORY_*` |
| FESTIVE / AUSTERE branches | shared `festapis` / `modapis` (not redefined) |

## Wiring

- Generator is **cumulative**: `--batch EB2e` rewrites `ExtraBeesBeeSpecies` with EB2a ∪ … ∪ EB2e
- Soft Botania: no new code — MYSTICAL flower type from EB3 degrades without Botania
- Lang: Binnie names for all 24 (+ `.desc` where present)

## Gaps / next

- Binnie `setIsNotCounted()` on JADED has no CE `IBeeSpeciesBuilder` equivalent (not `setSecret`; extract `secret=false`) — documented only
- Forestry `bee_comb_quartz` does not exist on CE 1.21.1 / our `EnumHoneyComb` — bees produce vanilla quartz instead
- Hive worldgen/loot still **EB4**

## Compile

`./gradlew compileJava` — clean after EB2e apply.

**Next stage:** `EB4` (do not start in this pass)
