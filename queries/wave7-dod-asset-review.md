# Wave 7 DoD asset/lang/recipe review (2026-08-21)

Independent review on `cursor/wave-7-full-port-6593` against `definition-of-done.md` + reforestry-review.

## Verdict after fixes: PASS

## Gaps found and fixed

| Severity | Gap | Fix |
|---|---|---|
| **Must** | All **88** Extra Trees saplings fell through `items/sapling.json` / `SaplingBlockStateResolver` to oak (wrong look in inv + world) | `tools/generate_extra_trees_sapling_assets.py` — Binnie template tint bake → item/block textures+models; +88 sapling select cases; +88 resolver entries |
| **Must** | ET species missing from `LeafBlockStateResolver` (planted ET leaves → oak fallback) | +88 species→texture-group entries |
| **Should** | Missing loot stubs `pods_banana` / `pods_plantain` / `pods_red_banana` (siblings cocoa/dates/papaya had stubs; drops still code-driven via `BlockFruitPod`) | Added matching empty block loot tables |
| **Should** | `ectoplasm` not in any `mineable/*` tag (Binnie `BlockWeb`; cobweb → hoe) | `data/minecraft/tags/block/mineable/hoe.json` |
| **Should** | `glass_fitting` registered without completing DoD row | items/models/texture/lang/tab/recipe (Binnie misc) |

## Confirmed OK (sampled + systematic)

- Feature item ids for GD/EB/ET: `items/{id}.json` + `models/item/{id}.json` present (misc dyes use `dye_*` / `radioactive_dust` serialized names)
- Textures for machines, alveary, hives, foods, buckets, moths, combs/drops (tints)
- `en_us`: item/block keys; woods via `for.trees.woodType.*` + grammar; Gendustry 12 error desc/help; creative tab titles
- Recipes: all GD machines/parts/upgrades, EB frames/alveary, ET machines/woods/boats (debug frame intentionally uncrafted — Binnie)
- Hive loot: N/A datapack — `BlockBeeHive.getDrops` + hive manager (same as CE apiculture; no `loot_table/blocks/beehive_*`)
- Creative tabs: gendustry / gene_samples / extra_bees / extra_trees populated
- Machine mineable pickaxe + alveary axe + EB scoop tags present

## Documented skips (unchanged)

- `hive_frame_debug` recipe — Binnie registers none (`queries/extra-bees-EB1.md`)
- Hive datapack loot — code drops
- ET-D / ET-K / infuser / nursery — unstarted modules
