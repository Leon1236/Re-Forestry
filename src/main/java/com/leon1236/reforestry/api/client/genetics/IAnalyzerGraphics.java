package com.leon1236.reforestry.api.client.genetics;

import java.util.List;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IAnalyzerGraphics {
	void drawChromosomeRow(IChromosome<? extends IAllele> chromosome);

	void drawSpeciesHeader();

	void drawSpeciesIconsRow(@Nullable Function<Identifier, ItemStack> iconGetter);

	<S> void drawProductList(Function<S, List<? extends IProduct>> getProducts);

	void drawText(Component text);

	void drawText(Component text, int xOffset);

	void drawColoredText(Component text, int color);

	void addLineSpacing(int lines);

	void setHaploid(boolean haploid);

	void drawTaxonomyPage(IGenome genome);

	void drawMutationsPage(IGenome genome, Function<Identifier, ItemStack> iconGetter);

	default void drawMutationsPage(IGenome genome) {
		drawMutationsPage(genome, id -> ItemStack.EMPTY);
	}

	int colorForDominance(boolean dominant);
}
