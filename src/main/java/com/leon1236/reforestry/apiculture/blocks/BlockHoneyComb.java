package com.leon1236.reforestry.apiculture.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;

public class BlockHoneyComb extends Block {
    private final EnumHoneyComb type;

    public BlockHoneyComb(EnumHoneyComb type, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = type;
    }

    public EnumHoneyComb getType() {
        return type;
    }
}
