# EB1 — Extra Bees module shell

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `extrabees/` (copied into `com.leon1236.reforestry.extra_bees`)  
**Extract:** `queries/extra-bees-items.json`, `queries/extra-bees-hives.json`

## Shipped

- Module `reforestry:extra_bees` (`ModuleExtraBees`), config toggle via `modules.properties`
- Depends on `reforestry:apiculture` (+ core)
- Plugin stub `ExtraBeesForestryPlugin` on `reforestry:plugin`
- Creative tab `extra_bees` (icon: barren comb)
- **74** active combs (`bee_comb_*`) with CE-style constant tints on `bee_combs` layers
- **24** active honey drops (`honey_drop_*`) with 2-layer tints (`honey_drop.0` / `.1` from Forestry 1.12)
- **4** active propolis (`propolis_water|oil|fuel|creosote`)
- **5** hive frames (`hive_frame_cocoa|cage|soul|clay|debug`) with Binnie modifiers
- **30** misc (scented gear, shards, dusts, dyes)
- `ectoplasm` block (placeable; loot 20% slime ball)
- **4** hive blocks: `beehive_water`, `beehive_rock`, `beehive_eb_nether`, `beehive_marble`
- Frame crafts (4; debug has no recipe in Binnie)
- Carpenter `scented_gear` (honey 500 mB, planks as wood-gear fallback)
- Dust/shard → vanilla/tin shapeless where Binnie had OreDict targets that exist here
- `reforestry:combs` tag extended; `c:dusts/{iron,gold,copper,tin,coal}`

## Counts (registered)

| Kind | Count |
|---|---:|
| Combs | 74 |
| Drops | 24 |
| Propolis | 4 |
| Frames | 5 |
| Misc | 30 |
| Ectoplasm | 1 |
| Hive blocks | 4 |
| **Total giveable ids** | **142** |

## Skips (documented)

| Skip | Reason |
|---|---|
| Inactive combs/drops/propolis | Extract placeholders (`active: false`) |
| Bee dictionary / honey crystal / 16 industrial frames | Wave 7 skip list |
| Alveary parts | EB5 |
| Hive worldgen + scoop loot species | EB4 (species not registered yet) |
| Centrifuge/squeezer products | EB6 — **done** (`queries/extra-bees-EB6.md`) |
| Dust→ingot for silver/platinum/nickel/… | No matching `c:ingots/*` / vanilla item (Binnie OreDict-gated) |
| Ruby/sapphire shard → gem | No gem in stack |
| Debug frame craft | Binnie registers no recipe |

## Id notes

- Nether hive: `beehive_eb_nether` (avoids CE `beehive_nether`)
- Comb tinting: data-driven `minecraft:constant` tints in `items/*.json` (same pattern as CE combs)

## Review fixes (EB1 full review)

- Model parents for misc/frames/propolis: `minecraft:item/generated` (same as GD0)
- Frame modifiers pass running current into Binnie `apply` (stacking parity)
- `c:dyes` + `c:dyes/{color}` for Extra Bees dyes (Binnie `OreDictionary.registerOre("dyeRed", …)`)
- `c:dusts/{metal}` for remaining dusts without craft targets yet

## Wave 7 full review (2026-08-21)

- Ectoplasm: cobweb `entityInside` stickiness (Binnie `BlockWeb`) + placement floor uses `isCollisionShapeFullBlock`
- See `queries/extra-bees-wave7-full-review.md`
## Player checks

1. Config: `reforestry:extra_bees=true` in `config/reforestry/modules.properties`
2. Creative tab **Extra Bees** shows combs → drops → propolis → frames → misc → ectoplasm → hives
3. `/give @s reforestry:bee_comb_barren` / `honey_drop_energy` / `hive_frame_cocoa` / `scented_gear` / `ectoplasm` / `beehive_eb_nether`
4. Place hive blocks and ectoplasm
