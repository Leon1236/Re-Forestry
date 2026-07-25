package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TileBase extends TileForestry {
    protected TileBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void openGui(Player player) {
        if (hasGui()) {
            player.openMenu(this);
        }
    }

    protected boolean hasGui() {
        return true;
    }
}
