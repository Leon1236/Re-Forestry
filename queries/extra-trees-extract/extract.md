# Extra Trees extract (ET0)

Research extract from Binnie `extratrees/` (design module counted only to bound ET-D). Not playable content. Re-run:

```
python3 tools/extract_extra_trees.py --clone MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie --out queries/extra-trees-extract --apply
```

Layout: all JSON/md for this stage live under `queries/extra-trees-extract/` (not flat `queries/extra-trees-*.json`).

## Counts

| Item | Extracted | Expected | Source |
|---|---:|---:|---|
| Tree species | 97 | 97 | `ETTreeDefinition` |
| Binomial skip/reuse | 9 | 9 | same genus+epithet as `DefaultTreeSpecies` |
| Fruits | 59 | 59 | `AlleleETFruitDefinition` |
| Plank woods | 36 | 36 | `ExtraTreePlanks` |
| CE overlap woods | 6 | 6 | Fir/Beech/Elm/Pear/Olive/Gingko |
| New plank woods | 30 | 30 | skip 6 overlap |
| Shrub log | 1 | 1 | `EnumShrubLog` |
| Mutations | 97 | 97 | `ExtraTreeMutation.init` |
| Moths | 22 | 22 | `ButterflySpecies` |
| Moth mutations | 0 | 0 | empty `registerMutations` |
| Foods | 60 | — | `Food` |
| Juices | 19 | — | `Juice` |
| Alcohols | 29 | — | `Alcohol` |
| Liqueurs | 17 | — | `Liqueur` |
| Spirits | 27 | — | `Spirit` |
| Misc fluids | 8 | — | `MiscFluid` |
| Tree liquids | 4 | — | `ExtraTreeLiquid` |

All locked counts match `queries/wave7-plan.md`.

## AcornOak

Binnie `AcornOak` is `quercus` / `robur` — the same binomial as CE `tree_oak`. Wave 7 keeps it as `reforestry:tree_acorn_oak` so Extra Trees still has a distinct breeding species. It is **not** one of the 9 skip/reuse rows.

## Source quirks (not padded)

- `AlleleETFruitDefinition.Plantain` ctor name is `platain` (Binnie typo); Re-Forestry id uses the enum: `fruit_plantain`.
- `getVanilla("Mahogony")` is Binnie’s spelling; remaps to `reforestry:tree_mahogany`.
- `Gingko` / `cocous` / `acer ubrum` (`RedMaple`) keep Binnie spelling in extract; CE overlap uses `GINKGO` / `cocos` / `tree_ginkgo`.
- `RoseGum` and `SwampGum` share `eucalyptus grandis` inside Extra Trees; both stay as separate species.
- Commented fruit `Papayimar` is not in the 59; `Food.PAPAYIMAR` still exists.

## Files

- `queries/extra-trees-extract/woods.json`
- `queries/extra-trees-extract/fruits.json`
- `queries/extra-trees-extract/species.json`
- `queries/extra-trees-extract/mutations.json`
- `queries/extra-trees-extract/moths.json`
- `queries/extra-trees-extract/foods.json`
- `queries/extra-trees-extract/fluids.json`
- `queries/extra-trees-extract/machines.md`
- `queries/extra-trees-extract/overlap.md`
