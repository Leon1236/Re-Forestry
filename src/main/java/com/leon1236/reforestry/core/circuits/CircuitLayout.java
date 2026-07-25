package com.leon1236.reforestry.core.circuits;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.circuits.ICircuitLayout;

public class CircuitLayout implements ICircuitLayout {
    private final String uid;
    private final Identifier socketType;

    public CircuitLayout(String uid, Identifier socketType) {
        this.uid = uid;
        this.socketType = socketType;
    }

    @Override
    public String getId() {
        return this.uid;
    }

    @Override
    public Component getName() {
        return Component.translatable("circuit.layout." + this.uid);
    }

    @Override
    public Component getUsage() {
        return Component.translatable("circuit.layout." + this.uid + ".usage");
    }

    @Override
    public Identifier getSocketType() {
        return this.socketType;
    }
}
