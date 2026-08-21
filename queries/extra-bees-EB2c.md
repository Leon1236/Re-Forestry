# EB2c — Extra Bees metals / minerals / gems / nuclear (+ precious)

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `ExtraBeeDefinition` / `ExtraBeeBranchDefinition`  
**Extracts:** `queries/extra-bees-species.json`, `queries/extra-bees-mutations.json`, `queries/extra-bees-id-map.json`  
**Generator:** `python3 tools/generate_extra_bees_species.py --batch EB2c --apply --lang`  
**Package:** `com.leon1236.reforestry.extra_bees.genetics.ExtraBeesBeeSpecies`

## Player exit

- **27** breedable Extra Bees species in this batch (83 cumulative with EB2a–b)
- **45** EB mutations whose parents already exist (101 cumulative; FR `modifySpecies` unchanged at 34 from EB2a)
- Ore-gated specialty combs ship as Extra Bees comb products (centrifuge soft-skips missing OreDict from EB6)
- Nuclear line uses `bee_effect_eb_radioactive` via `ExtraBeesBeeEffects.RADIOACTIVE`

## Species (27)

| Branch | Genus taxon | Species |
|---|---|---|
| METALLIC | `lamminapis` (new) | copper, tin, iron, lead |
| METALLIC2 | `metalapis` (new) | zinc, titanium, tungstate, nickel |
| PRECIOUS | `pluriapis` (new) | gold, silver, platinum |
| MINERAL | `niphapis` (new) | lapis, sodalite, pyrite, bauxite, cinnabar, sphalerite |
| GEMSTONE | `gemmapis` (new) | emerald, ruby, sapphire, diamond |
| NUCLEAR | `levapis` (new) | unstable, nuclear, radioactive, yellorium, cyanite, blutonium |

PRECIOUS is included so RADIOACTIVE (NUCLEAR × GOLD / SILVER) parents exist in the same batch. Without it, two nuclear mutations would block.

## Mutations

- **45** EB `addMutations` on EB2c results (no biome gates)
- Parents: Forestry CE, EB2a (`bee_mineral`, `bee_water`), EB2b (`bee_prehistoric`), or earlier EB2c species
- FR `modifySpecies` not re-emitted (still only EB2a WATER/ROCK/BASALT/MARBLE set)

## Wiring

- Generator is **cumulative**: `--batch EB2c` rewrites `ExtraBeesBeeSpecies` with EB2a ∪ EB2b ∪ EB2c
- Shared CE genera still skipped: `monapis` / `rustapis` / `paludapis` / `coagapis`
- Rock flower type + radioactive effect already registered in EB-FLOWERS+EB3
- Lang: Binnie names for all 27; many metal/gem/nuclear species have no `.desc` in Binnie (names only — not invented)

## Gaps / next

- **INK** still waits on PRIMARY dye parent (`bee_black`) — EB2d+
- Remaining dye / virulent / viscous / caustic / quantum / festive / etc. → EB2d–e (~33 species)
- Hive worldgen/loot still EB4
- Ore comb centrifuge outputs may soft-skip when OreDict items absent (EB6 behavior; intentional)

## Compile

`./gradlew compileJava` — clean after EB2c apply.

## Review (2026-08-21)

- Extract parity: **27** species / **45** EB mutations field-checked vs JSON + Binnie `ExtraBeeDefinition` / branch genomes (colors, binomials, dominant, products, specialties, chances, genomes, parents)
- PRECIOUS included so RADIOACTIVE parents (GOLD / SILVER) exist; NUCLEAR line uses `ExtraBeesBeeEffects.RADIOACTIVE` (`bee_effect_eb_radioactive`)
- Generator dry-run identical to committed `ExtraBeesBeeSpecies.java`; cumulative 83 species / 101 EB muts + 34 FR; `compileJava` clean
- Should fixed: extract `parse_allele` now applies `EFFECT_COLLISIONS`; species JSON RADIOACTIVE genome ids → `bee_effect_eb_radioactive`
- **Must:** none
- **Should:** none remaining

**Next stage:** `EB2d` (do not start in this review)
