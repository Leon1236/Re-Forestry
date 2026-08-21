# Wave 6 — Lepidopterology implementation report

Playable Forestry CE lepidopterology on Fabric 26.2 (`reforestry`). Branch `cursor/wave-6-lepidopterology-9408`. PR https://github.com/Leon1236/Re-Forestry/pull/2

## What landed

| Stage | What |
|---|---|
| D0 | `ModuleLepidopterology`; GE items `butterfly` / `butterfly_serum` / `caterpillar` / `cocoon`; data components; tab; chest recipe |
| D1 | 35 Java species; default `monarch`; silk mutation 0.07; cocoon products; analyzer plugin; 35 sprite pairs stored |
| D2 | Entity `reforestry:butterfly`; AI; renderer; species item models; re-flutter; commands; serum on kill |
| D3 | Cocoon blocks/tiles; `TileLeaves` nursery; leaf spawn; mating recipe; spawn config; tracker pickup |
| D4 | `foresters_manual_butterfly`; scoop on tab; docs |

## Ids

- GE items: `reforestry:butterfly`, `reforestry:butterfly_serum`, `reforestry:caterpillar`, `reforestry:cocoon`
- Blocks: `reforestry:cocoon` (no BlockItem), `reforestry:cocoon_solid`
- Entity: `reforestry:butterfly`
- Chest / bag (local): `reforestry:butterfly_chest`, `reforestry:lepidopterist_bag`
- Recipes: `butterfly_chest`, `butterfly_mating`, `foresters_manual_butterfly`
- Config: `lepidopterology.disable_butterfly_spawning` (default false = spawn on)
- Species: 35 ids matching CE `ForestryButterflySpecies.ALL`; default `monarch`
- Mutation: `latticed_heath × brimstone → bombyx_mori` @ 0.07 (builder `7f`)

## Fabric-vs-CE

See `queries/lepidopterology-wave6.md`. Highlights: Java species (not datapack JSON); Java mutation; no second genome; plugin finalize after items; leaf spawn from `randomTick`; mating serializer-only; re-flutter mixin; `ServerLevel.getGameRules()` for `SPAWN_MOBS`.

## Review table

Five independent full-diff reviews after D4. No Musts. Two small Shoulds from R2 were applied (mate death uses `hurtServer`; `plantCocoon` checks `Level.isLoaded`).

| Agent | Gate | Verdict |
|---|---|---|
| R1 | CE parity (ids, 35 species, 1 mutation, lifecycle, recipes, analyzer) | pass |
| R2 | Fabric 26.2 (no `hasChunkAt`, BoundingBox, entity/attributes, no Forge) | pass |
| R3 | No second genome; Java species; local chest/bag ids; no donor-mod deps | pass |
| R4 | Plugin/module completeness (finalize, sprites, nursery, config) | pass |
| R5 | DoD / assets (item JSON, tints, lang, tab, recipes, tags) | pass |

Leftover Shoulds are listed in `queries/lepidopterology-wave6.md`.

## Smoke

World may be down. Manual checks:

1. `/give` a monarch butterfly from the lepidopterology tab (genome already on the stack)
2. Craft `butterfly_chest` (glass + chest + butterflies)
3. Craft book + butterfly → Forester's Manual
4. Craft butterfly + serum → mated butterfly
5. Open analyzer on a monarch butterfly (4 pages)
6. Drop a butterfly item; it re-flutters after ~80 ticks
7. Use a cocoon on Forestry leaves / nurse a caterpillar on leaves
8. Leaf spawn if trees are present and `lepidopterology.disable_butterfly_spawning` is false
9. Cocoon age 0→2 then hatch (products + entity)

Compile: `./gradlew classes` succeeded after D0–D4 and after the post-review 26.2 Shoulds.
