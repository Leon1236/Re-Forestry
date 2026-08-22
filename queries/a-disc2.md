# A-DISC2 — Mutation discovery icons

CE `GuiNaturalistInventory.displaySpeciesInformation` on Fabric 26.2 naturalist chest + bag screens.

## Shared helper

`NaturalistSpeciesHover` is used by `ScreenNaturalistChest` and `ScreenNaturalistInventory`. Idle counts stay in `NaturalistBreedingStatistics`. No journal book — CE this screen has none.

## Haploid vs hybrid

CE this GUI does not call `isHaploid`. It uses `speciesPair.isSameAlleles()` and shows the inactive row only when alleles differ.

Re-Forestry also treats life stages `drone` and `pollen` as haploid (same as `BeeAnalyzerPlugin` drones; pollen is the tree haploid germling). Those skip the inactive row even if stored alleles differ. Saplings / queens / princesses / larvae stay diploid.

`maxMutationCount` follows CE, not the swapped prompt numbers: **25** when pure/haploid, **10** per species when hybrid (two name rows). `CycleTimer` pages when a species has more than that many non-secret mutations. Hold Shift to pause, matching CE.

## Unknown-icon UVs (`apiaristinventory.png` 256×256)

From CE `drawUnknownIcon` (`mutation.getChance()` as a percent). Our `IMutation.getChance()` is a 0–1 fraction (`chancePercent / 100f`); `EnumMutateChance.rateChance` multiplies by 100 so the same tiers apply.

| `rateChance` | CE percent | u | v | 16×16 |
|---|---|---|---|---|
| HIGHEST | ≥ 20 | 228 | 16 | yes |
| HIGHER | ≥ 15 | 212 | 16 | yes |
| HIGH | ≥ 12 | 196 | 16 | yes |
| NORMAL | ≥ 10 | 228 | 0 | yes |
| LOW | ≥ 5 | 212 | 0 | yes |
| LOWEST (default) | < 5 | 196 | 0 | yes |

26.2 blit: `graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, 16, 16, 256, 256)` from `extractLabels` (GUI-relative, like the alyzer). Discovered partners use `fakeItem` + `itemDecorations` (drone stacks for bees, sapling stacks for trees).
