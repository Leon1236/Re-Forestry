package com.leon1236.reforestry.api.climate;

import java.util.function.Function;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public interface IClimateState {

	float getTemperature();

	default TemperatureType getTemperatureEnum() {
		return TemperatureType.getFromValue(getTemperature());
	}

	float getHumidity();

	default HumidityType getHumidityEnum() {
		return HumidityType.getFromValue(getHumidity());
	}

	default float getClimate(ClimateType type) {
		return type == ClimateType.HUMIDITY ? getHumidity() : getTemperature();
	}

	IClimateState addTemperature(float temperature);

	IClimateState addHumidity(float humidity);

	IClimateState add(IClimateState state);

	default IClimateState add(ClimateType type, float value) {
		return type == ClimateType.HUMIDITY ? addHumidity(value) : addTemperature(value);
	}

	IClimateState setTemperature(float temperature);

	IClimateState setHumidity(float humidity);

	IClimateState setClimate(float temperature, float humidity);

	default IClimateState setClimate(ClimateType type, float value) {
		return type == ClimateType.HUMIDITY ? setHumidity(value) : setTemperature(value);
	}

	IClimateState multiply(double factor);

	default IClimateState subtractTemperature(float value) {
		return addTemperature(-value);
	}

	default IClimateState subtractHumidity(float value) {
		return addHumidity(-value);
	}

	IClimateState subtract(IClimateState state);

	default IClimateState subtract(ClimateType type, float value) {
		return type == ClimateType.HUMIDITY ? subtractHumidity(value) : subtractTemperature(value);
	}

	IClimateState map(Function<Float, Float> mapper);

	IClimateState copy(boolean mutable);

	IClimateState copy();

	IClimateState toMutable();

	IClimateState toImmutable();

	boolean isPresent();

	boolean isMutable();

	boolean isClamped();
}
