package com.leon1236.reforestry.core.plugin;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.agriculture.IFarmableFactory;
import com.leon1236.reforestry.api.plugin.IWindfallFarmableBuilder;

public final class WindfallFarmableBuilderImpl implements IWindfallFarmableBuilder {
	private final ImmutableSet.Builder<Item> windfalls = ImmutableSet.builder();
	private final IFarmableFactory factory;

	public WindfallFarmableBuilderImpl(IFarmableFactory factory) {
		this.factory = factory;
	}

	@Override
	public IWindfallFarmableBuilder addWindfall(Item windfall) {
		this.windfalls.add(windfall);
		return this;
	}

	@Override
	public IWindfallFarmableBuilder addWindfall(Iterable<? extends Item> windfall) {
		this.windfalls.addAll(windfall);
		return this;
	}

	public IFarmable build(Item germling) {
		return this.factory.create(germling, this.windfalls.build());
	}
}
