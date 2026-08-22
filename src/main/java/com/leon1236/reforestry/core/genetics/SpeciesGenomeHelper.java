package com.leon1236.reforestry.core.genetics;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;

public final class SpeciesGenomeHelper {
	private SpeciesGenomeHelper() {
	}

	public static void applyTaxonAlleles(IGenomeBuilder builder, IKaryotype karyotype, String genus) {
		ITaxon taxon = IForestryApi.get().getGeneticManager().getTaxonSafe(genus);
		if (taxon == null) {
			return;
		}
		ITaxon[] ancestry = IForestryApi.get().getGeneticManager().getParentTaxa(genus);
		for (ITaxon ancestor : ancestry) {
			for (var entry : ancestor.alleles().entrySet()) {
				IChromosome<?> chromosome = entry.getKey();
				if (!karyotype.chromosomes().contains(chromosome)) {
					ReForestry.LOGGER.warn(
							"Default allele set by taxon {} skipped for genus {} due to being invalid for karyotype {}",
							ancestor.name(), genus, karyotype.id());
					continue;
				}
				applyTaxonAllele(builder, chromosome, entry.getValue());
			}
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static void applyTaxonAllele(IGenomeBuilder builder, IChromosome<?> chromosome, ITaxon.TaxonAllele taxonAllele) {
		if (taxonAllele.reference() != null) {
			if (chromosome instanceof IRegistryChromosome<?> registryChromosome) {
				IAllele resolved = resolveRegistryReference(registryChromosome, taxonAllele);
				if (resolved != null) {
					builder.set((IChromosome) chromosome, resolved);
				}
			}
			return;
		}
		IAllele allele = taxonAllele.allele();
		if (allele != null) {
			builder.set((IChromosome) chromosome, allele);
		}
	}

	private static IAllele resolveRegistryReference(IRegistryChromosome<?> registryChromosome,
			ITaxon.TaxonAllele taxonAllele) {
		return registryChromosome.getSafe(taxonAllele.reference())
				.map(value -> com.leon1236.reforestry.core.genetics.alleles.AlleleManager.INSTANCE
						.registryAllele(value, taxonAllele.required()))
				.orElseGet(() -> {
					ReForestry.LOGGER.warn("Taxon allele reference '{}' not found for chromosome {}",
							taxonAllele.reference(), registryChromosome.id());
					return null;
				});
	}

	public static <A extends IAllele> IGenomeBuilder createDefaultGenomeBuilder(IKaryotype karyotype, String genus,
			IChromosome<A> speciesChromosome, A speciesAllele) {
		IGenomeBuilder builder = karyotype.genomeBuilder();
		applyTaxonAlleles(builder, karyotype, genus);
		builder.set(speciesChromosome, speciesAllele);
		return builder;
	}
}
