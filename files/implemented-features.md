# Re-Forestry — implemented features

What is **done and working** in the mod today.  
Update this file when a roadmap step lands. For how-to-build guidance, see `CLAUDE.md`. For the next work items, see **Next up** at the bottom.

Last updated: 2026-07-25 (ARB-11.9i — Phase 5 complete)

---

## Phase 1 — Project shell

| ID | Feature | Notes |
|---|---|---|
| 1 | Project setup | Gradle / Loom / Fabric 26.2 stack; mod loads in-game |
| 2 | Mod entry point | `ReForestry` + client entrypoint; startup log message |

---

## Phase 2 — Core framework

| ID | Feature | Notes |
|---|---|---|
| 3 | Registration framework | `FeatureBlockGroup` etc. — multiply constructors across enums |
| 4 | Module on/off system | Config-gated modules; client handlers only run for loaded modules |

---

## Phase 3 — Simple content

| ID | Feature | Notes |
|---|---|---|
| 5 | Simple items | Honey products, wax, pollen, combs, core resources/foods, fuels |
| 6 | Simple blocks | 81 vanilla-wood fireproof wood blocks |
| 3C | Bee comb blocks | 17 `block_bee_comb_*` with dual-tint coloring |
| 3D | Core blocks | Ores, resource storage, peat, humus, bog earth (tick behavior) |

---

## Phase 4 — Bees (apiculture)

| ID | Feature | Notes |
|---|---|---|
| 7 | Genetics engine | Alleles, chromosomes, genomes, karyotypes, DataComponents |
| 8 | Bee species + items | 69 species; queen/drone/princess/larvae; per-species tinting |
| 9 | Bee housing | Apiary + Bee House; GUI; production; progress bar; bee particles |
| 10 | Breeding + mutations | Mating, lifespan, offspring, 114 mutations (discovery journal deferred) |
| 4.0 | Climate + housing APIs | `api.climate`, biome→temp/humidity manager, expanded `IBeeHousing` (modifiers/listeners/errors/climate), scoop tags, species ideal climate |
| 4.1 | Tools, frames, armor | Scoop (+proven), smoker, frames×4 (wear + production mods), apiarist armor (implements `IArmorApiarist`); recipes + creative tab |
| 4.2 | Wild beehives + worldgen | 12 `beehive_*` blocks + `TileHive`; scoop drops/silk; smoker calm + sting/armor; `HiveDecorator` via Fabric biome mods |
| 4.3 | Work gating | `BeekeepingLogic.canWork` climate/activity/rain/sky/flower pipeline; real `ActivityType` + `FlowerType` tags; `HasFlowersCache` spiral; wild hive activity+rain; GUI error icons |
| 4.4 | Bee effects (partial) | CE-shaped `IBeeEffect` (`doEffect`/`doFX`); plugin registration; aggressive/potion/snowing/exploration/heroic/misanthrope/glacial live; remaining effects stubbed as `DummyBeeEffect`; hakuna_matata/matata mob effects; armor reduces harmful effects |
| 4.5 | Multiblock core framework | `api.multiblock` + `core.multiblock` (registry, rectangular controller, tile base); Fabric level tick / chunk load / BE load / world unload hooks; `TestRectangularController` smoke subclass (alveary playable structure is G) |
| 4.6 | Alveary (all 7 parts) | 3×3×3 `AlvearyController` (plain roof/interior validation, wooden slabs on top, air around the entrances, ×2 territory, climate steps, bee FX + pollen dust); plain, stabiliser (no mutations), fan/heater (Team Reborn `EnergyStorage`, 2k buffer, ±1 temperature step), hygroregulator (Fabric fluid tank, water/lava recipes), sieve (pollen from `IBeeListener.onPollenRetrieved`), swarmer (royal jelly → swarm hive via `HiveDefinitionSwarmer`); 4 menus + screens; recipes and item models |

---

## Phase 5 — Trees (arboriculture) — **done**

| ID | Feature | Notes |
|---|---|---|
| 11.1 | Shared genetics refactor | Mating/mutations usable by trees, not only bees |
| 11.2 | Forestry wood blocks | 43 wood types × ~26 kinds (~1,118 blocks), fireproof variants, signs |
| 11.3 | Tree genetics engine | 10 chromosomes, fruits, `TREE_GENOME` component |
| 11.4 | Tree species + germlings | 50 species, 40 mutations; sapling/pollen items; grafters |
| 11.5 | Sapling/leaves + growth | Plant → tick → logs+leaves; girth checks; CE growth pipeline (FeatureTree) |
| 11.6 | Tree breeding + pollination | Pollen mating; leaf drops; bee↔tree pollen on **genetic** leaves |
| 11.7 | Client rendering | Per-species leaf/sapling models, foliage tint, fruit overlay, sign BER |
| 11.8a | Species worldgen metadata | Rarity, temp/humidity, tree-feature factory, decorative/vanilla leaf hooks on all 50 species |
| 11.8b | Default / decorative leaves | 50×3 BE-less leaf families; shear/pick → decorative; wood `setDefaultLeaves` for worldgen |
| 11.8c | Growth engine | `FeatureBase`/`FeatureHelper`/`FeatureArboriculture`/`FeatureTree`/`TreeGrowthHelper` |
| 11.8d | Feature shapes batch A | Temperate/vanilla silhouettes wired in `DefaultTreeSpecies` |
| 11.8e | Feature shapes batch B | Remaining CE Features; all 50 species wired; `SimpleTreeGenerator` removed |
| 11.8f | Wild TreeDecorator + Fabric biome mods | Biome VEGETAL_DECORATION; `trees.tree_spawn_chance_modifier`; `custom_tree` feature type |
| 11.8g | Wood boats / chest boats | Shared boat entities + wood synched data; 43+43 items, recipes, dispenser, tab, renderer |
| 11.9a | Fruit pods | `pods_*` + BE; dates/papaya/coconut on log faces; cocoa → vanilla cocoa |
| 11.9b | Grafter vanilla loot | Fabric `MODIFY_DROPS` + vanilla leaf→genome map; grafter craft recipe |
| 11.9d | Charcoal pile | Log pile → ash with wall yields; charcoal block fuel 16000; recipes + `c:storage_blocks/charcoal` |
| 11.9e | Recipes / tags / loot catch-up | Vanilla fireproof recipes+loot+tags (incl. mangrove/pale_oak); pod/leaves loot; ash recipes; common tags |
| 11.9f | Creative tabs / models / lang | Tab lists leaves/pods/boats/charcoal; MC 26.2 item defs; lang fallbacks |
| 11.9g | Tree admin commands | `/reforestry tree spawnTree\|spawnForest <species>` (op; needs player look-dir) |
| 11.9i | Integration pass | Play-loop probes + docs; fixed `pale_pale_oak_*` datapack ids and item `#minecraft:leaves` ↔ block-only `reforestry:leaves` — see `queries/arb-11.9i-integration.md` |

**Deferred / polish (not blocking Phase 5):**

| ID | Feature | Notes |
|---|---|---|
| 11.9c | Vanilla/default leaf pollen bridge | Genetic `TileLeaves` only today |
| 11.9h | Arborist villager | Needs `tree_chest` / naturalist chest — `queries/arb-11.9h-arborist-villager.md` |

---

## Next up (not implemented yet)

| ID | Feature |
|---|---|
| — | Apiculture catch-up E remainder | Port remaining exotic effects (sculk, phasing, radioactive, …) |
| — | Apiculture catch-up H–I | Wax → polish (F multiblock core done as 4.5, G alveary done as 4.6) |
| 11.9c | Bee ↔ tree pollen on vanilla/default leaves | Expand `TreePollenType` (optional post–Phase 5 polish) |
| 11.9h | Arborist villager | **Deferred** — needs `tree_chest` / naturalist chest |
| 12 | Gendustry, Extra Bees, Extra Trees (as config modules) |
| — | Factory, farming, mail, lepidopterology, worktable, sorting, storage, energy, cultivation |
| — | Guide book, climatology, greenhouse, food, database (CE-dropped, restore later) |

---

## How to update this file

When you finish a step:

1. Move it from **Next up** into the matching phase table (or add a new row).
2. Keep the note short — what the player/dev can rely on, not a full verification diary.
3. Bump **Last updated**.
4. Point agents at this file for “what’s done,” not at `CLAUDE.md`.
