package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;

import net.minecraft.world.entity.Mob;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.effects.ThrottledBeeEffect;

public enum HermitBeeJubilance implements IBeeJubilance {
	INSTANCE;

	@Override
	public boolean isJubilant(IBeeSpecies species, IGenome genome, IBeeHousing housing) {
		List<Mob> list = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Mob.class);
		return list.isEmpty();
	}
}
