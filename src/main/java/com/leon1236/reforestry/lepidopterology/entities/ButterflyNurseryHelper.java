package com.leon1236.reforestry.lepidopterology.entities;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.api.lepidopterology.IButterflyNursery;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;

public final class ButterflyNurseryHelper {
	private ButterflyNurseryHelper() {
	}

	@Nullable
	public static IButterflyNursery getNursery(Level level, BlockPos pos) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof IButterflyNursery nursery) {
			return nursery;
		}
		return null;
	}

	@Nullable
	public static IButterflyNursery getOrCreateNursery(Level level, BlockPos pos, boolean create) {
		return getNursery(level, pos);
	}

	public static boolean canNurse(IButterfly butterfly, Level level, BlockPos pos) {
		IButterflyNursery nursery = getNursery(level, pos);
		return nursery != null && nursery.canNurse(butterfly);
	}
}
