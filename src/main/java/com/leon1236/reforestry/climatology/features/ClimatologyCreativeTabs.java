package com.leon1236.reforestry.climatology.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ClimatologyCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("climatology"));

	public static final FeatureCreativeTab CLIMATOLOGY = REGISTRY.creativeTab("climatology", tab -> {
		tab.icon(() -> ClimatologyBlocks.HABITAT_FORMER.item().getDefaultInstance());
		tab.displayItems((parameters, output) -> {
			output.accept(ClimatologyBlocks.HABITAT_FORMER.item());
			output.accept(ClimatologyItems.HABITAT_SCREEN.item());
		});
	});

	public static void init() {
	}
}
