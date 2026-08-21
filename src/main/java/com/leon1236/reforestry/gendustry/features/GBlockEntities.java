package com.leon1236.reforestry.gendustry.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.blockentity.AbstractMutatronBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.AdvancedMutatronBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.DnaExtractorBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.GeneticTransposerBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ImprinterBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.IndustrialApiaryBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.MutagenProducerBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.MutatronBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ProteinLiquefierBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ReplicatorBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.SamplerBlockEntity;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GBlockEntities {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureBlockEntityType<IndustrialApiaryBlockEntity> INDUSTRIAL_APIARY =
			REGISTRY.blockEntityType("industrial_apiary", IndustrialApiaryBlockEntity::new);

	public static final FeatureBlockEntityType<MutagenProducerBlockEntity> MUTAGEN_PRODUCER =
			REGISTRY.blockEntityType("mutagen_producer", MutagenProducerBlockEntity::new);

	public static final FeatureBlockEntityType<DnaExtractorBlockEntity> DNA_EXTRACTOR =
			REGISTRY.blockEntityType("dna_extractor", DnaExtractorBlockEntity::new);

	public static final FeatureBlockEntityType<ProteinLiquefierBlockEntity> PROTEIN_LIQUEFIER =
			REGISTRY.blockEntityType("protein_liquefier", ProteinLiquefierBlockEntity::new);

	public static final FeatureBlockEntityType<SamplerBlockEntity> SAMPLER =
			REGISTRY.blockEntityType("sampler", SamplerBlockEntity::new);

	public static final FeatureBlockEntityType<MutatronBlockEntity> MUTATRON =
			REGISTRY.blockEntityType("mutatron", MutatronBlockEntity::new);

	public static final FeatureBlockEntityType<AdvancedMutatronBlockEntity> ADVANCED_MUTATRON =
			REGISTRY.blockEntityType("advanced_mutatron", AdvancedMutatronBlockEntity::new);

	public static final FeatureBlockEntityType<ImprinterBlockEntity> IMPRINTER =
			REGISTRY.blockEntityType("imprinter", ImprinterBlockEntity::new);

	public static final FeatureBlockEntityType<GeneticTransposerBlockEntity> GENETIC_TRANSPOSER =
			REGISTRY.blockEntityType("genetic_transposer", GeneticTransposerBlockEntity::new);

	public static final FeatureBlockEntityType<ReplicatorBlockEntity> REPLICATOR =
			REGISTRY.blockEntityType("replicator", ReplicatorBlockEntity::new);

	public static void init() {
		EnergyHelper.registerSided(INDUSTRIAL_APIARY.type());
		InventoryHelper.registerSided(INDUSTRIAL_APIARY.type());

		EnergyHelper.registerSided(MUTAGEN_PRODUCER.type());
		InventoryHelper.registerSided(MUTAGEN_PRODUCER.type());
		FluidHelper.registerSided(MUTAGEN_PRODUCER.type(), MutagenProducerBlockEntity::getTankManager);

		EnergyHelper.registerSided(DNA_EXTRACTOR.type());
		InventoryHelper.registerSided(DNA_EXTRACTOR.type());
		FluidHelper.registerSided(DNA_EXTRACTOR.type(), DnaExtractorBlockEntity::getTankManager);

		EnergyHelper.registerSided(PROTEIN_LIQUEFIER.type());
		InventoryHelper.registerSided(PROTEIN_LIQUEFIER.type());
		FluidHelper.registerSided(PROTEIN_LIQUEFIER.type(), ProteinLiquefierBlockEntity::getTankManager);

		EnergyHelper.registerSided(SAMPLER.type());
		InventoryHelper.registerSided(SAMPLER.type());

		EnergyHelper.registerSided(MUTATRON.type());
		InventoryHelper.registerSided(MUTATRON.type());
		FluidHelper.registerSided(MUTATRON.type(), AbstractMutatronBlockEntity::getTankManager);

		EnergyHelper.registerSided(ADVANCED_MUTATRON.type());
		InventoryHelper.registerSided(ADVANCED_MUTATRON.type());
		FluidHelper.registerSided(ADVANCED_MUTATRON.type(), AbstractMutatronBlockEntity::getTankManager);

		EnergyHelper.registerSided(IMPRINTER.type());
		InventoryHelper.registerSided(IMPRINTER.type());

		EnergyHelper.registerSided(GENETIC_TRANSPOSER.type());
		InventoryHelper.registerSided(GENETIC_TRANSPOSER.type());

		EnergyHelper.registerSided(REPLICATOR.type());
		InventoryHelper.registerSided(REPLICATOR.type());
		FluidHelper.registerSided(REPLICATOR.type(), ReplicatorBlockEntity::getTankManager);
	}
}
