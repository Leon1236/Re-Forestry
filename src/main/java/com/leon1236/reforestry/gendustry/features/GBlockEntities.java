package com.leon1236.reforestry.gendustry.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.blockentity.DnaExtractorBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.MutagenProducerBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ProteinLiquefierBlockEntity;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GBlockEntities {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureBlockEntityType<MutagenProducerBlockEntity> MUTAGEN_PRODUCER =
			REGISTRY.blockEntityType("mutagen_producer", MutagenProducerBlockEntity::new);

	public static final FeatureBlockEntityType<DnaExtractorBlockEntity> DNA_EXTRACTOR =
			REGISTRY.blockEntityType("dna_extractor", DnaExtractorBlockEntity::new);

	public static final FeatureBlockEntityType<ProteinLiquefierBlockEntity> PROTEIN_LIQUEFIER =
			REGISTRY.blockEntityType("protein_liquefier", ProteinLiquefierBlockEntity::new);

	public static void init() {
		EnergyHelper.registerSided(MUTAGEN_PRODUCER.type());
		InventoryHelper.registerSided(MUTAGEN_PRODUCER.type());
		FluidHelper.registerSided(MUTAGEN_PRODUCER.type(), MutagenProducerBlockEntity::getTankManager);

		EnergyHelper.registerSided(DNA_EXTRACTOR.type());
		InventoryHelper.registerSided(DNA_EXTRACTOR.type());
		FluidHelper.registerSided(DNA_EXTRACTOR.type(), DnaExtractorBlockEntity::getTankManager);

		EnergyHelper.registerSided(PROTEIN_LIQUEFIER.type());
		InventoryHelper.registerSided(PROTEIN_LIQUEFIER.type());
		FluidHelper.registerSided(PROTEIN_LIQUEFIER.type(), ProteinLiquefierBlockEntity::getTankManager);
	}
}
