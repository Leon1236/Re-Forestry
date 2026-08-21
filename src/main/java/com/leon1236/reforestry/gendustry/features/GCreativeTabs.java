package com.leon1236.reforestry.gendustry.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.block.GendustryMachineType;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureCreativeTab GENDUSTRY = REGISTRY.creativeTab("gendustry", tab -> {
		tab.icon(() -> GBlocks.MACHINE.stack(GendustryMachineType.MUTAGEN_PRODUCER));
		tab.displayItems((parameters, output) -> {
			for (FeatureBlock<?> feature : GBlocks.MACHINE.getAll().values()) {
				output.accept(feature.item());
			}
			output.accept(GItems.POLLEN_KIT.item());
			output.accept(GFluids.MUTAGEN.getBucket());
			output.accept(GFluids.LIQUID_DNA.getBucket());
			output.accept(GFluids.PROTEIN.getBucket());
			for (FeatureItem<?> feature : GItems.RESOURCE.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : GItems.UPGRADE.getAll().values()) {
				output.accept(feature.item());
			}
			for (FeatureItem<?> feature : GItems.ELITE_UPGRADE.getAll().values()) {
				output.accept(feature.item());
			}
		});
	});

	public static void init() {
	}
}
