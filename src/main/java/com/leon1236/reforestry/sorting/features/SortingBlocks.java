package com.leon1236.reforestry.sorting.features;

import net.minecraft.world.item.BlockItem;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.sorting.blocks.BlockGeneticFilter;

public class SortingBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("sorting"));

	public static final FeatureBlock<BlockGeneticFilter> FILTER =
			REGISTRY.block("genetic_filter", BlockGeneticFilter::new, BlockItem::new);

	public static void init() {
	}
}
