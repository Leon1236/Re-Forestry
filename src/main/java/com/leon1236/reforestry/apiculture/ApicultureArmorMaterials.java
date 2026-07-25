package com.leon1236.reforestry.apiculture;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;

public final class ApicultureArmorMaterials {
    public static final ResourceKey<EquipmentAsset> APIARIST_ASSET =
            ResourceKey.create(EquipmentAssets.ROOT_ID, ReForestry.id("apiarist"));

    public static final ArmorMaterial APIARIST = new ArmorMaterial(
            3,
            ArmorMaterials.makeDefense(1, 2, 3, 1, 3),
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0f,
            0.0f,
            ReforestryBiomeTags.Items.REPAIRS_APIARIST_ARMOR,
            APIARIST_ASSET);

    private ApicultureArmorMaterials() {
    }
}
