package com.leon1236.reforestry.apiculture.client;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.client.genetics.IAnalyzerGraphics;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;

public class BeeAnalyzerPlugin implements IAnalyzerPlugin {
	@Override
	public void drawPage1(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawSpeciesHeader();
		graphics.drawChromosomeRow(BeeChromosomes.SPECIES);
		graphics.addLineSpacing(1);
		graphics.drawChromosomeRow(BeeChromosomes.LIFESPAN);
		graphics.drawChromosomeRow(BeeChromosomes.SPEED);
		graphics.drawChromosomeRow(BeeChromosomes.POLLINATION);
		graphics.drawChromosomeRow(BeeChromosomes.FLOWER_TYPE);
		graphics.drawChromosomeRow(BeeChromosomes.FERTILITY);
		graphics.drawChromosomeRow(BeeChromosomes.TERRITORY);
		graphics.drawChromosomeRow(BeeChromosomes.EFFECT);
	}

	@Override
	public void drawPage2(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawSpeciesHeader();
		graphics.drawChromosomeRow(BeeChromosomes.TEMPERATURE_TOLERANCE);
		graphics.drawChromosomeRow(BeeChromosomes.HUMIDITY_TOLERANCE);
		graphics.drawChromosomeRow(BeeChromosomes.ACTIVITY);
		graphics.drawChromosomeRow(BeeChromosomes.TOLERATES_RAIN);
		graphics.drawChromosomeRow(BeeChromosomes.CAVE_DWELLING);
	}

	@Override
	public void drawPage3(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawText(net.minecraft.network.chat.Component.translatable("for.gui.beealyzer.produce").append(":"));
		graphics.addLineSpacing(1);
		graphics.drawText(net.minecraft.network.chat.Component.translatable("for.gui.alyzer.overview"));
	}

	@Override
	public void drawPage4(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawMutationsPage(genome);
	}
}
