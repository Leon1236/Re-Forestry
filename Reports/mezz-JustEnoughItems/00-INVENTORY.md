# Inventory — mezz-JustEnoughItems

| Field | Value |
|---|---|
| **Repo** | mezz-JustEnoughItems |
| **Alias** | JEI |
| **Clone** | `MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems` |
| **Loader / MC** | Fabric 0.19.3 + Fabric API 0.155.0+26.2; NeoForge 26.2.0.24-beta — **Minecraft 26.2** (Java 25); JEI `30.15.0` |
| **Branch / HEAD** | `26.2` @ `758de67` |
| **Inventory date** | 2026-07-30 |
| **Module count** | **9** |

Multi-module Gradle (`settings.gradle.kts`). Rows follow major Gradle subprojects that own distinct concerns. Tiny release/CI dirs are out of scope, not merged into feature rows.

## Modules

| # | Module id | Gradle path | One-line role | Approx. Java/Kotlin sources |
|---|---|---|---|---|
| 1 | `common-api` | `CommonApi/` | Public addon surface: `IModPlugin`, recipe/ingredient/gui/registration APIs under `mezz.jei.api.*` | ~167 |
| 2 | `common` | `Common/` | Loader-agnostic shared impl: config, network, platform services, codecs, search/transfer helpers, assets | ~214 |
| 3 | `library` | `Library/` | Core engine: plugin load (`PluginLoader`), vanilla recipe plugins, recipe layouts, ingredients, JEI startup | ~232 |
| 4 | `gui` | `Gui/` | Client UI: ingredient overlay, bookmarks, recipe screens, filters, input handlers | ~213 |
| 5 | `fabric-loader` | `Fabric/` | Fabric mod jar: entrypoints, mixins, Fabric platform/fluids/network, `jei_mod_plugin` discovery | ~71 |
| 6 | `fabric-api` | `FabricApi/` | Fabric-only public API extras (`mezz.jei.api.fabric.*`, e.g. fluid ingredients) | ~8 |
| 7 | `neoforge-loader` | `NeoForge/` | NeoForge mod jar: events, platform, network, NeoForge plugins + gametests | ~104 |
| 8 | `neoforge-api` | `NeoForgeApi/` | NeoForge-only public API extras (`mezz.jei.api.neoforge.*`) | ~2 |
| 9 | `debug` | `Debug/` | Optional debug/test content (debug ingredients + data/assets) for development runs | ~26 |

## Out of scope

| Path | Why |
|---|---|
| `Changelog/` | Release-note templates / Gradle changelog packaging only |
| `buildSrc/` | Build conventions, not runtime feature code |
| `.github/`, `.jenkins/`, `.travis.yml` | CI / publish tooling |
| Root docs (`README.md`, `CONTRIBUTING.md`, `UNSUPPORTED_VERSIONS.md`, `LICENSE.txt`) | Documentation, not modules |
| Unit/gametest source sets inside modules | Covered later under owning module; not separate inventory rows |

## Notes

- Dependency shape (high level): **loader** (`Fabric` / `NeoForge`) → **Gui** + **Library** → **Common** → **CommonApi**; loader APIs (`FabricApi` / `NeoForgeApi`) sit beside the common API for loader-specific types.
- Addon integration contract for Re-Forestry: implement `mezz.jei.api.IModPlugin` (Fabric entrypoint key `jei_mod_plugin`); registration hooks live in `CommonApi` (`registerCategories`, `registerRecipes`, etc.).
- Spec version in `gradle.properties`: `specificationVersion=30.15.0` on MC `26.2`.
- Graph alias **JEI** indexes this clone; Phase 1 should one-agent-per-module using the nine ids above.
