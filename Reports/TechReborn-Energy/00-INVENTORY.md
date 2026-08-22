# Inventory — TechReborn-Energy

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/TechReborn-Energy`
- Graph alias: Energy
- Loader / MC version: Fabric Loader 0.18.4 / Minecraft 26.1-snapshot-1 (mod `team_reborn_energy` 5.0.0; Java 25; depends `fabric-transfer-api-v1`)
- Date: 2026-07-30

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | api | `team.reborn.energy.api` / `src/main/java/team/reborn/energy/api` | Core `EnergyStorage` lookup contract plus `EnergyStorageUtil` transfer helpers | S |
| 2 | base | `team.reborn.energy.api.base` / `src/main/java/team/reborn/energy/api/base` | Ready-made storages (simple, sided, limiting, infinite, item, delegating) | S |
| 3 | impl | `team.reborn.energy.impl` / `src/main/java/team/reborn/energy/impl` | Fabric entrypoint (`EnergyImpl`), empty storage, item-storage wiring | S |

## Out of scope for module agents

- Gradle / Loom build scripts (`build.gradle`, `gradle.properties`, wrappers) and CI (`.github/`)
- Root docs (`README.md`) and `LICENSE`
- Pure assets under `src/main/resources` (mod icon, `fabric.mod.json` tokens) unless needed as supporting evidence
- Test sources under `src/test/java/team/reborn/energy/test` (JUnit battery smoke tests — not a product module)
- Other repos — this inventory is TechReborn-Energy only

## Notes for manager

- Tiny Fabric energy API lib: **11** main Java files across **3** packages; no content/worldgen, only transfer-backed energy.
- Package layout is the real module split — do **not** invent a separate `util` module: `EnergyStorageUtil` lives in `api`.
- Conventions from README: push-based energy; reference values (1 coal = 4000, 1 plank = 750); built on Fabric API Lookup + Transaction (`fabric-transfer-api-v1`).
- Version line: 5.x targets Minecraft 26.1+; clone pins `minecraft_version=26.1-snapshot-1` (not 26.2).
- Graph alias `Energy` via `python3 tools/graphify_query.py Energy "…"`.
