package com.leon1236.reforestry.lepidopterology.features;

import net.minecraft.world.entity.MobCategory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.lepidopterology.entities.EntityButterfly;
import com.leon1236.reforestry.modules.features.FeatureEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class LepidopterologyEntities {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("lepidopterology"));

	public static final FeatureEntityType<EntityButterfly> BUTTERFLY = REGISTRY.entity(
			"butterfly",
			EntityButterfly::new,
			MobCategory.CREATURE,
			builder -> builder.sized(0.5f, 0.25f).clientTrackingRange(10).noLootTable());

	public static void init() {
	}
}
