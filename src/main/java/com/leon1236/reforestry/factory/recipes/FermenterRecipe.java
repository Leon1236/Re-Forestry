package com.leon1236.reforestry.factory.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.core.recipes.LegacyIngredientCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.recipes.IFermenterRecipe;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record FermenterRecipe(
        Ingredient resource,
        RecipeFluidAmount fluidResource,
        int fermentationValue,
        float modifier,
        Identifier outputId
) implements IFermenterRecipe {
    public static final MapCodec<FermenterRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            LegacyIngredientCodec.CODEC.fieldOf("resource").forGetter(FermenterRecipe::resource),
            RecipeFluidAmount.CODEC.fieldOf("fluidResource").forGetter(FermenterRecipe::fluidResource),
            com.mojang.serialization.Codec.intRange(1, Integer.MAX_VALUE).fieldOf("fermentationValue").forGetter(FermenterRecipe::fermentationValue),
            com.mojang.serialization.Codec.FLOAT.fieldOf("modifier").forGetter(FermenterRecipe::modifier),
            Identifier.CODEC.fieldOf("output").forGetter(FermenterRecipe::outputId)
    ).apply(instance, FermenterRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FermenterRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, FermenterRecipe::resource,
            RecipeFluidAmount.STREAM_CODEC, FermenterRecipe::fluidResource,
            net.minecraft.network.codec.ByteBufCodecs.VAR_INT, FermenterRecipe::fermentationValue,
            net.minecraft.network.codec.ByteBufCodecs.FLOAT, FermenterRecipe::modifier,
            Identifier.STREAM_CODEC, FermenterRecipe::outputId,
            FermenterRecipe::new
    );

    public static final RecipeSerializer<FermenterRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public Ingredient getInputItem() {
        return this.resource;
    }

    @Override
    public FluidVariant getInputFluid() {
        return this.fluidResource.variant();
    }

    @Override
    public long getInputFluidAmount() {
        return this.fluidResource.amountDroplets();
    }

    @Override
    public int getFermentationValue() {
        return this.fermentationValue;
    }

    @Override
    public float getModifier() {
        return this.modifier;
    }

    @Override
    public Fluid getOutput() {
        return BuiltInRegistries.FLUID.get(this.outputId).orElseThrow().value();
    }

    @Override
    public boolean matches(net.minecraft.world.item.ItemStack inputItem, FluidVariant inputFluid, long inputFluidAmount) {
        return this.resource.test(inputItem)
                && !inputFluid.isBlank()
                && inputFluid.getFluid() == getInputFluid().getFluid();
    }

    @Override
    public RecipeSerializer<? extends FermenterRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends FermenterRecipe> getType() {
        return FactoryRecipeTypes.FERMENTER.type();
    }
}
