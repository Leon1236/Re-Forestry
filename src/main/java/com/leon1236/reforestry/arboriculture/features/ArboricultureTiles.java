package com.leon1236.reforestry.arboriculture.features;

import java.util.stream.Stream;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;
import com.leon1236.reforestry.arboriculture.tiles.TileSapling;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ArboricultureTiles {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("arboriculture"));

    public static final FeatureBlockEntityType<SignBlockEntity> SIGN = REGISTRY.blockEntityType("sign",
            (pos, state) -> new SignBlockEntity(ArboricultureTiles.SIGN.type(), pos, state),
            Stream.concat(ArboricultureBlocks.SIGN.getAll().values().stream().map(feature -> (Block) feature.block()),
                            ArboricultureBlocks.WALL_SIGN.getAll().values().stream().map(feature -> (Block) feature.block()))
                    .toArray(Block[]::new));

    public static final FeatureBlockEntityType<TileSapling> SAPLING = REGISTRY.blockEntityType("sapling",
            TileSapling::new, ArboricultureBlocks.SAPLING.block());

    public static final FeatureBlockEntityType<TileLeaves> LEAVES = REGISTRY.blockEntityType("leaves",
            TileLeaves::new, ArboricultureBlocks.LEAVES.block());

    public static void init() {
        Stream.concat(ArboricultureBlocks.HANGING_SIGN.getAll().values().stream().map(feature -> (Block) feature.block()),
                        ArboricultureBlocks.WALL_HANGING_SIGN.getAll().values().stream().map(feature -> (Block) feature.block()))
                .forEach(block -> ((FabricBlockEntityType) BlockEntityTypes.HANGING_SIGN).addValidBlock(block));
    }
}
