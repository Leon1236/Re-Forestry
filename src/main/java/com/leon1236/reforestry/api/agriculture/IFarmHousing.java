package com.leon1236.reforestry.api.agriculture;

import java.util.Collection;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.core.IErrorLogicSource;

public interface IFarmHousing extends IErrorLogicSource, IExtentCache {
	BlockPos getCoords();

	Vec3i getArea();

	Vec3i getOffset();

	boolean doWork();

	boolean hasLiquid(FluidVariant variant, long amount);

	void removeLiquid(FluidVariant variant, long amount);

	boolean plantGermling(IFarmable farmable, Level world, BlockPos pos, Direction direction);

	default boolean isValidPlatform(Level world, BlockPos pos) {
		return false;
	}

	default boolean isSquare() {
		return false;
	}

	default boolean canPlantSoil(boolean manual) {
		return !manual;
	}

	IFarmInventory getFarmInventory();

	void addPendingProduct(ItemStack stack);

	void setFarmLogic(Direction direction, IFarmLogic logic);

	void resetFarmLogic(Direction direction);

	IFarmLogic getFarmLogic(Direction direction);

	Collection<IFarmLogic> getFarmLogics();

	int getStoredFertilizerScaled(int scale);

	BlockPos getFarmCorner(Direction direction);
}
