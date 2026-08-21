package com.leon1236.reforestry.api.genetics;

import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.IFlowerType;

public interface IFlowerTypeManager {
	@Nullable
	IFlowerType getFlowerType(Identifier id);

	IFlowerType getFlowerTypeSafe(Identifier id);

	Map<Identifier, IFlowerType> getAllFlowerTypes();

	void setFlowerTypes(Map<Identifier, IFlowerType> flowerTypes);
}
