package com.leon1236.reforestry.gendustry.recipe;

import java.util.IdentityHashMap;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.item.GeneSampleItem;
import com.leon1236.reforestry.gendustry.item.GendustryResourceType;
import com.leon1236.reforestry.gendustry.item.GeneticTemplateItem;
import com.leon1236.reforestry.gendustry.item.SpeciesTypeItem;

public class GeneticTemplateRecipe extends CustomRecipe {
	public static final GeneticTemplateRecipe INSTANCE = new GeneticTemplateRecipe();
	public static final MapCodec<GeneticTemplateRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance ->
			instance.group(CraftingBookCategory.CODEC.optionalFieldOf("category", CraftingBookCategory.MISC)
							.forGetter(recipe -> CraftingBookCategory.MISC))
					.apply(instance, category -> INSTANCE));
	public static final StreamCodec<RegistryFriendlyByteBuf, GeneticTemplateRecipe> STREAM_CODEC =
			StreamCodec.unit(INSTANCE);
	public static final RecipeSerializer<GeneticTemplateRecipe> SERIALIZER =
			new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

	private GeneticTemplateRecipe() {
	}

	@Override
	public boolean matches(CraftingInput container, Level level) {
		boolean hasTemplate = false;
		ISpeciesType<?, ?> speciesType = null;
		int samples = 0;

		for (int i = 0; i < container.size(); ++i) {
			ItemStack stack = container.getItem(i);
			if (stack.isEmpty()) {
				continue;
			}
			if (stack.is(GItems.GENETIC_TEMPLATE.item())
					|| stack.is(GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE))) {
				if (hasTemplate) {
					return false;
				}
				ISpeciesType<?, ?> templateType = SpeciesTypeItem.getSpeciesType(stack);
				if (speciesType != null) {
					if (speciesType != templateType && templateType != null) {
						return false;
					}
				} else {
					speciesType = templateType;
				}
				hasTemplate = true;
				continue;
			}
			if (!stack.is(GItems.GENE_SAMPLE.item())) {
				return false;
			}
			ISpeciesType<?, ?> sampleType = SpeciesTypeItem.getSpeciesType(stack);
			if (sampleType == null) {
				return false;
			}
			if (hasTemplate && speciesType != sampleType) {
				if (speciesType == null) {
					speciesType = sampleType;
				} else {
					return false;
				}
			} else {
				speciesType = sampleType;
			}
			++samples;
		}

		return hasTemplate && samples != 0;
	}

	@Override
	public ItemStack assemble(CraftingInput container) {
		IdentityHashMap<IChromosome<?>, IAllele> samples = new IdentityHashMap<>();
		ItemStack template = ItemStack.EMPTY;
		ISpeciesType<?, ?> type = null;

		for (int i = 0; i < container.size(); ++i) {
			ItemStack stack = container.getItem(i);
			if (stack.isEmpty()) {
				continue;
			}
			if (stack.is(GItems.GENE_SAMPLE.item())) {
				IChromosome<?> chromosome = GeneSampleItem.getChromosome(stack);
				IAllele allele = GeneSampleItem.getAllele(stack);
				if (chromosome != null && allele != null) {
					samples.put(chromosome, allele);
				}
				if (type == null) {
					type = SpeciesTypeItem.getSpeciesType(stack);
				}
			} else if (stack.is(GItems.GENETIC_TEMPLATE.item())
					|| stack.is(GItems.RESOURCE.item(GendustryResourceType.BLANK_GENETIC_TEMPLATE))) {
				if (!template.isEmpty()) {
					return ItemStack.EMPTY;
				}
				template = stack;
			}
		}

		if (!template.isEmpty() && !samples.isEmpty() && type != null) {
			ItemStack result = template.is(GItems.GENETIC_TEMPLATE.item())
					? template.copyWithCount(1)
					: new ItemStack(GItems.GENETIC_TEMPLATE.item());
			GeneticTemplateItem.setSpeciesType(result, type);
			GeneticTemplateItem.addAlleles(result, samples);
			return result;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public RecipeSerializer<GeneticTemplateRecipe> getSerializer() {
		return SERIALIZER;
	}
}
