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
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.api.recipes.ISqueezerContainerRecipe;
import com.leon1236.reforestry.core.recipes.RecipeItemAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record SqueezerContainerRecipe(RecipeItemAmount container, int time, RecipeItemAmount remnants, float remnantsChance)
        implements ISqueezerContainerRecipe {
    public static final MapCodec<SqueezerContainerRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RecipeItemAmount.CODEC.fieldOf("container").forGetter(SqueezerContainerRecipe::container),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("time").forGetter(SqueezerContainerRecipe::time),
            RecipeItemAmount.CODEC.fieldOf("remnants").forGetter(SqueezerContainerRecipe::remnants),
            Codec.floatRange(0f, 1f).fieldOf("remnantsChance").forGetter(SqueezerContainerRecipe::remnantsChance)
    ).apply(instance, SqueezerContainerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SqueezerContainerRecipe> STREAM_CODEC = StreamCodec.composite(
            RecipeItemAmount.STREAM_CODEC, SqueezerContainerRecipe::container,
            ByteBufCodecs.VAR_INT, SqueezerContainerRecipe::time,
            RecipeItemAmount.STREAM_CODEC, SqueezerContainerRecipe::remnants,
            ByteBufCodecs.FLOAT, SqueezerContainerRecipe::remnantsChance,
            SqueezerContainerRecipe::new
    );

    public static final RecipeSerializer<SqueezerContainerRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public ItemStack getEmptyContainer() {
        return this.container.toStack();
    }

    @Override
    public List<Ingredient> getInputs() {
        return List.of();
    }

    @Override
    public int getProcessingTime() {
        return this.time;
    }

    @Override
    public ItemStack getRemnants() {
        return this.remnants.toStack();
    }

    @Override
    public float getRemnantsChance() {
        return this.remnantsChance;
    }

    @Override
    public FluidVariant getOutputFluid() {
        return FluidVariant.blank();
    }

    @Override
    public long getOutputAmount() {
        return 0;
    }

    @Override
    public RecipeSerializer<? extends SqueezerContainerRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends SqueezerContainerRecipe> getType() {
        return FactoryRecipeTypes.SQUEEZER_CONTAINER.type();
    }
}
