# ARB-11.8d — Feature shapes batch A (2026-07-25)

Ported CE temperate/vanilla tree Feature classes into `com.leon1236.reforestry.arboriculture.worldgen` and wired them in `DefaultTreeSpecies`.

## Species → Feature

| Species id | Feature |
|---|---|
| tree_oak, tree_birch, tree_dark_oak | FeatureTreeVanilla (already present) |
| tree_lime | FeatureSilverLime |
| tree_hill_cherry | FeatureSourCherry |
| tree_walnut | FeatureWalnut |
| tree_chestnut | FeatureChestnut |
| tree_pear | FeaturePear |
| tree_plum | FeaturePlum |
| tree_maple | FeatureMaple |
| tree_beech | FeatureBeech |
| tree_elm | FeatureElm |
| tree_poplar | FeaturePoplar |
| tree_willow | FeatureWillow |
| tree_cherry | FeatureCherryVanilla |
| tree_dogwood | FeatureDogwood |
| tree_feijoa | FeatureFeijoa |

## Notes

- Package remap only: `forestry.*` → `com.leon1236.reforestry.*`; helpers already local (`FeatureHelper`, `FeatureTree`).
- Batch B (tropical/giant/conifer) still points at `FeatureTreeVanilla`.
- Compile: `./gradlew compileJava` OK.
