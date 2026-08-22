package com.leon1236.reforestry.apiculture.compat.jei;

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
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.apiculture.items.ItemCreativeHiveFrame;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

@JeiPlugin
public class ApicultureJeiPlugin implements IModPlugin {
	private static final Identifier BACKGROUND = ReForestry.id("textures/gui/jei/recipes.png");

	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("apiculture");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		IDrawable productsBackground = helper.createDrawable(BACKGROUND, 0, 61 + 30, 162, 61);
		IDrawable mutationsBackground = helper.createDrawable(BACKGROUND, 0, 30, 162, 61);
		IDrawable icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, defaultQueenIcon());
		registration.addRecipeCategories(
				new ProductsRecipeCategory(productsBackground, icon),
				new MutationsRecipeCategory(mutationsBackground, icon)
		);
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registry) {
		ISubtypeInterpreter<ItemStack> interpreter = (stack, context) -> BeeJeiHelper.activeSpeciesId(stack);
		registry.registerSubtypeInterpreter(ApicultureItems.BEE_QUEEN.item(), interpreter);
		registry.registerSubtypeInterpreter(ApicultureItems.BEE_DRONE.item(), interpreter);
		registry.registerSubtypeInterpreter(ApicultureItems.BEE_PRINCESS.item(), interpreter);
		registry.registerSubtypeInterpreter(ApicultureItems.BEE_LARVAE.item(), interpreter);
		registry.registerSubtypeInterpreter(ApicultureItems.FRAME_CREATIVE.item(),
				(stack, context) -> ItemCreativeHiveFrame.hasForceMutations(stack));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		JeiDescriptions.addDescription(registry, "frames",
				ApicultureItems.FRAME_UNTREATED,
				ApicultureItems.FRAME_IMPREGNATED,
				ApicultureItems.FRAME_PROVEN
		);
		JeiDescriptions.addDescription(registry, "apiarist.suit",
				ApicultureItems.APIARIST_HELMET,
				ApicultureItems.APIARIST_CHEST,
				ApicultureItems.APIARIST_LEGS,
				ApicultureItems.APIARIST_BOOTS
		);
		JeiDescriptions.addDescription(registry, ApicultureItems.SCOOP);

		List<ProductRecipe> products = new ArrayList<>();
		for (Identifier speciesId : ApicultureGenetics.getAllSpeciesIds()) {
			IBeeSpecies species = ApicultureGenetics.getSpecies(speciesId);
			if (species.products().isEmpty() && species.specialties().isEmpty()) {
				continue;
			}
			products.add(new ProductRecipe(speciesId, species));
		}
		registry.addRecipes(ApicultureJeiRecipeTypes.BEE_PRODUCTS, products);

		List<MutationDisplay> mutations = new ArrayList<>();
		for (Mutation mutation : ApicultureGenetics.getAllMutations()) {
			mutations.add(new MutationDisplay(mutation));
		}
		registry.addRecipes(ApicultureJeiRecipeTypes.BEE_MUTATIONS, mutations);
	}

	private static ItemStack defaultQueenIcon() {
		for (Identifier speciesId : ApicultureGenetics.getAllSpeciesIds()) {
			return BeeJeiHelper.analyzedStack(ApicultureItems.BEE_QUEEN, speciesId);
		}
		return new ItemStack(ApicultureItems.BEE_QUEEN.item());
	}
}
