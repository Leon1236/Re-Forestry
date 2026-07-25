package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.circuits.ISocketable;
import com.leon1236.reforestry.core.circuits.MachineSocketState;
import com.leon1236.reforestry.core.circuits.SocketHelper;

public abstract class SocketedPoweredTile extends TilePowered implements ISocketable {
    private final MachineSocketState sockets = new MachineSocketState(this::setChanged);

    protected SocketedPoweredTile(BlockEntityType<?> type, BlockPos pos, BlockState state, long capacity, long maxReceive) {
        super(type, pos, state, capacity, maxReceive);
    }

    @Override
    public int getSocketCount() {
        return this.sockets.getSocketCount();
    }

    @Override
    public ItemStack getSocket(int slot) {
        return this.sockets.getSocket(slot);
    }

    @Override
    public void setSocket(int slot, ItemStack stack) {
        this.sockets.setSocket(slot, stack);
    }

    @Override
    public Identifier getSocketType() {
        return this.sockets.getSocketType();
    }

    protected void saveSockets(ValueOutput output) {
        this.sockets.save(output);
    }

    protected void loadSockets(ValueInput input) {
        this.sockets.load(input);
        SocketHelper.onLoad(this);
    }
}
