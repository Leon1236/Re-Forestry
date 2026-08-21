package com.leon1236.reforestry.gendustry.compat.jei.producers;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.RecipeIngredientRole;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.gendustry.block.GendustryMachineType;
import com.leon1236.reforestry.gendustry.blockentity.ProducerBlockEntity;
import com.leon1236.reforestry.gendustry.client.GendustryGuiTextures;
import com.leon1236.reforestry.gendustry.features.GBlocks;
import com.leon1236.reforestry.gendustry.recipe.ProcessorRecipe;

public abstract class ProducerRecipeCategory<T extends ProcessorRecipe, F> extends ForestryRecipeCategory<T> {
	private final IDrawableAnimated arrow;
	private final IDrawable tankOverlay;
	private final IDrawable icon;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	protected ProducerRecipeCategory(IGuiHelper helper, IPlatformFluidHelper<F> fluidHelper, GendustryMachineType type, ItemStack stack) {
		super(helper.createDrawable(GendustryGuiTextures.PROCESSOR, 13, 18, 151, 60),
				GBlocks.MACHINE.get(type).block().getDescriptionId());
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		IDrawableStatic arrowDrawable = helper.createDrawable(GendustryGuiTextures.PROCESSOR, 176, 60, 55, 18);
		this.arrow = helper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
		this.tankOverlay = helper.createDrawable(GendustryGuiTextures.PROCESSOR, 176, 0, 16, 58);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, stack);
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.arrow.draw(graphics, 35, 23);
	}

	protected void addFluidTank(IRecipeLayoutBuilder builder, Fluid fluid, int amountMb) {
		builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 1)
				.setFluidRenderer(ProducerBlockEntity.TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0)
				.add(this.fluidType, this.fluidHelper.create(
						fluid.defaultFluidState().typeHolder(),
						FluidUnits.mbToDroplets(amountMb)));
	}

	protected IDrawable createLabwareSlot(IGuiHelper helper) {
		return helper.createDrawable(GendustryGuiTextures.PROCESSOR, 176, 78, 18, 18);
	}
}
