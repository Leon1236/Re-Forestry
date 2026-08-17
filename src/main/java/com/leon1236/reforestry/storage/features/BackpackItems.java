package com.leon1236.reforestry.storage.features;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.storage.EnumBackpackType;
import com.leon1236.reforestry.api.storage.IBackpackDefinition;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.storage.ModuleStorage;

public class BackpackItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("storage"));
	private static final CreativeModeTab NATURALIST_TAB_PLACEHOLDER = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
			.title(Component.translatable("itemGroup.storage"))
			.icon(() -> new ItemStack(Items.CHEST))
			.build();

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
	public static final FeatureItem<Item> APIARIST_BACKPACK = naturalist("apiarist_bag", ModuleStorage.APIARIST, ForestrySpeciesTypes.BEE);
	public static final FeatureItem<Item> ARBORIST_BACKPACK = naturalist("arborist_bag", ModuleStorage.ARBORIST, ForestrySpeciesTypes.TREE);
	public static final FeatureItem<Item> LEPIDOPTERIST_BACKPACK = naturalist("lepidopterist_bag", ModuleStorage.LEPIDOPTERIST, ForestrySpeciesTypes.BUTTERFLY);

	private static FeatureItem<Item> backpack(String id, IBackpackDefinition definition, EnumBackpackType type) {
		return REGISTRY.item(id, properties -> ModuleStorage.BACKPACK_INTERFACE.createBackpack(definition, type, properties));
	}

	private static FeatureItem<Item> naturalist(String id, IBackpackDefinition definition, Identifier speciesTypeId) {
		return REGISTRY.item(id, properties -> ModuleStorage.BACKPACK_INTERFACE.createNaturalistBackpack(
				definition, speciesTypeId, NATURALIST_TAB_PLACEHOLDER, properties));
	}

	public static void init() {
	}
}
