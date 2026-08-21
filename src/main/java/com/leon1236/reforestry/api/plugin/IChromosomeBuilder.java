package com.leon1236.reforestry.api.plugin;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;

public interface IChromosomeBuilder<A extends IAllele> {
	IChromosomeBuilder<A> setDefault(A allele);

	IChromosomeBuilder<A> setWeaklyInherited(boolean weaklyInherited);
}
