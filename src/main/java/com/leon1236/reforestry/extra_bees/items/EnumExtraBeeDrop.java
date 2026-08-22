package com.leon1236.reforestry.extra_bees.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumExtraBeeDrop implements IItemSubtype {
	ENERGY(0x9c4972, 0xe37171),
	ACID(0x4bb541, 0x49de3c),
	POISON(0xd106b9, 0xff03e2),
	APPLE(0xc75252, 0xc92a2a),
	ICE(0xaee8e2, 0x96fff5),
	MILK(0xe0e0e0, 0xffffff),
	SEED(0x7cc272, 0xc2bea7),
	ALCOHOL(0xdbe84d, 0xa5e84d),
	RED(0xcc4c4c, 0xff0000),
	YELLOW(0xe5e533, 0xffdd00),
	BLUE(0x99b2f2, 0x0022ff),
	GREEN(0x667f33, 0x009900),
	BLACK(0x191919, 0x575757),
	WHITE(0xd6d6d6, 0xffffff),
	BROWN(0x7f664c, 0x5c350f),
	ORANGE(0xf2b233, 0xff9d00),
	CYAN(0x4c99b2, 0x00ffe5),
	PURPLE(0xb266e5, 0xae00ff),
	GRAY(0x4c4c4c, 0xbababa),
	LIGHTBLUE(0x99b2f2, 0x009dff),
	PINK(0xf2b2cc, 0xff80df),
	LIMEGREEN(0x7fcc19, 0x00ff08),
	MAGENTA(0xe57fd8, 0xff00cc),
	LIGHTGRAY(0x999999, 0xc9c9c9);

	public static final EnumExtraBeeDrop[] VALUES = values();

	public final String serializedName;
	public final int primaryColor;
	public final int secondaryColor;

	EnumExtraBeeDrop(int primaryColor, int secondaryColor) {
		this.serializedName = name().toLowerCase(Locale.ENGLISH);
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
