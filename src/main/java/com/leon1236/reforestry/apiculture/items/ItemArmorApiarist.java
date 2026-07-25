package com.leon1236.reforestry.apiculture.items;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;

import com.leon1236.reforestry.api.apiculture.IArmorApiarist;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.apiculture.ApicultureArmorMaterials;

public class ItemArmorApiarist extends Item implements IArmorApiarist {
    public ItemArmorApiarist(Properties properties, ArmorType type) {
        super(properties.humanoidArmor(ApicultureArmorMaterials.APIARIST, type));
    }

    @Override
    public boolean protectEntity(LivingEntity entity, ItemStack armor, @Nullable IBeeEffect cause, boolean doProtect) {
        return true;
    }
}
