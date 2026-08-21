package com.leon1236.reforestry.cultivation.tiles;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ForestryFarmTypes;
import com.leon1236.reforestry.cultivation.features.CultivationTiles;

public class TileArboretum extends TilePlanter {
	public TileArboretum(BlockPos pos, BlockState state) {
		super(CultivationTiles.ARBORETUM.type(), pos, state, ForestryFarmTypes.ARBOREAL);
	}

	@Override
	public List<ItemStack> createGermlingStacks() {
		return List.of(
				new ItemStack(Blocks.OAK_SAPLING),
				new ItemStack(Blocks.BIRCH_SAPLING),
				new ItemStack(Blocks.BIRCH_SAPLING),
				new ItemStack(Blocks.OAK_SAPLING)
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
				new ItemStack(Blocks.OAK_LOG),
				new ItemStack(Items.APPLE),
				new ItemStack(Items.APPLE),
				new ItemStack(Blocks.OAK_LOG)
		);
	}
}
