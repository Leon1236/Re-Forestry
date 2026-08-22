package com.leon1236.reforestry.arboriculture.client;

import com.leon1236.reforestry.api.client.arboriculture.ILeafTint;

final class EscritoireLeafTint implements ILeafTint {
	private final int color;

	EscritoireLeafTint(int color) {
		this.color = color;
	}

	@Override
	public int get(int biomeColor) {
		return color;
	}
}
