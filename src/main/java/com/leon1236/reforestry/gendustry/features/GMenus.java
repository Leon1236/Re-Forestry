package com.leon1236.reforestry.gendustry.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.menu.ProducerMenu;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GMenus {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureMenuType<ProducerMenu, BlockPos> PROCESSOR =
			REGISTRY.menuType("processor", ProducerMenu::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
