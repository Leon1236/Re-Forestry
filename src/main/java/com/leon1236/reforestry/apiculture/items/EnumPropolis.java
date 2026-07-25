package com.leon1236.reforestry.apiculture.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumPropolis implements IItemSubtype {
    NORMAL(0xc5b24e),
    PULSATING(0x2ccdb1),
    SILKY(0xddff00),
    VOLCANIC(0xe84528);

    public final String serializedName;
    public final int primaryColor;

    EnumPropolis(int primaryColor) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.primaryColor = primaryColor;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
