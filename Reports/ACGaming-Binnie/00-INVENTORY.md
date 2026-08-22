# Inventory — ACGaming-Binnie

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Graph alias: `binnie`
- Loader / MC version: Forge (legacy) / Minecraft 1.12.2 (Forge 14.23.5.2847; Forestry 5.8.2.422)
- Date: 2026-07-30

Gradle subprojects from `settings.gradle` (player-facing feature units + APIs + fat-jar aggregator). Size = Java file count under `<module>/src/main/java`: S ≤40, M 41–150, L >150.

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 01 | all | `all/` (no Java; `all/build.gradle`) | Runtime fat-jar aggregator pulling botany/core/extrabees/extratrees/genetics | S (0 Java) |
| 02 | core | `binnie.core` / `core/src/main/java/binnie/core/` | Binnie Core: shared GUI/network/machines; in-game Compartments | L (~389) |
| 03 | core-api | `binnie.core.api` / `core-api/src/main/java/binnie/core/api/` | Public API for Binnie Core (GUI widgets, shared contracts) | S (~22) |
| 04 | botany | `binnie.botany` / `botany/src/main/java/binnie/botany/` | Botany: breedable flowers, soil/gardening, ceramic, farm hooks | M (~116) |
| 05 | botany-api | `binnie.botany.api` / `botany-api/src/main/java/binnie/botany/api/` | Public Botany API (flower genetics, gardening enums/managers) | S (~29) |
| 06 | design | `binnie.design` / `design/src/main/java/binnie/design/` | Design/carpentry blocks & GUI shared by Extra Trees woodworking | S (~21) |
| 07 | design-api | `binnie.design.api` / `design-api/src/main/java/binnie/design/api/` | Public Design API for patterned wood/glass designs | S (~12) |
| 08 | extrabees | `binnie.extrabees` / `extrabees/src/main/java/binnie/extrabees/` | Extra Bees: ~107 bee species, products, alveary bits, database | M (~128) |
| 09 | extratrees | `binnie.extratrees` / `extratrees/src/main/java/binnie/extratrees/` | Extra Trees: ~96 trees, wood/fruit, brewery/distillery, carpentry | L (~286) |
| 10 | extratrees-api | `binnie.extratrees.api` / `extratrees-api/src/main/java/binnie/extratrees/api/` | Public Extra Trees API | S (~14) |
| 11 | genetics | `binnie.genetics` / `genetics/src/main/java/binnie/genetics/` | Genetics: gene isolate/sequence/inoculate machines + analyst GUI | L (~194) |
| 12 | genetics-api | `binnie.genetics.api` / `genetics-api/src/main/java/binnie/genetics/api/` | Public Genetics API | S (~15) |

**Module count: 12** (L: 3, M: 2, S: 7)

## Out of scope for module agents

- Root Gradle/CI only: `build.gradle`, `gradle.properties`, `.github/`, `.travis.yml`, `gradlew*`
- Fat-jar module `all` (no feature source; do not treat as a content module)
- Assets/lang under each module’s `src/main/resources` unless manager assigns an assets pass
- Nested packages inside a Gradle module (e.g. `binnie.extratrees.alcohol`, `binnie.botany.ceramic`) — stay at subproject grain unless split later
- Third-party compat deep dives (JEI / JEIBees / CraftTweaker / IC2 / BuildCraft hooks inside feature modules) unless manager assigns a compat pass
- Comparing or porting against Forestry CE / Immersive Forestry / Re-Forestry `src/` (other agents)

## Notes for manager

- **Player-facing suite (README):** Extra Bees, Extra Trees, Botany, Genetics, Binnie Core (Compartments). `design` is supporting carpentry shared with Extra Trees, not a separate Curse product header.
- **Pairing:** each feature mod has a thin `*-api` sibling; decide whether module agents own API+impl together or run a separate API-data extract pass.
- **Re-Forestry fit:** project plans `reforestry:extra_bees` / `reforestry:extra_trees` as addon modules — this clone is **data/source to extract**, not a runtime dependency (1.12 Forge + ForestryMC).
- **Priority for data extract:** `extrabees` (species/products/mutations), `extratrees` (trees/wood/fruit), `botany` (flower species/colors) — genetics machines are behavior-heavy and less “CSV extract”.
- Graph alias for follow-ups: `binnie` → `python3 tools/graphify_query.py binnie "…"`.
