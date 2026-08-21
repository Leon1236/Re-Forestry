package com.leon1236.reforestry.extratrees.blocks;

import java.util.Locale;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.IFruitPodType;

public enum ExtraTreesPodType implements IFruitPodType {
	BANANA,
	RED_BANANA,
	PLANTAIN;

	public static final ExtraTreesPodType[] VALUES = values();

	private static final TagKey<Block> BANANA_LOGS = TagKey.create(Registries.BLOCK, ReForestry.id("banana_logs"));

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ROOT);
	}

	@Override
	public TagKey<Block> logTag() {
		return BANANA_LOGS;
	}
}
