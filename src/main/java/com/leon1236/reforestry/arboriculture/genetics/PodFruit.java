package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.core.ReforestryBiomeTags;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.blocks.BlockFruitPod;
import com.leon1236.reforestry.arboriculture.blocks.ForestryPodType;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.tiles.TileFruitPod;
import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.core.utils.BlockUtil;

public class PodFruit extends Fruit {
    private final ForestryPodType type;

    public PodFruit(Identifier id, boolean dominant, ForestryPodType type, List<Product> products) {
        super(id, dominant, 2, products);
        this.type = type;
    }

    public ForestryPodType getType() {
        return type;
    }

    @Override
    public boolean requiresFruitBlocks() {
        return true;
    }

    @Override
    public boolean trySpawnFruitBlock(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos) {
        if (rand.nextFloat() > getFruitChance(genome)) {
            return false;
        }

        if (this.type == ForestryPodType.COCOA) {
            return BlockUtil.tryPlantCocoaPod(level, pos);
        }

        IFruit activeAllele = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
        float yield = genome.getActiveAllele(TreeChromosomes.YIELD).value();
        return setFruitBlock(level, genome, activeAllele, yield, pos);
    }

    @Override
    public TagKey<Block> getLogTag() {
        return switch (this.type) {
            case DATES -> ReforestryBiomeTags.Blocks.PALM_LOGS;
            case PAPAYA -> ReforestryBiomeTags.Blocks.PAPAYA_LOGS;
            case COCONUT -> ReforestryBiomeTags.Blocks.COCONUT_LOGS;
            default -> BlockTags.JUNGLE_LOGS;
        };
    }

    public static boolean setFruitBlock(LevelAccessor level, IGenome genome, IFruit fruit, float yield, BlockPos pos) {
        Direction facing = BlockUtil.getValidPodFacing(level, pos, fruit.getLogTag());
        if (facing == null || !(fruit instanceof PodFruit podFruit)
                || !ArboricultureBlocks.PODS.getAll().containsKey(podFruit.getType())) {
            return false;
        }

        BlockFruitPod fruitPod = ArboricultureBlocks.PODS.get(podFruit.getType()).block();
        BlockState state = fruitPod.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, facing);
        boolean placed = level.setBlock(pos, state, 18);
        if (!placed) {
            return false;
        }

        if (level.getBlockState(pos).getBlock() != fruitPod) {
            return false;
        }

        TileFruitPod pod = TileUtil.getTile(level, pos, TileFruitPod.class);
        if (pod == null) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 18);
            return false;
        }

        pod.setProperties(genome, fruit, yield);
        return true;
    }
}
