package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;

public final class ProductRecipe {
	final Identifier speciesId;
	final ItemStack displayInput;
	final List<ItemStack> inputs;
	final List<? extends IProduct> products;
	final List<? extends IProduct> specialties;

	ProductRecipe(Identifier speciesId, IBeeSpecies species) {
		this.speciesId = speciesId;
		this.displayInput = BeeJeiHelper.analyzedStack(ApicultureItems.BEE_QUEEN, speciesId);
		this.inputs = BeeJeiHelper.allLifeStages(speciesId);
		this.products = species.products();
		this.specialties = species.specialties();
	}
}
