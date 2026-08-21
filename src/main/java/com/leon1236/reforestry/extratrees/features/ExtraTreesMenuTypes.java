package com.leon1236.reforestry.extratrees.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extratrees.gui.ContainerBrewery;
import com.leon1236.reforestry.extratrees.gui.ContainerDistillery;
import com.leon1236.reforestry.extratrees.gui.ContainerLumbermill;
import com.leon1236.reforestry.extratrees.gui.ContainerPress;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesMenuTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	public static final FeatureMenuType<ContainerLumbermill, BlockPos> LUMBERMILL =
			REGISTRY.menuType("lumbermill", ContainerLumbermill::new, BlockPos.STREAM_CODEC);
	public static final FeatureMenuType<ContainerPress, BlockPos> PRESS =
			REGISTRY.menuType("press", ContainerPress::new, BlockPos.STREAM_CODEC);
	public static final FeatureMenuType<ContainerBrewery, BlockPos> BREWERY =
			REGISTRY.menuType("brewery", ContainerBrewery::new, BlockPos.STREAM_CODEC);
	public static final FeatureMenuType<ContainerDistillery, BlockPos> DISTILLERY =
			REGISTRY.menuType("distillery", ContainerDistillery::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
