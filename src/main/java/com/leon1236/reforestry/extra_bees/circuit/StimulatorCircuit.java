package com.leon1236.reforestry.extra_bees.circuit;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.core.circuits.Circuit;

public class StimulatorCircuit extends Circuit {
	private final AlvearySimulatorCircuitType type;
	private final List<Component> extraTooltip = new ArrayList<>();

	public StimulatorCircuit(AlvearySimulatorCircuitType type) {
		super(type.getCircuitUid());
		this.type = type;
		type.addModifierTooltip(this.extraTooltip);
	}

	public AlvearySimulatorCircuitType getType() {
		return type;
	}

	public int getPowerUsage() {
		return type.getPower();
	}

	@Override
	public String getTranslationKey() {
		return "for.binnie.circuit." + this.type.getCircuitUid().replace("_", "");
	}

	@Override
	public boolean isCircuitable(Object tile) {
		return false;
	}

	@Override
	public void onInsertion(int slot, Object tile) {
	}

	@Override
	public void onLoad(int slot, Object tile) {
	}

	@Override
	public void onRemoval(int slot, Object tile) {
	}

	@Override
	public void onTick(int slot, Object tile) {
	}

	@Override
	public void addTooltip(List<Component> list) {
		super.addTooltip(list);
		list.addAll(this.extraTooltip);
	}
}
