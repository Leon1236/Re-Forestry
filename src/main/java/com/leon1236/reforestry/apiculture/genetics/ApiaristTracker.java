package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.saveddata.SavedDataType;

import com.leon1236.reforestry.api.apiculture.IApiaristTracker;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.core.genetics.BreedingTracker;

public class ApiaristTracker extends BreedingTracker implements IApiaristTracker {
	public static final String QUEENS_TOTAL_KEY = "QueensTotal";
	public static final String PRINCESSES_TOTAL_KEY = "PrincessesTotal";
	public static final String DRONES_TOTAL_KEY = "DronesTotal";

	private int queensTotal;
	private int princessesTotal;
	private int dronesTotal;

	public ApiaristTracker() {
		this(List.of(), List.of(), List.of(), 0, 0, 0);
	}

	private ApiaristTracker(
			List<Identifier> discoveredSpecies,
			List<String> discoveredMutations,
			List<String> researchedMutations,
			int queensTotal,
			int princessesTotal,
			int dronesTotal) {
		super(ForestrySpeciesTypes.BEE, discoveredSpecies, discoveredMutations, researchedMutations);
		this.queensTotal = queensTotal;
		this.princessesTotal = princessesTotal;
		this.dronesTotal = dronesTotal;
	}

	public static Codec<ApiaristTracker> codec() {
		return RecordCodecBuilder.create(instance -> instance.group(
				Identifier.CODEC.listOf().optionalFieldOf(SPECIES_KEY, List.of()).forGetter(ApiaristTracker::encodedSpecies),
				Codec.STRING.listOf().optionalFieldOf(MUTATIONS_KEY, List.of()).forGetter(ApiaristTracker::encodedMutations),
				Codec.STRING.listOf().optionalFieldOf(RESEARCHED_KEY, List.of()).forGetter(ApiaristTracker::encodedResearched),
				Codec.INT.optionalFieldOf(QUEENS_TOTAL_KEY, 0).forGetter(ApiaristTracker::getQueenCount),
				Codec.INT.optionalFieldOf(PRINCESSES_TOTAL_KEY, 0).forGetter(ApiaristTracker::getPrincessCount),
				Codec.INT.optionalFieldOf(DRONES_TOTAL_KEY, 0).forGetter(ApiaristTracker::getDroneCount)
		).apply(instance, ApiaristTracker::new));
	}

	public static SavedDataType<ApiaristTracker> typeFor(@Nullable GameProfile profile) {
		return typeFor(ForestrySpeciesTypes.BEE, profile, ApiaristTracker::new, codec());
	}

	@Override
	protected void writeUpdateData(CompoundTag nbt) {
		nbt.putInt(QUEENS_TOTAL_KEY, this.queensTotal);
		nbt.putInt(PRINCESSES_TOTAL_KEY, this.princessesTotal);
		nbt.putInt(DRONES_TOTAL_KEY, this.dronesTotal);
	}

	@Override
	protected void readUpdateData(CompoundTag nbt) {
		this.queensTotal = nbt.getIntOr(QUEENS_TOTAL_KEY, this.queensTotal);
		this.princessesTotal = nbt.getIntOr(PRINCESSES_TOTAL_KEY, this.princessesTotal);
		this.dronesTotal = nbt.getIntOr(DRONES_TOTAL_KEY, this.dronesTotal);
	}

	@Override
	public void registerQueen(Identifier speciesId) {
		this.queensTotal++;
		registerCaste(speciesId);
	}

	@Override
	public int getQueenCount() {
		return this.queensTotal;
	}

	@Override
	public void registerPrincess(Identifier speciesId) {
		this.princessesTotal++;
		registerCaste(speciesId);
	}

	@Override
	public int getPrincessCount() {
		return this.princessesTotal;
	}

	@Override
	public void registerDrone(Identifier speciesId) {
		this.dronesTotal++;
		registerCaste(speciesId);
	}

	@Override
	public int getDroneCount() {
		return this.dronesTotal;
	}

	private void registerCaste(Identifier speciesId) {
		setDirty();
		boolean alreadyDiscovered = isDiscovered(speciesId);
		registerBirth(speciesId);
		if (alreadyDiscovered) {
			sendUpdate(List.of(), List.of(), List.of());
		}
	}
}
