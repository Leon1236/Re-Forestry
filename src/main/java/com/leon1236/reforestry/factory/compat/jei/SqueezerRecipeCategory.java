package com.leon1236.reforestry.factory.compat.jei;

import java.util.List;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
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

import com.leon1236.reforestry.api.recipes.ISqueezerRecipe;
import com.leon1236.reforestry.core.compat.jei.ChanceTooltipCallback;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeLayoutHelper;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;
import com.leon1236.reforestry.factory.tiles.TileSqueezer;

public class SqueezerRecipeCategory<F> extends ForestryRecipeCategory<ISqueezerRecipe> {
	private final IDrawableAnimated arrow;
	private final IDrawable tankOverlay;
	private final IDrawable icon;
	private final ICraftingGridHelper craftingGridHelper;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public SqueezerRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.SQUEEZER, 9, 16, 158, 62), "block.reforestry.squeezer");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(FactoryGuiTextures.SQUEEZER, 176, 60, 43, 18);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
		this.tankOverlay = guiHelper.createDrawable(FactoryGuiTextures.SQUEEZER, 176, 0, 16, 58);
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.SQUEEZER).block()));
		this.craftingGridHelper = guiHelper.createCraftingGridHelper();
	}

	@Override
	public IRecipeType<ISqueezerRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.SQUEEZER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ISqueezerRecipe recipe, IFocusGroup focuses) {
		List<IRecipeSlotBuilder> craftingSlots = JeiRecipeLayoutHelper.layoutSlotGrid(
				builder, RecipeIngredientRole.INPUT, 3, 3, 8, 5, 18);
		JeiRecipeLayoutHelper.setCraftingItems(craftingSlots, recipe.getInputs(), 3, 3, this.craftingGridHelper);

		if (!recipe.getRemnants().isEmpty()) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, 88, 44)
					.add(recipe.getRemnants())
					.addRichTooltipCallback(new ChanceTooltipCallback(recipe.getRemnantsChance()));
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 2)
				.setFluidRenderer(TileSqueezer.TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getOutputFluid().getFluid().defaultFluidState().typeHolder(),
						recipe.getOutputAmount()));
	}

	@Override
	public void draw(ISqueezerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.arrow.draw(graphics, 67, 25);
	}
}
