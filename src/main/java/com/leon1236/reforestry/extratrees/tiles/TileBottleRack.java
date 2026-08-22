package com.leon1236.reforestry.extratrees.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.tiles.TileBase;
import com.leon1236.reforestry.extratrees.features.ExtraTreesTiles;
import com.leon1236.reforestry.extratrees.gui.ContainerBottleRack;

public class TileBottleRack extends TileBase {
	public static final int TANK_COUNT = 36;
	public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(1000);

	private final MultiFluidTank tanks;

	public TileBottleRack(BlockPos pos, BlockState state) {
		super(ExtraTreesTiles.BOTTLE_RACK.type(), pos, state);
		MultiFluidTank.Builder builder = MultiFluidTank.builder(this::setChanged);
		for (int i = 0; i < TANK_COUNT; i++) {
			builder.tank("tank_" + i, TANK_CAPACITY, TileBottleRack::acceptsExtraTreesFluid);
		}
		this.tanks = builder.build();
	}

	private static boolean acceptsExtraTreesFluid(FluidVariant variant) {
		if (variant.isBlank()) {
			return true;
		}
		Identifier id = BuiltInRegistries.FLUID.getKey(variant.getFluid());
		return id != null && ReForestry.MOD_ID.equals(id.getNamespace());
	}

	public MultiFluidTank getTankManager() {
		return this.tanks;
	}

	public FilteredFluidStorage getTank(int index) {
		return this.tanks.tank(index);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		this.tanks.writeValue(output.child("Tanks"));
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.tanks.readValue(input.childOrEmpty("Tanks"));
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ContainerBottleRack(containerId, playerInventory, this);
	}
}
