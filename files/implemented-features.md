# Implemented features

Last updated: 2026-08-21

## Wave 5 — Genetics public API

| Stage | Status | Notes |
|---|---|---|
| GP0a1 Public species / individual | done | `ISpecies` / `IIndividual` / `ILifeStage`; Bee/Tree wrappers |
| GP0a2 Genetic manager + taxonomy | done | `IGeneticManager`; 126 taxon JSON; Fabric reload |
| GP0a3 Individual item handler | done | `IIndividualHandlerItem` + `IIndividualItem` on GE items |
| GP0b Plugin registerGenetics | done | genetics-first plugin order; flower/pollen/error/client hooks |
| GP0c Promote bee/tree API | done | `IBee`/`ITree`/`IFruit`; `modifySpecies`; `ITreeManager` |
| GP0d Lepidopterology API shell | done | empty `BUTTERFLY` type; 34 taxa; items landed in Wave 6 D0 |

## Wave 6 — Lepidopterology

| Stage | Status | Notes |
|---|---|---|
| D0 Module + GE items | done | `butterfly` / `butterfly_serum` / `caterpillar` / `cocoon`; chest recipe; tab. Smoke: `/give @s reforestry:butterfly` (and the other three ids); open `reforestry:lepidopterology`; craft `butterfly_chest`. |
| D1 35 species + mutation + analyzer | done | 35 Java species from CE `DefaultButterflySpecies`; default `monarch`; `latticed_heath × brimstone → bombyx_mori` @ 0.07; cocoon string/silk_wisp products; analyzer 4 pages; 35 sprite pairs stored. Smoke: creative tab shows 35×4 stacks; analyzer on a monarch butterfly; silk moth genome uses silk cocoon. |
| D2 Entity + renderer + item model | done | Entity `reforestry:butterfly`; AI Flee/Mate/Pollinate/Rest/Rise/Wander; species item models; re-flutter ~80 ticks; `/reforestry butterfly`; serum on kill. Smoke: `/give` monarch then drop it; scoop a flying butterfly. |
| D3 Cocoons + leaf nursery/spawn + mating | done | Blocks `cocoon` / `cocoon_solid`; `TileLeaves` nursery; leaf spawn; `reforestry:butterfly_mating`; config `lepidopterology.disable_butterfly_spawning`. Smoke: plant cocoon under leaves; nurse a caterpillar; craft butterfly + serum. |
| D4 Remaining CE surface + docs | done | `foresters_manual_butterfly`; scoop on lepidopterology tab; pollen AI already in D2; tracker pickup in D3. Smoke: craft book + butterfly; open lepidopterology tab and see scoop. |
