package com.leon1236.reforestry.core.genetics.alleles;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;

record FloatAllele(Identifier alleleId, float value, boolean dominant) implements IFloatAllele {
}
