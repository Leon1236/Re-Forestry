package com.leon1236.reforestry.api.agriculture;

import java.util.Collection;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public interface IFarmType {
	int getFertilizerConsumption(IFarmHousing housing);

	int getWaterConsumption(IFarmHousing housing, float hydrationModifier);

	Component getDisplayName(boolean manual);

	String getTranslationKey();

	ItemStack getIcon();

	boolean isAcceptedSoil(BlockState state);

	boolean isAcceptedResource(ItemStack stack);

	boolean isAcceptedSeedling(ItemStack stack);

	boolean isAcceptedWindfall(ItemStack stack);

	Collection<Soil> getSoils();

	List<IFarmable> getFarmables();

	IFarmLogic getLogic(boolean manual);
}
