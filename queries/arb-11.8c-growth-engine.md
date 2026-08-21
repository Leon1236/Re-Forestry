# ARB-11.8c notes — growth engine

**Date:** 2026-07-25

## What landed
- CE growth pipeline ported: `FeatureBase`, `FeatureHelper`, `FeatureArboriculture`, `FeatureTree`, `FeatureTreeVanilla`, block-type helpers, `TreeContour`, `TreeGrowthHelper`, `TreeGenHelper`
- `ITreeSpecies` now extends `ITreeGenData` (girth/height/growth-pos/setLeaves/setLog/fruit/default genome)
- `TileSapling.tryGrow` / `canAcceptBoneMeal` call the species feature (`FeatureBase.place`), not a hard-coded shape
- All 50 species default to `FeatureTreeVanilla` (CE base trunk + cylinder canopy) until per-species `Feature*` ports (11.8d/e)
- `DefaultTreeGenerator` places genetic leaves for sapling growth; default-genome + `WorldGenLevel` uses `IWoodType.setDefaultLeaves` (11.8b)
- Leaf `DISTANCE` updated after generation via CE `updateLeaves` + `StructureTemplate.updateShapeAtEdge`
- `SimpleTreeGenerator` kept only as fallback when a feature is not a `FeatureBase`

## Deviations / Fabric notes
- No `BlockExtendedLeaves.SUPPORTED` yet (same as 11.8b note)
- MC 26.2: `LeavesBlock.updateDistance` is private — distance is set by `FeatureArboriculture.updateLeaves` instead
- No Forge `IPlantable` / `canSustainPlant`; `BlockUtil.canPlaceTree` uses vanilla replaceable + leaf/log tags (CE parity for `TreeGenHelper`)
- In-world grow not exercised this session (RCON server was down); `./gradlew clean compileJava` OK

## Next
- ARB-11.8d — replace `FeatureTreeVanilla` per species with real `FeatureLarch` etc.
