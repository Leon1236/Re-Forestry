package com.leon1236.reforestry.api.apiculture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;

public interface IArmorApiarist {
    boolean protectEntity(LivingEntity entity, ItemStack armor, @Nullable IBeeEffect cause, boolean doProtect);
}
