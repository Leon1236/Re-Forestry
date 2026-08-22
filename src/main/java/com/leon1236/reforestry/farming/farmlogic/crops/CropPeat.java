package com.leon1236.reforestry.farming.farmlogic.crops;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import com.leon1236.reforestry.core.features.CoreBlocks;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.utils.BlockUtil;

public class CropPeat extends Crop {
	public CropPeat(Level world, BlockPos position) {
		super(world, position);
	}

	@Override
	protected boolean isCrop(Level world, BlockPos pos) {
		return world.getBlockState(pos).is(CoreBlocks.PEAT.block());
	}

	@Override
	protected List<ItemStack> harvestBlock(Level level, BlockPos pos) {
		NonNullList<ItemStack> drops = NonNullList.create();
		drops.add(new ItemStack(CoreItems.PEAT.item()));

		BlockUtil.sendDestroyEffects(level, pos, level.getBlockState(pos));

		level.setBlock(pos, Blocks.DIRT.defaultBlockState(), Block.UPDATE_CLIENTS);
		return drops;
	}
}
