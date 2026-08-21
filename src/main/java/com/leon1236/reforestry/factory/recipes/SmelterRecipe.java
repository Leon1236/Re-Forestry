package com.leon1236.reforestry.factory.recipes;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import com.leon1236.reforestry.api.recipes.ISmelterRecipe;
import com.leon1236.reforestry.core.recipes.CraftingPatternHelper;
import com.leon1236.reforestry.api.recipes.IngredientStack;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record SmelterRecipe(List<IngredientStack> inputs, IngredientStack output, int processingTime) implements ISmelterRecipe {
    public static final MapCodec<SmelterRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            IngredientStack.CODEC.listOf().fieldOf("inputs").forGetter(SmelterRecipe::inputs),
            IngredientStack.CODEC.fieldOf("output").forGetter(SmelterRecipe::output),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("processingTime").forGetter(SmelterRecipe::processingTime)
    ).apply(instance, SmelterRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SmelterRecipe> STREAM_CODEC = StreamCodec.composite(
            IngredientStack.STREAM_CODEC.apply(ByteBufCodecs.list()), SmelterRecipe::inputs,
            IngredientStack.STREAM_CODEC, SmelterRecipe::output,
            ByteBufCodecs.VAR_INT, SmelterRecipe::processingTime,
            SmelterRecipe::new
    );

    public static final RecipeSerializer<SmelterRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public List<IngredientStack> getInputs() {
        return inputs;
    }

    @Override
    public ItemStack getOutput() {
        ItemStack stack = CraftingPatternHelper.firstStack(output.ingredient());
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return stack.copyWithCount(output.count());
    }

    @Override
    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public boolean matches(int processingTime, List<IngredientStack> recipeInputs, IngredientStack recipeOutput) {
        return this.processingTime == processingTime
                && this.inputs.equals(recipeInputs)
                && this.output.equals(recipeOutput);
    }

    public static boolean canAlloy(ISmelterRecipe recipe, List<ItemStack> contents) {
        int[] reserved = new int[contents.size()];
        for (IngredientStack input : recipe.getInputs()) {
            int remaining = input.count();
            for (int i = 0; i < contents.size() && remaining > 0; i++) {
                ItemStack stack = contents.get(i);
                if (stack.isEmpty() || !input.ingredient().test(stack)) {
                    continue;
                }
                int available = stack.getCount() - reserved[i];
                if (available <= 0) {
                    continue;
                }
                int take = Math.min(remaining, available);
                reserved[i] += take;
                remaining -= take;
            }
            if (remaining > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public RecipeSerializer<? extends SmelterRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends SmelterRecipe> getType() {
        return FactoryRecipeTypes.SMELTER.type();
    }

}
