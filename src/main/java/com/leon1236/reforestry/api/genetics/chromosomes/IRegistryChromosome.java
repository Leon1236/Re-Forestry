package com.leon1236.reforestry.api.genetics.chromosomes;

import java.util.Collection;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IRegistryChromosome<V extends IRegistryAlleleValue> extends IChromosome<IRegistryAllele<V>> {
    Optional<V> getSafe(Identifier valueId);

    Collection<V> values();

    void populate(ImmutableMap<Identifier, V> values);

    boolean isPopulated();
}
