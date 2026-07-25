package com.leon1236.reforestry.arboriculture.features;

import java.util.function.BiFunction;
import java.util.function.Function;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.VanillaWoodType;
import com.leon1236.reforestry.arboriculture.WoodAccess;
import com.leon1236.reforestry.arboriculture.blocks.BlockDecorativeLeaves;
import com.leon1236.reforestry.arboriculture.blocks.BlockDefaultLeaves;
import com.leon1236.reforestry.arboriculture.blocks.BlockDefaultLeavesFruit;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryButton;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryLeaves;
import com.leon1236.reforestry.arboriculture.blocks.BlockSapling;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;
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
import com.leon1236.reforestry.arboriculture.items.ItemBlockDecorativeLeaves;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryDoor;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryHangingSign;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestrySign;
import com.leon1236.reforestry.arboriculture.items.ItemBlockForestryWood;
import com.leon1236.reforestry.arboriculture.items.ItemBlockLeaves;
import com.leon1236.reforestry.arboriculture.items.ItemBlockWood;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.FeatureGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ArboricultureBlocks {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("arboriculture"));

    public static final FeatureBlockGroup<RotatedPillarBlock, VanillaWoodType> FIREPROOF_LOG =
            REGISTRY.blockGroup((type, properties) -> new RotatedPillarBlock(woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> type.serializedName + "_log"))
                    .identifier("fireproof_log", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<RotatedPillarBlock, VanillaWoodType> FIREPROOF_STRIPPED_LOG =
            REGISTRY.blockGroup((type, properties) -> new RotatedPillarBlock(woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> "stripped_" + type.serializedName + "_log"))
                    .identifier("fireproof_stripped_log", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<RotatedPillarBlock, VanillaWoodType> FIREPROOF_WOOD =
            REGISTRY.blockGroup((type, properties) -> new RotatedPillarBlock(woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> type.serializedName + "_wood"))
                    .identifier("fireproof_wood", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<RotatedPillarBlock, VanillaWoodType> FIREPROOF_STRIPPED_WOOD =
            REGISTRY.blockGroup((type, properties) -> new RotatedPillarBlock(woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> "stripped_" + type.serializedName + "_wood"))
                    .identifier("fireproof_stripped_wood", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<Block, VanillaWoodType> FIREPROOF_PLANKS =
            REGISTRY.blockGroup((type, properties) -> new Block(woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> type.serializedName + "_planks"))
                    .identifier("fireproof_planks", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<SlabBlock, VanillaWoodType> FIREPROOF_SLAB =
            REGISTRY.blockGroup((type, properties) -> new SlabBlock(woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> type.serializedName + "_slab"))
                    .identifier("fireproof_slab", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<FenceBlock, VanillaWoodType> FIREPROOF_FENCE =
            REGISTRY.blockGroup((type, properties) -> new FenceBlock(woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> type.serializedName + "_fence"))
                    .identifier("fireproof_fence", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<FenceGateBlock, VanillaWoodType> FIREPROOF_FENCE_GATE =
            REGISTRY.blockGroup((type, properties) -> new FenceGateBlock(type.vanillaWoodType, woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> type.serializedName + "_fence_gate"))
                    .identifier("fireproof_fence_gate", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<BlockForestryStairs, VanillaWoodType> FIREPROOF_STAIRS =
            REGISTRY.blockGroup((type, properties) -> new BlockForestryStairs(FIREPROOF_PLANKS.get(type).block().defaultBlockState(), woodProperties(properties)), VanillaWoodType.VALUES)
                    .itemWithType(woodItem(type -> type.serializedName + "_stairs"))
                    .identifier("fireproof_stairs", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> LOGS =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.LOG, false, type, properties), WoodBlockKind.LOG, false);
    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> LOGS_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.LOG, true, type, properties), WoodBlockKind.LOG, true);
    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> STRIPPED_LOGS =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_LOG, false, type, properties), WoodBlockKind.STRIPPED_LOG, false);
    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> STRIPPED_LOGS_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_LOG, true, type, properties), WoodBlockKind.STRIPPED_LOG, true);
    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> WOOD =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.WOOD, false, type, properties), WoodBlockKind.WOOD, false);
    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> WOOD_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.WOOD, true, type, properties), WoodBlockKind.WOOD, true);
    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> STRIPPED_WOOD =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_WOOD, false, type, properties), WoodBlockKind.STRIPPED_WOOD, false);
    public static final FeatureBlockGroup<BlockForestryLog, ForestryWoodType> STRIPPED_WOOD_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryLog(WoodBlockKind.STRIPPED_WOOD, true, type, properties), WoodBlockKind.STRIPPED_WOOD, true);

    public static final FeatureBlockGroup<BlockForestryPlank, ForestryWoodType> PLANKS =
            woodGroup((type, properties) -> new BlockForestryPlank(false, type, properties), WoodBlockKind.PLANKS, false);
    public static final FeatureBlockGroup<BlockForestryPlank, ForestryWoodType> PLANKS_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryPlank(true, type, properties), WoodBlockKind.PLANKS, true);

    public static final FeatureBlockGroup<BlockForestrySlab, ForestryWoodType> SLABS =
            woodGroup((type, properties) -> new BlockForestrySlab(PLANKS.get(type).block(), properties), WoodBlockKind.SLAB, false);
    public static final FeatureBlockGroup<BlockForestrySlab, ForestryWoodType> SLABS_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestrySlab(PLANKS_FIREPROOF.get(type).block(), properties), WoodBlockKind.SLAB, true);

    public static final FeatureBlockGroup<BlockForestryFence, ForestryWoodType> FENCES =
            woodGroup((type, properties) -> new BlockForestryFence(false, type, properties), WoodBlockKind.FENCE, false);
    public static final FeatureBlockGroup<BlockForestryFence, ForestryWoodType> FENCES_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryFence(true, type, properties), WoodBlockKind.FENCE, true);

    public static final FeatureBlockGroup<BlockForestryFenceGate, ForestryWoodType> FENCE_GATES =
            woodGroup((type, properties) -> new BlockForestryFenceGate(false, type, properties), WoodBlockKind.FENCE_GATE, false);
    public static final FeatureBlockGroup<BlockForestryFenceGate, ForestryWoodType> FENCE_GATES_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryFenceGate(true, type, properties), WoodBlockKind.FENCE_GATE, true);

    public static final FeatureBlockGroup<BlockForestryStairs, ForestryWoodType> STAIRS =
            woodGroup((type, properties) -> new BlockForestryStairs(PLANKS.get(type).block(), properties), WoodBlockKind.STAIRS, false);
    public static final FeatureBlockGroup<BlockForestryStairs, ForestryWoodType> STAIRS_FIREPROOF =
            woodGroup((type, properties) -> new BlockForestryStairs(PLANKS_FIREPROOF.get(type).block(), properties), WoodBlockKind.STAIRS, true);

    public static final FeatureBlockGroup<BlockForestryDoor, ForestryWoodType> DOORS =
            registerWood(REGISTRY.blockGroup(BlockForestryDoor::new, ForestryWoodType.VALUES)
                    .item(ItemBlockForestryDoor::new)
                    .identifier("door", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlockGroup<BlockForestryTrapdoor, ForestryWoodType> TRAPDOORS =
            registerWood(REGISTRY.blockGroup(BlockForestryTrapdoor::new, ForestryWoodType.VALUES)
                    .item(ItemBlockForestryWood::new)
                    .identifier("trapdoor", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlockGroup<BlockForestryButton, ForestryWoodType> BUTTON =
            registerWood(REGISTRY.blockGroup(BlockForestryButton::new, ForestryWoodType.VALUES)
                    .item(ItemBlockForestryWood::new)
                    .identifier("button", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlockGroup<BlockForestryPressurePlate, ForestryWoodType> PRESSURE_PLATE =
            registerWood(REGISTRY.blockGroup(BlockForestryPressurePlate::new, ForestryWoodType.VALUES)
                    .item(ItemBlockForestryWood::new)
                    .identifier("pressure_plate", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlockGroup<BlockForestryWallSign, ForestryWoodType> WALL_SIGN =
            registerWood(REGISTRY.blockGroup(BlockForestryWallSign::new, ForestryWoodType.VALUES)
                    .identifier("wall_sign", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlockGroup<BlockForestryStandingSign, ForestryWoodType> SIGN =
            registerWood(REGISTRY.blockGroup(BlockForestryStandingSign::new, ForestryWoodType.VALUES)
                    .itemWithType((block, type, properties) -> new ItemBlockForestrySign(block, WALL_SIGN.get(type).block(), properties))
                    .identifier("sign", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlockGroup<BlockForestryWallHangingSign, ForestryWoodType> WALL_HANGING_SIGN =
            registerWood(REGISTRY.blockGroup(BlockForestryWallHangingSign::new, ForestryWoodType.VALUES)
                    .identifier("wall_hanging_sign", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlockGroup<BlockForestryHangingSign, ForestryWoodType> HANGING_SIGN =
            registerWood(REGISTRY.blockGroup(BlockForestryHangingSign::new, ForestryWoodType.VALUES)
                    .itemWithType((block, type, properties) -> new ItemBlockForestryHangingSign(block, WALL_HANGING_SIGN.get(type).block(), properties))
                    .identifier("hanging_sign", FeatureGroup.IdentifierType.SUFFIX)
                    .create());

    public static final FeatureBlock<BlockSapling> SAPLING = REGISTRY.block("sapling_ge",
            properties -> new BlockSapling(properties.noCollision().strength(0.0F).sound(SoundType.GRASS).randomTicks()),
            null);

    public static final FeatureBlock<BlockForestryLeaves> LEAVES = REGISTRY.block("leaves",
            properties -> new BlockForestryLeaves(properties.strength(0.2F).sound(SoundType.GRASS)
                    .randomTicks().noOcclusion()),
            null);

    public static final FeatureBlockGroup<BlockDefaultLeaves, ForestryLeafType> LEAVES_DEFAULT =
            REGISTRY.blockGroup(BlockDefaultLeaves::new, ForestryLeafType.VALUES)
                    .item(ItemBlockLeaves::new)
                    .identifier("default_leaves", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<BlockDefaultLeavesFruit, ForestryLeafType> LEAVES_DEFAULT_FRUIT =
            REGISTRY.blockGroup(BlockDefaultLeavesFruit::new, ForestryLeafType.VALUES)
                    .item(ItemBlockLeaves::new)
                    .identifier("default_leaves_fruit", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    public static final FeatureBlockGroup<BlockDecorativeLeaves, ForestryLeafType> LEAVES_DECORATIVE =
            REGISTRY.blockGroup(BlockDecorativeLeaves::new, ForestryLeafType.VALUES)
                    .item(ItemBlockDecorativeLeaves::new)
                    .identifier("decorative_leaves", FeatureGroup.IdentifierType.SUFFIX)
                    .create();

    private static BlockBehaviour.Properties woodProperties(BlockBehaviour.Properties properties) {
        return properties.strength(2.0F, 3.0F).sound(SoundType.WOOD);
    }

    private static <B extends Block> FeatureBlockGroup.ItemFactory<B, VanillaWoodType> woodItem(Function<VanillaWoodType, String> vanillaId) {
        return (block, type, properties) -> new ItemBlockWood(block, properties, vanillaItem(vanillaId.apply(type)));
    }

    private static Item vanillaItem(String id) {
        return BuiltInRegistries.ITEM.get(Identifier.withDefaultNamespace(id)).orElseThrow().value();
    }

    private static <B extends Block & com.leon1236.reforestry.arboriculture.IWoodTyped> FeatureBlockGroup<B, ForestryWoodType> woodGroup(
            BiFunction<ForestryWoodType, BlockBehaviour.Properties, B> constructor, WoodBlockKind kind, boolean fireproof) {
        FeatureBlockGroup<B, ForestryWoodType> group = registerWood(REGISTRY.blockGroup(constructor, ForestryWoodType.VALUES)
                .item(ItemBlockForestryWood::new)
                .identifier((fireproof ? "fireproof_" : "") + kind.getSerializedName(), FeatureGroup.IdentifierType.SUFFIX)
                .create());
        if (!fireproof) {
            int burnOdds = kind == WoodBlockKind.LOG || kind == WoodBlockKind.STRIPPED_LOG
                    || kind == WoodBlockKind.WOOD || kind == WoodBlockKind.STRIPPED_WOOD ? 5 : 20;
            for (FeatureBlock<B> feature : group.getAll().values()) {
                net.fabricmc.fabric.api.registry.FlammableBlockRegistry.getDefaultInstance().add(feature.block(), 5, burnOdds);
            }
        }
        return group;
    }

    private static <B extends Block & com.leon1236.reforestry.arboriculture.IWoodTyped> FeatureBlockGroup<B, ForestryWoodType> registerWood(
            FeatureBlockGroup<B, ForestryWoodType> group) {
        for (FeatureBlock<B> feature : group.getAll().values()) {
            WoodAccess.INSTANCE.register(feature.block());
        }
        return group;
    }

    public static void init() {
        for (VanillaWoodType type : VanillaWoodType.VALUES) {
            WoodAccess.INSTANCE.register(type, WoodBlockKind.LOG, true, FIREPROOF_LOG.get(type).block().defaultBlockState());
            WoodAccess.INSTANCE.register(type, WoodBlockKind.LOG, false, vanillaLogBlock(type).defaultBlockState());
        }
        for (FeatureBlock<?> feature : LEAVES_DEFAULT.getAll().values()) {
            net.fabricmc.fabric.api.registry.FlammableBlockRegistry.getDefaultInstance().add(feature.block(), 30, 60);
        }
        for (FeatureBlock<?> feature : LEAVES_DEFAULT_FRUIT.getAll().values()) {
            net.fabricmc.fabric.api.registry.FlammableBlockRegistry.getDefaultInstance().add(feature.block(), 30, 60);
        }
        for (FeatureBlock<?> feature : LEAVES_DECORATIVE.getAll().values()) {
            net.fabricmc.fabric.api.registry.FlammableBlockRegistry.getDefaultInstance().add(feature.block(), 30, 60);
        }
        net.fabricmc.fabric.api.registry.FlammableBlockRegistry.getDefaultInstance().add(LEAVES.block(), 30, 60);
    }

    private static Block vanillaLogBlock(VanillaWoodType type) {
        return switch (type) {
            case OAK -> Blocks.OAK_LOG;
            case SPRUCE -> Blocks.SPRUCE_LOG;
            case BIRCH -> Blocks.BIRCH_LOG;
            case JUNGLE -> Blocks.JUNGLE_LOG;
            case ACACIA -> Blocks.ACACIA_LOG;
            case DARK_OAK -> Blocks.DARK_OAK_LOG;
            case CHERRY -> Blocks.CHERRY_LOG;
            case MANGROVE -> Blocks.MANGROVE_LOG;
            case PALE_OAK -> Blocks.PALE_OAK_LOG;
        };
    }
}
