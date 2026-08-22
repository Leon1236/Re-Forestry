package com.leon1236.reforestry.farming.multiblock;

import java.util.Collection;
import java.util.List;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.circuits.ForestryCircuitSocketTypes;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.multiblock.FakeMultiblockController;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;

public enum FakeFarmController implements FakeMultiblockController, IFarmControllerInternal {
	INSTANCE;

	private static final Container EMPTY_INVENTORY = new SimpleContainer(InventoryFarm.CONFIG.count) {
		@Override
		public boolean canPlaceItem(int slot, ItemStack stack) {
			return false;
		}
	};
	private static final FilteredFluidStorage EMPTY_TANK =
			new FilteredFluidStorage(0, FilteredFluidStorage.only(Fluids.WATER), () -> {
			});
	private static final IFarmLedgerDelegate EMPTY_LEDGER = new IFarmLedgerDelegate() {
		@Override
		public float getHydrationModifier() {
			return 1.0f;
		}

		@Override
		public float getHydrationTempModifier() {
			return 1.0f;
		}

		@Override
		public float getHydrationHumidModifier() {
			return 1.0f;
		}

		@Override
		public float getHydrationRainfallModifier() {
			return 1.0f;
		}

		@Override
		public double getDrought() {
			return 0;
		}
	};

	@Override
	public BlockPos getCoords() {
		return BlockPos.ZERO;
	}

	@Override
	public Vec3i getArea() {
		return Vec3i.ZERO;
	}

	@Override
	public Vec3i getOffset() {
		return Vec3i.ZERO;
	}

	@Override
	public boolean doWork() {
		return false;
	}

	@Override
	public boolean hasLiquid(FluidVariant variant, long amount) {
		return false;
	}

	@Override
	public void removeLiquid(FluidVariant variant, long amount) {
	}

	@Override
	public boolean plantGermling(IFarmable farmable, Level world, BlockPos pos, Direction direction) {
		return false;
	}

	@Override
	public FakeFarmInventory getFarmInventory() {
		return FakeFarmInventory.INSTANCE;
	}

	@Override
	public void addPendingProduct(ItemStack stack) {
	}

	@Override
	public void setFarmLogic(Direction direction, IFarmLogic logic) {
	}

	@Override
	public void resetFarmLogic(Direction direction) {
	}

	@Override
	public IFarmLogic getFarmLogic(Direction direction) {
		return FakeFarmLogic.INSTANCE;
	}

	@Override
	public Collection<IFarmLogic> getFarmLogics() {
		return List.of();
	}

	@Override
	public int getStoredFertilizerScaled(int scale) {
		return 0;
	}

	@Override
	public BlockPos getFarmCorner(Direction direction) {
		return BlockPos.ZERO;
	}

	@Override
	public int getExtents(Direction direction, BlockPos pos) {
		return 0;
	}

	@Override
	public void setExtents(Direction direction, BlockPos pos, int extend) {
	}

	@Override
	public void cleanExtents(Direction direction) {
	}

	@Override
	public String getUnlocalizedType() {
		return "for.multiblock.farm.type";
	}

	@Override
	public IFarmLedgerDelegate getFarmLedgerDelegate() {
		return EMPTY_LEDGER;
	}

	@Override
	public Container getInternalInventory() {
		return EMPTY_INVENTORY;
	}

	@Override
	public FilteredFluidStorage getWaterTank() {
		return EMPTY_TANK;
	}

	@Override
	public int getSocketCount() {
		return 1;
	}

	@Override
	public ItemStack getSocket(int slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setSocket(int slot, ItemStack stack) {
	}

	@Override
	public Identifier getSocketType() {
		return ForestryCircuitSocketTypes.FARM;
	}
}
