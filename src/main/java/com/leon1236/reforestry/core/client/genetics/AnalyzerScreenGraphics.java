package com.leon1236.reforestry.core.client.genetics;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import com.leon1236.reforestry.api.client.genetics.IAnalyzerGraphics;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.core.client.ScreenPortableAnalyzer;

public class AnalyzerScreenGraphics implements IAnalyzerGraphics {
	public static final int COLUMN_1 = 78;
	public static final int COLUMN_2 = 143;

	private static final int LABEL_COLOR = 0xFF404040;

	private final GuiGraphicsExtractor graphics;
	private final Font font;
	private final IGenome genome;

	private boolean haploid;
	private int currentX;
	private int currentY;

	public AnalyzerScreenGraphics(GuiGraphicsExtractor graphics, Font font, IGenome genome) {
		this.graphics = graphics;
		this.font = font;
		this.genome = genome;
		this.currentX = 12;
		this.currentY = 12;
	}

	@Override
	public void drawChromosomeRow(IChromosome<? extends IAllele> chromosome) {
		@SuppressWarnings("unchecked")
		IChromosome<IAllele> typed = (IChromosome<IAllele>) chromosome;
		IAllele active = this.genome.getActiveAllele(typed);
		IAllele inactive = this.genome.getInactiveAllele(typed);

		drawColored(typed.getChromosomeDisplayName(), 0, LABEL_COLOR);
		drawColored(typed.getDisplayName(active), COLUMN_1, colorForDominance(active.dominant()));
		if (!this.haploid) {
			drawColored(typed.getDisplayName(inactive), COLUMN_2, colorForDominance(inactive.dominant()));
		}
		addLineSpacing(1);
	}

	@Override
	public void drawSpeciesHeader() {
		drawColored(Component.translatable("for.gui.active"), COLUMN_1, LABEL_COLOR);
		if (!this.haploid) {
			drawColored(Component.translatable("for.gui.inactive"), COLUMN_2, LABEL_COLOR);
		}
		addLineSpacing(2);
	}

	@Override
	public void drawText(Component text) {
		drawText(text, 0);
	}

	@Override
	public void drawText(Component text, int xOffset) {
		drawColored(text, xOffset, LABEL_COLOR);
	}

	private void drawColored(Component text, int xOffset, int color) {
		this.graphics.text(this.font, text, this.currentX + xOffset, this.currentY, color, false);
	}

	@Override
	public void addLineSpacing(int lines) {
		this.currentY += 12 * lines;
	}

	@Override
	public void setHaploid(boolean haploid) {
		this.haploid = haploid;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void drawTaxonomyPage(IGenome genome) {
		drawText(Component.translatable("for.gui.alyzer.classification").append(":"));
		addLineSpacing(1);

		IChromosome<?> speciesChromosome = genome.karyotype().speciesChromosome();
		if (!(speciesChromosome instanceof IRegistryChromosome<?> registry)) {
			drawText(Component.translatable("for.gui.alyzer.nodescription"));
			return;
		}

		IRegistryAlleleValue active = genome.getActiveAllele((IRegistryChromosome<IRegistryAlleleValue>) registry).value();
		MutableComponent speciesName = Component.translatable(
				"allele." + registry.id().getNamespace() + '.' + registry.id().getPath() + '.' + active.id().getPath());
		drawColored(speciesName, 0, 0xFF7FFF00);
		addLineSpacing(1);
		drawText(Component.translatable("for.gui.alyzer.authority").append(": Sengir"));
		addLineSpacing(2);
		drawText(Component.translatable("for.gui.alyzer.nodescription"));
	}

	@Override
	public void drawMutationsPage(IGenome genome) {
		drawText(Component.translatable("for.gui.beealyzer.mutations").append(":"));
		addLineSpacing(1);
		drawText(Component.translatable("for.gui.alyzer.overview"));
	}

	@Override
	public int colorForDominance(boolean dominant) {
		return ScreenPortableAnalyzer.getColorCoding(dominant);
	}
}
