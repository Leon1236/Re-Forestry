package com.leon1236.reforestry.cultivation.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.cultivation.blocks.BlockPlanter;
import com.leon1236.reforestry.cultivation.blocks.BlockTypePlanter;
import com.leon1236.reforestry.cultivation.items.ItemBlockPlanter;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CultivationBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("cultivation"));

	public static final FeatureBlockGroup<BlockPlanter, BlockTypePlanter> MANAGED_PLANTER =
			REGISTRY.blockGroup((type, properties) -> new BlockPlanter(type, false, properties), BlockTypePlanter.values())
					.item(ItemBlockPlanter::new)
					.identifier("managed", FeatureGroup.IdentifierType.SUFFIX)
					.create();

	public static final FeatureBlockGroup<BlockPlanter, BlockTypePlanter> MANUAL_PLANTER =
			REGISTRY.blockGroup((type, properties) -> new BlockPlanter(type, true, properties), BlockTypePlanter.values())
					.item(ItemBlockPlanter::new)
					.identifier("manual", FeatureGroup.IdentifierType.SUFFIX)
					.create();

	public static void init() {
	}
}
