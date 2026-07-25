package com.leon1236.reforestry.arboriculture.items;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.entities.ForestryBoat;
import com.leon1236.reforestry.arboriculture.entities.ForestryChestBoat;
import com.leon1236.reforestry.arboriculture.entities.IForestryBoat;

public class ItemForestryBoat extends Item {
    private final ForestryWoodType type;
    private final boolean hasChest;

    public ItemForestryBoat(ForestryWoodType type, boolean hasChest, Item.Properties properties) {
        super(properties.stacksTo(1));
        this.type = type;
        this.hasChest = hasChest;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        String grammarKey = this.hasChest ? "for.chest_boat.grammar" : "for.boat.grammar";
        return Component.translatable(grammarKey, Component.translatable("for.trees.woodType." + this.type.getSerializedName()));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        }

        Vec3 viewVector = player.getViewVector(1.0F);
        List<Entity> entities = level.getEntities(player, player.getBoundingBox().expandTowards(viewVector.scale(5.0)).inflate(1.0), EntitySelector.CAN_BE_PICKED);
        if (!entities.isEmpty()) {
            Vec3 from = player.getEyePosition();
            for (Entity entity : entities) {
                AABB bb = entity.getBoundingBox().inflate(entity.getPickRadius());
                if (bb.contains(from)) {
                    return InteractionResult.PASS;
                }
            }
        }

        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        }

        AbstractBoat boat = createBoat(level, hitResult.getLocation());
        boat.setYRot(player.getYRot());
        if (!level.noCollision(boat, boat.getBoundingBox())) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide()) {
            level.addFreshEntity(boat);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
            itemStack.consume(1, player);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.SUCCESS;
    }

    private AbstractBoat createBoat(Level level, Vec3 location) {
        AbstractBoat boat = this.hasChest
                ? new ForestryChestBoat(level, location.x, location.y, location.z)
                : new ForestryBoat(level, location.x, location.y, location.z);
        ((IForestryBoat) boat).setWoodType(this.type);
        return boat;
    }
}
