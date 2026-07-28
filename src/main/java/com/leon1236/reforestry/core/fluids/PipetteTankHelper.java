package com.leon1236.reforestry.core.fluids;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

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

	public static boolean canHandleClick(ItemStack carried) {
		return carried.getItem() instanceof IToolPipette || carried.is(Items.BUCKET);
	}

	public static void handlePipetteClick(SingleFluidStorage tank, ServerPlayer player, AbstractContainerMenu menu) {
		ItemStack carried = menu.getCarried();
		if (carried.getItem() instanceof IToolPipette pipette) {
			handlePipette(tank, player, menu, pipette, carried);
			return;
		}
		if (carried.is(Items.BUCKET)) {
			fillEmptyBucket(tank, player, menu);
		}
	}

	private static void handlePipette(
			SingleFluidStorage tank,
			ServerPlayer player,
			AbstractContainerMenu menu,
			IToolPipette pipette,
			ItemStack carried
	) {
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

	private static void fillEmptyBucket(SingleFluidStorage tank, ServerPlayer player, AbstractContainerMenu menu) {
		if (tank.getAmount() < FluidConstants.BUCKET || tank.isResourceBlank()) {
			return;
		}
		ContainerItemContext context = ContainerItemContext.ofPlayerCursor(player, menu);
		Storage<FluidVariant> itemStorage = context.find(FluidStorage.ITEM);
		if (itemStorage == null) {
			return;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			long moved = StorageUtil.move(tank, itemStorage, variant -> true, FluidConstants.BUCKET, transaction);
			if (moved == FluidConstants.BUCKET) {
				transaction.commit();
				menu.broadcastChanges();
			}
		}
	}
}
