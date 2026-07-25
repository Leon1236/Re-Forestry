package com.leon1236.reforestry.arboriculture.blocks;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootParams;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public class BlockDefaultLeaves extends BlockAbstractLeaves {
    private final ForestryLeafType type;
    private final MapCodec<BlockDefaultLeaves> codec;

    public BlockDefaultLeaves(ForestryLeafType type, BlockBehaviour.Properties properties) {
        super(properties);
        this.type = type;
        this.codec = simpleCodec(props -> new BlockDefaultLeaves(type, props));
    }

    @Override
    public MapCodec<BlockDefaultLeaves> codec() {
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
    }
}
