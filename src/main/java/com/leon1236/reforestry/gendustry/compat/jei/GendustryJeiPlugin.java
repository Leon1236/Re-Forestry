package com.leon1236.reforestry.gendustry.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.apiculture.compat.jei.MutationDisplay;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeSources;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.gendustry.client.ScreenAdvancedMutatron;
import com.leon1236.reforestry.gendustry.client.ScreenMutatron;
import com.leon1236.reforestry.gendustry.client.ScreenProducer;
import com.leon1236.reforestry.gendustry.compat.jei.producers.DNAExtractorRecipeCategory;
import com.leon1236.reforestry.gendustry.compat.jei.producers.MutagenRecipeCategory;
import com.leon1236.reforestry.gendustry.compat.jei.producers.ProducerGuiContainerHandler;
import com.leon1236.reforestry.gendustry.compat.jei.producers.ProteinProducerRecipeCategory;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.recipe.DnaRecipe;
import com.leon1236.reforestry.gendustry.recipe.MutagenRecipe;
import com.leon1236.reforestry.gendustry.recipe.ProteinRecipe;

@JeiPlugin
public class GendustryJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("gendustry");
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		if (!gendustryLoaded()) {
			return;
		}
		registration.registerSubtypeInterpreter(GItems.GENE_SAMPLE.item(), new GeneSampleInterpreter());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		if (!gendustryLoaded()) {
			return;
		}
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		IPlatformFluidHelper<?> fluidHelper = jeiHelpers.getPlatformFluidHelper();
		registration.addRecipeCategories(new MutagenRecipeCategory<>(guiHelper, fluidHelper));
		registration.addRecipeCategories(new ProteinProducerRecipeCategory<>(guiHelper, fluidHelper));
		registration.addRecipeCategories(new DNAExtractorRecipeCategory<>(guiHelper, fluidHelper));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!gendustryLoaded()) {
			return;
		}
		registration.addCraftingStation(GendustryJeiRecipeTypes.MUTAGEN_PRODUCER, MutagenRecipeCategory.ICON_STACK);
		registration.addCraftingStation(GendustryJeiRecipeTypes.DNA_EXTRACTOR, DNAExtractorRecipeCategory.ICON_STACK);
		registration.addCraftingStation(GendustryJeiRecipeTypes.PROTEIN_LIQUEFIER, ProteinProducerRecipeCategory.ICON_STACK);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (!gendustryLoaded()) {
			return;
		}
		registration.addRecipes(GendustryJeiRecipeTypes.MUTAGEN_PRODUCER, JeiRecipeSources.collect(MutagenRecipe.class));
		registration.addRecipes(GendustryJeiRecipeTypes.PROTEIN_LIQUEFIER, JeiRecipeSources.collect(ProteinRecipe.class));
		registration.addRecipes(GendustryJeiRecipeTypes.DNA_EXTRACTOR, JeiRecipeSources.collect(DnaRecipe.class));
		registerFluidInfo(registration);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		if (!gendustryLoaded()) {
			return;
		}
		IRecipeType<?>[] mutationTypes = registration.getJeiHelpers().getAllRecipeTypes()
				.filter(type -> type.getRecipeClass() == MutationDisplay.class)
				.toArray(IRecipeType[]::new);
		if (mutationTypes.length > 0) {
			registration.addRecipeClickArea(ScreenMutatron.class, 68, 38, 55, 18, mutationTypes);
			registration.addRecipeClickArea(ScreenAdvancedMutatron.class, 68, 38, 55, 18, mutationTypes);
		}
		registration.addGuiContainerHandler(ScreenProducer.class, new ProducerGuiContainerHandler());
	}

	private static boolean gendustryLoaded() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("gendustry"));
	}

	private static <F> void registerFluidInfo(IRecipeRegistration registration) {
		IPlatformFluidHelper<F> fluidHelper = castFluidHelper(registration.getJeiHelpers().getPlatformFluidHelper());
		IIngredientTypeWithSubtypes<Fluid, F> fluidType = fluidHelper.getFluidIngredientType();
		long bucket = FluidUnits.mbToDroplets(1000);
		registration.addIngredientInfo(
				fluidHelper.create(GFluids.MUTAGEN.getFluid().defaultFluidState().typeHolder(), bucket),
				fluidType,
				Component.translatable("info.reforestry.mutagen"));
		registration.addIngredientInfo(
				fluidHelper.create(GFluids.LIQUID_DNA.getFluid().defaultFluidState().typeHolder(), bucket),
				fluidType,
				Component.translatable("info.reforestry.dna"));
		registration.addIngredientInfo(
				fluidHelper.create(GFluids.PROTEIN.getFluid().defaultFluidState().typeHolder(), bucket),
				fluidType,
				Component.translatable("info.reforestry.protein"));
	}

	@SuppressWarnings("unchecked")
	private static <F> IPlatformFluidHelper<F> castFluidHelper(IPlatformFluidHelper<?> fluidHelper) {
		return (IPlatformFluidHelper<F>) fluidHelper;
	}
}
