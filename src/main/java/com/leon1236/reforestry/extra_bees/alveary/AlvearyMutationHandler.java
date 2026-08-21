package com.leon1236.reforestry.extra_bees.alveary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public final class AlvearyMutationHandler {
	public record Mutagen(ItemStack stack, float multiplier) {
	}

	private static final List<Mutagen> MUTATIONS = new ArrayList<>();

	private AlvearyMutationHandler() {
	}

	public static boolean isMutationItem(ItemStack item) {
		return getMutationMult(item) > 1.0f;
	}

	public static float getMutationMult(ItemStack item) {
		if (item.isEmpty()) {
			return 1.0f;
		}
		for (Mutagen mutagen : MUTATIONS) {
			if (ItemStack.isSameItemSameComponents(item, mutagen.stack())) {
				return mutagen.multiplier();
			}
		}
		return 1.0f;
	}

	public static void addMutationItem(Item item, float chance) {
		addMutationItem(new ItemStack(item), chance);
	}

	public static void addMutationItem(ItemStack item, float chance) {
		if (item.isEmpty()) {
			return;
		}
		MUTATIONS.add(new Mutagen(item.copy(), chance));
		MUTATIONS.sort(Comparator.comparingDouble(Mutagen::multiplier));
	}

	public static void registerMutationItems() {
		MUTATIONS.clear();
		addMutationItem(new ItemStack(Blocks.SOUL_SAND), 1.5f);
		addMutationItem(Items.ENDER_PEARL, 2.0f);
		addMutationItem(Items.ENDER_EYE, 4.0f);
	}

	public static List<Mutagen> getMutagens() {
		return Collections.unmodifiableList(MUTATIONS);
	}
}
