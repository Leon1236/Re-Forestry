package com.leon1236.reforestry.api.genetics;

import com.mojang.serialization.Codec;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;

public interface IAlleleManager {
    IBooleanAllele booleanAllele(boolean value, boolean dominant);

    IFloatAllele floatAllele(float value, boolean dominant);

    IIntegerAllele integerAllele(int value, boolean dominant);

    <V> IValueAllele<V> valueAllele(String baseId, V value, boolean dominant);

    <V extends IRegistryAlleleValue> IRegistryAllele<V> registryAllele(V value, boolean dominant);

    IAllele getAllele(Identifier id);

    IChromosome<IBooleanAllele> booleanChromosome(Identifier id);

    IChromosome<IFloatAllele> floatChromosome(Identifier id);

    IChromosome<IIntegerAllele> integerChromosome(Identifier id);

    <V> IChromosome<IValueAllele<V>> valueChromosome(Identifier id);

    <V extends IRegistryAlleleValue> IRegistryChromosome<V> registryChromosome(Identifier id);

    IKaryotypeBuilder karyotypeBuilder();

    void registerKaryotype(IKaryotype karyotype);

    IKaryotype getKaryotype(Identifier id);

    Codec<IAllele> alleleCodec();
}
