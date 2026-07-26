package com.leon1236.reforestry.arboriculture.genetics;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;

public final class TreeGeneticsTooltips {
	private TreeGeneticsTooltips() {
	}

	public static void addAnalyzedTooltip(IGenome genome, Consumer<Component> tooltip) {
		GeneticsTooltips.addHybridTooltip(tooltip, genome, TreeChromosomes.SPECIES, "for.trees.hybrid");

		IFloatAllele saplings = genome.getActiveAllele(TreeChromosomes.SAPLINGS);
		IIntegerAllele maturation = genome.getActiveAllele(TreeChromosomes.MATURATION);
		IFloatAllele height = genome.getActiveAllele(TreeChromosomes.HEIGHT);
		IIntegerAllele girth = genome.getActiveAllele(TreeChromosomes.GIRTH);
		IFloatAllele yield = genome.getActiveAllele(TreeChromosomes.YIELD);
		IFloatAllele sappiness = genome.getActiveAllele(TreeChromosomes.SAPPINESS);

		tooltip.accept(Component.literal("S: ").append(TreeChromosomes.SAPLINGS.getDisplayName(saplings)).append(", ")
				.withStyle(ChatFormatting.YELLOW)
				.append(Component.literal("M: ").append(TreeChromosomes.MATURATION.getDisplayName(maturation))
						.withStyle(ChatFormatting.RED)));
		tooltip.accept(Component.literal("H: ").append(TreeChromosomes.HEIGHT.getDisplayName(height)).append(", ")
				.withStyle(ChatFormatting.LIGHT_PURPLE)
				.append(Component.literal("G: ").append(TreeChromosomes.GIRTH.getDisplayName(girth))
						.withStyle(ChatFormatting.AQUA)));
		tooltip.accept(Component.literal("Y: ").append(TreeChromosomes.YIELD.getDisplayName(yield)).append(", ")
				.withStyle(ChatFormatting.WHITE)
				.append(Component.literal("S: ").append(TreeChromosomes.SAPPINESS.getDisplayName(sappiness))
						.withStyle(ChatFormatting.GOLD)));

		IBooleanAllele fireproof = genome.getActiveAllele(TreeChromosomes.FIREPROOF);
		if (fireproof.value()) {
			tooltip.accept(TreeChromosomes.FIREPROOF.getChromosomeDisplayName().withStyle(ChatFormatting.RED));
		}

		IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
		ITreeEffect effect = genome.getActiveAllele(TreeChromosomes.EFFECT).value();
		MutableComponent fruitAndEffect = null;
		if (fruit != DefaultFruits.NONE) {
			fruitAndEffect = Component.literal("F: ")
					.append(TreeChromosomes.FRUIT.getDisplayName(genome.getActiveAllele(TreeChromosomes.FRUIT)))
					.withStyle(ChatFormatting.GREEN);
		}
		if (effect != TreeEffect.NONE) {
			MutableComponent effectLine = Component.literal("E: ")
					.append(TreeChromosomes.EFFECT.getDisplayName(genome.getActiveAllele(TreeChromosomes.EFFECT)))
					.withStyle(ChatFormatting.DARK_AQUA);
			if (fruitAndEffect != null) {
				fruitAndEffect.append(Component.literal(", ")).append(effectLine);
			} else {
				fruitAndEffect = effectLine;
			}
		}
		if (fruitAndEffect != null) {
			tooltip.accept(fruitAndEffect);
		}
	}
}
