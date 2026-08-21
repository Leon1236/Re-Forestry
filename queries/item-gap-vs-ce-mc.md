# Item gap — Re-Forestry vs ForestryCE / ForestryMC

**Date:** 2026-07-25
**Method:** CE Java `*Items.java` (+ crates, buckets) registry path ids vs **local Java** registrations (`*Items.java` groups/enums + block string ids). Item models alone do **not** count (many orphan assets exist).
**IDs:** CE/MC `forestry:` → our `reforestry:`.
**Note:** Fruits — CE registers bare ids (`cherry`); we register `fruit_cherry`. Treated as **present** (same content, id prefix drift).

**Implementation plan (staged):** [`item-gap-implementation-plan.md`](item-gap-implementation-plan.md)

## Counts

| | Count |
|---|---|
| ForestryCE item registry entries | ~355 (incl. 12 letters we will not port) |
| Matched locally (Java) | ~209 |
| **Still missing vs CE** | **~126** (plus 20 mail items we will not port) |

## Missing vs ForestryCE

### Core — tools, kits, bricks, gear (20)

- `axe_kit` — Axe Kit
- `brick_ash` — Brick Ash
- `brick_refractory_wax` — Brick Refractory Wax
- `brick_wax` — Brick Wax
- `bronze_axe` — Survivalist's Axe
- `bronze_hoe` — Survivalist's Hoe
- `bronze_pickaxe` — Survivalist's Pickaxe
- `bronze_shovel` — Survivalist's Shovel
- `bronze_sword` — Survivalist's Sword
- `foresters_manual` — Foresters Manual
- `gear_iron` — Gear Iron
- `hoe_kit` — Hoe Kit
- `kit_pickaxe` — Kit Pickaxe
- `kit_shovel` — Kit Shovel
- `naturalist_helmet` — Naturalist Helmet
- `pipette` — Pipette
- `portable_alyzer` — Portable Alyzer
- `research_note` — Research Note
- `sword_kit` — Sword Kit
- `wrench` — Wrench

### Lepidopterology (butterflies) (4)

- `butterfly_ge` — Butterfly Ge
- `caterpillar_ge` — Caterpillar Ge
- `cocoon_ge` — Cocoon Ge
- `serum_ge` — Serum Ge

### Mail — out of scope (20 CE items, not porting)

Forestry CE mail (catalogue, 7 stamps, 12 letters) is **intentionally not in Re-Forestry**. See [`mail-dropped.md`](mail-dropped.md). Do not treat these as a gap to close.

- `catalogue`
- `stamp_1n` … `stamp_100n` (7)
- `letter_{empty,small,big}_{fresh,stamped,opened,emptied}` (12)

### Storage — backpacks (17)

- `adventurer_bag` — Adventurer Bag
- `adventurer_bag_woven` — Adventurer Bag Woven
- `apiarist_bag` — Apiarist Bag
- `arborist_bag` — Arborist Bag
- `brewer_bag` — Brewer Bag
- `brewer_bag_woven` — Brewer Bag Woven
- `builder_bag` — Builder Bag
- `builder_bag_woven` — Builder Bag Woven
- `digger_bag` — Digger Bag
- `digger_bag_woven` — Digger Bag Woven
- `forester_bag` — Forester Bag
- `forester_bag_woven` — Forester Bag Woven
- `hunter_bag` — Hunter Bag
- `hunter_bag_woven` — Hunter Bag Woven
- `lepidopterist_bag` — Lepidopterist Bag
- `miner_bag` — Miner Bag
- `miner_bag_woven` — Miner Bag Woven

### Storage — crates (85)

- `crate` — Crate
- `crated_acacia_log` — Crated Acacia Log
- `crated_acacia_sapling` — Crated Acacia Sapling
- `crated_andesite` — Crated Andesite
- `crated_apatite` — Crated Apatite
- `crated_apple` — Crated Apple
- `crated_ash` — Crated Ash
- `crated_bee_comb` — —
- `crated_bee_comb_cocoa` — Crated Bee Comb Cocoa
- `crated_bee_comb_dripping` — Crated Bee Comb Dripping
- `crated_bee_comb_frozen` — Crated Bee Comb Frozen
- `crated_bee_comb_honey` — Crated Bee Comb Honey
- … and **73** more

<details><summary>Full list</summary>

```
crate
crated_acacia_log
crated_acacia_sapling
crated_andesite
crated_apatite
crated_apple
crated_ash
crated_bee_comb
crated_bee_comb_cocoa
crated_bee_comb_dripping
crated_bee_comb_frozen
crated_bee_comb_honey
crated_bee_comb_kaolin
crated_bee_comb_mellow
crated_bee_comb_mossy
crated_bee_comb_mysterious
crated_bee_comb_parched
crated_bee_comb_powdery
crated_bee_comb_sculken
crated_bee_comb_silky
crated_bee_comb_simmering
crated_bee_comb_sponge
crated_bee_comb_stringy
crated_bee_comb_vintage
crated_bee_comb_wheaten
crated_beeswax
crated_beetroot
crated_birch_log
crated_birch_sapling
crated_bog_earth
crated_bricks
crated_bronze
crated_cactus
crated_carrot
crated_charcoal
crated_clay_ball
crated_coal
crated_cobblestone
crated_cookie
crated_copper
crated_dark_oak_log
crated_dark_oak_sapling
crated_dark_prismarine
crated_diorite
crated_dirt
crated_fertilizer_compound
crated_glowstone
crated_granite
crated_grass_block
crated_gravel
crated_honeydew
crated_humus
crated_jungle_log
crated_jungle_sapling
crated_lapis
crated_mulch
crated_mycelium
crated_nether_bricks
crated_nether_wart
crated_netherrack
crated_oak_log
crated_oak_sapling
crated_obsidian
crated_peat
crated_phosphor
crated_pollen_cluster_crystalline
crated_pollen_cluster_normal
crated_potato
crated_prismarine
crated_prismarine_bricks
crated_propolis
crated_red_sand
crated_redstone
crated_refractory_wax
crated_royal_jelly
crated_sand
crated_sandstone
crated_seeds
crated_soul_sand
crated_spruce_log
crated_spruce_sapling
crated_stone
crated_sugar_cane
crated_tin
crated_wheat
```

</details>

## ForestryMC-only (not in CE — restore later)

Verified against 1.12 `item.for.*.name` where possible. CE dropped these (or moved to other mods/modules).

- `habitat_locator` — Habitat Locator
- `imprinter` — Imprinter
- `wax_cast` — Wax Cast
- `cart.beehouse` — Minecart with Bee House
- `cart.apiary` — Minecart with Apiary
- `camouflage_spray_can` — Camouflage Spray Can
- `habitat_screen` — Habitat Screen
- `infuser` — Infuser

Related MC content that became non-items or different systems: custom Forester’s Manual GUI (`book_forester` → CE `foresters_manual` + Patchouli), copper ingot (vanilla now), woven backpacks renamed `*_bag_t2` → `*_bag_woven`.

## Not in this list (but still missing as playable content)

These are mostly **blocks** (with block-items) from modules you have not ported yet — see `files/implemented-features.md` Next up and `queries/forestryMC-vs-forestryCE-content.md`:

- Farming multiblock farms
- Energy engines / solar
- Cultivation planters
- Worktable, genetic filter (sorting)
- CE-dropped: greenhouse, climatology, database

## Summary for planning

Largest remaining CE item gaps by volume (mail omitted — not porting):

1. **Storage — crates** — 85 ids (done in B6; list above may be stale)
2. **Core — tools, kits, bricks, gear** — 20 ids (Track A code done)
3. **Storage — backpacks** — 17 ids (Track B done)
4. **Lepidopterology** — 4 ids

Quickest CE wins if you are not starting a whole new module: the 20 core leftovers (bronze tools/kits, wrench, pipette, alyzer, spectacles, bricks, iron gear, manual, research note).
