package com.leon1236.reforestry.gendustry.item;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum GendustryUpgradeType implements IItemSubtype, IGendustryUpgradeType {
	AUTOMATION(1, 50),
	HEATER(5, 100),
	COOLER(5, 100),
	HUMIDIFIER(2, 50),
	DRYER(2, 50),
	POLLINATION(8, 100),
	SCRUBBER(1, 50),
	NETHER(1, 200),
	LIFESPAN(4, 300),
	LIGHTING(1, 50),
	PRODUCTIVITY(8, 300),
	WEATHERPROOF(1, 50),
	SIEVE(1, 100),
	SKY(1, 50),
	STABILIZER(1, 400),
	TERRITORY(4, 50),
	IMMUTABLE(1, 50);

	private final String name;
	private final int maxStackSize;
	private final int energyCost;

	GendustryUpgradeType(int maxStackSize, int energyCost) {
		this.name = name().toLowerCase(Locale.ENGLISH);
		this.maxStackSize = maxStackSize;
		this.energyCost = energyCost;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}

	@Override
	public int maxStackSize() {
		return this.maxStackSize;
	}

	@Override
	public int energyCost() {
		return this.energyCost;
	}
}
