package com.leon1236.reforestry.arboriculture.features;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.items.ItemGermlingGE;
import com.leon1236.reforestry.arboriculture.items.ItemGrafter;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ArboricultureItems {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("arboriculture"));

    public static final FeatureItem<ItemGermlingGE> SAPLING = REGISTRY.item("sapling",
            properties -> new ItemGermlingGE(properties, "sapling"));
    public static final FeatureItem<ItemGermlingGE> POLLEN_FERTILE = REGISTRY.item("pollen_fertile",
            properties -> new ItemGermlingGE(properties, "pollen"));

    public static final FeatureItem<ItemGrafter> GRAFTER = REGISTRY.item("grafter",
            properties -> new ItemGrafter(properties, 9));
    public static final FeatureItem<ItemGrafter> GRAFTER_PROVEN = REGISTRY.item("grafter_proven",
            properties -> new ItemGrafter(properties, 149));

    public static final FeatureItem<Item> AMBER_SAPLING = REGISTRY.item("amber_sapling");

    public static void init() {
    }
}
