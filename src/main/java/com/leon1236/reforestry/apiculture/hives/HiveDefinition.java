package com.leon1236.reforestry.apiculture.hives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.ClimateHelper;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.blocks.BlockHiveType;
import com.leon1236.reforestry.apiculture.features.ApicultureBlocks;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;

public enum HiveDefinition implements IHiveDefinition {
    FOREST(ApicultureBlocks.BEEHIVE.get(BlockHiveType.FOREST).block().defaultBlockState(), 6.0f,
            ReForestry.id("bee_forest"), HiveGenTree.INSTANCE) {
        @Override
        public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
            postGenFlowers(level, rand, pos, FLOWER_STATES);
        }

        @Override
        public boolean isGoodBiome(Holder<Biome> biome) {
            return super.isGoodBiome(biome) && !biome.is(ConventionalBiomeTags.IS_SNOWY);
        }
    },
    MEADOWS(ApicultureBlocks.BEEHIVE.get(BlockHiveType.MEADOWS).block().defaultBlockState(), 1.0f,
            ReForestry.id("bee_meadows"), new HiveGenGround(BlockTags.DIRT)) {
        @Override
        public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
            postGenFlowers(level, rand, pos, FLOWER_STATES);
        }

        @Override
        public boolean isGoodBiome(Holder<Biome> biome) {
            return super.isGoodBiome(biome) && !biome.is(BiomeTags.IS_FOREST);
        }
    },
    DESERT(ApicultureBlocks.BEEHIVE.get(BlockHiveType.DESERT).block().defaultBlockState(), 1.0f,
            ReForestry.id("bee_modest"), new HiveGenGround(ReforestryBiomeTags.Blocks.MODEST_BEE_GROUND)) {
        @Override
        public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
            postGenFlowers(level, rand, pos, CACTUS_STATES);
        }
    },
    JUNGLE(ApicultureBlocks.BEEHIVE.get(BlockHiveType.JUNGLE).block().defaultBlockState(), 6.0f,
            ReForestry.id("bee_tropical"), HiveGenTree.INSTANCE),
    END(ApicultureBlocks.BEEHIVE.get(BlockHiveType.END).block().defaultBlockState(), 0.25f,
            ReForestry.id("bee_ended"), new HiveGenGround(ReforestryBiomeTags.Blocks.ENDED_BEE_GROUND)) {
        @Override
        public boolean isGoodBiome(Holder<Biome> biome) {
            return biome.is(BiomeTags.IS_END);
        }
    },
    SNOW(ApicultureBlocks.BEEHIVE.get(BlockHiveType.SNOW).block().defaultBlockState(), 2.0f,
            ReForestry.id("bee_wintry"), new HiveGenGround(ReforestryBiomeTags.Blocks.WINTRY_BEE_GROUND)) {
        @Override
        public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
            BlockPos posAbove = pos.above();
            if (level.isEmptyBlock(posAbove)) {
                level.setBlock(posAbove, Blocks.SNOW.defaultBlockState(), Block.UPDATE_CLIENTS);
            }
            postGenFlowers(level, rand, pos, FLOWER_STATES);
        }
    },
    SWAMP(ApicultureBlocks.BEEHIVE.get(BlockHiveType.SWAMP).block().defaultBlockState(), 2.0f,
            ReForestry.id("bee_marshy"), new HiveGenGround(BlockTags.DIRT)) {
        @Override
        public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
            postGenFlowers(level, rand, pos, MUSHROOM_STATES);
        }

        @Override
        public boolean isGoodBiome(Holder<Biome> biome) {
            return super.isGoodBiome(biome) && !biome.is(ConventionalBiomeTags.IS_SNOWY);
        }
    },
    SAVANNA(ApicultureBlocks.BEEHIVE.get(BlockHiveType.SAVANNA).block().defaultBlockState(), 1.0f,
            ReForestry.id("bee_savanna"), new HiveGenGround(BlockTags.DIRT)),
    LUSH(ApicultureBlocks.BEEHIVE.get(BlockHiveType.LUSH).block().defaultBlockState(), 2.0f,
            ReForestry.id("bee_lush"),
            new HiveGenCaveCeiling(ReforestryBiomeTags.Blocks.LUSH_BEE_CEILING,
                    ReforestryBiomeTags.Blocks.CAVE_EXTRA_REPLACEABLES)) {
        @Override
        public boolean isGoodBiome(Holder<Biome> biome) {
            return super.isGoodBiome(biome) && biome.is(ConventionalBiomeTags.IS_CAVE);
        }

        @Override
        public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
            if (level.getBlockState(pos.below()).canBeReplaced()) {
                level.setBlock(pos.below(),
                        Blocks.CAVE_VINES.defaultBlockState().setValue(BlockStateProperties.BERRIES, rand.nextFloat() < 0.11F),
                        Block.UPDATE_CLIENTS);
            }
        }
    },
    AQUATIC(ApicultureBlocks.BEEHIVE.get(BlockHiveType.AQUATIC).block().defaultBlockState(), 1.0f,
            ReForestry.id("bee_aquatic"), new HiveGenOcean(BlockTags.SAND)) {
        @Override
        public boolean isGoodBiome(Holder<Biome> biome) {
            return biome.is(Biomes.WARM_OCEAN);
        }

        @Override
        public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
            Block[] coralFans = {
                    Blocks.FIRE_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN,
                    Blocks.HORN_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN
            };
            Block[] coralPlants = {
                    Blocks.FIRE_CORAL_FAN, Blocks.BRAIN_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN,
                    Blocks.HORN_CORAL_FAN, Blocks.TUBE_CORAL_FAN
            };
            for (Direction direction : Direction.values()) {
                BlockPos pos2 = pos.relative(direction);
                if (direction.getAxis().isHorizontal() && level.getBlockState(pos2).is(Blocks.WATER)) {
                    level.setBlock(pos2,
                            coralFans[rand.nextInt(5)].defaultBlockState()
                                    .setValue(HorizontalDirectionalBlock.FACING, direction),
                            Block.UPDATE_CLIENTS);
                }
                if (level.getBlockState(pos.above()).is(Blocks.WATER)) {
                    level.setBlock(pos.above(), coralPlants[rand.nextInt(5)].defaultBlockState(), Block.UPDATE_CLIENTS);
                }
            }
        }
    },
    NETHER(ApicultureBlocks.BEEHIVE.get(BlockHiveType.NETHER).block().defaultBlockState(), 4.0f,
            ReForestry.id("bee_embittered"),
            new HiveGenCaveCeiling(BlockTags.WART_BLOCKS, ReforestryBiomeTags.Blocks.NETHER_EXTRA_REPLACEABLES)) {
        @Override
        public boolean isGoodBiome(Holder<Biome> biome) {
            return biome.is(BiomeTags.IS_NETHER);
        }
    };

    private static final IHiveGen FLOWER_GROUND = new HiveGenGround(ReforestryBiomeTags.Blocks.PLANTABLE_FLOWERS_GROUND);
    private static final List<BlockState> FLOWER_STATES = new ArrayList<>();
    private static final List<BlockState> MUSHROOM_STATES = new ArrayList<>();
    private static final List<BlockState> CACTUS_STATES = Collections.singletonList(Blocks.CACTUS.defaultBlockState());

    static {
        FLOWER_STATES.addAll(Blocks.POPPY.getStateDefinition().getPossibleStates());
        FLOWER_STATES.addAll(Blocks.DANDELION.getStateDefinition().getPossibleStates());
        MUSHROOM_STATES.add(Blocks.RED_MUSHROOM.defaultBlockState());
        MUSHROOM_STATES.add(Blocks.BROWN_MUSHROOM.defaultBlockState());
    }

    private final BlockState blockState;
    private final float genChance;
    private final Identifier speciesId;
    private final IHiveGen hiveGen;

    HiveDefinition(BlockState hiveState, float genChance, Identifier beeTemplate, IHiveGen hiveGen) {
        this.blockState = hiveState;
        this.genChance = genChance;
        this.speciesId = beeTemplate;
        this.hiveGen = hiveGen;
    }

    @Override
    public IHiveGen getHiveGen() {
        return hiveGen;
    }

    @Override
    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    public boolean isGoodBiome(Holder<Biome> biome) {
        return !biome.is(BiomeTags.IS_NETHER);
    }

    @Override
    public boolean isGoodHumidity(HumidityType humidity) {
        IBeeSpecies species = ApicultureGenetics.getSpecies(speciesId);
        IGenome genome = ApicultureGenetics.getDefaultGenome(speciesId);
        HumidityType idealHumidity = species.getHumidity();
        ToleranceType humidityTolerance = genome.getActiveAllele(BeeChromosomes.HUMIDITY_TOLERANCE).value();
        return ClimateHelper.isWithinLimits(humidity, idealHumidity, humidityTolerance);
    }

    @Override
    public boolean isGoodTemperature(TemperatureType temperature) {
        IBeeSpecies species = ApicultureGenetics.getSpecies(speciesId);
        IGenome genome = ApicultureGenetics.getDefaultGenome(speciesId);
        TemperatureType idealTemperature = species.getTemperature();
        ToleranceType temperatureTolerance = genome.getActiveAllele(BeeChromosomes.TEMPERATURE_TOLERANCE).value();
        return ClimateHelper.isWithinLimits(temperature, idealTemperature, temperatureTolerance);
    }

    @Override
    public float getGenChance() {
        return genChance;
    }

    @Override
    public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
    }

    protected static void postGenFlowers(WorldGenLevel world, RandomSource rand, BlockPos hivePos,
            List<BlockState> flowerStates) {
        int plantedCount = 0;
        for (int i = 0; i < 10; i++) {
            int xOffset = rand.nextInt(8) - 4;
            int zOffset = rand.nextInt(8) - 4;
            BlockPos blockPos = hivePos.offset(xOffset, 0, zOffset);
            ChunkPos chunkPos = ChunkPos.containing(blockPos);
            if ((xOffset == 0 && zOffset == 0) || !world.hasChunk(chunkPos.x(), chunkPos.z())) {
                continue;
            }

            blockPos = FLOWER_GROUND.getPosForHive(world, blockPos.getX(), blockPos.getZ());
            if (blockPos == null) {
                continue;
            }

            BlockState state = flowerStates.get(rand.nextInt(flowerStates.size()));
            if (!state.canSurvive(world, blockPos)) {
                continue;
            }

            world.setBlock(blockPos, state, Block.UPDATE_CLIENTS);
            plantedCount++;
            if (plantedCount >= 3) {
                break;
            }
        }
    }
}
