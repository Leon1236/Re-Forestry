package com.leon1236.reforestry.core.circuits;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumCircuitBoardType implements IItemSubtype {
	BASIC(1, 0x191919, 0x6dcff6),
	ENHANCED(2, 0x191919, 0xcb7c32),
	REFINED(3, 0x191919, 0xc9c9c9),
	INTRICATE(4, 0x191919, 0xe2cb6b);

	private final int sockets;
	private final String serializedName;
	private final int primaryColor;
	private final int secondaryColor;

	EnumCircuitBoardType(int sockets, int primaryColor, int secondaryColor) {
		this.sockets = sockets;
		this.serializedName = name().toLowerCase(Locale.ENGLISH);
		this.primaryColor = primaryColor | 0xFF000000;
		this.secondaryColor = secondaryColor | 0xFF000000;
	}

	public int getSockets() {
		return this.sockets;
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
