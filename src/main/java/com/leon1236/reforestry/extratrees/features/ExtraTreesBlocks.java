package com.leon1236.reforestry.extratrees.features;

import java.util.function.BiFunction;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;
import com.leon1236.reforestry.arboriculture.WoodAccess;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryDoor;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryFence;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryFenceGate;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryLog;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryPlank;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestrySlab;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryStairs;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryDoor;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryWood;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesBlocks {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> LOGS =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.LOG, false, type, properties),
					WoodBlockKind.LOG, false, ExtraTreeWoodType.ALL_LOG_TYPES);
	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> LOGS_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.LOG, true, type, properties),
					WoodBlockKind.LOG, true, ExtraTreeWoodType.ALL_LOG_TYPES);

	public static final FeatureBlockGroup<BlockForestryPlank, ExtraTreeWoodType> PLANKS =
			woodGroup((type, properties) -> new BlockForestryPlank(false, type, properties),
					WoodBlockKind.PLANKS, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestryPlank, ExtraTreeWoodType> PLANKS_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryPlank(true, type, properties),
					WoodBlockKind.PLANKS, true, ExtraTreeWoodType.WITH_PRODUCTS);

	public static final FeatureBlockGroup<BlockForestrySlab, ExtraTreeWoodType> SLABS =
			woodGroup((type, properties) -> new BlockForestrySlab(PLANKS.get(type).block(), properties),
					WoodBlockKind.SLAB, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestrySlab, ExtraTreeWoodType> SLABS_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestrySlab(PLANKS_FIREPROOF.get(type).block(), properties),
					WoodBlockKind.SLAB, true, ExtraTreeWoodType.WITH_PRODUCTS);

	public static final FeatureBlockGroup<BlockForestryFence, ExtraTreeWoodType> FENCES =
			woodGroup((type, properties) -> new BlockForestryFence(false, type, properties),
					WoodBlockKind.FENCE, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestryFence, ExtraTreeWoodType> FENCES_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryFence(true, type, properties),
					WoodBlockKind.FENCE, true, ExtraTreeWoodType.WITH_PRODUCTS);

	public static final FeatureBlockGroup<BlockForestryFenceGate, ExtraTreeWoodType> FENCE_GATES =
			woodGroup((type, properties) -> new BlockForestryFenceGate(false, type, properties),
					WoodBlockKind.FENCE_GATE, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestryFenceGate, ExtraTreeWoodType> FENCE_GATES_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryFenceGate(true, type, properties),
					WoodBlockKind.FENCE_GATE, true, ExtraTreeWoodType.WITH_PRODUCTS);

	public static final FeatureBlockGroup<BlockForestryStairs, ExtraTreeWoodType> STAIRS =
			woodGroup((type, properties) -> new BlockForestryStairs(PLANKS.get(type).block(), properties),
					WoodBlockKind.STAIRS, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestryStairs, ExtraTreeWoodType> STAIRS_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryStairs(PLANKS_FIREPROOF.get(type).block(), properties),
					WoodBlockKind.STAIRS, true, ExtraTreeWoodType.WITH_PRODUCTS);

	public static final FeatureBlockGroup<BlockForestryDoor, ExtraTreeWoodType> DOORS =
			registerWood(REGISTRY.blockGroup(BlockForestryDoor::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.item(ItemBlockForestryDoor::new)
					.identifier("door", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	private static <B extends Block & IWoodTyped> FeatureBlockGroup<B, ExtraTreeWoodType> woodGroup(
			BiFunction<ExtraTreeWoodType, BlockBehaviour.Properties, B> constructor,
			WoodBlockKind kind,
			boolean fireproof,
			ExtraTreeWoodType[] types) {
		FeatureBlockGroup<B, ExtraTreeWoodType> group = registerWood(REGISTRY.blockGroup(constructor, types)
				.item(ItemBlockForestryWood::new)
				.identifier((fireproof ? "fireproof_" : "") + kind.getSerializedName(), FeatureGroup.IdentifierType.SUFFIX)
				.create());
		if (!fireproof) {
			int burnOdds = kind == WoodBlockKind.LOG ? 5 : 20;
			for (FeatureBlock<B> feature : group.getAll().values()) {
				FlammableBlockRegistry.getDefaultInstance().add(feature.block(), 5, burnOdds);
			}
		}
		return group;
	}

	private static <B extends Block & IWoodTyped> FeatureBlockGroup<B, ExtraTreeWoodType> registerWood(
			FeatureBlockGroup<B, ExtraTreeWoodType> group) {
		for (FeatureBlock<B> feature : group.getAll().values()) {
			WoodAccess.INSTANCE.register(feature.block());
		}
		return group;
	}

	public static void init() {
		for (ExtraTreeWoodType type : ExtraTreeWoodType.ALL_LOG_TYPES) {
			String name = type.getSerializedName();
			WoodAccess.INSTANCE.registerLogTag(
					type,
					false,
					TagKey.create(Registries.BLOCK, ReForestry.id(name + "_logs")),
					TagKey.create(Registries.ITEM, ReForestry.id(name + "_logs")));
			WoodAccess.INSTANCE.registerLogTag(
					type,
					true,
					TagKey.create(Registries.BLOCK, ReForestry.id("fireproof_" + name + "_logs")),
					TagKey.create(Registries.ITEM, ReForestry.id("fireproof_" + name + "_logs")));
		}
	}
}
