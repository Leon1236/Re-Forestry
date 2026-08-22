package com.leon1236.reforestry.climatology.blocks;

import com.leon1236.reforestry.climatology.features.ClimatologyTiles;
import com.leon1236.reforestry.climatology.tiles.TileHabitatFormer;
import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;

public enum ClimatologyBlockType implements IBlockType {
	HABITAT_FORMER(new MachineProperties.Builder<>(ClimatologyTiles.HABITAT_FORMER, "habitat_former")
			.setServerTicker(TileHabitatFormer::serverTick)
			.create());

	private final IMachineProperties<?> machineProperties;

	ClimatologyBlockType(IMachineProperties<?> machineProperties) {
		this.machineProperties = machineProperties;
	}

	@Override
	public IMachineProperties<?> getMachineProperties() {
		return this.machineProperties;
	}

	@Override
	public String getSerializedName() {
		return this.machineProperties.getSerializedName();
	}
}
