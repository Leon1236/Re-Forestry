package com.leon1236.reforestry.apiculture.genetics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IFlowerType;
import com.leon1236.reforestry.api.apiculture.genetics.IBee;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.ClimateHelper;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.pollen.IPollen;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.core.genetics.IndividualLiving;
import com.leon1236.reforestry.core.genetics.pollen.PollenManager;

public class Bee extends IndividualLiving<IBeeSpecies, IBee, BeeSpeciesType> implements IBee {
	public static final Codec<Bee> CODEC = RecordCodecBuilder.create(instance -> {
		Codec<IGenome> genomeCodec = BeeChromosomes.KARYOTYPE.genomeCodec();
		return instance.group(
				genomeCodec.fieldOf("genome").forGetter(Bee::getGenome),
				genomeCodec.optionalFieldOf("mate").forGetter(Bee::getMateOptional),
				Codec.BOOL.fieldOf("analyzed").forGetter(Bee::isAnalyzed),
				Codec.INT.fieldOf("health").forGetter(Bee::getHealth),
				Codec.INT.fieldOf("max_health").forGetter(Bee::getMaxHealth),
				Codec.BOOL.fieldOf("pristine").forGetter(Bee::isPristine),
				Codec.INT.optionalFieldOf("generation", 0).forGetter(Bee::getGeneration)
		).apply(instance, Bee::new);
	});

	private boolean pristine = true;
	private int generation;

	public Bee(IGenome genome) {
		super(genome);
	}

	private Bee(IGenome genome, Optional<IGenome> mate, boolean analyzed, int health, int maxHealth, boolean pristine,
			int generation) {
		super(genome, mate, analyzed, health, maxHealth);
		this.pristine = pristine;
		this.generation = generation;
	}

	@Override
	protected int getMaxHealthFromGenome(IGenome genome) {
		return genome.getActiveAllele(BeeChromosomes.LIFESPAN).value();
	}

	@Override
	public void setPristine(boolean pristine) {
		this.pristine = pristine;
	}

	@Override
	public boolean isPristine() {
		return pristine;
	}

	@Override
	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	@Override
	public IBeeSpecies getSpecies() {
		return species;
	}

	@Override
	public IBeeSpecies getInactiveSpecies() {
		return inactiveSpecies;
	}

	@Override
	public IBee copy() {
		return (IBee) super.copy();
	}

	@Override
	public IBee copyWithGenome(IGenome newGenome) {
		return super.copyWithGenome(newGenome);
	}

	@Override
	public IEffectData[] doEffect(IEffectData[] storedData, IBeeHousing housing) {
		return applyEffects(storedData, housing, true);
	}

	@Override
	public IEffectData[] doFX(IEffectData[] storedData, IBeeHousing housing) {
		return applyEffects(storedData, housing, false);
	}

	private IEffectData[] applyEffects(IEffectData[] storedData, IBeeHousing housing, boolean server) {
		IBeeEffect effect = genome.getActiveAllele(BeeChromosomes.EFFECT).value();
		storedData[0] = applyEffect(effect, storedData[0], housing, server);
		if (effect.isCombinable()) {
			IBeeEffect secondary = genome.getInactiveAllele(BeeChromosomes.EFFECT).value();
			if (secondary.isCombinable()) {
				storedData[1] = applyEffect(secondary, storedData[1], housing, server);
			}
		}
		return storedData;
	}

	private IEffectData applyEffect(IBeeEffect effect, IEffectData storedData, IBeeHousing housing, boolean server) {
		storedData = effect.validateStorage(storedData);
		return server ? effect.doEffect(genome, storedData, housing) : effect.doFX(genome, storedData, housing);
	}

	@Override
	public Set<IError> getCanWork(IBeeHousing housing) {
		return BeeCanWork.getCanWork(genome, housing);
	}

	@Override
	public List<Holder.Reference<Biome>> getSuitableBiomes(Registry<Biome> registry) {
		ArrayList<Holder.Reference<Biome>> suitable = new ArrayList<>();
		for (Holder.Reference<Biome> holder : registry.listElements().toList()) {
			if (isSuitableBiome(holder)) {
				suitable.add(holder);
			}
		}
		return suitable;
	}

	private boolean isSuitableBiome(Holder<Biome> biome) {
		IClimateManager manager = IForestryApi.get().getClimateManager();
		TemperatureType temperature = manager.getTemperature(biome);
		HumidityType humidity = manager.getHumidity(biome);
		return ClimateHelper.isWithinLimits(temperature, humidity,
				species.getTemperature(), genome.getActiveAllele(BeeChromosomes.TEMPERATURE_TOLERANCE).value(),
				species.getHumidity(), genome.getActiveAllele(BeeChromosomes.HUMIDITY_TOLERANCE).value());
	}

	@Override
	public List<ItemStack> getProduceList() {
		List<ItemStack> stacks = new ArrayList<>();
		for (IBeeSpecies.Product product : getSpecies().products()) {
			stacks.add(product.createStack());
		}
		return stacks;
	}

	@Override
	public List<ItemStack> getSpecialtyList() {
		List<ItemStack> stacks = new ArrayList<>();
		for (IBeeSpecies.Product product : getSpecies().specialties()) {
			stacks.add(product.createStack());
		}
		return stacks;
	}

	@Override
	public List<ItemStack> produceStacks(IBeeHousing housing) {
		List<ItemStack> stacks = new ArrayList<>();
		var random = housing.level().getRandom();
		for (IBeeSpecies.Product product : getSpecies().products()) {
			if (random.nextFloat() < product.chance()) {
				stacks.add(product.createStack());
			}
		}
		if (getSpecies().isJubilant(genome, housing)) {
			for (IBeeSpecies.Product product : getSpecies().specialties()) {
				if (random.nextFloat() < product.chance()) {
					stacks.add(product.createStack());
				}
			}
		}
		return stacks;
	}

	@Override
	@Nullable
	public IBee spawnPrincess(IBeeHousing housing) {
		IBee princess = copy();
		princess.setPristine(false);
		return princess;
	}

	@Override
	public List<IBee> spawnDrones(IBeeHousing housing) {
		if (mate == null) {
			return List.of();
		}
		int toCreate = genome.getActiveAllele(BeeChromosomes.FERTILITY).value();
		if (toCreate < 1) {
			return List.of();
		}
		Level level = housing.level();
		if (level == null) {
			return List.of();
		}
		RandomSource random = level.getRandom();
		List<IBee> drones = new ArrayList<>(toCreate);
		for (int i = 0; i < toCreate; i++) {
			BeeMating.MatingResult result = random.nextBoolean()
					? BeeMating.resolveOffspringGenome(genome, mate, housing, random)
					: BeeMating.resolveOffspringGenome(mate, genome, housing, random);
			Bee drone = new Bee(result.genome());
			drone.setPristine(true);
			drones.add(drone);
		}
		return drones;
	}

	@Override
	@Nullable
	public BlockPos plantFlowerRandom(IBeeHousing housing, List<BlockState> potentialFlowers) {
		IFlowerType flowerType = genome.getActiveAllele(BeeChromosomes.FLOWER_TYPE).value();
		if (flowerType.plantRandomFlower(housing.level(), housing.position(), potentialFlowers)) {
			return housing.position();
		}
		return null;
	}

	@Override
	@Nullable
	public IPollen retrievePollen(IBeeHousing housing) {
		return PollenManager.INSTANCE.getPollen(housing.level(), housing.position());
	}

	@Override
	public boolean pollinateRandom(IBeeHousing housing, IPollen pollen) {
		return pollen.tryPollinate(housing.level(), housing.position());
	}

	@Override
	public Iterator<BlockPos.MutableBlockPos> getAreaIterator(IBeeHousing housing) {
		return BeeCanWork.getAreaIterator(genome, housing);
	}

	@Override
	protected void savePropertiesToStack(ItemStack stack) {
		super.savePropertiesToStack(stack);
		stack.set(ApicultureDataComponents.BEE_GENOME.type(), genome);
		if (mate != null) {
			stack.set(ApicultureDataComponents.BEE_MATE_GENOME.type(), mate);
		} else {
			stack.remove(ApicultureDataComponents.BEE_MATE_GENOME.type());
		}
		int lifeUsed = Math.max(0, maxHealth - health);
		if (lifeUsed == 0) {
			stack.remove(ApicultureDataComponents.BEE_LIFE_USED.type());
		} else {
			stack.set(ApicultureDataComponents.BEE_LIFE_USED.type(), lifeUsed);
		}
		BeeStackHelper.setPristine(stack, pristine);
		BeeStackHelper.setGeneration(stack, generation);
	}

	@Override
	public void loadPropertiesFromStack(ItemStack stack) {
		super.loadPropertiesFromStack(stack);
		setMate(stack.get(ApicultureDataComponents.BEE_MATE_GENOME.type()));
		int lifeUsed = stack.getOrDefault(ApicultureDataComponents.BEE_LIFE_USED.type(), 0);
		setHealth(maxHealth - lifeUsed);
		pristine = BeeStackHelper.isPristine(stack);
		generation = BeeStackHelper.getGeneration(stack);
	}

	@Override
	protected void copyPropertiesTo(IBee other) {
		super.copyPropertiesTo(other);
		if (other instanceof Bee bee) {
			bee.pristine = pristine;
			bee.generation = generation;
		}
	}

	@Nullable
	public static Bee fromStack(ItemStack stack) {
		IGenome genome = stack.get(ApicultureDataComponents.BEE_GENOME.type());
		if (genome == null) {
			return null;
		}
		Bee bee = new Bee(genome);
		bee.loadPropertiesFromStack(stack);
		return bee;
	}
}
