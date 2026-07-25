package com.leon1236.reforestry.api.circuits;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public interface ICircuitLayout {
    String getId();

    Component getName();

    Component getUsage();

    Identifier getSocketType();
}
