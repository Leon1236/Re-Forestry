package com.leon1236.reforestry.api.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.item.ItemStack;

public record MachineRecipeEntry(
		ItemStack result,
		List<List<ItemStack>> pattern,
		Optional<FluidVariant> liquid,
		int liquidAmountMb
) {
	public static final long CYCLE_MS = 1000L;

	public MachineRecipeEntry {
		result = result.copy();
		if (pattern.size() == 9) {
			List<List<ItemStack>> copy = new ArrayList<>(9);
			for (List<ItemStack> slot : pattern) {
				copy.add(slot.stream()
						.filter(stack -> stack != null && !stack.isEmpty())
						.map(stack -> stack.copyWithCount(1))
						.toList());
			}
			pattern = List.copyOf(copy);
		} else {
			pattern = List.of();
		}
		if (liquid.isEmpty() || liquid.get().isBlank() || liquidAmountMb <= 0) {
			liquid = Optional.empty();
			liquidAmountMb = 0;
		}
	}

	public static MachineRecipeEntry of(ItemStack result) {
		return new MachineRecipeEntry(result, List.of(), Optional.empty(), 0);
	}

	public static MachineRecipeEntry of(ItemStack result, List<List<ItemStack>> pattern) {
		return new MachineRecipeEntry(result, pattern, Optional.empty(), 0);
	}

	public static MachineRecipeEntry of(ItemStack result, List<List<ItemStack>> pattern, @Nullable FluidVariant liquid, int liquidAmountMb) {
		return new MachineRecipeEntry(result, pattern, Optional.ofNullable(liquid), liquidAmountMb);
	}

	public static MachineRecipeEntry of(ItemStack result, @Nullable FluidVariant liquid, int liquidAmountMb) {
		return new MachineRecipeEntry(result, List.of(), Optional.ofNullable(liquid), liquidAmountMb);
	}

	public boolean hasPattern() {
		return pattern.size() == 9;
	}

	public boolean hasLiquid() {
		return liquid.isPresent() && liquidAmountMb > 0;
	}

	public ItemStack patternStack(int slot, long timeMs) {
		if (!hasPattern() || slot < 0 || slot >= 9) {
			return ItemStack.EMPTY;
		}
		List<ItemStack> alternatives = pattern.get(slot);
		if (alternatives.isEmpty()) {
			return ItemStack.EMPTY;
		}
		int index = (int) Math.floorMod(timeMs / CYCLE_MS, alternatives.size());
		return alternatives.get(index).copyWithCount(1);
	}

	public static int cycleIndex(int alternativeCount, long timeMs) {
		if (alternativeCount <= 0) {
			return 0;
		}
		return (int) Math.floorMod(timeMs / CYCLE_MS, alternativeCount);
	}
}
