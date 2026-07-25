package com.leon1236.reforestry.core.multiblock;

import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.core.climate.FakeClimateProvider;

public class TestRectangularController extends RectangularMultiblockControllerBase {
	private static final IMultiblockSizeLimits SIZE_LIMITS = new IMultiblockSizeLimits() {
		@Override
		public int getMinimumNumberOfBlocksForAssembledMachine() {
			return 1;
		}

		@Override
		public int getMaximumXSize() {
			return 3;
		}

		@Override
		public int getMaximumZSize() {
			return 3;
		}

		@Override
		public int getMaximumYSize() {
			return 3;
		}

		@Override
		public int getMinimumXSize() {
			return 1;
		}

		@Override
		public int getMinimumYSize() {
			return 1;
		}

		@Override
		public int getMinimumZSize() {
			return 1;
		}
	};

	private boolean ticking;

	public TestRectangularController(Level world) {
		super(world, SIZE_LIMITS);
	}

	public boolean isTicking() {
		return this.ticking;
	}

	@Override
	protected void onBlockAdded(IMultiblockComponent newPart) {
	}

	@Override
	protected void onBlockRemoved(IMultiblockComponent oldPart) {
	}

	@Override
	protected void onAssimilate(IMultiblockControllerInternal assimilated) {
	}

	@Override
	public void onAssimilated(IMultiblockControllerInternal assimilator) {
	}

	@Override
	protected void onAttachedPartWithMultiblockData(IMultiblockComponent part, net.minecraft.nbt.CompoundTag data) {
		read(data, this.level.registryAccess());
	}

	@Override
	protected boolean serverTick(int tickCount) {
		this.ticking = true;
		return false;
	}

	@Override
	protected void clientTick(int tickCount) {
	}

	@Override
	protected void isGoodForExteriorLevel(IMultiblockComponent part, int level) {
	}

	@Override
	protected void isGoodForInterior(IMultiblockComponent part) {
	}

	@Override
	public String getUnlocalizedType() {
		return "for.multiblock.rectangular.test";
	}

	@Override
	public TemperatureType temperature() {
		return FakeClimateProvider.INSTANCE.temperature();
	}

	@Override
	public HumidityType humidity() {
		return FakeClimateProvider.INSTANCE.humidity();
	}
}
