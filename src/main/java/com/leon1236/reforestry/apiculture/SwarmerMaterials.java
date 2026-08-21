package com.leon1236.reforestry.apiculture;

import java.util.IdentityHashMap;
import java.util.Map;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class SwarmerMaterials {
	private static final Map<Item, Float> CHANCES = new IdentityHashMap<>();

	private SwarmerMaterials() {
	}

	public static void register(Item item, float chance) {
		CHANCES.put(item, chance);
	}

	public static boolean isInducer(ItemStack stack) {
		return CHANCES.containsKey(stack.getItem());
	}

	public static float getChance(ItemStack stack) {
		Float chance = CHANCES.get(stack.getItem());
		return chance == null ? 0f : chance;
	}
}
