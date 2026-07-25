# Research stack — MD dumps, MCP, graphify, Minecraft

## Why agents still open `.java`

`MarkDown_Maker/Markdown_files/*.md` are **whole-repo dumps** (ForestryCE alone is ~15MB). They do not fit in context. Their useful form is:

- `MarkDown_Maker/Markdown_files/mods.db` (SQLite + FTS5 from `ingest.py`)
- MCP `user-minecraft-mods`: `search_code` → `get_file`

Opening our own `src/.../*.java` is different: that is **code we edit**, not a reference dump.

## What each layer is

| Layer | Role |
|---|---|
| Giant `.md` dumps | Build input for `ingest.py` only — do not read as primary source |
| MCP / `mods.db` | Searchable reference mods + Minecraft-26.2 |
| `graphify-out/` | How **our** `src/` classes connect (`graphify query` / `path` / `explain`) |
| Local `src/` `.java` | Files to change after graph/MCP points you there |
| `queries/*.md` | Durable research notes — check before re-researching |

## Access order

1. Local architecture → `graphify query`, then Read cited `src/` files.
2. CE / Fabric API / Energy / … → MCP (never open the giant `.md` dumps).
3. Vanilla Minecraft → MCP repo `Minecraft-26.2` (Loom-mapped sources).
4. Save lasting answers under `queries/`.

## Do not confuse

| Thing | Actually is |
|---|---|
| `FabricMC-yarn` | Yarn **mappings project** — not game code |
| `minecraft-client.jar` in Loom cache | Binary — not searchable as text |
| `Minecraft-26.2` in MCP | Yarn-named Minecraft `.java` from Loom `genSources` |

## Rebuild notes

- Graphify: focused on live `src/main/java` (not whole `src/` assets, not `MarkDown_Maker/`, not `files/OLD_*`). After big refactors: re-detect + AST extract + `to_json(..., force=True)`.
- Minecraft index (already done once for 26.2):
  1. Loom sources jar under `.gradle/loom-cache/minecraftMaven/.../minecraft-merged-*-26.2-sources.jar` (or `./gradlew genSources`)
  2. Unzip `net/minecraft/**/*.java` into a temp folder
  3. `python3 tools/dump_minecraft_sources_md.py`
  4. `python3 MarkDown_Maker/Markdown_files/ingest.py`
