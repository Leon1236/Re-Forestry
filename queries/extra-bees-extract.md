# Extra Bees extract (EB0)

Research extract from Binnie `extrabees/` only. Not playable content. Re-run:

```
python3 tools/extract_extra_bees.py --clone MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie --out queries --apply
```

## Counts

| Item | Extracted | Expected | Source |
|---|---:|---:|---|
| Species | 116 | 116 | `ExtraBeeDefinition` |
| Mutations | 168 | 168 | `ExtraBeeDefinition.registerMutations` + `doInit` |
| Forestry-result mutations | 34 | 34 | `doInit` 3-arg `registerMutation` |
| Effects | 25 | 25 | `ExtraBeesEffect` |
| Flower types | 11 | 11 | `ExtraBeesFlowers` |
| Alveary parts | 7 | 7 | `ExtraBeeMachines` |
| Hive types | 4 | 4 | `EnumHiveType` / `BinnieHiveDescription` |
| Frames | 5 | 5 | `EnumHiveFrame` |
| Active combs | 74 | — | `EnumHoneyComb` 2-arg ctor |
| Inactive combs | 12 | — | `EnumHoneyComb` no-arg ctor |

All locked counts match `queries/wave7-plan.md`.

## Id collisions (local CE)

Locked remaps:
- `extrabees.species.primeval` → `reforestry:bee_eb_primeval` (CE `reforestry:bee_primeval`)
- `extrabees.species.relic` → `reforestry:bee_eb_relic` (CE `reforestry:bee_relic`)
- `extrabees.species.boggy` → `reforestry:bee_eb_boggy` (CE `reforestry:bee_boggy`)
- effect `RADIOACTIVE` → `reforestry:bee_effect_eb_radioactive` (CE `reforestry:bee_effect_radioactive`)
- hive `NETHER` → `reforestry:beehive_eb_nether` (CE `reforestry:beehive_nether`)

## Skip list (Wave 7 / extract policy)

| Skip | Why | Source |
|---|---|---|
| Empty ALLOY branch | No species; commented INVAR/BRONZE/BRASS/STEEL | `ExtraBeeBranchDefinition.ALLOY`, `ExtraBeeDefinition` alloy comment block |
| Commented species ELECTRUM, OLIVINE, PEAT, CITRUS, MINT, ANGRY, INVAR, BRONZE, BRASS, STEEL | Not registered | `ExtraBeeDefinition` `//NAME` lines |
| Inactive combs BRONZE, MINT, CITRUS, PEAT, BRASS, ELECTRUM, STEEL, IRIDIUM, OLIVINE, INVAR, PULP, MULCH | No-arg ctor placeholders (`active = false`) | `EnumHoneyComb()` |
| Inactive honey drops CITRUS, FRUIT, VEGETABLE, PUMPKIN, MELON | No-arg ctor placeholders | `EnumHoneyDrop()` |
| Inactive propolis MILK, FRUIT, SEED, ALCOHOL, GLACIAL, PEAT | No-arg ctor placeholders | `EnumPropolis()` |
| Bee dictionary | Wave 7 skip | `ItemBeeDictionary` registry `dictionary` |
| Honey crystal | Wave 7 skip (IC2 electric item) | `ItemHoneyCrystal` registry `honey_crystal` |
| Industrial frames (16) | Wave 7 skip | `EnumIndustrialFrame` |
| Binnie genetics / botany / Extra Trees | Out of EB0 scope | parse `extrabees/` only |

## ARTIC spelling

Binnie enum is `ARTIC` (not ARCTIC). Extract keeps `reforestry:bee_artic` / `extrabees.species.artic`.

## Forestry-result mutations

34 mutations result in CE bees (`BeeDefinition.*`). Those need GP0c `modifySpecies` before they can be registered from Extra Bees. Listed in `extra-bees-mutations.json` with `forestry_result: true`.

## Files

- `queries/extra-bees-id-map.json`
- `queries/extra-bees-species.json`
- `queries/extra-bees-mutations.json`
- `queries/extra-bees-effects.json`
- `queries/extra-bees-flowers.json`
- `queries/extra-bees-items.json`
- `queries/extra-bees-hives.json`
