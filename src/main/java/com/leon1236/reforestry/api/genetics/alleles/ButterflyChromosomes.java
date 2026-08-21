package com.leon1236.reforestry.api.genetics.alleles;

import com.leon1236.reforestry.api.apiculture.IFlowerType;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyEffect;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;

public final class ButterflyChromosomes {
	public static final IRegistryChromosome<IButterflySpecies> SPECIES = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.SPECIES;
	public static final IChromosome<IFloatAllele> SIZE = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.SIZE;
	public static final IChromosome<IFloatAllele> SPEED = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.SPEED;
	public static final IChromosome<IIntegerAllele> LIFESPAN = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.LIFESPAN;
	public static final IChromosome<IIntegerAllele> METABOLISM = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.METABOLISM;
	public static final IChromosome<IIntegerAllele> FERTILITY = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.FERTILITY;
	public static final IChromosome<IValueAllele<ToleranceType>> TEMPERATURE_TOLERANCE =
			com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.TEMPERATURE_TOLERANCE;
	public static final IChromosome<IValueAllele<ToleranceType>> HUMIDITY_TOLERANCE =
			com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.HUMIDITY_TOLERANCE;
	public static final IChromosome<IBooleanAllele> NEVER_SLEEPS = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.NEVER_SLEEPS;
	public static final IChromosome<IBooleanAllele> TOLERATES_RAIN = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.TOLERATES_RAIN;
	public static final IChromosome<IBooleanAllele> FIREPROOF = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.FIREPROOF;
	public static final IRegistryChromosome<IFlowerType> FLOWER_TYPE = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.FLOWER_TYPE;
	public static final IRegistryChromosome<IButterflyEffect> EFFECT = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.EFFECT;
	public static final IRegistryChromosome<IButterflyCocoon> COCOON = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.COCOON;
	public static final IKaryotype KARYOTYPE = com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes.KARYOTYPE;

	private ButterflyChromosomes() {
	}
}
