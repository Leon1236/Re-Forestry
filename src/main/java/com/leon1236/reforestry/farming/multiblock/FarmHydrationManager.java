package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.farming.farmlogic.IFarmHousingInternal;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;

public class FarmHydrationManager implements IFarmLedgerDelegate {
	private static final int DELAY_HYDRATION = 100;
	private static final float RAINFALL_MODIFIER_MAX = 15f;
	private static final float RAINFALL_MODIFIER_MIN = 0.5f;

	private final IFarmHousingInternal housing;
	private int hydrationDelay;
	private int ticksSinceRainfall;

	public FarmHydrationManager(IFarmHousingInternal housing) {
		this.housing = housing;
	}

	public void updateServer() {
		Level world = this.housing.getWorldObj();
		if (world == null) {
			return;
		}
		BlockPos coordinates = this.housing.getTopCoord();
		if (world.isRainingAt(coordinates.above())) {
			if (this.hydrationDelay > 0) {
				this.hydrationDelay--;
			} else {
				this.ticksSinceRainfall = 0;
			}
		} else {
			this.hydrationDelay = DELAY_HYDRATION;
			if (this.ticksSinceRainfall < Integer.MAX_VALUE) {
				this.ticksSinceRainfall++;
			}
		}
	}

	@Override
	public float getHydrationModifier() {
		return getHydrationTempModifier() * getHydrationHumidModifier() * getHydrationRainfallModifier();
	}

	@Override
	public float getHydrationTempModifier() {
		return switch (this.housing.temperature()) {
			case NORMAL -> 1.0f;
			case WARM -> 1.5f;
			case HOT, HELLISH -> 2.0f;
			default -> 0.8f;
		};
	}

	@Override
	public float getHydrationHumidModifier() {
		return switch (this.housing.humidity()) {
			case ARID -> 2.0f;
			case NORMAL -> 1.5f;
			case DAMP -> 1.0f;
		};
	}

	@Override
	public float getHydrationRainfallModifier() {
		return Mth.clamp((float) this.ticksSinceRainfall / 24000, RAINFALL_MODIFIER_MIN, RAINFALL_MODIFIER_MAX);
	}

	@Override
	public double getDrought() {
		return Math.round((double) this.ticksSinceRainfall / 24000 * 10) / 10.0;
	}

	public void write(CompoundTag data) {
		data.putInt("HydrationDelay", this.hydrationDelay);
		data.putInt("TicksSinceRainfall", this.ticksSinceRainfall);
	}

	public void read(CompoundTag data) {
		this.hydrationDelay = data.getIntOr("HydrationDelay", 0);
		this.ticksSinceRainfall = data.getIntOr("TicksSinceRainfall", 0);
	}

	public void save(ValueOutput output) {
		output.putInt("HydrationDelay", this.hydrationDelay);
		output.putInt("TicksSinceRainfall", this.ticksSinceRainfall);
	}

	public void load(ValueInput input) {
		this.hydrationDelay = input.getIntOr("HydrationDelay", 0);
		this.ticksSinceRainfall = input.getIntOr("TicksSinceRainfall", 0);
	}
}
