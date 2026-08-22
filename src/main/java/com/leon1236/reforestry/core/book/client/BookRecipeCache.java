package com.leon1236.reforestry.core.book.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.gui.MachineRecipeEntry;
import com.leon1236.reforestry.core.gui.MachineGuiRecipes;

public final class BookRecipeCache {
	private static Map<Item, MachineRecipeEntry> carpenterByResult = Map.of();
	private static Map<Item, MachineRecipeEntry> fabricatorByResult = Map.of();

	private BookRecipeCache() {
	}

	public static void rebuild(@Nullable Level level) {
		if (level == null) {
			carpenterByResult = Map.of();
			fabricatorByResult = Map.of();
			return;
		}
		carpenterByResult = index(MachineGuiRecipes.carpenter(level));
		fabricatorByResult = index(MachineGuiRecipes.fabricator(level));
	}

	public static Optional<MachineRecipeEntry> carpenter(ItemStack stack) {
		return lookup(carpenterByResult, stack);
	}

	public static Optional<MachineRecipeEntry> fabricator(ItemStack stack) {
		return lookup(fabricatorByResult, stack);
	}

	private static Map<Item, MachineRecipeEntry> index(List<MachineRecipeEntry> entries) {
		Map<Item, MachineRecipeEntry> indexed = new HashMap<>();
		for (MachineRecipeEntry entry : entries) {
			indexed.putIfAbsent(entry.result().getItem(), entry);
		}
		return Map.copyOf(indexed);
	}

	private static Optional<MachineRecipeEntry> lookup(Map<Item, MachineRecipeEntry> indexed, ItemStack stack) {
		if (stack.isEmpty()) {
			return Optional.empty();
		}
		MachineRecipeEntry direct = indexed.get(stack.getItem());
		return Optional.ofNullable(direct);
	}
}
