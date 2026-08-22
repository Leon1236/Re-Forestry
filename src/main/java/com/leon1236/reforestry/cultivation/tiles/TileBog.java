package com.leon1236.reforestry.cultivation.tiles;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ForestryFarmTypes;
import com.leon1236.reforestry.core.features.CoreBlocks;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.cultivation.features.CultivationTiles;

public class TileBog extends TilePlanter {
	public TileBog(BlockPos pos, BlockState state) {
		super(CultivationTiles.BOG.type(), pos, state, ForestryFarmTypes.PEAT);
	}

	@Override
	public List<ItemStack> createGermlingStacks() {
		return List.of();
	}

	@Override
	public List<ItemStack> createResourceStacks() {
		return List.of(
				new ItemStack(CoreBlocks.BOG_EARTH.block()),
				new ItemStack(CoreBlocks.BOG_EARTH.block()),
				new ItemStack(CoreBlocks.BOG_EARTH.block()),
				new ItemStack(CoreBlocks.BOG_EARTH.block())
		);
	}

	@Override
	public List<ItemStack> createProductionStacks() {
		return List.of(
				new ItemStack(CoreItems.PEAT.item()),
				new ItemStack(CoreItems.PEAT.item()),
				new ItemStack(CoreItems.PEAT.item()),
				new ItemStack(CoreItems.PEAT.item())
		);
	}
}
