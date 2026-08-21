package com.leon1236.reforestry.extratrees.features;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.IWoodTyped;
import com.leon1236.reforestry.arboriculture.WoodAccess;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryButton;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryDoor;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryFence;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryFenceGate;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryHangingSign;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryLog;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryPlank;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryPressurePlate;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestrySlab;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryStairs;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryStandingSign;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryTrapdoor;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryWallHangingSign;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryWallSign;
import com.leon1236.reforestry.arboriculture.blocks.BlockFruitPod;
import com.leon1236.reforestry.arboriculture.features.ArboricultureTiles;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryDoor;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryHangingSign;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestrySign;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryWood;
import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.extratrees.blocks.ExtraTreesPodType;
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

	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> STRIPPED_LOGS =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_LOG, false, type, properties),
					WoodBlockKind.STRIPPED_LOG, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> STRIPPED_LOGS_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_LOG, true, type, properties),
					WoodBlockKind.STRIPPED_LOG, true, ExtraTreeWoodType.WITH_PRODUCTS);

	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> WOOD =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.WOOD, false, type, properties),
					WoodBlockKind.WOOD, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> WOOD_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.WOOD, true, type, properties),
					WoodBlockKind.WOOD, true, ExtraTreeWoodType.WITH_PRODUCTS);

	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> STRIPPED_WOOD =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_WOOD, false, type, properties),
					WoodBlockKind.STRIPPED_WOOD, false, ExtraTreeWoodType.WITH_PRODUCTS);
	public static final FeatureBlockGroup<BlockForestryLog, ExtraTreeWoodType> STRIPPED_WOOD_FIREPROOF =
			woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_WOOD, true, type, properties),
					WoodBlockKind.STRIPPED_WOOD, true, ExtraTreeWoodType.WITH_PRODUCTS);

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

	public static final FeatureBlockGroup<BlockForestryTrapdoor, ExtraTreeWoodType> TRAPDOORS =
			registerWood(REGISTRY.blockGroup(BlockForestryTrapdoor::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.item(ItemBlockForestryWood::new)
					.identifier("trapdoor", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	public static final FeatureBlockGroup<BlockForestryButton, ExtraTreeWoodType> BUTTON =
			registerWood(REGISTRY.blockGroup(BlockForestryButton::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.item(ItemBlockForestryWood::new)
					.identifier("button", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	public static final FeatureBlockGroup<BlockForestryPressurePlate, ExtraTreeWoodType> PRESSURE_PLATE =
			registerWood(REGISTRY.blockGroup(BlockForestryPressurePlate::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.item(ItemBlockForestryWood::new)
					.identifier("pressure_plate", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	public static final FeatureBlockGroup<BlockForestryWallSign, ExtraTreeWoodType> WALL_SIGN =
			registerWood(REGISTRY.blockGroup(BlockForestryWallSign::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.identifier("wall_sign", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	public static final FeatureBlockGroup<BlockForestryStandingSign, ExtraTreeWoodType> SIGN =
			registerWood(REGISTRY.blockGroup(BlockForestryStandingSign::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.itemWithType((block, type, properties) -> new ItemBlockForestrySign(block, WALL_SIGN.get(type).block(), properties))
					.identifier("sign", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	public static final FeatureBlockGroup<BlockForestryWallHangingSign, ExtraTreeWoodType> WALL_HANGING_SIGN =
			registerWood(REGISTRY.blockGroup(BlockForestryWallHangingSign::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.identifier("wall_hanging_sign", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	public static final FeatureBlockGroup<BlockForestryHangingSign, ExtraTreeWoodType> HANGING_SIGN =
			registerWood(REGISTRY.blockGroup(BlockForestryHangingSign::new, ExtraTreeWoodType.WITH_PRODUCTS)
					.itemWithType((block, type, properties) -> new ItemBlockForestryHangingSign(block, WALL_HANGING_SIGN.get(type).block(), properties))
					.identifier("hanging_sign", FeatureGroup.IdentifierType.SUFFIX)
					.create());

	public static final FeatureBlockGroup<BlockFruitPod, ExtraTreesPodType> PODS =
			REGISTRY.blockGroup(BlockFruitPod::new, ExtraTreesPodType.VALUES)
					.item((block, properties) -> new net.minecraft.world.item.BlockItem(block, properties))
					.identifier("pods")
					.create();

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
			int burnOdds = kind == WoodBlockKind.LOG || kind == WoodBlockKind.STRIPPED_LOG
					|| kind == WoodBlockKind.WOOD || kind == WoodBlockKind.STRIPPED_WOOD ? 5 : 20;
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

		for (ExtraTreeWoodType type : ExtraTreeWoodType.WITH_PRODUCTS) {
			StrippableBlockRegistry.register(LOGS.get(type).block(), STRIPPED_LOGS.get(type).block());
			StrippableBlockRegistry.register(LOGS_FIREPROOF.get(type).block(), STRIPPED_LOGS_FIREPROOF.get(type).block());
			StrippableBlockRegistry.register(WOOD.get(type).block(), STRIPPED_WOOD.get(type).block());
			StrippableBlockRegistry.register(WOOD_FIREPROOF.get(type).block(), STRIPPED_WOOD_FIREPROOF.get(type).block());
		}

		Stream.concat(SIGN.getAll().values().stream().map(feature -> (Block) feature.block()),
						WALL_SIGN.getAll().values().stream().map(feature -> (Block) feature.block()))
				.forEach(block -> ((FabricBlockEntityType) ArboricultureTiles.SIGN.type()).addValidBlock(block));
		Stream.concat(HANGING_SIGN.getAll().values().stream().map(feature -> (Block) feature.block()),
						WALL_HANGING_SIGN.getAll().values().stream().map(feature -> (Block) feature.block()))
				.forEach(block -> ((FabricBlockEntityType) BlockEntityTypes.HANGING_SIGN).addValidBlock(block));

		for (FeatureBlock<BlockFruitPod> feature : PODS.getAll().values()) {
			((FabricBlockEntityType) ArboricultureTiles.PODS.type()).addValidBlock(feature.block());
		}
	}
}
