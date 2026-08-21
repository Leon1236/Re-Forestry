package com.leon1236.reforestry.farming.circuits;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.agriculture.HorizontalDirection;
import com.leon1236.reforestry.api.agriculture.IFarmCircuit;
import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.core.circuits.Circuit;
import com.leon1236.reforestry.farming.multiblock.FakeFarmLogic;

public class CircuitFarmLogic extends Circuit implements IFarmCircuit {
	private final Identifier farmTypeId;
	private final boolean manual;

	public CircuitFarmLogic(String uid, Identifier farmTypeId, boolean manual) {
		super(uid);
		this.farmTypeId = farmTypeId;
		this.manual = manual;
	}

	@Nullable
	private IFarmType lookupType() {
		return IForestryApi.INSTANCE.getFarmingManager().getFarmType(this.farmTypeId);
	}

	@Override
	public String getTranslationKey() {
		IFarmType type = lookupType();
		return type != null ? type.getTranslationKey() : "for.circuit." + this.farmTypeId.getPath();
	}

	@Override
	public Component getDisplayName() {
		IFarmType type = lookupType();
		return type != null ? type.getDisplayName(this.manual) : super.getDisplayName();
	}

	@Override
	public IFarmType getProperties() {
		IFarmType type = lookupType();
		if (type == null) {
			return FakeFarmLogic.INSTANCE.getType();
		}
		return type;
	}

	@Override
	public boolean isManual() {
		return this.manual;
	}

	@Override
	public boolean isCircuitable(Object tile) {
		return tile instanceof IFarmHousing;
	}

	@Override
	public void onInsertion(int slot, Object tile) {
		apply(slot, tile);
	}

	@Override
	public void onLoad(int slot, Object tile) {
		apply(slot, tile);
	}

	@Override
	public void onRemoval(int slot, Object tile) {
		if (!(tile instanceof IFarmHousing housing)) {
			return;
		}
		if (slot < 0 || slot >= HorizontalDirection.VALUES.size()) {
			return;
		}
		housing.resetFarmLogic(HorizontalDirection.VALUES.get(slot));
	}

	@Override
	public void onTick(int slot, Object tile) {
	}

	private void apply(int slot, Object tile) {
		if (!(tile instanceof IFarmHousing housing)) {
			return;
		}
		if (slot < 0 || slot >= HorizontalDirection.VALUES.size()) {
			return;
		}
		IFarmType type = lookupType();
		if (type == null) {
			return;
		}
		IFarmLogic logic = type.getLogic(this.manual);
		if (logic == null) {
			return;
		}
		Direction direction = HorizontalDirection.VALUES.get(slot);
		housing.setFarmLogic(direction, logic);
	}
}
