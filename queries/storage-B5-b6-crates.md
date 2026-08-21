# Storage B5–B6 — crates

**Date:** 2026-08-17  
**CE:** 1.21.1 `CrateItems` + `ApicultureCrates` + carpenter `recipe/carpenter/crates/**`  
**Extract:** `python3 tools/generate_crate_data.py --apply`

## What players get

- Empty `reforestry:crate` (carpenter: 4 logs in a diamond + 1000 mB water → 24 crates).
- 83 filled `crated_*` items. Carpenter pack: 3×3 of the item + empty crate in the box slot + 100 mB water → 1 filled crate. Unpack: shapeless filled crate → 9 items, no fluid.
- Right-click a filled crate: consume 1, drop 9 of the contained item (40-tick pickup delay, CE).

Comb crate **registry ids** follow CE 1.21.1 (`crated_honey_comb`, `crated_spongy_comb`, …), not CE20 `crated_bee_comb_*`. Pack/unpack **ingredients** still use our CE20 item ids (`bee_comb_honey`, `ingot_bronze`, `fertilizer_compound`, `pollen_cluster_normal`, `propolis_normal`).

## API

None. CE has no crate types in `forestry.api.storage`. Contents are a field on `ItemCrated`, not NBT.

## Fabric vs CE

| CE | Re-Forestry |
|---|---|
| NeoForge `forestry:filled_crate` model loader (content overlay + `IColoredItem`) | Vanilla layered `item/generated` (`STOR-CRATE`). See `queries/stor-crate.md` |
| Honeydew crate on `CrateItems` (core item in CE) | Honeydew crate in `ApicultureCrates` (our honeydew lives in apiculture) |
| `@FeatureProvider` static load | `CrateItems.init()` from `ModuleStorage`; bee crates from `ModuleStorage.registerOptionalCrates()` after every module `init` (both ids are in `loadedModules`) |
| Carpenter `box: []` / `{ "item": "..." }` + `liquid.id` | Omit empty box; `box` string; `liquid.fluid` (existing carpenter codec) |

Bee crate recipes still exist if apiculture is disabled (datapack will fail to resolve those ingredients). Default config has apiculture on.
