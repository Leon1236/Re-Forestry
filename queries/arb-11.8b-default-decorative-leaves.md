# ARB-11.8b notes — default / fruit-default / decorative leaves

**Date:** 2026-07-25

## What landed
- `ForestryLeafType` (50) + `BlockDefaultLeaves` / `BlockDefaultLeavesFruit` / `BlockDecorativeLeaves`
- Registered as `LEAVES_DEFAULT`, `LEAVES_DEFAULT_FRUIT`, `LEAVES_DECORATIVE` (ids `tree_*_default_leaves` etc.)
- Species bind leaf types in `ArboricultureGenetics.finalizeRegistration`; DefaultTreeSpecies sets decorative stacks + vanilla/default leaf states
- `IWoodType.setDefaultLeaves` on Forestry/Vanilla wood (worldgen-ready; sapling growth still places genetic leaves)
- Shear / silk-touch / pick-block on genetic + default leaves → decorative stack
- Assets via `tools/generate_leaf_variant_assets.py`; species wiring via `tools/sync_tree_species_leaves.py`

## Deviations
- No Forge `BlockExtendedLeaves.SUPPORTED` yet (add with 11.8c if large canopies need it)
- Decorative fruit overlay is tint-only foliage models (genetic fruit overlay unchanged)
- Cherry decorative stack is vanilla `CHERRY_LEAVES` (CE)
- MC 26.2: `setDecorativeLeaves` takes `Supplier<ItemStack>` — eager `new ItemStack` during `ModInitializer` throws `Components not bound yet`

## Re-sync
```bash
python3 tools/sync_tree_species_leaves.py --apply
python3 tools/generate_leaf_variant_assets.py --apply
```
