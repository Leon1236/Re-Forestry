package com.leon1236.reforestry.api.plugin;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;

public interface IFarmingRegistration {
	IFarmTypeBuilder createFarmType(Identifier id, BiFunction<IFarmType, Boolean, IFarmLogic> logicFactory, ItemStack icon);

	void modifyFarmType(Identifier id, Consumer<IFarmTypeBuilder> action);

	void registerFertilizer(Item fertilizer, int amount);
}
