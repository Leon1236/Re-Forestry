package com.leon1236.reforestry.core.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockResourceStorage extends Block {
    private final EnumResourceType type;

    public BlockResourceStorage(EnumResourceType type, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = type;
    }

    public EnumResourceType getType() {
        return type;
    }
}
