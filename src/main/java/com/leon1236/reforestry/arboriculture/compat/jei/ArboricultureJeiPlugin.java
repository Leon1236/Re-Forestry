package com.leon1236.reforestry.arboriculture.compat.jei;

import org.jetbrains.annotations.Nullable;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.features.CharcoalBlocks;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;

@JeiPlugin
public class ArboricultureJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("arboriculture");
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration registry) {
		ISubtypeInterpreter<ItemStack> interpreter = (stack, context) -> activeSpeciesId(stack);
		registry.registerSubtypeInterpreter(ArboricultureItems.SAPLING.item(), interpreter);
		registry.registerSubtypeInterpreter(ArboricultureItems.POLLEN_FERTILE.item(), interpreter);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new CharcoalPileWallCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		JeiDescriptions.addDescription(registry, ArboricultureItems.GRAFTER);
		JeiDescriptions.addDescription(registry, ArboricultureItems.GRAFTER_PROVEN);
		registry.addRecipes(ReforestryJeiRecipeTypes.CHARCOAL_PILE, CharcoalManager.get().getWalls());
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addCraftingStation(ReforestryJeiRecipeTypes.CHARCOAL_PILE, CharcoalBlocks.LOG_PILE.block());
	}

	@Nullable
	static Identifier activeSpeciesId(ItemStack stack) {
		IGenome genome = stack.get(ArboricultureDataComponents.TREE_GENOME.type());
		if (genome == null) {
			return null;
		}
		return genome.getActiveAllele(TreeChromosomes.SPECIES).alleleId();
	}
}
