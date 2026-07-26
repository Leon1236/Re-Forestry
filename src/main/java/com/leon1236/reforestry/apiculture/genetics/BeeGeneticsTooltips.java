package com.leon1236.reforestry.apiculture.genetics;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.ClimateHelper;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;

public final class BeeGeneticsTooltips {
	private BeeGeneticsTooltips() {
	}

	public static void addAnalyzedTooltip(IGenome genome, Consumer<Component> tooltip) {
		GeneticsTooltips.addHybridTooltip(tooltip, genome, BeeChromosomes.SPECIES, "for.bees.hybrid");

		IIntegerAllele lifespan = genome.getActiveAllele(BeeChromosomes.LIFESPAN);
		IFloatAllele speed = genome.getActiveAllele(BeeChromosomes.SPEED);
		tooltip.accept(BeeChromosomes.LIFESPAN.getDisplayName(lifespan)
				.append(" ")
				.append(Component.translatable("for.gui.life"))
				.withStyle(ChatFormatting.GRAY));
		tooltip.accept(BeeChromosomes.SPEED.getDisplayName(speed)
				.append(" ")
				.append(Component.translatable("for.gui.worker"))
				.withStyle(ChatFormatting.GRAY));

		IBeeSpecies active = genome.getActiveAllele(BeeChromosomes.SPECIES).value();
		IValueAllele<ToleranceType> tempTolerance = genome.getActiveAllele(BeeChromosomes.TEMPERATURE_TOLERANCE);
		IValueAllele<ToleranceType> humidTolerance = genome.getActiveAllele(BeeChromosomes.HUMIDITY_TOLERANCE);
		tooltip.accept(Component.literal("T: ")
				.append(ClimateHelper.toDisplay(active.getTemperature()))
				.append(" / ")
				.append(BeeChromosomes.TEMPERATURE_TOLERANCE.getDisplayName(tempTolerance))
				.withStyle(ChatFormatting.GREEN));
		tooltip.accept(Component.literal("H: ")
				.append(ClimateHelper.toDisplay(active.getHumidity()))
				.append(" / ")
				.append(BeeChromosomes.HUMIDITY_TOLERANCE.getDisplayName(humidTolerance))
				.withStyle(ChatFormatting.GREEN));

		tooltip.accept(BeeChromosomes.FLOWER_TYPE.getDisplayName(genome.getActiveAllele(BeeChromosomes.FLOWER_TYPE))
				.withStyle(ChatFormatting.GRAY));

		var activity = genome.getActiveAllele(BeeChromosomes.ACTIVITY);
		if (activity.value() != ActivityType.DIURNAL) {
			tooltip.accept(BeeChromosomes.ACTIVITY.getDisplayName(activity).withStyle(ChatFormatting.GOLD));
		}

		IBooleanAllele rain = genome.getActiveAllele(BeeChromosomes.TOLERATES_RAIN);
		if (rain.value()) {
			tooltip.accept(Component.translatable("chromosome.reforestry.tolerates_rain.tooltip").withStyle(ChatFormatting.WHITE));
		}
	}
}
