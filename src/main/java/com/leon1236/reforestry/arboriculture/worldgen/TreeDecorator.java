package com.leon1236.reforestry.arboriculture.worldgen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.core.utils.BlockUtil;

public class TreeDecorator extends Feature<NoneFeatureConfiguration> {
    private static final Map<ResourceKey<Biome>, List<ITreeSpecies>> BIOME_CACHE = new HashMap<>();

    public TreeDecorator() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public static void clearBiomeCache() {
        BIOME_CACHE.clear();
    }

    @Nullable
    private static BlockPos getValidPos(WorldGenLevel world, int x, int z) {
        final BlockPos topPos = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, new BlockPos(x, 0, z));
        if (topPos.getY() == 0) {
            return null;
        }

        final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(topPos.getX(), topPos.getY(), topPos.getZ());

        BlockState blockState = world.getBlockState(pos);
        while (BlockUtil.canReplace(blockState, world, pos)) {
            pos.move(Direction.DOWN);
            if (pos.getY() <= 0) {
                return null;
            }
            blockState = world.getBlockState(pos);
        }

        if (blockState.is(BlockTags.SUPPORTS_VEGETATION)) {
            return pos.above();
        }
        return null;
    }

    private static synchronized void generateBiomeCache(WorldGenLevel level) {
        if (!BIOME_CACHE.isEmpty()) {
            return;
        }

        List<ITreeSpecies> allSpecies = List.copyOf(ArboricultureGenetics.getAllSpecies());
        IClimateManager manager = IForestryApi.INSTANCE.getClimateManager();

        level.registryAccess().lookupOrThrow(Registries.BIOME).listElements().forEach(biome -> {
            List<ITreeSpecies> trees = BIOME_CACHE.computeIfAbsent(biome.key(), k -> new ArrayList<>());
            TemperatureType temperature = manager.getTemperature(biome);
            HumidityType humidity = manager.getHumidity(biome);

            for (ITreeSpecies species : allSpecies) {
                if (species.getRarity() > 0.0f
                        && temperature == species.getTemperature()
                        && humidity == species.getHumidity()) {
                    trees.add(species);
                }
            }
        });
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin();

        double globalRarity = ForestryConfig.treesSpawnNaturally();
        if (globalRarity <= 0.0) {
            return false;
        }

        if (BIOME_CACHE.isEmpty()) {
            generateBiomeCache(level);
        }

        for (int tries = 0; tries < 4; tries++) {
            int x = pos.getX() + rand.nextInt(16);
            int z = pos.getZ() + rand.nextInt(16);

            Holder<Biome> biome = level.getBiome(pos);
            List<ITreeSpecies> trees = BIOME_CACHE.computeIfAbsent(
                    biome.unwrapKey().orElseThrow(),
                    k -> List.of());

            for (ITreeSpecies species : trees) {
                if (species.getRarity() * globalRarity >= rand.nextFloat()) {
                    BlockPos validPos = getValidPos(level, x, z);
                    if (validPos == null) {
                        continue;
                    }
                    if (TreeGenHelper.generateTree(species, null, level, context.random(), validPos)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
