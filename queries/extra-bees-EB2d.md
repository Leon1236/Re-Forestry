# EB2d — Extra Bees viscous / caustic / virulent

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `ExtraBeeDefinition` / `ExtraBeeBranchDefinition`  
**Extracts:** `queries/extra-bees-species.json`, `queries/extra-bees-mutations.json`, `queries/extra-bees-id-map.json`  
**Generator:** `python3 tools/generate_extra_bees_species.py --batch EB2d --apply --lang`  
**Package:** `com.leon1236.reforestry.extra_bees.genetics.ExtraBeesBeeSpecies`

## Player exit

- **9** breedable Extra Bees species in this batch (92 cumulative with EB2a–c)
- **9** EB mutations whose parents already exist (110 cumulative; FR `modifySpecies` unchanged at 34 from EB2a)
- Jungle flower type + miasmic / ectoplasm / acid effects (CE + EB3)

## Species (9)

| Branch | Genus taxon | Species |
|---|---|---|
| VIRULENT | `virapis` (new) | malicious, infectious, virulent |
| VISCOUS | `viscapis` (new) | viscous, glutinous, sticky |
| CAUSTIC | `morbapis` (new) | corrosive, caustic, acidic |

## Mutations

- **9** EB `addMutations` on EB2d results (no biome gates)
- Parents: Forestry CE (`bee_sinister`, `bee_tropical`, `bee_exotic`, `bee_fiendish`), EB2a (`bee_water`), or earlier EB2d species
- Cross-branch: CORROSIVE = MALICIOUS × VISCOUS
- FR `modifySpecies` not re-emitted (still only EB2a WATER/ROCK/BASALT/MARBLE set)

## Wiring

- Generator is **cumulative**: `--batch EB2d` rewrites `ExtraBeesBeeSpecies` with EB2a ∪ EB2b ∪ EB2c ∪ EB2d
- Shared CE genera still skipped: `monapis` / `rustapis` / `paludapis` / `coagapis`
- Effects: VIRULENT uses `ForestryAlleles.EFFECT_MIASMIC`; VISCOUS uses `ExtraBeesBeeEffects.ECTOPLASM`; CAUSTIC uses `ExtraBeesBeeEffects.ACID`
- Specialties: `VENOMOUS` / `SLIME` / `ACIDIC` combs (already registered in EB1)
- Lang: Binnie names for all 9; no `.desc` in Binnie (names only — not invented)

## Gaps / next

- **INK** still waits on PRIMARY dye parent (`bee_black`) — EB2e
- Remaining dye / quantum / festive / austere / FTB / Botania → EB2e (~24 species)
- Hive worldgen/loot still EB4

## Compile

`./gradlew compileJava` — clean after EB2d apply.

**Next stage:** `EB2e` (do not start in this pass)
