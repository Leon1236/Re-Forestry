package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.entities.ForestryBoat;
import com.leon1236.reforestry.arboriculture.entities.ForestryChestBoat;
import com.leon1236.reforestry.arboriculture.entities.IForestryBoat;

public class ForestryBoatDispenserBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
    private final ForestryWoodType type;
    private final boolean hasChest;

    public ForestryBoatDispenserBehavior(ForestryWoodType type, boolean hasChest) {
        this.type = type;
        this.hasChest = hasChest;
    }

    @Override
    public ItemStack execute(BlockSource source, ItemStack dispensed) {
        Direction direction = source.state().getValue(DispenserBlock.FACING);
        ServerLevel level = source.level();
        Vec3 center = source.center();
        double justOutsideDispenser = 0.5625 + 1.375F / 2.0;
        double spawnX = center.x() + direction.getStepX() * justOutsideDispenser;
        double spawnY = center.y() + direction.getStepY() * 1.125F;
        double spawnZ = center.z() + direction.getStepZ() * justOutsideDispenser;
        BlockPos frontPos = source.pos().relative(direction);
        double yOffset;
        if (level.getFluidState(frontPos).is(FluidTags.WATER)) {
            yOffset = 1.0;
        } else {
            if (!level.getBlockState(frontPos).isAir() || !level.getFluidState(frontPos.below()).is(FluidTags.WATER)) {
                return this.defaultDispenseItemBehavior.dispense(source, dispensed);
            }
            yOffset = 0.0;
        }

        AbstractBoat boat = this.hasChest
                ? new ForestryChestBoat(level, spawnX, spawnY + yOffset, spawnZ)
                : new ForestryBoat(level, spawnX, spawnY + yOffset, spawnZ);
        ((IForestryBoat) boat).setWoodType(this.type);
        boat.setYRot(direction.toYRot());
        level.addFreshEntity(boat);
        dispensed.shrink(1);
        return dispensed;
    }

    @Override
    protected void playSound(BlockSource source) {
        source.level().levelEvent(1000, source.pos(), 0);
    }
}
