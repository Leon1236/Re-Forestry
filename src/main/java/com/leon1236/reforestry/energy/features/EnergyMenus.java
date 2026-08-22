package com.leon1236.reforestry.energy.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.energy.gui.ContainerBiogasEngine;
import com.leon1236.reforestry.energy.gui.ContainerPeatEngine;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class EnergyMenus {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("energy"));

	public static final FeatureMenuType<ContainerPeatEngine, BlockPos> ENGINE_PEAT =
			REGISTRY.menuType("peat_engine", ContainerPeatEngine::new, BlockPos.STREAM_CODEC);
	public static final FeatureMenuType<ContainerBiogasEngine, BlockPos> ENGINE_BIOGAS =
			REGISTRY.menuType("biogas_engine", ContainerBiogasEngine::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
