package com.leon1236.reforestry.api.genetics;

import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface ITaxon {
	String name();

	TaxonomicRank rank();

	List<ITaxon> children();

	List<ISpecies<?>> species();

	@Nullable
	ITaxon parent();

	Map<IChromosome<?>, TaxonAllele> alleles();

	record TaxonAllele(@Nullable IAllele allele, @Nullable Identifier reference, boolean required) {
		public static TaxonAllele data(IAllele allele, boolean required) {
			return new TaxonAllele(allele, null, required);
		}

		public static TaxonAllele reference(Identifier id, boolean required) {
			return new TaxonAllele(null, id, required);
		}
	}
}
