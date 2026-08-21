package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;
import com.leon1236.reforestry.core.genetics.Taxon;

record ButterflySpecies(Identifier id, String genus, String species, boolean dominant, int serumColor, float rarity,
		float flightDistance, boolean nocturnal, boolean moth, @Nullable TagKey<Biome> spawnBiomes, boolean glint,
		boolean secret, int complexity, String authority, TemperatureType temperature, HumidityType humidity,
		List<IProduct> butterflyLoot, List<IProduct> caterpillarProducts) implements IButterflySpecies {
	@Override
	public TemperatureType getTemperature() {
		return temperature;
	}

	@Override
	public HumidityType getHumidity() {
		return humidity;
	}

	@Override
	public String getTranslationKey() {
		return "allele.reforestry.butterfly_species.butterfly_" + id.getPath();
	}

	@Override
	public IGenome getDefaultGenome() {
		return LepidopterologyGenetics.getDefaultGenome(id);
	}

	@Override
	public String getBinomial() {
		if (genus.isEmpty()) {
			return species;
		}
		return Character.toUpperCase(genus.charAt(0)) + genus.substring(1) + " " + species;
	}

	@Override
	public String getSpeciesName() {
		return species;
	}

	@Override
	public ITaxon getGenus() {
		ITaxon taxon = com.leon1236.reforestry.api.IForestryApi.INSTANCE.getGeneticManager().getTaxonSafe(genus);
		return taxon != null ? taxon : Taxon.nameOnly(genus);
	}

	@Override
	public IButterflySpeciesType getType() {
		return ButterflySpeciesType.INSTANCE;
	}

	@Override
	public boolean isSecret() {
		return secret;
	}

	@Override
	public int getComplexity() {
		return complexity;
	}

	@Override
	public IButterfly createIndividual(Map<IChromosome<?>, IAllele> alleles) {
		return createIndividual(getDefaultGenome().copyWith(alleles));
	}

	@Override
	public IButterfly createIndividualFromPairs(Map<IChromosome<?>, AllelePair<?>> allelePairs) {
		return createIndividual(getDefaultGenome().copyWithPairs(allelePairs));
	}

	@Override
	public IButterfly createIndividual(IGenome genome) {
		if (genome.karyotype() != ButterflyChromosomes.KARYOTYPE) {
			throw new IllegalArgumentException("Genome karyotype does not match butterfly species");
		}
		return new Butterfly(genome);
	}

	@Override
	public boolean hasGlint() {
		return glint;
	}

	@Override
	public boolean isDominant() {
		return dominant;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public int getEscritoireColor() {
		return serumColor;
	}

	@Override
	@Nullable
	public TagKey<Biome> getSpawnBiomes() {
		return spawnBiomes;
	}

	@Override
	public float getRarity() {
		return rarity;
	}

	@Override
	public float getFlightDistance() {
		return flightDistance;
	}

	@Override
	public boolean isNocturnal() {
		return nocturnal;
	}

	@Override
	public boolean isMoth() {
		return moth;
	}

	@Override
	public List<? extends IProduct> getButterflyLoot() {
		return butterflyLoot;
	}

	@Override
	public List<? extends IProduct> getCaterpillarProducts() {
		return caterpillarProducts;
	}

	@Override
	public int getSerumColor() {
		return serumColor;
	}

	@Override
	public void addTooltip(IButterfly individual, List<Component> tooltip) {
		GeneticsTooltips.addHybridTooltip(tooltip::add, individual.getGenome(), ButterflyChromosomes.SPECIES,
				"for.butterflies.hybrid");
		if (!individual.isAnalyzed()) {
			tooltip.add(Component.literal("<").append(Component.translatable("for.gui.unknown")).append(">")
					.withStyle(ChatFormatting.GRAY));
		}
	}
}
