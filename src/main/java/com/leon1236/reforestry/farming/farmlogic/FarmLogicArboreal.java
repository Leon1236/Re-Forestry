package com.leon1236.reforestry.farming.farmlogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;

public class FarmLogicArboreal extends FarmLogicHomogeneous {
	@Nullable
	private List<IFarmable> farmables;

	public FarmLogicArboreal(IFarmType properties, boolean isManual) {
		super(properties, isManual);
	}

	@Override
	protected List<IFarmable> getFarmables() {
		if (this.farmables == null) {
			this.farmables = new ArrayList<>(this.type.getFarmables());
		}
		return this.farmables;
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

	private Collection<ICrop> harvestBlocks(Level level, BlockPos pos) {
		IFarmable farmable = getFarmableForBlock(level, pos, getFarmables());
		if (farmable == null) {
			return List.of();
		}

		ArrayDeque<BlockPos> knownCropPositions = new ArrayDeque<>();
		knownCropPositions.add(pos);

		Set<BlockPos> checkedBlocks = new HashSet<>();
		ArrayDeque<ICrop> crops = new ArrayDeque<>();

		while (!knownCropPositions.isEmpty()) {
			BlockPos knownCropPos = knownCropPositions.pop();
			for (BlockPos mutable : BlockPos.betweenClosed(knownCropPos.offset(-1, -1, -1), knownCropPos.offset(1, 1, 1))) {
				if (!level.isLoaded(mutable)) {
					return crops;
				}

				BlockPos candidate = mutable.immutable();
				if (!checkedBlocks.contains(candidate)) {
					checkedBlocks.add(candidate);

					BlockState blockState = level.getBlockState(candidate);
					ICrop crop = farmable.getCropAt(level, candidate, blockState);
					if (crop != null) {
						crops.push(crop);
						knownCropPositions.push(candidate);
					}
				}
			}
		}

		return crops;
	}

	@Nullable
	private static IFarmable getFarmableForBlock(Level world, BlockPos position, Collection<IFarmable> farmables) {
		if (world.isEmptyBlock(position)) {
			return null;
		}
		BlockState blockState = world.getBlockState(position);
		for (IFarmable farmable : farmables) {
			ICrop crop = farmable.getCropAt(world, position, blockState);
			if (crop != null) {
				return farmable;
			}
		}
		return null;
	}

	@Override
	protected boolean maintainSeedlings(Level world, IFarmHousing farmHousing, BlockPos pos, Direction direction, int extent) {
		for (int i = 0; i < extent; i++) {
			BlockPos position = translateWithOffset(pos, direction, i);
			if (!world.isLoaded(position)) {
				break;
			}

			if (world.isEmptyBlock(position)) {
				BlockPos soilPosition = position.below();
				BlockState soilState = world.getBlockState(soilPosition);
				if (isAcceptedSoil(soilState)) {
					return plantSapling(world, farmHousing, position, direction);
				}
			}
		}
		return false;
	}

	private boolean plantSapling(Level world, IFarmHousing farmHousing, BlockPos position, Direction direction) {
		Collections.shuffle(getFarmables());
		for (IFarmable candidate : getFarmables()) {
			if (farmHousing.plantGermling(candidate, world, position, direction)) {
				return true;
			}
		}

		return false;
	}
}
