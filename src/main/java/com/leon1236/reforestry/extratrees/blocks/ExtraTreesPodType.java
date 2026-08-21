package com.leon1236.reforestry.extratrees.blocks;

import java.util.Locale;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.api.arboriculture.IFruitPodType;

public enum ExtraTreesPodType implements IFruitPodType {
	BANANA,
	RED_BANANA,
	PLANTAIN;

	public static final ExtraTreesPodType[] VALUES = values();

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ROOT);
	}

	@Override
	public TagKey<Block> logTag() {
		return BlockTags.LOGS;
	}
}
