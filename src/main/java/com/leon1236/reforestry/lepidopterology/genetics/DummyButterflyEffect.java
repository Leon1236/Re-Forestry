package com.leon1236.reforestry.lepidopterology.genetics;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.lepidopterology.IButterflyEffect;
import com.leon1236.reforestry.api.lepidopterology.IEntityButterfly;

public final class DummyButterflyEffect implements IButterflyEffect {
	private final Identifier id;
	private final boolean dominant;

	public DummyButterflyEffect(Identifier id, boolean dominant) {
		this.id = id;
		this.dominant = dominant;
	}

	@Override
	public Identifier id() {
		return id;
	}

	@Override
	public boolean isDominant() {
		return dominant;
	}

	@Override
	public IEffectData doEffect(IEntityButterfly butterfly, IEffectData storedData) {
		return storedData;
	}
}
