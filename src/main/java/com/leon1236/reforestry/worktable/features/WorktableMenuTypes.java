package com.leon1236.reforestry.worktable.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.worktable.gui.ContainerWorktable;

public class WorktableMenuTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("worktable"));

	public static final FeatureMenuType<ContainerWorktable, BlockPos> WORKTABLE =
			REGISTRY.menuType("worktable", ContainerWorktable::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
