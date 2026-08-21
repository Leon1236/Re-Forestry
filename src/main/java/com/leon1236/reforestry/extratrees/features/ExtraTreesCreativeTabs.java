package com.leon1236.reforestry.extratrees.features;

import java.util.List;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.extratrees.items.EnumExtraTreesFood;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	private static final List<FeatureBlockGroup<?, ExtraTreeWoodType>> WOOD_GROUPS = List.of(
			ExtraTreesBlocks.LOGS,
			ExtraTreesBlocks.LOGS_FIREPROOF,
			ExtraTreesBlocks.STRIPPED_LOGS,
			ExtraTreesBlocks.STRIPPED_LOGS_FIREPROOF,
			ExtraTreesBlocks.WOOD,
			ExtraTreesBlocks.WOOD_FIREPROOF,
			ExtraTreesBlocks.STRIPPED_WOOD,
			ExtraTreesBlocks.STRIPPED_WOOD_FIREPROOF,
			ExtraTreesBlocks.PLANKS,
			ExtraTreesBlocks.PLANKS_FIREPROOF,
			ExtraTreesBlocks.SLABS,
			ExtraTreesBlocks.SLABS_FIREPROOF,
			ExtraTreesBlocks.FENCES,
			ExtraTreesBlocks.FENCES_FIREPROOF,
			ExtraTreesBlocks.FENCE_GATES,
			ExtraTreesBlocks.FENCE_GATES_FIREPROOF,
			ExtraTreesBlocks.STAIRS,
			ExtraTreesBlocks.STAIRS_FIREPROOF,
			ExtraTreesBlocks.DOORS,
			ExtraTreesBlocks.TRAPDOORS,
			ExtraTreesBlocks.BUTTON,
			ExtraTreesBlocks.PRESSURE_PLATE,
			ExtraTreesBlocks.SIGN,
			ExtraTreesBlocks.HANGING_SIGN
	);

	public static final FeatureCreativeTab EXTRA_TREES = REGISTRY.creativeTab("extra_trees", tab -> {
		tab.icon(() -> ExtraTreesBlocks.PLANKS.get(ExtraTreeWoodType.CEDAR).item().getDefaultInstance());
		tab.displayItems((parameters, output) -> {
			for (EnumExtraTreesFood food : EnumExtraTreesFood.VALUES) {
				output.accept(ExtraTreesItems.FOODS.item(food));
			}
			for (FeatureBlock<?> feature : ExtraTreesBlocks.PODS.getAll().values()) {
				if (feature.item() != null) {
					output.accept(feature.item());
				}
			}
			for (FeatureBlockGroup<?, ExtraTreeWoodType> group : WOOD_GROUPS) {
				for (FeatureBlock<?> feature : group.getAll().values()) {
					if (feature.item() != null) {
						output.accept(feature.item());
					}
				}
			}
			for (ExtraTreeWoodType type : ExtraTreeWoodType.WITH_PRODUCTS) {
				output.accept(ExtraTreesItems.BOAT.item(type));
				output.accept(ExtraTreesItems.CHEST_BOAT.item(type));
			}
			output.accept(ExtraTreesItems.PROVEN_GEAR.item());
			output.accept(ExtraTreesItems.SAWDUST.item());
			output.accept(ExtraTreesItems.BARK.item());
			output.accept(ExtraTreesItems.WOOD_WAX.item());
			output.accept(ExtraTreesItems.YEAST.item());
			output.accept(ExtraTreesItems.LAGER_YEAST.item());
			output.accept(ExtraTreesItems.GRAIN_WHEAT.item());
			output.accept(ExtraTreesItems.GRAIN_BARLEY.item());
			output.accept(ExtraTreesItems.GRAIN_RYE.item());
			output.accept(ExtraTreesItems.GRAIN_CORN.item());
			output.accept(ExtraTreesItems.GRAIN_ROASTED.item());
			if (ExtraTreesBlocks.HOPS.item() != null) {
				output.accept(ExtraTreesBlocks.HOPS.item());
			}
			for (var feature : ExtraTreesBlocks.MACHINES.getAll().values()) {
				if (feature.item() != null) {
					output.accept(feature.item());
				}
			}
			for (com.leon1236.reforestry.extratrees.fluids.ExtraTreesFluids fluid : com.leon1236.reforestry.extratrees.fluids.ExtraTreesFluids.values()) {
				output.accept(fluid.getBucket());
			}
		});
	});

	public static void init() {
	}
}
