package com.leon1236.reforestry.cultivation.blocks;

import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;
import com.leon1236.reforestry.cultivation.features.CultivationTiles;
import com.leon1236.reforestry.cultivation.tiles.TilePlanter;

public enum BlockTypePlanter implements IBlockType {
	ARBORETUM(new MachineProperties.Builder<>(CultivationTiles.ARBORETUM, "arboretum")
			.setServerTicker(TilePlanter::serverTick)
			.create()),
	FARM_CROPS(new MachineProperties.Builder<>(CultivationTiles.CROPS, "farm_crops")
			.setServerTicker(TilePlanter::serverTick)
			.create()),
	FARM_MUSHROOM(new MachineProperties.Builder<>(CultivationTiles.MUSHROOM, "farm_mushroom")
			.setServerTicker(TilePlanter::serverTick)
			.create()),
	FARM_GOURD(new MachineProperties.Builder<>(CultivationTiles.GOURD, "farm_gourd")
			.setServerTicker(TilePlanter::serverTick)
			.create()),
	FARM_NETHER(new MachineProperties.Builder<>(CultivationTiles.NETHER, "farm_nether")
			.setServerTicker(TilePlanter::serverTick)
			.create()),
	FARM_ENDER(new MachineProperties.Builder<>(CultivationTiles.ENDER, "farm_ender")
			.setServerTicker(TilePlanter::serverTick)
			.create()),
	PEAT_POG(new MachineProperties.Builder<>(CultivationTiles.BOG, "peat_bog")
			.setServerTicker(TilePlanter::serverTick)
			.create());

	private final IMachineProperties<?> machineProperties;

	BlockTypePlanter(IMachineProperties<?> machineProperties) {
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
