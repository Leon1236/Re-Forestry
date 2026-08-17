package com.leon1236.reforestry.worktable.blocks;

import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;
import com.leon1236.reforestry.worktable.features.WorktableTiles;

public enum WorktableBlockType implements IBlockType {
	WORKTABLE(new MachineProperties.Builder<>(WorktableTiles.WORKTABLE, "worktable").create());

	private final IMachineProperties<?> machineProperties;

	WorktableBlockType(IMachineProperties<?> machineProperties) {
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
