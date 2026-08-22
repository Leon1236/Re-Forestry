# Inventory — thedarkcolour-ModKit

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-ModKit`
- Graph alias: modkit
- Loader / MC version: Forge 1.20.1-47.0.3 / Minecraft 1.20.1
- Date: 2026-07-30

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | block | `thedarkcolour.modkit.block` / `src/main/java/thedarkcolour/modkit/block` | Dev/test infinite-power block content | S |
| 2 | blockentity | `thedarkcolour.modkit.blockentity` / `src/main/java/thedarkcolour/modkit/blockentity` | Block-entity for the infinite-power block | S |
| 3 | data | `thedarkcolour.modkit.data` / `src/main/java/thedarkcolour/modkit/data` | Datagen helpers: recipes, tags, models, loot, lang, NBT recipes | L |
| 4 | item | `thedarkcolour.modkit.item` / `src/main/java/thedarkcolour/modkit/item` | Creative/dev wand items (fill, clear, clone, kill, distance) | M |

## Out of scope for module agents

- Gradle / ForgeGradle build scripts (`build.gradle`, `gradle.properties`, wrappers, `jitpack.yml`)
- Pure assets under `src/main/resources` (textures, `mods.toml`, pack metadata) unless needed as supporting evidence
- Generated resources (`src/generated`, `src/generated_test`)
- Root package bootstrap/utilities: `ModKit.java`, `ModKitDataGen.java`, `MKUtils.java` (not a feature package)
- Testmod under `src/test/java/thedarkcolour/testmod` (consumer example, not library modules)
- License / root docs

## Notes for manager

- Small Forge library (“make mods without all the boilerplate”); graph ~563 nodes / 1259 edges. Primary reuse surface is **`data`** (`DataHelper`, `MKRecipeProvider`, tags/models/loot/lang providers, NBT recipe builders).
- Nested under `data` only (not separate top-level modules): `data.loot`, `data.model`, `data.recipe` — keep with the `data` agent.
- `block` / `blockentity` / `item` are shipped sample/dev tools registered by root `ModKit`, not a large content pack.
- Module count: **4**
