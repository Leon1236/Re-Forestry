package com.leon1236.reforestry.gendustry.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import com.leon1236.reforestry.api.recipes.LegacyIngredientCodec;
import com.leon1236.reforestry.gendustry.features.GRecipeTypes;

public class ProteinRecipe extends ProcessorRecipe {
	public static final MapCodec<ProteinRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LegacyIngredientCodec.CODEC.fieldOf("ingredient").forGetter(ProteinRecipe::getIngredient),
			Codec.INT.fieldOf("amount").forGetter(ProteinRecipe::getAmount)
	).apply(instance, ProteinRecipe::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, ProteinRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, ProteinRecipe::getIngredient,
			ByteBufCodecs.VAR_INT, ProteinRecipe::getAmount,
			ProteinRecipe::new
	);

	public static final RecipeSerializer<ProteinRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	private final Ingredient ingredient;

	public ProteinRecipe(Ingredient ingredient, int amount) {
		super(amount);
		this.ingredient = ingredient;
	}

	public Ingredient getIngredient() {
		return this.ingredient;
	}

	@Override
	public boolean isIngredient(ItemStack stack) {
		return this.ingredient.test(stack);
	}

	@Override
	public RecipeSerializer<? extends ProteinRecipe> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public RecipeType<? extends ProteinRecipe> getType() {
		return GRecipeTypes.PROTEIN.type();
	}
}
