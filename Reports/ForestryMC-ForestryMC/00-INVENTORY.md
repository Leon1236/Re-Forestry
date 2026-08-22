# Inventory — ForestryMC-ForestryMC

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC`
- Graph alias: `forestry12`
- Loader / MC version: Forge 14.23.5.2860 / Minecraft 1.12.2 (mod Java 8; spec 5.8.2)
- Date: 2026-07-30

Top-level packages under `src/main/java/forestry/` (excluding root `Forestry.java` / `package-info.java`). Size = Java file count: S ≤40, M 41–150, L >150.

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 01 | api | `forestry.api` / `src/main/java/forestry/api/` | Public addon API (genetics, recipes, module hooks, per-feature contracts) | L (~354) |
| 02 | apiculture | `forestry.apiculture` / `src/main/java/forestry/apiculture/` | Bees, hives, alveary, frames, bee housing logic | L (~208) |
| 03 | arboriculture | `forestry.arboriculture` / `src/main/java/forestry/arboriculture/` | Trees, wood types, fruit, charcoal submodule | L (~189) |
| 04 | book | `forestry.book` / `src/main/java/forestry/book/` | In-game Forester’s Book UI and content | M (~58) |
| 05 | climatology | `forestry.climatology` / `src/main/java/forestry/climatology/` | Habitat / climate control blocks and tiles | S (~30) |
| 06 | core | `forestry.core` / `src/main/java/forestry/core/` | Shared base: items/blocks, genetics impl, GUI, network, fluids module | L (~552) |
| 07 | cultivation | `forestry.cultivation` / `src/main/java/forestry/cultivation/` | Planter / cultivator machines | S (~27) |
| 08 | database | `forestry.database` / `src/main/java/forestry/database/` | Genetic database block and browser GUI | S (~28) |
| 09 | energy | `forestry.energy` / `src/main/java/forestry/energy/` | Engines and Forge energy integration | M (~55) |
| 10 | factory | `forestry.factory` / `src/main/java/forestry/factory/` | Processing machines (carpenter, squeezer, still, etc.) | M (~124) |
| 11 | farming | `forestry.farming` / `src/main/java/forestry/farming/` | Multiblock farm logic and farmables | M (~107) |
| 12 | food | `forestry.food` / `src/main/java/forestry/food/` | Honeyed / ambrosia-style food items | S (~5) |
| 13 | greenhouse | `forestry.greenhouse` / `src/main/java/forestry/greenhouse/` | Greenhouse multiblock and climatiser blocks (1.12 content) | S (~9) |
| 14 | lepidopterology | `forestry.lepidopterology` / `src/main/java/forestry/lepidopterology/` | Butterflies genetics and world entities | M (~69) |
| 15 | mail | `forestry.mail` / `src/main/java/forestry/mail/` | Letters, stamps, trade stations | M (~70) |
| 16 | modules | `forestry.modules` / `src/main/java/forestry/modules/` | Module loader / UID registry / enablement helpers | S (~8) |
| 17 | plugins | `forestry.plugins` / `src/main/java/forestry/plugins/` | Optional third-party compat (IC2, BC, JEI, BoP, etc.) | S (~23) |
| 18 | sorting | `forestry.sorting` / `src/main/java/forestry/sorting/` | Genetic filter block and filter UI | S (~35) |
| 19 | storage | `forestry.storage` / `src/main/java/forestry/storage/` | Backpacks and crates modules | S (~34) |
| 20 | worktable | `forestry.worktable` / `src/main/java/forestry/worktable/` | Worktable crafting station | S (~30) |

**Module count: 20** (L: 4, M: 6, S: 10)

## Out of scope for module agents

- Nested UIDs that are **not** top-level packages: `charcoal` (under arboriculture), `fluids` (under core), `backpacks` / `crates` (under storage), `research`
- Root entrypoint only: `forestry.Forestry`
- External localization repo (pulled at build; not in this clone’s Java tree)
- Per-plugin deep dives inside `plugins` unless manager assigns a compat pass
- Assets/data under `src/main/resources` (inventory is package-level only)
- Comparing or porting against CE / Immersive Forestry (other agents)

## Notes for manager

- **CE-dropped / 1.12-only packages present here:** `greenhouse`, `book`, `climatology`, `cultivation`, `database`, `sorting`, `mail`, `food`, `worktable` (and greenhouse-related block code) — treat as primary value of this repo vs CE.
- Official UID list also in `forestry.modules.ForestryModuleUids` (includes compat plugin UIDs and nested charcoal/fluids/backpacks/crates/research).
- `api` is large and cross-cuts every feature; decide whether module agents own matching `api/<feature>` slices or a separate API pass.
- `plugins` is compat only; keep out of core feature port queues unless targeting a specific mod.
- Graph alias for follow-ups: `forestry12` → `python3 tools/graphify_query.py forestry12 "…"`.
