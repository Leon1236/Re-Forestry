package com.leon1236.reforestry.lepidopterology.client;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.client.genetics.IAnalyzerGraphics;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;

public class ButterflyAnalyzerPlugin implements IAnalyzerPlugin {
	@Override
	public void drawPage1(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawSpeciesHeader();
		graphics.drawChromosomeRow(ButterflyChromosomes.SPECIES);
		graphics.addLineSpacing(1);
		graphics.drawChromosomeRow(ButterflyChromosomes.SIZE);
		graphics.drawChromosomeRow(ButterflyChromosomes.SPEED);
		graphics.drawChromosomeRow(ButterflyChromosomes.METABOLISM);
		graphics.drawChromosomeRow(ButterflyChromosomes.FERTILITY);
		graphics.drawChromosomeRow(ButterflyChromosomes.FLOWER_TYPE);
		graphics.drawChromosomeRow(ButterflyChromosomes.EFFECT);
	}

	@Override
	public void drawPage2(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawSpeciesHeader();
		graphics.drawClimatePreferences(ButterflyChromosomes.TEMPERATURE_TOLERANCE, ButterflyChromosomes.HUMIDITY_TOLERANCE,
				genome);
		graphics.drawChromosomeRow(ButterflyChromosomes.NEVER_SLEEPS);
		graphics.drawChromosomeRow(ButterflyChromosomes.TOLERATES_RAIN);
		graphics.drawChromosomeRow(ButterflyChromosomes.FIREPROOF);
	}

	@Override
	public void drawPage3(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.setHaploid(true);
		IButterflySpecies species = genome.getActiveAllele(ButterflyChromosomes.SPECIES).value();

		graphics.drawText(Component.translatable("for.gui.loot.butterfly").append(":"));
		graphics.addLineSpacing(1);
		drawProducts(graphics, species.getButterflyLoot());

		graphics.addLineSpacing(2);
		graphics.drawText(Component.translatable("for.gui.loot.caterpillar").append(":"));
		graphics.addLineSpacing(1);
		drawProducts(graphics, species.getCaterpillarProducts());

		graphics.addLineSpacing(2);
		graphics.drawText(Component.translatable("for.gui.loot.cocoon").append(":"));
		graphics.addLineSpacing(1);
		IButterflyCocoon cocoon = genome.getActiveAllele(ButterflyChromosomes.COCOON).value();
		drawProducts(graphics, cocoon.getProducts());
	}

	@Override
	public void drawPage4(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawMutationsPage(genome);
	}

	private static void drawProducts(IAnalyzerGraphics graphics, List<? extends IProduct> products) {
		if (products.isEmpty()) {
			graphics.drawText(Component.translatable("for.gui.alyzer.overview"));
			return;
		}
		for (IProduct product : products) {
			int percent = Math.round(product.chance() * 100f);
			graphics.drawText(product.createStack().getHoverName()
					.copy()
					.append(" x")
					.append(String.valueOf(product.count()))
					.append(" (")
					.append(String.valueOf(percent))
					.append("%)"));
		}
	}
}
