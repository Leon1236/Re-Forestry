package com.leon1236.reforestry.api.genetics.alleles;

import net.minecraft.core.Vec3i;

import com.leon1236.reforestry.api.apiculture.IActivityType;
import com.leon1236.reforestry.api.apiculture.IFlowerType;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;

public final class BeeChromosomes {
	@SuppressWarnings("unchecked")
	public static final IRegistryChromosome<IBeeSpecies> SPECIES =
			(IRegistryChromosome<IBeeSpecies>) (IRegistryChromosome<?>)
					com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.SPECIES;
	public static final IChromosome<IFloatAllele> SPEED = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.SPEED;
	public static final IChromosome<IIntegerAllele> LIFESPAN = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.LIFESPAN;
	public static final IChromosome<IIntegerAllele> FERTILITY = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.FERTILITY;
	public static final IChromosome<IValueAllele<ToleranceType>> TEMPERATURE_TOLERANCE =
			com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.TEMPERATURE_TOLERANCE;
	public static final IChromosome<IValueAllele<ToleranceType>> HUMIDITY_TOLERANCE =
			com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.HUMIDITY_TOLERANCE;
	public static final IRegistryChromosome<IActivityType> ACTIVITY = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.ACTIVITY;
	public static final IChromosome<IBooleanAllele> CAVE_DWELLING = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.CAVE_DWELLING;
	public static final IChromosome<IBooleanAllele> TOLERATES_RAIN = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.TOLERATES_RAIN;
	@SuppressWarnings("unchecked")
	public static final IRegistryChromosome<IFlowerType> FLOWER_TYPE =
			(IRegistryChromosome<IFlowerType>) (IRegistryChromosome<?>)
					com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.FLOWER_TYPE;
	public static final IRegistryChromosome<IBeeEffect> EFFECT = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.EFFECT;
	public static final IChromosome<IIntegerAllele> POLLINATION = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.POLLINATION;
	public static final IChromosome<IValueAllele<Vec3i>> TERRITORY = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.TERRITORY;
	public static final IKaryotype KARYOTYPE = com.leon1236.reforestry.apiculture.genetics.BeeChromosomes.KARYOTYPE;

	private BeeChromosomes() {
	}
}
