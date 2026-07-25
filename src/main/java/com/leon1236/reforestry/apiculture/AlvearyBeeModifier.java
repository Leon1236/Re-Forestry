package com.leon1236.reforestry.apiculture;

import net.minecraft.core.Vec3i;

import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;

public class AlvearyBeeModifier implements IBeeModifier {
	@Override
	public Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
		return currentModifier.multiply(2);
	}
}
