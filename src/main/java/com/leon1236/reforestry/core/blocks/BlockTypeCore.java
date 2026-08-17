package com.leon1236.reforestry.core.blocks;

import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.tiles.TileAnalyzer;

public enum BlockTypeCore implements IBlockType {
	ANALYZER("analyzer", new MachineProperties.Builder<>(CoreTiles.ANALYZER, "analyzer")
			.setServerTicker(TileAnalyzer::serverTick)
			.create());

	private final String serializedName;
	private final IMachineProperties<?> machineProperties;

	BlockTypeCore(String serializedName, IMachineProperties<?> machineProperties) {
		this.serializedName = serializedName;
		this.machineProperties = machineProperties;
	}

	@Override
	public IMachineProperties<?> getMachineProperties() {
		return machineProperties;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
