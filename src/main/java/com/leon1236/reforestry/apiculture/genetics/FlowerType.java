package com.leon1236.reforestry.apiculture.genetics;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;

public enum FlowerType implements IFlowerType {
    VANILLA(ReforestryBiomeTags.Blocks.VANILLA_FLOWERS),
    NETHER(ReforestryBiomeTags.Blocks.NETHER_FLOWERS),
    CACTI(ReforestryBiomeTags.Blocks.CACTI_FLOWERS),
    MUSHROOMS(ReforestryBiomeTags.Blocks.MUSHROOMS_FLOWERS),
    END(ReforestryBiomeTags.Blocks.END_FLOWERS),
    JUNGLE(ReforestryBiomeTags.Blocks.JUNGLE_FLOWERS),
    SNOW(ReforestryBiomeTags.Blocks.SNOW_FLOWERS),
    WHEAT(ReforestryBiomeTags.Blocks.WHEAT_FLOWERS),
    GOURD(ReforestryBiomeTags.Blocks.GOURD_FLOWERS),
    CAVE(ReforestryBiomeTags.Blocks.CAVE_FLOWERS),
    PHOTOSYNTHESIS(null),
    ANCIENT(ReforestryBiomeTags.Blocks.ANCIENT_FLOWERS),
    SEA(ReforestryBiomeTags.Blocks.SEA_FLOWERS),
    CORAL(ReforestryBiomeTags.Blocks.CORAL_FLOWERS),
    SCULK(ReforestryBiomeTags.Blocks.SCULK_FLOWERS);

    private final Identifier id = ReForestry.id("flower_type_" + name().toLowerCase(Locale.ENGLISH));
    @Nullable
    private final TagKey<Block> acceptableFlowers;

    FlowerType(@Nullable TagKey<Block> acceptableFlowers) {
        this.acceptableFlowers = acceptableFlowers;
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public boolean isAcceptableFlower(Level level, BlockPos pos) {
        if (this == PHOTOSYNTHESIS) {
            return level.isBrightOutside() && level.getBrightness(LightLayer.SKY, pos) >= 15;
        }
        if (acceptableFlowers == null) {
            return false;
        }
        if (this == END && level.getBiome(pos).is(BiomeTags.IS_END)) {
            return true;
        }
        return level.getBlockState(pos).is(acceptableFlowers);
    }
}
