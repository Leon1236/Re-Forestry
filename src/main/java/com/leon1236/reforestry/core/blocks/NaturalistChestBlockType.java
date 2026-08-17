package com.leon1236.reforestry.core.blocks;

import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.tiles.TileNaturalistChest;

public enum NaturalistChestBlockType implements IBlockType {
	BEE_CHEST("bee_chest", new MachineProperties.Builder<>(CoreTiles.BEE_CHEST, "bee_chest")
			.create()),
	TREE_CHEST("tree_chest", new MachineProperties.Builder<>(CoreTiles.TREE_CHEST, "tree_chest")
			.create()),
	BUTTERFLY_CHEST("butterfly_chest", new MachineProperties.Builder<>(CoreTiles.BUTTERFLY_CHEST, "butterfly_chest")
			.create());

	private final String serializedName;
	private final IMachineProperties<?> machineProperties;

	NaturalistChestBlockType(String serializedName, IMachineProperties<? extends TileNaturalistChest> machineProperties) {
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
