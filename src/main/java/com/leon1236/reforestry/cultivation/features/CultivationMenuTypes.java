package com.leon1236.reforestry.cultivation.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.cultivation.gui.ContainerPlanter;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CultivationMenuTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("cultivation"));

	public static final FeatureMenuType<ContainerPlanter, BlockPos> PLANTER =
			REGISTRY.menuType("planter", ContainerPlanter::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
