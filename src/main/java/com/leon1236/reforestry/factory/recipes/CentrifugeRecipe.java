package com.leon1236.reforestry.factory.recipes;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.core.recipes.LegacyIngredientCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import com.leon1236.reforestry.api.core.Product;
import com.leon1236.reforestry.api.recipes.ICentrifugeRecipe;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record CentrifugeRecipe(Ingredient input, int processingTime, List<Product> products) implements ICentrifugeRecipe {
    public static final MapCodec<CentrifugeRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            LegacyIngredientCodec.CODEC.fieldOf("input").forGetter(CentrifugeRecipe::input),
            Codec.intRange(1, Integer.MAX_VALUE).optionalFieldOf("time", 20).forGetter(CentrifugeRecipe::processingTime),
            Product.CODEC.listOf().fieldOf("products").forGetter(CentrifugeRecipe::products)
    ).apply(instance, CentrifugeRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CentrifugeRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CentrifugeRecipe::input,
            ByteBufCodecs.VAR_INT, CentrifugeRecipe::processingTime,
            Product.STREAM_CODEC.apply(ByteBufCodecs.list()), CentrifugeRecipe::products,
            CentrifugeRecipe::new
    );

    public static final RecipeSerializer<CentrifugeRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public Ingredient getInput() {
        return input;
    }

    @Override
    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public List<ItemStack> getProducts(RandomSource random, double outputMult) {
        List<ItemStack> result = new ArrayList<>();
        for (Product product : products) {
            double probability = product.chance() * outputMult;
            if (probability >= 1.0 || random.nextFloat() < probability) {
                result.add(product.createStack());
            }
        }
        return result;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public RecipeSerializer<? extends CentrifugeRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends CentrifugeRecipe> getType() {
        return FactoryRecipeTypes.CENTRIFUGE.type();
    }
}
