package com.leon1236.reforestry.api.storage;

import java.util.Locale;

import net.minecraft.util.StringRepresentable;

public enum EnumBackpackType implements StringRepresentable {
	NORMAL,
	WOVEN,
	NATURALIST;

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ENGLISH);
	}
}
