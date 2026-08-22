# Extra Trees overlap with Re-Forestry arboriculture

Do not re-register these. ET1 woods use `ExtraTreeWoodType` for the 30 new planks + shrub log only.

## Woods (6)

| ExtraTreePlanks | ForestryWoodType | Note |
|---|---|---|
| Fir | FIR | same wood family |
| Beech | BEECH | same wood family |
| Elm | ELM | same wood family |
| Pear | PEAR | same wood family |
| Olive | OLIVE | same wood family |
| Gingko | GINKGO | Binnie spelling Gingko → CE GINKGO |

`EnumETLog` also has log-only variants (no plank products): Eucalyptus2, Eucalyptus3, Cherry, Cinnamon.

## Species binomial skip/reuse (9)

| ET enum | ET binomial | Reuse | Match |
|---|---|---|---|
| SourCherry | `prunus cerasus` | `reforestry:tree_hill_cherry` | exact |
| Orange | `citrus sinensis` | `reforestry:tree_orange` | exact |
| Beech | `fagus sylvatica` | `reforestry:tree_beech` | exact |
| Olive | `olea europaea` | `reforestry:tree_olive` | exact |
| BalsamFir | `abies balsamea` | `reforestry:tree_fir` | exact |
| Pear | `pyrus communis` | `reforestry:tree_pear` | exact |
| Gingko | `ginkgo biloba` | `reforestry:tree_ginkgo` | spelling |
| MonkeyPuzzle | `araucaria araucana` | `reforestry:tree_pewen` | exact |
| Coconut | `cocous nucifera` | `reforestry:tree_coconut` | spelling |

## AcornOak (keep)

`AcornOak` is `quercus robur` like `tree_oak`, but Wave 7 **keeps** `reforestry:tree_acorn_oak`.

## Id collisions (not binomial skips)

Snake of the Binnie enum would collide with an existing `tree_*` that has a **different** binomial. Remap like Extra Bees `bee_eb_*`.

| ET enum | ET binomial | Would be | Remap |
|---|---|---|---|
| Lime | `citrus latifolia` | `reforestry:tree_lime` | `reforestry:tree_et_lime` |
| Elm | `ulmus procera` | `reforestry:tree_elm` | `reforestry:tree_et_elm` |
| Fir | `abies alba` | `reforestry:tree_fir` | `reforestry:tree_et_fir` |

Lime is Persian lime (`citrus latifolia`), not CE silver lime (`tilia tomentosa` / `tree_lime`). Fir is silver fir (`abies alba`); CE `tree_fir` is balsam fir (reused by `BalsamFir`). Elm is English elm (`ulmus procera`); CE `tree_elm` is wych elm (`ulmus glabra`).

## DefaultFruits reuse

| ET fruit | Reuse |
|---|---|
| Olive | `reforestry:fruit_olive` |
| Orange | `reforestry:fruit_orange` |
| Apple | `reforestry:fruit_apple` |
| Pear | `reforestry:fruit_pear` |
| Coconut | `reforestry:fruit_coconut` |

Lemon stays CE-only (`fruit_lemon`). Extra Trees has KeyLime/Lime/Citron, not a Lemon fruit allele.

## Skip list (Wave 7 / extract policy)

| Skip | Why | Source |
|---|---|---|
| 6 overlap woods | Already `ForestryWoodType` FIR/BEECH/ELM/PEAR/OLIVE/GINKGO | `ExtraTreePlanks` |
| 9 duplicate species | Same binomial as `DefaultTreeSpecies` (Gingko/Coconut spelling-normalized) | `ETTreeDefinition` |
| Infuser | Code under `machines/infuser/` but **not** in `ExtraTreeMachine`; never shipped | not a stage |
| Nursery | Enum supplier returns null (TODO) | never shipped, not a stage |
| Designer / stained glass / multi-fence | ET-D deferred | `Woodworker`/`Panelworker`/`Glassworker` + `binnie.design` |
| Kitchen bottle rack | `ModuleKitchen` TODO (`blockKitchen = Blocks.AIR`) | optional ET-K after ET5 |
| Tree/moth databases | Wave 7 skip | `ModuleTreeDatabase` / `ModuleMothDatabase` |
| Commented fruit Papayimar | Not in `AlleleETFruitDefinition.values()` | comment `// , Papayimar(...)` |
| Mail | Out of scope | — |

## `getVanilla` → local tree ids

Binnie `forestry.tree` + CamelCase (`forestry.treeCherry`) remaps to CE 1.21.1 / Re-Forestry `tree_*`:

| getVanilla | Binnie uid | Re-Forestry |
|---|---|---|
| Oak | `forestry.treeOak` | `reforestry:tree_oak` |
| Birch | `forestry.treeBirch` | `reforestry:tree_birch` |
| Cherry | `forestry.treeCherry` | `reforestry:tree_hill_cherry` — 1.12 Cherry is today's hill cherry, not vanilla `tree_cherry` |
| Lemon | `forestry.treeLemon` | `reforestry:tree_lemon` |
| Maple | `forestry.treeMaple` | `reforestry:tree_maple` |
| Plum | `forestry.treePlum` | `reforestry:tree_plum` |
| Chestnut | `forestry.treeChestnut` | `reforestry:tree_chestnut` |
| Walnut | `forestry.treeWalnut` | `reforestry:tree_walnut` |
| Lime | `forestry.treeLime` | `reforestry:tree_lime` |
| Willow | `forestry.treeWillow` | `reforestry:tree_willow` |
| Ebony | `forestry.treeEbony` | `reforestry:tree_ebony` |
| Balsa | `forestry.treeBalsa` | `reforestry:tree_balsa` |
| Jungle | `forestry.treeJungle` | `reforestry:tree_jungle` |
| Kapok | `forestry.treeKapok` | `reforestry:tree_kapok` |
| Teak | `forestry.treeTeak` | `reforestry:tree_teak` |
| Spruce | `forestry.treeSpruce` | `reforestry:tree_spruce` |
| Pine | `forestry.treePine` | `reforestry:tree_pine` |
| Larch | `forestry.treeLarch` | `reforestry:tree_larch` |
| Mahogony | `forestry.treeMahogony` | `reforestry:tree_mahogany` (Binnie typo Mahogony) |
| Wenge | `forestry.treeWenge` | `reforestry:tree_wenge` |
