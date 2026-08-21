package com.leon1236.reforestry.gendustry.features;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.item.EliteGendustryUpgradeType;
import com.leon1236.reforestry.gendustry.item.GendustryResourceType;
import com.leon1236.reforestry.gendustry.item.GendustryUpgradeItem;
import com.leon1236.reforestry.gendustry.item.GendustryUpgradeType;
import com.leon1236.reforestry.gendustry.item.ItemGendustryTooltip;
import com.leon1236.reforestry.gendustry.item.PollenKitItem;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureItemGroup<Item, GendustryResourceType> RESOURCE =
			REGISTRY.itemGroup((type, properties) -> switch (type) {
				case BLANK_GENE_SAMPLE, BLANK_GENETIC_TEMPLATE -> new ItemGendustryTooltip(properties);
				default -> new Item(properties);
			}, GendustryResourceType.values()).create();

	public static final FeatureItemGroup<GendustryUpgradeItem, GendustryUpgradeType> UPGRADE =
			REGISTRY.itemGroup((type, properties) -> new GendustryUpgradeItem(type, properties), GendustryUpgradeType.values())
					.identifier("upgrade", FeatureGroup.IdentifierType.SUFFIX)
					.create();

	public static final FeatureItemGroup<GendustryUpgradeItem, EliteGendustryUpgradeType> ELITE_UPGRADE =
			REGISTRY.itemGroup((type, properties) -> new GendustryUpgradeItem(type, properties), EliteGendustryUpgradeType.values())
					.identifier("elite_upgrade", FeatureGroup.IdentifierType.SUFFIX)
					.create();

	public static final FeatureItem<PollenKitItem> POLLEN_KIT =
			REGISTRY.item("pollen_kit", PollenKitItem::new);

	public static void init() {
	}
}
