package com.leon1236.reforestry.factory.circuits;

import com.leon1236.reforestry.api.circuits.IMachineUpgradable;
import com.leon1236.reforestry.core.circuits.Circuit;

public class CircuitMachineUpgrade extends Circuit {
    private final double speedBoost;
    private final float powerDraw;
    private final float outputMult;

    public CircuitMachineUpgrade(String id, double speedBoost, float powerDraw, float outputMult) {
        super(id);
        this.speedBoost = speedBoost;
        this.powerDraw = powerDraw;
        this.outputMult = outputMult;
    }

    @Override
    public boolean isCircuitable(Object tile) {
        return tile instanceof IMachineUpgradable;
    }

    @Override
    public void onInsertion(int slot, Object tile) {
        apply(tile);
    }

    @Override
    public void onLoad(int slot, Object tile) {
        apply(tile);
    }

    @Override
    public void onRemoval(int slot, Object tile) {
        if (tile instanceof IMachineUpgradable machine) {
            machine.removeMachineUpgrade(this.speedBoost, this.powerDraw, this.outputMult);
        }
    }

    private void apply(Object tile) {
        if (tile instanceof IMachineUpgradable machine) {
            machine.applyMachineUpgrade(this.speedBoost, this.powerDraw, this.outputMult);
        }
    }

    @Override
    public void onTick(int slot, Object tile) {
    }
}
