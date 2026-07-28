package com.leon1236.reforestry.core.fluids;

import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import com.leon1236.reforestry.api.core.ISidedAccess;
import com.leon1236.reforestry.core.access.AccessStorageHelper;

public final class FluidHelper {
	private FluidHelper() {
	}

	public static <T extends BlockEntity & ISidedAccess> void registerSided(
			BlockEntityType<T> type,
			Function<T, Storage<FluidVariant>> storage
	) {
		FluidStorage.SIDED.registerForBlockEntity(
				(tile, direction) -> AccessStorageHelper.wrap(tile, direction, storage.apply(tile)),
				type
		);
	}

	public static boolean interactWithTank(Player player, InteractionHand hand, Level level, BlockPos pos, @Nullable Direction side) {
		Storage<FluidVariant> tank = FluidStorage.SIDED.find(level, pos, side);
		if (tank == null && side != null) {
			tank = FluidStorage.SIDED.find(level, pos, null);
		}
		if (tank == null) {
			return false;
		}

		ContainerItemContext context = ContainerItemContext.forPlayerInteraction(player, hand);
		Storage<FluidVariant> itemStorage = context.find(FluidStorage.ITEM);
		if (itemStorage == null) {
			return false;
		}

		if (moveBucket(itemStorage, tank)) {
			return true;
		}
		return moveBucket(tank, itemStorage);
	}

	private static boolean moveBucket(Storage<FluidVariant> from, Storage<FluidVariant> to) {
		try (Transaction transaction = Transaction.openOuter()) {
			long moved = StorageUtil.move(from, to, variant -> true, FluidConstants.BUCKET, transaction);
			if (moved > 0) {
				transaction.commit();
				return true;
			}
		}
		return false;
	}
}
