package com.leon1236.reforestry.api.genetics.chromosomes;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;

public interface IChromosome<A extends IAllele> {
    Identifier id();

    default String getChromosomeTranslationKey() {
        return Util.makeDescriptionId("chromosome", id());
    }

    default MutableComponent getChromosomeDisplayName() {
        return Component.translatable(getChromosomeTranslationKey());
    }

    default String getTranslationKey(A allele) {
        if (allele instanceof IBooleanAllele booleanAllele) {
            return booleanAllele.value() ? "allele.reforestry.true" : "allele.reforestry.false";
        }
        Identifier chromosomeId = id();
        Identifier alleleId = allele.alleleId();
        StringBuilder key = new StringBuilder("allele.");
        key.append(chromosomeId.getNamespace()).append('.').append(chromosomeId.getPath()).append('.');
        if (alleleId.getNamespace().equals(chromosomeId.getNamespace())) {
            key.append(alleleId.getPath());
        } else {
            key.append(alleleId.getNamespace()).append('.').append(alleleId.getPath());
        }
        return key.toString();
    }

    default MutableComponent getDisplayName(A allele) {
        return Component.translatable(getTranslationKey(allele));
    }
}
