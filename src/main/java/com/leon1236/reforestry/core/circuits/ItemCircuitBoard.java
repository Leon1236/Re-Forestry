package com.leon1236.reforestry.core.circuits;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.circuits.ICircuit;
import com.leon1236.reforestry.api.circuits.ICircuitBoard;
import com.leon1236.reforestry.api.circuits.ICircuitLayout;
import com.leon1236.reforestry.core.features.CoreItems;

public class ItemCircuitBoard extends Item {
    private final EnumCircuitBoardType type;

    public ItemCircuitBoard(EnumCircuitBoardType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public EnumCircuitBoardType getType() {
        return this.type;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
            Consumer<Component> tooltip, TooltipFlag flag) {
        ICircuitBoard circuitBoard = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(stack);
        if (circuitBoard != null) {
            List<Component> lines = new ArrayList<>();
            circuitBoard.addTooltip(lines);
            lines.forEach(tooltip);
        }
    }

    public static ItemStack createCircuitBoard(EnumCircuitBoardType type, @Nullable ICircuitLayout layout, ICircuit[] circuits) {
        ItemStack stack = new ItemStack(CoreItems.CIRCUITBOARDS.get(type).item());
        CircuitBoard.writeTag(stack, new CircuitBoard(type, layout, circuits).write());
        return stack;
    }
}
