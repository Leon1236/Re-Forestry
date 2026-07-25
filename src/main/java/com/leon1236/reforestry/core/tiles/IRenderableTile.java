package com.leon1236.reforestry.core.tiles;

import com.leon1236.reforestry.core.render.TankRenderInfo;

public interface IRenderableTile {
	TankRenderInfo getResourceTankInfo();

	TankRenderInfo getProductTankInfo();
}
