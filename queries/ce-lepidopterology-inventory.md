# Forestry CE 1.21.1 — Lepidopterology complete inventory

Source clone used for this note: `/tmp/ForestryCE` (GitHub `thedarkcolour/ForestryCE` branch `1.21.1`).  
`tools/graphify_query.py`, `MarkDown_Maker/graphify/`, and MCP `minecraft-mods` were **not** present in this environment; inventory is from the live CE clone + `MIGRATION.md`.

Registry namespace stays `forestry:` even in the optional `forestrybutterflies` jar.

---

## Package layout (1.21.1 vs older)

| Aspect | CE 1.21.1 | Older (pre-split / 1.20.1-style) |
|---|---|---|
| Jar | Optional `forestrybutterflies` (`src/butterflies`) | Same packages lived in the single Forestry jar |
| Package root | Still `forestry.lepidopterology.*` | Same root name |
| Items | `forestry.lepidopterology.butterflies` | Was `forestry.lepidopterology.items` |
| Genetics impl | `forestry.lepidopterology.butterflies.genetics` | Was `forestry.lepidopterology.genetics` |
| Cocoon blocks/tiles | `forestry.lepidopterology.cocoons` | Was `blocks` / `tiles` |
| API | Still in **base** jar: `forestry.api.lepidopterology` under `src/main` | Same |
| Plugin | `LepidopterologyForestryPlugin` (own jar service) | Was folded into `DefaultForestryPlugin` |
| Registry ids | Unchanged | Unchanged |

Internal package moves (from `MIGRATION.md`):  
`blocks` → `cocoons`, `items` → `butterflies`, `genetics` → `butterflies.genetics`, `tiles` → `cocoons`.

Chest + backpack ship in **base** (`forestry` jar); butterfly genetics jar owns species/items/entity/cocoons.

---

## A. Module class and what it registers

**Module:** `/tmp/ForestryCE/src/butterflies/java/forestry/lepidopterology/ModuleLepidopterology.java`  
**Module id:** `forestry:lepidopterology`  
**Depends on:** `forestry:core`, `forestry:arboriculture`

### Module itself

- Cancels butterfly dimension travel
- Entity attributes for `forestry:butterfly`
- Reload listener: `ButterflySpeciesManager` (`butterfly_species` datapack folder)
- Datapack sync packet: `butterfly_species_sync`
- Commands under `/forestry butterfly …`
- Client handler: `LepidopterologyClientHandler`
- Hardcoded toggles: `spawnButterflysFromLeaves=true`, `generateCocoons=false`, serum chances `0.55` / `0`

### Feature registration (`@FeatureProvider` in butterflies jar)

| Feature class | Registers |
|---|---|
| `LepidopterologyItems` | `butterfly`, `butterfly_serum`, `caterpillar`, `cocoon` items |
| `LepidopterologyBlocks` | `cocoon`, `cocoon_solid` blocks |
| `LepidopterologyTiles` | BE types `cocoon`, `solid_cocoon` |
| `LepidopterologyEntities` | entity `butterfly` |
| `LepidopterologyRecipes` | serializer `butterfly_mating` |
| `LepidopterologyCreativeTab` | creative tab `forestry:lepidopterology` |

### Plugin (`LepidopterologyForestryPlugin`)

- Species type `forestry:butterfly_species` + karyotype + stages
- Filter rule types
- Cocoons + effect via `registerLepidopterology`
- Client analyzer plugin + default textures

### Base jar (not butterflies jar) but lepidopterology-related

- Block `forestry:lepidopterists_chest` + tile + recipe (recipe JSON generated from butterflies datagen)
- Item `forestry:lepidopterists_backpack` + recipe
- Shared menu `forestry:naturalist_inventory`
- Recipe type `forestry:butterfly_mutation` (`GeneticsRecipeTypes` in core)
- Portable analyzer / analyzer block (shared genetics)

### Menus

No butterfly-only menu. Shared:

- `forestry:naturalist_inventory` — lepidopterist chest (and other naturalist chests)
- `forestry:analyzer` — block analyzer
- Portable analyzer item GUI (core)

### Worldgen

No biome modifier / placed feature for cocoons. `generateCocoons` is hardcoded `false` and **never called** elsewhere. `cocoon_solid` is commented “used only in world generation” but generation is off.

Spawning is **leaf-tick** via `ButterflySpawner` (`ILeafTickHandler`), not classic mob spawners.

---

## B. Item ids

| Registry id | Class / notes |
|---|---|
| `forestry:butterfly` | `ItemButterflyGE` stage BUTTERFLY |
| `forestry:butterfly_serum` | stage SERUM |
| `forestry:caterpillar` | stage CATERPILLAR |
| `forestry:cocoon` | stage COCOON (item form; age NBT `Age` 0–2) |
| `forestry:lepidopterists_backpack` | base jar naturalist backpack |
| `forestry:scoop` / `forestry:proven_scoop` | shown in lepidopterology creative tab (core tools) |
| `forestry:portable_analyzer` | shared genetics alyzer |
| `forestry:foresters_manual` | also craftable with a butterfly |

Life-stage → item id contract: `ButterflyLifeStage` in  
`/tmp/ForestryCE/src/main/java/forestry/api/lepidopterology/genetics/ButterflyLifeStage.java`

There is a model `butterfly_spawn_egg.json` in butterflies assets, but **no spawn-egg item** is registered in `LepidopterologyItems`.

---

## C. Block ids

| Registry id | Notes |
|---|---|
| `forestry:cocoon` | Placeable cocoon; age property; tile `forestry:cocoon` |
| `forestry:cocoon_solid` | Solid worldgen variant; tile `forestry:solid_cocoon` |
| `forestry:lepidopterists_chest` | Base jar naturalist chest (`NaturalistChestBlockType.LEPIDOPTERIST_CHEST`) |

Nursery is **not** a dedicated block id — Forestry leaves act as `IButterflyNursery`.

---

## D. Entity types

| Registry id | Class | Size | Category |
|---|---|---|---|
| `forestry:butterfly` | `EntityButterfly` | 0.5 × 0.25 | `CREATURE` |

Path: `/tmp/ForestryCE/src/butterflies/java/forestry/lepidopterology/entities/EntityButterfly.java`

---

## E. Species (35) — Java vs datapack

**Count:** 35 (31 butterflies + 4 moths), matching `ForestryButterflySpecies.ALL`.

### Runtime source of truth

Datapack folder: `data/forestry/butterfly_species/*.json`  
Generated into: `/tmp/ForestryCE/src/generated/resources_butterflies/data/forestry/butterfly_species/`  
Loaded by `ButterflySpeciesManager` / rebuilt via `LepidopterologyReloadHandler`.

`ButterflySpeciesType.handleSpeciesRegistration` returns an **empty** species map; live species come **only** from datapack reload.

### Authoritative Java builders (also feed datagen)

`/tmp/ForestryCE/src/butterflies/java/forestry/lepidopterology/plugin/DefaultButterflySpecies.java`  
Datagen: `ButterflySpeciesProvider` reads the same builders and emits JSON.

### Species ids (`forestry:…`)

Butterflies:  
`cabbage_white`, `brimstone`, `aurora`, `clouded_yellow`, `palaeno_sulphur`, `reseda`, `spring_azure`, `gozora_azure`, `citrus_swallow`, `emerald_peacock`, `thoas_swallow`, `spicebush_swallow`, `black_swallow`, `zebra_swallow`, `glasswing`, `speckled_wood`, `mspeckled_wood`, `cspeckled_wood`, `mbluemorpho`, `pbluemorpho`, `rbluemorpho`, `comma`, `batesia`, `blue_wing`, `monarch`, `blue_duke`, `glassy_tiger`, `postman`, `malachite`, `leopard_lacewing`, `diana_fritillary`

Moths:  
`brimstone_moth`, `latticed_heath`, `atlas`, `bombyx_mori` (silk cocoon allele; authority Nedelosk)

### Taxa

34 taxon JSON files under `data/forestry/taxon/` (butterflies datapack), including root `lepidoptera`.

Species type id: `forestry:butterfly_species` (`ForestrySpeciesTypes.BUTTERFLY`).

Default species allele: `forestry:monarch`.

---

## F. Mutations

Exactly **one** built-in mutation:

| Recipe id | Parents | Result | Chance |
|---|---|---|---|
| `forestry:butterfly_mutation/bombyx_mori_1` | `latticed_heath` × `brimstone` | `bombyx_mori` | 0.07 |

- Type: `forestry:butterfly_mutation` (core `GeneticsRecipeTypes`)
- Provider: `ButterflyMutationProvider`
- Path: `/tmp/ForestryCE/src/generated/resources_butterflies/data/forestry/recipe/butterfly_mutation/bombyx_mori_1.json`

---

## G. Effects and cocoons

### Effects

| Id | Impl |
|---|---|
| `forestry:butterfly_effect_none` | `DummyButterflyEffect` (no-op) |

Chromosome `forestry:butterfly_effect` — marked **unimplemented** in API comments beyond the none allele.

### Cocoon alleles (products)

| Id | Products |
|---|---|
| `forestry:cocoon_default` | string ×2 (1), ×1 (0.75), ×3 (0.25) |
| `forestry:cocoon_silk` | silk wisp ×3 (0.75), ×2 (0.25) — used by Bombyx mori |

Block models/textures: early / middle / late (+ silk late).

---

## H. Breeding / spawning / behavior classes

| Role | Path |
|---|---|
| Individual | `…/butterflies/genetics/Butterfly.java` |
| Species type | `…/butterflies/genetics/ButterflySpeciesType.java` |
| Species runtime | `ButterflySpecies.java`, `ButterflySpeciesDefinition.java`, `ButterflySpeciesManager.java`, `ButterflySpeciesProjector.java` |
| Leaf spawn | `…/butterflies/ButterflySpawner.java` |
| Entity | `…/entities/EntityButterfly.java` |
| AI | `AIButterflyFlee`, `Mate`, `Pollinate`, `Rest`, `Rise`, `Wander` (+ base `AIButterflyBase`, `AIButterflyInteract`, `AIButterflyMovement`) |
| States | `EnumButterflyState` |
| Cocoon BE | `…/cocoons/TileCocoon.java` |
| Nursery API | leaves via `IButterflyNursery` / `TreeUtil` |

Lifecycle:

1. Spawn from sapient Forestry leaves (`SAPPINESS * YIELD` roll + species rarity), config `disable_butterfly_spawning`
2. Mate in world (`AIButterflyMate`) **or** craft mate (`butterfly` + `butterfly_serum` → mated butterfly)
3. Mated butterfly lays caterpillar into leaf nursery
4. Nursery matures → plants `forestry:cocoon`
5. Cocoon ages 0→2 then hatches butterfly + cocoon product drops
6. Serum can drop on kill (`serumChance` 0.55 × metabolism)

Dropped butterfly items can re-flutter into entities after ~80 ticks (`ItemButterflyGE.onEntityItemUpdate`).

---

## I. GUI

| UI | Menu / screen | Notes |
|---|---|---|
| Lepidopterist chest | `forestry:naturalist_inventory` / `GuiNaturalistInventory` | 128 slots, 8×5 visible, scroll; breeding-tracker ledger |
| Lepidopterist backpack | naturalist backpack container (storage module) | Same naturalist GUI pattern |
| Analyzer (block) | `forestry:analyzer` | Shared; butterfly pages via `ButterflyAnalyzerPlugin` |
| Portable analyzer | core item GUI | Same plugin |
| No dedicated “butterfly alveary / breeding machine” | — | Breeding is entity + crafting mating |

Chest renderer texture key: `lepichest` (`RenderNaturalistChest`).

---

## J. Recipes

| Recipe id | Type | Result / purpose |
|---|---|---|
| `forestry:lepidopterists_chest` | shaped | chest from glass + wooden chest + `forestry:butterfly` |
| `forestry:lepidopterists_backpack` | shaped | backpack from wool/string/sticks + chest (core datagen) |
| `forestry:butterfly_mating` | special serializer | butterfly + serum → mated butterfly |
| `forestry:foresters_manual_butterfly` | shapeless | book + butterfly → foresters manual |
| `forestry:butterfly_mutation/bombyx_mori_1` | butterfly_mutation | see §F |

Chest pattern: `" # " / "XYX" / "XXX"` with `#` colorless glass, `X` butterfly, `Y` wooden chest.

---

## K. Worldgen / spawn

- **No** cocoon worldgen while `generateCocoons == false` (and unused).
- **Spawn:** leaf tick handler when arboriculture trees tick leaves and `spawnButterflysFromLeaves`.
- Config: `ForestryConfig.SERVER.disableButterflySpawning`, cluster width/height/limit.
- Home radius: `ModuleLepidopterology.maxDistance = 64`.
- Butterflies cannot change dimensions.

---

## L. API (`forestry.api.lepidopterology`)

Under `/tmp/ForestryCE/src/main/java/forestry/api/lepidopterology/`:

| Type | File |
|---|---|
| Species id constants | `ForestryButterflySpecies.java` |
| Effect ids | `ForestryButterflyEffects.java` |
| Cocoon ids | `ForestryCocoons.java` |
| `IButterflyCocoon` | `IButterflyCocoon.java` |
| `IButterflyEffect` | `IButterflyEffect.java` |
| `IButterflyNursery` | `IButterflyNursery.java` |
| `IEntityButterfly` | `IEntityButterfly.java` |
| `ILepidopteristTracker` | `ILepidopteristTracker.java` |
| Genetics | `genetics/IButterfly.java`, `IButterflySpecies.java`, `IButterflySpeciesType.java`, `ButterflyLifeStage.java` |
| Client | `api/client/lepidopterology/IButterflyClientManager.java` |

Plugin API (base jar):

- `forestry.api.plugin.ILepidopterologyRegistration`
- `forestry.api.plugin.IButterflySpeciesBuilder`
- `IForestryPlugin#registerLepidopterology`

Chromosomes: `forestry.api.core.genetics.alleles.ButterflyChromosomes`  
(`species`, `size`, `speed`, `butterfly_lifespan`, `metabolism`, `fertility`, temp/humidity tolerance, `never_sleeps`, `tolerates_rain`, `fireproof`, `flower_type` unimplemented, `butterfly_effect`, `cocoon`)

---

## M. Plugin hooks `registerLepidopterology`

`IForestryPlugin.registerLepidopterology(ILepidopterologyRegistration)`:

- `registerSpecies(id, genus, species, dominant, serumColor, rarity)` → builder
- `registerCocoon(id, cocoon)`
- `registerEffect(id, effect)`

Called from `ButterflySpeciesType.handleSpeciesRegistration` for every plugin; CE’s own plugin also registers genetics/filter rules in `registerGenetics`.

Service entry:  
`/tmp/ForestryCE/src/butterflies/resources/META-INF/services/forestry.api.plugin.IForestryPlugin`  
→ `forestry.lepidopterology.plugin.LepidopterologyForestryPlugin`

---

## N. Client renderers

| Piece | Path |
|---|---|
| Entity renderer | `…/render/ButterflyEntityRenderer.java` |
| Entity model | `…/render/ButterflyModel.java` |
| Item model loader `forestry:butterfly_ge` | `…/render/ButterflyItemModel.java` |
| Client module | `…/proxy/LepidopterologyClientHandler.java` |
| Analyzer pages | `…/client/plugin/ButterflyAnalyzerPlugin.java` |
| Texture registration | `…/client/plugin/LepidopterologyClientRegistration.java` |
| Model layer | `ForestryModelLayers.BUTTERFLY_LAYER` |
| Item colors | serum + caterpillar tint |
| Cocoon item property | `forestry:age` |

Textures (35 each):  
`assets/forestry/textures/item/butterfly/<path>.png`  
`assets/forestry/textures/entity/butterfly/<path>.png`  
(live in **base** `src/main/resources`, not butterflies source set)

---

## O. “Flutter”, pollen, leaf interaction

**Flutter is not a separate mechanic/entity.**

Uses of the name:

1. Genetic filter rule type `FLUTTER` = “any butterfly specimen” (`LepidopterologyFilterRuleType.FLUTTER`, lang `for.gui.filter.forestry.lepidopterology.flutter`)
2. Local variable `flutter` in `ItemButterflyGE` for the individual
3. Analyzer sprite `forestry:analyzer/flutter`
4. Legacy lang keys `flutterlyzer` in some translations (no separate item in 1.21.1; unified portable analyzer)

**Pollen:** `AIButterflyPollinate` uses `IPollenManager` — pick up pollen from a rest block, later try to pollinate another. Cooldown `COOLDOWNS` (1500). Gated by `ModuleLepidopterology.isPollinationAllowed()` (hardcoded true).

**Leaves:** nursery for caterpillars; spawn source via `ButterflySpawner`; breaking leaves with caterpillar can drop caterpillar item (`BlockForestryLeaves`).

---

## P. Naturalist backpack filter

- Definition: `ModuleStorage.LEPIDOPTERIST` → `BackpackFilterNaturalist(ForestrySpeciesTypes.BUTTERFLY)`
- Accepts any individual whose species type id is `forestry:butterfly_species` (all life stages)
- Item id: `forestry:lepidopterists_backpack`
- JEI description key: `for.jei.description.lepidopterist_bag`

Genetic filter board also gets lepidopterology rule types: `flutter`, `butterfly`, `serum`, `caterpillar`, `cocoon`, plus shared pure-breed / nocturnal / flyer logics in `LepidopterologyFilterRule`.

---

## Q. Analyzer / alyzer support

- No dedicated butterfly alyzer item in 1.21.1
- Shared `forestry:portable_analyzer` + `forestry:analyzer`
- Plugin: `ButterflyAnalyzerPlugin` (4 pages: stats, climate/behavior, loot lists, mutations)
- Research material for butterfly type: glass bottle weight `0.9f`
- Tag: `forestry:genetic_samples` includes `forestry:caterpillar` (butterflies datapack)

---

## R. Research / tracker

- Tracker impl: `LepidopteristTracker` extends `BreedingTracker`
- API: `ILepidopteristTracker` — `registerCatch`, `registerPickup`
- Save file prefix: `LepidopteristTracker.<uuid|common>`
- Chest / naturalist GUI ledger shows bred count vs species count
- Commands: `butterfly kill`, give-species, modify-genome

---

## Wave 6 planning map (D0–D4 + CE completeness)

Minimal D0–D4 style scope usually means: GE items, entity, 35 species, chest recipe. CE’s **full** lepidopterology surface also includes:

1. Four life-stage items + cocoon age property  
2. Entity + full AI suite + pollen  
3. 35 datapack species + 34 taxa + chromosomes/karyotype  
4. One mutation + mating crafting recipe  
5. Cocoon blocks/tiles (default + solid) + two cocoon alleles  
6. Leaf spawn + leaf nursery breeding loop  
7. Lepidopterist chest (base) + backpack (base) + naturalist GUI  
8. Analyzer plugin pages  
9. Breeding tracker + commands + species sync packet  
10. Creative tab, JEI subtypes, filter rules, textures/models  
11. Foresters manual alternate recipe  

**Out of scope / dead in CE 1.21.1:** cocoon worldgen (`generateCocoons=false`), dedicated flutterlyzer item, butterfly spawn egg item (model only), non-none butterfly effects.

---

## Key absolute paths (clone root `/tmp/ForestryCE`)

```
src/butterflies/java/forestry/lepidopterology/ModuleLepidopterology.java
src/butterflies/java/forestry/lepidopterology/plugin/LepidopterologyForestryPlugin.java
src/butterflies/java/forestry/lepidopterology/plugin/DefaultButterflySpecies.java
src/butterflies/java/forestry/lepidopterology/features/Lepidopterology{Items,Blocks,Entities,Tiles,Recipes}.java
src/main/java/forestry/api/lepidopterology/**
src/main/java/forestry/api/plugin/ILepidopterologyRegistration.java
src/main/java/forestry/core/platform/tile/TileLepidopteristChest.java
src/main/java/forestry/core/content/backpacks/features/BackpackItems.java
src/generated/resources_butterflies/data/forestry/butterfly_species/
src/generated/resources_butterflies/data/forestry/recipe/
src/generated/resources/data/forestry/recipe/lepidopterists_backpack.json
MIGRATION.md
```
