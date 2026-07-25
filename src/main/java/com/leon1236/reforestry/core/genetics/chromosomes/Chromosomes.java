package com.leon1236.reforestry.core.genetics.chromosomes;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;

public final class Chromosomes {
    private Chromosomes() {
    }

    public static IChromosome<IBooleanAllele> booleanChromosome(Identifier id) {
        return new Chromosome<>(id);
    }

    public static IChromosome<IFloatAllele> floatChromosome(Identifier id) {
        return new Chromosome<>(id);
    }

    public static IChromosome<IIntegerAllele> integerChromosome(Identifier id) {
        return new Chromosome<>(id);
    }

    public static <V> IChromosome<IValueAllele<V>> valueChromosome(Identifier id) {
        return new Chromosome<>(id);
    }

    public static <V extends IRegistryAlleleValue> IRegistryChromosome<V> registryChromosome(Identifier id) {
        return new RegistryChromosome<>(id);
    }
}
