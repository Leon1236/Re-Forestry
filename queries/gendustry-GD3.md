# GD3 — Gendustry producer machines

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`ProducerBlockEntity`, `PoweredTankBlockEntity`, `GendustryMachineType`)

## Shipped

- Blocks + items: `reforestry:mutagen_producer`, `protein_liquefier`, `dna_extractor` (`FeatureBlockGroup` over 3-value `GendustryMachineType` only)
- Tiles: `PoweredTankBlockEntity` → `ProducerBlockEntity` → three concrete tiles
- Shared menu/screen id `reforestry:processor` (`ProducerMenu` / `ScreenProducer`)
- Energy ctor swap: local `TilePowered(capacity, maxReceive)` = `(1000000, 10000)` (donor Forge args were reversed)
- Fabric fluid tanks (`MultiFluidTank` / `FilteredFluidStorage`); container fill via `FluidContainerHelper`
- DNA extractor `usesLabware=true` with 10% labware consume chance
- Crafts, loot tables, `#minecraft:mineable/pickaxe`, GUI texture, hints, lang
- `GendustryError` (12) registered for DNA labware + later machines (sprites copied)
- Creative tab: machines first (donor order), icon `mutagen_producer`
- Tank GUI colors from fluid `particleColor` (sampled from still textures)
- Inventory drops on break (`playerWillDestroy`)

## Energy / cycle

| Machine | FE / cycle | Ticks |
|---|---|---|
| Mutagen producer | 100000 | 200 |
| DNA extractor | 80000 | 50 |
| Protein liquefier | 20000 | 100 |

## Choices

- Register only the three GD3 machines (other donor enum values wait for GD4+)
- Menu id stays donor `processor` (donor todo rename)
- Errors shipped early (needed for `NO_LABWARE`); JEI still GD8
- No donor `fabric.mod.json` depends
- No Forge caps — Team Reborn energy + Fabric transfer fluids/items

## Gaps / next

- Sampler + gene sample/template: **GD4**
- Remaining machines (mutatron…): GD5–GD7
- JEI producer categories: **GD8**

## Player checks

1. Craft or `/give` the three machines; place; open GUI (shared processor layout)
2. Feed FE + valid input (redstone / meat / bee); fluid appears in tank; empty bucket fills
3. DNA extractor: needs labware; labware sometimes consumes (10%)
4. Creative tab: machines → pollen kit → buckets → parts → upgrades
