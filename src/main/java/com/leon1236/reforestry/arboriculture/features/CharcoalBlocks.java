package com.leon1236.reforestry.arboriculture.features;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

import net.minecraft.world.item.BlockItem;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.blocks.BlockAsh;
import com.leon1236.reforestry.arboriculture.blocks.BlockCharcoal;
import com.leon1236.reforestry.arboriculture.blocks.DecorativeLogPileBlock;
import com.leon1236.reforestry.arboriculture.blocks.LogPileBlock;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CharcoalBlocks {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("arboriculture"));

    public static final FeatureBlock<BlockCharcoal> CHARCOAL = REGISTRY.block("charcoal", BlockCharcoal::new, BlockItem::new);
    public static final FeatureBlock<LogPileBlock> LOG_PILE = REGISTRY.block("log_pile", LogPileBlock::new, BlockItem::new);
    public static final FeatureBlock<DecorativeLogPileBlock> DECORATIVE_LOG_PILE =
            REGISTRY.block("decorative_log_pile", DecorativeLogPileBlock::new, BlockItem::new);
    public static final FeatureBlock<BlockAsh> ASH = REGISTRY.block("ash_block", BlockAsh::new, BlockItem::new);

    public static void init() {
        FlammableBlockRegistry flammable = FlammableBlockRegistry.getDefaultInstance();
        flammable.add(LOG_PILE.block(), 12, 25);
        flammable.add(DECORATIVE_LOG_PILE.block(), 12, 25);

        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(CHARCOAL.item(), 16000);
            builder.add(LOG_PILE.item(), 1200);
            builder.add(DECORATIVE_LOG_PILE.item(), 1200);
        });
    }
}
