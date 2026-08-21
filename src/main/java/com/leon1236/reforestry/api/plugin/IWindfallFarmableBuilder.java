package com.leon1236.reforestry.api.plugin;

import net.minecraft.world.item.Item;

public interface IWindfallFarmableBuilder {
	IWindfallFarmableBuilder addWindfall(Item windfall);

	IWindfallFarmableBuilder addWindfall(Iterable<? extends Item> windfall);
}
