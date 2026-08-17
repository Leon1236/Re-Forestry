---
name: reforestry-lookup
description: >-
  Looks up Forestry CE, Immersive Forestry, Minecraft 26.2, Fabric API, and
  local Re-Forestry code via graphify and the minecraft-mods MCP without
  inventing paths or registry ids. Use when researching how a CE/IF feature
  works, finding a class, method, texture, recipe, or registry id, mapping
  Forge to Fabric, checking a vanilla 26.2 API, or before writing new
  Re-Forestry code.
---

# Re-Forestry lookup

Never invent a path, class name, texture, lang key, or `reforestry:` / CE `forestry:` id. Verify first.

Check `queries/` for an existing note on this topic before researching again.

## Access order

1. **Local architecture** (what our classes call):
   `python3 tools/graphify_query.py local "ScreenBottler"`
   Then Read the cited files under `src/`.
2. **Reference architecture** (what CE/IF/JEI/FAPI call):
   `python3 tools/graphify_query.py CE "TileBottler energy"`
3. **Reference file text** — MCP `minecraft-mods`: `search_code` then `get_file`.
4. **Vanilla 26.2 APIs** — MCP repo `Minecraft-26.2`, or `python3 tools/graphify_query.py mc "BoundingBox"`.
5. Save lasting answers under `queries/{topic}.md` (short: what we chose and why).

## Graphify (architecture)

```bash
python3 tools/graphify_query.py list
python3 tools/graphify_query.py CE "TileBottler energy"
python3 tools/graphify_query.py --path CE TileBottler TilePowered
python3 tools/graphify_query.py --explain JEI RecipeCategory
python3 tools/graphify_query.py local "ContainerBackpack"
python3 tools/graphify_query.py mc "isLoaded BlockPos"
```

Aliases: `CE` (1.21.1), `CE20` (frozen 1.20.1), `IF`, `JEI`, `FAPI`, `Energy`, `MC`, `local` / `reforestry`, `binnie`, `gendustry`, `geckolib`, `corelib`, `backpack`, `kaupenjoe`, `forestry12`, `modkit`.

## MCP file text (`minecraft-mods`)

1. `GetMcpTools` with `server`: `minecraft-mods` (discover schemas).
2. `search_code` with `query`, optional `repo` (`thedarkcolour-ForestryCE` = 1.21.1, `thedarkcolour-ForestryCE-1.20.1` = frozen 1.20.1, `Minecraft-26.2`, …), `language`, `limit`.
3. `get_file` with `repo` + `path` from the hit. `list_files` to browse a folder.

If `minecraft-mods` is not in the MCP catalog, do **not** open `MarkDown_Maker/Markdown_files/*.md` or `files/mods MD/*.md`. Fall back:

```bash
python3 tools/graphify_query.py CE "ClassName"
python3 -c "import json; print(json.load(open('MarkDown_Maker/graphify/index.json'))['thedarkcolour-ForestryCE']['clone'])"
```

Read **`.java` / JSON in that clone**. If the indexed clone path is stale, run `python3 tools/refresh_reference_clones.py --check` and refresh the repo. Fall back to `MarkDown_Maker/Finished_github_clone/` for the same repo name. CE 1.21.1 package moves are documented in that clone’s `MIGRATION.md` (registry ids did not change).

## Which repo

| Need | Repo / alias |
|---|---|
| Forestry behavior, ids, GUI, recipes | `CE` (`thedarkcolour-ForestryCE`, branch `1.21.1`) |
| Frozen CE 1.20.1 (old extract/datagen layout) | `CE20` (`thedarkcolour-ForestryCE-1.20.1`) |
| Older IF fork (local clone only) | `IF` — only if 1.21.1 CE is wrong/missing |
| Extra Bees/Trees **data** | `binnie` — extract, do not translate 1.12 code |
| Vanilla method on 26.2 | `MC` / `Minecraft-26.2` |
| Fabric hook name | `FAPI` |
| Energy | `Energy` |
| JEI / backpacks / GeckoLib | `JEI`, `backpack`, `geckolib` |

`FabricMC-yarn` is mappings, not game code. Do not search it for APIs.

## Namespace when reading CE

Primary lookup is CE **1.21.1**. That branch reorganized Java (e.g. `TileBottler` is `forestry.core.content.machines.tiles`, not `forestry.factory.tiles`). Re-Forestry packages still follow the older `com.leon1236.reforestry.{module}.*` layout — map the 1.21.1 class onto our existing module; do not mass-rename our tree to match CE 1.21.1.

CE `forestry:` → our `reforestry:`.  
CE `forestry.api.*` → `com.leon1236.reforestry.api.*`.

Frozen 1.20.1 (`CE20`) still uses the older `forestry.{module}.*` paths that our code already mirrors.

CSV inventories in `files/` use `forestry:` prefixes — read them as `reforestry:`.

## Do not

- Open giant Markdown dumps as primary reading.
- Type species, mutations, or recipe tables from memory — use `tools/extract_*.py` / `tools/generate_*.py`.
- Copy `@Deprecated` 26.2 methods from CE (see project rule on `hasChunkAt` / `BoundingBox`).
- Treat `CLAUDE.md` as the feature tracker — that is `files/implemented-features.md`.
