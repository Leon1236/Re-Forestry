package com.leon1236.reforestry.core.genetics.alleles;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;

record IntegerAllele(Identifier alleleId, int value, boolean dominant) implements IIntegerAllele {
}
