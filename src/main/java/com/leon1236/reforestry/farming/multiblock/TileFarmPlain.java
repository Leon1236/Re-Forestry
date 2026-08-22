package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.multiblock.IMultiblockController;
import com.leon1236.reforestry.farming.blocks.FarmBlock;
import com.leon1236.reforestry.farming.features.FarmingTiles;

public class TileFarmPlain extends TileFarm {
	public TileFarmPlain(BlockPos pos, BlockState state) {
		super(FarmingTiles.PLAIN.type(), pos, state);
	}

	@Override
	public void onMachineAssembled(IMultiblockController multiblockController, BlockPos minCoord, BlockPos maxCoord) {
		super.onMachineAssembled(multiblockController, minCoord, maxCoord);
		if (this.level == null) {
			return;
		}
		int bandY = maxCoord.getY() - 1;
		BlockState state = getBlockState();
		if (getBlockPos().getY() == bandY && state.hasProperty(FarmBlock.BAND)) {
			this.level.setBlock(getBlockPos(), state.setValue(FarmBlock.BAND, true), Block.UPDATE_CLIENTS);
		}
	}

	@Override
	public void onMachineBroken() {
		super.onMachineBroken();
		if (this.level == null) {
			return;
		}
		BlockState state = getBlockState();
		if (state.hasProperty(FarmBlock.BAND)) {
			this.level.setBlock(getBlockPos(), state.setValue(FarmBlock.BAND, false), Block.UPDATE_CLIENTS);
		}
	}
}
