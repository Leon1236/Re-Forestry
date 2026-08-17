package com.leon1236.reforestry.storage.features;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.storage.EnumBackpackType;
import com.leon1236.reforestry.api.storage.IBackpackDefinition;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.storage.ModuleStorage;

public class BackpackItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("storage"));

	public static final FeatureItem<Item> MINER_BACKPACK = backpack("miner_bag", ModuleStorage.MINER, EnumBackpackType.NORMAL);
	public static final FeatureItem<Item> MINER_BACKPACK_T_2 = backpack("miner_bag_woven", ModuleStorage.MINER, EnumBackpackType.WOVEN);
	public static final FeatureItem<Item> DIGGER_BACKPACK = backpack("digger_bag", ModuleStorage.DIGGER, EnumBackpackType.NORMAL);
	public static final FeatureItem<Item> DIGGER_BACKPACK_T_2 = backpack("digger_bag_woven", ModuleStorage.DIGGER, EnumBackpackType.WOVEN);
	public static final FeatureItem<Item> FORESTER_BACKPACK = backpack("forester_bag", ModuleStorage.FORESTER, EnumBackpackType.NORMAL);
	public static final FeatureItem<Item> FORESTER_BACKPACK_T_2 = backpack("forester_bag_woven", ModuleStorage.FORESTER, EnumBackpackType.WOVEN);
	public static final FeatureItem<Item> HUNTER_BACKPACK = backpack("hunter_bag", ModuleStorage.HUNTER, EnumBackpackType.NORMAL);
	public static final FeatureItem<Item> HUNTER_BACKPACK_T_2 = backpack("hunter_bag_woven", ModuleStorage.HUNTER, EnumBackpackType.WOVEN);
	public static final FeatureItem<Item> ADVENTURER_BACKPACK = backpack("adventurer_bag", ModuleStorage.ADVENTURER, EnumBackpackType.NORMAL);
	public static final FeatureItem<Item> ADVENTURER_BACKPACK_T_2 = backpack("adventurer_bag_woven", ModuleStorage.ADVENTURER, EnumBackpackType.WOVEN);
	public static final FeatureItem<Item> BUILDER_BACKPACK = backpack("builder_bag", ModuleStorage.BUILDER, EnumBackpackType.NORMAL);
	public static final FeatureItem<Item> BUILDER_BACKPACK_T_2 = backpack("builder_bag_woven", ModuleStorage.BUILDER, EnumBackpackType.WOVEN);
	public static final FeatureItem<Item> BREWER_BACKPACK = backpack("brewer_bag", ModuleStorage.BREWER, EnumBackpackType.NORMAL);
	public static final FeatureItem<Item> BREWER_BACKPACK_T_2 = backpack("brewer_bag_woven", ModuleStorage.BREWER, EnumBackpackType.WOVEN);

	private static FeatureItem<Item> backpack(String id, IBackpackDefinition definition, EnumBackpackType type) {
		return REGISTRY.item(id, properties -> ModuleStorage.BACKPACK_INTERFACE.createBackpack(definition, type, properties));
	}

	public static void init() {
	}
}
