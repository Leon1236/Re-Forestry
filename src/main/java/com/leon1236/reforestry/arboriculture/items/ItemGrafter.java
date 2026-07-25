package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IToolGrafter;

public class ItemGrafter extends Item implements IToolGrafter {
    public ItemGrafter(Properties properties, int maxDamage) {
        super(properties.durability(maxDamage));
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.getBlock() instanceof LeavesBlock || state.is(BlockTags.LEAVES) || super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return state.is(BlockTags.LEAVES) ? 4.0f : 1.0f;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide() && !state.is(BlockTags.FIRE)) {
            stack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
        }
        return state.is(BlockTags.LEAVES);
    }

    @Override
    public float getSaplingModifier(ItemStack stack, Level level, Player player, BlockPos pos) {
        return 100f;
    }
}
