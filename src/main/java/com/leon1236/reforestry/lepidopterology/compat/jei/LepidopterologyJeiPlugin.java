package com.leon1236.reforestry.lepidopterology.compat.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.apiculture.compat.jei.MutationDisplay;
import com.leon1236.reforestry.apiculture.compat.jei.MutationsRecipeCategory;
import com.leon1236.reforestry.apiculture.compat.jei.ProductRecipe;
import com.leon1236.reforestry.apiculture.compat.jei.ProductsRecipeCategory;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiHelper;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiRecipeTypes;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyItems;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflySpeciesType;
import com.leon1236.reforestry.lepidopterology.genetics.LepidopterologyGenetics;

@JeiPlugin
public class LepidopterologyJeiPlugin implements IModPlugin {
	private static final Identifier BACKGROUND = ReForestry.id("textures/gui/jei/recipes.png");

	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("lepidopterology");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		if (!lepidopterologyLoaded()) {
			return;
		}
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		IDrawable productsBackground = helper.createDrawable(BACKGROUND, 0, 61 + 30, 162, 61);
		IDrawable mutationsBackground = helper.createDrawable(BACKGROUND, 0, 30, 162, 61);
		IDrawable icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ButterflySpeciesType.INSTANCE.createDefaultStack());
		registration.addRecipeCategories(
				new ProductsRecipeCategory(productsBackground, icon, GeneticsJeiRecipeTypes.BUTTERFLY_PRODUCTS,
						"for.jei.products.reforestry.butterfly_species"),
				new MutationsRecipeCategory(mutationsBackground, icon, GeneticsJeiRecipeTypes.BUTTERFLY_MUTATIONS,
						"for.jei.mutations.reforestry.butterfly_species", ButterflySpeciesType.INSTANCE)
		);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registry) {
		if (!lepidopterologyLoaded()) {
			return;
		}
		ISubtypeInterpreter<ItemStack> interpreter = (stack, context) -> activeSpeciesId(stack);
		registry.registerSubtypeInterpreter(LepidopterologyItems.BUTTERFLY.item(), interpreter);
		registry.registerSubtypeInterpreter(LepidopterologyItems.SERUM.item(), interpreter);
		registry.registerSubtypeInterpreter(LepidopterologyItems.CATERPILLAR.item(), interpreter);
		registry.registerSubtypeInterpreter(LepidopterologyItems.COCOON.item(), interpreter);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		if (!lepidopterologyLoaded()) {
			return;
		}
		JeiDescriptions.addDescription(registry, ApicultureItems.SCOOP);

		List<ProductRecipe> products = new ArrayList<>();
		for (IButterflySpecies species : LepidopterologyGenetics.getAllSpecies()) {
			if (!GeneticsJeiHelper.hasProducts(species)) {
				continue;
			}
			products.add(new ProductRecipe(species));
		}
		registry.addRecipes(GeneticsJeiRecipeTypes.BUTTERFLY_PRODUCTS, products);

		List<MutationDisplay> mutations = new ArrayList<>();
		for (com.leon1236.reforestry.core.genetics.mutations.Mutation mutation : LepidopterologyGenetics.getAllMutations()) {
			mutations.add(new MutationDisplay(mutation, ButterflySpeciesType.INSTANCE));
		}
		registry.addRecipes(GeneticsJeiRecipeTypes.BUTTERFLY_MUTATIONS, mutations);
	}

	private static boolean lepidopterologyLoaded() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("lepidopterology"));
	}

	static Identifier activeSpeciesId(ItemStack stack) {
		var genome = stack.get(LepidopterologyDataComponents.BUTTERFLY_GENOME.type());
		if (genome == null) {
			return null;
		}
		return genome.getActiveAllele(ButterflyChromosomes.SPECIES).alleleId();
	}
}
