package com.leon1236.reforestry.arboriculture;

import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;

public final class WoodHelper {
    private WoodHelper() {
    }

    public static Component getDisplayName(IWoodTyped wood, IWoodType woodType) {
        return getDisplayName(wood.getBlockKind(), wood.isFireproof(), woodType);
    }

    public static Component getDisplayName(WoodBlockKind kind, boolean fireproof, IWoodType woodType) {
        Component displayName = Component.translatable("for." + kind.getSerializedName() + ".grammar",
                Component.translatable("for.trees.woodType." + woodType.getSerializedName()));
        if (fireproof) {
            displayName = Component.translatable("block.reforestry.fireproof", displayName);
        }
        return displayName;
    }
}
