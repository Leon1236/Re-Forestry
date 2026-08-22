package com.leon1236.reforestry.farming.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.farming.gui.ContainerFarm;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FarmingMenuTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("farming"));

	public static final FeatureMenuType<ContainerFarm, BlockPos> FARM =
			REGISTRY.menuType("farm", ContainerFarm::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
