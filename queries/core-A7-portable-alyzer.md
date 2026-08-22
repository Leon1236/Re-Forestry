# A7 — Portable alyzer

## CE behaviour (source of truth)

- Item `portable_alyzer` (`ItemAlyzer`) opens `ContainerAlyzer` / `PortableAnalyzerScreen`.
- Inventory on the held item: energy (honey/honeydew), specimen in, five analyze page slots.
- Placing an unanalyzed genetic item in specimen + fuel → `analyze()`, register species on breeding tracker, consume 1 fuel, move stack to page I.
- Client pages drawn via `IAnalyzerPlugin` per species type (bee / tree / butterfly).
- CE also has `api.genetics.alyzer.IAlleleDisplayHelper` (tooltip/alyzer provider registry); portable screen itself uses client `IAnalyzerPlugin`.

## Fabric / Re-Forestry adaptations

| CE | Ours |
|---|---|
| Forge capability `IIndividualHandlerItem` | `IndividualItems` access API + `GeneticItemHelper` over bee/tree DataComponents |
| `IIndividual.isAnalyzed` / `analyze` | Data component `reforestry:analyzed` |
| Item NBT slot map + `Charges` | Vanilla `DataComponents.CONTAINER`; tooltip charges = honey count in slot 0 |
| Full `IAnalyzerGraphics` (products, climate icons, mutation icons) | Products + mutation icons (CORE-E3). Climate preference sprites still slim (text rows). Taxonomy page still stubbed |
| `ILifeStage` / full `ISpecies` | Life stage `String` from `ItemBeeGE` / `ItemGermlingGE`; species type ids from `ForestrySpeciesTypes` |
| `IAlleleDisplayHelper` + apiculture `IGeneticTooltipProvider` + `IIndividual` | Ported helper; tooltip provider takes `IGenome` only (same A6-style adaptation) |
| Butterfly plugin | No plugin → unsupported / nodescription until Track D |

## Assets / recipe

- Model/texture/GUI atlas already present under `assets/reforestry/`.
- Carpenter recipe `carpenter/portable_analyzer.json` (water 2000 mB, tin / glass pane / diamond / redstone).
- Tag `reforestry:drop_honey` = `honey_drop` + `honeydew`.

## Validation (2026-07-26)

Code review vs CE found and fixed:
1. **Parent identity** — `ItemInventory` now refuses to write if the hand item is no longer the opened alyzer (CE used NBT UID).
2. **Locked hotbar slot** — main-hand alyzer slot is locked while GUI is open (CE `SlotLocked`), so the parent can’t be picked up mid-session.
3. **Menu open path** — uses `ExtendedMenuProvider` (correct). Contrast: soldering iron still uses plain `MenuProvider` and can throw Fabric’s extended-menu error.

Still intentional gaps (documented slim port):
- Page V taxonomy is still a short stub (authority + no description), not CE’s full taxon tree.
- Error ledgers not drawn on the screen yet (`IErrorSource` is implemented).
- In-world smoke still needs `runServer` + minecraft-world MCP (not available in this agent session catalog).

Page III/IV landed in CORE-E3 (`queries/core-e3-alyzer-pages.md`).

## Smoke

1. Give `portable_alyzer` + honey drop + princess/drone → open GUI without crash.
2. Insert honey + unanalyzed bee → bee moves to page I; chromosomes show; honey decrements; second analyze of same bee needs no honey.
3. Sapling + honey → tree chromosomes on pages I–II.
4. Empty honey + unanalyzed specimen → stays in specimen / `NO_HONEY` error; no analyze.
