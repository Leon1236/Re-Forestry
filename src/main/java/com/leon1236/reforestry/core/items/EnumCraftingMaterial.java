package com.leon1236.reforestry.core.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumCraftingMaterial implements IItemSubtype {
    PULSATING_DUST,
    PULSATING_MESH,
    WOOD_PULP,
    SILK_WISP,
    WOVEN_SILK,
    ICE_SHARD,
    PHOSPHOR,
    IMPREGNATED_STICK,
    SCENTED_PANELING;

    public final String serializedName;

    EnumCraftingMaterial() {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
