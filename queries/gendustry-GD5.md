# GD5 — Mutatron + Advanced Mutatron

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`AbstractMutatronBlockEntity`, `MutatronBlockEntity`, `AdvancedMutatronBlockEntity`)

## Shipped

- Blocks + items: `reforestry:mutatron`, `advanced_mutatron` (BE, menus, screens, crafts, loot, pickaxe, textures, hints, lang)
- Shared base `AbstractMutatronBlockEntity` / `AbstractMutatronMenu` / `AbstractMutatronScreen`
- Mutagen tank (10 000 mB, filtered); can slot drains via `FluidContainerHelper.drainIntoTank`
- Energy ctor swap: local `TilePowered(capacity, maxReceive)` = `(1000000, 10000)`; 100 000 FE / 40 ticks
- Errors: `NO_MUTAGEN`, `NO_LABWARE`, `NO_MATES`, `INCOMPATIBLE_SPECIES`, `NO_MUTATIONS`, `NO_SELECTION` (advanced)
- Mutations via local `IMutationManager.getCombinations` + result species default genome (+ `Mutation.specialAlleles` when present)
- Advanced: 4 choice slots + cycle buttons; selected mutation remembered as `lastChoice`

## Choices

- Genome build uses result species default genome (local `IMutation` has no CE `getResultAlleles`); special alleles applied when the concrete `Mutation` record is present
- Advanced button ids start at 10 so they do not collide with pipette tank slot `0`
- Fixed donor double-offset on selection apply; choice icons refresh when offset changes
- No donor `fabric.mod.json` depends

## Gaps / next

- Imprinter / transposer / replicator: **GD6**
- JEI: **GD8**

## Player checks

1. Craft or `/give` mutatron; place; open GUI (tank + princess/drone/labware layout)
2. Feed FE + mutagen + labware + compatible mates → mutated offspring in output
3. Advanced mutatron: click a mutation icon before work starts; cycle arrows when >4 options
4. Error tabs for missing mates / mutagen / selection
