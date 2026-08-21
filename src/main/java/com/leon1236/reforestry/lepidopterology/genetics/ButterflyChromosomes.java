package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.List;

import com.google.common.collect.ImmutableMap;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.apiculture.IFlowerType;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.api.lepidopterology.ForestryButterflyEffects;
import com.leon1236.reforestry.api.lepidopterology.ForestryCocoons;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyEffect;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.FlowerType;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class ButterflyChromosomes {
	public static final IRegistryChromosome<IButterflySpecies> SPECIES = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("butterfly_species"));
	public static final IChromosome<IFloatAllele> SIZE = AlleleManager.INSTANCE.floatChromosome(ReForestry.id("size"));
	public static final IChromosome<IFloatAllele> SPEED = BeeChromosomes.SPEED;
	public static final IChromosome<IIntegerAllele> LIFESPAN = AlleleManager.INSTANCE.integerChromosome(ReForestry.id("butterfly_lifespan"));
	public static final IChromosome<IIntegerAllele> METABOLISM = AlleleManager.INSTANCE.integerChromosome(ReForestry.id("metabolism"));
	public static final IChromosome<IIntegerAllele> FERTILITY = BeeChromosomes.FERTILITY;
	public static final IChromosome<IValueAllele<ToleranceType>> TEMPERATURE_TOLERANCE = BeeChromosomes.TEMPERATURE_TOLERANCE;
	public static final IChromosome<IValueAllele<ToleranceType>> HUMIDITY_TOLERANCE = BeeChromosomes.HUMIDITY_TOLERANCE;
	public static final IChromosome<IBooleanAllele> NEVER_SLEEPS = AlleleManager.INSTANCE.booleanChromosome(ReForestry.id("never_sleeps"));
	public static final IChromosome<IBooleanAllele> TOLERATES_RAIN = BeeChromosomes.TOLERATES_RAIN;
	public static final IChromosome<IBooleanAllele> FIREPROOF = TreeChromosomes.FIREPROOF;
	@SuppressWarnings("unchecked")
	public static final IRegistryChromosome<IFlowerType> FLOWER_TYPE =
			(IRegistryChromosome<IFlowerType>) (IRegistryChromosome<?>) BeeChromosomes.FLOWER_TYPE;
	public static final IRegistryChromosome<IButterflyEffect> EFFECT = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("butterfly_effect"));
	public static final IRegistryChromosome<IButterflyCocoon> COCOON = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("cocoon"));

	public static final DummyButterflyEffect NONE_EFFECT = new DummyButterflyEffect(ForestryButterflyEffects.NONE, true);
	public static final DummyCocoon DEFAULT_COCOON = new DummyCocoon(ForestryCocoons.DEFAULT, true, List.of());
	public static final DummyCocoon SILK_COCOON = new DummyCocoon(ForestryCocoons.SILK, true, List.of());

	public static final IKaryotype KARYOTYPE;

	static {
		EFFECT.populate(ImmutableMap.of(NONE_EFFECT.id(), NONE_EFFECT));
		COCOON.populate(ImmutableMap.of(DEFAULT_COCOON.id(), DEFAULT_COCOON));

		KARYOTYPE = AlleleManager.INSTANCE.karyotypeBuilder()
				.setSpecies(SPECIES)
				.set(SIZE, ForestryAlleles.SIZE_SMALL)
				.set(SPEED, ForestryAlleles.SPEED_SLOWEST)
				.set(LIFESPAN, ForestryAlleles.LIFESPAN_SHORTER)
				.set(METABOLISM, ForestryAlleles.METABOLISM_SLOWER)
				.set(FERTILITY, ForestryAlleles.FERTILITY_3)
				.set(TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_NONE)
				.set(HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_NONE)
				.set(NEVER_SLEEPS, AlleleManager.INSTANCE.booleanAllele(false, true))
				.set(TOLERATES_RAIN, AlleleManager.INSTANCE.booleanAllele(false, true))
				.set(FIREPROOF, AlleleManager.INSTANCE.booleanAllele(false, true))
				.set(BeeChromosomes.FLOWER_TYPE, AlleleManager.INSTANCE.registryAllele(FlowerType.VANILLA, true))
				.set(EFFECT, AlleleManager.INSTANCE.registryAllele(NONE_EFFECT, true))
				.set(COCOON, AlleleManager.INSTANCE.registryAllele(DEFAULT_COCOON, true))
				.setWeaklyInherited(TEMPERATURE_TOLERANCE)
				.setWeaklyInherited(HUMIDITY_TOLERANCE)
				.setWeaklyInherited(NEVER_SLEEPS)
				.setWeaklyInherited(TOLERATES_RAIN)
				.create(ReForestry.id("butterflies"));
	}

	private ButterflyChromosomes() {
	}
}
