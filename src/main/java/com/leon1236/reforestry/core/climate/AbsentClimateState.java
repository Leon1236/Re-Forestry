package com.leon1236.reforestry.core.climate;

import java.util.function.Function;

import com.google.common.base.MoreObjects;

import com.leon1236.reforestry.api.climate.IClimateState;

public final class AbsentClimateState implements IClimateState {

	public static final AbsentClimateState INSTANCE = new AbsentClimateState();

	private AbsentClimateState() {
	}

	@Override
	public IClimateState copy() {
		return this;
	}

	@Override
	public IClimateState setTemperature(float temperature) {
		return this;
	}

	@Override
	public IClimateState setHumidity(float humidity) {
		return this;
	}

	@Override
	public IClimateState setClimate(float temperature, float humidity) {
		return this;
	}

	@Override
	public IClimateState addTemperature(float temperature) {
		return this;
	}

	@Override
	public IClimateState addHumidity(float humidity) {
		return this;
	}

	@Override
	public IClimateState add(IClimateState state) {
		return this;
	}

	@Override
	public IClimateState multiply(double factor) {
		return this;
	}

	@Override
	public IClimateState subtract(IClimateState state) {
		return this;
	}

	@Override
	public IClimateState map(Function<Float, Float> mapper) {
		return this;
	}

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public boolean isClamped() {
		return false;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public IClimateState toImmutable() {
		return this;
	}

	@Override
	public IClimateState copy(boolean mutable) {
		return this;
	}

	@Override
	public IClimateState toMutable() {
		return this;
	}

	@Override
	public float getTemperature() {
		return Float.NaN;
	}

	@Override
	public float getHumidity() {
		return Float.NaN;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IClimateState otherState)) {
			return false;
		}
		return Float.isNaN(otherState.getTemperature()) && Float.isNaN(otherState.getHumidity());
	}

	@Override
	public int hashCode() {
		return Float.hashCode(Float.NaN) * 31 + Float.hashCode(Float.NaN);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("temperature", Float.NaN).add("humidity", Float.NaN).toString();
	}
}
