package com.leon1236.reforestry.climatology.client;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.api.climate.IClimateHousing;
import com.leon1236.reforestry.climatology.items.ItemHabitatScreen;

public record HabitatScreenClimateTintSource() implements ItemTintSource {
	public static final HabitatScreenClimateTintSource INSTANCE = new HabitatScreenClimateTintSource();
	public static final MapCodec<HabitatScreenClimateTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

	@Override
	public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
		if (!ItemHabitatScreen.isValid(stack, level)) {
			return ARGB.opaque(0xFFFFFF);
		}
		BlockPos pos = ItemHabitatScreen.getLinkedPos(stack);
		if (pos == null || level == null) {
			return ARGB.opaque(0xFFFFFF);
		}
		BlockEntity be = level.getBlockEntity(pos);
		if (!(be instanceof IClimateHousing housing)) {
			return ARGB.opaque(0xFFFFFF);
		}
		return ARGB.opaque(housing.getTransformer().getCurrent().getTemperatureEnum().color);
	}

	@Override
	public MapCodec<? extends ItemTintSource> type() {
		return MAP_CODEC;
	}
}
