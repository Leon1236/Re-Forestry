package com.leon1236.reforestry.api.client.genetics;

import java.util.List;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface IAnalyzerPlugin {
	void drawPage1(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen);

	void drawPage2(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen);

	void drawPage3(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen);

	void drawPage4(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen);

	default void drawPage5(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawTaxonomyPage(genome);
	}

	default List<String> getHints() {
		return List.of();
	}
}
