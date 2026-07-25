package com.leon1236.reforestry.factory.blocks;

import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.tiles.TileBottler;
import com.leon1236.reforestry.factory.tiles.TileCarpenter;
import com.leon1236.reforestry.factory.tiles.TileCentrifuge;
import com.leon1236.reforestry.factory.tiles.TileFabricator;
import com.leon1236.reforestry.factory.tiles.TileFermenter;
import com.leon1236.reforestry.factory.tiles.TileMoistener;
import com.leon1236.reforestry.factory.tiles.TileSmelter;
import com.leon1236.reforestry.factory.tiles.TileSqueezer;
import com.leon1236.reforestry.factory.tiles.TileStill;

public enum BlockTypeFactoryPlain implements IBlockType {
    CENTRIFUGE("centrifuge", new MachineProperties.Builder<>(FactoryTiles.CENTRIFUGE, "centrifuge")
            .setServerTicker(TileCentrifuge::serverTick)
            .create()),
    SMELTER("smelter", new MachineProperties.Builder<>(FactoryTiles.SMELTER, "smelter")
            .setServerTicker(TileSmelter::serverTick)
            .create()),
    STILL("still", new MachineProperties.Builder<>(FactoryTiles.STILL, "still")
            .setServerTicker(TileStill::serverTick)
            .create()),
    SQUEEZER("squeezer", new MachineProperties.Builder<>(FactoryTiles.SQUEEZER, "squeezer")
            .setServerTicker(TileSqueezer::serverTick)
            .create()),
    BOTTLER("bottler", new MachineProperties.Builder<>(FactoryTiles.BOTTLER, "bottler")
            .setServerTicker(TileBottler::serverTick)
            .create()),
    CARPENTER("carpenter", new MachineProperties.Builder<>(FactoryTiles.CARPENTER, "carpenter")
            .setServerTicker(TileCarpenter::serverTick)
            .create()),
    FERMENTER("fermenter", new MachineProperties.Builder<>(FactoryTiles.FERMENTER, "fermenter")
            .setServerTicker(TileFermenter::serverTick)
            .create()),
    FABRICATOR("fabricator", new MachineProperties.Builder<>(FactoryTiles.FABRICATOR, "fabricator")
            .setServerTicker(TileFabricator::serverTick)
            .create()),
    MOISTENER("moistener", new MachineProperties.Builder<>(FactoryTiles.MOISTENER, "moistener")
            .setServerTicker(TileMoistener::serverTick)
            .create());

    private final String serializedName;
    private final IMachineProperties<?> machineProperties;

    BlockTypeFactoryPlain(String serializedName, IMachineProperties<?> machineProperties) {
        this.serializedName = serializedName;
        this.machineProperties = machineProperties;
    }

    @Override
    public IMachineProperties<?> getMachineProperties() {
        return machineProperties;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
