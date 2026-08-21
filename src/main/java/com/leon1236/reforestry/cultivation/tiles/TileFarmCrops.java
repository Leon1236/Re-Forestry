package com.leon1236.reforestry.cultivation.tiles;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ForestryFarmTypes;
import com.leon1236.reforestry.cultivation.features.CultivationTiles;

public class TileFarmCrops extends TilePlanter {
	public TileFarmCrops(BlockPos pos, BlockState state) {
		super(CultivationTiles.CROPS.type(), pos, state, ForestryFarmTypes.CROPS);
	}

	@Override
	public List<ItemStack> createGermlingStacks() {
		return List.of(
				new ItemStack(Items.WHEAT_SEEDS),
				new ItemStack(Items.POTATO),
				new ItemStack(Items.CARROT),
				new ItemStack(Items.BEETROOT_SEEDS)
		);
	}

	@Override
	public List<ItemStack> createResourceStacks() {
		return List.of(
				new ItemStack(Blocks.DIRT),
				new ItemStack(Blocks.DIRT),
				new ItemStack(Blocks.DIRT),
				new ItemStack(Blocks.DIRT)
		);
	}

	@Override
	public List<ItemStack> createProductionStacks() {
		return List.of(
				new ItemStack(Items.WHEAT),
				new ItemStack(Items.POTATO),
				new ItemStack(Items.CARROT),
				new ItemStack(Items.BEETROOT)
		);
	}
}
