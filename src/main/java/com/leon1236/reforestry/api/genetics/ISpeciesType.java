package com.leon1236.reforestry.api.genetics;

import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;

public interface ISpeciesType<S extends ISpecies<I>, I extends IIndividual> extends IBreedingTrackerHandler {
	Identifier id();

	IKaryotype getKaryotype();

	@Nullable
	ILifeStage getLifeStage(ItemStack stack);

	IMutationManager getMutations();

	List<S> getAllSpecies();

	S getSpecies(Identifier id);

	@Nullable
	S getSpeciesSafe(Identifier id);

	S getRandomSpecies(RandomSource rand);

	ImmutableSet<Identifier> getAllSpeciesIds();

	int getSpeciesCount();

	S getDefaultSpecies();

	ILifeStage getDefaultStage();

	String getTranslationKey();

	default MutableComponent getDisplayName() {
		return Component.translatable(getTranslationKey());
	}

	default ItemStack createDefaultStack() {
		return getDefaultSpecies().createStack(getDefaultStage());
	}

	Collection<ILifeStage> getLifeStages();

	IBreedingTracker getBreedingTracker(LevelAccessor level, @Nullable GameProfile profile);

	default ILifeStage getTypeForMutation(int position) {
		return getDefaultStage();
	}

	ItemStack createStack(I individual, ILifeStage type);

	ItemStack createStack(Identifier speciesId, ILifeStage stage);

	Codec<? extends I> getIndividualCodec();

	boolean isMember(IIndividual individual);

	default boolean isMember(ItemStack stack) {
		IIndividual individual = IIndividualHandlerItem.getIndividual(stack);
		return individual != null && isMember(individual);
	}

	float getResearchSuitability(S species, ItemStack stack);

	List<ItemStack> getResearchBounty(S species, Level level, GameProfile researcher, I individual, int bountyLevel);

	String getBreedingTrackerFile(@Nullable GameProfile profile);

	IBreedingTracker createBreedingTracker();

	void initializeBreedingTracker(IBreedingTracker tracker, @Nullable Level world, @Nullable GameProfile profile);

	I createRandomIndividual(RandomSource rand);

	ImmutableMap<Identifier, S> handleSpeciesRegistration(List<IForestryPlugin> plugins);

	void onSpeciesRegistered(ImmutableMap<Identifier, S> allSpecies);

	@SuppressWarnings("unchecked")
	default <T extends ISpeciesType<?, ?>> T cast() {
		return (T) this;
	}
}
