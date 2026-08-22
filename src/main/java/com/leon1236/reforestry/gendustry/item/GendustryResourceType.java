package com.leon1236.reforestry.gendustry.item;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum GendustryResourceType implements IItemSubtype {
	LABWARE,
	UPGRADE_FRAME,
	ELITE_UPGRADE_FRAME,
	CLIMATE_CONTROL_MODULE,
	POWER_MODULE,
	GENETICS_PROCESSOR,
	ENVIRONMENTAL_PROCESSOR,
	BLANK_GENE_SAMPLE,
	BLANK_GENETIC_TEMPLATE,
	RECEPTACLE;

	private final String name = name().toLowerCase(Locale.ENGLISH);

	@Override
	public String getSerializedName() {
		return this.name;
	}
}
