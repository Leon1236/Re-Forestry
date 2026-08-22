package com.leon1236.reforestry.extra_bees.multiblock;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.apiculture.multiblock.MultiblockLogicAlveary;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesTiles;

public class TileAlvearyTransmission extends TileExtraBeeAlveary
		implements IAlvearyComponent.Active<MultiblockLogicAlveary> {
	public static final long CAPACITY = 1000;
	public static final long MAX_INSERT = 2000;
	public static final long MAX_EXTRACT = 2000;
	private static final int MAX_OUTPUT = 500;

	private final SimpleEnergyStorage energyStorage;

	public TileAlvearyTransmission(BlockPos pos, BlockState state) {
		super(ExtraBeesTiles.ALVEARY_TRANSMISSION.type(), BlockExtraBeeAlvearyType.TRANSMISSION, pos, state);
		this.energyStorage = new SimpleEnergyStorage(CAPACITY, MAX_INSERT, MAX_EXTRACT) {
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
	public void updateServer(int tickCount) {
		if (this.level == null || this.energyStorage.amount <= 0) {
			return;
		}

		List<EnergyStorage> handlers = new ArrayList<>();
		for (IMultiblockComponent component : getMultiblockLogic().getController().getComponents()) {
			if (!(component instanceof BlockEntity tile) || tile == this || this.level != tile.getLevel()) {
				continue;
			}
			EnergyStorage storage = EnergyStorage.SIDED.find(this.level, tile.getBlockPos(), Direction.NORTH);
			if (storage != null && storage.supportsInsertion()) {
				handlers.add(storage);
			}
		}
		if (handlers.isEmpty()) {
			return;
		}

		long output = this.energyStorage.amount / handlers.size();
		if (output > MAX_OUTPUT) {
			output = MAX_OUTPUT;
		}
		if (output < 1) {
			output = 1;
		}

		for (EnergyStorage handler : handlers) {
			try (Transaction transaction = Transaction.openOuter()) {
				long moved = EnergyStorageUtil.move(this.energyStorage, handler, output, transaction);
				if (moved > 0) {
					transaction.commit();
				}
			}
			if (this.energyStorage.amount <= 0) {
				return;
			}
		}
	}

	@Override
	public void updateClient(int tickCount) {
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.energyStorage.amount = Math.min(CAPACITY, Math.max(0L, input.getLongOr("Energy", 0L)));
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putLong("Energy", this.energyStorage.amount);
	}
}
