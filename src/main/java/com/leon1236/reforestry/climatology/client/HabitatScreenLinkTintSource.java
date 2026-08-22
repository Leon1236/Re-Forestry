package com.leon1236.reforestry.climatology.client;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.climatology.items.ItemHabitatScreen;

public record HabitatScreenLinkTintSource() implements ItemTintSource {
	public static final HabitatScreenLinkTintSource INSTANCE = new HabitatScreenLinkTintSource();
	public static final MapCodec<HabitatScreenLinkTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

	@Override
	public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
		return ARGB.opaque(ItemHabitatScreen.isValid(stack, level) ? 0x14B276 : 0xBA1F17);
	}

	@Override
	public MapCodec<? extends ItemTintSource> type() {
		return MAP_CODEC;
	}
}
