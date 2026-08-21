package com.leon1236.reforestry.gendustry.item;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.gendustry.features.GendustryDataComponents;
import com.leon1236.reforestry.gendustry.item.data.GeneSampleInfo;
import com.leon1236.reforestry.gendustry.item.data.GeneticTemplateInfo;

public abstract class SpeciesTypeItem extends Item {
	public SpeciesTypeItem(Properties properties) {
		super(properties);
	}

	@Override
	public Component getName(ItemStack stack) {
		ISpeciesType<?, ?> speciesType = getSpeciesType(stack);
		return Component.translatable(getDescriptionId(), speciesType != null ? speciesType.getDisplayName() : "?");
	}

	@Nullable
	public static ISpeciesType<?, ?> getSpeciesType(ItemStack stack) {
		GeneSampleInfo sample = stack.get(GendustryDataComponents.GENE_SAMPLE.type());
		if (sample != null) {
			return sample.type();
		}
		GeneticTemplateInfo template = stack.get(GendustryDataComponents.GENETIC_TEMPLATE.type());
		return template != null ? template.type() : null;
	}
}
