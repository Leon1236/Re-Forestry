package com.leon1236.reforestry.gendustry.block;

import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;
import com.leon1236.reforestry.gendustry.blockentity.AdvancedMutatronBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.DnaExtractorBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.GeneticTransposerBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ImprinterBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.MutagenProducerBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.MutatronBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ProteinLiquefierBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ReplicatorBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.SamplerBlockEntity;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;

public enum GendustryMachineType implements IBlockType {
	MUTAGEN_PRODUCER("mutagen_producer", new MachineProperties.Builder<>(GBlockEntities.MUTAGEN_PRODUCER, "mutagen_producer")
			.setServerTicker(MutagenProducerBlockEntity::serverTick)
			.create()),
	DNA_EXTRACTOR("dna_extractor", new MachineProperties.Builder<>(GBlockEntities.DNA_EXTRACTOR, "dna_extractor")
			.setServerTicker(DnaExtractorBlockEntity::serverTick)
			.create()),
	PROTEIN_LIQUEFIER("protein_liquefier", new MachineProperties.Builder<>(GBlockEntities.PROTEIN_LIQUEFIER, "protein_liquefier")
			.setServerTicker(ProteinLiquefierBlockEntity::serverTick)
			.create()),
	SAMPLER("sampler", new MachineProperties.Builder<>(GBlockEntities.SAMPLER, "sampler")
			.setServerTicker(SamplerBlockEntity::serverTick)
			.create()),
	MUTATRON("mutatron", new MachineProperties.Builder<>(GBlockEntities.MUTATRON, "mutatron")
			.setServerTicker(MutatronBlockEntity::serverTick)
			.create()),
	ADVANCED_MUTATRON("advanced_mutatron", new MachineProperties.Builder<>(GBlockEntities.ADVANCED_MUTATRON, "advanced_mutatron")
			.setServerTicker(AdvancedMutatronBlockEntity::serverTick)
			.create()),
	IMPRINTER("imprinter", new MachineProperties.Builder<>(GBlockEntities.IMPRINTER, "imprinter")
			.setServerTicker(ImprinterBlockEntity::serverTick)
			.create()),
	GENETIC_TRANSPOSER("genetic_transposer", new MachineProperties.Builder<>(GBlockEntities.GENETIC_TRANSPOSER, "genetic_transposer")
			.setServerTicker(GeneticTransposerBlockEntity::serverTick)
			.create()),
	REPLICATOR("replicator", new MachineProperties.Builder<>(GBlockEntities.REPLICATOR, "replicator")
			.setServerTicker(ReplicatorBlockEntity::serverTick)
			.create());

	private final String serializedName;
	private final IMachineProperties<?> machineProperties;

	GendustryMachineType(String serializedName, IMachineProperties<?> machineProperties) {
		this.serializedName = serializedName;
		this.machineProperties = machineProperties;
	}

	@Override
	public IMachineProperties<?> getMachineProperties() {
		return this.machineProperties;
	}

	@Override
	public String getSerializedName() {
		return this.serializedName;
	}
}
