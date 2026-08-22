package com.leon1236.reforestry.gendustry.features;

import net.minecraft.core.BlockPos;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.menu.AdvancedMutatronMenu;
import com.leon1236.reforestry.gendustry.menu.IndustrialApiaryMenu;
import com.leon1236.reforestry.gendustry.menu.MutatronMenu;
import com.leon1236.reforestry.gendustry.menu.ProducerMenu;
import com.leon1236.reforestry.gendustry.menu.ReplicatorMenu;
import com.leon1236.reforestry.gendustry.menu.ThreeInputMenu;
import com.leon1236.reforestry.modules.features.FeatureMenuType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GMenus {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureMenuType<IndustrialApiaryMenu, BlockPos> INDUSTRIAL_APIARY =
			REGISTRY.menuType("industrial_apiary", IndustrialApiaryMenu::new, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ProducerMenu, BlockPos> PROCESSOR =
			REGISTRY.menuType("processor", ProducerMenu::new, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ThreeInputMenu<?>, BlockPos> SAMPLER =
			REGISTRY.menuType("sampler", ThreeInputMenu::sampler, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ThreeInputMenu<?>, BlockPos> IMPRINTER =
			REGISTRY.menuType("imprinter", ThreeInputMenu::imprinter, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ThreeInputMenu<?>, BlockPos> GENETIC_TRANSPOSER =
			REGISTRY.menuType("genetic_transposer", ThreeInputMenu::geneticTransposer, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<MutatronMenu, BlockPos> MUTATRON =
			REGISTRY.menuType("mutatron", MutatronMenu::new, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<AdvancedMutatronMenu, BlockPos> ADVANCED_MUTATRON =
			REGISTRY.menuType("advanced_mutatron", AdvancedMutatronMenu::new, BlockPos.STREAM_CODEC);

	public static final FeatureMenuType<ReplicatorMenu, BlockPos> REPLICATOR =
			REGISTRY.menuType("replicator", ReplicatorMenu::new, BlockPos.STREAM_CODEC);

	public static void init() {
	}
}
