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

public class MutagenRecipe extends ProcessorRecipe {
	public static final MapCodec<MutagenRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LegacyIngredientCodec.CODEC.fieldOf("ingredient").forGetter(MutagenRecipe::getIngredient),
			Codec.INT.fieldOf("amount").forGetter(MutagenRecipe::getAmount)
	).apply(instance, MutagenRecipe::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, MutagenRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, MutagenRecipe::getIngredient,
			ByteBufCodecs.VAR_INT, MutagenRecipe::getAmount,
			MutagenRecipe::new
	);

	public static final RecipeSerializer<MutagenRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	private final Ingredient ingredient;

	public MutagenRecipe(Ingredient ingredient, int amount) {
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
	public RecipeSerializer<? extends MutagenRecipe> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public RecipeType<? extends MutagenRecipe> getType() {
		return GRecipeTypes.MUTAGEN.type();
	}
}
