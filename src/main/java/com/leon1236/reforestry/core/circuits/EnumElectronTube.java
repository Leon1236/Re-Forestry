package com.leon1236.reforestry.core.circuits;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumElectronTube implements IItemSubtype {
	COPPER(0xffffff, 0xe3b78e),
	TIN(0xffffff, 0xe6f8ff),
	BRONZE(0xffffff, 0xddc276),
	IRON(0xffffff, 0xcccccc),
	GOLD(0xffffff, 0xffff8b),
	DIAMOND(0xffffff, 0x8cf5e3),
	OBSIDIAN(0xffffff, 0x866bc0),
	BLAZE(0xfff87e, 0xd96600),
	EMERALD(0xffffff, 0x00cc41),
	APATITE(0xffffff, 0x579cd9),
	LAPIS(0xffffff, 0x1c57c6),
	ENDER(0x255661, 0x33adad),
	AMBER(0xffffff, 0xe29536);

	private final String serializedName;
	private final int primaryColor;
	private final int secondaryColor;

	EnumElectronTube(int primaryColor, int secondaryColor) {
		this.serializedName = name().toLowerCase(Locale.ENGLISH);
		this.primaryColor = primaryColor | 0xFF000000;
		this.secondaryColor = secondaryColor | 0xFF000000;
	}

	public int getPrimaryColor() {
		return this.primaryColor;
	}

	public int getSecondaryColor() {
		return this.secondaryColor;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
