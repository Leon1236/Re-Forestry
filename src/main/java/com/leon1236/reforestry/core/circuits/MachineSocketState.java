package com.leon1236.reforestry.core.circuits;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.circuits.ForestryCircuitSocketTypes;

public final class MachineSocketState implements ISocketable {
    private final NonNullList<ItemStack> sockets = NonNullList.withSize(1, ItemStack.EMPTY);
    private final Runnable onChanged;

    public MachineSocketState(Runnable onChanged) {
        this.onChanged = onChanged;
    }

    @Override
    public int getSocketCount() {
        return 1;
    }

    @Override
    public ItemStack getSocket(int slot) {
        return slot == 0 ? this.sockets.get(0) : ItemStack.EMPTY;
    }

    @Override
    public void setSocket(int slot, ItemStack stack) {
        if (slot != 0) {
            return;
        }
        SocketHelper.setSocket(this, slot, stack);
    }

    void storeSocket(ItemStack stack) {
        this.sockets.set(0, stack);
        this.onChanged.run();
    }

    @Override
    public Identifier getSocketType() {
        return ForestryCircuitSocketTypes.MACHINE;
    }

    public void save(ValueOutput output) {
        if (!this.sockets.get(0).isEmpty()) {
            output.store("Socket", ItemStack.CODEC, this.sockets.get(0));
        }
    }

    public void load(ValueInput input) {
        this.sockets.set(0, input.read("Socket", ItemStack.CODEC).orElse(ItemStack.EMPTY));
    }
}
