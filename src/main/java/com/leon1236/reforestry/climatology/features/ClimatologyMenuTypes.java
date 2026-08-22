package com.leon1236.reforestry.climatology.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.climatology.gui.ContainerHabitatFormer;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ClimatologyMenuTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("climatology"));

	public static final FeatureMenuType<ContainerHabitatFormer, BlockPos> HABITAT_FORMER =
			REGISTRY.menuType("habitat_former", ContainerHabitatFormer::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
