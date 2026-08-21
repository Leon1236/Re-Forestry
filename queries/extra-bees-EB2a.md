# EB2a — Extra Bees hive-line + barren/rocky/hostile/volcanic/shadow/aquatic/classical

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `ExtraBeeDefinition` / `ExtraBeeBranchDefinition`  
**Extracts:** `queries/extra-bees-species.json`, `queries/extra-bees-mutations.json`, `queries/extra-bees-id-map.json`  
**Generator:** `python3 tools/generate_extra_bees_species.py --batch EB2a --apply`  
**Package:** `com.leon1236.reforestry.extra_bees.genetics.ExtraBeesBeeSpecies`

## Player exit

- **25** breedable Extra Bees species registered (hive parents WATER/ROCK/BASALT/MARBLE + branch lines)
- **22** EB mutations (result is an EB2a species; parents already registered)
- **34** `modifySpecies` mutations that result in CE bees (COMMON/CULTIVATED/SINISTER/FIENDISH with WATER/ROCK/BASALT/MARBLE parents)
- Genomes use EB flower types + effects from EB-FLOWERS+EB3
- Products/specialties from extract (Extra Bees + Forestry combs, royal jelly)

## Species (25)

| Branch | Genus taxon | Species |
|---|---|---|
| BARREN | `vacapis` | arid, barren, desolate, decomposing, gnawing |
| HOSTILE | `infenapis` | rotten, bone, creeper |
| ROCKY | `monapis` (shared CE monastic taxon) | rock, stone, granite, mineral |
| AQUATIC | `aquapis` | water, river, ocean |
| CLASSICAL | `grecapis` | marble, roman, greek, classical |
| VOLCANIC | `irrapis` | basalt, tempered, volcanic |
| SHADOW | `pullapis` | shadow, darkened, abyss |

Hive-found: `bee_water`, `bee_rock`, `bee_basalt`, `bee_marble`.

## Deferred to later EB2*

| Species | Why |
|---|---|
| `bee_ink` (AQUATIC) | Mutation needs `bee_black` (PRIMARY / dye batch) |
| `bee_glowstone` (VOLCANIC) | Mutation needs `bee_excited` (ENERGETIC batch) |

## Mutations

- EB: 22 `addMutations` on EB2a results (biome gates: RIVER/OCEAN/NETHER → `BiomeTags`)
- FR: 34 via `modifySpecies` on `bee_common` / `bee_cultivated` / `bee_sinister` / `bee_fiendish`

## Wiring

- `ExtraBeesForestryPlugin.registerGenetics` → `ExtraBeesBeeSpecies.registerTaxa` (6 new genera under `apidae`; skip redefining `monapis`)
- `ExtraBeesForestryPlugin.registerApiculture` → effects first, then `ExtraBeesBeeSpecies.register`
- Id remaps from extract (`bee_eb_*` not in this batch); ARTIC spelling preserved for later batches
- Lang: `allele.reforestry.bee_species.{id}` (+ `.desc` where Binnie had one)

## Genome mapping (Binnie → CE 26.2)

| Binnie | Re-Forestry |
|---|---|
| `NEVER_SLEEPS` true | `ActivityType.METATURNAL` |
| species `setNocturnal` without never-sleeps | `ActivityType.NOCTURNAL` |
| `FLOWERING` | `POLLINATION` |
| `FLOWER_PROVIDER` | `FLOWER_TYPE` |
| `Fertility.LOW/NORMAL` | `FERTILITY_1` / `FERTILITY_2` |
| Extra Bees flower/effect UIDs | `ExtraBeesFlowerType` / `ExtraBeesBeeEffects` alleles |

## Gaps / next

- INK + GLOWSTONE (+ their mutations) wait for dye/energetic parents
- Remaining ~91 species + ~112 EB mutations in EB2b–e
- Hive worldgen/loot still EB4
- Rocky bees share `monapis` taxon with CE monastic (Binnie scientific name collision; documented)

**Next stage:** `EB2b`
