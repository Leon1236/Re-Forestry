package com.leon1236.reforestry.gendustry.compat.jei.producers;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.gendustry.block.GendustryMachineType;
import com.leon1236.reforestry.gendustry.compat.jei.GendustryJeiRecipeTypes;
import com.leon1236.reforestry.gendustry.features.GBlocks;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.recipe.ProteinRecipe;

public class ProteinProducerRecipeCategory<F> extends ProducerRecipeCategory<ProteinRecipe, F> {
	public static final ItemStack ICON_STACK = new ItemStack(GBlocks.MACHINE.get(GendustryMachineType.PROTEIN_LIQUEFIER).block());

	public ProteinProducerRecipeCategory(IGuiHelper helper, IPlatformFluidHelper<F> fluidHelper) {
		super(helper, fluidHelper, GendustryMachineType.PROTEIN_LIQUEFIER, ICON_STACK);
	}

	@Override
	public IRecipeType<ProteinRecipe> getRecipeType() {
		return GendustryJeiRecipeTypes.PROTEIN_LIQUEFIER;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ProteinRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 23).add(recipe.getIngredient());
		addFluidTank(builder, GFluids.PROTEIN.getFluid(), recipe.getAmount());
	}
}
