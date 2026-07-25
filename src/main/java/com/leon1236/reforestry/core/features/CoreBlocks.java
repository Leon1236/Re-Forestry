package com.leon1236.reforestry.core.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.BlockBogEarth;
import com.leon1236.reforestry.core.blocks.BlockHumus;
import com.leon1236.reforestry.core.blocks.BlockResourceStorage;
import com.leon1236.reforestry.core.blocks.EnumResourceType;
import com.leon1236.reforestry.core.energy.BlockCreativeEnergy;
import com.leon1236.reforestry.core.energy.BlockDebugPowered;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CoreBlocks {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    private static ResourceKey<Block> key(String name) {
        return ResourceKey.create(Registries.BLOCK, ReForestry.id(name));
    }

    public static final FeatureBlock<BlockBogEarth> BOG_EARTH = REGISTRY.block("bog_earth", BlockBogEarth::new, BlockItem::new);

    public static final FeatureBlock<Block> PEAT = REGISTRY.block("peat",
            properties -> new Block(properties.strength(0.5f).sound(SoundType.GRAVEL)), null);

    public static final FeatureBlock<BlockHumus> HUMUS = REGISTRY.block("humus", BlockHumus::new, BlockItem::new);

    public static final FeatureBlockGroup<BlockResourceStorage, EnumResourceType> RESOURCE_STORAGE =
            REGISTRY.blockGroup((type, properties) -> new BlockResourceStorage(type, properties.strength(3f, 5f)), EnumResourceType.values())
                    .item(BlockItem::new)
                    .identifier("resource_storage")
                    .create();

    public static final FeatureBlock<DropExperienceBlock> APATITE_ORE = REGISTRY.block("apatite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(0, 4),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE).setId(key("apatite_ore"))),
            BlockItem::new);

    public static final FeatureBlock<DropExperienceBlock> DEEPSLATE_APATITE_ORE = REGISTRY.block("deepslate_apatite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(0, 4),
                    BlockBehaviour.Properties.ofFullCopy(APATITE_ORE.block())
                            .mapColor(MapColor.DEEPSLATE).strength(4.5f, 3.0f).sound(SoundType.DEEPSLATE)
                            .setId(key("deepslate_apatite_ore"))),
            BlockItem::new);

    public static final FeatureBlock<Block> TIN_ORE = REGISTRY.block("tin_ore",
            properties -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE).setId(key("tin_ore"))),
            BlockItem::new);

    public static final FeatureBlock<Block> DEEPSLATE_TIN_ORE = REGISTRY.block("deepslate_tin_ore",
            properties -> new Block(BlockBehaviour.Properties.ofFullCopy(TIN_ORE.block())
                    .mapColor(MapColor.DEEPSLATE).strength(4.5f, 3.0f).sound(SoundType.DEEPSLATE)
                    .setId(key("deepslate_tin_ore"))),
            BlockItem::new);

    public static final FeatureBlock<Block> RAW_TIN_BLOCK = REGISTRY.block("raw_tin_block",
            properties -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK).setId(key("raw_tin_block"))),
            BlockItem::new);

    public static final FeatureBlock<BlockCreativeEnergy> CREATIVE_ENERGY = REGISTRY.block("debug_creative_energy",
            BlockCreativeEnergy::new, BlockItem::new);

    public static final FeatureBlock<BlockDebugPowered> DEBUG_POWERED = REGISTRY.block("debug_powered",
            BlockDebugPowered::new, BlockItem::new);

    public static void init() {
    }
}
