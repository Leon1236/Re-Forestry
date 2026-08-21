package com.leon1236.reforestry.api.plugin;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import it.unimi.dsi.fastutil.floats.Float2IntFunction;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.agriculture.IFarmableFactory;
import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IWaterConsumption;

public interface IFarmTypeBuilder {
	IFarmTypeBuilder setIcon(ItemStack stack);

	IFarmTypeBuilder setLogicFactory(BiFunction<IFarmType, Boolean, IFarmLogic> factory);

	default IFarmTypeBuilder setFertilizerConsumption(int consumption) {
		return setFertilizerConsumption(housing -> consumption);
	}

	IFarmTypeBuilder setFertilizerConsumption(ToIntFunction<IFarmHousing> consumption);

	default IFarmTypeBuilder setWaterConsumption(int waterConsumption) {
		return setWaterConsumption((housing, hydrationModifier) -> waterConsumption);
	}

	default IFarmTypeBuilder setWaterConsumption(Float2IntFunction waterConsumption) {
		return setWaterConsumption((housing, hydrationModifier) -> waterConsumption.get(hydrationModifier));
	}

	IFarmTypeBuilder setWaterConsumption(IWaterConsumption waterConsumption);

	default IFarmTypeBuilder addSoil(Block block) {
		return addSoil(new ItemStack(block), block.defaultBlockState());
	}

	IFarmTypeBuilder addSoil(ItemStack resource, BlockState soilState);

	IFarmTypeBuilder addGermling(ItemStack germling);

	IFarmTypeBuilder addGermlings(Iterable<ItemStack> seedling);

	IFarmTypeBuilder addProduct(ItemStack product);

	IFarmTypeBuilder addProducts(Collection<ItemStack> products);

	IFarmTypeBuilder addFarmable(IFarmable farmable);

	IFarmTypeBuilder addWindfallFarmable(Item germling, IFarmableFactory factory, Consumer<IWindfallFarmableBuilder> action);

	IFarmTypeBuilder modifyWindfallFarmable(Item germling, Consumer<IWindfallFarmableBuilder> action);
}
