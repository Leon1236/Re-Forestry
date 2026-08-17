package com.leon1236.reforestry.worktable.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.worktable.blocks.BlockWorktable;
import com.leon1236.reforestry.worktable.blocks.WorktableBlockType;
import com.leon1236.reforestry.worktable.items.ItemWorktable;

public class WorktableBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("worktable"));

	public static final FeatureBlock<BlockWorktable> WORKTABLE = REGISTRY.block("worktable",
			properties -> new BlockWorktable(WorktableBlockType.WORKTABLE, properties),
			ItemWorktable::new);

	public static void init() {
	}
}
