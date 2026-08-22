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
| MCP / `mods.db` | Searchable reference mods + Minecraft-26.2 (file text / exact paths) |
| `graphify-out/` | How **our** `src/main/java` classes connect |
| `MarkDown_Maker/graphify/<repo>/` | Per-clone **architecture** graphs (call/type edges) for reference repos |
| Local `src/` `.java` | Files to change after graph/MCP points you there |
| `queries/*.md` | Durable research notes — check before re-researching |

## Access order

1. Local architecture → `graphify query` (root `graphify-out/`) or `python3 tools/graphify_query.py local "…"`, then Read cited `src/` files.
2. Reference-repo architecture (“what calls what” in CE/JEI/…) → `python3 tools/graphify_query.py <repo|alias> "…"` (index: `MarkDown_Maker/graphify/index.json`).
3. Reference file text / exact paths → MCP `search_code` → `get_file` (never open the giant `.md` dumps).
4. Vanilla Minecraft APIs → MCP `Minecraft-26.2`, or graph `python3 tools/graphify_query.py mc "…"`.
5. Save lasting answers under `queries/`.

## Graphify query (fast paths)

```bash
python3 tools/graphify_query.py list
python3 tools/graphify_query.py CE "TileBottler energy"
python3 tools/graphify_query.py --path CE TileBottler TilePowered
python3 tools/graphify_query.py --explain JEI RecipeCategory
python3 tools/graphify_query.py local "ScreenBottler"
```

Aliases: `CE` (Forestry CE **1.21.1**), `CE20` (frozen CE **1.20.1**), `IF`, `JEI`, `FAPI`, `Energy`, `MC`, `local`/`reforestry`, `binnie`, `gendustry`, `geckolib`, `corelib`, `backpack`, `kaupenjoe`, `forestry12`, `modkit`.

## Do not confuse

| Thing | Actually is |
|---|---|
| `FabricMC-yarn` | Yarn **mappings project** — not game code |
| `minecraft-client.jar` in Loom cache | Binary — not searchable as text |
| `Minecraft-26.2` in MCP | Yarn-named Minecraft `.java` from Loom `genSources` |

## Rebuild notes

- **Refresh GitHub clones + Markdown + mods.db + graphify** (usual command):
  ```bash
  python3 tools/refresh_reference_clones.py --check
  python3 tools/refresh_reference_clones.py
  python3 tools/refresh_reference_clones.py CE JEI FAPI
  ```
  Catalog: `tools/reference_repos.py`. Frozen CE 1.20.1 is skipped unless `--include-frozen`. Yarn is skipped unless `--include-skipped`. Immersive Forestry is optional (upstream GitHub is gone). Minecraft-26.2 is not a GitHub clone.
- **Local graph:** `GRAPHIFY_FORCE=1 graphify extract ./src/main/java --code-only`, then keep canonical outputs in project-root `graphify-out/` (do not leave `src/main/java/graphify-out/`).
- **One graph only:** `python3 tools/graphify_clone.py --list` / `--all` / `<repo>`.
- Add a **new** reference repo:
  1. Add a `RefRepo(...)` row in `tools/reference_repos.py` (url, branch, `scan_roots`)
  2. `python3 tools/refresh_reference_clones.py <repo-name>`
  3. Update the repo table in `.cursor/rules/reforestry-source-of-truth.mdc` and aliases in `tools/graphify_query.py`
- Minecraft index (already done once for 26.2):
  1. Loom sources jar under `.gradle/loom-cache/minecraftMaven/.../minecraft-merged-*-26.2-sources.jar` (or `./gradlew genSources`)
  2. Unzip `net/minecraft/**/*.java` into a temp folder
  3. `python3 tools/dump_minecraft_sources_md.py`
  4. `python3 MarkDown_Maker/Markdown_files/ingest.py`

`thedarkcolour-ForestryCE` is the **1.21.1** clone (primary). Frozen 1.20.1 lives beside it as `thedarkcolour-ForestryCE-1.20.1` (recipe extract scripts and alias `CE20`). `tools/graphify_clone.py` finds `graphify` on PATH, `$GRAPHIFY`, or `~/micromamba/envs/cei/bin/graphify`.

## Graphify rebuilt 2026-08-22 (full refresh: clones + mods.db + graphify)

| Graph | Nodes | Edges |
|---|---:|---:|
| `reforestry-local` (`graphify-out/`) | 8905 | 22207 |
| `thedarkcolour-ForestryCE` (`1.21.1` @ `6c2b822`, rebuilt 2026-08-22) | 15704 | 58372 |
| `thedarkcolour-ForestryCE-1.20.1` (frozen `1.20.1` @ `4abde9a`, rebuilt 2026-08-22) | 14457 | 52207 |
| `thedarkcolour-Immersive-Forestry` | 18308 | 47300 |
| `ForestryMC-ForestryMC` (`mc-1.12` @ `72e15b0`, rebuilt 2026-08-22) | 18196 | 63455 |
| `FabricMC-fabric-api` (`26.2` @ `370a4fc`, rebuilt 2026-08-22) | 12814 | 41271 |
| `ACGaming-Binnie` (`master-MC1.12` @ `21df891`, rebuilt 2026-08-22) | 13280 | 41564 |
| `Minecraft-26.2` | 80222 | 297589 |
| `mezz-JustEnoughItems` (`26.2` @ `a71ae7c`, rebuilt 2026-08-22) | 6232 | 20458 |
| `Tiviacz1337-Travelers-Backpack` (Fabric `26.1-fabric` @ `4c30fc4`, rebuilt 2026-08-22) | 2990 | 10821 |
| `bernie-g-geckolib` (`main` @ `0504807`, rebuilt 2026-08-22) | 2578 | 7609 |
| `SuperMartijn642-SuperMartijn642sCoreLib` (`forge-1.16` @ `7903696`, rebuilt 2026-08-22) | 2017 | 5244 |
| `thedarkcolour-gendustry` (`1.20.1` @ `a9fac63`, rebuilt 2026-08-22) | 856 | 2505 |
| `Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X` (`main` @ `16103a9`, rebuilt 2026-08-22) | 654 | 1745 |
| `thedarkcolour-ModKit` (`1.20.1` @ `2623205`, rebuilt 2026-08-22) | 442 | 1293 |
| `TechReborn-Energy` (`master` @ `fdc4144`, rebuilt 2026-08-22) | 144 | 347 |

Skipped: `FabricMC-yarn` (mappings only). No live clone for `CoFH-1.20.4` (MCP dump only).
