package com.leon1236.reforestry.apiculture.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.blocks.BlockAlveary;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;
import com.leon1236.reforestry.apiculture.blocks.BlockBeeHive;
import com.leon1236.reforestry.apiculture.blocks.BlockBeeHousing;
import com.leon1236.reforestry.apiculture.blocks.BlockHiveType;
import com.leon1236.reforestry.apiculture.blocks.BlockHoneyComb;
import com.leon1236.reforestry.apiculture.blocks.BlockWax;
import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.apiculture.items.ItemBlockAlveary;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ApicultureBlocks {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("apiculture"));

    public static final FeatureBlock<BlockBeeHousing> APIARY = REGISTRY.block("apiary",
            properties -> new BlockBeeHousing(housingProperties(properties), () -> ApicultureTiles.APIARY.type()),
            BlockItem::new);

    public static final FeatureBlock<BlockBeeHousing> BEE_HOUSE = REGISTRY.block("bee_house",
            properties -> new BlockBeeHousing(housingProperties(properties), () -> ApicultureTiles.BEE_HOUSE.type()),
            BlockItem::new);

    public static final FeatureBlockGroup<BlockBeeHive, BlockHiveType> BEEHIVE =
            REGISTRY.blockGroup((type, properties) -> new BlockBeeHive(type, hiveProperties(properties)),
                            BlockHiveType.values())
                    .item(BlockItem::new)
                    .identifier("beehive")
                    .create();

    public static final FeatureBlockGroup<BlockAlveary, BlockAlvearyType> ALVEARY =
            REGISTRY.blockGroup((type, properties) -> new BlockAlveary(type, alvearyProperties(properties)),
                            BlockAlvearyType.VALUES)
                    .item(ItemBlockAlveary::new)
                    .identifier("alveary")
                    .create();

    public static final FeatureBlockGroup<BlockHoneyComb, EnumHoneyComb> BEE_COMB =
            REGISTRY.blockGroup((type, properties) -> new BlockHoneyComb(type, combProperties(properties)), EnumHoneyComb.VALUES)
                    .item(BlockItem::new)
                    .identifier("block_bee_comb")
                    .create();

    public static final FeatureBlock<BlockWax> WAX_BLOCK = REGISTRY.block("wax_block",
            properties -> new BlockWax(waxProperties("wax_block", MapColor.COLOR_YELLOW, true)),
            BlockItem::new);

    public static final FeatureBlock<BlockWax> REFRACTORY_WAX_BLOCK = REGISTRY.block("wax_block_refractory",
            properties -> new BlockWax(waxProperties("wax_block_refractory", MapColor.COLOR_RED, false)),
            BlockItem::new);

    private static BlockBehaviour.Properties housingProperties(BlockBehaviour.Properties properties) {
        return properties.strength(2.5F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties alvearyProperties(BlockBehaviour.Properties properties) {
        return properties.strength(1.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties hiveProperties(BlockBehaviour.Properties properties) {
        return properties.sound(SoundType.WOOD).lightLevel(state -> 7).strength(2.5F);
    }

    private static BlockBehaviour.Properties combProperties(BlockBehaviour.Properties properties) {
        return properties.sound(SoundType.CORAL_BLOCK).strength(1F);
    }

    private static ResourceKey<Block> key(String name) {
        return ResourceKey.create(Registries.BLOCK, ReForestry.id(name));
    }

    private static BlockBehaviour.Properties waxProperties(String name, MapColor color, boolean ignitedByLava) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.HONEYCOMB_BLOCK)
                .sound(SoundType.HONEY_BLOCK)
                .mapColor(color)
                .setId(key(name));
        if (ignitedByLava) {
            properties = properties.ignitedByLava();
        }
        return properties;
    }

    public static void init() {
        for (var feature : BEEHIVE.getAll().values()) {
            FlammableBlockRegistry.getDefaultInstance().add(feature.block(), 5, 5);
        }
        FlammableBlockRegistry.getDefaultInstance().add(WAX_BLOCK.block(), 45, 45);
    }
}
