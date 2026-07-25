package com.leon1236.reforestry.apiculture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.IArmorApiarist;
import com.leon1236.reforestry.api.apiculture.IArmorApiaristHelper;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;

public final class ArmorApiaristHelper implements IArmorApiaristHelper {
    @Override
    public boolean isArmorApiarist(ItemStack stack, LivingEntity entity, @Nullable IBeeEffect cause, boolean doProtect) {
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.getItem() instanceof IArmorApiarist armorApiarist) {
            return armorApiarist.protectEntity(entity, stack, cause, doProtect);
        }
        return false;
    }

    @Override
    public int wearsItems(LivingEntity entity, @Nullable IBeeEffect cause, boolean doProtect) {
        int count = 0;
        for (EquipmentSlot slot : EquipmentSlot.VALUES) {
            if (isArmorApiarist(entity.getItemBySlot(slot), entity, cause, doProtect)) {
                count++;
            }
        }
        return count;
    }
}
