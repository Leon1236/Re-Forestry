package com.leon1236.reforestry.core.blocks;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public enum EnumResourceType implements IBlockSubtype {
    APATITE,
    TIN,
    BRONZE,
    AMBER;

    public final String serializedName;

    EnumResourceType() {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
