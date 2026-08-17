package com.leon1236.reforestry.core.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.energy.TileCreativeEnergy;
import com.leon1236.reforestry.core.energy.TileDebugPowered;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.core.tiles.TileAnalyzer;
import com.leon1236.reforestry.core.tiles.TileApiaristChest;
import com.leon1236.reforestry.core.tiles.TileArboristChest;
import com.leon1236.reforestry.core.tiles.TileLepidopteristChest;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CoreTiles {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    public static final FeatureBlockEntityType<TileCreativeEnergy> CREATIVE_ENERGY = REGISTRY.blockEntityType(
            "debug_creative_energy", TileCreativeEnergy::new, CoreBlocks.CREATIVE_ENERGY.block());

    public static final FeatureBlockEntityType<TileDebugPowered> DEBUG_POWERED = REGISTRY.blockEntityType(
            "debug_powered", TileDebugPowered::new, CoreBlocks.DEBUG_POWERED.block());

    public static final FeatureBlockEntityType<TileApiaristChest> BEE_CHEST =
            REGISTRY.blockEntityType("bee_chest", TileApiaristChest::new);

    public static final FeatureBlockEntityType<TileArboristChest> TREE_CHEST =
            REGISTRY.blockEntityType("tree_chest", TileArboristChest::new);

    public static final FeatureBlockEntityType<TileLepidopteristChest> BUTTERFLY_CHEST =
            REGISTRY.blockEntityType("butterfly_chest", TileLepidopteristChest::new);

    public static final FeatureBlockEntityType<TileAnalyzer> ANALYZER =
            REGISTRY.blockEntityType("analyzer", TileAnalyzer::new);

    public static void init() {
        EnergyHelper.registerSided(DEBUG_POWERED.type());
        InventoryHelper.registerSided(BEE_CHEST.type());
        InventoryHelper.registerSided(TREE_CHEST.type());
        InventoryHelper.registerSided(BUTTERFLY_CHEST.type());
        EnergyHelper.registerSided(ANALYZER.type());
        InventoryHelper.registerSided(ANALYZER.type());
        FluidHelper.registerSided(ANALYZER.type(), TileAnalyzer::getTankManager);
    }
}
