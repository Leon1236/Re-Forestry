package com.leon1236.reforestry.factory.blocks;

import com.leon1236.reforestry.core.blocks.IBlockType;
import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.blocks.MachineProperties;
import com.leon1236.reforestry.core.tiles.TileMill;
import com.leon1236.reforestry.factory.features.FactoryTiles;

public enum BlockTypeFactoryTesr implements IBlockType {
    RAINMAKER(new MachineProperties.Builder<>(FactoryTiles.RAINMAKER, "rainmaker")
            .setClientTicker(TileMill::clientTick)
            .setServerTicker(TileMill::serverTick)
            .create());

    private final IMachineProperties<?> machineProperties;

    BlockTypeFactoryTesr(IMachineProperties<?> machineProperties) {
        this.machineProperties = machineProperties;
    }

    @Override
    public IMachineProperties<?> getMachineProperties() {
        return machineProperties;
    }

    @Override
    public String getSerializedName() {
        return machineProperties.getSerializedName();
    }
}
