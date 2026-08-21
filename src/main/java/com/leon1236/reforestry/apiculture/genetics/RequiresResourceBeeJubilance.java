package com.leon1236.reforestry.apiculture.genetics;

import java.util.Collections;
import java.util.HashSet;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.api.genetics.IGenome;

public class RequiresResourceBeeJubilance implements IBeeJubilance {
	private final HashSet<BlockState> acceptedBlockStates = new HashSet<>();

	public RequiresResourceBeeJubilance(BlockState... acceptedBlockStates) {
		Collections.addAll(this.acceptedBlockStates, acceptedBlockStates);
	}

	@Override
	public boolean isJubilant(IBeeSpecies species, IGenome genome, IBeeHousing housing) {
		Level level = housing.level();
		BlockPos pos = housing.position();

		BlockEntity tile;
		do {
			pos = pos.below();
			tile = level.getBlockEntity(pos);
		} while (tile instanceof IBeeHousing && pos.getY() > 0);

		BlockState blockState = level.getBlockState(pos);
		return this.acceptedBlockStates.contains(blockState);
	}
}
