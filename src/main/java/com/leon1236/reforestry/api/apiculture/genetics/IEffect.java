package com.leon1236.reforestry.api.apiculture.genetics;

import com.leon1236.reforestry.api.genetics.IEffectData;

public interface IEffect {
    boolean isCombinable();

    IEffectData validateStorage(IEffectData storedData);
}
