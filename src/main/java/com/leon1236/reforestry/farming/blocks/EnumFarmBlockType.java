package com.leon1236.reforestry.farming.blocks;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public enum EnumFarmBlockType implements IBlockSubtype {
	PLAIN,
	GEARBOX,
	HATCH,
	VALVE,
	CONTROL;

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ENGLISH);
	}
}
