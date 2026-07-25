package com.leon1236.reforestry.api.apiculture;

import net.minecraft.world.item.ItemStack;

public interface IBeeHousingInventory {
    ItemStack getQueen();

    ItemStack getDrone();

    void setQueen(ItemStack stack);

    void setDrone(ItemStack stack);

    boolean addProduct(ItemStack product);
}
