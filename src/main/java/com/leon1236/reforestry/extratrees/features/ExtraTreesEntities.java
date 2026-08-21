package com.leon1236.reforestry.extratrees.features;

import net.minecraft.world.entity.MobCategory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extratrees.entities.ExtraTreesBoat;
import com.leon1236.reforestry.extratrees.entities.ExtraTreesChestBoat;
import com.leon1236.reforestry.modules.features.FeatureEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesEntities {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	public static final FeatureEntityType<ExtraTreesBoat> BOAT = REGISTRY.entity(
			"extra_tree_boat",
			ExtraTreesBoat::new,
			MobCategory.MISC,
			builder -> builder.sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).noLootTable());

	public static final FeatureEntityType<ExtraTreesChestBoat> CHEST_BOAT = REGISTRY.entity(
			"extra_tree_chest_boat",
			ExtraTreesChestBoat::new,
			MobCategory.MISC,
			builder -> builder.sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).noLootTable());

	public static void init() {
	}
}
