package com.leon1236.reforestry.arboriculture.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;

public class DecorativeLogPileBlock extends RotatedPillarBlock {
    public DecorativeLogPileBlock(Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1.5f).noOcclusion());
    }
}
