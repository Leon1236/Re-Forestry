package com.leon1236.reforestry.api.apiculture;

import net.minecraft.world.level.block.state.BlockState;

public interface IJubilanceFactory {
	IBeeJubilance getDefault();

	IBeeJubilance getHermit();

	IBeeJubilance getRequiresResource(BlockState... acceptedBlockStates);
}
