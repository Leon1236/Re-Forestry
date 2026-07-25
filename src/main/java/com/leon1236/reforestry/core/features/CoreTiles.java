package com.leon1236.reforestry.core.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.energy.TileCreativeEnergy;
import com.leon1236.reforestry.core.energy.TileDebugPowered;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CoreTiles {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    public static final FeatureBlockEntityType<TileCreativeEnergy> CREATIVE_ENERGY = REGISTRY.blockEntityType(
            "debug_creative_energy", TileCreativeEnergy::new, CoreBlocks.CREATIVE_ENERGY.block());

    public static final FeatureBlockEntityType<TileDebugPowered> DEBUG_POWERED = REGISTRY.blockEntityType(
            "debug_powered", TileDebugPowered::new, CoreBlocks.DEBUG_POWERED.block());

    public static void init() {
        EnergyHelper.registerSided(DEBUG_POWERED.type());
    }
}
