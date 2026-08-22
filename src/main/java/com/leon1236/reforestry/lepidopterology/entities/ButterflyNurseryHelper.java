package com.leon1236.reforestry.lepidopterology.entities;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.api.lepidopterology.IButterflyNursery;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;

public final class ButterflyNurseryHelper {
	private ButterflyNurseryHelper() {
	}

	@Nullable
	public static IButterflyNursery getNursery(LevelAccessor level, BlockPos pos) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof IButterflyNursery nursery) {
			return nursery;
		}
		return null;
	}

	public static boolean canCreateNursery(LevelAccessor world, BlockPos pos) {
		return world.getBlockEntity(pos) instanceof TileLeaves leaves && leaves.getGenome() != null;
	}

	@Nullable
	public static IButterflyNursery getOrCreateNursery(LevelAccessor level, BlockPos pos, boolean create) {
		IButterflyNursery nursery = getNursery(level, pos);
		if (nursery != null) {
			return nursery;
		}
		if (create && canCreateNursery(level, pos)) {
			return getNursery(level, pos);
		}
		return null;
	}

	public static boolean canNurse(IButterfly butterfly, LevelAccessor level, BlockPos pos) {
		IButterflyNursery nursery = getNursery(level, pos);
		return nursery != null && nursery.canNurse(butterfly);
	}
}
