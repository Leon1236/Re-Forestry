package com.leon1236.reforestry.api.multiblock;

import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.climate.IClimateControlled;

public interface IAlvearyComponent<T extends IMultiblockLogicAlveary> extends IMultiblockComponent {
	@Override
	T getMultiblockLogic();

	interface BeeListener<T extends IMultiblockLogicAlveary> extends IAlvearyComponent<T> {
		IBeeListener getBeeListener();
	}

	interface BeeModifier<T extends IMultiblockLogicAlveary> extends IAlvearyComponent<T> {
		IBeeModifier getBeeModifier();
	}

	interface Climatiser<T extends IMultiblockLogicAlveary> extends IAlvearyComponent<T> {
		void changeClimate(int tickCount, IClimateControlled alveary);
	}

	interface Active<T extends IMultiblockLogicAlveary> extends IAlvearyComponent<T> {
		void updateServer(int tickCount);

		void updateClient(int tickCount);
	}
}
