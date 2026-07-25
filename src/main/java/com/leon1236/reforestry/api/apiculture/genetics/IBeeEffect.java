package com.leon1236.reforestry.api.apiculture.genetics;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IBeeEffect extends IEffect, IRegistryAlleleValue {
    boolean isDominant();

    @Override
    default IEffectData validateStorage(IEffectData storedData) {
        return storedData;
    }

    @Override
    default boolean isCombinable() {
        return false;
    }

    default IEffectData doEffect(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        return storedData;
    }

    default IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        return storedData;
    }
}
