package com.leon1236.reforestry.apiculture.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumPollenCluster implements IItemSubtype {
    NORMAL(0xa28a25, 0xa28a25),
    CRYSTALLINE(0xffffff, 0xc5feff);

    public final String serializedName;
    public final int primaryColor;
    public final int secondaryColor;

    EnumPollenCluster(int primaryColor, int secondaryColor) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
