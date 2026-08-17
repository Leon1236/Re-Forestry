package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.core.features.CoreTiles;

public class TileLepidopteristChest extends TileNaturalistChest {
	public TileLepidopteristChest(BlockPos pos, BlockState state) {
		super(CoreTiles.BUTTERFLY_CHEST.type(), pos, state, ForestrySpeciesTypes.BUTTERFLY);
	}
}
