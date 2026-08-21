package com.leon1236.reforestry.core.genetics;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.IFlowerType;
import com.leon1236.reforestry.api.genetics.IFlowerTypeManager;
import com.leon1236.reforestry.apiculture.genetics.FlowerType;

public final class FlowerTypeManager implements IFlowerTypeManager {
	public static final FlowerTypeManager INSTANCE = new FlowerTypeManager();

	private volatile Map<Identifier, IFlowerType> flowerTypes = Map.of();

	private FlowerTypeManager() {
	}

	public void bootstrapVanilla() {
		Map<Identifier, IFlowerType> values = new LinkedHashMap<>();
		for (FlowerType type : FlowerType.values()) {
			values.put(type.id(), type);
		}
		setFlowerTypes(values);
	}

	@Override
	@Nullable
	public IFlowerType getFlowerType(Identifier id) {
		return flowerTypes.get(id);
	}

	@Override
	public IFlowerType getFlowerTypeSafe(Identifier id) {
		IFlowerType type = flowerTypes.get(id);
		return type != null ? type : FlowerType.VANILLA;
	}

	@Override
	public Map<Identifier, IFlowerType> getAllFlowerTypes() {
		return flowerTypes;
	}

	@Override
	public void setFlowerTypes(Map<Identifier, IFlowerType> flowerTypes) {
		this.flowerTypes = Map.copyOf(flowerTypes);
	}

	public void register(Identifier id, IFlowerType type) {
		Map<Identifier, IFlowerType> merged = new LinkedHashMap<>(flowerTypes);
		merged.put(id, type);
		this.flowerTypes = Map.copyOf(merged);
	}
}
