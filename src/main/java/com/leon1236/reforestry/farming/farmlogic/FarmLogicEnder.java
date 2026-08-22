package com.leon1236.reforestry.farming.farmlogic;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.core.utils.BlockUtil;
import com.leon1236.reforestry.farming.farmlogic.crops.CropDestroy;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableChorus;

public class FarmLogicEnder extends FarmLogicHomogeneous {
	private static final Set<Direction> VALID_DIRECTIONS = ImmutableSet.of(Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);

	private final IFarmable chorusFarmable = FarmableChorus.INSTANCE;

	public FarmLogicEnder(IFarmType properties, boolean isManual) {
		super(properties, isManual);
	}

	@Override
	public List<ItemStack> collect(Level level, IFarmHousing farmHousing) {
		return collectEntityItems(level, farmHousing, true);
	}

	@Override
	public Collection<ICrop> harvest(Level level, IFarmHousing farmHousing, Direction direction, int extent, BlockPos pos) {
		BlockPos position = farmHousing.getValidPosition(direction, pos, extent, pos.above());
		Collection<ICrop> crops = harvestBlocks(level, position);
		farmHousing.increaseExtent(direction, pos, extent);

		return crops;
	}

	private Collection<ICrop> harvestBlocks(Level world, BlockPos position) {
		if (!world.isLoaded(position) || world.isEmptyBlock(position)) {
			return Collections.emptySet();
		}

		ICrop crop = getCrop(world, position);
		if (crop != null) {
			return Collections.singleton(crop);
		}

		ArrayDeque<ICrop> crops = new ArrayDeque<>();
		ArrayDeque<ICrop> plants = new ArrayDeque<>();
		harvestBlock(world, position, Direction.DOWN, plants, crops);
		if (!crops.isEmpty()) {
			return crops;
		}
		return plants;
	}

	private boolean harvestBlock(Level world, BlockPos pos, Direction from, ArrayDeque<ICrop> plants, ArrayDeque<ICrop> flowers) {
		if (!world.isLoaded(pos)) {
			return true;
		}

		BlockState blockState = world.getBlockState(pos);
		if (blockState.getBlock() == Blocks.CHORUS_FLOWER) {
			ICrop crop = this.chorusFarmable.getCropAt(world, pos, blockState);
			if (crop != null) {
				flowers.addFirst(crop);
				return false;
			}
			return false;
		} else if (blockState.getBlock() == Blocks.CHORUS_PLANT) {
			boolean canHarvest = true;
			for (Direction facing : VALID_DIRECTIONS) {
				if (facing == from) {
					continue;
				}
				canHarvest &= harvestBlock(world, pos.relative(facing), facing.getOpposite(), plants, flowers);
			}
			if (canHarvest) {
				plants.addFirst(new CropDestroy(world, blockState, pos, null));
			}
			return canHarvest;
		}
		return true;
	}

	@Override
	protected boolean maintainSeedlings(Level world, IFarmHousing farmHousing, BlockPos pos, Direction direction, int extent) {
		for (int i = 0; i < extent; i++) {
			BlockPos position = translateWithOffset(pos, direction, i);
			if (!world.isLoaded(position)) {
				break;
			}

			BlockState state = world.getBlockState(position);
			if (!world.isEmptyBlock(position) && !BlockUtil.isReplaceableBlock(state, world, position)) {
				continue;
			}

			BlockPos soilPos = position.below();
			BlockState blockState = world.getBlockState(soilPos);
			if (!isAcceptedSoil(blockState)) {
				continue;
			}

			if (trySetCrop(world, farmHousing, position, direction)) {
				return true;
			}
		}

		return false;
	}
}
