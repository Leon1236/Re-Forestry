package com.leon1236.reforestry.arboriculture.blocks;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public class BlockDefaultLeavesFruit extends BlockAbstractLeaves {
    private final ForestryLeafType type;
    private final MapCodec<BlockDefaultLeavesFruit> codec;

    public BlockDefaultLeavesFruit(ForestryLeafType type, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = type;
        this.codec = simpleCodec(props -> new BlockDefaultLeavesFruit(type, props));
    }

    @Override
    public MapCodec<BlockDefaultLeavesFruit> codec() {
        return codec;
    }

    public Identifier getSpeciesId() {
        return this.type.getSpeciesId();
    }

    public ForestryLeafType getType() {
        return this.type;
    }

    @Override
    protected IGenome getGenome(BlockGetter level, BlockPos pos) {
        return this.type.getGenome();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        IGenome genome = getGenome(level, pos);
        if (genome == null) {
            return InteractionResult.FAIL;
        }
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
        List<ItemStack> products = fruit.getFruits(genome, level, fruit.getRipeningPeriod());
        level.setBlock(pos, ArboricultureBlocks.LEAVES_DEFAULT.get(this.type).block().defaultBlockState()
                .setValue(LeavesBlock.PERSISTENT, state.getValue(LeavesBlock.PERSISTENT))
                .setValue(LeavesBlock.DISTANCE, state.getValue(LeavesBlock.DISTANCE)), Block.UPDATE_CLIENTS);
        for (ItemStack product : products) {
            player.getInventory().placeItemBackInInventory(product);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void getLeafDrop(List<ItemStack> drops, Level level, @Nullable BlockPos pos,
            @Nullable Player player, float saplingModifier, LootParams.Builder context) {
        IGenome genome = this.type.getGenome();
        if (genome == null || level == null) {
            return;
        }
        RandomSource random = level.getRandom();
        float saplingChance = genome.getActiveAllele(TreeChromosomes.SAPLINGS).value() * saplingModifier;
        if (random.nextFloat() < Math.min(1.0f, saplingChance)) {
            ItemStack sapling = new ItemStack(ArboricultureItems.SAPLING.item());
            sapling.set(ArboricultureDataComponents.TREE_GENOME.type(), genome);
            drops.add(sapling);
        }
        IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
        if (fruit.isFruitLeaf()) {
            drops.addAll(fruit.getFruits(genome, level, Integer.MAX_VALUE));
        }
    }
}
