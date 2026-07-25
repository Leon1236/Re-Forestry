package com.leon1236.reforestry.factory.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import com.leon1236.reforestry.api.recipes.IHygroregulatorRecipe;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record HygroregulatorRecipe(
        RecipeFluidAmount liquid,
        int retainTime,
        byte humiditySteps,
        byte temperatureSteps
) implements IHygroregulatorRecipe {
    public static final MapCodec<HygroregulatorRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RecipeFluidAmount.CODEC.fieldOf("liquid").forGetter(HygroregulatorRecipe::liquid),
            com.mojang.serialization.Codec.INT.fieldOf("time").forGetter(HygroregulatorRecipe::retainTime),
            com.mojang.serialization.Codec.BYTE.fieldOf("humidity_steps").forGetter(HygroregulatorRecipe::humiditySteps),
            com.mojang.serialization.Codec.BYTE.fieldOf("temperature_steps").forGetter(HygroregulatorRecipe::temperatureSteps)
    ).apply(instance, HygroregulatorRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HygroregulatorRecipe> STREAM_CODEC = StreamCodec.composite(
            RecipeFluidAmount.STREAM_CODEC, HygroregulatorRecipe::liquid,
            net.minecraft.network.codec.ByteBufCodecs.VAR_INT, HygroregulatorRecipe::retainTime,
            net.minecraft.network.codec.ByteBufCodecs.BYTE, HygroregulatorRecipe::humiditySteps,
            net.minecraft.network.codec.ByteBufCodecs.BYTE, HygroregulatorRecipe::temperatureSteps,
            HygroregulatorRecipe::new
    );

    public static final RecipeSerializer<HygroregulatorRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public FluidVariant getInputFluid() {
        return this.liquid.variant();
    }

    @Override
    public long getInputFluidAmount() {
        return this.liquid.amountDroplets();
    }

    @Override
    public int getRetainTime() {
        return this.retainTime;
    }

    @Override
    public byte getHumiditySteps() {
        return this.humiditySteps;
    }

    @Override
    public byte getTemperatureSteps() {
        return this.temperatureSteps;
    }

    public boolean matches(FluidVariant variant, long amount) {
        return !variant.isBlank()
                && variant.getFluid() == getInputFluid().getFluid()
                && amount >= getInputFluidAmount();
    }

    @Override
    public RecipeSerializer<? extends HygroregulatorRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends HygroregulatorRecipe> getType() {
        return FactoryRecipeTypes.HYGROREGULATOR.type();
    }
}
