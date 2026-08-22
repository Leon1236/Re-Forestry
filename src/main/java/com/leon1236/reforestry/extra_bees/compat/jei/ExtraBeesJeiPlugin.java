package com.leon1236.reforestry.extra_bees.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.blocks.EnumExtraBeeHive;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesBlocks;
import com.leon1236.reforestry.extra_bees.items.EnumExtraBeeFrame;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesItems;

@JeiPlugin
public class ExtraBeesJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("extra_bees");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (!extraBeesLoaded()) {
			return;
		}
		JeiDescriptions.addDescription(registration, "extra_bees_frames", ExtraBeesItems.FRAMES.get(EnumExtraBeeFrame.COCOA),
				ExtraBeesItems.FRAMES.get(EnumExtraBeeFrame.CAGE),
				ExtraBeesItems.FRAMES.get(EnumExtraBeeFrame.SOUL),
				ExtraBeesItems.FRAMES.get(EnumExtraBeeFrame.CLAY),
				ExtraBeesItems.FRAMES.get(EnumExtraBeeFrame.DEBUG));
		for (BlockExtraBeeAlvearyType type : BlockExtraBeeAlvearyType.VALUES) {
			JeiDescriptions.addDescription(registration,
					ExtraBeesBlocks.ALVEARY.get(type).block().asItem(),
					"extra_bees_alveary_" + type.getSerializedName());
		}
		JeiDescriptions.addDescription(registration, ExtraBeesBlocks.ECTOPLASM.block());
		JeiDescriptions.addDescription(registration, "extra_bees_hives",
				ExtraBeesBlocks.BEEHIVE.get(EnumExtraBeeHive.WATER).block(),
				ExtraBeesBlocks.BEEHIVE.get(EnumExtraBeeHive.ROCK).block(),
				ExtraBeesBlocks.BEEHIVE.get(EnumExtraBeeHive.NETHER).block(),
				ExtraBeesBlocks.BEEHIVE.get(EnumExtraBeeHive.MARBLE).block());
	}

	private static boolean extraBeesLoaded() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("extra_bees"));
	}
}
