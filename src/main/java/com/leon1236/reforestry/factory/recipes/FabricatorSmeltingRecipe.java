package com.leon1236.reforestry.factory.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.core.recipes.LegacyIngredientCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.api.recipes.IFabricatorSmeltingRecipe;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record FabricatorSmeltingRecipe(
        Ingredient resource,
        RecipeFluidAmount product,
        int meltingPoint
) implements IFabricatorSmeltingRecipe {
    public static final MapCodec<FabricatorSmeltingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            LegacyIngredientCodec.CODEC.fieldOf("resource").forGetter(FabricatorSmeltingRecipe::resource),
            RecipeFluidAmount.CODEC.fieldOf("product").forGetter(FabricatorSmeltingRecipe::product),
            com.mojang.serialization.Codec.intRange(1, Integer.MAX_VALUE).fieldOf("melting").forGetter(FabricatorSmeltingRecipe::meltingPoint)
    ).apply(instance, FabricatorSmeltingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FabricatorSmeltingRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, FabricatorSmeltingRecipe::resource,
            RecipeFluidAmount.STREAM_CODEC, FabricatorSmeltingRecipe::product,
            net.minecraft.network.codec.ByteBufCodecs.VAR_INT, FabricatorSmeltingRecipe::meltingPoint,
            FabricatorSmeltingRecipe::new
    );

    public static final RecipeSerializer<FabricatorSmeltingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public Ingredient getInput() {
        return this.resource;
    }

    @Override
    public int getMeltingPoint() {
        return this.meltingPoint;
    }

    @Override
    public FluidVariant getResultFluid() {
        return this.product.variant();
    }

    @Override
    public long getResultFluidAmount() {
        return this.product.amountDroplets();
    }

    @Override
    public RecipeSerializer<? extends FabricatorSmeltingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends FabricatorSmeltingRecipe> getType() {
        return FactoryRecipeTypes.FABRICATOR_SMELTING.type();
    }
}
