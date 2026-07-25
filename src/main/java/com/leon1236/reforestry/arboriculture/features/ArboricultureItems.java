package com.leon1236.reforestry.arboriculture.features;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.items.ForestryBoatDispenserBehavior;
import com.leon1236.reforestry.arboriculture.items.ItemGermlingGE;
import com.leon1236.reforestry.arboriculture.items.ItemGrafter;
import com.leon1236.reforestry.arboriculture.items.ItemForestryBoat;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
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

    public static final FeatureItemGroup<ItemForestryBoat, ForestryWoodType> BOAT = REGISTRY
            .itemGroup((type, properties) -> new ItemForestryBoat(type, false, properties), ForestryWoodType.VALUES)
            .identifier("boat", FeatureGroup.IdentifierType.SUFFIX)
            .create();
    public static final FeatureItemGroup<ItemForestryBoat, ForestryWoodType> CHEST_BOAT = REGISTRY
            .itemGroup((type, properties) -> new ItemForestryBoat(type, true, properties), ForestryWoodType.VALUES)
            .identifier("chest_boat", FeatureGroup.IdentifierType.SUFFIX)
            .create();

    public static final FeatureItem<Item> AMBER_SAPLING = REGISTRY.item("amber_sapling");

    public static void init() {
        for (ForestryWoodType type : ForestryWoodType.VALUES) {
            DispenserBlock.registerBehavior(BOAT.item(type), new ForestryBoatDispenserBehavior(type, false));
            DispenserBlock.registerBehavior(CHEST_BOAT.item(type), new ForestryBoatDispenserBehavior(type, true));
        }
    }
}
