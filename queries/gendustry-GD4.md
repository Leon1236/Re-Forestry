# GD4 — Sampler + gene sample / template

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`SamplerBlockEntity`, `GeneSampleItem`, `GeneticTemplateItem`, `GeneSampleInfo`, `GeneticTemplateRecipe`)

## Shipped

- Block + item: `reforestry:sampler` (BE, menu `sampler`, screen, craft, loot, pickaxe tag, textures, hints)
- Items: `gene_sample`, `genetic_template` (filled) — blanks already from GD0
- Data components: `reforestry:gene_sample` (`GeneSampleInfo`), `reforestry:genetic_template` (`GeneticTemplateInfo`) — not raw NBT
- Sampler: specimen + blank gene sample + labware → random allele sample; energy ctor `(100000, 10000)`; 20k FE / 20 ticks
- Tooltips: chromosome + allele (dominant/recessive colors); template allele list + count
- Smelting wipe: `gene_sample` → `blank_gene_sample`, `genetic_template` → `blank_genetic_template`
- Crafting: `reforestry:genetic_template` serializer + `combine_genetic_template` recipe
- Creative tab `itemGroup.gene_samples`: best-effort dump from all species default genomes (unique alleles per chromosome × species type)

## Choices

- Donor still used NBT on items; we store the same fields as MC 26.2 `DataComponentType`s (matches local butterfly/tree genome components)
- No `IKaryotype.getAlleles` in our API — creative tab collects alleles from registered species default genomes
- Color coding constants mirrored from `ScreenPortableAnalyzer` (item class must stay common, not client)
- Energy argument order swapped vs donor Forge ctor (local capacity first)
- No donor `fabric.mod.json` depends

## Gaps / next

- Mutatron + advanced mutatron: **GD5**
- Imprinter / transposer / replicator consume templates: **GD6**
- JEI gene-sample subtypes: **GD8**

## Player checks

1. Craft or `/give` sampler; place; open GUI (three-input layout)
2. Feed FE + individual + blank gene sample + labware → filled gene sample in output; tooltip shows allele
3. Combine gene sample(s) with blank/filled genetic template in crafting table
4. Smelt filled sample/template → blank
5. Creative tab **Gene Samples** lists allele stacks
