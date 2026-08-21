package com.leon1236.reforestry.core.client;

import java.util.function.IntUnaryOperator;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.arboriculture.ILeafSprite;
import com.leon1236.reforestry.api.client.arboriculture.ILeafTint;
import com.leon1236.reforestry.api.client.plugin.IClientHelper;

public final class ClientHelperImpl implements IClientHelper {
	public static final ClientHelperImpl INSTANCE = new ClientHelperImpl();

	private ClientHelperImpl() {
	}

	@Override
	public ILeafTint createNoneTint() {
		return biomeColor -> 0xFFFFFF;
	}

	@Override
	public ILeafTint createFixedTint(int color) {
		return biomeColor -> color;
	}

	@Override
	public ILeafTint createBiomeTint() {
		return biomeColor -> biomeColor;
	}

	@Override
	public ILeafTint createBiomeTint(IntUnaryOperator mapper) {
		return mapper::applyAsInt;
	}

	@Override
	public ILeafSprite createLeafSprite(Identifier id) {
		return () -> id;
	}
}
