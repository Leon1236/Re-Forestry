package com.leon1236.reforestry.arboriculture.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;

import com.leon1236.reforestry.core.features.CoreItems;

public class BlockAsh extends Block {
    public static final IntegerProperty AMOUNT = IntegerProperty.create("amount", 0, 63);

    public BlockAsh(Properties properties) {
        super(properties.sound(SoundType.SAND).strength(0.6F).mapColor(MapColor.COLOR_LIGHT_GRAY)
                .pushReaction(PushReaction.DESTROY));
        registerDefaultState(getStateDefinition().any().setValue(AMOUNT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AMOUNT);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        int amount = state.getValue(AMOUNT);
        if (amount <= 0) {
            return List.of();
        }
        List<ItemStack> drops = new ArrayList<>(2);
        RandomSource random = params.getLevel().getRandom();
        int ashCount = 0;
        for (int i = 0; i < 2; i++) {
            if (random.nextFloat() < 1.0f / 3.0f) {
                ashCount++;
            }
        }
        if (ashCount > 0) {
            drops.add(new ItemStack(CoreItems.ASH.item(), ashCount));
        }
        drops.add(new ItemStack(Items.CHARCOAL, amount));
        return drops;
    }
}
