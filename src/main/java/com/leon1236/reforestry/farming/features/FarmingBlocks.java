package com.leon1236.reforestry.farming.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.farming.blocks.EnumFarmBlockType;
import com.leon1236.reforestry.farming.blocks.EnumFarmMaterial;
import com.leon1236.reforestry.farming.blocks.FarmBlock;
import com.leon1236.reforestry.farming.items.ItemBlockFarm;
import com.leon1236.reforestry.modules.features.FeatureBlockTable;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FarmingBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("farming"));

	public static final FeatureBlockTable<FarmBlock, EnumFarmBlockType, EnumFarmMaterial> FARM =
			REGISTRY.blockTable(FarmBlock::create, EnumFarmBlockType.values(), EnumFarmMaterial.values())
					.item(ItemBlockFarm::new)
					.identifier((part, material) -> {
						String mat = switch (material) {
							case SANDSTONE_CHISELED -> "chiseled_sandstone";
							case BRICK_NETHER -> "nether_brick";
							case BRICK_CHISELED -> "chiseled_stone_brick";
							case QUARTZ_CHISELED -> "chiseled_quartz";
							case QUARTZ_LINES -> "quartz_pillar";
							default -> material.getSerializedName();
						};
						String partName = (part == EnumFarmBlockType.PLAIN && material == EnumFarmMaterial.STONE_BRICK)
								? "block"
								: part.getSerializedName();
						return mat + "_farm_" + partName;
					})
					.create();

	public static void init() {
	}
}
