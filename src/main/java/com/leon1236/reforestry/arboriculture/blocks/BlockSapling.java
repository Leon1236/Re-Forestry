package com.leon1236.reforestry.arboriculture.blocks;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.tiles.TileSapling;

public class BlockSapling extends Block implements BonemealableBlock, EntityBlock {
    private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public BlockSapling(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BlockTags.SUPPORTS_VEGETATION);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileSapling(pos, state);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() > 0.1f) {
            return;
        }
        if (level.getBlockEntity(pos) instanceof TileSapling sapling) {
            sapling.onBlockTick(level, pos, state, random);
        }
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        BlockEntity blockEntity = params.getParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof TileSapling sapling && sapling.getGenome() != null) {
            return Collections.singletonList(createStack(sapling.getGenome()));
        }
        return Collections.emptyList();
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        if (level.getBlockEntity(pos) instanceof TileSapling sapling && sapling.getGenome() != null) {
            return createStack(sapling.getGenome());
        }
        return ItemStack.EMPTY;
    }

    private static ItemStack createStack(IGenome genome) {
        ItemStack stack = new ItemStack(ArboricultureItems.SAPLING.item());
        stack.set(ArboricultureDataComponents.TREE_GENOME.type(), genome);
        return stack;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(net.minecraft.world.level.Level level, RandomSource random, BlockPos pos, BlockState state) {
        if (random.nextFloat() >= 0.45F) {
            return false;
        }
        return !(level.getBlockEntity(pos) instanceof TileSapling sapling) || sapling.canAcceptBoneMeal(random);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof TileSapling sapling) {
            sapling.tryGrow(random, true);
        }
    }
}
