package com.leon1236.reforestry.storage.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.storage.BackpackMode;

public class StorageDataComponents {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("storage"));

	public static final FeatureDataComponent<BackpackMode> BACKPACK_MODE =
			REGISTRY.dataComponent("backpack_mode", builder -> builder
					.persistent(BackpackMode.CODEC)
					.networkSynchronized(BackpackMode.STREAM_CODEC));

	public static void init() {
	}
}
