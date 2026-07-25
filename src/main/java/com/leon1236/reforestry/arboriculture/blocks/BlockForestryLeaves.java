package com.leon1236.reforestry.arboriculture.blocks;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.api.arboriculture.IToolGrafter;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;

public class BlockForestryLeaves extends LeavesBlock implements EntityBlock, BonemealableBlock {
    private static final MapCodec<BlockForestryLeaves> CODEC = simpleCodec(BlockForestryLeaves::new);

    public BlockForestryLeaves(Properties properties) {
        super(0.1f, properties);
    }

    @Override
    public MapCodec<? extends BlockForestryLeaves> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileLeaves(pos, state);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (level.getBlockEntity(pos) instanceof TileLeaves leaves && !leaves.isRemoved() && random.nextFloat() <= 0.1f) {
            leaves.onBlockTick(level, pos, state, random);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof TileLeaves leaves && leaves.hasFruit() && leaves.getRipeness() >= 0.9f) {
            for (ItemStack fruit : leaves.pickFruit()) {
                player.getInventory().placeItemBackInInventory(fruit);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockEntity(pos) instanceof TileLeaves leaves && leaves.hasFruit() && leaves.getRipeness() < 1.0f;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof TileLeaves leaves) {
            leaves.onBlockTick(level, pos, state, random);
        }
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (!(blockEntity instanceof TileLeaves leaves)) {
            return List.of();
        }
        IGenome genome = leaves.getGenome();
        Level level = leaves.getLevel();
        if (genome == null || level == null) {
            return List.of();
        }

        List<ItemStack> drops = new ArrayList<>();
        if (leaves.hasFruit()) {
            drops.addAll(leaves.pickFruit());
        }

        RandomSource random = level.getRandom();
        float saplingChance = genome.getActiveAllele(TreeChromosomes.SAPLINGS).value();
        saplingChance *= getSaplingModifier(params, level, leaves.getBlockPos());
        if (random.nextFloat() < Math.min(1.0f, saplingChance)) {
            ItemStack sapling = new ItemStack(ArboricultureItems.SAPLING.item());
            sapling.set(ArboricultureDataComponents.TREE_GENOME.type(), leaves.resolveSaplingGenome(random));
            drops.add(sapling);
        }

        return drops;
    }

    private static float getSaplingModifier(LootParams.Builder params, Level level, BlockPos pos) {
        ItemInstance toolInstance = params.getOptionalParameter(LootContextParams.TOOL);
        if (!(toolInstance instanceof ItemStack tool) || !(tool.getItem() instanceof IToolGrafter grafter)) {
            return 1.0f;
        }
        if (!(params.getOptionalParameter(LootContextParams.THIS_ENTITY) instanceof Player player)) {
            return 1.0f;
        }
        return grafter.getSaplingModifier(tool, level, player, pos);
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
        ColorParticleOption particle = ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, level.getClientLeafTintColor(pos));
        ParticleUtils.spawnParticleBelow(level, pos, random, particle);
    }
}
