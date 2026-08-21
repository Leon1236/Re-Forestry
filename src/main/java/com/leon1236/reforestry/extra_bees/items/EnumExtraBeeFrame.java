package com.leon1236.reforestry.extra_bees.items;

import java.util.Locale;

import com.leon1236.reforestry.api.core.IItemSubtype;

public enum EnumExtraBeeFrame implements IItemSubtype {
	COCOA(240, 0.75f, 0.25f, 1.5f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f),
	CAGE(240, 0.75f, 0.5f, 0.75f, 0.5f, 0.0f, 0.0f, 0.5f, 0.1f),
	SOUL(80, 0.75f, 0.5f, 0.25f, 0.1f, 1.5f, 5.0f, 0.0f, 0.0f),
	CLAY(240, 1.5f, 5.0f, 0.75f, 0.2f, 0.5f, 0.2f, 0.0f, 0.0f),
	DEBUG(240, 0.0001f, 0.0001f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

	public static final EnumExtraBeeFrame[] VALUES = values();

	public final String serializedName;
	public final int maxDamage;
	public final float lifespan;
	public final float lifespanMax;
	public final float production;
	public final float productionMax;
	public final float mutation;
	public final float mutationMax;
	public final float territory;
	public final float territoryMax;

	EnumExtraBeeFrame(int maxDamage, float lifespan, float lifespanMax, float production, float productionMax,
			float mutation, float mutationMax, float territory, float territoryMax) {
		this.serializedName = name().toLowerCase(Locale.ENGLISH);
		this.maxDamage = maxDamage;
		this.lifespan = lifespan;
		this.lifespanMax = lifespanMax;
		this.production = production;
		this.productionMax = productionMax;
		this.mutation = mutation;
		this.mutationMax = mutationMax;
		this.territory = territory;
		this.territoryMax = territoryMax;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
