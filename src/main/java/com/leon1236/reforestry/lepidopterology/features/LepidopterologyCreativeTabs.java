package com.leon1236.reforestry.lepidopterology.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.NaturalistChestBlockType;
import com.leon1236.reforestry.core.features.CoreBlocks;
import com.leon1236.reforestry.modules.ModuleManager;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.storage.features.BackpackItems;

public class LepidopterologyCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("lepidopterology"));

	public static final FeatureCreativeTab LEPIDOPTEROLOGY = REGISTRY.creativeTab("lepidopterology", tab -> {
		tab.icon(() -> LepidopterologyItems.BUTTERFLY.item().getDefaultInstance());
		tab.displayItems((parameters, output) -> {
			output.accept(CoreBlocks.NATURALIST_CHESTS.get(NaturalistChestBlockType.BUTTERFLY_CHEST).item());
			if (ModuleManager.INSTANCE.isModuleLoaded(ReForestry.id("storage"))) {
				output.accept(BackpackItems.LEPIDOPTERIST_BACKPACK.item());
			}
			output.accept(LepidopterologyItems.BUTTERFLY.item());
			output.accept(LepidopterologyItems.SERUM.item());
			output.accept(LepidopterologyItems.CATERPILLAR.item());
			output.accept(LepidopterologyItems.COCOON.item());
		});
	});

	public static void init() {
	}
}
