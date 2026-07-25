package com.leon1236.reforestry.api.circuits;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ICircuitBoard {
    void addTooltip(List<Component> list);

    void onInsertion(Object tile);

    void onLoad(Object tile);

    void onRemoval(Object tile);

    void onTick(Object tile);

    ICircuit[] getCircuits();

    @Nullable
    Identifier getSocketType();
}
