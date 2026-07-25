package com.leon1236.reforestry.core.genetics.alleles;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

record RegistryAllele<V extends IRegistryAlleleValue>(Identifier alleleId, V value, boolean dominant) implements IRegistryAllele<V> {
}
