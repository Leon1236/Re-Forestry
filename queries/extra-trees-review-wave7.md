# Extra Trees Wave 7 independent review (2026-08-21)

## Verdict

**PASS** (after Must fix in this pass; prior DoD review had already closed sapling/leaf/glass_fitting gaps).

## Verified counts

| Area | Expected | Actual |
|---|---:|---:|
| Product woods | 30 | 30 |
| Log-only + shrub | 4 + 1 | 5 (`ExtraTreeWoodType` = 35) |
| Overlap woods skipped | 6 | 6 |
| ET1b kinds (30 woods) | full modern set | present (stripped/wood/trapdoor/button/plate/signs/boats) |
| New fruits | 54 | 54 (+5 CE reuse) |
| Species | 88 | 88 (97 − 9 skips) |
| Remaps | 3 | `tree_et_lime` / `tree_et_elm` / `tree_et_fir` |
| Acorn oak kept | 1 | `tree_acorn_oak` |
| Mutations | 97 | 97 (3× `MutationConditionMinHeight`) |
| Growth | 88/88 | 88 (9× `FeatureLazyTree`) |
| Machines | 4 | lumbermill, press, brewery, distillery |
| Foods | 59 | 59 (Papayimar skipped) |
| Fluids | 104 | 104 |
| Hops | 1 | yes |
| Moths | 22 `moth_*` | 22 / 0 mutations |
| Donor depends | none | none |

## Skips (documented)

- 9 binomial species; 6 overlap woods
- No designer / infuser / nursery
- Papayimar food (Binnie fruit allele commented out)
- Kitchen / cocktails / databases / ET-D

## This pass

### Must
- Deleted orphan `data/c/tags/item/crops/papayimar.json` (referenced unregistered `reforestry:papayimar` while food remains intentionally skipped)

### Should / hygiene
- Synced `tools/generate_leaf_variant_assets.py` `SPECIES_TO_GROUP` with the 88 ET leaf-resolver entries

### Already on branch (prior Wave 7 DoD review)
- 88 ET sapling textures/models + `items/sapling.json` / `SaplingBlockStateResolver`
- 88 ET entries in `LeafBlockStateResolver`
- `glass_fitting` item / texture / lang / tab / recipe

## Compile

`./gradlew compileJava --rerun-tasks` — BUILD SUCCESSFUL
