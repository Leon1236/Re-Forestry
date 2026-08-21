package com.leon1236.reforestry.api.lepidopterology;

import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IButterflyEffect extends IRegistryAlleleValue {
	boolean isDominant();

	IEffectData doEffect(IEntityButterfly butterfly, IEffectData storedData);
}
