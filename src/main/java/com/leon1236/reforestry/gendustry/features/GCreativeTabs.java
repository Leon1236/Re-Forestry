package com.leon1236.reforestry.gendustry.features;

import java.util.IdentityHashMap;
import java.util.Map;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.gendustry.block.GendustryMachineType;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.item.GeneSampleItem;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureCreativeTab GENDUSTRY = REGISTRY.creativeTab("gendustry", tab -> {
		tab.icon(() -> GBlocks.MACHINE.stack(GendustryMachineType.MUTAGEN_PRODUCER));
		tab.displayItems((parameters, output) -> {
			for (FeatureBlock<?> feature : GBlocks.MACHINE.getAll().values()) {
				output.accept(feature.item());
			}
			output.accept(GItems.POLLEN_KIT.item());
			output.accept(GFluids.MUTAGEN.getBucket());
			output.accept(GFluids.LIQUID_DNA.getBucket());
			output.accept(GFluids.PROTEIN.getBucket());
			for (FeatureItem<?> feature : GItems.RESOURCE.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : GItems.UPGRADE.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : GItems.ELITE_UPGRADE.getAll().values()) {
				output.accept(feature.item());
			}
		});
	});

	public static final FeatureCreativeTab GENE_SAMPLES = REGISTRY.creativeTab("gene_samples", tab -> {
		tab.icon(() -> new ItemStack(GItems.GENE_SAMPLE.item()));
		tab.displayItems((parameters, output) -> {
			for (ISpeciesType<?, ?> speciesType : IForestryApi.get().getGeneticManager().getSpeciesTypes()) {
				Map<IChromosome<?>, IdentityHashMap<IAllele, Boolean>> allelesByChromosome = new IdentityHashMap<>();
				for (IChromosome<?> chromosome : speciesType.getKaryotype().chromosomes()) {
					allelesByChromosome.put(chromosome, new IdentityHashMap<>());
				}
				for (ISpecies<?> species : speciesType.getAllSpecies()) {
					for (var entry : species.getDefaultGenome().chromosomes().entrySet()) {
						IdentityHashMap<IAllele, Boolean> alleles = allelesByChromosome.get(entry.getKey());
						if (alleles == null) {
							continue;
						}
						alleles.put(entry.getValue().active(), Boolean.TRUE);
						alleles.put(entry.getValue().inactive(), Boolean.TRUE);
					}
				}
				for (var entry : allelesByChromosome.entrySet()) {
					for (IAllele allele : entry.getValue().keySet()) {
						output.accept(GeneSampleItem.createStack(speciesType, entry.getKey(), allele));
					}
				}
			}
		});
	});

	public static void init() {
	}
}
