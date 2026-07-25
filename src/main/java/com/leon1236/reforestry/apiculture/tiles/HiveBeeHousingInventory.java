package com.leon1236.reforestry.apiculture.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;

class HiveBeeHousingInventory implements IBeeHousingInventory {
    @Nullable
    private ItemStack queen;
    @Nullable
    private ItemStack drone;

    private final TileHive hive;

    HiveBeeHousingInventory(TileHive hive) {
        this.hive = hive;
    }

    @Override
    public ItemStack getQueen() {
        if (queen == null) {
            queen = createBeeStack(ApicultureItems.BEE_QUEEN.item().getDefaultInstance());
        }
        return queen;
    }

    @Override
    public ItemStack getDrone() {
        if (drone == null) {
            drone = createBeeStack(ApicultureItems.BEE_DRONE.item().getDefaultInstance());
        }
        return drone;
    }

    private ItemStack createBeeStack(ItemStack stack) {
        stack.set(ApicultureDataComponents.BEE_GENOME.type(),
                ApicultureGenetics.getDefaultGenome(hive.getSpeciesId()));
        return stack;
    }

    @Override
    public void setQueen(ItemStack stack) {
        queen = stack;
    }

    @Override
    public void setDrone(ItemStack stack) {
        drone = stack;
    }

    @Override
    public boolean addProduct(ItemStack product) {
        return true;
    }
}
