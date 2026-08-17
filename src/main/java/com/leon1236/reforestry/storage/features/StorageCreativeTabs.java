package com.leon1236.reforestry.storage.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class StorageCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("storage"));

	public static final FeatureCreativeTab STORAGE = REGISTRY.creativeTab("storage", tab -> {
		tab.icon(() -> BackpackItems.MINER_BACKPACK.item().getDefaultInstance());
		tab.displayItems((parameters, output) -> {
			output.accept(BackpackItems.APIARIST_BACKPACK.item());
			output.accept(BackpackItems.ARBORIST_BACKPACK.item());
			output.accept(BackpackItems.LEPIDOPTERIST_BACKPACK.item());
			output.accept(BackpackItems.MINER_BACKPACK.item());
			output.accept(BackpackItems.MINER_BACKPACK_T_2.item());
			output.accept(BackpackItems.DIGGER_BACKPACK.item());
			output.accept(BackpackItems.DIGGER_BACKPACK_T_2.item());
			output.accept(BackpackItems.FORESTER_BACKPACK.item());
			output.accept(BackpackItems.FORESTER_BACKPACK_T_2.item());
			output.accept(BackpackItems.HUNTER_BACKPACK.item());
			output.accept(BackpackItems.HUNTER_BACKPACK_T_2.item());
			output.accept(BackpackItems.ADVENTURER_BACKPACK.item());
			output.accept(BackpackItems.ADVENTURER_BACKPACK_T_2.item());
			output.accept(BackpackItems.BUILDER_BACKPACK.item());
			output.accept(BackpackItems.BUILDER_BACKPACK_T_2.item());
			output.accept(BackpackItems.BREWER_BACKPACK.item());
			output.accept(BackpackItems.BREWER_BACKPACK_T_2.item());
			output.accept(CrateItems.CRATE.item());
			for (var crate : CrateItems.getCrates()) {
				output.accept(crate.item());
			}
		});
	});

	public static void init() {
	}
}
