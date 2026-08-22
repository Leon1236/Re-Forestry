package com.leon1236.reforestry.apiculture.client;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.client.genetics.IAnalyzerGraphics;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;

public class BeeAnalyzerPlugin implements IAnalyzerPlugin {
	@Override
	public void drawPage1(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.setHaploid("drone".equals(lifeStage));
		graphics.drawSpeciesIconsRow(this::droneIcon);
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
		graphics.setHaploid("drone".equals(lifeStage));
		graphics.drawSpeciesHeader();
		graphics.drawChromosomeRow(BeeChromosomes.TEMPERATURE_TOLERANCE);
		graphics.drawChromosomeRow(BeeChromosomes.HUMIDITY_TOLERANCE);
		graphics.drawChromosomeRow(BeeChromosomes.ACTIVITY);
		graphics.drawChromosomeRow(BeeChromosomes.TOLERATES_RAIN);
		graphics.drawChromosomeRow(BeeChromosomes.CAVE_DWELLING);

		if ("princess".equals(lifeStage) || "queen".equals(lifeStage)) {
			boolean pristine = BeeStackHelper.isPristine(specimen);
			Component stock = Component.translatable(pristine ? "for.bees.stock.pristine" : "for.bees.stock.ignoble")
					.withStyle(style -> style.withItalic(pristine));
			graphics.drawColoredText(stock, 0xFF14D50B);
			graphics.addLineSpacing(1);
			int generation = BeeStackHelper.getGeneration(specimen);
			if (generation > 0) {
				graphics.drawColoredText(
						Component.translatable("for.gui.beealyzer.generations", generation), 0xFF14D50B);
				graphics.addLineSpacing(1);
			}
		}
	}

	@Override
	public void drawPage3(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.setHaploid("drone".equals(lifeStage));
		graphics.drawText(Component.translatable("for.gui.beealyzer.produce").append(":"));
		graphics.addLineSpacing(1);
		graphics.drawProductList(IBeeSpecies::products);

		graphics.addLineSpacing(4);

		graphics.setHaploid(true);
		graphics.drawText(Component.translatable("for.gui.beealyzer.specialty").append(":"));
		graphics.addLineSpacing(1);
		graphics.drawProductList(IBeeSpecies::specialties);
	}

	@Override
	public void drawPage4(IAnalyzerGraphics graphics, IGenome genome, String lifeStage, ItemStack specimen) {
		graphics.drawMutationsPage(genome, this::droneIcon);
	}

	private ItemStack droneIcon(Identifier speciesId) {
		if (ApicultureGenetics.getSpeciesSafe(speciesId) == null) {
			return ItemStack.EMPTY;
		}
		return BeeStackHelper.createBeeStack(
				ApicultureItems.BEE_DRONE.item(),
				ApicultureGenetics.getDefaultGenome(speciesId),
				true,
				0);
	}
}
