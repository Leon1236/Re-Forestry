package com.leon1236.reforestry.climatology.features;

import net.minecraft.world.item.BlockItem;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.climatology.blocks.BlockHabitatFormer;
import com.leon1236.reforestry.climatology.blocks.ClimatologyBlockType;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ClimatologyBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("climatology"));

	public static final FeatureBlock<BlockHabitatFormer> HABITAT_FORMER = REGISTRY.block(
			"habitat_former",
			properties -> new BlockHabitatFormer(ClimatologyBlockType.HABITAT_FORMER, properties),
			BlockItem::new);

	public static void init() {
	}
}
