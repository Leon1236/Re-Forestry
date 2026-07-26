package com.leon1236.reforestry.core.gui;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.core.circuits.ISocketable;
import com.leon1236.reforestry.core.circuits.ItemSolderingIron;
import com.leon1236.reforestry.core.circuits.SocketHelper;
import com.leon1236.reforestry.core.inventory.InventoryUtil;

public class SlotCircuitSocket extends Slot {
    private final ISocketable socketable;
    private final int socketIndex;

    public SlotCircuitSocket(ISocketable socketable, int socketIndex, int x, int y) {
        super(new SocketContainer(socketable, socketIndex), 0, x, y);
        this.socketable = socketable;
        this.socketIndex = socketIndex;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (stack.isEmpty() || !IForestryApi.INSTANCE.getCircuitManager().isCircuitBoard(stack)) {
            return false;
        }
        var board = IForestryApi.INSTANCE.getCircuitManager().getCircuitBoard(stack);
        return board != null && socketable.getSocketType().equals(board.getSocketType());
    }

    @Override
    public void set(ItemStack stack) {
        SocketHelper.setSocket(this.socketable, this.socketIndex, stack);
    }

    @Override
    public ItemStack remove(int amount) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean mayPickup(Player player) {
        return false;
    }

    public boolean tryRemoveWithSolderingIron(Player player, ItemStack carried) {
        if (carried.getItem() instanceof ItemSolderingIron && !getItem().isEmpty()) {
            ItemStack socketStack = getItem().copy();
            if (InventoryUtil.stowInInventory(socketStack, player.getInventory(), true)) {
                SocketHelper.setSocket(this.socketable, this.socketIndex, ItemStack.EMPTY);
                carried.hurtAndBreak(1, player, player.getUsedItemHand());
                return true;
            }
        }
        return false;
    }

    private static final class SocketContainer implements Container {
        private final ISocketable socketable;
        private final int socketIndex;

        SocketContainer(ISocketable socketable, int socketIndex) {
            this.socketable = socketable;
            this.socketIndex = socketIndex;
        }

        @Override
        public int getContainerSize() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return getItem(0).isEmpty();
        }

        @Override
        public ItemStack getItem(int slot) {
            return this.socketable.getSocket(this.socketIndex);
        }

        @Override
        public ItemStack removeItem(int slot, int amount) {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItemNoUpdate(int slot) {
            return ItemStack.EMPTY;
        }

        @Override
        public void setItem(int slot, ItemStack stack) {
            SocketHelper.setSocket(this.socketable, this.socketIndex, stack);
        }

        @Override
        public void setChanged() {
        }

        @Override
        public boolean stillValid(Player player) {
            return true;
        }

        @Override
        public void clearContent() {
            SocketHelper.setSocket(this.socketable, this.socketIndex, ItemStack.EMPTY);
        }
    }
}
