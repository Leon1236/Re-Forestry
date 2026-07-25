package com.leon1236.reforestry.core.genetics.alleles;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;

record BooleanAllele(Identifier alleleId, boolean value, boolean dominant) implements IBooleanAllele {
}
