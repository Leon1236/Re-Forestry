# thedarkcolour-gendustry

## Directory structure

```text
thedarkcolour-gendustry/
├── .github/
│   ├── workflows/
│   │   └── publish.yml
│   ├── FUNDING.yml
│   └── ISSUE_TEMPLATE.md
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── generated/
│   │   └── resources/
│   │       ├── .cache/
│   │       │   ├── 02a388ee54758b79d36587ca49e043c0c05fc129
│   │       │   ├── 17bf486417190b9b075888bd20665b89993b9d5d
│   │       │   ├── 30d760365f09e476af034a38b0c7a340c0c31694
│   │       │   ├── 59eb3dbb5f86130e09b3c62d89b9525ee01cf52d
│   │       │   ├── 9fb1092f32d4fcbf9e061ffd718d4ec689c6c95e
│   │       │   ├── bf931954ab5a321ad31e445dffa32b91605b826b
│   │       │   └── e45428b82fb5616b4a68a8071643f387f24f965a
│   │       ├── assets/
│   │       │   └── gendustry/
│   │       │       ├── blockstates/
│   │       │       │   ├── advanced_mutatron.json
│   │       │       │   ├── dna_extractor.json
│   │       │       │   ├── fluid_liquid_dna.json
│   │       │       │   ├── fluid_mutagen.json
│   │       │       │   ├── fluid_protein.json
│   │       │       │   ├── genetic_transposer.json
│   │       │       │   ├── imprinter.json
│   │       │       │   ├── industrial_apiary.json
│   │       │       │   ├── mutagen_producer.json
│   │       │       │   ├── mutatron.json
│   │       │       │   ├── protein_liquefier.json
│   │       │       │   ├── replicator.json
│   │       │       │   └── sampler.json
│   │       │       ├── lang/
│   │       │       │   └── en_us.json
│   │       │       └── models/
│   │       │           ├── block/
│   │       │           │   ├── advanced_mutatron.json
│   │       │           │   ├── dna_extractor.json
│   │       │           │   ├── fluid_liquid_dna.json
│   │       │           │   ├── fluid_mutagen.json
│   │       │           │   ├── fluid_protein.json
│   │       │           │   ├── genetic_transposer.json
│   │       │           │   ├── imprinter.json
│   │       │           │   ├── industrial_apiary.json
│   │       │           │   ├── mutagen_producer.json
│   │       │           │   ├── mutatron.json
│   │       │           │   ├── protein_liquefier.json
│   │       │           │   ├── replicator.json
│   │       │           │   └── sampler.json
│   │       │           └── item/
│   │       │               ├── activity_simulator_elite_upgrade.json
│   │       │               ├── advanced_mutatron.json
│   │       │               ├── automation_upgrade.json
│   │       │               ├── blank_gene_sample.json
│   │       │               ├── blank_genetic_template.json
│   │       │               ├── bucket_liquid_dna.json
│   │       │               ├── bucket_mutagen.json
│   │       │               ├── bucket_protein.json
│   │       │               ├── climate_control_module.json
│   │       │               ├── cooler_upgrade.json
│   │       │               ├── dna_extractor.json
│   │       │               ├── dryer_upgrade.json
│   │       │               ├── elite_upgrade_frame.json
│   │       │               ├── environmental_processor.json
│   │       │               ├── fertility_elite_upgrade.json
│   │       │               ├── gene_sample.json
│   │       │               ├── genetic_template.json
│   │       │               ├── genetic_transposer.json
│   │       │               ├── genetics_processor.json
│   │       │               ├── heater_upgrade.json
│   │       │               ├── humidifier_upgrade.json
│   │       │               ├── immutable_upgrade.json
│   │       │               ├── imprinter.json
│   │       │               ├── industrial_apiary.json
│   │       │               ├── labware.json
│   │       │               ├── lifespan_upgrade.json
│   │       │               ├── lighting_upgrade.json
│   │       │               ├── mutagen_producer.json
│   │       │               ├── mutation_elite_upgrade.json
│   │       │               ├── mutatron.json
│   │       │               ├── nether_upgrade.json
│   │       │               ├── pollen_kit.json
│   │       │               ├── pollination_upgrade.json
│   │       │               ├── power_module.json
│   │       │               ├── productivity_elite_upgrade.json
│   │       │               ├── productivity_upgrade.json
│   │       │               ├── protein_liquefier.json
│   │       │               ├── receptacle.json
│   │       │               ├── replicator.json
│   │       │               ├── sampler.json
│   │       │               ├── scrubber_upgrade.json
│   │       │               ├── sieve_upgrade.json
│   │       │               ├── sky_upgrade.json
│   │       │               ├── stabilizer_upgrade.json
│   │       │               ├── territory_elite_upgrade.json
│   │       │               ├── territory_upgrade.json
│   │       │               ├── upgrade_frame.json
│   │       │               ├── weatherproof_upgrade.json
│   │       │               └── youth_elite_upgrade.json
│   │       └── data/
│   │           ├── gendustry/
│   │           │   ├── advancements/
│   │           │   │   └── recipes/
│   │           │   │       ├── misc/
│   │           │   │       │   ├── activity_simulator_elite_upgrade.json
│   │           │   │       │   ├── advanced_mutatron.json
│   │           │   │       │   ├── automation_upgrade.json
│   │           │   │       │   ├── blank_gene_sample.json
│   │           │   │       │   ├── blank_genetic_template.json
│   │           │   │       │   ├── climate_control_module.json
│   │           │   │       │   ├── cooler_upgrade.json
│   │           │   │       │   ├── dna_extractor.json
│   │           │   │       │   ├── dryer_upgrade.json
│   │           │   │       │   ├── elite_upgrade_frame.json
│   │           │   │       │   ├── environmental_processor.json
│   │           │   │       │   ├── fertility_elite_upgrade.json
│   │           │   │       │   ├── genetic_transposer.json
│   │           │   │       │   ├── genetics_processor.json
│   │           │   │       │   ├── heater_upgrade.json
│   │           │   │       │   ├── humidifier_upgrade.json
│   │           │   │       │   ├── immutable_upgrade.json
│   │           │   │       │   ├── imprinter.json
│   │           │   │       │   ├── industrial_apiary.json
│   │           │   │       │   ├── labware.json
│   │           │   │       │   ├── lifespan_upgrade.json
│   │           │   │       │   ├── lighting_upgrade.json
│   │           │   │       │   ├── mutagen_producer.json
│   │           │   │       │   ├── mutation_elite_upgrade.json
│   │           │   │       │   ├── mutatron.json
│   │           │   │       │   ├── nether_upgrade.json
│   │           │   │       │   ├── pollination_upgrade.json
│   │           │   │       │   ├── power_module.json
│   │           │   │       │   ├── productivity_elite_upgrade.json
│   │           │   │       │   ├── productivity_upgrade.json
│   │           │   │       │   ├── protein_liquefier.json
│   │           │   │       │   ├── receptacle.json
│   │           │   │       │   ├── replicator.json
│   │           │   │       │   ├── sampler.json
│   │           │   │       │   ├── scrubber_upgrade.json
│   │           │   │       │   ├── sieve_upgrade.json
│   │           │   │       │   ├── sky_upgrade.json
│   │           │   │       │   ├── stabilizer_upgrade.json
│   │           │   │       │   ├── territory_elite_upgrade.json
│   │           │   │       │   ├── territory_upgrade.json
│   │           │   │       │   ├── upgrade_frame.json
│   │           │   │       │   ├── weatherproof_upgrade.json
│   │           │   │       │   └── youth_elite_upgrade.json
│   │           │   │       └── tools/
│   │           │   │           └── pollen_kit.json
│   │           │   ├── loot_tables/
│   │           │   │   └── blocks/
│   │           │   │       ├── advanced_mutatron.json
│   │           │   │       ├── dna_extractor.json
│   │           │   │       ├── genetic_transposer.json
│   │           │   │       ├── imprinter.json
│   │           │   │       ├── industrial_apiary.json
│   │           │   │       ├── mutagen_producer.json
│   │           │   │       ├── mutatron.json
│   │           │   │       ├── protein_liquefier.json
│   │           │   │       ├── replicator.json
│   │           │   │       └── sampler.json
│   │           │   ├── recipes/
│   │           │   │   ├── dna/
│   │           │   │   │   ├── butterfly.json
│   │           │   │   │   ├── caterpillar.json
│   │           │   │   │   ├── cocoon.json
│   │           │   │   │   ├── drone.json
│   │           │   │   │   ├── larvae.json
│   │           │   │   │   ├── pollen.json
│   │           │   │   │   ├── princess.json
│   │           │   │   │   ├── queen.json
│   │           │   │   │   ├── sapling.json
│   │           │   │   │   └── serum.json
│   │           │   │   ├── mutagen/
│   │           │   │   │   ├── glowstone.json
│   │           │   │   │   ├── glowstone_dust.json
│   │           │   │   │   ├── redstone.json
│   │           │   │   │   └── redstone_block.json
│   │           │   │   ├── protein/
│   │           │   │   │   ├── beef.json
│   │           │   │   │   ├── cod.json
│   │           │   │   │   ├── porkchop.json
│   │           │   │   │   ├── pufferfish.json
│   │           │   │   │   ├── rabbit.json
│   │           │   │   │   ├── salmon.json
│   │           │   │   │   └── tropical_fish.json
│   │           │   │   ├── activity_simulator_elite_upgrade.json
│   │           │   │   ├── advanced_mutatron.json
│   │           │   │   ├── automation_upgrade.json
│   │           │   │   ├── blank_gene_sample.json
│   │           │   │   ├── blank_gene_sample_wipe_dna.json
│   │           │   │   ├── blank_genetic_template.json
│   │           │   │   ├── blank_genetic_template_wipe_dna.json
│   │           │   │   ├── climate_control_module.json
│   │           │   │   ├── combine_genetic_template.json
│   │           │   │   ├── cooler_upgrade.json
│   │           │   │   ├── dna_extractor.json
│   │           │   │   ├── dryer_upgrade.json
│   │           │   │   ├── elite_upgrade_frame.json
│   │           │   │   ├── environmental_processor.json
│   │           │   │   ├── fertility_elite_upgrade.json
│   │           │   │   ├── genetic_transposer.json
│   │           │   │   ├── genetics_processor.json
│   │           │   │   ├── heater_upgrade.json
│   │           │   │   ├── humidifier_upgrade.json
│   │           │   │   ├── immutable_upgrade.json
│   │           │   │   ├── imprinter.json
│   │           │   │   ├── industrial_apiary.json
│   │           │   │   ├── labware.json
│   │           │   │   ├── lifespan_upgrade.json
│   │           │   │   ├── lighting_upgrade.json
│   │           │   │   ├── mutagen_producer.json
│   │           │   │   ├── mutation_elite_upgrade.json
│   │           │   │   ├── mutatron.json
│   │           │   │   ├── nether_upgrade.json
│   │           │   │   ├── pollen_kit.json
│   │           │   │   ├── pollination_upgrade.json
│   │           │   │   ├── power_module.json
│   │           │   │   ├── productivity_elite_upgrade.json
│   │           │   │   ├── productivity_upgrade.json
│   │           │   │   ├── protein_liquefier.json
│   │           │   │   ├── receptacle.json
│   │           │   │   ├── replicator.json
│   │           │   │   ├── sampler.json
│   │           │   │   ├── scrubber_upgrade.json
│   │           │   │   ├── sieve_upgrade.json
│   │           │   │   ├── sky_upgrade.json
│   │           │   │   ├── stabilizer_upgrade.json
│   │           │   │   ├── territory_elite_upgrade.json
│   │           │   │   ├── territory_upgrade.json
│   │           │   │   ├── upgrade_frame.json
│   │           │   │   ├── weatherproof_upgrade.json
│   │           │   │   └── youth_elite_upgrade.json
│   │           │   └── tags/
│   │           │       └── items/
│   │           │           └── upgrades.json
│   │           └── minecraft/
│   │               └── tags/
│   │                   └── blocks/
│   │                       └── mineable/
│   │                           └── pickaxe.json
│   └── main/
│       ├── java/
│       │   └── thedarkcolour/
│       │       └── gendustry/
│       │           ├── api/
│       │           │   ├── GendustryTags.java
│       │           │   └── package-info.java
│       │           ├── block/
│       │           │   ├── GendustryMachineBlock.java
│       │           │   ├── GendustryMachineType.java
│       │           │   └── package-info.java
│       │           ├── blockentity/
│       │           │   ├── AbstractMutatronBlockEntity.java
│       │           │   ├── AdvancedMutatronBlockEntity.java
│       │           │   ├── ApiaryModifiers.java
│       │           │   ├── DnaExtractorBlockEntity.java
│       │           │   ├── GeneticTransposerBlockEntity.java
│       │           │   ├── GeneticTransposerInventory.java
│       │           │   ├── IHintTile.java
│       │           │   ├── ImprinterBlockEntity.java
│       │           │   ├── ImprinterInventory.java
│       │           │   ├── IndustrialApiaryBeeModifier.java
│       │           │   ├── IndustrialApiaryBlockEntity.java
│       │           │   ├── IndustrialApiaryInventory.java
│       │           │   ├── MutagenProducerBlockEntity.java
│       │           │   ├── MutatronBlockEntity.java
│       │           │   ├── MutatronInventory.java
│       │           │   ├── package-info.java
│       │           │   ├── PoweredTankBlockEntity.java
│       │           │   ├── ProducerBlockEntity.java
│       │           │   ├── ProducerInventory.java
│       │           │   ├── ProteinLiquefierBlockEntity.java
│       │           │   ├── ReplicatorBlockEntity.java
│       │           │   ├── ReplicatorInventory.java
│       │           │   ├── SamplerBlockEntity.java
│       │           │   └── SamplerInventory.java
│       │           ├── client/
│       │           │   ├── screen/
│       │           │   │   ├── AbstractMutatronScreen.java
│       │           │   │   ├── AdvancedMutatronScreen.java
│       │           │   │   ├── IndustrialApiaryScreen.java
│       │           │   │   ├── MutatronScreen.java
│       │           │   │   ├── package-info.java
│       │           │   │   ├── ProducerScreen.java
│       │           │   │   ├── ReplicatorScreen.java
│       │           │   │   └── ThreeInputScreen.java
│       │           │   ├── ClientHandler.java
│       │           │   └── package-info.java
│       │           ├── compat/
│       │           │   ├── forestry/
│       │           │   │   ├── GendustryError.java
│       │           │   │   ├── GendustryForestryPlugin.java
│       │           │   │   └── package-info.java
│       │           │   └── jei/
│       │           │       ├── producers/
│       │           │       │   ├── DNAExtractorRecipeCategory.java
│       │           │       │   ├── MutagenRecipeCategory.java
│       │           │       │   ├── package-info.java
│       │           │       │   ├── ProducerGuiContainerHandler.java
│       │           │       │   ├── ProducerRecipeCategory.java
│       │           │       │   └── ProteinProducerRecipeCategory.java
│       │           │       ├── GendustryJeiPlugin.java
│       │           │       ├── GendustryRecipeType.java
│       │           │       ├── GeneSampleInterpreter.java
│       │           │       └── package-info.java
│       │           ├── data/
│       │           │   ├── BlockLoot.java
│       │           │   ├── BlockModels.java
│       │           │   ├── Data.java
│       │           │   ├── English.java
│       │           │   ├── LootProvider.java
│       │           │   ├── ModTags.java
│       │           │   ├── package-info.java
│       │           │   ├── Recipes.java
│       │           │   └── TranslationKeys.java
│       │           ├── item/
│       │           │   ├── data/
│       │           │   │   ├── GeneSampleInfo.java
│       │           │   │   └── package-info.java
│       │           │   ├── DebugWand.java
│       │           │   ├── EliteGendustryUpgradeType.java
│       │           │   ├── GendustryResourceType.java
│       │           │   ├── GendustryUpgradeItem.java
│       │           │   ├── GendustryUpgradeType.java
│       │           │   ├── GeneSampleItem.java
│       │           │   ├── GeneticTemplateItem.java
│       │           │   ├── IGendustryUpgradeType.java
│       │           │   ├── package-info.java
│       │           │   ├── PollenKitItem.java
│       │           │   └── SpeciesTypeItem.java
│       │           ├── menu/
│       │           │   ├── AbstractMutatronMenu.java
│       │           │   ├── AdvancedMutatronMenu.java
│       │           │   ├── IndustrialApiaryMenu.java
│       │           │   ├── MutatronMenu.java
│       │           │   ├── package-info.java
│       │           │   ├── ProducerMenu.java
│       │           │   ├── ReplicatorMenu.java
│       │           │   └── ThreeInputMenu.java
│       │           ├── recipe/
│       │           │   ├── cache/
│       │           │   │   ├── DnaRecipeCache.java
│       │           │   │   ├── IRecipeCache.java
│       │           │   │   ├── MutagenRecipeCache.java
│       │           │   │   ├── package-info.java
│       │           │   │   ├── ProteinRecipeCache.java
│       │           │   │   └── RecipeCacheRegistry.java
│       │           │   ├── DnaFinishedRecipe.java
│       │           │   ├── DnaRecipe.java
│       │           │   ├── GeneticTemplateRecipe.java
│       │           │   ├── MutagenFinishedRecipe.java
│       │           │   ├── MutagenRecipe.java
│       │           │   ├── package-info.java
│       │           │   ├── ProcessorRecipe.java
│       │           │   ├── ProteinFinishedRecipe.java
│       │           │   └── ProteinRecipe.java
│       │           ├── registry/
│       │           │   ├── GBlockEntities.java
│       │           │   ├── GBlocks.java
│       │           │   ├── GCreativeTabs.java
│       │           │   ├── GFluids.java
│       │           │   ├── GItems.java
│       │           │   ├── GMenus.java
│       │           │   ├── GRecipeTypes.java
│       │           │   └── package-info.java
│       │           ├── Gendustry.java
│       │           ├── GendustryModule.java
│       │           └── package-info.java
│       ├── resources/
│       │   ├── assets/
│       │   │   └── gendustry/
│       │   │       ├── lang/
│       │   │       │   ├── es_mx.json
│       │   │       │   ├── ja_jp.json
│       │   │       │   ├── ru_ru.json
│       │   │       │   └── zh_cn.json
│       │   │       └── textures/
│       │   │           ├── block/
│       │   │           │   ├── liquid/
│       │   │           │   │   ├── liquid_dna_flowing.png
│       │   │           │   │   ├── liquid_dna_flowing.png.mcmeta
│       │   │           │   │   ├── liquid_dna_still.png
│       │   │           │   │   ├── liquid_dna_still.png.mcmeta
│       │   │           │   │   ├── mutagen_flowing.png
│       │   │           │   │   ├── mutagen_flowing.png.mcmeta
│       │   │           │   │   ├── mutagen_still.png
│       │   │           │   │   ├── mutagen_still.png.mcmeta
│       │   │           │   │   ├── protein_flowing.png
│       │   │           │   │   ├── protein_flowing.png.mcmeta
│       │   │           │   │   ├── protein_still.png
│       │   │           │   │   └── protein_still.png.mcmeta
│       │   │           │   ├── advanced_mutatron_bottom.png
│       │   │           │   ├── advanced_mutatron_side.png
│       │   │           │   ├── advanced_mutatron_top.png
│       │   │           │   ├── dna_extractor_bottom.png
│       │   │           │   ├── dna_extractor_side.png
│       │   │           │   ├── dna_extractor_top.png
│       │   │           │   ├── genetic_transposer_bottom.png
│       │   │           │   ├── genetic_transposer_side.png
│       │   │           │   ├── genetic_transposer_top.png
│       │   │           │   ├── imprinter_bottom.png
│       │   │           │   ├── imprinter_side.png
│       │   │           │   ├── imprinter_top.png
│       │   │           │   ├── industrial_apiary_bottom.png
│       │   │           │   ├── industrial_apiary_side.png
│       │   │           │   ├── industrial_apiary_top.png
│       │   │           │   ├── mutagen_producer_bottom.png
│       │   │           │   ├── mutagen_producer_side.png
│       │   │           │   ├── mutagen_producer_top.png
│       │   │           │   ├── mutatron_bottom.png
│       │   │           │   ├── mutatron_side.png
│       │   │           │   ├── mutatron_top.png
│       │   │           │   ├── protein_liquefier_bottom.png
│       │   │           │   ├── protein_liquefier_side.png
│       │   │           │   ├── protein_liquefier_top.png
│       │   │           │   ├── replicator_bottom.png
│       │   │           │   ├── replicator_side.png
│       │   │           │   ├── replicator_top.png
│       │   │           │   ├── sampler_bottom.png
│       │   │           │   ├── sampler_side.png
│       │   │           │   └── sampler_top.png
│       │   │           ├── forestry/
│       │   │           │   └── atlas/
│       │   │           │       └── gui/
│       │   │           │           └── errors/
│       │   │           │               ├── incompatible_species.png
│       │   │           │               ├── no_blank.png
│       │   │           │               ├── no_dna.png
│       │   │           │               ├── no_labware.png
│       │   │           │               ├── no_mates.png
│       │   │           │               ├── no_mutagen.png
│       │   │           │               ├── no_mutations.png
│       │   │           │               ├── no_protein.png
│       │   │           │               ├── no_samples.png
│       │   │           │               ├── no_selection.png
│       │   │           │               ├── no_source.png
│       │   │           │               └── no_template.png
│       │   │           ├── gui/
│       │   │           │   ├── advanced_mutatron.png
│       │   │           │   ├── apiary.png
│       │   │           │   ├── extractor.png
│       │   │           │   ├── imprinter.png
│       │   │           │   ├── liquifier.png
│       │   │           │   ├── mutatron.png
│       │   │           │   ├── mutatron_advanced.png
│       │   │           │   ├── processor.png
│       │   │           │   ├── replicator.png
│       │   │           │   ├── sampler.png
│       │   │           │   └── transposer.png
│       │   │           └── item/
│       │   │               ├── activity_simulator_elite_upgrade.png
│       │   │               ├── automation_upgrade.png
│       │   │               ├── blank_gene_sample.png
│       │   │               ├── blank_genetic_template.png
│       │   │               ├── bucket_liquid_dna.png
│       │   │               ├── bucket_mutagen.png
│       │   │               ├── bucket_protein.png
│       │   │               ├── climate_control_module.png
│       │   │               ├── cooler_upgrade.png
│       │   │               ├── dryer_upgrade.png
│       │   │               ├── elite_upgrade_frame.png
│       │   │               ├── environmental_processor.png
│       │   │               ├── fertility_elite_upgrade.png
│       │   │               ├── gene_sample.png
│       │   │               ├── genetic_template.png
│       │   │               ├── genetics_processor.png
│       │   │               ├── heater_upgrade.png
│       │   │               ├── humidifier_upgrade.png
│       │   │               ├── immutable_upgrade.png
│       │   │               ├── labware.png
│       │   │               ├── lifespan_upgrade.png
│       │   │               ├── lighting_upgrade.png
│       │   │               ├── mutation_elite_upgrade.png
│       │   │               ├── nether_upgrade.png
│       │   │               ├── pollen_kit.png
│       │   │               ├── pollination_upgrade.png
│       │   │               ├── power_module.png
│       │   │               ├── productivity_elite_upgrade.png
│       │   │               ├── productivity_upgrade.png
│       │   │               ├── receptacle.png
│       │   │               ├── scrubber_upgrade.png
│       │   │               ├── sieve_upgrade.png
│       │   │               ├── sky_upgrade.png
│       │   │               ├── stabilizer_upgrade.png
│       │   │               ├── territory_elite_upgrade.png
│       │   │               ├── territory_upgrade.png
│       │   │               ├── upgrade_frame.png
│       │   │               ├── weatherproof_upgrade.png
│       │   │               └── youth_elite_upgrade.png
│       │   ├── META-INF/
│       │   │   └── services/
│       │   │       └── forestry.api.plugin.IForestryPlugin
│       │   ├── gpl-3.0.txt
│       │   └── pack.mcmeta
│       └── templates/
│           └── META-INF/
│               └── mods.toml
├── .editorconfig
├── .gitignore
├── build.gradle
├── changelog.md
├── formatting-settings.jar
├── gradle.properties
├── gradlew
├── gradlew.bat
├── LICENSE.md
├── logo.png
└── settings.gradle
```

## File contents

### .editorconfig

```ini
# https://editorconfig.org/
root = true

[*]
end_of_line = lf
insert_final_newline = true

[*.{java,gradle}]
indent_style = tab
indent_size = 4
charset = utf-8

[*.{json,yml}]
indent_style = space
indent_size = 2
charset = utf-8
```

### .github/FUNDING.yml

```yaml
github: thedarkcolour
patreon: thedarkcolour
ko_fi: thedarkcolour
```

### .github/ISSUE_TEMPLATE.md

```markdown
Thank you for reporting!

For issues:
 * Try the latest version, it may have fixed the issue already.
 * If you crashed, please paste the crash log to [gist](https://gist.github.com/) and link it here.
```

### .github/workflows/publish.yml

```yaml
name: Release for 1.20.1

on:
  push:
    branches: [ '1.20.1' ]
  workflow_dispatch:

jobs:
  release:
    name: Publish release JAR for Gendustry
    runs-on: ubuntu-latest
    if: "startsWith(github.event.head_commit.message, '[Release]')"
    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Setup Java
        uses: actions/setup-java@v4
        with:
          distribution: 'microsoft'
          java-version: '17'

      - name: Setup Gradle
        uses: gradle/actions/setup-gradle@v4

      - name: Upload to CurseForge
        run: ./gradlew curseforge
        env:
          CURSEFORGE_TOKEN: ${{ secrets.CURSEFORGE_TOKEN }}

      - name: Upload to Modrinth
        run: ./gradlew modrinth
        env:
          MODRINTH_TOKEN: ${{ secrets.MODRINTH_TOKEN }}
```

### .gitignore

```text
/bin
/out
/build
/run
/deps/run
/.idea
/eclipse
/.gradle
/*.iml
/*.ipr
```

### build.gradle

```groovy
plugins {
	id('idea')
	id('maven-publish')
	id('com.modrinth.minotaur') version '2.+'
	id('com.matthewprenger.cursegradle') version '1.4.0'
	id 'org.jetbrains.gradle.plugin.idea-ext' version '1.3'
	id 'net.neoforged.moddev.legacyforge' version '2.0.80'
}

group = "thedarkcolour.gendustry"
version = gendustryVersion
base.archivesName = "gendustry-$minecraftVersion"

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

legacyForge {
	version = "1.20.1-$forgeVersion"

	//accessTransformers = files('src/main/resources/META-INF/accesstransformer.cfg')
	// Set to false because Forge has broken access transformers
	//validateAccessTransformers = true

	// Add Parchment parameter mappings
	parchment {
		mappingsVersion = project.mappingsVersion
		minecraftVersion = project.minecraftVersion
	}

	runs {
		configureEach {
			logLevel = org.slf4j.event.Level.DEBUG
			jvmArgument '-XX:+AllowEnhancedClassRedefinition'
			jvmArgument '-XX:+IgnoreUnrecognizedVMOptions'
		}

		client {
			client()
		}

		server {
			server()
			gameDirectory.set(project.layout.projectDirectory.dir('run/server'))
			programArgument '--nogui'
		}

		data {
			data()
			programArguments.addAll('--mod', 'gendustry', '--all', '--output', file('src/generated/resources/').absolutePath, '--existing', file('src/main/resources/').absolutePath)
		}
	}

	mods {
		gendustry {
			sourceSet sourceSets.main
		}
	}
}

repositories {
	maven {
		name = 'jei'
		url = 'https://maven.blamejared.com/'
		content { includeGroup "mezz.jei" }
	}
	maven {
		name = "Patchouli"
		url = "https://maven.blamejared.com/"
	}
	maven {
		url "https://cursemaven.com"
		content {
			includeGroup "curse.maven"
		}
	}
	maven {
		name = 'ModKit'
		url 'https://jitpack.io'
		content { includeGroup 'com.github.thedarkcolour' }
	}
	maven {
		name = 'Curios'
		url = 'https://maven.theillusivec4.top/'
		content { includeGroup 'top.theillusivec4.curios' }
	}
	exclusiveContent {
		forRepository {
			maven {
				name = "Modrinth"
				url = "https://api.modrinth.com/maven"
			}
		}
		filter {
			includeGroup "maven.modrinth"
		}
	}
}

dependencies {
	// JEI OPTIONAL
	modCompileOnly "mezz.jei:jei-$minecraftVersion-common-api:$jeiVersion"
	modCompileOnly "mezz.jei:jei-$minecraftVersion-forge-api:$jeiVersion"
	modRuntimeOnly "mezz.jei:jei-$minecraftVersion-forge:$jeiVersion"
	// PATCHOULI REQUIRED
	modCompileOnly "vazkii.patchouli:Patchouli:$patchouliVersion-FORGE:api"
	modRuntimeOnly "vazkii.patchouli:Patchouli:$patchouliVersion-FORGE"
	// ModKit DEV ONLY
	modImplementation 'com.github.thedarkcolour:ModKit:cc8f1c11ec'
	// FORESTRY REQUIRED
	modImplementation "maven.modrinth:forestry-community-edition:$forestryVersion"
	// Curios Optional
	modImplementation "top.theillusivec4.curios:curios-forge:${curiosVersion}+${minecraftVersion}"

	modImplementation 'maven.modrinth:cofh-core:11.0.2'
	modImplementation 'maven.modrinth:thermal-foundation:11.0.6'
	modImplementation 'maven.modrinth:thermal-dynamics:11.0.1'

	// DEV ONLY
	compileOnly 'org.jetbrains:annotations:23.0.0'
}

// Include resources generated by data generators.
sourceSets.main.resources { srcDir 'src/generated/resources' }

var generateModMetadata = tasks.register('generateModMetadata', ProcessResources) {
	var replaceProperties = [
			'version'              : project.version,
			'forgeVersionRange'    : forgeVersionRange,
			'jeiVersionRange'      : jeiVersionRange,
			'patchouliVersionRange': patchouliVersionRange,
			'fmlVersionRange'      : fmlVersionRange,
			'forestryVersionRange' : forestryVersionRange,
	]
	inputs.properties replaceProperties
	expand replaceProperties

	from 'src/main/templates'
	into 'build/generated/sources/modMetadata'
}
sourceSets.main.resources.srcDir generateModMetadata

idea.project.settings {
	taskTriggers {
		afterSync generateModMetadata
	}
}

tasks.withType(JavaCompile).configureEach {
	options.encoding = 'UTF-8'
}

idea {
	module {
		downloadSources = true
		downloadJavadoc = true
	}
}

if (System.getenv('CURSEFORGE_TOKEN')) {
	curseforge {
		apiKey = System.getenv('CURSEFORGE_TOKEN')

		project {
			id = project.curseforgeId
			changelogType = 'markdown'
			changelog = getChangelog(version)

			releaseType = 'release'

			addGameVersion(project.minecraftVersion)
			addGameVersion('Forge')
			addGameVersion('Java 17')

			mainArtifact(reobfJar.archiveFile) {
				displayName = "Gendustry: Community Edition ${project.version}"
				relations {
					requiredDependency 'forestry-community-edition'
				}
			}
		}
	}
}

modrinth {
	token = System.getenv('MODRINTH_TOKEN')
	projectId = project.modrinthId
	versionName = "Gendustry: Community Edition $version"
	versionNumber = version.toString()
	versionType = 'release'
	gameVersions = [minecraftVersion]
	loaders = ['forge']
	changelog = getChangelog(project.version)

	uploadFile = reobfJar

	dependencies {
		required.project('forestry-community-edition')
	}
}

static def getChangelog(Object version) {
	version = version.toString()

	def file = new File('changelog.md')
	if (!file.exists()) {
		return 'Changelog file not found'
	}

	// Relies on the changelog block being "##blahblahblah_VERSION" where _ is a space
	def content = file.text.normalize().split("##.* ")

	for (final def chunk in content) {
		if (chunk.isEmpty()) continue

		def lineTerminatorIndex = chunk.findIndexOf { c -> c == '\n' || c == '\r' }
		def versionString = chunk.substring(0, lineTerminatorIndex)

		if (versionString == version) {
			return "## Gendustry $version\n" + chunk.substring(lineTerminatorIndex + 1)
		}
	}

	// Fallback in case this fails
	return "Gendustry Update ${version}"
}
```

### changelog.md

```markdown
## Gendustry 1.0.5
- Fixed incorrect tooltip for Sieve Upgrade (#3)
- Fix not being able to extract items from Imprinter (#3)
- Added Immutable Upgrade to prevent mutations in the Industrial Apiary (#9)
- Add Russian translation, thanks to deaddiesel (#12)

## Gendustry 1.0.4
- Fixed incorrect tooltip for Advanced Mutatron hint
- Added Spanish translations
- Added Chinese translation, thanks to WolfGenerals (#8)
- Added Japanes translation, thanks to code-onigiri (#10)
- Allow using incomplete genetic templates in the Imprinter (#5)

## Gendustry 1.0.3
- Fixed Advanced Mutatron not allowing the first mutation to be selected (#6)

## Gendustry 1.0.2
- Improved JEI compatibility thanks to CrossVas (#1, #2)

## Gendustry 1.0.1
- Fix modded fluid pipes not connecting to machines.

## Gendustry 1.0.0
- Initial release. Thanks to Spearkiller for providing a Weatherproof texture
```

### gradle.properties

```properties
org.gradle.daemon=true
org.gradle.jvmargs=-Xmx3G
org.gradle.caching=true
org.gradle.parallel=true

curseforgeId=1179084
modrinthId=2zkSGMyK

# Gendustry
gendustryVersion=1.0.5

# Minecraft
minecraftVersion=1.20.1
mappingsVersion=2023.06.26
# Forge
forgeVersion=47.4.0
forgeVersionRange=[47.1.0,)
fmlVersionRange=[43,)
# JEI
jeiVersion=15.20.0.106
jeiVersionRange=[15,)
# Patchouli
patchouliVersion=1.20.1-84
patchouliVersionRange=[1.20.1-84,)
# Forestry
forestryVersion=2.10.0
forestryVersionRange=[2.3.0,)
# Curios
curiosVersion=5.14.1
```

### gradle/wrapper/gradle-wrapper.properties

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-9.2.1-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

### gradlew

```
#!/usr/bin/env bash

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn ( ) {
    echo "$*"
}

die ( ) {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MINGW* )
    msys=true
    ;;
esac

# For Cygwin, ensure paths are in UNIX format before anything is touched.
if $cygwin ; then
    [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
fi

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >&-
APP_HOME="`pwd -P`"
cd "$SAVED" >&-

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
if [ "$cygwin" = "false" -a "$darwin" = "false" ] ; then
    MAX_FD_LIMIT=`ulimit -H -n`
    if [ $? -eq 0 ] ; then
        if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ] ; then
            MAX_FD="$MAX_FD_LIMIT"
        fi
        ulimit -n $MAX_FD
        if [ $? -ne 0 ] ; then
            warn "Could not set maximum file descriptor limit: $MAX_FD"
        fi
    else
        warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
fi

# For Darwin, add options to specify how the application appears in the dock
if $darwin; then
    GRADLE_OPTS="$GRADLE_OPTS \"-Xdock:name=$APP_NAME\" \"-Xdock:icon=$APP_HOME/media/gradle.icns\""
fi

# For Cygwin, switch paths to Windows format before running java
if $cygwin ; then
    APP_HOME=`cygpath --path --mixed "$APP_HOME"`
    CLASSPATH=`cygpath --path --mixed "$CLASSPATH"`

    # We build the pattern for arguments to be converted via cygpath
    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`
    SEP=""
    for dir in $ROOTDIRSRAW ; do
        ROOTDIRS="$ROOTDIRS$SEP$dir"
        SEP="|"
    done
    OURCYGPATTERN="(^($ROOTDIRS))"
    # Add a user-defined pattern to the cygpath arguments
    if [ "$GRADLE_CYGPATTERN" != "" ] ; then
        OURCYGPATTERN="$OURCYGPATTERN|($GRADLE_CYGPATTERN)"
    fi
    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    i=0
    for arg in "$@" ; do
        CHECK=`echo "$arg"|egrep -c "$OURCYGPATTERN" -`
        CHECK2=`echo "$arg"|egrep -c "^-"`                                 ### Determine if an option

        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition
            eval `echo args$i`=`cygpath --path --ignore --mixed "$arg"`
        else
            eval `echo args$i`="\"$arg\""
        fi
        i=$((i+1))
    done
    case $i in
        (0) set -- ;;
        (1) set -- "$args0" ;;
        (2) set -- "$args0" "$args1" ;;
        (3) set -- "$args0" "$args1" "$args2" ;;
        (4) set -- "$args0" "$args1" "$args2" "$args3" ;;
        (5) set -- "$args0" "$args1" "$args2" "$args3" "$args4" ;;
        (6) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" ;;
        (7) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" ;;
        (8) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" ;;
        (9) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" "$args8" ;;
    esac
fi

# Split up the JVM_OPTS And GRADLE_OPTS values into an array, following the shell quoting and substitution rules
function splitJvmOpts() {
    JVM_OPTS=("$@")
}
eval splitJvmOpts $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS
JVM_OPTS[${#JVM_OPTS[*]}]="-Dorg.gradle.appname=$APP_BASE_NAME"

exec "$JAVACMD" "${JVM_OPTS[@]}" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
```

### gradlew.bat

```batch
@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
```

### LICENSE.md

```markdown
                    GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

                            Preamble

  The GNU General Public License is a free, copyleft license for
software and other kinds of works.

  The licenses for most software and other practical works are designed
to take away your freedom to share and change the works.  By contrast,
the GNU General Public License is intended to guarantee your freedom to
share and change all versions of a program--to make sure it remains free
software for all its users.  We, the Free Software Foundation, use the
GNU General Public License for most of our software; it applies also to
any other work released this way by its authors.  You can apply it to
your programs, too.

  When we speak of free software, we are referring to freedom, not
price.  Our General Public Licenses are designed to make sure that you
have the freedom to distribute copies of free software (and charge for
them if you wish), that you receive source code or can get it if you
want it, that you can change the software or use pieces of it in new
free programs, and that you know you can do these things.

  To protect your rights, we need to prevent others from denying you
these rights or asking you to surrender the rights.  Therefore, you have
certain responsibilities if you distribute copies of the software, or if
you modify it: responsibilities to respect the freedom of others.

  For example, if you distribute copies of such a program, whether
gratis or for a fee, you must pass on to the recipients the same
freedoms that you received.  You must make sure that they, too, receive
or can get the source code.  And you must show them these terms so they
know their rights.

  Developers that use the GNU GPL protect your rights with two steps:
(1) assert copyright on the software, and (2) offer you this License
giving you legal permission to copy, distribute and/or modify it.

  For the developers' and authors' protection, the GPL clearly explains
that there is no warranty for this free software.  For both users' and
authors' sake, the GPL requires that modified versions be marked as
changed, so that their problems will not be attributed erroneously to
authors of previous versions.

  Some devices are designed to deny users access to install or run
modified versions of the software inside them, although the manufacturer
can do so.  This is fundamentally incompatible with the aim of
protecting users' freedom to change the software.  The systematic
pattern of such abuse occurs in the area of products for individuals to
use, which is precisely where it is most unacceptable.  Therefore, we
have designed this version of the GPL to prohibit the practice for those
products.  If such problems arise substantially in other domains, we
stand ready to extend this provision to those domains in future versions
of the GPL, as needed to protect the freedom of users.

  Finally, every program is threatened constantly by software patents.
States should not allow patents to restrict development and use of
software on general-purpose computers, but in those that do, we wish to
avoid the special danger that patents applied to a free program could
make it effectively proprietary.  To prevent this, the GPL assures that
patents cannot be used to render the program non-free.

  The precise terms and conditions for copying, distribution and
modification follow.

                       TERMS AND CONDITIONS

  0. Definitions.

  "This License" refers to version 3 of the GNU General Public License.

  "Copyright" also means copyright-like laws that apply to other kinds of
works, such as semiconductor masks.

  "The Program" refers to any copyrightable work licensed under this
License.  Each licensee is addressed as "you".  "Licensees" and
"recipients" may be individuals or organizations.

  To "modify" a work means to copy from or adapt all or part of the work
in a fashion requiring copyright permission, other than the making of an
exact copy.  The resulting work is called a "modified version" of the
earlier work or a work "based on" the earlier work.

  A "covered work" means either the unmodified Program or a work based
on the Program.

  To "propagate" a work means to do anything with it that, without
permission, would make you directly or secondarily liable for
infringement under applicable copyright law, except executing it on a
computer or modifying a private copy.  Propagation includes copying,
distribution (with or without modification), making available to the
public, and in some countries other activities as well.

  To "convey" a work means any kind of propagation that enables other
parties to make or receive copies.  Mere interaction with a user through
a computer network, with no transfer of a copy, is not conveying.

  An interactive user interface displays "Appropriate Legal Notices"
to the extent that it includes a convenient and prominently visible
feature that (1) displays an appropriate copyright notice, and (2)
tells the user that there is no warranty for the work (except to the
extent that warranties are provided), that licensees may convey the
work under this License, and how to view a copy of this License.  If
the interface presents a list of user commands or options, such as a
menu, a prominent item in the list meets this criterion.

  1. Source Code.

  The "source code" for a work means the preferred form of the work
for making modifications to it.  "Object code" means any non-source
form of a work.

  A "Standard Interface" means an interface that either is an official
standard defined by a recognized standards body, or, in the case of
interfaces specified for a particular programming language, one that
is widely used among developers working in that language.

  The "System Libraries" of an executable work include anything, other
than the work as a whole, that (a) is included in the normal form of
packaging a Major Component, but which is not part of that Major
Component, and (b) serves only to enable use of the work with that
Major Component, or to implement a Standard Interface for which an
implementation is available to the public in source code form.  A
"Major Component", in this context, means a major essential component
(kernel, window system, and so on) of the specific operating system
(if any) on which the executable work runs, or a compiler used to
produce the work, or an object code interpreter used to run it.

  The "Corresponding Source" for a work in object code form means all
the source code needed to generate, install, and (for an executable
work) run the object code and to modify the work, including scripts to
control those activities.  However, it does not include the work's
System Libraries, or general-purpose tools or generally available free
programs which are used unmodified in performing those activities but
which are not part of the work.  For example, Corresponding Source
includes interface definition files associated with source files for
the work, and the source code for shared libraries and dynamically
linked subprograms that the work is specifically designed to require,
such as by intimate data communication or control flow between those
subprograms and other parts of the work.

  The Corresponding Source need not include anything that users
can regenerate automatically from other parts of the Corresponding
Source.

  The Corresponding Source for a work in source code form is that
same work.

  2. Basic Permissions.

  All rights granted under this License are granted for the term of
copyright on the Program, and are irrevocable provided the stated
conditions are met.  This License explicitly affirms your unlimited
permission to run the unmodified Program.  The output from running a
covered work is covered by this License only if the output, given its
content, constitutes a covered work.  This License acknowledges your
rights of fair use or other equivalent, as provided by copyright law.

  You may make, run and propagate covered works that you do not
convey, without conditions so long as your license otherwise remains
in force.  You may convey covered works to others for the sole purpose
of having them make modifications exclusively for you, or provide you
with facilities for running those works, provided that you comply with
the terms of this License in conveying all material for which you do
not control copyright.  Those thus making or running the covered works
for you must do so exclusively on your behalf, under your direction
and control, on terms that prohibit them from making any copies of
your copyrighted material outside their relationship with you.

  Conveying under any other circumstances is permitted solely under
the conditions stated below.  Sublicensing is not allowed; section 10
makes it unnecessary.

  3. Protecting Users' Legal Rights From Anti-Circumvention Law.

  No covered work shall be deemed part of an effective technological
measure under any applicable law fulfilling obligations under article
11 of the WIPO copyright treaty adopted on 20 December 1996, or
similar laws prohibiting or restricting circumvention of such
measures.

  When you convey a covered work, you waive any legal power to forbid
circumvention of technological measures to the extent such circumvention
is effected by exercising rights under this License with respect to
the covered work, and you disclaim any intention to limit operation or
modification of the work as a means of enforcing, against the work's
users, your or third parties' legal rights to forbid circumvention of
technological measures.

  4. Conveying Verbatim Copies.

  You may convey verbatim copies of the Program's source code as you
receive it, in any medium, provided that you conspicuously and
appropriately publish on each copy an appropriate copyright notice;
keep intact all notices stating that this License and any
non-permissive terms added in accord with section 7 apply to the code;
keep intact all notices of the absence of any warranty; and give all
recipients a copy of this License along with the Program.

  You may charge any price or no price for each copy that you convey,
and you may offer support or warranty protection for a fee.

  5. Conveying Modified Source Versions.

  You may convey a work based on the Program, or the modifications to
produce it from the Program, in the form of source code under the
terms of section 4, provided that you also meet all of these conditions:

    a) The work must carry prominent notices stating that you modified
    it, and giving a relevant date.

    b) The work must carry prominent notices stating that it is
    released under this License and any conditions added under section
    7.  This requirement modifies the requirement in section 4 to
    "keep intact all notices".

    c) You must license the entire work, as a whole, under this
    License to anyone who comes into possession of a copy.  This
    License will therefore apply, along with any applicable section 7
    additional terms, to the whole of the work, and all its parts,
    regardless of how they are packaged.  This License gives no
    permission to license the work in any other way, but it does not
    invalidate such permission if you have separately received it.

    d) If the work has interactive user interfaces, each must display
    Appropriate Legal Notices; however, if the Program has interactive
    interfaces that do not display Appropriate Legal Notices, your
    work need not make them do so.

  A compilation of a covered work with other separate and independent
works, which are not by their nature extensions of the covered work,
and which are not combined with it such as to form a larger program,
in or on a volume of a storage or distribution medium, is called an
"aggregate" if the compilation and its resulting copyright are not
used to limit the access or legal rights of the compilation's users
beyond what the individual works permit.  Inclusion of a covered work
in an aggregate does not cause this License to apply to the other
parts of the aggregate.

  6. Conveying Non-Source Forms.

  You may convey a covered work in object code form under the terms
of sections 4 and 5, provided that you also convey the
machine-readable Corresponding Source under the terms of this License,
in one of these ways:

    a) Convey the object code in, or embodied in, a physical product
    (including a physical distribution medium), accompanied by the
    Corresponding Source fixed on a durable physical medium
    customarily used for software interchange.

    b) Convey the object code in, or embodied in, a physical product
    (including a physical distribution medium), accompanied by a
    written offer, valid for at least three years and valid for as
    long as you offer spare parts or customer support for that product
    model, to give anyone who possesses the object code either (1) a
    copy of the Corresponding Source for all the software in the
    product that is covered by this License, on a durable physical
    medium customarily used for software interchange, for a price no
    more than your reasonable cost of physically performing this
    conveying of source, or (2) access to copy the
    Corresponding Source from a network server at no charge.

    c) Convey individual copies of the object code with a copy of the
    written offer to provide the Corresponding Source.  This
    alternative is allowed only occasionally and noncommercially, and
    only if you received the object code with such an offer, in accord
    with subsection 6b.

    d) Convey the object code by offering access from a designated
    place (gratis or for a charge), and offer equivalent access to the
    Corresponding Source in the same way through the same place at no
    further charge.  You need not require recipients to copy the
    Corresponding Source along with the object code.  If the place to
    copy the object code is a network server, the Corresponding Source
    may be on a different server (operated by you or a third party)
    that supports equivalent copying facilities, provided you maintain
    clear directions next to the object code saying where to find the
    Corresponding Source.  Regardless of what server hosts the
    Corresponding Source, you remain obligated to ensure that it is
    available for as long as needed to satisfy these requirements.

    e) Convey the object code using peer-to-peer transmission, provided
    you inform other peers where the object code and Corresponding
    Source of the work are being offered to the general public at no
    charge under subsection 6d.

  A separable portion of the object code, whose source code is excluded
from the Corresponding Source as a System Library, need not be
included in conveying the object code work.

  A "User Product" is either (1) a "consumer product", which means any
tangible personal property which is normally used for personal, family,
or household purposes, or (2) anything designed or sold for incorporation
into a dwelling.  In determining whether a product is a consumer product,
doubtful cases shall be resolved in favor of coverage.  For a particular
product received by a particular user, "normally used" refers to a
typical or common use of that class of product, regardless of the status
of the particular user or of the way in which the particular user
actually uses, or expects or is expected to use, the product.  A product
is a consumer product regardless of whether the product has substantial
commercial, industrial or non-consumer uses, unless such uses represent
the only significant mode of use of the product.

  "Installation Information" for a User Product means any methods,
procedures, authorization keys, or other information required to install
and execute modified versions of a covered work in that User Product from
a modified version of its Corresponding Source.  The information must
suffice to ensure that the continued functioning of the modified object
code is in no case prevented or interfered with solely because
modification has been made.

  If you convey an object code work under this section in, or with, or
specifically for use in, a User Product, and the conveying occurs as
part of a transaction in which the right of possession and use of the
User Product is transferred to the recipient in perpetuity or for a
fixed term (regardless of how the transaction is characterized), the
Corresponding Source conveyed under this section must be accompanied
by the Installation Information.  But this requirement does not apply
if neither you nor any third party retains the ability to install
modified object code on the User Product (for example, the work has
been installed in ROM).

  The requirement to provide Installation Information does not include a
requirement to continue to provide support service, warranty, or updates
for a work that has been modified or installed by the recipient, or for
the User Product in which it has been modified or installed.  Access to a
network may be denied when the modification itself materially and
adversely affects the operation of the network or violates the rules and
protocols for communication across the network.

  Corresponding Source conveyed, and Installation Information provided,
in accord with this section must be in a format that is publicly
documented (and with an implementation available to the public in
source code form), and must require no special password or key for
unpacking, reading or copying.

  7. Additional Terms.

  "Additional permissions" are terms that supplement the terms of this
License by making exceptions from one or more of its conditions.
Additional permissions that are applicable to the entire Program shall
be treated as though they were included in this License, to the extent
that they are valid under applicable law.  If additional permissions
apply only to part of the Program, that part may be used separately
under those permissions, but the entire Program remains governed by
this License without regard to the additional permissions.

  When you convey a copy of a covered work, you may at your option
remove any additional permissions from that copy, or from any part of
it.  (Additional permissions may be written to require their own
removal in certain cases when you modify the work.)  You may place
additional permissions on material, added by you to a covered work,
for which you have or can give appropriate copyright permission.

  Notwithstanding any other provision of this License, for material you
add to a covered work, you may (if authorized by the copyright holders of
that material) supplement the terms of this License with terms:

    a) Disclaiming warranty or limiting liability differently from the
    terms of sections 15 and 16 of this License; or

    b) Requiring preservation of specified reasonable legal notices or
    author attributions in that material or in the Appropriate Legal
    Notices displayed by works containing it; or

    c) Prohibiting misrepresentation of the origin of that material, or
    requiring that modified versions of such material be marked in
    reasonable ways as different from the original version; or

    d) Limiting the use for publicity purposes of names of licensors or
    authors of the material; or

    e) Declining to grant rights under trademark law for use of some
    trade names, trademarks, or service marks; or

    f) Requiring indemnification of licensors and authors of that
    material by anyone who conveys the material (or modified versions of
    it) with contractual assumptions of liability to the recipient, for
    any liability that these contractual assumptions directly impose on
    those licensors and authors.

  All other non-permissive additional terms are considered "further
restrictions" within the meaning of section 10.  If the Program as you
received it, or any part of it, contains a notice stating that it is
governed by this License along with a term that is a further
restriction, you may remove that term.  If a license document contains
a further restriction but permits relicensing or conveying under this
License, you may add to a covered work material governed by the terms
of that license document, provided that the further restriction does
not survive such relicensing or conveying.

  If you add terms to a covered work in accord with this section, you
must place, in the relevant source files, a statement of the
additional terms that apply to those files, or a notice indicating
where to find the applicable terms.

  Additional terms, permissive or non-permissive, may be stated in the
form of a separately written license, or stated as exceptions;
the above requirements apply either way.

  8. Termination.

  You may not propagate or modify a covered work except as expressly
provided under this License.  Any attempt otherwise to propagate or
modify it is void, and will automatically terminate your rights under
this License (including any patent licenses granted under the third
paragraph of section 11).

  However, if you cease all violation of this License, then your
license from a particular copyright holder is reinstated (a)
provisionally, unless and until the copyright holder explicitly and
finally terminates your license, and (b) permanently, if the copyright
holder fails to notify you of the violation by some reasonable means
prior to 60 days after the cessation.

  Moreover, your license from a particular copyright holder is
reinstated permanently if the copyright holder notifies you of the
violation by some reasonable means, this is the first time you have
received notice of violation of this License (for any work) from that
copyright holder, and you cure the violation prior to 30 days after
your receipt of the notice.

  Termination of your rights under this section does not terminate the
licenses of parties who have received copies or rights from you under
this License.  If your rights have been terminated and not permanently
reinstated, you do not qualify to receive new licenses for the same
material under section 10.

  9. Acceptance Not Required for Having Copies.

  You are not required to accept this License in order to receive or
run a copy of the Program.  Ancillary propagation of a covered work
occurring solely as a consequence of using peer-to-peer transmission
to receive a copy likewise does not require acceptance.  However,
nothing other than this License grants you permission to propagate or
modify any covered work.  These actions infringe copyright if you do
not accept this License.  Therefore, by modifying or propagating a
covered work, you indicate your acceptance of this License to do so.

  10. Automatic Licensing of Downstream Recipients.

  Each time you convey a covered work, the recipient automatically
receives a license from the original licensors, to run, modify and
propagate that work, subject to this License.  You are not responsible
for enforcing compliance by third parties with this License.

  An "entity transaction" is a transaction transferring control of an
organization, or substantially all assets of one, or subdividing an
organization, or merging organizations.  If propagation of a covered
work results from an entity transaction, each party to that
transaction who receives a copy of the work also receives whatever
licenses to the work the party's predecessor in interest had or could
give under the previous paragraph, plus a right to possession of the
Corresponding Source of the work from the predecessor in interest, if
the predecessor has it or can get it with reasonable efforts.

  You may not impose any further restrictions on the exercise of the
rights granted or affirmed under this License.  For example, you may
not impose a license fee, royalty, or other charge for exercise of
rights granted under this License, and you may not initiate litigation
(including a cross-claim or counterclaim in a lawsuit) alleging that
any patent claim is infringed by making, using, selling, offering for
sale, or importing the Program or any portion of it.

  11. Patents.

  A "contributor" is a copyright holder who authorizes use under this
License of the Program or a work on which the Program is based.  The
work thus licensed is called the contributor's "contributor version".

  A contributor's "essential patent claims" are all patent claims
owned or controlled by the contributor, whether already acquired or
hereafter acquired, that would be infringed by some manner, permitted
by this License, of making, using, or selling its contributor version,
but do not include claims that would be infringed only as a
consequence of further modification of the contributor version.  For
purposes of this definition, "control" includes the right to grant
patent sublicenses in a manner consistent with the requirements of
this License.

  Each contributor grants you a non-exclusive, worldwide, royalty-free
patent license under the contributor's essential patent claims, to
make, use, sell, offer for sale, import and otherwise run, modify and
propagate the contents of its contributor version.

  In the following three paragraphs, a "patent license" is any express
agreement or commitment, however denominated, not to enforce a patent
(such as an express permission to practice a patent or covenant not to
sue for patent infringement).  To "grant" such a patent license to a
party means to make such an agreement or commitment not to enforce a
patent against the party.

  If you convey a covered work, knowingly relying on a patent license,
and the Corresponding Source of the work is not available for anyone
to copy, free of charge and under the terms of this License, through a
publicly available network server or other readily accessible means,
then you must either (1) cause the Corresponding Source to be so
available, or (2) arrange to deprive yourself of the benefit of the
patent license for this particular work, or (3) arrange, in a manner
consistent with the requirements of this License, to extend the patent
license to downstream recipients.  "Knowingly relying" means you have
actual knowledge that, but for the patent license, your conveying the
covered work in a country, or your recipient's use of the covered work
in a country, would infringe one or more identifiable patents in that
country that you have reason to believe are valid.

  If, pursuant to or in connection with a single transaction or
arrangement, you convey, or propagate by procuring conveyance of, a
covered work, and grant a patent license to some of the parties
receiving the covered work authorizing them to use, propagate, modify
or convey a specific copy of the covered work, then the patent license
you grant is automatically extended to all recipients of the covered
work and works based on it.

  A patent license is "discriminatory" if it does not include within
the scope of its coverage, prohibits the exercise of, or is
conditioned on the non-exercise of one or more of the rights that are
specifically granted under this License.  You may not convey a covered
work if you are a party to an arrangement with a third party that is
in the business of distributing software, under which you make payment
to the third party based on the extent of your activity of conveying
the work, and under which the third party grants, to any of the
parties who would receive the covered work from you, a discriminatory
patent license (a) in connection with copies of the covered work
conveyed by you (or copies made from those copies), or (b) primarily
for and in connection with specific products or compilations that
contain the covered work, unless you entered into that arrangement,
or that patent license was granted, prior to 28 March 2007.

  Nothing in this License shall be construed as excluding or limiting
any implied license or other defenses to infringement that may
otherwise be available to you under applicable patent law.

  12. No Surrender of Others' Freedom.

  If conditions are imposed on you (whether by court order, agreement or
otherwise) that contradict the conditions of this License, they do not
excuse you from the conditions of this License.  If you cannot convey a
covered work so as to satisfy simultaneously your obligations under this
License and any other pertinent obligations, then as a consequence you may
not convey it at all.  For example, if you agree to terms that obligate you
to collect a royalty for further conveying from those to whom you convey
the Program, the only way you could satisfy both those terms and this
License would be to refrain entirely from conveying the Program.

  13. Use with the GNU Affero General Public License.

  Notwithstanding any other provision of this License, you have
permission to link or combine any covered work with a work licensed
under version 3 of the GNU Affero General Public License into a single
combined work, and to convey the resulting work.  The terms of this
License will continue to apply to the part which is the covered work,
but the special requirements of the GNU Affero General Public License,
section 13, concerning interaction through a network will apply to the
combination as such.

  14. Revised Versions of this License.

  The Free Software Foundation may publish revised and/or new versions of
the GNU General Public License from time to time.  Such new versions will
be similar in spirit to the present version, but may differ in detail to
address new problems or concerns.

  Each version is given a distinguishing version number.  If the
Program specifies that a certain numbered version of the GNU General
Public License "or any later version" applies to it, you have the
option of following the terms and conditions either of that numbered
version or of any later version published by the Free Software
Foundation.  If the Program does not specify a version number of the
GNU General Public License, you may choose any version ever published
by the Free Software Foundation.

  If the Program specifies that a proxy can decide which future
versions of the GNU General Public License can be used, that proxy's
public statement of acceptance of a version permanently authorizes you
to choose that version for the Program.

  Later license versions may give you additional or different
permissions.  However, no additional obligations are imposed on any
author or copyright holder as a result of your choosing to follow a
later version.

  15. Disclaimer of Warranty.

  THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY
APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT
HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM "AS IS" WITHOUT WARRANTY
OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
ALL NECESSARY SERVICING, REPAIR OR CORRECTION.

  16. Limitation of Liability.

  IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS
THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY
GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD
PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),
EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF
SUCH DAMAGES.

  17. Interpretation of Sections 15 and 16.

  If the disclaimer of warranty and limitation of liability provided
above cannot be given local legal effect according to their terms,
reviewing courts shall apply local law that most closely approximates
an absolute waiver of all civil liability in connection with the
Program, unless a warranty or assumption of liability accompanies a
copy of the Program in return for a fee.

                     END OF TERMS AND CONDITIONS

            How to Apply These Terms to Your New Programs

  If you develop a new program, and you want it to be of the greatest
possible use to the public, the best way to achieve this is to make it
free software which everyone can redistribute and change under these terms.

  To do so, attach the following notices to the program.  It is safest
to attach them to the start of each source file to most effectively
state the exclusion of warranty; and each file should have at least
the "copyright" line and a pointer to where the full notice is found.

    <one line to give the program's name and a brief idea of what it does.>
    Copyright (C) <year>  <name of author>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

Also add information on how to contact you by electronic and paper mail.

  If the program does terminal interaction, make it output a short
notice like this when it starts in an interactive mode:

    <program>  Copyright (C) <year>  <name of author>
    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
    This is free software, and you are welcome to redistribute it
    under certain conditions; type `show c' for details.

The hypothetical commands `show w' and `show c' should show the appropriate
parts of the General Public License.  Of course, your program's commands
might be different; for a GUI interface, you would use an "about box".

  You should also get your employer (if you work as a programmer) or school,
if any, to sign a "copyright disclaimer" for the program, if necessary.
For more information on this, and how to apply and follow the GNU GPL, see
<https://www.gnu.org/licenses/>.

  The GNU General Public License does not permit incorporating your program
into proprietary programs.  If your program is a subroutine library, you
may consider it more useful to permit linking proprietary applications with
the library.  If this is what you want to do, use the GNU Lesser General
Public License instead of this License.  But first, please read
<https://www.gnu.org/licenses/why-not-lgpl.html>.
```

### settings.gradle

```groovy
pluginManagement {
	repositories {
		maven { url = 'https://maven.parchmentmc.org' }
		maven { url = "https://files.minecraftforge.net/maven" }
		gradlePluginPortal()
	}
}
```

### src/generated/resources/.cache/02a388ee54758b79d36587ca49e043c0c05fc129

```
// 1.20.1	2025-01-14T18:04:31.0824962	ModKit Block Models for mod 'gendustry'
f07e54f31c2a9ea4278dbabf08250508635467a0 assets/gendustry/blockstates/advanced_mutatron.json
7363cf644275db82cee619d93e3c098069c684bd assets/gendustry/blockstates/dna_extractor.json
0f21b70a2d0937c16d99867db05c8595640dffd4 assets/gendustry/blockstates/fluid_liquid_dna.json
85f19c041e572a08224e05b7840cb3f00cfa8d8e assets/gendustry/blockstates/fluid_mutagen.json
23e4b0bfb8932b9c5d3a1b1cbbc4565143249999 assets/gendustry/blockstates/fluid_protein.json
a5cf56900badee709dd35b89b16054214abc5153 assets/gendustry/blockstates/genetic_transposer.json
2c3f9b54fed255dee113e76c34411ebd82d300a1 assets/gendustry/blockstates/imprinter.json
6cb100e623bc1a58896ab48a25880f284cecdc5a assets/gendustry/blockstates/industrial_apiary.json
fb8985206e8a8caf311d90ebe982c8525a2ce73a assets/gendustry/blockstates/mutagen_producer.json
d405fb87c8b793df7e9a35d9157bca1d8d60abb0 assets/gendustry/blockstates/mutatron.json
ad79b9837789a76234f256a68ad9ab10d48c5594 assets/gendustry/blockstates/protein_liquefier.json
59c115b74224094851c42bf3ef0e0330bee1aaf7 assets/gendustry/blockstates/replicator.json
4246e920fd1a4da30a92c6ff0845ede8ce435c8b assets/gendustry/blockstates/sampler.json
a29a223b18c160cab5b0855638676fac214339bc assets/gendustry/models/block/advanced_mutatron.json
7ca15b0f8fb1642c3b9ca327ffc8042e0711627b assets/gendustry/models/block/dna_extractor.json
63aec2960bf981b51755d91c3b37566ce229752d assets/gendustry/models/block/fluid_liquid_dna.json
53b07aa414f1d15071931598ec556305b2d879b2 assets/gendustry/models/block/fluid_mutagen.json
07b77bc3dcf6934aff2925c7eb8614bb4d507cd7 assets/gendustry/models/block/fluid_protein.json
554d748a2137a602ff05cb32cbd7ad4f82a4b8a0 assets/gendustry/models/block/genetic_transposer.json
249c7bf70c71f013768f514bce12be293d748e36 assets/gendustry/models/block/imprinter.json
573ac351d91b057de159887ba8dccb673bd24c4f assets/gendustry/models/block/industrial_apiary.json
6b9a816c03f3698c5312cf34afef307ceb683b24 assets/gendustry/models/block/mutagen_producer.json
aca8b87cc3151ed55f4c0ba5ed93b74c078eff18 assets/gendustry/models/block/mutatron.json
d6e4613d767957396400738eef0f9615a84acfe9 assets/gendustry/models/block/protein_liquefier.json
a7ae2d75fa1019c3f0493fa08d850040b52453b8 assets/gendustry/models/block/replicator.json
8e5612201287582ed0aff9c2d9e2cf5609d115aa assets/gendustry/models/block/sampler.json
```

### src/generated/resources/.cache/17bf486417190b9b075888bd20665b89993b9d5d

```
// 1.20.1	2026-02-04T15:31:27.531728488	ModKit Language: en_us for mod 'gendustry'
f91886e324699cb9b65f64c4f86091fdec4c7fa3 assets/gendustry/lang/en_us.json
```

### src/generated/resources/.cache/30d760365f09e476af034a38b0c7a340c0c31694

```
// 1.20.1	2026-02-04T15:31:27.532047922	ModKit Item Models for mod 'gendustry'
419930a4240936d7d46ff8a7ec6666a30186b950 assets/gendustry/models/item/activity_simulator_elite_upgrade.json
53189ae85fbd2fb12a228c9a566f2b20b646773c assets/gendustry/models/item/advanced_mutatron.json
7f529ca2d98fed6e1e53cb127ef919aeb00abe64 assets/gendustry/models/item/automation_upgrade.json
81fadb1b3458c8256823a3c6a015d47d702b0a48 assets/gendustry/models/item/blank_gene_sample.json
85c5c132193d7c96c76dc9398966eaaf1a17ecb7 assets/gendustry/models/item/blank_genetic_template.json
45d4d54ee94a13976eb128782de8611d958bf791 assets/gendustry/models/item/bucket_liquid_dna.json
01ae261d5edf90db1f21eed3cf5fbfd6d9362899 assets/gendustry/models/item/bucket_mutagen.json
1c6d7595c18db975688fccbc173716685c27d30e assets/gendustry/models/item/bucket_protein.json
d51684e185463d02b89bedae439a0b26ddd8e9a9 assets/gendustry/models/item/climate_control_module.json
775581785b41bcf946dc005267a7445ecd35fbfd assets/gendustry/models/item/cooler_upgrade.json
a003a13a38d68118a7c67c9bc60e96583f2c7f29 assets/gendustry/models/item/dna_extractor.json
059f154b29ef9cbe20f36788f027c6fd3be4f0bd assets/gendustry/models/item/dryer_upgrade.json
7e4af79545cf8c1834c36fddc815ef9f92ce3868 assets/gendustry/models/item/elite_upgrade_frame.json
53f28033ac2f62f9473b1e6f4ded5eba212a0e57 assets/gendustry/models/item/environmental_processor.json
36342c3a8bdaaf9af1e061a0eeac89fe65809c11 assets/gendustry/models/item/fertility_elite_upgrade.json
695e8fcff245fd61ff95e159a2847d8aadb41dca assets/gendustry/models/item/gene_sample.json
4e9a58d0ce5113743cc45836c08f5532801a79e0 assets/gendustry/models/item/genetic_template.json
ba47231339daab7c6a5530b2f83cfc697a544bbc assets/gendustry/models/item/genetic_transposer.json
e315bb3bf4734999d6d641afdd2a145ec10d7bc8 assets/gendustry/models/item/genetics_processor.json
2e8ce44762af7175ba39d83a635c9d4288b4f44f assets/gendustry/models/item/heater_upgrade.json
6bb6a98898e9b80a61e01591bf131280aab1de1d assets/gendustry/models/item/humidifier_upgrade.json
2ea8ee500c212c62609ce43dba353b04b72c544c assets/gendustry/models/item/immutable_upgrade.json
7f80e3ab3ffda0b5d5e6c99fb12f3f14af717673 assets/gendustry/models/item/imprinter.json
a970bce06e5467d37050f6181e64487b46c445cd assets/gendustry/models/item/industrial_apiary.json
28d2f565884fe409294a4fa05cb9ad76d7535865 assets/gendustry/models/item/labware.json
c788af76b31332a2e3ae6f6767c5baa829bacae9 assets/gendustry/models/item/lifespan_upgrade.json
7900f8802fa77a72f386c50f40eece37ce3abc58 assets/gendustry/models/item/lighting_upgrade.json
9e423ec6499f40c9ed8a29d4f720bf8837410350 assets/gendustry/models/item/mutagen_producer.json
279a5471f9d3c732bc4220867644dd58b56c740d assets/gendustry/models/item/mutation_elite_upgrade.json
45d7aec0a046cdf568e63e5f8575c6ce711da274 assets/gendustry/models/item/mutatron.json
5e019525afa437433122e4c27cca8e847367095c assets/gendustry/models/item/nether_upgrade.json
1e7b34287e4a66fa5c7b74d4f4098b200b1d6f79 assets/gendustry/models/item/pollen_kit.json
eacbc7e323c27f0dcd1e6fecb0c32358624d5c21 assets/gendustry/models/item/pollination_upgrade.json
88ec666a218667dc0152a01d8cc393e110930f36 assets/gendustry/models/item/power_module.json
f43e77c9fe7734167198c98b8434b3ae82f0c955 assets/gendustry/models/item/productivity_elite_upgrade.json
0e25936f78a051ca68fc0cba57ef2934689c25f0 assets/gendustry/models/item/productivity_upgrade.json
ad902950641ec76c7135b4881e64ce76827008e2 assets/gendustry/models/item/protein_liquefier.json
05eb907f18850e5833108dca472bca2f92cbea05 assets/gendustry/models/item/receptacle.json
4ed7ac22b24c1979e8cadfbe677ced188a823c49 assets/gendustry/models/item/replicator.json
0fbefe0fe4635495ac9b20c12b1ea9a7e2d493d5 assets/gendustry/models/item/sampler.json
eaf7d3724e1666844ff0859b9cd15775a4926bcf assets/gendustry/models/item/scrubber_upgrade.json
a99b5dbe05c0e108509476abd31e53942e5381f1 assets/gendustry/models/item/sieve_upgrade.json
f082bb4845ad1e2f760066ab940a5ec8c0d7a054 assets/gendustry/models/item/sky_upgrade.json
e4225021a62bafa3142f94468284326fb756e4f2 assets/gendustry/models/item/stabilizer_upgrade.json
5a5ce7ecfa62c7223e8d70a1992550929a65be64 assets/gendustry/models/item/territory_elite_upgrade.json
bb731b506815cf714fb0d2d8e03fd317b067a38d assets/gendustry/models/item/territory_upgrade.json
74e3be8512282ee8dbd0cac541f2e86caf03b1ee assets/gendustry/models/item/upgrade_frame.json
5bc28955bdab0416c779f7f2da36de75509f2ebd assets/gendustry/models/item/weatherproof_upgrade.json
4538825b7ba8f71dc39baad00794791ca44034e3 assets/gendustry/models/item/youth_elite_upgrade.json
```

### src/generated/resources/.cache/59eb3dbb5f86130e09b3c62d89b9525ee01cf52d

```
// 1.20.1	2025-01-14T23:41:55.5157209	Loot Tables
87d30d8d2f3079a6aa3931e528d17772d9c5ea88 data/gendustry/loot_tables/blocks/advanced_mutatron.json
00d253231acd73314a9bfaecd7d056669b5e132b data/gendustry/loot_tables/blocks/dna_extractor.json
ae1c6ebd961b64a518856905da14f846e4be9d23 data/gendustry/loot_tables/blocks/genetic_transposer.json
249587b244486cd8680f156df315ff838ca341fc data/gendustry/loot_tables/blocks/imprinter.json
7afbdfb7c70df40a233837ff75ccebe5401c48c0 data/gendustry/loot_tables/blocks/industrial_apiary.json
44e0f67431594f4a255f2f84b9eb3930910a1991 data/gendustry/loot_tables/blocks/mutagen_producer.json
6b056e9ec33df1b1f41660f14d63077743d3cbf4 data/gendustry/loot_tables/blocks/mutatron.json
c2fcd6605ed42b4fffa9b5610caa39e8b8e68348 data/gendustry/loot_tables/blocks/protein_liquefier.json
de4516e1b5353cb8936c5a6a276b13332e161a18 data/gendustry/loot_tables/blocks/replicator.json
f1debc4957fc21bb6b6aa0ad0088c281416ae75e data/gendustry/loot_tables/blocks/sampler.json
```

### src/generated/resources/.cache/9fb1092f32d4fcbf9e061ffd718d4ec689c6c95e

```
// 1.20.1	2026-02-04T15:31:27.53251987	Recipes
f6e776b964531a01c7ba87ab89c7705969105cc3 data/gendustry/advancements/recipes/misc/activity_simulator_elite_upgrade.json
d10b8cb8f7e9927b0057ca12bb22cf6c25abfefa data/gendustry/advancements/recipes/misc/advanced_mutatron.json
611d8e0b68aa9b0a58559a0dfb5ceb13fd9298eb data/gendustry/advancements/recipes/misc/automation_upgrade.json
b66ac124766832ccfd7e846ffd85e06596115c2f data/gendustry/advancements/recipes/misc/blank_gene_sample.json
436bdbe1f3bc8ed6eb0064a575da9e2112f89350 data/gendustry/advancements/recipes/misc/blank_genetic_template.json
85e4b246bb6778fd76ac0b634478cb09774adf5a data/gendustry/advancements/recipes/misc/climate_control_module.json
e2b57f55d23b89d4dd79b10d4090477c7c516b6b data/gendustry/advancements/recipes/misc/cooler_upgrade.json
8b88ce525abf9c34feac55e8c11f661ae1b93310 data/gendustry/advancements/recipes/misc/dna_extractor.json
5bd2fb490832096cf2af2404a8a726a5d8aeb733 data/gendustry/advancements/recipes/misc/dryer_upgrade.json
28667474cf09382ec18d37da17d0ea260da129a4 data/gendustry/advancements/recipes/misc/elite_upgrade_frame.json
1c0c9d303c51ff52f5d39f28dd8ea0434cf7a772 data/gendustry/advancements/recipes/misc/environmental_processor.json
345e703e6d2a6d4ad137537cb5c37b51c084b01f data/gendustry/advancements/recipes/misc/fertility_elite_upgrade.json
40cf75938a490c055687d23f74dde3597021047d data/gendustry/advancements/recipes/misc/genetic_transposer.json
da3d79cab8f6c7ae7361d701f858cd1489a88c68 data/gendustry/advancements/recipes/misc/genetics_processor.json
6116daf1aae5f4dfa1ec3bb2e5a959d13a30fe4e data/gendustry/advancements/recipes/misc/heater_upgrade.json
1ca9aa6aeaca90dc4b74d77e35396bf1330e53ca data/gendustry/advancements/recipes/misc/humidifier_upgrade.json
0d2d6e4cd09cc366cee3160bca835655531658b2 data/gendustry/advancements/recipes/misc/immutable_upgrade.json
da4ef79c304ab54f5432b7300fb576f2468292fb data/gendustry/advancements/recipes/misc/imprinter.json
9ffdd1d9502b20f1f578d76b19c9a62ac9ab7120 data/gendustry/advancements/recipes/misc/industrial_apiary.json
fea26e784616f44b05efb77a70db12f1a745f88d data/gendustry/advancements/recipes/misc/labware.json
46134d7a0be45aa806dbfa22090b34025ff20616 data/gendustry/advancements/recipes/misc/lifespan_upgrade.json
f64640b3da0b6d5916f61f714084e8cedbb11819 data/gendustry/advancements/recipes/misc/lighting_upgrade.json
3b02722367de4691f4c657c835b2474b021fd05e data/gendustry/advancements/recipes/misc/mutagen_producer.json
c7bbe9dc8ebee66d02696909dfa0c5dc6052b62d data/gendustry/advancements/recipes/misc/mutation_elite_upgrade.json
faeda74ee1955b57fc80b653b95f1da8f60df461 data/gendustry/advancements/recipes/misc/mutatron.json
5df6d94e9bd44e90b71715adb7b3ab4e17d15489 data/gendustry/advancements/recipes/misc/nether_upgrade.json
4d07dcc1af2616259503a5d95de0585b1c1c4940 data/gendustry/advancements/recipes/misc/pollination_upgrade.json
350766261ee3eba412126ced874eb09430bd3240 data/gendustry/advancements/recipes/misc/power_module.json
7eba56a16289d1bfb7bfa0c0908c88492d1904c0 data/gendustry/advancements/recipes/misc/productivity_elite_upgrade.json
55f74895054abfefabdb1fa42f823e421db0ca24 data/gendustry/advancements/recipes/misc/productivity_upgrade.json
b5316ba94eaeb75e82a5ef8a7f27a3a76d5d59f4 data/gendustry/advancements/recipes/misc/protein_liquefier.json
b2f79bc5048d3e921d09e4e5b8285fe49c4069f9 data/gendustry/advancements/recipes/misc/receptacle.json
e431d6b0f5b102f51c642798322c4d8170b8bdf4 data/gendustry/advancements/recipes/misc/replicator.json
41249e63fc3a8f371538906e30f9fe9ab44c2305 data/gendustry/advancements/recipes/misc/sampler.json
421a4c16667200508193349df8241b8b46900ff8 data/gendustry/advancements/recipes/misc/scrubber_upgrade.json
10f158a3a454d52f59afa9f69694c90d836cf127 data/gendustry/advancements/recipes/misc/sieve_upgrade.json
911c597ad10255de31cdaeb77e82e0b252acc855 data/gendustry/advancements/recipes/misc/sky_upgrade.json
45f4eb30c51b1ea0ca179a60a2f523572a199326 data/gendustry/advancements/recipes/misc/stabilizer_upgrade.json
4587ac36b8dfea2cb47ae5883b9230009a48debb data/gendustry/advancements/recipes/misc/territory_elite_upgrade.json
1db3809dbd4b1ae9223cdb1cfb3563e9f58bfbd7 data/gendustry/advancements/recipes/misc/territory_upgrade.json
fd059d9c1ba2adcf020915566acc0c77da626091 data/gendustry/advancements/recipes/misc/upgrade_frame.json
93384c4d2822b5bc5604ba70d275ceec929b5ef4 data/gendustry/advancements/recipes/misc/weatherproof_upgrade.json
790b9019062a946c51389ccdaf8e872d52661210 data/gendustry/advancements/recipes/misc/youth_elite_upgrade.json
3f7968eaa3996a779027058b182c4cbb9e978232 data/gendustry/advancements/recipes/tools/pollen_kit.json
113d0a1933e65d257da6c20bb7eb76d8f94add93 data/gendustry/recipes/activity_simulator_elite_upgrade.json
2388b82bab9910903d3acc0131db57cebe7373c3 data/gendustry/recipes/advanced_mutatron.json
b87faa1bbf5eb71ecc44a6c5a23e5abfe3d4ed64 data/gendustry/recipes/automation_upgrade.json
d6ea5ac2ddf764d1b7218e1fc11da0ead2b274aa data/gendustry/recipes/blank_gene_sample.json
755c4fa9dc91996629f02c51f7d2cbc9fc4b050f data/gendustry/recipes/blank_gene_sample_wipe_dna.json
066077215827c97dbdeed89f79644074c5115041 data/gendustry/recipes/blank_genetic_template.json
a7f6402b1b684f333792f0484e3e35f49358f633 data/gendustry/recipes/blank_genetic_template_wipe_dna.json
a4d95241e639906aab9c5c737863560401480a0b data/gendustry/recipes/climate_control_module.json
f687cc074d768e789b2d43e62fc906be97c58347 data/gendustry/recipes/combine_genetic_template.json
45a06cae1f7bab07a93086cc00a694a17a00d880 data/gendustry/recipes/cooler_upgrade.json
229f6a1cd1e8c23694c5108459f5de194efd5906 data/gendustry/recipes/dna/butterfly.json
4292d9a2976565315a4255be1f660435fa449d5a data/gendustry/recipes/dna/caterpillar.json
59c53139f3f24805e5001dd08925e8b6ecb02e27 data/gendustry/recipes/dna/cocoon.json
2c76f025619f28d4158560435440eed4e2097512 data/gendustry/recipes/dna/drone.json
222536094cf0c80e647641c30315ad6dff23749a data/gendustry/recipes/dna/larvae.json
4bc638cda6099f6b9e4ce46b024e4485f3e921bf data/gendustry/recipes/dna/pollen.json
01228d2c8433f025a6e867f67aebce5e49294af7 data/gendustry/recipes/dna/princess.json
054f9162f068c570e76337f041013d8ba9e4b4b1 data/gendustry/recipes/dna/queen.json
aeeea2c652f238e7603f6d5c6fe094ba17569d0b data/gendustry/recipes/dna/sapling.json
660c786e588fe8c87c21bdd2e1394fbea83a3559 data/gendustry/recipes/dna/serum.json
5c12ca057c9bd513560789eb97bda2bf29ab49c9 data/gendustry/recipes/dna_extractor.json
67d8b0786245d71d7b72a3cd71b55e5a07d44966 data/gendustry/recipes/dryer_upgrade.json
297c2d8e805ec336444ebd9ec5c419f19eaf4f50 data/gendustry/recipes/elite_upgrade_frame.json
6616dd88e329cf9cf0125a7030bf013adb7a9121 data/gendustry/recipes/environmental_processor.json
848c3afd3134ce9ee9ffafe14ea1ea74806ca0df data/gendustry/recipes/fertility_elite_upgrade.json
20f8239d429e8ca3f90d8e94d135c10239ccf8d0 data/gendustry/recipes/genetic_transposer.json
b5bc69acc9d24e9c165dae37c6a13867b3bf2294 data/gendustry/recipes/genetics_processor.json
afc9f29be5956ec5bac67d79da9e5f4d47cea5d0 data/gendustry/recipes/heater_upgrade.json
4b0a6fc8403334263f2dce4d598aece481931fef data/gendustry/recipes/humidifier_upgrade.json
ad918f5729d3c1d34cf31a0701ba7b1ab1595224 data/gendustry/recipes/immutable_upgrade.json
1758d6e62b33a5e7e3247252374ed1b1844f4023 data/gendustry/recipes/imprinter.json
a2534112977f3dcf51222db16f58b38af20ebe07 data/gendustry/recipes/industrial_apiary.json
49ac9ef178bf8833fdaf3c9352848bf7d2819fd7 data/gendustry/recipes/labware.json
5a8a651f4d2dfb2189d366b4c311275637d81c85 data/gendustry/recipes/lifespan_upgrade.json
cac4a50e53616fdd7934c41bdf8e3c48bbaa21af data/gendustry/recipes/lighting_upgrade.json
cb5c4c72b8ef85fd29910f97ff8ffcadb2d7d094 data/gendustry/recipes/mutagen/glowstone.json
55a00920fb5721671564e42b9f0ecaee45006bba data/gendustry/recipes/mutagen/glowstone_dust.json
1ccb2272ec0ec69ffc0c5925173b6964a7c72ee1 data/gendustry/recipes/mutagen/redstone.json
56e98d5d8355ebef463de0101d7158b2f2d251f8 data/gendustry/recipes/mutagen/redstone_block.json
a25fc93c340a600acee4fc9546a8fd4c0d398693 data/gendustry/recipes/mutagen_producer.json
02a9e75bd0a4cf6665d8629313ad2b453f136f69 data/gendustry/recipes/mutation_elite_upgrade.json
fe09292eda4663ede6a06a734a3058c72bc5a2d9 data/gendustry/recipes/mutatron.json
89b19bae71ed3904802726f0a4e6f018a0ee0e4a data/gendustry/recipes/nether_upgrade.json
571c6e9b375aec6a8fe3704d121a97ac45f6c5a0 data/gendustry/recipes/pollen_kit.json
398e74fc59522c0dad30157299c9d026e79a4539 data/gendustry/recipes/pollination_upgrade.json
577599453955914aa25aaa1eeb2a82290aca8c73 data/gendustry/recipes/power_module.json
f92c2071f551fb9b13c087a77707c01cc5025fad data/gendustry/recipes/productivity_elite_upgrade.json
637315de889058b0d5fa24c9813ca63053f6cf6a data/gendustry/recipes/productivity_upgrade.json
d085832ee27decbc77ba94611341566f7b9193c0 data/gendustry/recipes/protein/beef.json
252530d1cf7f4567122c422fa1b8f918db6cb0ec data/gendustry/recipes/protein/cod.json
3252b0df877b4e0883ba4397aaaf5fb89fcb7fad data/gendustry/recipes/protein/porkchop.json
ca53c63c78b08871076323f9a0c7ff420bec8990 data/gendustry/recipes/protein/pufferfish.json
553091dd7e400e6a1e40e6fa23aca2442041c2a0 data/gendustry/recipes/protein/rabbit.json
e623827b959a2409af87b8c921f6de8cd73b1d9e data/gendustry/recipes/protein/salmon.json
63000ab315360768fe18aa0a2bbd204cf7c81877 data/gendustry/recipes/protein/tropical_fish.json
a2c7e6c66243a7287b9aa16e01b1802314f8ec80 data/gendustry/recipes/protein_liquefier.json
82f1540f27dcebb8b282a0c78865a9414e276f78 data/gendustry/recipes/receptacle.json
65f775db25378abf847536fbc1f8da803a277c1c data/gendustry/recipes/replicator.json
a6521a34a256f7d136c6cff1b58bbe03c38c0526 data/gendustry/recipes/sampler.json
dff5921829c289a1f270e5acfc287118fc6b1928 data/gendustry/recipes/scrubber_upgrade.json
182413128eea1f87713839d9996e71cfd521972e data/gendustry/recipes/sieve_upgrade.json
c156f83a491cc0927311829ec3b0e12225ad4cbc data/gendustry/recipes/sky_upgrade.json
7241534a4868abd7ee8052aa79ab8b6a43fc7ac3 data/gendustry/recipes/stabilizer_upgrade.json
b946ee743be62f1f6f05a83b71de106074c22faf data/gendustry/recipes/territory_elite_upgrade.json
034368dd60fea3b6897890034db54d5b63249b7c data/gendustry/recipes/territory_upgrade.json
0f35c9585a6cbda4d769b96d5b59f107b2f45036 data/gendustry/recipes/upgrade_frame.json
96e57130f84c865966b09cd0e78810c6fe5bcee3 data/gendustry/recipes/weatherproof_upgrade.json
85e96da3f4b504351fcf2f12b650624f91ee42ee data/gendustry/recipes/youth_elite_upgrade.json
```

### src/generated/resources/.cache/bf931954ab5a321ad31e445dffa32b91605b826b

```
// 1.20.1	2025-01-14T23:34:15.3528076	Tags for minecraft:block mod id gendustry
5e2461affec4130b06272e763286edecea92ea6d data/minecraft/tags/blocks/mineable/pickaxe.json
```

### src/generated/resources/.cache/e45428b82fb5616b4a68a8071643f387f24f965a

```
// 1.20.1	2026-02-04T15:31:27.530506255	Tags for minecraft:item mod id gendustry
617b09908b0598847375bb56fc971bd35b1ade64 data/gendustry/tags/items/upgrades.json
```

### src/generated/resources/assets/gendustry/blockstates/advanced_mutatron.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/advanced_mutatron"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/dna_extractor.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/dna_extractor"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/fluid_liquid_dna.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/fluid_liquid_dna"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/fluid_mutagen.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/fluid_mutagen"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/fluid_protein.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/fluid_protein"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/genetic_transposer.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/genetic_transposer"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/imprinter.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/imprinter"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/industrial_apiary.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/industrial_apiary"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/mutagen_producer.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/mutagen_producer"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/mutatron.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/mutatron"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/protein_liquefier.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/protein_liquefier"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/replicator.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/replicator"
    }
  }
}
```

### src/generated/resources/assets/gendustry/blockstates/sampler.json

```json
{
  "variants": {
    "": {
      "model": "gendustry:block/sampler"
    }
  }
}
```

### src/generated/resources/assets/gendustry/lang/en_us.json

```json
{
  "block.gendustry.advanced_mutatron": "Advanced Mutatron",
  "block.gendustry.dna_extractor": "DNA Extractor",
  "block.gendustry.fluid_liquid_dna": "Fluid Liquid Dna",
  "block.gendustry.fluid_mutagen": "Fluid Mutagen",
  "block.gendustry.fluid_protein": "Fluid Protein",
  "block.gendustry.genetic_transposer": "Genetic Transposer",
  "block.gendustry.imprinter": "Imprinter",
  "block.gendustry.industrial_apiary": "Industrial Apiary",
  "block.gendustry.mutagen_producer": "Mutagen Producer",
  "block.gendustry.mutatron": "Mutatron",
  "block.gendustry.protein_liquefier": "Protein Liquefier",
  "block.gendustry.replicator": "Replicator",
  "block.gendustry.sampler": "Sampler",
  "errors.gendustry.incompatible_species.desc": "Incompatible species",
  "errors.gendustry.incompatible_species.help": "Individuals may only be mated with individuals of the same species type.",
  "errors.gendustry.no_blank.desc": "Missing blank template/sample",
  "errors.gendustry.no_blank.help": "The Genetic Transposer needs Blank Gene Samples or Blank Genetic Templates to copy to.",
  "errors.gendustry.no_dna.desc": "Missing Liquid DNA",
  "errors.gendustry.no_dna.help": "This machine requires Liquid DNA to operate.",
  "errors.gendustry.no_labware.desc": "No Labware",
  "errors.gendustry.no_labware.help": "This machine requires Labware to operate.",
  "errors.gendustry.no_mates.desc": "No Mates",
  "errors.gendustry.no_mates.help": "Two compatible mates are required for a mutation to occur.",
  "errors.gendustry.no_mutagen.desc": "No Mutagen",
  "errors.gendustry.no_mutagen.help": "Mutagen is required to trigger a mutation.",
  "errors.gendustry.no_mutations.desc": "No Mutations",
  "errors.gendustry.no_mutations.help": "There are no mutations between these two species. Please choose different species.",
  "errors.gendustry.no_protein.desc": "Missing Protein",
  "errors.gendustry.no_protein.help": "This machine requires Protein to operate.",
  "errors.gendustry.no_samples.desc": "No Samples",
  "errors.gendustry.no_samples.help": "This machine requires Blank Gene Samples to operate.",
  "errors.gendustry.no_selection.desc": "Select a mutation",
  "errors.gendustry.no_selection.help": "You must choose a mutation for the Advanced Mutatron.",
  "errors.gendustry.no_source.desc": "Missing source template/sample",
  "errors.gendustry.no_source.help": "The Genetic Transposer is missing a filled Gene Sample or Genetic Template.",
  "errors.gendustry.no_template.desc": "Missing template",
  "errors.gendustry.no_template.help": "A complete Genetic Template is required to operate.",
  "fluid_type.gendustry.liquid_dna": "Liquid DNA",
  "fluid_type.gendustry.mutagen": "Mutagen",
  "fluid_type.gendustry.protein": "Protein",
  "for.hints.advanced_mutatron.desc": "To choose the desired mutation between the two parents, use the Advanced Mutatron.",
  "for.hints.advanced_mutatron.tag": "How to use the Advanced Mutatron?",
  "for.hints.dna_ingredients.desc": "Liquid DNA can be made from any organisms with a Forestry genome, like bees, saplings and pollen, and butterflies.",
  "for.hints.dna_ingredients.tag": "What makes Liquid DNA?",
  "for.hints.dna_usage.desc": "Use Liquid DNA in the Replicator to construct new organisms!",
  "for.hints.dna_usage.tag": "How to use Liquid DNA?",
  "for.hints.imprinter_usage.desc": "The Imprinter replaces the genome of an individual with the alleles stored in a Genetic Template. Ignoble stock may not survive.",
  "for.hints.imprinter_usage.tag": "How to use the Imprinter?",
  "for.hints.industrial_apiary.desc": "The left slots are for a Princess and Drone. The four middle slots are for upgrades. The nine right slots are outputs.",
  "for.hints.industrial_apiary.tag": "How to use the Industrial Apiary?",
  "for.hints.industrial_apiary_upgrades.desc": "The Industrial Apiary does not need frames. Instead, it uses upgrades that affect climate, productivity, lifespan, and more!",
  "for.hints.industrial_apiary_upgrades.tag": "Why no frames?",
  "for.hints.mutagen_ingredients.desc": "Mutagen can be made from redstone, glowstone, and even uranium!",
  "for.hints.mutagen_ingredients.tag": "What makes Mutagen?",
  "for.hints.mutagen_usage.desc": "Produce Mutagen to use in other Gendustry machines.",
  "for.hints.mutagen_usage.tag": "What's Mutagen for?",
  "for.hints.mutatron_usage.desc": "The Mutatron triggers a mutation between two parent organisms, yielding offspring of a new species.",
  "for.hints.mutatron_usage.tag": "What is the Mutatron?",
  "for.hints.protein_ingredients.desc": "Protein can be made from any kind of raw meat.",
  "for.hints.protein_ingredients.tag": "How to get Protein?",
  "for.hints.protein_usage.desc": "Protein is an ingredient used by the Replicator to create new organisms.",
  "for.hints.protein_usage.tag": "How to use Protein?",
  "for.hints.replicator_usage.desc": "The Replicator produces a new organism from a Genetic Template using Liquid DNA and Protein.",
  "for.hints.replicator_usage.tag": "How to use the Replicator?",
  "for.hints.sample_reuse.desc": "Unwanted gene samples can be wiped blank by heating them in a furnace.",
  "for.hints.sample_reuse.tag": "Don't throw away samples!",
  "for.hints.sample_selection.desc": "The Sampler picks a random allele from the specimen's genome and saves it to a gene sample.",
  "for.hints.sample_selection.tag": "How to choose a gene?",
  "for.hints.sample_usage.desc": "Gene samples can be crafted with a Genetic Template to create a complete genome for use in the Imprinter.",
  "for.hints.sample_usage.tag": "What are samples for?",
  "for.hints.transposer_usage.desc": "The Genetic Transposer creates copies of Gene Samples and Genetic Templates.",
  "for.hints.transposer_usage.tag": "How to use the Genetic Transposer?",
  "gendustry.for.chance": "%s%% chance to be consumed!",
  "info.gendustry.dna": "Liquid DNA is used in the Replicator to produce new organisms from complete Genetic Templates.",
  "info.gendustry.mutagen": "Mutagen is used in the Mutatron and Advanced Mutatron to trigger mutations between two organisms.",
  "info.gendustry.protein": "Protein is used in the Replicator to produce new organisms from complete Genetic Templates.",
  "item.gendustry.activity_simulator_elite_upgrade": "Activity Simulator Elite Upgrade",
  "item.gendustry.activity_simulator_elite_upgrade.tooltip": "A combination of the Sky, Weatherproof, and Lighting upgrades.",
  "item.gendustry.automation_upgrade": "Automation Upgrade",
  "item.gendustry.automation_upgrade.tooltip": "Automatically recycles princesses and drones from deceased queens.",
  "item.gendustry.blank_gene_sample": "Blank Gene Sample",
  "item.gendustry.blank_gene_sample.tooltip": "Use in the Sampler to obtain Gene Samples",
  "item.gendustry.blank_genetic_template": "Blank Genetic Template",
  "item.gendustry.blank_genetic_template.tooltip": "Combine with Gene Samples in a Crafting Table",
  "item.gendustry.bucket_liquid_dna": "Bucket Liquid Dna",
  "item.gendustry.bucket_mutagen": "Bucket Mutagen",
  "item.gendustry.bucket_protein": "Bucket Protein",
  "item.gendustry.climate_control_module": "Climate Control Module",
  "item.gendustry.cooler_upgrade": "Cooler Upgrade",
  "item.gendustry.cooler_upgrade.tooltip": "Lowers the temperature of the apiary by 1 step.",
  "item.gendustry.dryer_upgrade": "Dryer Upgrade",
  "item.gendustry.dryer_upgrade.tooltip": "Lowers the humidity of the apiary by 1 step.",
  "item.gendustry.elite_upgrade_frame": "Elite Upgrade Frame",
  "item.gendustry.environmental_processor": "Environmental Processor",
  "item.gendustry.fertility_elite_upgrade": "Fertility Elite Upgrade",
  "item.gendustry.fertility_elite_upgrade.tooltip": "Increases fertility count by 1.",
  "item.gendustry.gene_sample": "Gene Sample (%s)",
  "item.gendustry.genetic_template": "Genetic Template (%s)",
  "item.gendustry.genetic_template.allele_count": "Alleles (%1$s/%2$s)",
  "item.gendustry.genetic_template.allele_entry": "  %1$s - %2$s",
  "item.gendustry.genetic_template.missing_allele": "MISSING",
  "item.gendustry.genetics_processor": "Genetics Processor",
  "item.gendustry.heater_upgrade": "Heater Upgrade",
  "item.gendustry.heater_upgrade.tooltip": "Raises the temperature of the apiary by 1 step.",
  "item.gendustry.humidifier_upgrade": "Humidifier Upgrade",
  "item.gendustry.humidifier_upgrade.tooltip": "Raises the humidity of the apiary by 1 step.",
  "item.gendustry.immutable_upgrade": "Immutable Upgrade",
  "item.gendustry.immutable_upgrade.tooltip": "Prevents bee mutations from occurring.",
  "item.gendustry.labware": "Labware",
  "item.gendustry.lifespan_upgrade": "Lifespan Upgrade",
  "item.gendustry.lifespan_upgrade.tooltip": "Decreases lifespan by 20%.",
  "item.gendustry.lighting_upgrade": "Lighting Upgrade",
  "item.gendustry.lighting_upgrade.tooltip": "Allows bees to work without needing to sleep.",
  "item.gendustry.mutation_elite_upgrade": "Mutation Elite Upgrade",
  "item.gendustry.mutation_elite_upgrade.tooltip": "Increases bee mutation chances by 25%.",
  "item.gendustry.nether_upgrade": "Nether Upgrade",
  "item.gendustry.nether_upgrade.tooltip": "Sets the apiary's climate to Hellish temperature and Arid humidity.",
  "item.gendustry.pollen_kit": "Pollen Kit",
  "item.gendustry.pollination_upgrade": "Pollination Upgrade",
  "item.gendustry.pollination_upgrade.tooltip": "Increases bee pollination by 25%.",
  "item.gendustry.power_module": "Power Module",
  "item.gendustry.productivity_elite_upgrade": "Productivity Elite Upgrade",
  "item.gendustry.productivity_elite_upgrade.tooltip": "Increases bee productivity by 25% and speeds up work cycle by 15 ticks.",
  "item.gendustry.productivity_upgrade": "Productivity Upgrade",
  "item.gendustry.productivity_upgrade.tooltip": "Increases bee productivity by 25%.",
  "item.gendustry.receptacle": "Receptacle",
  "item.gendustry.scrubber_upgrade": "Scrubber Upgrade",
  "item.gendustry.scrubber_upgrade.tooltip": "Disables bee pollination.",
  "item.gendustry.sieve_upgrade": "Sieve Upgrade",
  "item.gendustry.sieve_upgrade.tooltip": "Allows collecting pollen from nearby trees, like the Alveary Sieve.",
  "item.gendustry.sky_upgrade": "Sky Upgrade",
  "item.gendustry.sky_upgrade.tooltip": "Simulates a view of the sky for bees that aren't cave dwelling.",
  "item.gendustry.stabilizer_upgrade": "Stabilizer Upgrade",
  "item.gendustry.stabilizer_upgrade.tooltip": "Prevents Ignoble Stock bees from dying.",
  "item.gendustry.territory_elite_upgrade": "Territory Elite Upgrade",
  "item.gendustry.territory_elite_upgrade.tooltip": "Increases bee territory by 25%, but has a higher limit.",
  "item.gendustry.territory_upgrade": "Territory Upgrade",
  "item.gendustry.territory_upgrade.tooltip": "Increases territory by 25%.",
  "item.gendustry.upgrade.energy_cost": "Energy Cost: %s RF",
  "item.gendustry.upgrade.max_count": "Max Count: %s",
  "item.gendustry.upgrade_frame": "Upgrade Frame",
  "item.gendustry.weatherproof_upgrade": "Weatherproof Upgrade",
  "item.gendustry.weatherproof_upgrade.tooltip": "Allows bees to work during the rain.",
  "item.gendustry.youth_elite_upgrade": "Youth Elite Upgrade",
  "item.gendustry.youth_elite_upgrade.tooltip": "Increases lifespan by 20%.",
  "itemGroup.gendustry": "Gendustry",
  "itemGroup.gene_samples": "Gene Samples"
}
```

### src/generated/resources/assets/gendustry/models/block/advanced_mutatron.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/advanced_mutatron_bottom",
    "side": "gendustry:block/advanced_mutatron_side",
    "top": "gendustry:block/advanced_mutatron_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/dna_extractor.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/dna_extractor_bottom",
    "side": "gendustry:block/dna_extractor_side",
    "top": "gendustry:block/dna_extractor_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/fluid_liquid_dna.json

```json
{
  "textures": {
    "particle": "gendustry:block/liquid/liquid_dna_still"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/fluid_mutagen.json

```json
{
  "textures": {
    "particle": "gendustry:block/liquid/mutagen_still"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/fluid_protein.json

```json
{
  "textures": {
    "particle": "gendustry:block/liquid/protein_still"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/genetic_transposer.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/genetic_transposer_bottom",
    "side": "gendustry:block/genetic_transposer_side",
    "top": "gendustry:block/genetic_transposer_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/imprinter.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/imprinter_bottom",
    "side": "gendustry:block/imprinter_side",
    "top": "gendustry:block/imprinter_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/industrial_apiary.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/industrial_apiary_bottom",
    "side": "gendustry:block/industrial_apiary_side",
    "top": "gendustry:block/industrial_apiary_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/mutagen_producer.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/mutagen_producer_bottom",
    "side": "gendustry:block/mutagen_producer_side",
    "top": "gendustry:block/mutagen_producer_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/mutatron.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/mutatron_bottom",
    "side": "gendustry:block/mutatron_side",
    "top": "gendustry:block/mutatron_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/protein_liquefier.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/protein_liquefier_bottom",
    "side": "gendustry:block/protein_liquefier_side",
    "top": "gendustry:block/protein_liquefier_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/replicator.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/replicator_bottom",
    "side": "gendustry:block/replicator_side",
    "top": "gendustry:block/replicator_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/block/sampler.json

```json
{
  "parent": "minecraft:block/cube_bottom_top",
  "textures": {
    "bottom": "gendustry:block/sampler_bottom",
    "side": "gendustry:block/sampler_side",
    "top": "gendustry:block/sampler_top"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/activity_simulator_elite_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/activity_simulator_elite_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/advanced_mutatron.json

```json
{
  "parent": "gendustry:block/advanced_mutatron"
}
```

### src/generated/resources/assets/gendustry/models/item/automation_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/automation_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/blank_gene_sample.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/blank_gene_sample"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/blank_genetic_template.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/blank_genetic_template"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/bucket_liquid_dna.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/bucket_liquid_dna"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/bucket_mutagen.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/bucket_mutagen"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/bucket_protein.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/bucket_protein"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/climate_control_module.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/climate_control_module"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/cooler_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/cooler_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/dna_extractor.json

```json
{
  "parent": "gendustry:block/dna_extractor"
}
```

### src/generated/resources/assets/gendustry/models/item/dryer_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/dryer_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/elite_upgrade_frame.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/elite_upgrade_frame"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/environmental_processor.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/environmental_processor"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/fertility_elite_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/fertility_elite_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/gene_sample.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/gene_sample"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/genetic_template.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/genetic_template"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/genetic_transposer.json

```json
{
  "parent": "gendustry:block/genetic_transposer"
}
```

### src/generated/resources/assets/gendustry/models/item/genetics_processor.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/genetics_processor"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/heater_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/heater_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/humidifier_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/humidifier_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/immutable_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/immutable_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/imprinter.json

```json
{
  "parent": "gendustry:block/imprinter"
}
```

### src/generated/resources/assets/gendustry/models/item/industrial_apiary.json

```json
{
  "parent": "gendustry:block/industrial_apiary"
}
```

### src/generated/resources/assets/gendustry/models/item/labware.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/labware"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/lifespan_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/lifespan_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/lighting_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/lighting_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/mutagen_producer.json

```json
{
  "parent": "gendustry:block/mutagen_producer"
}
```

### src/generated/resources/assets/gendustry/models/item/mutation_elite_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/mutation_elite_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/mutatron.json

```json
{
  "parent": "gendustry:block/mutatron"
}
```

### src/generated/resources/assets/gendustry/models/item/nether_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/nether_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/pollen_kit.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/pollen_kit"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/pollination_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/pollination_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/power_module.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/power_module"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/productivity_elite_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/productivity_elite_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/productivity_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/productivity_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/protein_liquefier.json

```json
{
  "parent": "gendustry:block/protein_liquefier"
}
```

### src/generated/resources/assets/gendustry/models/item/receptacle.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/receptacle"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/replicator.json

```json
{
  "parent": "gendustry:block/replicator"
}
```

### src/generated/resources/assets/gendustry/models/item/sampler.json

```json
{
  "parent": "gendustry:block/sampler"
}
```

### src/generated/resources/assets/gendustry/models/item/scrubber_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/scrubber_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/sieve_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/sieve_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/sky_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/sky_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/stabilizer_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/stabilizer_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/territory_elite_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/territory_elite_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/territory_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/territory_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/upgrade_frame.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/upgrade_frame"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/weatherproof_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/weatherproof_upgrade"
  }
}
```

### src/generated/resources/assets/gendustry/models/item/youth_elite_upgrade.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "gendustry:item/youth_elite_upgrade"
  }
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/activity_simulator_elite_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:elite_upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:activity_simulator_elite_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:activity_simulator_elite_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/advanced_mutatron.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:advanced_mutatron"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:advanced_mutatron"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/automation_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:automation_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:automation_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/blank_gene_sample.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:ingots/tin"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:blank_gene_sample"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:blank_gene_sample"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/blank_genetic_template.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:blank_gene_sample"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:blank_genetic_template"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:blank_genetic_template"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/climate_control_module.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:dusts/redstone"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:climate_control_module"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:climate_control_module"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/cooler_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:cooler_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:cooler_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/dna_extractor.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:dna_extractor"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:dna_extractor"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/dryer_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:dryer_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:dryer_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/elite_upgrade_frame.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:storage_blocks/gold"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:elite_upgrade_frame"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:elite_upgrade_frame"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/environmental_processor.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gems/diamond"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:environmental_processor"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:environmental_processor"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/fertility_elite_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:elite_upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:fertility_elite_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:fertility_elite_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/genetic_transposer.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:ingots/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:genetic_transposer"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:genetic_transposer"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/genetics_processor.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gems/diamond"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:genetics_processor"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:genetics_processor"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/heater_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:heater_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:heater_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/humidifier_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:humidifier_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:humidifier_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/immutable_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:immutable_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:immutable_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/imprinter.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:imprinter"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:imprinter"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/industrial_apiary.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:industrial_apiary"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:industrial_apiary"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/labware.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:glass_panes"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:labware"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:labware"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/lifespan_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:lifespan_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:lifespan_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/lighting_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:lighting_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:lighting_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/mutagen_producer.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:mutagen_producer"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:mutagen_producer"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/mutation_elite_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:elite_upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:mutation_elite_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:mutation_elite_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/mutatron.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:ingots/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:mutatron"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:mutatron"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/nether_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:nether_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:nether_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/pollination_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:pollination_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:pollination_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/power_module.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:storage_blocks/redstone"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:power_module"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:power_module"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/productivity_elite_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:elite_upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:productivity_elite_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:productivity_elite_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/productivity_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:productivity_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:productivity_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/protein_liquefier.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:protein_liquefier"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:protein_liquefier"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/receptacle.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:ingots/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:receptacle"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:receptacle"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/replicator.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:replicator"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:replicator"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/sampler.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:gears/bronze"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:sampler"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:sampler"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/scrubber_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:scrubber_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:scrubber_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/sieve_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:sieve_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:sieve_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/sky_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:sky_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:sky_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/stabilizer_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:stabilizer_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:stabilizer_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/territory_elite_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:elite_upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:territory_elite_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:territory_elite_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/territory_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:territory_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:territory_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/upgrade_frame.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:ingots/tin"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:upgrade_frame"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:upgrade_frame"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/weatherproof_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:weatherproof_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:weatherproof_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/misc/youth_elite_upgrade.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:elite_upgrade_frame"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:youth_elite_upgrade"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:youth_elite_upgrade"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/advancements/recipes/tools/pollen_kit.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "gendustry:labware"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "gendustry:pollen_kit"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "gendustry:pollen_kit"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/advanced_mutatron.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:advanced_mutatron"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/advanced_mutatron"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/dna_extractor.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:dna_extractor"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/dna_extractor"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/genetic_transposer.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:genetic_transposer"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/genetic_transposer"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/imprinter.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:imprinter"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/imprinter"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/industrial_apiary.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:industrial_apiary"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/industrial_apiary"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/mutagen_producer.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:mutagen_producer"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/mutagen_producer"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/mutatron.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:mutatron"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/mutatron"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/protein_liquefier.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:protein_liquefier"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/protein_liquefier"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/replicator.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:replicator"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/replicator"
}
```

### src/generated/resources/data/gendustry/loot_tables/blocks/sampler.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "gendustry:sampler"
        }
      ],
      "rolls": 1.0
    }
  ],
  "random_sequence": "gendustry:blocks/sampler"
}
```

### src/generated/resources/data/gendustry/recipes/activity_simulator_elite_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:elite_upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "L": {
      "item": "gendustry:lighting_upgrade"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    },
    "S": {
      "item": "gendustry:sky_upgrade"
    },
    "W": {
      "item": "gendustry:weatherproof_upgrade"
    }
  },
  "pattern": [
    "RWR",
    "LFS",
    "IGI"
  ],
  "result": {
    "item": "gendustry:activity_simulator_elite_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/advanced_mutatron.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "tag": "forge:gems/quartz"
    },
    "M": {
      "item": "gendustry:power_module"
    },
    "P": {
      "item": "gendustry:genetics_processor"
    },
    "S": {
      "item": "gendustry:mutatron"
    }
  },
  "pattern": [
    "GHG",
    "PSP",
    "GMG"
  ],
  "result": {
    "item": "gendustry:advanced_mutatron"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/automation_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "minecraft:comparator"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    " G ",
    "RFR",
    " C "
  ],
  "result": {
    "item": "gendustry:automation_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/blank_gene_sample.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "I": {
      "tag": "forge:ingots/tin"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    " I ",
    "IRI",
    " I "
  ],
  "result": {
    "item": "gendustry:blank_gene_sample"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/blank_gene_sample_wipe_dna.json

```json
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.1,
  "ingredient": {
    "item": "gendustry:gene_sample"
  },
  "result": "gendustry:blank_gene_sample"
}
```

### src/generated/resources/data/gendustry/recipes/blank_genetic_template.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "tag": "forge:gems/diamond"
    },
    "I": {
      "item": "gendustry:blank_gene_sample"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "RIR",
    "IDI",
    "RIR"
  ],
  "result": {
    "item": "gendustry:blank_genetic_template"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/blank_genetic_template_wipe_dna.json

```json
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.1,
  "ingredient": {
    "item": "gendustry:genetic_template"
  },
  "result": "gendustry:blank_genetic_template"
}
```

### src/generated/resources/data/gendustry/recipes/climate_control_module.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "B": {
      "tag": "forge:ingots/bronze"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "BRB",
    "BGB",
    "BRB"
  ],
  "result": {
    "item": "gendustry:climate_control_module"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/combine_genetic_template.json

```json
{
  "type": "gendustry:genetic_template",
  "category": "misc"
}
```

### src/generated/resources/data/gendustry/recipes/cooler_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "gendustry:climate_control_module"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "item": "minecraft:ice"
    }
  },
  "pattern": [
    "PPP",
    "IFI",
    "ICI"
  ],
  "result": {
    "item": "gendustry:cooler_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/dna/butterfly.json

```json
{
  "type": "gendustry:dna",
  "amount": 200,
  "species_type": "forestry:butterfly_species",
  "stage": "butterfly"
}
```

### src/generated/resources/data/gendustry/recipes/dna/caterpillar.json

```json
{
  "type": "gendustry:dna",
  "amount": 1000,
  "species_type": "forestry:butterfly_species",
  "stage": "caterpillar"
}
```

### src/generated/resources/data/gendustry/recipes/dna/cocoon.json

```json
{
  "type": "gendustry:dna",
  "amount": 1000,
  "species_type": "forestry:butterfly_species",
  "stage": "cocoon"
}
```

### src/generated/resources/data/gendustry/recipes/dna/drone.json

```json
{
  "type": "gendustry:dna",
  "amount": 100,
  "species_type": "forestry:bee_species",
  "stage": "drone"
}
```

### src/generated/resources/data/gendustry/recipes/dna/larvae.json

```json
{
  "type": "gendustry:dna",
  "amount": 300,
  "species_type": "forestry:bee_species",
  "stage": "larvae"
}
```

### src/generated/resources/data/gendustry/recipes/dna/pollen.json

```json
{
  "type": "gendustry:dna",
  "amount": 400,
  "species_type": "forestry:tree_species",
  "stage": "pollen"
}
```

### src/generated/resources/data/gendustry/recipes/dna/princess.json

```json
{
  "type": "gendustry:dna",
  "amount": 500,
  "species_type": "forestry:bee_species",
  "stage": "princess"
}
```

### src/generated/resources/data/gendustry/recipes/dna/queen.json

```json
{
  "type": "gendustry:dna",
  "amount": 600,
  "species_type": "forestry:bee_species",
  "stage": "queen"
}
```

### src/generated/resources/data/gendustry/recipes/dna/sapling.json

```json
{
  "type": "gendustry:dna",
  "amount": 100,
  "species_type": "forestry:tree_species",
  "stage": "sapling"
}
```

### src/generated/resources/data/gendustry/recipes/dna/serum.json

```json
{
  "type": "gendustry:dna",
  "amount": 800,
  "species_type": "forestry:butterfly_species",
  "stage": "serum"
}
```

### src/generated/resources/data/gendustry/recipes/dna_extractor.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "item": "gendustry:genetics_processor"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "item": "minecraft:hopper"
    },
    "P": {
      "item": "gendustry:power_module"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "GHG",
    "DSD",
    "GPG"
  ],
  "result": {
    "item": "gendustry:dna_extractor"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/dryer_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "gendustry:climate_control_module"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "item": "minecraft:dead_bush"
    }
  },
  "pattern": [
    "PPP",
    "IFI",
    "ICI"
  ],
  "result": {
    "item": "gendustry:dryer_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/elite_upgrade_frame.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:storage_blocks/gold"
    },
    "R": {
      "item": "gendustry:power_module"
    },
    "T": {
      "item": "minecraft:ghast_tear"
    }
  },
  "pattern": [
    "ITI",
    "RGR",
    "ITI"
  ],
  "result": {
    "count": 2,
    "item": "gendustry:elite_upgrade_frame"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/environmental_processor.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "tag": "forge:gems/diamond"
    },
    "P": {
      "tag": "forge:ingots/gold"
    },
    "Q": {
      "tag": "forge:gems/lapis"
    }
  },
  "pattern": [
    "DQD",
    "QPQ",
    "DQD"
  ],
  "result": {
    "item": "gendustry:environmental_processor"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/fertility_elite_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "item": "gendustry:bucket_liquid_dna"
    },
    "F": {
      "item": "gendustry:elite_upgrade_frame"
    },
    "M": {
      "item": "gendustry:bucket_protein"
    },
    "P": {
      "item": "gendustry:genetics_processor"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "RMR",
    "PFP",
    "RDR"
  ],
  "result": {
    "item": "gendustry:fertility_elite_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/genetic_transposer.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "item": "gendustry:genetics_processor"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "M": {
      "item": "gendustry:power_module"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "GIG",
    "HSH",
    "GMG"
  ],
  "result": {
    "item": "gendustry:genetic_transposer"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/genetics_processor.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "tag": "forge:gems/diamond"
    },
    "P": {
      "tag": "forge:ender_pearls"
    },
    "Q": {
      "tag": "forge:gems/quartz"
    }
  },
  "pattern": [
    "DQD",
    "QPQ",
    "DQD"
  ],
  "result": {
    "item": "gendustry:genetics_processor"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/heater_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "gendustry:climate_control_module"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "item": "minecraft:blaze_powder"
    }
  },
  "pattern": [
    "PPP",
    "IFI",
    "ICI"
  ],
  "result": {
    "item": "gendustry:heater_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/humidifier_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "gendustry:climate_control_module"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "tag": "forge:mushrooms"
    }
  },
  "pattern": [
    "PPP",
    "IFI",
    "ICI"
  ],
  "result": {
    "item": "gendustry:humidifier_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/immutable_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "P": {
      "item": "gendustry:genetics_processor"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    },
    "X": {
      "item": "minecraft:redstone_torch"
    }
  },
  "pattern": [
    "RPR",
    "RFR",
    "GXG"
  ],
  "result": {
    "item": "gendustry:immutable_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/imprinter.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "item": "gendustry:genetics_processor"
    },
    "M": {
      "item": "gendustry:power_module"
    },
    "R": {
      "item": "gendustry:receptacle"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "GHG",
    "RSR",
    "GMG"
  ],
  "result": {
    "item": "gendustry:imprinter"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/industrial_apiary.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "tag": "forge:glass"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "P": {
      "item": "minecraft:piston"
    },
    "R": {
      "item": "gendustry:receptacle"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "CRC",
    "CSC",
    "GPG"
  ],
  "result": {
    "item": "gendustry:industrial_apiary"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/labware.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "tag": "forge:gems/diamond"
    },
    "G": {
      "tag": "forge:glass_panes"
    }
  },
  "pattern": [
    "G G",
    "G G",
    " D "
  ],
  "result": {
    "count": 2,
    "item": "gendustry:labware"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/lifespan_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "E": {
      "item": "minecraft:wither_rose"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "ERE",
    "RFR",
    "IGI"
  ],
  "result": {
    "item": "gendustry:lifespan_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/lighting_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "item": "minecraft:glowstone"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "DRD",
    "RFR",
    "IGI"
  ],
  "result": {
    "item": "gendustry:lighting_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/mutagen/glowstone.json

```json
{
  "type": "gendustry:mutagen",
  "amount": 800,
  "ingredient": {
    "item": "minecraft:glowstone"
  }
}
```

### src/generated/resources/data/gendustry/recipes/mutagen/glowstone_dust.json

```json
{
  "type": "gendustry:mutagen",
  "amount": 200,
  "ingredient": {
    "item": "minecraft:glowstone_dust"
  }
}
```

### src/generated/resources/data/gendustry/recipes/mutagen/redstone.json

```json
{
  "type": "gendustry:mutagen",
  "amount": 100,
  "ingredient": {
    "item": "minecraft:redstone"
  }
}
```

### src/generated/resources/data/gendustry/recipes/mutagen/redstone_block.json

```json
{
  "type": "gendustry:mutagen",
  "amount": 900,
  "ingredient": {
    "item": "minecraft:redstone_block"
  }
}
```

### src/generated/resources/data/gendustry/recipes/mutagen_producer.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "minecraft:cauldron"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "item": "minecraft:hopper"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "item": "gendustry:power_module"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "IHI",
    "PSP",
    "GCG"
  ],
  "result": {
    "item": "gendustry:mutagen_producer"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/mutation_elite_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "item": "gendustry:bucket_liquid_dna"
    },
    "F": {
      "item": "gendustry:elite_upgrade_frame"
    },
    "M": {
      "item": "gendustry:bucket_mutagen"
    },
    "P": {
      "item": "gendustry:genetics_processor"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "RMR",
    "PFP",
    "RDR"
  ],
  "result": {
    "item": "gendustry:mutation_elite_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/mutatron.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "minecraft:cauldron"
    },
    "H": {
      "item": "gendustry:genetics_processor"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "M": {
      "item": "gendustry:power_module"
    },
    "R": {
      "item": "gendustry:receptacle"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "RHI",
    "MSR",
    "RCI"
  ],
  "result": {
    "item": "gendustry:mutatron"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/nether_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "minecraft:crimson_nylium"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "S": {
      "item": "minecraft:soul_sand"
    },
    "W": {
      "item": "minecraft:warped_nylium"
    }
  },
  "pattern": [
    "CWS",
    "IFI",
    "IGI"
  ],
  "result": {
    "item": "gendustry:nether_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/pollen_kit.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "category": "equipment",
  "ingredients": [
    {
      "item": "gendustry:labware"
    },
    {
      "tag": "forge:string"
    },
    {
      "item": "minecraft:paper"
    }
  ],
  "result": {
    "item": "gendustry:pollen_kit"
  }
}
```

### src/generated/resources/data/gendustry/recipes/pollination_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "tag": "minecraft:small_flowers"
    }
  },
  "pattern": [
    "PPP",
    "IFI",
    "IGI"
  ],
  "result": {
    "item": "gendustry:pollination_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/power_module.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/gold"
    },
    "P": {
      "item": "minecraft:piston"
    },
    "R": {
      "tag": "forge:storage_blocks/redstone"
    }
  },
  "pattern": [
    "GIG",
    "PRP",
    "GIG"
  ],
  "result": {
    "item": "gendustry:power_module"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/productivity_elite_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "C": {
      "item": "forestry:pollen_cluster_normal"
    },
    "D": {
      "tag": "forge:gems/emerald"
    },
    "F": {
      "item": "gendustry:elite_upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "R": {
      "item": "forestry:royal_jelly"
    }
  },
  "pattern": [
    "DRD",
    "RFR",
    "CGC"
  ],
  "result": {
    "item": "gendustry:productivity_elite_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/productivity_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "tag": "forge:gems/emerald"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "DRD",
    "RFR",
    "IGI"
  ],
  "result": {
    "item": "gendustry:productivity_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/protein/beef.json

```json
{
  "type": "gendustry:protein",
  "amount": 500,
  "ingredient": {
    "item": "minecraft:beef"
  }
}
```

### src/generated/resources/data/gendustry/recipes/protein/cod.json

```json
{
  "type": "gendustry:protein",
  "amount": 250,
  "ingredient": {
    "item": "minecraft:cod"
  }
}
```

### src/generated/resources/data/gendustry/recipes/protein/porkchop.json

```json
{
  "type": "gendustry:protein",
  "amount": 500,
  "ingredient": {
    "item": "minecraft:porkchop"
  }
}
```

### src/generated/resources/data/gendustry/recipes/protein/pufferfish.json

```json
{
  "type": "gendustry:protein",
  "amount": 250,
  "ingredient": {
    "item": "minecraft:pufferfish"
  }
}
```

### src/generated/resources/data/gendustry/recipes/protein/rabbit.json

```json
{
  "type": "gendustry:protein",
  "amount": 250,
  "ingredient": {
    "item": "minecraft:rabbit"
  }
}
```

### src/generated/resources/data/gendustry/recipes/protein/salmon.json

```json
{
  "type": "gendustry:protein",
  "amount": 250,
  "ingredient": {
    "item": "minecraft:salmon"
  }
}
```

### src/generated/resources/data/gendustry/recipes/protein/tropical_fish.json

```json
{
  "type": "gendustry:protein",
  "amount": 250,
  "ingredient": {
    "item": "minecraft:tropical_fish"
  }
}
```

### src/generated/resources/data/gendustry/recipes/protein_liquefier.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "item": "minecraft:hopper"
    },
    "M": {
      "item": "gendustry:power_module"
    },
    "P": {
      "item": "minecraft:piston"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "GHG",
    "PSP",
    "GMG"
  ],
  "result": {
    "item": "gendustry:protein_liquefier"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/receptacle.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "item": "minecraft:light_weighted_pressure_plate"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "tag": "forge:glass_panes"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "III",
    "IPI",
    "RGR"
  ],
  "result": {
    "item": "gendustry:receptacle"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/replicator.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "item": "gendustry:genetics_processor"
    },
    "M": {
      "item": "gendustry:power_module"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "GHG",
    "MSM",
    "GHG"
  ],
  "result": {
    "item": "gendustry:replicator"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/sampler.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "tag": "forge:gems/diamond"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "H": {
      "item": "gendustry:genetics_processor"
    },
    "M": {
      "item": "gendustry:power_module"
    },
    "P": {
      "item": "gendustry:receptacle"
    },
    "S": {
      "item": "forestry:sturdy_machine"
    }
  },
  "pattern": [
    "GHG",
    "PSD",
    "GMG"
  ],
  "result": {
    "item": "gendustry:sampler"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/scrubber_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "S": {
      "item": "forestry:silk_wisp"
    }
  },
  "pattern": [
    "SGS",
    "GFG",
    "SGS"
  ],
  "result": {
    "item": "gendustry:scrubber_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/sieve_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "S": {
      "item": "forestry:silk_wisp"
    }
  },
  "pattern": [
    "SSS",
    "SFS",
    "IGI"
  ],
  "result": {
    "item": "gendustry:sieve_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/sky_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "L": {
      "tag": "forge:gems/lapis"
    }
  },
  "pattern": [
    "LLL",
    "IFI",
    "IGI"
  ],
  "result": {
    "item": "gendustry:sky_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/stabilizer_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "P": {
      "item": "gendustry:genetics_processor"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "RPR",
    "RFR",
    "GIG"
  ],
  "result": {
    "item": "gendustry:stabilizer_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/territory_elite_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "B": {
      "item": "minecraft:grass_block"
    },
    "F": {
      "item": "gendustry:elite_upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "RBR",
    "BFB",
    "RGR"
  ],
  "result": {
    "item": "gendustry:territory_elite_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/territory_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "B": {
      "item": "minecraft:grass_block"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "RBR",
    "BFB",
    "RGR"
  ],
  "result": {
    "item": "gendustry:territory_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/upgrade_frame.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "G": {
      "tag": "forge:nuggets/gold"
    },
    "I": {
      "tag": "forge:ingots/tin"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "IGI",
    "R R",
    "IGI"
  ],
  "result": {
    "count": 2,
    "item": "gendustry:upgrade_frame"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/weatherproof_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "D": {
      "item": "forestry:beeswax"
    },
    "F": {
      "item": "gendustry:upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    }
  },
  "pattern": [
    "DRD",
    "RFR",
    "IGI"
  ],
  "result": {
    "item": "gendustry:weatherproof_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/recipes/youth_elite_upgrade.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "F": {
      "item": "gendustry:elite_upgrade_frame"
    },
    "G": {
      "tag": "forge:gears/bronze"
    },
    "I": {
      "tag": "forge:ingots/bronze"
    },
    "R": {
      "tag": "forge:dusts/redstone"
    },
    "T": {
      "item": "minecraft:ghast_tear"
    }
  },
  "pattern": [
    "TRT",
    "RFR",
    "IGI"
  ],
  "result": {
    "item": "gendustry:youth_elite_upgrade"
  },
  "show_notification": true
}
```

### src/generated/resources/data/gendustry/tags/items/upgrades.json

```json
{
  "values": [
    "gendustry:automation_upgrade",
    "gendustry:heater_upgrade",
    "gendustry:cooler_upgrade",
    "gendustry:humidifier_upgrade",
    "gendustry:dryer_upgrade",
    "gendustry:pollination_upgrade",
    "gendustry:scrubber_upgrade",
    "gendustry:nether_upgrade",
    "gendustry:lifespan_upgrade",
    "gendustry:lighting_upgrade",
    "gendustry:productivity_upgrade",
    "gendustry:weatherproof_upgrade",
    "gendustry:sieve_upgrade",
    "gendustry:sky_upgrade",
    "gendustry:stabilizer_upgrade",
    "gendustry:territory_upgrade",
    "gendustry:immutable_upgrade",
    "gendustry:mutation_elite_upgrade",
    "gendustry:activity_simulator_elite_upgrade",
    "gendustry:productivity_elite_upgrade",
    "gendustry:territory_elite_upgrade",
    "gendustry:youth_elite_upgrade",
    "gendustry:fertility_elite_upgrade"
  ]
}
```

### src/generated/resources/data/minecraft/tags/blocks/mineable/pickaxe.json

```json
{
  "values": [
    "gendustry:industrial_apiary",
    "gendustry:mutagen_producer",
    "gendustry:dna_extractor",
    "gendustry:protein_liquefier",
    "gendustry:sampler",
    "gendustry:mutatron",
    "gendustry:advanced_mutatron",
    "gendustry:imprinter",
    "gendustry:genetic_transposer",
    "gendustry:replicator"
  ]
}
```

### src/main/java/thedarkcolour/gendustry/api/GendustryTags.java

```java
package thedarkcolour.gendustry.api;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import thedarkcolour.gendustry.Gendustry;

public class GendustryTags {
	public static class Items {
		public static final TagKey<Item> UPGRADES = itemTag("upgrades");
	}

	private static TagKey<Item> itemTag(String name) {
		return ItemTags.create(Gendustry.loc(name));
	}
}
```

### src/main/java/thedarkcolour/gendustry/api/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.api;
```

### src/main/java/thedarkcolour/gendustry/block/GendustryMachineBlock.java

```java
package thedarkcolour.gendustry.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import forestry.core.blocks.BlockBase;

public class GendustryMachineBlock extends BlockBase<GendustryMachineType> {
	public GendustryMachineBlock(GendustryMachineType blockType) {
		super(blockType, Properties.of().sound(SoundType.METAL).mapColor(MapColor.SAND).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops());
	}
}
```

### src/main/java/thedarkcolour/gendustry/block/GendustryMachineType.java

```java
package thedarkcolour.gendustry.block;

import java.util.Locale;

import forestry.core.blocks.IBlockType;
import forestry.core.blocks.IMachineProperties;
import forestry.core.blocks.MachineProperties;
import forestry.core.tiles.IForestryTicker;
import forestry.core.tiles.TileForestry;
import forestry.modules.features.FeatureTileType;

import thedarkcolour.gendustry.blockentity.AdvancedMutatronBlockEntity;
import thedarkcolour.gendustry.blockentity.DnaExtractorBlockEntity;
import thedarkcolour.gendustry.blockentity.GeneticTransposerBlockEntity;
import thedarkcolour.gendustry.blockentity.ImprinterBlockEntity;
import thedarkcolour.gendustry.blockentity.IndustrialApiaryBlockEntity;
import thedarkcolour.gendustry.blockentity.MutagenProducerBlockEntity;
import thedarkcolour.gendustry.blockentity.MutatronBlockEntity;
import thedarkcolour.gendustry.blockentity.ProteinLiquefierBlockEntity;
import thedarkcolour.gendustry.blockentity.ReplicatorBlockEntity;
import thedarkcolour.gendustry.blockentity.SamplerBlockEntity;
import thedarkcolour.gendustry.registry.GBlockEntities;

public enum GendustryMachineType implements IBlockType {
	INDUSTRIAL_APIARY(GBlockEntities.INDUSTRIAL_APIARY, IndustrialApiaryBlockEntity::serverTick, IndustrialApiaryBlockEntity::clientTick),
	MUTAGEN_PRODUCER(GBlockEntities.MUTAGEN_PRODUCER, MutagenProducerBlockEntity::serverTick),
	DNA_EXTRACTOR(GBlockEntities.DNA_EXTRACTOR, DnaExtractorBlockEntity::serverTick),
	PROTEIN_LIQUEFIER(GBlockEntities.PROTEIN_LIQUEFIER, ProteinLiquefierBlockEntity::serverTick),
	SAMPLER(GBlockEntities.SAMPLER, SamplerBlockEntity::serverTick),
	MUTATRON(GBlockEntities.MUTATRON, MutatronBlockEntity::serverTick),
	ADVANCED_MUTATRON(GBlockEntities.ADVANCED_MUTATRON, AdvancedMutatronBlockEntity::serverTick),
	IMPRINTER(GBlockEntities.IMPRINTER, ImprinterBlockEntity::serverTick),
	GENETIC_TRANSPOSER(GBlockEntities.GENETIC_TRANSPOSER, GeneticTransposerBlockEntity::serverTick),
	REPLICATOR(GBlockEntities.REPLICATOR, ReplicatorBlockEntity::serverTick);

	private final IMachineProperties<?> properties;

	<T extends TileForestry> GendustryMachineType(FeatureTileType<T> teClass, IForestryTicker<T> serverTicker) {
		String name = name().toLowerCase(Locale.ENGLISH);

		this.properties = new MachineProperties.Builder<>(teClass, name)
				.setServerTicker(serverTicker)
				.create();
	}

	<T extends TileForestry> GendustryMachineType(FeatureTileType<T> teClass, IForestryTicker<T> serverTicker, IForestryTicker<T> clientTicker) {
		String name = name().toLowerCase(Locale.ENGLISH);

		this.properties = new MachineProperties.Builder<>(teClass, name)
				.setServerTicker(serverTicker)
				.setClientTicker(clientTicker)
				.create();
	}

	@Override
	public IMachineProperties<?> getMachineProperties() {
		return this.properties;
	}

	@Override
	public String getSerializedName() {
		return this.properties.getSerializedName();
	}
}
```

### src/main/java/thedarkcolour/gendustry/block/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.block;
```

### src/main/java/thedarkcolour/gendustry/blockentity/AbstractMutatronBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.fluids.capability.IFluidHandler;

import forestry.api.IForestryApi;
import forestry.api.core.IErrorLogic;
import forestry.api.genetics.IBreedingTracker;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IMutation;
import forestry.api.genetics.ISpecies;
import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.alleles.AllelePair;
import forestry.api.genetics.alleles.IChromosome;
import forestry.api.genetics.alleles.IKaryotype;
import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.api.plugin.IGenomeBuilder;
import forestry.core.fluids.FilteredTank;
import forestry.core.fluids.FluidHelper;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.menu.AdvancedMutatronMenu;
import thedarkcolour.gendustry.menu.MutatronMenu;
import thedarkcolour.gendustry.registry.GBlockEntities;
import thedarkcolour.gendustry.registry.GFluids;

public abstract class AbstractMutatronBlockEntity extends PoweredTankBlockEntity {
	private static final int ENERGY_PER_WORK_CYCLE = 100000;
	private static final int TICKS_PER_WORK_CYCLE = 40;

	public static final String HINTS_KEY = "gendustry.mutatron";

	private final FilteredTank mutagenTank;
	protected final MutatronInventory inventory;
	public final boolean isAdvanced;

	@Nullable
	private IMutation<?> currentMutation;
	protected ItemStack currentPrimary = ItemStack.EMPTY;
	protected ItemStack currentSecondary = ItemStack.EMPTY;
	@Nullable
	private UUID lastPlayer;

	public AbstractMutatronBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state, 10000, 1000000);

		this.mutagenTank = new FilteredTank(10000).setFilters(Set.of(GFluids.MUTAGEN.fluid()));
		this.tankManager.add(this.mutagenTank);
		this.inventory = new MutatronInventory(this);
		this.isAdvanced = type == GBlockEntities.ADVANCED_MUTATRON.tileType();

		setInternalInventory(this.inventory);
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	@Override
	public void serverTick(Level level, BlockPos pos, BlockState state) {
		super.serverTick(level, pos, state);

		if (updateOnInterval(20)) {
			FluidHelper.drainContainers(this.tankManager, this, MutatronInventory.SLOT_CAN_INPUT);
		}
	}

	@Override
	public boolean hasWork() {
		IErrorLogic errors = getErrorLogic();

		// Check mutagen
		boolean noMutagen = this.mutagenTank.getFluid().getAmount() < 1000;
		errors.setCondition(noMutagen, GendustryError.NO_MUTAGEN);

		// Check labware
		boolean noLabware = this.inventory.getItem(MutatronInventory.SLOT_LABWARE).isEmpty();
		errors.setCondition(noLabware, GendustryError.NO_LABWARE);

		// Make sure inputs haven't changed
		if (hasMutation()) {
			if (this.inventory.getItem(MutatronInventory.SLOT_PRIMARY) != this.currentPrimary
					|| this.inventory.getItem(MutatronInventory.SLOT_SECONDARY) != this.currentSecondary
					|| this.currentSecondary.isEmpty()
					|| this.currentSecondary.isEmpty()
			) {
				onMutationsUpdated(List.of(), ItemStack.EMPTY, ItemStack.EMPTY);
			}
		}

		// Try to choose a new recipe
		if (!hasMutation()) {
			ItemStack primaryStack = this.inventory.getItem(MutatronInventory.SLOT_PRIMARY);
			ItemStack secondaryStack = this.inventory.getItem(MutatronInventory.SLOT_SECONDARY);
			IIndividual primary = IIndividualHandlerItem.getIndividual(primaryStack);
			IIndividual secondary = IIndividualHandlerItem.getIndividual(secondaryStack);

			// Check if two mates are present
			boolean noMates = primary == null || secondary == null;
			errors.setCondition(noMates, GendustryError.NO_MATES);

			if (noMates) {
				return false;
			}

			// Check if two mates are of same species type
			boolean incompatible = primary.getType() != secondary.getType();
			errors.setCondition(incompatible, GendustryError.INCOMPATIBLE_SPECIES);

			if (incompatible) {
				return false;
			}

			// Check if mutations are possible
			List<IMutation<ISpecies<?>>> mutations = IForestryApi.INSTANCE.getGeneticManager().getMutations(primary.getType()).getCombinations(primary.getSpecies(), secondary.getSpecies());
			boolean noMutations = mutations.isEmpty();
			errors.setCondition(noMutations, GendustryError.NO_MUTATIONS);

			if (noMutations) {
				return false;
			}

			// Choose mutation
			onMutationsUpdated(mutations, primaryStack, secondaryStack);
		}

		return !noMutagen && !noLabware;
	}

	protected boolean hasMutation() {
		return this.currentMutation != null;
	}

	protected abstract void onMutationsUpdated(List<IMutation<ISpecies<?>>> mutations, ItemStack primaryStack, ItemStack secondaryStack);

	@Override
	protected boolean workCycle() {
		// Check for room in result slot
		if (!this.inventory.getItem(MutatronInventory.SLOT_RESULT).isEmpty()) {
			return false;
		}
		// Consume inputs
		ItemStack primary = this.inventory.removeItem(MutatronInventory.SLOT_PRIMARY, 1);
		this.inventory.removeItem(MutatronInventory.SLOT_SECONDARY, 1);
		this.inventory.removeItem(MutatronInventory.SLOT_LABWARE, 1);
		this.mutagenTank.drain(1000, IFluidHandler.FluidAction.EXECUTE);

		// Set output
		IIndividualHandlerItem.ifPresent(primary, individual -> {
			ISpeciesType<?, ?> speciesType = individual.getType();

			// Create new genome
			IGenome genome = createMutatedGenome(this.currentMutation);

			// Create new individual
			IIndividual newIndividual = individual.copyWithGenome(genome);
			newIndividual.setMate(genome);
			ItemStack result = newIndividual.createStack(speciesType.getTypeForMutation(2));

			this.inventory.setItem(MutatronInventory.SLOT_RESULT, result);

			// Register mutation for the last player to access this block
			if (this.lastPlayer != null) {
				Player player = this.level.getPlayerByUUID(this.lastPlayer);

				if (player == null) {
					this.lastPlayer = null;
				} else {
					IBreedingTracker tracker = speciesType.getBreedingTracker(this.level, player.getGameProfile());
					tracker.registerMutation(this.currentMutation);
				}
			}
		});

		// Reset state
		setCurrentMutation(null, ItemStack.EMPTY, ItemStack.EMPTY);

		return true;
	}

	// todo make this a method in base Forestry
	public static IGenome createMutatedGenome(IMutation<?> mutation) {
		IKaryotype karyotype = mutation.getType().getKaryotype();
		IGenomeBuilder builder = karyotype.createGenomeBuilder();

		ImmutableList<AllelePair<?>> allelePairs = mutation.getResultAlleles();
		ImmutableList<IChromosome<?>> chromosomes = karyotype.getChromosomes();

		for (int i = 0; i < chromosomes.size(); i++) {
			IChromosome<?> chromosome = chromosomes.get(i);
			AllelePair<?> pair = allelePairs.get(i);

			builder.setUnchecked(chromosome, pair);
		}

		return builder.build();
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		// Track last player who opened
		this.lastPlayer = player.getUUID();

		return this.isAdvanced ? new AdvancedMutatronMenu(windowId, playerInv, (AdvancedMutatronBlockEntity) this) : new MutatronMenu(windowId, playerInv, (MutatronBlockEntity) this);
	}

	public void setCurrentMutation(@Nullable IMutation<?> mutation, ItemStack primary, ItemStack secondary) {
		this.currentMutation = mutation;
		this.currentPrimary = primary;
		this.currentSecondary = secondary;
	}

	@Nullable
	public IMutation<?> getCurrentMutation() {
		return this.currentMutation;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/AdvancedMutatronBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import forestry.api.genetics.IMutation;
import forestry.api.genetics.ISpecies;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.registry.GBlockEntities;

public class AdvancedMutatronBlockEntity extends AbstractMutatronBlockEntity {
	private List<IMutation<ISpecies<?>>> possibilities = List.of();
	@Nullable
	private IMutation<?> lastChoice = null;

	public AdvancedMutatronBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.ADVANCED_MUTATRON.tileType(), pos, state);
	}

	@Override
	protected void onMutationsUpdated(List<IMutation<ISpecies<?>>> mutations, ItemStack primaryStack, ItemStack secondaryStack) {
		this.possibilities = mutations;
		this.currentPrimary = primaryStack;
		this.currentSecondary = secondaryStack;

		if (this.lastChoice != null && this.possibilities.contains(this.lastChoice)) {
			setCurrentMutation(this.lastChoice, primaryStack, secondaryStack);
		}
	}

	@Override
	public void setCurrentMutation(@Nullable IMutation<?> mutation, ItemStack primary, ItemStack secondary) {
		super.setCurrentMutation(mutation, primary, secondary);

		if (mutation != null) {
			this.lastChoice = mutation;
		}
	}

	@Override
	public boolean hasWork() {
		boolean canWork = super.hasWork();
		boolean noSelection = getErrorLogic().setCondition(getCurrentMutation() == null, GendustryError.NO_SELECTION);

		return canWork && !noSelection;
	}

	@Override
	protected boolean hasMutation() {
		return !this.possibilities.isEmpty();
	}

	public List<IMutation<ISpecies<?>>> getPossibilities() {
		return this.possibilities;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ApiaryModifiers.java

```java
/*
 * Copyright (c) bdew, 2013 - 2017
 * https://github.com/bdew/gendustry
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package thedarkcolour.gendustry.blockentity;

import javax.annotation.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;

import forestry.api.core.HumidityType;
import forestry.api.core.TemperatureType;

/**
 * Stores current modifiers from all upgrades in an apiary
 */
public class ApiaryModifiers {
    /**
     * Territory modifier, multiplicative
     */
    public float territory = 1;

    /**
     * Mutation chance modifier, multiplicative
     * max safe = 10 (degenerating - offspring become unnatural)
     */
    public float mutation = 1;

    /**
     * Lifespan modifier, multiplicative, higher = longer
     */
    public float lifespan = 1;

    /**
     * Production modifier - increases chance to get products each tick, multiplicative
     * max safe = 10 (overworked - becomes unnatural)
     */
    public float production = 1;

    /**
     * Flowering and pollination chance modifier, multiplicative
     */
    public float flowering = 1;

    /**
     * Genetic decay chance, applies to fatigued unnatural bees, multiplicative
     */
    public float geneticDecay = 1;

    /**
     * Sealed - bees can work in rain without the required traits
     */
    public boolean isSealed = false;

    /**
     * Self lighted - bees can work in the night without the required traits, makes block emit light
     */
    public boolean isSelfLighted = false;

    /**
     * Sunlight simulated - bees can work in caves without the required traits
     */
    public boolean isSunlightSimulated = false;

    /**
     * Automated - will auto move offspring to the right slots to allow further breeding
     */
    public boolean isAutomated = false;

    /**
     * Allows collection of pollen from trees
     */
    public boolean isCollectingPollen = false;

    /**
     * If set - overrides biome as seen by jubilance checks, etc.
     */
    @Nullable
    public Holder<Biome> biomeOverride = null;

    /**
     * Energy use modifier, multiplicative
     */
    public float energy = 1;

    /**
     * Temperature type used for climate checks
     */
    public TemperatureType temperature = TemperatureType.NORMAL;

    /**
     * Humidity type used for climate checks
     */
    public HumidityType humidity = HumidityType.NORMAL;
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/DnaExtractorBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import forestry.api.core.ForestryError;
import forestry.api.core.IError;
import forestry.api.genetics.capability.IIndividualHandlerItem;

import thedarkcolour.gendustry.recipe.DnaRecipe;
import thedarkcolour.gendustry.recipe.cache.DnaRecipeCache;
import thedarkcolour.gendustry.registry.GBlockEntities;
import thedarkcolour.gendustry.registry.GFluids;
import org.jetbrains.annotations.Nullable;

public class DnaExtractorBlockEntity extends ProducerBlockEntity<DnaExtractorBlockEntity, DnaRecipe> {
	private static final int ENERGY_PER_WORK_CYCLE = 80000;
	private static final int TICKS_PER_WORK_CYCLE = 50;

	public static final String HINTS_KEY = "gendustry.dna_extractor";

	public DnaExtractorBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.DNA_EXTRACTOR, GFluids.LIQUID_DNA, true, pos, state);
	}

	@Override
	public boolean isValidInput(ItemStack input) {
		return IIndividualHandlerItem.isIndividual(input);
	}

	@Nullable
	@Override
	public DnaRecipe getRecipe(ItemStack input) {
		IIndividualHandlerItem handler = IIndividualHandlerItem.get(input);
		return handler == null ? null : DnaRecipeCache.INSTANCE.getRecipe(handler.getStage());
	}

	@Override
	public void startWorking() {
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	@Override
	public IError getNoInputError() {
		return ForestryError.NO_SPECIMEN;
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/GeneticTransposerBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.items.ItemHandlerHelper;

import forestry.api.core.IErrorLogic;
import forestry.core.tiles.TilePowered;

import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.menu.ThreeInputMenu;
import thedarkcolour.gendustry.registry.GBlockEntities;

public class GeneticTransposerBlockEntity extends TilePowered implements IHintTile {
	private static final float CONSUME_LABWARE_CHANCE = 0.2f;

	public static final String HINTS_KEY = "gendustry.genetic_transposer";

	private final GeneticTransposerInventory inventory;

	public GeneticTransposerBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.GENETIC_TRANSPOSER.tileType(), pos, state, 10000, 1000000);

		this.inventory = new GeneticTransposerInventory(this);
		setInternalInventory(this.inventory);

		setTicksPerWorkCycle(20);
		setEnergyPerWorkCycle(50000);
	}

	@Override
	public boolean hasWork() {
		IErrorLogic errors = getErrorLogic();
		boolean noBlanks = errors.setCondition(this.inventory.getItem(GeneticTransposerInventory.SLOT_INPUT).isEmpty(), GendustryError.NO_BLANK);
		boolean noSource = errors.setCondition(this.inventory.getItem(GeneticTransposerInventory.SLOT_SOURCE).isEmpty(), GendustryError.NO_SOURCE);
		boolean noLabware = errors.setCondition(this.inventory.getItem(GeneticTransposerInventory.SLOT_LABWARE).isEmpty(), GendustryError.NO_LABWARE);
		return !noBlanks && !noSource && !noLabware;
	}

	@Override
	protected boolean workCycle() {
		// Check for room in result slot
		ItemStack copy = this.inventory.getItem(GeneticTransposerInventory.SLOT_SOURCE).copyWithCount(1);
		ItemStack output = this.inventory.getItem(GeneticTransposerInventory.SLOT_OUTPUT);
		if (!output.isEmpty() && !ItemHandlerHelper.canItemStacksStack(output, copy)) {
			return false;
		}
		// Consume inputs
		this.inventory.removeItem(GeneticTransposerInventory.SLOT_INPUT, 1);
		if (this.level.random.nextFloat() < CONSUME_LABWARE_CHANCE) {
			this.inventory.removeItem(GeneticTransposerInventory.SLOT_LABWARE, 1);
		}
		// Place output in result slot
		ItemStack result;
		if (output.isEmpty()) {
			result = copy;
		} else {
			result = output.copy();
			result.grow(1);
		}
		this.inventory.setItem(GeneticTransposerInventory.SLOT_OUTPUT, result);

		return true;
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		return ThreeInputMenu.geneticTransposer(windowId, playerInv, this);
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/GeneticTransposerInventory.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import forestry.core.inventory.InventoryAdapterTile;

import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.registry.GItems;

public class GeneticTransposerInventory extends InventoryAdapterTile<GeneticTransposerBlockEntity> {
	public static final int SLOT_INPUT = SamplerInventory.SLOT_INPUT;
	public static final int SLOT_SOURCE = SamplerInventory.SLOT_BLANK_SAMPLE;
	public static final int SLOT_LABWARE = SamplerInventory.SLOT_LABWARE;
	public static final int SLOT_OUTPUT = SamplerInventory.SLOT_OUTPUT;

	public GeneticTransposerInventory(GeneticTransposerBlockEntity tile) {
		super(tile, 4, "items");
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		return switch (slotIndex) {
			case SLOT_INPUT -> {
				Item blankTemplate = GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE);
				Item blankSample = GItems.RESOURCE.item(GendustryResourceType.BLANK_GENE_SAMPLE);
				ItemStack source = getItem(SLOT_SOURCE);

				if (source.is(GItems.GENETIC_TEMPLATE.item())) {
					yield stack.is(blankTemplate);
				} else if (source.is(GItems.GENE_SAMPLE.item())) {
					yield stack.is(blankSample);
				} else {
					yield stack.is(blankTemplate) || stack.is(blankSample);
				}
			}
			case SLOT_SOURCE -> {
				ItemStack blank = getItem(SLOT_INPUT);

				if (blank.is(GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE))) {
					yield stack.is(GItems.GENETIC_TEMPLATE.item());
				} else if (blank.is(GItems.RESOURCE.item(GendustryResourceType.BLANK_GENE_SAMPLE))) {
					yield stack.is(GItems.GENE_SAMPLE.item());
				} else {
					yield stack.is(GItems.GENETIC_TEMPLATE.item()) || stack.is(GItems.GENE_SAMPLE.item());
				}
			}
			case SLOT_LABWARE -> stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
			default -> false;
		};
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/IHintTile.java

```java
package thedarkcolour.gendustry.blockentity;

public interface IHintTile {
	String getHintsKey();
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ImprinterBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import forestry.api.core.ForestryError;
import forestry.api.core.IErrorLogic;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IChromosome;
import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.tiles.TilePowered;

import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.item.GeneticTemplateItem;
import thedarkcolour.gendustry.menu.ThreeInputMenu;
import thedarkcolour.gendustry.registry.GBlockEntities;

public class ImprinterBlockEntity extends TilePowered implements IHintTile {
	public static final String HINTS_KEY = "gendustry.imprinter";

	private final ImprinterInventory inventory;

	public ImprinterBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.IMPRINTER.tileType(), pos, state, 10000, 1000000);

		this.inventory = new ImprinterInventory(this);
		setInternalInventory(this.inventory);

		setTicksPerWorkCycle(80);
		setEnergyPerWorkCycle(100000);
	}

	@Override
	public boolean hasWork() {
		IErrorLogic errors = getErrorLogic();
		boolean noSpecimen = errors.setCondition(this.inventory.getItem(ImprinterInventory.SLOT_INPUT).isEmpty(), ForestryError.NO_SPECIMEN);
		boolean noTemplate = errors.setCondition(this.inventory.getItem(ImprinterInventory.SLOT_TEMPLATE).isEmpty(), GendustryError.NO_TEMPLATE);
		boolean noLabware = errors.setCondition(this.inventory.getItem(ImprinterInventory.SLOT_LABWARE).isEmpty(), GendustryError.NO_LABWARE);
		return !noTemplate && !noLabware && !noSpecimen;
	}

	@Override
	protected boolean workCycle() {
		// Check for room in result slot
		if (!this.inventory.getItem(ImprinterInventory.SLOT_OUTPUT).isEmpty()) {
			return false;
		}
		// Consume inputs
		ItemStack organism = this.inventory.removeItem(ImprinterInventory.SLOT_INPUT, 1);
		this.inventory.removeItem(ImprinterInventory.SLOT_LABWARE, 1);

		return IIndividualHandlerItem.filter(organism, (individual, stage) -> {
			ItemStack template = this.inventory.getItem(ImprinterInventory.SLOT_TEMPLATE);
			Map<IChromosome<?>, IAllele> alleles = GeneticTemplateItem.getAlleles(template);
			IGenome newGenome = individual.getGenome().copyWith(alleles);

			IIndividual newIndividual = individual.copyWithGenome(newGenome);
			if (individual.getMate() != null) {
				newIndividual.setMate(newGenome);
			}
			this.inventory.setItem(ImprinterInventory.SLOT_OUTPUT, newIndividual.createStack(stage));

			return true;
		});
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		return ThreeInputMenu.imprinter(windowId, playerInv, this);
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ImprinterInventory.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.inventory.InventoryAdapterTile;

import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.item.GeneticTemplateItem;
import thedarkcolour.gendustry.registry.GItems;

public class ImprinterInventory extends InventoryAdapterTile<ImprinterBlockEntity> {
	// The SamplerImprinterMenu uses slot indices from the sampler inventory, so these must correspond to them
	public static final int SLOT_INPUT = SamplerInventory.SLOT_INPUT;
	public static final int SLOT_TEMPLATE = SamplerInventory.SLOT_BLANK_SAMPLE;
	public static final int SLOT_LABWARE = SamplerInventory.SLOT_LABWARE;
	public static final int SLOT_OUTPUT = SamplerInventory.SLOT_OUTPUT;

	public ImprinterInventory(ImprinterBlockEntity tile) {
		super(tile, 4, "items");
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		return switch (slotIndex) {
			case SLOT_INPUT -> IIndividualHandlerItem.isIndividual(stack);
			case SLOT_TEMPLATE -> stack.is(GItems.GENETIC_TEMPLATE.item()) && !GeneticTemplateItem.getAlleles(stack).isEmpty();
			case SLOT_LABWARE -> stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
			default -> false;
		};
	}

	@Override
	public boolean canTakeItemThroughFace(int slotIndex, ItemStack stack, Direction side) {
		return slotIndex == SLOT_OUTPUT;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/IndustrialApiaryBeeModifier.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.Vec3i;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.genetics.IBeeSpecies;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutation;
import forestry.core.inventory.IInventoryAdapter;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.item.EliteGendustryUpgradeType;
import thedarkcolour.gendustry.item.GendustryUpgradeItem;
import thedarkcolour.gendustry.item.GendustryUpgradeType;
import thedarkcolour.gendustry.item.IGendustryUpgradeType;

class IndustrialApiaryBeeModifier implements IBeeModifier {
	float territory;
	float mutation;
	float lifespan;
	float productivity;
	float pollination;
	int throttle;
	int fertility;
	int temperature;
	int humidity;
	boolean automated;
	boolean stabilized;
	boolean noMutations;
	boolean weatherproof;
	boolean lighting;
	boolean sky;
	boolean nether;
	boolean scrubber;
	boolean sieve;

	IndustrialApiaryBeeModifier() {
		reset();
	}

	private void reset() {
		this.territory = 1f;
		this.mutation = 1f;
		this.lifespan = 1f;
		this.productivity = 1f;
		this.pollination = 1f;
		this.throttle = 0;
		this.fertility = 0;
		this.temperature = 0;
		this.humidity = 0;
		this.automated = false;
		this.stabilized = false;
		this.noMutations = false;
		this.weatherproof = false;
		this.lighting = false;
		this.sky = false;
		this.nether = false;
		this.scrubber = false;
		this.sieve = false;
	}

	// Returns the sum of the energy costs of all upgrades
	public int recalculate(IInventoryAdapter inventory) {
		reset();

		int energyCost = 0;

		for (int i = 0; i < IndustrialApiaryInventory.UPGRADE_SLOT_COUNT; ++i) {
			ItemStack stack = inventory.getItem(IndustrialApiaryInventory.UPGRADE_SLOT_START + i);
			Item item = stack.getItem();
			int count = stack.getCount();

			// Hardcoded for now. If you want an API, open an Issue on GitHub.
			if (item instanceof GendustryUpgradeItem upgrade) {
				IGendustryUpgradeType upgradeType = upgrade.getType();
				energyCost += upgradeType.energyCost() * count;

				if (upgradeType instanceof GendustryUpgradeType type) {
					// Regular upgrades
					switch (type) {
						case AUTOMATION -> this.automated = true;
						case HEATER -> this.temperature += count;
						case COOLER -> this.temperature -= count;
						case HUMIDIFIER -> this.humidity += count;
						case DRYER -> this.humidity -= count;
						case POLLINATION -> this.pollination += 0.25f * count;
						case SCRUBBER -> this.scrubber = true;
						case NETHER -> this.nether = true;
						case LIFESPAN -> this.lifespan += 2f * count;
						case LIGHTING -> this.lighting = true;
						case PRODUCTIVITY -> this.productivity += 0.25f * count;
						case WEATHERPROOF -> this.weatherproof = true;
						case SIEVE -> this.sieve = true;
						case SKY -> this.sky = true;
						case STABILIZER -> this.stabilized = true;
						case TERRITORY -> this.territory += 0.25f * count;
						case IMMUTABLE -> this.noMutations = true;
					}
				} else if (upgradeType instanceof EliteGendustryUpgradeType type) {
					// Elite upgrades
					switch (type) {
						case MUTATION -> this.mutation += 0.25f;
						case ACTIVITY_SIMULATOR -> {
							this.lighting = true;
							this.sky = true;
							this.weatherproof = true;
						}
						case PRODUCTIVITY -> {
							this.productivity += 0.25f * count;
							this.throttle += 15 * count;
						}
						case TERRITORY -> this.territory += 0.25f * count;
						case YOUTH -> this.mutation -= 0.2f * count;
						case FERTILITY -> this.fertility += count;
					}
				}
			}
		}

		return energyCost;
	}

	@Override
	public Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
		return new Vec3i((int) (currentModifier.getX() * this.territory), (int) (currentModifier.getY() * this.territory), (int) (currentModifier.getZ() * this.territory));
	}

	@Override
	public float modifyMutationChance(IGenome genome, IGenome mate, IMutation<IBeeSpecies> mutation, float currentChance) {
		return this.noMutations ? 0 : currentChance * this.mutation;
	}

	@Override
	public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
		return currentAging * this.lifespan;
	}

	@Override
	public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
		return currentSpeed * this.productivity;
	}

	@Override
	public float modifyPollination(IGenome genome, float currentPollination) {
		return this.scrubber ? 0.0f : currentPollination * this.pollination;
	}

	@Override
	public float modifyGeneticDecay(IGenome genome, float currentDecay) {
		return this.stabilized ? 0.0f : currentDecay;
	}

	@Override
	public boolean isSealed() {
		return this.weatherproof;
	}

	@Override
	public boolean isAlwaysActive(IGenome genome) {
		return this.lighting;
	}

	@Override
	public boolean isSunlightSimulated() {
		return this.sky;
	}

	@Override
	public boolean isHellish() {
		return this.nether;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/IndustrialApiaryBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import com.mojang.authlib.GameProfile;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemHandlerHelper;

import forestry.api.IForestryApi;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeHousingInventory;
import forestry.api.apiculture.IBeeListener;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IBeekeepingLogic;
import forestry.api.apiculture.genetics.BeeLifeStage;
import forestry.api.apiculture.genetics.IBee;
import forestry.api.climate.ClimateState;
import forestry.api.climate.IClimateProvider;
import forestry.api.core.ForestryError;
import forestry.api.core.HumidityType;
import forestry.api.core.IErrorLogic;
import forestry.api.core.TemperatureType;
import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.api.genetics.pollen.IPollen;
import forestry.apiculture.gui.IGuiBeeHousingDelegate;
import forestry.core.network.IStreamableGui;
import forestry.core.owner.IOwnedTile;
import forestry.core.owner.IOwnerHandler;
import forestry.core.owner.OwnerHandler;
import forestry.core.tiles.IPowerHandler;
import forestry.core.tiles.TileBase;
import forestry.core.utils.NetworkUtil;
import forestry.energy.EnergyHelper;
import forestry.energy.ForestryEnergyStorage;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.menu.IndustrialApiaryMenu;
import thedarkcolour.gendustry.registry.GBlockEntities;

public class IndustrialApiaryBlockEntity extends TileBase implements IBeeHousing, IOwnedTile, IClimateProvider, IGuiBeeHousingDelegate, IStreamableGui, IPowerHandler, IBeeListener {
	public static final String HINTS_KEY = "gendustry.industrial_apiary";
	public static final int BASE_ENERGY = 200;

	// Energy
	private final ForestryEnergyStorage energyStorage;
	private final LazyOptional<ForestryEnergyStorage> energyCap;
	// Inventory
	private final IndustrialApiaryInventory inventory;
	// Beekeeping logic
	private final OwnerHandler ownerHandler;
	private final IBeekeepingLogic beeLogic;
	protected IClimateProvider climate;

	// State
	private final IndustrialApiaryBeeModifier modifier;
	private int breedingProgressPercent;
	private int energyConsumption;
	protected boolean recycleQueen;

	public IndustrialApiaryBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.INDUSTRIAL_APIARY.tileType(), pos, state);

		this.energyStorage = new ForestryEnergyStorage(100000, 1000000);
		this.energyCap = LazyOptional.of(() -> this.energyStorage);
		this.inventory = new IndustrialApiaryInventory(this);
		setInternalInventory(this.inventory);
		this.ownerHandler = new OwnerHandler();
		this.beeLogic = IForestryApi.INSTANCE.getHiveManager().createBeekeepingLogic(this);
		this.climate = IForestryApi.INSTANCE.getClimateManager().createDummyClimateProvider();

		this.modifier = new IndustrialApiaryBeeModifier();
		// Base consumption is 200, plus whatever the upgrades
		this.energyConsumption = BASE_ENERGY;
	}

	@Override
	public void setLevel(Level level) {
		super.setLevel(level);
		this.climate = IForestryApi.INSTANCE.getClimateManager().createClimateProvider(level, this.worldPosition);

		refreshUpgrades();
	}

	@Override
	public void serverTick(Level level, BlockPos pos, BlockState state) {
		// Check redstone state
		IErrorLogic errors = getErrorLogic();
		boolean disabled = isRedstoneActivated();
		errors.setCondition(disabled, ForestryError.DISABLED_BY_REDSTONE);

		// Automation upgrade
		if (this.recycleQueen) {
			this.recycleQueen = false;

			recycleQueen();
		}

		// Bee logic that costs power
		if (!disabled) {
			if (this.beeLogic.canWork()) {
				// Check power state
				boolean hasEnergy = EnergyHelper.consumeEnergyToDoWork(this.energyStorage, 1, this.energyConsumption);
				errors.setCondition(!hasEnergy, ForestryError.NO_POWER);
				if (hasEnergy) {
					this.beeLogic.doWork();
				}
			}
		}

		// Update climate periodically
		if ((level.getGameTime() & 63L) == 0L) {
			refreshClimate();
		}
	}

	private void recycleQueen() {
		for (int i = 0; i < IndustrialApiaryInventory.OUTPUT_SLOT_COUNT; ++i) {
			int slotIndex = IndustrialApiaryInventory.OUTPUT_SLOT_START + i;
			ItemStack stack = this.inventory.getItem(slotIndex);

			IIndividualHandlerItem.ifPresent(stack, (bee, stage) -> {
				if (stage == BeeLifeStage.PRINCESS) {
					// Move the princess back into place
					this.inventory.setItem(slotIndex, ItemStack.EMPTY);
					this.inventory.setQueen(stack);
				} else if (stage == BeeLifeStage.DRONE) {
					ItemStack drone = this.inventory.getDrone();

					if (drone.isEmpty()) {
						// Move entire stack to drone slot
						this.inventory.setDrone(stack);
						this.inventory.setItem(slotIndex, ItemStack.EMPTY);
					} else {
						// Replenish drones
						int free = drone.getMaxStackSize() - drone.getCount();

						if (free > 0 && ItemHandlerHelper.canItemStacksStack(drone, stack)) {
							int taken = Math.min(stack.getCount(), free);
							stack.shrink(taken);
							ItemStack newDrone = drone.copyWithCount(drone.getCount() + taken);
							this.inventory.setDrone(newDrone);
						}
					}
				}
			});
		}
	}

	@Override
	public void setChanged() {
		super.setChanged();

		refreshUpgrades();
	}

	private void refreshUpgrades() {
		this.energyConsumption = BASE_ENERGY + this.modifier.recalculate(this.inventory);
		this.beeLogic.setWorkThrottle(Math.max(5, 550 - this.modifier.throttle));

		refreshClimate();
	}

	@Override
	public void clientTick(Level level, BlockPos pos, BlockState state) {
		if (this.beeLogic.canDoBeeFX()) {
			this.beeLogic.doBeeFX();
		}
	}

	@Override
	public boolean onPollenRetrieved(IPollen<?> pollen) {
		return this.modifier.sieve && this.inventory.addProduct(pollen.createStack(), false);
	}

	@Override
	public void onQueenDeath() {
		this.recycleQueen = this.modifier.automated;

		// Fertility
		if (this.modifier.fertility > 0) {
			spawnAdditionalOffspring();
		}
	}

	private void spawnAdditionalOffspring() {
		int fertility = this.modifier.fertility;
		ArrayList<IBee> drones = new ArrayList<>();

		IIndividualHandlerItem.ifPresent(this.inventory.getQueen(), individual -> {
			if (individual instanceof IBee queen) {
				while (drones.size() < fertility) {
					List<IBee> offspring = queen.spawnDrones(this);
					if (offspring.isEmpty()) {
						// This should never happen, but if fertility is 0, avoid infinite loop
						break;
					}
					drones.addAll(offspring);
				}
			}
		});

		for (int i = 0; i < fertility; ++i) {
			ItemStack stack = drones.get(i).createStack(BeeLifeStage.DRONE);
			this.inventory.addProduct(stack, true);
		}
	}

	// Updates the climate to reflect upgrades
	private void refreshClimate() {
		IClimateProvider oldClimate = this.modifier.nether ? new ClimateState(TemperatureType.HELLISH, HumidityType.ARID) : IForestryApi.INSTANCE.getClimateManager().createClimateProvider(this.level, this.worldPosition);
		this.climate = new ClimateState(oldClimate.temperature().up(this.modifier.temperature), oldClimate.humidity().up(this.modifier.humidity));
	}

	@Override
	public void writeGuiData(FriendlyByteBuf data) {
		this.energyStorage.writeData(data);
		data.writeVarInt(this.beeLogic.getBeeProgressPercent());
		NetworkUtil.writeClimateState(data, this.climate);
	}

	@Override
	public void readGuiData(FriendlyByteBuf data) {
		this.energyStorage.readData(data);
		this.breedingProgressPercent = data.readVarInt();
		this.climate = NetworkUtil.readClimateState(data);
	}

	@Override
	public void load(CompoundTag data) {
		super.load(data);
		this.energyStorage.read(data);
		this.beeLogic.read(data);
		this.ownerHandler.read(data);
	}

	@Override
	public void saveAdditional(CompoundTag data) {
		super.saveAdditional(data);
		this.energyStorage.write(data);
		this.beeLogic.write(data);
		this.ownerHandler.write(data);
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		return new IndustrialApiaryMenu(windowId, playerInv, this);
	}

	@Override
	public Iterable<IBeeModifier> getBeeModifiers() {
		return Collections.singleton(this.modifier);
	}

	@Override
	public Iterable<IBeeListener> getBeeListeners() {
		return Collections.singleton(this);
	}

	@Override
	public IBeeHousingInventory getBeeInventory() {
		return this.inventory;
	}

	@Override
	public IBeekeepingLogic getBeekeepingLogic() {
		return this.beeLogic;
	}

	@Override
	public String getHintKey() {
		return HINTS_KEY;
	}

	@Override
	public TemperatureType temperature() {
		return this.climate.temperature();
	}

	@Override
	public HumidityType humidity() {
		return this.climate.humidity();
	}

	@Override
	public IOwnerHandler getOwnerHandler() {
		return this.ownerHandler;
	}

	@Override
	public int getBlockLightValue() {
		return this.level.getMaxLocalRawBrightness(this.worldPosition.above());
	}

	@Override
	public boolean canBlockSeeTheSky() {
		return this.level.canSeeSky(this.worldPosition.above());
	}

	@Override
	public boolean isRaining() {
		return this.level.isRainingAt(this.worldPosition.above());
	}

	@Nullable
	@Override
	public GameProfile getOwner() {
		return this.ownerHandler.getOwner();
	}

	@Override
	public Vec3 getBeeFXCoordinates() {
		return this.worldPosition.getCenter();
	}

	@Override
	public Holder<Biome> getBiome() {
		return this.level.getBiome(this.worldPosition);
	}

	@Override
	public int getHealthScaled(int pixels) {
		return (this.breedingProgressPercent * pixels) / 100;
	}

	@Override
	public ForestryEnergyStorage getEnergyManager() {
		return this.energyStorage;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		return !this.remove && capability == ForgeCapabilities.ENERGY ? this.energyCap.cast() : super.getCapability(capability, facing);
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/IndustrialApiaryInventory.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import forestry.api.apiculture.IBeeHousingInventory;
import forestry.api.apiculture.genetics.BeeLifeStage;
import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.inventory.InventoryAdapterTile;
import forestry.core.utils.InventoryUtil;

import thedarkcolour.gendustry.api.GendustryTags;

public class IndustrialApiaryInventory extends InventoryAdapterTile<IndustrialApiaryBlockEntity> implements IBeeHousingInventory {
	public static final int QUEEN = 0;
	public static final int DRONE = 1;
	public static final int UPGRADE_SLOT_START = 2;
	public static final int UPGRADE_SLOT_COUNT = 4;
	public static final int OUTPUT_SLOT_START = 6;
	public static final int OUTPUT_SLOT_COUNT = 9;

	public IndustrialApiaryInventory(IndustrialApiaryBlockEntity tile) {
		super(tile, 15, "items");
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		if (slotIndex == QUEEN) {
			return IIndividualHandlerItem.filter(stack, (i, stage) -> stage == BeeLifeStage.PRINCESS || stage == BeeLifeStage.QUEEN);
		} else if (slotIndex == DRONE) {
			return IIndividualHandlerItem.filter(stack, (i, stage) -> stage == BeeLifeStage.DRONE);
		} else if (UPGRADE_SLOT_START <= slotIndex && slotIndex < UPGRADE_SLOT_START + UPGRADE_SLOT_COUNT) {
			// Only one slot per upgrade type
			for (int i = UPGRADE_SLOT_START; i < UPGRADE_SLOT_START + UPGRADE_SLOT_COUNT; ++i) {
				Item currentItem = getItem(i).getItem();

				if (slotIndex != i) {
					// AIR is empty
					if (stack.is(currentItem)) {
						return false;
					}
				}
			}

			return stack.is(GendustryTags.Items.UPGRADES);
		} else {
			return false;
		}
	}

	@Override
	public boolean canTakeItemThroughFace(int slotIndex, ItemStack stack, Direction side) {
		// Wait till drones are recycled before extracting
		if (this.tile.recycleQueen && IIndividualHandlerItem.filter(stack, (i, stage) -> stage == BeeLifeStage.PRINCESS || stage == BeeLifeStage.DRONE)) {
			return false;
		}
		return OUTPUT_SLOT_START <= slotIndex && slotIndex < OUTPUT_SLOT_COUNT;
	}

	@Override
	public ItemStack getQueen() {
		return getItem(QUEEN);
	}

	@Override
	public ItemStack getDrone() {
		return getItem(DRONE);
	}

	@Override
	public void setQueen(ItemStack itemStack) {
		setItem(QUEEN, itemStack);
	}

	@Override
	public void setDrone(ItemStack itemStack) {
		setItem(DRONE, itemStack);
	}

	@Override
	public boolean addProduct(ItemStack product, boolean all) {
		if (getQueen().isEmpty() && IIndividualHandlerItem.filter(product, (i, stage) -> stage == BeeLifeStage.PRINCESS)) {
			setQueen(product);
			return true;
		}
		return InventoryUtil.tryAddStack(this, product, IndustrialApiaryInventory.OUTPUT_SLOT_START, IndustrialApiaryInventory.OUTPUT_SLOT_COUNT, all, true);
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/MutagenProducerBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import forestry.api.core.ForestryError;
import forestry.api.core.IError;

import thedarkcolour.gendustry.recipe.MutagenRecipe;
import thedarkcolour.gendustry.recipe.cache.MutagenRecipeCache;
import thedarkcolour.gendustry.registry.GBlockEntities;
import thedarkcolour.gendustry.registry.GFluids;
import org.jetbrains.annotations.Nullable;

// Based somewhat on the squeezer
public class MutagenProducerBlockEntity extends ProducerBlockEntity<MutagenProducerBlockEntity, MutagenRecipe> {
	// All recipes take 100000 RF to process. Choose your mutagens wisely!
	private static final int ENERGY_PER_WORK_CYCLE = 100000;
	private static final int TICKS_PER_WORK_CYCLE = 200;

	public static final String HINTS_KEY = "gendustry.mutagen_producer";

	public MutagenProducerBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.MUTAGEN_PRODUCER, GFluids.MUTAGEN, false, pos, state);
	}

	@Override
	public boolean isValidInput(ItemStack input) {
		return MutagenRecipeCache.INSTANCE.getRecipe(input) != null;
	}

	@Override
	public @Nullable MutagenRecipe getRecipe(ItemStack input) {
		return MutagenRecipeCache.INSTANCE.getRecipe(input);
	}

	@Override
	public void startWorking() {
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	@Override
	public IError getNoInputError() {
		return ForestryError.NO_RECIPE;
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/MutatronBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import forestry.api.genetics.IMutation;
import forestry.api.genetics.ISpecies;

import thedarkcolour.gendustry.registry.GBlockEntities;

public class MutatronBlockEntity extends AbstractMutatronBlockEntity {
	public MutatronBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.MUTATRON.tileType(), pos, state);
	}

	@Override
	protected void onMutationsUpdated(List<IMutation<ISpecies<?>>> mutations, ItemStack primaryStack, ItemStack secondaryStack) {
		if (mutations.isEmpty()) {
			setCurrentMutation(null, primaryStack, secondaryStack);
		} else {
			int index = this.level.random.nextInt(mutations.size());
			setCurrentMutation(mutations.get(index), primaryStack, secondaryStack);
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/MutatronInventory.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.fluids.FluidUtil;

import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.inventory.InventoryAdapterTile;

import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.gendustry.registry.GItems;

public class MutatronInventory extends InventoryAdapterTile<AbstractMutatronBlockEntity> {
	public static final int SLOT_PRIMARY = 0;
	public static final int SLOT_SECONDARY = 1;
	public static final int SLOT_LABWARE = 2;
	public static final int SLOT_CAN_INPUT = 3;
	public static final int SLOT_RESULT = 4;

	public MutatronInventory(AbstractMutatronBlockEntity tile) {
		super(tile, 5, "items");
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		return switch (slotIndex) {
			case SLOT_PRIMARY ->
					IIndividualHandlerItem.filter(stack, (individual, stage) -> stage == individual.getType().getTypeForMutation(0));
			case SLOT_SECONDARY ->
					IIndividualHandlerItem.filter(stack, (individual, stage) -> stage == individual.getType().getTypeForMutation(1));
			case SLOT_LABWARE -> stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
			case SLOT_CAN_INPUT ->
					FluidUtil.getFluidContained(stack).filter(fluid -> fluid.getFluid() == GFluids.MUTAGEN.fluid()).isPresent();
			default -> false;
		};
	}

	@Override
	public boolean canTakeItemThroughFace(int slotIndex, ItemStack stack, Direction side) {
		return slotIndex == SLOT_RESULT;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.blockentity;
```

### src/main/java/thedarkcolour/gendustry/blockentity/PoweredTankBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;

import forestry.core.fluids.ITankManager;
import forestry.core.fluids.TankManager;
import forestry.core.tiles.ILiquidTankTile;
import forestry.core.tiles.TilePowered;

import org.jetbrains.annotations.Nullable;

public abstract class PoweredTankBlockEntity extends TilePowered implements ILiquidTankTile {
	protected final TankManager tankManager;
	private final LazyOptional<IFluidHandler> fluidCap;

	public PoweredTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int maxTransfer, int capacity) {
		super(type, pos, state, maxTransfer, capacity);

		this.tankManager = new TankManager(this);
		this.fluidCap = LazyOptional.of(this::getTankManager);
	}

	@Override
	public void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		this.tankManager.write(nbt);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.tankManager.read(nbt);
	}

	@Override
	public void writeData(FriendlyByteBuf data) {
		super.writeData(data);
		this.tankManager.writeData(data);
	}

	@Override
	public void readData(FriendlyByteBuf data) {
		super.readData(data);
		this.tankManager.readData(data);
	}

	@Override
	public void writeGuiData(FriendlyByteBuf data) {
		super.writeGuiData(data);
		this.tankManager.writeData(data);
	}

	@Override
	public void readGuiData(FriendlyByteBuf data) {
		super.readGuiData(data);
		this.tankManager.readData(data);
	}

	@Override
	public ITankManager getTankManager() {
		return this.tankManager;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		return !this.remove && cap == ForgeCapabilities.FLUID_HANDLER ? this.fluidCap.cast() : super.getCapability(cap, side);
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ProducerBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import forestry.api.core.IError;
import forestry.core.fluids.FilteredTank;
import forestry.core.fluids.FluidHelper;
import forestry.modules.features.FeatureTileType;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.menu.ProducerMenu;
import thedarkcolour.gendustry.recipe.ProcessorRecipe;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.gendustry.registry.GItems;

// Common logic shared between Mutagen Producer, Protein Liquefier, DNA Extractor
public abstract class ProducerBlockEntity<T extends ProducerBlockEntity<T, R>, R extends ProcessorRecipe> extends PoweredTankBlockEntity {
	private static final float CONSUME_LABWARE_CHANCE = 0.1f;

	protected final FilteredTank outputTank;
	protected final ProducerInventory<T> inventory;
	public final boolean usesLabware;
	private final GFluids resultFluid;

	@Nullable
	protected ProcessorRecipe currentRecipe;

	@SuppressWarnings("unchecked")
	public ProducerBlockEntity(FeatureTileType<?> type, GFluids result, boolean usesLabware, BlockPos pos, BlockState state) {
		super(type.tileType(), pos, state, 10000, 1000000);

		this.outputTank = new FilteredTank(10000, false, true).setFilters(Set.of(result.fluid()));
		this.tankManager.add(this.outputTank);
		this.inventory = new ProducerInventory<>((T) this, usesLabware);
		this.usesLabware = usesLabware;
		this.resultFluid = result;

		setInternalInventory(this.inventory);
	}

	@Override
	public void serverTick(Level level, BlockPos pos, BlockState state) {
		super.serverTick(level, pos, state);

		if (updateOnInterval(20)) {
			FluidStack fluid = this.outputTank.getFluid();
			if (!fluid.isEmpty()) {
				FluidHelper.fillContainers(this.tankManager, this.inventory, ProducerInventory.SLOT_CAN_INPUT, ProducerInventory.SLOT_CAN_OUTPUT, fluid.getFluid(), true);
			}
		}
	}

	@Override
	public boolean hasWork() {
		// Check input
		R matchingRecipe = getRecipe(this.inventory.getItem(ProducerInventory.SLOT_INPUT));
		if (this.currentRecipe != matchingRecipe) {
			this.currentRecipe = matchingRecipe;

			if (this.currentRecipe != null) {
				// Set up machine for work
				startWorking();
			}
		}

		boolean hasInput = !getErrorLogic().setCondition(this.currentRecipe == null, getNoInputError());

		// Check labware
		if (this.usesLabware) {
			ItemStack labware = this.inventory.getItem(ProducerInventory.SLOT_LABWARE);
			boolean hasLabware = !getErrorLogic().setCondition(labware.isEmpty(), GendustryError.NO_LABWARE);

			return hasInput && hasLabware;
		}

		return hasInput;
	}

	@Override
	protected boolean workCycle() {
		// Check for room in result tank
		int resultAmount = this.currentRecipe.getAmount();
		if (this.outputTank.getRemainingSpace() < resultAmount) {
			return false;
		}

		// Check input
		ItemStack input = this.inventory.getItem(ProducerInventory.SLOT_INPUT);
		if (!this.currentRecipe.isIngredient(input)) {
			return false;
		}
		if (this.usesLabware) {
			// Check labware
			ItemStack labware = this.inventory.getItem(ProducerInventory.SLOT_LABWARE);
			if (!labware.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE))) {
				return false;
			}
			// Consume labware
			if (this.level.random.nextFloat() < CONSUME_LABWARE_CHANCE) {
				this.inventory.removeItem(ProducerInventory.SLOT_LABWARE, 1);
			}
		}
		// Consume input
		this.inventory.removeItem(ProducerInventory.SLOT_INPUT, 1);

		// Create outputs
		FluidStack result = this.resultFluid.fluidStack(resultAmount);
		this.outputTank.fillInternal(result, IFluidHandler.FluidAction.EXECUTE);

		// Return true upon completion of work
		return true;
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		return new ProducerMenu(windowId, playerInv, this);
	}

	public abstract boolean isValidInput(ItemStack input);

	@Nullable
	public abstract R getRecipe(ItemStack input);

	public abstract void startWorking();

	public abstract IError getNoInputError();

	public abstract String getHintsKey();
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ProducerInventory.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import forestry.core.fluids.FluidHelper;
import forestry.core.inventory.InventoryAdapterTile;

import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.registry.GItems;

public class ProducerInventory<T extends ProducerBlockEntity<T, ?>> extends InventoryAdapterTile<T> {
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_CAN_INPUT = 1;
	public static final int SLOT_CAN_OUTPUT = 2;
	public static final int SLOT_LABWARE = 3;

	private final boolean usesLabware;

	public ProducerInventory(T tile, boolean usesLabware) {
		super(tile, usesLabware ? 4 : 3, "items");

		this.usesLabware = usesLabware;
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		return switch (slotIndex) {
			case SLOT_INPUT -> tile.isValidInput(stack);
			case SLOT_LABWARE -> this.usesLabware && stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
			case SLOT_CAN_INPUT -> FluidHelper.isFillableEmptyContainer(stack);
			default -> false;
		};
	}

	@Override
	public boolean canTakeItemThroughFace(int slotIndex, ItemStack stack, Direction side) {
		return slotIndex == SLOT_CAN_OUTPUT;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ProteinLiquefierBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import forestry.api.core.ForestryError;
import forestry.api.core.IError;

import thedarkcolour.gendustry.recipe.ProteinRecipe;
import thedarkcolour.gendustry.recipe.cache.ProteinRecipeCache;
import thedarkcolour.gendustry.registry.GBlockEntities;
import thedarkcolour.gendustry.registry.GFluids;
import org.jetbrains.annotations.Nullable;

public class ProteinLiquefierBlockEntity extends ProducerBlockEntity<ProteinLiquefierBlockEntity, ProteinRecipe> {
	private static final int ENERGY_PER_WORK_CYCLE = 20000;
	private static final int TICKS_PER_WORK_CYCLE = 100;

	public static final String HINTS_KEY = "gendustry.protein_liquefier";

	public ProteinLiquefierBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.PROTEIN_LIQUEFIER, GFluids.PROTEIN, false, pos, state);
	}

	@Override
	public boolean isValidInput(ItemStack input) {
		return ProteinRecipeCache.INSTANCE.getRecipe(input) != null;
	}

	@Nullable
	@Override
	public ProteinRecipe getRecipe(ItemStack input) {
		return ProteinRecipeCache.INSTANCE.getRecipe(input);
	}

	@Override
	public void startWorking() {
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	@Override
	public IError getNoInputError() {
		return ForestryError.NO_RECIPE;
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ReplicatorBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import java.util.Map;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.fluids.capability.IFluidHandler;

import forestry.api.apiculture.genetics.IBee;
import forestry.api.core.IErrorLogic;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.ISpecies;
import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IChromosome;
import forestry.api.genetics.alleles.IValueAllele;
import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.fluids.FilteredTank;
import forestry.core.fluids.FluidHelper;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.item.GeneticTemplateItem;
import thedarkcolour.gendustry.menu.ReplicatorMenu;
import thedarkcolour.gendustry.registry.GBlockEntities;
import thedarkcolour.gendustry.registry.GFluids;

public class ReplicatorBlockEntity extends PoweredTankBlockEntity {
	public static final String HINTS_KEY = "gendustry.replicator";

	private final FilteredTank dnaTank;
	private final FilteredTank proteinTank;
	private final ReplicatorInventory inventory;

	public ReplicatorBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.REPLICATOR.tileType(), pos, state, 10000, 1000000);

		this.dnaTank = new FilteredTank(10000).setFilters(Set.of(GFluids.LIQUID_DNA.fluid()));
		this.proteinTank = new FilteredTank(10000).setFilters(Set.of(GFluids.PROTEIN.fluid()));
		this.tankManager.add(this.dnaTank);
		this.tankManager.add(this.proteinTank);
		this.inventory = new ReplicatorInventory(this);
		setInternalInventory(this.inventory);

		setEnergyPerWorkCycle(200000);
		setTicksPerWorkCycle(50);
	}

	@Override
	public void serverTick(Level level, BlockPos pos, BlockState state) {
		super.serverTick(level, pos, state);

		if (updateOnInterval(20)) {
			FluidHelper.drainContainers(this.tankManager, this, ReplicatorInventory.SLOT_DNA_CAN_INPUT);
			FluidHelper.drainContainers(this.tankManager, this, ReplicatorInventory.SLOT_PROTEIN_CAN_INPUT);
		}
	}

	@Override
	public boolean hasWork() {
		IErrorLogic errors = getErrorLogic();
		boolean noDna = errors.setCondition(this.dnaTank.getFluidAmount() < 1000, GendustryError.NO_DNA);
		boolean noProtein = errors.setCondition(this.proteinTank.getFluidAmount() < 1000, GendustryError.NO_PROTEIN);
		boolean noTemplate = errors.setCondition(this.inventory.getItem(ReplicatorInventory.SLOT_TEMPLATE).isEmpty(), GendustryError.NO_TEMPLATE);
		return !noDna && !noProtein && !noTemplate;
	}

	@Override
	protected boolean workCycle() {
		// Check for room in result slot
		if (!this.inventory.getItem(ReplicatorInventory.SLOT_OUTPUT).isEmpty()) {
			return false;
		}

		// Read template
		ItemStack template = this.inventory.getItem(ReplicatorInventory.SLOT_TEMPLATE);
		ISpeciesType<?, ?> speciesType = GeneticTemplateItem.getSpeciesType(template);
		Map<IChromosome<?>, IAllele> alleles = GeneticTemplateItem.getAlleles(template);
		if (speciesType == null) {
			return false;
		}

		// Consume inputs
		this.dnaTank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
		this.proteinTank.drain(1000, IFluidHandler.FluidAction.EXECUTE);

		// Create new individual
		IValueAllele<ISpecies<?>> speciesAllele = alleles.get(speciesType.getKaryotype().getSpeciesChromosome()).cast();
		IIndividual individual = speciesAllele.value().createIndividual(alleles);
		// Ignoble stock
		if (individual instanceof IBee bee) {
			bee.setPristine(false);
		}
		this.inventory.setItem(ReplicatorInventory.SLOT_OUTPUT, individual.createStack(speciesType.getTypeForMutation(2)));

		return true;
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		return new ReplicatorMenu(windowId, playerInv, this);
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/ReplicatorInventory.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.fluids.FluidUtil;

import forestry.core.inventory.InventoryAdapterTile;

import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.item.GeneticTemplateItem;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.gendustry.registry.GItems;

public class ReplicatorInventory extends InventoryAdapterTile<ReplicatorBlockEntity> {
	public static final int SLOT_TEMPLATE = 0;
	public static final int SLOT_DNA_CAN_INPUT = 1;
	public static final int SLOT_PROTEIN_CAN_INPUT = 2;
	public static final int SLOT_OUTPUT = 3;

	public ReplicatorInventory(ReplicatorBlockEntity tile) {
		super(tile, 4, "items");
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		return switch (slotIndex) {
			case SLOT_TEMPLATE -> stack.is(GItems.GENETIC_TEMPLATE.item()) && GeneticTemplateItem.isComplete(stack);
			case SLOT_DNA_CAN_INPUT -> FluidUtil.getFluidContained(stack).filter(fluid -> fluid.getFluid() == GFluids.LIQUID_DNA.fluid()).isPresent();
			case SLOT_PROTEIN_CAN_INPUT -> FluidUtil.getFluidContained(stack).filter(fluid -> fluid.getFluid() == GFluids.PROTEIN.fluid()).isPresent();
			default -> false;
		};
	}

	@Override
	public boolean canTakeItemThroughFace(int slotIndex, ItemStack stack, Direction side) {
		return slotIndex == SLOT_OUTPUT;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/SamplerBlockEntity.java

```java
package thedarkcolour.gendustry.blockentity;

import java.util.Map;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import forestry.api.core.ForestryError;
import forestry.api.core.IErrorLogic;
import forestry.api.genetics.alleles.AllelePair;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IChromosome;
import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.tiles.TilePowered;

import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.item.GeneSampleItem;
import thedarkcolour.gendustry.menu.ThreeInputMenu;
import thedarkcolour.gendustry.registry.GBlockEntities;

public class SamplerBlockEntity extends TilePowered implements IHintTile {
	private static final int ENERGY_PER_WORK_CYCLE = 20000;
	private static final int TICKS_PER_WORK_CYCLE = 20;

	public static final String HINTS_KEY = "gendustry.sampler";

	private final SamplerInventory inventory;

	public SamplerBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.SAMPLER.tileType(), pos, state, 10000, 100000);

		this.inventory = new SamplerInventory(this);

		setInternalInventory(this.inventory);
		setTicksPerWorkCycle(TICKS_PER_WORK_CYCLE);
		setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
	}

	@Override
	public boolean hasWork() {
		IErrorLogic errors = getErrorLogic();
		boolean noSamples = errors.setCondition(this.inventory.getItem(SamplerInventory.SLOT_BLANK_SAMPLE).isEmpty(), GendustryError.NO_SAMPLES);
		boolean noLabware = errors.setCondition(this.inventory.getItem(SamplerInventory.SLOT_LABWARE).isEmpty(), GendustryError.NO_LABWARE);
		boolean noSpecimen = errors.setCondition(this.inventory.getItem(SamplerInventory.SLOT_INPUT).isEmpty(), ForestryError.NO_SPECIMEN);
		return !noSamples && !noLabware && !noSpecimen;
	}

	@Override
	protected boolean workCycle() {
		// Check for room in result slot
		if (!this.inventory.getItem(SamplerInventory.SLOT_OUTPUT).isEmpty()) {
			return false;
		}
		// Consume inputs
		ItemStack organism = this.inventory.removeItem(SamplerInventory.SLOT_INPUT, 1);
		this.inventory.removeItem(SamplerInventory.SLOT_LABWARE, 1);
		this.inventory.removeItem(SamplerInventory.SLOT_BLANK_SAMPLE, 1);

		return IIndividualHandlerItem.filter(organism, individual -> {
			RandomSource random = this.level.random;
			Map.Entry<IChromosome<?>, AllelePair<?>> randomEntry = Util.getRandom(individual.getGenome().getChromosomes().entrySet().asList(), random);
			AllelePair<?> randomPair = randomEntry.getValue();
			IAllele chosenAllele = random.nextBoolean() ? randomPair.active() : randomPair.inactive();

			this.inventory.setItem(SamplerInventory.SLOT_OUTPUT, GeneSampleItem.createStack(individual.getType(), randomEntry.getKey(), chosenAllele));

			return true;
		});
	}

	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory playerInv, Player player) {
		return ThreeInputMenu.sampler(windowId, playerInv, this);
	}

	@Override
	public String getHintsKey() {
		return HINTS_KEY;
	}
}
```

### src/main/java/thedarkcolour/gendustry/blockentity/SamplerInventory.java

```java
package thedarkcolour.gendustry.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.inventory.InventoryAdapterTile;

import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.registry.GItems;

public class SamplerInventory extends InventoryAdapterTile<SamplerBlockEntity> {
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_BLANK_SAMPLE = 1;
	public static final int SLOT_LABWARE = 2;
	public static final int SLOT_OUTPUT = 3;

	public SamplerInventory(SamplerBlockEntity tile) {
		super(tile, 4, "items");
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		return switch (slotIndex) {
			case SLOT_INPUT -> IIndividualHandlerItem.isIndividual(stack);
			case SLOT_BLANK_SAMPLE -> stack.is(GItems.RESOURCE.item(GendustryResourceType.BLANK_GENE_SAMPLE));
			case SLOT_LABWARE -> stack.is(GItems.RESOURCE.item(GendustryResourceType.LABWARE));
			default -> false;
		};
	}

	@Override
	public boolean canTakeItemThroughFace(int slotIndex, ItemStack stack, Direction side) {
		return slotIndex == SLOT_OUTPUT;
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/ClientHandler.java

```java
package thedarkcolour.gendustry.client;

import net.minecraft.client.gui.screens.MenuScreens;

import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import forestry.api.client.IClientModuleHandler;

import thedarkcolour.gendustry.client.screen.AdvancedMutatronScreen;
import thedarkcolour.gendustry.client.screen.IndustrialApiaryScreen;
import thedarkcolour.gendustry.client.screen.MutatronScreen;
import thedarkcolour.gendustry.client.screen.ProducerScreen;
import thedarkcolour.gendustry.client.screen.ReplicatorScreen;
import thedarkcolour.gendustry.client.screen.ThreeInputScreen;
import thedarkcolour.gendustry.registry.GMenus;

public class ClientHandler implements IClientModuleHandler {
	@Override
	public void registerEvents(IEventBus modBus) {
		modBus.addListener(ClientHandler::clientSetup);
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(GMenus.PROCESSOR.menuType(), ProducerScreen::new);
			MenuScreens.register(GMenus.SAMPLER.menuType(), ThreeInputScreen::new);
			MenuScreens.register(GMenus.IMPRINTER.menuType(), ThreeInputScreen::new);
			MenuScreens.register(GMenus.GENETIC_TRANSPOSER.menuType(), ThreeInputScreen::new);
			MenuScreens.register(GMenus.MUTATRON.menuType(), MutatronScreen::new);
			MenuScreens.register(GMenus.ADVANCED_MUTATRON.menuType(), AdvancedMutatronScreen::new);
			MenuScreens.register(GMenus.REPLICATOR.menuType(), ReplicatorScreen::new);
			MenuScreens.register(GMenus.INDUSTRIAL_APIARY.menuType(), IndustrialApiaryScreen::new);
		});
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.client;
```

### src/main/java/thedarkcolour/gendustry/client/screen/AbstractMutatronScreen.java

```java
package thedarkcolour.gendustry.client.screen;

import java.util.List;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.gui.GuiForestryTitled;
import forestry.core.gui.widgets.TankWidget;

import thedarkcolour.gendustry.blockentity.AbstractMutatronBlockEntity;
import thedarkcolour.gendustry.data.TranslationKeys;
import thedarkcolour.gendustry.menu.AbstractMutatronMenu;

public class AbstractMutatronScreen<M extends AbstractMutatronMenu<?>> extends GuiForestryTitled<M> {
	protected final AbstractMutatronBlockEntity tile;

	public AbstractMutatronScreen(ResourceLocation texture, M menu, Inventory playerInv, Component title) {
		super(texture, menu, playerInv, title);

		this.tile = menu.getTile();
		this.widgetManager.add(new TankWidget(this.widgetManager, 11, 8, 0));
		this.imageHeight = 176;
	}

	@Override
	protected void drawWidgets(GuiGraphics graphics) {
		super.drawWidgets(graphics);
		int progress = this.tile.getProgressScaled(55);
		graphics.blit(this.textureFile, 68, 38, 176, 60, progress, 18);
	}

	@Override
	protected void addLedgers() {
		addErrorLedger(this.tile);
		addPowerLedger(this.tile.getEnergyManager());
		addHintLedger(AbstractMutatronBlockEntity.HINTS_KEY);
	}

	static {
		HINTS.putAll(AbstractMutatronBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_MUTATRON_USAGE,
				TranslationKeys.HINT_ADVANCED_MUTATRON_USAGE
		));
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/screen/AdvancedMutatronScreen.java

```java
package thedarkcolour.gendustry.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import forestry.core.config.Constants;
import forestry.core.gui.buttons.GuiBetterButton;
import forestry.core.gui.buttons.StandardButtonTextureSets;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.menu.AdvancedMutatronMenu;

public class AdvancedMutatronScreen extends AbstractMutatronScreen<AdvancedMutatronMenu> {
	@Nullable
	private GuiBetterButton leftButton;
	@Nullable
	private GuiBetterButton rightButton;

	public AdvancedMutatronScreen(AdvancedMutatronMenu menu, Inventory playerInv, Component title) {
		super(Gendustry.loc(Constants.TEXTURE_PATH_GUI + "/advanced_mutatron.png"), menu, playerInv, title);

		menu.setDataListener(this::updateButtonVisibility);
	}

	// React to changes in menu data
	private void updateButtonVisibility() {
		if (this.leftButton != null && this.rightButton != null) {
			this.leftButton.visible = this.rightButton.visible = this.menu.getPossibilityCount() > 4;
		}
	}

	@Override
	public void init() {
		super.init();

		Minecraft mc = Minecraft.getInstance();
		int containerId = this.menu.containerId;

		this.leftButton = new GuiBetterButton(this.leftPos + 130, this.topPos + 73, StandardButtonTextureSets.LEFT_BUTTON_SMALL, button -> {
			mc.gameMode.handleInventoryButtonClick(containerId, AdvancedMutatronMenu.BUTTON_CYCLE_LEFT);
		});
		this.rightButton = new GuiBetterButton(this.leftPos + 141, this.topPos + 73, StandardButtonTextureSets.RIGHT_BUTTON_SMALL, button -> {
			mc.gameMode.handleInventoryButtonClick(containerId, AdvancedMutatronMenu.BUTTON_CYCLE_RIGHT);
		});
		addRenderableWidget(this.leftButton);
		addRenderableWidget(this.rightButton);

		updateButtonVisibility();
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(graphics, partialTicks, mouseX, mouseY);

		int slots = Math.min(4, this.menu.getPossibilityCount());
		for (int i = 0; i < slots; ++i) {
			int x = this.leftPos + 63 + i * 16;
			int y = this.topPos + 70;
			int v = 176;

			if (this.menu.getSelected() == i + this.menu.getOffset()) {
				v += 18;
			} else if (mouseX >= x && mouseX < x + 16 && mouseY >= y && mouseY < y + 18) {
				v += 36;
			}

			graphics.blit(this.textureFile, x, y, 0, v, 16, 18);
		}
	}

	@Override
	protected void slotClicked(Slot slot, int slotId, int button, ClickType type) {
		super.slotClicked(slot, slotId, button, type);

		if (slot instanceof AdvancedMutatronMenu.ChoiceSlot choiceSlot) {
			Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, AdvancedMutatronMenu.CHOICE_CLICKED + choiceSlot.choiceIndex);
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/screen/IndustrialApiaryScreen.java

```java
package thedarkcolour.gendustry.client.screen;

import java.util.List;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import forestry.api.apiculture.genetics.BeeLifeStage;
import forestry.api.genetics.capability.IIndividualHandlerItem;
import forestry.core.config.Constants;
import forestry.core.gui.GuiForestryTitled;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.blockentity.IndustrialApiaryBlockEntity;
import thedarkcolour.gendustry.data.TranslationKeys;
import thedarkcolour.gendustry.menu.IndustrialApiaryMenu;

public class IndustrialApiaryScreen extends GuiForestryTitled<IndustrialApiaryMenu> {
	public IndustrialApiaryScreen(IndustrialApiaryMenu menu, Inventory playerInv, Component title) {
		super(Gendustry.loc(Constants.TEXTURE_PATH_GUI + "/apiary.png"), menu, playerInv, title);
	}

	@Override
	protected void drawWidgets(GuiGraphics graphics) {
		super.drawWidgets(graphics);
		graphics.blit(this.textureFile, 60, 21, 176, 0, getProgress(38), 18);
	}

	@Override
	protected void addLedgers() {
		addErrorLedger(this.menu.getTile());
		addPowerLedger(this.menu.getTile().getEnergyManager());
		addOwnerLedger(this.menu.getTile());
		addHintLedger(IndustrialApiaryBlockEntity.HINTS_KEY);
		addClimateLedger(this.menu.getTile());
	}

	@Override
	protected void renderTooltip(GuiGraphics pGuiGraphics, int pX, int pY) {
		super.renderTooltip(pGuiGraphics, pX, pY);

		if (isHovering(60, 21, 38, 18, pX, pY)) {
			pGuiGraphics.renderTooltip(this.font, Component.literal(getProgress(100) + "%"), pX, pY);
		}
	}

	private int getProgress(int pixels) {
		int progress = this.menu.getTile().getHealthScaled(pixels);
		if (IIndividualHandlerItem.filter(this.menu.getTile().getBeeInventory().getQueen(), (i, stage) -> stage == BeeLifeStage.QUEEN)) {
			progress = pixels - progress;
		}
		return progress;
	}

	static {
		HINTS.putAll(IndustrialApiaryBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_INDUSTRIAL_APIARY_USAGE,
				TranslationKeys.HINT_INDUSTRIAL_APIARY_UPGRADES
		));
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/screen/MutatronScreen.java

```java
package thedarkcolour.gendustry.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.config.Constants;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.menu.MutatronMenu;

public class MutatronScreen extends AbstractMutatronScreen<MutatronMenu> {
	public MutatronScreen(MutatronMenu menu, Inventory playerInv, Component title) {
		super(Gendustry.loc(Constants.TEXTURE_PATH_GUI + "/mutatron.png"), menu, playerInv, title);
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/screen/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.client.screen;
```

### src/main/java/thedarkcolour/gendustry/client/screen/ProducerScreen.java

```java
package thedarkcolour.gendustry.client.screen;

import java.util.List;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.config.Constants;
import forestry.core.gui.GuiForestryTitled;
import forestry.core.gui.widgets.TankWidget;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.blockentity.DnaExtractorBlockEntity;
import thedarkcolour.gendustry.blockentity.MutagenProducerBlockEntity;
import thedarkcolour.gendustry.blockentity.ProducerBlockEntity;
import thedarkcolour.gendustry.blockentity.ProteinLiquefierBlockEntity;
import thedarkcolour.gendustry.data.TranslationKeys;
import thedarkcolour.gendustry.menu.ProducerMenu;

public class ProducerScreen extends GuiForestryTitled<ProducerMenu> {
	private final ProducerBlockEntity<?, ?> tile;

	public ProducerScreen(ProducerMenu menu, Inventory playerInv, Component title) {
		super(Gendustry.loc(Constants.TEXTURE_PATH_GUI + "/processor.png"), menu, playerInv, title);

		this.tile = menu.getTile();
		this.widgetManager.add(new TankWidget(this.widgetManager, 122, 19, 0));
	}

	@Override
	protected void drawWidgets(GuiGraphics graphics) {
		super.drawWidgets(graphics);
		int progress = this.tile.getProgressScaled(55);
		graphics.blit(this.textureFile, 48, 40, 176, 60, progress, 18);

		if (this.tile.usesLabware) {
			graphics.blit(this.textureFile, 63, 18, 176, 78, 18, 18);
		}
	}

	@Override
	protected void addLedgers() {
		addErrorLedger(this.tile);
		addPowerLedger(this.tile.getEnergyManager());
		addHintLedger(this.tile.getHintsKey());
	}

	static {
		HINTS.putAll(MutagenProducerBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_MUTAGEN_USAGE,
				TranslationKeys.HINT_MUTAGEN_INGREDIENTS
		));
		HINTS.putAll(DnaExtractorBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_DNA_USAGE,
				TranslationKeys.HINT_DNA_INGREDIENTS
		));
		HINTS.putAll(ProteinLiquefierBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_PROTEIN_USAGE,
				TranslationKeys.HINT_PROTEIN_INGREDIENTS
		));
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/screen/ReplicatorScreen.java

```java
package thedarkcolour.gendustry.client.screen;

import java.util.List;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.config.Constants;
import forestry.core.gui.GuiForestryTitled;
import forestry.core.gui.widgets.TankWidget;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.blockentity.ReplicatorBlockEntity;
import thedarkcolour.gendustry.data.TranslationKeys;
import thedarkcolour.gendustry.menu.ReplicatorMenu;

public class ReplicatorScreen extends GuiForestryTitled<ReplicatorMenu> {
	private final ReplicatorBlockEntity tile;

	public ReplicatorScreen(ReplicatorMenu menu, Inventory playerInv, Component title) {
		super(Gendustry.loc(Constants.TEXTURE_PATH_GUI + "/replicator.png"), menu, playerInv, title);

		this.tile = menu.getTile();
		this.widgetManager.add(new TankWidget(this.widgetManager, 11, 8, 0));
		this.widgetManager.add(new TankWidget(this.widgetManager, 31, 8, 1));
		this.imageHeight = 176;
	}

	@Override
	protected void addLedgers() {
		addErrorLedger(this.tile);
		addPowerLedger(this.tile.getEnergyManager());
		addHintLedger(ReplicatorBlockEntity.HINTS_KEY);
	}

	@Override
	protected void drawWidgets(GuiGraphics graphics) {
		super.drawWidgets(graphics);
		int progress = this.tile.getProgressScaled(42);
		graphics.blit(this.textureFile, 70, 46, 176, 60, progress, 18);
	}

	static {
		HINTS.putAll(ReplicatorBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_DNA_USAGE,
				TranslationKeys.HINT_PROTEIN_USAGE,
				TranslationKeys.HINT_REPLICATOR_USAGE
		));
	}
}
```

### src/main/java/thedarkcolour/gendustry/client/screen/ThreeInputScreen.java

```java
package thedarkcolour.gendustry.client.screen;

import java.util.List;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.config.Constants;
import forestry.core.gui.GuiForestryTitled;
import forestry.core.tiles.TilePowered;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.blockentity.GeneticTransposerBlockEntity;
import thedarkcolour.gendustry.blockentity.IHintTile;
import thedarkcolour.gendustry.blockentity.ImprinterBlockEntity;
import thedarkcolour.gendustry.blockentity.SamplerBlockEntity;
import thedarkcolour.gendustry.data.TranslationKeys;
import thedarkcolour.gendustry.menu.ThreeInputMenu;

// Reused by both sampler and imprinter
public class ThreeInputScreen extends GuiForestryTitled<ThreeInputMenu<? extends IHintTile>> {
	private final TilePowered tile;
	private final String hintsKey;

	public ThreeInputScreen(ThreeInputMenu<? extends IHintTile> menu, Inventory inv, Component title) {
		super(Gendustry.loc(Constants.TEXTURE_PATH_GUI + "/sampler.png"), menu, inv, title);

		this.tile = menu.getTile();
		this.hintsKey = menu.getTile().getHintsKey();
	}

	@Override
	protected void drawWidgets(GuiGraphics graphics) {
		super.drawWidgets(graphics);
		int progress = this.tile.getProgressScaled(68);
		graphics.blit(this.textureFile, 53, 48, 176, 0, progress, 18);
	}

	@Override
	protected void addLedgers() {
		addErrorLedger(this.tile);
		addPowerLedger(this.tile.getEnergyManager());
		addHintLedger(this.hintsKey);
	}

	static {
		HINTS.putAll(SamplerBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_SAMPLE_USAGE,
				TranslationKeys.HINT_SAMPLE_REUSE,
				TranslationKeys.HINT_SAMPLE_SELECTION
		));
		HINTS.putAll(ImprinterBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_IMPRINTER_USAGE
		));
		HINTS.putAll(GeneticTransposerBlockEntity.HINTS_KEY, List.of(
				TranslationKeys.HINT_TRANSPOSER_USAGE
		));
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/forestry/GendustryError.java

```java
package thedarkcolour.gendustry.compat.forestry;

import java.util.Locale;

import net.minecraft.resources.ResourceLocation;

import forestry.api.core.IError;

import thedarkcolour.gendustry.Gendustry;

public enum GendustryError implements IError {
	NO_LABWARE,
	NO_SAMPLES,
	INCOMPATIBLE_SPECIES,
	NO_MUTATIONS,
	NO_MATES,
	NO_MUTAGEN,
	NO_TEMPLATE,
	NO_SELECTION,
	NO_BLANK,
	NO_SOURCE,
	NO_DNA,
	NO_PROTEIN,
	;

	private final ResourceLocation id;
	private final ResourceLocation sprite;
	private final String descriptionKey;
	private final String helpKey;

	GendustryError() {
		String name = name().toLowerCase(Locale.ENGLISH);
		this.id = Gendustry.loc(name);
		this.sprite = Gendustry.loc("errors/" + name);
		String idDotted = Gendustry.ID + '.' + name;
		this.descriptionKey = "errors." + idDotted + ".desc";
		this.helpKey = "errors." + idDotted + ".help";
	}

	@Override
	public String getDescriptionTranslationKey() {
		return this.descriptionKey;
	}

	@Override
	public String getHelpTranslationKey() {
		return this.helpKey;
	}

	@Override
	public ResourceLocation getSprite() {
		return this.sprite;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/forestry/GendustryForestryPlugin.java

```java
package thedarkcolour.gendustry.compat.forestry;

import net.minecraft.resources.ResourceLocation;

import forestry.api.plugin.IErrorRegistration;
import forestry.api.plugin.IForestryPlugin;

import thedarkcolour.gendustry.GendustryModule;

public class GendustryForestryPlugin implements IForestryPlugin {
	@Override
	public ResourceLocation id() {
		return GendustryModule.MODULE_ID;
	}

	@Override
	public void registerErrors(IErrorRegistration errors) {
		for (GendustryError error : GendustryError.values()) {
			errors.registerError(error);
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/forestry/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.compat.forestry;
```

### src/main/java/thedarkcolour/gendustry/compat/jei/GendustryJeiPlugin.java

```java
package thedarkcolour.gendustry.compat.jei;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import forestry.apiculture.compat.MutationRecipe;
import forestry.core.ClientsideCode;
import forestry.core.utils.RecipeUtils;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.client.screen.MutatronScreen;
import thedarkcolour.gendustry.client.screen.ProducerScreen;
import thedarkcolour.gendustry.compat.jei.producers.DNAExtractorRecipeCategory;
import thedarkcolour.gendustry.compat.jei.producers.MutagenRecipeCategory;
import thedarkcolour.gendustry.compat.jei.producers.ProducerGuiContainerHandler;
import thedarkcolour.gendustry.compat.jei.producers.ProteinProducerRecipeCategory;
import thedarkcolour.gendustry.data.TranslationKeys;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.gendustry.registry.GItems;
import thedarkcolour.gendustry.registry.GRecipeTypes;

@JeiPlugin
public class GendustryJeiPlugin implements IModPlugin {
	public static final ResourceLocation ID = Gendustry.loc("jei");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		registration.registerSubtypeInterpreter(GItems.GENE_SAMPLE.item(), new GeneSampleInterpreter());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
		registration.addRecipeCategories(new MutagenRecipeCategory(guiHelper));
		registration.addRecipeCategories(new ProteinProducerRecipeCategory(guiHelper));
		registration.addRecipeCategories(new DNAExtractorRecipeCategory(guiHelper));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(MutagenRecipeCategory.ICON_STACK, GendustryRecipeType.MUTAGEN_PRODUCER);
		registration.addRecipeCatalyst(DNAExtractorRecipeCategory.ICON_STACK, GendustryRecipeType.DNA_EXTRACTOR);
		registration.addRecipeCatalyst(ProteinProducerRecipeCategory.ICON_STACK, GendustryRecipeType.PROTEIN_LIQUEFIER);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager manager = ClientsideCode.getRecipeManager();
		registration.addRecipes(GendustryRecipeType.MUTAGEN_PRODUCER, RecipeUtils.getRecipes(manager, GRecipeTypes.MUTAGEN).toList());
		registration.addRecipes(GendustryRecipeType.PROTEIN_LIQUEFIER, RecipeUtils.getRecipes(manager, GRecipeTypes.PROTEIN).toList());
		registration.addRecipes(GendustryRecipeType.DNA_EXTRACTOR, RecipeUtils.getRecipes(manager, GRecipeTypes.DNA).toList());

		registration.addIngredientInfo(GFluids.MUTAGEN.fluidStack(1000), ForgeTypes.FLUID_STACK, Component.translatable(TranslationKeys.JEI_INFO_MUTAGEN));
		registration.addIngredientInfo(GFluids.LIQUID_DNA.fluidStack(1000), ForgeTypes.FLUID_STACK, Component.translatable(TranslationKeys.JEI_INFO_DNA));
		registration.addIngredientInfo(GFluids.PROTEIN.fluidStack(1000), ForgeTypes.FLUID_STACK, Component.translatable(TranslationKeys.JEI_INFO_PROTEIN));
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		RecipeType<?>[] mutationTypes = registration.getJeiHelpers().getAllRecipeTypes()
				.filter(type -> type.getRecipeClass() == MutationRecipe.class)
				.toArray(RecipeType[]::new);

		registration.addRecipeClickArea(MutatronScreen.class, 68, 38, 55, 18, mutationTypes);
		registration.addGuiContainerHandler(ProducerScreen.class, new ProducerGuiContainerHandler());
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/jei/GendustryRecipeType.java

```java
package thedarkcolour.gendustry.compat.jei;

import mezz.jei.api.recipe.RecipeType;
import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.recipe.DnaRecipe;
import thedarkcolour.gendustry.recipe.MutagenRecipe;
import thedarkcolour.gendustry.recipe.ProteinRecipe;

public class GendustryRecipeType {
	public static final RecipeType<MutagenRecipe> MUTAGEN_PRODUCER = create("mutagen_producer", MutagenRecipe.class);
	public static final RecipeType<DnaRecipe> DNA_EXTRACTOR = create("dna_extractor", DnaRecipe.class);
	public static final RecipeType<ProteinRecipe> PROTEIN_LIQUEFIER = create("protein_liquefier", ProteinRecipe.class);

	private static <T> RecipeType<T> create(String uid, Class<? extends T> recipeClass) {
		return RecipeType.create(Gendustry.ID, uid, recipeClass);
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/jei/GeneSampleInterpreter.java

```java
package thedarkcolour.gendustry.compat.jei;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;

class GeneSampleInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
	@Override
	public String apply(ItemStack ingredient, UidContext context) {
		// not sure if this is the correct way to use the parameter but hey
		if (context == UidContext.Recipe) {
			return "written";
		}

		if (ingredient.hasTag()) {
			CompoundTag nbt = ingredient.getTag();

			if (nbt.contains("speciesType") && nbt.contains("chromosome") && nbt.contains("allele")) {
				return nbt.getString("speciesType") + nbt.getString("chromosome") + nbt.getString("allele");
			}
		}
		return IIngredientSubtypeInterpreter.NONE;
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/jei/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.compat.jei;
```

### src/main/java/thedarkcolour/gendustry/compat/jei/producers/DNAExtractorRecipeCategory.java

```java
package thedarkcolour.gendustry.compat.jei.producers;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.compat.jei.GendustryRecipeType;
import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.recipe.DnaRecipe;
import thedarkcolour.gendustry.registry.GBlocks;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.gendustry.registry.GItems;

public class DNAExtractorRecipeCategory extends ProducerRecipeCategory<DnaRecipe> {
	public static final ItemStack ICON_STACK = new ItemStack(GBlocks.MACHINE.get(GendustryMachineType.DNA_EXTRACTOR).block());

	private final IDrawable labwareSlot;

	public DNAExtractorRecipeCategory(IGuiHelper helper) {
		super(helper, GendustryMachineType.DNA_EXTRACTOR, ICON_STACK);
		this.labwareSlot = helper.createDrawable(GUI, 176, 78, 18, 18);
	}

	@Override
	public RecipeType<DnaRecipe> getRecipeType() {
		return GendustryRecipeType.DNA_EXTRACTOR;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, DnaRecipe recipe, IFocusGroup iFocusGroup) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 23).addItemStack(recipe.getSpeciesType().getDefaultSpecies().createStack(recipe.getStage()));
		addFluidTank(builder, GFluids.LIQUID_DNA.fluid(), recipe.getAmount());
		builder.addSlot(RecipeIngredientRole.INPUT, 51, 1).addItemStack(GItems.RESOURCE.item(GendustryResourceType.LABWARE).getDefaultInstance()).addRichTooltipCallback((recipeSlotView, tooltip) -> {
			tooltip.add(Component.translatable("gen.for.chance", 10).withStyle(ChatFormatting.AQUA));
		});
	}

	@Override
	public void draw(DnaRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.labwareSlot.draw(graphics, 50, 0);
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/jei/producers/MutagenRecipeCategory.java

```java
package thedarkcolour.gendustry.compat.jei.producers;

import net.minecraft.world.item.ItemStack;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.compat.jei.GendustryRecipeType;
import thedarkcolour.gendustry.recipe.MutagenRecipe;
import thedarkcolour.gendustry.registry.GBlocks;
import thedarkcolour.gendustry.registry.GFluids;

public class MutagenRecipeCategory extends ProducerRecipeCategory<MutagenRecipe> {
	public static final ItemStack ICON_STACK = new ItemStack(GBlocks.MACHINE.get(GendustryMachineType.MUTAGEN_PRODUCER).block());

	public MutagenRecipeCategory(IGuiHelper helper) {
		super(helper, GendustryMachineType.MUTAGEN_PRODUCER, ICON_STACK);
	}

	@Override
	public RecipeType<MutagenRecipe> getRecipeType() {
		return GendustryRecipeType.MUTAGEN_PRODUCER;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, MutagenRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 23).addIngredients(recipe.getIngredient());
		addFluidTank(builder, GFluids.MUTAGEN.fluid(), recipe.getAmount());
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/jei/producers/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.compat.jei.producers;
```

### src/main/java/thedarkcolour/gendustry/compat/jei/producers/ProducerGuiContainerHandler.java

```java
package thedarkcolour.gendustry.compat.jei.producers;

import java.util.Collection;
import java.util.Collections;

import net.minecraft.world.level.block.entity.BlockEntity;

import mezz.jei.api.gui.handlers.IGuiClickableArea;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import thedarkcolour.gendustry.blockentity.DnaExtractorBlockEntity;
import thedarkcolour.gendustry.blockentity.MutagenProducerBlockEntity;
import thedarkcolour.gendustry.blockentity.ProteinLiquefierBlockEntity;
import thedarkcolour.gendustry.client.screen.ProducerScreen;
import thedarkcolour.gendustry.compat.jei.GendustryRecipeType;

public class ProducerGuiContainerHandler implements IGuiContainerHandler<ProducerScreen> {
	@Override
	public Collection<IGuiClickableArea> getGuiClickableAreas(ProducerScreen containerScreen, double guiMouseX, double guiMouseY) {
		BlockEntity blockEntity = containerScreen.getMenu().getTile();
		if (blockEntity instanceof MutagenProducerBlockEntity) {
			return Collections.singleton(IGuiClickableArea.createBasic(48, 40, 55, 18, GendustryRecipeType.MUTAGEN_PRODUCER));
		} else if (blockEntity instanceof DnaExtractorBlockEntity) {
			return Collections.singleton(IGuiClickableArea.createBasic(48, 40, 55, 18, GendustryRecipeType.DNA_EXTRACTOR));
		} else if (blockEntity instanceof ProteinLiquefierBlockEntity) {
			return Collections.singleton(IGuiClickableArea.createBasic(48, 40, 55, 18, GendustryRecipeType.PROTEIN_LIQUEFIER));
		}
		return Collections.emptyList();
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/jei/producers/ProducerRecipeCategory.java

```java
package thedarkcolour.gendustry.compat.jei.producers;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import net.minecraftforge.fluids.FluidStack;

import forestry.core.recipes.jei.ForestryRecipeCategory;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeIngredientRole;
import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.recipe.ProcessorRecipe;
import thedarkcolour.gendustry.registry.GBlocks;

public abstract class ProducerRecipeCategory<T extends ProcessorRecipe> extends ForestryRecipeCategory<T> {
	protected static final ResourceLocation GUI = new ResourceLocation(Gendustry.ID, "textures/gui/processor.png");

	private final IDrawableAnimated arrow;
	private final IDrawable tankOverlay;
	private final IDrawable icon;

	public ProducerRecipeCategory(IGuiHelper helper, GendustryMachineType type, ItemStack stack) {
		super(helper.createDrawable(GUI, 13, 18, 151, 60), GBlocks.MACHINE.get(type).getTranslationKey());

		IDrawableStatic arrowDrawable = helper.createDrawable(GUI, 176, 60, 55, 18);
		this.arrow = helper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
		this.tankOverlay = helper.createDrawable(GUI, 176, 0, 16, 58);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, stack);
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		this.arrow.draw(graphics, 35, 23);
	}

	protected void addFluidTank(IRecipeLayoutBuilder builder, Fluid fluid, int amount) {
		builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 1)
				.setFluidRenderer(10000, false, 16, 58)
				.setOverlay(tankOverlay, 0, 0)
				.addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(fluid, amount));
	}
}
```

### src/main/java/thedarkcolour/gendustry/compat/jei/producers/ProteinProducerRecipeCategory.java

```java
package thedarkcolour.gendustry.compat.jei.producers;

import net.minecraft.world.item.ItemStack;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.compat.jei.GendustryRecipeType;
import thedarkcolour.gendustry.recipe.ProteinRecipe;
import thedarkcolour.gendustry.registry.GBlocks;
import thedarkcolour.gendustry.registry.GFluids;

public class ProteinProducerRecipeCategory extends ProducerRecipeCategory<ProteinRecipe> {
	public static final ItemStack ICON_STACK = new ItemStack(GBlocks.MACHINE.get(GendustryMachineType.PROTEIN_LIQUEFIER).block());

	public ProteinProducerRecipeCategory(IGuiHelper helper) {
		super(helper, GendustryMachineType.PROTEIN_LIQUEFIER, ICON_STACK);
	}

	@Override
	public RecipeType<ProteinRecipe> getRecipeType() {
		return GendustryRecipeType.PROTEIN_LIQUEFIER;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ProteinRecipe recipe, IFocusGroup iFocusGroup) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 23).addIngredients(recipe.getIngredient());
		addFluidTank(builder, GFluids.PROTEIN.fluid(), recipe.getAmount());
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/BlockLoot.java

```java
package thedarkcolour.gendustry.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import thedarkcolour.gendustry.block.GendustryMachineBlock;
import thedarkcolour.gendustry.registry.GBlocks;

// Copied from: https://github.com/thedarkcolour/ExDeorum/blob/1.20.1/src/main/java/thedarkcolour/exdeorum/data/BlockLoot.java
class BlockLoot extends BlockLootSubProvider {
	private final List<Block> added = new ArrayList<>();

	BlockLoot() {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
	}

	@Override
	protected void generate() {
		for (GendustryMachineBlock block : GBlocks.MACHINE.getBlocks()) {
			dropSelf(block);
		}
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return this.added;
	}

	@Override
	protected void add(Block block, LootTable.Builder builder) {
		super.add(block, builder);
		this.added.add(block);
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/BlockModels.java

```java
package thedarkcolour.gendustry.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import forestry.modules.features.FeatureBlock;

import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.registry.GBlocks;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.modkit.data.MKBlockModelProvider;
import static forestry.core.data.models.ForestryBlockStateProvider.path;

class BlockModels {
	static void addBlockModels(MKBlockModelProvider models) {
		for (GendustryMachineType type : GendustryMachineType.values()) {
			machine(models, GBlocks.MACHINE.get(type));
		}

		for (GFluids fluid : GFluids.values()) {
			Block block = fluid.getFeature().fluidBlock().block();
			ModelFile blockModel = models.models().getBuilder(path(block)).texture("particle", fluid.getFeature().properties().resources[0]);
			models.getVariantBuilder(block).partialState().modelForState().modelFile(blockModel).addModel();
		}
	}

	private static void machine(MKBlockModelProvider models, FeatureBlock<?, ?> block) {
		ResourceLocation texture = models.blockTexture(block.block());
		BlockModelBuilder model = models.models().cubeBottomTop(block.getName(), texture.withSuffix("_side"), texture.withSuffix("_bottom"), texture.withSuffix("_top"));

		models.getVariantBuilder(block.block()).partialState().setModels(new ConfiguredModel(model, 0, 0, false));
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/Data.java

```java
package thedarkcolour.gendustry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.modkit.data.DataHelper;

public class Data {
	public static void gatherData(GatherDataEvent event) {
		DataHelper helper = new DataHelper(Gendustry.ID, event);
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();

		helper.createEnglish(true, English::addTranslations);
		helper.createRecipes(Recipes::addRecipes);
		helper.createBlockModels(BlockModels::addBlockModels);
		helper.createItemModels(true, true, false, null);
		helper.createTags(Registries.BLOCK, ModTags::addBlockTags);
		helper.createTags(Registries.ITEM, ModTags::addItemTags);

		generator.addProvider(event.includeServer(), new LootProvider(output));
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/English.java

```java
package thedarkcolour.gendustry.data;

import net.minecraft.world.level.ItemLike;

import forestry.api.core.IError;

import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.compat.forestry.GendustryError;
import thedarkcolour.gendustry.item.EliteGendustryUpgradeType;
import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.item.GendustryUpgradeType;
import thedarkcolour.gendustry.registry.GBlocks;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.gendustry.registry.GItems;
import thedarkcolour.modkit.data.MKEnglishProvider;

class English {
    static void addTranslations(MKEnglishProvider lang) {
		// Translation keys
		lang.add(TranslationKeys.TEMPLATE_MISSING_ALLELE, "MISSING");
		lang.add(TranslationKeys.TEMPLATE_ALLELE_ENTRY, "  %1$s - %2$s");
		lang.add(TranslationKeys.TEMPLATE_ALLELE_COUNT, "Alleles (%1$s/%2$s)");
		lang.add(TranslationKeys.UPGRADE_ENERGY_COST, "Energy Cost: %s RF");
		lang.add(TranslationKeys.UPGRADE_STACK_LIMIT, "Max Count: %s");

		// JEI
		lang.add(TranslationKeys.JEI_LABWARE_CHANCE, "%s%% chance to be consumed!");
		lang.add(TranslationKeys.JEI_INFO_MUTAGEN, "Mutagen is used in the Mutatron and Advanced Mutatron to trigger mutations between two organisms.");
		lang.add(TranslationKeys.JEI_INFO_DNA, "Liquid DNA is used in the Replicator to produce new organisms from complete Genetic Templates.");
		lang.add(TranslationKeys.JEI_INFO_PROTEIN, "Protein is used in the Replicator to produce new organisms from complete Genetic Templates.");

		// Machine hints
		addHint(lang, TranslationKeys.HINT_MUTAGEN_USAGE, "What's Mutagen for?", "Produce Mutagen to use in other Gendustry machines.");
		addHint(lang, TranslationKeys.HINT_MUTAGEN_INGREDIENTS, "What makes Mutagen?", "Mutagen can be made from redstone, glowstone, and even uranium!");
		addHint(lang, TranslationKeys.HINT_DNA_USAGE, "How to use Liquid DNA?", "Use Liquid DNA in the Replicator to construct new organisms!");
		addHint(lang, TranslationKeys.HINT_DNA_INGREDIENTS, "What makes Liquid DNA?", "Liquid DNA can be made from any organisms with a Forestry genome, like bees, saplings and pollen, and butterflies.");
		addHint(lang, TranslationKeys.HINT_PROTEIN_USAGE, "How to use Protein?", "Protein is an ingredient used by the Replicator to create new organisms.");
		addHint(lang, TranslationKeys.HINT_PROTEIN_INGREDIENTS, "How to get Protein?", "Protein can be made from any kind of raw meat.");
		addHint(lang, TranslationKeys.HINT_SAMPLE_USAGE, "What are samples for?", "Gene samples can be crafted with a Genetic Template to create a complete genome for use in the Imprinter.");
		addHint(lang, TranslationKeys.HINT_SAMPLE_REUSE, "Don't throw away samples!", "Unwanted gene samples can be wiped blank by heating them in a furnace.");
		addHint(lang, TranslationKeys.HINT_SAMPLE_SELECTION, "How to choose a gene?", "The Sampler picks a random allele from the specimen's genome and saves it to a gene sample.");
		addHint(lang, TranslationKeys.HINT_IMPRINTER_USAGE, "How to use the Imprinter?", "The Imprinter replaces the genome of an individual with the alleles stored in a Genetic Template. Ignoble stock may not survive.");
		addHint(lang, TranslationKeys.HINT_TRANSPOSER_USAGE, "How to use the Genetic Transposer?", "The Genetic Transposer creates copies of Gene Samples and Genetic Templates.");
		addHint(lang, TranslationKeys.HINT_REPLICATOR_USAGE, "How to use the Replicator?", "The Replicator produces a new organism from a Genetic Template using Liquid DNA and Protein.");
		addHint(lang, TranslationKeys.HINT_MUTATRON_USAGE, "What is the Mutatron?", "The Mutatron triggers a mutation between two parent organisms, yielding offspring of a new species.");
		addHint(lang, TranslationKeys.HINT_ADVANCED_MUTATRON_USAGE, "How to use the Advanced Mutatron?", "To choose the desired mutation between the two parents, use the Advanced Mutatron.");
		addHint(lang, TranslationKeys.HINT_INDUSTRIAL_APIARY_USAGE, "How to use the Industrial Apiary?", "The left slots are for a Princess and Drone. The four middle slots are for upgrades. The nine right slots are outputs.");
		addHint(lang, TranslationKeys.HINT_INDUSTRIAL_APIARY_UPGRADES, "Why no frames?", "The Industrial Apiary does not need frames. Instead, it uses upgrades that affect climate, productivity, lifespan, and more!");

		// Item translation overrides
		lang.add(GBlocks.MACHINE.get(GendustryMachineType.DNA_EXTRACTOR).block(), "DNA Extractor");
		lang.add(GFluids.LIQUID_DNA.fluid().getFluidType(), "Liquid DNA");
		lang.add(GItems.GENE_SAMPLE.get(), "Gene Sample (%s)");
		lang.add(GItems.GENETIC_TEMPLATE.get(), "Genetic Template (%s)");

		// Machine errors
		addError(lang, GendustryError.NO_LABWARE, "No Labware", "This machine requires Labware to operate.");
		addError(lang, GendustryError.NO_SAMPLES, "No Samples", "This machine requires Blank Gene Samples to operate.");
		addError(lang, GendustryError.INCOMPATIBLE_SPECIES, "Incompatible species", "Individuals may only be mated with individuals of the same species type.");
		addError(lang, GendustryError.NO_MUTATIONS, "No Mutations", "There are no mutations between these two species. Please choose different species.");
		addError(lang, GendustryError.NO_MATES, "No Mates", "Two compatible mates are required for a mutation to occur.");
		addError(lang, GendustryError.NO_MUTAGEN, "No Mutagen", "Mutagen is required to trigger a mutation.");
		addError(lang, GendustryError.NO_TEMPLATE, "Missing template", "A complete Genetic Template is required to operate.");
		addError(lang, GendustryError.NO_SELECTION, "Select a mutation", "You must choose a mutation for the Advanced Mutatron.");
		addError(lang, GendustryError.NO_BLANK, "Missing blank template/sample", "The Genetic Transposer needs Blank Gene Samples or Blank Genetic Templates to copy to.");
		addError(lang, GendustryError.NO_SOURCE, "Missing source template/sample", "The Genetic Transposer is missing a filled Gene Sample or Genetic Template.");
		addError(lang, GendustryError.NO_DNA, "Missing Liquid DNA", "This machine requires Liquid DNA to operate.");
		addError(lang, GendustryError.NO_PROTEIN, "Missing Protein", "This machine requires Protein to operate.");

		// Creative tabs
		lang.add("itemGroup.gendustry", "Gendustry");
		lang.add("itemGroup.gene_samples", "Gene Samples");

		// Item tooltips
		addTooltip(lang, GItems.RESOURCE.get(GendustryResourceType.BLANK_GENETIC_TEMPLATE), "Combine with Gene Samples in a Crafting Table");
		addTooltip(lang, GItems.RESOURCE.get(GendustryResourceType.BLANK_GENE_SAMPLE), "Use in the Sampler to obtain Gene Samples");

		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.AUTOMATION), "Automatically recycles princesses and drones from deceased queens.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.HEATER), "Raises the temperature of the apiary by 1 step.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.COOLER), "Lowers the temperature of the apiary by 1 step.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.HUMIDIFIER), "Raises the humidity of the apiary by 1 step.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.DRYER), "Lowers the humidity of the apiary by 1 step.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.POLLINATION), "Increases bee pollination by 25%.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.SCRUBBER), "Disables bee pollination.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.NETHER), "Sets the apiary's climate to Hellish temperature and Arid humidity.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.LIFESPAN), "Decreases lifespan by 20%.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.LIGHTING), "Allows bees to work without needing to sleep.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.PRODUCTIVITY), "Increases bee productivity by 25%.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.WEATHERPROOF), "Allows bees to work during the rain.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.SIEVE), "Allows collecting pollen from nearby trees, like the Alveary Sieve.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.SKY), "Simulates a view of the sky for bees that aren't cave dwelling.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.STABILIZER), "Prevents Ignoble Stock bees from dying.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.TERRITORY), "Increases territory by 25%.");
		addTooltip(lang, GItems.UPGRADE.get(GendustryUpgradeType.IMMUTABLE), "Prevents bee mutations from occurring.");

		addTooltip(lang, GItems.ELITE_UPGRADE.get(EliteGendustryUpgradeType.MUTATION), "Increases bee mutation chances by 25%.");
		addTooltip(lang, GItems.ELITE_UPGRADE.get(EliteGendustryUpgradeType.ACTIVITY_SIMULATOR), "A combination of the Sky, Weatherproof, and Lighting upgrades.");
		addTooltip(lang, GItems.ELITE_UPGRADE.get(EliteGendustryUpgradeType.PRODUCTIVITY), "Increases bee productivity by 25% and speeds up work cycle by 15 ticks.");
		addTooltip(lang, GItems.ELITE_UPGRADE.get(EliteGendustryUpgradeType.TERRITORY), "Increases bee territory by 25%, but has a higher limit.");
		addTooltip(lang, GItems.ELITE_UPGRADE.get(EliteGendustryUpgradeType.YOUTH), "Increases lifespan by 20%.");
		addTooltip(lang, GItems.ELITE_UPGRADE.get(EliteGendustryUpgradeType.FERTILITY), "Increases fertility count by 1.");
	}

	private static void addHint(MKEnglishProvider lang, String hint, String title, String description) {
		lang.add("for.hints." + hint + ".tag", title);
		lang.add("for.hints." + hint + ".desc", description);
	}

	private static void addError(MKEnglishProvider lang, IError error, String title, String description) {
		String path = error.getId().getPath();
		String namespace = error.getId().getNamespace();
		String combined = namespace + '.' + path;

		lang.add("errors." + combined + ".desc", title);
		lang.add("errors." + combined + ".help", description);
	}

	// ItemForestry allows adding tooltips to items like this
	private static void addTooltip(MKEnglishProvider lang, ItemLike item, String tooltip) {
		lang.add(item.asItem().getDescriptionId() + ".tooltip", tooltip);
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/LootProvider.java

```java
package thedarkcolour.gendustry.data;

import java.util.List;
import java.util.Set;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

class LootProvider extends LootTableProvider {
	public LootProvider(PackOutput pOutput) {
		super(pOutput, Set.of(), List.of(new SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)));
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/ModTags.java

```java
package thedarkcolour.gendustry.data;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import thedarkcolour.gendustry.api.GendustryTags;
import thedarkcolour.gendustry.registry.GBlocks;
import thedarkcolour.gendustry.registry.GItems;
import thedarkcolour.modkit.data.MKTagsProvider;

class ModTags {
	static void addItemTags(MKTagsProvider<Item> tags) {
		tags.tag(GendustryTags.Items.UPGRADES).add(GItems.UPGRADE.getItems().toArray(Item[]::new));
		tags.tag(GendustryTags.Items.UPGRADES).add(GItems.ELITE_UPGRADE.getItems().toArray(Item[]::new));
	}

	static void addBlockTags(MKTagsProvider<Block> tags) {
		tags.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(GBlocks.MACHINE.blockArray());
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.data;
```

### src/main/java/thedarkcolour/gendustry/data/Recipes.java

```java
package thedarkcolour.gendustry.data;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import forestry.api.ForestryConstants;
import forestry.api.ForestryTags;
import forestry.api.IForestryApi;
import forestry.api.apiculture.genetics.BeeLifeStage;
import forestry.api.arboriculture.genetics.TreeLifeStage;
import forestry.api.genetics.ForestrySpeciesTypes;
import forestry.api.genetics.ILifeStage;
import forestry.api.lepidopterology.genetics.ButterflyLifeStage;
import forestry.core.items.ItemForestry;
import forestry.modules.features.FeatureItem;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.item.EliteGendustryUpgradeType;
import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.item.GendustryUpgradeType;
import thedarkcolour.gendustry.recipe.DnaFinishedRecipe;
import thedarkcolour.gendustry.recipe.MutagenFinishedRecipe;
import thedarkcolour.gendustry.recipe.ProteinFinishedRecipe;
import thedarkcolour.gendustry.registry.GBlocks;
import thedarkcolour.gendustry.registry.GFluids;
import thedarkcolour.gendustry.registry.GItems;
import thedarkcolour.gendustry.registry.GRecipeTypes;
import thedarkcolour.modkit.data.MKRecipeProvider;

class Recipes {
	private static final RegistryObject<Item> SILK_WISP = RegistryObject.create(ForestryConstants.forestry("silk_wisp"), ForgeRegistries.ITEMS);
	private static final RegistryObject<Item> BEESWAX = RegistryObject.create(ForestryConstants.forestry("beeswax"), ForgeRegistries.ITEMS);
	private static final RegistryObject<Item> ROYAL_JELLY = RegistryObject.create(ForestryConstants.forestry("royal_jelly"), ForgeRegistries.ITEMS);
	private static final RegistryObject<Item> POLLEN_CLUSTER = RegistryObject.create(ForestryConstants.forestry("pollen_cluster_normal"), ForgeRegistries.ITEMS);
	private static final RegistryObject<Item> STURDY_CASING = RegistryObject.create(ForestryConstants.forestry("sturdy_machine"), ForgeRegistries.ITEMS);

	static void addRecipes(Consumer<FinishedRecipe> writer, MKRecipeProvider recipes) {
		// Genetic Template
		recipes.special("combine_genetic_template", GRecipeTypes.GENETIC_TEMPLATE_SERIALIZER);

		// Mutagen recipes
		mutagen(writer, Items.REDSTONE, 100);
		mutagen(writer, Items.GLOWSTONE_DUST, 200);
		mutagen(writer, Items.GLOWSTONE, 800);
		mutagen(writer, Items.REDSTONE_BLOCK, 900);
		// todo Yellorium and Uranium

		// Protein recipes
		protein(writer, Items.PORKCHOP, 500);
		protein(writer, Items.BEEF, 500);
		protein(writer, Items.RABBIT, 250);
		protein(writer, Items.COD, 250);
		protein(writer, Items.SALMON, 250);
		protein(writer, Items.PUFFERFISH, 250);
		protein(writer, Items.TROPICAL_FISH, 250);

		// DNA recipes
		dna(writer, ForestrySpeciesTypes.BEE, BeeLifeStage.DRONE, 100);
		dna(writer, ForestrySpeciesTypes.BEE, BeeLifeStage.PRINCESS, 500);
		dna(writer, ForestrySpeciesTypes.BEE, BeeLifeStage.QUEEN, 600);
		dna(writer, ForestrySpeciesTypes.BEE, BeeLifeStage.LARVAE, 300);
		dna(writer, ForestrySpeciesTypes.TREE, TreeLifeStage.SAPLING, 100);
		dna(writer, ForestrySpeciesTypes.TREE, TreeLifeStage.POLLEN, 400);
		dna(writer, ForestrySpeciesTypes.BUTTERFLY, ButterflyLifeStage.BUTTERFLY, 200);
		dna(writer, ForestrySpeciesTypes.BUTTERFLY, ButterflyLifeStage.SERUM, 800);
		dna(writer, ForestrySpeciesTypes.BUTTERFLY, ButterflyLifeStage.CATERPILLAR, 1000);
		dna(writer, ForestrySpeciesTypes.BUTTERFLY, ButterflyLifeStage.COCOON, 1000);

		// Crafting recipes
		resourceCraftingRecipes(recipes);
		upgradeCraftingRecipes(recipes);
		eliteUpgradeCraftingRecipes(recipes);
		machineCraftingRecipes(recipes);

		// Pollen Kit
		recipes.shapelessCrafting(RecipeCategory.TOOLS, GItems.POLLEN_KIT, 1, GItems.RESOURCE.item(GendustryResourceType.LABWARE), Tags.Items.STRING, Items.PAPER);
	}

	private static void resourceCraftingRecipes(MKRecipeProvider recipes) {
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.LABWARE), 2, recipe -> {
			recipe.define('G', Tags.Items.GLASS_PANES);
			recipe.define('D', Tags.Items.GEMS_DIAMOND);
			recipe.pattern("G G");
			recipe.pattern("G G");
			recipe.pattern(" D ");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.UPGRADE_FRAME), 2, recipe -> {
			recipe.define('I', ForestryTags.Items.INGOTS_TIN);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('G', Tags.Items.NUGGETS_GOLD);
			recipe.pattern("IGI");
			recipe.pattern("R R");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.ELITE_UPGRADE_FRAME), 2, recipe -> {
			recipe.define('I', Tags.Items.STORAGE_BLOCKS_GOLD);
			recipe.define('R', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('T', Items.GHAST_TEAR);
			recipe.pattern("ITI");
			recipe.pattern("RGR");
			recipe.pattern("ITI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.CLIMATE_CONTROL_MODULE), recipe -> {
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('B', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.pattern("BRB");
			recipe.pattern("BGB");
			recipe.pattern("BRB");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE), recipe -> {
			recipe.define('R', Tags.Items.STORAGE_BLOCKS_REDSTONE);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('I', Tags.Items.INGOTS_GOLD);
			recipe.define('P', Items.PISTON);
			recipe.pattern("GIG");
			recipe.pattern("PRP");
			recipe.pattern("GIG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR), recipe -> {
			recipe.define('D', Tags.Items.GEMS_DIAMOND);
			recipe.define('Q', Tags.Items.GEMS_QUARTZ);
			recipe.define('P', Tags.Items.ENDER_PEARLS);
			recipe.pattern("DQD");
			recipe.pattern("QPQ");
			recipe.pattern("DQD");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.ENVIRONMENTAL_PROCESSOR), recipe -> {
			recipe.define('D', Tags.Items.GEMS_DIAMOND);
			recipe.define('Q', Tags.Items.GEMS_LAPIS);
			recipe.define('P', Tags.Items.INGOTS_GOLD);
			recipe.pattern("DQD");
			recipe.pattern("QPQ");
			recipe.pattern("DQD");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.BLANK_GENE_SAMPLE), recipe -> {
			recipe.define('I', ForestryTags.Items.INGOTS_TIN);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern(" I ");
			recipe.pattern("IRI");
			recipe.pattern(" I ");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE), recipe -> {
			recipe.define('I', GItems.RESOURCE.item(GendustryResourceType.BLANK_GENE_SAMPLE));
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('D', Tags.Items.GEMS_DIAMOND);
			recipe.pattern("RIR");
			recipe.pattern("IDI");
			recipe.pattern("RIR");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.RESOURCE.item(GendustryResourceType.RECEPTACLE), recipe -> {
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('P', Tags.Items.GLASS_PANES);
			recipe.define('G', Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern("III");
			recipe.pattern("IPI");
			recipe.pattern("RGR");
		});

		// Furnace recipes
		recipes.renameRecipes(oldId -> oldId.withSuffix("_wipe_dna"), finishedRecipe -> {
			recipes.smelting(GItems.GENE_SAMPLE, GItems.RESOURCE.item(GendustryResourceType.BLANK_GENE_SAMPLE), 0.1f);
			recipes.smelting(GItems.GENETIC_TEMPLATE, GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE), 0.1f);
		});
	}

	private static void upgradeCraftingRecipes(MKRecipeProvider recipes) {
		FeatureItem<ItemForestry> upgradeFrame = GItems.RESOURCE.get(GendustryResourceType.UPGRADE_FRAME);

		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.AUTOMATION), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('C', Items.COMPARATOR);
			recipe.pattern(" G ");
			recipe.pattern("RFR");
			recipe.pattern(" C ");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.HEATER), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('C', GItems.RESOURCE.item(GendustryResourceType.CLIMATE_CONTROL_MODULE));
			recipe.define('P', Items.BLAZE_POWDER);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("PPP");
			recipe.pattern("IFI");
			recipe.pattern("ICI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.COOLER), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('C', GItems.RESOURCE.item(GendustryResourceType.CLIMATE_CONTROL_MODULE));
			recipe.define('P', Items.ICE);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("PPP");
			recipe.pattern("IFI");
			recipe.pattern("ICI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.HUMIDIFIER), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('C', GItems.RESOURCE.item(GendustryResourceType.CLIMATE_CONTROL_MODULE));
			recipe.define('P', Tags.Items.MUSHROOMS);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("PPP");
			recipe.pattern("IFI");
			recipe.pattern("ICI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.DRYER), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('C', GItems.RESOURCE.item(GendustryResourceType.CLIMATE_CONTROL_MODULE));
			recipe.define('P', Items.DEAD_BUSH);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("PPP");
			recipe.pattern("IFI");
			recipe.pattern("ICI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.POLLINATION), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('P', ItemTags.SMALL_FLOWERS);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("PPP");
			recipe.pattern("IFI");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.SCRUBBER), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', SILK_WISP.get());
			recipe.pattern("SGS");
			recipe.pattern("GFG");
			recipe.pattern("SGS");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.NETHER), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('C', Items.CRIMSON_NYLIUM);
			recipe.define('W', Items.WARPED_NYLIUM);
			recipe.define('S', Items.SOUL_SAND);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("CWS");
			recipe.pattern("IFI");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.LIFESPAN), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('E', Items.WITHER_ROSE);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern("ERE");
			recipe.pattern("RFR");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.LIGHTING), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('D', Items.GLOWSTONE);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern("DRD");
			recipe.pattern("RFR");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.PRODUCTIVITY), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('D', Tags.Items.GEMS_EMERALD);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern("DRD");
			recipe.pattern("RFR");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.WEATHERPROOF), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('D', BEESWAX);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern("DRD");
			recipe.pattern("RFR");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.SIEVE), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', SILK_WISP.get());
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("SSS");
			recipe.pattern("SFS");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.SKY), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('L', Tags.Items.GEMS_LAPIS);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.pattern("LLL");
			recipe.pattern("IFI");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.STABILIZER), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.pattern("RPR");
			recipe.pattern("RFR");
			recipe.pattern("GIG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.TERRITORY), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('B', Items.GRASS_BLOCK);
			recipe.pattern("RBR");
			recipe.pattern("BFB");
			recipe.pattern("RGR");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.UPGRADE.item(GendustryUpgradeType.IMMUTABLE), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('X', Items.REDSTONE_TORCH);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.pattern("RPR");
			recipe.pattern("RFR");
			recipe.pattern("GXG");
		});
	}

	private static void eliteUpgradeCraftingRecipes(MKRecipeProvider recipes) {
		FeatureItem<ItemForestry> upgradeFrame = GItems.RESOURCE.get(GendustryResourceType.ELITE_UPGRADE_FRAME);

		recipes.shapedCrafting(RecipeCategory.MISC, GItems.ELITE_UPGRADE.item(EliteGendustryUpgradeType.MUTATION), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('D', GFluids.LIQUID_DNA.getBucket());
			recipe.define('M', GFluids.MUTAGEN.getBucket());
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern("RMR");
			recipe.pattern("PFP");
			recipe.pattern("RDR");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.ELITE_UPGRADE.item(EliteGendustryUpgradeType.ACTIVITY_SIMULATOR), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('S', GItems.UPGRADE.item(GendustryUpgradeType.SKY));
			recipe.define('W', GItems.UPGRADE.item(GendustryUpgradeType.WEATHERPROOF));
			recipe.define('L', GItems.UPGRADE.item(GendustryUpgradeType.LIGHTING));
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);

			recipe.pattern("RWR");
			recipe.pattern("LFS");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.ELITE_UPGRADE.item(EliteGendustryUpgradeType.PRODUCTIVITY), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('D', Tags.Items.GEMS_EMERALD);
			recipe.define('C', POLLEN_CLUSTER);
			recipe.define('R', ROYAL_JELLY);
			recipe.pattern("DRD");
			recipe.pattern("RFR");
			recipe.pattern("CGC");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.ELITE_UPGRADE.item(EliteGendustryUpgradeType.TERRITORY), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('B', Items.GRASS_BLOCK);
			recipe.pattern("RBR");
			recipe.pattern("BFB");
			recipe.pattern("RGR");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.ELITE_UPGRADE.item(EliteGendustryUpgradeType.YOUTH), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('T', Items.GHAST_TEAR);
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.pattern("TRT");
			recipe.pattern("RFR");
			recipe.pattern("IGI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GItems.ELITE_UPGRADE.item(EliteGendustryUpgradeType.FERTILITY), recipe -> {
			recipe.define('F', upgradeFrame);
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('D', GFluids.LIQUID_DNA.getBucket());
			recipe.define('M', GFluids.PROTEIN.getBucket());
			recipe.define('R', Tags.Items.DUSTS_REDSTONE);
			recipe.pattern("RMR");
			recipe.pattern("PFP");
			recipe.pattern("RDR");
		});
	}

	private static void machineCraftingRecipes(MKRecipeProvider recipes) {
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.INDUSTRIAL_APIARY), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('C', Tags.Items.GLASS);
			recipe.define('S', STURDY_CASING);
			recipe.define('P', Items.PISTON);
			recipe.define('R', GItems.RESOURCE.item(GendustryResourceType.RECEPTACLE));
			recipe.pattern("CRC");
			recipe.pattern("CSC");
			recipe.pattern("GPG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.MUTAGEN_PRODUCER), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('C', Items.CAULDRON);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', Items.HOPPER);
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.pattern("IHI");
			recipe.pattern("PSP");
			recipe.pattern("GCG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.DNA_EXTRACTOR), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', Items.HOPPER);
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.define('D', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.pattern("GHG");
			recipe.pattern("DSD");
			recipe.pattern("GPG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.PROTEIN_LIQUEFIER), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', Items.HOPPER);
			recipe.define('P', Items.PISTON);
			recipe.define('M', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.pattern("GHG");
			recipe.pattern("PSP");
			recipe.pattern("GMG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.SAMPLER), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.RECEPTACLE));
			recipe.define('M', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.define('D', Tags.Items.GEMS_DIAMOND);
			recipe.pattern("GHG");
			recipe.pattern("PSD");
			recipe.pattern("GMG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.MUTATRON), recipe -> {
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('R', GItems.RESOURCE.item(GendustryResourceType.RECEPTACLE));
			recipe.define('M', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.define('C', Items.CAULDRON);
			recipe.pattern("RHI");
			recipe.pattern("MSR");
			recipe.pattern("RCI");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.ADVANCED_MUTATRON), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', GBlocks.MACHINE.get(GendustryMachineType.MUTATRON));
			recipe.define('H', Tags.Items.GEMS_QUARTZ);
			recipe.define('P', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('M', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.pattern("GHG");
			recipe.pattern("PSP");
			recipe.pattern("GMG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.IMPRINTER), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('R', GItems.RESOURCE.item(GendustryResourceType.RECEPTACLE));
			recipe.define('M', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.pattern("GHG");
			recipe.pattern("RSR");
			recipe.pattern("GMG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.GENETIC_TRANSPOSER), recipe -> {
			recipe.define('I', ForestryTags.Items.INGOTS_BRONZE);
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('M', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.pattern("GIG");
			recipe.pattern("HSH");
			recipe.pattern("GMG");
		});
		recipes.shapedCrafting(RecipeCategory.MISC, GBlocks.MACHINE.get(GendustryMachineType.REPLICATOR), recipe -> {
			recipe.define('G', ForestryTags.Items.GEARS_BRONZE);
			recipe.define('S', STURDY_CASING);
			recipe.define('H', GItems.RESOURCE.item(GendustryResourceType.GENETICS_PROCESSOR));
			recipe.define('M', GItems.RESOURCE.item(GendustryResourceType.POWER_MODULE));
			recipe.pattern("GHG");
			recipe.pattern("MSM");
			recipe.pattern("GHG");
		});
	}

	private static void mutagen(Consumer<FinishedRecipe> writer, Item input, int mutagen) {
		writer.accept(new MutagenFinishedRecipe(Gendustry.loc("mutagen/" + MKRecipeProvider.path(input)), Ingredient.of(input), mutagen));
	}

	private static void protein(Consumer<FinishedRecipe> writer, Item input, int protein) {
		writer.accept(new ProteinFinishedRecipe(Gendustry.loc("protein/" + MKRecipeProvider.path(input)), Ingredient.of(input), protein));
	}

	private static void dna(Consumer<FinishedRecipe> writer, ResourceLocation speciesType, ILifeStage input, int dna) {
		writer.accept(new DnaFinishedRecipe(Gendustry.loc("dna/" + input.getSerializedName()), IForestryApi.INSTANCE.getGeneticManager().getSpeciesType(speciesType), input, dna));
	}
}
```

### src/main/java/thedarkcolour/gendustry/data/TranslationKeys.java

```java
package thedarkcolour.gendustry.data;

public class TranslationKeys {
	public static final String TEMPLATE_MISSING_ALLELE = "item.gendustry.genetic_template.missing_allele";
	public static final String TEMPLATE_ALLELE_ENTRY = "item.gendustry.genetic_template.allele_entry";
	public static final String TEMPLATE_ALLELE_COUNT = "item.gendustry.genetic_template.allele_count";

	public static final String UPGRADE_ENERGY_COST = "item.gendustry.upgrade.energy_cost";
	public static final String UPGRADE_STACK_LIMIT = "item.gendustry.upgrade.max_count";

	public static final String JEI_INFO_MUTAGEN = "info.gendustry.mutagen";
	public static final String JEI_INFO_DNA = "info.gendustry.dna";
	public static final String JEI_INFO_PROTEIN = "info.gendustry.protein";

	// These are not the actual translation keys for the hints.
	public static final String HINT_MUTAGEN_USAGE = "mutagen_usage";
	public static final String HINT_MUTAGEN_INGREDIENTS = "mutagen_ingredients";
	public static final String HINT_DNA_USAGE = "dna_usage";
	public static final String HINT_DNA_INGREDIENTS = "dna_ingredients";
	public static final String HINT_PROTEIN_USAGE = "protein_usage";
	public static final String HINT_PROTEIN_INGREDIENTS = "protein_ingredients";
	public static final String HINT_SAMPLE_USAGE = "sample_usage";
	public static final String HINT_SAMPLE_REUSE = "sample_reuse";
	public static final String HINT_SAMPLE_SELECTION = "sample_selection";
	public static final String HINT_IMPRINTER_USAGE = "imprinter_usage";
	public static final String HINT_TRANSPOSER_USAGE = "transposer_usage";
	public static final String HINT_REPLICATOR_USAGE = "replicator_usage";
	public static final String HINT_MUTATRON_USAGE = "mutatron_usage";
	public static final String HINT_ADVANCED_MUTATRON_USAGE = "advanced_mutatron";
	public static final String HINT_INDUSTRIAL_APIARY_USAGE = "industrial_apiary";
	public static final String HINT_INDUSTRIAL_APIARY_UPGRADES = "industrial_apiary_upgrades";
	public static final String JEI_LABWARE_CHANCE = "gendustry.for.chance";
}
```

### src/main/java/thedarkcolour/gendustry/Gendustry.java

```java
package thedarkcolour.gendustry;

import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.fml.common.Mod;

@Mod(Gendustry.ID)
public class Gendustry {
	public static final String ID = "gendustry";

	public static ResourceLocation loc(String path) {
		return new ResourceLocation(ID, path);
	}
}
```

### src/main/java/thedarkcolour/gendustry/GendustryModule.java

```java
package thedarkcolour.gendustry;

import java.util.function.Consumer;

import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.eventbus.api.IEventBus;

import forestry.api.client.IClientModuleHandler;
import forestry.api.modules.ForestryModule;
import forestry.api.modules.IForestryModule;

import thedarkcolour.gendustry.client.ClientHandler;
import thedarkcolour.gendustry.data.Data;
import thedarkcolour.gendustry.recipe.cache.DnaRecipeCache;
import thedarkcolour.gendustry.recipe.cache.MutagenRecipeCache;
import thedarkcolour.gendustry.recipe.cache.ProteinRecipeCache;
import thedarkcolour.gendustry.recipe.cache.RecipeCacheRegistry;

@ForestryModule
public class GendustryModule implements IForestryModule {
	public static final ResourceLocation MODULE_ID = Gendustry.loc("core");

	@Override
	public void registerEvents(IEventBus modBus) {
		// Recipe caching
		new RecipeCacheRegistry(registrar -> {
			registrar.accept(MutagenRecipeCache.INSTANCE);
			registrar.accept(DnaRecipeCache.INSTANCE);
			registrar.accept(ProteinRecipeCache.INSTANCE);
		});

        try {
            modBus.addListener(Data::gatherData);
        } catch (ClassCastException ignored) {
        }
	}

	@Override
	public ResourceLocation getId() {
		return GendustryModule.MODULE_ID;
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new ClientHandler());
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/data/GeneSampleInfo.java

```java
package thedarkcolour.gendustry.item.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import forestry.api.IForestryApi;
import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IChromosome;
import net.minecraft.resources.ResourceLocation;

public record GeneSampleInfo(ISpeciesType<?, ?> type, IChromosome<?> chromosome, IAllele allele) {
    public static final Codec<GeneSampleInfo> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                ResourceLocation.CODEC
                        .xmap(IForestryApi.INSTANCE.getGeneticManager()::getSpeciesType, ISpeciesType::id)
                        .fieldOf("type")
                        .forGetter(o -> o.type().cast()),
                IForestryApi.INSTANCE.getAlleleManager().chromosomeCodec()
                        .fieldOf("chromosome")
                        .forGetter(GeneSampleInfo::chromosome),
                IAllele.CODEC
                        .fieldOf("allele")
                        .forGetter(GeneSampleInfo::allele)
        ).apply(instance, GeneSampleInfo::new);
    });
}
```

### src/main/java/thedarkcolour/gendustry/item/data/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.item.data;
```

### src/main/java/thedarkcolour/gendustry/item/DebugWand.java

```java
package thedarkcolour.gendustry.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import thedarkcolour.gendustry.blockentity.IndustrialApiaryBlockEntity;

public class DebugWand extends Item {
	public DebugWand() {
		super(new Properties().stacksTo(1));
	}

	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		Level level = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();

		if (!level.isClientSide && level.getBlockEntity(pos) instanceof IndustrialApiaryBlockEntity apiary) {
			ctx.getPlayer().sendSystemMessage(Component.literal("Yep, that's an Industrial Apiary."));
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/EliteGendustryUpgradeType.java

```java
package thedarkcolour.gendustry.item;

import java.util.Locale;

import forestry.api.core.IItemSubtype;

public enum EliteGendustryUpgradeType implements IItemSubtype, IGendustryUpgradeType {
	// Increases mutation chances
	MUTATION(4, 400),
	// Functions as LIGHTING, WEATHERPROOF, and SKY
	ACTIVITY_SIMULATOR(1, 200),
	// Increases production, but even further
	PRODUCTIVITY(32, 400),
	// Increases territory, but even further
	TERRITORY(16, 100),
	// Increases lifespan
	YOUTH(4, 50),
	// Increases fertility
	FERTILITY(4, 1000),
	;

	private final String name = name().toLowerCase(Locale.ENGLISH);
	private final int maxStackSize;
	private final int energyCost;

	EliteGendustryUpgradeType(int maxStackSize, int energyCost) {
		this.maxStackSize = maxStackSize;
		this.energyCost = energyCost;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}

	@Override
	public int maxStackSize() {
		return this.maxStackSize;
	}

	@Override
	public int energyCost() {
		return this.energyCost;
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/GendustryResourceType.java

```java
package thedarkcolour.gendustry.item;

import java.util.Locale;

import forestry.api.core.IItemSubtype;

public enum GendustryResourceType implements IItemSubtype {
	LABWARE,
	UPGRADE_FRAME,
	ELITE_UPGRADE_FRAME,
	CLIMATE_CONTROL_MODULE,
	POWER_MODULE,
	GENETICS_PROCESSOR,
	ENVIRONMENTAL_PROCESSOR,
	BLANK_GENE_SAMPLE,
	BLANK_GENETIC_TEMPLATE,
	// This used to be called "bee receptacle" but isn't really accurate since other organisms can be manipulated
	RECEPTACLE;

	private final String name = name().toLowerCase(Locale.ENGLISH);

	@Override
	public String getSerializedName() {
		return this.name;
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/GendustryUpgradeItem.java

```java
package thedarkcolour.gendustry.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.data.TranslationKeys;

public class GendustryUpgradeItem extends Item {
	private final IGendustryUpgradeType type;
	@Nullable
	private String descriptionKey;

	public GendustryUpgradeItem(IGendustryUpgradeType type) {
		super(new Properties().stacksTo(type.maxStackSize()));

		this.type = type;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advanced) {
		if (this.descriptionKey == null) {
			this.descriptionKey = getOrCreateDescriptionId() + ".tooltip";
		}
		tooltip.add(Component.translatable(this.descriptionKey).withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.empty());
		tooltip.add(Component.translatable(TranslationKeys.UPGRADE_ENERGY_COST, Component.literal(String.valueOf(this.type.energyCost())).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.translatable(TranslationKeys.UPGRADE_STACK_LIMIT, Component.literal(String.valueOf(this.type.maxStackSize())).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
	}

	public IGendustryUpgradeType getType() {
		return this.type;
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/GendustryUpgradeType.java

```java
package thedarkcolour.gendustry.item;

import java.util.Locale;

import forestry.api.core.IItemSubtype;

public enum GendustryUpgradeType implements IItemSubtype, IGendustryUpgradeType {
	// Automatically re-breeds bees
	AUTOMATION(1, 50),
	// Increases temperature by 1 step
	HEATER(5, 100),
	// Decreases temperature by 1 step
	COOLER(5, 100),
	// Increases humidity by 1 step
	HUMIDIFIER(2, 50),
	// Decreases humidity by 1 step
	DRYER(2, 50),
	// Increases pollination
	POLLINATION(8, 100),
	// Disables pollination
	SCRUBBER(1, 50),
	// Sets temperature to HELLISH and humidity to ARID
	NETHER(1, 200),
	// Decreases lifespan
	LIFESPAN(4, 300),
	// Bees never sleep
	LIGHTING(1, 50),
	// Increases production
	PRODUCTIVITY(8, 300),
	// Bees ignore the weather
	WEATHERPROOF(1, 50),
	// Functions as alveary sieve
	SIEVE(1, 100),
	// Bees ignore obstructed sky
	SKY(1, 50),
	// Ignoble bees never die
	STABILIZER(1, 400),
	// Increases bee territory
	TERRITORY(4, 50),
	// Prevents bee mutations
	IMMUTABLE(1, 50),
	;

	private final String name;
	private final int maxStackSize;
	private final int energyCost;

	GendustryUpgradeType(int maxStackSize, int energyCost) {
		this.name = name().toLowerCase(Locale.ENGLISH);
		this.maxStackSize = maxStackSize;
		this.energyCost = energyCost;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}

	@Override
	public int maxStackSize() {
		return this.maxStackSize;
	}

	@Override
	public int energyCost() {
		return this.energyCost;
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/GeneSampleItem.java

```java
package thedarkcolour.gendustry.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import forestry.api.IForestryApi;
import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IChromosome;
import forestry.core.render.ColourProperties;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.registry.GItems;

public class GeneSampleItem extends SpeciesTypeItem {
	public static final String NBT_CHROMOSOME = "chromosome";
	public static final String NBT_ALLELE = "allele";

	public GeneSampleItem() {
		super(new Item.Properties().rarity(Rarity.UNCOMMON));
	}

	public static ItemStack createStack(ISpeciesType<?, ?> speciesType, IChromosome<?> chromosome, IAllele allele) {
		ItemStack stack = new ItemStack(GItems.GENE_SAMPLE);
		CompoundTag nbt = stack.getOrCreateTag();
		nbt.putString(NBT_SPECIES_TYPE, speciesType.id().toString());
		nbt.putString(NBT_CHROMOSOME, chromosome.id().toString());
		nbt.putString(NBT_ALLELE, allele.alleleId().toString());
		return stack;
	}

	@Nullable
	public static IChromosome<?> getChromosome(ItemStack stack) {
		if (stack.hasTag()) {
			ResourceLocation location = ResourceLocation.tryParse(stack.getTag().getString(NBT_CHROMOSOME));

			if (location != null) {
				return IForestryApi.INSTANCE.getAlleleManager().getChromosome(location);
			}
		}

		return null;
	}

	@Nullable
	public static IAllele getAllele(ItemStack stack) {
		if (stack.hasTag()) {
			ResourceLocation location = ResourceLocation.tryParse(stack.getTag().getString(NBT_ALLELE));

			if (location != null) {
				return IForestryApi.INSTANCE.getAlleleManager().getAllele(location);
			}
		}

		return null;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag pIsAdvanced) {
		IChromosome<?> chromosome = getChromosome(stack);
		if (chromosome == null) {
			return;
		}
		IAllele allele = getAllele(stack);
		if (allele == null) {
			return;
		}

		tooltip.add(chromosome.getChromosomeDisplayName().append(" - ").withStyle(ChatFormatting.GRAY).append(chromosome.getDisplayName(allele.cast()).withStyle(style -> style.withColor(getColorCoding(allele.dominant())))));
	}

	// Copied from GuiAlyzer
	public static int getColorCoding(boolean dominant) {
		if (dominant) {
			return ColourProperties.INSTANCE.get("gui.beealyzer.dominant");
		} else {
			return ColourProperties.INSTANCE.get("gui.beealyzer.recessive");
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/GeneticTemplateItem.java

```java
package thedarkcolour.gendustry.item;

import com.google.common.collect.ImmutableList;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import forestry.api.IForestryApi;
import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IAlleleManager;
import forestry.api.genetics.alleles.IChromosome;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.data.TranslationKeys;

public class GeneticTemplateItem extends SpeciesTypeItem {
	public static final String NBT_ALLELES = "alleles";

	public GeneticTemplateItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.RARE));
	}

	public static void addAlleles(ItemStack template, Map<IChromosome<?>, IAllele> samples) {
		CompoundTag nbt = template.getOrCreateTagElement(NBT_ALLELES);

		samples.forEach((chromosome, allele) -> nbt.putString(chromosome.id().toString(), allele.alleleId().toString()));
	}

	public static Map<IChromosome<?>, IAllele> getAlleles(ItemStack template) {
		CompoundTag nbt = template.getTagElement(NBT_ALLELES);

		if (nbt == null) {
			return Map.of();
		}

		IdentityHashMap<IChromosome<?>, IAllele> alleles = new IdentityHashMap<>(nbt.size());
		IAlleleManager manager = IForestryApi.INSTANCE.getAlleleManager();

		for (String key : nbt.getAllKeys()) {
			ResourceLocation chromosomeId = ResourceLocation.tryParse(key);

			if (chromosomeId != null) {
				IChromosome<?> chromosome = manager.getChromosome(chromosomeId);

				if (chromosome != null) {
					ResourceLocation alleleId = ResourceLocation.tryParse(nbt.getString(key));

					if (alleleId != null) {
						IAllele allele = manager.getAllele(alleleId);

						if (allele != null) {
							alleles.put(chromosome, allele);
						}
					}
				}
			}
		}

		return alleles;
	}

	public static boolean isComplete(ItemStack stack) {
		ISpeciesType<?, ?> speciesType = getSpeciesType(stack);
		Map<IChromosome<?>, IAllele> alleles = getAlleles(stack);
		return speciesType != null && speciesType.getKaryotype().size() == alleles.size();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		ISpeciesType<?, ?> speciesType = getSpeciesType(stack);

		if (speciesType == null) {
			return;
		}

		ImmutableList<IChromosome<?>> chromosomes = speciesType.getKaryotype().getChromosomes();
		Map<IChromosome<?>, IAllele> alleles = getAlleles(stack);
		int countIndex = tooltip.size();
		int totalAlleles = chromosomes.size();
		int foundAlleles = 0;

		for (IChromosome<?> chromosome : chromosomes) {
			IAllele allele = alleles.get(chromosome);
			Component chromosomeName = chromosome.getChromosomeDisplayName();

			if (allele == null) {
				tooltip.add(Component.translatable(TranslationKeys.TEMPLATE_ALLELE_ENTRY, chromosomeName, Component.translatable(TranslationKeys.TEMPLATE_MISSING_ALLELE).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.GRAY));
			} else {
				Component alleleName = chromosome.getDisplayName(allele.cast()).withStyle(style -> style.withColor(GeneSampleItem.getColorCoding(allele.dominant())));
				tooltip.add(Component.translatable(TranslationKeys.TEMPLATE_ALLELE_ENTRY, chromosomeName, alleleName).withStyle(ChatFormatting.GRAY));
				++foundAlleles;
			}
		}

		tooltip.add(countIndex, Component.translatable(TranslationKeys.TEMPLATE_ALLELE_COUNT, foundAlleles, totalAlleles).withStyle(ChatFormatting.GRAY));
	}
}
```

### src/main/java/thedarkcolour/gendustry/item/IGendustryUpgradeType.java

```java
package thedarkcolour.gendustry.item;

public interface IGendustryUpgradeType {
	int maxStackSize();

	int energyCost();
}
```

### src/main/java/thedarkcolour/gendustry/item/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.item;
```

### src/main/java/thedarkcolour/gendustry/item/PollenKitItem.java

```java
package thedarkcolour.gendustry.item;

import forestry.api.IForestryApi;
import forestry.api.genetics.pollen.IPollen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class PollenKitItem extends Item {
    public PollenKitItem() {
        super(new Item.Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();

        if (!level.isClientSide) {
            Player player = ctx.getPlayer();
            BlockPos pos = ctx.getClickedPos();
            IPollen<?> pollen = IForestryApi.INSTANCE.getPollenManager().getPollen(level, pos, player);

            if (pollen != null && player != null) {
                // Generate pollen item and consume kit
                ItemStack stack = pollen.createStack();
                if (!player.getInventory().add(stack)) {
                    player.drop(stack, false);
                }
                ctx.getItemInHand().shrink(1);
            }

            return InteractionResult.CONSUME;
        }

        return InteractionResult.SUCCESS;
    }
}
```

### src/main/java/thedarkcolour/gendustry/item/SpeciesTypeItem.java

```java
package thedarkcolour.gendustry.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import forestry.api.IForestryApi;
import forestry.api.genetics.ISpeciesType;

import org.jetbrains.annotations.Nullable;

public abstract class SpeciesTypeItem extends Item {
	public static final String NBT_SPECIES_TYPE = "speciesType";

	public SpeciesTypeItem(Properties properties) {
		super(properties);
	}

	@Override
	public Component getName(ItemStack stack) {
		ISpeciesType<?, ?> speciesType = getSpeciesType(stack);

		return Component.translatable(getOrCreateDescriptionId(), speciesType != null ? speciesType.getDisplayName() : "?");
	}

	/**
	 * Gets the species type stored on an item.
	 *
	 * @param stack An item, either the Gene Sample or Genetic Template.
	 * @return The species type of the item.
	 */
	@Nullable
	public static ISpeciesType<?, ?> getSpeciesType(ItemStack stack) {
		if (stack.hasTag()) {
			ResourceLocation location = ResourceLocation.tryParse(stack.getTag().getString(NBT_SPECIES_TYPE));

			if (location != null) {
				return IForestryApi.INSTANCE.getGeneticManager().getSpeciesType(location);
			}
		}

		return null;
	}

	public static void setSpeciesType(ItemStack stack, ISpeciesType<?, ?> type) {
		stack.getOrCreateTag().putString(NBT_SPECIES_TYPE, type.id().toString());
	}
}
```

### src/main/java/thedarkcolour/gendustry/menu/AbstractMutatronMenu.java

```java
package thedarkcolour.gendustry.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

import forestry.core.gui.ContainerLiquidTanks;
import forestry.core.gui.slots.SlotFiltered;
import forestry.core.gui.slots.SlotLiquidIn;
import forestry.core.gui.slots.SlotOutput;

import thedarkcolour.gendustry.blockentity.AbstractMutatronBlockEntity;
import thedarkcolour.gendustry.blockentity.MutatronInventory;

public class AbstractMutatronMenu<T extends AbstractMutatronBlockEntity> extends ContainerLiquidTanks<T> {
	protected AbstractMutatronMenu(int windowId, MenuType<?> menuType, Inventory playerInv, T tile) {
		super(windowId, menuType, playerInv, tile, 8, 94);

		// Princess slot
		addSlot(new SlotFiltered(this.tile, MutatronInventory.SLOT_PRIMARY, 41, 26));
		// Drone slot
		addSlot(new SlotFiltered(this.tile, MutatronInventory.SLOT_SECONDARY, 41, 49));
		// Labware slot
		addSlot(new SlotFiltered(this.tile, MutatronInventory.SLOT_LABWARE, 84, 20));
		// Can slot
		addSlot(new SlotLiquidIn(this.tile, MutatronInventory.SLOT_CAN_INPUT, 11, 71));
		// Output Queen slot
		addSlot(new SlotOutput(this.tile, MutatronInventory.SLOT_RESULT, 133, 39));
	}
}
```

### src/main/java/thedarkcolour/gendustry/menu/AdvancedMutatronMenu.java

```java
package thedarkcolour.gendustry.menu;

import java.util.List;
import java.util.Objects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import forestry.api.genetics.IIndividual;
import forestry.api.genetics.ILifeStage;
import forestry.api.genetics.IMutation;
import forestry.api.genetics.ISpecies;
import forestry.core.tiles.TileUtil;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.gendustry.blockentity.AbstractMutatronBlockEntity;
import thedarkcolour.gendustry.blockentity.AdvancedMutatronBlockEntity;
import thedarkcolour.gendustry.blockentity.MutatronInventory;
import thedarkcolour.gendustry.registry.GMenus;

public class AdvancedMutatronMenu extends AbstractMutatronMenu<AdvancedMutatronBlockEntity> {
	// Used in Menu.clickMenuButton and MultiPlayerGameMode.handleInventoryButtonClick
	public static final int BUTTON_CYCLE_LEFT = 0;
	public static final int BUTTON_CYCLE_RIGHT = 1;
	public static final int CHOICE_CLICKED = 2;

	// Currently displayed options
	public final Slot[] choices;
	// Synced: the total number of possibilities, the offset in the list display, and the currently selected mutation
	private final SimpleContainerData data;

	// Tracks the list stored in the block entity (server only)
	private List<IMutation<ISpecies<?>>> possibilities;
	// List of icons kept on the server to rotate into the display slots
	private List<ItemStack> icons;
	// Nonnull on client for the GUI to react to data changes
	@Nullable
	private Runnable dataListener;

	public AdvancedMutatronMenu(int windowId, Inventory playerInventory, AdvancedMutatronBlockEntity tile) {
		super(windowId, GMenus.ADVANCED_MUTATRON.menuType(), playerInventory, tile);

		this.choices = new ChoiceSlot[4];
		this.possibilities = List.of();
		this.icons = List.of();
		this.data = new SimpleContainerData(3);

		for (int i = 0; i < 4; i++) {
			ChoiceSlot choice = new ChoiceSlot(i, 63 + i * 16, 71);
			addSlot(choice);
			this.choices[i] = choice;
		}

		addDataSlots(this.data);
	}

	public static AdvancedMutatronMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		AdvancedMutatronBlockEntity tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), AdvancedMutatronBlockEntity.class);
		return new AdvancedMutatronMenu(windowId, playerInv, Objects.requireNonNull(tile));
	}

	// Functions as a server tick method for the menu
	@Override
	public void broadcastChanges() {
		// Track possibilities from tile, updating if necessary
		List<IMutation<ISpecies<?>>> newPossibilities = this.tile.getPossibilities();
		if (this.possibilities != newPossibilities) {
			this.possibilities = newPossibilities;

			// -1 is unselected (ImmutableList doesn't like null values)
			IMutation<?> current = this.tile.getCurrentMutation();
			setSelected(current == null ? -1 : newPossibilities.indexOf(current));

			// Reset slot display
			setOffset(0);
			this.icons = newPossibilities.stream().map(mutation -> {
				ILifeStage stage = mutation.getType().getTypeForMutation(2);
				IIndividual individual = mutation.getResult().createIndividual(AbstractMutatronBlockEntity.createMutatedGenome(mutation));
				return individual.createStack(stage);
			}).toList();
			for (int i = 0; i < this.icons.size(); ++i) {
				this.choices[i].set(this.icons.get(i));
			}
			for (int i = this.icons.size(); i < 4; ++i) {
				this.choices[i].set(ItemStack.EMPTY);
			}

			setPossibilityCount(this.possibilities.size());
		}

		super.broadcastChanges();
	}

	// Called on the client by the server
	@Override
	public boolean clickMenuButton(Player player, int id) {
		int offset = getOffset();

		if (getPossibilityCount() > 4) {
			// Left = backward, Right = forward
			if (id == BUTTON_CYCLE_LEFT) {
				if (offset > 0) {
					setOffset(offset - 1);
				}
			} else if (id == BUTTON_CYCLE_RIGHT) {
				if (offset + 4 < getPossibilityCount()) {
					setOffset(offset + 1);
				}
			}
		}

		if (id >= CHOICE_CLICKED && id < CHOICE_CLICKED + 4) {
			setSelected(offset + id - 2);
		}

		return super.clickMenuButton(player, id);
	}

	public int getPossibilityCount() {
		return this.data.get(0);
	}

	public void setPossibilityCount(int possibilityCount) {
		this.data.set(0, possibilityCount);
	}

	public int getOffset() {
		return this.data.get(1);
	}

	public void setOffset(int offset) {
		this.data.set(1, offset);
	}

	public int getSelected() {
		return this.data.get(2);
	}

	public void setSelected(int selected) {
		this.data.set(2, selected);

		if (selected >= 0 && !this.tile.getLevel().isClientSide) {
			List<IMutation<ISpecies<?>>> possibilities = this.tile.getPossibilities();
			int choice = getSelected() + getOffset();

			if (choice < possibilities.size()) {
				this.tile.setCurrentMutation(possibilities.get(choice), this.tile.getItem(MutatronInventory.SLOT_PRIMARY), this.tile.getItem(MutatronInventory.SLOT_SECONDARY));
			}
		}
	}

	public void setDataListener(Runnable listener) {
		this.dataListener = listener;
	}

	@Override
	public void setData(int pId, int pData) {
		super.setData(pId, pData);

		if (this.dataListener != null) {
			this.dataListener.run();
		}
	}

	public static class ChoiceSlot extends Slot {
		// Index between 0-3
		public final int choiceIndex;

		public ChoiceSlot(int choiceIndex, int x, int y) {
			super(new SimpleContainer(1), 0, x, y);
			this.choiceIndex = choiceIndex;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public boolean mayPickup(Player pPlayer) {
			return false;
		}

		@Override
		public boolean isHighlightable() {
			return false;
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/menu/IndustrialApiaryMenu.java

```java
package thedarkcolour.gendustry.menu;

import java.util.Objects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

import forestry.api.core.HumidityType;
import forestry.api.core.TemperatureType;
import forestry.core.gui.ContainerTile;
import forestry.core.gui.slots.SlotFiltered;
import forestry.core.gui.slots.SlotOutput;
import forestry.core.network.packets.PacketGuiStream;
import forestry.core.tiles.TileUtil;

import thedarkcolour.gendustry.blockentity.IndustrialApiaryBlockEntity;
import thedarkcolour.gendustry.blockentity.IndustrialApiaryInventory;
import thedarkcolour.gendustry.registry.GMenus;

public class IndustrialApiaryMenu extends ContainerTile<IndustrialApiaryBlockEntity> {
	private int previousBeePercent = -1;
	private TemperatureType previousTemperature;
	private HumidityType previousHumidity;

	public IndustrialApiaryMenu(int windowId, Inventory playerInv, IndustrialApiaryBlockEntity tile) {
		super(windowId, GMenus.INDUSTRIAL_APIARY.menuType(), playerInv, tile, 8, 84);

		addSlot(new SlotFiltered(this.tile, IndustrialApiaryInventory.QUEEN, 26, 29));

		addSlot(new SlotFiltered(this.tile, IndustrialApiaryInventory.DRONE, 26, 52));

		for (int i = 0; i < IndustrialApiaryInventory.UPGRADE_SLOT_COUNT; i++) {
			int x = 62 + (i % 2) * 18;
			int y = 43 + (i / 2) * 18;

			addSlot(new SlotFiltered(this.tile, IndustrialApiaryInventory.UPGRADE_SLOT_START + i, x, y));
		}

		for (int i = 0; i < IndustrialApiaryInventory.OUTPUT_SLOT_COUNT; i++) {
			int x = 116 + (i % 3) * 18;
			int y = 25 + (i / 3) * 18;
			addSlot(new SlotOutput(this.tile, IndustrialApiaryInventory.OUTPUT_SLOT_START + i, x, y));
		}

		if (!tile.getLevel().isClientSide) {
			tile.getBeekeepingLogic().clearCachedValues();
		}
	}

	@Override
	public void broadcastChanges() {
		super.broadcastChanges();

		int beeProgressPercent = this.tile.getBeekeepingLogic().getBeeProgressPercent();
		TemperatureType temperature = this.tile.temperature();
		HumidityType humidity = this.tile.humidity();

		if (this.previousBeePercent != beeProgressPercent || this.previousTemperature != temperature || this.previousHumidity != humidity) {
			this.previousBeePercent = beeProgressPercent;
			this.previousTemperature = temperature;
			this.previousHumidity = humidity;

			PacketGuiStream packet = new PacketGuiStream(this.tile);
			sendPacketToListeners(packet);
		}
	}

	public static IndustrialApiaryMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		IndustrialApiaryBlockEntity tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), IndustrialApiaryBlockEntity.class);
		return new IndustrialApiaryMenu(windowId, playerInv, Objects.requireNonNull(tile));
	}
}
```

### src/main/java/thedarkcolour/gendustry/menu/MutatronMenu.java

```java
package thedarkcolour.gendustry.menu;

import java.util.Objects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.tiles.TileUtil;

import thedarkcolour.gendustry.blockentity.MutatronBlockEntity;
import thedarkcolour.gendustry.registry.GMenus;

public class MutatronMenu extends AbstractMutatronMenu<MutatronBlockEntity> {
	public MutatronMenu(int windowId, Inventory playerInv, MutatronBlockEntity tile) {
		super(windowId, GMenus.MUTATRON.menuType(), playerInv, tile);
	}

	public static MutatronMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		MutatronBlockEntity tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), MutatronBlockEntity.class);
		return new MutatronMenu(windowId, playerInv, Objects.requireNonNull(tile));
	}
}
```

### src/main/java/thedarkcolour/gendustry/menu/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.menu;
```

### src/main/java/thedarkcolour/gendustry/menu/ProducerMenu.java

```java
package thedarkcolour.gendustry.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.gui.ContainerLiquidTanks;
import forestry.core.gui.slots.SlotFiltered;
import forestry.core.gui.slots.SlotLiquidIn;
import forestry.core.gui.slots.SlotOutput;
import forestry.core.tiles.TileUtil;

import thedarkcolour.gendustry.blockentity.ProducerBlockEntity;
import thedarkcolour.gendustry.blockentity.ProducerInventory;
import thedarkcolour.gendustry.registry.GMenus;

public class ProducerMenu extends ContainerLiquidTanks<ProducerBlockEntity<?, ?>> {
	public ProducerMenu(int windowId, Inventory playerInventory, ProducerBlockEntity<?, ?> tile) {
		super(windowId, GMenus.PROCESSOR.menuType(), playerInventory, tile, 8, 84);

		// Input slot
		addSlot(new SlotFiltered(this.tile, ProducerInventory.SLOT_INPUT, 14, 41));
		// Can slot
		addSlot(new SlotLiquidIn(this.tile, ProducerInventory.SLOT_CAN_INPUT, 147, 25));
		// Output slot
		addSlot(new SlotOutput(this.tile, ProducerInventory.SLOT_CAN_OUTPUT, 147, 61));

		// Labware slot
		if (this.tile.usesLabware) {
			addSlot(new SlotFiltered(this.tile, ProducerInventory.SLOT_LABWARE, 64, 19));
		}
	}

	public static ProducerMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		ProducerBlockEntity<?, ?> tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), ProducerBlockEntity.class);
		return new ProducerMenu(windowId, playerInv, tile);
	}
}
```

### src/main/java/thedarkcolour/gendustry/menu/ReplicatorMenu.java

```java
package thedarkcolour.gendustry.menu;

import java.util.Objects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

import forestry.core.gui.ContainerLiquidTanks;
import forestry.core.gui.slots.SlotFiltered;
import forestry.core.gui.slots.SlotLiquidIn;
import forestry.core.gui.slots.SlotOutput;
import forestry.core.tiles.TileUtil;

import thedarkcolour.gendustry.blockentity.ReplicatorBlockEntity;
import thedarkcolour.gendustry.blockentity.ReplicatorInventory;
import thedarkcolour.gendustry.registry.GMenus;

public class ReplicatorMenu extends ContainerLiquidTanks<ReplicatorBlockEntity> {
	public ReplicatorMenu(int windowId, Inventory playerInv, ReplicatorBlockEntity tile) {
		super(windowId, GMenus.REPLICATOR.menuType(), playerInv, tile, 8, 94);

		// Template slot
		addSlot(new SlotFiltered(this.tile, ReplicatorInventory.SLOT_TEMPLATE, 80, 23));
		// Liquid DNA input slot
		addSlot(new SlotLiquidIn(this.tile, ReplicatorInventory.SLOT_DNA_CAN_INPUT, 11, 71));
		// Protein input slot
		addSlot(new SlotLiquidIn(this.tile, ReplicatorInventory.SLOT_PROTEIN_CAN_INPUT, 31, 71));
		// Output Queen slot
		addSlot(new SlotOutput(this.tile, ReplicatorInventory.SLOT_OUTPUT, 124, 47));
	}

	public static ReplicatorMenu fromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		ReplicatorBlockEntity tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), ReplicatorBlockEntity.class);
		return new ReplicatorMenu(windowId, playerInv, Objects.requireNonNull(tile));
	}
}
```

### src/main/java/thedarkcolour/gendustry/menu/ThreeInputMenu.java

```java
package thedarkcolour.gendustry.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

import forestry.core.gui.ContainerTile;
import forestry.core.gui.slots.SlotFiltered;
import forestry.core.gui.slots.SlotOutput;
import forestry.core.tiles.IFilterSlotDelegate;
import forestry.core.tiles.TilePowered;
import forestry.core.tiles.TileUtil;

import thedarkcolour.gendustry.blockentity.GeneticTransposerBlockEntity;
import thedarkcolour.gendustry.blockentity.IHintTile;
import thedarkcolour.gendustry.blockentity.ImprinterBlockEntity;
import thedarkcolour.gendustry.blockentity.SamplerBlockEntity;
import thedarkcolour.gendustry.blockentity.SamplerInventory;
import thedarkcolour.gendustry.registry.GMenus;

// Reused by Sampler, Imprinter, and Genetic Transposer
public class ThreeInputMenu<T extends TilePowered & Container & IFilterSlotDelegate & IHintTile> extends ContainerTile<T> {
	public ThreeInputMenu(int windowId, Inventory playerInventory, T tile, MenuType<?> menuType) {
		super(windowId, menuType, playerInventory, tile, 8, 84);

		// Input slot
		addSlot(new SlotFiltered(this.tile, SamplerInventory.SLOT_INPUT, 32, 49));
		// Blank sample slot (template slot for Imprinter)
		addSlot(new SlotFiltered(this.tile, SamplerInventory.SLOT_BLANK_SAMPLE, 65, 28));
		// Labware slot
		addSlot(new SlotFiltered(this.tile, SamplerInventory.SLOT_LABWARE, 89, 28));
		// Output slot
		addSlot(new SlotOutput(this.tile, SamplerInventory.SLOT_OUTPUT, 128, 49));
	}

	public static ThreeInputMenu<SamplerBlockEntity> samplerFromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		SamplerBlockEntity tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), SamplerBlockEntity.class);
		return ThreeInputMenu.sampler(windowId, playerInv, tile);
	}

	public static ThreeInputMenu<ImprinterBlockEntity> imprinterFromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		ImprinterBlockEntity tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), ImprinterBlockEntity.class);
		return ThreeInputMenu.imprinter(windowId, playerInv, tile);
	}

	public static ThreeInputMenu<GeneticTransposerBlockEntity> geneticTransposerFromNetwork(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
		GeneticTransposerBlockEntity tile = TileUtil.getTile(playerInv.player.level(), extraData.readBlockPos(), GeneticTransposerBlockEntity.class);
		return ThreeInputMenu.geneticTransposer(windowId, playerInv, tile);
	}

	public static ThreeInputMenu<SamplerBlockEntity> sampler(int windowId, Inventory playerInventory, SamplerBlockEntity tile) {
		return new ThreeInputMenu<>(windowId, playerInventory, tile, GMenus.SAMPLER.menuType());
	}

	public static ThreeInputMenu<ImprinterBlockEntity> imprinter(int windowId, Inventory playerInventory, ImprinterBlockEntity tile) {
		return new ThreeInputMenu<>(windowId, playerInventory, tile, GMenus.IMPRINTER.menuType());
	}

	public static ThreeInputMenu<GeneticTransposerBlockEntity> geneticTransposer(int windowId, Inventory playerInventory, GeneticTransposerBlockEntity tile) {
		return new ThreeInputMenu<>(windowId, playerInventory, tile, GMenus.GENETIC_TRANSPOSER.menuType());
	}
}
```

### src/main/java/thedarkcolour/gendustry/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry;
```

### src/main/java/thedarkcolour/gendustry/recipe/cache/DnaRecipeCache.java

```java
package thedarkcolour.gendustry.recipe.cache;

import java.util.IdentityHashMap;

import net.minecraft.world.item.crafting.RecipeManager;

import forestry.api.genetics.ILifeStage;
import forestry.core.utils.RecipeUtils;

import thedarkcolour.gendustry.recipe.DnaRecipe;
import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

public enum DnaRecipeCache implements IRecipeCache {
	INSTANCE;

	private IdentityHashMap<ILifeStage, DnaRecipe> recipes = new IdentityHashMap<>();

	@Nullable
	public DnaRecipe getRecipe(ILifeStage stage) {
		return this.recipes.get(stage);
	}


	@Override
	public void reload(RecipeManager recipes) {
		IdentityHashMap<ILifeStage, DnaRecipe> builder = new IdentityHashMap<>();

		RecipeUtils.getRecipes(recipes, GRecipeTypes.DNA).forEach(recipe -> builder.put(recipe.getStage(), recipe));

		this.recipes = builder;
	}

	@Override
	public void unload() {
		this.recipes = new IdentityHashMap<>();
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/cache/IRecipeCache.java

```java
package thedarkcolour.gendustry.recipe.cache;

import net.minecraft.world.item.crafting.RecipeManager;

/**
 * A recipe cache is an object responsible for fast, map-based recipe lookup.
 * To use properly, use a {@link RecipeCacheRegistry}.
 */
public interface IRecipeCache {
	/**
	 * Called whenever recipes are loaded/reloaded so that caches have up-to-date information.
	 *
	 * @param recipes The recipe manager containing all currently loaded recipes.
	 */
	void reload(RecipeManager recipes);

	/**
	 * Used to clean up caches such as maps after a world is closed.
	 */
	void unload();
}
```

### src/main/java/thedarkcolour/gendustry/recipe/cache/MutagenRecipeCache.java

```java
package thedarkcolour.gendustry.recipe.cache;

import com.google.common.collect.ImmutableList;

import java.util.IdentityHashMap;
import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;

import forestry.core.utils.RecipeUtils;

import thedarkcolour.gendustry.recipe.MutagenRecipe;
import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

public enum MutagenRecipeCache implements IRecipeCache {
	INSTANCE;

	// Does not depend on NBT
	private IdentityHashMap<Item, MutagenRecipe> simple = new IdentityHashMap<>();
	// Depends on NBT
	private List<MutagenRecipe> complex = List.of();

	@Nullable
	public MutagenRecipe getRecipe(ItemStack stack) {
		MutagenRecipe recipe = this.simple.get(stack.getItem());

		if (recipe == null) {
			for (MutagenRecipe r : this.complex) {
				if (r.getIngredient().test(stack)) {
					return r;
				}
			}
		}

		return recipe;
	}

	@Override
	public void reload(RecipeManager recipes) {
		IdentityHashMap<Item, MutagenRecipe> simpleBuilder = new IdentityHashMap<>();
		ImmutableList.Builder<MutagenRecipe> complexBuilder = ImmutableList.builder();

		RecipeUtils.getRecipes(recipes, GRecipeTypes.MUTAGEN).forEach(recipe -> {
			Ingredient ingredient = recipe.getIngredient();

			if (ingredient.isSimple()) {
				for (ItemStack stack : ingredient.getItems()) {
					simpleBuilder.put(stack.getItem(), recipe);
				}
			} else {
				complexBuilder.add(recipe);
			}
		});

		this.simple = simpleBuilder;
		this.complex = complexBuilder.build();
	}

	@Override
	public void unload() {
		this.simple = new IdentityHashMap<>();
		this.complex = List.of();
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/cache/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.recipe.cache;
```

### src/main/java/thedarkcolour/gendustry/recipe/cache/ProteinRecipeCache.java

```java
package thedarkcolour.gendustry.recipe.cache;

import com.google.common.collect.ImmutableList;

import java.util.IdentityHashMap;
import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;

import forestry.core.utils.RecipeUtils;

import thedarkcolour.gendustry.recipe.ProteinRecipe;
import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

// Copy of MutagenRecipeCache, was too lazy to generify that one
public enum ProteinRecipeCache implements IRecipeCache {
	INSTANCE;

	// Does not depend on NBT
	private IdentityHashMap<Item, ProteinRecipe> simple = new IdentityHashMap<>();
	// Depends on NBT
	private List<ProteinRecipe> complex = List.of();

	@Nullable
	public ProteinRecipe getRecipe(ItemStack stack) {
		ProteinRecipe recipe = this.simple.get(stack.getItem());

		if (recipe == null) {
			for (ProteinRecipe r : this.complex) {
				if (r.getIngredient().test(stack)) {
					return r;
				}
			}
		}

		return recipe;
	}

	@Override
	public void reload(RecipeManager recipes) {
		IdentityHashMap<Item, ProteinRecipe> simpleBuilder = new IdentityHashMap<>();
		ImmutableList.Builder<ProteinRecipe> complexBuilder = ImmutableList.builder();

		RecipeUtils.getRecipes(recipes, GRecipeTypes.PROTEIN).forEach(recipe -> {
			Ingredient ingredient = recipe.getIngredient();

			if (ingredient.isSimple()) {
				for (ItemStack stack : ingredient.getItems()) {
					simpleBuilder.put(stack.getItem(), recipe);
				}
			} else {
				complexBuilder.add(recipe);
			}
		});

		this.simple = simpleBuilder;
		this.complex = complexBuilder.build();
	}

	@Override
	public void unload() {
		this.simple = new IdentityHashMap<>();
		this.complex = List.of();
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/cache/RecipeCacheRegistry.java

```java
package thedarkcolour.gendustry.recipe.cache;

import com.google.common.collect.ImmutableSet;

import java.util.function.Consumer;

import net.minecraft.util.Unit;
import net.minecraft.world.item.crafting.RecipeManager;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;

import net.minecraftforge.fml.loading.FMLEnvironment;

import forestry.core.ClientsideCode;

/**
 * Register your {@link IRecipeCache} recipe caches here so that they are properly reloaded.
 * To use, create a new instance in your mod constructor. No need to store the created instance.
 * <p>
 * Code is adapted from: <a href="https://github.com/thedarkcolour/ExDeorum/blob/1.20.1/src/main/java/thedarkcolour/exdeorum/recipe/RecipeUtil.java">Ex Deorum</a>
 */
public final class RecipeCacheRegistry {
	private final ImmutableSet<IRecipeCache> caches;
	private boolean needsReload;

	public RecipeCacheRegistry(Consumer<Consumer<IRecipeCache>> registerCaches) {
		ImmutableSet.Builder<IRecipeCache> builder = ImmutableSet.builder();
		registerCaches.accept(builder::add);
		this.caches = builder.build();

		MinecraftForge.EVENT_BUS.addListener((ServerStoppingEvent event) -> unload());
		MinecraftForge.EVENT_BUS.addListener((AddReloadListenerEvent event) -> {
			RecipeManager recipes = event.getServerResources().getRecipeManager();
			event.addListener((prepBarrier, resourceManager, prepProfiler, reloadProfiler, backgroundExecutor, gameExecutor) ->
					prepBarrier
							.wait(Unit.INSTANCE)
							.thenRunAsync(() -> reload(recipes), gameExecutor)
			);
		});

		if (FMLEnvironment.dist == Dist.CLIENT) {
			MinecraftForge.EVENT_BUS.addListener((TagsUpdatedEvent event) -> {
				RecipeManager recipes = ClientsideCode.getRecipeManager();

				if (this.needsReload && recipes != null) {
					reload(recipes);
					this.needsReload = false;
				}
			});
		}

	}

	private void reload(RecipeManager recipes) {
		for (IRecipeCache cache : this.caches) {
			cache.reload(recipes);
		}
	}

	private void unload() {
		for (IRecipeCache cache : this.caches) {
			cache.unload();
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/DnaFinishedRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import com.google.gson.JsonObject;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

import forestry.api.genetics.ILifeStage;
import forestry.api.genetics.ISpeciesType;

import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

public class DnaFinishedRecipe implements FinishedRecipe {
	private final ResourceLocation id;
	private final ISpeciesType<?, ?> speciesType;
	private final ILifeStage stage;
	private final int amount;

	public DnaFinishedRecipe(ResourceLocation id, ISpeciesType<?, ?> speciesType, ILifeStage stage, int amount) {
		this.id = id;
		this.speciesType = speciesType;
		this.stage = stage;
		this.amount = amount;
	}

	@Override
	public void serializeRecipeData(JsonObject json) {
		json.addProperty("species_type", this.speciesType.id().toString());
		json.addProperty("stage", this.stage.getSerializedName());
		json.addProperty("amount", this.amount);
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getType() {
		return GRecipeTypes.DNA.serializer();
	}

	@Nullable
	@Override
	public JsonObject serializeAdvancement() {
		return null;
	}

	@Nullable
	@Override
	public ResourceLocation getAdvancementId() {
		return null;
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/DnaRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import forestry.api.IForestryApi;
import forestry.api.genetics.ILifeStage;
import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.capability.IIndividualHandlerItem;

import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

public class DnaRecipe extends ProcessorRecipe {
	private final ISpeciesType<?, ?> speciesType;
	private final ILifeStage stage;

	public DnaRecipe(ResourceLocation id, ISpeciesType<?, ?> speciesType, ILifeStage stage, int amount) {
		super(id, amount);

		this.speciesType = speciesType;
		this.stage = stage;
	}

	public ISpeciesType<?, ?> getSpeciesType() {
		return this.speciesType;
	}

	public ILifeStage getStage() {
		return this.stage;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return GRecipeTypes.DNA.serializer();
	}

	@Override
	public RecipeType<?> getType() {
		return GRecipeTypes.DNA.type();
	}

	@Override
	public boolean isIngredient(ItemStack stack) {
		return IIndividualHandlerItem.filter(stack, (i, stage) -> stage == this.stage);
	}

	public static class Serializer implements RecipeSerializer<DnaRecipe> {
		@Override
		public DnaRecipe fromJson(ResourceLocation id, JsonObject json) {
			ResourceLocation speciesTypeId = new ResourceLocation(GsonHelper.getAsString(json, "species_type"));
			String stageName = GsonHelper.getAsString(json, "stage");
			int amount = GsonHelper.getAsInt(json, "amount");

			ISpeciesType<?, ?> speciesType = IForestryApi.INSTANCE.getGeneticManager().getSpeciesType(speciesTypeId);
			ILifeStage stage = null;
			for (ILifeStage s : speciesType.getLifeStages()) {
				if (s.getSerializedName().equals(stageName)) {
					stage = s;
					break;
				}
			}
			if (stage == null) {
				throw new IllegalStateException("No such life stage " + stageName + " for species type " + speciesTypeId);
			}

			return new DnaRecipe(id, speciesType, stage, amount);
		}

		@Override
		public @Nullable DnaRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf data) {
			ISpeciesType<?, ?> speciesType = IForestryApi.INSTANCE.getGeneticManager().getSpeciesType(data.readResourceLocation());

			// todo LifeStage API needs to be redesigned for better serialization
			ILifeStage stage = null;
			String stageName = data.readUtf();
			for (ILifeStage s : speciesType.getLifeStages()) {
				if (s.getSerializedName().equals(stageName)) {
					stage = s;
					break;
				}
			}
			if (stage == null) {
				return null;
			}
			int amount = data.readShort();

			return new DnaRecipe(id, speciesType, stage, amount);
		}

		@Override
		public void toNetwork(FriendlyByteBuf data, DnaRecipe recipe) {
			data.writeResourceLocation(recipe.speciesType.id());
			data.writeUtf(recipe.stage.getSerializedName());
			data.writeShort(recipe.amount);
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/GeneticTemplateRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import java.util.IdentityHashMap;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IChromosome;

import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.item.GeneSampleItem;
import thedarkcolour.gendustry.item.GeneticTemplateItem;
import thedarkcolour.gendustry.item.SpeciesTypeItem;
import thedarkcolour.gendustry.registry.GItems;
import thedarkcolour.gendustry.registry.GRecipeTypes;

public class GeneticTemplateRecipe extends CustomRecipe {
	public GeneticTemplateRecipe(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
	}

	@Override
	public boolean matches(CraftingContainer container, Level level) {
		boolean hasTemplate = false;
		ISpeciesType<?, ?> speciesType = null;
		int samples = 0;

		for (int i = 0; i < container.getContainerSize(); ++i) {
			ItemStack stack = container.getItem(i);

			// Skip empty slot
			if (stack.isEmpty()) {
				continue;
			}
			if (stack.is(GItems.GENETIC_TEMPLATE.item()) || stack.is(GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE))) {
				// Only one genetic template
				if (hasTemplate) {
					return false;
				} else {
					ISpeciesType<?, ?> templateType = SpeciesTypeItem.getSpeciesType(stack);

					// If we've already found samples, make sure the type matches
					if (speciesType != null) {
						if (speciesType != templateType && templateType != null) {
							return false;
						}
					} else {
						speciesType = templateType;
					}
					hasTemplate = true;
				}
				continue;
			}
			// Only gene samples are allowed
			if (!stack.is(GItems.GENE_SAMPLE.item())) {
				return false;
			} else {
				ISpeciesType<?, ?> sampleType = SpeciesTypeItem.getSpeciesType(stack);

				// No invalid samples
				if (sampleType == null) {
					return false;
				}

				// Ensure sample matches the template type, or set the type if the template is blank
				if (hasTemplate && speciesType != sampleType) {
					if (speciesType == null) {
						speciesType = sampleType;
					} else {
						return false;
					}
				} else {
					speciesType = sampleType;
				}

				++samples;
			}
		}

		return hasTemplate && samples != 0;
	}

	@Override
	public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
		IdentityHashMap<IChromosome<?>, IAllele> samples = new IdentityHashMap<>();
		ItemStack template = ItemStack.EMPTY;
		ISpeciesType<?, ?> type = null;

		for (int i = 0; i < container.getContainerSize(); ++i) {
			ItemStack stack = container.getItem(i);

			if (stack.isEmpty()) {
				continue;
			}
			if (stack.is(GItems.GENE_SAMPLE.item())) {
				IChromosome<?> chromosome = GeneSampleItem.getChromosome(stack);
				IAllele allele = GeneSampleItem.getAllele(stack);

				samples.put(chromosome, allele);

				if (type == null) {
					type = SpeciesTypeItem.getSpeciesType(stack);
				}
			} else if (stack.is(GItems.GENETIC_TEMPLATE.item()) || stack.is(GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE))) {
				if (!template.isEmpty()) {
					return ItemStack.EMPTY;
				} else {
					template = stack;
				}
			}
		}

		if (!template.isEmpty() && !samples.isEmpty() && type != null) {
			ItemStack result = template.is(GItems.GENETIC_TEMPLATE.item()) ? template.copyWithCount(1) : new ItemStack(GItems.GENETIC_TEMPLATE);

			SpeciesTypeItem.setSpeciesType(result, type);
			GeneticTemplateItem.addAlleles(result, samples);

			return result;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return GRecipeTypes.GENETIC_TEMPLATE_SERIALIZER.get();
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/MutagenFinishedRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import com.google.gson.JsonObject;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

// Using AI to write this class is the real data generation
public class MutagenFinishedRecipe implements FinishedRecipe {
	private final ResourceLocation id;
	private final Ingredient ingredient;
	private final int amount;

	public MutagenFinishedRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
		this.id = id;
		this.ingredient = ingredient;
		this.amount = amount;
	}

	@Override
	public void serializeRecipeData(JsonObject json) {
		json.add("ingredient", this.ingredient.toJson());
		json.addProperty("amount", this.amount);
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getType() {
		return GRecipeTypes.MUTAGEN.serializer();
	}

	@Nullable
	@Override
	public JsonObject serializeAdvancement() {
		return null;
	}

	@Nullable
	@Override
	public ResourceLocation getAdvancementId() {
		return null;
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/MutagenRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

public class MutagenRecipe extends ProcessorRecipe {
	private final Ingredient ingredient;

	public MutagenRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
		super(id, amount);
		this.ingredient = ingredient;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	@Override
	public boolean isIngredient(ItemStack stack) {
		return this.ingredient.test(stack);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return GRecipeTypes.MUTAGEN.serializer();
	}

	@Override
	public RecipeType<?> getType() {
		return GRecipeTypes.MUTAGEN.type();
	}

	public static class Serializer implements RecipeSerializer<MutagenRecipe> {
		@Override
		public MutagenRecipe fromJson(ResourceLocation id, JsonObject json) {
			Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
			int amount = GsonHelper.getAsInt(json, "amount");

			return new MutagenRecipe(id, ingredient, amount);
		}

		@Override
		public @Nullable MutagenRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf data) {
			Ingredient ingredient = Ingredient.fromNetwork(data);
			int amount = data.readShort();
			return new MutagenRecipe(id, ingredient, amount);
		}

		@Override
		public void toNetwork(FriendlyByteBuf data, MutagenRecipe recipe) {
			recipe.ingredient.toNetwork(data);
			data.writeShort(recipe.amount);
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.recipe;
```

### src/main/java/thedarkcolour/gendustry/recipe/ProcessorRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import forestry.api.recipes.IForestryRecipe;

public abstract class ProcessorRecipe implements IForestryRecipe {
	private final ResourceLocation id;
	protected final int amount;

	public ProcessorRecipe(ResourceLocation id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	public int getAmount() {
		return this.amount;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return ItemStack.EMPTY;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	public abstract boolean isIngredient(ItemStack stack);
}
```

### src/main/java/thedarkcolour/gendustry/recipe/ProteinFinishedRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import com.google.gson.JsonObject;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

// Using AI to write this class is the real data generation
public class ProteinFinishedRecipe implements FinishedRecipe {
	private final ResourceLocation id;
	private final Ingredient ingredient;
	private final int amount;

	public ProteinFinishedRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
		this.id = id;
		this.ingredient = ingredient;
		this.amount = amount;
	}

	@Override
	public void serializeRecipeData(JsonObject json) {
		json.add("ingredient", this.ingredient.toJson());
		json.addProperty("amount", this.amount);
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getType() {
		return GRecipeTypes.PROTEIN.serializer();
	}

	@Nullable
	@Override
	public JsonObject serializeAdvancement() {
		return null;
	}

	@Nullable
	@Override
	public ResourceLocation getAdvancementId() {
		return null;
	}
}
```

### src/main/java/thedarkcolour/gendustry/recipe/ProteinRecipe.java

```java
package thedarkcolour.gendustry.recipe;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import thedarkcolour.gendustry.registry.GRecipeTypes;
import org.jetbrains.annotations.Nullable;

public class ProteinRecipe extends ProcessorRecipe {
	private final Ingredient ingredient;

	public ProteinRecipe(ResourceLocation id, Ingredient ingredient, int amount) {
		super(id, amount);

		this.ingredient = ingredient;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	@Override
	public boolean isIngredient(ItemStack stack) {
		return this.ingredient.test(stack);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return GRecipeTypes.PROTEIN.serializer();
	}

	@Override
	public RecipeType<?> getType() {
		return GRecipeTypes.PROTEIN.type();
	}

	public static class Serializer implements RecipeSerializer<ProteinRecipe> {
		@Override
		public ProteinRecipe fromJson(ResourceLocation id, JsonObject json) {
			Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
			int amount = GsonHelper.getAsInt(json, "amount");

			return new ProteinRecipe(id, ingredient, amount);
		}

		@Override
		public @Nullable ProteinRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf data) {
			Ingredient ingredient = Ingredient.fromNetwork(data);
			int amount = data.readShort();
			return new ProteinRecipe(id, ingredient, amount);
		}

		@Override
		public void toNetwork(FriendlyByteBuf data, ProteinRecipe recipe) {
			recipe.ingredient.toNetwork(data);
			data.writeShort(recipe.amount);
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/registry/GBlockEntities.java

```java
package thedarkcolour.gendustry.registry;

import java.util.Set;

import forestry.modules.features.FeatureProvider;
import forestry.modules.features.FeatureTileType;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import thedarkcolour.gendustry.GendustryModule;
import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.blockentity.AdvancedMutatronBlockEntity;
import thedarkcolour.gendustry.blockentity.DnaExtractorBlockEntity;
import thedarkcolour.gendustry.blockentity.GeneticTransposerBlockEntity;
import thedarkcolour.gendustry.blockentity.ImprinterBlockEntity;
import thedarkcolour.gendustry.blockentity.IndustrialApiaryBlockEntity;
import thedarkcolour.gendustry.blockentity.MutagenProducerBlockEntity;
import thedarkcolour.gendustry.blockentity.MutatronBlockEntity;
import thedarkcolour.gendustry.blockentity.ProteinLiquefierBlockEntity;
import thedarkcolour.gendustry.blockentity.ReplicatorBlockEntity;
import thedarkcolour.gendustry.blockentity.SamplerBlockEntity;

@FeatureProvider
public class GBlockEntities {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(GendustryModule.MODULE_ID);

	public static final FeatureTileType<IndustrialApiaryBlockEntity> INDUSTRIAL_APIARY = REGISTRY.tile(IndustrialApiaryBlockEntity::new, "industrial_apiary", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.INDUSTRIAL_APIARY).block()));
	public static final FeatureTileType<MutagenProducerBlockEntity> MUTAGEN_PRODUCER = REGISTRY.tile(MutagenProducerBlockEntity::new, "mutagen_producer", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.MUTAGEN_PRODUCER).block()));
	public static final FeatureTileType<DnaExtractorBlockEntity> DNA_EXTRACTOR = REGISTRY.tile(DnaExtractorBlockEntity::new, "dna_extractor", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.DNA_EXTRACTOR).block()));
	public static final FeatureTileType<ProteinLiquefierBlockEntity> PROTEIN_LIQUEFIER = REGISTRY.tile(ProteinLiquefierBlockEntity::new, "protein_liquefier", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.PROTEIN_LIQUEFIER).block()));
	public static final FeatureTileType<SamplerBlockEntity> SAMPLER = REGISTRY.tile(SamplerBlockEntity::new, "sampler", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.SAMPLER).block()));
	public static final FeatureTileType<MutatronBlockEntity> MUTATRON = REGISTRY.tile(MutatronBlockEntity::new, "mutatron", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.MUTATRON).block()));
	public static final FeatureTileType<AdvancedMutatronBlockEntity> ADVANCED_MUTATRON = REGISTRY.tile(AdvancedMutatronBlockEntity::new, "advanced_mutatron", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.ADVANCED_MUTATRON).block()));
	public static final FeatureTileType<ImprinterBlockEntity> IMPRINTER = REGISTRY.tile(ImprinterBlockEntity::new, "imprinter", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.IMPRINTER).block()));
	public static final FeatureTileType<GeneticTransposerBlockEntity> GENETIC_TRANSPOSER = REGISTRY.tile(GeneticTransposerBlockEntity::new, "genetic_transposer", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.GENETIC_TRANSPOSER).block()));
	public static final FeatureTileType<ReplicatorBlockEntity> REPLICATOR = REGISTRY.tile(ReplicatorBlockEntity::new, "replicator", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.REPLICATOR).block()));
}
```

### src/main/java/thedarkcolour/gendustry/registry/GBlocks.java

```java
package thedarkcolour.gendustry.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import forestry.modules.features.FeatureBlockGroup;
import forestry.modules.features.FeatureProvider;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import thedarkcolour.gendustry.GendustryModule;
import thedarkcolour.gendustry.block.GendustryMachineBlock;
import thedarkcolour.gendustry.block.GendustryMachineType;

@FeatureProvider
public class GBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(GendustryModule.MODULE_ID);

	public static final FeatureBlockGroup<GendustryMachineBlock, GendustryMachineType> MACHINE = REGISTRY
			.blockGroup(GendustryMachineBlock::new, GendustryMachineType.values())
			.item(b -> new BlockItem(b, new Item.Properties()))
			.create();
}
```

### src/main/java/thedarkcolour/gendustry/registry/GCreativeTabs.java

```java
package thedarkcolour.gendustry.registry;

import net.minecraft.world.item.CreativeModeTab;

import forestry.api.IForestryApi;
import forestry.api.genetics.ISpeciesType;
import forestry.api.genetics.alleles.IAllele;
import forestry.api.genetics.alleles.IChromosome;
import forestry.api.genetics.alleles.IKaryotype;
import forestry.core.tab.ForestryCreativeTabs;
import forestry.modules.features.FeatureCreativeTab;
import forestry.modules.features.FeatureProvider;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import thedarkcolour.gendustry.GendustryModule;
import thedarkcolour.gendustry.block.GendustryMachineType;
import thedarkcolour.gendustry.item.GeneSampleItem;

@FeatureProvider
public class GCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(GendustryModule.MODULE_ID);

	public static final FeatureCreativeTab GENDUSTRY = REGISTRY.creativeTab("gendustry", tab -> {
		tab.icon(() -> GBlocks.MACHINE.stack(GendustryMachineType.INDUSTRIAL_APIARY));
		tab.displayItems(GCreativeTabs::addGendustryItems);
		tab.withTabsBefore(ForestryCreativeTabs.MAIL.getKey());
		tab.withTabsAfter(GCreativeTabs.GENE_SAMPLES.getKey());
	});

	public static final FeatureCreativeTab GENE_SAMPLES = REGISTRY.creativeTab("gene_samples", tab -> {
		tab.icon(GItems.GENE_SAMPLE::stack);
		tab.displayItems(GCreativeTabs::addGeneSamples);
		tab.withTabsBefore(GCreativeTabs.GENDUSTRY.getKey());
	});

	private static void addGendustryItems(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output items) {
		GBlocks.MACHINE.getBlocks().forEach(items::accept);
		items.accept(GItems.POLLEN_KIT);
		items.accept(GFluids.MUTAGEN.getBucket());
		items.accept(GFluids.LIQUID_DNA.getBucket());
		items.accept(GFluids.PROTEIN.getBucket());
		GItems.RESOURCE.getItems().forEach(items::accept);
		GItems.UPGRADE.getItems().forEach(items::accept);
		GItems.ELITE_UPGRADE.getItems().forEach(items::accept);
	}

	private static void addGeneSamples(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output items) {
		for (ISpeciesType<?, ?> speciesType : IForestryApi.INSTANCE.getGeneticManager().getSpeciesTypes()) {
			IKaryotype karyotype = speciesType.getKaryotype();

			for (IChromosome<?> chromosome : karyotype.getChromosomes()) {
				for (IAllele allele : karyotype.getAlleles(chromosome)) {
					items.accept(GeneSampleItem.createStack(speciesType, chromosome, allele));
				}
			}
		}
	}
}
```

### src/main/java/thedarkcolour/gendustry/registry/GFluids.java

```java
package thedarkcolour.gendustry.registry;

import java.util.Locale;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import net.minecraftforge.fluids.FluidStack;

import forestry.modules.features.FeatureFluid;
import forestry.modules.features.FeatureItem;
import forestry.modules.features.FeatureProvider;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import thedarkcolour.gendustry.Gendustry;
import thedarkcolour.gendustry.GendustryModule;

@FeatureProvider
public enum GFluids {
	MUTAGEN,
	LIQUID_DNA,
	PROTEIN;

	private final ResourceLocation id;
	private final FeatureFluid feature;
	private final FeatureItem<BucketItem> bucket;

	GFluids() {
		IFeatureRegistry registry = ModFeatureRegistry.get(GendustryModule.MODULE_ID);
		this.feature = registry
				.fluid(name().toLowerCase(Locale.ENGLISH))
				.bucket(this::getBucket)
				.create();
		this.bucket = registry
				.item(() -> new BucketItem(this::fluid, new Item.Properties()
								.craftRemainder(Items.BUCKET)
								.stacksTo(1)),
						"bucket_" + name().toLowerCase(Locale.ENGLISH)
				);
		this.id = Gendustry.loc(feature.getName());
	}

	public final ResourceLocation getId() {
		return this.id;
	}

	public FeatureFluid getFeature() {
		return this.feature;
	}

	public BucketItem getBucket() {
		return this.bucket.item();
	}

	public Fluid fluid() {
		return this.feature.fluid();
	}

	public Fluid getFlowing() {
		return this.feature.flowing();
	}

	public FluidStack fluidStack(int mb) {
		return new FluidStack(fluid(), mb);
	}
}
```

### src/main/java/thedarkcolour/gendustry/registry/GItems.java

```java
package thedarkcolour.gendustry.registry;

import net.minecraft.world.item.Item;

import forestry.core.items.ItemForestry;
import forestry.modules.features.FeatureGroup;
import forestry.modules.features.FeatureItem;
import forestry.modules.features.FeatureItemGroup;
import forestry.modules.features.FeatureProvider;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import thedarkcolour.gendustry.GendustryModule;
import thedarkcolour.gendustry.item.EliteGendustryUpgradeType;
import thedarkcolour.gendustry.item.GendustryResourceType;
import thedarkcolour.gendustry.item.GendustryUpgradeItem;
import thedarkcolour.gendustry.item.GendustryUpgradeType;
import thedarkcolour.gendustry.item.GeneSampleItem;
import thedarkcolour.gendustry.item.GeneticTemplateItem;
import thedarkcolour.gendustry.item.PollenKitItem;

@FeatureProvider
public class GItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(GendustryModule.MODULE_ID);

	public static final FeatureItemGroup<ItemForestry, GendustryResourceType> RESOURCE = REGISTRY.itemGroup(subtype -> new ItemForestry(), GendustryResourceType.values()).create();
	public static final FeatureItemGroup<GendustryUpgradeItem, GendustryUpgradeType> UPGRADE = REGISTRY.itemGroup(GendustryUpgradeItem::new, GendustryUpgradeType.values()).identifier("upgrade", FeatureGroup.IdentifierType.SUFFIX).create();
	public static final FeatureItemGroup<GendustryUpgradeItem, EliteGendustryUpgradeType> ELITE_UPGRADE = REGISTRY.itemGroup(GendustryUpgradeItem::new, EliteGendustryUpgradeType.values()).identifier("elite_upgrade", FeatureGroup.IdentifierType.SUFFIX).create();
	public static final FeatureItem<Item> POLLEN_KIT = REGISTRY.item(PollenKitItem::new, "pollen_kit");
	public static final FeatureItem<Item> GENE_SAMPLE = REGISTRY.item(GeneSampleItem::new, "gene_sample");
	public static final FeatureItem<Item> GENETIC_TEMPLATE = REGISTRY.item(GeneticTemplateItem::new, "genetic_template");
	//public static final FeatureItem<DebugWand> DEBUG_WAND = REGISTRY.item(DebugWand::new, "debug_wand");
	//public static final FeatureItem<Item> INDUSTRIAL_SCOOP = REGISTRY.item(() -> new IndustrialScoopItem(), "industrial_scoop");
	//public static final FeatureItem<Item> INDUSTRIAL_GRAFTER = REGISTRY.item(() -> new IndustrialGrafterItem(), "industrial_grafter");
}
```

### src/main/java/thedarkcolour/gendustry/registry/GMenus.java

```java
package thedarkcolour.gendustry.registry;

import forestry.modules.features.FeatureMenuType;
import forestry.modules.features.FeatureProvider;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import thedarkcolour.gendustry.GendustryModule;
import thedarkcolour.gendustry.blockentity.IHintTile;
import thedarkcolour.gendustry.menu.AdvancedMutatronMenu;
import thedarkcolour.gendustry.menu.IndustrialApiaryMenu;
import thedarkcolour.gendustry.menu.MutatronMenu;
import thedarkcolour.gendustry.menu.ProducerMenu;
import thedarkcolour.gendustry.menu.ReplicatorMenu;
import thedarkcolour.gendustry.menu.ThreeInputMenu;

@FeatureProvider
public class GMenus {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(GendustryModule.MODULE_ID);

	// todo rename to "producer" in 1.21
	public static final FeatureMenuType<ProducerMenu> PROCESSOR = REGISTRY.menuType(ProducerMenu::fromNetwork, "processor");
	public static final FeatureMenuType<ThreeInputMenu<? extends IHintTile>> SAMPLER = REGISTRY.menuType(ThreeInputMenu::samplerFromNetwork, "sampler");
	public static final FeatureMenuType<ThreeInputMenu<? extends IHintTile>> IMPRINTER = REGISTRY.menuType(ThreeInputMenu::imprinterFromNetwork, "imprinter");
	public static final FeatureMenuType<ThreeInputMenu<? extends IHintTile>> GENETIC_TRANSPOSER = REGISTRY.menuType(ThreeInputMenu::geneticTransposerFromNetwork, "genetic_transposer");
	public static final FeatureMenuType<MutatronMenu> MUTATRON = REGISTRY.menuType(MutatronMenu::fromNetwork, "mutatron");
	public static final FeatureMenuType<AdvancedMutatronMenu> ADVANCED_MUTATRON = REGISTRY.menuType(AdvancedMutatronMenu::fromNetwork, "advanced_mutatron");
	public static final FeatureMenuType<ReplicatorMenu> REPLICATOR = REGISTRY.menuType(ReplicatorMenu::fromNetwork, "replicator");
	public static final FeatureMenuType<IndustrialApiaryMenu> INDUSTRIAL_APIARY = REGISTRY.menuType(IndustrialApiaryMenu::fromNetwork, "industrial_apiary");
}
```

### src/main/java/thedarkcolour/gendustry/registry/GRecipeTypes.java

```java
package thedarkcolour.gendustry.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

import net.minecraftforge.registries.RegistryObject;

import forestry.modules.features.FeatureProvider;
import forestry.modules.features.FeatureRecipeType;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import thedarkcolour.gendustry.GendustryModule;
import thedarkcolour.gendustry.recipe.DnaRecipe;
import thedarkcolour.gendustry.recipe.GeneticTemplateRecipe;
import thedarkcolour.gendustry.recipe.MutagenRecipe;
import thedarkcolour.gendustry.recipe.ProteinRecipe;

@FeatureProvider
public class GRecipeTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(GendustryModule.MODULE_ID);

	public static final FeatureRecipeType<MutagenRecipe> MUTAGEN = REGISTRY.recipeType("mutagen", MutagenRecipe.Serializer::new);
	public static final FeatureRecipeType<ProteinRecipe> PROTEIN = REGISTRY.recipeType("protein", ProteinRecipe.Serializer::new);
	public static final FeatureRecipeType<DnaRecipe> DNA = REGISTRY.recipeType("dna", DnaRecipe.Serializer::new);

	public static final RegistryObject<SimpleCraftingRecipeSerializer<?>> GENETIC_TEMPLATE_SERIALIZER = REGISTRY.getRegistry(Registries.RECIPE_SERIALIZER).register("genetic_template", () -> new SimpleCraftingRecipeSerializer<>(GeneticTemplateRecipe::new));
}
```

### src/main/java/thedarkcolour/gendustry/registry/package-info.java

```java
@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.gendustry.registry;
```

### src/main/resources/assets/gendustry/lang/es_mx.json

```json
{
  "block.gendustry.advanced_mutatron": "Mutatrón avanzado",
  "block.gendustry.dna_extractor": "Extractor de ADN",
  "block.gendustry.fluid_liquid_dna": "ADN líquido fluido",
  "block.gendustry.fluid_mutagen": "Mutágeno fluido",
  "block.gendustry.fluid_protein": "Proteína fluida",
  "block.gendustry.genetic_transposer": "Transpositor genético",
  "block.gendustry.imprinter": "Imprimidor",
  "block.gendustry.industrial_apiary": "Apiario industrial",
  "block.gendustry.mutagen_producer": "Productor de mutágeno",
  "block.gendustry.mutatron": "Mutatrón",
  "block.gendustry.protein_liquefier": "Licuador de proteínas",
  "block.gendustry.replicator": "Replicador",
  "block.gendustry.sampler": "Muestreador",
  "errors.gendustry.incompatible_species.desc": "Especies incompatibles",
  "errors.gendustry.incompatible_species.help": "Los individuos solo pueden aparearse con individuos del mismo tipo de especie.",
  "errors.gendustry.no_blank.desc": "Falta plantilla/muestra en blanco",
  "errors.gendustry.no_blank.help": "El transpositor genético necesita muestras de genes en blanco o plantillas genéticas en blanco para copiar.",
  "errors.gendustry.no_dna.desc": "Falta ADN líquido",
  "errors.gendustry.no_dna.help": "Esta máquina requiere ADN líquido para funcionar.",
  "errors.gendustry.no_labware.desc": "No hay material de laboratorio",
  "errors.gendustry.no_labware.help": "Esta máquina requiere material de laboratorio para funcionar.",
  "errors.gendustry.no_mates.desc": "No hay parejas",
  "errors.gendustry.no_mates.help": "Se requieren dos parejas compatibles para que ocurra una mutación.",
  "errors.gendustry.no_mutagen.desc": "No hay mutágeno",
  "errors.gendustry.no_mutagen.help": "Se requiere mutágeno para desencadenar una mutación.",
  "errors.gendustry.no_mutations.desc": "No hay mutaciones",
  "errors.gendustry.no_mutations.help": "No hay mutaciones entre estas dos especies. Por favor, elige especies diferentes.",
  "errors.gendustry.no_protein.desc": "Falta proteína",
  "errors.gendustry.no_protein.help": "Esta máquina requiere proteína para funcionar.",
  "errors.gendustry.no_samples.desc": "No hay muestras",
  "errors.gendustry.no_samples.help": "Esta máquina requiere muestras de genes en blanco para funcionar.",
  "errors.gendustry.no_selection.desc": "Selecciona una mutación",
  "errors.gendustry.no_selection.help": "Debes elegir una mutación para el mutatrón avanzado.",
  "errors.gendustry.no_source.desc": "Falta plantilla/muestra de origen",
  "errors.gendustry.no_source.help": "Al transpositor genético le falta una muestra de genes o plantilla genética llena.",
  "errors.gendustry.no_template.desc": "Falta plantilla",
  "errors.gendustry.no_template.help": "Se requiere una plantilla genética completa para funcionar.",
  "fluid_type.gendustry.liquid_dna": "ADN líquido",
  "fluid_type.gendustry.mutagen": "Mutágeno",
  "fluid_type.gendustry.protein": "Proteína",
  "for.hints.advanced_mutatron.desc": "Para elegir la mutación deseada entre los dos padres, usa el mutatrón avanzado.",
  "for.hints.advanced_mutatron.tag": "¿Cómo usar el mutatrón avanzado?",
  "for.hints.dna_ingredients.desc": "El ADN líquido se puede obtener de cualquier organismo con un genoma de silvicultura, como abejas, retoños y polen, y mariposas.",
  "for.hints.dna_ingredients.tag": "¿Qué produce ADN líquido?",
  "for.hints.dna_usage.desc": "¡Usa ADN líquido en el replicador para construir nuevos organismos!",
  "for.hints.dna_usage.tag": "¿Cómo usar el ADN líquido?",
  "for.hints.imprinter_usage.desc": "El imprimidor reemplaza el genoma de un individuo con los alelos almacenados en una plantilla genética. El stock innoble puede no sobrevivir.",
  "for.hints.imprinter_usage.tag": "¿Cómo usar el imprimidor?",
  "for.hints.industrial_apiary.desc": "Las ranuras izquierdas son para una princesa y un zángano. Las cuatro ranuras centrales son para mejoras. Las nueve ranuras derechas son salidas.",
  "for.hints.industrial_apiary.tag": "¿Cómo usar el apiario industrial?",
  "for.hints.industrial_apiary_upgrades.desc": "El apiario industrial no necesita marcos. En su lugar, utiliza mejoras que afectan el clima, la productividad, la vida útil y ¡mucho más!",
  "for.hints.industrial_apiary_upgrades.tag": "¿Por qué no hay marcos?",
  "for.hints.mutagen_ingredients.desc": "¡El mutágeno se puede hacer de redstone, piedtra luminosa e incluso uranio!",
  "for.hints.mutagen_ingredients.tag": "¿Qué produce mutágeno?",
  "for.hints.mutagen_usage.desc": "Produce mutágeno para usar en otras máquinas de Gendustry.",
  "for.hints.mutagen_usage.tag": "¿Para qué sirve el mutágeno?",
  "for.hints.mutatron_usage.desc": "El mutatrón desencadena una mutación entre dos organismos parentales, produciendo descendencia de una nueva especie.",
  "for.hints.mutatron_usage.tag": "¿Qué es el mutatrón?",
  "for.hints.protein_ingredients.desc": "La proteína se puede hacer de cualquier tipo de carne cruda.",
  "for.hints.protein_ingredients.tag": "¿Cómo obtener proteína?",
  "for.hints.protein_usage.desc": "La proteína es un ingrediente utilizado por el replicador para crear nuevos organismos.",
  "for.hints.protein_usage.tag": "¿Cómo usar la proteína?",
  "for.hints.replicator_usage.desc": "El replicador produce un nuevo organismo a partir de una plantilla genética usando ADN líquido y proteína.",
  "for.hints.replicator_usage.tag": "¿Cómo usar el replicador?",
  "for.hints.sample_reuse.desc": "Las muestras de genes no deseadas se pueden borrar calentándolas en un horno.",
  "for.hints.sample_reuse.tag": "¡No tires las muestras!",
  "for.hints.sample_selection.desc": "El muestreador elige un alelo aleatorio del genoma del espécimen y lo guarda en una muestra de genes.",
  "for.hints.sample_selection.tag": "¿Cómo elegir un gen?",
  "for.hints.sample_usage.desc": "Las muestras de genes se pueden combinar con una plantilla genética para crear un genoma completo para usar en el imprimidor.",
  "for.hints.sample_usage.tag": "¿Para qué sirven las muestras?",
  "for.hints.transposer_usage.desc": "El transpositor genético crea copias de muestras de genes y plantillas genéticas.",
  "for.hints.transposer_usage.tag": "¿Cómo usar el transpositor genético?",
  "gendustry.for.chance": "¡%s%% de probabilidad de ser consumido!",
  "info.gendustry.dna": "El ADN líquido se utiliza en el replicador para producir nuevos organismos a partir de plantillas genéticas completas.",
  "info.gendustry.mutagen": "El mutágeno se utiliza en el mutatrón y el mutatrón avanzado para desencadenar mutaciones entre dos organismos.",
  "info.gendustry.protein": "La proteína se utiliza en el replicador para producir nuevos organismos a partir de plantillas genéticas completas.",
  "item.gendustry.activity_simulator_elite_upgrade": "Mejora élite de simulador de actividad",
  "item.gendustry.activity_simulator_elite_upgrade.tooltip": "Una combinación de las mejoras de cielo, resistente a la intemperie y iluminación.",
  "item.gendustry.automation_upgrade": "Mejora de automatización",
  "item.gendustry.automation_upgrade.tooltip": "Recicla automáticamente princesas y zánganos de reinas fallecidas.",
  "item.gendustry.blank_gene_sample": "Muestra de gen en blanco",
  "item.gendustry.blank_gene_sample.tooltip": "Usar en el muestreador para obtener muestras de genes",
  "item.gendustry.blank_genetic_template": "Plantilla genética en blanco",
  "item.gendustry.blank_genetic_template.tooltip": "Combinar con muestras de genes en una mesa de crafteo",
  "item.gendustry.bucket_liquid_dna": "Cubo de ADN líquido",
  "item.gendustry.bucket_mutagen": "Cubo de mutágeno",
  "item.gendustry.bucket_protein": "Cubo de proteína",
  "item.gendustry.climate_control_module": "Módulo de control climático",
  "item.gendustry.cooler_upgrade": "Mejora de enfriador",
  "item.gendustry.cooler_upgrade.tooltip": "Disminuye la temperatura del apiario en 1 paso.",
  "item.gendustry.dryer_upgrade": "Mejora de secador",
  "item.gendustry.dryer_upgrade.tooltip": "Disminuye la humedad del apiario en 1 paso.",
  "item.gendustry.elite_upgrade_frame": "Marco de mejora élite",
  "item.gendustry.environmental_processor": "Procesador ambiental",
  "item.gendustry.fertility_elite_upgrade": "Mejora élite de fertilidad",
  "item.gendustry.fertility_elite_upgrade.tooltip": "Aumenta el conteo de fertilidad en 1.",
  "item.gendustry.gene_sample": "Muestra de gen (%s)",
  "item.gendustry.genetic_template": "Plantilla genética (%s)",
  "item.gendustry.genetic_template.allele_count": "Alelos (%1$s/%2$s)",
  "item.gendustry.genetic_template.allele_entry": "  %1$s - %2$s",
  "item.gendustry.genetic_template.missing_allele": "FALTA",
  "item.gendustry.genetics_processor": "Procesador de genética",
  "item.gendustry.heater_upgrade": "Mejora de calentador",
  "item.gendustry.heater_upgrade.tooltip": "Aumenta la temperatura del apiario en 1 paso.",
  "item.gendustry.humidifier_upgrade": "Mejora de humidificador",
  "item.gendustry.humidifier_upgrade.tooltip": "Aumenta la humedad del apiario en 1 paso.",
  "item.gendustry.labware": "Aparato laboratorio",
  "item.gendustry.lifespan_upgrade": "Mejora de vida útil",
  "item.gendustry.lifespan_upgrade.tooltip": "Disminuye la vida útil en un 20%.",
  "item.gendustry.lighting_upgrade": "Mejora de iluminación",
  "item.gendustry.lighting_upgrade.tooltip": "Permite a las abejas trabajar sin necesidad de dormir.",
  "item.gendustry.mutation_elite_upgrade": "Mejora élite de mutación",
  "item.gendustry.mutation_elite_upgrade.tooltip": "Aumenta las posibilidades de mutación de las abejas en un 25%.",
  "item.gendustry.nether_upgrade": "Mejora del nether",
  "item.gendustry.nether_upgrade.tooltip": "Establece el clima del apiario a temperatura infernal y humedad árida.",
  "item.gendustry.pollen_kit": "Kit de polen",
  "item.gendustry.pollination_upgrade": "Mejora de polinización",
  "item.gendustry.pollination_upgrade.tooltip": "Aumenta la polinización de las abejas en un 25%.",
  "item.gendustry.power_module": "Módulo de energía",
  "item.gendustry.productivity_elite_upgrade": "Mejora élite de productividad",
  "item.gendustry.productivity_elite_upgrade.tooltip": "Aumenta la productividad de las abejas en un 25% y acelera el ciclo de trabajo en 15 ticks.",
  "item.gendustry.productivity_upgrade": "Mejora de productividad",
  "item.gendustry.productivity_upgrade.tooltip": "Aumenta la productividad de las abejas en un 25%.",
  "item.gendustry.receptacle": "Receptáculo",
  "item.gendustry.scrubber_upgrade": "Mejora de depurador",
  "item.gendustry.scrubber_upgrade.tooltip": "Deshabilita la polinización de las abejas.",
  "item.gendustry.sieve_upgrade": "Mejora de tamiz",
  "item.gendustry.sieve_upgrade.tooltip": "Recicla automáticamente princesas y zánganos de reinas fallecidas.",
  "item.gendustry.sky_upgrade": "Mejora de cielo",
  "item.gendustry.sky_upgrade.tooltip": "Simula una vista del cielo para las abejas que no habitan en cuevas.",
  "item.gendustry.stabilizer_upgrade": "Mejora de estabilizador",
  "item.gendustry.stabilizer_upgrade.tooltip": "Evita que las abejas de stock innoble mueran.",
  "item.gendustry.territory_elite_upgrade": "Mejora élite de territorio",
  "item.gendustry.territory_elite_upgrade.tooltip": "Aumenta el territorio de las abejas en un 25%, pero tiene un límite superior.",
  "item.gendustry.territory_upgrade": "Mejora de territorio",
  "item.gendustry.territory_upgrade.tooltip": "Aumenta el territorio en un 25%.",
  "item.gendustry.upgrade.energy_cost": "Costo de energía: %s RF",
  "item.gendustry.upgrade.max_count": "Conteo máximo: %s",
  "item.gendustry.upgrade_frame": "Marco de mejora",
  "item.gendustry.weatherproof_upgrade": "Mejora resistente a la intemperie",
  "item.gendustry.weatherproof_upgrade.tooltip": "Permite a las abejas trabajar durante la lluvia.",
  "item.gendustry.youth_elite_upgrade": "Mejora élite de juventud",
  "item.gendustry.youth_elite_upgrade.tooltip": "Aumenta la vida útil en un 20%.",
  "itemGroup.gendustry": "Gendustry",
  "itemGroup.gene_samples": "Muestras de genes"
}
```

### src/main/resources/assets/gendustry/lang/ja_jp.json

```json
{
  "block.gendustry.advanced_mutatron": "高性能変異機",
  "block.gendustry.dna_extractor": "DNA抽出機",
  "block.gendustry.fluid_liquid_dna": "液体DNA",
  "block.gendustry.fluid_mutagen": "変異原",
  "block.gendustry.fluid_protein": "タンパク質",
  "block.gendustry.genetic_transposer": "遺伝子転写機",
  "block.gendustry.imprinter": "遺伝子刷り込み機",
  "block.gendustry.industrial_apiary": "工業用養蜂箱",
  "block.gendustry.mutagen_producer": "変異原製造機",
  "block.gendustry.mutatron": "変異機",
  "block.gendustry.protein_liquefier": "タンパク質液化機",
  "block.gendustry.replicator": "複製機",
  "block.gendustry.sampler": "サンプラー",
  "errors.gendustry.incompatible_species.desc": "互換性のない種",
  "errors.gendustry.incompatible_species.help": "個体は同じ種の個体としか交配できません。",
  "errors.gendustry.no_blank.desc": "空のテンプレート/サンプルがありません",
  "errors.gendustry.no_blank.help": "遺伝子転写機には、コピー先となる空の遺伝子サンプルまたは空の遺伝子テンプレートが必要です。",
  "errors.gendustry.no_dna.desc": "液体DNAがありません",
  "errors.gendustry.no_dna.help": "この機械の動作には液体DNAが必要です。",
  "errors.gendustry.no_labware.desc": "実験器具がありません",
  "errors.gendustry.no_labware.help": "この機械の動作には実験器具が必要です。",
  "errors.gendustry.no_mates.desc": "交配相手がいません",
  "errors.gendustry.no_mates.help": "突然変異を発生させるには、互換性のある2匹の交配相手が必要です。",
  "errors.gendustry.no_mutagen.desc": "変異原がありません",
  "errors.gendustry.no_mutagen.help": "突然変異を引き起こすには変異原が必要です。",
  "errors.gendustry.no_mutations.desc": "突然変異なし",
  "errors.gendustry.no_mutations.help": "これら2つの種の間には突然変異は存在しません。別の種を選択してください。",
  "errors.gendustry.no_protein.desc": "タンパク質がありません",
  "errors.gendustry.no_protein.help": "この機械の動作にはタンパク質が必要です。",
  "errors.gendustry.no_samples.desc": "サンプルがありません",
  "errors.gendustry.no_samples.help": "この機械の動作には空の遺伝子サンプルが必要です。",
  "errors.gendustry.no_selection.desc": "突然変異を選択してください",
  "errors.gendustry.no_selection.help": "高性能変異機で発生させる突然変異を選択する必要があります。",
  "errors.gendustry.no_source.desc": "ソースとなるテンプレート/サンプルがありません",
  "errors.gendustry.no_source.help": "遺伝子転写機に、情報が入った遺伝子サンプルまたは遺伝子テンプレートがありません。",
  "errors.gendustry.no_template.desc": "テンプレートがありません",
  "errors.gendustry.no_template.help": "動作には完成した遺伝子テンプレートが必要です。",
  "fluid_type.gendustry.liquid_dna": "液体DNA",
  "fluid_type.gendustry.mutagen": "変異原",
  "fluid_type.gendustry.protein": "タンパク質",
  "for.hints.advanced_mutatron.desc": "2匹の親の間で望む突然変異を選択するには、高性能変異機を使用します。",
  "for.hints.advanced_mutatron.tag": "高性能変異機の使い方は？",
  "for.hints.dna_ingredients.desc": "液体DNAは、蜂、苗木、花粉、蝶など、Forestryのゲノムを持つあらゆる生物から作ることができます。",
  "for.hints.dna_ingredients.tag": "液体DNAの材料は？",
  "for.hints.dna_usage.desc": "液体DNAを複製機で使って、新しい生物を構築しましょう！",
  "for.hints.dna_usage.tag": "液体DNAの使い方は？",
  "for.hints.imprinter_usage.desc": "遺伝子刷り込み機は、個体のゲノムを遺伝子テンプレートに保存された対立遺伝子で置き換えます。劣性の個体は生き残れないかもしれません。",
  "for.hints.imprinter_usage.tag": "遺伝子刷り込み機の使い方は？",
  "for.hints.industrial_apiary.desc": "左のスロットはプリンセスとドローン用です。中央の4つのスロットはアップグレード用。右の9つのスロットは出力用です。",
  "for.hints.industrial_apiary.tag": "工業用養蜂箱の使い方は？",
  "for.hints.industrial_apiary_upgrades.desc": "工業用養蜂箱は巣枠を必要としません。代わりに、気候、生産性、寿命などに影響を与えるアップグレードを使用します！",
  "for.hints.industrial_apiary_upgrades.tag": "なぜ巣枠がないの？",
  "for.hints.mutagen_ingredients.desc": "変異原はレッドストーンやグロウストーン、さらにはウランからも作ることができます！",
  "for.hints.mutagen_ingredients.tag": "変異原の材料は？",
  "for.hints.mutagen_usage.desc": "変異原を生産して、他のGendustryの機械で使用します。",
  "for.hints.mutagen_usage.tag": "変異原の用途は？",
  "for.hints.mutatron_usage.desc": "変異機は2匹の親生物の間で突然変異を引き起こし、新しい種の子孫を生み出します。",
  "for.hints.mutatron_usage.tag": "変異機とは？",
  "for.hints.protein_ingredients.desc": "タンパク質はあらゆる種類の生肉から作ることができます。",
  "for.hints.protein_ingredients.tag": "タンパク質の入手方法は？",
  "for.hints.protein_usage.desc": "タンパク質は、複製機が新しい生物を作り出すために使用する材料です。",
  "for.hints.protein_usage.tag": "タンパク質の使い方は？",
  "for.hints.replicator_usage.desc": "複製機は、液体DNAとタンパク質を使って、遺伝子テンプレートから新しい生物を生産します。",
  "for.hints.replicator_usage.tag": "複製機の使い方は？",
  "for.hints.sample_reuse.desc": "不要な遺伝子サンプルは、かまどで加熱することで白紙に戻すことができます。",
  "for.hints.sample_reuse.tag": "サンプルを捨てないで！",
  "for.hints.sample_selection.desc": "サンプラーは標本のゲノムからランダムに対立遺伝子を選び出し、それを遺伝子サンプルに保存します。",
  "for.hints.sample_selection.tag": "遺伝子の選び方は？",
  "for.hints.sample_usage.desc": "遺伝子サンプルは遺伝子テンプレートとクラフトすることで、遺伝子刷り込み機で使用するための完全なゲノムを作成できます。",
  "for.hints.sample_usage.tag": "サンプルの用途は？",
  "for.hints.transposer_usage.desc": "遺伝子転写機は、遺伝子サンプルや遺伝子テンプレートのコピーを作成します。",
  "for.hints.transposer_usage.tag": "遺伝子転写機の使い方は？",
  "gendustry.for.chance": "%s%%の確率で消費されます！",
  "info.gendustry.dna": "液体DNAは、複製機で完全な遺伝子テンプレートから新しい生物を生産するために使用されます。",
  "info.gendustry.mutagen": "変異原は、変異機および高性能変異機で2つの生物間の突然変異を引き起こすために使用されます。",
  "info.gendustry.protein": "タンパク質は、複製機で完全な遺伝子テンプレートから新しい生物を生産するために使用されます。",
  "item.gendustry.activity_simulator_elite_upgrade": "高性能活動シミュレーターアップグレード",
  "item.gendustry.activity_simulator_elite_upgrade.tooltip": "天候耐性、照明、および天候アップグレードを組み合わせたものです。",
  "item.gendustry.automation_upgrade": "自動化アップグレード",
  "item.gendustry.automation_upgrade.tooltip": "死んだ女王蜂から生まれたプリンセスとドローンを自動的に再利用します。",
  "item.gendustry.blank_gene_sample": "空の遺伝子サンプル",
  "item.gendustry.blank_gene_sample.tooltip": "サンプラーで使用して遺伝子サンプルを入手します。",
  "item.gendustry.blank_genetic_template": "空の遺伝子テンプレート",
  "item.gendustry.blank_genetic_template.tooltip": "作業台で遺伝子サンプルと組み合わせます。",
  "item.gendustry.bucket_liquid_dna": "液体DNA入りバケツ",
  "item.gendustry.bucket_mutagen": "変異原入りバケツ",
  "item.gendustry.bucket_protein": "タンパク質入りバケツ",
  "item.gendustry.climate_control_module": "気候制御モジュール",
  "item.gendustry.cooler_upgrade": "冷却アップグレード",
  "item.gendustry.cooler_upgrade.tooltip": "養蜂箱の温度を1段階下げます。",
  "item.gendustry.dryer_upgrade": "乾燥アップグレード",
  "item.gendustry.dryer_upgrade.tooltip": "養蜂箱の湿度を1段階下げます。",
  "item.gendustry.elite_upgrade_frame": "高性能アップグレードフレーム",
  "item.gendustry.environmental_processor": "環境プロセッサー",
  "item.gendustry.fertility_elite_upgrade": "高性能繁殖力アップグレード",
  "item.gendustry.fertility_elite_upgrade.tooltip": "繁殖回数を1増やします。",
  "item.gendustry.gene_sample": "遺伝子サンプル (%s)",
  "item.gendustry.genetic_template": "遺伝子テンプレート (%s)",
  "item.gendustry.genetic_template.allele_count": "対立遺伝子 (%1$s/%2$s)",
  "item.gendustry.genetic_template.allele_entry": "  %1$s - %2$s",
  "item.gendustry.genetic_template.missing_allele": "欠損",
  "item.gendustry.genetics_processor": "遺伝子プロセッサー",
  "item.gendustry.heater_upgrade": "加熱アップグレード",
  "item.gendustry.heater_upgrade.tooltip": "養蜂箱の温度を1段階上げます。",
  "item.gendustry.humidifier_upgrade": "加湿アップグレード",
  "item.gendustry.humidifier_upgrade.tooltip": "養蜂箱の湿度を1段階上げます。",
  "item.gendustry.labware": "実験器具",
  "item.gendustry.lifespan_upgrade": "寿命アップグレード",
  "item.gendustry.lifespan_upgrade.tooltip": "寿命を20%減少させます。",
  "item.gendustry.lighting_upgrade": "照明アップグレード",
  "item.gendustry.lighting_upgrade.tooltip": "蜂が睡眠を必要とせずに活動できるようにします。",
  "item.gendustry.mutation_elite_upgrade": "高性能突然変異アップグレード",
  "item.gendustry.mutation_elite_upgrade.tooltip": "蜂の突然変異の確率を25%増加させます。",
  "item.gendustry.nether_upgrade": "ネザーアップグレード",
  "item.gendustry.nether_upgrade.tooltip": "養蜂箱の気候を地獄のような温度と乾燥した湿度に設定します。",
  "item.gendustry.pollen_kit": "花粉キット",
  "item.gendustry.pollination_upgrade": "受粉アップグレード",
  "item.gendustry.pollination_upgrade.tooltip": "蜂の受粉率を25%増加させます。",
  "item.gendustry.power_module": "パワーモジュール",
  "item.gendustry.productivity_elite_upgrade": "高性能生産性アップグレード",
  "item.gendustry.productivity_elite_upgrade.tooltip": "蜂の生産性を25%増加させ、作業サイクルを15ティック速めます。",
  "item.gendustry.productivity_upgrade": "生産性アップグレード",
  "item.gendustry.productivity_upgrade.tooltip": "蜂の生産性を25%増加させます。",
  "item.gendustry.receptacle": "レセプタクル",
  "item.gendustry.scrubber_upgrade": "スクラバーアップグレード",
  "item.gendustry.scrubber_upgrade.tooltip": "蜂の受粉を無効にします。",
  "item.gendustry.sieve_upgrade": "ふるい分けアップグレード",
  "item.gendustry.sieve_upgrade.tooltip": "死んだ女王蜂から生まれたプリンセスとドローンを自動的に再利用します。",
  "item.gendustry.sky_upgrade": "天候アップグレード",
  "item.gendustry.sky_upgrade.tooltip": "洞窟性でない蜂のために、空が見える状態をシミュレートします。",
  "item.gendustry.stabilizer_upgrade": "安定化アップグレード",
  "item.gendustry.stabilizer_upgrade.tooltip": "劣性遺伝子を持つ蜂が死ぬのを防ぎます。",
  "item.gendustry.territory_elite_upgrade": "高性能活動範囲アップグレード",
  "item.gendustry.territory_elite_upgrade.tooltip": "蜂の活動範囲を25%増加させますが、上限はより高くなります。",
  "item.gendustry.territory_upgrade": "活動範囲アップグレード",
  "item.gendustry.territory_upgrade.tooltip": "活動範囲を25%増加させます。",
  "item.gendustry.upgrade.energy_cost": "エネルギーコスト: %s RF",
  "item.gendustry.upgrade.max_count": "最大数: %s",
  "item.gendustry.upgrade_frame": "アップグレードフレーム",
  "item.gendustry.weatherproof_upgrade": "天候耐性アップグレード",
  "item.gendustry.weatherproof_upgrade.tooltip": "雨天時でも蜂が活動できるようにします。",
  "item.gendustry.youth_elite_upgrade": "高性能寿命延長アップグレード",
  "item.gendustry.youth_elite_upgrade.tooltip": "寿命を20%増加させます。",
  "itemGroup.gendustry": "Gendustry",
  "itemGroup.gene_samples": "遺伝子サンプル"
}
```

### src/main/resources/assets/gendustry/lang/ru_ru.json

```json
{
  "block.gendustry.advanced_mutatron": "Продвинутый мутатрон",
  "block.gendustry.dna_extractor": "Экстрактор ДНК",
  "block.gendustry.fluid_liquid_dna": "Жидкая ДНК",
  "block.gendustry.fluid_mutagen": "Мутаген",
  "block.gendustry.fluid_protein": "Белок",
  "block.gendustry.genetic_transposer": "Генетический транспозер",
  "block.gendustry.imprinter": "Импринтер",
  "block.gendustry.industrial_apiary": "Промышленная пасека",
  "block.gendustry.mutagen_producer": "Мутагенный производитель",
  "block.gendustry.mutatron": "Мутатрон",
  "block.gendustry.protein_liquefier": "Белковый разжижитель",
  "block.gendustry.replicator": "Репликатор",
  "block.gendustry.sampler": "Пробоотборник",

  "errors.gendustry.incompatible_species.desc": "Несовместимые виды",
  "errors.gendustry.incompatible_species.help": "Особей можно скрещивать только с особями того же типа.",
  "errors.gendustry.no_blank.desc": "Отсутствует пустой шаблон/образец",
  "errors.gendustry.no_blank.help": "Генетическому транспозеру необходимы пустой генетический образец или пустой генетический шаблон для копирования.",
  "errors.gendustry.no_dna.desc": "Отсутствует жидкая ДНК",
  "errors.gendustry.no_dna.help": "Для работы этой машины требуется жидкая ДНК.",
  "errors.gendustry.no_labware.desc": "Лабораторное оборудование отсутствует",
  "errors.gendustry.no_labware.help": "Для работы этой машины требуется лабораторное оборудование.",
  "errors.gendustry.no_mates.desc": "Партнёры отсутствуют",
  "errors.gendustry.no_mates.help": "Для возникновения мутации требуются два совместимых партнёра.",
  "errors.gendustry.no_mutagen.desc": "Мутаген отсутствует",
  "errors.gendustry.no_mutagen.help": "Для возникновения мутации требуется мутаген.",
  "errors.gendustry.no_mutations.desc": "Мутации отсутствуют",
  "errors.gendustry.no_mutations.help": "Между этими двумя видами нет мутаций. Выберите другой вид.",
  "errors.gendustry.no_protein.desc": "Белок отсутствует",
  "errors.gendustry.no_protein.help": "Для работы этой машины требуется белок.",
  "errors.gendustry.no_samples.desc": "Образцы отсутствуют",
  "errors.gendustry.no_samples.help": "Для работы этой машины требуются пустые генетические образцы.",
  "errors.gendustry.no_selection.desc": "Выберите мутацию",
  "errors.gendustry.no_selection.help": "Вы должны выбрать мутацию для продвинутого мутатрона.",
  "errors.gendustry.no_source.desc": "Отсутствует исходный шаблон/образец",
  "errors.gendustry.no_source.help": "Генетический транспозер не имеет заполненного генетического образца или генетического шаблона.",
  "errors.gendustry.no_template.desc": "Шаблон отсутствует",
  "errors.gendustry.no_template.help": "Для работы требуется полный генетический шаблон.",

  "fluid_type.gendustry.liquid_dna": "Жидкая ДНК",
  "fluid_type.gendustry.mutagen": "Мутаген",
  "fluid_type.gendustry.protein": "Белок",

  "for.hints.advanced_mutatron.desc": "Чтобы выбрать желаемую мутацию между двумя родителями, воспользуйтесь продвинутым мутатроном.",
  "for.hints.advanced_mutatron.tag": "Как пользоваться репликатором?",
  "for.hints.dna_ingredients.desc": "Жидкую ДНК можно получить из любых организмов с лесным геномом, таких как пчёлы, саженцы, пыльца и бабочки.",
  "for.hints.dna_ingredients.tag": "Из чего состоит жидкая ДНК?",
  "for.hints.dna_usage.desc": "Используйте жидкую ДНК в репликаторе для создания новых организмов!",
  "for.hints.dna_usage.tag": "Как использовать жидкую ДНК?",
  "for.hints.imprinter_usage.desc": "Импринтер заменяет геном индивидуума аллелями, хранящимися в генетическом шаблоне. Неблагородный род может не выжить.",
  "for.hints.imprinter_usage.tag": "Как пользоваться импринтером?",
  "for.hints.industrial_apiary.desc": "Левые слоты предназначены для принцессы и трутня. Четыре средних слота предназначены для улучшений. Девять правых слотов - это выходы.",
  "for.hints.industrial_apiary.tag": "Как пользоваться промышленной пасекой?",
  "for.hints.industrial_apiary_upgrades.desc": "Промышленная пасека не нуждается в рамках. Вместо этого она использует улучшения, которые влияют на климат, продуктивность, продолжительность жизни и многое другое!",
  "for.hints.industrial_apiary_upgrades.tag": "Почему нет рамок?",
  "for.hints.mutagen_ingredients.desc": "Мутаген можно получить из редстоуна, светокамня и даже урана!",
  "for.hints.mutagen_ingredients.tag": "Из чего получается мутаген?",
  "for.hints.mutagen_usage.desc": "Мутаген производят для использования в других генетических машинах.",
  "for.hints.mutagen_usage.tag": "Для чего нужен мутаген?",
  "for.hints.mutatron_usage.desc": "Мутатрон запускает мутацию между двумя родителями, приводя к появлению потомства нового вида.",
  "for.hints.mutatron_usage.tag": "Что такое мутатрон?",
  "for.hints.protein_ingredients.desc": "Белок можно получить из любого сырого мяса.",
  "for.hints.protein_ingredients.tag": "Как получить белок?",
  "for.hints.protein_usage.desc": "Белок - это ингредиент, используемый репликатором для создания новых организмов.",
  "for.hints.protein_usage.tag": "Как использовать белок?",
  "for.hints.replicator_usage.desc": "Репликатор создаёт новый организм на основе генетического шаблона, используя жидкую ДНК и белок.",
  "for.hints.replicator_usage.tag": "Как пользоваться репликатором?",
  "for.hints.sample_reuse.desc": "Ненужные генетические образцы можно удалить, нагрев их в печи.",
  "for.hints.sample_reuse.tag": "Не выбрасывайте образцы!",
  "for.hints.sample_selection.desc": "Пробоотборник выбирает случайный аллель из генома образца и сохраняет его в генетическом образце.",
  "for.hints.sample_selection.tag": "Как выбрать ген?",
  "for.hints.sample_usage.desc": "Генетические образцы можно создать с помощью генетического шаблона, чтобы создать полный геном для использования в импринтере.",
  "for.hints.sample_usage.tag": "Для чего нужны образцы?",
  "for.hints.transposer_usage.desc": "Генетический транспозер создаёт копии генетических образцов и генетических шаблонов.",
  "for.hints.transposer_usage.tag": "Как использовать генетический транспозер?",

  "gendustry.for.chance": "Шанс потребления: %s%%!",

  "info.gendustry.dna": "Жидкая ДНК используется в репликаторе для создания новых организмов на основе полных генетических шаблонов.",
  "info.gendustry.mutagen": "Мутаген используется в мутатроне и продвинутом мутатроне для запуска мутаций между двумя организмами.",
  "info.gendustry.protein": "Белок используется в репликаторе для создания новых организмов из полных генетических шаблонов.",

  "item.gendustry.activity_simulator_elite_upgrade": "Элитное улучшение: Симулятор активности",
  "item.gendustry.activity_simulator_elite_upgrade.tooltip": "§7Сочетание улучшений «§bНебо§7», «§8Дождевой щит§7» и «§eОсвещение§7».",
  "item.gendustry.automation_upgrade": "Улучшение: Автоматизация",
  "item.gendustry.automation_upgrade.tooltip": "§7Автоматически перерабатывает принцесс и трутней от умерших королев.",
  "item.gendustry.blank_gene_sample": "Пустой генетический образец",
  "item.gendustry.blank_gene_sample.tooltip": "§7Используйте в пробоотборнике для получения генетических образцов.",
  "item.gendustry.blank_genetic_template": "Пустой генетический шаблон",
  "item.gendustry.blank_genetic_template.tooltip": "§7Комбинируйте с генетическими образцами в верстаке.",
  "item.gendustry.bucket_liquid_dna": "Ведро жидкой ДНК",
  "item.gendustry.bucket_mutagen": "Ведро мутагена",
  "item.gendustry.bucket_protein": "Ведро белка",
  "item.gendustry.climate_control_module": "Модуль: Климат-контроль",
  "item.gendustry.cooler_upgrade": "Улучшение: Вентилятор",
  "item.gendustry.cooler_upgrade.tooltip": "§7Уменьшает температуру в пасеке на §e1 §7ступень.",
  "item.gendustry.dryer_upgrade": "Улучшение: Осушение",
  "item.gendustry.dryer_upgrade.tooltip": "§7Уменьшает влажность в пасеке на §e1 §7ступень.",
  "item.gendustry.elite_upgrade_frame": "Элитная рамка улучшения",
  "item.gendustry.environmental_processor": "Экологический процессор",
  "item.gendustry.fertility_elite_upgrade": "Элитное улучшение: Плодовитость",
  "item.gendustry.fertility_elite_upgrade.tooltip": "§7Увеличивает коэффициент плодовитости на §e1 §7ступень.",
  "item.gendustry.gene_sample": "Генетический образец (%s)",
  "item.gendustry.genetic_template": "Генетический шаблон (%s)",
  "item.gendustry.genetic_template.allele_count": "Аллели (%1$s/%2$s)",
  "item.gendustry.genetic_template.allele_entry": "  %1$s - %2$s",
  "item.gendustry.genetic_template.missing_allele": "ОТСУТСТВУЕТ",
  "item.gendustry.genetics_processor": "Генетический процессор",
  "item.gendustry.heater_upgrade": "Улучшение: Обогреватель",
  "item.gendustry.heater_upgrade.tooltip": "§7Повышает температуру в пасеке на §e1 §7ступень.",
  "item.gendustry.humidifier_upgrade": "Улучшение: Увлажнение",
  "item.gendustry.humidifier_upgrade.tooltip": "§7Повышает влажность в пасеке на §e1 §7ступень.",
  "item.gendustry.labware": "Лабораторное оборудование",
  "item.gendustry.lifespan_upgrade": "Улучшение: Срок жизни",
  "item.gendustry.lifespan_upgrade.tooltip": "§7Уменьшает продолжительность жизни на §e20%§7.",
  "item.gendustry.lighting_upgrade": "Улучшение: Освещение",
  "item.gendustry.lighting_upgrade.tooltip": "§7Позволяет пчёлам работать без необходимости спать.",
  "item.gendustry.mutation_elite_upgrade": "Элитное улучшение: Мутация",
  "item.gendustry.mutation_elite_upgrade.tooltip": "§7Увеличивает вероятность мутации пчелы на §e25%§7.",
  "item.gendustry.nether_upgrade": "Улучшение: Незер",
  "item.gendustry.nether_upgrade.tooltip": "§7Устанавливает в пасеке §cадскую температуру §7и §9сухую влажность§7.",
  "item.gendustry.pollen_kit": "Набор для сбора пыльцы",
  "item.gendustry.pollination_upgrade": "Улучшение: Опыление",
  "item.gendustry.pollination_upgrade.tooltip": "§7Увеличивает опыление пчёлами на §e25%§7.",
  "item.gendustry.power_module": "Модуль: Энергия",
  "item.gendustry.productivity_elite_upgrade": "Элитное улучшение: Продуктивность",
  "item.gendustry.productivity_elite_upgrade.tooltip": "§7Увеличивает продуктивность пчёл на §e25%§7 и ускоряет рабочий цикл на §e15 §7тиков.",
  "item.gendustry.productivity_upgrade": "Улучшение: Продуктивность",
  "item.gendustry.productivity_upgrade.tooltip": "§7Увеличивает продуктивность пчёл на §e25%§7.",
  "item.gendustry.receptacle": "Пчелиный сосуд",
  "item.gendustry.scrubber_upgrade": "Улучшение: Скруббер",
  "item.gendustry.scrubber_upgrade.tooltip": "§7Отключает опыление пчёлами.",
  "item.gendustry.sieve_upgrade": "Улучшение: Сито",
  "item.gendustry.sieve_upgrade.tooltip": "§7Автоматически перерабатывает принцесс и трутней от умерших королев.",
  "item.gendustry.sky_upgrade": "Улучшение: Небо",
  "item.gendustry.sky_upgrade.tooltip": "§7Имитирует вид неба для пчёл, которые не живут в пещерах.",
  "item.gendustry.stabilizer_upgrade": "Улучшение: Стабилизатор",
  "item.gendustry.stabilizer_upgrade.tooltip": "§7Предотвращает гибель неблагородных пчёл.",
  "item.gendustry.territory_elite_upgrade": "Элитное улучшение: Территория",
  "item.gendustry.territory_elite_upgrade.tooltip": "§7Увеличивает территорию пчёл на §e25%§7, но имеет более высокий лимит.",
  "item.gendustry.territory_upgrade": "Улучшение: Территория",
  "item.gendustry.territory_upgrade.tooltip": "§7Увеличивает территорию на §e25%§7.",
  "item.gendustry.upgrade.energy_cost": "Стоимость энергии: %s RF",
  "item.gendustry.upgrade.max_count": "Макс. кол-во: %s",
  "item.gendustry.upgrade_frame": "Рамка улучшения",
  "item.gendustry.weatherproof_upgrade": "Улучшение: Дождевой щит",
  "item.gendustry.weatherproof_upgrade.tooltip": "Позволяет пчёлам работать во время дождя.",
  "item.gendustry.youth_elite_upgrade": "Элитное улучшение: Молодость",
  "item.gendustry.youth_elite_upgrade.tooltip": "§7Увеличивает продолжительность жизни на §e20%§7.",

  "itemGroup.gendustry": "Лесное хозяйство: Генетика",
  "itemGroup.gene_samples": "Лесное хозяйство: Генетические образцы"
}
```

### src/main/resources/assets/gendustry/lang/zh_cn.json

```json
{
  "block.gendustry.advanced_mutatron": "高级诱变机",
  "block.gendustry.dna_extractor": "基因提取机",
  "block.gendustry.fluid_liquid_dna": "液态基因流体",
  "block.gendustry.fluid_mutagen": "诱变剂流体",
  "block.gendustry.fluid_protein": "蛋白质流体",
  "block.gendustry.genetic_transposer": "基因转换机",
  "block.gendustry.imprinter": "基因压印机",
  "block.gendustry.industrial_apiary": "工业蜂箱",
  "block.gendustry.mutagen_producer": "诱变剂制造机",
  "block.gendustry.mutatron": "诱变机",
  "block.gendustry.protein_liquefier": "蛋白质液化机",
  "block.gendustry.replicator": "基因复制机",
  "block.gendustry.sampler": "基因采样机",
  "errors.gendustry.incompatible_species.desc": "不兼容的物种",
  "errors.gendustry.incompatible_species.help": "个体只能与同物种类型的个体交配。",
  "errors.gendustry.no_blank.desc": "缺少空白模板/样本",
  "errors.gendustry.no_blank.help": "基因转换机需要空白基因样本或空白基因模板来进行复制。",
  "errors.gendustry.no_dna.desc": "缺少液态基因",
  "errors.gendustry.no_dna.help": "该机器需要液态基因才能运作。",
  "errors.gendustry.no_labware.desc": "缺少实验器具",
  "errors.gendustry.no_labware.help": "该机器需要实验器具才能运作。",
  "errors.gendustry.no_mates.desc": "缺少配偶",
  "errors.gendustry.no_mates.help": "需要两个兼容的配偶才能发生突变。",
  "errors.gendustry.no_mutagen.desc": "缺少诱变剂",
  "errors.gendustry.no_mutagen.help": "需要诱变剂来触发突变。",
  "errors.gendustry.no_mutations.desc": "无可用突变",
  "errors.gendustry.no_mutations.help": "这两个物种之间没有可用突变，请选择其他物种。",
  "errors.gendustry.no_protein.desc": "缺少蛋白质",
  "errors.gendustry.no_protein.help": "该机器需要蛋白质才能运作。",
  "errors.gendustry.no_samples.desc": "缺少样本",
  "errors.gendustry.no_samples.help": "该机器需要空白基因样本才能运作。",
  "errors.gendustry.no_selection.desc": "请选择突变",
  "errors.gendustry.no_selection.help": "必须为高级诱变机选择一个突变。",
  "errors.gendustry.no_source.desc": "缺少源模板/样本",
  "errors.gendustry.no_source.help": "基因转换机缺少已填充的基因样本或基因模板。",
  "errors.gendustry.no_template.desc": "缺少模板",
  "errors.gendustry.no_template.help": "需要完整的基因模板才能运作。",
  "fluid_type.gendustry.liquid_dna": "液态基因",
  "fluid_type.gendustry.mutagen": "诱变剂",
  "fluid_type.gendustry.protein": "蛋白质",
  "for.hints.advanced_mutatron.desc": "使用高级诱变机选择两个亲本之间的所需突变。",
  "for.hints.advanced_mutatron.tag": "如何使用高级诱变机？",
  "for.hints.dna_ingredients.desc": "液态基因可由任何具有林业基因组的生物制成，如蜜蜂、树苗、花粉和蝴蝶。",
  "for.hints.dna_ingredients.tag": "如何制作液态基因？",
  "for.hints.dna_usage.desc": "在复制机中使用液态基因来构建新生物！",
  "for.hints.dna_usage.tag": "如何使用液态基因？",
  "for.hints.imprinter_usage.desc": "压印机用基因模板中的等位基因替换个体基因组，卑贱血统可能无法存活。",
  "for.hints.imprinter_usage.tag": "如何使用压印机？",
  "for.hints.industrial_apiary.desc": "左侧插槽放置公主蜂和雄蜂，中间四个插槽放置升级模块，右侧九个插槽为输出。",
  "for.hints.industrial_apiary.tag": "如何使用工业蜂箱？",
  "for.hints.industrial_apiary_upgrades.desc": "工业蜂箱不需要蜂箱框架，使用升级模块来影响气候、产量、寿命等属性！",
  "for.hints.industrial_apiary_upgrades.tag": "为什么不需要框架？",
  "for.hints.mutagen_ingredients.desc": "诱变剂可由红石、荧石甚至铀制成！",
  "for.hints.mutagen_ingredients.tag": "如何制作诱变剂？",
  "for.hints.mutagen_usage.desc": "生产诱变剂用于其他基因工业机器。",
  "for.hints.mutagen_usage.tag": "诱变剂有什么用？",
  "for.hints.mutatron_usage.desc": "诱变机触发两个亲本生物之间的突变，产生新物种后代。",
  "for.hints.mutatron_usage.tag": "什么是诱变机？",
  "for.hints.protein_ingredients.desc": "蛋白质可由任何生肉制成。",
  "for.hints.protein_ingredients.tag": "如何获取蛋白质？",
  "for.hints.protein_usage.desc": "蛋白质是复制机创建新生物所需的原料。",
  "for.hints.protein_usage.tag": "如何使用蛋白质？",
  "for.hints.replicator_usage.desc": "复制机使用基因模板配合液态基因和蛋白质来生成新生物。",
  "for.hints.replicator_usage.tag": "如何使用复制机？",
  "for.hints.sample_reuse.desc": "不需要的基因样本可在熔炉中烧制成空白样本。",
  "for.hints.sample_reuse.tag": "不要丢弃样本！",
  "for.hints.sample_selection.desc": "采样机从样本基因组中随机选取等位基因并保存到基因样本。",
  "for.hints.sample_selection.tag": "如何选择基因？",
  "for.hints.sample_usage.desc": "基因样本可与基因模板合成完整基因组，用于压印机。",
  "for.hints.sample_usage.tag": "样本有什么用？",
  "for.hints.transposer_usage.desc": "基因转换机用于复制基因样本和基因模板。",
  "for.hints.transposer_usage.tag": "如何使用基因转换机？",
  "gendustry.for.chance": "%s%% 几率被消耗！",
  "info.gendustry.dna": "液态基因用于复制机根据完整基因模板生成新生物。",
  "info.gendustry.mutagen": "诱变剂用于诱变机和高级诱变机触发生物间的突变。",
  "info.gendustry.protein": "蛋白质用于复制机根据完整基因模板生成新生物。",
  "item.gendustry.activity_simulator_elite_upgrade": "精英活动模拟器升级",
  "item.gendustry.activity_simulator_elite_upgrade.tooltip": "露天、防雨和照明升级的组合。",
  "item.gendustry.automation_upgrade": "自动化升级",
  "item.gendustry.automation_upgrade.tooltip": "自动回收死亡蜂后的公主蜂和雄蜂。",
  "item.gendustry.blank_gene_sample": "空白基因样本",
  "item.gendustry.blank_gene_sample.tooltip": "在采样机中使用以获取基因样本",
  "item.gendustry.blank_genetic_template": "空白基因模板",
  "item.gendustry.blank_genetic_template.tooltip": "在工作台中与基因样本组合",
  "item.gendustry.bucket_liquid_dna": "液态基因桶",
  "item.gendustry.bucket_mutagen": "诱变剂桶",
  "item.gendustry.bucket_protein": "蛋白质桶",
  "item.gendustry.climate_control_module": "气候控制模块",
  "item.gendustry.cooler_upgrade": "冷却器升级",
  "item.gendustry.cooler_upgrade.tooltip": "将蜂箱温度降低1级。",
  "item.gendustry.dryer_upgrade": "干燥升级",
  "item.gendustry.dryer_upgrade.tooltip": "将蜂箱湿度降低1级。",
  "item.gendustry.elite_upgrade_frame": "精英升级框架",
  "item.gendustry.environmental_processor": "环境处理器",
  "item.gendustry.fertility_elite_upgrade": "精英生育升级",
  "item.gendustry.fertility_elite_upgrade.tooltip": "生育次数增加1次。",
  "item.gendustry.gene_sample": "基因样本(%s)",
  "item.gendustry.genetic_template": "基因模板(%s)",
  "item.gendustry.genetic_template.allele_count": "等位基因(%1$s/%2$s)",
  "item.gendustry.genetic_template.allele_entry": "  %1$s - %2$s",
  "item.gendustry.genetic_template.missing_allele": "缺失",
  "item.gendustry.genetics_processor": "基因处理器",
  "item.gendustry.heater_upgrade": "加热器升级",
  "item.gendustry.heater_upgrade.tooltip": "将蜂箱温度提升1级。",
  "item.gendustry.humidifier_upgrade": "加湿器升级",
  "item.gendustry.humidifier_upgrade.tooltip": "将蜂箱湿度提升1级。",
  "item.gendustry.labware": "实验器具",
  "item.gendustry.lifespan_upgrade": "寿命升级",
  "item.gendustry.lifespan_upgrade.tooltip": "寿命缩短20%。",
  "item.gendustry.lighting_upgrade": "照明升级",
  "item.gendustry.lighting_upgrade.tooltip": "使蜜蜂无需睡眠即可工作。",
  "item.gendustry.mutation_elite_upgrade": "精英突变升级",
  "item.gendustry.mutation_elite_upgrade.tooltip": "蜜蜂突变几率增加25%。",
  "item.gendustry.nether_upgrade": "地狱升级",
  "item.gendustry.nether_upgrade.tooltip": "将蜂箱气候设为地狱温度与干旱湿度。",
  "item.gendustry.pollen_kit": "花粉收集套件",
  "item.gendustry.pollination_upgrade": "授粉升级",
  "item.gendustry.pollination_upgrade.tooltip": "蜜蜂授粉效率提升25%。",
  "item.gendustry.power_module": "能量模块",
  "item.gendustry.productivity_elite_upgrade": "精英生产升级",
  "item.gendustry.productivity_elite_upgrade.tooltip": "产量提升25%，工作周期缩短15刻。",
  "item.gendustry.productivity_upgrade": "生产升级",
  "item.gendustry.productivity_upgrade.tooltip": "产量提升25%。",
  "item.gendustry.receptacle": "插槽",
  "item.gendustry.scrubber_upgrade": "洗涤塔升级",
  "item.gendustry.scrubber_upgrade.tooltip": "禁用蜜蜂授粉功能。",
  "item.gendustry.sieve_upgrade": "筛滤升级",
  "item.gendustry.sieve_upgrade.tooltip": "自动回收死亡蜂后的公主蜂和雄蜂。",
  "item.gendustry.sky_upgrade": "露天升级",
  "item.gendustry.sky_upgrade.tooltip": "为非穴居蜜蜂模拟露天环境。",
  "item.gendustry.stabilizer_upgrade": "稳定器升级",
  "item.gendustry.stabilizer_upgrade.tooltip": "防止卑贱血统蜜蜂死亡。",
  "item.gendustry.territory_elite_upgrade": "精英范围升级",
  "item.gendustry.territory_elite_upgrade.tooltip": "活动范围扩大25%，上限更高。",
  "item.gendustry.territory_upgrade": "活动范围升级",
  "item.gendustry.territory_upgrade.tooltip": "活动范围扩大25%。",
  "item.gendustry.upgrade.energy_cost": "能量消耗: %s RF",
  "item.gendustry.upgrade.max_count": "最大数量: %s",
  "item.gendustry.upgrade_frame": "升级框架",
  "item.gendustry.weatherproof_upgrade": "防雨升级",
  "item.gendustry.weatherproof_upgrade.tooltip": "允许蜜蜂在雨天工作。",
  "item.gendustry.youth_elite_upgrade": "精英寿命升级",
  "item.gendustry.youth_elite_upgrade.tooltip": "寿命延长20%。",
  "itemGroup.gendustry": "基因工业",
  "itemGroup.gene_samples": "基因样本"
}
```

### src/main/resources/assets/gendustry/textures/block/liquid/liquid_dna_flowing.png.mcmeta

```
{
  "animation": {}
}
```

### src/main/resources/assets/gendustry/textures/block/liquid/liquid_dna_still.png.mcmeta

```
{
  "animation": {
    "frametime": 8
  }
}
```

### src/main/resources/assets/gendustry/textures/block/liquid/mutagen_flowing.png.mcmeta

```
{
  "animation": {}
}
```

### src/main/resources/assets/gendustry/textures/block/liquid/mutagen_still.png.mcmeta

```
{
  "animation": {
    "frametime": 4
  }
}
```

### src/main/resources/assets/gendustry/textures/block/liquid/protein_flowing.png.mcmeta

```
{
  "animation": {}
}
```

### src/main/resources/assets/gendustry/textures/block/liquid/protein_still.png.mcmeta

```
{
  "animation": {
    "frametime": 8
  }
}
```

### src/main/resources/gpl-3.0.txt

```text
                    GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

                            Preamble

  The GNU General Public License is a free, copyleft license for
software and other kinds of works.

  The licenses for most software and other practical works are designed
to take away your freedom to share and change the works.  By contrast,
the GNU General Public License is intended to guarantee your freedom to
share and change all versions of a program--to make sure it remains free
software for all its users.  We, the Free Software Foundation, use the
GNU General Public License for most of our software; it applies also to
any other work released this way by its authors.  You can apply it to
your programs, too.

  When we speak of free software, we are referring to freedom, not
price.  Our General Public Licenses are designed to make sure that you
have the freedom to distribute copies of free software (and charge for
them if you wish), that you receive source code or can get it if you
want it, that you can change the software or use pieces of it in new
free programs, and that you know you can do these things.

  To protect your rights, we need to prevent others from denying you
these rights or asking you to surrender the rights.  Therefore, you have
certain responsibilities if you distribute copies of the software, or if
you modify it: responsibilities to respect the freedom of others.

  For example, if you distribute copies of such a program, whether
gratis or for a fee, you must pass on to the recipients the same
freedoms that you received.  You must make sure that they, too, receive
or can get the source code.  And you must show them these terms so they
know their rights.

  Developers that use the GNU GPL protect your rights with two steps:
(1) assert copyright on the software, and (2) offer you this License
giving you legal permission to copy, distribute and/or modify it.

  For the developers' and authors' protection, the GPL clearly explains
that there is no warranty for this free software.  For both users' and
authors' sake, the GPL requires that modified versions be marked as
changed, so that their problems will not be attributed erroneously to
authors of previous versions.

  Some devices are designed to deny users access to install or run
modified versions of the software inside them, although the manufacturer
can do so.  This is fundamentally incompatible with the aim of
protecting users' freedom to change the software.  The systematic
pattern of such abuse occurs in the area of products for individuals to
use, which is precisely where it is most unacceptable.  Therefore, we
have designed this version of the GPL to prohibit the practice for those
products.  If such problems arise substantially in other domains, we
stand ready to extend this provision to those domains in future versions
of the GPL, as needed to protect the freedom of users.

  Finally, every program is threatened constantly by software patents.
States should not allow patents to restrict development and use of
software on general-purpose computers, but in those that do, we wish to
avoid the special danger that patents applied to a free program could
make it effectively proprietary.  To prevent this, the GPL assures that
patents cannot be used to render the program non-free.

  The precise terms and conditions for copying, distribution and
modification follow.

                       TERMS AND CONDITIONS

  0. Definitions.

  "This License" refers to version 3 of the GNU General Public License.

  "Copyright" also means copyright-like laws that apply to other kinds of
works, such as semiconductor masks.

  "The Program" refers to any copyrightable work licensed under this
License.  Each licensee is addressed as "you".  "Licensees" and
"recipients" may be individuals or organizations.

  To "modify" a work means to copy from or adapt all or part of the work
in a fashion requiring copyright permission, other than the making of an
exact copy.  The resulting work is called a "modified version" of the
earlier work or a work "based on" the earlier work.

  A "covered work" means either the unmodified Program or a work based
on the Program.

  To "propagate" a work means to do anything with it that, without
permission, would make you directly or secondarily liable for
infringement under applicable copyright law, except executing it on a
computer or modifying a private copy.  Propagation includes copying,
distribution (with or without modification), making available to the
public, and in some countries other activities as well.

  To "convey" a work means any kind of propagation that enables other
parties to make or receive copies.  Mere interaction with a user through
a computer network, with no transfer of a copy, is not conveying.

  An interactive user interface displays "Appropriate Legal Notices"
to the extent that it includes a convenient and prominently visible
feature that (1) displays an appropriate copyright notice, and (2)
tells the user that there is no warranty for the work (except to the
extent that warranties are provided), that licensees may convey the
work under this License, and how to view a copy of this License.  If
the interface presents a list of user commands or options, such as a
menu, a prominent item in the list meets this criterion.

  1. Source Code.

  The "source code" for a work means the preferred form of the work
for making modifications to it.  "Object code" means any non-source
form of a work.

  A "Standard Interface" means an interface that either is an official
standard defined by a recognized standards body, or, in the case of
interfaces specified for a particular programming language, one that
is widely used among developers working in that language.

  The "System Libraries" of an executable work include anything, other
than the work as a whole, that (a) is included in the normal form of
packaging a Major Component, but which is not part of that Major
Component, and (b) serves only to enable use of the work with that
Major Component, or to implement a Standard Interface for which an
implementation is available to the public in source code form.  A
"Major Component", in this context, means a major essential component
(kernel, window system, and so on) of the specific operating system
(if any) on which the executable work runs, or a compiler used to
produce the work, or an object code interpreter used to run it.

  The "Corresponding Source" for a work in object code form means all
the source code needed to generate, install, and (for an executable
work) run the object code and to modify the work, including scripts to
control those activities.  However, it does not include the work's
System Libraries, or general-purpose tools or generally available free
programs which are used unmodified in performing those activities but
which are not part of the work.  For example, Corresponding Source
includes interface definition files associated with source files for
the work, and the source code for shared libraries and dynamically
linked subprograms that the work is specifically designed to require,
such as by intimate data communication or control flow between those
subprograms and other parts of the work.

  The Corresponding Source need not include anything that users
can regenerate automatically from other parts of the Corresponding
Source.

  The Corresponding Source for a work in source code form is that
same work.

  2. Basic Permissions.

  All rights granted under this License are granted for the term of
copyright on the Program, and are irrevocable provided the stated
conditions are met.  This License explicitly affirms your unlimited
permission to run the unmodified Program.  The output from running a
covered work is covered by this License only if the output, given its
content, constitutes a covered work.  This License acknowledges your
rights of fair use or other equivalent, as provided by copyright law.

  You may make, run and propagate covered works that you do not
convey, without conditions so long as your license otherwise remains
in force.  You may convey covered works to others for the sole purpose
of having them make modifications exclusively for you, or provide you
with facilities for running those works, provided that you comply with
the terms of this License in conveying all material for which you do
not control copyright.  Those thus making or running the covered works
for you must do so exclusively on your behalf, under your direction
and control, on terms that prohibit them from making any copies of
your copyrighted material outside their relationship with you.

  Conveying under any other circumstances is permitted solely under
the conditions stated below.  Sublicensing is not allowed; section 10
makes it unnecessary.

  3. Protecting Users' Legal Rights From Anti-Circumvention Law.

  No covered work shall be deemed part of an effective technological
measure under any applicable law fulfilling obligations under article
11 of the WIPO copyright treaty adopted on 20 December 1996, or
similar laws prohibiting or restricting circumvention of such
measures.

  When you convey a covered work, you waive any legal power to forbid
circumvention of technological measures to the extent such circumvention
is effected by exercising rights under this License with respect to
the covered work, and you disclaim any intention to limit operation or
modification of the work as a means of enforcing, against the work's
users, your or third parties' legal rights to forbid circumvention of
technological measures.

  4. Conveying Verbatim Copies.

  You may convey verbatim copies of the Program's source code as you
receive it, in any medium, provided that you conspicuously and
appropriately publish on each copy an appropriate copyright notice;
keep intact all notices stating that this License and any
non-permissive terms added in accord with section 7 apply to the code;
keep intact all notices of the absence of any warranty; and give all
recipients a copy of this License along with the Program.

  You may charge any price or no price for each copy that you convey,
and you may offer support or warranty protection for a fee.

  5. Conveying Modified Source Versions.

  You may convey a work based on the Program, or the modifications to
produce it from the Program, in the form of source code under the
terms of section 4, provided that you also meet all of these conditions:

    a) The work must carry prominent notices stating that you modified
    it, and giving a relevant date.

    b) The work must carry prominent notices stating that it is
    released under this License and any conditions added under section
    7.  This requirement modifies the requirement in section 4 to
    "keep intact all notices".

    c) You must license the entire work, as a whole, under this
    License to anyone who comes into possession of a copy.  This
    License will therefore apply, along with any applicable section 7
    additional terms, to the whole of the work, and all its parts,
    regardless of how they are packaged.  This License gives no
    permission to license the work in any other way, but it does not
    invalidate such permission if you have separately received it.

    d) If the work has interactive user interfaces, each must display
    Appropriate Legal Notices; however, if the Program has interactive
    interfaces that do not display Appropriate Legal Notices, your
    work need not make them do so.

  A compilation of a covered work with other separate and independent
works, which are not by their nature extensions of the covered work,
and which are not combined with it such as to form a larger program,
in or on a volume of a storage or distribution medium, is called an
"aggregate" if the compilation and its resulting copyright are not
used to limit the access or legal rights of the compilation's users
beyond what the individual works permit.  Inclusion of a covered work
in an aggregate does not cause this License to apply to the other
parts of the aggregate.

  6. Conveying Non-Source Forms.

  You may convey a covered work in object code form under the terms
of sections 4 and 5, provided that you also convey the
machine-readable Corresponding Source under the terms of this License,
in one of these ways:

    a) Convey the object code in, or embodied in, a physical product
    (including a physical distribution medium), accompanied by the
    Corresponding Source fixed on a durable physical medium
    customarily used for software interchange.

    b) Convey the object code in, or embodied in, a physical product
    (including a physical distribution medium), accompanied by a
    written offer, valid for at least three years and valid for as
    long as you offer spare parts or customer support for that product
    model, to give anyone who possesses the object code either (1) a
    copy of the Corresponding Source for all the software in the
    product that is covered by this License, on a durable physical
    medium customarily used for software interchange, for a price no
    more than your reasonable cost of physically performing this
    conveying of source, or (2) access to copy the
    Corresponding Source from a network server at no charge.

    c) Convey individual copies of the object code with a copy of the
    written offer to provide the Corresponding Source.  This
    alternative is allowed only occasionally and noncommercially, and
    only if you received the object code with such an offer, in accord
    with subsection 6b.

    d) Convey the object code by offering access from a designated
    place (gratis or for a charge), and offer equivalent access to the
    Corresponding Source in the same way through the same place at no
    further charge.  You need not require recipients to copy the
    Corresponding Source along with the object code.  If the place to
    copy the object code is a network server, the Corresponding Source
    may be on a different server (operated by you or a third party)
    that supports equivalent copying facilities, provided you maintain
    clear directions next to the object code saying where to find the
    Corresponding Source.  Regardless of what server hosts the
    Corresponding Source, you remain obligated to ensure that it is
    available for as long as needed to satisfy these requirements.

    e) Convey the object code using peer-to-peer transmission, provided
    you inform other peers where the object code and Corresponding
    Source of the work are being offered to the general public at no
    charge under subsection 6d.

  A separable portion of the object code, whose source code is excluded
from the Corresponding Source as a System Library, need not be
included in conveying the object code work.

  A "User Product" is either (1) a "consumer product", which means any
tangible personal property which is normally used for personal, family,
or household purposes, or (2) anything designed or sold for incorporation
into a dwelling.  In determining whether a product is a consumer product,
doubtful cases shall be resolved in favor of coverage.  For a particular
product received by a particular user, "normally used" refers to a
typical or common use of that class of product, regardless of the status
of the particular user or of the way in which the particular user
actually uses, or expects or is expected to use, the product.  A product
is a consumer product regardless of whether the product has substantial
commercial, industrial or non-consumer uses, unless such uses represent
the only significant mode of use of the product.

  "Installation Information" for a User Product means any methods,
procedures, authorization keys, or other information required to install
and execute modified versions of a covered work in that User Product from
a modified version of its Corresponding Source.  The information must
suffice to ensure that the continued functioning of the modified object
code is in no case prevented or interfered with solely because
modification has been made.

  If you convey an object code work under this section in, or with, or
specifically for use in, a User Product, and the conveying occurs as
part of a transaction in which the right of possession and use of the
User Product is transferred to the recipient in perpetuity or for a
fixed term (regardless of how the transaction is characterized), the
Corresponding Source conveyed under this section must be accompanied
by the Installation Information.  But this requirement does not apply
if neither you nor any third party retains the ability to install
modified object code on the User Product (for example, the work has
been installed in ROM).

  The requirement to provide Installation Information does not include a
requirement to continue to provide support service, warranty, or updates
for a work that has been modified or installed by the recipient, or for
the User Product in which it has been modified or installed.  Access to a
network may be denied when the modification itself materially and
adversely affects the operation of the network or violates the rules and
protocols for communication across the network.

  Corresponding Source conveyed, and Installation Information provided,
in accord with this section must be in a format that is publicly
documented (and with an implementation available to the public in
source code form), and must require no special password or key for
unpacking, reading or copying.

  7. Additional Terms.

  "Additional permissions" are terms that supplement the terms of this
License by making exceptions from one or more of its conditions.
Additional permissions that are applicable to the entire Program shall
be treated as though they were included in this License, to the extent
that they are valid under applicable law.  If additional permissions
apply only to part of the Program, that part may be used separately
under those permissions, but the entire Program remains governed by
this License without regard to the additional permissions.

  When you convey a copy of a covered work, you may at your option
remove any additional permissions from that copy, or from any part of
it.  (Additional permissions may be written to require their own
removal in certain cases when you modify the work.)  You may place
additional permissions on material, added by you to a covered work,
for which you have or can give appropriate copyright permission.

  Notwithstanding any other provision of this License, for material you
add to a covered work, you may (if authorized by the copyright holders of
that material) supplement the terms of this License with terms:

    a) Disclaiming warranty or limiting liability differently from the
    terms of sections 15 and 16 of this License; or

    b) Requiring preservation of specified reasonable legal notices or
    author attributions in that material or in the Appropriate Legal
    Notices displayed by works containing it; or

    c) Prohibiting misrepresentation of the origin of that material, or
    requiring that modified versions of such material be marked in
    reasonable ways as different from the original version; or

    d) Limiting the use for publicity purposes of names of licensors or
    authors of the material; or

    e) Declining to grant rights under trademark law for use of some
    trade names, trademarks, or service marks; or

    f) Requiring indemnification of licensors and authors of that
    material by anyone who conveys the material (or modified versions of
    it) with contractual assumptions of liability to the recipient, for
    any liability that these contractual assumptions directly impose on
    those licensors and authors.

  All other non-permissive additional terms are considered "further
restrictions" within the meaning of section 10.  If the Program as you
received it, or any part of it, contains a notice stating that it is
governed by this License along with a term that is a further
restriction, you may remove that term.  If a license document contains
a further restriction but permits relicensing or conveying under this
License, you may add to a covered work material governed by the terms
of that license document, provided that the further restriction does
not survive such relicensing or conveying.

  If you add terms to a covered work in accord with this section, you
must place, in the relevant source files, a statement of the
additional terms that apply to those files, or a notice indicating
where to find the applicable terms.

  Additional terms, permissive or non-permissive, may be stated in the
form of a separately written license, or stated as exceptions;
the above requirements apply either way.

  8. Termination.

  You may not propagate or modify a covered work except as expressly
provided under this License.  Any attempt otherwise to propagate or
modify it is void, and will automatically terminate your rights under
this License (including any patent licenses granted under the third
paragraph of section 11).

  However, if you cease all violation of this License, then your
license from a particular copyright holder is reinstated (a)
provisionally, unless and until the copyright holder explicitly and
finally terminates your license, and (b) permanently, if the copyright
holder fails to notify you of the violation by some reasonable means
prior to 60 days after the cessation.

  Moreover, your license from a particular copyright holder is
reinstated permanently if the copyright holder notifies you of the
violation by some reasonable means, this is the first time you have
received notice of violation of this License (for any work) from that
copyright holder, and you cure the violation prior to 30 days after
your receipt of the notice.

  Termination of your rights under this section does not terminate the
licenses of parties who have received copies or rights from you under
this License.  If your rights have been terminated and not permanently
reinstated, you do not qualify to receive new licenses for the same
material under section 10.

  9. Acceptance Not Required for Having Copies.

  You are not required to accept this License in order to receive or
run a copy of the Program.  Ancillary propagation of a covered work
occurring solely as a consequence of using peer-to-peer transmission
to receive a copy likewise does not require acceptance.  However,
nothing other than this License grants you permission to propagate or
modify any covered work.  These actions infringe copyright if you do
not accept this License.  Therefore, by modifying or propagating a
covered work, you indicate your acceptance of this License to do so.

  10. Automatic Licensing of Downstream Recipients.

  Each time you convey a covered work, the recipient automatically
receives a license from the original licensors, to run, modify and
propagate that work, subject to this License.  You are not responsible
for enforcing compliance by third parties with this License.

  An "entity transaction" is a transaction transferring control of an
organization, or substantially all assets of one, or subdividing an
organization, or merging organizations.  If propagation of a covered
work results from an entity transaction, each party to that
transaction who receives a copy of the work also receives whatever
licenses to the work the party's predecessor in interest had or could
give under the previous paragraph, plus a right to possession of the
Corresponding Source of the work from the predecessor in interest, if
the predecessor has it or can get it with reasonable efforts.

  You may not impose any further restrictions on the exercise of the
rights granted or affirmed under this License.  For example, you may
not impose a license fee, royalty, or other charge for exercise of
rights granted under this License, and you may not initiate litigation
(including a cross-claim or counterclaim in a lawsuit) alleging that
any patent claim is infringed by making, using, selling, offering for
sale, or importing the Program or any portion of it.

  11. Patents.

  A "contributor" is a copyright holder who authorizes use under this
License of the Program or a work on which the Program is based.  The
work thus licensed is called the contributor's "contributor version".

  A contributor's "essential patent claims" are all patent claims
owned or controlled by the contributor, whether already acquired or
hereafter acquired, that would be infringed by some manner, permitted
by this License, of making, using, or selling its contributor version,
but do not include claims that would be infringed only as a
consequence of further modification of the contributor version.  For
purposes of this definition, "control" includes the right to grant
patent sublicenses in a manner consistent with the requirements of
this License.

  Each contributor grants you a non-exclusive, worldwide, royalty-free
patent license under the contributor's essential patent claims, to
make, use, sell, offer for sale, import and otherwise run, modify and
propagate the contents of its contributor version.

  In the following three paragraphs, a "patent license" is any express
agreement or commitment, however denominated, not to enforce a patent
(such as an express permission to practice a patent or covenant not to
sue for patent infringement).  To "grant" such a patent license to a
party means to make such an agreement or commitment not to enforce a
patent against the party.

  If you convey a covered work, knowingly relying on a patent license,
and the Corresponding Source of the work is not available for anyone
to copy, free of charge and under the terms of this License, through a
publicly available network server or other readily accessible means,
then you must either (1) cause the Corresponding Source to be so
available, or (2) arrange to deprive yourself of the benefit of the
patent license for this particular work, or (3) arrange, in a manner
consistent with the requirements of this License, to extend the patent
license to downstream recipients.  "Knowingly relying" means you have
actual knowledge that, but for the patent license, your conveying the
covered work in a country, or your recipient's use of the covered work
in a country, would infringe one or more identifiable patents in that
country that you have reason to believe are valid.

  If, pursuant to or in connection with a single transaction or
arrangement, you convey, or propagate by procuring conveyance of, a
covered work, and grant a patent license to some of the parties
receiving the covered work authorizing them to use, propagate, modify
or convey a specific copy of the covered work, then the patent license
you grant is automatically extended to all recipients of the covered
work and works based on it.

  A patent license is "discriminatory" if it does not include within
the scope of its coverage, prohibits the exercise of, or is
conditioned on the non-exercise of one or more of the rights that are
specifically granted under this License.  You may not convey a covered
work if you are a party to an arrangement with a third party that is
in the business of distributing software, under which you make payment
to the third party based on the extent of your activity of conveying
the work, and under which the third party grants, to any of the
parties who would receive the covered work from you, a discriminatory
patent license (a) in connection with copies of the covered work
conveyed by you (or copies made from those copies), or (b) primarily
for and in connection with specific products or compilations that
contain the covered work, unless you entered into that arrangement,
or that patent license was granted, prior to 28 March 2007.

  Nothing in this License shall be construed as excluding or limiting
any implied license or other defenses to infringement that may
otherwise be available to you under applicable patent law.

  12. No Surrender of Others' Freedom.

  If conditions are imposed on you (whether by court order, agreement or
otherwise) that contradict the conditions of this License, they do not
excuse you from the conditions of this License.  If you cannot convey a
covered work so as to satisfy simultaneously your obligations under this
License and any other pertinent obligations, then as a consequence you may
not convey it at all.  For example, if you agree to terms that obligate you
to collect a royalty for further conveying from those to whom you convey
the Program, the only way you could satisfy both those terms and this
License would be to refrain entirely from conveying the Program.

  13. Use with the GNU Affero General Public License.

  Notwithstanding any other provision of this License, you have
permission to link or combine any covered work with a work licensed
under version 3 of the GNU Affero General Public License into a single
combined work, and to convey the resulting work.  The terms of this
License will continue to apply to the part which is the covered work,
but the special requirements of the GNU Affero General Public License,
section 13, concerning interaction through a network will apply to the
combination as such.

  14. Revised Versions of this License.

  The Free Software Foundation may publish revised and/or new versions of
the GNU General Public License from time to time.  Such new versions will
be similar in spirit to the present version, but may differ in detail to
address new problems or concerns.

  Each version is given a distinguishing version number.  If the
Program specifies that a certain numbered version of the GNU General
Public License "or any later version" applies to it, you have the
option of following the terms and conditions either of that numbered
version or of any later version published by the Free Software
Foundation.  If the Program does not specify a version number of the
GNU General Public License, you may choose any version ever published
by the Free Software Foundation.

  If the Program specifies that a proxy can decide which future
versions of the GNU General Public License can be used, that proxy's
public statement of acceptance of a version permanently authorizes you
to choose that version for the Program.

  Later license versions may give you additional or different
permissions.  However, no additional obligations are imposed on any
author or copyright holder as a result of your choosing to follow a
later version.

  15. Disclaimer of Warranty.

  THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY
APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT
HOLDERS AND/OR OTHER PARTIES PROVIDE THE PROGRAM "AS IS" WITHOUT WARRANTY
OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
ALL NECESSARY SERVICING, REPAIR OR CORRECTION.

  16. Limitation of Liability.

  IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS
THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY
GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD
PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),
EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF
SUCH DAMAGES.

  17. Interpretation of Sections 15 and 16.

  If the disclaimer of warranty and limitation of liability provided
above cannot be given local legal effect according to their terms,
reviewing courts shall apply local law that most closely approximates
an absolute waiver of all civil liability in connection with the
Program, unless a warranty or assumption of liability accompanies a
copy of the Program in return for a fee.

                     END OF TERMS AND CONDITIONS

            How to Apply These Terms to Your New Programs

  If you develop a new program, and you want it to be of the greatest
possible use to the public, the best way to achieve this is to make it
free software which everyone can redistribute and change under these terms.

  To do so, attach the following notices to the program.  It is safest
to attach them to the start of each source file to most effectively
state the exclusion of warranty; and each file should have at least
the "copyright" line and a pointer to where the full notice is found.

    <one line to give the program's name and a brief idea of what it does.>
    Copyright (C) <year>  <name of author>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

Also add information on how to contact you by electronic and paper mail.

  If the program does terminal interaction, make it output a short
notice like this when it starts in an interactive mode:

    <program>  Copyright (C) <year>  <name of author>
    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
    This is free software, and you are welcome to redistribute it
    under certain conditions; type `show c' for details.

The hypothetical commands `show w' and `show c' should show the appropriate
parts of the General Public License.  Of course, your program's commands
might be different; for a GUI interface, you would use an "about box".

  You should also get your employer (if you work as a programmer) or school,
if any, to sign a "copyright disclaimer" for the program, if necessary.
For more information on this, and how to apply and follow the GNU GPL, see
<https://www.gnu.org/licenses/>.

  The GNU General Public License does not permit incorporating your program
into proprietary programs.  If your program is a subroutine library, you
may consider it more useful to permit linking proprietary applications with
the library.  If this is what you want to do, use the GNU Lesser General
Public License instead of this License.  But first, please read
<https://www.gnu.org/licenses/why-not-lgpl.html>.
```

### src/main/resources/META-INF/services/forestry.api.plugin.IForestryPlugin

```
thedarkcolour.gendustry.compat.forestry.GendustryForestryPlugin
```

### src/main/resources/pack.mcmeta

```
{
    "pack": {
        "description": "Gendustry: Community Edition Resources",
        "pack_format": 15
    }
}
```

### src/main/templates/META-INF/mods.toml

```toml
modLoader="javafml"
loaderVersion="${fmlVersionRange}"
issueTrackerURL="https://github.com/thedarkcolour/Gendustry/issues"

license="GNU GPLv3"

[[mods]]
	modId="gendustry"
	version="${version}"
	displayName="Gendustry"
	credits="blackdew, CrossVas, Spearkiller"
	authors="thedarkcolour"
	description='''
	Machines for manipulation of Forestry genetics.
	'''

[[dependencies.gendustry]]
	modId="forge"
	mandatory=true
	versionRange="${forgeVersionRange}"
	ordering="NONE"
	side="BOTH"

[[dependencies.gendustry]]
	modId="patchouli"
	mandatory=true
	versionRange="${patchouliVersionRange}"
	ordering="NONE"
	side="BOTH"

[[dependencies.gendustry]]
	modId="forestry"
	mandatory=true
	versionRange="${forestryVersionRange}"
	ordering="NONE"
	side="CLIENT"

[[dependencies.gendustry]]
	modId="jei"
	mandatory=false
	versionRange="${jeiVersionRange}"
	ordering="NONE"
	side="CLIENT"
```

## Skipped files

- `formatting-settings.jar` — binary file
- `gradle/wrapper/gradle-wrapper.jar` — binary file
- `logo.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/advanced_mutatron_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/advanced_mutatron_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/advanced_mutatron_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/dna_extractor_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/dna_extractor_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/dna_extractor_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/genetic_transposer_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/genetic_transposer_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/genetic_transposer_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/imprinter_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/imprinter_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/imprinter_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/industrial_apiary_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/industrial_apiary_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/industrial_apiary_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/liquid/liquid_dna_flowing.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/liquid/liquid_dna_still.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/liquid/mutagen_flowing.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/liquid/mutagen_still.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/liquid/protein_flowing.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/liquid/protein_still.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/mutagen_producer_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/mutagen_producer_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/mutagen_producer_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/mutatron_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/mutatron_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/mutatron_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/protein_liquefier_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/protein_liquefier_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/protein_liquefier_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/replicator_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/replicator_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/replicator_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/sampler_bottom.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/sampler_side.png` — binary file
- `src/main/resources/assets/gendustry/textures/block/sampler_top.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/incompatible_species.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_blank.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_dna.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_labware.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_mates.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_mutagen.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_mutations.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_protein.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_samples.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_selection.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_source.png` — binary file
- `src/main/resources/assets/gendustry/textures/forestry/atlas/gui/errors/no_template.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/advanced_mutatron.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/apiary.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/extractor.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/imprinter.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/liquifier.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/mutatron.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/mutatron_advanced.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/processor.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/replicator.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/sampler.png` — binary file
- `src/main/resources/assets/gendustry/textures/gui/transposer.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/activity_simulator_elite_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/automation_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/blank_gene_sample.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/blank_genetic_template.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/bucket_liquid_dna.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/bucket_mutagen.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/bucket_protein.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/climate_control_module.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/cooler_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/dryer_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/elite_upgrade_frame.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/environmental_processor.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/fertility_elite_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/gene_sample.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/genetic_template.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/genetics_processor.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/heater_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/humidifier_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/immutable_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/labware.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/lifespan_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/lighting_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/mutation_elite_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/nether_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/pollen_kit.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/pollination_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/power_module.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/productivity_elite_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/productivity_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/receptacle.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/scrubber_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/sieve_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/sky_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/stabilizer_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/territory_elite_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/territory_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/upgrade_frame.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/weatherproof_upgrade.png` — binary file
- `src/main/resources/assets/gendustry/textures/item/youth_elite_upgrade.png` — binary file
