package com.leon1236.reforestry.api.plugin;

import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface ITaxonBuilder {
	void defineSubTaxon(String name);

	void defineSubTaxon(String name, Consumer<ITaxonBuilder> action);

	default <A extends IAllele> void setDefaultChromosome(IChromosome<A> chromosome, A allele) {
		setDefaultChromosome(chromosome, allele, true);
	}

	<A extends IAllele> void setDefaultChromosome(IChromosome<A> chromosome, A allele, boolean required);

	default void setDefaultChromosome(IChromosome<?> chromosome, Identifier id) {
		setDefaultChromosome(chromosome, id, true);
	}

	void setDefaultChromosome(IChromosome<?> chromosome, Identifier id, boolean required);
}
