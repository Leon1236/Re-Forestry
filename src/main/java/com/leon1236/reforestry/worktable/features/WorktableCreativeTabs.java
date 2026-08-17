package com.leon1236.reforestry.worktable.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class WorktableCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("worktable"));

	public static final FeatureCreativeTab WORKTABLE = REGISTRY.creativeTab("worktable", tab -> {
		tab.icon(() -> WorktableBlocks.WORKTABLE.item().getDefaultInstance());
		tab.displayItems((parameters, output) -> output.accept(WorktableBlocks.WORKTABLE.item()));
	});

	public static void init() {
	}
}
