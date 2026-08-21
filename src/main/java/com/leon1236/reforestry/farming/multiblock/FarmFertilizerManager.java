package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class FarmFertilizerManager {
	private static final int BUFFER_FERTILIZER = 200;

	private final IFarmInventoryInternal inventory;
	private int storedFertilizer;

	public FarmFertilizerManager(IFarmInventoryInternal inventory) {
		this.inventory = inventory;
		this.storedFertilizer = 0;
	}

	public boolean hasFertilizer(int amount) {
		if (this.inventory.getFertilizerValue() < 0) {
			return true;
		}
		return this.storedFertilizer >= amount;
	}

	public void removeFertilizer(int amount) {
		if (this.inventory.getFertilizerValue() < 0) {
			return;
		}
		this.storedFertilizer -= amount;
		if (this.storedFertilizer < 0) {
			this.storedFertilizer = 0;
		}
	}

	public boolean maintainFertilizer() {
		if (this.storedFertilizer <= BUFFER_FERTILIZER) {
			int fertilizerValue = this.inventory.getFertilizerValue();
			if (fertilizerValue < 0) {
				this.storedFertilizer += 2000;
			} else if (this.inventory.useFertilizer()) {
				this.storedFertilizer += fertilizerValue;
			}
		}
		return this.storedFertilizer > 0;
	}

	public int getStoredFertilizerScaled(int scale) {
		if (this.storedFertilizer == 0) {
			return 0;
		}
		return this.storedFertilizer * scale / (this.inventory.getFertilizerValue() + BUFFER_FERTILIZER);
	}

	public void write(CompoundTag data) {
		data.putInt("StoredFertilizer", this.storedFertilizer);
	}

	public void read(CompoundTag data) {
		this.storedFertilizer = data.getIntOr("StoredFertilizer", 0);
	}

	public void save(ValueOutput output) {
		output.putInt("StoredFertilizer", this.storedFertilizer);
	}

	public void load(ValueInput input) {
		this.storedFertilizer = input.getIntOr("StoredFertilizer", 0);
	}
}
