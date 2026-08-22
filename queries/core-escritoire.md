# Escritoire complexity (CORE-E1b)

## Choice

CE `ISpecies.getComplexity()` lazily returns authored non-zero values, else `GeneticsUtil.getResearchComplexity` = `1 + mutation-ancestor depth` (`getMutationsInto` walk).

Re-Forestry still has no public `ISpecies` complexity API (`GP0a1`). For escritoire + arborist trades we use a shared helper:

`com.leon1236.reforestry.core.genetics.GeneticsUtil`

- Bee: `getResearchComplexity(IBeeSpecies)` — mutation depth on `ApicultureGenetics` results.
- Tree: `getResearchComplexity(ITreeSpecies)` — same on `ArboricultureGenetics`, with **giant sequoia** (`reforestry:tree_giant_sequoia`) forced to **10** (CE `DefaultTreeSpecies.setComplexity(10)`).

`TreeSpeciesComplexity` (arborist villager) now delegates to this helper.

## Token board

`tokenCount = complexity(active) + complexity(inactive)`, rounded even, clamp **6–22** — matches CE `EscritoireGameBoard.getTokenCount`.
