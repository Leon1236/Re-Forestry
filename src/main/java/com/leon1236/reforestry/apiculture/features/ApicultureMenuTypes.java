package com.leon1236.reforestry.apiculture.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.gui.ContainerAlveary;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearyHygroregulator;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearySieve;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearySwarmer;
import com.leon1236.reforestry.apiculture.gui.ContainerBeeHousing;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ApicultureMenuTypes {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("apiculture"));

    public static final FeatureMenuType<ContainerBeeHousing, BlockPos> BEE_HOUSING = REGISTRY.menuType("bee_housing",
            ContainerBeeHousing::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerAlveary, BlockPos> ALVEARY = REGISTRY.menuType("alveary",
            ContainerAlveary::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerAlvearyHygroregulator, BlockPos> ALVEARY_HYGROREGULATOR =
            REGISTRY.menuType("alveary_hygroregulator", ContainerAlvearyHygroregulator::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerAlvearySieve, BlockPos> ALVEARY_SIEVE =
            REGISTRY.menuType("alveary_sieve", ContainerAlvearySieve::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerAlvearySwarmer, BlockPos> ALVEARY_SWARMER =
            REGISTRY.menuType("alveary_swarmer", ContainerAlvearySwarmer::new, BlockPos.STREAM_CODEC);

    public static void init() {
    }
}
