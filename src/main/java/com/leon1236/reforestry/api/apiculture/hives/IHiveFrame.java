package com.leon1236.reforestry.api.apiculture.hives;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;

public interface IHiveFrame {
    ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IGenome queen, int wear);

    IBeeModifier getBeeModifier(ItemStack frame);
}
