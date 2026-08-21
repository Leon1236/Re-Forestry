# GD6 — Imprinter, Genetic Transposer, Replicator

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`ImprinterBlockEntity`, `GeneticTransposerBlockEntity`, `ReplicatorBlockEntity`)

## Shipped

- Blocks + items: `reforestry:imprinter`, `genetic_transposer`, `replicator` (BE, menus, screens, crafts, loot, pickaxe, textures, hints, lang)
- Shared `ThreeInputMenu` / `ScreenThreeInput` (sampler GUI texture) for sampler + imprinter + transposer via `IGendustryHintTile`
- Replicator dual tanks (DNA + protein, 10 000 mB each); can slots drain via `FluidContainerHelper.drainIntoTank`
- Energy ctor swap: local `TilePowered(capacity, maxReceive)` = `(1000000, 10000)` for all three
  - Imprinter: 100 000 FE / 80 ticks
  - Transposer: 50 000 FE / 20 ticks; labware 20% consume
  - Replicator: 200 000 FE / 50 ticks
- Errors: `NO_TEMPLATE`, `NO_BLANK`, `NO_SOURCE`, `NO_DNA`, `NO_PROTEIN` (+ specimen/labware as used)
- Imprinter: template alleles onto individual genome; preserves mate when present
- Transposer: copies filled sample/template onto matching blank
- Replicator: complete template + DNA + protein → new individual (`IBee.setPristine(false)`); mutation stage 2 stack

## Choices

- Three-input GUIs share sampler.png (donor does the same)
- Imprinter validates organism/template before consuming inputs (GD5 workCycle discipline)
- Replicator species allele via `IValueAllele` + `ISpecies` (no donor `IAllele.cast()`)
- Fluids in droplets; 1000 mB per cycle
- No donor `fabric.mod.json` depends

## Gaps / next

- Industrial apiary: **GD7a**
- Upgrade modifiers: **GD7b**
- JEI + error sprites polish: **GD8**

## Player checks

1. Craft or `/give` imprinter / genetic_transposer / replicator; place; open GUIs
2. Imprinter: FE + labware + specimen + non-empty template → imprinted output
3. Transposer: blank + filled sample/template + labware → copy in output
4. Replicator: complete template + DNA + protein buckets/tanks → new organism (ignoble bees)
5. Error tabs for missing template / blank / source / dna / protein
