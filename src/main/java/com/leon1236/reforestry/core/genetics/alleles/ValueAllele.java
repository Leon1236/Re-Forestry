package com.leon1236.reforestry.core.genetics.alleles;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;

record ValueAllele<V>(Identifier alleleId, V value, boolean dominant) implements IValueAllele<V> {
}
