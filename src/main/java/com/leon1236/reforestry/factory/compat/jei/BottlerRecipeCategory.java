package com.leon1236.reforestry.factory.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
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

import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;
import com.leon1236.reforestry.factory.recipes.BottlerRecipe;
import com.leon1236.reforestry.factory.tiles.TileBottler;

public class BottlerRecipeCategory<F> extends ForestryRecipeCategory<BottlerRecipe> {
	private final IDrawableStatic slot;
	private final IDrawableStatic tank;
	private final IDrawableStatic arrowDown;
	private final IDrawable tankOverlay;
	private final IDrawable icon;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public BottlerRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createBlankDrawable(62, 60), "block.reforestry.bottler");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		this.slot = guiHelper.getSlotDrawable();
		this.tank = guiHelper.createDrawable(FactoryGuiTextures.BOTTLER, 79, 13, 18, 60);
		this.arrowDown = guiHelper.createDrawable(FactoryGuiTextures.BOTTLER, 20, 25, 12, 8);
		this.tankOverlay = guiHelper.createDrawable(FactoryGuiTextures.BOTTLER, 176, 0, 16, 58);
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.BOTTLER).block()));
	}

	@Override
	public IRecipeType<BottlerRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.BOTTLER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BottlerRecipe recipe, IFocusGroup focuses) {
		F fluidIngredient = this.fluidHelper.create(
				recipe.fluid.getFluid().defaultFluidState().typeHolder(),
				recipe.fluidAmount);

		if (recipe.fillRecipe) {
			builder.addSlot(RecipeIngredientRole.INPUT, 45, 1)
					.add(recipe.inputStack);
			builder.addSlot(RecipeIngredientRole.OUTPUT, 45, 43)
					.add(recipe.outputStack);
			builder.addSlot(RecipeIngredientRole.INPUT, 23, 1)
					.setFluidRenderer(TileBottler.TANK_CAPACITY, false, 16, 58)
					.setOverlay(this.tankOverlay, 0, 0)
					.add(this.fluidType, fluidIngredient);
		} else {
			builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
					.add(recipe.inputStack);
			builder.addSlot(RecipeIngredientRole.OUTPUT, 1, 43)
					.add(recipe.outputStack);
			builder.addSlot(RecipeIngredientRole.OUTPUT, 23, 1)
					.setFluidRenderer(TileBottler.TANK_CAPACITY, false, 16, 58)
					.setOverlay(this.tankOverlay, 0, 0)
					.add(this.fluidType, fluidIngredient);
		}
	}

	@Override
	public void draw(BottlerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);

		if (recipe.fillRecipe) {
			this.slot.draw(graphics, 44, 0);
			this.arrowDown.draw(graphics, 47, 26);
			this.slot.draw(graphics, 44, 42);
		} else {
			this.slot.draw(graphics, 0, 0);
			this.arrowDown.draw(graphics, 3, 26);
			this.slot.draw(graphics, 0, 42);
		}

		this.tank.draw(graphics, 22, 0);
	}
}
