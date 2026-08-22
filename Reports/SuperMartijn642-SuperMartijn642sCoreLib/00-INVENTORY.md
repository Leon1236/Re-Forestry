# Inventory — SuperMartijn642-SuperMartijn642sCoreLib

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`
- Graph alias: corelib
- Loader / MC version: Forge 36.2.42 / Minecraft 1.16.5 (mod `supermartijn642corelib` 1.1.22)
- Date: 2026-07-30

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | block | `com.supermartijn642.core.block` / `src/main/java/com/supermartijn642/core/block` | Base blocks, block entities, shapes, and tickable entity helpers | S |
| 2 | data | `com.supermartijn642.core.data` / `src/main/java/com/supermartijn642/core/data` | Resource conditions, conditional recipes, and custom tag entries | M |
| 3 | extensions | `com.supermartijn642.core.extensions` / `src/main/java/com/supermartijn642/core/extensions` | Small loader-facing extension hooks (e.g. tag loader) | S |
| 4 | generator | `com.supermartijn642.core.generator` / `src/main/java/com/supermartijn642/core/generator` | Datagen: models, blockstates, recipes, loot, tags, lang, advancements | M |
| 5 | gui | `com.supermartijn642.core.gui` / `src/main/java/com/supermartijn642/core/gui` | Cross-version GUI: containers, screens, widgets, slots, screen utils | L |
| 6 | item | `com.supermartijn642.core.item` / `src/main/java/com/supermartijn642/core/item` | Base items, block-items, creative tabs, item properties/rarity | S |
| 7 | mixin | `com.supermartijn642.core.mixin` / `src/main/java/com/supermartijn642/core/mixin` | Mixin/accessor hooks into Forge/vanilla for GUI, tags, datagen, render | M |
| 8 | network | `com.supermartijn642.core.network` / `src/main/java/com/supermartijn642/core/network` | Packet channel, base packets, and packet context helpers | S |
| 9 | registry | `com.supermartijn642.core.registry` / `src/main/java/com/supermartijn642/core/registry` | Registration handlers (common/client/generators) and registry wrappers | S |
| 10 | render | `com.supermartijn642.core.render` / `src/main/java/com/supermartijn642/core/render` | Custom item/BE renderers, render config/state, world render event | M |
| 11 | util | `com.supermartijn642.core.util` / `src/main/java/com/supermartijn642/core/util` | Shared utility types (Either, Maybe, Pair/Triple, Holder, etc.) | S |

## Out of scope for module agents

- Root package classes under `com.supermartijn642.core` (`CoreLib`, `ClientUtils`, `CommonUtils`, `CoreSide`, `EnergyFormat`, `TextComponents`) — mod bootstrap / shared helpers, not a feature package
- Build/CI (`build.gradle`, `gradle.properties`, `.github/`), docs (`README.md`, `changelog.md`), and license
- Non-Java assets under `src/main/resources` (including mixins JSON / `mods.toml` templates) unless a module deep-dive needs them as evidence
- Test sources under `src/test` and generated resources under `src/generated`
- Other repos — this inventory is CoreLib-only

## Notes for manager

- This clone is **Forge 1.16.5** Core Lib (shared GUI/helpers across older MC versions). There is **no** top-level `config` package in this tree; README branding sometimes says “Config Lib,” but packages are the feature map above.
- Largest feature surface is **gui** (~28 Java files, including `widget` / `widget.premade`). Next: **data** + **generator** (datagen / conditions / tags).
- Graph alias `corelib` → `python3 tools/graphify_query.py corelib "…"`.
- Module count: **11**.
