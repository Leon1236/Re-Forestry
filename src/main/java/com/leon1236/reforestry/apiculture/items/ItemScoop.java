package com.leon1236.reforestry.apiculture.items;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;

public class ItemScoop extends Item {
    public ItemScoop(Properties properties, int durability) {
        super(properties.durability(durability).enchantable(15));
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(ReforestryBiomeTags.Blocks.MINEABLE_SCOOP) || super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return state.is(ReforestryBiomeTags.Blocks.MINEABLE_SCOOP) ? 2.0f : 1.0f;
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide() && state.getDestroySpeed(level, pos) != 0.0f) {
            stack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
        }
        return true;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level level = target.level();
        if (!(target instanceof Bee)) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide()) {
            ItemStack drone = new ItemStack(ApicultureItems.BEE_DRONE.item());
            drone.set(ApicultureDataComponents.BEE_GENOME.type(),
                    ApicultureGenetics.getDefaultGenome(ReForestry.id("bee_vanilla")));
            level.addFreshEntity(new ItemEntity(level, target.getX(), target.getY(), target.getZ(), drone));
            level.playSound(null, target.blockPosition(), SoundEvents.BEE_HURT, SoundSource.PLAYERS, 1.0f, 1.0f);
            target.discard();
            stack.hurtAndBreak(1, player, hand);
        }
        return InteractionResult.SUCCESS;
    }
}
