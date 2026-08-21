# EB5 — Extra Bees alveary parts

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `extrabees` `ModuleAlveary` + machines (copied into `com.leon1236.reforestry.extra_bees`)  
**Ids:** `queries/extra-bees-id-map.json` → `reforestry:alveary_*`

## Shipped

| Part | Registry id | Behavior |
|---|---|---|
| Mutator | `alveary_mutator` | Slot for mutagen items; boosts mutation; consumes on queen death |
| Frame Housing | `alveary_frame` | One hive frame; applies frame modifiers; wear ×5 |
| Rain Shield | `alveary_rain_shield` | `isSealed` |
| Lighting | `alveary_lighting` | `isAlwaysActive` |
| Stimulator | `alveary_stimulator` | Circuit board + FE; 9 stimulator circuits |
| Hatchery | `alveary_hatchery` | Occasional larvae from queen genome (1/2400) |
| Transmission | `alveary_transmission` | FE buffer; distributes to other alveary energy parts |

## Circuits (9)

Layout `reforestry.stimulator` / socket `reforestry:stimulator`. Soldered from electron tubes (Binnie tube metas): iron, diamond, apatite, obsidian, lapis, blaze, gold, tin, bronze.

## Recipes

Plain `alveary_block` + tubes / refined circuit board / tin gear — mirror Binnie `ModuleAlveary.registerRecipes`.

## Energy

Stimulator + transmission use Team Reborn `SimpleEnergyStorage` (1000 capacity) like local fan/heater. Transmission moves FE into other multiblock components that accept insert.

## Integration

Tiles use `MultiblockLogicAlveary` + `IAlvearyComponent` so they assemble with Forestry alveary. `BlockAlveary` entrance detection counts Extra Bees alveary blocks as neighbors.

## Review fixes (2026-08-21)

- Rain shield / lighting return per-tile `IBeeModifier` (`this`) so removing one of several identical parts does not clear the shared static modifier from `AlvearyController`
- Item tooltips include Binnie per-part `extrabees.alveary.*.info` italic lines
- Nether circuit boolean tooltip uses `for.gui.hellish` (not the circuit title key)
- Hatchery hoppers may insert larvae (`canPlaceItemThroughFace` matches slot validator)

## Gaps / skips

| Item | Note |
|---|---|
| IC2 mutagens (uranium/MOX/…) | No IC2 in stack — only soul sand / ender pearl / ender eye |
| JEI alveary categories | Local alveary has none; stimulator circuits use soldering iron like farms |
| Mutator GUI item icons | Text list of mutagens (GuiGraphicsExtractor has no item blit) |

## Player checks

1. Craft all 7 parts; place in a working 3×3×3 alveary (+ slabs)
2. Mutator: soul sand / ender pearl / eye; watch mutation; item consumes on queen death
3. Frame housing: insert untreated/Extra Bees frame
4. Rain shield / lighting: bees work in rain / at night
5. Stimulator: solder stimulator circuit → insert board → supply FE
6. Hatchery: larvae appear over time with a queen
7. Transmission: FE in → fans/heater/stimulator charge
