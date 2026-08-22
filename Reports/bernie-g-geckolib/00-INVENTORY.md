# Inventory — bernie-g-geckolib

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Graph alias: geckolib
- Loader / MC version: Fabric + Forge + NeoForge / Minecraft 26.2 (GeckoLib 5.5.3, Java 25; Fabric Loader 0.19.3, Fabric API 0.153.0+26.2)
- Date: 2026-07-30

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | animatable | `com.geckolib.animatable` / `common/src/main/java/com/geckolib/animatable` | Geo* animatable contracts, instance caches, managers, stateless helpers | M |
| 2 | animation | `com.geckolib.animation` / `common/src/main/java/com/geckolib/animation` | Animation core: controllers, processor, raw animations, easing/loop/state | L |
| 3 | cache | `com.geckolib.cache` / `common/src/main/java/com/geckolib/cache` | Baked geo model/animation caches, bones, resource reload hooks | M |
| 4 | model | `com.geckolib.model` / `common/src/main/java/com/geckolib/model` | GeoModel API and defaulted model helpers for asset paths | S |
| 5 | loading | `com.geckolib.loading` / `common/src/main/java/com/geckolib/loading` | Geo/animation JSON loaders, definitions, Molang math parser | L |
| 6 | renderer | `com.geckolib.renderer` / `common/src/main/java/com/geckolib/renderer` | Entity/item/block/armor/object renderers, layers, textures, internals | L |
| 7 | network | `com.geckolib.network` / `common/src/main/java/com/geckolib/network` | Multiloader animation sync packets (entity/item/block/singleton) | M |
| 8 | service | `com.geckolib.service` / `common/src/main/java/com/geckolib/service` | Platform SPI: events, networking, client/platform service facades | S |
| 9 | event-common | `com.geckolib.event` / `common/src/main/java/com/geckolib/event` | Shared GeoRenderEvent contracts used across loaders | S |
| 10 | constant | `com.geckolib.constant` / `common/src/main/java/com/geckolib/constant` | DataTickets and shared typed constants | S |
| 11 | mixin | `com.geckolib.mixin` / `common/src/main/java/com/geckolib/mixin` | Common client/server mixins for render and animatable hooks | M |
| 12 | util | `com.geckolib.util` (+ `object`) / `common/src/main/java/com/geckolib/util` | Client/render helpers and small shared object types | S |
| 13 | fabric-platform | `com.geckolib.platform` (+ entry) / `fabric/src/main/java/com/geckolib` | Fabric platform impl, mod/client entrypoints (`GeckoLib`, `GeckoLibClient`) | S |
| 14 | fabric-event | `com.geckolib.event` / `fabric/src/main/java/com/geckolib/event` | Fabric-specific Geo pre-render / compile-layer / compile-state events | M |
| 15 | fabric-network | `com.geckolib.network` / `fabric/src/main/java/com/geckolib/network` | Fabric packet registration bridging common MultiloaderPacket types | S |

## Out of scope for module agents

- Gradle / Loom / buildSrc scripts (`*.gradle.kts`, `libs.versions.toml`, wrappers)
- Pure assets under `*/src/main/resources` (textures, mixins JSON, access wideners, lang) unless a feature agent needs them as supporting evidence
- `forge/` and `neoforge/` loader modules (present in clone; graph scan roots are `common` + `fabric` only — Re-Forestry cares about Fabric)
- Root docs (`README.md`, `changelog.md`) and license files
- Example-mod Maven artifacts referenced in version catalog (not part of this clone’s source modules)

## Notes for manager

- Multiloader layout: shared API/engine in **`common`** (~257 Java files); loader glue in **`fabric`** / **`forge`** / **`neoforge`** (~31 Java each). Prefer Phase 1 agents on `common` packages + Fabric platform/event/network only.
- Suggested consumer surface for Re-Forestry: `animatable` + `model` + `renderer` (+ `animation` if custom controllers); treat `loading`/`cache` as library internals unless debugging asset paths.
- `service` is the SPI boundary; Fabric implementations live under `fabric-platform` / `fabric-event` / `fabric-network` — keep those three together when tracing loader wiring.
- Package rename vs older GeckoLib: `com.geckolib.*` (not `software.bernie.geckolib`). Mod version **5.5.3** targets **MC 26.2**.
- Graph: alias `geckolib` → `MarkDown_Maker/graphify/bernie-g-geckolib/graphify-out/` (scan_roots: `common`, `fabric`).
