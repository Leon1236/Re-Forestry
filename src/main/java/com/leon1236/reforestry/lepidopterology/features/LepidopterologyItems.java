package com.leon1236.reforestry.lepidopterology.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.lepidopterology.items.ItemButterflyGE;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class LepidopterologyItems {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("lepidopterology"));

	public static final FeatureItem<ItemButterflyGE> BUTTERFLY = REGISTRY.item(
			ButterflyLifeStage.BUTTERFLY.itemId().getPath(),
			properties -> new ItemButterflyGE(properties, ButterflyLifeStage.BUTTERFLY));
	public static final FeatureItem<ItemButterflyGE> SERUM = REGISTRY.item(
			ButterflyLifeStage.SERUM.itemId().getPath(),
			properties -> new ItemButterflyGE(properties, ButterflyLifeStage.SERUM));
	public static final FeatureItem<ItemButterflyGE> CATERPILLAR = REGISTRY.item(
			ButterflyLifeStage.CATERPILLAR.itemId().getPath(),
			properties -> new ItemButterflyGE(properties, ButterflyLifeStage.CATERPILLAR));
	public static final FeatureItem<ItemButterflyGE> COCOON = REGISTRY.item(
			ButterflyLifeStage.COCOON.itemId().getPath(),
			properties -> new ItemButterflyGE(properties, ButterflyLifeStage.COCOON));

	public static void init() {
	}
}
