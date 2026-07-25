package com.leon1236.reforestry.core.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumFruit implements IItemSubtype {
    CHERRY(1, 0.3f, 16),
    WALNUT(1, 0.6f, 16),
    CHESTNUT(1, 1f, 16),
    LEMON(4, 0.3f, 32),
    PLUM(4, 0.3f, 32),
    DATES(1, 0.6f, 10),
    PAPAYA(4, 1f, 32),
    PEAR(4, 0.3f, 32),
    ORANGE(4, 0.3f, 32),
    COCONUT(2, 1.25f, 72),
    OLIVE(1, 0.8f, 16),
    FEIJOA(2, 0.6f, 16);

    public final String serializedName;
    public final int nutrition;
    public final float saturationModifier;
    public final int useTicks;

    EnumFruit(int nutrition, float saturationModifier, int useTicks) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.nutrition = nutrition;
        this.saturationModifier = saturationModifier;
        this.useTicks = useTicks;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
