package com.leon1236.reforestry.gendustry.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.tiles.TilePowered;

public abstract class PoweredTankBlockEntity extends TilePowered {
	public static final long ENERGY_CAPACITY = 1000000L;
	public static final long ENERGY_MAX_RECEIVE = 10000L;

	protected MultiFluidTank tankManager;

	protected PoweredTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state, ENERGY_CAPACITY, ENERGY_MAX_RECEIVE);
	}

	public MultiFluidTank getTankManager() {
		return this.tankManager;
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		if (this.tankManager != null) {
			this.tankManager.writeValue(output.child("Tanks"));
		}
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		if (this.tankManager != null) {
			this.tankManager.readValue(input.childOrEmpty("Tanks"));
		}
	}
}
