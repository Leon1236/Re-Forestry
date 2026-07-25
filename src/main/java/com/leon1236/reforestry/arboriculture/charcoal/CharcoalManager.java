package com.leon1236.reforestry.arboriculture.charcoal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.ICharcoalManager;
import com.leon1236.reforestry.api.arboriculture.ICharcoalPileWall;
import com.leon1236.reforestry.core.config.ForestryConfig;

public class CharcoalManager implements ICharcoalManager {
    private static CharcoalManager instance = new CharcoalManager();

    private final List<ICharcoalPileWall> walls = new ArrayList<>();

    public static CharcoalManager get() {
        return instance;
    }

    public static void setInstance(CharcoalManager manager) {
        instance = manager;
    }

    public static int charcoalAmountBase() {
        return ForestryConfig.charcoalAmountBase();
    }

    public static int charcoalWallCheckRange() {
        return ForestryConfig.charcoalWallCheckRange();
    }

    public void addWall(BlockState blockState, int amount) {
        int minExclusive = -charcoalAmountBase();
        int maxExclusive = 63 - charcoalAmountBase();
        if (amount <= minExclusive || amount >= maxExclusive) {
            throw new IllegalArgumentException(
                    "amount must be greater than " + minExclusive + " and less than " + maxExclusive);
        }
        this.walls.add(new CharcoalPileWall(blockState, amount));
    }

    @Override
    public void registerWall(Block block, int amount) {
        this.walls.add(new CharcoalPileWall(block, amount));
    }

    @Override
    public void registerWall(BlockState blockState, int amount) {
        addWall(blockState, amount);
    }

    @Override
    public void registerWall(ICharcoalPileWall wall) {
        this.walls.add(wall);
    }

    @Nullable
    @Override
    public ICharcoalPileWall getWall(BlockState state) {
        for (ICharcoalPileWall wall : this.walls) {
            if (wall.matches(state)) {
                return wall;
            }
        }
        return null;
    }

    @Override
    public boolean removeWall(Block block) {
        return removeWall(block.defaultBlockState());
    }

    @Override
    public boolean removeWall(BlockState state) {
        for (ICharcoalPileWall wall : this.walls) {
            if (wall.matches(state)) {
                return this.walls.remove(wall);
            }
        }
        return false;
    }

    @Override
    public List<ICharcoalPileWall> getWalls() {
        return Collections.unmodifiableList(this.walls);
    }
}
