# ET2 — Extra Trees fruits + species + mutations

**Date:** 2026-08-21  
**Status:** Implemented  
**Donor:** Binnie Extra Trees (extract only; no fabric depends)  
**Extract:** `queries/extra-trees-extract/{fruits,species,mutations,foods}.json`  
**Generator:** `python3 tools/generate_extra_trees_ET2.py --apply --assets --lang`

## Player exit

- `/give` saplings for **88** Extra Trees species (creative arboriculture tab lists all tree genomes, including ET)
- **97** mutations breed (9 results remapped onto CE species via `modifySpecies`)
- Fruits drop: ripening leaf fruits on `TileLeaves`; banana / red banana / plantain as hanging pods

## Counts

| Kind | Count | Notes |
|---|---|---|
| New fruit alleles | **54** | `ExtraTreesFruits` via `registerFruit` |
| CE fruit reuse | **5** | Olive / Orange / Apple / Pear / Coconut → `DefaultFruits` |
| Foods (drop items) | **59** | Skip Papayimar; needed so fruit products exist (full juice/alcohol still ET5) |
| New species | **88** | Skip 9 binomial duplicates; keep `tree_acorn_oak`; remaps `tree_et_lime` / `tree_et_elm` / `tree_et_fir` |
| Mutations | **97** | Skip parents remapped to CE ids; `getVanilla` map from extract; 3 min-Y height conditions |
| Pod blocks | **3** | Banana / Red banana / Plantain |

## Registration

- `ExtraTreesForestryPlugin.registerArboriculture` → fruits then species
- `IArboricultureRegistration.modifySpecies` added (mirror Extra Bees) for the 9 CE-result mutations
- Overlap woods Beech / Fir / Elm → `ForestryWoodType` (not `ExtraTreeWoodType`)
- Growth: `ExtraTreesTreeGenerator` always places `TileLeaves` (ET3 adds Binnie `FeatureTree` + default leaf blocks)

## Assets / lang

- Food item textures from Binnie `textures/items/food`
- Fruit overlay sprites `block/fruit/{tiny,small,average,large,larger,pear}` + `LeafFruitOverlay` list
- Pod textures `block/pods/{banana,red_banana,plantain}.{0,1,2}`

## Gaps (not ET2)

| Gap | Why |
|---|---|
| Default / decorative leaf blocks per species | ET3 / later — trees use shared `TileLeaves` for now |
| Binnie worldgen features | **ET3** |
| Juices / alcohol / press recipes | **ET5** |
| Papayimar fruit | Commented out of Binnie allele enum |

## Next

**ET3** — growth features / worldgen (`FeatureTree` ports). Do not start until ET2 is accepted.
