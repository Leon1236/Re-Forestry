package com.leon1236.reforestry.extratrees.features;

import java.util.List;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	private static final List<FeatureBlockGroup<?, ExtraTreeWoodType>> WOOD_GROUPS = List.of(
			ExtraTreesBlocks.LOGS,
			ExtraTreesBlocks.LOGS_FIREPROOF,
			ExtraTreesBlocks.PLANKS,
			ExtraTreesBlocks.PLANKS_FIREPROOF,
			ExtraTreesBlocks.SLABS,
			ExtraTreesBlocks.SLABS_FIREPROOF,
			ExtraTreesBlocks.FENCES,
			ExtraTreesBlocks.FENCES_FIREPROOF,
			ExtraTreesBlocks.FENCE_GATES,
			ExtraTreesBlocks.FENCE_GATES_FIREPROOF,
			ExtraTreesBlocks.STAIRS,
			ExtraTreesBlocks.STAIRS_FIREPROOF,
			ExtraTreesBlocks.DOORS
	);

	public static final FeatureCreativeTab EXTRA_TREES = REGISTRY.creativeTab("extra_trees", tab -> {
		tab.icon(() -> ExtraTreesBlocks.PLANKS.get(ExtraTreeWoodType.CEDAR).item().getDefaultInstance());
		tab.displayItems((parameters, output) -> {
			for (FeatureBlockGroup<?, ExtraTreeWoodType> group : WOOD_GROUPS) {
				for (FeatureBlock<?> feature : group.getAll().values()) {
					if (feature.item() != null) {
						output.accept(feature.item());
					}
				}
			}
		});
	});

	public static void init() {
	}
}
