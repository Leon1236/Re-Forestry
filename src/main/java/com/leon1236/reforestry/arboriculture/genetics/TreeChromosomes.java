package com.leon1236.reforestry.arboriculture.genetics;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class TreeChromosomes {
    public static final IRegistryChromosome<ITreeSpecies> SPECIES = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("tree_species"));
    public static final IChromosome<IFloatAllele> HEIGHT = AlleleManager.INSTANCE.floatChromosome(ReForestry.id("height"));
    public static final IChromosome<IFloatAllele> SAPLINGS = AlleleManager.INSTANCE.floatChromosome(ReForestry.id("saplings"));
    public static final IRegistryChromosome<IFruit> FRUIT = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("fruits"));
    public static final IChromosome<IFloatAllele> YIELD = AlleleManager.INSTANCE.floatChromosome(ReForestry.id("yield"));
    public static final IChromosome<IFloatAllele> SAPPINESS = AlleleManager.INSTANCE.floatChromosome(ReForestry.id("sappiness"));
    public static final IRegistryChromosome<ITreeEffect> EFFECT = AlleleManager.INSTANCE.registryChromosome(ReForestry.id("tree_effect"));
    public static final IChromosome<IIntegerAllele> MATURATION = AlleleManager.INSTANCE.integerChromosome(ReForestry.id("maturation"));
    public static final IChromosome<IIntegerAllele> GIRTH = AlleleManager.INSTANCE.integerChromosome(ReForestry.id("girth"));
    public static final IChromosome<IBooleanAllele> FIREPROOF = AlleleManager.INSTANCE.booleanChromosome(ReForestry.id("fireproof"));

    public static final IKaryotype KARYOTYPE;

    static {
        populateFruit();
        populateEffect();

        KARYOTYPE = AlleleManager.INSTANCE.karyotypeBuilder()
                .setSpecies(SPECIES)
                .set(HEIGHT, ForestryAlleles.HEIGHT_SMALL)
                .set(SAPLINGS, ForestryAlleles.SAPLINGS_LOWER)
                .set(FRUIT, AlleleManager.INSTANCE.registryAllele(DefaultFruits.NONE, true))
                .set(YIELD, ForestryAlleles.YIELD_LOWEST)
                .set(SAPPINESS, ForestryAlleles.SAPPINESS_LOWEST)
                .set(EFFECT, AlleleManager.INSTANCE.registryAllele(TreeEffect.NONE, true))
                .set(MATURATION, ForestryAlleles.MATURATION_AVERAGE)
                .set(GIRTH, ForestryAlleles.GIRTH_1)
                .set(FIREPROOF, AlleleManager.INSTANCE.booleanAllele(false, true))
                .create(ReForestry.id("trees"));
    }

    private TreeChromosomes() {
    }

    private static void populateFruit() {
        ImmutableMap.Builder<Identifier, IFruit> values = ImmutableMap.builder();
        for (IFruit fruit : DefaultFruits.ALL) {
            values.put(fruit.id(), fruit);
        }
        FRUIT.populate(values.build());
    }

    private static void populateEffect() {
        ImmutableMap.Builder<Identifier, ITreeEffect> values = ImmutableMap.builder();
        for (TreeEffect effect : TreeEffect.values()) {
            values.put(effect.id(), effect);
        }
        EFFECT.populate(values.build());
    }
}
