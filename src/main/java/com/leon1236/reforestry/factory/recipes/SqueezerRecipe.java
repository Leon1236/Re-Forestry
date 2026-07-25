package com.leon1236.reforestry.factory.recipes;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.core.recipes.LegacyIngredientCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.api.recipes.ISqueezerRecipe;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.core.recipes.RecipeItemAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record SqueezerRecipe(List<Ingredient> resources, int time, RecipeFluidAmount output, RecipeItemAmount remnant, float chance)
        implements ISqueezerRecipe {
    public static final MapCodec<SqueezerRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            LegacyIngredientCodec.listCodec().fieldOf("resources").forGetter(SqueezerRecipe::resources),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("time").forGetter(SqueezerRecipe::time),
            RecipeFluidAmount.CODEC.fieldOf("output").forGetter(SqueezerRecipe::output),
            RecipeItemAmount.CODEC.fieldOf("remnant").forGetter(SqueezerRecipe::remnant),
            Codec.floatRange(0f, 1f).fieldOf("chance").forGetter(SqueezerRecipe::chance)
    ).apply(instance, SqueezerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SqueezerRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), SqueezerRecipe::resources,
            ByteBufCodecs.VAR_INT, SqueezerRecipe::time,
            RecipeFluidAmount.STREAM_CODEC, SqueezerRecipe::output,
            RecipeItemAmount.STREAM_CODEC, SqueezerRecipe::remnant,
            ByteBufCodecs.FLOAT, SqueezerRecipe::chance,
            SqueezerRecipe::new
    );

    public static final RecipeSerializer<SqueezerRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public List<Ingredient> getInputs() {
        return this.resources;
    }

    @Override
    public int getProcessingTime() {
        return this.time;
    }

    @Override
    public ItemStack getRemnants() {
        return this.remnant.toStack();
    }

    @Override
    public float getRemnantsChance() {
        return this.chance;
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
    public RecipeSerializer<? extends SqueezerRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends SqueezerRecipe> getType() {
        return FactoryRecipeTypes.SQUEEZER.type();
    }
}
