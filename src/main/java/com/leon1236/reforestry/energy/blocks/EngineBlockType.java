package com.leon1236.reforestry.energy.blocks;

import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;
import com.leon1236.reforestry.energy.features.EnergyTiles;
import com.leon1236.reforestry.energy.tiles.EngineBlockEntity;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;

public enum EngineBlockType implements IBlockType {
	PEAT(createEngineProperties(EnergyTiles.PEAT_ENGINE, "peat")),
	BIOGAS(createEngineProperties(EnergyTiles.BIOGAS_ENGINE, "biogas")),
	CLOCKWORK(createEngineProperties(EnergyTiles.CLOCKWORK_ENGINE, "clockwork"));

	public static final EngineBlockType[] VALUES = values();

	private final IMachineProperties<?> machineProperties;

	EngineBlockType(IMachineProperties<?> machineProperties) {
		this.machineProperties = machineProperties;
	}

	private static <T extends EngineBlockEntity> IMachineProperties<T> createEngineProperties(
			FeatureBlockEntityType<T> teClass,
			String name
	) {
		return new MachineProperties.Builder<>(teClass, name)
				.setClientTicker(EngineBlockEntity::clientTick)
				.setServerTicker(EngineBlockEntity::serverTick)
				.create();
	}

	@Override
	public IMachineProperties<?> getMachineProperties() {
		return this.machineProperties;
	}

	@Override
	public String getSerializedName() {
		return getMachineProperties().getSerializedName();
	}
}
