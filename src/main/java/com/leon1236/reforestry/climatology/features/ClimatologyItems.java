package com.leon1236.reforestry.climatology.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.climatology.items.ItemHabitatScreen;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ClimatologyItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("climatology"));

	public static final FeatureItem<ItemHabitatScreen> HABITAT_SCREEN =
			REGISTRY.item("habitat_screen", ItemHabitatScreen::new);

	public static void init() {
	}
}
