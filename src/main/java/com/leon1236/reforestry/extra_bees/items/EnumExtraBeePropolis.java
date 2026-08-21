package com.leon1236.reforestry.extra_bees.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumExtraBeePropolis implements IItemSubtype {
	WATER(0x24b3c9),
	OIL(0x172f33),
	FUEL(0xa38d12),
	CREOSOTE(0x877501);

	public static final EnumExtraBeePropolis[] VALUES = values();

	public final String serializedName;
	public final int primaryColor;

	EnumExtraBeePropolis(int primaryColor) {
		this.serializedName = name().toLowerCase(Locale.ENGLISH);
		this.primaryColor = primaryColor;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
