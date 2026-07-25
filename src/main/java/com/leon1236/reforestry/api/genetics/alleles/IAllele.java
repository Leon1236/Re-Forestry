package com.leon1236.reforestry.api.genetics.alleles;

import com.mojang.serialization.Codec;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public sealed interface IAllele permits IBooleanAllele, IFloatAllele, IIntegerAllele, IValueAllele {
    Codec<IAllele> CODEC = AlleleManager.INSTANCE.alleleCodec();

    Identifier alleleId();

    boolean dominant();
}
