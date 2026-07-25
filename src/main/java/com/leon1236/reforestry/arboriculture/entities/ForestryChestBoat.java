package com.leon1236.reforestry.arboriculture.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractChestBoat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.features.ArboricultureEntities;

public class ForestryChestBoat extends AbstractChestBoat implements IForestryBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_WOOD_TYPE =
            SynchedEntityData.defineId(ForestryChestBoat.class, EntityDataSerializers.INT);

    private ForestryChestBoat(EntityType<? extends ForestryChestBoat> type, Level level, ForestryBoat.DropItem dropItem) {
        super(type, level, dropItem);
        dropItem.boat = this;
    }

    public ForestryChestBoat(EntityType<? extends ForestryChestBoat> type, Level level) {
        this(type, level, new ForestryBoat.DropItem(true));
    }

    public ForestryChestBoat(Level level, double x, double y, double z) {
        this(ArboricultureEntities.CHEST_BOAT.entityType(), level);
        setInitialPos(x, y, z);
    }

    @Override
    protected double rideHeight(EntityDimensions dimensions) {
        return dimensions.height() / 3.0F;
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
}
