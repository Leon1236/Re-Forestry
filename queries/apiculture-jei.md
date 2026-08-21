# Apiculture JEI (bees only)

CE 1.21.1 `ApicultureJeiPlugin` loops every `ISpeciesType` (bees, trees, butterflies). This stage registers **bee products and bee mutations only**. Tree/butterfly JEI stays deferred.

## 26.2 JEI draw API

- Categories extend `ForestryRecipeCategory` and draw with `GuiGraphicsExtractor` (not NeoForge `GuiGraphics`).
- Slots use `IRecipeSlotBuilder.add(...)` / `addItemStacks(...)` (not deprecated `addItemStack`).
- Recipe types: `IRecipeType.create` with CE ids `reforestry:bee_species_products` / `bee_species_mutations` (`ForestrySpeciesTypes.BEE` + `_products`/`_mutations`).
- Analyzed stacks set `CoreDataComponents.ANALYZED` true (no `GeneticItemHelper.analyze`, which needs a Player).
- Item subtypes: active species id from `ApicultureDataComponents.BEE_GENOME` + `BeeChromosomes.SPECIES`. Creative frame: `ItemCreativeHiveFrame.hasForceMutations` (CUSTOM_DATA `force_mutations`) so JEI shows base vs max-mutation stacks separately.
- Mutation result uses the species default genome; local `Mutation.specialAlleles` are not applied in the JEI queen display.
- Mutation condition tooltips use CE keys `for.mutation.condition.*` with arguments (biome tag, block name, `reforestry.date.month.N`).
