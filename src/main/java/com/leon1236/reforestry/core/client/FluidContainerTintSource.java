package com.leon1236.reforestry.core.client;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.items.FluidContainerContents;

public record FluidContainerTintSource() implements ItemTintSource {
	public static final FluidContainerTintSource INSTANCE = new FluidContainerTintSource();
	public static final MapCodec<FluidContainerTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

	@Override
	public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
		FluidContainerContents contents = FluidContainerContents.get(stack);
		if (contents.amount() <= 0 || contents.variant().isBlank()) {
			return ARGB.opaque(0xffffff);
		}
		return ARGB.opaque(RenderUtil.getFluidColor(contents.variant().getFluid()));
	}

	@Override
	public MapCodec<? extends ItemTintSource> type() {
		return MAP_CODEC;
	}
}
