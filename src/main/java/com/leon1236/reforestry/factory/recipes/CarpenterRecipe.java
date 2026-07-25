package com.leon1236.reforestry.factory.recipes;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.core.recipes.LegacyIngredientCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

import com.leon1236.reforestry.api.recipes.ICarpenterRecipe;
import com.leon1236.reforestry.core.recipes.CraftingInputHelper;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record CarpenterRecipe(
        int packagingTime,
        Optional<RecipeFluidAmount> liquid,
        Optional<Ingredient> box,
        CraftingRecipe craftingRecipe,
        Optional<ItemStack> resultOverride
) implements ICarpenterRecipe {
    private static final Codec<CraftingRecipe> CRAFTING_RECIPE_CODEC = Recipe.CODEC.comapFlatMap(
            recipe -> recipe instanceof CraftingRecipe crafting
                    ? DataResult.success(crafting)
                    : DataResult.error(() -> "Carpenter recipe must embed a crafting recipe"),
            recipe -> recipe
    );

    private static final StreamCodec<RegistryFriendlyByteBuf, CraftingRecipe> CRAFTING_RECIPE_STREAM_CODEC =
            Recipe.STREAM_CODEC.map(
                    recipe -> {
                        if (recipe instanceof CraftingRecipe crafting) {
                            return crafting;
                        }
                        throw new IllegalStateException("Carpenter recipe must embed a crafting recipe");
                    },
                    recipe -> recipe
            );

    public static final MapCodec<CarpenterRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("time").forGetter(CarpenterRecipe::packagingTime),
            RecipeFluidAmount.CODEC.optionalFieldOf("liquid").forGetter(CarpenterRecipe::liquid),
            LegacyIngredientCodec.CODEC.optionalFieldOf("box").forGetter(CarpenterRecipe::box),
            CRAFTING_RECIPE_CODEC.fieldOf("recipe").forGetter(CarpenterRecipe::craftingRecipe),
            ItemStack.CODEC.optionalFieldOf("result").forGetter(CarpenterRecipe::resultOverride)
    ).apply(instance, CarpenterRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CarpenterRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, CarpenterRecipe::packagingTime,
            ByteBufCodecs.optional(RecipeFluidAmount.STREAM_CODEC), CarpenterRecipe::liquid,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, CarpenterRecipe::box,
            CRAFTING_RECIPE_STREAM_CODEC, CarpenterRecipe::craftingRecipe,
            ByteBufCodecs.optional(ItemStack.STREAM_CODEC), CarpenterRecipe::resultOverride,
            CarpenterRecipe::new
    );

    public static final RecipeSerializer<CarpenterRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public int getPackagingTime() {
        return this.packagingTime;
    }

    @Override
    public CraftingRecipe getCraftingGridRecipe() {
        return this.craftingRecipe;
    }

    @Override
    @Nullable
    public Ingredient getBox() {
        return this.box.orElse(null);
    }

    @Override
    public FluidVariant getInputFluid() {
        return this.liquid.map(RecipeFluidAmount::variant).orElse(FluidVariant.blank());
    }

    @Override
    public long getInputFluidAmount() {
        return this.liquid.map(RecipeFluidAmount::amountDroplets).orElse(0L);
    }

    @Override
    public ItemStack getResultStack() {
        return this.resultOverride.map(ItemStack::copy).orElseGet(() -> this.craftingRecipe.assemble(CraftingInput.EMPTY));
    }

    @Override
    public boolean matches(FluidVariant fluid, long fluidAmount, ItemStack boxStack, Container craftingGrid, Level level) {
        Optional<RecipeFluidAmount> requiredLiquid = this.liquid;
        if (requiredLiquid.isPresent()) {
            RecipeFluidAmount required = requiredLiquid.get();
            if (fluid.isBlank()
                    || fluid.getFluid() != required.variant().getFluid()
                    || fluidAmount < required.amountDroplets()) {
                return false;
            }
        }

        Optional<Ingredient> requiredBox = this.box;
        if (requiredBox.isPresent() && !requiredBox.get().test(boxStack)) {
            return false;
        }

        CraftingInput input = CraftingInputHelper.fromGrid(craftingGrid, 0);
        return this.craftingRecipe.matches(input, level);
    }

    @Override
    public RecipeSerializer<? extends CarpenterRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends CarpenterRecipe> getType() {
        return FactoryRecipeTypes.CARPENTER.type();
    }
}
