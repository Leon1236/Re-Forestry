package com.leon1236.reforestry.factory.recipes;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.core.recipes.CraftingInputHelper;
import com.leon1236.reforestry.core.recipes.LegacyIngredientCodec;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.factory.features.FactoryRecipeTypes;

public record FabricatorRecipe(
		Optional<Ingredient> plan,
		RecipeFluidAmount molten,
		CraftingRecipe craftingRecipe
) implements IFabricatorRecipe {
	private static final Codec<CraftingRecipe> CRAFTING_RECIPE_CODEC = Recipe.CODEC.comapFlatMap(
			recipe -> recipe instanceof CraftingRecipe crafting
					? DataResult.success(crafting)
					: DataResult.error(() -> "Fabricator recipe must embed a crafting recipe"),
			recipe -> recipe
	);

	private static final StreamCodec<RegistryFriendlyByteBuf, CraftingRecipe> CRAFTING_RECIPE_STREAM_CODEC =
			Recipe.STREAM_CODEC.map(
					recipe -> {
						if (recipe instanceof CraftingRecipe crafting) {
							return crafting;
						}
						throw new IllegalStateException("Fabricator recipe must embed a crafting recipe");
					},
					recipe -> recipe
			);

	public static final MapCodec<FabricatorRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			LegacyIngredientCodec.OPTIONAL.optionalFieldOf("plan", Optional.empty()).forGetter(FabricatorRecipe::plan),
			RecipeFluidAmount.CODEC.fieldOf("molten").forGetter(FabricatorRecipe::molten),
			CRAFTING_RECIPE_CODEC.fieldOf("recipe").forGetter(FabricatorRecipe::craftingRecipe)
	).apply(instance, FabricatorRecipe::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, FabricatorRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, FabricatorRecipe::plan,
			RecipeFluidAmount.STREAM_CODEC, FabricatorRecipe::molten,
			CRAFTING_RECIPE_STREAM_CODEC, FabricatorRecipe::craftingRecipe,
			FabricatorRecipe::new
	);

	public static final RecipeSerializer<FabricatorRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public FluidVariant getResultFluid() {
		return this.molten.variant();
	}

	@Override
	public long getResultFluidAmount() {
		return this.molten.amountDroplets();
	}

	@Override
	public Optional<Ingredient> getPlan() {
		return this.plan;
	}

	@Override
	public CraftingRecipe getCraftingGridRecipe() {
		return this.craftingRecipe;
	}

	@Override
	public ItemStack getResultStack() {
		return this.craftingRecipe.assemble(CraftingInput.EMPTY);
	}

	@Override
	public boolean matches(Level level, FluidVariant liquid, long liquidAmount, ItemStack planStack, Container craftingGrid) {
		if (liquid.isBlank()
				|| liquid.getFluid() != this.molten.variant().getFluid()
				|| liquidAmount < this.molten.amountDroplets()) {
			return false;
		}
		if (!Ingredient.testOptionalIngredient(this.plan, planStack)) {
			return false;
		}
		CraftingInput input = CraftingInputHelper.fromGrid(craftingGrid, 0);
		return this.craftingRecipe.matches(input, level);
	}

	@Override
	public RecipeSerializer<? extends FabricatorRecipe> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public RecipeType<? extends FabricatorRecipe> getType() {
		return FactoryRecipeTypes.FABRICATOR.type();
	}
}
