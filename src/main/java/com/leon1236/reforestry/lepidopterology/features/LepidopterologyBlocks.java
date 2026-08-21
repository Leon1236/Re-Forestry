package com.leon1236.reforestry.lepidopterology.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.lepidopterology.blocks.BlockCocoon;
import com.leon1236.reforestry.lepidopterology.blocks.BlockSolidCocoon;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class LepidopterologyBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("lepidopterology"));

	public static final FeatureBlock<BlockCocoon> COCOON = REGISTRY.block("cocoon", BlockCocoon::new, null);
	public static final FeatureBlock<BlockSolidCocoon> COCOON_SOLID = REGISTRY.block("cocoon_solid", BlockSolidCocoon::new, null);

	public static void init() {
	}
}
