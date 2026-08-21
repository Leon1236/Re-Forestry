package com.leon1236.reforestry.farming.farmlogic;

import java.util.ArrayDeque;
import java.util.Collection;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.core.features.CoreBlocks;
import com.leon1236.reforestry.farming.farmlogic.crops.CropPeat;

public class FarmLogicPeat extends FarmLogicWatered {
	public FarmLogicPeat(IFarmType properties, boolean isManual) {
		super(properties, isManual);
	}

	@Override
	public Collection<ICrop> harvest(Level level, IFarmHousing housing, Direction direction, int extent, BlockPos pos) {
		ArrayDeque<ICrop> crops = new ArrayDeque<>();
		for (int i = 0; i < extent; i++) {
			BlockPos position = translateWithOffset(pos, direction, i);
			if (!level.isLoaded(position)) {
				return crops;
			}
			BlockState blockState = level.getBlockState(position);
			if (blockState.is(CoreBlocks.PEAT.block())) {
				crops.addFirst(new CropPeat(level, position));
			}
		}
		return crops;
	}
}
