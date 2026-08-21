package com.leon1236.reforestry.extratrees.features;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.block.DispenserBlock;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.extratrees.items.EnumExtraTreesFood;
import com.leon1236.reforestry.extratrees.items.ExtraTreesBoatDispenserBehavior;
import com.leon1236.reforestry.extratrees.items.ItemExtraTreesBoat;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	public static final FeatureItemGroup<Item, EnumExtraTreesFood> FOODS =
			REGISTRY.itemGroup((type, properties) -> new Item(properties.food(
					new FoodProperties.Builder()
							.nutrition(type.nutrition)
							.saturationModifier(type.saturationModifier)
							.build(),
					Consumable.builder()
							.animation(ItemUseAnimation.EAT)
							.sound(SoundEvents.GENERIC_EAT)
							.consumeSeconds(1.6f)
							.build())), EnumExtraTreesFood.VALUES)
					.create();

	public static final FeatureItemGroup<ItemExtraTreesBoat, ExtraTreeWoodType> BOAT = REGISTRY
			.itemGroup((type, properties) -> new ItemExtraTreesBoat(type, false, properties), ExtraTreeWoodType.WITH_PRODUCTS)
			.identifier("boat", FeatureGroup.IdentifierType.SUFFIX)
			.create();
	public static final FeatureItemGroup<ItemExtraTreesBoat, ExtraTreeWoodType> CHEST_BOAT = REGISTRY
			.itemGroup((type, properties) -> new ItemExtraTreesBoat(type, true, properties), ExtraTreeWoodType.WITH_PRODUCTS)
			.identifier("chest_boat", FeatureGroup.IdentifierType.SUFFIX)
			.create();


	public static final FeatureItem<Item> PROVEN_GEAR = REGISTRY.item("proven_gear", Item::new);
	public static final FeatureItem<Item> SAWDUST = REGISTRY.item("sawdust", Item::new);
	public static final FeatureItem<Item> BARK = REGISTRY.item("bark", Item::new);
	public static final FeatureItem<Item> WOOD_WAX = REGISTRY.item("wood_wax", Item::new);
	public static final FeatureItem<Item> YEAST = REGISTRY.item("yeast", Item::new);
	public static final FeatureItem<Item> LAGER_YEAST = REGISTRY.item("yeast_lager", Item::new);
	public static final FeatureItem<Item> GRAIN_WHEAT = REGISTRY.item("grain_wheat", Item::new);
	public static final FeatureItem<Item> GRAIN_BARLEY = REGISTRY.item("grain_barley", Item::new);
	public static final FeatureItem<Item> GRAIN_RYE = REGISTRY.item("grain_rye", Item::new);
	public static final FeatureItem<Item> GRAIN_CORN = REGISTRY.item("grain_corn", Item::new);
	public static final FeatureItem<Item> GRAIN_ROASTED = REGISTRY.item("grain_roasted", Item::new);
	public static final FeatureItem<Item> GLASS_FITTING = REGISTRY.item("glass_fitting", Item::new);

	public static void init() {
		for (ExtraTreeWoodType type : ExtraTreeWoodType.WITH_PRODUCTS) {
			DispenserBlock.registerBehavior(BOAT.item(type), new ExtraTreesBoatDispenserBehavior(type, false));
			DispenserBlock.registerBehavior(CHEST_BOAT.item(type), new ExtraTreesBoatDispenserBehavior(type, true));
		}
	}
}
