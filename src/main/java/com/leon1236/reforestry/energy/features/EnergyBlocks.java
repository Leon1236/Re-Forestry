package com.leon1236.reforestry.energy.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.energy.blocks.EngineBlock;
import com.leon1236.reforestry.energy.blocks.EngineBlockType;
import com.leon1236.reforestry.energy.items.ItemBlockEngine;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class EnergyBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("energy"));

	public static final FeatureBlockGroup<EngineBlock, EngineBlockType> ENGINES =
			REGISTRY.blockGroup(EngineBlock::new, EngineBlockType.VALUES)
					.item(ItemBlockEngine::new)
					.identifier("engine", FeatureGroup.IdentifierType.SUFFIX)
					.create();

	public static void init() {
	}
}
