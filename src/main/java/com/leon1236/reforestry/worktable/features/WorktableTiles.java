package com.leon1236.reforestry.worktable.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.worktable.tiles.TileWorktable;

public class WorktableTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("worktable"));

	public static final FeatureBlockEntityType<TileWorktable> WORKTABLE =
			REGISTRY.blockEntityType("worktable", TileWorktable::new);

	public static void init() {
		InventoryHelper.registerSided(WORKTABLE.type());
	}
}
