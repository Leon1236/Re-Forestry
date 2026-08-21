package com.leon1236.reforestry.api.client.plugin;

import java.util.function.IntUnaryOperator;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.arboriculture.ILeafSprite;
import com.leon1236.reforestry.api.client.arboriculture.ILeafTint;

public interface IClientHelper {
	ILeafTint createNoneTint();

	ILeafTint createFixedTint(int color);

	ILeafTint createBiomeTint();

	ILeafTint createBiomeTint(IntUnaryOperator mapper);

	ILeafSprite createLeafSprite(Identifier id);
}
