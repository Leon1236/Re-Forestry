package com.leon1236.reforestry.gendustry.item;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EliteGendustryUpgradeType implements IItemSubtype, IGendustryUpgradeType {
	MUTATION(4, 400),
	ACTIVITY_SIMULATOR(1, 200),
	PRODUCTIVITY(32, 400),
	TERRITORY(16, 100),
	YOUTH(4, 50),
	FERTILITY(4, 1000);

	private final String name = name().toLowerCase(Locale.ENGLISH);
	private final int maxStackSize;
	private final int energyCost;

	EliteGendustryUpgradeType(int maxStackSize, int energyCost) {
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
