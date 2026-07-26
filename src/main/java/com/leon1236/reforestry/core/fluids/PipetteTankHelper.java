package com.leon1236.reforestry.core.fluids;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import com.leon1236.reforestry.api.core.IToolPipette;

public final class PipetteTankHelper {
	private PipetteTankHelper() {
	}

	public static void handlePipetteClick(SingleFluidStorage tank, ServerPlayer player, AbstractContainerMenu menu) {
		ItemStack carried = menu.getCarried();
		if (!(carried.getItem() instanceof IToolPipette pipette)) {
			return;
		}

		ContainerItemContext context = ContainerItemContext.ofPlayerCursor(player, menu);
		Storage<FluidVariant> itemStorage = context.find(FluidStorage.ITEM);
		if (itemStorage == null) {
			return;
		}

		if (pipette.canPipette(carried) && tank.getAmount() > 0) {
			try (Transaction transaction = Transaction.openOuter()) {
				long moved = StorageUtil.move(tank, itemStorage, variant -> true, FluidConstants.BUCKET, transaction);
				if (moved > 0) {
					transaction.commit();
					menu.broadcastChanges();
				}
			}
		} else {
			try (Transaction transaction = Transaction.openOuter()) {
				long moved = StorageUtil.move(itemStorage, tank, variant -> true, FluidConstants.BUCKET, transaction);
				if (moved > 0) {
					transaction.commit();
					menu.broadcastChanges();
				}
			}
		}
	}
}
