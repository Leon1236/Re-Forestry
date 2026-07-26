package com.leon1236.reforestry.api.genetics.capability;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;

public final class IndividualItems {
	private static Access access = Access.EMPTY;

	private IndividualItems() {
	}

	public static void setAccess(Access access) {
		IndividualItems.access = access == null ? Access.EMPTY : access;
	}

	public static boolean isIndividual(ItemStack stack) {
		return access.isIndividual(stack);
	}

	@Nullable
	public static IGenome getGenome(ItemStack stack) {
		return access.getGenome(stack);
	}

	@Nullable
	public static Identifier getSpeciesTypeId(ItemStack stack) {
		return access.getSpeciesTypeId(stack);
	}

	@Nullable
	public static String getLifeStage(ItemStack stack) {
		return access.getLifeStage(stack);
	}

	public static boolean isAnalyzed(ItemStack stack) {
		return access.isAnalyzed(stack);
	}

	public static boolean analyze(ItemStack stack, Player player) {
		return access.analyze(stack, player);
	}

	public static void ifPresent(ItemStack stack, BiConsumer<IGenome, String> action) {
		if (stack.isEmpty()) {
			return;
		}
		IGenome genome = getGenome(stack);
		String stage = getLifeStage(stack);
		if (genome != null && stage != null) {
			action.accept(genome, stage);
		}
	}

	public static void ifPresent(ItemStack stack, Consumer<IGenome> action) {
		IGenome genome = getGenome(stack);
		if (genome != null) {
			action.accept(genome);
		}
	}

	public static boolean filter(ItemStack stack, Predicate<IGenome> predicate) {
		IGenome genome = getGenome(stack);
		return genome != null && predicate.test(genome);
	}

	public static boolean filter(ItemStack stack, BiPredicate<IGenome, String> predicate) {
		if (stack.isEmpty()) {
			return false;
		}
		IGenome genome = getGenome(stack);
		String stage = getLifeStage(stack);
		return genome != null && stage != null && predicate.test(genome, stage);
	}

	public interface Access {
		Access EMPTY = new Access() {
		};

		default boolean isIndividual(ItemStack stack) {
			return false;
		}

		@Nullable
		default IGenome getGenome(ItemStack stack) {
			return null;
		}

		@Nullable
		default Identifier getSpeciesTypeId(ItemStack stack) {
			return null;
		}

		@Nullable
		default String getLifeStage(ItemStack stack) {
			return null;
		}

		default boolean isAnalyzed(ItemStack stack) {
			return false;
		}

		default boolean analyze(ItemStack stack, Player player) {
			return false;
		}
	}
}
