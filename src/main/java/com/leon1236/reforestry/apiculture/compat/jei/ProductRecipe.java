package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiHelper;

public final class ProductRecipe {
	final ISpecies<?> species;
	final Identifier speciesId;
	final ItemStack displayInput;
	final List<ItemStack> inputs;
	final List<IProduct> products;
	final List<IProduct> specialties;

	public ProductRecipe(ISpecies<?> species) {
		this.species = species;
		this.speciesId = species.id();
		ISpeciesType<?, ?> type = species.getType();
		ILifeStage displayStage = type.getTypeForMutation(2);
		List<ItemStack> inputStacks = new ArrayList<>();
		ItemStack display = ItemStack.EMPTY;
		for (ILifeStage stage : type.getLifeStages()) {
			ItemStack stack = GeneticsJeiHelper.createAnalyzedStack(type, stage, speciesId, null);
			inputStacks.add(stack);
			if (stage == displayStage) {
				display = stack;
			}
		}
		this.inputs = inputStacks;
		this.displayInput = display;
		List<IProduct> productList = new ArrayList<>();
		List<IProduct> specialtyList = new ArrayList<>();
		GeneticsJeiHelper.resolveProducts(species, productList, specialtyList);
		this.products = productList;
		this.specialties = specialtyList;
	}
}
