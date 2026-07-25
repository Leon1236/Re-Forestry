package com.leon1236.reforestry.api.arboriculture;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface ICharcoalManager {
    void registerWall(Block block, int amount);

    void registerWall(BlockState blockState, int amount);

    void registerWall(ICharcoalPileWall wall);

    @Nullable
    ICharcoalPileWall getWall(BlockState state);

    boolean removeWall(Block block);

    boolean removeWall(BlockState state);

    List<ICharcoalPileWall> getWalls();
}
