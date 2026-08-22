package com.leon1236.reforestry.farming.farmlogic.crops;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.utils.BlockUtil;

public class CropChorusFlower extends Crop {
	private static final BlockState BLOCK_STATE = Blocks.CHORUS_FLOWER.defaultBlockState();

	public CropChorusFlower(Level world, BlockPos position) {
		super(world, position);
	}

	@Override
	protected boolean isCrop(Level world, BlockPos pos) {
		return world.getBlockState(pos).getBlock() == Blocks.CHORUS_FLOWER;
	}

	@Override
	protected List<ItemStack> harvestBlock(Level level, BlockPos pos) {
		BlockUtil.sendDestroyEffects(level, pos, BLOCK_STATE);
		level.removeBlock(pos, false);
		return List.of(new ItemStack(Blocks.CHORUS_FLOWER));
	}
}
