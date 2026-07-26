package com.leon1236.reforestry.storage.features;

import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class StorageCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("storage"));

	public static final FeatureCreativeTab STORAGE = REGISTRY.creativeTab("storage", tab -> {
		tab.icon(Items.CHEST::getDefaultInstance);
		tab.displayItems((parameters, output) -> {
		});
	});

	public static void init() {
	}
}
