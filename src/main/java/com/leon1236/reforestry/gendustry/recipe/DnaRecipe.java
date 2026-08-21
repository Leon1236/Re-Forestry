package com.leon1236.reforestry.gendustry.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.gendustry.features.GRecipeTypes;

public class DnaRecipe extends ProcessorRecipe {
	public static final MapCodec<DnaRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Identifier.CODEC.fieldOf("species_type").forGetter(recipe -> recipe.speciesType.id()),
			Codec.STRING.fieldOf("stage").forGetter(recipe -> recipe.stage.getSerializedName()),
			Codec.INT.fieldOf("amount").forGetter(DnaRecipe::getAmount)
	).apply(instance, DnaRecipe::fromSerialized));

	public static final StreamCodec<RegistryFriendlyByteBuf, DnaRecipe> STREAM_CODEC = StreamCodec.composite(
			Identifier.STREAM_CODEC, recipe -> recipe.speciesType.id(),
			ByteBufCodecs.STRING_UTF8, recipe -> recipe.stage.getSerializedName(),
			ByteBufCodecs.VAR_INT, DnaRecipe::getAmount,
			DnaRecipe::fromSerialized
	);

	public static final RecipeSerializer<DnaRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	private final ISpeciesType<?, ?> speciesType;
	private final ILifeStage stage;

	public DnaRecipe(ISpeciesType<?, ?> speciesType, ILifeStage stage, int amount) {
		super(amount);
		this.speciesType = speciesType;
		this.stage = stage;
	}

	public static DnaRecipe fromSerialized(Identifier speciesTypeId, String stageName, int amount) {
		ISpeciesType<?, ?> speciesType = IForestryApi.get().getGeneticManager().getSpeciesType(speciesTypeId);
		ILifeStage stage = null;
		for (ILifeStage candidate : speciesType.getLifeStages()) {
			if (candidate.getSerializedName().equals(stageName)) {
				stage = candidate;
				break;
			}
		}
		if (stage == null) {
			throw new IllegalStateException("No such life stage " + stageName + " for species type " + speciesTypeId);
		}
		return new DnaRecipe(speciesType, stage, amount);
	}

	public ISpeciesType<?, ?> getSpeciesType() {
		return this.speciesType;
	}

	public ILifeStage getStage() {
		return this.stage;
	}

	@Override
	public boolean isIngredient(ItemStack stack) {
		return IIndividualHandlerItem.filter(stack, (individual, lifeStage) -> lifeStage == this.stage);
	}

	@Override
	public RecipeSerializer<? extends DnaRecipe> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public RecipeType<? extends DnaRecipe> getType() {
		return GRecipeTypes.DNA.type();
	}
}
