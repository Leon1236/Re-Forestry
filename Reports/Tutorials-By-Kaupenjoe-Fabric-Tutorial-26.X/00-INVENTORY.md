# Inventory — Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28/Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X`
- Graph alias: kaupenjoe
- Loader / MC version: Fabric Loader 0.19.3 / Minecraft 26.2 (Fabric API 0.155.2+26.2, Loom 1.17-SNAPSHOT, mod `tutorialmod` 0.0.54-26.2)
- Date: 2026-07-30

## Modules

Coherent tutorial lesson areas from `net.kaupenjoe.tutorialmod` packages (not every class). Nested `custom` / `entity` / `packet` packages belong to their parent module.

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | blocks | `…tutorialmod.block` (+ `custom`, `entity`, `entity.renderer`) | Block registration, custom blocks (crops, lamp, magic, pedestal, crystallizer), block entities, BER | M |
| 2 | items | `…tutorialmod.item` (+ `custom`) + `food` | Item/tool/armor registration, custom items (e.g. chisel), food properties | M |
| 3 | menus | `…tutorialmod.menu` (+ `custom`) | Menu types and client screens (pedestal, crystallizer GUIs) | M |
| 4 | recipes | `…tutorialmod.recipe` (+ `custom`) | Custom recipe type/serializer (crystallizer) and registration | S |
| 5 | datagen | `…tutorialmod.datagen` (+ `recipe`, `villager`) | Fabric datagen: models, loot, tags, recipes, advancements, paintings, jukebox, damage types, villager trades | L |
| 6 | compat | `…tutorialmod.compat` (+ `custom`) | Optional REI plugin (crystallizer category/display) | S |
| 7 | networking | `…tutorialmod.networking` (+ `packet`) | Payload registration and C2S/S2C packet examples | S |
| 8 | villagers | `…tutorialmod.villager` | Custom villager profession / POI wiring | S |
| 9 | effects | `…tutorialmod.effect` + `potion` | Mob effects and potion registration | S |
| 10 | sounds | `…tutorialmod.sound` | Sound event registration | S |
| 11 | tags | `…tutorialmod.tags` | Runtime block/item (and related) tag keys | S |
| 12 | loot | `…tutorialmod.loot` | Loot table modifiers (Fabric loot API) | S |
| 13 | creative_tabs | `…tutorialmod.creativemodetab` | Creative mode tab registration and contents | S |
| 14 | data_components | `…tutorialmod.data` | Custom item data components | S |
| 15 | registries | `…tutorialmod.registries` | Fuel, compostable, and brewing-recipe Fabric registry hooks | S |
| 16 | keymapping | `…tutorialmod.keymapping` | Client keybinding registration | S |
| 17 | mixins | `…tutorialmod.mixin` | Example / client mixins | S |
| 18 | stats | `…tutorialmod.stat` | Custom statistics registration | S |

**Module count: 18**

## Out of scope for module agents

- Gradle / Loom build scripts (`build.gradle`, `gradle.properties`, wrappers)
- Pure assets under `src/main/resources/assets` (textures, models, lang, sounds files) unless needed as supporting evidence
- Generated datapack output under `src/main/generated` (consume as evidence of datagen only)
- Root docs (`README.md`, LICENSE) and Git metadata
- Entrypoint bootstrap classes `TutorialMod`, `TutorialModClient`, `TutorialModDataGenerator` (wiring only — not content modules)
- Worldgen (no `worldgen` / biome / configured-feature package or data in this clone)
- YouTube playlist / per-branch lesson history outside this checked-out tree

## Notes for manager

- This is a **tutorial showcase mod**, not a production content mod: packages map to Fabric 26.2 lesson topics (registration, datagen, GUIs, BE, REI, networking). Prefer pattern extraction over porting tutorial content into Re-Forestry.
- Stack matches Re-Forestry targets closely (MC 26.2, Loader 0.19.3, Fabric API 0.155.2+26.2). Soft dep: REI (+ Architectury/Cloth via build). Entrypoints: `main`, `client`, `fabric-datagen`, `rei_client`, `rei_common`.
- Cross-cutting demos: crystallizer spans **blocks** + **menus** + **recipes** + **datagen** + **compat**; pedestal spans **blocks** + **menus**. Assign the owning package as primary; treat other packages as supporting.
- Villager **runtime** lives in `villagers`; trade/POI **datagen** lives under `datagen/villager` — keep Phase 1 villager agent aware of both, or pair with datagen when researching trades.
- No ore/tree/structure worldgen lesson in this tree; do not invent a worldgen module.
