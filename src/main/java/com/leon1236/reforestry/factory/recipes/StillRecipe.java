package com.leon1236.reforestry.factory.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.api.recipes.IStillRecipe;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record StillRecipe(RecipeFluidAmount input, RecipeFluidAmount output, int time) implements IStillRecipe {
    public static final MapCodec<StillRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RecipeFluidAmount.CODEC.fieldOf("input").forGetter(StillRecipe::input),
            RecipeFluidAmount.CODEC.fieldOf("output").forGetter(StillRecipe::output),
            com.mojang.serialization.Codec.intRange(1, Integer.MAX_VALUE).fieldOf("time").forGetter(StillRecipe::time)
    ).apply(instance, StillRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, StillRecipe> STREAM_CODEC = StreamCodec.composite(
            RecipeFluidAmount.STREAM_CODEC, StillRecipe::input,
            RecipeFluidAmount.STREAM_CODEC, StillRecipe::output,
            net.minecraft.network.codec.ByteBufCodecs.VAR_INT, StillRecipe::time,
            StillRecipe::new
    );

    public static final RecipeSerializer<StillRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public int getCyclesPerUnit() {
        return this.time;
    }

    @Override
    public FluidVariant getInputFluid() {
        return this.input.variant();
    }

    @Override
    public long getInputAmount() {
        return this.input.amountDroplets();
    }

    @Override
    public FluidVariant getOutputFluid() {
        return this.output.variant();
    }

    @Override
    public long getOutputAmount() {
        return this.output.amountDroplets();
    }

    @Override
    public boolean matches(FluidVariant fluid, long amount) {
        return !fluid.isBlank()
                && fluid.getFluid() == getInputFluid().getFluid()
                && amount >= getInputAmount();
    }

    @Override
    public RecipeSerializer<? extends StillRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends StillRecipe> getType() {
        return FactoryRecipeTypes.STILL.type();
    }
}
