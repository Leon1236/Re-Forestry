package com.leon1236.reforestry.apiculture.features;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import team.reborn.energy.api.EnergyStorage;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyFan;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyHeater;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyHygroregulator;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyPlain;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearySieve;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyStabiliser;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearySwarmer;
import com.leon1236.reforestry.apiculture.tiles.TileBeeHousing;
import com.leon1236.reforestry.apiculture.tiles.TileHive;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ApicultureTiles {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("apiculture"));

    public static final FeatureBlockEntityType<TileBeeHousing> APIARY = REGISTRY.blockEntityType("apiary",
            (pos, state) -> new TileBeeHousing(ApicultureTiles.APIARY.type(), pos, state, "apiary", true),
            ApicultureBlocks.APIARY.block());

    public static final FeatureBlockEntityType<TileBeeHousing> BEE_HOUSE = REGISTRY.blockEntityType("bee_house",
            (pos, state) -> new TileBeeHousing(ApicultureTiles.BEE_HOUSE.type(), pos, state, "bee_house", false),
            ApicultureBlocks.BEE_HOUSE.block());

    public static final FeatureBlockEntityType<TileHive> HIVE = REGISTRY.blockEntityType("hive",
            TileHive::new,
            ApicultureBlocks.BEEHIVE.getAll().values().stream().map(FeatureBlock::block).toArray(Block[]::new));

    public static final FeatureBlockEntityType<TileAlvearyPlain> ALVEARY = REGISTRY.blockEntityType("alveary",
            TileAlvearyPlain::new, ApicultureBlocks.ALVEARY.get(BlockAlvearyType.PLAIN).block());

    public static final FeatureBlockEntityType<TileAlvearySwarmer> ALVEARY_SWARMER =
            REGISTRY.blockEntityType("alveary_swarmer", TileAlvearySwarmer::new,
                    ApicultureBlocks.ALVEARY.get(BlockAlvearyType.SWARMER).block());

    public static final FeatureBlockEntityType<TileAlvearyFan> ALVEARY_FAN =
            REGISTRY.blockEntityType("alveary_fan", TileAlvearyFan::new,
                    ApicultureBlocks.ALVEARY.get(BlockAlvearyType.FAN).block());

    public static final FeatureBlockEntityType<TileAlvearyHeater> ALVEARY_HEATER =
            REGISTRY.blockEntityType("alveary_heater", TileAlvearyHeater::new,
                    ApicultureBlocks.ALVEARY.get(BlockAlvearyType.HEATER).block());

    public static final FeatureBlockEntityType<TileAlvearyHygroregulator> ALVEARY_HYGRO =
            REGISTRY.blockEntityType("alveary_hygro", TileAlvearyHygroregulator::new,
                    ApicultureBlocks.ALVEARY.get(BlockAlvearyType.HYGRO).block());

    public static final FeatureBlockEntityType<TileAlvearyStabiliser> ALVEARY_STABILISER =
            REGISTRY.blockEntityType("alveary_stabiliser", TileAlvearyStabiliser::new,
                    ApicultureBlocks.ALVEARY.get(BlockAlvearyType.STABILISER).block());

    public static final FeatureBlockEntityType<TileAlvearySieve> ALVEARY_SIEVE =
            REGISTRY.blockEntityType("alveary_sieve", TileAlvearySieve::new,
                    ApicultureBlocks.ALVEARY.get(BlockAlvearyType.SIEVE).block());

    public static BlockEntityType<?> alvearyType(BlockAlvearyType type) {
        return switch (type) {
            case PLAIN -> ALVEARY.type();
            case SWARMER -> ALVEARY_SWARMER.type();
            case FAN -> ALVEARY_FAN.type();
            case HEATER -> ALVEARY_HEATER.type();
            case HYGRO -> ALVEARY_HYGRO.type();
            case STABILISER -> ALVEARY_STABILISER.type();
            case SIEVE -> ALVEARY_SIEVE.type();
        };
    }

    public static void init() {
        EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getEnergyStorage(), ALVEARY_FAN.type());
        EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getEnergyStorage(), ALVEARY_HEATER.type());
        FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTank(), ALVEARY_HYGRO.type());
    }
}
