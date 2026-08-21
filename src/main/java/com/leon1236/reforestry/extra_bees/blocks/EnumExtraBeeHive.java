package com.leon1236.reforestry.extra_bees.blocks;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.IBlockSubtype;

public enum EnumExtraBeeHive implements IBlockSubtype {
	WATER("water", "bee_water"),
	ROCK("rock", "bee_rock"),
	NETHER("eb_nether", "bee_basalt"),
	MARBLE("marble", "bee_marble");

	private final String serializedName;
	private final Identifier speciesId;

	EnumExtraBeeHive(String serializedName, String speciesPath) {
		this.serializedName = serializedName;
		this.speciesId = ReForestry.id(speciesPath);
	}

	public Identifier getSpeciesId() {
		return speciesId;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
