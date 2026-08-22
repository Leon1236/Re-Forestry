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
import com.leon1236.reforestry.gendustry.recipe.MutagenRecipe;

public class MutagenRecipeCategory<F> extends ProducerRecipeCategory<MutagenRecipe, F> {
	public static final ItemStack ICON_STACK = new ItemStack(GBlocks.MACHINE.get(GendustryMachineType.MUTAGEN_PRODUCER).block());

	public MutagenRecipeCategory(IGuiHelper helper, IPlatformFluidHelper<F> fluidHelper) {
		super(helper, fluidHelper, GendustryMachineType.MUTAGEN_PRODUCER, ICON_STACK);
	}

	@Override
	public IRecipeType<MutagenRecipe> getRecipeType() {
		return GendustryJeiRecipeTypes.MUTAGEN_PRODUCER;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, MutagenRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 23).add(recipe.getIngredient());
		addFluidTank(builder, GFluids.MUTAGEN.getFluid(), recipe.getAmount());
	}
}
