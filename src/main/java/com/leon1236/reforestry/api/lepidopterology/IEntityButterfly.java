package com.leon1236.reforestry.api.lepidopterology;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.entity.PathfinderMob;

import com.leon1236.reforestry.api.genetics.pollen.IPollen;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;

public interface IEntityButterfly {
	void changeExhaustion(int change);

	int getExhaustion();

	IButterfly getButterfly();

	PathfinderMob getEntity();

	@Nullable
	IPollen getPollen();

	void setPollen(@Nullable IPollen pollen);

	boolean canMateWith(IEntityButterfly butterfly);

	boolean canMate();
}
