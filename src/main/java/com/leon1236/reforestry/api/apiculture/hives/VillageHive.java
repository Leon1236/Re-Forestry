package com.leon1236.reforestry.api.apiculture.hives;

import java.util.Map;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public record VillageHive(Identifier speciesId, Map<IChromosome<?>, IAllele> alleles) {
}
