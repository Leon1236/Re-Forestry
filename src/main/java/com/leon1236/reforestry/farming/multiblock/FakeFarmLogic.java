package com.leon1236.reforestry.farming.multiblock;

import java.util.Collection;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.agriculture.Soil;

public enum FakeFarmLogic implements IFarmLogic {
	INSTANCE;

	@Override
	public Collection<ICrop> harvest(Level level, IFarmHousing housing, Direction direction, int extent, BlockPos pos) {
		return List.of();
	}

	@Override
	public IFarmType getType() {
		return FakeFarmType.INSTANCE;
	}

	@Override
	public boolean isManual() {
		return false;
	}

	enum FakeFarmType implements IFarmType {
		INSTANCE;

		@Override
		public int getFertilizerConsumption(IFarmHousing housing) {
			return 0;
		}

		@Override
		public int getWaterConsumption(IFarmHousing housing, float hydrationModifier) {
			return 0;
		}

		@Override
		public Component getDisplayName(boolean manual) {
			return Component.empty();
		}

		@Override
		public String getTranslationKey() {
			return "";
		}

		@Override
		public ItemStack getIcon() {
			return ItemStack.EMPTY;
		}

		@Override
		public boolean isAcceptedSoil(BlockState state) {
			return false;
		}

		@Override
		public boolean isAcceptedResource(ItemStack stack) {
			return false;
		}

		@Override
		public boolean isAcceptedSeedling(ItemStack stack) {
			return false;
		}

		@Override
		public boolean isAcceptedWindfall(ItemStack stack) {
			return false;
		}

		@Override
		public Collection<Soil> getSoils() {
			return List.of();
		}

		@Override
		public List<IFarmable> getFarmables() {
			return List.of();
		}

		@Override
		public IFarmLogic getLogic(boolean manual) {
			return FakeFarmLogic.INSTANCE;
		}
	}
}
