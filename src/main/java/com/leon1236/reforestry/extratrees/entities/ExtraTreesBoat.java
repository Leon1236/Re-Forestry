package com.leon1236.reforestry.extratrees.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.extratrees.ExtraTreeWoodType;
import com.leon1236.reforestry.extratrees.features.ExtraTreesEntities;
import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;

public class ExtraTreesBoat extends Boat implements IExtraTreesBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_WOOD_TYPE =
			SynchedEntityData.defineId(ExtraTreesBoat.class, EntityDataSerializers.INT);

	private ExtraTreesBoat(EntityType<? extends ExtraTreesBoat> type, Level level, DropItem dropItem) {
		super(type, level, dropItem);
		dropItem.boat = this;
	}

	public ExtraTreesBoat(EntityType<? extends ExtraTreesBoat> type, Level level) {
		this(type, level, new DropItem(false));
	}

	public ExtraTreesBoat(Level level, double x, double y, double z) {
		this(ExtraTreesEntities.BOAT.entityType(), level);
		setInitialPos(x, y, z);
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

	static final class DropItem implements java.util.function.Supplier<Item> {
		private final boolean hasChest;
		IExtraTreesBoat boat;

		DropItem(boolean hasChest) {
			this.hasChest = hasChest;
		}

		@Override
		public Item get() {
			ExtraTreeWoodType woodType = boat != null ? boat.getWoodType() : ExtraTreeWoodType.CEDAR;
			return hasChest
					? ExtraTreesItems.CHEST_BOAT.item(woodType)
					: ExtraTreesItems.BOAT.item(woodType);
		}
	}
}
