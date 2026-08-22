package com.leon1236.reforestry.arboriculture.client;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.client.genetics.IAnalyzerGraphics;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public class TreeAnalyzerPlugin implements IAnalyzerPlugin {
	@Override
	public void drawPage1(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawSpeciesIconsRow(this::saplingIcon);
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
		IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
		graphics.setHaploid(true);
		graphics.drawText(Component.translatable("for.gui.beealyzer.produce").append(":"));
		graphics.addLineSpacing(1);
		graphics.drawProductList(species -> asProducts(fruit.getProducts()));

		graphics.addLineSpacing(4);

		graphics.drawText(Component.translatable("for.gui.beealyzer.specialty").append(":"));
		graphics.addLineSpacing(1);
		graphics.drawProductList(species -> asProducts(fruit.getSpecialties()));
	}

	@Override
	public void drawPage4(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawMutationsPage(genome, this::saplingIcon);
	}

	private ItemStack saplingIcon(Identifier speciesId) {
		if (ArboricultureGenetics.getSpeciesSafe(speciesId) == null) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = new ItemStack(ArboricultureItems.SAPLING.item());
		stack.set(ArboricultureDataComponents.TREE_GENOME.type(), ArboricultureGenetics.getDefaultGenome(speciesId));
		return stack;
	}

	private static List<IProduct> asProducts(List<? extends IProduct> fruitProducts) {
		List<IProduct> products = new ArrayList<>(fruitProducts.size());
		products.addAll(fruitProducts);
		return products;
	}
}
