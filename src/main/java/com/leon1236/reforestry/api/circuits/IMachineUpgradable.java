package com.leon1236.reforestry.api.circuits;

public interface IMachineUpgradable {
    void applyMachineUpgrade(double speedChange, double powerChange, double outputChange);

    void removeMachineUpgrade(double speedChange, double powerChange, double outputChange);
}
