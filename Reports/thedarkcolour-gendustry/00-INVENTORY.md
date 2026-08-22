# Inventory — thedarkcolour-gendustry

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`
- Graph alias: gendustry
- Loader / MC version: Forge 47.4.0 / Minecraft 1.20.1 (gendustryVersion 1.0.5; NeoForged legacyforge plugin)
- Date: 2026-07-30

## Modules

Top-level feature packages under `src/main/java/thedarkcolour/gendustry/` (108 Java files total).

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | api | `thedarkcolour.gendustry.api` / `src/main/java/thedarkcolour/gendustry/api` | Public tags / API surface for Gendustry | S |
| 2 | block | `thedarkcolour.gendustry.block` / `src/main/java/thedarkcolour/gendustry/block` | Machine block type enum and Forestry-style machine blocks | S |
| 3 | blockentity | `thedarkcolour.gendustry.blockentity` / `src/main/java/thedarkcolour/gendustry/blockentity` | Machine tile logic (mutatron, apiary, replicator, producers, etc.) | L |
| 4 | client | `thedarkcolour.gendustry.client` / `src/main/java/thedarkcolour/gendustry/client` | Client handler and machine screens | M |
| 5 | compat | `thedarkcolour.gendustry.compat` / `src/main/java/thedarkcolour/gendustry/compat` | Forestry plugin/errors and JEI recipe categories | M |
| 6 | data | `thedarkcolour.gendustry.data` / `src/main/java/thedarkcolour/gendustry/data` | Datagen (recipes, models, loot, lang, tags) | M |
| 7 | item | `thedarkcolour.gendustry.item` / `src/main/java/thedarkcolour/gendustry/item` | Gene samples, templates, upgrades, pollen kit, resources | M |
| 8 | menu | `thedarkcolour.gendustry.menu` / `src/main/java/thedarkcolour/gendustry/menu` | Server container menus for machines | M |
| 9 | recipe | `thedarkcolour.gendustry.recipe` / `src/main/java/thedarkcolour/gendustry/recipe` | Custom recipe types plus recipe caches | M |
| 10 | registry | `thedarkcolour.gendustry.registry` / `src/main/java/thedarkcolour/gendustry/registry` | Deferred registration (blocks, items, fluids, menus, BEs, recipe types) | M |

## Out of scope for module agents

- Gradle / NeoForged build scripts (`build.gradle`, `gradle.properties`, wrappers)
- Pure assets under `src/main/resources` and generated data under `src/generated/resources`
- Template packaging (`src/main/templates`, `mods.toml` tokens)
- Root docs / license / logo (`changelog.md`, `LICENSE.md`, `logo.png`)
- Entry / bootstrap classes `Gendustry.java`, `GendustryModule.java` (mod + Forestry module wiring only — not a content package)

## Notes for manager

- Single Forestry CE addon module: `GendustryModule` registers as `gendustry:core` (`@ForestryModule`); not a multi-module Forestry-style split.
- Mandatory deps (mods.toml): Forge, Patchouli, Forestry; JEI optional. Curios version is in gradle.properties but not a top-level package here.
- Ten machines via `GendustryMachineType`: industrial_apiary, mutagen_producer, dna_extractor, protein_liquefier, sampler, mutatron, advanced_mutatron, imprinter, genetic_transposer, replicator — all live under `blockentity` (+ matching `menu` / `client.screen`).
- Suggested Phase 1 priority: `blockentity` (L, core gameplay), then `item` + `recipe`, then `compat` (JEI/Forestry) and `registry` as wiring support.
- Graph: `MarkDown_Maker/graphify/thedarkcolour-gendustry/graphify-out/` (~1104 nodes); alias `gendustry`.
