package com.leon1236.reforestry.apiculture.features;

import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.apiculture.items.EnumPollenCluster;
import com.leon1236.reforestry.apiculture.items.EnumPropolis;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.storage.features.CrateItems;
import com.leon1236.reforestry.storage.items.ItemCrated;

public class ApicultureCrates {
	public static final FeatureItem<ItemCrated> CRATED_POLLEN_CLUSTER_NORMAL = CrateItems.registerCrate(
			ApicultureItems.POLLEN_CLUSTER.item(EnumPollenCluster.NORMAL), "crated_pollen_cluster_normal");
	public static final FeatureItem<ItemCrated> CRATED_POLLEN_CLUSTER_CRYSTALLINE = CrateItems.registerCrate(
			ApicultureItems.POLLEN_CLUSTER.item(EnumPollenCluster.CRYSTALLINE), "crated_pollen_cluster_crystalline");
	public static final FeatureItem<ItemCrated> CRATED_PROPOLIS = CrateItems.registerCrate(
			ApicultureItems.PROPOLIS.item(EnumPropolis.NORMAL), "crated_propolis");
	public static final FeatureItem<ItemCrated> CRATED_ROYAL_JELLY = CrateItems.registerCrate(
			ApicultureItems.ROYAL_JELLY.item(), "crated_royal_jelly");
	public static final FeatureItem<ItemCrated> CRATED_HONEYDEW = CrateItems.registerCrate(
			ApicultureItems.HONEYDEW.item(), "crated_honeydew");

	static {
		for (EnumHoneyComb comb : EnumHoneyComb.VALUES) {
			String crateId = comb == EnumHoneyComb.SPONGE ? "crated_spongy_comb" : "crated_" + comb.getSerializedName() + "_comb";
			CrateItems.registerCrate(ApicultureItems.BEE_COMBS.item(comb), crateId);
		}
	}

	public static void init() {
	}
}
