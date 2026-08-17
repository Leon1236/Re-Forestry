package com.leon1236.reforestry.core.genetics.chromosomes;

import java.util.Collection;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;

final class RegistryChromosome<V extends IRegistryAlleleValue> implements IRegistryChromosome<V> {
    private final Identifier id;
    private ImmutableMap<Identifier, V> valuesById;

    RegistryChromosome(Identifier id) {
        this.id = id;
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public Optional<V> getSafe(Identifier valueId) {
        if (valuesById == null) {
            return Optional.empty();
        }
        V value = valuesById.get(valueId);
        return value == null ? Optional.empty() : Optional.of(value);
    }

    @Override
    public Collection<V> values() {
        return valuesById == null ? ImmutableMap.<Identifier, V>of().values() : valuesById.values();
    }

    @Override
    public void populate(ImmutableMap<Identifier, V> values) {
        if (valuesById != null) {
            throw new IllegalStateException("Registry chromosome " + id + " has already been populated");
        }
        valuesById = values;
    }

    @Override
    public boolean isPopulated() {
        return valuesById != null;
    }
}
