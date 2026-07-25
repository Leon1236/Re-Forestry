package com.leon1236.reforestry.api.apiculture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Vec3i;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;

public interface IBeeModifier {
    default Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
        return currentModifier;
    }

    default float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
        return currentChance;
    }

    default float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
        return currentAging;
    }

    default float modifyProductionSpeed(IGenome genome, float currentSpeed) {
        return currentSpeed;
    }

    default float modifyPollination(IGenome genome, float currentPollination) {
        return currentPollination;
    }

    default float modifyGeneticDecay(IGenome genome, float currentDecay) {
        return currentDecay;
    }

    default boolean isSealed() {
        return false;
    }

    default boolean isAlwaysActive(IGenome genome) {
        return false;
    }

    default boolean isSunlightSimulated() {
        return false;
    }

    default boolean isHellish() {
        return false;
    }
}
