package com.leon1236.reforestry.factory.features;

import net.minecraft.world.item.BlockItem;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.factory.blocks.BlockFactoryPlain;
import com.leon1236.reforestry.factory.blocks.BlockFactoryTesr;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryTesr;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FactoryBlocks {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("factory"));

    public static final FeatureBlockGroup<BlockFactoryPlain, BlockTypeFactoryPlain> PLAIN =
            REGISTRY.blockGroup(BlockFactoryPlain::new, BlockTypeFactoryPlain.values())
                    .item(BlockItem::new)
                    .create();

    public static final FeatureBlockGroup<BlockFactoryTesr, BlockTypeFactoryTesr> TESR =
            REGISTRY.blockGroup(BlockFactoryTesr::new, BlockTypeFactoryTesr.values())
                    .item(BlockItem::new)
                    .create();

    public static void init() {
    }
}
