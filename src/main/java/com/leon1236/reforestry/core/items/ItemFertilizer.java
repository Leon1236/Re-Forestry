package com.leon1236.reforestry.core.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

public class ItemFertilizer extends Item {
    public ItemFertilizer(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        InteractionHand hand = context.getHand();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getClickedFace();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        ItemStack heldItem = player.getItemInHand(hand);
        if (!player.mayUseItemAt(pos.relative(facing), facing, heldItem)) {
            return InteractionResult.FAIL;
        }

        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof BonemealableBlock growable)
                || !growable.isValidBonemealTarget(level, pos, state)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            if (growable.isBonemealSuccess(level, serverLevel.getRandom(), pos, state)) {
                growable.performBonemeal(serverLevel, serverLevel.getRandom(), pos, state);
            }
            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
            }
            level.levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, pos, 0);
        }

        return InteractionResult.SUCCESS;
    }
}
