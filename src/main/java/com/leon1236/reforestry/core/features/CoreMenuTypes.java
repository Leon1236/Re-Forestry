package com.leon1236.reforestry.core.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.gui.ContainerAlyzer;
import com.leon1236.reforestry.core.gui.ContainerSolderingIron;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

import net.minecraft.network.codec.ByteBufCodecs;

public class CoreMenuTypes {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    public static final FeatureMenuType<ContainerSolderingIron, Boolean> SOLDERING_IRON =
            REGISTRY.menuType("soldering_iron", (id, inv, handIsMain) ->
                    new ContainerSolderingIron(id, inv.player, new com.leon1236.reforestry.core.circuits.ItemInventorySolderingIron(inv.player)),
                    ByteBufCodecs.BOOL);

    public static final FeatureMenuType<ContainerAlyzer, Boolean> ALYZER =
            REGISTRY.menuType("alyzer", ContainerAlyzer::fromNetwork, ByteBufCodecs.BOOL);

    public static void init() {
    }
}
