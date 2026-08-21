# Wave 7 — Addons (locked plan)

**Date:** 2026-08-21 (revised after checking cloud)  
**Repo state:** this workspace has Waves **1b–4** (escritoire, energy, sorting, farming) that were never pushed. GitHub `main` has **Wave 5** (GP0a1–d) merged as [PR #1](https://github.com/Leon1236/Re-Forestry/pull/1). **Wave 6** lepidopterology (D0–D4) is a complete draft [PR #2](https://github.com/Leon1236/Re-Forestry/pull/2) on `cursor/wave-6-lepidopterology-9408`. Histories **diverged** at `b7bf6cbc`. Mail out of scope.

Re-plan vs `queries/remaining-work-stages.md` Wave 7, verified against:

- Gendustry: `MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`
- Binnie: `MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Textures: `for textures only/thedarkcolour-gendustry/` and `for textures only/ACGaming-Binnie/`

Graphify `index.json` Documents clone paths are stale — use the workspace `MarkDown_Maker/Finished_github_clone/2026-07-24/` copies.

## Locked decisions

| Item | Decision |
|---|---|
| Packages | `com.leon1236.reforestry.gendustry` / `extra_bees` / `extratrees` |
| Module ids | `reforestry:gendustry` / `extra_bees` / `extra_trees` |
| Namespace | always `reforestry:` |
| Plugins | `GendustryForestryPlugin`, `ExtraBeesForestryPlugin`, `ExtraTreesForestryPlugin` |
| Woods | `ExtraTreeWoodType` + `FeatureBlockGroup` + `WoodAccess`. Do not edit `ForestryWoodType` |
| Infuser / nursery | **never** — unfinished in Binnie |
| Designer | **ET-D** deferred |
| Kitchen bottle rack | **ET-K** optional after ET5 |
| EB id collisions | `bee_eb_primeval`, `bee_eb_relic`, `bee_eb_boggy`; effect `bee_effect_eb_radioactive`; hive `beehive_eb_nether` |
| ARTIC | keep Binnie spelling |
| Youth elite upgrade | port code (mutation −20%/stack), not the lifespan tooltip |
| Gendustry energy ctor | local `TilePowered(capacity, maxReceive)` — swap donor argument order |
| Industrial apiary | `new BeekeepingLogic(this)` like `TileBeeHousing`, not donor `IIndividualHandlerItem` |

**Skip:** Gendustry scoop/grafter/uranium/Patchouli/`debug_wand`; Extra Bees dictionary / honey crystal / industrial frames / Binnie genetics / inactive combs / ALLOY branch; Extra Trees 9 duplicate species + 6 overlap woods + databases; mail.

## Counts (verified)

| Content | Count | Note |
|---|---|---|
| Extra Bees species | 116 | 32 branches; ALLOY empty |
| Extra Bees mutations | 168 | 34 result in CE bees (`modifySpecies` — GP0c) |
| Extra Bees effects | 25 | |
| Extra Bees flower types | 11 | GP0b before EB2 |
| Extra Trees species | 97 | skip 9 CE duplicates → ~88 new |
| Extra Trees fruits | 59 | ~54 new |
| Extra Trees plank woods | 36 | skip 6 CE overlap → **30 new** + shrub log |
| Extra Trees moths | 22 | no mutations; Wave 6 |
| Gendustry machines | 10 | |
| Gendustry upgrades | 23 | 17 + 6 elite |

## Cloud Wave 5 / 6 (checked 2026-08-21)

| Wave | Where | Status | What addons get |
|---|---|---|---|
| 5 GP0a1–d | `origin/main` (PR #1 **merged**) | Not in this worktree | `ISpecies` / `IIndividual` / `IGeneticManager` / `IIndividualHandlerItem`; `registerGenetics` / `registerFlowerType` / `modifySpecies` / `registerFruit` / `registerErrors` / empty `BUTTERFLY` type |
| 6 D0–D4 | `cursor/wave-6-lepidopterology-9408` (PR #2 **draft**) | Not in this worktree | Module + items `butterfly` / `butterfly_serum` / `caterpillar` / `cocoon`; 35 species; entity; cocoons; mating; chest recipe |

Cloud `IForestryPlugin` does **not** have local `registerFilter` / `registerFarming` (Waves 3–4). Cloud `ReForestry.java` does **not** load energy/sorting/farming/cultivation. Merge must **keep local modules + those plugin hooks**.

Wave 5 leftover (not a Wave 7 skip): taxon JSON allele maps are not applied to genomes yet (`queries/genetics-wave5-report.md` on origin/main).

## Prerequisite before genetics-dependent addon stages

**W7-INT** — merge local Waves 1b–4 with `origin/main` (Wave 5), then merge `origin/cursor/wave-6-lepidopterology-9408` (Wave 6). Compile. Do not drop farming/energy/filter.

Extracts **EB0 / ET0** do not need that merge.

## Now (after W7-INT)

Order: **EB0 → ET0 → W7-INT → GD0 → EB1 → GD1 → GD2 (incl. DNA) → GD3 (incl. DNA extractor) → GD4 → GD5 → GD6 → GD7a → GD7b → GD8 → EB5 → EB6 → EB2a–e → EB3 → EB4 → ET1a → ET1b → ET2 → ET3 → ET4 → ET5 → ET6 → S2**

| # | ID | Size | Player exit |
|---|---|---|---|
| 1 | EB0 | M | Extract JSON + `tools/extract_extra_bees.py` (no merge needed) |
| 2 | ET0 | M | Extract JSON + `tools/extract_extra_trees.py` (no merge needed) |
| 3 | W7-INT | M | Local tree has Wave 5 API + Wave 6 butterflies + Waves 1b–4; `./gradlew classes` |
| 4 | GD0 | S | Module toggle; tab with parts, 23 upgrades, pollen kit |
| 5 | EB1 | M | `/give` combs, drops, frames, dusts, ectoplasm, hive blocks |
| 6 | GD1 | S | Three fluid buckets |
| 7 | GD2 | M | Mutagen + protein + bee/tree DNA recipes (butterfly DNA after Wave 6) |
| 8 | GD3 | M | Mutagen producer, protein liquefier, **DNA extractor** |
| 9 | GD4 | M | Sampler + gene sample/template |
| 10 | GD5 | M | Mutatron + advanced mutatron |
| 11 | GD6 | M | Imprinter, transposer, replicator |
| 12 | GD7a | M–L | Industrial apiary bees work with FE |
| 13 | GD7b | M | Upgrades including fertility extra drones |
| 14 | GD8 | S | 12 errors + JEI (producers + gene-sample subtypes) |
| 15 | EB5 | L | 7 alveary parts |
| 16 | EB6 | M | Centrifuge/squeezer datapack |
| 17 | EB2a–e | L | 116 Extra Bees species + 168 mutations (flower types via `registerFlowerType`) |
| 18 | EB3 | M | 25 bee effects wired |
| 19 | EB4 | M | Four hives + worldgen |
| 20 | ET1a | L | 30 woods + shrub log (1.12 kinds) |
| 21 | ET1b | L | Stripped/boats/signs/charcoal walls |
| 22 | ET2 | L | Fruits + ~88 species + mutations |
| 23 | ET3 | L | Growth / worldgen |
| 24 | ET4 | L | Lumbermill, press, brewery, distillery |
| 25 | ET5 | L | Foods, juices, alcohol, hops |
| 26 | ET6 | M | 22 Extra Trees moths on Wave 6 butterfly type |
| 27 | S2 | S | Genetic filter butterfly rules |

Optional: **ET-K** bottle rack after ET5.

## Still deferred (not this pass)

**ET-D** designer / stained glass / multi-fence. Infuser and nursery never shipped in Binnie. Binnie genetics serums (Gendustry covers that). Mail.

## Agent prompt stub

```
Implement Re-Forestry stage {ID} from queries/wave7-plan.md.
Follow .cursor/skills/reforestry-port/SKILL.md and reforestry-lookup.
Never invent registry ids. Namespace forestry:/gendustry:/extrabees:/extratrees: → reforestry:.
Packages com.leon1236.reforestry.{gendustry|extra_bees|extratrees}. No donor fabric.mod.json depends.
Mail out of scope. No Java comments.
DoD: .cursor/skills/reforestry-port/definition-of-done.md.
When done: update files/implemented-features.md and tick remaining-work-stages.md.
Do not start the next Wave 7 stage.
```
