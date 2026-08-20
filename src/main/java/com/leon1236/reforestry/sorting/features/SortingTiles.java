package com.leon1236.reforestry.sorting.features;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.sorting.inventory.FilterItemStorage;
import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

public class SortingTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("sorting"));

	public static final FeatureBlockEntityType<TileGeneticFilter> GENETIC_FILTER =
			REGISTRY.blockEntityType("genetic_filter", TileGeneticFilter::new, SortingBlocks.FILTER.block());

	public static void init() {
		ItemStorage.SIDED.registerForBlockEntity((tile, direction) -> {
			if (tile.isRemoved() || direction == null) {
				return null;
			}
			return new FilterItemStorage(tile, direction);
		}, GENETIC_FILTER.type());
	}
}
