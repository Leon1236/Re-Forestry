package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.core.features.CoreTiles;

public class TileApiaristChest extends TileNaturalistChest {
	public TileApiaristChest(BlockPos pos, BlockState state) {
		super(CoreTiles.BEE_CHEST.type(), pos, state, ForestrySpeciesTypes.BEE);
	}
}
