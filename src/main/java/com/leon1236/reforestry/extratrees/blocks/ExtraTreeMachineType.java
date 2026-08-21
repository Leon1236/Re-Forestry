package com.leon1236.reforestry.extratrees.blocks;

import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;
import com.leon1236.reforestry.extratrees.features.ExtraTreesTiles;
import com.leon1236.reforestry.extratrees.tiles.TileBrewery;
import com.leon1236.reforestry.extratrees.tiles.TileDistillery;
import com.leon1236.reforestry.extratrees.tiles.TileLumbermill;
import com.leon1236.reforestry.extratrees.tiles.TilePress;

public enum ExtraTreeMachineType implements IBlockType {
	LUMBERMILL("lumbermill", new MachineProperties.Builder<>(ExtraTreesTiles.LUMBERMILL, "lumbermill")
			.setServerTicker(TileLumbermill::serverTick)
			.create()),
	PRESS("press", new MachineProperties.Builder<>(ExtraTreesTiles.PRESS, "press")
			.setServerTicker(TilePress::serverTick)
			.create()),
	BREWERY("brewery", new MachineProperties.Builder<>(ExtraTreesTiles.BREWERY, "brewery")
			.setServerTicker(TileBrewery::serverTick)
			.create()),
	DISTILLERY("distillery", new MachineProperties.Builder<>(ExtraTreesTiles.DISTILLERY, "distillery")
			.setServerTicker(TileDistillery::serverTick)
			.create());

	public static final ExtraTreeMachineType[] VALUES = values();

	private final String serializedName;
	private final IMachineProperties<?> machineProperties;

	ExtraTreeMachineType(String serializedName, IMachineProperties<?> machineProperties) {
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
