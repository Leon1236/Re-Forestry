package com.leon1236.reforestry.core.inventory;

import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.fabricmc.fabric.api.transfer.v1.item.ContainerStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;

public final class InventoryHelper {
    private InventoryHelper() {
    }

    public static <T extends BlockEntity & WorldlyContainer> void registerSided(BlockEntityType<T> type) {
        ItemStorage.SIDED.registerForBlockEntity((tile, direction) -> ContainerStorage.of(tile, direction), type);
    }
}
