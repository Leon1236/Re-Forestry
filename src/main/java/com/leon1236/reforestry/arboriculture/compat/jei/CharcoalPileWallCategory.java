package com.leon1236.reforestry.arboriculture.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.ICharcoalPileWall;
import com.leon1236.reforestry.arboriculture.features.CharcoalBlocks;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.features.CoreItems;

public class CharcoalPileWallCategory extends ForestryRecipeCategory<ICharcoalPileWall> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/jei/recipes.png");

	private final IDrawable slot;
	private final IDrawableStatic arrow;
	private final IDrawableAnimated arrowAnimated;
	private final IDrawableStatic flame;
	private final IDrawableAnimated flameAnimated;
	private final IDrawable icon;

	public CharcoalPileWallCategory(IGuiHelper helper) {
		super(helper.createBlankDrawable(120, 38), "for.jei.charcoal.pile");
		this.arrow = helper.createDrawable(TEXTURE, 0, 14, 22, 16);
		IDrawableStatic arrowOverlay = helper.createDrawable(TEXTURE, 22, 14, 22, 16);
		this.arrowAnimated = helper.createAnimatedDrawable(arrowOverlay, 160, IDrawableAnimated.StartDirection.LEFT, false);
		this.flame = helper.createDrawable(TEXTURE, 0, 0, 14, 14);
		IDrawableStatic flameOverlay = helper.createDrawable(TEXTURE, 14, 0, 14, 14);
		this.flameAnimated = helper.createAnimatedDrawable(flameOverlay, 260, IDrawableAnimated.StartDirection.TOP, true);
		this.slot = helper.getSlotDrawable();
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(CharcoalBlocks.LOG_PILE.block()));
	}

	@Override
	public IRecipeType<ICharcoalPileWall> getRecipeType() {
		return ReforestryJeiRecipeTypes.CHARCOAL_PILE;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void draw(ICharcoalPileWall recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.flame.draw(graphics, 52, 0);
		this.flameAnimated.draw(graphics, 52, 0);
		this.arrow.draw(graphics, 50, 16);
		this.arrowAnimated.draw(graphics, 50, 16);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ICharcoalPileWall recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 17)
				.setBackground(this.slot, -1, -1)
				.addItemStacks(recipe.getDisplayItems());

		builder.addSlot(RecipeIngredientRole.INPUT, 21, 17)
				.setBackground(this.slot, -1, -1)
				.add(new ItemStack(CharcoalBlocks.LOG_PILE.block()));

		int amount = 9 + recipe.getCharcoalAmount();

		builder.addSlot(RecipeIngredientRole.OUTPUT, 85, 17)
				.setBackground(this.slot, -1, -1)
				.add(new ItemStack(Items.CHARCOAL, amount));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 105, 17)
				.setBackground(this.slot, -1, -1)
				.add(new ItemStack(CoreItems.ASH.item(), amount / 4));
	}
}
