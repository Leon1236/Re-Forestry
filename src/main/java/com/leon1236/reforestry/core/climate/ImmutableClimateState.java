package com.leon1236.reforestry.core.climate;

import java.util.function.Function;

import com.google.common.base.MoreObjects;

import com.leon1236.reforestry.api.climate.IClimateState;

class ImmutableClimateState implements IClimateState {

	static final ImmutableClimateState MIN = new ImmutableClimateState(0.0F, 0.0F);
	static final ImmutableClimateState MAX = new ImmutableClimateState(2.0F, 2.0F);

	private final float temperature;
	private final float humidity;

	ImmutableClimateState(IClimateState climateState) {
		this(climateState.getTemperature(), climateState.getHumidity());
	}

	ImmutableClimateState(float temperature, float humidity) {
		this.temperature = temperature;
		this.humidity = humidity;
	}

	@Override
	public IClimateState copy(boolean mutable) {
		return ClimateStateHelper.INSTANCE.create(this, mutable);
	}

	@Override
	public IClimateState copy() {
		return this;
	}

	@Override
	public IClimateState toMutable() {
		return copy(true);
	}

	@Override
	public IClimateState toImmutable() {
		return this;
	}

	@Override
	public IClimateState addTemperature(float temperature) {
		return ClimateStateHelper.of(this.temperature + temperature, humidity);
	}

	@Override
	public IClimateState addHumidity(float humidity) {
		return ClimateStateHelper.of(temperature, this.humidity + humidity);
	}

	@Override
	public IClimateState add(IClimateState state) {
		return ClimateStateHelper.of(this.temperature + state.getTemperature(), this.humidity + state.getHumidity());
	}

	@Override
	public IClimateState setTemperature(float temperature) {
		return ClimateStateHelper.of(temperature, this.humidity);
	}

	@Override
	public IClimateState setHumidity(float humidity) {
		return ClimateStateHelper.of(this.temperature, humidity);
	}

	@Override
	public IClimateState setClimate(float temperature, float humidity) {
		return ClimateStateHelper.of(temperature, humidity);
	}

	@Override
	public IClimateState multiply(double factor) {
		return ClimateStateHelper.of((float) (this.temperature * factor), (float) (this.humidity * factor));
	}

	@Override
	public IClimateState subtract(IClimateState state) {
		return ClimateStateHelper.of(this.temperature - state.getTemperature(), this.humidity - state.getHumidity());
	}

	@Override
	public IClimateState map(Function<Float, Float> mapper) {
		return ClimateStateHelper.of(mapper.apply(temperature), mapper.apply(humidity));
	}

	@Override
	public boolean isPresent() {
		return !Float.isNaN(temperature) && !Float.isNaN(humidity);
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public boolean isClamped() {
		return temperature < 2.0F && temperature >= 0.0F && humidity < 2.0F && humidity >= 0.0F;
	}

	@Override
	public float getTemperature() {
		return temperature;
	}

	@Override
	public float getHumidity() {
		return humidity;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IClimateState otherState)) {
			return false;
		}
		return otherState.getTemperature() == temperature && otherState.getHumidity() == humidity;
	}

	@Override
	public int hashCode() {
		return Float.hashCode(temperature) * 31 + Float.hashCode(humidity);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("temperature", temperature).add("humidity", humidity).toString();
	}
}
