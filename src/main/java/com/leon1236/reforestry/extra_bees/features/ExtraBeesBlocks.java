package com.leon1236.reforestry.extra_bees.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.blocks.BlockBeeHive;
import com.leon1236.reforestry.extra_bees.blocks.BlockEctoplasm;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlveary;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.blocks.EnumExtraBeeHive;
import com.leon1236.reforestry.extra_bees.items.ItemBlockExtraBeeAlveary;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraBeesBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_bees"));

	public static final FeatureBlockGroup<BlockBeeHive, EnumExtraBeeHive> BEEHIVE =
			REGISTRY.blockGroup((type, properties) -> new BlockBeeHive(type.getSpeciesId(), hiveProperties(properties),
							() -> ExtraBeesTiles.HIVE.type()), EnumExtraBeeHive.values())
					.item(BlockItem::new)
					.identifier("beehive")
					.create();

	public static final FeatureBlockGroup<BlockExtraBeeAlveary, BlockExtraBeeAlvearyType> ALVEARY =
			REGISTRY.blockGroup((type, properties) -> new BlockExtraBeeAlveary(type, alvearyProperties(properties)),
							BlockExtraBeeAlvearyType.VALUES)
					.item(ItemBlockExtraBeeAlveary::new)
					.identifier("alveary")
					.create();

	public static final FeatureBlock<BlockEctoplasm> ECTOPLASM = REGISTRY.block("ectoplasm",
			properties -> new BlockEctoplasm(ectoplasmProperties()),
			BlockItem::new);

	private static BlockBehaviour.Properties hiveProperties(BlockBehaviour.Properties properties) {
		return properties.sound(SoundType.WOOD).lightLevel(state -> 7).strength(1.0F);
	}

	private static BlockBehaviour.Properties alvearyProperties(BlockBehaviour.Properties properties) {
		return properties.strength(1.0F).sound(SoundType.WOOD);
	}

	private static BlockBehaviour.Properties ectoplasmProperties() {
		return BlockBehaviour.Properties.ofFullCopy(Blocks.COBWEB)
				.strength(0.5F)
				.setId(ResourceKey.create(Registries.BLOCK, ReForestry.id("ectoplasm")));
	}

	public static void init() {
		for (var feature : BEEHIVE.getAll().values()) {
			FlammableBlockRegistry.getDefaultInstance().add(feature.block(), 5, 5);
		}
	}
}
