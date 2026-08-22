package com.leon1236.reforestry.extra_bees.features;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeComb;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeDrop;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeFrame;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeMisc;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeePropolis;
import com.leon1236.reforestry.extra_bees.items.ItemExtraBeeHiveFrame;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraBeesItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_bees"));

	public static final FeatureItemGroup<Item, EnumExtraBeeComb> BEE_COMBS =
			REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumExtraBeeComb.VALUES)
					.identifier("bee_comb")
					.create();

	public static final FeatureItemGroup<Item, EnumExtraBeeDrop> HONEY_DROPS =
			REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumExtraBeeDrop.VALUES)
					.identifier("honey_drop")
					.create();

	public static final FeatureItemGroup<Item, EnumExtraBeePropolis> PROPOLIS =
			REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumExtraBeePropolis.VALUES)
					.identifier("propolis")
					.create();

	public static final FeatureItemGroup<Item, EnumExtraBeeMisc> MISC =
			REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumExtraBeeMisc.VALUES)
					.create();

	public static final FeatureItemGroup<ItemExtraBeeHiveFrame, EnumExtraBeeFrame> FRAMES =
			REGISTRY.itemGroup(ItemExtraBeeHiveFrame::new, EnumExtraBeeFrame.VALUES)
					.identifier("hive_frame")
					.create();

	public static void init() {
	}
}
