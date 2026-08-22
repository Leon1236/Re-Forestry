package com.leon1236.reforestry.farming.multiblock;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.multiblock.IFarmComponent;
import com.leon1236.reforestry.farming.features.FarmingTiles;

public class TileFarmHatch extends TileFarm implements IFarmComponent.Active<MultiblockLogicFarm> {
	public TileFarmHatch(BlockPos pos, BlockState state) {
		super(FarmingTiles.HATCH.type(), pos, state);
	}

	@Override
	public boolean allowsAutomation() {
		return true;
	}

	@Override
	public void updateServer(int tickCount) {
		if (tickCount % 40 != 0) {
			return;
		}
		Level level = this.level;
		if (level == null) {
			return;
		}
		Container products = getMultiblockLogic().getController().getFarmInventory().getProductInventory();
		for (int slot = 0; slot < products.getContainerSize(); slot++) {
			ItemStack stack = products.getItem(slot);
			if (stack.isEmpty()) {
				continue;
			}
			if (exportStack(level, products, slot, stack)) {
				return;
			}
		}
	}

	private boolean exportStack(Level level, Container products, int slot, ItemStack stack) {
		ItemVariant variant = ItemVariant.of(stack);
		BlockPos hatchPos = getBlockPos();
		for (Direction side : Direction.values()) {
			BlockPos neighborPos = hatchPos.relative(side);
			if (neighborPos.getY() >= hatchPos.getY() || !level.isLoaded(neighborPos)) {
				continue;
			}
			BlockEntity neighbor = level.getBlockEntity(neighborPos);
			if (neighbor instanceof TileFarm) {
				continue;
			}
			Storage<ItemVariant> destination = ItemStorage.SIDED.find(level, neighborPos, side.getOpposite());
			if (destination == null) {
				continue;
			}
			try (Transaction transaction = Transaction.openOuter()) {
				long inserted = destination.insert(variant, stack.getCount(), transaction);
				if (inserted <= 0) {
					continue;
				}
				transaction.commit();
				products.removeItem(slot, (int) inserted);
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateClient(int tickCount) {
	}
}
