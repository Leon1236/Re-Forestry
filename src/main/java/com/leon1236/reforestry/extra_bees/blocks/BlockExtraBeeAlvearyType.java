package com.leon1236.reforestry.extra_bees.blocks;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public enum BlockExtraBeeAlvearyType implements IBlockSubtype {
	MUTATOR,
	FRAME,
	RAIN_SHIELD,
	LIGHTING,
	STIMULATOR,
	HATCHERY,
	TRANSMISSION;

	public static final BlockExtraBeeAlvearyType[] VALUES = values();

	private final String serializedName;

	BlockExtraBeeAlvearyType() {
		this.serializedName = name().toLowerCase(Locale.ENGLISH);
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}

	@Override
	public String toString() {
		return serializedName;
	}
}
