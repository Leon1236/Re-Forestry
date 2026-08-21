package com.leon1236.reforestry.api.genetics.alleles;

import com.leon1236.reforestry.api.arboriculture.ITreeSpecies;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.api.arboriculture.genetics.ITreeEffect;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;

public final class TreeChromosomes {
	@SuppressWarnings("unchecked")
	public static final IRegistryChromosome<ITreeSpecies> SPECIES =
			(IRegistryChromosome<ITreeSpecies>) (IRegistryChromosome<?>)
					com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.SPECIES;
	public static final IChromosome<IFloatAllele> HEIGHT = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.HEIGHT;
	public static final IChromosome<IFloatAllele> SAPLINGS = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.SAPLINGS;
	public static final IRegistryChromosome<IFruit> FRUIT = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.FRUIT;
	public static final IChromosome<IFloatAllele> YIELD = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.YIELD;
	public static final IChromosome<IFloatAllele> SAPPINESS = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.SAPPINESS;
	public static final IRegistryChromosome<ITreeEffect> EFFECT = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.EFFECT;
	public static final IChromosome<IIntegerAllele> MATURATION = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.MATURATION;
	public static final IChromosome<IIntegerAllele> GIRTH = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.GIRTH;
	public static final IChromosome<IBooleanAllele> FIREPROOF = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.FIREPROOF;
	public static final IKaryotype KARYOTYPE = com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes.KARYOTYPE;

	private TreeChromosomes() {
	}
}
