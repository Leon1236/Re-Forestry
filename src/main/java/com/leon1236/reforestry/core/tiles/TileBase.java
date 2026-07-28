package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.AccessMode;
import com.leon1236.reforestry.api.core.ISidedAccess;
import com.leon1236.reforestry.core.access.SidedAccess;

public abstract class TileBase extends TileForestry implements ISidedAccess {
    private final SidedAccess sidedAccess = new SidedAccess();

    protected TileBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public AccessMode getAccess(Direction direction) {
        return this.sidedAccess.getAccess(direction);
    }

    @Override
    public void setAccess(Direction direction, AccessMode mode) {
        this.sidedAccess.setAccess(direction, mode);
        setChanged();
        notifyAccessChanged();
    }

    @Override
    public void cycleAccess(Direction direction) {
        this.sidedAccess.cycleAccess(direction);
        setChanged();
        notifyAccessChanged();
    }

    private void notifyAccessChanged() {
        if (this.level != null && !this.level.isClientSide()) {
            this.level.updateNeighborsAt(this.worldPosition, getBlockState().getBlock());
        }
    }

    public void openGui(Player player) {
        if (hasGui()) {
            player.openMenu(this);
        }
    }

    protected boolean hasGui() {
        return true;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.sidedAccess.read(input.childOrEmpty("Access"));
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        this.sidedAccess.write(output.child("Access"));
    }
}
