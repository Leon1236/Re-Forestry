package com.leon1236.reforestry.api.arboriculture;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public interface IFruitPodType extends IBlockSubtype {
	TagKey<Block> logTag();

	default boolean useSmallAabb() {
		return false;
	}
}
