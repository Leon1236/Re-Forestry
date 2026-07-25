# ARB-11.8a notes — species worldgen metadata

**Date:** 2026-07-25

## What landed
- Builder/species mirror CE: `setRarity`, `setTemperature`, `setHumidity`, `setTreeFeature` / `setGenerator`, `addVanillaStates`, `addVanillaSapling`, `setDecorativeLeaves`.
- Climate enums reused from bees (`TemperatureType` / `HumidityType`).
- All 50 species call `setTreeFeature(FeatureSimpleTree::new)` until real `Feature*` ports (11.8c/d).
- Rarity/climate values extracted from CE via `tools/sync_tree_species_worldgen.py`.

## Deviations
- Decorative / default-fruit leaf stacks and most vanilla leaf state lists not wired in `DefaultTreeSpecies` yet — those blocks come in 11.8b.
- `FeatureSimpleTree.place` returns false (stub). Sapling growth still uses `SimpleTreeGenerator` directly.
- MC 26.2: `LeavesBlock.updateDistance` is private; `DefaultTreeGenerator.setLeaves` places genetic leaves without that helper.

## Re-sync
```bash
python3 tools/sync_tree_species_worldgen.py --apply
```
