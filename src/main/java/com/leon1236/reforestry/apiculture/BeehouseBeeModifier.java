package com.leon1236.reforestry.apiculture;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;

public final class BeehouseBeeModifier implements IBeeModifier {
    @Override
    public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
        return 0.25f * currentSpeed;
    }

    @Override
    public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
        return 0.0f;
    }

    @Override
    public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
        return currentAging / 3f;
    }

    @Override
    public float modifyPollination(IGenome genome, float currentPollination) {
        return 3.0f * currentPollination;
    }

    @Override
    public float modifyGeneticDecay(IGenome genome, float currentDecay) {
        return 0.0f;
    }
}
