# Wave 9 Stage 9 — CORE-UX + BOOK-FARM-ARBOR

**Date:** 2026-08-22

## G-REDSTONE-VIS — done

Farm `CONTROL` blocks override `Block.isSignalSource` so redstone dust visually connects (`RedStoneWireBlock.shouldConnectTo`). `ownSignal` / `getSignal` stay at 0 — the farm does not emit power, only reads neighbor signals via `TileFarmControl.cancelTask`.

## CORE-A7-TAXONOMY — done

Portable analyzer page V (`AnalyzerScreenGraphics.drawTaxonomyPage`):

- Walks `ITaxon` parent chain from `ISpecies.getGenus()` with rank colours and droppable-rank skip when overcrowded
- Species binomial + authority from species data (not hardcoded)
- Description from `getDescriptionTranslationKey()` when lang exists; else `for.gui.alyzer.nodescription`

Page II climate (`drawClimatePreferences` on bee + butterfly plugins):

- Temperature/humidity preference rows (active/inactive)
- Tolerance rows with atlas icons (`tolerance_both/up/down/none.png`)

## HOPPER-TEST — done

Documented code-level verification in `queries/hopper-test.md` (rules unchanged; matches `factory-play-loop.md`).

## BOOK-FARM-ARBOR — done

Verified 13 almanac entries (farming 9 + arboriculture 4) under `patchouli_books/foresters_manual/en_us/entries/`.

Fixes:

- Structure crafting pages: recipe ids `farm_*_brick` → existing `brick_farm_*` recipes
- Valve page lang: BuildCraft pipe → generic fluid pipe

Categories: `reforestry:farming`, `reforestry:structure` (parent farming), `reforestry:arboriculture`. Lang keys already present in `en_us.json`.

## Player checks

1. Place redstone dust against a farm control block — wire connects; farm still reads signal without emitting.
2. Analyze a bee page II — climate preferences + tolerance icons; page V — full taxonomy tree + authority/description.
3. Forester's Manual → Farming (9 entries) + Arboriculture (4 entries) render; structure recipes show valid crafts.
4. Factory hopper rules: see `queries/hopper-test.md` (code review only).
