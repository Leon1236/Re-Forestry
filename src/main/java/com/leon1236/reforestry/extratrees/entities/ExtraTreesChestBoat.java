package com.leon1236.reforestry.extratrees.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractChestBoat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.extratrees.features.ExtraTreesEntities;

public class ExtraTreesChestBoat extends AbstractChestBoat implements IExtraTreesBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_WOOD_TYPE =
			SynchedEntityData.defineId(ExtraTreesChestBoat.class, EntityDataSerializers.INT);

	private ExtraTreesChestBoat(EntityType<? extends ExtraTreesChestBoat> type, Level level, ExtraTreesBoat.DropItem dropItem) {
		super(type, level, dropItem);
		dropItem.boat = this;
	}

	public ExtraTreesChestBoat(EntityType<? extends ExtraTreesChestBoat> type, Level level) {
		this(type, level, new ExtraTreesBoat.DropItem(true));
	}

	public ExtraTreesChestBoat(Level level, double x, double y, double z) {
		this(ExtraTreesEntities.CHEST_BOAT.entityType(), level);
		setInitialPos(x, y, z);
	}

	@Override
	protected double rideHeight(EntityDimensions dimensions) {
		return dimensions.height() / 3.0F;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(DATA_ID_WOOD_TYPE, ExtraTreeWoodType.CEDAR.ordinal());
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putString("type", getWoodType().name());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		String typeName = input.getStringOr("type", ExtraTreeWoodType.CEDAR.name());
		try {
			setWoodType(ExtraTreeWoodType.valueOf(typeName));
		} catch (IllegalArgumentException e) {
			setWoodType(ExtraTreeWoodType.CEDAR);
		}
	}

	@Override
	public void setWoodType(ExtraTreeWoodType woodType) {
		this.entityData.set(DATA_ID_WOOD_TYPE, woodType.ordinal());
	}

	@Override
	public ExtraTreeWoodType getWoodType() {
		int ordinal = this.entityData.get(DATA_ID_WOOD_TYPE);
		ExtraTreeWoodType[] values = ExtraTreeWoodType.VALUES;
		if (ordinal < 0 || ordinal >= values.length) {
			return ExtraTreeWoodType.CEDAR;
		}
		return values[ordinal];
	}
}
