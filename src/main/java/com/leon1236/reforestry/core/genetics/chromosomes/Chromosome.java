package com.leon1236.reforestry.core.genetics.chromosomes;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

record Chromosome<A extends IAllele>(Identifier id) implements IChromosome<A> {
}
