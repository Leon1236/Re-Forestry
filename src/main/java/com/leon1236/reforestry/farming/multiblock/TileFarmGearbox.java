package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.multiblock.IFarmComponent;
import com.leon1236.reforestry.api.multiblock.IFarmController;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.farming.features.FarmingTiles;

public class TileFarmGearbox extends TileFarm implements IFarmComponent.Active<MultiblockLogicFarm> {
	public static final long CAPACITY = 10000;
	public static final long MAX_INSERT = 200;

	private static final int WORK_CYCLES = 4;
	private static final int ENERGY_PER_OPERATION = WORK_CYCLES * 50;

	private final SimpleEnergyStorage energyStorage;

	private int activationDelay;
	private int previousDelays;
	private int workCounter;

	public TileFarmGearbox(BlockPos pos, BlockState state) {
		super(FarmingTiles.GEARBOX.type(), pos, state);
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
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.energyStorage.amount = Math.max(0L, Math.min(CAPACITY, input.getLongOr("Energy", 0L)));
		this.activationDelay = input.getIntOr("ActivationDelay", 0);
		this.previousDelays = input.getIntOr("PrevDelays", 0);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putLong("Energy", this.energyStorage.amount);
		output.putInt("ActivationDelay", this.activationDelay);
		output.putInt("PrevDelays", this.previousDelays);
	}

	@Override
	public void updateServer(int tickCount) {
		if (this.energyStorage.amount <= 0) {
			return;
		}

		if (this.activationDelay > 0) {
			this.activationDelay--;
			return;
		}

		if (this.workCounter < WORK_CYCLES
				&& EnergyHelper.consumeEnergyToDoWork(this.energyStorage, WORK_CYCLES, ENERGY_PER_OPERATION)) {
			this.workCounter++;
			setChanged();
		}

		if (this.workCounter >= WORK_CYCLES && tickCount % 5 == 0) {
			IFarmController farmController = getMultiblockLogic().getController();
			if (farmController.doWork()) {
				this.workCounter = 0;
				this.previousDelays = 0;
			} else {
				this.activationDelay = Math.min(10 * this.previousDelays, 120);
				this.previousDelays++;
			}
		}
	}

	@Override
	public void updateClient(int tickCount) {
	}
}
