package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.ClimateHelper;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyNursery;
import com.leon1236.reforestry.api.lepidopterology.IEntityButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.core.genetics.IndividualLiving;
import com.leon1236.reforestry.core.genetics.Mating;
import com.leon1236.reforestry.lepidopterology.ModuleLepidopterology;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;

public class Butterfly extends IndividualLiving<IButterflySpecies, IButterfly, ButterflySpeciesType> implements IButterfly {
	private static final RandomSource rand = RandomSource.create();

	public static final Codec<Butterfly> CODEC = RecordCodecBuilder.create(instance -> {
		Codec<IGenome> genomeCodec = ButterflyChromosomes.KARYOTYPE.genomeCodec();
		return instance.group(
				genomeCodec.fieldOf("genome").forGetter(Butterfly::getGenome),
				genomeCodec.optionalFieldOf("mate").forGetter(Butterfly::getMateOptional),
				Codec.BOOL.fieldOf("analyzed").forGetter(Butterfly::isAnalyzed),
				Codec.INT.fieldOf("health").forGetter(Butterfly::getHealth),
				Codec.INT.fieldOf("max_health").forGetter(Butterfly::getMaxHealth)
		).apply(instance, Butterfly::new);
	});

	public Butterfly(IGenome genome) {
		super(genome);
	}

	private Butterfly(IGenome genome, Optional<IGenome> mate, boolean analyzed, int health, int maxHealth) {
		super(genome, mate, analyzed, health, maxHealth);
	}

	@Override
	protected int getMaxHealthFromGenome(IGenome genome) {
		return genome.getActiveAllele(ButterflyChromosomes.LIFESPAN).value();
	}

	@Override
	public ButterflySpeciesType getType() {
		return ButterflySpeciesType.INSTANCE;
	}

	@Override
	public IButterflySpecies getSpecies() {
		return species;
	}

	@Override
	public IButterflySpecies getInactiveSpecies() {
		return inactiveSpecies;
	}

	@Override
	public IButterfly copy() {
		return super.copy();
	}

	@Override
	public boolean canSpawn(Level level, double x, double y, double z) {
		if (!canFly(level)) {
			return false;
		}
		BlockPos pos = BlockPos.containing(x, y, z);
		Holder<Biome> biome = level.getBiome(pos);
		IButterflySpecies species = getGenome().getActiveAllele(ButterflyChromosomes.SPECIES).value();
		return (species.getSpawnBiomes() == null || biome.is(species.getSpawnBiomes())) && isAcceptedEnvironment(level, pos);
	}

	@Override
	public boolean canTakeFlight(Level level, double x, double y, double z) {
		return canFly(level) && isAcceptedEnvironment(level, x, y, z);
	}

	private boolean canFly(Level world) {
		return (!world.isRaining() || getGenome().getActiveAllele(ButterflyChromosomes.TOLERATES_RAIN).value())
				&& isActiveThisTime(world.isBrightOutside());
	}

	@Override
	public boolean isAcceptedEnvironment(Level world, BlockPos pos) {
		Holder<Biome> biome = world.getBiome(pos);
		TemperatureType biomeTemperature = IForestryApi.get().getClimateManager().getTemperature(biome);
		HumidityType biomeHumidity = IForestryApi.get().getClimateManager().getHumidity(biome);
		ToleranceType temperatureTolerance = getGenome().getActiveAllele(ButterflyChromosomes.TEMPERATURE_TOLERANCE).value();
		ToleranceType humidityTolerance = getGenome().getActiveAllele(ButterflyChromosomes.HUMIDITY_TOLERANCE).value();
		return ClimateHelper.isWithinLimits(biomeTemperature, biomeHumidity,
				this.species.getTemperature(), temperatureTolerance,
				this.species.getHumidity(), humidityTolerance);
	}

	@Nullable
	@Override
	public IButterfly spawnCaterpillar(IButterflyNursery nursery) {
		if (this.mate == null) {
			return null;
		}
		Level level = nursery.getWorldObj();
		if (level == null) {
			return null;
		}
		Mating.MatingResult result = Mating.resolveOffspringGenome(
				ButterflyChromosomes.SPECIES,
				LepidopterologyGenetics::getDefaultGenome,
				LepidopterologyGenetics::getMutations,
				this.genome,
				this.mate,
				level,
				nursery.getCoordinates(),
				level.getRandom());
		return new Butterfly(result.genome());
	}

	private boolean isActiveThisTime(boolean isDayTime) {
		if (getGenome().getActiveAllele(ButterflyChromosomes.NEVER_SLEEPS).value()) {
			return true;
		}
		return isDayTime != this.species.isNocturnal();
	}

	@Override
	public List<ItemStack> getLootDrop(IEntityButterfly entity, boolean playerKill, int lootLevel) {
		ArrayList<ItemStack> drop = new ArrayList<>();
		PathfinderMob creature = entity.getEntity();
		float metabolism = (float) getGenome().getActiveAllele(ButterflyChromosomes.METABOLISM).value() / 10;
		List<? extends IProduct> products = this.species.getButterflyLoot();
		RandomSource random = creature.level().getRandom();
		for (IProduct product : products) {
			if (random.nextFloat() < product.chance() * metabolism) {
				drop.add(product.createStack());
			}
		}
		return drop;
	}

	@Override
	public List<ItemStack> getCaterpillarDrop(IButterflyNursery nursery, boolean playerKill, int lootLevel) {
		ArrayList<ItemStack> drop = new ArrayList<>();
		float metabolism = (float) getGenome().getActiveAllele(ButterflyChromosomes.METABOLISM).value() / 10;
		List<? extends IProduct> products = this.species.getCaterpillarProducts();
		for (IProduct product : products) {
			if (rand.nextFloat() < product.chance() * metabolism) {
				drop.add(product.createStack());
			}
		}
		return drop;
	}

	@Override
	public List<ItemStack> getCocoonDrop(boolean includeButterfly, IButterflyCocoon cocoon) {
		ArrayList<ItemStack> drop = new ArrayList<>();
		float metabolism = (float) getGenome().getActiveAllele(ButterflyChromosomes.METABOLISM).value() / 10;
		List<? extends IProduct> products = cocoon.getProducts();
		for (IProduct product : products) {
			if (rand.nextFloat() < product.chance() * metabolism) {
				drop.add(product.createStack());
			}
		}
		if (ModuleLepidopterology.getSerumChance() > 0) {
			if (rand.nextFloat() < ModuleLepidopterology.getSerumChance() * metabolism) {
				ItemStack stack = getType().createStack(this, ButterflyLifeStage.SERUM);
				if (ModuleLepidopterology.getSecondSerumChance() > 0
						&& rand.nextFloat() < ModuleLepidopterology.getSecondSerumChance() * metabolism) {
					stack.setCount(2);
				}
				drop.add(stack);
			}
		}
		if (includeButterfly) {
			drop.add(getType().createStack(this, ButterflyLifeStage.BUTTERFLY));
		}
		return drop;
	}

	@Override
	public Component getDisplayName() {
		return getSpecies().getDisplayName();
	}

	@Override
	protected void savePropertiesToStack(ItemStack stack) {
		super.savePropertiesToStack(stack);
		stack.set(LepidopterologyDataComponents.BUTTERFLY_GENOME.type(), genome);
		if (mate != null) {
			stack.set(LepidopterologyDataComponents.BUTTERFLY_MATE_GENOME.type(), mate);
		} else {
			stack.remove(LepidopterologyDataComponents.BUTTERFLY_MATE_GENOME.type());
		}
	}

	@Override
	public void loadPropertiesFromStack(ItemStack stack) {
		super.loadPropertiesFromStack(stack);
		setMate(stack.get(LepidopterologyDataComponents.BUTTERFLY_MATE_GENOME.type()));
	}

	@Nullable
	public static Butterfly fromStack(ItemStack stack) {
		IGenome genome = stack.get(LepidopterologyDataComponents.BUTTERFLY_GENOME.type());
		if (genome == null) {
			return null;
		}
		Butterfly butterfly = new Butterfly(genome);
		butterfly.loadPropertiesFromStack(stack);
		return butterfly;
	}
}
