package com.leon1236.reforestry.api.client.genetics;

import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IAnalyzerGraphics {
	void drawChromosomeRow(IChromosome<? extends IAllele> chromosome);

	void drawSpeciesHeader();

	void drawText(Component text);

	void drawText(Component text, int xOffset);

	void addLineSpacing(int lines);

	void setHaploid(boolean haploid);

	void drawTaxonomyPage(IGenome genome);

	void drawMutationsPage(IGenome genome);

	int colorForDominance(boolean dominant);
}
