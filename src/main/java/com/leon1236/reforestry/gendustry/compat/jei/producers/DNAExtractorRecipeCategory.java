package com.leon1236.reforestry.gendustry.compat.jei.producers;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.compat.jei.ChanceTooltipCallback;
import com.leon1236.reforestry.gendustry.block.GendustryMachineType;
import com.leon1236.reforestry.gendustry.compat.jei.GendustryJeiRecipeTypes;
import com.leon1236.reforestry.gendustry.features.GBlocks;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.item.GendustryResourceType;
import com.leon1236.reforestry.gendustry.recipe.DnaRecipe;

public class DNAExtractorRecipeCategory<F> extends ProducerRecipeCategory<DnaRecipe, F> {
	public static final ItemStack ICON_STACK = new ItemStack(GBlocks.MACHINE.get(GendustryMachineType.DNA_EXTRACTOR).block());

	private final IDrawable labwareSlot;

	public DNAExtractorRecipeCategory(IGuiHelper helper, IPlatformFluidHelper<F> fluidHelper) {
		super(helper, fluidHelper, GendustryMachineType.DNA_EXTRACTOR, ICON_STACK);
		this.labwareSlot = createLabwareSlot(helper);
	}

	@Override
	public IRecipeType<DnaRecipe> getRecipeType() {
		return GendustryJeiRecipeTypes.DNA_EXTRACTOR;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, DnaRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 23)
				.add(recipe.getSpeciesType().getDefaultSpecies().createStack(recipe.getStage()));
		addFluidTank(builder, GFluids.LIQUID_DNA.getFluid(), recipe.getAmount());
		builder.addSlot(RecipeIngredientRole.INPUT, 51, 1)
				.add(new ItemStack(GItems.RESOURCE.item(GendustryResourceType.LABWARE)))
				.addRichTooltipCallback(new ChanceTooltipCallback(0.1f));
	}

	@Override
	public void draw(DnaRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.labwareSlot.draw(graphics, 50, 0);
	}
}
