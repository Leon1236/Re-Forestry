package com.leon1236.reforestry.extratrees.features;

import net.minecraft.world.level.block.DispenserBlock;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.extratrees.items.ExtraTreesBoatDispenserBehavior;
import com.leon1236.reforestry.extratrees.items.ItemExtraTreesBoat;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	public static final FeatureItemGroup<ItemExtraTreesBoat, ExtraTreeWoodType> BOAT = REGISTRY
			.itemGroup((type, properties) -> new ItemExtraTreesBoat(type, false, properties), ExtraTreeWoodType.WITH_PRODUCTS)
			.identifier("boat", FeatureGroup.IdentifierType.SUFFIX)
			.create();
	public static final FeatureItemGroup<ItemExtraTreesBoat, ExtraTreeWoodType> CHEST_BOAT = REGISTRY
			.itemGroup((type, properties) -> new ItemExtraTreesBoat(type, true, properties), ExtraTreeWoodType.WITH_PRODUCTS)
			.identifier("chest_boat", FeatureGroup.IdentifierType.SUFFIX)
			.create();

	public static void init() {
		for (ExtraTreeWoodType type : ExtraTreeWoodType.WITH_PRODUCTS) {
			DispenserBlock.registerBehavior(BOAT.item(type), new ExtraTreesBoatDispenserBehavior(type, false));
			DispenserBlock.registerBehavior(CHEST_BOAT.item(type), new ExtraTreesBoatDispenserBehavior(type, true));
		}
	}
}
