# Wave 9 — implementation report

Plan: `/home/ivan/.cursor/plans/remaining_implementation_map_146cd7da.plan.md`  
Build: `./gradlew build` green after each stage (final run 2026-08-22).

## Stages completed

| Stage | ID | Summary |
|---|---|---|
| 0 | DATA-FIX | Analyzer/portable alyzer advancements, gameplay root tree |
| 1 | BOOK-0 | Native Forester's Almanac (`api/book`, client screens) |
| 2 | HYGIENE | Mail error/lang cleanup, orphan infuser/raintank delete |
| 3 | JEI-CORE + BOOK-CORE | Cross-module JEI descriptions, 17 core almanac entries |
| 4 | GEN-JEI + BOOK-GEN + LEPIDO | Tree/butterfly JEI, mutatron fix, lepidopterology book |
| 5 | GP-POLISH | Taxon alleles, suitable biomes, hive-drop alleles, client model maps |
| 6 | FACTORY-DATA | 17 comb block recipes + advancements, `bog_earth_wax_capsule` |
| 7 | FACTORY-VIS + BOOK-MACHINES | Owner ledger, smelter book page, rainmaker client FX |
| 8 | ADDON-POLISH + BOOK-ADDON | EB centrifuge fallbacks, ET GUI/liqueurs, Addons book category |
| 9 | CORE-UX + BOOK-FARM-ARBOR | Farm redstone vis, alyzer taxonomy, farm/arbor book verify |
| 10 | BOOK-BEE | 19 bee/alveary pages + `tools/validate_book.py` |
| 11 | TR1 | Optional Trinkets spectacles slot |
| 12 | WAVE8-RESTORE | **Skipped** (optional OR0/greenhouse/database) |
| 13 | DOC-SYNC | Status `.md` sweep + this report |

## Almanac

~73 entries across 11 categories (core, machines, engines, beekeeping, alveary, arboriculture, farming, genetics, lepidopterology, addons).  
Validation: `python3 tools/validate_book.py` — 12 remaining errors are CE resource-storage / compost recipe ids not ported (intentional gaps).

## Intentionally not in Wave 9

Mail, Wave 8 restore (stage 12), CE tin/bronze/apatite storage blocks, Patchouli runtime dependency.

## Per-stage notes

See `queries/wave9-stage*.md`, `queries/factory-F6-data.md`, `queries/genetics-GP0-polish.md`, `queries/wave9-stage11-tr1.md`.
