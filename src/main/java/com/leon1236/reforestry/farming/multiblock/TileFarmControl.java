package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.IFarmListener;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.multiblock.IFarmComponent;
import com.leon1236.reforestry.farming.blocks.FarmBlock;
import com.leon1236.reforestry.farming.features.FarmingTiles;

public class TileFarmControl extends TileFarm implements IFarmComponent.Listener<MultiblockLogicFarm> {
	private final IFarmListener farmListener;

	public TileFarmControl(BlockPos pos, BlockState state) {
		super(FarmingTiles.CONTROL.type(), pos, state);
		this.farmListener = new ControlFarmListener(this);
	}

	@Override
	public IFarmListener getFarmListener() {
		return this.farmListener;
	}

	private static class ControlFarmListener implements IFarmListener {
		private final TileFarmControl tile;

		ControlFarmListener(TileFarmControl tile) {
			this.tile = tile;
		}

		@Override
		public boolean cancelTask(IFarmLogic logic, Direction direction) {
			Level world = this.tile.getLevel();
			if (world == null) {
				return false;
			}
			BlockPos pos = this.tile.getBlockPos();
			for (Direction facing : new Direction[]{Direction.UP, Direction.DOWN, direction}) {
				BlockPos neighborPos = pos.relative(facing);
				if (!world.isLoaded(neighborPos)) {
					continue;
				}
				BlockState blockState = world.getBlockState(neighborPos);
				if (!(blockState.getBlock() instanceof FarmBlock) && world.getSignal(pos, facing) > 0) {
					return true;
				}
			}
			return false;
		}
	}
}
