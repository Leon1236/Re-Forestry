package com.leon1236.reforestry.apiculture.multiblock;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.apiculture.FakeBeekeepingLogic;
import com.leon1236.reforestry.apiculture.InventoryBeeHousing;
import com.leon1236.reforestry.apiculture.tiles.FakeBeeHousingInventory;
import com.leon1236.reforestry.core.multiblock.FakeMultiblockController;

public enum FakeAlvearyController implements FakeMultiblockController, IAlvearyControllerInternal {
	INSTANCE;

	private static final Container EMPTY_INVENTORY = new SimpleContainer(InventoryBeeHousing.SLOT_COUNT) {
		@Override
		public boolean canPlaceItem(int slot, ItemStack stack) {
			return false;
		}
	};

	@Override
	public Container getInternalInventory() {
		return EMPTY_INVENTORY;
	}

	@Override
	public int getHealthScaled(int scale) {
		return 0;
	}

	@Nullable
	@Override
	public Level level() {
		return null;
	}

	@Override
	public BlockPos position() {
		return BlockPos.ZERO;
	}

	@Override
	public IBeeHousingInventory beeInventory() {
		return FakeBeeHousingInventory.INSTANCE;
	}

	@Override
	public Iterable<IBeeModifier> getBeeModifiers() {
		return List.of();
	}

	@Override
	public Iterable<IBeeListener> getBeeListeners() {
		return List.of();
	}

	@Override
	public IBeekeepingLogic getBeekeepingLogic() {
		return FakeBeekeepingLogic.INSTANCE;
	}

	@Override
	public int getBlockLightValue() {
		return 0;
	}

	@Override
	public boolean canBlockSeeTheSky() {
		return false;
	}

	@Override
	public boolean isRaining() {
		return false;
	}

	@Nullable
	@Override
	public GameProfile getOwner() {
		return null;
	}

	@Override
	public Vec3 getBeeFXCoordinates() {
		return Vec3.ZERO;
	}

	@Nullable
	@Override
	public Holder<Biome> getBiome() {
		return null;
	}
}
