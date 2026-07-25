package com.leon1236.reforestry.factory.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.factory.gui.ContainerBottler;
import com.leon1236.reforestry.factory.gui.ContainerCarpenter;
import com.leon1236.reforestry.factory.gui.ContainerCentrifuge;
import com.leon1236.reforestry.factory.gui.ContainerFabricator;
import com.leon1236.reforestry.factory.gui.ContainerMoistener;
import com.leon1236.reforestry.factory.gui.ContainerFermenter;
import com.leon1236.reforestry.factory.gui.ContainerSmelter;
import com.leon1236.reforestry.factory.gui.ContainerSqueezer;
import com.leon1236.reforestry.factory.gui.ContainerStill;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FactoryMenuTypes {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("factory"));

    public static final FeatureMenuType<ContainerCentrifuge, BlockPos> CENTRIFUGE =
            REGISTRY.menuType("centrifuge", ContainerCentrifuge::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerSmelter, BlockPos> SMELTER =
            REGISTRY.menuType("smelter", ContainerSmelter::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerStill, BlockPos> STILL =
            REGISTRY.menuType("still", ContainerStill::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerSqueezer, BlockPos> SQUEEZER =
            REGISTRY.menuType("squeezer", ContainerSqueezer::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerBottler, BlockPos> BOTTLER =
            REGISTRY.menuType("bottler", ContainerBottler::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerCarpenter, BlockPos> CARPENTER =
            REGISTRY.menuType("carpenter", ContainerCarpenter::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerFermenter, BlockPos> FERMENTER =
            REGISTRY.menuType("fermenter", ContainerFermenter::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerFabricator, BlockPos> FABRICATOR =
            REGISTRY.menuType("fabricator", ContainerFabricator::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerMoistener, BlockPos> MOISTENER =
            REGISTRY.menuType("moistener", ContainerMoistener::new, BlockPos.STREAM_CODEC);

    public static void init() {
    }
}
