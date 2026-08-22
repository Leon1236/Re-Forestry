# Apiculture JEI

CE 1.21.1 `ApicultureJeiPlugin` loops every `ISpeciesType` (bees, trees, butterflies). Re-Forestry matches that layout across three plugins.

## Wave 9 Stage 4 — tree + butterfly genetics JEI

| Plugin | Categories | Notes |
|---|---|---|
| `ApicultureJeiPlugin` | Bee products + mutations | Unchanged entry; bee-only descriptions (frames, suit, scoop) |
| `ArboricultureJeiPlugin` | Tree products + mutations + charcoal pile | Sapling / pollen_fertile subtypes |
| `LepidopterologyJeiPlugin` | Butterfly products + mutations | Module-gated; 4 GE item subtypes; scoop description |

Shared helpers live in `core.compat.jei.GeneticsJeiHelper`. Mutation JEI stacks apply `Mutation.specialAlleles` via `AbstractMutatronBlockEntity.createMutatedGenome` (same as mutatron). Gendustry mutatron click areas open **all** `MutationDisplay` recipe types (bee, tree, butterfly).

Recipe type ids: `reforestry:bee_species_products|_mutations`, `reforestry:tree_species_*`, `reforestry:butterfly_species_*`.

## Wave 9 Stage 3 — core genetics-tool JEI (cross-module)

`CoreJeiPlugin` (`reforestry:core`) ingredient info for items that touch the genetics workflow:

| Lang key | Item/block |
|---|---|
| `for.jei.description.analyzer` | Desk analyzer block |
| `for.jei.description.escritoire` | Escritoire block |
| `for.jei.description.portable_alyzer` | Portable alyzer item |
| `for.jei.description.pipette` | Pipette |
| `for.jei.description.wrench` | Wrench |

Also registered in Stage 3 (separate plugins): compost/mulch/fertilizer (`CoreJeiPlugin`); peat/biogas/clockwork engines (`EnergyJeiPlugin`); genetic filter (`SortingJeiPlugin`); Extra Bees frames/alveary/ectoplasm/hives (`ExtraBeesJeiPlugin`); all 10 gendustry machines + upgrade groups (`GendustryJeiPlugin`). Entrypoints in `fabric.mod.json` → `jei_mod_plugin`.

## 26.2 JEI draw API

- Categories extend `ForestryRecipeCategory` and draw with `GuiGraphicsExtractor` (not NeoForge `GuiGraphics`).
- Slots use `IRecipeSlotBuilder.add(...)` / `addItemStacks(...)` (not deprecated `addItemStack`).
- Recipe types: `IRecipeType.create` with CE ids `reforestry:bee_species_products` / `bee_species_mutations` (`ForestrySpeciesTypes.BEE` + `_products`/`_mutations`).
- Analyzed stacks set `CoreDataComponents.ANALYZED` true (no `GeneticItemHelper.analyze`, which needs a Player).
- Item subtypes: active species id from `ApicultureDataComponents.BEE_GENOME` + `BeeChromosomes.SPECIES`. Creative frame: `ItemCreativeHiveFrame.hasForceMutations` (CUSTOM_DATA `force_mutations`) so JEI shows base vs max-mutation stacks separately.
- Mutation result uses `AbstractMutatronBlockEntity.createMutatedGenome` so `Mutation.specialAlleles` appear in JEI (Wave 9 Stage 4).
- Mutation condition tooltips use CE keys `for.mutation.condition.*` with arguments (biome tag, block name, `reforestry.date.month.N`).
