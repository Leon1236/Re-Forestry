package com.leon1236.reforestry.arboriculture.features;

import net.minecraft.world.entity.MobCategory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.entities.ForestryBoat;
import com.leon1236.reforestry.arboriculture.entities.ForestryChestBoat;
import com.leon1236.reforestry.modules.features.FeatureEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ArboricultureEntities {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("arboriculture"));

    public static final FeatureEntityType<ForestryBoat> BOAT = REGISTRY.entity(
            "boat",
            ForestryBoat::new,
            MobCategory.MISC,
            builder -> builder.sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).noLootTable());

    public static final FeatureEntityType<ForestryChestBoat> CHEST_BOAT = REGISTRY.entity(
            "chest_boat",
            ForestryChestBoat::new,
            MobCategory.MISC,
            builder -> builder.sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).noLootTable());

    public static void init() {
    }
}
