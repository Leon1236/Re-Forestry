package com.leon1236.reforestry.factory.compat.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.fuels.FermenterFuel;
import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.api.recipes.IFermenterRecipe;
import com.leon1236.reforestry.api.recipes.IVariableFermentable;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.recipes.CraftingPatternHelper;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class FermenterRecipeCategory<F> extends ForestryRecipeCategory<IFermenterRecipe> {
	private static final long TANK_CAPACITY = FluidUnits.mbToDroplets(3000);

	private final IDrawableAnimated progressBar0;
	private final IDrawableAnimated progressBar1;
	private final IDrawable tankOverlay;
	private final IDrawable icon;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public FermenterRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.FERMENTER, 34, 18, 108, 60), "block.reforestry.fermenter");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		IDrawableStatic progressBarDrawable0 = guiHelper.createDrawable(FactoryGuiTextures.FERMENTER, 176, 60, 4, 18);
		this.progressBar0 = guiHelper.createAnimatedDrawable(progressBarDrawable0, 40, IDrawableAnimated.StartDirection.BOTTOM, false);
		IDrawableStatic progressBarDrawable1 = guiHelper.createDrawable(FactoryGuiTextures.FERMENTER, 176, 78, 4, 18);
		this.progressBar1 = guiHelper.createAnimatedDrawable(progressBarDrawable1, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
		this.tankOverlay = guiHelper.createDrawable(FactoryGuiTextures.FERMENTER, 192, 0, 16, 58);
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.FERMENTER).block()));
	}

	@Override
	public IRecipeType<IFermenterRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.FERMENTER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, IFermenterRecipe recipe, IFocusGroup focuses) {
		IRecipeSlotBuilder ingredientInputSlot = builder.addSlot(RecipeIngredientRole.INPUT, 51, 5)
				.add(recipe.getInputItem());

		List<ItemStack> fuelInputs = fermenterFuels().stream()
				.map(FermenterFuel::item)
				.map(ItemStack::copy)
				.toList();
		builder.addSlot(RecipeIngredientRole.INPUT, 41, 39)
				.addItemStacks(fuelInputs);

		long inputAmount = FluidUnits.mbToDroplets(recipe.getFermentationValue());
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.setFluidRenderer(TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getInputFluid().getFluid().defaultFluidState().typeHolder(),
						inputAmount));

		int baseAmount = Math.round(recipe.getFermentationValue() * recipe.getModifier());
		List<F> outputs = new ArrayList<>();
		for (ItemStack fermentable : CraftingPatternHelper.stacksFromIngredient(recipe.getInputItem())) {
			float amount = baseAmount;
			if (fermentable.getItem() instanceof IVariableFermentable variableFermentable) {
				amount *= variableFermentable.getFermentationModifier(fermentable);
			}
			outputs.add(this.fluidHelper.create(
					recipe.getOutput().defaultFluidState().typeHolder(),
					FluidUnits.mbToDroplets(Math.round(amount))));
		}

		IRecipeSlotBuilder fluidOutputSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 1)
				.setFluidRenderer(TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0);
		for (F output : outputs) {
			fluidOutputSlot.add(this.fluidType, output);
		}

		builder.createFocusLink(ingredientInputSlot, fluidOutputSlot);
	}

	@Override
	public void draw(IFermenterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.progressBar0.draw(graphics, 40, 14);
		this.progressBar1.draw(graphics, 64, 28);
	}

	private static Collection<FermenterFuel> fermenterFuels() {
		Map<ItemStack, FermenterFuel> fuels = FuelManager.fermenterFuel;
		return fuels == null ? List.of() : fuels.values();
	}
}
