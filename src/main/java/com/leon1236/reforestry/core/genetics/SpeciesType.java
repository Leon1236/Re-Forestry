package com.leon1236.reforestry.core.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.GameProfile;

import net.minecraft.util.Util;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.IMutationManager;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

public abstract class SpeciesType<S extends ISpecies<I>, I extends IIndividual> implements ISpeciesType<S, I> {
	protected final Identifier id;
	protected final IKaryotype karyotype;
	private final ILifeStage defaultStage;
	private final String translationKey;
	private final Identifier defaultSpeciesId;
	private final List<ILifeStage> stages;

	private volatile ImmutableMap<Item, ILifeStage> stagesByItem = ImmutableMap.of();
	private volatile ImmutableMap<Identifier, S> allSpecies = ImmutableMap.of();
	private volatile IMutationManager mutations = new IdentifierMutationManager(List.of());

	protected SpeciesType(Identifier id, IKaryotype karyotype, ILifeStage defaultStage, Identifier defaultSpeciesId,
			Collection<ILifeStage> stages) {
		this.id = id;
		this.karyotype = karyotype;
		this.defaultStage = defaultStage;
		this.defaultSpeciesId = defaultSpeciesId;
		this.translationKey = Util.makeDescriptionId("species_type", id);
		this.stages = List.copyOf(stages);
	}

	@Override
	public Identifier id() {
		return id;
	}

	@Override
	public IKaryotype getKaryotype() {
		return karyotype;
	}

	@Override
	public S getDefaultSpecies() {
		return getSpecies(defaultSpeciesId);
	}

	@Override
	public ILifeStage getDefaultStage() {
		return defaultStage;
	}

	@Override
	public String getTranslationKey() {
		return translationKey;
	}

	@Override
	public Collection<ILifeStage> getLifeStages() {
		return stages;
	}

	@Nullable
	@Override
	public ILifeStage getLifeStage(ItemStack stack) {
		return stagesByItem().get(stack.getItem());
	}

	private ImmutableMap<Item, ILifeStage> stagesByItem() {
		ImmutableMap<Item, ILifeStage> cached = stagesByItem;
		if (!cached.isEmpty() || stages.isEmpty()) {
			return cached;
		}
		ImmutableMap.Builder<Item, ILifeStage> builder = ImmutableMap.builder();
		for (ILifeStage stage : stages) {
			Item item = stage.getItemForm();
			if (item != Items.AIR) {
				builder.put(item, stage);
			}
		}
		ImmutableMap<Item, ILifeStage> built = builder.build();
		stagesByItem = built;
		return built;
	}

	public void setMutations(IMutationManager mutations) {
		this.mutations = mutations;
	}

	@Override
	public IMutationManager getMutations() {
		return mutations;
	}

	@Override
	public void onSpeciesRegistered(ImmutableMap<Identifier, S> allSpecies) {
		this.allSpecies = allSpecies;
	}

	@Override
	public List<S> getAllSpecies() {
		return allSpecies.values().asList();
	}

	@Override
	public S getSpecies(Identifier id) {
		S species = allSpecies.get(id);
		if (species == null) {
			throw new RuntimeException("No species was found with that ID: " + id);
		}
		return species;
	}

	@Override
	@Nullable
	public S getSpeciesSafe(Identifier id) {
		return allSpecies.get(id);
	}

	@Override
	public S getRandomSpecies(RandomSource rand) {
		List<S> species = getAllSpecies();
		return species.get(rand.nextInt(species.size()));
	}

	@Override
	public ImmutableSet<Identifier> getAllSpeciesIds() {
		return allSpecies.keySet();
	}

	@Override
	public int getSpeciesCount() {
		return allSpecies.size();
	}

	@Override
	public IBreedingTracker getBreedingTracker(LevelAccessor level, @Nullable GameProfile profile) {
		return BreedingTrackerManager.INSTANCE.getTracker(id, level, profile);
	}

	@Override
	public ItemStack createStack(I individual, ILifeStage type) {
		if (!stages.contains(type)) {
			throw new IllegalArgumentException("Invalid life stage for species type " + id + ": " + type);
		}
		return individual.createStack(type);
	}

	@Override
	public ItemStack createStack(Identifier speciesId, ILifeStage stage) {
		return getSpecies(speciesId).createStack(stage);
	}

	@Override
	public float getResearchSuitability(S species, ItemStack stack) {
		return 0f;
	}

	@Override
	public List<ItemStack> getResearchBounty(S species, Level level, GameProfile researcher, I individual,
			int bountyLevel) {
		return new ArrayList<>();
	}

	@Override
	public IBreedingTracker createBreedingTracker() {
		return new BreedingTracker(id);
	}

	@Override
	public void initializeBreedingTracker(IBreedingTracker tracker, @Nullable Level world,
			@Nullable GameProfile profile) {
		if (tracker instanceof BreedingTracker breedingTracker) {
			breedingTracker.setUsername(profile);
			breedingTracker.setLevel(world);
		}
	}

	@Override
	public I createRandomIndividual(RandomSource rand) {
		return getRandomSpecies(rand).createIndividual();
	}

	@Override
	public ImmutableMap<Identifier, S> handleSpeciesRegistration(List<IForestryPlugin> plugins) {
		return allSpecies;
	}
}
