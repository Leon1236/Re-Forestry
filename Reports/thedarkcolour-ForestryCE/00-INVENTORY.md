# Inventory — thedarkcolour-ForestryCE

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/thedarkcolour-ForestryCE`
- Graph alias: CE
- Loader / MC version: Forge 47.4.0 / Minecraft 1.20.1 (forestryVersion 2.10.2)
- Date: 2026-07-30

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | api | `forestry.api` / `src/main/java/forestry/api` | Public Forestry API (genetics, climate, modules, capabilities, tags) | L |
| 2 | apiimpl | `forestry.apiimpl` / `src/main/java/forestry/apiimpl` | Runtime implementations of the public API (managers, client genetics UI) | S |
| 3 | apiculture | `forestry.apiculture` / `src/main/java/forestry/apiculture` | Bees, hives, alveary, beekeeping logic and related content | L |
| 4 | arboriculture | `forestry.arboriculture` / `src/main/java/forestry/arboriculture` | Trees, wood, leaves, fruit, worldgen; also hosts charcoal submodule | L |
| 5 | compat | `forestry.compat` / `src/main/java/forestry/compat` | Optional third-party integrations (e.g. Curios module) | S |
| 6 | core | `forestry.core` / `src/main/java/forestry/core` | Shared items/blocks/GUI/config/genetics base; also hosts fluids submodule | L |
| 7 | cultivation | `forestry.cultivation` / `src/main/java/forestry/cultivation` | Automatic planters / farm-like cultivation machines | M |
| 8 | energy | `forestry.energy` / `src/main/java/forestry/energy` | Engines and Forestry energy production/consumption | M |
| 9 | factory | `forestry.factory` / `src/main/java/forestry/factory` | Processing machines (carpenter, centrifuge, fermenter, still, etc.) | M |
| 10 | farming | `forestry.farming` / `src/main/java/forestry/farming` | Multiblock farm controller, farm logic, and farm blocks | M |
| 11 | lepidopterology | `forestry.lepidopterology` / `src/main/java/forestry/lepidopterology` | Butterflies / moths genetics, entities, and related content | M |
| 12 | mail | `forestry.mail` / `src/main/java/forestry/mail` | Letter/trade-station mail system and stamps | M |
| 13 | modules | `forestry.modules` / `src/main/java/forestry/modules` | Module manager and feature-registration framework | S |
| 14 | plugin | `forestry.plugin` / `src/main/java/forestry/plugin` | Default Forestry plugin: species/taxonomies/woods/farms registration | S |
| 15 | sorting | `forestry.sorting` / `src/main/java/forestry/sorting` | Genetic filter / sorting machines | M |
| 16 | storage | `forestry.storage` / `src/main/java/forestry/storage` | Backpacks and crate-style storage items | M |
| 17 | worktable | `forestry.worktable` / `src/main/java/forestry/worktable` | Worktable crafting station | S |

## Out of scope for module agents

- Gradle / Loom build scripts (`build.gradle`, `gradle.properties`, wrappers)
- Pure assets under `src/main/resources` (textures, models, lang) unless a feature agent needs them as supporting evidence
- Generated / template packaging (`src/main/templates`, built `mods.toml` tokens)
- Root docs (`README.md`, changelog, HOW TO notes) and license files
- Entry class `forestry.Forestry` (mod bootstrap only — not a content module)

## Notes for manager

- Official `ForestryModuleIds` also lists **charcoal**, **fluids**, and **curios** as separate modules, but they are not top-level packages: charcoal lives under `arboriculture`, fluids under `core`, curios under `compat`. Module agents for those parents should treat nested module classes as in-scope unless charcoal/fluids/curios are split into their own agent assignments.
- `api` vs `apiimpl` vs `plugin`: API is the contract surface; apiimpl is the dogfood implementation; plugin is default content registration. Boundaries can blur for genetics/species work — prefer routing public contracts to `api`, registration data to `plugin`.
- `modules` is framework (feature registry / module manager), not game content; keep agents focused on content packages unless researching module wiring.
- Suggested slug keep-as-is: all top-level package names already match CE module id style (`apiculture`, `factory`, …). Optional extra inventory rows later: `charcoal`, `fluids`, `curios` if managers want 1:1 with `ForestryModuleIds`.
