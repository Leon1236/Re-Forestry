package com.leon1236.reforestry.apiculture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Vec3i;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;

public class BeeHousingModifier implements IBeeModifier {
    private final IBeeHousing beeHousing;

    public BeeHousingModifier(IBeeHousing beeHousing) {
        this.beeHousing = beeHousing;
    }

    @Override
    public Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            currentModifier = modifier.modifyTerritory(genome, currentModifier);
        }
        return currentModifier;
    }

    @Override
    public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            currentChance = modifier.modifyMutationChance(genome, mate, mutation, currentChance);
        }
        return currentChance;
    }

    @Override
    public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            currentAging = modifier.modifyAging(genome, mate, currentAging);
        }
        return currentAging;
    }

    @Override
    public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            currentSpeed = modifier.modifyProductionSpeed(genome, currentSpeed);
        }
        return currentSpeed;
    }

    @Override
    public float modifyPollination(IGenome genome, float currentPollination) {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            currentPollination = modifier.modifyPollination(genome, currentPollination);
        }
        return currentPollination;
    }

    @Override
    public float modifyGeneticDecay(IGenome genome, float currentDecay) {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            currentDecay = modifier.modifyGeneticDecay(genome, currentDecay);
        }
        return currentDecay;
    }

    @Override
    public boolean isSealed() {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            if (modifier.isSealed()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAlwaysActive(IGenome genome) {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            if (modifier.isAlwaysActive(genome)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSunlightSimulated() {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            if (modifier.isSunlightSimulated()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isHellish() {
        for (IBeeModifier modifier : this.beeHousing.getBeeModifiers()) {
            if (modifier.isHellish()) {
                return true;
            }
        }
        return false;
    }
}
