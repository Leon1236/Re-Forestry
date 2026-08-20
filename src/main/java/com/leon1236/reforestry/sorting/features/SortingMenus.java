package com.leon1236.reforestry.sorting.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.sorting.gui.ContainerGeneticFilter;

public class SortingMenus {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("sorting"));

	public static final FeatureMenuType<ContainerGeneticFilter, BlockPos> GENETIC_FILTER =
			REGISTRY.menuType("genetic_filter", ContainerGeneticFilter::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
