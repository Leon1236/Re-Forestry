# Wave 9 Stage 8 — ADDON-POLISH + BOOK-ADDON

**Date:** 2026-08-22

## EB-MARBLE-TAG — already done

`data/reforestry/tags/block/hive_grounds/marble.json` references `#c:stones/marble` with `"required": false`. `HiveGenMarble` uses `ReforestryBiomeTags.Blocks.MARBLE_HIVE_BLOCKS`.

## EB-CENT-SKIP — partial fix

Added `OREDICT_TO_ITEM` in `tools/generate_extra_bees_machine_recipes.py` for vanilla fallbacks:

| OreDict | Item |
|---|---|
| `dustSulfur` | `minecraft:gunpowder` |
| `dustSmallIron` / `dustSmallPyrite` | `minecraft:iron_nugget` |
| `dustCertusQuartz` | `minecraft:quartz` |
| `dustEnderPearl` | `minecraft:ender_pearl` |
| `dustSawdust` / `sawdust` | `minecraft:stick` |

Regenerated with `--apply`. Soft-skip lines **29 → 22** (7 try-products now resolve). IC2 items, uranium ores, rubber, and mod-specific dusts still skip.

## ET-GUI-ART — done

Replaced `still.png` stand-ins for `textures/gui/press.png`, `brewery.png`, `distillery.png` with fermenter-base composites plus Binnie block art (176×166 / 176×222).

## ET kitchen chain

| Item | Status |
|---|---|
| **ET-COCKTAIL → ET-LIQUEUR** | **done** — 11 brewery liqueur recipes: neutral spirit + `c:crops/*` → liqueur fluid (no yeast). See `ExtraTreesAlcoholRecipes.registerLiqueurs()` + `BreweryRecipeManager.addLiqueurRecipe`. |
| **ET-PAPAYIMAR** | **done** — `EnumExtraTreesFood.PAPAYIMAR`, item assets, `c:crops/papayimar` tag. No press juice in Binnie extract. |
| **ET-HOPS-2 → ET-VILLAGE-HOPS** | **deferred** — double-high hops block + `VillageHopeField` need 26.2 jigsaw village piece port (Binnie 1.12 `StructureVillagePieces`). Single-height hops remain playable. |

## BOOK-ADDON — done

New category `reforestry:addons` + 4 entries under `patchouli_books/foresters_manual/en_us/entries/addons/`:

- `gendustry_overview`
- `industrial_apiary`
- `extra_bees_alveary`
- `extra_trees_machines`

Lang keys in `en_us.json`. `BookLoader` auto-loads category + entries.

## Player checks

1. Marble hive generates on marble-tagged blocks when `#c:stones/marble` mod is present.
2. Centrifuge `bee_comb_acidic` can yield gunpowder (try chance unchanged).
3. Brewery: neutral spirit + almond → almond liqueur (crop in ingredient slot, yeast empty).
4. Eat `reforestry:papayimar`.
5. Open Forester's Manual → Addons category → four entries render.
