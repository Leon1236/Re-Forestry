package com.leon1236.reforestry.api.multiblock;

import com.leon1236.reforestry.api.agriculture.IFarmListener;

public interface IFarmComponent<T extends IMultiblockLogicFarm> extends IMultiblockComponent {
	@Override
	T getMultiblockLogic();

	interface Listener<T extends IMultiblockLogicFarm> extends IFarmComponent<T> {
		IFarmListener getFarmListener();
	}

	interface Active<T extends IMultiblockLogicFarm> extends IFarmComponent<T> {
		void updateServer(int tickCount);

		void updateClient(int tickCount);
	}
}
