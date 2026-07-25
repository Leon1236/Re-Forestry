package com.leon1236.reforestry.apiculture;

import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;

public final class ApiaryBeeModifier implements IBeeModifier {
    @Override
    public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
        return 0.1f * currentSpeed;
    }
}
