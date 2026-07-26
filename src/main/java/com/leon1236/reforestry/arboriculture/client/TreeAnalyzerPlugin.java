package com.leon1236.reforestry.arboriculture.client;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.client.genetics.IAnalyzerGraphics;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public class TreeAnalyzerPlugin implements IAnalyzerPlugin {
	@Override
	public void drawPage1(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawSpeciesHeader();
		graphics.drawChromosomeRow(TreeChromosomes.SPECIES);
		graphics.addLineSpacing(1);
		graphics.drawChromosomeRow(TreeChromosomes.SAPLINGS);
		graphics.drawChromosomeRow(TreeChromosomes.MATURATION);
		graphics.drawChromosomeRow(TreeChromosomes.HEIGHT);
		graphics.drawChromosomeRow(TreeChromosomes.GIRTH);
		graphics.drawChromosomeRow(TreeChromosomes.YIELD);
		graphics.drawChromosomeRow(TreeChromosomes.SAPPINESS);
		graphics.drawChromosomeRow(TreeChromosomes.EFFECT);
	}

	@Override
	public void drawPage2(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawSpeciesHeader();
		graphics.drawChromosomeRow(TreeChromosomes.FIREPROOF);
		graphics.drawChromosomeRow(TreeChromosomes.FRUIT);
	}

	@Override
	public void drawPage3(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.setHaploid(true);
		graphics.drawText(Component.translatable("for.gui.beealyzer.produce").append(":"));
		graphics.addLineSpacing(1);
		graphics.drawText(Component.translatable("for.gui.alyzer.overview"));
	}

	@Override
	public void drawPage4(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawMutationsPage(genome);
	}
}
