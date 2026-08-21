# EB4 — Extra Bees wild hive worldgen

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `BinnieHiveDescription` / `WorldGenHive*` / `BlockRegister` drops  
**Extract:** `queries/extra-bees-hives.json`  
**Package:** `com.leon1236.reforestry.extra_bees.hives`

## Player exit

Find four Extra Bees wild hives in worldgen: water, rock, `beehive_eb_nether`, marble (marble only when marble blocks are tagged).

## Shipped

| Hive id (registerHive / scoop) | Block | Species loot 0.8 | Valiant 0.03 | genChance | Placement |
|---|---|---|---|---|---|
| `bee_water` | `beehive_water` | `bee_water` | yes | 2.0 | Water above sand/clay/dirt/stone (`hive_grounds/water_below`) |
| `bee_rock` | `beehive_rock` | `bee_rock` | yes | 6.0 | `STONE_ORE_REPLACEABLES` with horizontal air |
| `bee_basalt` | `beehive_eb_nether` | `bee_basalt` | yes | 6.0 | Netherrack wall embed; nether biomes only |
| `bee_marble` | `beehive_marble` | `bee_marble` | yes | 20.0 | `#c:stones/marble` / `hive_grounds/marble`; ≥1 non-marble face |

- Ignoble share **0.5** on all drops (Binnie `setIgnobleShare(0.5)`; extract verified)
- No extra comb items in hive loot (Binnie `HiveDrop` extras empty)
- Wired via `ExtraBeesForestryPlugin.registerApiculture` → existing `HiveDecorator` feature
- Scoop: EB hive blocks on `reforestry:mineable/scoop`; drops use existing `BlockBeeHive` + hive manager

## Marble soft-tag

- Convention: empty `data/c/tags/block/stones/marble.json` (OreDict `stoneMarble` → modern `c:stones/marble`)
- Consumer: `data/reforestry/tags/block/hive_grounds/marble.json` soft-refs `#c:stones/marble` (`required: false`)
- Vanilla has no marble — MARBLE hives generate only when a datapack/mod fills `c:stones/marble`

## Biome / climate

- Matches Binnie: non-nether hives accept any biome (including nether); NETHER hive requires `IS_NETHER`
- Humidity/temperature gates use hive species genome (same pattern as CE `HiveDefinition`)

## Gaps

- Marble never spawns in vanilla-only worlds (by design; soft-tag empty until a marble mod/datapack fills it)
- Rock uses `STONE_ORE_REPLACEABLES` (modern stand-in for 1.12 `isReplaceableOreGen(STONE)`)
- Water below materials mapped to tags (Material.SAND/CLAY/GROUND/ROCK → sand/gravel/clay/dirt/stone tags)
- No config knobs for hive rates (Binnie `ConfigurationMain` defaults baked as extract `gen_chance`)

## Next

**ET1a** — Extra Trees woods (`ExtraTreeWoodType` + WoodAccess). Do not start here from this stage’s agent.
