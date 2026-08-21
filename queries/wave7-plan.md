# Wave 7 — Addons (locked plan)

**Date:** 2026-08-21 (re-planned; docs synced to full-port order)  
**Repo state:** W7-INT done — Waves 1b–4 + Wave 5 genetics API + Wave 6 butterflies on this tree. EB0 + ET0 extracts done. Mail out of scope.

Re-plan vs donor clones / extracts:

- Gendustry: `MarkDown_Maker/github_clone/thedarkcolour-gendustry` (or Finished_github_clone copy)
- Binnie: `MarkDown_Maker/.../ACGaming-Binnie`
- Extracts: `queries/extra-bees-*.json`, `queries/extra-trees-extract/`
- Textures: `for textures only/thedarkcolour-gendustry/` and `for textures only/ACGaming-Binnie/` (or donor `src/main/resources`)

## Locked decisions

| Item | Decision |
|---|---|
| Packages | `com.leon1236.reforestry.gendustry` / `extra_bees` / `extratrees` |
| Module ids | `reforestry:gendustry` / `extra_bees` / `extra_trees` |
| Namespace | always `reforestry:` |
| Plugins | `GendustryForestryPlugin`, `ExtraBeesForestryPlugin`, `ExtraTreesForestryPlugin` via `fabric.mod.json` `reforestry:plugin` |
| Woods | `ExtraTreeWoodType` + `FeatureBlockGroup` + `WoodAccess`. Do not edit `ForestryWoodType` |
| Infuser / nursery | **never** — unfinished in Binnie |
| Designer | **ET-D** deferred |
| Kitchen bottle rack | **ET-K** deferred — Binnie never shipped it; foods do not depend on it |
| EB id collisions | `bee_eb_primeval`, `bee_eb_relic`, `bee_eb_boggy`; effect `bee_effect_eb_radioactive`; hive `beehive_eb_nether` |
| ARTIC | keep Binnie spelling |
| Youth elite upgrade | port code (mutation −20%/stack), not the lifespan tooltip |
| Gendustry energy ctor | local `TilePowered(capacity, maxReceive)` — swap donor argument order |
| Industrial apiary | `new BeekeepingLogic(this)` like `TileBeeHousing` |
| EB genetics order | **Flowers + 25 effects before any EB2 species batch** |
| Charcoal walls | Not an ET1b goal |

**Skip:** Gendustry scoop/grafter/uranium/Patchouli/`debug_wand`; Extra Bees dictionary / honey crystal / industrial frames / Binnie genetics / inactive combs / ALLOY branch; Extra Trees 9 duplicate species + 6 overlap woods + databases; mail.

## Counts (verified)

| Content | Count | Note |
|---|---|---|
| Extra Bees species | 116 | 29 branch enum values; ALLOY empty |
| Extra Bees mutations | 168 | 34 result in CE bees (`modifySpecies` — GP0c) |
| Extra Bees effects | 25 | |
| Extra Bees flower types | 11 | before EB2 |
| Extra Trees species | 97 | skip 9 CE duplicates → ~88 new |
| Extra Trees fruits | 59 | ~54 new |
| Extra Trees plank woods | 36 | skip 6 CE overlap → **30 new** + shrub log |
| Extra Trees moths | 22 | no mutations; Wave 6 |
| Gendustry machines | 10 | |
| Gendustry upgrades | 23 | 17 + 6 elite |

## Prerequisite

**W7-INT** — **done**. **EB0 / ET0** — **done**.

## Now (remaining order)

Order: **GD0 → EB1 → GD1 → GD2 (incl. DNA) → GD3 (incl. DNA extractor) → GD4 → GD5 → GD6 → GD7a → GD7b → GD8 → EB5 → EB6 → EB-FLOWERS+EB3 → EB2a–e → EB4 → ET1a → ET1b → ET2 → ET3 → ET4 → ET5 → ET6 → S2**

| # | ID | Size | Player exit | Connected must-ship |
|---|---|---|---|---|
| 1 | GD0 | S | Module toggle; tab with 10 parts, 23 upgrades, pollen kit | **done** — Plugin entry; upgrade tag; crafts; models/lang; pollen-kit use |
| 2 | EB1 | M | `/give` combs, drops, frames, dusts, ectoplasm, hive blocks | Module + tab; frame crafts; scented_gear; dust/shard recipes |
| 3 | GD1 | S | Three fluid buckets | Fluid blocks + textures + lang |
| 4 | GD2 | M | Mutagen + protein + DNA recipes load | **done** — All 4+7+10 DNA (bee/tree/butterfly) |
| 5 | GD3 | M | Mutagen producer, protein liquefier, DNA extractor | **done** — Menus/screens; labware chance; energy ctor swap |
| 6 | GD4 | M | Sampler + gene sample/template | **done** — 26.2 components; wipe + combine; gene_samples tab |
| 7 | GD5 | M | Mutatron + advanced mutatron | **done** — GUIs, tanks, errors; FE ctor swap |
| 8 | GD6 | M | Imprinter, transposer, replicator | **done** — Dual tanks; blank/source/dna/protein errors |
| 9 | GD7a | M–L | Industrial apiary bees work with FE | **done** — `BeekeepingLogic(this)`; menu/GUI |
| 10 | GD7b | M | Upgrades including fertility extra drones | **done** — Modifier + energy; youth mutation −20%/stack |
| 11 | GD8 | S | 12 errors + JEI | Error sprites; soft jei_mod_plugin |
| 12 | EB5 | L | 7 alveary parts | 7 crafts; stimulator circuits; FE |
| 13 | EB6 | M | Centrifuge/squeezer datapack | Soft-skip missing fluids/oredict |
| 14 | EB-FLOWERS+EB3 | M | 11 flower types + 25 effects | Before any EB2 genomes |
| 15–19 | EB2a–e | L | 116 species + 168 mutations | 34 `modifySpecies` with EB2a |
| 20 | EB4 | M | Four hives + worldgen | Loot + marble soft-tag |
| 21 | ET1a | L | 30 woods + shrub log | ExtraTreeWoodType + WoodAccess |
| 22 | ET1b | L | Stripped/boats/signs/trapdoor/button/plate | Full WoodBlockKind; no charcoal walls |
| 23 | ET2 | L | Fruits + ~88 species + mutations | registerFruit; overlap woods → ForestryWoodType |
| 24 | ET3 | L | Growth / worldgen | FeatureTree ports |
| 25 | ET4 | L | Lumbermill, press, brewery, distillery | Lumbermill playable; misc early items |
| 26 | ET5 | L | Foods, juices, alcohol, hops | Fluids + machine recipes |
| 27 | ET6 | M | 22 moths on butterfly type | Ids `moth_*` |
| 28 | S2 | S | Genetic filter butterfly/moth rules | After ET6 |

## Still deferred (not this pass)

**ET-D** designer / stained glass / multi-fence. Infuser and nursery never shipped in Binnie. **ET-K** bottle rack. Binnie genetics serums (Gendustry covers that). Mail.

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
