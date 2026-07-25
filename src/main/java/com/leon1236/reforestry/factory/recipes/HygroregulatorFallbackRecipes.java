package com.leon1236.reforestry.factory.recipes;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.api.recipes.IHygroregulatorRecipe;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public final class HygroregulatorFallbackRecipes {
    private static final List<FallbackRecipe> RECIPES = List.of(
            new FallbackRecipe(Fluids.WATER, 1, (byte) 1, (byte) -1, 0),
            new FallbackRecipe(Fluids.LAVA, 1, (byte) -1, (byte) 1, 0));

    private HygroregulatorFallbackRecipes() {
    }

    @Nullable
    public static IHygroregulatorRecipe match(FluidVariant variant, long amount) {
        if (variant.isBlank()) {
            return null;
        }
        for (FallbackRecipe recipe : RECIPES) {
            if (recipe.matches(variant, amount)) {
                return recipe;
            }
        }
        return null;
    }

    private static final class FallbackRecipe implements IHygroregulatorRecipe {
        private final Fluid fluid;
        private final int amountMb;
        private final byte humiditySteps;
        private final byte temperatureSteps;
        private final int retainTime;

        private FallbackRecipe(Fluid fluid, int amountMb, byte humiditySteps, byte temperatureSteps, int retainTime) {
            this.fluid = fluid;
            this.amountMb = amountMb;
            this.humiditySteps = humiditySteps;
            this.temperatureSteps = temperatureSteps;
            this.retainTime = retainTime;
        }

        private boolean matches(FluidVariant variant, long amount) {
            return !variant.isBlank()
                    && variant.getFluid() == this.fluid
                    && amount >= getInputFluidAmount();
        }

        @Override
        public FluidVariant getInputFluid() {
            return FluidVariant.of(this.fluid);
        }

        @Override
        public long getInputFluidAmount() {
            return FluidUnits.mbToDroplets(this.amountMb);
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

        @Override
        public RecipeSerializer<? extends IHygroregulatorRecipe> getSerializer() {
            return HygroregulatorRecipe.SERIALIZER;
        }

        @Override
        public RecipeType<? extends IHygroregulatorRecipe> getType() {
            return FactoryRecipeTypes.HYGROREGULATOR.type();
        }
    }
}
