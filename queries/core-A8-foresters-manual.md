# A8 — Forester's manual

## Decision: native Forester's Almanac (Wave 9 BOOK-0)

**Choice:** Replace Patchouli stub with a **native 1.12-style almanac** (`api/book`, client screens, JSON loader). No Patchouli dependency.

**Stage 0 (DATA-FIX):** Gameplay advancement tree + guide loot wired.

| File | Purpose |
|---|---|
| `data/reforestry/advancement/root.json` | Honeycomb tab; grants `grant_guide` loot on first join |
| `data/reforestry/loot_table/grant_guide.json` | Gives `reforestry:foresters_manual` |
| `advancement/recipes/misc/analyzer.json` | Criterion fixed to `reforestry:portable_alyzer` |
| `advancement/recipes/tools/portable_alyzer.json` | Unlocks carpenter portable alyzer craft |
| `advancement/recipes/misc/foresters_manual_butterfly.json` | Butterfly manual recipe unlock |

**Recipes on disk:** `foresters_manual_honeydrop`, `foresters_manual_sapling`, `foresters_manual_butterfly`.

## CE behaviour (source of truth)

- `ForestersManualItem` → open book GUI + page-turn sound (was Patchouli on CE).
- Book content: Patchouli JSON under `assets/reforestry/patchouli_books/` — loaded natively in BOOK-0.
- Advancement reward loot `grant_guide` gives the item.

## BOOK-0 deliverables (Stage 1) — **done**

- `api/book/*` — `IForesterBook`, `IBookCategory`, `IBookEntry`, `IBookPage`, `IBookLoader`
- `core/book/*` — `BookLoader` reads existing Patchouli JSON under `patchouli_books/foresters_manual/`
- Page renderers — text, crafting, spotlight, image, carpenter, fabricator, farm_gui, farm_layout
- Client screens — categories → entries → pages (`ScreenForesterBookCategories` / `Entries` / `Pages`)
- `ForestersManualItem.use()` opens almanac via `ForesterBookOpener` + page-turn sound
- Registered in `CoreClientHandler` (`ForesterBookClient.register()`)

## BOOK-CORE (Wave 9 Stage 3) — **done**

17 entries under `core` category load from existing Patchouli JSON and render natively:

`analyzer`, `bog_earth`, `casings`, `circuit_board`, `compost`, `escritoire`, `fertilizer_compound`, `gears`, `humus`, `ores`, `pipette`, `portable_alyzer`, `resources`, `soldering_iron`, `tubes`, `worktable`, `wrench`.

Dual-recipe pages (`recipe` + `recipe2`) and carpenter/fabricator `/double` templates use live recipe lookup in `BookPageRenderer`. Spotlight pages support optional `title` key.

## BOOK-GENETICS + BOOK-LEPIDO (Wave 9 Stage 4) — **done**

- `genetics/filter` — existing Patchouli entry (crafting + filter GUI text)
- New **lepidopterology** category + 6 entries: `introduction`, `scoop`, `butterfly_chest`, `lifecycle`, `mating`, `filter_rules`
