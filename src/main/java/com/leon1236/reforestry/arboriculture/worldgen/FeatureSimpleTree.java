package com.leon1236.reforestry.arboriculture.worldgen;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;

public class FeatureSimpleTree extends Feature<NoneFeatureConfiguration> {
    private final ITreeGenData tree;

    public FeatureSimpleTree(ITreeGenData tree) {
        super(NoneFeatureConfiguration.CODEC);
        this.tree = tree;
    }

    public ITreeGenData getTree() {
        return this.tree;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return false;
    }
}
