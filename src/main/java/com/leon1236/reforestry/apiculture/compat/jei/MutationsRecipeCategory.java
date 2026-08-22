package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiHelper;

public class MutationsRecipeCategory extends ForestryRecipeCategory<MutationDisplay> {
	private static final int SPECIES_SLOTS_Y = 16;
	private static final int SPECIES_SLOT_0_X = 19;
	private static final int SPECIES_SLOT_1_X = 72;
	private static final int SPECIES_SLOT_2_X = 126;

	private final IDrawable icon;
	private final IRecipeType<MutationDisplay> recipeType;
	private final ISpeciesType<?, ?> speciesType;

	public MutationsRecipeCategory(
			IDrawable background,
			IDrawable icon,
			IRecipeType<MutationDisplay> recipeType,
			String titleKey,
			ISpeciesType<?, ?> speciesType
	) {
		super(background, titleKey);
		this.icon = icon;
		this.recipeType = recipeType;
		this.speciesType = speciesType;
	}

	@Override
	public IRecipeType<MutationDisplay> getRecipeType() {
		return recipeType;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, MutationDisplay recipe, IFocusGroup focuses) {
		IIngredientAcceptor<?> inputs = builder.addInvisibleIngredients(RecipeIngredientRole.INPUT);
		List<ItemStack> inputStacks = new ArrayList<>(recipe.firstInputs);
		inputStacks.addAll(recipe.secondInputs);
		inputs.addItemStacks(inputStacks);
		builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStacks(recipe.resultOutputs);

		builder.addSlot(RecipeIngredientRole.INPUT, SPECIES_SLOT_0_X, SPECIES_SLOTS_Y)
				.add(recipe.firstParent);
		builder.addSlot(RecipeIngredientRole.INPUT, SPECIES_SLOT_1_X, SPECIES_SLOTS_Y)
				.add(recipe.secondParent);
		builder.addSlot(RecipeIngredientRole.OUTPUT, SPECIES_SLOT_2_X, SPECIES_SLOTS_Y)
				.add(recipe.result);
	}

	@Override
	public void draw(MutationDisplay recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		GeneticsJeiHelper.drawCentered(graphics, speciesType.getSpecies(recipe.firstParentId()).getDisplayName(), SPECIES_SLOT_0_X + 9, SPECIES_SLOTS_Y + 22, 0xffffffff);
		GeneticsJeiHelper.drawCentered(graphics, speciesType.getSpecies(recipe.secondParentId()).getDisplayName(), SPECIES_SLOT_1_X + 9, SPECIES_SLOTS_Y + 22, 0xffffffff);
		GeneticsJeiHelper.drawCentered(graphics, speciesType.getSpecies(recipe.resultId()).getDisplayName(), SPECIES_SLOT_2_X + 9, SPECIES_SLOTS_Y + 22, 0xffffffff);

		String percentageString = GeneticsJeiHelper.formatPercentage(recipe.mutation.getChance()) + "%";
		List<Component> conditions = recipe.mutation.getSpecialConditions();
		if (conditions.isEmpty()) {
			GeneticsJeiHelper.drawCentered(graphics, Component.literal(percentageString), 105, 12, 0xffffff);
		} else {
			GeneticsJeiHelper.drawCentered(graphics, Component.literal("[" + percentageString + "]"), 105, 12, 0xffffff);
		}
	}

	@Override
	public void getTooltip(ITooltipBuilder tooltip, MutationDisplay recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
		List<Component> conditions = recipe.mutation.getSpecialConditions();
		if (!conditions.isEmpty() && mouseX >= 90 && mouseX <= 120 && mouseY >= 11 && mouseY <= 19) {
			tooltip.addAll(conditions);
		}
	}
}
