package com.leon1236.reforestry.core.compat.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.modules.features.FeatureItem;

public final class JeiDescriptions {
	private static final String DESCRIPTION_KEY = "for.jei.description.";

	private JeiDescriptions() {
	}

	public static void addDescription(IRecipeRegistration registry, FeatureItem<?>... items) {
		for (FeatureItem<?> item : items) {
			addDescription(registry, item.item());
		}
	}

	public static void addDescription(IRecipeRegistration registry, Block... blocks) {
		for (Block block : blocks) {
			Item item = block.asItem();
			if (item != Items.AIR) {
				addDescription(registry, item);
			}
		}
	}

	public static void addDescription(IRecipeRegistration registry, Item item) {
		Identifier id = BuiltInRegistries.ITEM.getKey(item);
		addDescription(registry, item, id.getPath());
	}

	public static void addDescription(IRecipeRegistration registry, Item item, String itemKey) {
		registry.addIngredientInfo(
				new ItemStack(item),
				VanillaTypes.ITEM_STACK,
				Component.translatable(DESCRIPTION_KEY + itemKey));
	}

	public static void addDescription(IRecipeRegistration registry, String itemKey, Block... blocks) {
		List<ItemStack> stacks = new ArrayList<>(blocks.length);
		for (Block block : blocks) {
			Item item = block.asItem();
			if (item != Items.AIR) {
				stacks.add(new ItemStack(item));
			}
		}
		registry.addIngredientInfo(
				stacks,
				VanillaTypes.ITEM_STACK,
				Component.translatable(DESCRIPTION_KEY + itemKey));
	}

	public static void addDescription(IRecipeRegistration registry, String itemKey, FeatureItem<?>... items) {
		List<ItemStack> stacks = new ArrayList<>(items.length);
		for (FeatureItem<?> item : items) {
			stacks.add(new ItemStack(item.item()));
		}
		registry.addIngredientInfo(
				stacks,
				VanillaTypes.ITEM_STACK,
				Component.translatable(DESCRIPTION_KEY + itemKey));
	}
}
