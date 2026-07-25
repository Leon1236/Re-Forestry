package com.leon1236.reforestry.apiculture.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IBlockSubtype;
import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumHoneyComb implements IItemSubtype, IBlockSubtype {
    HONEY(0xe8d56a, 0xffa12b),
    COCOA(0x674016, 0xffb62b),
    SIMMERING(0x981919, 0xfe8738),
    STRINGY(0xc8be67, 0xbda93e),
    FROZEN(0xf9ffff, 0xa0ffff),
    DRIPPING(0xdc7613, 0xffff00),
    SILKY(0x508907, 0xddff00),
    PARCHED(0xdcbe13, 0xffff00),
    MYSTERIOUS(0x161616, 0xe099ff),
    POWDERY(0x676767, 0xffffff),
    WHEATEN(0xfeff8f, 0xffffff),
    MOSSY(0x2a3313, 0x7e9939),
    MELLOW(0x886000, 0xfff960),
    KAOLIN(0x5e6c8d, 0xafb9d6),
    VINTAGE(0xdeb887, 0xcd853f),
    SPONGE(0x9d8f39, 0xe1e351),
    SCULKEN(0x111b21, 0x05625d);

    public static final EnumHoneyComb[] VALUES = values();

    public final String serializedName;
    public final int primaryColor;
    public final int secondaryColor;

    EnumHoneyComb(int primaryColor, int secondaryColor) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }
}
