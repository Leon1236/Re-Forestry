package com.leon1236.reforestry.lepidopterology.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.lepidopterology.tiles.TileCocoon;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class LepidopterologyTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("lepidopterology"));

	public static final FeatureBlockEntityType<TileCocoon> COCOON = REGISTRY.blockEntityType(
			"cocoon",
			(pos, state) -> new TileCocoon(pos, state, false),
			LepidopterologyBlocks.COCOON.block());
	public static final FeatureBlockEntityType<TileCocoon> SOLID_COCOON = REGISTRY.blockEntityType(
			"solid_cocoon",
			(pos, state) -> new TileCocoon(pos, state, true),
			LepidopterologyBlocks.COCOON_SOLID.block());

	public static void init() {
	}
}
