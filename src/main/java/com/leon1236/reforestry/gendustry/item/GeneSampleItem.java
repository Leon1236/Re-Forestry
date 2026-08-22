package com.leon1236.reforestry.gendustry.item;

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
import com.leon1236.reforestry.gendustry.item.data.GeneSampleInfo;

public class GeneSampleItem extends SpeciesTypeItem {
	private static final int DOMINANT_RED = 0xFFEC3661;
	private static final int RECESSIVE_BLUE = 0xFF3687EC;

	public GeneSampleItem(Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	public static ItemStack createStack(ISpeciesType<?, ?> speciesType, IChromosome<?> chromosome, IAllele allele) {
		ItemStack stack = new ItemStack(GItems.GENE_SAMPLE.item());
		stack.set(GendustryDataComponents.GENE_SAMPLE.type(), new GeneSampleInfo(speciesType, chromosome, allele));
		return stack;
	}

	@Nullable
	public static GeneSampleInfo getInfo(ItemStack stack) {
		return stack.get(GendustryDataComponents.GENE_SAMPLE.type());
	}

	@Nullable
	public static IChromosome<?> getChromosome(ItemStack stack) {
		GeneSampleInfo info = getInfo(stack);
		return info != null ? info.chromosome() : null;
	}

	@Nullable
	public static IAllele getAllele(ItemStack stack) {
		GeneSampleInfo info = getInfo(stack);
		return info != null ? info.allele() : null;
	}

	public static int getColorCoding(boolean dominant) {
		return dominant ? DOMINANT_RED : RECESSIVE_BLUE;
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		GeneSampleInfo info = getInfo(stack);
		if (info == null) {
			return;
		}
		IChromosome chromosome = info.chromosome();
		IAllele allele = info.allele();
		tooltip.accept(chromosome.getChromosomeDisplayName()
				.append(" - ")
				.withStyle(ChatFormatting.GRAY)
				.append(chromosome.getDisplayName(allele)
						.withStyle(style -> style.withColor(getColorCoding(allele.dominant())))));
	}
}
