package com.leon1236.reforestry.factory.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.core.recipes.LegacyIngredientCodec;
import com.leon1236.reforestry.core.recipes.RecipeItemAmount;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import com.leon1236.reforestry.api.recipes.IMoistenerRecipe;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record MoistenerRecipe(Ingredient resource, RecipeItemAmount product, int timePerItem) implements IMoistenerRecipe {
    public static final MapCodec<MoistenerRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            LegacyIngredientCodec.CODEC.fieldOf("resource").forGetter(MoistenerRecipe::resource),
            RecipeItemAmount.CODEC.fieldOf("product").forGetter(MoistenerRecipe::product),
            com.mojang.serialization.Codec.intRange(1, Integer.MAX_VALUE).fieldOf("time").forGetter(MoistenerRecipe::timePerItem)
    ).apply(instance, MoistenerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, MoistenerRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, MoistenerRecipe::resource,
            RecipeItemAmount.STREAM_CODEC, MoistenerRecipe::product,
            net.minecraft.network.codec.ByteBufCodecs.VAR_INT, MoistenerRecipe::timePerItem,
            MoistenerRecipe::new
    );

    public static final RecipeSerializer<MoistenerRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public int getTimePerItem() {
        return this.timePerItem;
    }

    @Override
    public Ingredient getInput() {
        return this.resource;
    }

    @Override
    public ItemStack getProduct() {
        return this.product.toStack();
    }

    @Override
    public RecipeSerializer<? extends MoistenerRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends MoistenerRecipe> getType() {
        return FactoryRecipeTypes.MOISTENER.type();
    }
}
