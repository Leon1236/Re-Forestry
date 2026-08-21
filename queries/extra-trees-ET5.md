# Extra Trees ET5 — foods, juices, alcohol, hops

**Date:** 2026-08-21  
**Status:** Implemented  
**Donor:** Binnie Extra Trees `ModuleCore` / `ModuleAlcohol` / `BlockHops` (no fabric.mod.json depends)  
**Extract:** `queries/extra-trees-extract/{foods,fluids}.json`  
**Generator:** `python3 tools/generate_extra_trees_ET5.py --apply --assets --lang`

## Player exit

- Eat Extra Trees foods (59 from ET2; unchanged)
- Press fruits → typed juices in the fruit press
- Brew juices/grains + yeast → alcohols; distill alcohols → spirits (3 levels)
- Grow hops on farmland; harvest when mature

## Counts

| Kind | Count | Notes |
|---|---|---|
| Foods | **59** | Already from ET2; Papayimar still skipped |
| Fluids | **104** | 19 juice + 29 alcohol + 17 liqueur + 27 spirit + 8 misc + 4 tree liquid |
| Fluid id remaps | **2** | Alcohol `Fruit` → `alcohol_fruit` (avoid CE `juice`); `gingerAle` → `ginger_ale` |
| Press recipes | **24** | ET foods matching juice crops + vanilla apple/carrot |
| Brewery recipes | **28** | Juice ferments + grain beers/mashes |
| Distillery recipe groups | **23** | Each expands to 6 level recipes (Binnie `addDistillery`) |
| Squeezer food recipes | **51** | ModuleCore juice/oil amounts → CE `juice` / `seed_oil` |
| Misc items | **7** | yeast, yeast_lager, 5 grains |
| Hops | **1** | Single-height `CropBlock` (Binnie is double-high; playable simplify) |

## Wiring

- `ExtraTreesFluids` + client tint via shared `extra_trees_liquid` texture
- Runtime managers: `FruitPressRecipeManager`, `BreweryRecipeManager`, `DistilleryRecipeManager`
- Tiles consume recipes; distillery GUI cycles level 1–3 (button id 2)
- JEI: `ExtraTreesJeiPlugin` (press / brewery / distillery)
- Carpenter `wood_wax` with turpentine (ET4 gap closed)
- Tags: `reforestry:grain_*`, `reforestry:hops`, plus `c:crops/*` for foods

## Gaps → ET6 / later

| Gap | Why |
|---|---|
| Cocktails / glassware / drink item | Optional kitchen; not ET5 player exit |
| Double-high hops | Binnie `HALF` up/down; single crop is enough to grow |
| Liqueur machine recipes | Binnie mostly cocktail mixing, not brewery |
| Press GUI fruit-specific art | Still factory still.png stand-in |
| Village hops field | Binnie `VillageHopeField` not ported |
| Potato / agave / sparkling / fruit brew inputs | Binnie also has no brewery liquid for these (solid potato ferment unused; cocktail-only alcohols) |

## Review fixes (2026-08-21)

- Brewery grain recipes match Binnie `grainCount >= 2` (empty third slot allowed)
- Press/brewery/distillery tank tooltips show fluid name when filled
- Distillery level uses lang `extratrees.gui.distillery.level`
- `yeast_lager` display fixed to "Lager Yeast" (Binnie EN typo)
- Removed no-op `BlockHops.randomTick` override

## Next

**ET6** — 22 moths on butterfly type (`moth_*`). Do not start until ET5 is accepted.
