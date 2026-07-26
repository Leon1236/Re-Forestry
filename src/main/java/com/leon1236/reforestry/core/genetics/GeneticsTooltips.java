package com.leon1236.reforestry.core.genetics;

import java.util.function.Consumer;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;

public final class GeneticsTooltips {
	private GeneticsTooltips() {
	}

	public static void appendGeneticsTooltip(ItemStack stack, Consumer<Component> tooltip, Consumer<IGenome> analyzedDetails) {
		if (!IndividualItems.isIndividual(stack)) {
			return;
		}
		if (!IndividualItems.isAnalyzed(stack)) {
			tooltip.accept(Component.translatable("for.gui.unknown").withStyle(ChatFormatting.GRAY));
			return;
		}
		if (isShiftDown()) {
			IndividualItems.ifPresent(stack, analyzedDetails);
		} else {
			tooltip.accept(Component.translatable("for.gui.tooltip.tmi").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
		}
	}

	public static void addHybridTooltip(
			Consumer<Component> tooltip,
			IGenome genome,
			IRegistryChromosome<?> species,
			String hybridKey) {
		IAllele activeAllele = genome.getActiveAllele(species);
		IAllele inactiveAllele = genome.getInactiveAllele(species);
		if (activeAllele.alleleId().equals(inactiveAllele.alleleId())) {
			return;
		}
		@SuppressWarnings("unchecked")
		IChromosome<IAllele> typed = (IChromosome<IAllele>) (IChromosome<?>) species;
		tooltip.accept(Component.translatable(
				hybridKey,
				typed.getDisplayName(activeAllele),
				typed.getDisplayName(inactiveAllele)).withStyle(ChatFormatting.BLUE));
	}

	private static boolean isShiftDown() {
		if (FabricLoader.getInstance().getEnvironmentType() != EnvType.CLIENT) {
			return false;
		}
		return Minecraft.getInstance().hasShiftDown();
	}
}
