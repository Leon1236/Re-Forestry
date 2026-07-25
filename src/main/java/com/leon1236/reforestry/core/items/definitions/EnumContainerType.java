package com.leon1236.reforestry.core.items.definitions;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumContainerType implements IItemSubtype {
    CAN,
    CAPSULE,
    REFRACTORY;

    private final String name;

    EnumContainerType() {
        this.name = name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
