package com.leon1236.reforestry.core.circuits;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.circuits.ICircuitBoard;

public final class SocketHelper {
    private SocketHelper() {
    }

    public static void setSocket(ISocketable socketable, int slot, ItemStack stack) {
        if (!(socketable instanceof MachineSocketState machineSocket)) {
            return;
        }
        if (!stack.isEmpty() && !IForestryApi.INSTANCE.getCircuitManager().isCircuitBoard(stack)) {
            return;
        }

        ItemStack existing = machineSocket.getSocket(slot);
        if (!existing.isEmpty()) {
            ICircuitBoard oldBoard = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(existing);
            if (oldBoard != null) {
                oldBoard.onRemoval(socketable);
            }
        }

        ItemStack placed = stack.isEmpty() ? ItemStack.EMPTY : stack.copyWithCount(1);
        machineSocket.storeSocket(placed);

        if (!placed.isEmpty()) {
            ICircuitBoard newBoard = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(placed);
            if (newBoard != null) {
                newBoard.onInsertion(socketable);
            }
        }
    }

    public static void onLoad(ISocketable socketable) {
        for (int slot = 0; slot < socketable.getSocketCount(); slot++) {
            ItemStack chip = socketable.getSocket(slot);
            if (!chip.isEmpty()) {
                ICircuitBoard board = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(chip);
                if (board != null) {
                    board.onLoad(socketable);
                }
            }
        }
    }
}
