package com.leon1236.reforestry.factory.compat.jei;

import java.util.List;
import java.util.Optional;

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
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.recipes.ICarpenterRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeLayoutHelper;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;
import com.leon1236.reforestry.factory.tiles.TileCarpenter;

public class CarpenterRecipeCategory<F> extends ForestryRecipeCategory<ICarpenterRecipe> {
	private final IDrawable icon;
	private final IDrawableAnimated arrow;
	private final IDrawable tankOverlay;
	private final ICraftingGridHelper craftingGridHelper;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public CarpenterRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.CARPENTER, 9, 16, 158, 61), "block.reforestry.carpenter");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.CARPENTER).block()));
		this.craftingGridHelper = guiHelper.createCraftingGridHelper();
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(FactoryGuiTextures.CARPENTER, 176, 59, 4, 17);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.BOTTOM, false);
		this.tankOverlay = guiHelper.createDrawable(FactoryGuiTextures.CARPENTER, 176, 0, 16, 58);
	}

	@Override
	public IRecipeType<ICarpenterRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.CARPENTER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ICarpenterRecipe recipe, IFocusGroup focuses) {
		CraftingRecipe craftingGridRecipe = recipe.getCraftingGridRecipe();
		ItemStack result = recipe.getResultStack();

		ItemStack preview = result.copy();
		preview.setCount(1);
		builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 71, 35)
				.add(VanillaTypes.ITEM_STACK, preview);

		builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 40)
				.add(VanillaTypes.ITEM_STACK, result);

		List<IRecipeSlotBuilder> craftingSlots = JeiRecipeLayoutHelper.layoutSlotGrid(
				builder, RecipeIngredientRole.INPUT, 3, 3, 1, 4, 18);
		JeiRecipeLayoutHelper.setCraftingItems(craftingSlots, craftingGridRecipe, this.craftingGridHelper);

		IRecipeSlotBuilder boxSlot = builder.addSlot(RecipeIngredientRole.INPUT, 74, 4);
		Optional.ofNullable(recipe.getBox()).ifPresent(boxSlot::add);

		IRecipeSlotBuilder tankSlot = builder.addSlot(RecipeIngredientRole.INPUT, 141, 1)
				.setFluidRenderer(TileCarpenter.TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0);

		long amount = recipe.getInputFluidAmount();
		if (amount > 0 && !recipe.getInputFluid().isBlank()) {
			tankSlot.add(this.fluidType, this.fluidHelper.create(
					recipe.getInputFluid().getFluid().defaultFluidState().typeHolder(),
					amount));
		}
	}

	@Override
	public void draw(ICarpenterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.arrow.draw(graphics, 89, 34);
	}
}
