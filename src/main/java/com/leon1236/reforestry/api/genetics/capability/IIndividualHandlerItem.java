package com.leon1236.reforestry.api.genetics.capability;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.arboriculture.genetics.ITreeSpeciesType;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.IIndividualItem;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;

public interface IIndividualHandlerItem {
	static void ifPresent(ItemStack stack, BiConsumer<IIndividual, ILifeStage> action) {
		IIndividual individual = getIndividual(stack);
		ILifeStage lifeStage = getLifeStage(stack);
		if (individual != null && lifeStage != null) {
			action.accept(individual, lifeStage);
		}
	}

	static void ifPresent(ItemStack stack, Consumer<IIndividual> action) {
		IIndividual individual = getIndividual(stack);
		if (individual != null) {
			action.accept(individual);
		}
	}

	static boolean isIndividual(ItemStack stack) {
		return getIndividual(stack) != null;
	}

	static boolean hasIndividual(ItemStack stack) {
		return getGenome(stack) != null;
	}

	static boolean filter(ItemStack stack, Predicate<IIndividual> predicate) {
		IIndividual individual = getIndividual(stack);
		return individual != null && predicate.test(individual);
	}

	static boolean filter(ItemStack stack, BiPredicate<IIndividual, ILifeStage> predicate) {
		IIndividual individual = getIndividual(stack);
		ILifeStage lifeStage = getLifeStage(stack);
		return individual != null && lifeStage != null && predicate.test(individual, lifeStage);
	}

	@Nullable
	static IIndividual getIndividual(ItemStack stack) {
		if (stack.getItem() instanceof IIndividualItem item) {
			return item.getIndividualFromComponent(stack);
		}
		ISpeciesType<?, ?> type = IForestryApi.INSTANCE.getGeneticManager().getSpeciesTypeSafe(ForestrySpeciesTypes.TREE);
		if (type instanceof ITreeSpeciesType treeType) {
			return treeType.getVanillaIndividual(stack.getItem());
		}
		return null;
	}

	@Nullable
	static IGenome getGenome(ItemStack stack) {
		IIndividual individual = getIndividual(stack);
		return individual == null ? null : individual.getGenome();
	}

	@Nullable
	static ILifeStage getLifeStage(ItemStack stack) {
		return stack.getItem() instanceof IIndividualItem item ? item.getLifeStage() : null;
	}

	@Nullable
	static ISpeciesType<?, ?> getSpeciesType(ItemStack stack) {
		return stack.getItem() instanceof IIndividualItem item ? item.getSpeciesType() : null;
	}

	@SuppressWarnings("unchecked")
	static <S extends ISpecies<?>> S getSpecies(ItemStack stack, ISpeciesType<S, ?> type) {
		IIndividual individual = getIndividual(stack);
		return individual != null ? (S) individual.getSpecies() : type.getDefaultSpecies();
	}
}
