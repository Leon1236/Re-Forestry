package com.leon1236.reforestry.lepidopterology.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyRecipes;

public class ButterflyMatingRecipe extends CustomRecipe {
	public static final ButterflyMatingRecipe INSTANCE = new ButterflyMatingRecipe();
	public static final MapCodec<ButterflyMatingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(CraftingBookCategory.CODEC.optionalFieldOf("category", CraftingBookCategory.MISC)
							.forGetter(recipe -> CraftingBookCategory.MISC))
					.apply(instance, category -> INSTANCE));
	public static final StreamCodec<RegistryFriendlyByteBuf, ButterflyMatingRecipe> STREAM_CODEC =
			StreamCodec.unit(INSTANCE);
	public static final RecipeSerializer<ButterflyMatingRecipe> SERIALIZER =
			new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	private ButterflyMatingRecipe() {
	}

	@Override
	public boolean matches(CraftingInput grid, Level level) {
		boolean hasButterfly = false;
		boolean hasSerum = false;
		for (int i = 0; i < grid.size(); i++) {
			ItemStack stack = grid.getItem(i);
			if (stack.isEmpty()) {
				continue;
			}
			ILifeStage stage = IIndividualHandlerItem.getLifeStage(stack);
			if (!(stage instanceof ButterflyLifeStage butterflyStage)) {
				return false;
			}
			if (butterflyStage == ButterflyLifeStage.BUTTERFLY) {
				if (hasButterfly) {
					return false;
				}
				hasButterfly = true;
			} else if (butterflyStage == ButterflyLifeStage.SERUM) {
				if (hasSerum) {
					return false;
				}
				hasSerum = true;
			} else {
				return false;
			}
		}
		return hasButterfly && hasSerum;
	}

	@Override
	public ItemStack assemble(CraftingInput grid) {
		IButterfly butterfly = null;
		IIndividual serum = null;
		for (int i = 0; i < grid.size(); i++) {
			ItemStack stack = grid.getItem(i);
			IIndividual individual = IIndividualHandlerItem.getIndividual(stack);
			if (individual == null) {
				continue;
			}
			ILifeStage stage = IIndividualHandlerItem.getLifeStage(stack);
			if (stage == ButterflyLifeStage.BUTTERFLY) {
				butterfly = (IButterfly) individual;
			} else if (stage == ButterflyLifeStage.SERUM) {
				serum = individual;
			}
		}
		if (butterfly != null && serum != null) {
			IButterfly copy = butterfly.copy();
			copy.setMate(serum.getGenome());
			return copy.createStack(ButterflyLifeStage.BUTTERFLY);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public RecipeSerializer<ButterflyMatingRecipe> getSerializer() {
		return LepidopterologyRecipes.MATING;
	}
}
