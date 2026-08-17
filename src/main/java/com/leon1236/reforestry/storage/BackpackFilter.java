package com.leon1236.reforestry.storage;

import java.util.function.Predicate;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BackpackFilter implements Predicate<ItemStack> {
	private final TagKey<Item> acceptKey;
	private final TagKey<Item> rejectKey;

	public BackpackFilter(TagKey<Item> acceptKey, TagKey<Item> rejectKey) {
		this.acceptKey = acceptKey;
		this.rejectKey = rejectKey;
	}

	@Override
	public boolean test(ItemStack itemStack) {
		return itemStack.is(this.acceptKey) && !itemStack.is(this.rejectKey);
	}
}
