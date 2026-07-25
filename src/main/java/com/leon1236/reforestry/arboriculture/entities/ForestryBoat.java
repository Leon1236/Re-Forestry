package com.leon1236.reforestry.arboriculture.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.features.ArboricultureEntities;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;

public class ForestryBoat extends Boat implements IForestryBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_WOOD_TYPE =
            SynchedEntityData.defineId(ForestryBoat.class, EntityDataSerializers.INT);

    private ForestryBoat(EntityType<? extends ForestryBoat> type, Level level, DropItem dropItem) {
        super(type, level, dropItem);
        dropItem.boat = this;
    }

    public ForestryBoat(EntityType<? extends ForestryBoat> type, Level level) {
        this(type, level, new DropItem(false));
    }

    public ForestryBoat(Level level, double x, double y, double z) {
        this(ArboricultureEntities.BOAT.entityType(), level);
        setInitialPos(x, y, z);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_ID_WOOD_TYPE, ForestryWoodType.LARCH.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putString("type", getWoodType().name());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        String typeName = input.getStringOr("type", ForestryWoodType.LARCH.name());
        try {
            setWoodType(ForestryWoodType.valueOf(typeName));
        } catch (IllegalArgumentException e) {
            setWoodType(ForestryWoodType.LARCH);
        }
    }

    @Override
    public void setWoodType(ForestryWoodType woodType) {
        this.entityData.set(DATA_ID_WOOD_TYPE, woodType.ordinal());
    }

    @Override
    public ForestryWoodType getWoodType() {
        int ordinal = this.entityData.get(DATA_ID_WOOD_TYPE);
        ForestryWoodType[] values = ForestryWoodType.VALUES;
        if (ordinal < 0 || ordinal >= values.length) {
            return ForestryWoodType.LARCH;
        }
        return values[ordinal];
    }

    static final class DropItem implements java.util.function.Supplier<Item> {
        private final boolean hasChest;
        IForestryBoat boat;

        DropItem(boolean hasChest) {
            this.hasChest = hasChest;
        }

        @Override
        public Item get() {
            ForestryWoodType woodType = boat != null ? boat.getWoodType() : ForestryWoodType.LARCH;
            return hasChest
                    ? ArboricultureItems.CHEST_BOAT.item(woodType)
                    : ArboricultureItems.BOAT.item(woodType);
        }
    }
}
