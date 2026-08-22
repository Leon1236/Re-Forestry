package com.leon1236.reforestry.core.features;

import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.escritoire.ContainerEscritoire;
import com.leon1236.reforestry.core.gui.ContainerAlyzer;
import com.leon1236.reforestry.core.gui.ContainerAnalyzer;
import com.leon1236.reforestry.core.gui.ContainerNaturalistChest;
import com.leon1236.reforestry.core.gui.ContainerSolderingIron;
import com.leon1236.reforestry.core.gui.NaturalistChestMenuData;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CoreMenuTypes {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    public static final FeatureMenuType<ContainerSolderingIron, Boolean> SOLDERING_IRON =
            REGISTRY.menuType("soldering_iron", (id, inv, handIsMain) ->
                    new ContainerSolderingIron(id, inv.player, new com.leon1236.reforestry.core.circuits.ItemInventorySolderingIron(inv.player)),
                    ByteBufCodecs.BOOL);

    public static final FeatureMenuType<ContainerAlyzer, Boolean> ALYZER =
            REGISTRY.menuType("alyzer", ContainerAlyzer::fromNetwork, ByteBufCodecs.BOOL);

    public static final StreamCodec<io.netty.buffer.ByteBuf, NaturalistChestMenuData> NATURALIST_CHEST_MENU_DATA =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, NaturalistChestMenuData::pos,
                    ByteBufCodecs.VAR_INT, NaturalistChestMenuData::page,
                    NaturalistChestMenuData::new);

    public static final FeatureMenuType<ContainerNaturalistChest, NaturalistChestMenuData> NATURALIST_CHEST =
            REGISTRY.menuType("naturalist_chest", ContainerNaturalistChest::fromNetwork, NATURALIST_CHEST_MENU_DATA);

    public static final FeatureMenuType<ContainerAnalyzer, BlockPos> ANALYZER =
            REGISTRY.menuType("analyzer", ContainerAnalyzer::new, BlockPos.STREAM_CODEC);

    public static final FeatureMenuType<ContainerEscritoire, BlockPos> ESCRITOIRE =
            REGISTRY.menuType("escritoire", ContainerEscritoire::new, BlockPos.STREAM_CODEC);

    public static void init() {
    }
}
