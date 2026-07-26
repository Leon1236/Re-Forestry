package com.leon1236.reforestry.core.genetics;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.Identifier;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IMutation;

public class BreedingTracker extends SavedData implements IBreedingTracker {
	private static final String MUTATION_FORMAT = "%s-%s=%s";

	private final Identifier typeId;
	private final Set<Identifier> discoveredSpecies = new HashSet<>();
	private final Set<String> discoveredMutations = new HashSet<>();
	private final Set<String> researchedMutations = new HashSet<>();

	@Nullable
	private GameProfile username;
	@Nullable
	private Level level;

	public BreedingTracker(Identifier typeId) {
		this(typeId, List.of(), List.of(), List.of());
	}

	private BreedingTracker(Identifier typeId, List<Identifier> discoveredSpecies, List<String> discoveredMutations,
			List<String> researchedMutations) {
		this.typeId = typeId;
		this.discoveredSpecies.addAll(discoveredSpecies);
		this.discoveredMutations.addAll(discoveredMutations);
		this.researchedMutations.addAll(researchedMutations);
	}

	public static Codec<BreedingTracker> codec(Identifier typeId) {
		return RecordCodecBuilder.create(instance -> instance.group(
				Identifier.CODEC.listOf().optionalFieldOf("SD", List.of()).forGetter(tracker -> List.copyOf(tracker.discoveredSpecies)),
				Codec.STRING.listOf().optionalFieldOf("MD", List.of()).forGetter(tracker -> List.copyOf(tracker.discoveredMutations)),
				Codec.STRING.listOf().optionalFieldOf("RD", List.of()).forGetter(tracker -> List.copyOf(tracker.researchedMutations))
		).apply(instance, (species, mutations, researched) -> new BreedingTracker(typeId, species, mutations, researched)));
	}

	public static SavedDataType<BreedingTracker> typeFor(Identifier speciesTypeId, @Nullable GameProfile profile) {
		String playerKey = profile == null || profile.id() == null ? "common" : profile.id().toString();
		Identifier fileId = ReForestry.id("breeding_tracker/" + speciesTypeId.getPath() + "/" + playerKey);
		return new SavedDataType<>(fileId, () -> new BreedingTracker(speciesTypeId), codec(speciesTypeId),
				DataFixTypes.SAVED_DATA_SCOREBOARD);
	}

	public void setUsername(@Nullable GameProfile username) {
		this.username = username;
	}

	public void setLevel(@Nullable Level level) {
		this.level = level;
	}

	public Identifier typeId() {
		return typeId;
	}

	private static String getMutationString(IMutation mutation) {
		return String.format(MUTATION_FORMAT,
				mutation.firstParent(),
				mutation.secondParent(),
				mutation.result());
	}

	@Override
	public void syncToPlayer(Player player) {
	}

	@Override
	public void registerMutation(IMutation mutation) {
		String mutationString = getMutationString(mutation);
		if (this.discoveredMutations.add(mutationString)) {
			setDirty();
		}
	}

	@Override
	public boolean isDiscovered(IMutation mutation) {
		String mutationString = getMutationString(mutation);
		return this.discoveredMutations.contains(mutationString) || this.researchedMutations.contains(mutationString);
	}

	@Override
	public boolean isDiscovered(Identifier speciesId) {
		return this.discoveredSpecies.contains(speciesId);
	}

	@Override
	public Collection<Identifier> getDiscoveredSpecies() {
		return this.discoveredSpecies;
	}

	@Override
	public int getSpeciesBred() {
		return this.discoveredSpecies.size();
	}

	@Override
	public void registerBirth(Identifier speciesId) {
		registerSpecies(speciesId);
	}

	@Override
	public void registerPickup(Identifier speciesId) {
		registerSpecies(speciesId);
	}

	@Override
	public void registerSpecies(Identifier speciesId) {
		if (this.discoveredSpecies.add(speciesId)) {
			setDirty();
		}
	}

	@Override
	public void researchMutation(IMutation mutation) {
		String mutationString = getMutationString(mutation);
		if (this.researchedMutations.add(mutationString)) {
			setDirty();
			registerMutation(mutation);
		}
	}

	@Override
	public boolean isResearched(IMutation mutation) {
		return this.researchedMutations.contains(getMutationString(mutation));
	}
}
