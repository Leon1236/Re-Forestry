package com.leon1236.reforestry.arboriculture.worldgen.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.worldgen.TreeGenHelper;

public class ForestryTreeFeature extends Feature<ForestryTreeFeatureConfig> {
    public ForestryTreeFeature() {
        super(ForestryTreeFeatureConfig.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<ForestryTreeFeatureConfig> context) {
        IGenome genome = context.config().genome();
        ITreeSpecies species = genome.getActiveAllele(TreeChromosomes.SPECIES).value();
        return TreeGenHelper.generateTree(species, genome, context.level(), context.random(), context.origin());
    }
}
