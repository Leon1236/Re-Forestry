package com.leon1236.reforestry.api.apiculture;

import java.util.Collections;
import java.util.List;

import net.minecraft.core.BlockPos;

public interface IBeekeepingLogic {
    boolean canWork();

    void doWork();

    int getWorkProgressPercent();

    default void onGuiOpened() {
    }

    default List<BlockPos> getFlowerPositions() {
        return Collections.emptyList();
    }

    default void doBeeFX() {
    }
}
