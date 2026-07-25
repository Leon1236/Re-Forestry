package com.leon1236.reforestry.apiculture.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.hives.IHiveTile;

public class ItemSmoker extends Item {
    public ItemSmoker(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int ticksRemaining) {
        addSmoke(level, entity, (ticksRemaining % 5) + 1);
    }

    private static HumanoidArm getHandSide(Entity entity) {
        if (entity instanceof LivingEntity living) {
            InteractionHand activeHand = living.getUsedItemHand();
            HumanoidArm handSide = living.getMainArm();
            if (activeHand == InteractionHand.OFF_HAND) {
                handSide = handSide.getOpposite();
            }
            return handSide;
        }
        return HumanoidArm.RIGHT;
    }

    private static void addSmoke(Level level, Entity entity, int distance) {
        if (distance <= 0) {
            return;
        }
        Vec3 look = entity.getLookAngle();
        HumanoidArm handSide = getHandSide(entity);
        Vec3 handOffset = handSide == HumanoidArm.RIGHT
                ? look.cross(new Vec3(0, 1, 0))
                : look.cross(new Vec3(0, -1, 0));
        Vec3 lookDistance = new Vec3(look.x * distance, look.y * distance, look.z * distance);
        Vec3 scaledOffset = handOffset.scale(1.0 / distance);
        Vec3 smokePos = lookDistance.add(entity.position()).add(scaledOffset);

        if (level.isClientSide()) {
            level.addParticle(ParticleTypes.SMOKE, smokePos.x, smokePos.y + 1, smokePos.z, 0, 0.01, 0);
        }

        BlockPos blockPos = BlockPos.containing(smokePos.x, smokePos.y + 1, smokePos.z);
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof IHiveTile hive) {
            hive.calmBees();
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if (blockEntity instanceof IHiveTile hive) {
            hive.calmBees();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 32;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.BOW;
    }
}
