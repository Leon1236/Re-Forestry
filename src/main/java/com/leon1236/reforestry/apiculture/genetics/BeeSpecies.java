package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;
import java.util.Map;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.apiculture.genetics.IBee;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;
import com.leon1236.reforestry.core.genetics.Taxon;

record BeeSpecies(Identifier id, String genus, String species, boolean dominant, int outlineColor, int bodyColor,
                   int stripesColor, boolean secret, boolean glint, String authority,
                   TemperatureType temperature, HumidityType humidity,
                   List<IBeeSpecies.Product> products, List<IBeeSpecies.Product> specialties, IBeeJubilance jubilance,
                   int complexity) implements IBeeSpecies {
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
        return "allele.reforestry.bee_species." + id.getPath();
    }

    @Override
    public IGenome getDefaultGenome() {
        return ApicultureGenetics.getDefaultGenome(id);
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
        ITaxon taxon = com.leon1236.reforestry.api.IForestryApi.get().getGeneticManager().getTaxonSafe(genus);
        return taxon != null ? taxon : Taxon.nameOnly(genus);
    }

    @Override
	public ISpeciesType<? extends IBeeSpecies, IBee> getType() {
        return BeeSpeciesType.INSTANCE;
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
    public IBee createIndividual(Map<IChromosome<?>, IAllele> alleles) {
        return createIndividual(getDefaultGenome().copyWith(alleles));
    }

    @Override
    public IBee createIndividualFromPairs(Map<IChromosome<?>, AllelePair<?>> allelePairs) {
        return createIndividual(getDefaultGenome().copyWithPairs(allelePairs));
    }

    @Override
    public IBee createIndividual(IGenome genome) {
        if (genome.karyotype() != BeeChromosomes.KARYOTYPE) {
            throw new IllegalArgumentException("Genome karyotype does not match bee species");
        }
        return new Bee(genome);
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
        return outlineColor;
    }

    @Override
    public void addTooltip(IBee individual, List<Component> tooltip) {
        GeneticsTooltips.addHybridTooltip(tooltip::add, individual.getGenome(), BeeChromosomes.SPECIES, "for.bees.hybrid");
        if (!individual.isAnalyzed()) {
            tooltip.add(Component.literal("<").append(Component.translatable("for.gui.unknown")).append(">")
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
