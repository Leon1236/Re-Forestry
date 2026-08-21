package com.leon1236.reforestry.extra_bees.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeComb;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraBeesCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_bees"));

	public static final FeatureCreativeTab EXTRA_BEES = REGISTRY.creativeTab("extra_bees", tab -> {
		tab.icon(() -> ExtraBeesItems.BEE_COMBS.get(EnumExtraBeeComb.BARREN).item().getDefaultInstance());
		tab.displayItems((parameters, output) -> {
			for (FeatureItem<?> feature : ExtraBeesItems.BEE_COMBS.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : ExtraBeesItems.HONEY_DROPS.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : ExtraBeesItems.PROPOLIS.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : ExtraBeesItems.FRAMES.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : ExtraBeesItems.MISC.getAll().values()) {
				output.accept(feature.item());
			}
			output.accept(ExtraBeesBlocks.ECTOPLASM.item());
			for (var feature : ExtraBeesBlocks.BEEHIVE.getAll().values()) {
				output.accept(feature.item());
			}
		});
	});

	public static void init() {
	}
}
