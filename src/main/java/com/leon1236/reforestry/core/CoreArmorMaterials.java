package com.leon1236.reforestry.core;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import com.leon1236.reforestry.ReForestry;

public final class CoreArmorMaterials {
	public static final ResourceKey<EquipmentAsset> NATURALIST_ASSET =
			ResourceKey.create(EquipmentAssets.ROOT_ID, ReForestry.id("naturalist"));

	public static final ArmorMaterial NATURALIST = new ArmorMaterial(
			5,
			ArmorMaterials.makeDefense(1, 2, 3, 1, 3),
			15,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0f,
			0.0f,
			ItemTags.REPAIRS_LEATHER_ARMOR,
			NATURALIST_ASSET);

	private CoreArmorMaterials() {
	}
}
