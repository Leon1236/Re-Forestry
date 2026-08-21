package com.leon1236.reforestry.gendustry.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.features.GendustryDataComponents;
import com.leon1236.reforestry.gendustry.item.data.GeneticTemplateInfo;

public class GeneticTemplateItem extends SpeciesTypeItem {
	public GeneticTemplateItem(Properties properties) {
		super(properties.stacksTo(1).rarity(Rarity.RARE));
	}

	public static ItemStack createStack(ISpeciesType<?, ?> speciesType, Map<IChromosome<?>, IAllele> alleles) {
		ItemStack stack = new ItemStack(GItems.GENETIC_TEMPLATE.item());
		stack.set(GendustryDataComponents.GENETIC_TEMPLATE.type(), new GeneticTemplateInfo(speciesType, alleles));
		return stack;
	}

	public static void addAlleles(ItemStack template, Map<IChromosome<?>, IAllele> samples) {
		GeneticTemplateInfo existing = getInfo(template);
		if (existing != null) {
			template.set(GendustryDataComponents.GENETIC_TEMPLATE.type(), existing.withAlleles(samples));
			return;
		}
		ISpeciesType<?, ?> type = SpeciesTypeItem.getSpeciesType(template);
		if (type != null) {
			template.set(GendustryDataComponents.GENETIC_TEMPLATE.type(), new GeneticTemplateInfo(type, samples));
		}
	}

	public static void setSpeciesType(ItemStack stack, ISpeciesType<?, ?> type) {
		GeneticTemplateInfo existing = getInfo(stack);
		Map<IChromosome<?>, IAllele> alleles = existing != null ? existing.alleles() : Map.of();
		stack.set(GendustryDataComponents.GENETIC_TEMPLATE.type(), new GeneticTemplateInfo(type, alleles));
	}

	@Nullable
	public static GeneticTemplateInfo getInfo(ItemStack stack) {
		return stack.get(GendustryDataComponents.GENETIC_TEMPLATE.type());
	}

	public static Map<IChromosome<?>, IAllele> getAlleles(ItemStack template) {
		GeneticTemplateInfo info = getInfo(template);
		return info != null ? info.alleles() : Map.of();
	}

	public static boolean isComplete(ItemStack stack) {
		GeneticTemplateInfo info = getInfo(stack);
		return info != null && info.isComplete();
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		GeneticTemplateInfo info = getInfo(stack);
		if (info == null) {
			return;
		}

		var chromosomes = info.type().getKaryotype().chromosomes();
		Map<IChromosome<?>, IAllele> alleles = info.alleles();
		List<Component> lines = new ArrayList<>(chromosomes.size() + 1);
		int foundAlleles = 0;

		for (IChromosome<?> chromosome : chromosomes) {
			IAllele allele = alleles.get(chromosome);
			Component chromosomeName = chromosome.getChromosomeDisplayName();
			if (allele == null) {
				lines.add(Component.translatable("item.reforestry.genetic_template.allele_entry",
						chromosomeName,
						Component.translatable("item.reforestry.genetic_template.missing_allele").withStyle(ChatFormatting.GRAY))
						.withStyle(ChatFormatting.GRAY));
			} else {
				IChromosome typed = chromosome;
				Component alleleName = typed.getDisplayName(allele)
						.withStyle(style -> style.withColor(GeneSampleItem.getColorCoding(allele.dominant())));
				lines.add(Component.translatable("item.reforestry.genetic_template.allele_entry", chromosomeName, alleleName)
						.withStyle(ChatFormatting.GRAY));
				++foundAlleles;
			}
		}

		lines.add(0, Component.translatable("item.reforestry.genetic_template.allele_count", foundAlleles, chromosomes.size())
				.withStyle(ChatFormatting.GRAY));
		lines.forEach(tooltip);
	}
}
