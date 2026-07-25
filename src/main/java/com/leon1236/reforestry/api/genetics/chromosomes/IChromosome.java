package com.leon1236.reforestry.api.genetics.chromosomes;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;

public interface IChromosome<A extends IAllele> {
    Identifier id();
}
