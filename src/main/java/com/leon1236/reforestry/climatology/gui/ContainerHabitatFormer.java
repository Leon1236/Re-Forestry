package com.leon1236.reforestry.climatology.gui;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.climate.IClimateState;
import com.leon1236.reforestry.api.climate.IClimateTransformer;
import com.leon1236.reforestry.climatology.features.ClimatologyMenuTypes;
import com.leon1236.reforestry.climatology.tiles.TileHabitatFormer;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

public class ContainerHabitatFormer extends ContainerMachine<TileHabitatFormer> implements IContainerLiquidTanks {
	public static final int BUTTON_CIRCLE = 0;
	public static final int BUTTON_RANGE_BASE = 100;

	public ContainerHabitatFormer(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileHabitatFormer.class));
	}

	public ContainerHabitatFormer(int containerId, Inventory playerInventory, TileHabitatFormer tile) {
		super(ClimatologyMenuTypes.HABITAT_FORMER.type(), containerId, playerInventory, tile, 151);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getEnergyData());
		addDataSlots(tile.getHabitatData());
	}

	@Override
	protected void addMachineSlots(TileHabitatFormer tile) {
		addSlot(new Slot(tile, TileHabitatFormer.SLOT_INPUT, 129, 38) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return tile.canPlaceItem(TileHabitatFormer.SLOT_INPUT, stack);
			}
		});
	}

	public float getCurrentTemperature() {
		return Float.intBitsToFloat(tile.getHabitatData().get(1));
	}

	public float getCurrentHumidity() {
		return Float.intBitsToFloat(tile.getHabitatData().get(2));
	}

	public float getTargetTemperature() {
		return Float.intBitsToFloat(tile.getHabitatData().get(3));
	}

	public float getTargetHumidity() {
		return Float.intBitsToFloat(tile.getHabitatData().get(4));
	}

	public float getDefaultTemperature() {
		return Float.intBitsToFloat(tile.getHabitatData().get(5));
	}

	public float getDefaultHumidity() {
		return Float.intBitsToFloat(tile.getHabitatData().get(6));
	}

	public int getRange() {
		return tile.getHabitatData().get(7);
	}

	public boolean isCircular() {
		return tile.getHabitatData().get(8) == 1;
	}

	public int getTankAmountMb() {
		return tile.getHabitatData().get(0);
	}

	public int getTankCapacityMb() {
		return (int) FluidUnits.dropletsToMb(TileHabitatFormer.TANK_CAPACITY);
	}

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (super.clickMenuButton(player, id)) {
			return true;
		}
		IClimateTransformer transformer = tile.getTransformer();
		if (id == BUTTON_CIRCLE) {
			transformer.setCircular(!transformer.isCircular());
			return true;
		}
		if (id >= BUTTON_RANGE_BASE && id <= BUTTON_RANGE_BASE + 16) {
			transformer.setRange(id - BUTTON_RANGE_BASE);
			return true;
		}
		if (PipetteTankHelper.canHandleClick(getCarried())) {
			if (player instanceof ServerPlayer serverPlayer) {
				handlePipetteClick(0, serverPlayer);
			}
			return true;
		}
		return false;
	}

	@Override
	public void handlePipetteClick(int slot, ServerPlayer player) {
		SingleFluidStorage tank = getTank(slot);
		if (tank != null) {
			PipetteTankHelper.handlePipetteClick(tank, player, this);
		}
	}

	@Override
	public SingleFluidStorage getTank(int slot) {
		return slot == 0 ? tile.getResourceTank() : null;
	}

	public void setTargetClimate(IClimateState state) {
		tile.getTransformer().setTarget(state);
	}
}
