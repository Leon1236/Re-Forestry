package com.leon1236.reforestry.lepidopterology.features;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.NaturalistChestBlockType;
import com.leon1236.reforestry.core.features.CoreBlocks;
import com.leon1236.reforestry.lepidopterology.genetics.LepidopterologyGenetics;
import com.leon1236.reforestry.modules.ModuleManager;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;
import com.leon1236.reforestry.storage.features.BackpackItems;

public class LepidopterologyCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("lepidopterology"));

	public static final FeatureCreativeTab LEPIDOPTEROLOGY = REGISTRY.creativeTab("lepidopterology", tab -> {
		tab.icon(() -> LepidopterologyItems.BUTTERFLY.item().getDefaultInstance());
		tab.displayItems((parameters, output) -> {
			output.accept(CoreBlocks.NATURALIST_CHESTS.get(NaturalistChestBlockType.BUTTERFLY_CHEST).item());
			if (ModuleManager.INSTANCE.isModuleLoaded(ReForestry.id("storage"))) {
				output.accept(BackpackItems.LEPIDOPTERIST_BACKPACK.item());
			}
			for (Identifier speciesId : LepidopterologyGenetics.getAllSpeciesIds()) {
				for (FeatureItem<?> item : new FeatureItem<?>[]{
						LepidopterologyItems.BUTTERFLY,
						LepidopterologyItems.SERUM,
						LepidopterologyItems.CATERPILLAR,
						LepidopterologyItems.COCOON}) {
					ItemStack stack = new ItemStack(item.item());
					stack.set(LepidopterologyDataComponents.BUTTERFLY_GENOME.type(),
							LepidopterologyGenetics.getDefaultGenome(speciesId));
					output.accept(stack);
				}
			}
		});
	});

	public static void init() {
	}
}
