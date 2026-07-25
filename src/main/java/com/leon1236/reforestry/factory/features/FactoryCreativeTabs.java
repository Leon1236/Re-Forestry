package com.leon1236.reforestry.factory.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.features.FluidsItems;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.items.definitions.EnumContainerType;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryTesr;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FactoryCreativeTabs {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("factory"));

    public static final FeatureCreativeTab FACTORY = REGISTRY.creativeTab("factory", tab -> {
        tab.icon(() -> FactoryBlocks.PLAIN.stack(BlockTypeFactoryPlain.CENTRIFUGE));
        tab.displayItems((parameters, output) -> {
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.CENTRIFUGE).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.SMELTER).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.STILL).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.SQUEEZER).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.BOTTLER).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.CARPENTER).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.FERMENTER).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.FABRICATOR).item());
            output.accept(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.MOISTENER).item());
            output.accept(FactoryBlocks.TESR.get(BlockTypeFactoryTesr.RAINMAKER).item());
            output.accept(CoreItems.STURDY_CASING.item());
            output.accept(CoreItems.HARDENED_CASING.item());
            output.accept(CoreItems.COMPOST.item());
            output.accept(CoreItems.MULCH.item());
            output.accept(CoreItems.FERTILIZER_COMPOUND.item());
            output.accept(CoreItems.IODINE_CHARGE.item());
            output.accept(CoreItems.DISSIPATION_CHARGE.item());
            for (EnumContainerType type : EnumContainerType.values()) {
                output.accept(FluidsItems.CONTAINERS.item(type));
            }
            for (ForestryFluids fluid : ForestryFluids.values()) {
                output.accept(fluid.getBucket());
            }
        });
    });

    public static void init() {
    }
}
