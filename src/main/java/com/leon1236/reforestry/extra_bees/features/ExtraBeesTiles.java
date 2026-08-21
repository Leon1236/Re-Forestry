package com.leon1236.reforestry.extra_bees.features;

import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.tiles.TileHive;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraBeesTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_bees"));

	public static final FeatureBlockEntityType<TileHive> HIVE = REGISTRY.blockEntityType("extra_bees_hive",
			TileHive::new,
			ExtraBeesBlocks.BEEHIVE.getAll().values().stream().map(FeatureBlock::block).toArray(Block[]::new));

	public static void init() {
	}
}
