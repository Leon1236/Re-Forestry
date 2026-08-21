package com.leon1236.reforestry.gendustry.features;

import net.minecraft.world.item.BlockItem;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.block.GendustryMachineBlock;
import com.leon1236.reforestry.gendustry.block.GendustryMachineType;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureBlockGroup<GendustryMachineBlock, GendustryMachineType> MACHINE =
			REGISTRY.blockGroup(GendustryMachineBlock::new, GendustryMachineType.values())
					.item(BlockItem::new)
					.create();

	public static void init() {
	}
}
