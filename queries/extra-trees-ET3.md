# ET3 — Extra Trees growth + worldgen

**Date:** 2026-08-21  
**Status:** Implemented  
**Donor:** Binnie Extra Trees `WorldGen*` (adopt into `extratrees.worldgen`; no fabric depends)  
**Generator:** `python3 tools/generate_extra_trees_ET3.py --apply`

## Player exit

- Every Extra Trees sapling (all **88** species) grows with a Binnie-shaped crown (or CE `FeaturePlum` / `FeatureLemon` where Binnie reused Forestry gens)
- Banana / red banana / plantain still hang pods via `FeatureTree.generateExtras` + `ExtraTreesPodFruit`
- Leaf fruits keep ripening on `TileLeaves` (ET generator always places leaf BEs)
- Natural worldgen for the **8** species Binnie gave rarity (`WildCherry` + 7 berry shrubs)

## Growth coverage

| Kind | Count | Notes |
|---|---|---|
| Species with growth | **88 / 88** | Every `setGenerator` uses a typed `Feature*` factory |
| Unique generators | **52** | From extract `worldgen` field |
| New `Feature*` classes | **50** | Under `com.leon1236.reforestry.extratrees.worldgen` |
| CE reuse | **2** | `FeaturePlum`, `FeatureLemon` |
| Shared generators | yes | e.g. 11 citrus → Lemon; 10 shrubs → `FeatureShrub`; 9 spices → `FeatureLazyTree`; 3 bananas → `FeatureBanana` |

Base: `FeatureBinnieTree` ports Binnie cylinder/sphere + bushiness. `FeatureShrub` mirrors Binnie `WorldGenShrub.Shrub` (SOFT replace, short trunk).

## Worldgen coverage

| Species | Rarity | Generator |
|---|---|---|
| `tree_wild_cherry` | 0.0015 | `FeaturePlum` |
| `tree_blackcurrant` | 0.0025 | `FeatureShrub` |
| `tree_redcurrant` | 0.0025 | `FeatureShrub` |
| `tree_raspberry` | 0.0025 | `FeatureShrub` |
| `tree_blueberry` | 0.0025 | `FeatureShrub` |
| `tree_cranberry` | 0.0025 | `FeatureShrub` |
| `tree_juniper` | 0.0025 | `FeatureShrub` |
| `tree_golden_raspberry` | 0.0025 | `FeatureShrub` |

Uses existing `TreeDecorator` (rarity × climate match). Default temperature/humidity `NORMAL` matches Binnie (no climate overrides on those enums).

## Gaps (not ET3)

| Gap | Why |
|---|---|
| Per-species default / decorative leaf blocks | `ExtraTreeWoodType.setDefaultLeaves` still false; growth uses `TileLeaves` (works for fruit alleles) |
| Blackberry / gooseberry / dwarf hazel natural spawn | Binnie gave them `WorldGenShrub` but **no** `setRarity` |
| Unused Binnie gens (CommonBeech, BalsamFir, …) | Those species are CE duplicates skipped in ET2 |
| Juices / alcohol / lumbermill | **ET4** / **ET5** |

## Next

**ET4** — lumbermill, press, brewery, distillery. Do not start until ET3 is accepted.
