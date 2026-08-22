package com.leon1236.reforestry.arboriculture.compat.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.ITreeSpecies;
import com.leon1236.reforestry.apiculture.compat.jei.MutationDisplay;
import com.leon1236.reforestry.apiculture.compat.jei.MutationsRecipeCategory;
import com.leon1236.reforestry.apiculture.compat.jei.ProductRecipe;
import com.leon1236.reforestry.apiculture.compat.jei.ProductsRecipeCategory;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.features.CharcoalBlocks;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.genetics.TreeSpeciesType;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiHelper;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiRecipeTypes;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;

@JeiPlugin
public class ArboricultureJeiPlugin implements IModPlugin {
	private static final Identifier BACKGROUND = ReForestry.id("textures/gui/jei/recipes.png");

	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("arboriculture");
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registry) {
		ISubtypeInterpreter<ItemStack> interpreter = (stack, context) -> activeSpeciesId(stack);
		registry.registerSubtypeInterpreter(ArboricultureItems.SAPLING.item(), interpreter);
		registry.registerSubtypeInterpreter(ArboricultureItems.POLLEN_FERTILE.item(), interpreter);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
		IDrawable productsBackground = helper.createDrawable(BACKGROUND, 0, 61 + 30, 162, 61);
		IDrawable mutationsBackground = helper.createDrawable(BACKGROUND, 0, 30, 162, 61);
		IDrawable icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, TreeSpeciesType.INSTANCE.createDefaultStack());
		registry.addRecipeCategories(
				new CharcoalPileWallCategory(registry.getJeiHelpers().getGuiHelper()),
				new ProductsRecipeCategory(productsBackground, icon, GeneticsJeiRecipeTypes.TREE_PRODUCTS,
						"for.jei.products.reforestry.tree_species"),
				new MutationsRecipeCategory(mutationsBackground, icon, GeneticsJeiRecipeTypes.TREE_MUTATIONS,
						"for.jei.mutations.reforestry.tree_species", TreeSpeciesType.INSTANCE)
		);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		JeiDescriptions.addDescription(registry, ArboricultureItems.GRAFTER);
		JeiDescriptions.addDescription(registry, ArboricultureItems.GRAFTER_PROVEN);
		registry.addRecipes(ReforestryJeiRecipeTypes.CHARCOAL_PILE, CharcoalManager.get().getWalls());

		List<ProductRecipe> products = new ArrayList<>();
		for (ITreeSpecies species : ArboricultureGenetics.getAllSpecies()) {
			if (!GeneticsJeiHelper.hasProducts(species)) {
				continue;
			}
			products.add(new ProductRecipe(species));
		}
		registry.addRecipes(GeneticsJeiRecipeTypes.TREE_PRODUCTS, products);

		List<MutationDisplay> mutations = new ArrayList<>();
		for (com.leon1236.reforestry.core.genetics.mutations.Mutation mutation : ArboricultureGenetics.getAllMutations()) {
			mutations.add(new MutationDisplay(mutation, TreeSpeciesType.INSTANCE));
		}
		registry.addRecipes(GeneticsJeiRecipeTypes.TREE_MUTATIONS, mutations);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addCraftingStation(ReforestryJeiRecipeTypes.CHARCOAL_PILE, CharcoalBlocks.LOG_PILE.block());
	}

	static Identifier activeSpeciesId(ItemStack stack) {
		var genome = stack.get(ArboricultureDataComponents.TREE_GENOME.type());
		if (genome == null) {
			return null;
		}
		return genome.getActiveAllele(TreeChromosomes.SPECIES).alleleId();
	}
}
