package com.leon1236.reforestry.apiculture.genetics;

import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.apiculture.IJubilanceFactory;

public class JubilanceFactory implements IJubilanceFactory {
	@Override
	public IBeeJubilance getDefault() {
		return DefaultBeeJubilance.INSTANCE;
	}

	@Override
	public IBeeJubilance getHermit() {
		return HermitBeeJubilance.INSTANCE;
	}

	@Override
	public IBeeJubilance getRequiresResource(BlockState... acceptedBlockStates) {
		return new RequiresResourceBeeJubilance(acceptedBlockStates);
	}
}
