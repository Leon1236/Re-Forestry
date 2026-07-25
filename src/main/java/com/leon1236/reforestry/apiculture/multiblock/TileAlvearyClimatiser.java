package com.leon1236.reforestry.apiculture.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.climate.IClimateControlled;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.apiculture.blocks.BlockAlveary;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;
import com.leon1236.reforestry.core.tiles.IActivatable;

public abstract class TileAlvearyClimatiser extends TileAlveary
		implements IActivatable, IAlvearyComponent.Climatiser<MultiblockLogicAlveary> {
	public static final long CAPACITY = 2000;
	public static final long MAX_INSERT = 1000;

	private static final int FE_PER_OPERATION = 50;
	private static final int MAX_WORKING_TIME = 20;

	private final SimpleEnergyStorage energyStorage;
	private final byte temperatureSteps;

	private int workingTime;

	protected TileAlvearyClimatiser(BlockAlvearyType type, BlockPos pos, BlockState state, byte temperatureSteps) {
		super(type, pos, state);
		this.temperatureSteps = temperatureSteps;
		this.energyStorage = new SimpleEnergyStorage(CAPACITY, MAX_INSERT, 0) {
			@Override
			protected void onFinalCommit() {
				setChanged();
			}
		};
	}

	public SimpleEnergyStorage getEnergyStorage() {
		return this.energyStorage;
	}

	@Override
	public void changeClimate(int tickCount, IClimateControlled climateControlled) {
		if (this.workingTime < MAX_WORKING_TIME && this.energyStorage.amount >= FE_PER_OPERATION) {
			this.energyStorage.amount -= FE_PER_OPERATION;
			this.workingTime += FE_PER_OPERATION / 10;
			setChanged();
		}

		if (this.workingTime > 0) {
			this.workingTime--;
			climateControlled.addTemperatureChange(this.temperatureSteps);
		}

		setActive(this.workingTime > 0);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.energyStorage.amount = input.getLongOr("Energy", 0L);
		this.workingTime = input.getIntOr("Heating", 0);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putLong("Energy", this.energyStorage.amount);
		output.putInt("Heating", this.workingTime);
	}

	@Override
	public boolean isActive() {
		return getBlockState().getValue(BlockAlveary.STATE) == BlockAlveary.State.ON;
	}

	@Override
	public void setActive(boolean active) {
		if (this.level != null && isActive() != active) {
			this.level.setBlockAndUpdate(getBlockPos(),
					getBlockState().setValue(BlockAlveary.STATE, active ? BlockAlveary.State.ON : BlockAlveary.State.OFF));
		}
	}
}
