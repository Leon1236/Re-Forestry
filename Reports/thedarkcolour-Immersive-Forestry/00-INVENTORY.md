# Inventory — thedarkcolour-Immersive-Forestry

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-Immersive-Forestry`
- Graph alias: IF
- Loader / MC version: NeoForge 21.1.x / Minecraft 1.21.1 (mod `forestry` 2.10.7)
- Date: 2026-07-30

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | api | `forestry.api` / `src/main/java/forestry/api/` | Public Forestry API (genetics, climate, modules, capabilities, tags) | L |
| 2 | apiculture | `forestry.apiculture` / `src/main/java/forestry/apiculture/` | Beekeeping: bees, hives, alveary, frames, honey products | L |
| 3 | apiimpl | `forestry.apiimpl` / `src/main/java/forestry/apiimpl/` | Internal implementations of the public `forestry.api` contracts | S |
| 4 | arboriculture | `forestry.arboriculture` / `src/main/java/forestry/arboriculture/` | Trees: species, wood, leaves, pollen, fruit, charcoal | L |
| 5 | compat | `forestry.compat` / `src/main/java/forestry/compat/` | Optional mod integrations (JEI, KubeJS, Curios, etc.) | S |
| 6 | core | `forestry.core` / `src/main/java/forestry/core/` | Shared core: items, tiles, GUI, network, fluids, genetics utilities | L |
| 7 | cultivation | `forestry.cultivation` / `src/main/java/forestry/cultivation/` | Planter / cultivator machines for automated crop growth | S |
| 8 | energy | `forestry.energy` / `src/main/java/forestry/energy/` | Forestry engines and energy generation | S |
| 9 | factory | `forestry.factory` / `src/main/java/forestry/factory/` | Factory machines (carpenter, centrifuge, fermenter, still, etc.) | M |
| 10 | farming | `forestry.farming` / `src/main/java/forestry/farming/` | Multifarm blocks, logic, and farmable definitions | M |
| 11 | lepidopterology | `forestry.lepidopterology` / `src/main/java/forestry/lepidopterology/` | Butterflies: species, cocoon, serum, breeding | M |
| 12 | mail | `forestry.mail` / `src/main/java/forestry/mail/` | Postal system: letters, stamps, traders, carriers | M |
| 13 | modules | `forestry.modules` / `src/main/java/forestry/modules/` | Module loader / feature-registry framework for Forestry modules | S |
| 14 | plugin | `forestry.plugin` / `src/main/java/forestry/plugin/` | Default `IForestryPlugin`: species taxonomies and built-in content | S |
| 15 | sorting | `forestry.sorting` / `src/main/java/forestry/sorting/` | Genetic filter / sorter for bees, trees, butterflies | S |
| 16 | storage | `forestry.storage` / `src/main/java/forestry/storage/` | Backpacks, crates, and related storage items | S |
| 17 | worktable | `forestry.worktable` / `src/main/java/forestry/worktable/` | Worktable crafting station and recipe memory | S |

## Out of scope for module agents

- Root entry `forestry.Forestry` / `Forestry.java` (mod bootstrap only — not a content module)
- Build/CI (`build.gradle`, `gradle.properties`, `.github/`), docs (`docs/`, `PORTING.md`, `changelog.md`), and repo tooling under `tools/`
- Non-Java assets/data under `src/main/resources` and templates (`neoforge.mods.toml`) unless a module deep-dive explicitly needs them
- Deferred Immersive Forestry–specific machines/integrations called out in README as post-parity work
- Other repos (Forestry CE, 1.12 Forestry, Binnie, etc.) — this inventory is IF-only

## Notes for manager

- Unofficial NeoForge 1.21.1 fork of Forestry CE; **mod ID stays `forestry`** (replacement for CE, not side-by-side).
- Top-level layout under `src/main/java/forestry/` matches CE-style modules; `api` / `apiimpl` / `modules` / `plugin` are distinct packages worth separate assignment when API vs content matters.
- Size from Java file counts roughly: core≈462, api≈309, apiculture≈188, arboriculture≈180 (L); factory/farming/mail/lepidopterology (M); rest (S).
- Graph alias `IF` is available via `python3 tools/graphify_query.py IF "…"`.
