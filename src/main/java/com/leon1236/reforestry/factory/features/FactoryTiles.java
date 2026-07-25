package com.leon1236.reforestry.factory.features;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.factory.tiles.TileBottler;
import com.leon1236.reforestry.factory.tiles.TileCarpenter;
import com.leon1236.reforestry.factory.tiles.TileCentrifuge;
import com.leon1236.reforestry.factory.tiles.TileFabricator;
import com.leon1236.reforestry.factory.tiles.TileMillRainmaker;
import com.leon1236.reforestry.factory.tiles.TileMoistener;
import com.leon1236.reforestry.factory.tiles.TileFermenter;
import com.leon1236.reforestry.factory.tiles.TileSmelter;
import com.leon1236.reforestry.factory.tiles.TileSqueezer;
import com.leon1236.reforestry.factory.tiles.TileStill;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FactoryTiles {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("factory"));

    public static final FeatureBlockEntityType<TileCentrifuge> CENTRIFUGE =
            REGISTRY.blockEntityType("centrifuge", TileCentrifuge::new);

    public static final FeatureBlockEntityType<TileSmelter> SMELTER =
            REGISTRY.blockEntityType("smelter", TileSmelter::new);

    public static final FeatureBlockEntityType<TileStill> STILL =
            REGISTRY.blockEntityType("still", TileStill::new);

    public static final FeatureBlockEntityType<TileSqueezer> SQUEEZER =
            REGISTRY.blockEntityType("squeezer", TileSqueezer::new);

    public static final FeatureBlockEntityType<TileBottler> BOTTLER =
            REGISTRY.blockEntityType("bottler", TileBottler::new);

    public static final FeatureBlockEntityType<TileCarpenter> CARPENTER =
            REGISTRY.blockEntityType("carpenter", TileCarpenter::new);

    public static final FeatureBlockEntityType<TileFermenter> FERMENTER =
            REGISTRY.blockEntityType("fermenter", TileFermenter::new);

    public static final FeatureBlockEntityType<TileFabricator> FABRICATOR =
            REGISTRY.blockEntityType("fabricator", TileFabricator::new);

    public static final FeatureBlockEntityType<TileMoistener> MOISTENER =
            REGISTRY.blockEntityType("moistener", TileMoistener::new);

    public static final FeatureBlockEntityType<TileMillRainmaker> RAINMAKER =
            REGISTRY.blockEntityType("rainmaker", TileMillRainmaker::new);

    public static void init() {
        EnergyHelper.registerSided(CENTRIFUGE.type());
        InventoryHelper.registerSided(CENTRIFUGE.type());
        EnergyHelper.registerSided(SMELTER.type());
        InventoryHelper.registerSided(SMELTER.type());
        EnergyHelper.registerSided(STILL.type());
        InventoryHelper.registerSided(STILL.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTankManager(), STILL.type());
        EnergyHelper.registerSided(SQUEEZER.type());
        InventoryHelper.registerSided(SQUEEZER.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTankManager(), SQUEEZER.type());
        EnergyHelper.registerSided(BOTTLER.type());
        InventoryHelper.registerSided(BOTTLER.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTankManager(), BOTTLER.type());
        EnergyHelper.registerSided(CARPENTER.type());
        InventoryHelper.registerSided(CARPENTER.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTankManager(), CARPENTER.type());
        EnergyHelper.registerSided(FERMENTER.type());
        InventoryHelper.registerSided(FERMENTER.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTankManager(), FERMENTER.type());
        EnergyHelper.registerSided(FABRICATOR.type());
        InventoryHelper.registerSided(FABRICATOR.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTankManager(), FABRICATOR.type());
        InventoryHelper.registerSided(MOISTENER.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getResourceTank(), MOISTENER.type());
    }
}
