package com.leon1236.reforestry.core.circuits;

import net.minecraft.nbt.CompoundTag;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.circuits.ICircuit;
import com.leon1236.reforestry.api.circuits.ICircuitBoard;
import com.leon1236.reforestry.api.circuits.ICircuitLayout;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CircuitBoard implements ICircuitBoard {
    private final EnumCircuitBoardType type;
    @Nullable
    private final ICircuitLayout layout;
    private final ICircuit[] circuits;

    public CircuitBoard(EnumCircuitBoardType type, @Nullable ICircuitLayout layout, ICircuit[] circuits) {
        this.type = type;
        this.layout = layout;
        this.circuits = circuits;
    }

    public CircuitBoard(CompoundTag compound) {
        this.type = EnumCircuitBoardType.values()[compound.getShortOr("T", (short) 0)];

        ICircuitLayout layout = null;
        if (compound.contains("LY")) {
            layout = IForestryApi.INSTANCE.getCircuitManager().getLayout(compound.getString("LY").orElse(""));
        }
        this.layout = layout;

        this.circuits = new ICircuit[4];
        for (int i = 0; i < 4; i++) {
            String key = "CA.I" + i;
            if (compound.contains(key)) {
                ICircuit circuit = IForestryApi.INSTANCE.getCircuitManager().getCircuit(compound.getString(key).orElse(""));
                if (circuit != null) {
                    this.circuits[i] = circuit;
                }
            }
        }
    }

    public static CompoundTag readTag(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        return customData != null ? customData.copyTag() : new CompoundTag();
    }

    public static void writeTag(ItemStack stack, CompoundTag compound) {
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(compound));
    }

    @Override
    public void addTooltip(List<Component> list) {
        if (this.layout != null) {
            list.add(Component.literal(this.layout.getUsage().getString() + ":").withStyle(ChatFormatting.GOLD));
            List<Component> extendedTooltip = new ArrayList<>();
            for (ICircuit circuit : this.circuits) {
                if (circuit != null) {
                    circuit.addTooltip(extendedTooltip);
                }
            }
            if (extendedTooltip.size() <= 4) {
                list.addAll(extendedTooltip);
            } else {
                list.add(Component.translatable("for.gui.tooltip.tmi").withStyle(ChatFormatting.GRAY, ChatFormatting.UNDERLINE));
            }
        } else {
            int socketCount = this.type.getSockets();
            String localizationKey = "item.reforestry.circuit_board.tooltip." + (socketCount == 1 ? "singular" : "plural");
            list.add(Component.translatable(localizationKey, socketCount).withStyle(ChatFormatting.GRAY));
        }
    }

    public CompoundTag write() {
        CompoundTag compound = new CompoundTag();
        compound.putShort("T", (short) this.type.ordinal());
        if (this.layout != null) {
            compound.putString("LY", this.layout.getId());
        }
        for (int i = 0; i < this.circuits.length; i++) {
            ICircuit circuit = this.circuits[i];
            if (circuit != null) {
                compound.putString("CA.I" + i, circuit.getId());
            }
        }
        return compound;
    }

    @Override
    public void onInsertion(Object tile) {
        for (int i = 0; i < this.circuits.length; i++) {
            ICircuit circuit = this.circuits[i];
            if (circuit != null) {
                circuit.onInsertion(i, tile);
            }
        }
    }

    @Override
    public void onLoad(Object tile) {
        for (int i = 0; i < this.circuits.length; i++) {
            ICircuit circuit = this.circuits[i];
            if (circuit != null) {
                circuit.onLoad(i, tile);
            }
        }
    }

    @Override
    public void onRemoval(Object tile) {
        for (int i = 0; i < this.circuits.length; i++) {
            ICircuit circuit = this.circuits[i];
            if (circuit != null) {
                circuit.onRemoval(i, tile);
            }
        }
    }

    @Override
    public void onTick(Object tile) {
        for (int i = 0; i < this.circuits.length; i++) {
            ICircuit circuit = this.circuits[i];
            if (circuit != null) {
                circuit.onTick(i, tile);
            }
        }
    }

    @Override
    public ICircuit[] getCircuits() {
        return this.circuits;
    }

    @Nullable
    @Override
    public Identifier getSocketType() {
        return this.layout != null ? this.layout.getSocketType() : null;
    }
}
