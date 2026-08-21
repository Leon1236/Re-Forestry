package com.leon1236.reforestry.extra_bees.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyFrame;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyHatchery;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyMutator;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyStimulator;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraBeesMenuTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_bees"));

	public static final FeatureMenuType<ContainerAlvearyMutator, BlockPos> ALVEARY_MUTATOR =
			REGISTRY.menuType("alveary_mutator", ContainerAlvearyMutator::new, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ContainerAlvearyFrame, BlockPos> ALVEARY_FRAME =
			REGISTRY.menuType("alveary_frame", ContainerAlvearyFrame::new, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ContainerAlvearyHatchery, BlockPos> ALVEARY_HATCHERY =
			REGISTRY.menuType("alveary_hatchery", ContainerAlvearyHatchery::new, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ContainerAlvearyStimulator, BlockPos> ALVEARY_STIMULATOR =
			REGISTRY.menuType("alveary_stimulator", ContainerAlvearyStimulator::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
