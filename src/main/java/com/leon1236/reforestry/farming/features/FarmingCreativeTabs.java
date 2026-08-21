package com.leon1236.reforestry.farming.features;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.farming.blocks.EnumFarmBlockType;
import com.leon1236.reforestry.farming.blocks.EnumFarmMaterial;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FarmingCreativeTabs {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("farming"));
	private static Supplier<ItemStack> agricultureIcon = () -> FarmingBlocks.FARM.stack(EnumFarmBlockType.PLAIN, EnumFarmMaterial.STONE_BRICK);
	private static final List<Consumer<CreativeModeTab.Output>> extraAgricultureItems = new ArrayList<>();

	public static final FeatureCreativeTab AGRICULTURE = REGISTRY.creativeTab("agriculture", tab -> {
		tab.icon(() -> agricultureIcon.get());
		tab.displayItems((parameters, output) -> {
			for (EnumFarmMaterial material : EnumFarmMaterial.values()) {
				for (EnumFarmBlockType type : EnumFarmBlockType.values()) {
					output.accept(FarmingBlocks.FARM.stack(type, material));
				}
			}
			for (Consumer<CreativeModeTab.Output> extra : extraAgricultureItems) {
				extra.accept(output);
			}
		});
	});

	public static void setAgricultureIcon(Supplier<ItemStack> icon) {
		agricultureIcon = icon;
	}

	public static void addAgricultureItems(Consumer<CreativeModeTab.Output> extra) {
		extraAgricultureItems.add(extra);
	}

	public static void init() {
	}
}
