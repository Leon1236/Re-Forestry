package com.leon1236.reforestry.factory.compat.jei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.api.recipes.IFabricatorSmeltingRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeLayoutHelper;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeSources;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class FabricatorRecipeCategory<F> extends ForestryRecipeCategory<IFabricatorRecipe> {
	private final IDrawable icon;
	private final ICraftingGridHelper craftingGridHelper;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public FabricatorRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.FABRICATOR, 20, 16, 136, 54), "block.reforestry.fabricator");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.FABRICATOR).block()));
		this.craftingGridHelper = guiHelper.createCraftingGridHelper();
	}

	@Override
	public IRecipeType<IFabricatorRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.FABRICATOR;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, IFabricatorRecipe recipe, IFocusGroup focuses) {
		Fluid recipeFluid = recipe.getResultFluid().getFluid();
		long amount = recipe.getResultFluidAmount();
		long capacity = Math.max(amount, FluidUnits.mbToDroplets(2000));

		builder.addSlot(RecipeIngredientRole.INPUT, 6, 32)
				.setFluidRenderer(capacity, false, 16, 16)
				.add(this.fluidType, this.fluidHelper.create(recipeFluid.defaultFluidState().typeHolder(), amount));

		CraftingRecipe craftingGridRecipe = recipe.getCraftingGridRecipe();
		List<IRecipeSlotBuilder> craftingSlots = JeiRecipeLayoutHelper.layoutSlotGrid(
				builder, RecipeIngredientRole.INPUT, 3, 3, 47, 1, 18);
		JeiRecipeLayoutHelper.setCraftingItems(craftingSlots, craftingGridRecipe, this.craftingGridHelper);

		List<Ingredient> smeltingInput = smeltingInputs().getOrDefault(recipeFluid, List.of());
		if (!smeltingInput.isEmpty()) {
			IRecipeSlotBuilder smeltingSlot = builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 6, 5);
			for (Ingredient ingredient : smeltingInput) {
				smeltingSlot.add(ingredient);
			}
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 37)
				.add(VanillaTypes.ITEM_STACK, recipe.getResultStack());
	}

	private static Map<Fluid, List<Ingredient>> smeltingInputs() {
		Map<Fluid, List<Ingredient>> smeltingInputs = new HashMap<>();
		for (IFabricatorSmeltingRecipe smelting : JeiRecipeSources.collect(IFabricatorSmeltingRecipe.class)) {
			Fluid fluid = smelting.getResultFluid().getFluid();
			smeltingInputs.computeIfAbsent(fluid, ignored -> new ArrayList<>()).add(smelting.getInput());
		}
		return smeltingInputs;
	}
}
