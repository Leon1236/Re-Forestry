package com.leon1236.reforestry.api.apiculture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;

public interface IArmorApiaristHelper {
    boolean isArmorApiarist(ItemStack stack, LivingEntity entity, @Nullable IBeeEffect cause, boolean doProtect);

    int wearsItems(LivingEntity entity, @Nullable IBeeEffect cause, boolean doProtect);
}
